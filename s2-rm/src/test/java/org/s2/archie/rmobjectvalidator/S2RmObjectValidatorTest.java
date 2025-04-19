package org.s2.archie.rmobjectvalidator;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.nedap.archie.adlparser.ADLParseException;
import com.nedap.archie.aom.Archetype;
import com.nedap.archie.aom.OperationalTemplate;
import com.nedap.archie.flattener.Flattener;
import com.nedap.archie.flattener.FlattenerConfiguration;
import com.nedap.archie.flattener.FullArchetypeRepository;
import com.nedap.archie.json.ArchieJacksonConfiguration;
import com.nedap.archie.rminfo.MetaModels;
import com.nedap.archie.rmobjectvalidator.*;
import com.nedap.archie.testutil.ArchetypeRepositoryBuilder;
import com.nedap.archie.testutil.TestUtil;
import org.junit.BeforeClass;
import org.junit.Test;
import org.s2.rm.base.data_types.text.PlainText;
import org.s2.rm.base.patterns.data_structures.InfoNode;
import org.s2.rm.base.patterns.data_structures.Node;
import org.s2.rm.care.composition.Composition;
import org.s2.rm.entity.social_entity.Person;
import org.s2.rminfo.S2RmInfoLookup;
import org.s2.rminfo.S2RmMetaModelsInitialiser;
import org.s2.serialisation.json.S2RmJacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.List;

import static org.junit.Assert.*;

public class S2RmObjectValidatorTest {

    private static TestUtil testUtil;

    private static final Logger logger = LoggerFactory.getLogger(S2RmObjectValidatorTest.class);

    private static FullArchetypeRepository  repository;

    private static MetaModels metaModels;

    private static RMObjectValidator s2EhrValidator;

    private static RMObjectValidator s2EntityValidator;

    @BeforeClass
    public static void setup() {
        testUtil = new TestUtil(S2RmInfoLookup.getInstance());

        repository = ArchetypeRepositoryBuilder.parseRepository (S2RmObjectValidatorTest.class, "s2-models");
        logger.info("archetypes parsed: " + repository.getAllArchetypes().size());

        metaModels = new S2RmMetaModelsInitialiser().getMetaModels();
        repository.compile(metaModels);

        metaModels.selectModel("s2", "EHR", "0.8.7");
        s2EhrValidator = new RMObjectValidator(metaModels.getSelectedModel(), repository,
                new ValidationConfiguration.Builder().validateInvariants(false).build());

        metaModels.selectModel("s2", "ENTITY", "0.8.7");
        s2EntityValidator = new RMObjectValidator(metaModels.getSelectedModel(), repository,
                new ValidationConfiguration.Builder().validateInvariants(false).build());
    }

    @Test
    public void emptyTest() throws Exception {}

    @Test
    public void validCBC() throws Exception {
        Archetype archetype = repository.getArchetype("s2-EHR-Composition.t_lab_report-CBC.v1.1.0");
        OperationalTemplate opt = createOpt(archetype);

        InputStream stream = getClass().getResourceAsStream("/s2-synth-data/fixed/s2-EHR-Composition.t_lab_report-CBC.v1.0.0.json");
        Composition composition = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant()).readValue(stream, Composition.class);

        List<RMObjectValidationMessage> validationMessages = s2EhrValidator.validate(opt, composition);
        logMessages(validationMessages);

