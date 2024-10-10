package org.s2.rm.base.patterns.domain_data_structures;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.foundation_types.terminology.TerminologyTerm;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.archetyped.Link;
import org.s2.rm.base.patterns.data_structures.Node;

/**
* BMM name: Comms_address_use
* BMM ancestors: Address_use
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Comms_address_use", propOrder = {
  "address",
  "addressPlatform"
})
public class CommsAddressUse extends AddressUse {
  /**
  * BMM name: address | BMM type: String
  * isMandatory: true | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 1..1
  */
  @XmlElement(name = "address")
  private String address;

  /**
  * BMM name: address_platform | BMM type: Terminology_term
  * isMandatory: false | isComputed: false | isImRuntime: true | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "address_platform")
  private @Nullable TerminologyTerm addressPlatform;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  // This property is in at least one descendant where it probably has a different type.
  // Skip the property in the parent class (this one).
  // private @Nullable Uuid uid;

  public CommsAddressUse() {}

  public CommsAddressUse(String address, String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
    this.address = address;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    CommsAddressUse otherAsCommsAddressUse = (CommsAddressUse) other;
    return Objects.equals(getPurposes(), otherAsCommsAddressUse.getPurposes()) &&
      Objects.equals(getTimeValidity(), otherAsCommsAddressUse.getTimeValidity()) &&
      Objects.equals(getOriginalValue(), otherAsCommsAddressUse.getOriginalValue()) &&
      Objects.equals(getValue(), otherAsCommsAddressUse.getValue()) &&
      Objects.equals(getNullFlavor(), otherAsCommsAddressUse.getNullFlavor()) &&
      Objects.equals(getNullReason(), otherAsCommsAddressUse.getNullReason()) &&
      Objects.equals(getItems(), otherAsCommsAddressUse.getItems()) &&
      Objects.equals(getCode(), otherAsCommsAddressUse.getCode()) &&
      Objects.equals(getOriginalCode(), otherAsCommsAddressUse.getOriginalCode()) &&
      Objects.equals(getLinks(), otherAsCommsAddressUse.getLinks()) &&
      Objects.equals(getUid(), otherAsCommsAddressUse.getUid()) &&
      Objects.equals(getArchetypeNodeId(), otherAsCommsAddressUse.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsCommsAddressUse.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsCommsAddressUse.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsCommsAddressUse.getFeederAudit()) &&
      Objects.equals(address, otherAsCommsAddressUse.address) &&
      Objects.equals(addressPlatform, otherAsCommsAddressUse.addressPlatform);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), address, addressPlatform);
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public @Nullable TerminologyTerm getAddressPlatform() {
    return addressPlatform;
  }

  public void setAddressPlatform(@Nullable TerminologyTerm addressPlatform) {
    this.addressPlatform = addressPlatform;
  }

  @Override
  public String bmmClassName() {
    return "Comms_address_use";
  }

  @Override
  public String toString() {
    return "Comms_address_use";
  }
}
