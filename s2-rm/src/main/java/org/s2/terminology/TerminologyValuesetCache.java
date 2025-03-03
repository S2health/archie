package org.s2.terminology;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TerminologyValuesetCache {
    private static final String BASE_PATH = "/s2-terminology/value-set/";
    private static final TerminologyCacheableSet MISSING_VALUE_SET = new TerminologyCacheableSet(); // Marker value

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<String, TerminologyCacheableSet> cache = new ConcurrentHashMap<>();

    public TerminologyCacheableSet getValueSet(String valueSetName) {
        TerminologyCacheableSet result = cache.computeIfAbsent(valueSetName, this::loadValueSet);
        return result == MISSING_VALUE_SET ? null : result; // Return null if the value set was missing
    }

    private TerminologyCacheableSet loadValueSet(String valueSetName) {
        String path = BASE_PATH + valueSetName + ".json";
        try (InputStream inputStream = getClass().getResourceAsStream(path)) {
            if (inputStream == null) {
                return MISSING_VALUE_SET; // Cache the fact that this value set does not exist
            }
            return objectMapper.readValue(inputStream, TerminologyCacheableSet.class);
        } catch (Exception e) {
            return MISSING_VALUE_SET; // Also cache failures to avoid retrying
        }
    }
}
