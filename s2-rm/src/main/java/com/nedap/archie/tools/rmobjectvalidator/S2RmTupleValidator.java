package com.nedap.archie.tools.rmobjectvalidator;

import com.nedap.archie.aom.*;
import com.nedap.archie.query.RMObjectAttributes;
import com.nedap.archie.query.RMObjectWithPath;
//import com.nedap.archie.rminfo.ModelInfoLookup;
import org.s2.rminfo.S2RmInfoLookup;
import com.nedap.archie.rmobjectvalidator.RMObjectValidationMessage;
import com.nedap.archie.rmobjectvalidator.RMObjectValidationMessageIds;
//import com.nedap.archie.rmobjectvalidator.RmPrimitiveObjectValidator;
//import com.nedap.archie.rmobjectvalidator.ValidationHelper;
// import com.nedap.archie.rmobjectvalidator.ValidationHelper;

import java.util.ArrayList;
import java.util.List;

public class S2RmTupleValidator {
    private final S2RmInfoLookup lookup;
    private final S2ValidationHelper validationHelper;
    private final S2RmPrimitiveObjectValidator rmPrimitiveObjectValidator;

    public S2RmTupleValidator(S2RmInfoLookup lookup, S2ValidationHelper validationHelper, S2RmPrimitiveObjectValidator rmPrimitiveObjectValidator) {
        this.lookup = lookup;
        this.validationHelper = validationHelper;
        this.rmPrimitiveObjectValidator = rmPrimitiveObjectValidator;
    }

    public List<RMObjectValidationMessage> validate(CObject cobject, String pathSoFar, List<RMObjectWithPath> rmObjects, CAttributeTuple tuple) {
        List<RMObjectValidationMessage> result = new ArrayList<>();
        if (rmObjects.size() != 1) {
            String message = RMObjectValidationMessageIds.rm_TUPLE_CONSTRAINT.getMessage(cobject.toString(), rmObjects.toString());
            result.add(new RMObjectValidationMessage(cobject, pathSoFar, message));
            return result;
        }
        Object rmObject = rmObjects.get(0).getObject();
        if (!validationHelper.isValid(tuple, rmObject)) {
            if(tuple.getTuples().size() == 1) {
                // Try to make useful validation messages
                result.addAll(validateSingleTuple(pathSoFar, rmObject, tuple));
            }

            if(result.isEmpty()) {
                // Fall back to generic validation message
                String message = RMObjectValidationMessageIds.rm_TUPLE_MISMATCH.getMessage(tuple.toString());
                result.add(new RMObjectValidationMessage(cobject, pathSoFar, message));
            }
        }
        return result;
    }

    /**
     * Validate a CAttributeTuple with a single tuple.
     *
     * This will check each attribute in the tuple individually to get more specific validation messages.
     */
    private List<RMObjectValidationMessage> validateSingleTuple(String pathSoFar, Object rmObject, CAttributeTuple attributeTuple) {
        List<RMObjectValidationMessage> result = new ArrayList<>();

        CPrimitiveTuple tuple = attributeTuple.getTuples().get(0);

        int index = 0;
        for(CAttribute attribute:attributeTuple.getMembers()) {
            String attributeName = attribute.getRmAttributeName();
            CPrimitiveObject<?, ?> cPrimitiveObject = tuple.getMembers().get(index);
            Object value = RMObjectAttributes.getAttributeValueFromRMObject(rmObject, attributeName, lookup);
            String path = pathSoFar + "/" + attributeName + "[" + cPrimitiveObject.getNodeId() + "]";

            result.addAll(rmPrimitiveObjectValidator.validate_inner(value, path, cPrimitiveObject));

            index++;
        }

        return result;
    }
}
