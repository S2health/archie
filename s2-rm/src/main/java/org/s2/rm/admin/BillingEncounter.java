package org.s2.rm.admin;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.data_types.RweIdRef;
import org.s2.rm.base.foundation_types.terminology.TerminologyTerm;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.data_structures.Node;
import org.s2.rm.base.patterns.participation.Participation;
import org.s2.rm.entity.occurrent.CareProcess;
import org.s2.rm.entity.social_entity.IdUse;

/**
* BMM name: Billing_encounter
* BMM ancestors: Care_process
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Billing_encounter", propOrder = {
  "identifier",
  "externalIdentifiers",
  "interactionType",
  "lifecycleState",
  "serviceEvents"
})
public class BillingEncounter extends CareProcess {
  /**
  * BMM name: identifier | BMM type: Rwe_id_ref
  * isMandatory: true | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 1..1
  */
  @XmlElement(name = "identifier")
  private RweIdRef identifier;

  /**
  * BMM name: external_identifiers | BMM type: {@code List<Id_use>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "external_identifiers")
  private @Nullable List<IdUse> externalIdentifiers;

  /**
  * BMM name: interaction_type | BMM type: Terminology_term
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "interaction_type")
  private @Nullable TerminologyTerm interactionType;

  /**
  * BMM name: lifecycle_state | BMM type: Encounter_state
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "lifecycle_state")
  private @Nullable EncounterState lifecycleState;

  /**
  * BMM name: service_events | BMM type: {@code List<Service_event>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "service_events")
  private @Nullable List<ServiceEvent> serviceEvents;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  // This property is in at least one descendant where it probably has a different type.
  // Skip the property in the parent class (this one).
  // private @Nullable Uuid uid;

  public BillingEncounter() {}

  public BillingEncounter(RweIdRef identifier, TerminologyTerm domainType, String archetypeNodeId, String name) {
    super(domainType, archetypeNodeId, name);
    this.identifier = identifier;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    BillingEncounter otherAsBillingEncounter = (BillingEncounter) other;
    return Objects.equals(getSubject(), otherAsBillingEncounter.getSubject()) &&
      Objects.equals(getResponsibleOrganization(), otherAsBillingEncounter.getResponsibleOrganization()) &&
      Objects.equals(getStartTime(), otherAsBillingEncounter.getStartTime()) &&
      Objects.equals(getEndTime(), otherAsBillingEncounter.getEndTime()) &&
      Objects.equals(getParticipations(), otherAsBillingEncounter.getParticipations()) &&
      Objects.equals(getDomainType(), otherAsBillingEncounter.getDomainType()) &&
      Objects.equals(getRelationships(), otherAsBillingEncounter.getRelationships()) &&
      Objects.equals(getOtherDetails(), otherAsBillingEncounter.getOtherDetails()) &&
      Objects.equals(getUid(), otherAsBillingEncounter.getUid()) &&
      Objects.equals(getArchetypeNodeId(), otherAsBillingEncounter.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsBillingEncounter.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsBillingEncounter.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsBillingEncounter.getFeederAudit()) &&
      Objects.equals(identifier, otherAsBillingEncounter.identifier) &&
      Objects.equals(externalIdentifiers, otherAsBillingEncounter.externalIdentifiers) &&
      Objects.equals(interactionType, otherAsBillingEncounter.interactionType) &&
      Objects.equals(lifecycleState, otherAsBillingEncounter.lifecycleState) &&
      Objects.equals(serviceEvents, otherAsBillingEncounter.serviceEvents);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(super.hashCode(), identifier, interactionType, lifecycleState);
    result = externalIdentifiers == null ? 0 : 31 * result + externalIdentifiers.hashCode();
    result = serviceEvents == null ? 0 : 31 * result + serviceEvents.hashCode();
    return result;
  }

  public RweIdRef getIdentifier() {
    return identifier;
  }

  public void setIdentifier(RweIdRef identifier) {
    this.identifier = identifier;
  }

  public @Nullable List<IdUse> getExternalIdentifiers() {
    return externalIdentifiers;
  }

  public void setExternalIdentifiers(@Nullable List<IdUse> externalIdentifiers) {
    this.externalIdentifiers = externalIdentifiers;
  }

  public @Nullable TerminologyTerm getInteractionType() {
    return interactionType;
  }

  public void setInteractionType(@Nullable TerminologyTerm interactionType) {
    this.interactionType = interactionType;
  }

  public @Nullable EncounterState getLifecycleState() {
    return lifecycleState;
  }

  public void setLifecycleState(@Nullable EncounterState lifecycleState) {
    this.lifecycleState = lifecycleState;
  }

  public @Nullable List<ServiceEvent> getServiceEvents() {
    return serviceEvents;
  }

  public void setServiceEvents(@Nullable List<ServiceEvent> serviceEvents) {
    this.serviceEvents = serviceEvents;
  }

  @Override
  public String bmmClassName() {
    return "Billing_encounter";
  }

  @Override
  public String toString() {
    return "Billing_encounter";
  }
}
