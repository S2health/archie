package com.nedap.archie.terminology;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TerminologyTermSimple {
    private final String code;
    private final String description;
    private final String system;

    @JsonCreator
    public TerminologyTermSimple(
            @JsonProperty("code") String code,
            @JsonProperty("description") String description,
            @JsonProperty("system") String system) {
        this.code = code;
        this.description = description;
        this.system = system;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getSystem() {
        return system;
    }
}
