package org.openehr.bmm.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.openehr.bmm.v2.persistence.jackson.BmmJacksonUtil;

public class BmmRoutineTest {

    @Test
    public void testBmmRoutine() throws JsonProcessingException {
        BmmModel bmmModel = new BmmModel();

        // Create a class representing Boolean
        BmmSimpleClass bmmBooleanClass = new BmmSimpleClass("Boolean", "Boolean class", false);

        // Create a type object representing Boolean
        BmmSimpleType bmmBooleanType = bmmBooleanClass.generateType();

        // Create a class representing String
        BmmSimpleClass bmmStringClass = new BmmSimpleClass("String", "String class", false);

        // Create a type object representing String
        BmmSimpleType bmmStringType = bmmStringClass.generateType();


        // Create a class representing Person
        BmmSimpleClass bmmPersonClass = new BmmSimpleClass("Person", "Person class", false);
        // add a singe valued property called 'identifier'
        BmmUnitaryProperty bmmPersonIdentifierProperty = new BmmUnitaryProperty("identifier", bmmStringType, "person identifier", true, false);
        bmmPersonClass.addProperty(bmmPersonIdentifierProperty);

        // add an invariant for the property
        BmmAssertion bmmIdentifierInvariant = new BmmAssertion("not identifier.is_empty", "identifier_validity");
        bmmPersonClass.addInvariant(bmmIdentifierInvariant);

        // add a function representing 'is_valid_identifier (id: String): Boolean'
        BmmParameter<BmmSimpleType> bmmIdParameter = new BmmParameter<>("id", bmmStringType, true, "identifier string");
        BmmFunction<BmmSimpleType> bmmIsValidFunction = new BmmFunction<BmmSimpleType>("is_valid_identifier", bmmBooleanType, true, "identifier validation function");
        bmmIsValidFunction.addParameter(bmmIdParameter);

        bmmPersonClass.addFunction(bmmIsValidFunction);

        // add the classes to the model
        bmmModel.addClassDefinition(bmmBooleanClass);
        bmmModel.addClassDefinition(bmmStringClass);
        bmmModel.addClassDefinition(bmmPersonClass);

        String s = BmmJacksonUtil.getObjectMapper().writeValueAsString(bmmModel);
        System.out.println(s);

    }
}
