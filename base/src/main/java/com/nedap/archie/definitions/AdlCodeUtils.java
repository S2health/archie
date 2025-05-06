package com.nedap.archie.definitions;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.nedap.archie.paths.PathSegment;
import com.nedap.archie.paths.PathUtil;
import com.nedap.archie.query.APathQuery;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Pattern;

public class AdlCodeUtils {

    private static Pattern idCodePattern = Pattern.compile("(id|at|ac)(0|[1-9][0-9]*)(\\.(0|[1-9][0-9]*))*");
    private static Pattern adl14CodePattern = Pattern.compile("(id|at|ac)([0-9]+)(\\.(0|[1-9][0-9]*))*");

    public static int getSpecializationDepthFromCode(String code) {
        if(code == null) {
            return -1;
        } else if(code.indexOf(AdlCodeDefinitions.SPECIALIZATION_SEPARATOR) < 0) {
            return 0;
        } else {
            return StringUtils.countMatches(code, String.valueOf(AdlCodeDefinitions.SPECIALIZATION_SEPARATOR));
        }
    }

    public static boolean isIdCode(String code) {
        return code.startsWith(AdlCodeDefinitions.ID_CODE_LEADER);
    }

    public static boolean isAdl14IdCode(String code) {
        return code.startsWith(AdlCodeDefinitions.VALUE_CODE_LEADER);
    }

    public static boolean isValueCode(String code) {
        return code.startsWith(AdlCodeDefinitions.VALUE_CODE_LEADER);
    }

    public static boolean isValueSetCode(String code) {
        return code.startsWith(AdlCodeDefinitions.VALUE_SET_CODE_LEADER);
    }


    public static boolean isOverriddenIdCode(String specializedNodeId, String parentNodeId) {
        if(specializedNodeId.equalsIgnoreCase(parentNodeId)) {
            return true;
        }

        return specializedNodeId.toLowerCase().startsWith(parentNodeId.toLowerCase() + ".");
    }
    public static boolean isValidValueSetCode(String code) {
        return isValueSetCode(code) && isValidCode(code);
    }

    public static boolean isValidCode(String code) {
        if(code == null) {
            return false;
        }
        return idCodePattern.matcher(code).matches();
    }

    /**
     * Get the numeric node id from a valid id code without any prefix (like at, ac or id)
     * @param nodeId the node id to strip the prefix of.
     * @return the numeric node id without any prefix
     */
    public static String stripPrefix(String nodeId) {
        if(isValidCode(nodeId)) {
            return nodeId.substring(2);
        }
        return nodeId;
    }

    public static String pathAtSpecializationLevel(List<PathSegment> pathSegments, int level) {
        //todo: this doesn't clone the original
        for(PathSegment segment:pathSegments) {
            if(segment.getNodeId() != null && isValidCode(segment.getNodeId()) && getSpecializationDepthFromCode(segment.getNodeId()) > level) {
                segment.setNodeId(codeAtLevel(segment.getNodeId(), level));
            }
        }
        return PathUtil.getPath(pathSegments);
    }

    public static String codeAtLevel(String nodeId, int level) {
        NodeIdUtil nodeIdUtil = new NodeIdUtil(nodeId);
        List<Integer> codes = new ArrayList<>();
        for(int i = 0; i <= level && i < nodeIdUtil.getCodes().size();i++) {
            codes.add(nodeIdUtil.getCodes().get(i));
        }
        //remove leading .0 codes - they are not present in the code at the given level
        int numberOfCodesToRemove = 0;
        for(int i = codes.size()-1; i >= 0 ; i--) {
            if(codes.get(i).intValue() == 0) {
                numberOfCodesToRemove++;
            } else {
                break;
            }
        }
        if(numberOfCodesToRemove > 0) {
            codes = codes.subList(0, codes.size()-numberOfCodesToRemove);
        }
        return nodeIdUtil.getPrefix() + Joiner.on(AdlCodeDefinitions.SPECIALIZATION_SEPARATOR).join(codes);

    }

    public static int codeIndexAtLevel(String nodeId, int specialisationDepth) {
        NodeIdUtil nodeIdUtil = new NodeIdUtil(nodeId);
        if(specialisationDepth < 0 || specialisationDepth >= nodeIdUtil.getCodes().size()) {
            throw new IllegalArgumentException("code is not valid at specialization depth " + specialisationDepth);
        }
        return nodeIdUtil.getCodes().get(specialisationDepth);
    }


