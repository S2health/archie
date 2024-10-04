package org.s2.rm.entity.entity;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.foundation_types.terminology.TerminologyTerm;
import org.s2.rm.base.model_support.identification.ObjectRef;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.Locatable;

/**
* BMM name: Entity_relationship_group
* BMM ancestors: Locatable
* isAbstract: true | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Entity_relationship_group", propOrder = {
  "uid",
  "domainType",
  "members"
})
public abstract class EntityRelationshipGroup extends Locatable {
  /**
  * BMM name: domain_type | BMM type: Terminology_term
  * isMandatory: true | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 1..1
  */
  @XmlElement(name = "domain_type")
  private TerminologyTerm domainType;

  /**
  * BMM name: members | BMM type: {@code List<Object_ref>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "members")
  private @Nullable List<ObjectRef> members;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public EntityRelationshipGroup() {}

  public EntityRelationshipGroup(TerminologyTerm domainType, String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
    this.domainType = domainType;
  }

  public TerminologyTerm getDomainType() {
    return domainType;
  }

  public void setDomainType(TerminologyTerm domainType) {
    this.domainType = domainType;
  }

  public @Nullable List<ObjectRef> getMembers() {
    return members;
  }

  public void setMembers(@Nullable List<ObjectRef> members) {
    this.members = members;
  }

  public @Nullable Uuid getUid() {
    return uid;
  }

  public void setUid(@Nullable Uuid uid) {
    this.uid = uid;
  }

  @Override
  public String bmmClassName() {
    return "Entity_relationship_group";
  }

  @Override
  public String toString() {
    return "Entity_relationship_group";
  }
}
