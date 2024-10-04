package org.s2.rm.entity.entity_kind.continuant;

import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.model_support.identification.Uuid;

/**
* BMM name: Material_entity_kind
* BMM ancestors: Materially_dependent_entity_kind
* isAbstract: true | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Material_entity_kind", propOrder = {
  "uid"
})
public abstract class MaterialEntityKind extends MateriallyDependentEntityKind {

  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public MaterialEntityKind() {}

  public MaterialEntityKind(String archetypeNodeId, String name) {
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
    return "Material_entity_kind";
  }

  @Override
  public String toString() {
    return "Material_entity_kind";
  }
}