    /**
     * Returns true if at least one [idx] predicate is present in the path
     * @param path
     * @return
     */
    public static boolean isArchetypePath(String path) {
        APathQuery query = new APathQuery(path);
        for(PathSegment segment:query.getPathSegments()) {
            if(segment.getNodeId() != null || segment.getArchetypeRef() != null) {
                return true;
            }
        }
        return false;
    }

    //check if the last node id in the path has a bigger specialization level than the specialization level of the parent
    //but it does a little loop to check if it happens somewhere else as well. ok...
    public static boolean isPhantomPathAtLevel(List<PathSegment> pathSegments, int specializationDepth) {
        for(int i = pathSegments.size()-1; i >=0; i--) {
            String nodeId = pathSegments.get(i).getNodeId();
            if(nodeId != null && isValidCode(nodeId) && specializationDepth > getSpecializationDepthFromCode(nodeId)) {
                return codeExistsAtLevel(nodeId, specializationDepth);
            }
        }
        return false;
    }

    public static boolean codeExistsAtLevel(String nodeId, int specializationDepth) {
        NodeIdUtil nodeIdUtil = new NodeIdUtil(nodeId);
        int specializationDepthOfCode = getSpecializationDepthFromCode(nodeId);
        if(specializationDepth <= specializationDepthOfCode) {
            String code = "";
            for(int i = 0; i <= specializationDepth; i++) {
                code += nodeIdUtil.getCodes().get(i);
            }
            return Integer.parseInt(code) > 0;
        }
        return false;
    }

    public static boolean codesConformant(String childNodeId, String parentNodeId) {
        return isValidCode(childNodeId) && childNodeId.startsWith(parentNodeId) &&
                (childNodeId.length() == parentNodeId.length() || (childNodeId.length() > parentNodeId.length() && childNodeId.charAt(parentNodeId.length()) == AdlCodeDefinitions.SPECIALIZATION_SEPARATOR));

    }

    /** Get the maximum code used at the given specialization level. useful for generating new codes*/
    public static int getMaximumIdCode(int specializationDepth, Collection<String> usedIdCodes) {

        int maximumIdCode = 0;
        for(String code:usedIdCodes) {
            if (code.length() > 2) {
                int numberOfDots = getSpecializationDepthFromCode(code);
                if(specializationDepth == numberOfDots) {
                    try {
                        int numericCode = numberOfDots == 0 ? Integer.parseInt(code.substring(2)) : Integer.parseInt(code.substring(code.lastIndexOf('.') + 1));
                        maximumIdCode = Math.max(numericCode, maximumIdCode);
                    } catch (NumberFormatException ex) {
                        //TODO: get rid of this, temporary for term codes that still need conversion!
                    }
                }
            }
        }
        return maximumIdCode;
    }

    /** Get the maximum code used at the given specialization level. useful for generating new codes*/
    public static int getMaximumIdCode(int specializationDepth, String prefix, Collection<String> usedIdCodes) {
        if(specializationDepth == 0) {
            throw new IllegalArgumentException("can only get the maximum code with prefix at a specialization depth > 0");
        }
        int maximumIdCode = 0;
        for(String code:usedIdCodes) {
            if(code.startsWith(prefix + ".")) {
                int numberOfDots = CharMatcher.is(AdlCodeDefinitions.SPECIALIZATION_SEPARATOR).countIn(code);
                if(specializationDepth == numberOfDots) {
                    int numericCode = Integer.parseInt(code.substring(code.lastIndexOf('.')+1));
                    maximumIdCode = Math.max(numericCode, maximumIdCode);
                }
            }
        }
        return maximumIdCode;
    }

    /**
     * Given a code such as 'id4.1.0.0.1', return the nearest code that exists in a parent. In this example,
     * returns id4.1, so removes all zeros.
     * @param nodeId
     * @return
     */
    public static String getCodeInNearestParent(String nodeId) {

        NodeIdUtil nodeIdUtil = new NodeIdUtil(nodeId);

        List<Integer> codes = nodeIdUtil.getCodes();
        int newDepth = 0;
        for(int i = codes.size()-2; i >= 0; i--) {
            if(codes.get(i) != 0) {
                newDepth = i;
                break;
            }
        }
        return nodeIdUtil.getPrefix() + Joiner.on('.').join(codes.subList(0, newDepth+1));

    }

    public static boolean isValidADL14Code(String code) {
        if(code == null) {
            return false;
        }
        return adl14CodePattern.matcher(code).matches();
    }

}
