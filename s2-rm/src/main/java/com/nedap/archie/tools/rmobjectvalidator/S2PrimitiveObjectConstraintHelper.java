package com.nedap.archie.tools.rmobjectvalidator;

import com.nedap.archie.aom.Archetype;
import com.nedap.archie.aom.primitives.CTerminologyCode;
import com.nedap.archie.aom.terminology.ArchetypeTerminology;
import com.nedap.archie.archetypevalidator.PrimitiveObjectConstraintHelper;
import com.nedap.archie.base.OpenEHRBase;
import com.nedap.archie.definitions.AdlCodeUtils;
import com.nedap.archie.terminology.S2TerminologyAccess;
import com.nedap.archie.terminology.TerminologyCodeSystems;
import com.nedap.archie.terminology.OpenEHRTerminologyAccess;

import org.s2.rm.base.foundation_types.terminology.TerminologyCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class S2PrimitiveObjectConstraintHelper extends PrimitiveObjectConstraintHelper {
    private static final Logger logger = LoggerFactory.getLogger(S2PrimitiveObjectConstraintHelper.class);

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
                } else if (terminologyId.equalsIgnoreCase("snomed") ||
                        terminologyId.equalsIgnoreCase("loinc") ||
                        terminologyId.equalsIgnoreCase("iso_639-1") ||
                        terminologyId.equalsIgnoreCase("iso_3166-3") ||
                        terminologyId.equalsIgnoreCase("iso_3166-1-alpha2")) {
                    values = getTerminologySetExpanded(terminologyCode, terminologyId);
                } else {
                    // This is not a local nor an openehr terminology.
                    // If a term binding is there, we may be able to validate, if external, we wil not be able to.
                    // Return true for now for non-local terminology values.
                    //TODO: implement checking for direct term bindings later
                    return !failOnUnknownTerminologyId;
                }

                String internalTerminologyValidationError = null;

                if (values != null && !values.isEmpty()) {
                    boolean result = value.getCodeString() != null && values.contains(value.getCodeString());
                    if (result) {
                        return true;
                    } else {
                        String archetypeId = terminologyCode.getArchetype().getArchetypeId().toString();
                        internalTerminologyValidationError = "ERROR: Internal terminology validation error in archetype " + archetypeId + " where terminology code " + terminologyCode + " does not contain " + value.getCodeString();
                    }
                }

                // s2 external vset check - JCoyle
                String s2ValuesetId = getS2ValuesetId(terminologyCode);
                if (s2ValuesetId != null && !s2ValuesetId.isEmpty()) {
                    boolean result = S2TerminologyAccess.getInstance().valuesetHasMember(s2ValuesetId, value.getCodeString());
                    if (result) {
                        return true;
                    } else {
                        String archetypeId = terminologyCode.getArchetype().getArchetypeId().toString();
                        logger.info("ERROR: External terminology validation error in archetype " + archetypeId + " where terminologyCode " + terminologyCode + " of S2ValuesetId " + s2ValuesetId + " does not contain " + value.getCodeString());
                    }
                }

                if (terminologyId != null && !terminologyId.isEmpty()) {
                    if (hasAcBoundCodesetForTerminologyId(terminologyCode, terminologyId)) {
                        boolean result = S2TerminologyAccess.getInstance().codesetHasMember(terminologyId, value.getCodeString());
                        if (result) {
                            return true;
                        } else {
                            String archetypeId = terminologyCode.getArchetype().getArchetypeId().toString();
                            logger.info("ERROR: External terminology validation error in archetype " + archetypeId + " where terminologyCode " + terminologyCode + " of terminology " + terminologyId + " does not contain " + value.getCodeString());
                        }
                    }
                }

                // Internal error only gets logged if external also fails
                if (internalTerminologyValidationError != null) {
                    logger.info(internalTerminologyValidationError);
                }
            }

        } else {
            return true;
        }

        return false;
    }

    // Tests if ac code is bound to a full code set
    private boolean hasAcBoundCodesetForTerminologyId(CTerminologyCode terminologyCode, String terminologyId) {
        TerminologyCodeSystems codeSystems = S2TerminologyAccess.getInstance().getTerminologyCodeSystems();

        ArchetypeTerminology terminology = getTerminology(terminologyCode);
        S2TerminologyAccess terminologyAccess = S2TerminologyAccess.getInstance();
        String acCode = terminologyAccess.parseAcCode(terminologyCode.toString());
        if(acCode.startsWith("ac")) {
            URI termBinding = terminology.getTermBinding(terminologyId, acCode);
            if(termBinding != null) {
                if(codeSystems.hasCodeSystemURI(termBinding.toString())) {
                    return true;
                }
            }
        }
        return false;
    }

    private String getS2ValuesetId(CTerminologyCode terminologyCode) {
        ArchetypeTerminology terminology = getTerminology(terminologyCode);
        S2TerminologyAccess terminologyAccess = S2TerminologyAccess.getInstance();
        String acCode = terminologyAccess.parseAcCode(terminologyCode.toString());
        if(acCode.startsWith("ac")) {
            URI termBinding = terminology.getTermBinding("s2", acCode);
            if (termBinding != null) {
                return terminologyAccess.parseS2TerminologyURI(termBinding.toString());
            }
        }
        return null;
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

    private List<String> getTerminologySetExpanded(CTerminologyCode terminologyCode, String terminologyId) {
        List<String> atCodes = terminologyCode.getValueSetExpanded();
        ArchetypeTerminology terminology = getTerminology(terminologyCode);
        S2TerminologyAccess terminologyAccess = S2TerminologyAccess.getInstance();
        List<String> result = new ArrayList<>();

        if(terminology == null) {
            return result;
        }

        for (String atCode : atCodes) {
            URI termBinding = terminology.getTermBinding(terminologyId, atCode);
            if (termBinding != null) {
                String code = null;

                if(terminologyId.equals("snomed")) {
                    code = terminologyAccess.parseSnomedTerminologyURI(termBinding.toString());
                } else if(terminologyId.equals("loinc")) {
                    code = terminologyAccess.parseLoincTerminologyURI(termBinding.toString());
                }else if(terminologyId.equals("iso_639-1")) {
                    code = terminologyAccess.parseIso6391TerminologyURI(termBinding.toString());
                } else if(terminologyId.equals("iso_639-3")) {
                    code = terminologyAccess.parseIso6393TerminologyURI(termBinding.toString());
                } else if(terminologyId.equals("iso_3166-1-alpha2")) {
                    code = terminologyAccess.parseIso31661alpha2PatternTerminologyURI(termBinding.toString());
                }

                if (code != null) {
                    result.add(code);
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
