package org.s2.rm.entity.social_entity;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.data_structures.Node;

/**
* BMM name: Location_address_use
* BMM ancestors: Address_use
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Location_address_use", propOrder = {
  "uid",
  "description"
})
public class LocationAddressUse extends AddressUse {
  /**
  * BMM name: description | BMM type: {@code List<Node>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "description")
  private @Nullable List<Node> description;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public LocationAddressUse() {}

  public LocationAddressUse(String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    LocationAddressUse otherAsLocationAddressUse = (LocationAddressUse) other;
    return Objects.equals(getAddressType(), otherAsLocationAddressUse.getAddressType()) &&
      Objects.equals(getPurposes(), otherAsLocationAddressUse.getPurposes()) &&
      Objects.equals(getTimeValidity(), otherAsLocationAddressUse.getTimeValidity()) &&
      Objects.equals(uid, otherAsLocationAddressUse.uid) &&
      Objects.equals(getArchetypeNodeId(), otherAsLocationAddressUse.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsLocationAddressUse.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsLocationAddressUse.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsLocationAddressUse.getFeederAudit()) &&
      Objects.equals(description, otherAsLocationAddressUse.description);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(super.hashCode(), uid);
    result = description == null ? 0 : 31 * result + description.hashCode();
    return result;
  }

  public @Nullable List<Node> getDescription() {
    return description;
  }

  public void setDescription(@Nullable List<Node> description) {
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
    return "Location_address_use";
  }

  @Override
  public String toString() {
    return "Location_address_use";
  }
}
