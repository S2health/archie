package org.s2.terminology;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.HashMap;
import java.util.Map;

public class TerminologyCodeSystems {

    private Map<String, TerminologyCodeSystem> codeSystems = new HashMap<>();

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

    @Override
    public String toString() {
        return "TerminologyCodeSystems{" +
                "codeSystems=" + codeSystems +
                '}';
    }
}

