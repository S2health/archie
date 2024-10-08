package org.s2.rm.entity.entity_kind;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.foundation_types.terminology.TerminologyTerm;
import org.s2.rm.base.model_support.identification.ObjectRef;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.archetyped.Locatable;

/**
* BMM name: Entity_kind_relationship_group
* BMM ancestors: Locatable
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Entity_kind_relationship_group", propOrder = {
  "uid",
  "type",
  "members"
})
public class EntityKindRelationshipGroup extends Locatable {
  /**
  * BMM name: type | BMM type: Terminology_term
  * isMandatory: true | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 1..1
  */
  @XmlElement(name = "type")
  private TerminologyTerm type;

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

  public EntityKindRelationshipGroup() {}

  public EntityKindRelationshipGroup(TerminologyTerm type, String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
    this.type = type;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    EntityKindRelationshipGroup otherAsEntityKindRelationshipGroup = (EntityKindRelationshipGroup) other;
    return Objects.equals(uid, otherAsEntityKindRelationshipGroup.uid) &&
      Objects.equals(getArchetypeNodeId(), otherAsEntityKindRelationshipGroup.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsEntityKindRelationshipGroup.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsEntityKindRelationshipGroup.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsEntityKindRelationshipGroup.getFeederAudit()) &&
      Objects.equals(type, otherAsEntityKindRelationshipGroup.type) &&
      Objects.equals(members, otherAsEntityKindRelationshipGroup.members);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(super.hashCode(), uid, type);
    result = members == null ? 0 : 31 * result + members.hashCode();
    return result;
  }

  public TerminologyTerm getType() {
    return type;
  }

  public void setType(TerminologyTerm type) {
    this.type = type;
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
    return "Entity_kind_relationship_group";
  }

  @Override
  public String toString() {
    return "Entity_kind_relationship_group";
  }
}
