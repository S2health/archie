package org.s2.rm.base.patterns.domain_data_structures;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.data_types.text.Text;
import org.s2.rm.base.foundation_types.interval.Interval;
import org.s2.rm.base.foundation_types.time.RmDate;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.archetyped.Link;
import org.s2.rm.base.patterns.data_structures.InfoNode;
import org.s2.rm.base.patterns.data_structures.Node;

/**
* BMM name: Capability
* BMM ancestors: Info_node
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Capability", propOrder = {
  "uid",
  "description",
  "timeValidity"
})
public class Capability extends InfoNode {
  /**
  * BMM name: description | BMM type: Text
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "description")
  private @Nullable Text description;

  /**
  * BMM name: time_validity | BMM type: {@code Interval<Date>}
  * isMandatory: false | isComputed: false | isImRuntime: true | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "time_validity")
  private @Nullable Interval<RmDate> timeValidity;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public Capability() {}

  public Capability(String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    Capability otherAsCapability = (Capability) other;
    return Objects.equals(getOriginalValue(), otherAsCapability.getOriginalValue()) &&
      Objects.equals(getValue(), otherAsCapability.getValue()) &&
      Objects.equals(getNullFlavor(), otherAsCapability.getNullFlavor()) &&
      Objects.equals(getNullReason(), otherAsCapability.getNullReason()) &&
      Objects.equals(getItems(), otherAsCapability.getItems()) &&
      Objects.equals(getCode(), otherAsCapability.getCode()) &&
      Objects.equals(getOriginalCode(), otherAsCapability.getOriginalCode()) &&
      Objects.equals(getLinks(), otherAsCapability.getLinks()) &&
      Objects.equals(uid, otherAsCapability.uid) &&
      Objects.equals(getArchetypeNodeId(), otherAsCapability.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsCapability.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsCapability.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsCapability.getFeederAudit()) &&
      Objects.equals(description, otherAsCapability.description) &&
      Objects.equals(timeValidity, otherAsCapability.timeValidity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), uid, description, timeValidity);
  }

  public @Nullable Text getDescription() {
    return description;
  }

  public void setDescription(@Nullable Text description) {
    this.description = description;
  }

  public @Nullable Interval<RmDate> getTimeValidity() {
    return timeValidity;
  }

  public void setTimeValidity(@Nullable Interval<RmDate> timeValidity) {
    this.timeValidity = timeValidity;
  }

  public @Nullable Uuid getUid() {
    return uid;
  }

  public void setUid(@Nullable Uuid uid) {
    this.uid = uid;
  }

  @Override
  public String bmmClassName() {
    return "Capability";
  }

  @Override
  public String toString() {
    return "Capability";
  }
}
