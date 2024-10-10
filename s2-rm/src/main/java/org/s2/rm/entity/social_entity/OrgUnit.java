package org.s2.rm.entity.social_entity;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.foundation_types.terminology.TerminologyTerm;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.data_structures.Node;
import org.s2.rm.base.patterns.domain_data_structures.PartyIdentity;

/**
* BMM name: Org_unit
* BMM ancestors: Org_entity
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Org_unit", propOrder = {
  "uid"
})
public class OrgUnit extends OrgEntity {

  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public OrgUnit() {}

  public OrgUnit(List<PartyIdentity> identities, TerminologyTerm domainType, String archetypeNodeId, String name) {
    super(identities, domainType, archetypeNodeId, name);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    OrgUnit otherAsOrgUnit = (OrgUnit) other;
    return Objects.equals(getPersonas(), otherAsOrgUnit.getPersonas()) &&
      Objects.equals(getLanguages(), otherAsOrgUnit.getLanguages()) &&
      Objects.equals(getIdentifiers(), otherAsOrgUnit.getIdentifiers()) &&
      Objects.equals(getIdentities(), otherAsOrgUnit.getIdentities()) &&
      Objects.equals(getLocationAddresses(), otherAsOrgUnit.getLocationAddresses()) &&
      Objects.equals(getCommsAddresses(), otherAsOrgUnit.getCommsAddresses()) &&
      Objects.equals(getAccountabilityTypes(), otherAsOrgUnit.getAccountabilityTypes()) &&
      Objects.equals(getDomainType(), otherAsOrgUnit.getDomainType()) &&
      Objects.equals(getRelationships(), otherAsOrgUnit.getRelationships()) &&
      Objects.equals(getOtherDetails(), otherAsOrgUnit.getOtherDetails()) &&
      Objects.equals(uid, otherAsOrgUnit.uid) &&
      Objects.equals(getArchetypeNodeId(), otherAsOrgUnit.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsOrgUnit.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsOrgUnit.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsOrgUnit.getFeederAudit());
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), uid);
  }

  public @Nullable Uuid getUid() {
    return uid;
  }

  public void setUid(@Nullable Uuid uid) {
    this.uid = uid;
  }

  @Override
  public String bmmClassName() {
    return "Org_unit";
  }

  @Override
  public String toString() {
    return "Org_unit";
  }
}
