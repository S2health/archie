package org.s2.rm.entity.physical_entity;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.foundation_types.terminology.TerminologyTerm;
import org.s2.rm.base.model_support.identification.ObjectRef;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.data_structures.Node;
import org.s2.rm.entity.entity.Entity;
import org.s2.rm.entity.entity_kind.continuant.PhysicalEntityKind;

/**
* BMM name: Physical_entity
* BMM generic parameters: {@code Physical_entity<K Physical_entity_kind>}
* BMM ancestors: Entity
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Physical_entity", propOrder = {
  "uid",
  "kind",
  "kindRef"
})
public class PhysicalEntity<K extends PhysicalEntityKind> extends Entity {
  /**
  * BMM name: kind | BMM type: K
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "kind")
  private @Nullable K kind;

  /**
  * BMM name: kind_ref | BMM type: Object_ref
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "kind_ref")
  private @Nullable ObjectRef kindRef;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public PhysicalEntity() {}

  public PhysicalEntity(TerminologyTerm domainType, String archetypeNodeId, String name) {
    super(domainType, archetypeNodeId, name);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    PhysicalEntity<K> otherAsPhysicalEntity = (PhysicalEntity<K>) other;
    return Objects.equals(getDomainType(), otherAsPhysicalEntity.getDomainType()) &&
      Objects.equals(getRelationships(), otherAsPhysicalEntity.getRelationships()) &&
      Objects.equals(getOtherDetails(), otherAsPhysicalEntity.getOtherDetails()) &&
      Objects.equals(uid, otherAsPhysicalEntity.uid) &&
      Objects.equals(getArchetypeNodeId(), otherAsPhysicalEntity.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsPhysicalEntity.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsPhysicalEntity.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsPhysicalEntity.getFeederAudit()) &&
      Objects.equals(kind, otherAsPhysicalEntity.kind) &&
      Objects.equals(kindRef, otherAsPhysicalEntity.kindRef);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), uid, kind, kindRef);
  }

  public @Nullable K getKind() {
    return kind;
  }

  public void setKind(@Nullable K kind) {
    this.kind = kind;
  }

  public @Nullable ObjectRef getKindRef() {
    return kindRef;
  }

  public void setKindRef(@Nullable ObjectRef kindRef) {
    this.kindRef = kindRef;
  }

  public @Nullable Uuid getUid() {
    return uid;
  }

  public void setUid(@Nullable Uuid uid) {
    this.uid = uid;
  }

  @Override
  public String bmmClassName() {
    return "Physical_entity";
  }

  @Override
  public String toString() {
    return "Physical_entity";
  }
}
