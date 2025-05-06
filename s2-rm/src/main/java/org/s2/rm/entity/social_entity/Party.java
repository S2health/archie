package org.s2.rm.entity.social_entity;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.data_structures.Node;

/**
* BMM name: Party
* BMM ancestors: Social_entity
* isAbstract: true | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.7
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
  * BMM name: identifiers | BMM type: {@code List<Node>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "identifiers")
  private @Nullable List<Node> identifiers;

  /**
  * BMM name: identities | BMM type: {@code List<Node>}
  * isMandatory: true | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 1..1
  */
  @XmlElement(name = "identities")
  private List<Node> identities;

  /**
  * BMM name: location_addresses | BMM type: {@code List<Node>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "location_addresses")
  private @Nullable List<Node> locationAddresses;

  /**
  * BMM name: comms_addresses | BMM type: {@code List<Node>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "comms_addresses")
  private @Nullable List<Node> commsAddresses;

  /**
  * BMM name: accountability_types | BMM type: {@code List<Node>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "accountability_types")
  private @Nullable List<Node> accountabilityTypes;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public Party() {}

  public Party(List<Node> identities, String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
    this.identities = identities;
  }

  public @Nullable List<Node> getIdentifiers() {
    return identifiers;
  }

  public void setIdentifiers(@Nullable List<Node> identifiers) {
    this.identifiers = identifiers;
  }

  public List<Node> getIdentities() {
    return identities;
  }

  public void setIdentities(List<Node> identities) {
    this.identities = identities;
  }

  public @Nullable List<Node> getLocationAddresses() {
    return locationAddresses;
  }

  public void setLocationAddresses(@Nullable List<Node> locationAddresses) {
    this.locationAddresses = locationAddresses;
  }

  public @Nullable List<Node> getCommsAddresses() {
    return commsAddresses;
  }

  public void setCommsAddresses(@Nullable List<Node> commsAddresses) {
    this.commsAddresses = commsAddresses;
  }

  public @Nullable List<Node> getAccountabilityTypes() {
    return accountabilityTypes;
  }

  public void setAccountabilityTypes(@Nullable List<Node> accountabilityTypes) {
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
