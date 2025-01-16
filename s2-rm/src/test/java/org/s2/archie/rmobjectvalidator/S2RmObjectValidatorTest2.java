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
import org.s2.rminfo.S2RmInfoLookup;
import org.s2.rminfo.S2RmMetaModelsInitialiser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
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
    public void test1() throws Exception {
        Archetype archetype = parse("/adl2-tests/rmobjectvalidity/s2-EHR-Info_node.element_with_required_attributes.v1.0.0.adls");
        OperationalTemplate opt = createOpt(archetype);

        InfoNode node = (InfoNode) testUtil.constructEmptyRMObject(archetype.getDefinition());
        Proportion proportion = (Proportion) node.getValue();
        assert proportion != null;
        proportion.setDenominator(new Quantity(new BigDecimal("4.0"), new CodedText(new TerminologyTerm("ml", new TerminologyCode("snomed", "258773002")), "mL")));

        List<RMObjectValidationMessage> validationMessages = validatorWithoutInvariants.validate(opt, node);
        assertEquals("There should be 2 errors", 2, validationMessages.size());
        assertEquals("There should be a validation message about the numerator", "Attribute numerator of class Proportion does not match existence 1..1", validationMessages.get(1).getMessage());
        assertEquals("There should be a validation message about the magnitiude", "Attribute magnitude of class Proportion does not match existence 1..1", validationMessages.get(0).getMessage());
        assertEquals("The path should be correct", "/value/numerator", validationMessages.get(1).getPath());
        assertEquals("The archetype path should be correct", "/value[id2]/numerator", validationMessages.get(1).getArchetypePath());

        proportion.setMagnitude(BigDecimal.valueOf(0.5));
        proportion.setNumerator(new Quantity(new BigDecimal("2.0"), new CodedText(new TerminologyTerm("ml", new TerminologyCode("snomed", "258773002")), "mL")));

        validationMessages = validator.validate(opt, node);
        assertEquals("There should be 0 errors", 0, validationMessages.size());
    }

    private OperationalTemplate createOpt(Archetype archetype) {
        return (OperationalTemplate) new Flattener(repository, AllMetaModelsInitialiser.getMetaModels(), FlattenerConfiguration.forOperationalTemplate()).flatten(archetype, 0);
    }

    private Archetype parse(String filename) throws IOException, ADLParseException {
        return TestUtil.parseFailOnErrors(this.getClass(), filename);
    }

}