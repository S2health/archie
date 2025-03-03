package org.s2.terminology;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true) // ✅ Ignore unknown JSON properties
public class TerminologySet {
    private final String issuer;
    private final String code;
    private final String description;
    private final TerminologySetType type;
    private final String system;
    private final List<TerminologyTermSimple> members;
    private final int totalMembers;

    private Map<String, TerminologyTermSimple> memberMap = new HashMap<>(); // Stores members as a map


    @JsonCreator
    public TerminologySet(
                          @JsonProperty("issuer") String issuer,
                          @JsonProperty("code") String code,
                          @JsonProperty("description") String description,
                          @JsonProperty("type") TerminologySetType type,
                          @JsonProperty("system") String system,
                          @JsonProperty("members") List<TerminologyTermSimple> members,
                          @JsonProperty("totalMembers") int totalMembers) {
        this.issuer = issuer;
        this.code = code;
        this.description = description;
        this.type = type;
        this.system = system;
        this.members = members;
        this.totalMembers = totalMembers;

        init(); // Call init() after object creation, build map

    }

    private void init() {
        if (members != null) {
            for (TerminologyTermSimple term : members) {
                memberMap.put(term.getCode(), term); // Store in map by code
            }
        }
    }

    public boolean hasMember(String code) {
        return memberMap.containsKey(code);
    }

    public String getIssuer() {
        return issuer;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public TerminologySetType getType() {
        return type;
    }

    public String getSystem() {
        return system;
    }

    public List<TerminologyTermSimple> getMembers() {
        return members;
    }

    public int getTotalMembers() {
        return totalMembers;
    }

    // Serialize "isComplete" but don't expect it during deserialization
    @JsonGetter("isComplete") // Ensures "isComplete" is included in JSON
    public boolean isComplete() {
        return members.size() == totalMembers;
    }
}
