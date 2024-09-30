package org.s2.rm.entity.entity_kind.continuant;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.data_structures.Node;

/**
* BMM name: Substance_kind
* BMM ancestors: Independent_object_kind
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Substance_kind", propOrder = {
  "parts",
  "uid"
})
public class SubstanceKind extends IndependentObjectKind {

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

  public SubstanceKind() {}

  public SubstanceKind(String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    SubstanceKind otherAsSubstanceKind = (SubstanceKind) other;
    return Objects.equals(parts, otherAsSubstanceKind.parts) &&
      Objects.equals(getItems(), otherAsSubstanceKind.getItems()) &&
      Objects.equals(uid, otherAsSubstanceKind.uid) &&
      Objects.equals(getArchetypeNodeId(), otherAsSubstanceKind.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsSubstanceKind.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsSubstanceKind.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsSubstanceKind.getFeederAudit());
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
    return "Substance_kind";
  }

  @Override
  public String toString() {
    return "Substance_kind";
  }
}
