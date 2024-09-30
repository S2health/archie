package org.s2.rm.base.patterns.data_structures;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.model_support.identification.ObjectRef;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.archetyped.Link;

/**
* BMM name: Entity_ref_node
* BMM ancestors: Node
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Entity_ref_node", propOrder = {
  "uid",
  "isSelf",
  "entityRef"
})
public class EntityRefNode extends Node {
  /**
  * BMM name: is_self | BMM type: Boolean
  * isMandatory: true | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 1..1
  */
  @XmlElement(name = "is_self")
  private boolean isSelf;

  /**
  * BMM name: entity_ref | BMM type: Object_ref
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "entity_ref")
  private @Nullable ObjectRef entityRef;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public EntityRefNode() {}

  public EntityRefNode(boolean isSelf, String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
    this.isSelf = isSelf;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    EntityRefNode otherAsEntityRefNode = (EntityRefNode) other;
    return Objects.equals(getValue(), otherAsEntityRefNode.getValue()) &&
      Objects.equals(getNullFlavor(), otherAsEntityRefNode.getNullFlavor()) &&
      Objects.equals(getNullReason(), otherAsEntityRefNode.getNullReason()) &&
      Objects.equals(getItems(), otherAsEntityRefNode.getItems()) &&
      Objects.equals(getCode(), otherAsEntityRefNode.getCode()) &&
      Objects.equals(getOriginalCode(), otherAsEntityRefNode.getOriginalCode()) &&
      Objects.equals(getLinks(), otherAsEntityRefNode.getLinks()) &&
      Objects.equals(uid, otherAsEntityRefNode.uid) &&
      Objects.equals(getArchetypeNodeId(), otherAsEntityRefNode.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsEntityRefNode.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsEntityRefNode.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsEntityRefNode.getFeederAudit()) &&
      Objects.equals(isSelf, otherAsEntityRefNode.isSelf) &&
      Objects.equals(entityRef, otherAsEntityRefNode.entityRef);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), uid, isSelf, entityRef);
  }

  public boolean getIsSelf() {
    return isSelf;
  }

  public void setIsSelf(boolean isSelf) {
    this.isSelf = isSelf;
  }

  public @Nullable ObjectRef getEntityRef() {
    return entityRef;
  }

  public void setEntityRef(@Nullable ObjectRef entityRef) {
    this.entityRef = entityRef;
  }

  public @Nullable Uuid getUid() {
    return uid;
  }

  public void setUid(@Nullable Uuid uid) {
    this.uid = uid;
  }

  @Override
  public String bmmClassName() {
    return "Entity_ref_node";
  }

  @Override
  public String toString() {
    return "Entity_ref_node";
  }
}
