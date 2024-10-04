package org.s2.rm.entity.entity;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.foundation_types.interval.Interval;
import org.s2.rm.base.foundation_types.terminology.TerminologyTerm;
import org.s2.rm.base.foundation_types.time.RmDate;
import org.s2.rm.base.model_support.identification.ObjectRef;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.Locatable;
import org.s2.rm.base.patterns.data_structures.Node;

/**
* BMM name: Entity_relationship
* BMM ancestors: Locatable
* isAbstract: true | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Entity_relationship", propOrder = {
  "uid",
  "domainType",
  "source",
  "target",
  "otherDetails",
  "timeValidity"
})
public abstract class EntityRelationship extends Locatable {
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

  /**
  * BMM name: other_details | BMM type: {@code List<Node>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "other_details")
  private @Nullable List<Node> otherDetails;

  /**
  * BMM name: time_validity | BMM type: {@code Interval<Date>}
  * isMandatory: false | isComputed: false | isImRuntime: true | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "time_validity")
  private @Nullable Interval<RmDate> timeValidity;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public EntityRelationship() {}

  public EntityRelationship(TerminologyTerm domainType, ObjectRef source, ObjectRef target, String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
    this.domainType = domainType;
    this.source = source;
    this.target = target;
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

  public @Nullable List<Node> getOtherDetails() {
    return otherDetails;
  }

  public void setOtherDetails(@Nullable List<Node> otherDetails) {
    this.otherDetails = otherDetails;
  }

  public @Nullable Interval<RmDate> getTimeValidity() {
    return timeValidity;
  }

  public void setTimeValidity(@Nullable Interval<RmDate> timeValidity) {
    this.timeValidity = timeValidity;
  }

  public @Nullable Uuid getUid() {
    return uid;
  }

  public void setUid(@Nullable Uuid uid) {
    this.uid = uid;
  }

  @Override
  public String bmmClassName() {
    return "Entity_relationship";
  }

  @Override
  public String toString() {
    return "Entity_relationship";
  }
}
