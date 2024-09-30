package org.s2.rm.entity.social_entity;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.foundation_types.terminology.TerminologyTerm;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.data_structures.Node;

/**
* BMM name: Team
* BMM ancestors: Aggregate_agent
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Team", propOrder = {
  "uid"
})
public class Team extends AggregateAgent {

  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public Team() {}

  public Team(List<PartyIdentity> identities, TerminologyTerm domainType, String archetypeNodeId, String name) {
    super(identities, domainType, archetypeNodeId, name);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    Team otherAsTeam = (Team) other;
    return Objects.equals(getPersonas(), otherAsTeam.getPersonas()) &&
      Objects.equals(getLanguages(), otherAsTeam.getLanguages()) &&
      Objects.equals(getIdentifiers(), otherAsTeam.getIdentifiers()) &&
      Objects.equals(getIdentities(), otherAsTeam.getIdentities()) &&
      Objects.equals(getLocationAddresses(), otherAsTeam.getLocationAddresses()) &&
      Objects.equals(getCommsAddresses(), otherAsTeam.getCommsAddresses()) &&
      Objects.equals(getAccountabilityTypes(), otherAsTeam.getAccountabilityTypes()) &&
      Objects.equals(getDomainType(), otherAsTeam.getDomainType()) &&
      Objects.equals(getRelationships(), otherAsTeam.getRelationships()) &&
      Objects.equals(getOtherDetails(), otherAsTeam.getOtherDetails()) &&
      Objects.equals(uid, otherAsTeam.uid) &&
      Objects.equals(getArchetypeNodeId(), otherAsTeam.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsTeam.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsTeam.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsTeam.getFeederAudit());
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
    return "Team";
  }

  @Override
  public String toString() {
    return "Team";
  }
}
