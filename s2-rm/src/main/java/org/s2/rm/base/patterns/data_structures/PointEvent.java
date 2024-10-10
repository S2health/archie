package org.s2.rm.base.patterns.data_structures;

import java.util.*;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.foundation_types.time.RmDateTime;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;

/**
* BMM name: Point_event
* BMM ancestors: Event
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Point_event")
public class PointEvent extends Event {

  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  // This property is in at least one descendant where it probably has a different type.
  // Skip the property in the parent class (this one).
  // private @Nullable Uuid uid;

  public PointEvent() {}

  public PointEvent(RmDateTime time, String archetypeNodeId, String name) {
    super(time, archetypeNodeId, name);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    PointEvent otherAsPointEvent = (PointEvent) other;
    return Objects.equals(getTime(), otherAsPointEvent.getTime()) &&
      Objects.equals(getItems(), otherAsPointEvent.getItems()) &&
      Objects.equals(getUid(), otherAsPointEvent.getUid()) &&
      Objects.equals(getArchetypeNodeId(), otherAsPointEvent.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsPointEvent.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsPointEvent.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsPointEvent.getFeederAudit());
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode());
  }

  @Override
  public String bmmClassName() {
    return "Point_event";
  }

  @Override
  public String toString() {
    return "Point_event";
  }
}
