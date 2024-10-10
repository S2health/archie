package org.s2.rm.entity.occurrent;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.foundation_types.terminology.TerminologyTerm;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.data_structures.EntityRefNode;
import org.s2.rm.base.patterns.data_structures.Node;
import org.s2.rm.base.patterns.data_structures.Participation;

/**
* BMM name: Care_process
* BMM ancestors: Process
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Care_process", propOrder = {
  "uid",
  "subject",
  "responsibleOrganization"
})
public class CareProcess extends Process {
  /**
  * BMM name: subject | BMM type: Entity_ref_node
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "subject")
  private @Nullable EntityRefNode subject;

  /**
  * BMM name: responsible_organization | BMM type: Entity_ref_node
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "responsible_organization")
  private @Nullable EntityRefNode responsibleOrganization;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public CareProcess() {}

  public CareProcess(TerminologyTerm domainType, String archetypeNodeId, String name) {
    super(domainType, archetypeNodeId, name);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    CareProcess otherAsCareProcess = (CareProcess) other;
    return Objects.equals(getStartTime(), otherAsCareProcess.getStartTime()) &&
      Objects.equals(getEndTime(), otherAsCareProcess.getEndTime()) &&
      Objects.equals(getParticipations(), otherAsCareProcess.getParticipations()) &&
      Objects.equals(getDomainType(), otherAsCareProcess.getDomainType()) &&
      Objects.equals(getRelationships(), otherAsCareProcess.getRelationships()) &&
      Objects.equals(getOtherDetails(), otherAsCareProcess.getOtherDetails()) &&
      Objects.equals(uid, otherAsCareProcess.uid) &&
      Objects.equals(getArchetypeNodeId(), otherAsCareProcess.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsCareProcess.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsCareProcess.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsCareProcess.getFeederAudit()) &&
      Objects.equals(subject, otherAsCareProcess.subject) &&
      Objects.equals(responsibleOrganization, otherAsCareProcess.responsibleOrganization);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), uid, subject, responsibleOrganization);
  }

  public @Nullable EntityRefNode getSubject() {
    return subject;
  }

  public void setSubject(@Nullable EntityRefNode subject) {
    this.subject = subject;
  }

  public @Nullable EntityRefNode getResponsibleOrganization() {
    return responsibleOrganization;
  }

  public void setResponsibleOrganization(@Nullable EntityRefNode responsibleOrganization) {
    this.responsibleOrganization = responsibleOrganization;
  }

  public @Nullable Uuid getUid() {
    return uid;
  }

  public void setUid(@Nullable Uuid uid) {
    this.uid = uid;
  }

  @Override
  public String bmmClassName() {
    return "Care_process";
  }

  @Override
  public String toString() {
    return "Care_process";
  }
}
