package org.s2.rm.base.patterns.participation;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.foundation_types.interval.Interval;
import org.s2.rm.base.foundation_types.terminology.TerminologyTerm;
import org.s2.rm.base.foundation_types.time.RmDateTime;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.archetyped.Locatable;
import org.s2.rm.base.patterns.data_structures.EntityRefNode;

/**
* BMM name: Participation
* BMM ancestors: Locatable
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Participation", propOrder = {
  "uid",
  "function",
  "time",
  "mode",
  "performer"
})
public class Participation extends Locatable {
  /**
  * BMM name: function | BMM type: Terminology_term
  * isMandatory: true | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 1..1
  * valueConstraint: s2.ParticipationFunction
  */
  @XmlElement(name = "function")
  private TerminologyTerm function;

  /**
  * BMM name: time | BMM type: {@code Interval<Date_time>}
  * isMandatory: false | isComputed: false | isImRuntime: true | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "time")
  private @Nullable Interval<RmDateTime> time;

  /**
  * BMM name: mode | BMM type: Terminology_term
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  * valueConstraint: s2.ParticipationMode
  */
  @XmlElement(name = "mode")
  private @Nullable TerminologyTerm mode;

  /**
  * BMM name: performer | BMM type: Entity_ref_node
  * isMandatory: true | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 1..1
  */
  @XmlElement(name = "performer")
  private EntityRefNode performer;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public Participation() {}

  public Participation(TerminologyTerm function, EntityRefNode performer, String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
    this.function = function;
    this.performer = performer;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    Participation otherAsParticipation = (Participation) other;
    return Objects.equals(uid, otherAsParticipation.uid) &&
      Objects.equals(getArchetypeNodeId(), otherAsParticipation.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsParticipation.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsParticipation.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsParticipation.getFeederAudit()) &&
      Objects.equals(function, otherAsParticipation.function) &&
      Objects.equals(time, otherAsParticipation.time) &&
      Objects.equals(mode, otherAsParticipation.mode) &&
      Objects.equals(performer, otherAsParticipation.performer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), uid, function, time, mode, performer);
  }

  public TerminologyTerm getFunction() {
    return function;
  }

  public void setFunction(TerminologyTerm function) {
    this.function = function;
  }

  public @Nullable Interval<RmDateTime> getTime() {
    return time;
  }

  public void setTime(@Nullable Interval<RmDateTime> time) {
    this.time = time;
  }

  public @Nullable TerminologyTerm getMode() {
    return mode;
  }

  public void setMode(@Nullable TerminologyTerm mode) {
    this.mode = mode;
  }

  public EntityRefNode getPerformer() {
    return performer;
  }

  public void setPerformer(EntityRefNode performer) {
    this.performer = performer;
  }

  public @Nullable Uuid getUid() {
    return uid;
  }

  public void setUid(@Nullable Uuid uid) {
    this.uid = uid;
  }

  @Override
  public String bmmClassName() {
    return "Participation";
  }

  @Override
  public String toString() {
    return "Participation";
  }
}
