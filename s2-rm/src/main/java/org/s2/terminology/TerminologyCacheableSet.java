package org.s2.terminology;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TerminologyCacheableSet {
    private final String id;
    private final String creationDate;
    private final String source;
    private TerminologySet set;
    private final String errorMessage;

    @JsonCreator
    public TerminologyCacheableSet(
            @JsonProperty("id") String id,
            @JsonProperty("creationDate") String creationDate,
            @JsonProperty("source") String source,
            @JsonProperty("set") TerminologySet set,
            @JsonProperty("errorMessage") String errorMessage) {
        this.id = id;
        this.creationDate = creationDate;
        this.source = source;
        this.set = set;
        this.errorMessage = errorMessage;
    }

    public TerminologyCacheableSet() {
        this(null, null, null, null, null);
    }

    public String getId() {
        return id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getSource() {
        return source;
    }

    public TerminologySet getSet() {
        return set;
    }

    public void setSet(TerminologySet set) {
        this.set = set;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @JsonIgnore
    public boolean isError() {
        return errorMessage != null;
    }
}
