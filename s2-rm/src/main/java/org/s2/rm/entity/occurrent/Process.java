package org.s2.rm.entity.occurrent;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.foundation_types.terminology.TerminologyTerm;
import org.s2.rm.base.foundation_types.time.RmDateTime;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.data_structures.Node;
import org.s2.rm.base.patterns.participation.Participation;
import org.s2.rm.entity.entity.Entity;

/**
* BMM name: Process
* BMM ancestors: Entity
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Process", propOrder = {
  "uid",
  "startTime",
  "endTime",
  "participations"
})
public class Process extends Entity {
  /**
  * BMM name: start_time | BMM type: Date_time
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "start_time")
  private @Nullable RmDateTime startTime;

  /**
  * BMM name: end_time | BMM type: Date_time
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "end_time")
  private @Nullable RmDateTime endTime;

  /**
  * BMM name: participations | BMM type: {@code List<Participation>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "participations")
  private @Nullable List<Participation> participations;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public Process() {}

  public Process(TerminologyTerm domainType, String archetypeNodeId, String name) {
    super(domainType, archetypeNodeId, name);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    Process otherAsProcess = (Process) other;
    return Objects.equals(getDomainType(), otherAsProcess.getDomainType()) &&
      Objects.equals(getRelationships(), otherAsProcess.getRelationships()) &&
      Objects.equals(getOtherDetails(), otherAsProcess.getOtherDetails()) &&
      Objects.equals(uid, otherAsProcess.uid) &&
      Objects.equals(getArchetypeNodeId(), otherAsProcess.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsProcess.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsProcess.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsProcess.getFeederAudit()) &&
      Objects.equals(startTime, otherAsProcess.startTime) &&
      Objects.equals(endTime, otherAsProcess.endTime) &&
      Objects.equals(participations, otherAsProcess.participations);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(super.hashCode(), uid, startTime, endTime);
    result = participations == null ? 0 : 31 * result + participations.hashCode();
    return result;
  }

  public @Nullable RmDateTime getStartTime() {
    return startTime;
  }

  public void setStartTime(@Nullable RmDateTime startTime) {
    this.startTime = startTime;
  }

  public @Nullable RmDateTime getEndTime() {
    return endTime;
  }

  public void setEndTime(@Nullable RmDateTime endTime) {
    this.endTime = endTime;
  }

  public @Nullable List<Participation> getParticipations() {
    return participations;
  }

  public void setParticipations(@Nullable List<Participation> participations) {
    this.participations = participations;
  }

  public @Nullable Uuid getUid() {
    return uid;
  }

  public void setUid(@Nullable Uuid uid) {
    this.uid = uid;
  }

  @Override
  public String bmmClassName() {
    return "Process";
  }

  @Override
  public String toString() {
    return "Process";
  }
}
