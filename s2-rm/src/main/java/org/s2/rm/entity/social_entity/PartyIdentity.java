package org.s2.rm.entity.social_entity;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.foundation_types.terminology.TerminologyTerm;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.archetyped.Locatable;
import org.s2.rm.base.patterns.data_structures.Node;

/**
* BMM name: Party_identity
* BMM ancestors: Locatable
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Party_identity", propOrder = {
  "uid",
  "simpleName",
  "identityType",
  "use",
  "description"
})
public class PartyIdentity extends Locatable {
  /**
  * BMM name: simple_name | BMM type: String
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "simple_name")
  private @Nullable String simpleName;

  /**
  * BMM name: identity_type | BMM type: Terminology_term
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "identity_type")
  private @Nullable TerminologyTerm identityType;

  /**
  * BMM name: use | BMM type: Terminology_term
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "use")
  private @Nullable TerminologyTerm use;

  /**
  * BMM name: description | BMM type: {@code List<Node>}
  * isMandatory: true | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 1..1
  */
  @XmlElement(name = "description")
  private List<Node> description;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public PartyIdentity() {}

  public PartyIdentity(List<Node> description, String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
    this.description = description;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    PartyIdentity otherAsPartyIdentity = (PartyIdentity) other;
    return Objects.equals(uid, otherAsPartyIdentity.uid) &&
      Objects.equals(getArchetypeNodeId(), otherAsPartyIdentity.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsPartyIdentity.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsPartyIdentity.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsPartyIdentity.getFeederAudit()) &&
      Objects.equals(simpleName, otherAsPartyIdentity.simpleName) &&
      Objects.equals(identityType, otherAsPartyIdentity.identityType) &&
      Objects.equals(use, otherAsPartyIdentity.use) &&
      Objects.equals(description, otherAsPartyIdentity.description);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(super.hashCode(), uid, simpleName, identityType, use);
    result = description == null ? 0 : 31 * result + description.hashCode();
    return result;
  }

  public @Nullable String getSimpleName() {
    return simpleName;
  }

  public void setSimpleName(@Nullable String simpleName) {
    this.simpleName = simpleName;
  }

  public @Nullable TerminologyTerm getIdentityType() {
    return identityType;
  }

  public void setIdentityType(@Nullable TerminologyTerm identityType) {
    this.identityType = identityType;
  }

  public @Nullable TerminologyTerm getUse() {
    return use;
  }

  public void setUse(@Nullable TerminologyTerm use) {
    this.use = use;
  }

  public List<Node> getDescription() {
    return description;
  }

  public void setDescription(List<Node> description) {
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
    return "Party_identity";
  }

  @Override
  public String toString() {
    return "Party_identity";
  }
}
