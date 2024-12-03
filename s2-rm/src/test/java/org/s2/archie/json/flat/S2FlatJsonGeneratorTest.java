package org.s2.archie.json.flat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.nedap.archie.ArchieLanguageConfiguration;
import com.nedap.archie.adlparser.ADLParseException;
import com.nedap.archie.adlparser.ADLParser;
import com.nedap.archie.aom.Archetype;
import com.nedap.archie.aom.OperationalTemplate;
import com.nedap.archie.creation.ExampleJsonInstanceGenerator;
import com.nedap.archie.flattener.Flattener;
import com.nedap.archie.flattener.FlattenerConfiguration;
import com.nedap.archie.flattener.SimpleArchetypeRepository;
import com.nedap.archie.json.flat.DuplicateKeyException;
import com.nedap.archie.json.flat.FlatJsonExampleInstanceGenerator;
import com.nedap.archie.json.flat.FlatJsonFormatConfiguration;
import com.nedap.archie.json.flat.FlatJsonGenerator;
import com.nedap.archie.rminfo.MetaModels;
import org.junit.After;
import org.junit.Test;
import org.openehr.referencemodels.AllMetaModelsInitialiser;
import org.s2.rm.base.data_types.encapsulated.Multimedia;
import org.s2.rm.base.data_types.quantity.Count;
import org.s2.rm.base.data_types.text.PlainText;
import org.s2.rm.base.patterns.data_structures.InfoNode;
import org.s2.rm.care.entry.DirectObservation;
import org.s2.rminfo.S2RmInfoLookup;
import org.s2.serialisation.json.S2RmJacksonUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class S2FlatJsonGeneratorTest {

    private static final String BLOOD_PRESSURE_PATH = "/s2-models/archetypes/CARE/Entry/Observation/Direct_observation/s2-EHR-Direct_observation.blood_pressure.v3.0.0.adls";
    private static final double EPSILON = 0.00000001d;

    @After
    public void tearDown() {
        ArchieLanguageConfiguration.setThreadLocalDescriptiongAndMeaningLanguage(null);
    }

    @Test
    public void testBloodPressureExample() throws Exception {

        OperationalTemplate bloodPressureOpt = parseBloodPressure();
        FlatJsonFormatConfiguration config = FlatJsonFormatConfiguration.standardFormatInDevelopment();
        config.setFilterNames(false);
        config.setFilterTypes(false);
        config.setWritePipesForPrimitiveTypes(false);
        Map<String, Object> stringObjectMap = createExampleInstance(bloodPressureOpt, config);

        System.out.println(S2RmJacksonUtil.getObjectMapper().writeValueAsString(stringObjectMap));

        //type property
        assertEquals("DirectObservation", stringObjectMap.get("/_type"));
        //just a string
        assertEquals("Systolic", stringObjectMap.get("/data[id5]/name"));
        //ignored field
        assertFalse(stringObjectMap.containsKey("/data[id5]/archetype_node_id"));
        //ignored field
        assertFalse(stringObjectMap.containsKey("/archetype_details"));
        //date time format
        assertEquals("2018-01-01T12:00:00Z", stringObjectMap.get("/data_series[id1043]/time"));
        //numbers
        assertEquals(0.0d, (Double) stringObjectMap.get("/data[id5]/value/value/magnitude"), EPSILON);
        assertEquals(0l, ((Long) stringObjectMap.get("/data[id5]/value/value/precision")).longValue());
        //test indices
        assertEquals("Systolic", stringObjectMap.get("/data[id5]/name"));

    }



    @Test
    public void testBloodPressureExampleWithPipesForFinalFields() throws Exception {

        OperationalTemplate bloodPressureOpt = parseBloodPressure();
        FlatJsonFormatConfiguration config = FlatJsonFormatConfiguration.standardFormatInDevelopment();
        config.setFilterNames(false);
        config.setFilterTypes(false);
        Map<String, Object> stringObjectMap = createExampleInstance(bloodPressureOpt, config);

        System.out.println(S2RmJacksonUtil.getObjectMapper().writeValueAsString(stringObjectMap));

        //type property
        assertEquals("DirectObservation", stringObjectMap.get("/_type"));
        //just a string
        assertEquals("Systolic", stringObjectMap.get("/data[id5]/name"));
        //ignored field
        assertFalse(stringObjectMap.containsKey("/data[id5]|archetype_node_id"));
        //ignored field
        assertFalse(stringObjectMap.containsKey("/archetype_details"));
        //date time format
        assertEquals("2018-01-01T12:00:00Z", stringObjectMap.get("/data_series[id1043]/time"));
        //numbers
        assertEquals(0.0d, (Double) stringObjectMap.get("/data[id5]/value/value|magnitude"), EPSILON);
        assertEquals(0l, ((Long) stringObjectMap.get("/data[id5]/value/value|precision")).longValue());
        //test indices
        assertEquals("Systolic", stringObjectMap.get("/data[id5]/name"));
    }

    @Test
    public void testNedapInternalFormat() throws Exception {
        OperationalTemplate bloodPressureOpt = parseBloodPressure();
        FlatJsonFormatConfiguration config = FlatJsonFormatConfiguration.nedapInternalFormat();
        config.setFilterNames(false);
        config.setFilterTypes(false);
        config.setWritePipesForPrimitiveTypes(false);
        Map<String, Object> stringObjectMap = createExampleInstance(bloodPressureOpt, config);

        System.out.println(S2RmJacksonUtil.getObjectMapper().writeValueAsString(stringObjectMap));

        //type property
        assertEquals("DirectObservation", stringObjectMap.get("/@type"));
        //just a string
        assertEquals("Systolic", stringObjectMap.get("/data[id5,1]/name"));
        //ignored field
        assertFalse(stringObjectMap.containsKey("/data[id5]/archetype_node_id"));
        //ignored field
        assertFalse(stringObjectMap.containsKey("/archetype_details"));
        //date time format
        assertEquals("2018-01-01T12:00:00Z", stringObjectMap.get("/data_series[id1043]/time"));
        //numbers
        assertEquals(0.0d, (Double) stringObjectMap.get("/data[id5,1]/value/magnitude"), EPSILON);
        assertEquals(0l, ((Long) stringObjectMap.get("/data[id5,1]/value/precision")).longValue());
        //test indices
        assertEquals("Systolic", stringObjectMap.get("/data[id5,1]/name/value"));
    }

    @Test
    public void continuesIndices() throws Exception {
        InfoNode cluster = new InfoNode();

        cluster.addItem(new InfoNode("id2", "first"));
        cluster.setValue(new PlainText("First"));

        cluster.addItem(new InfoNode("id3", "2"));
        cluster.setValue(new Count(2));

        cluster.addItem(new InfoNode("id2", "third"));
        cluster.setValue(new PlainText("Third"));

        cluster.addItem(new InfoNode("id3", "4"));
        cluster.setValue(new Count(4));

        FlatJsonFormatConfiguration config = FlatJsonFormatConfiguration.nedapInternalFormat();
        Map<String, Object> stringObjectMap = new FlatJsonGenerator(S2RmInfoLookup.getInstance(), config).buildPathsAndValues(cluster);

        System.out.println(S2RmJacksonUtil.getObjectMapper().writeValueAsString(stringObjectMap));

        Map<String, Object> expected = new LinkedHashMap<>();
        expected.put("/@type", "InfoNode");
        expected.put("/items[id2,1]/@type", "InfoNode");
        expected.put("/items[id2,1]/value/@type", "PlainText");
        expected.put("/items[id2,1]/value/value", "First");
        expected.put("/items[id3,2]/@type", "InfoNode");
        expected.put("/items[id3,2]/value/@type", "Count");
        expected.put("/items[id3,2]/value/magnitude", 2L);
        expected.put("/items[id2,3]/@type", "InfoNode");
        expected.put("/items[id2,3]/value/@type", "PlainText");
        expected.put("/items[id2,3]/value/value", "Third");
        expected.put("/items[id3,4]/@type", "InfoNode");
        expected.put("/items[id3,4]/value/@type", "Count");
        expected.put("/items[id3,4]/value/magnitude", 4L);

        assertEquals(expected, stringObjectMap);
    }

    @Test
    public void serializeBytes() throws Exception {
        Multimedia multimedia = new Multimedia();
        Byte[] bytes = new Byte[]{42, 83, 120, -128, 127, 30, -80, 15};
        multimedia.setData(Arrays.asList(bytes));

        FlatJsonGenerator flatJsonGenerator = new FlatJsonGenerator(S2RmInfoLookup.getInstance(), FlatJsonFormatConfiguration.nedapInternalFormat());

        Map<String, Object> pathsAndValues = flatJsonGenerator.buildPathsAndValues(multimedia);
        assertEquals("{\"/@type\":\"Multimedia\",\"/data\":\"KlN4gH8esA8=\"}", new JsonMapper().writeValueAsString(pathsAndValues));
    }

    @Test
    public void dontSerializeNames() throws  Exception {
        OperationalTemplate bloodPressureOpt = parseBloodPressure();
        FlatJsonFormatConfiguration config = FlatJsonFormatConfiguration.nedapInternalFormat();
        config.setWritePipesForPrimitiveTypes(false);
        config.setFilterNames(true);
        config.setFilterTypes(false);
        //config.getIgnoredAttributes().add(new AttributeReference("LOCATABLE", "name"));
        Map<String, Object> stringObjectMap = new FlatJsonExampleInstanceGenerator().generateExample(bloodPressureOpt, AllMetaModelsInitialiser.getMetaModels(), "en", config);

        System.out.println(S2RmJacksonUtil.getObjectMapper().writeValueAsString(stringObjectMap));

        //type property
        assertEquals("OBSERVATION", stringObjectMap.get("/@type"));
        //no name
        assertNull(stringObjectMap.get("/data[id5]/name/value"));
        //ignored field
        assertFalse(stringObjectMap.containsKey("/data[id5]/archetype_node_id"));
        //ignored field
        assertFalse(stringObjectMap.containsKey("/archetype_details"));
        //date time format
        assertEquals("2018-01-01T12:00:00Z", stringObjectMap.get("/data_series[id1043]/time"));
        //numbers
        assertEquals(0.0d, (Double) stringObjectMap.get("/data[id5,1]/value/value/magnitude"), EPSILON);
        assertEquals(0l, ((Long) stringObjectMap.get("/data[id5,1]/value/value/precision")).longValue());
        //no name
        assertNull(stringObjectMap.get("/data[id5]/name/value"));
    }

    @Test
    public void dontSerializeTypes() throws  Exception {
        OperationalTemplate bloodPressureOpt = parseBloodPressure();
        FlatJsonFormatConfiguration config = FlatJsonFormatConfiguration.nedapInternalFormat();
        config.setWritePipesForPrimitiveTypes(false);
        config.setFilterNames(true);
        config.setFilterTypes(true);
        //config.getIgnoredAttributes().add(new AttributeReference("LOCATABLE", "name"));
        MetaModels metaModels = AllMetaModelsInitialiser.getMetaModels();
        metaModels.selectModel(bloodPressureOpt);

        ExampleJsonInstanceGenerator exampleJsonInstanceGenerator = new ExampleJsonInstanceGenerator(metaModels, "en");
        exampleJsonInstanceGenerator.setTypePropertyName("_type");
        Map<String, Object> generatedExample = exampleJsonInstanceGenerator.generate(bloodPressureOpt);
        ObjectMapper objectMapper = metaModels.getSelectedModel().getJsonObjectMapper();
        String jsonRmObject = objectMapper.writeValueAsString(generatedExample);
        DirectObservation bloodPressure = objectMapper.readValue(jsonRmObject, DirectObservation.class);
        //set the name to be different from the archetype, so it will be added here
        bloodPressure.setName("different from archetype");

        Map<String, Object> stringObjectMap = new FlatJsonGenerator(metaModels.getSelectedModelInfoLookup(), config).buildPathsAndValues(bloodPressure, bloodPressureOpt, "en");

        System.out.println(S2RmJacksonUtil.getObjectMapper().writeValueAsString(stringObjectMap));

        //no type at root
        assertNull(stringObjectMap.get("/@type"));
        //no name when the same as archetype
        assertNull(stringObjectMap.get("/data[id5]/name"));
        assertEquals("different from archetype", stringObjectMap.get("/name"));
        //type here is different than in archetype: POINT_EVENT in data, EVENT in archetype
        //so it must be included in the flat format
        assertEquals("POINT_EVENT", stringObjectMap.get("/data_series[id1043]/items[id7,1]/@type"));
        //TODO: type when alternatives exist
        //no type at Element
        assertNull(stringObjectMap.get("/data[id5]/@type"));

        //ignored field
        assertFalse(stringObjectMap.containsKey("/data[id5]/archetype_node_id"));
        //ignored field
        assertFalse(stringObjectMap.containsKey("/archetype_details"));
        //date time format
        assertEquals("2018-01-01T12:00:00Z", stringObjectMap.get("/data_series[id1043]/time"));
        //numbers
        assertEquals(0.0d, (Double) stringObjectMap.get("/data[id5,1]/value/magnitude"), EPSILON);
        assertEquals(0l, ((Long) stringObjectMap.get("/data[id5,1]/value/precision")).longValue());
        //no name
        assertNull(stringObjectMap.get("/data[id5]/name/value"));
    }

    @Test
    public void restoresThreadLocalDescriptionAndMeaningLanguage() throws  Exception {
        OperationalTemplate bloodPressureOpt = parseBloodPressure();
        FlatJsonFormatConfiguration config = FlatJsonFormatConfiguration.nedapInternalFormat();

        MetaModels metaModels = AllMetaModelsInitialiser.getMetaModels();
        metaModels.selectModel(bloodPressureOpt);

        ExampleJsonInstanceGenerator exampleJsonInstanceGenerator = new ExampleJsonInstanceGenerator(metaModels, "en");
        exampleJsonInstanceGenerator.setTypePropertyName("_type");
        Map<String, Object> generatedExample = exampleJsonInstanceGenerator.generate(bloodPressureOpt);
        ObjectMapper objectMapper = metaModels.getSelectedModel().getJsonObjectMapper();
        String jsonRmObject = objectMapper.writeValueAsString(generatedExample);
        DirectObservation bloodPressure = objectMapper.readValue(jsonRmObject, DirectObservation.class);

        ArchieLanguageConfiguration.setThreadLocalDescriptiongAndMeaningLanguage("nl");

        new FlatJsonGenerator(metaModels.getSelectedModelInfoLookup(), config).buildPathsAndValues(bloodPressure, bloodPressureOpt, "en");

        // The ThreadLocalDescriptiongAndMeaningLanguage should be restored to nl after the buildPathsAndValues call.
        assertEquals("nl", ArchieLanguageConfiguration.getThreadLocalDescriptiongAndMeaningLanguage());
    }

    /**
     * Test filtering two types where alternatives in the archetype are possible, without a node id being persent to separate the different types
     */
    @Test
    public void filterTypesWithAlternatives() throws Exception {
        OperationalTemplate bloodPressureOpt = parseTypeAlternatives();
        FlatJsonFormatConfiguration config = FlatJsonFormatConfiguration.nedapInternalFormat();
        config.setFilterNames(true);
        config.setFilterTypes(true);
        //config.getIgnoredAttributes().add(new AttributeReference("LOCATABLE", "name"));
        Map<String, Object> stringObjectMap = new FlatJsonExampleInstanceGenerator().generateExample(bloodPressureOpt, AllMetaModelsInitialiser.getMetaModels(), "en", config);

        System.out.println(S2RmJacksonUtil.getObjectMapper().writeValueAsString(stringObjectMap));

        //the one data value
        assertEquals("true", stringObjectMap.get("/items[id2,1]/value/value"));
        //the type
        assertEquals("BooleanValue", stringObjectMap.get("/items[id2,1]/value/@type"));
        //and nothing else!
        assertEquals(2, stringObjectMap.size());

    }


    private OperationalTemplate parseBloodPressure() throws IOException, ADLParseException {
        try (InputStream stream = getClass().getResourceAsStream(BLOOD_PRESSURE_PATH)) {
            Archetype bloodPressure = new ADLParser(AllMetaModelsInitialiser.getMetaModels()).parse(stream);
            Flattener flattener = new Flattener(new SimpleArchetypeRepository(), AllMetaModelsInitialiser.getMetaModels(), FlattenerConfiguration.forOperationalTemplate());
            return (OperationalTemplate) flattener.flatten(bloodPressure,0);
        }
    }

    private OperationalTemplate parseTypeAlternatives() throws IOException, ADLParseException {
        try (InputStream stream = getClass().getResourceAsStream("s2-EHR-Info_node.element_with_two_dv_types.v1.0.0.adls")) {
            Archetype typeAlternatives = new ADLParser(AllMetaModelsInitialiser.getMetaModels()).parse(stream);
            Flattener flattener = new Flattener(new SimpleArchetypeRepository(), AllMetaModelsInitialiser.getMetaModels(), FlattenerConfiguration.forOperationalTemplate());
            return (OperationalTemplate) flattener.flatten(typeAlternatives,0);
        }
    }

    private Map<String, Object> createExampleInstance(OperationalTemplate bloodPressureOpt, FlatJsonFormatConfiguration config) throws IOException, DuplicateKeyException {
        return new FlatJsonExampleInstanceGenerator().generateExample(bloodPressureOpt, AllMetaModelsInitialiser.getMetaModels(), "en", config);
    }
}
