package org.s2.archie.json;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.time.Duration;
import java.time.Period;
import java.time.temporal.ChronoUnit;

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
        try(InputStream stream = getClass().getResourceAsStream("s2-EHR-Order.medication_order.v4.0.2.json")) {
            Archetype archetype = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant()).readValue(stream, Archetype.class);
            System.out.println(archetype);
            assertTrue(archetype.getGenerated());
            assertThat(archetype.getArchetypeId().getFullId(), is("s2-EHR-Order.medication_order.v4.0.2"));
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
        try(InputStream stream = getClass().getResourceAsStream("delirium_observation_screening.json")) {
            ObjectMapper objectMapper = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createLegacyConfiguration());
            Archetype archetype = objectMapper.readValue(stream, Archetype.class);
            String reserialized = objectMapper.writeValueAsString(archetype);
            //System.out.println(reserialized);
            objectMapper.readValue(reserialized, Archetype.class);
        }
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
