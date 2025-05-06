package org.s2.rm.entity.social_entity;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.model_support.identification.ObjectRef;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.archetyped.Link;
import org.s2.rm.base.patterns.data_structures.Node;

/**
* BMM name: Persona
* BMM ancestors: Party
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.7
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Persona", propOrder = {
  "performer",
  "capabilities"
})
public class Persona extends Party {
  /**
  * BMM name: performer | BMM type: Object_ref
  * isMandatory: true | isComputed: false | isImRuntime: true | isImInfrastructure: false | existence: 1..1
  */
  @XmlElement(name = "performer")
  private ObjectRef performer;

  /**
  * BMM name: capabilities | BMM type: {@code List<Node>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "capabilities")
  private @Nullable List<Node> capabilities;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  // This property is in at least one descendant where it probably has a different type.
  // Skip the property in the parent class (this one).
  // private @Nullable Uuid uid;

  public Persona() {}

  public Persona(ObjectRef performer, List<Node> identities, String archetypeNodeId, String name) {
    super(identities, archetypeNodeId, name);
    this.performer = performer;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    Persona otherAsPersona = (Persona) other;
    return Objects.equals(getIdentifiers(), otherAsPersona.getIdentifiers()) &&
      Objects.equals(getIdentities(), otherAsPersona.getIdentities()) &&
      Objects.equals(getLocationAddresses(), otherAsPersona.getLocationAddresses()) &&
      Objects.equals(getCommsAddresses(), otherAsPersona.getCommsAddresses()) &&
      Objects.equals(getAccountabilityTypes(), otherAsPersona.getAccountabilityTypes()) &&
      Objects.equals(getRelationships(), otherAsPersona.getRelationships()) &&
      Objects.equals(getOtherDetails(), otherAsPersona.getOtherDetails()) &&
      Objects.equals(getCode(), otherAsPersona.getCode()) &&
      Objects.equals(getOriginalCode(), otherAsPersona.getOriginalCode()) &&
      Objects.equals(getLinks(), otherAsPersona.getLinks()) &&
      Objects.equals(getUid(), otherAsPersona.getUid()) &&
      Objects.equals(getArchetypeNodeId(), otherAsPersona.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsPersona.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsPersona.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsPersona.getFeederAudit()) &&
      Objects.equals(performer, otherAsPersona.performer) &&
      Objects.equals(capabilities, otherAsPersona.capabilities);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(super.hashCode(), performer);
    result = capabilities == null ? 0 : 31 * result + capabilities.hashCode();
    return result;
  }

  public ObjectRef getPerformer() {
    return performer;
  }

  public void setPerformer(ObjectRef performer) {
    this.performer = performer;
  }

  public @Nullable List<Node> getCapabilities() {
    return capabilities;
  }

  public void setCapabilities(@Nullable List<Node> capabilities) {
    this.capabilities = capabilities;
  }

  @Override
  public String bmmClassName() {
    return "Persona";
  }

  @Override
  public String toString() {
    return "Persona";
  }
}
