package com.nedap.archie.archetypevalidator;

import com.nedap.archie.aom.Archetype;
import com.nedap.archie.aom.primitives.CTerminologyCode;
import com.nedap.archie.aom.terminology.ArchetypeTerminology;
import com.nedap.archie.base.OpenEHRBase;
import com.nedap.archie.base.RMObject;
import com.nedap.archie.base.terminology.TerminologyCode;
import com.nedap.archie.definitions.AdlCodeUtils;
import com.nedap.archie.terminology.OpenEHRTerminologyAccess;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class  OpenEhrPrimitiveObjectConstraintHelper extends PrimitiveObjectConstraintHelper {

    protected boolean isValidTerminologyValue(CTerminologyCode terminologyCode, OpenEHRBase rmValue) {
        if (terminologyCode.getConstraint().isEmpty())
            return true;
        else if (terminologyCode.isConstraintRequired()) {
            if (rmValue == null)
                return false;
            else if (rmValue instanceof TerminologyCode) {
                TerminologyCode value = (TerminologyCode) rmValue;

                List<String> values;
                String terminologyId = value.getTerminologyId();
                if (terminologyId == null || terminologyId.equalsIgnoreCase("local") || AdlCodeUtils.isValueSetCode(value.getTerminologyId())) {
                    values = terminologyCode.getValueSetExpanded();
                } else if (terminologyId.equalsIgnoreCase("openehr")) {
                    values = getOpenEHRValueSetExpanded(terminologyCode);
                } else if (terminologyId.equalsIgnoreCase("IANA_media-types")) {
                    values = getIANAMediaTypesValueSetExpanded(terminologyCode);
                } else {
                    // This is not a local nor an openehr terminology.
                    // If a term binding is there, we may be able to validate, if external, we wil not be able to.
                    // Return true for now for non-local terminology values.
                    //TODO: implement checking for direct term bindings later
                    return !failOnUnknownTerminologyId;
                }

                if (values != null && !values.isEmpty()) {
                    return value.getCodeString() != null && values.contains(value.getCodeString());
                }
            }
        } else {
            return true;
        }

        return false;
    }

    private List<String> getOpenEHRValueSetExpanded(CTerminologyCode terminologyCode) {
        List<String> atCodes = terminologyCode.getValueSetExpanded();
        ArchetypeTerminology terminology = getTerminology(terminologyCode);
        OpenEHRTerminologyAccess terminologyAccess = OpenEHRTerminologyAccess.getInstance();
        List<String> result = new ArrayList<>();

        if(terminology == null) {
            return result;
        }

        for(String atCode : atCodes) {
            URI termBinding = terminology.getTermBinding("openehr", atCode);
            if (termBinding != null) {
                String code = terminologyAccess.parseTerminologyURI(termBinding.toString());
                if (code != null) {
                    result.add(code);
                }
            }
        }

        return result;
    }

    private List<String> getIANAMediaTypesValueSetExpanded(CTerminologyCode terminologyCode) {
        List<String> atCodes = terminologyCode.getValueSetExpanded();
        ArchetypeTerminology terminology = getTerminology(terminologyCode);
        OpenEHRTerminologyAccess terminologyAccess = OpenEHRTerminologyAccess.getInstance();
        List<String> result = new ArrayList<>();

        if(terminology == null) {
            return result;
        }

        for (String atCode : atCodes) {
            URI termBinding = terminology.getTermBinding("IANA_media-types", atCode);
            if (termBinding != null) {
                String value = terminologyAccess.parseIANATerminologyURI(termBinding.toString());
                if (value != null) {
                    result.add(value);
                }
            }
        }

        return result;
    }

    private ArchetypeTerminology getTerminology(CTerminologyCode cTerminologyCode) {
        Archetype archetype = cTerminologyCode.getArchetype();
        if(archetype != null) {
            //ideally this would not happen, but no reference to archetype exists in leaf constraints in rules so far
            //so for now fix it so it doesn't throw a NullPointerException
            return archetype.getTerminology(cTerminologyCode);
        }
        return null;
    }
}
