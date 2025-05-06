package com.nedap.archie.terminology;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TerminologyCodeSystem {
    private String issuer;
    @JsonProperty("code_namespace")
    private String codeNamespace;
    @JsonProperty("terminology_uri")
    private String terminologyUri;
    @JsonProperty("terminology_code_uri_root")
    private String terminologyCodeUriRoot;
    private String description;

    // Constructors
    public TerminologyCodeSystem() {}

    public TerminologyCodeSystem(String issuer, String codeNamespace, String terminologyUri,
                                 String terminologyCodeUriRoot, String description) {
        this.issuer = issuer;
        this.codeNamespace = codeNamespace;
        this.terminologyUri = terminologyUri;
        this.terminologyCodeUriRoot = terminologyCodeUriRoot;
        this.description = description;
    }


    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getCodeNamespace() {
        return codeNamespace;
    }

    public void setCodeNamespace(String codeNamespace) {
        this.codeNamespace = codeNamespace;
    }

    public String getTerminologyUri() {
        return terminologyUri;
    }

    public void setTerminologyUri(String terminologyUri) {
        this.terminologyUri = terminologyUri;
    }

    public String getTerminologyCodeUriRoot() {
        return terminologyCodeUriRoot;
    }

    public void setTerminologyCodeUriRoot(String terminologyCodeUriRoot) {
        this.terminologyCodeUriRoot = terminologyCodeUriRoot;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Optional: toString() for easier debugging
    @Override
    public String toString() {
        return "TerminologyCodeSystem{" +
                "issuer='" + issuer + '\'' +
                ", codeNamespace='" + codeNamespace + '\'' +
                ", terminologyUri='" + terminologyUri + '\'' +
                ", terminologyCodeUriRoot='" + terminologyCodeUriRoot + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

