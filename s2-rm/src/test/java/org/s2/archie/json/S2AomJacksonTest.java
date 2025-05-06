package org.s2.archie.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Lists;
import com.nedap.archie.adlparser.ADLParser;
import com.nedap.archie.aom.Archetype;
import com.nedap.archie.aom.ArchetypeSlot;
import com.nedap.archie.aom.CComplexObject;
import com.nedap.archie.aom.primitives.CDuration;
import com.nedap.archie.aom.primitives.CString;
import com.nedap.archie.aom.primitives.CTerminologyCode;
import com.nedap.archie.aom.primitives.ConstraintStatus;
import com.nedap.archie.aom.rmoverlay.VisibilityType;
import com.nedap.archie.base.Interval;
import com.nedap.archie.json.ArchieJacksonConfiguration;
import org.apache.commons.io.IOUtils;
import org.s2.serialisation.json.S2RmJacksonUtil;
import com.nedap.archie.rules.BinaryOperator;
import com.nedap.archie.rules.Constraint;
import com.nedap.archie.rules.ModelReference;
import com.nedap.archie.rules.OperatorKind;
import com.nedap.archie.serializer.adl.ADLArchetypeSerializer;
import com.nedap.archie.testutil.TestUtil;
import org.junit.Test;
import org.openehr.referencemodels.AllMetaModelsInitialiser;
import org.threeten.extra.PeriodDuration;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * A test that tests JSON parsing of Archetypes using Jackson
 *
 * Created by pieter.bos on 06/07/16.
 */
public class S2AomJacksonTest {

