package org.s2.rm.entity.entity_kind.continuant;

import java.util.*;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.data_structures.Node;

/**
* BMM name: Fiat_point
* BMM ancestors: Material_location
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Fiat_point")
public class FiatPoint extends MaterialLocation {

  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  // This property is in at least one descendant where it probably has a different type.
  // Skip the property in the parent class (this one).
  // private @Nullable Uuid uid;

  public FiatPoint() {}

  public FiatPoint(String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    FiatPoint otherAsFiatPoint = (FiatPoint) other;
    return Objects.equals(getItems(), otherAsFiatPoint.getItems()) &&
      Objects.equals(getUid(), otherAsFiatPoint.getUid()) &&
      Objects.equals(getArchetypeNodeId(), otherAsFiatPoint.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsFiatPoint.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsFiatPoint.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsFiatPoint.getFeederAudit());
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode());
  }

  @Override
  public String bmmClassName() {
    return "Fiat_point";
  }

  @Override
  public String toString() {
    return "Fiat_point";
  }
}
