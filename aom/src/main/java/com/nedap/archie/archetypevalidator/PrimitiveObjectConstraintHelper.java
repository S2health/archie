package com.nedap.archie.archetypevalidator;

import com.nedap.archie.aom.Archetype;
import com.nedap.archie.aom.CPrimitiveObject;
import com.nedap.archie.aom.primitives.COrdered;
import com.nedap.archie.aom.primitives.CString;
import com.nedap.archie.aom.primitives.CTemporal;
import com.nedap.archie.aom.primitives.CTerminologyCode;
import com.nedap.archie.aom.terminology.ArchetypeTerminology;
import com.nedap.archie.base.Interval;
import com.nedap.archie.base.OpenEHRBase;
import com.nedap.archie.base.RMObject;
import com.nedap.archie.base.terminology.TerminologyCode;
import com.nedap.archie.definitions.AdlCodeUtils;
import com.nedap.archie.terminology.OpenEHRTerminologyAccess;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

abstract public class  PrimitiveObjectConstraintHelper {
    protected boolean failOnUnknownTerminologyId = false;
    public void setFailOnUnknownTerminologyId (boolean flag) {
        failOnUnknownTerminologyId = flag;
    }

    /**
     * True if the given value is a valid value for this constraint
     * Must be overridden in classes where the AssumedAndDefaultValue is not the actual value.
     * For example when it is an interval or pattern
     *
     * @param value
     * @return
     */
    public <ValueType> boolean isValidValue(CPrimitiveObject<?, ValueType> cPrimitiveObject, ValueType value) {
        if(cPrimitiveObject instanceof CTemporal) {
            return isValidTemporalValue((CTemporal<ValueType>) cPrimitiveObject, value);

        } else if (cPrimitiveObject instanceof COrdered) {
            return isValidOrderedNotTemporalValue((COrdered<ValueType>) cPrimitiveObject, value);

        } else if (cPrimitiveObject instanceof CString) {
            return isValidStringValue((CString) cPrimitiveObject, (String) value);

        } else if (cPrimitiveObject instanceof CTerminologyCode) {
            // FIXME
            // the following line is the correct one, except for the problem that we have a
            // TerminologyCode in each RM, rather than a single common one. We should fix this.
            // return isValidTerminologyValue((CTerminologyCode) cPrimitiveObject, (TerminologyCode) value);

            // So for now we relax the typing and test later.
            return isValidTerminologyValue((CTerminologyCode) cPrimitiveObject, (OpenEHRBase) value);

        } else {
            return isValidOtherValue(cPrimitiveObject, value);
        }
    }

    protected <ValueType> boolean isValidOtherValue(CPrimitiveObject<?, ValueType> cPrimitiveObject, ValueType value) {
        if(cPrimitiveObject.getConstraint().isEmpty()) {
            return true;
        }
        for(Object constraint:cPrimitiveObject.getConstraint()) {
            if(Objects.equals(constraint, value)) {
                return true;
            }
        }
        return false;
    }

    protected <T> boolean isValidOrderedNotTemporalValue(COrdered<T> cOrdered, T value) {
        if(cOrdered.getConstraint().isEmpty()) {
            return true;
        }
        for(Interval<T> constraint:cOrdered.getConstraint()) {
            if(constraint.has(value)) {
                return true;
            }
        }
        return false;
    }

    protected boolean isValidStringValue(CString cString, String value) {
        if(cString.getConstraint().isEmpty()) {
            return true;
        }
        for(String constraint:cString.getConstraint()) {
            if(constraint.length() > 1 && CString.isRegexConstraint(constraint)) {
                //regexp. Strip first and last character and match. If you want to input
                //data starting and ending with '/', you cannot in the AOM, although ADL lets you express if just fine.
                //perhaps we should make the constraint object something more expressive than a String?
                if(matchesRegexp(value, constraint)) {
                    return true;
                }
            } else {
                //TODO: does case matter here?
                if(Objects.equals(value, constraint)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean matchesRegexp(String value, String constraint) {
        return value.matches(constraint.substring(1).substring(0, constraint.length()-2));
    }

    protected <T> boolean isValidTemporalValue(CTemporal<T> cTemporal, T value) {
        if(cTemporal.getConstraint().isEmpty() && cTemporal.getPatternConstraint() == null) {
            return true;
        }
        if(cTemporal.getPatternConstraint() == null) {
            return isValidOrderedNotTemporalValue(cTemporal, value);
        } else {
            //TODO: find a library that validates ISO 8601 patterns
            return true;
        }
    }

    abstract protected boolean isValidTerminologyValue(CTerminologyCode terminologyCode, OpenEHRBase value) ;

}
