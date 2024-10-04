package org.s2.rm.entity.entity_kind;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.Locatable;
import org.s2.rm.base.patterns.data_structures.Node;

/**
* BMM name: Entity_kind
* BMM ancestors: Locatable
* isAbstract: true | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Entity_kind", propOrder = {
  "uid",
  "items"
})
public abstract class EntityKind extends Locatable {
  /**
  * BMM name: items | BMM type: {@code List<Node>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "items")
  private @Nullable List<Node> items;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public EntityKind() {}

  public EntityKind(String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
  }

  public @Nullable List<Node> getItems() {
    return items;
  }

  public void setItems(@Nullable List<Node> items) {
    this.items = items;
  }

  public @Nullable Uuid getUid() {
    return uid;
  }

  public void setUid(@Nullable Uuid uid) {
    this.uid = uid;
  }

  @Override
  public String bmmClassName() {
    return "Entity_kind";
  }

  @Override
  public String toString() {
    return "Entity_kind";
  }
}
