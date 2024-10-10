package org.s2.rm.entity.entity_kind.continuant;

import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.model_support.identification.Uuid;

/**
* BMM name: Material_location
* BMM ancestors: Materially_dependent_entity_kind
* isAbstract: true | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Material_location", propOrder = {
  "uid"
})
public abstract class MaterialLocation extends MateriallyDependentEntityKind {

  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public MaterialLocation() {}

  public MaterialLocation(String archetypeNodeId, String name) {
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
    return "Material_location";
  }

  @Override
  public String toString() {
    return "Material_location";
  }
}
