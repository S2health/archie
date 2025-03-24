package com.nedap.archie.flattener;

import com.nedap.archie.aom.*;
import com.nedap.archie.aom.terminology.ArchetypeTerm;
import com.nedap.archie.aom.terminology.ArchetypeTerminology;
import com.nedap.archie.aom.terminology.ValueSet;
import com.nedap.archie.aom.utils.AOMUtils;
import com.nedap.archie.definitions.AdlDefinitions;
import com.nedap.archie.query.ComplexObjectProxyReplacement;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Creates operational templates. Not to be used externally, use the Flattener with the right parameters to
 * create operational templates
 */
class OperationalTemplateCreator {

    private final Flattener flattener;

    OperationalTemplateCreator(Flattener flattener) {
        this.flattener = flattener;
    }

    public static OperationalTemplate createOperationalTemplate(Archetype archetype) {
        Archetype clone = archetype.clone(); //clone so we do not overwrite the parent archetype. never
        OperationalTemplate result = new OperationalTemplate();
        result.setArchetypeId((ArchetypeHRID) archetype.getArchetypeId().clone());
        result.setDefinition(clone.getDefinition());
        result.setDifferential(false);

        result.setRmRelease(clone.getRmRelease());
        result.setAdlVersion(clone.getAdlVersion());
        result.setTerminology(clone.getTerminology());
        result.setGenerated(true);
        result.setOtherMetaData(clone.getOtherMetaData());
        result.setRules(clone.getRules());
        result.setBuildUid(clone.getBuildUid());
        result.setDescription(clone.getDescription());
        result.setOriginalLanguage(clone.getOriginalLanguage());
        result.setTranslations(clone.getTranslations());
        result.setAnnotations(clone.getAnnotations());
        result.setRmOverlay(clone.getRmOverlay());

        return result;
    }

    public static void overrideArchetypeId(Archetype result, Archetype override) {
        result.setArchetypeId(override.getArchetypeId());
        result.setParentArchetypeId(override.getParentArchetypeId());
    }

    public void expandReferences(OperationalTemplate result, int depth) { //should this be OperationalTemplate?
        //TODO: closing archetype slots should be moved to AFTER including other archetypes
        closeArchetypeSlots(result);
        fillArchetypeRootsOpt(result, depth);
        fillComplexObjectProxies(result);
    }

    static void expandValueSets(OperationalTemplate operationalTemplate) {
        List<ArchetypeTerminology> terminologies = Arrays.asList(operationalTemplate.getTerminology());
       // terminologies.addAll(operationalTemplate.getComponentTerminologies().values());
        for(ArchetypeTerminology terminology:terminologies) {
            for(ValueSet valueSet:terminology.getValueSets().values()) {
                valueSet.setMembers(AOMUtils.getExpandedValueSetMembers(terminology.getValueSets(), valueSet));
            }
        }
    }

    /** Zero occurrences and existence constraint processing when creating OPT templates. Removes attributes */
    public void removeZeroOccurrencesConstraints(Archetype archetype) {
        Stack<CObject> workList = new Stack<>();
        workList.push(archetype.getDefinition());
        while (!workList.isEmpty()) {
            CObject object = workList.pop();
            List<CAttribute> attributesToRemove = new ArrayList<>();
            for (CAttribute attribute : object.getAttributes()) {
                if (attribute.getExistence() != null && attribute.getExistence().getUpper() == 0 && !attribute.getExistence().isUpperUnbounded()) {
                    attributesToRemove.add(attribute);
                } else {
                    List<CObject> objectsToRemove = new ArrayList<>();
                    for (CObject child : attribute.getChildren()) {
                        if (!child.isAllowed()) {
                            objectsToRemove.add(child);
                        }
                        workList.push(child);
                    }
                    FlattenerUtil.removeAnnotationsForArchetypeConstraints(archetype, objectsToRemove);
                    attribute.getChildren().removeAll(objectsToRemove);
                }

            }
            FlattenerUtil.removeAnnotationsForArchetypeConstraints(archetype, attributesToRemove);
            object.getAttributes().removeAll(attributesToRemove);
        }
    }

    /** Zero occurrences and existence constraint processing when creating OPT templates. Removes attributes */
    public void fillEmptyOccurrences(Archetype archetype) {
        Stack<CObject> workList = new Stack<>();
        workList.push(archetype.getDefinition());
        while (!workList.isEmpty()) {
            CObject object = workList.pop();
            if( (object instanceof CComplexObject || object instanceof ArchetypeSlot || object instanceof CComplexObjectProxy)
                    && object.getOccurrences() == null) {
                object.setOccurrences(object.effectiveOccurrences(flattener.getMetaModels()::referenceModelPropMultiplicity));
            }
            for (CAttribute attribute : object.getAttributes()) {

                for (CObject child : attribute.getChildren()) {
                    workList.push(child);
                }
            }
        }
    }


