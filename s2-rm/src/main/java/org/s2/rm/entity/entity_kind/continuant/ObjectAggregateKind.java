package org.s2.rm.entity.entity_kind.continuant;

import java.util.*;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.data_structures.Node;

/**
* BMM name: Object_aggregate_kind
* BMM ancestors: Material_entity_kind
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Object_aggregate_kind")
public class ObjectAggregateKind extends MaterialEntityKind {

  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  // This property is in at least one descendant where it probably has a different type.
  // Skip the property in the parent class (this one).
  // private @Nullable Uuid uid;

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
      Objects.equals(getUid(), otherAsObjectAggregateKind.getUid()) &&
      Objects.equals(getArchetypeNodeId(), otherAsObjectAggregateKind.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsObjectAggregateKind.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsObjectAggregateKind.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsObjectAggregateKind.getFeederAudit());
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode());
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
