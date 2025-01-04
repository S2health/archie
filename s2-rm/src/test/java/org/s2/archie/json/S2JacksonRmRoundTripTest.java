package org.s2.archie.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.adlparser.ADLParser;
import com.nedap.archie.adlparser.modelconstraints.S2RmConstraintImposer;
import com.nedap.archie.aom.Archetype;
import com.nedap.archie.json.ArchieJacksonConfiguration;
import org.s2.rm.base.data_types.quantity.*;
import org.s2.rm.base.data_types.text.PlainText;
import org.s2.rm.base.data_types.uri.UriRef;
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
import java.util.ArrayList;

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
        InfoNode infoNode =  (InfoNode) testUtil.constructEmptyRMObject(archetype.getDefinition());
        InfoNode firstNode = (InfoNode) infoNode.getItems().get(0);

        Uuid uid = new Uuid("111111-2222-3333-444444");
        infoNode.setUid(uid);
        RMQueryContext queryContext = getQueryContext(infoNode);

        PlainText text = queryContext.find("/items[id2]/value");
        text.setText("test-text");

        Count count = queryContext.find("/items[id3]/value");
        count.setMagnitude(4);

        Quantity quantity = queryContext.find("/items[id4]/value");
        quantity.setMagnitude(new BigDecimal(23));

        Proportion proportion = queryContext.find("/items[id5]/value");
        proportion.setMagnitude(new BigDecimal(1));
        proportion.setDenominator(quantity);

        DateValue date = queryContext.find("/items[id6]/value");
        date.setMagnitude(new RmDate("2016-01-01"));

        TimeValue time = queryContext.find("/items[id7]/value");
        time.setMagnitude(new RmTime("12:00:00"));

        DateTimeValue datetime = queryContext.find("/items[id8]/value");
        datetime.setMagnitude(new RmDateTime("2016-01-01T12:00:00"));

        CodedOrdinal codedOrdinal = queryContext.find("/items[id9]/value");

        UriRef uriRef = queryContext.find("/items[id10]/value");
        uriRef.setValue("http://test.example.com");

        String json = S2RmJacksonUtil.getObjectMapper().writeValueAsString(infoNode);
        System.out.println(json);
        InfoNode parsedInfoNode = (InfoNode) S2RmJacksonUtil.getObjectMapper().readValue(json, InfoNode.class);
        RMQueryContext parsedQueryContext = getQueryContext(parsedInfoNode);

        assertThat(parsedQueryContext.<PlainText>find("/items[id2]/value").getText(), is("test-text"));
        assertThat(parsedQueryContext.<Quantity>find("/items[id4]/value").getMagnitude().intValue(), is(23));
        assertThat(parsedQueryContext.<DateValue>find("/items[id6]/value").getMagnitude().getValue(), is("2016-01-01"));
        assertThat(parsedQueryContext.<DateTimeValue>find("/items[id8]/value").getMagnitude().getValue(), is("2016-01-01T12:00:00"));
        assertThat(parsedQueryContext.<TimeValue>find("/items[id7]/value").getMagnitude().getValue(), is("12:00:00"));
        assertThat(parsedQueryContext.<UriRef>find("/items[id10]/value").getValue(), is("http://test.example.com"));
        assertThat(parsedInfoNode.getUid().getValue(), is("111111-2222-3333-444444"));
        assertThat(parsedInfoNode.getArchetypeNodeId(), is("id1"));

    }

    private RMQueryContext getQueryContext(InfoNode infoNode) {
        return new RMQueryContext(S2RmInfoLookup.getInstance(), infoNode, S2RmJaxbUtil.getArchieJAXBContext());
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
