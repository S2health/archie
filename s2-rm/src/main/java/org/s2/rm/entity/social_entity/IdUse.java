package org.s2.rm.entity.social_entity;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.data_types.RweIdRef;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.archetyped.Locatable;
import org.s2.rm.base.patterns.data_structures.Node;

/**
* BMM name: Id_use
* BMM ancestors: Locatable
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Id_use", propOrder = {
  "uid",
  "originalId",
  "description"
})
public class IdUse extends Locatable {
  /**
  * BMM name: original_id | BMM type: Rwe_id_ref
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "original_id")
  private @Nullable RweIdRef originalId;

  /**
  * BMM name: description | BMM type: {@code List<Node>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "description")
  private @Nullable List<Node> description;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public IdUse() {}

  public IdUse(String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    IdUse otherAsIdUse = (IdUse) other;
    return Objects.equals(uid, otherAsIdUse.uid) &&
      Objects.equals(getArchetypeNodeId(), otherAsIdUse.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsIdUse.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsIdUse.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsIdUse.getFeederAudit()) &&
      Objects.equals(originalId, otherAsIdUse.originalId) &&
      Objects.equals(description, otherAsIdUse.description);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(super.hashCode(), uid, originalId);
    result = description == null ? 0 : 31 * result + description.hashCode();
    return result;
  }

  public @Nullable RweIdRef getOriginalId() {
    return originalId;
  }

  public void setOriginalId(@Nullable RweIdRef originalId) {
    this.originalId = originalId;
  }

  public @Nullable List<Node> getDescription() {
    return description;
  }

  public void setDescription(@Nullable List<Node> description) {
    this.description = description;
  }

  public @Nullable Uuid getUid() {
    return uid;
  }

  public void setUid(@Nullable Uuid uid) {
    this.uid = uid;
  }

  @Override
  public String bmmClassName() {
    return "Id_use";
  }

  @Override
  public String toString() {
    return "Id_use";
  }
}
