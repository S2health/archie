package org.s2.archie.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.json.ArchieJacksonConfiguration;
import org.s2.rm.base.foundation_types.time.RmDateTime;
import org.s2.serialisation.json.S2RmJacksonUtil;
import org.junit.Test;
import org.s2.rm.care.composition.Composition;
import org.s2.rm.base.data_types.quantity.DateTimeValue;
import org.s2.rm.base.data_types.quantity.DurationValue;
import org.s2.rm.base.data_types.text.PlainText;
import org.threeten.extra.PeriodDuration;

import java.io.InputStream;
import java.time.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class S2RmJacksonTest {

    /*
    @Test
    public void parseEhrBaseJsonExample() throws Exception {
        try(InputStream stream = getClass().getResourceAsStream("pablos_example.json")) {
            ArchieJacksonConfiguration configuration = ArchieJacksonConfiguration.createStandardsCompliant();
            Composition parsed = S2RmJacksonUtil.getObjectMapper(configuration).readValue(stream, Composition.class);
            assertEquals("__THIS_SHOULD_BE_MODIFIED_BY_THE_TEST_::piri.ehrscape.com::1", parsed.getUid().getValue());
            assertEquals("openEHR-EHR-COMPOSITION.report-mnd.v1", parsed.getArchetypeNodeId());
            
            String json = S2RmJacksonUtil.getObjectMapper(configuration).writeValueAsString(parsed);
            ObjectMapper simpleMapper = new ObjectMapper();
            LinkedHashMap mapped = simpleMapper.readValue(json, LinkedHashMap.class);
            assertEquals("openEHR-EHR-COMPOSITION.report-mnd.v1", mapped.get("archetype_node_id"));
            Map uidMap = (Map) mapped.get("uid");
            assertEquals("__THIS_SHOULD_BE_MODIFIED_BY_THE_TEST_::piri.ehrscape.com::1", uidMap.get("value"));
        }
    }

     */

    @Test
    public void parseDuration() throws Exception {
        String json = "{\n" +
                "  \"_type\": \"Duration_value\",\n" +
                "  \"magnitude\": \"PT12H20S\"\n" +
                "}";
        ObjectMapper objectMapper = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant());
        DurationValue durationValue = objectMapper.readValue(json, DurationValue.class);
        assertEquals("PT12H20S", durationValue.getMagnitude().getValue());
    }

    @Test
    public void parseNegativeDuration() throws Exception {
        String json = "{\n" +
                "  \"_type\": \"Duration_value\",\n" +
                "  \"magnitude\": \"-PT12H20S\"\n" +
                "}";
        ObjectMapper objectMapper = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant());
        DurationValue durationValue = objectMapper.readValue(json, DurationValue.class);
        assertEquals("-PT12H20S", durationValue.getMagnitude().getValue());

    }

    @Test
    public void parsePeriodDuration() throws Exception {
        String json = "{\n" +
                "  \"_type\": \"Duration_value\",\n" +
                "  \"magnitude\": \"-P10Y10DT12H20S\"\n" +
                "}";
        ObjectMapper objectMapper = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant());
        DurationValue durationValue = objectMapper.readValue(json, DurationValue.class);
        assertEquals("-P10Y10DT12H20S", durationValue.getMagnitude().getValue());

    }

    @Test
    public void emptyDvTextIsIncluded() throws JsonProcessingException {
        ArchieJacksonConfiguration configuration = ArchieJacksonConfiguration.createStandardsCompliant();
        configuration.setSerializeEmptyCollections(false);
        configuration.setAlwaysIncludeTypeProperty(true);
        ObjectMapper objectMapper = S2RmJacksonUtil.getObjectMapper(configuration);
        PlainText plainText = new PlainText("");
        String actualJson = objectMapper.writeValueAsString(plainText);
        assertEquals(
                        removeWhiteSpaces("{\n"
                                + "  \"_type\" : \"Plain_text\",\n"
                                + "  \"text\" : \"\"\n"
                                + "}"), removeWhiteSpaces(actualJson));
    }

    @Test
    public void emptyCollectionIsNotIncluded() throws JsonProcessingException {
        ArchieJacksonConfiguration configuration = ArchieJacksonConfiguration.createStandardsCompliant();;
        configuration.setSerializeEmptyCollections(false);
        configuration.setAlwaysIncludeTypeProperty(true);
        ObjectMapper objectMapper = S2RmJacksonUtil.getObjectMapper(configuration);
        PlainText plainText = new PlainText("");

        String actualJson = objectMapper.writeValueAsString(plainText);
        assertEquals(
                removeWhiteSpaces("{\n"
                        + "  \"_type\" : \"Plain_text\",\n"
                        + "  \"text\" : \"\"\n"
                        + "}"), removeWhiteSpaces(actualJson));
    }

    @Test
    public void emptyCollectionInCollection() throws JsonProcessingException {
        ArchieJacksonConfiguration configuration = ArchieJacksonConfiguration.createStandardsCompliant();;
        configuration.setSerializeEmptyCollections(false);
        ObjectMapper objectMapper = S2RmJacksonUtil.getObjectMapper(configuration);
        Map<String, Map<String, String>> map = new LinkedHashMap<>();
        map.put("test", new LinkedHashMap<>());


        String actualJson = objectMapper.writeValueAsString(map);
        assertEquals(
                removeWhiteSpaces("{\"test\":{}}"),
                removeWhiteSpaces(actualJson));
    }

    @Test
    public void serializeDateTimeValue() throws Exception {
        ObjectMapper objectMapper = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant());
        DateTimeValue dateTime = new DateTimeValue(new RmDateTime("2015-01-01T12:10:12,00"));
        String dateTimeString = objectMapper.writeValueAsString(dateTime);
        assertTrue(dateTimeString.contains("\"2015-01-01T12:10:12,00\""));
        DateTimeValue parsedDateTime = objectMapper.readValue(dateTimeString, DateTimeValue.class);
        assertEquals(dateTime.getMagnitude(), parsedDateTime.getMagnitude());

    }

    String removeWhiteSpaces(String input) {
        return input.replaceAll("\\s+", "");
    }

}
