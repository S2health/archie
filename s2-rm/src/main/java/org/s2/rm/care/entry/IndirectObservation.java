package org.s2.rm.care.entry;

import java.util.*;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.foundation_types.terminology.TerminologyCode;
import org.s2.rm.base.foundation_types.time.RmDateTime;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.archetyped.Link;
import org.s2.rm.base.patterns.data_structures.EntityRefNode;
import org.s2.rm.base.patterns.data_structures.Node;
import org.s2.rm.base.patterns.participation.Participation;

/**
* BMM name: Indirect_observation
* BMM ancestors: Observation
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Indirect_observation", propOrder = {
  "resultTime"
})
public class IndirectObservation extends Observation {
  /**
  * BMM name: result_time | BMM type: Date_time
  * isMandatory: true | isComputed: false | isImRuntime: true | isImInfrastructure: false | existence: 1..1
  */
  @XmlElement(name = "result_time")
  private RmDateTime resultTime;

  public IndirectObservation() {}

  public IndirectObservation(RmDateTime resultTime, Uuid uid, RmDateTime time, TerminologyCode language, EntityRefNode subject, String archetypeNodeId, String name) {
    super(uid, time, language, subject, archetypeNodeId, name);
    this.resultTime = resultTime;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    IndirectObservation otherAsIndirectObservation = (IndirectObservation) other;
    return Objects.equals(getData(), otherAsIndirectObservation.getData()) &&
      Objects.equals(getState(), otherAsIndirectObservation.getState()) &&
      Objects.equals(getOrderTracking(), otherAsIndirectObservation.getOrderTracking()) &&
      Objects.equals(getQualifiers(), otherAsIndirectObservation.getQualifiers()) &&
      Objects.equals(getGuidelineIds(), otherAsIndirectObservation.getGuidelineIds()) &&
      Objects.equals(getUid(), otherAsIndirectObservation.getUid()) &&
      Objects.equals(getTime(), otherAsIndirectObservation.getTime()) &&
      Objects.equals(getLanguage(), otherAsIndirectObservation.getLanguage()) &&
      Objects.equals(getSubject(), otherAsIndirectObservation.getSubject()) &&
      Objects.equals(getReporter(), otherAsIndirectObservation.getReporter()) &&
      Objects.equals(getAuthorizationActions(), otherAsIndirectObservation.getAuthorizationActions()) &&
      Objects.equals(getOtherParticipations(), otherAsIndirectObservation.getOtherParticipations()) &&
      Objects.equals(getWorkflowId(), otherAsIndirectObservation.getWorkflowId()) &&
      Objects.equals(getComment(), otherAsIndirectObservation.getComment()) &&
      Objects.equals(getCode(), otherAsIndirectObservation.getCode()) &&
      Objects.equals(getOriginalCode(), otherAsIndirectObservation.getOriginalCode()) &&
      Objects.equals(getLinks(), otherAsIndirectObservation.getLinks()) &&
      Objects.equals(getArchetypeNodeId(), otherAsIndirectObservation.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsIndirectObservation.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsIndirectObservation.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsIndirectObservation.getFeederAudit()) &&
      Objects.equals(resultTime, otherAsIndirectObservation.resultTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), resultTime);
  }

  public RmDateTime getResultTime() {
    return resultTime;
  }

  public void setResultTime(RmDateTime resultTime) {
    this.resultTime = resultTime;
  }

  @Override
  public String bmmClassName() {
    return "Indirect_observation";
  }

  @Override
  public String toString() {
    return "Indirect_observation";
  }
}
