package com.nedap.archie.flattener;

import com.google.common.collect.Lists;
import com.nedap.archie.adlparser.ADLParseException;
import com.nedap.archie.adlparser.ADLParser;
import com.nedap.archie.aom.*;
import com.nedap.archie.archetypevalidator.ArchetypeValidator;
import com.nedap.archie.archetypevalidator.ValidationResult;
import com.nedap.archie.testutil.ParseValidArchetypeTestUtil;
import com.nedap.archie.openehr.rminfo.OpenEhrRmInfoLookup;
import com.nedap.archie.rminfo.ReferenceModels;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openehr.referencemodels.AllMetaModelsInitialiser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.*;

public class OperationalTemplateCreatorTest {

    private static ReferenceModels models;

    private static ArchetypeValidator validator;
    @BeforeClass
    public static void setup() {
        models = new ReferenceModels();
        models.registerModel(OpenEhrRmInfoLookup.getInstance());
        validator = new ArchetypeValidator(models);
    }

    @Test
    public void fillEmptyOccurrences() throws Exception {
        try(InputStream stream = getClass().getResourceAsStream("openEHR-EHR-CLUSTER.cluster_with_annotations.v1.adls")) {
            Archetype archetype = new ADLParser(AllMetaModelsInitialiser.getMetaModels()).parse(stream);
            SimpleArchetypeRepository repository = new SimpleArchetypeRepository();
            repository.addArchetype(archetype);
            Flattener flattener = new Flattener(repository, AllMetaModelsInitialiser.getMetaModels(), FlattenerConfiguration.forOperationalTemplate());
            OperationalTemplate template = (OperationalTemplate) flattener.flatten(archetype, 0);

            Stack<CObject> workList = new Stack<>();
            workList.push(template.getDefinition());
            while(!workList.isEmpty()) {
                CObject cObject = workList.pop();
                if(cObject instanceof CComplexObject) {
                    assertNotNull(cObject.getOccurrences());
                    CObject objectInOriginal = archetype.itemAtPath(cObject.getPath());
                    assertEquals(objectInOriginal.effectiveOccurrences(OpenEhrRmInfoLookup.getInstance()::referenceModelPropMultiplicity), cObject.getOccurrences());
                }
                for(CAttribute attribute:cObject.getAttributes()) {
                    workList.addAll(attribute.getChildren());
                }
            }
        }
    }

    @Test
    public void dontFillEmptyOccurrencesUnlessSet() throws Exception {
        try(InputStream stream = getClass().getResourceAsStream("openEHR-EHR-CLUSTER.cluster_with_annotations.v1.adls")) {
            Archetype archetype = new ADLParser(AllMetaModelsInitialiser.getMetaModels()).parse(stream);
            FlattenerConfiguration flattenerConfiguration = FlattenerConfiguration.forOperationalTemplate();
            flattenerConfiguration.setFillEmptyOccurrences(false);
            SimpleArchetypeRepository repository = new SimpleArchetypeRepository();
            repository.addArchetype(archetype);
            Flattener flattener = new Flattener(repository, AllMetaModelsInitialiser.getMetaModels(), flattenerConfiguration);
            OperationalTemplate template = (OperationalTemplate) flattener.flatten(archetype, 0);

            Stack<CObject> workList = new Stack<>();
            workList.push(template.getDefinition());
            while(!workList.isEmpty()) {
                CObject cObject = workList.pop();
                if(cObject instanceof CComplexObject) {
                    //this archetype has no occurrences at all
                    assertNull(cObject.getOccurrences());
                }
                for(CAttribute attribute:cObject.getAttributes()) {
                    workList.addAll(attribute.getChildren());
                }
            }
        }
    }

    @Test(expected = RuntimeException.class)
    public void failOnMissingArchetypeEnabled() throws Exception {
        SimpleArchetypeRepository repository = new SimpleArchetypeRepository();
        try(InputStream stream1 = getClass().getResourceAsStream("openEHR-EHR-OBSERVATION.with_used_archetype.v1.adls"); InputStream stream2 = getClass().getResourceAsStream("openEHR-EHR-CLUSTER.cluster_with_annotations.v1.adls")) {
            Archetype archetype1 = new ADLParser(AllMetaModelsInitialiser.getMetaModels()).parse(stream1);
            repository.addArchetype(archetype1);

            // get the used archetype into the repo
            Archetype archetype2 = new ADLParser(AllMetaModelsInitialiser.getMetaModels()).parse(stream2);
            // repository.addArchetype(archetype2);
            FlattenerConfiguration flattenerConfiguration = FlattenerConfiguration.forOperationalTemplate();
            Flattener flattener = new Flattener(repository, AllMetaModelsInitialiser.getMetaModels(), flattenerConfiguration);
            OperationalTemplate template = (OperationalTemplate) flattener.flatten(archetype1, 0);
            fail();
        }
    }

