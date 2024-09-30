package org.s2.rm.admin;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.foundation_types.terminology.TerminologyTerm;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.data_structures.EntityRefNode;
import org.s2.rm.base.patterns.data_structures.Node;
import org.s2.rm.base.patterns.participation.Participation;
import org.s2.rm.entity.occurrent.CareProcess;

/**
* BMM name: Service_event
* BMM ancestors: Care_process
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Service_event", propOrder = {
  "uid",
  "lifecycleState",
  "facility"
})
public class ServiceEvent extends CareProcess {
  /**
  * BMM name: lifecycle_state | BMM type: Service_event_state
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "lifecycle_state")
  private @Nullable ServiceEventState lifecycleState;

  /**
  * BMM name: facility | BMM type: Entity_ref_node
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "facility")
  private @Nullable EntityRefNode facility;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public ServiceEvent() {}

  public ServiceEvent(TerminologyTerm domainType, String archetypeNodeId, String name) {
    super(domainType, archetypeNodeId, name);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    ServiceEvent otherAsServiceEvent = (ServiceEvent) other;
    return Objects.equals(getSubject(), otherAsServiceEvent.getSubject()) &&
      Objects.equals(getResponsibleOrganization(), otherAsServiceEvent.getResponsibleOrganization()) &&
      Objects.equals(getStartTime(), otherAsServiceEvent.getStartTime()) &&
      Objects.equals(getEndTime(), otherAsServiceEvent.getEndTime()) &&
      Objects.equals(getParticipations(), otherAsServiceEvent.getParticipations()) &&
      Objects.equals(getDomainType(), otherAsServiceEvent.getDomainType()) &&
      Objects.equals(getRelationships(), otherAsServiceEvent.getRelationships()) &&
      Objects.equals(getOtherDetails(), otherAsServiceEvent.getOtherDetails()) &&
      Objects.equals(uid, otherAsServiceEvent.uid) &&
      Objects.equals(getArchetypeNodeId(), otherAsServiceEvent.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsServiceEvent.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsServiceEvent.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsServiceEvent.getFeederAudit()) &&
      Objects.equals(lifecycleState, otherAsServiceEvent.lifecycleState) &&
      Objects.equals(facility, otherAsServiceEvent.facility);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), uid, lifecycleState, facility);
  }

  public @Nullable ServiceEventState getLifecycleState() {
    return lifecycleState;
  }

  public void setLifecycleState(@Nullable ServiceEventState lifecycleState) {
    this.lifecycleState = lifecycleState;
  }

  public @Nullable EntityRefNode getFacility() {
    return facility;
  }

  public void setFacility(@Nullable EntityRefNode facility) {
    this.facility = facility;
  }

  public @Nullable Uuid getUid() {
    return uid;
  }

  public void setUid(@Nullable Uuid uid) {
    this.uid = uid;
  }

  @Override
  public String bmmClassName() {
    return "Service_event";
  }

  @Override
  public String toString() {
    return "Service_event";
  }
}
