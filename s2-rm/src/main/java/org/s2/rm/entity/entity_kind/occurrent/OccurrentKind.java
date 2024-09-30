package org.s2.rm.entity.entity_kind.occurrent;

import javax.xml.bind.annotation.*;
import org.s2.rm.entity.entity_kind.EntityKind;

/**
* BMM name: Occurrent_kind
* BMM ancestors: Entity_kind
* isAbstract: true | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Occurrent_kind")
public abstract class OccurrentKind extends EntityKind {
  public OccurrentKind() {}

  public OccurrentKind(String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
  }

  @Override
  public String bmmClassName() {
    return "Occurrent_kind";
  }

  @Override
  public String toString() {
    return "Occurrent_kind";
  }
}
