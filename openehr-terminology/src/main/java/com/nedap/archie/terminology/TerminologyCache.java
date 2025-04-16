package com.nedap.archie.terminology;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TerminologyCache {
    private static final String VSET_PATH = "/s2-terminology/value-set/";
    private static final String CSET_PATH = "/s2-terminology/code-set/";
    private static final TerminologyCacheableSet MISSING_SET = new TerminologyCacheableSet(); // Marker value

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<String, TerminologyCacheableSet> cache = new ConcurrentHashMap<>();

    public TerminologyCacheableSet getSet(String setName, TerminologySetType type) {
        // TerminologyCacheableSet result = cache.computeIfAbsent(valueSetName, this::loadSet);
        TerminologyCacheableSet result = cache.computeIfAbsent(setName, key -> loadSet(key, type));
        return result == MISSING_SET ? null : result; // Return null if the value set was missing
    }

    private TerminologyCacheableSet loadSet(String setName,TerminologySetType type) {
        String path = null;
        switch (type) {
            case CSET: path = CSET_PATH + setName + ".json"; break;
            case VSET: path = VSET_PATH + setName + ".json"; break;
            default: return MISSING_SET;
        }

        try (InputStream inputStream = getClass().getResourceAsStream(path)) {
            if (inputStream == null) {
                return MISSING_SET; // Cache the fact that this value set does not exist
            }
            return objectMapper.readValue(inputStream, TerminologyCacheableSet.class);
        } catch (Exception e) {
            return MISSING_SET; // Also cache failures to avoid retrying
        }
    }
}
