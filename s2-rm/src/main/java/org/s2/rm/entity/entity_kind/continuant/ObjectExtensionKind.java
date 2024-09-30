package org.s2.rm.entity.entity_kind.continuant;

import javax.xml.bind.annotation.*;

/**
* BMM name: Object_extension_kind
* BMM ancestors: Material_entity_kind
* isAbstract: true | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Object_extension_kind")
public abstract class ObjectExtensionKind extends MaterialEntityKind {
  /**
  * BMM name: parts | BMM type: {@code List<Object_extension_kind>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  // This property is in at least one descendant where it probably has a different type.
  // Skip the property in the parent class (this one).
  // private @Nullable List<ObjectExtensionKind> parts;

  public ObjectExtensionKind() {}

  public ObjectExtensionKind(String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
  }

  @Override
  public String bmmClassName() {
    return "Object_extension_kind";
  }

  @Override
  public String toString() {
    return "Object_extension_kind";
  }
}
