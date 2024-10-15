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
* BMM name: Entity_kind_relationship
* BMM ancestors: Locatable
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Entity_kind_relationship", propOrder = {
  "uid",
  "domainType",
  "source",
  "target"
})
public class EntityKindRelationship extends Locatable {
  /**
  * BMM name: domain_type | BMM type: Terminology_term
  * isMandatory: true | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 1..1
  */
  @XmlElement(name = "domain_type")
  private TerminologyTerm domainType;

  /**
  * BMM name: source | BMM type: Object_ref
  * isMandatory: true | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 1..1
  */
  @XmlElement(name = "source")
  private ObjectRef source;

  /**
  * BMM name: target | BMM type: Object_ref
  * isMandatory: true | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 1..1
  */
  @XmlElement(name = "target")
  private ObjectRef target;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public EntityKindRelationship() {}

  public EntityKindRelationship(TerminologyTerm domainType, ObjectRef source, ObjectRef target, String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
    this.domainType = domainType;
    this.source = source;
    this.target = target;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    EntityKindRelationship otherAsEntityKindRelationship = (EntityKindRelationship) other;
    return Objects.equals(uid, otherAsEntityKindRelationship.uid) &&
      Objects.equals(getArchetypeNodeId(), otherAsEntityKindRelationship.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsEntityKindRelationship.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsEntityKindRelationship.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsEntityKindRelationship.getFeederAudit()) &&
      Objects.equals(domainType, otherAsEntityKindRelationship.domainType) &&
      Objects.equals(source, otherAsEntityKindRelationship.source) &&
      Objects.equals(target, otherAsEntityKindRelationship.target);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), uid, domainType, source, target);
  }

  public TerminologyTerm getDomainType() {
    return domainType;
  }

  public void setDomainType(TerminologyTerm domainType) {
    this.domainType = domainType;
  }

  public ObjectRef getSource() {
    return source;
  }

  public void setSource(ObjectRef source) {
    this.source = source;
  }

  public ObjectRef getTarget() {
    return target;
  }

  public void setTarget(ObjectRef target) {
    this.target = target;
  }

  public @Nullable Uuid getUid() {
    return uid;
  }

  public void setUid(@Nullable Uuid uid) {
    this.uid = uid;
  }

  @Override
  public String bmmClassName() {
    return "Entity_kind_relationship";
  }

  @Override
  public String toString() {
    return "Entity_kind_relationship";
  }
}
