package org.s2.rm.entity.entity;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.foundation_types.terminology.TerminologyTerm;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.Locatable;
import org.s2.rm.base.patterns.data_structures.Node;

/**
* BMM name: Entity
* BMM ancestors: Locatable
* isAbstract: true | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Entity", propOrder = {
  "uid",
  "domainType",
  "relationships",
  "otherDetails"
})
public abstract class Entity extends Locatable {
  /**
  * BMM name: domain_type | BMM type: Terminology_term
  * isMandatory: true | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 1..1
  */
  @XmlElement(name = "domain_type")
  private TerminologyTerm domainType;

  /**
  * BMM name: relationships | BMM type: {@code List<Entity_relationship_group>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "relationships")
  private @Nullable List<EntityRelationshipGroup> relationships;

  /**
  * BMM name: other_details | BMM type: {@code List<Node>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "other_details")
  private @Nullable List<Node> otherDetails;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public Entity() {}

  public Entity(TerminologyTerm domainType, String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
    this.domainType = domainType;
  }

  public TerminologyTerm getDomainType() {
    return domainType;
  }

  public void setDomainType(TerminologyTerm domainType) {
    this.domainType = domainType;
  }

  public @Nullable List<EntityRelationshipGroup> getRelationships() {
    return relationships;
  }

  public void setRelationships(@Nullable List<EntityRelationshipGroup> relationships) {
    this.relationships = relationships;
  }

  public @Nullable List<Node> getOtherDetails() {
    return otherDetails;
  }

  public void setOtherDetails(@Nullable List<Node> otherDetails) {
    this.otherDetails = otherDetails;
  }

  public @Nullable Uuid getUid() {
    return uid;
  }

  public void setUid(@Nullable Uuid uid) {
    this.uid = uid;
  }

  @Override
  public String bmmClassName() {
    return "Entity";
  }

  @Override
  public String toString() {
    return "Entity";
  }
}
