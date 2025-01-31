package org.s2.archie.rmobjectvalidator;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.nedap.archie.adlparser.ADLParseException;
import com.nedap.archie.aom.Archetype;
import com.nedap.archie.aom.OperationalTemplate;
import com.nedap.archie.flattener.Flattener;
import com.nedap.archie.flattener.FlattenerConfiguration;
import com.nedap.archie.flattener.FullArchetypeRepository;
import com.nedap.archie.flattener.InMemoryFullArchetypeRepository;
import com.nedap.archie.json.ArchieJacksonConfiguration;
import com.nedap.archie.rminfo.MetaModels;
import com.nedap.archie.rmobjectvalidator.RMObjectValidationMessage;
import com.nedap.archie.rmobjectvalidator.RMObjectValidationMessageType;
import com.nedap.archie.rmobjectvalidator.RMObjectValidator;
import com.nedap.archie.rmobjectvalidator.ValidationConfiguration;
import com.nedap.archie.testutil.ArchetypeRepositoryBuilder;
import com.nedap.archie.testutil.TestUtil;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openehr.referencemodels.AllMetaModelsInitialiser;
import org.s2.archie.archetypevalidator.S2ModelsArchetypeValidatorTest;
import org.s2.rm.base.data_types.quantity.Proportion;
import org.s2.rm.base.data_types.quantity.Quantity;
import org.s2.rm.base.data_types.text.CodedText;
import org.s2.rm.base.data_types.text.PlainText;
import org.s2.rm.base.foundation_types.terminology.TerminologyCode;
import org.s2.rm.base.foundation_types.terminology.TerminologyTerm;
import org.s2.rm.base.patterns.data_structures.InfoNode;
import org.s2.rm.base.patterns.data_structures.Node;
import org.s2.rm.care.composition.Composition;
import org.s2.rminfo.S2RmInfoLookup;
import org.s2.rminfo.S2RmMetaModelsInitialiser;
import org.s2.serialisation.json.S2RmJacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.List;

import static org.junit.Assert.*;

public class S2RmObjectValidatorTest2 {

    private TestUtil testUtil;

    private static final Logger logger = LoggerFactory.getLogger(S2RmObjectValidatorTest2.class);

    FullArchetypeRepository repository;

    MetaModels bmmReferenceModels;
    private RMObjectValidator validator;

    private RMObjectValidator validatorWithoutInvariants;

    @Before
    public void setup() {
        testUtil = new TestUtil(S2RmInfoLookup.getInstance());

        repository = ArchetypeRepositoryBuilder.parseRepository(this.getClass(), "s2-models");
        logger.info("archetypes parsed: " + repository.getAllArchetypes().size());

        S2RmMetaModelsInitialiser s2RmMetaModelsInitialiser = new S2RmMetaModelsInitialiser();
        bmmReferenceModels = new MetaModels(null, s2RmMetaModelsInitialiser.getBmmRepository(), s2RmMetaModelsInitialiser.getAomProfiles());
        repository.compile(bmmReferenceModels);

        validator = new RMObjectValidator(S2RmInfoLookup.getInstance(), repository);
        validatorWithoutInvariants = new RMObjectValidator(S2RmInfoLookup.getInstance(), repository,
                new ValidationConfiguration.Builder().validateInvariants(false).build());
    }

    @Test
    public void validateCreatedDataAgainstOptSimple() throws Exception {
        OperationalTemplate opt = repository.getOperationalTemplate("s2-EHR-Info_node.environmental_conditions.v1.0.0");
        InfoNode infoNode = (InfoNode) testUtil.constructEmptyRMObject(opt.getDefinition());

        List<RMObjectValidationMessage> validationMessages = validatorWithoutInvariants.validate(opt, infoNode);
    }

    @Test
    public void validateSynthDataAgainstOptSimple() throws Exception {
        OperationalTemplate opt = repository.getOperationalTemplate("s2-EHR-Info_node.environmental_conditions.v1.0.0");

        // read in a synth data file for the above template
        try(InputStream stream = getClass().getResourceAsStream("s2-EHR-Info_node.environmental_conditions.v1.0.0.json")) {
            InfoNode infoNode = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant()).readValue(stream, InfoNode.class);

            // try to validate
            List<RMObjectValidationMessage> validationMessages = validatorWithoutInvariants.validate(opt, infoNode);
        }
    }

    @Test
    public void validateCreatedDataAgainstOptComplex() throws Exception {
        OperationalTemplate opt = repository.getOperationalTemplate("s2-EHR-Composition.t_encounter-vital_signs-minimal.v1.0.1");
        Composition comp = (Composition) testUtil.constructEmptyRMObject(opt.getDefinition());

        List<RMObjectValidationMessage> validationMessages = validatorWithoutInvariants.validate(opt, comp);
    }

    @Test
    public void validateSynthDataAgainstOptComplex() throws Exception {
        OperationalTemplate opt = repository.getOperationalTemplate("s2-EHR-Composition.t_encounter-vital_signs-minimal.v1.0.1");

        // read in a synth data file for the above template
        try(InputStream stream = getClass().getResourceAsStream("s2-EHR-Composition.t_encounter-vital_signs-minimal.v1.0.1")) {
            Composition comp = S2RmJacksonUtil.getObjectMapper(ArchieJacksonConfiguration.createStandardsCompliant()).readValue(stream, Composition.class);

            // try to validate
            List<RMObjectValidationMessage> validationMessages = validatorWithoutInvariants.validate(opt, comp);
        }
    }

    private OperationalTemplate createOpt(Archetype archetype) {
        return (OperationalTemplate) new Flattener(repository, AllMetaModelsInitialiser.getMetaModels(), FlattenerConfiguration.forOperationalTemplate()).flatten(archetype, 0);
    }

    private Archetype parse(String filename) throws IOException, ADLParseException {
        return TestUtil.parseFailOnErrors(this.getClass(), filename);
    }

}