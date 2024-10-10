package org.s2.rm.entity.social_entity;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.foundation_types.terminology.TerminologyTerm;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.domain_data_structures.CommsAddressUse;
import org.s2.rm.base.patterns.domain_data_structures.IdUse;
import org.s2.rm.base.patterns.domain_data_structures.LocationAddressUse;
import org.s2.rm.base.patterns.domain_data_structures.PartyIdentity;

/**
* BMM name: Party
* BMM ancestors: Social_entity
* isAbstract: true | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Party", propOrder = {
  "uid",
  "identifiers",
  "identities",
  "locationAddresses",
  "commsAddresses",
  "accountabilityTypes"
})
public abstract class Party extends SocialEntity {
  /**
  * BMM name: identifiers | BMM type: {@code List<Id_use>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "identifiers")
  private @Nullable List<IdUse> identifiers;

  /**
  * BMM name: identities | BMM type: {@code List<Party_identity>}
  * isMandatory: true | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 1..1
  */
  @XmlElement(name = "identities")
  private List<PartyIdentity> identities;

  /**
  * BMM name: location_addresses | BMM type: {@code List<Location_address_use>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "location_addresses")
  private @Nullable List<LocationAddressUse> locationAddresses;

  /**
  * BMM name: comms_addresses | BMM type: {@code List<Comms_address_use>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "comms_addresses")
  private @Nullable List<CommsAddressUse> commsAddresses;

  /**
  * BMM name: accountability_types | BMM type: {@code List<Accountability>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "accountability_types")
  private @Nullable List<Accountability> accountabilityTypes;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public Party() {}

  public Party(List<PartyIdentity> identities, TerminologyTerm domainType, String archetypeNodeId, String name) {
    super(domainType, archetypeNodeId, name);
    this.identities = identities;
  }

  public @Nullable List<IdUse> getIdentifiers() {
    return identifiers;
  }

  public void setIdentifiers(@Nullable List<IdUse> identifiers) {
    this.identifiers = identifiers;
  }

  public List<PartyIdentity> getIdentities() {
    return identities;
  }

  public void setIdentities(List<PartyIdentity> identities) {
    this.identities = identities;
  }

  public @Nullable List<LocationAddressUse> getLocationAddresses() {
    return locationAddresses;
  }

  public void setLocationAddresses(@Nullable List<LocationAddressUse> locationAddresses) {
    this.locationAddresses = locationAddresses;
  }

  public @Nullable List<CommsAddressUse> getCommsAddresses() {
    return commsAddresses;
  }

  public void setCommsAddresses(@Nullable List<CommsAddressUse> commsAddresses) {
    this.commsAddresses = commsAddresses;
  }

  public @Nullable List<Accountability> getAccountabilityTypes() {
    return accountabilityTypes;
  }

  public void setAccountabilityTypes(@Nullable List<Accountability> accountabilityTypes) {
    this.accountabilityTypes = accountabilityTypes;
  }

  public @Nullable Uuid getUid() {
    return uid;
  }

  public void setUid(@Nullable Uuid uid) {
    this.uid = uid;
  }

  @Override
  public String bmmClassName() {
    return "Party";
  }

  @Override
  public String toString() {
    return "Party";
  }
}
