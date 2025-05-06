package com.nedap.archie.tools.json;

import com.nedap.archie.json.JSONSchemaCreator;

import java.util.ArrayList;
import java.util.HashSet;

public class S2RmJsonSchemaCreator extends JSONSchemaCreator {

    public S2RmJsonSchemaCreator() {
        super();

        ArrayList<String> rootTypes = new ArrayList<>();
        rootTypes.add("Accountability");
        rootTypes.add("Action");
        rootTypes.add("Activity");
        rootTypes.add("AdminEntry");
        rootTypes.add("Agent");
        rootTypes.add("AggregateAgent");
        rootTypes.add("Assessment");
        rootTypes.add("Automaton");
        rootTypes.add("BillingEncounter");
        rootTypes.add("Capability");
        rootTypes.add("CommsAddressUse");
        rootTypes.add("Composition");
        rootTypes.add("ConsumableUse");
        rootTypes.add("Contribution");
        rootTypes.add("DirectObservation");
        rootTypes.add("Ehr");
        rootTypes.add("EhrAccess");
        rootTypes.add("EhrStatus");
        rootTypes.add("EntityKindRelationship");
        rootTypes.add("EntityKindRelationshipGroup");
        rootTypes.add("EntityRefNode");
        rootTypes.add("EntityRelationship");
        rootTypes.add("EntityRelationshipGroup");
        rootTypes.add("EventContext");
        rootTypes.add("Folder");
        rootTypes.add("IdUse");
        rootTypes.add("Imaging");
        rootTypes.add("ImportedVersion");
        rootTypes.add("IndirectObservation");
        rootTypes.add("InfoNode");
        rootTypes.add("IntervalEvent");
        rootTypes.add("LabResult");
        rootTypes.add("LanguageCapability");
        rootTypes.add("Link");
        rootTypes.add("LocationAddressUse");
        rootTypes.add("Order");
        rootTypes.add("OrderTracking");
        rootTypes.add("OrgEntity");
        rootTypes.add("OrgUnit");
        rootTypes.add("Organization");
        rootTypes.add("OriginalVersion");
        rootTypes.add("PartyIdentity");
        rootTypes.add("PartyRelationship");
        rootTypes.add("Person");
        rootTypes.add("Persona");
        rootTypes.add("PhysicalEntity");
        rootTypes.add("PhysicalEntityKind");
        rootTypes.add("PointEvent");
        rootTypes.add("QuestionnaireResponse");
        rootTypes.add("ResourceUse");
        rootTypes.add("Score");
        rootTypes.add("Section");
        rootTypes.add("ServiceEvent");
        rootTypes.add("ServiceUse");
        rootTypes.add("SubstanceKind");
        rootTypes.add("Team");
        setRootTypes(rootTypes);

        HashSet<String> ignoredAttributes = new HashSet<>();
        ignoredAttributes.add("Resource_description.parent_resource"); //this is a runtime attribute, not serialized!
        super.setIgnoredAttributes(ignoredAttributes);

        HashSet<String> ignoredClasses = new HashSet<>();
        ignoredClasses.add("Multiplicity_interval");
        ignoredClasses.add("Cardinality");
        super.setIgnoredClasses(ignoredClasses);
    }
}
