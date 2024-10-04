package org.s2.rm.entity.entity_kind.occurrent;

import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.entity.entity_kind.EntityKind;

/**
* BMM name: Occurrent_kind
* BMM ancestors: Entity_kind
* isAbstract: true | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Occurrent_kind", propOrder = {
  "uid"
})
public abstract class OccurrentKind extends EntityKind {

  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public OccurrentKind() {}

  public OccurrentKind(String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
  }

  public @Nullable Uuid getUid() {
    return uid;
  }

  public void setUid(@Nullable Uuid uid) {
    this.uid = uid;
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
