package org.s2.terminology;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class S2TerminologyAccess {

    static volatile S2TerminologyAccess instance;

    private static final Pattern snomedTermIdPattern = Pattern.compile("http://snomed.info/id/(?<code>[0-9]+)");
    // For Loinc need to match temporary UUID codes as well.
    private static final Pattern loincTermIdPattern = Pattern.compile("http://loinc\\.org/(?<code>[0-9]+-[0-9]|[a-f0-9-]+)");

    private S2TerminologyAccess() {

    }

    public static S2TerminologyAccess getInstance() {
        if(instance == null) {
            // for now
            instance = new S2TerminologyAccess();
            // TODO : read in valuesets
            // createInstance(READ_FROM_JSON);
        }
        return instance;
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

}
