package org.s2.terminology;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TerminologySetType {
    VSET("vset"),
    CSET("cset"),
    OSET("oset");

    private final String value;

    TerminologySetType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static TerminologySetType fromString(String value) {
        for (TerminologySetType type : values()) {
            if (type.value.equalsIgnoreCase(value)) { // Allow case-insensitive match
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown TerminologySetType: " + value);
    }
}

