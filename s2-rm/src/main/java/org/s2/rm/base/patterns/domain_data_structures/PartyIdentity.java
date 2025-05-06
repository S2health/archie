package org.s2.rm.base.patterns.domain_data_structures;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.foundation_types.terminology.TerminologyTerm;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.archetyped.Link;
import org.s2.rm.base.patterns.data_structures.InfoNode;
import org.s2.rm.base.patterns.data_structures.Node;

/**
* BMM name: Party_identity
* BMM ancestors: Info_node
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Party_identity", propOrder = {
  "uid",
  "purpose",
  "simpleName"
})
public class PartyIdentity extends InfoNode {
  /**
  * BMM name: purpose | BMM type: Terminology_term
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "purpose")
  private @Nullable TerminologyTerm purpose;

  /**
  * BMM name: simple_name | BMM type: String
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "simple_name")
  private @Nullable String simpleName;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public PartyIdentity() {}

  public PartyIdentity(String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    PartyIdentity otherAsPartyIdentity = (PartyIdentity) other;
    return Objects.equals(getOriginalValue(), otherAsPartyIdentity.getOriginalValue()) &&
      Objects.equals(getValue(), otherAsPartyIdentity.getValue()) &&
      Objects.equals(getNullFlavor(), otherAsPartyIdentity.getNullFlavor()) &&
      Objects.equals(getNullReason(), otherAsPartyIdentity.getNullReason()) &&
      Objects.equals(getItems(), otherAsPartyIdentity.getItems()) &&
      Objects.equals(getCode(), otherAsPartyIdentity.getCode()) &&
      Objects.equals(getOriginalCode(), otherAsPartyIdentity.getOriginalCode()) &&
      Objects.equals(getLinks(), otherAsPartyIdentity.getLinks()) &&
      Objects.equals(uid, otherAsPartyIdentity.uid) &&
      Objects.equals(getArchetypeNodeId(), otherAsPartyIdentity.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsPartyIdentity.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsPartyIdentity.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsPartyIdentity.getFeederAudit()) &&
      Objects.equals(purpose, otherAsPartyIdentity.purpose) &&
      Objects.equals(simpleName, otherAsPartyIdentity.simpleName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), uid, purpose, simpleName);
  }

  public @Nullable TerminologyTerm getPurpose() {
    return purpose;
  }

  public void setPurpose(@Nullable TerminologyTerm purpose) {
    this.purpose = purpose;
  }

  public @Nullable String getSimpleName() {
    return simpleName;
  }

  public void setSimpleName(@Nullable String simpleName) {
    this.simpleName = simpleName;
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
