package org.s2.rm.entity.physical_entity;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.model_support.archetyped.FeederAudit;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.data_structures.Node;

/**
* BMM name: Fiat_point
* BMM ancestors: Material_location
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.0
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Fiat_point", propOrder = {
  "uid"
})
public class FiatPoint extends MaterialLocation {

  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public FiatPoint() {}

  public FiatPoint(String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    FiatPoint otherAsFiatPoint = (FiatPoint) other;
    return Objects.equals(getDescription(), otherAsFiatPoint.getDescription()) &&
      Objects.equals(uid, otherAsFiatPoint.uid) &&
      Objects.equals(getArchetypeNodeId(), otherAsFiatPoint.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsFiatPoint.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsFiatPoint.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsFiatPoint.getFeederAudit());
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), uid);
  }

  public @Nullable Uuid getUid() {
    return uid;
  }

  public void setUid(@Nullable Uuid uid) {
    this.uid = uid;
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
