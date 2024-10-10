package org.s2.rm.entity.social_entity;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.foundation_types.terminology.TerminologyTerm;
import org.s2.rm.base.model_support.identification.ObjectRef;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.data_structures.Node;
import org.s2.rm.entity.entity.EntityRelationship;

/**
* BMM name: Party_relationship
* BMM ancestors: Entity_relationship
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Party_relationship", propOrder = {
  "scoper"
})
public class PartyRelationship extends EntityRelationship {
  /**
  * BMM name: scoper | BMM type: String
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "scoper")
  private @Nullable String scoper;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  // This property is in at least one descendant where it probably has a different type.
  // Skip the property in the parent class (this one).
  // private @Nullable Uuid uid;

  public PartyRelationship() {}

  public PartyRelationship(TerminologyTerm domainType, ObjectRef source, ObjectRef target, String archetypeNodeId, String name) {
    super(domainType, source, target, archetypeNodeId, name);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    PartyRelationship otherAsPartyRelationship = (PartyRelationship) other;
    return Objects.equals(getDomainType(), otherAsPartyRelationship.getDomainType()) &&
      Objects.equals(getSource(), otherAsPartyRelationship.getSource()) &&
      Objects.equals(getTarget(), otherAsPartyRelationship.getTarget()) &&
      Objects.equals(getOtherDetails(), otherAsPartyRelationship.getOtherDetails()) &&
      Objects.equals(getTimeValidity(), otherAsPartyRelationship.getTimeValidity()) &&
      Objects.equals(getUid(), otherAsPartyRelationship.getUid()) &&
      Objects.equals(getArchetypeNodeId(), otherAsPartyRelationship.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsPartyRelationship.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsPartyRelationship.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsPartyRelationship.getFeederAudit()) &&
      Objects.equals(scoper, otherAsPartyRelationship.scoper);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), scoper);
  }

  public @Nullable String getScoper() {
    return scoper;
  }

  public void setScoper(@Nullable String scoper) {
    this.scoper = scoper;
  }

  @Override
  public String bmmClassName() {
    return "Party_relationship";
  }

  @Override
  public String toString() {
    return "Party_relationship";
  }
}
