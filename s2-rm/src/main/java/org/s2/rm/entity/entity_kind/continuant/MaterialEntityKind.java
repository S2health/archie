package org.s2.rm.entity.entity_kind.continuant;

import javax.xml.bind.annotation.*;

/**
* BMM name: Material_entity_kind
* BMM ancestors: Materially_dependent_entity_kind
* isAbstract: true | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Material_entity_kind")
public abstract class MaterialEntityKind extends MateriallyDependentEntityKind {
  public MaterialEntityKind() {}

  public MaterialEntityKind(String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
  }

  @Override
  public String bmmClassName() {
    return "Material_entity_kind";
  }

  @Override
  public String toString() {
    return "Material_entity_kind";
  }
}
