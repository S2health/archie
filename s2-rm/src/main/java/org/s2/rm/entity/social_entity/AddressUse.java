package org.s2.rm.entity.social_entity;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.data_types.timing.Timing;
import org.s2.rm.base.foundation_types.terminology.TerminologyTerm;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.archetyped.Locatable;

/**
* BMM name: Address_use
* BMM ancestors: Locatable
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Address_use", propOrder = {
  "uid",
  "addressType",
  "purposes",
  "timeValidity"
})
public class AddressUse extends Locatable {
  /**
  * BMM name: address_type | BMM type: Terminology_term
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "address_type")
  private @Nullable TerminologyTerm addressType;

  /**
  * BMM name: purposes | BMM type: {@code List<Terminology_term>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "purposes")
  private @Nullable List<TerminologyTerm> purposes;

  /**
  * BMM name: time_validity | BMM type: Timing
  * isMandatory: false | isComputed: false | isImRuntime: true | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "time_validity")
  private @Nullable Timing timeValidity;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public AddressUse() {}

  public AddressUse(String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    AddressUse otherAsAddressUse = (AddressUse) other;
    return Objects.equals(uid, otherAsAddressUse.uid) &&
      Objects.equals(getArchetypeNodeId(), otherAsAddressUse.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsAddressUse.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsAddressUse.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsAddressUse.getFeederAudit()) &&
      Objects.equals(addressType, otherAsAddressUse.addressType) &&
      Objects.equals(purposes, otherAsAddressUse.purposes) &&
      Objects.equals(timeValidity, otherAsAddressUse.timeValidity);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(super.hashCode(), uid, addressType, timeValidity);
    result = purposes == null ? 0 : 31 * result + purposes.hashCode();
    return result;
  }

  public @Nullable TerminologyTerm getAddressType() {
    return addressType;
  }

  public void setAddressType(@Nullable TerminologyTerm addressType) {
    this.addressType = addressType;
  }

  public @Nullable List<TerminologyTerm> getPurposes() {
    return purposes;
  }

  public void setPurposes(@Nullable List<TerminologyTerm> purposes) {
    this.purposes = purposes;
  }

  public @Nullable Timing getTimeValidity() {
    return timeValidity;
  }

  public void setTimeValidity(@Nullable Timing timeValidity) {
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
    return "Address_use";
  }

  @Override
  public String toString() {
    return "Address_use";
  }
}