    @Test
    public void failOnMissingArchetypeDisabled() throws Exception {
        try(InputStream stream = getClass().getResourceAsStream("openEHR-EHR-OBSERVATION.with_used_archetype.v1.adls")) {
            Archetype archetype = new ADLParser(AllMetaModelsInitialiser.getMetaModels()).parse(stream);
            FlattenerConfiguration flattenerConfiguration = FlattenerConfiguration.forOperationalTemplate();
            flattenerConfiguration.setFailOnMissingUsedArchetype(false);
            SimpleArchetypeRepository repository = new SimpleArchetypeRepository();
            repository.addArchetype(archetype);
            Flattener flattener = new Flattener(repository, AllMetaModelsInitialiser.getMetaModels(), flattenerConfiguration);
            OperationalTemplate template = (OperationalTemplate) flattener.flatten(archetype, 0);

            CArchetypeRoot archetypeRoot = template.getDefinition().itemAtPath("/data[id2]/events[id3]/data[id4]/items[id8]");
            assertEquals("openEHR-EHR-CLUSTER.cluster_with_annotations.v1", archetypeRoot.getArchetypeRef());
            assertTrue(archetypeRoot.getAttributes().isEmpty());
        }
    }

    @Test
    public void allowSpecializationBeforeExclusionEnabled() throws Exception {
        InMemoryFullArchetypeRepository repository = new InMemoryFullArchetypeRepository();
        Archetype parentArchetype = parse("/com/nedap/archie/flattener/siblingorder/openEHR-EHR-CLUSTER.siblingorderparent.v1.0.0.adls");
        repository.addArchetype(parentArchetype);

        FlattenerConfiguration config = FlattenerConfiguration.forOperationalTemplate();
        // Explicitly set it to true, even though it's default
        config.setAllowSpecializationAfterExclusion(true);

        Archetype child =  parse("/com/nedap/archie/archetypevalidator/openEHR-EHR-CLUSTER.specialized_nodes_order.v1.0.0.adls");
        repository.addArchetype(child);

        Archetype flatChild =  createOPTWithConfig(child, repository, config);
        List<CObject> children = flatChild.getDefinition().getAttribute("items").getChildren();
        List<String> nodeIds = children.stream().map(CObject::getNodeId).collect(Collectors.toList());
        assertEquals(
                Lists.newArrayList("id5.1", "id6.1", "id7.1"),
                nodeIds
        );
    }

    @Test
    public void allowSpecializationBeforeExclusionDisabled() throws Exception {
        InMemoryFullArchetypeRepository repository = new InMemoryFullArchetypeRepository();
        Archetype parentArchetype = parse("/com/nedap/archie/flattener/siblingorder/openEHR-EHR-CLUSTER.siblingorderparent.v1.0.0.adls");
        repository.addArchetype(parentArchetype);

        FlattenerConfiguration config = FlattenerConfiguration.forOperationalTemplate();
        config.setAllowSpecializationAfterExclusion(false);

        Archetype child =  parse("/com/nedap/archie/archetypevalidator/openEHR-EHR-CLUSTER.specialized_nodes_order.v1.0.0.adls");
        repository.addArchetype(child);

        Archetype flatChild =  createOPTWithConfig(child, repository, config);
        List<CObject> children = flatChild.getDefinition().getAttribute("items").getChildren();
        List<String> nodeIds = children.stream().map(CObject::getNodeId).collect(Collectors.toList());
        assertEquals(
                Lists.newArrayList("id6.1", "id7.1"),
                nodeIds
        );
    }

    private Archetype createOPTWithConfig(Archetype archetype, InMemoryFullArchetypeRepository repository, FlattenerConfiguration config) throws IOException, ADLParseException {
        ValidationResult validationResult = validator.validate(archetype, repository);
        assertTrue(validationResult.getErrors().toString(), validationResult.passes());
        return new Flattener(repository, AllMetaModelsInitialiser.getMetaModels(), config).flatten(archetype, 0);
    }

    private Archetype parse(String filePath) throws IOException, ADLParseException {
        return ParseValidArchetypeTestUtil.parse(this.getClass(), filePath);
    }
}
