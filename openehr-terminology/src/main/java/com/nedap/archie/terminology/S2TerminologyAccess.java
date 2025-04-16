package com.nedap.archie.terminology;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class S2TerminologyAccess {

    static volatile S2TerminologyAccess instance;

    private static final Logger logger = LoggerFactory.getLogger(S2TerminologyAccess.class);


    private static final Pattern acCodeIdPattern = Pattern.compile("\\{\\[(?<code>.+?)\\]\\}");

    private static final Pattern iso6391Pattern = Pattern.compile("http://iso.org/code_sets/iso_639-1/(?<code>\\w+)");
    private static final Pattern iso6393Pattern = Pattern.compile("http://iso.org/code_sets/iso_639-3/(?<code>\\w+)");
    private static final Pattern iso31661alpha2Pattern = Pattern.compile("http://iso.org/code_sets/iso_3166-1-alpha2/(?<code>\\w+)");

    private static final Pattern s2TermIdPattern = Pattern.compile("http://s2health.org/id/(?<code>s2\\.\\w+)");
    private static final Pattern snomedTermIdPattern = Pattern.compile("http://snomed.info/id/(?<code>[0-9]+)");
    // For Loinc need to match temporary UUID codes as well.
    private static final Pattern loincTermIdPattern = Pattern.compile("http://loinc\\.org/(?<code>[0-9]+-[0-9]|[a-f0-9-]+)");

    private TerminologyCache terminologyCache = new TerminologyCache();
    private TerminologyCodeSystems terminologyCodeSystems = null;

    private S2TerminologyAccess() {

    }

    public TerminologyCodeSystems getTerminologyCodeSystems() {
        if (terminologyCodeSystems == null) {
            try {
                terminologyCodeSystems = loadTerminologyCodeSystems();
            } catch (IOException e) {
                logger.error("Failed to load terminology code systems", e);
            }
        }

        if (terminologyCodeSystems == null) {
            terminologyCodeSystems = new TerminologyCodeSystems();
        }

        return terminologyCodeSystems;
    }

    public boolean valuesetHasMember(String valuesetId, String code) {
        TerminologyCacheableSet vset = terminologyCache.getSet(valuesetId, TerminologySetType.VSET);
        if (vset == null) { return false; }
        else {
            return vset.getSet().hasMember(code);
        }
    }

    public boolean codesetHasMember(String terminologyId, String code) {
        TerminologyCacheableSet cset = terminologyCache.getSet(terminologyId, TerminologySetType.CSET);
        if (cset == null) { return false; }
        else {
            return cset.getSet().hasMember(code);
        }
    }



    public static S2TerminologyAccess getInstance() {
        if(instance == null) {
            // for now
            instance = new S2TerminologyAccess();
            // createInstance(READ_FROM_JSON);
        }
        return instance;
    }

    public String parseAcCode(String acCode) {
        Matcher matcher = acCodeIdPattern.matcher(acCode);
        if(matcher.matches()) {
            return matcher.group("code");
        }
        return null;
    }

    public String parseSnomedTerminologyURI(String uri) {
        Matcher matcher = snomedTermIdPattern.matcher(uri);
        if(matcher.matches()) {
            return matcher.group("code");
        }
        return null;
    }

    public String parseLoincTerminologyURI(String uri) {
        Matcher matcher = loincTermIdPattern.matcher(uri);
        if(matcher.matches()) {
            return matcher.group("code");
        }
        return null;
    }

    public String parseS2TerminologyURI(String uri) {
        Matcher matcher = s2TermIdPattern.matcher(uri);
        if(matcher.matches()) {
            return matcher.group("code");
        }
        return null;
    }

    public String parseIso6391TerminologyURI(String uri) {
        Matcher matcher = iso6391Pattern.matcher(uri);
        if(matcher.matches()) {
            return matcher.group("code");
        }
        return null;
    }

    public String parseIso6393TerminologyURI(String uri) {
        Matcher matcher = iso6393Pattern.matcher(uri);
        if(matcher.matches()) {
            return matcher.group("code");
        }
        return null;
    }

    public String parseIso31661alpha2PatternTerminologyURI(String uri) {
        Matcher matcher = iso31661alpha2Pattern.matcher(uri);
        if(matcher.matches()) {
            return matcher.group("code");
        }
        return null;
    }

    private TerminologyCodeSystems loadTerminologyCodeSystems() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        InputStream stream = getClass().getResourceAsStream("/org/s2/terminology/code_system.json");
        if (stream == null) {
            throw new IOException("Could not find resource.");
        }

        return objectMapper.readValue(stream, TerminologyCodeSystems.class);
    }

}
