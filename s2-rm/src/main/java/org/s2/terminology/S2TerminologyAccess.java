package org.s2.terminology;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class S2TerminologyAccess {

    static volatile S2TerminologyAccess instance;

    private static final Pattern acCodeIdPattern = Pattern.compile("\\{\\[(?<code>.+?)\\]\\}");

    private static final Pattern s2TermIdPattern = Pattern.compile("http://s2health.org/id/(?<code>s2\\.\\w+)");
    private static final Pattern snomedTermIdPattern = Pattern.compile("http://snomed.info/id/(?<code>[0-9]+)");
    // For Loinc need to match temporary UUID codes as well.
    private static final Pattern loincTermIdPattern = Pattern.compile("http://loinc\\.org/(?<code>[0-9]+-[0-9]|[a-f0-9-]+)");

    private TerminologyValuesetCache terminologyValuesetCache = new TerminologyValuesetCache();

    private S2TerminologyAccess() {

    }

    public boolean valuesetHasMember(String valuesetId, String code) {
        TerminologyCacheableSet vset = terminologyValuesetCache.getValueSet(valuesetId);
        if (vset == null) { return false; }
        else {
            return vset.getSet().hasMember(code);
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

}