    @Test
    public void parseMedicationOrder() throws Exception {
        try(InputStream stream = getClass().getResourceAsStream("s2-EHR-Order.medication_order.v5.0.0.json")) {
            Archetype archetype = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant()).readValue(stream, Archetype.class);
            System.out.println(archetype);
            assertTrue(archetype.getGenerated());
            assertThat(archetype.getArchetypeId().getFullId(), is("s2-EHR-Order.medication_order.v5.0.0"));
            assertThat(archetype.getDefinition().getRmTypeName(), is("Order"));
            CComplexObject infoNode = archetype.getDefinition().itemAtPath("/activities[id2]/description[id95]");
            assertThat(infoNode.getRmTypeName(), is("Info_node"));
            assertThat(infoNode.getNodeId(), is("id95"));
            assertThat(infoNode.getParent(), is(equalTo(archetype.getDefinition().itemAtPath("/activities[id2]/description"))));
            assertEquals("Administration method", infoNode.getTerm().getText());
            System.out.println(ADLArchetypeSerializer.serialize(archetype));
        }
    }

    @Test
    public void roundTripMedicationOrder() throws Exception {
        try(InputStream stream = getClass().getResourceAsStream("s2-EHR-Order.medication_order.v5.0.0.json")) {
            ArchieJacksonConfiguration config = ArchieJacksonConfiguration.createStandardsCompliant();
            config.setAlwaysIncludeTypeProperty(true);
            ObjectMapper objectMapper = S2RmJacksonUtil.getObjectMapper(config);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);


            String json = IOUtils.toString(stream, StandardCharsets.UTF_8);
            Archetype archetype = objectMapper.readValue(json, Archetype.class);
            String reserialized = objectMapper.writeValueAsString(archetype);
            //System.out.println(reserialized);
            //objectMapper.readValue(reserialized, Archetype.class);

            assertTrue(areJsonStringsEqual(json, reserialized, true));

        }
    }

    private boolean areJsonStringsEqual(String json1, String json2, boolean ignoreType) {
        ObjectMapper objectMapper = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant());
        try {
            JsonNode tree1 = objectMapper.readTree(json1);
            JsonNode tree2 = objectMapper.readTree(json2);
            return compareJsonNodes(tree1, tree2, "", ignoreType);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean compareJsonNodes(JsonNode node1, JsonNode node2, String path, boolean ignoreType) {
        boolean areEqual = true; // Assume nodes are equal initially

        // If both nodes are null, they are equal at this level
        if (node1 == null && node2 == null) {
            return true;
        }

        // If one node is null and the other is not, print the difference
        if (node1 == null) {
            System.out.println("Difference at path " + path + ": (first is null)");
            return false;
        }

        if (node2 == null) {
            System.out.println("Difference at path " + path + ": (second is null)");
            return false;
        }

        // If nodes are not equal at this level
        if (!node1.equals(node2)) {
            if (node1.isObject() && node2.isObject()) {
                Iterator<Map.Entry<String, JsonNode>> fields1 = node1.fields();
                Iterator<Map.Entry<String, JsonNode>> fields2 = node2.fields();

                // Compare fields of both JSON objects
                while (fields1.hasNext()) {
                    Map.Entry<String, JsonNode> entry1 = fields1.next();
                    String key = entry1.getKey();

                    // Skip the `_type` property if the ignoreType flag is set
                    if (ignoreType && key.equals("_type")) {
                        continue;
                    }

                    JsonNode value1 = entry1.getValue();
                    JsonNode value2 = node2.get(key); // Corresponding key in the second JSON

                    if (!compareJsonNodes(value1, value2, path + "/" + key, ignoreType)) {
                        areEqual = false; // Mark as not equal, but continue checking
                    }
                }

                // Check for keys that are in node2 but not in node1
                while (fields2.hasNext()) {
                    Map.Entry<String, JsonNode> entry2 = fields2.next();
                    String key = entry2.getKey();

                    // Skip the `_type` property if the ignoreType flag is set
                    if (ignoreType && key.equals("_type")) {
                        continue;
                    }

                    if (!node1.has(key)) {
                        System.out.println("Difference at path: " + path + "/" + key + " (Key missing in first JSON)");
                        areEqual = false;
                    }
                }
            } else if (node1.isArray() && node2.isArray()) {
                // Compare arrays
                int minSize = Math.min(node1.size(), node2.size());
                for (int i = 0; i < minSize; i++) {
                    if (!compareJsonNodes(node1.get(i), node2.get(i), path + "[" + i + "]", ignoreType)) {
                        areEqual = false; // Mark as not equal, but continue checking
                    }
                }

                // Check for additional elements in arrays
                if (node1.size() > node2.size()) {
                    System.out.println("Difference at path: " + path + " (Extra elements in first JSON array)");
                    areEqual = false;
                } else if (node2.size() > node1.size()) {
                    System.out.println("Difference at path: " + path + " (Extra elements in second JSON array)");
                    areEqual = false;
                }
            } else {
                // Leaf nodes are different
                System.out.println("Difference at path: " + path + " (Value1: " + node1 + ", Value2: " + node2 + ")");
                areEqual = false;
            }
        }

        return areEqual;
    }





    @Test
    public void cDuration() throws Exception {
        CDuration cDuration = new CDuration();
        cDuration.addConstraint(new Interval<>(Duration.of(-10, ChronoUnit.HOURS), Duration.of(10, ChronoUnit.SECONDS)));
        ObjectMapper objectMapper = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant());
        String cDurationJson = objectMapper.writeValueAsString(cDuration);
        assertTrue(cDurationJson.contains("-PT10H"));
        assertTrue(cDurationJson.contains("PT10S"));
        System.out.println(cDurationJson);

        CDuration parsedDuration = objectMapper.readValue(cDurationJson, CDuration.class);
        assertEquals(Lists.newArrayList(new Interval<>(Duration.of(-10, ChronoUnit.HOURS), Duration.of(10, ChronoUnit.SECONDS))), parsedDuration.getConstraint());
    }

    @Test
    public void cDurationPeriodDuration() throws Exception {
        CDuration cDuration = new CDuration();
        PeriodDuration tenYearsTenSeconds = PeriodDuration.of(Period.of(10, 0, 0), Duration.of(10, ChronoUnit.SECONDS));
        cDuration.addConstraint(new Interval<>(Duration.of(-10, ChronoUnit.HOURS), tenYearsTenSeconds));
        ObjectMapper objectMapper = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant());
        String cDurationJson = objectMapper.writeValueAsString(cDuration);
        assertTrue(cDurationJson.contains("-PT10H"));
        assertTrue(cDurationJson.contains("P10YT10S"));
        System.out.println(cDurationJson);

        CDuration parsedDuration = objectMapper.readValue(cDurationJson, CDuration.class);
        assertEquals(Lists.newArrayList(new Interval<>(Duration.of(-10, ChronoUnit.HOURS), tenYearsTenSeconds)), parsedDuration.getConstraint());
    }

    @Test
    public void cTerminologyCode() throws Exception {
        CTerminologyCode cTermCode = new CTerminologyCode();
        cTermCode.setConstraint(Lists.newArrayList("ac23"));
        cTermCode.setConstraintStatus(ConstraintStatus.PREFERRED);
        ObjectMapper objectMapper = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant());
        String json = objectMapper.writeValueAsString(cTermCode);

        assertTrue(json.contains("\"constraint_status\" : \"preferred\""));
        CTerminologyCode parsedTermCode = objectMapper.readValue(json, CTerminologyCode.class);
        assertEquals(cTermCode.getConstraint(), parsedTermCode.getConstraint());
        assertEquals(ConstraintStatus.PREFERRED, parsedTermCode.getConstraintStatus());
    }

}
