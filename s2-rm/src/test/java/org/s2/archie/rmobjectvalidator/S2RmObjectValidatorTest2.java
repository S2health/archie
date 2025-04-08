package org.s2.archie.rmobjectvalidator;

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
import com.nedap.archie.tools.rmobjectvalidator.S2RMObjectValidator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openehr.referencemodels.AllMetaModelsInitialiser;
import org.s2.rm.base.patterns.data_structures.InfoNode;
import org.s2.rm.care.composition.Composition;
import org.s2.rm.entity.social_entity.Person;
import org.s2.rminfo.S2RmInfoLookup;
import org.s2.rminfo.S2RmMetaModelsInitialiser;
import org.s2.serialisation.json.S2RmJacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class S2RmObjectValidatorTest2 {

    private static TestUtil testUtil;

    static S2RmMetaModelsInitialiser s2RmMetaModelsInitialiser;

    private static final Logger logger = LoggerFactory.getLogger(S2RmObjectValidatorTest2.class);

    static FullArchetypeRepository  repository;

    static MetaModels bmmReferenceModels;
    static private S2RMObjectValidator validator;

    static private S2RMObjectValidator validatorWithoutInvariants;

    @BeforeClass
    public static void setup() {
        testUtil = new TestUtil(S2RmInfoLookup.getInstance());

        repository = ArchetypeRepositoryBuilder.parseRepository (S2RmObjectValidatorTest2.class, "s2-models");
        logger.info("archetypes parsed: " + repository.getAllArchetypes().size());

        s2RmMetaModelsInitialiser = new S2RmMetaModelsInitialiser();
        bmmReferenceModels = new MetaModels(null, s2RmMetaModelsInitialiser.getBmmRepository(), s2RmMetaModelsInitialiser.getAomProfiles());
        repository.compile(bmmReferenceModels);

        validator = new S2RMObjectValidator(S2RmInfoLookup.getInstance(), repository);
        validatorWithoutInvariants = new S2RMObjectValidator(S2RmInfoLookup.getInstance(), repository,
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

        List<RMObjectValidationMessage> validationMessages = validatorWithoutInvariants.validate(opt, composition);
        logMessages(validationMessages);

        assertEquals("There should be 0 error", 0, validationMessages.size());

    }

    @Test
    public void validatedCBC_WithWrongCode() throws Exception {
        Archetype archetype = repository.getArchetype("s2-EHR-Composition.t_lab_report-CBC.v1.1.0");
        OperationalTemplate opt = createOpt(archetype);

        InputStream stream = getClass().getResourceAsStream("/s2-test-data/fail/s2-EHR-Composition.t_lab_report-CBC_wrong_code.json");
        Composition composition = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant()).readValue(stream, Composition.class);

        List<RMObjectValidationMessage> validationMessages = validatorWithoutInvariants.validate(opt, composition);
        logMessages(validationMessages);

        assertEquals("There should be 2 errors", 2, validationMessages.size());

    }

    @Test
    public void validatedCBC_WithWrongDatatype() throws Exception {
        Archetype archetype = repository.getArchetype("s2-EHR-Composition.t_lab_report-CBC.v1.1.0");
        OperationalTemplate opt = createOpt(archetype);

        InputStream stream = getClass().getResourceAsStream("/s2-test-data/fail/s2-EHR-Composition.t_lab_report-CBC_wrong_datatype.json");
        Composition composition = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant()).readValue(stream, Composition.class);

        List<RMObjectValidationMessage> validationMessages = validatorWithoutInvariants.validate(opt, composition);
        logMessages(validationMessages);

        assertEquals("There should be 1 errors", 1, validationMessages.size());

    }

    @Test
    public void validTermInValueset() throws Exception {
        Archetype archetype = repository.getArchetype("s2-EHR-Composition.t_lab_report-ABO-Rh.v1.1.0");
        OperationalTemplate opt = createOpt(archetype);

        InputStream stream = getClass().getResourceAsStream("/s2-synth-data/fixed/s2-EHR-Composition.t_lab_report-ABO-Rh.v1.0.0.json");
        Composition composition = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant()).readValue(stream, Composition.class);

        List<RMObjectValidationMessage> validationMessages = validatorWithoutInvariants.validate(opt, composition);
        logMessages(validationMessages);

        assertEquals("There should be 0 errors", 0, validationMessages.size());
    }

    @Test
    public void validateTermNotInValueset() throws Exception {
        Archetype archetype = repository.getArchetype("s2-EHR-Composition.t_lab_report-ABO-Rh.v1.1.0");
        OperationalTemplate opt = createOpt(archetype);

        InputStream stream = getClass().getResourceAsStream("/s2-test-data/fail/s2-term_not_in_value_set-report_status.json");
        Composition composition = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant()).readValue(stream, Composition.class);

        List<RMObjectValidationMessage> validationMessages = validatorWithoutInvariants.validate(opt, composition);
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

        List<RMObjectValidationMessage> validationMessages = validatorWithoutInvariants.validate(opt, composition);
        logMessages(validationMessages);

        assertEquals("There should be 2 errors", 2, validationMessages.size());
    }

    @Test
    public void validateDecimalOutOfRange() throws Exception {
        Archetype archetype = repository.getArchetype("s2-EHR-Composition.t_lab_report-CBC.v1.1.0");
        OperationalTemplate opt = createOpt(archetype);

        InputStream stream = getClass().getResourceAsStream("/s2-test-data/fail/s2_decimal_out_of_range-lab_value.json");
        Composition composition = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant()).readValue(stream, Composition.class);

        List<RMObjectValidationMessage> validationMessages = validatorWithoutInvariants.validate(opt, composition);
        logMessages(validationMessages);

        assertEquals("There should be 2 errors", 2, validationMessages.size());
    }

    @Test
    public void validateDecimalOutOfRangeMinimal() throws Exception {
        Archetype archetype = repository.getArchetype("s2-EHR-Composition.t_lab_report-CBC.v1.1.0");
        OperationalTemplate opt = createOpt(archetype);

        InputStream stream = getClass().getResourceAsStream("/s2-test-data/fail/s2_decimal_out_of_range-lab_value-minimal.json");
        Composition composition = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant()).readValue(stream, Composition.class);

        List<RMObjectValidationMessage> validationMessages = validatorWithoutInvariants.validate(opt, composition);
        logMessages(validationMessages);

        assertEquals("There should be 1 errors", 1, validationMessages.size());
    }

    @Test
    public void validateWrongReportStatus() throws Exception {
        Archetype archetype = repository.getArchetype("s2-EHR-Composition.t_lab_report-ABO-Rh.v1.1.0");
        OperationalTemplate opt = createOpt(archetype);

        InputStream stream = getClass().getResourceAsStream("/s2-test-data/fail/s2-term_description_wrong-report_status.json");
        Composition composition = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant()).readValue(stream, Composition.class);

        List<RMObjectValidationMessage> validationMessages = validatorWithoutInvariants.validate(opt, composition);
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

        List<RMObjectValidationMessage> validationMessages = validatorWithoutInvariants.validate(opt, composition);
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

        List<RMObjectValidationMessage> validationMessages = validatorWithoutInvariants.validate(opt, composition);
        logMessages(validationMessages);

        assertEquals("There should be 1 errors", 1, validationMessages.size());
    }


    // TODO: Error not caught
    @Test
    public void validateEmptyContainer() throws Exception {
        // can't find s2-EHR-Order.t_simple_medication_order.v2
        Archetype archetype = repository.getArchetype("s2-EHR-Composition.t_simple_medication_list.v2.0.0");
        OperationalTemplate opt = createOpt(archetype);

        InputStream stream = getClass().getResourceAsStream("/s2-test-data/fail/s2_empty_container-order_activities.json");
        Composition composition = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant()).readValue(stream, Composition.class);

        List<RMObjectValidationMessage> validationMessages = validatorWithoutInvariants.validate(opt, composition);
        logMessages(validationMessages);

        assertEquals("There should be 1 errors", 1, validationMessages.size());
    }

    // TODO: Breaks during validation
    @Test
    public void validateMissingMandatoryPersonIdentity() throws Exception {
        // can't find s2-EHR-Order.t_simple_medication_order.v2
        Archetype archetype = repository.getArchetype("s2-ENTITY-Person.t_person-test.v1.2.1");
        OperationalTemplate opt = createOpt(archetype);

        InputStream stream = getClass().getResourceAsStream("/s2-test-data/fail/s2_missing_mandatory-person_identity.json");
        Person person = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant()).readValue(stream, Person.class);

        List<RMObjectValidationMessage> validationMessages = validatorWithoutInvariants.validate(opt, person);
        logMessages(validationMessages);

        assertEquals("There should be 1 errors", 1, validationMessages.size());
    }


    @Test
    public void validateCreatedDataAgainstOptSimple() throws Exception {
        Archetype archetype = repository.getArchetype("s2-EHR-Info_node.environmental_conditions.v1.0.0");
        OperationalTemplate opt = createOpt(archetype);
        InfoNode infoNode = (InfoNode) testUtil.constructEmptyRMObject(opt.getDefinition());

        List<RMObjectValidationMessage> validationMessages = validatorWithoutInvariants.validate(opt, infoNode);
        logMessages(validationMessages);

    }

    @Test
    public void validateSynthDataAgainstOptSimple() throws Exception {
        Archetype archetype = repository.getArchetype("s2-EHR-Info_node.environmental_conditions.v1.0.0");
        OperationalTemplate opt = createOpt(archetype);

        // read in a synth data file for the above template
        try(InputStream stream = getClass().getResourceAsStream("/s2-synth-data/fixed/s2-EHR-Info_node.environmental_conditions.v1.0.0.json")) {
            InfoNode infoNode = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant()).readValue(stream, InfoNode.class);

            // try to validate
            List<RMObjectValidationMessage> validationMessages = validatorWithoutInvariants.validate(opt, infoNode);
            logMessages(validationMessages);

        }
    }

    @Test
    public void validateCreatedDataAgainstOptComplex() throws Exception {
        Archetype archetype = repository.getArchetype("s2-EHR-Composition.t_encounter-vital_signs-minimal.v1.0.1");
        OperationalTemplate opt = createOpt(archetype);

        Composition comp = (Composition) testUtil.constructEmptyRMObject(opt.getDefinition());

        List<RMObjectValidationMessage> validationMessages = validatorWithoutInvariants.validate(opt, comp);
        logMessages(validationMessages);

    }

    @Test
    public void validateSynthDataAgainstOptComplex() throws Exception {
        Archetype archetype = repository.getArchetype("s2-EHR-Composition.t_encounter-vital_signs-minimal.v1.0.1");
        OperationalTemplate opt = createOpt(archetype);

        // read in a synth data file for the above template
        try(InputStream stream = getClass().getResourceAsStream("/s2-synth-data/fixed/s2-EHR-Composition.t_encounter-vital_signs-minimal.v1.0.1.json")) {
            Composition comp = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant()).readValue(stream, Composition.class);

            // try to validate
            List<RMObjectValidationMessage> validationMessages = validatorWithoutInvariants.validate(opt, comp);
            logMessages(validationMessages);

        }
    }

    private OperationalTemplate createOpt(Archetype archetype) {
        return (OperationalTemplate) new Flattener(repository, bmmReferenceModels, FlattenerConfiguration.forOperationalTemplate()).flatten(archetype, 0);
    }

    private Archetype parse(String filename) throws IOException, ADLParseException {
        return TestUtil.parseFailOnErrors(this.getClass(), filename);
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