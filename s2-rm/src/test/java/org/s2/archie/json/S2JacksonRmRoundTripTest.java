package org.s2.archie.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.adlparser.ADLParser;
import com.nedap.archie.adlparser.modelconstraints.S2RmConstraintImposer;
import com.nedap.archie.aom.Archetype;
import com.nedap.archie.json.ArchieJacksonConfiguration;
import org.s2.rm.base.data_types.quantity.DateTimeValue;
import org.s2.rm.base.data_types.quantity.DateValue;
import org.s2.rm.base.data_types.quantity.Quantity;
import org.s2.rm.base.data_types.quantity.TimeValue;
import org.s2.rm.base.data_types.text.PlainText;
import org.s2.rm.base.foundation_types.primitive_types.Uri;
import org.s2.rm.base.foundation_types.terminology.TerminologyCode;
import org.s2.rm.base.foundation_types.terminology.TerminologyTerm;
import org.s2.rm.base.foundation_types.time.RmDate;
import org.s2.rm.base.foundation_types.time.RmDateTime;
import org.s2.rm.base.foundation_types.time.RmTime;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rminfo.S2RmInfoLookup;
import org.s2.serialisation.json.S2RmJacksonUtil;
import com.nedap.archie.query.RMQueryContext;
import com.nedap.archie.testutil.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.s2.rm.care.composition.Composition;
import org.s2.rm.base.patterns.data_structures.InfoNode;
import org.s2.serialisation.xml.S2RmJaxbUtil;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.hamcrest.CoreMatchers.is;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Tests JSON serialization and deserialization of RM Objects using Jackson
 *
 * Created by pieter.bos on 30/06/16.
 */
public class S2JacksonRmRoundTripTest {

    private ADLParser parser;
    private Archetype archetype;

    private TestUtil testUtil;

    @Before
    public void setup() {
        testUtil = new TestUtil(S2RmInfoLookup.getInstance());
        parser = new ADLParser(new S2RmConstraintImposer());
    }

    @Test
    public void dataValues() throws Exception {
        archetype = parser.parse(S2JacksonRmRoundTripTest.class.getResourceAsStream("s2-EHR-Info_node.datavalues.v1.0.0.adls"));
        InfoNode cluster =  (InfoNode) testUtil.constructEmptyRMObject(archetype.getDefinition());
        Uuid uid = new Uuid("111111-2222-3333-444444");
        cluster.setUid(uid);
        RMQueryContext queryContext = getQueryContext(cluster);
        PlainText text = queryContext.find("/items['Text']/value");
        text.setText("test-text");
        Quantity quantity = queryContext.find("/items['Quantity']/value");
        quantity.setMagnitude(new BigDecimal(23));
        DateValue date = queryContext.find("/items['Date']/value");
        date.setMagnitude(new RmDate("2016-01-01"));

        DateTimeValue datetime = queryContext.find("/items['Datetime']/value");
        datetime.setMagnitude(new RmDateTime("2016-01-01T12:00:00"));

        TimeValue time = queryContext.find("/items['Time']/value");
        time.setMagnitude(new RmTime("12:00:00"));

        Uri uri = queryContext.find("/items['Uri']/value");
        uri.setValue("http://test.example.com");

        String json = S2RmJacksonUtil.getObjectMapper().writeValueAsString(cluster);
        System.out.println(json);
        InfoNode parsedInfoNode = (InfoNode) S2RmJacksonUtil.getObjectMapper().readValue(json, InfoNode.class);
        RMQueryContext parsedQueryContext = getQueryContext(parsedInfoNode);

        assertThat(parsedQueryContext.<PlainText>find("/items['Text']/value").getText(), is("test-text"));
        assertThat(parsedQueryContext.<Quantity>find("/items['Quantity']/value").getMagnitude(), is(23d));
        assertThat(parsedQueryContext.<DateValue>find("/items['Date']/value").getMagnitude(), is(LocalDate.of(2016, 1, 1)));
        assertThat(parsedQueryContext.<DateTimeValue>find("/items['Datetime']/value").getMagnitude(), is(LocalDateTime.of(2016, 1, 1, 12, 00)));
        assertThat(parsedQueryContext.<TimeValue>find("/items['Time']/value").getMagnitude(), is(LocalTime.of(12, 0)));
        assertThat(parsedQueryContext.<Uri>find("/items['Uri']/value").getValue(), is(URI.create("http://test.example.com")));
        assertThat(parsedInfoNode.getUid().getValue(), is("SOME_UUID"));
        assertThat(parsedInfoNode.getArchetypeNodeId(), is("id1"));

    }

    private RMQueryContext getQueryContext(InfoNode cluster) {
        return new RMQueryContext(S2RmInfoLookup.getInstance(), cluster, S2RmJaxbUtil.getArchieJAXBContext());
    }

    @Test
    public void composition() throws Exception {
        Composition composition = new Composition();
        composition.setCategory(new TerminologyTerm("persistent", new TerminologyCode("openEhr", "123")));
        composition.setTerritory(new TerminologyCode("openEhr", "456"));
        composition.setLanguage(new TerminologyCode("openEhr", "nl"));
        ArchieJacksonConfiguration config = ArchieJacksonConfiguration.createStandardsCompliant();
        //include the type property name so we can parse it as an RmObject here.
        config.setAlwaysIncludeTypeProperty(true);

        ObjectMapper objectMapper = S2RmJacksonUtil.getObjectMapper();
        String json = objectMapper.writeValueAsString(composition);

        Composition parsedComposition = (Composition) objectMapper.readValue(json, Composition.class);
        assertEquals(composition.getCategory().getConcept().getCodeString(), parsedComposition.getCategory().getConcept().getCodeString());
        assertEquals(composition.getLanguage().getCodeString(), parsedComposition.getLanguage().getCodeString());
        assertEquals(composition.getTerritory().getCodeString(), parsedComposition.getTerritory().getCodeString());
    }

}
