package org.s2.rm.entity.entity_kind.continuant;

import javax.xml.bind.annotation.*;
import org.s2.rm.entity.entity_kind.EntityKind;

/**
* BMM name: Physical_entity_kind
* BMM ancestors: Entity_kind
* isAbstract: true | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Physical_entity_kind")
public abstract class PhysicalEntityKind extends EntityKind {
  public PhysicalEntityKind() {}

  public PhysicalEntityKind(String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
  }

  @Override
  public String bmmClassName() {
    return "Physical_entity_kind";
  }

  @Override
  public String toString() {
    return "Physical_entity_kind";
  }
}
