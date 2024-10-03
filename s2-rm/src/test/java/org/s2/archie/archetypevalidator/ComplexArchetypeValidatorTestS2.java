package org.s2.archie.archetypevalidator;

import com.nedap.archie.adlparser.ADLParser;
import com.nedap.archie.antlr.errors.ANTLRParserErrors;
import com.nedap.archie.aom.Archetype;
import com.nedap.archie.archetypevalidator.ArchetypeValidator;
import com.nedap.archie.archetypevalidator.ValidationMessage;
import com.nedap.archie.archetypevalidator.ValidationResult;
import com.nedap.archie.flattener.InMemoryFullArchetypeRepository;
import com.nedap.archie.rminfo.ReferenceModels;
import org.junit.Before;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.s2.rminfo.S2RmInfoLookup;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class ComplexArchetypeValidatorTestS2 {
    private ReferenceModels models;
    private static InMemoryFullArchetypeRepository repository;

    @Before
    public void setup() throws Exception {
        models = new ReferenceModels();
        models.registerModel(S2RmInfoLookup.getInstance());
        repository = new InMemoryFullArchetypeRepository();

        Reflections reflections = new Reflections("org.s2.archie", Scanners.Resources);
        List<String> adlFiles = new ArrayList<>(reflections.getResources(Pattern.compile(".*\\.adls")));

        for(String file: adlFiles) {
            InputStream stream = ComplexArchetypeValidatorTestS2.class.getResourceAsStream("/" + file);
            Archetype archetype = new ADLParser().parse(stream);
            repository.addArchetype(archetype);
        }
    }

    @Test
    public void testNodeArchetype() {
        Archetype archetype = repository.getArchetype("s2-EHR-Node.adverse_reaction_event.v1.0.0");
        ValidationResult validationResult = new ArchetypeValidator(models).validate(archetype, repository);
        List<ValidationMessage> messages = validationResult.getErrors();
        assertEquals(5, messages.size());
    }

    @Test
    public void testTemplateWithOverlay() {
        Archetype archetype = repository.getArchetype("s2-EHR-Composition.t_encounter-vital_signs-minimal.v1.0.0");
        ValidationResult validationResult = new ArchetypeValidator(models).validate(archetype, repository);
        List<ValidationMessage> messages = validationResult.getErrors();
        assertEquals(0, messages.size());
    }


}
