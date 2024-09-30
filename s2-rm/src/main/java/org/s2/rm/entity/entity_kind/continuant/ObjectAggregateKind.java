package org.s2.rm.entity.entity_kind.continuant;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.data_structures.Node;

/**
* BMM name: Object_aggregate_kind
* BMM ancestors: Material_entity_kind
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Object_aggregate_kind", propOrder = {
  "uid"
})
public class ObjectAggregateKind extends MaterialEntityKind {

  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public ObjectAggregateKind() {}

  public ObjectAggregateKind(String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    ObjectAggregateKind otherAsObjectAggregateKind = (ObjectAggregateKind) other;
    return Objects.equals(getItems(), otherAsObjectAggregateKind.getItems()) &&
      Objects.equals(uid, otherAsObjectAggregateKind.uid) &&
      Objects.equals(getArchetypeNodeId(), otherAsObjectAggregateKind.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsObjectAggregateKind.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsObjectAggregateKind.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsObjectAggregateKind.getFeederAudit());
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
    return "Object_aggregate_kind";
  }

  @Override
  public String toString() {
    return "Object_aggregate_kind";
  }
}
