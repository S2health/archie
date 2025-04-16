package com.nedap.archie.terminology;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class TerminologyCodeSystems {

    private Map<String, TerminologyCodeSystem> codeSystems = new HashMap<>();
    @JsonIgnore
    private HashSet<String> codeSystemURIs = null;

    @JsonAnySetter
    public void addCodeSystem(String key, TerminologyCodeSystem value) {
        codeSystems.put(key, value);
    }

    public Map<String, TerminologyCodeSystem> getCodeSystems() {
        return codeSystems;
    }

    public void setCodeSystems(Map<String, TerminologyCodeSystem> codeSystems) {
        this.codeSystems = codeSystems;
    }

    public boolean hasCodeSystemURI(String uri) {
        if(codeSystemURIs == null) {
            if(codeSystems != null && !codeSystems.isEmpty()) {
                codeSystemURIs = new HashSet<>();
                for(TerminologyCodeSystem codeSystem : codeSystems.values()) {
                    codeSystemURIs.add(codeSystem.getTerminologyUri());
                }
            }
        }

        if(codeSystemURIs != null) {
            return codeSystemURIs.contains(uri);
        }
        return false;
    }

    @Override
    public String toString() {
        return "TerminologyCodeSystems{" +
                "codeSystems=" + codeSystems +
                '}';
    }
}

