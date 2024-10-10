package org.s2.rm.entity.entity_kind.continuant;

import java.util.*;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.data_structures.Node;

/**
* BMM name: Artifact_kind
* BMM ancestors: Independent_object_kind
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Artifact_kind")
public class ArtifactKind extends IndependentObjectKind {

  // Properties added from the extended class: ObjectExtensionKind

  /**
  * BMM name: parts | BMM type: {@code List<Object_extension_kind>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  // This property is in at least one descendant where it probably has a different type.
  // Skip the property in the parent class (this one).
  // private @Nullable List<ObjectExtensionKind> parts;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  // This property is in at least one descendant where it probably has a different type.
  // Skip the property in the parent class (this one).
  // private @Nullable Uuid uid;

  public ArtifactKind() {}

  public ArtifactKind(String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    ArtifactKind otherAsArtifactKind = (ArtifactKind) other;
    return Objects.equals(getParts(), otherAsArtifactKind.getParts()) &&
      Objects.equals(getItems(), otherAsArtifactKind.getItems()) &&
      Objects.equals(getUid(), otherAsArtifactKind.getUid()) &&
      Objects.equals(getArchetypeNodeId(), otherAsArtifactKind.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsArtifactKind.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsArtifactKind.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsArtifactKind.getFeederAudit());
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode());
  }

  @Override
  public String bmmClassName() {
    return "Artifact_kind";
  }

  @Override
  public String toString() {
    return "Artifact_kind";
  }
}
