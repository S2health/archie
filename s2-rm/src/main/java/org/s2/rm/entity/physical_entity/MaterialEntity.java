package org.s2.rm.entity.physical_entity;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.foundation_types.terminology.TerminologyTerm;
import org.s2.rm.base.model_support.identification.ObjectRef;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.data_structures.Node;
import org.s2.rm.entity.entity_kind.continuant.MaterialEntityKind;
import org.s2.rm.entity.social_entity.IdUse;

/**
* BMM name: Material_entity
* BMM generic parameters: {@code Material_entity<K Material_entity_kind>}
* BMM ancestors: {@code Physical_entity<K>}
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Material_entity", propOrder = {
  "uid",
  "identifier",
  "identifiers",
  "individualAttributes",
  "lotInformation",
  "useDetails"
})
public class MaterialEntity<K extends MaterialEntityKind> extends PhysicalEntity<K> {
  /**
  * BMM name: identifier | BMM type: Object_ref
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "identifier")
  private @Nullable ObjectRef identifier;

  /**
  * BMM name: identifiers | BMM type: {@code List<Id_use>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "identifiers")
  private @Nullable List<IdUse> identifiers;

  /**
  * BMM name: individual_attributes | BMM type: {@code List<Node>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "individual_attributes")
  private @Nullable List<Node> individualAttributes;

  /**
  * BMM name: lot_information | BMM type: {@code List<Node>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "lot_information")
  private @Nullable List<Node> lotInformation;

  /**
  * BMM name: use_details | BMM type: {@code List<Node>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "use_details")
  private @Nullable List<Node> useDetails;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public MaterialEntity() {}

  public MaterialEntity(TerminologyTerm domainType, String archetypeNodeId, String name) {
    super(domainType, archetypeNodeId, name);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    MaterialEntity<K> otherAsMaterialEntity = (MaterialEntity<K>) other;
    return Objects.equals(getKind(), otherAsMaterialEntity.getKind()) &&
      Objects.equals(getKindRef(), otherAsMaterialEntity.getKindRef()) &&
      Objects.equals(getDomainType(), otherAsMaterialEntity.getDomainType()) &&
      Objects.equals(getRelationships(), otherAsMaterialEntity.getRelationships()) &&
      Objects.equals(getOtherDetails(), otherAsMaterialEntity.getOtherDetails()) &&
      Objects.equals(uid, otherAsMaterialEntity.uid) &&
      Objects.equals(getArchetypeNodeId(), otherAsMaterialEntity.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsMaterialEntity.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsMaterialEntity.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsMaterialEntity.getFeederAudit()) &&
      Objects.equals(identifier, otherAsMaterialEntity.identifier) &&
      Objects.equals(identifiers, otherAsMaterialEntity.identifiers) &&
      Objects.equals(individualAttributes, otherAsMaterialEntity.individualAttributes) &&
      Objects.equals(lotInformation, otherAsMaterialEntity.lotInformation) &&
      Objects.equals(useDetails, otherAsMaterialEntity.useDetails);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(super.hashCode(), uid, identifier);
    result = identifiers == null ? 0 : 31 * result + identifiers.hashCode();
    result = individualAttributes == null ? 0 : 31 * result + individualAttributes.hashCode();
    result = lotInformation == null ? 0 : 31 * result + lotInformation.hashCode();
    result = useDetails == null ? 0 : 31 * result + useDetails.hashCode();
    return result;
  }

  public @Nullable ObjectRef getIdentifier() {
    return identifier;
  }

  public void setIdentifier(@Nullable ObjectRef identifier) {
    this.identifier = identifier;
  }

  public @Nullable List<IdUse> getIdentifiers() {
    return identifiers;
  }

  public void setIdentifiers(@Nullable List<IdUse> identifiers) {
    this.identifiers = identifiers;
  }

  public @Nullable List<Node> getIndividualAttributes() {
    return individualAttributes;
  }

  public void setIndividualAttributes(@Nullable List<Node> individualAttributes) {
    this.individualAttributes = individualAttributes;
  }

  public @Nullable List<Node> getLotInformation() {
    return lotInformation;
  }

  public void setLotInformation(@Nullable List<Node> lotInformation) {
    this.lotInformation = lotInformation;
  }

  public @Nullable List<Node> getUseDetails() {
    return useDetails;
  }

  public void setUseDetails(@Nullable List<Node> useDetails) {
    this.useDetails = useDetails;
  }

  public @Nullable Uuid getUid() {
    return uid;
  }

  public void setUid(@Nullable Uuid uid) {
    this.uid = uid;
  }

  @Override
  public String bmmClassName() {
    return "Material_entity";
  }

  @Override
  public String toString() {
    return "Material_entity";
  }
}
