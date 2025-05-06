package com.nedap.archie.adl14.log;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nedap.archie.aom.terminology.ArchetypeTerm;
import com.nedap.archie.aom.utils.AOMUtils;
import com.nedap.archie.definitions.AdlCodeUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ConvertedCodeResult {
    private String originalCode;
    //it's possible a single code gets converted to multiple codes (eg id, at, ac), so store that here
    private List<String> convertedCodes = new ArrayList<>();
    private Map<String, ArchetypeTerm> terms = new LinkedHashMap<>();

    public ConvertedCodeResult(String oldCode, String newCode) {
        originalCode = oldCode;
        convertedCodes.add(newCode);
    }
    @JsonIgnore
    public boolean isSingleReplacement() {
        return convertedCodes.size() == 1;
    }

    public String getOriginalCode() {
        return originalCode;
    }

    public List<String> getConvertedCodes() {
        return convertedCodes;
    }

    public void addNewCode(String newCode) {
        convertedCodes.add(newCode);
    }

    @JsonIgnore
    public String getIdCode() {
        for(String code:convertedCodes) {
            if(AdlCodeUtils.isIdCode(code)) {
                return code;
            }
        }
        return null;
    }
    @JsonIgnore
    public boolean hasIdCode() {
        return getIdCode() != null;
    }

    @JsonIgnore
    public String getValueCode() {
        for(String code:convertedCodes) {
            if(AdlCodeUtils.isValueCode(code)) {
                return code;
            }
        }
        return null;
    }
    @JsonIgnore
    public boolean hasValueCode() {
        return getValueCode() != null;
    }

    public Map<String, ArchetypeTerm> getTerms() {
        return terms;
    }

    public void setTerms(Map<String, ArchetypeTerm> terms) {
        this.terms = terms;
    }

    public void addTerm(String language, ArchetypeTerm term) {
        this.terms.put(language, term);
    }
}