    private void closeArchetypeSlots(OperationalTemplate archetype) {
        if(!getConfig().isCloseArchetypeSlots()) {
            return;
        }
        Stack<CObject> workList = new Stack<>();
        workList.push(archetype.getDefinition());
        while(!workList.isEmpty()) {
            CObject object = workList.pop();
            for(CAttribute attribute:object.getAttributes()) {
                List<CObject> toRemove = new ArrayList<>();
                for(CObject child:attribute.getChildren()) {
                    if(child instanceof ArchetypeSlot) { //use_archetype
                        if(((ArchetypeSlot) child).isClosed()) {
                            toRemove.add(child);
                        }
                    }
                    workList.push(child);
                }
                attribute.getChildren().removeAll(toRemove);
            }
        }
    }

    /**
     * Fill all CArchetypeRoot nodes within result OPT with copies of the archetypes or templates they refer to
     * @param result outer OPT
     * @param depth depth of archetype chaining through CArchetypeRoots; used to prevent over-deep filling
     */
    private void fillArchetypeRootsOpt (OperationalTemplate result, int depth) {
        if (!getConfig().isFillArchetypeRoots()) {
            return;
        }

        // initialise the stack used to detect cyclic archetype inclusion, i.e. use_archetype statements
        // that cause direct or indirect reference cycles
        fillersOnCurrentPath = new Stack<>();

        fillArchetypeRootsArchetype(result, result.getArchetypeId().getFullId(), result.getDefinition(), depth);
    }

    /**
     *
     * @param result : the OPT being built
     * @param rootArchId : the id of the archetype sub-tree within the OPT
     * @param archRootInOpt : archetype root point within `result` OPT
     * @param depth : depth of chaining, used to limit non-recursive inclusion
     */
    private void fillArchetypeRootsArchetype(OperationalTemplate result, String rootArchId, CComplexObject archRootInOpt, int depth) throws RuntimeException {

        Stack<CObject> workList = new Stack<>();
        workList.push (archRootInOpt);

        Archetype rootArchetype = flattener.getRepository().getArchetype (rootArchId);
        if (rootArchetype == null)
            throw new RuntimeException ("Root archetype :" + rootArchId + " not found in OPT repository (including overlays).");
        // if the archetype is a template, get its overlays - this takes care of templates included in templates
        else if (rootArchetype instanceof Template) {
            Template childTemplate = (Template) rootArchetype;
            for (TemplateOverlay overlay:childTemplate.getTemplateOverlays()) {
                flattener.getRepository().addExtraArchetype(overlay);
            }
        }

        while (!workList.isEmpty()) {
            CObject object = workList.pop();
            for (CAttribute attribute:object.getAttributes()) {
                List<CObject> children = attribute.getChildren();
                for (CObject child:children) {
                    // deal with CArchetypeRoot node that currently has no attributes, i.e. is empty
                    if (child instanceof CArchetypeRoot &&
                            flattener.isCreateOperationalTemplate() &&
                            (child.getAttributes() == null || child.getAttributes().isEmpty()))
                    {
                        CArchetypeRoot car = (CArchetypeRoot) child;
                        Archetype supplierArchetype = flattener.getRepository().getArchetype (car.getArchetypeRef());

                        // if we can't find the supplier archetype bail out
                        if (supplierArchetype == null) {
                            if (getConfig().isFailOnMissingUsedArchetype())
                                throw new RuntimeException ("Archetype with reference :" + car.getArchetypeRef() + " not found.");

                        // don't do anything if we've hit the recursion limit. We can only check it here because we've only
                        // just worked out the resolved archetype id - the archetype ref is not reliable for this purpose
                        } else {
                            if (fillersOnCurrentPathCount(supplierArchetype.getArchetypeId().getFullId())
                                    <= AdlDefinitions.TemplateMaxRecursionDepth && depth < AdlDefinitions.TemplateMaxDepth)
                            {
                                // when we know the archetype that a CArchetypeRoot ref resolves to, we put it on the
                                // filler id stack, which is used to detect cycles in archetype filler referencing
                                fillersOnCurrentPath.push(rootArchId);
String indent = StringUtils.repeat('x', (int) fillersOnCurrentPath.size());
System.out.println(indent + "++++ push " + rootArchId);

                                fillArchetypeRoot (car, supplierArchetype, result, depth + 1);

                                // car node is now populated with flattened (copy of) supplier archetype
                                fillArchetypeRootsArchetype(result, supplierArchetype.getArchetypeId().getFullId(), car, depth);

                                fillersOnCurrentPath.pop();
System.out.println(indent + "      pop " + rootArchId);
                            }
                        }
                    } else {
                        workList.push(child);
                    }
                }
            }
        }
    }

