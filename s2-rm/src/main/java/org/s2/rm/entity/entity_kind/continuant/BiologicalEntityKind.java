package org.s2.rm.entity.entity_kind.continuant;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.data_structures.Node;

/**
* BMM name: Biological_entity_kind
* BMM ancestors: Independent_object_kind
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Biological_entity_kind", propOrder = {
  "parts",
  "uid"
})
public class BiologicalEntityKind extends IndependentObjectKind {

  // Properties added from the extended class: ObjectExtensionKind

  /**
  * BMM name: parts | BMM type: {@code List<Object_extension_kind>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "parts")
  private @Nullable List<ObjectExtensionKind> parts;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public BiologicalEntityKind() {}

  public BiologicalEntityKind(String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    BiologicalEntityKind otherAsBiologicalEntityKind = (BiologicalEntityKind) other;
    return Objects.equals(parts, otherAsBiologicalEntityKind.parts) &&
      Objects.equals(getItems(), otherAsBiologicalEntityKind.getItems()) &&
      Objects.equals(uid, otherAsBiologicalEntityKind.uid) &&
      Objects.equals(getArchetypeNodeId(), otherAsBiologicalEntityKind.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsBiologicalEntityKind.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsBiologicalEntityKind.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsBiologicalEntityKind.getFeederAudit());
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(super.hashCode(), uid);
    result = parts == null ? 0 : 31 * result + parts.hashCode();
    return result;
  }

  public @Nullable List<ObjectExtensionKind> getParts() {
    return parts;
  }

  public void setParts(@Nullable List<ObjectExtensionKind> parts) {
    this.parts = parts;
  }

  public @Nullable Uuid getUid() {
    return uid;
  }

  public void setUid(@Nullable Uuid uid) {
    this.uid = uid;
  }

  @Override
  public String bmmClassName() {
    return "Biological_entity_kind";
  }

  @Override
  public String toString() {
    return "Biological_entity_kind";
  }
}
