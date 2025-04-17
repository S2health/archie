package com.nedap.archie.rmobjectvalidator;

import com.google.common.collect.Lists;
import com.nedap.archie.aom.CAttribute;
import com.nedap.archie.aom.CObject;
import com.nedap.archie.base.Cardinality;
import com.nedap.archie.base.MultiplicityInterval;
import com.nedap.archie.rminfo.MetaModel;
import com.nedap.archie.rminfo.ModelInfoLookup;
import com.nedap.archie.rminfo.RMAttributeInfo;
import com.nedap.archie.rminfo.RMTypeInfo;
import org.openehr.bmm.core.BmmContainerProperty;
import org.openehr.bmm.core.BmmModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RmMultiplicityValidator {
    private MetaModel metaModel;

    public RmMultiplicityValidator(MetaModel metaModel) {
        this.metaModel = metaModel;
    }

    public List<RMObjectValidationMessage> validate(CAttribute attribute, String pathSoFar, Object attributeValue) {
        if (attributeValue instanceof Collection) {
            Collection<?> collectionValue = (Collection<?>) attributeValue;
            //validate multiplicity
            Cardinality cardinality = attribute.getCardinality();
            if (cardinality != null) {
                if (!cardinality.getInterval().has(collectionValue.size())) {
                    String message = RMObjectValidationMessageIds.rm_CARDINALITY_MISMATCH.getMessage(cardinality.getInterval().toString());
                    return Lists.newArrayList(new RMObjectValidationMessage(attribute, pathSoFar, message, RMObjectValidationMessageType.CARDINALITY_MISMATCH));
                }
            } else {
                // check BMM for cardinality
//                BmmModel bmmModel = metaModel.getSelectedBmmModel();
//                BmmContainerProperty bmmProperty = bmmModel.propertyAtPath (pathSoFar);
//
//
//                MultiplicityInterval interval = infoLookup.referenceModelPropMultiplicity(attribute.getParent().getRmTypeName(), attribute.getRmAttributeName());
//                if(interval != null) {
//                    if(!interval.has(collectionValue.size())) {
//                        String message = RMObjectValidationMessageIds.rm_CARDINALITY_MISMATCH.getMessage(interval.toString());
//                        return Lists.newArrayList(new RMObjectValidationMessage(attribute, pathSoFar, message, RMObjectValidationMessageType.CARDINALITY_MISMATCH));
//                    }
//                }

            }
        } else {
            MultiplicityInterval existence = attribute.getExistence();
            if (existence != null) {
                if (!existence.has(attributeValue == null ? 0 : 1)) {
                    String message = RMObjectValidationMessageIds.rm_EXISTENCE_MISMATCH.getMessage(attribute.getRmAttributeName(),
                            attribute.getParent() == null ? "Unknown type" : attribute.getParent().getRmTypeName(), existence.toString());

                    return Lists.newArrayList((new RMObjectValidationMessage(attribute, pathSoFar, message, RMObjectValidationMessageType.REQUIRED)));
                }
            }
        }
        return new ArrayList<>();
    }
}