        assertEquals("There should be 0 error", 0, validationMessages.size());

    }

    @Test
    public void validatedCBC_WithWrongCode() throws Exception {
        Archetype archetype = repository.getArchetype("s2-EHR-Composition.t_lab_report-CBC.v1.1.0");
        OperationalTemplate opt = createOpt(archetype);

        InputStream stream = getClass().getResourceAsStream("/s2-test-data/fail/s2-EHR-Composition.t_lab_report-CBC_wrong_code.json");
        Composition composition = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant()).readValue(stream, Composition.class);

        List<RMObjectValidationMessage> validationMessages = s2EhrValidator.validate(opt, composition);
        logMessages(validationMessages);

        assertEquals("There should be 2 errors", 2, validationMessages.size());

    }

    @Test
    public void validatedCBC_WithWrongDatatype() throws Exception {
        Archetype archetype = repository.getArchetype("s2-EHR-Composition.t_lab_report-CBC.v1.1.0");
        OperationalTemplate opt = createOpt(archetype);

        InputStream stream = getClass().getResourceAsStream("/s2-test-data/fail/s2-EHR-Composition.t_lab_report-CBC_wrong_datatype.json");
        Composition composition = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant()).readValue(stream, Composition.class);

        List<RMObjectValidationMessage> validationMessages = s2EhrValidator.validate(opt, composition);
        logMessages(validationMessages);

        assertEquals("There should be 1 errors", 1, validationMessages.size());

    }

    @Test
    public void validTermInValueset() throws Exception {
        Archetype archetype = repository.getArchetype("s2-EHR-Composition.t_lab_report-ABO-Rh.v1.1.0");
        OperationalTemplate opt = createOpt(archetype);

        InputStream stream = getClass().getResourceAsStream("/s2-synth-data/fixed/s2-EHR-Composition.t_lab_report-ABO-Rh.v1.0.0.json");
        Composition composition = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant()).readValue(stream, Composition.class);

        List<RMObjectValidationMessage> validationMessages = s2EhrValidator.validate(opt, composition);
        logMessages(validationMessages);

        assertEquals("There should be 0 errors", 0, validationMessages.size());
    }

    @Test
    public void validateTermNotInValueset() throws Exception {
        Archetype archetype = repository.getArchetype("s2-EHR-Composition.t_lab_report-ABO-Rh.v1.1.0");
        OperationalTemplate opt = createOpt(archetype);

        InputStream stream = getClass().getResourceAsStream("/s2-test-data/fail/s2-term_not_in_value_set-report_status.json");
        Composition composition = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant()).readValue(stream, Composition.class);

        List<RMObjectValidationMessage> validationMessages = s2EhrValidator.validate(opt, composition);
        logMessages(validationMessages);

        assertEquals("There should be 1 errors", 1, validationMessages.size());
    }

    @Test
    public void validateMissingMandatoryCategory() throws Exception {
        // can't find s2-EHR-Order.t_simple_medication_order.v2
        Archetype archetype = repository.getArchetype("s2-EHR-Composition.t_lab_report-CBC.v1.1.0");
        OperationalTemplate opt = createOpt(archetype);

        InputStream stream = getClass().getResourceAsStream("/s2-test-data/fail/s2_missing_mandatory-category.json");
        Composition composition = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant()).readValue(stream, Composition.class);

        List<RMObjectValidationMessage> validationMessages = s2EhrValidator.validate(opt, composition);
        logMessages(validationMessages);

        assertEquals("There should be 2 errors", 2, validationMessages.size());
    }

    @Test
    public void validateDecimalOutOfRange() throws Exception {
        Archetype archetype = repository.getArchetype("s2-EHR-Composition.t_lab_report-CBC.v1.1.0");
        OperationalTemplate opt = createOpt(archetype);

        InputStream stream = getClass().getResourceAsStream("/s2-test-data/fail/s2_decimal_out_of_range-lab_value.json");
        Composition composition = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant()).readValue(stream, Composition.class);

        List<RMObjectValidationMessage> validationMessages = s2EhrValidator.validate(opt, composition);
        logMessages(validationMessages);

        assertEquals("There should be 2 errors", 2, validationMessages.size());
    }

    @Test
    public void validateDecimalOutOfRangeMinimal() throws Exception {
        Archetype archetype = repository.getArchetype("s2-EHR-Composition.t_lab_report-CBC.v1.1.0");
        OperationalTemplate opt = createOpt(archetype);

        InputStream stream = getClass().getResourceAsStream("/s2-test-data/fail/s2_decimal_out_of_range-lab_value-minimal.json");
        Composition composition = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant()).readValue(stream, Composition.class);

        List<RMObjectValidationMessage> validationMessages = s2EhrValidator.validate(opt, composition);
        logMessages(validationMessages);

        assertEquals("There should be 1 errors", 1, validationMessages.size());
    }

    @Test
    public void validateWrongReportStatus() throws Exception {
        Archetype archetype = repository.getArchetype("s2-EHR-Composition.t_lab_report-ABO-Rh.v1.1.0");
        OperationalTemplate opt = createOpt(archetype);

        InputStream stream = getClass().getResourceAsStream("/s2-test-data/fail/s2-term_description_wrong-report_status.json");
        Composition composition = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant()).readValue(stream, Composition.class);

        List<RMObjectValidationMessage> validationMessages = s2EhrValidator.validate(opt, composition);
        logMessages(validationMessages);

        assertEquals("There should be 1 errors", 1, validationMessages.size());
    }

    @Test
    public void validateMissingMandatoryUnits() throws Exception {
        // can't find s2-EHR-Order.t_simple_medication_order.v2
        Archetype archetype = repository.getArchetype("s2-EHR-Composition.t_lab_report-CBC.v1.1.0");
        OperationalTemplate opt = createOpt(archetype);

        InputStream stream = getClass().getResourceAsStream("/s2-test-data/fail/s2_missing_mandatory-units.json");
        Composition composition = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant()).readValue(stream, Composition.class);

        List<RMObjectValidationMessage> validationMessages = s2EhrValidator.validate(opt, composition);
        logMessages(validationMessages);

        assertEquals("There should be 1 errors", 1, validationMessages.size());
    }

    @Test
    public void validateRecursiveOverlayWrongRmTypeDataValue() throws Exception {
        // can't find s2-EHR-Order.t_simple_medication_order.v2
        Archetype archetype = repository.getArchetype("s2-EHR-Composition.t_simple_medication_list.v2.0.0");
        OperationalTemplate opt = createOpt(archetype);

        InputStream stream = getClass().getResourceAsStream("/s2-test-data/fail/s2_wrong_rm_type-data_value.json");
        Composition composition = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant()).readValue(stream, Composition.class);

        List<RMObjectValidationMessage> validationMessages = s2EhrValidator.validate(opt, composition);
        logMessages(validationMessages);

        assertEquals("There should be 1 errors", 1, validationMessages.size());
    }


    // TODO: Cardinality error not caught
    @Test
    public void validateEmptyContainer() throws Exception {
        // can't find s2-EHR-Order.t_simple_medication_order.v2
        Archetype archetype = repository.getArchetype("s2-EHR-Composition.t_simple_medication_list.v2.0.0");
        OperationalTemplate opt = createOpt(archetype);

        InputStream stream = getClass().getResourceAsStream("/s2-test-data/fail/s2_empty_container-order_activities.json");
        Composition composition = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant()).readValue(stream, Composition.class);

        List<RMObjectValidationMessage> validationMessages = s2EhrValidator.validate(opt, composition);
        logMessages(validationMessages);

        assertEquals("There should be 1 errors", 1, validationMessages.size());
    }

    // TODO: Cardinality error not caught
    @Test
    public void validateMissingMandatoryPersonIdentity() throws Exception {
        // can't find s2-EHR-Order.t_simple_medication_order.v2
        Archetype archetype = repository.getArchetype("s2-ENTITY-Person.t_person-test.v1.2.1");
        OperationalTemplate opt = createOpt(archetype);

        InputStream stream = getClass().getResourceAsStream("/s2-test-data/fail/s2_missing_mandatory-person_identity.json");
        Person person = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant()).readValue(stream, Person.class);

        List<RMObjectValidationMessage> validationMessages = s2EntityValidator.validate(opt, person);
        logMessages(validationMessages);

        assertEquals("There should be 1 errors", 1, validationMessages.size());
    }


    @Test
    public void validateCreatedDataAgainstOptSimple() throws Exception {
        Archetype archetype = repository.getArchetype("s2-EHR-Info_node.environmental_conditions.v1.0.0");
        OperationalTemplate opt = createOpt(archetype);
        InfoNode infoNode = (InfoNode) testUtil.constructEmptyRMObject(opt.getDefinition());

        List<RMObjectValidationMessage> validationMessages = s2EhrValidator.validate(opt, infoNode);
        logMessages(validationMessages);

        assertEquals("There should be 0 errors", 0, validationMessages.size());
    }

    @Test
    public void validateSynthDataAgainstOptSimple() throws Exception {
        Archetype archetype = repository.getArchetype("s2-EHR-Info_node.environmental_conditions.v1.0.0");
        OperationalTemplate opt = createOpt(archetype);

        // read in a synth data file for the above template
        try(InputStream stream = getClass().getResourceAsStream("/s2-synth-data/fixed/s2-EHR-Info_node.t_environmental_conditions-test.v1.0.0.json")) {
            InfoNode infoNode = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant()).readValue(stream, InfoNode.class);

            // try to validate
            List<RMObjectValidationMessage> validationMessages = s2EhrValidator.validate(opt, infoNode);
            logMessages(validationMessages);

            assertEquals("There should be 0 errors", 0, validationMessages.size());
        }
    }

    @Test
    public void validateCreatedDataAgainstOptComplex() throws Exception {
        Archetype archetype = repository.getArchetype("s2-EHR-Composition.t_encounter-vital_signs-minimal.v1.0.1");
        OperationalTemplate opt = createOpt(archetype);

        Composition comp = (Composition) testUtil.constructEmptyRMObject(opt.getDefinition());

        List<RMObjectValidationMessage> validationMessages = s2EhrValidator.validate(opt, comp);
        logMessages(validationMessages);

        assertEquals("There should be 104 errors", 104, validationMessages.size());
    }

    @Test
    public void testEmptyNodeWithoutArchetype() {
        Node node = new InfoNode();

        node.setValue(new PlainText("something"));

        List<RMObjectValidationMessage> messages = s2EhrValidator.validate(node);
        assertEquals(2, messages.size());
        for(RMObjectValidationMessage message:messages) {
            assertTrue(message.getPath() + " unexpected value", Sets.newHashSet("/name", "/archetype_node_id", "/").contains(message.getPath()));
            assertTrue(EnumSet.of(RMObjectValidationMessageType.REQUIRED, RMObjectValidationMessageType.INVARIANT_ERROR).contains(message.getType()));
        }
    }

    @Test
    public void testNodeWithoutArchetype() {
        Node node1 = new InfoNode();
        node1.setName("test Info_node");
        node1.setArchetypeNodeId("id12");
        Node node2 = new InfoNode();
        node2.setValue(new PlainText("hi!"));
        node1.setItems(Lists.newArrayList(node2));

        List<RMObjectValidationMessage> messages = s2EhrValidator.validate(node1);
        assertEquals(messages.toString() ,2, messages.size());
        for(RMObjectValidationMessage message:messages) {
            assertTrue(message.getPath(), Sets.newHashSet("/items[1]/name", "/items[1]/archetype_node_id").contains(message.getPath()));
            assertEquals(RMObjectValidationMessageType.REQUIRED, message.getType());
        }
    }

    @Test
    public void testValidNodeWithoutArchetype() {
        Node node1 = new InfoNode();
        node1.setName("test Info_node");
        node1.setArchetypeNodeId("id12");
        Node node2 = new InfoNode();
        node2.setName("test Info_node");
        node2.setValue(new PlainText("value"));
        node2.setArchetypeNodeId("id15");
        node1.setItems(Lists.newArrayList(node2));

        List<RMObjectValidationMessage> messages = s2EhrValidator.validate(node1);
        assertEquals(messages.toString(), 0, messages.size());

    }

    @Test
    public void testNestedEmptyNodeWithoutArchetype() {
        Node node = new InfoNode();

        List<RMObjectValidationMessage> validate = s2EhrValidator.validate(node);
        assertFalse(validate.isEmpty());
    }


    private OperationalTemplate createOpt(Archetype archetype) {
        return (OperationalTemplate) new Flattener(repository, metaModels, FlattenerConfiguration.forOperationalTemplate()).flatten(archetype, 0);
    }

    private void logMessages(List<RMObjectValidationMessage> messages) {
        if (messages != null && !messages.isEmpty()) {
            for (RMObjectValidationMessage message : messages) {
                logger.info(message.getArchetypePath());
                logger.info(message.getHumanReadableArchetypePath());
                logger.info(message.getMessage());
            }
        }
    }

}