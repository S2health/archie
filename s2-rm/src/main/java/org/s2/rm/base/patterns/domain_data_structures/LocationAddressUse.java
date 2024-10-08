package org.s2.rm.base.patterns.domain_data_structures;

import java.util.*;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.archetyped.Link;
import org.s2.rm.base.patterns.data_structures.Node;

/**
* BMM name: Location_address_use
* BMM ancestors: Address_use
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Location_address_use")
public class LocationAddressUse extends AddressUse {

  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  // This property is in at least one descendant where it probably has a different type.
  // Skip the property in the parent class (this one).
  // private @Nullable Uuid uid;

  public LocationAddressUse() {}

  public LocationAddressUse(String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    LocationAddressUse otherAsLocationAddressUse = (LocationAddressUse) other;
    return Objects.equals(getPurposes(), otherAsLocationAddressUse.getPurposes()) &&
      Objects.equals(getTimeValidity(), otherAsLocationAddressUse.getTimeValidity()) &&
      Objects.equals(getOriginalValue(), otherAsLocationAddressUse.getOriginalValue()) &&
      Objects.equals(getValue(), otherAsLocationAddressUse.getValue()) &&
      Objects.equals(getNullFlavor(), otherAsLocationAddressUse.getNullFlavor()) &&
      Objects.equals(getNullReason(), otherAsLocationAddressUse.getNullReason()) &&
      Objects.equals(getItems(), otherAsLocationAddressUse.getItems()) &&
      Objects.equals(getCode(), otherAsLocationAddressUse.getCode()) &&
      Objects.equals(getOriginalCode(), otherAsLocationAddressUse.getOriginalCode()) &&
      Objects.equals(getLinks(), otherAsLocationAddressUse.getLinks()) &&
      Objects.equals(getUid(), otherAsLocationAddressUse.getUid()) &&
      Objects.equals(getArchetypeNodeId(), otherAsLocationAddressUse.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsLocationAddressUse.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsLocationAddressUse.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsLocationAddressUse.getFeederAudit());
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode());
  }

  @Override
  public String bmmClassName() {
    return "Location_address_use";
  }

  @Override
  public String toString() {
    return "Location_address_use";
  }
}
