package com.nedap.archie.archetypevalidator.validations;

import com.nedap.archie.aom.Archetype;
import com.nedap.archie.aom.CAttribute;
import com.nedap.archie.aom.utils.AOMUtils;
import com.nedap.archie.archetypevalidator.ErrorType;
import com.nedap.archie.definitions.AdlCodeUtils;
import org.openehr.utils.message.I18n;

/**
 * Not a full validation, but just the implementation for CAttribute validation as part of SpecializedDefinitionVBalidation to not end up with huge hard to read files
 */
public class SpecializedAttributeValidation {


    public boolean validateTest(CAttribute attribute, SpecializedDefinitionValidation validation) {
        if(!attribute.isSecondOrderConstrained()) {
            return !AdlCodeUtils.isPhantomPathAtLevel(attribute.getPathSegments(), validation.getFlatParent().specializationDepth()) &&
                validation.getFlatParent().hasPath(AdlCodeUtils.pathAtSpecializationLevel(attribute.getPathSegments(), validation.getFlatParent().specializationDepth()));
        }
        return false;
    }

    public void validate(CAttribute attribute, SpecializedDefinitionValidation validation) {
        Archetype flatParent = validation.getFlatParent();
        CAttribute parentAttribute = flatParent.itemAtPath(AdlCodeUtils.pathAtSpecializationLevel(attribute.getPathSegments(), flatParent.specializationDepth()));
        if(!attribute.cConformsTo(parentAttribute)) {

        //TODO: this should also be with differentialPath != null, but NO idea how that would work
          if(attribute.getDifferentialPath() == null && attribute.isMultiple() != parentAttribute.isMultiple()) {
              validation.addMessageWithPath(ErrorType.VSAM, attribute.path());
          }
          else if(!attribute.existenceConformsTo(parentAttribute)) {
                validation.addMessageWithPath(ErrorType.VSANCE, attribute.path(),
                        I18n.t("Attribute existence is {0}, which does not conform to parent existince {1}",
                                attribute.getExistence(), parentAttribute.getExistence()));
          } else if (!attribute.cardinalityConformsTo(parentAttribute)) {
              validation.addMessageWithPath(ErrorType.VSANCC, attribute.path(),
                      I18n.t("Attribute cardinality {0} does not conform to parent cardinality {1}",
                              attribute.getCardinality(), parentAttribute.getCardinality()));
          }
        }
    }
}
