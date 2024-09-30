package org.s2.rm.care.entry;

import java.util.*;
import javax.annotation.Nullable;
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
* Entry subtype used to represent single sample observation.
* BMM name: Observation
* BMM ancestors: Care_act_entry
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Observation", propOrder = {
  "data",
  "state"
})
public class Observation extends CareActEntry {
  /**
  * BMM name: data | BMM type: {@code List<Node>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "data")
  private @Nullable List<Node> data;

  /**
  * BMM name: state | BMM type: {@code List<Node>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "state")
  private @Nullable List<Node> state;

  public Observation() {}

  public Observation(Uuid uid, RmDateTime time, TerminologyCode language, EntityRefNode subject, String archetypeNodeId, String name) {
    super(uid, time, language, subject, archetypeNodeId, name);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    Observation otherAsObservation = (Observation) other;
    return Objects.equals(getOrderTracking(), otherAsObservation.getOrderTracking()) &&
      Objects.equals(getQualifiers(), otherAsObservation.getQualifiers()) &&
      Objects.equals(getGuidelineIds(), otherAsObservation.getGuidelineIds()) &&
      Objects.equals(getUid(), otherAsObservation.getUid()) &&
      Objects.equals(getTime(), otherAsObservation.getTime()) &&
      Objects.equals(getLanguage(), otherAsObservation.getLanguage()) &&
      Objects.equals(getSubject(), otherAsObservation.getSubject()) &&
      Objects.equals(getReporter(), otherAsObservation.getReporter()) &&
      Objects.equals(getAuthorizationActions(), otherAsObservation.getAuthorizationActions()) &&
      Objects.equals(getOtherParticipations(), otherAsObservation.getOtherParticipations()) &&
      Objects.equals(getWorkflowId(), otherAsObservation.getWorkflowId()) &&
      Objects.equals(getComment(), otherAsObservation.getComment()) &&
      Objects.equals(getCode(), otherAsObservation.getCode()) &&
      Objects.equals(getOriginalCode(), otherAsObservation.getOriginalCode()) &&
      Objects.equals(getLinks(), otherAsObservation.getLinks()) &&
      Objects.equals(getArchetypeNodeId(), otherAsObservation.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsObservation.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsObservation.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsObservation.getFeederAudit()) &&
      Objects.equals(data, otherAsObservation.data) &&
      Objects.equals(state, otherAsObservation.state);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(super.hashCode());
    result = data == null ? 0 : 31 * result + data.hashCode();
    result = state == null ? 0 : 31 * result + state.hashCode();
    return result;
  }

  public @Nullable List<Node> getData() {
    return data;
  }

  public void setData(@Nullable List<Node> data) {
    this.data = data;
  }

  public @Nullable List<Node> getState() {
    return state;
  }

  public void setState(@Nullable List<Node> state) {
    this.state = state;
  }

  @Override
  public String bmmClassName() {
    return "Observation";
  }

  @Override
  public String toString() {
    return "Observation";
  }
}