    private void fillComplexObjectProxies(OperationalTemplate result) throws RuntimeException {
        if(!getConfig().isReplaceUseNode()) {
            return;
        }
        Stack<CObject> workList = new Stack<>();
        workList.push(result.getDefinition());
        List<ComplexObjectProxyReplacement> replacements = new ArrayList<>();
        while(!workList.isEmpty()) {
            CObject object = workList.pop();
            for(CAttribute attribute:object.getAttributes()) {
                for(CObject child:attribute.getChildren()) {
                    if(child instanceof CComplexObjectProxy) { //use_node
                        ComplexObjectProxyReplacement possibleReplacement =
                                ComplexObjectProxyReplacement.getComplexObjectProxyReplacement((CComplexObjectProxy) child);
                        if(possibleReplacement != null) {
                            replacements.add(possibleReplacement);
                        } else {
                            throw new RuntimeException("cannot find target in CComplexObjectProxy");
                        }

                    }
                    workList.push(child);
                }
            }
        }
        for(ComplexObjectProxyReplacement replacement:replacements) {
            replacement.replace();
        }
    }

    /**
     * Fill archetype root car, within result, with (a clone of) supplier archetype
     */
    private void fillArchetypeRoot(CArchetypeRoot car, Archetype supplierArchetype, OperationalTemplate result, int depth) {

        String newArchetypeRef = car.getArchetypeRef();

        String supplierArchetypeFullId = supplierArchetype.getArchetypeId().getFullId();

        if (supplierArchetype instanceof TemplateOverlay){
            //we want to be able to check which archetype this is in the UI. If it's an overlay, that means retrieving the non-operational template
            //which is a hassle.
            //That's a problem. Is this the way to fix is?
            newArchetypeRef = supplierArchetype.getParentArchetypeId();
        }

        // The following creates a new clone
        Archetype supplierArchetypeFlattened = flattener.getNewFlattener().flatten (supplierArchetype, depth);

        //
        CComplexObject carToFill = car;
        if (flattener.isUseComplexObjectForArchetypeSlotReplacement()) {
            carToFill = supplierArchetypeFlattened.getDefinition();
            car.getParent().replaceChild (car.getNodeId(), carToFill);
        } else {
            carToFill.setAttributes (supplierArchetypeFlattened.getDefinition().getAttributes());
            carToFill.setAttributeTuples (supplierArchetypeFlattened.getDefinition().getAttributeTuples());
            carToFill.setDefaultValue (supplierArchetypeFlattened.getDefinition().getDefaultValue());
        }

        ArchetypeTerminology terminology = supplierArchetypeFlattened.getTerminology();

        //The node id will be replaced from "id1" to something like "openEHR-EHR-COMPOSITION.template_overlay.v1.0.0
        //so store it in the terminology as well
        Map<String, Map<String, ArchetypeTerm>> termDefinitions = terminology.getTermDefinitions();

        for (String language: termDefinitions.keySet()) {
            Map<String, ArchetypeTerm> translations = termDefinitions.get(language);
            translations.put(supplierArchetypeFullId, TerminologyFlattener.getTerm(terminology.getTermDefinitions(), language, supplierArchetypeFlattened.getDefinition().getNodeId()));
        }

        //rootToFill.setNodeId(newNodeId);
        if (!flattener.isUseComplexObjectForArchetypeSlotReplacement()) {
            car.setArchetypeRef (supplierArchetypeFullId);
        }

        //todo: should we filter this?
        if (supplierArchetypeFlattened instanceof OperationalTemplate) {
            OperationalTemplate template = (OperationalTemplate) supplierArchetypeFlattened;
            //add all the component terminologies, otherwise we lose translation
            for (String subarchetypeId:template.getComponentTerminologies().keySet()) {
                result.addComponentTerminology(subarchetypeId, template.getComponentTerminologies().get(subarchetypeId));
            }
        }

        result.addComponentTerminology(supplierArchetypeFullId, terminology);

        String prefix = supplierArchetypeFlattened.getArchetypeId().getConceptId() + "_";
        flattener.getRulesFlattener().combineRules(supplierArchetypeFlattened, car.getArchetype(), prefix, prefix, carToFill.getPath(), false);
        flattener.getAnnotationsAndOverlaysFlattener().addAnnotationsWithPathPrefix(carToFill.getPath(), supplierArchetypeFlattened, result);
        flattener.getAnnotationsAndOverlaysFlattener().addVisibilityWithPathPrefix(carToFill.getPath(), supplierArchetypeFlattened, result);
        //todo: do we have to put something in the terminology extracts?
        //templateResult.addTerminologyExtract(child.getNodeId(), archetype.getTerminology().);
    }

    private FlattenerConfiguration getConfig() {
        return flattener.getConfiguration();
    }

    private Stack<String> fillersOnCurrentPath = new Stack<>();

    public long fillersOnCurrentPathCount(String archetypeId) {
        return fillersOnCurrentPath.stream()
                .filter(str -> str.equals(archetypeId))
                .count();
    }

}
