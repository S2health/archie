package org.s2.rm.entity.entity_kind.continuant;

import javax.xml.bind.annotation.*;

/**
* BMM name: Materially_dependent_entity_kind
* BMM ancestors: Physical_entity_kind
* isAbstract: true | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Materially_dependent_entity_kind")
public abstract class MateriallyDependentEntityKind extends PhysicalEntityKind {
  public MateriallyDependentEntityKind() {}

  public MateriallyDependentEntityKind(String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
  }

  @Override
  public String bmmClassName() {
    return "Materially_dependent_entity_kind";
  }

  @Override
  public String toString() {
    return "Materially_dependent_entity_kind";
  }
}
