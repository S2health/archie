package org.s2.rm.entity.social_entity;

import java.util.*;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.foundation_types.terminology.TerminologyTerm;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.data_structures.Node;

/**
* BMM name: Automaton
* BMM ancestors: Individual_agent
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Automaton")
public class Automaton extends IndividualAgent {

  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  // This property is in at least one descendant where it probably has a different type.
  // Skip the property in the parent class (this one).
  // private @Nullable Uuid uid;

  public Automaton() {}

  public Automaton(List<PartyIdentity> identities, TerminologyTerm domainType, String archetypeNodeId, String name) {
    super(identities, domainType, archetypeNodeId, name);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    Automaton otherAsAutomaton = (Automaton) other;
    return Objects.equals(getPersonas(), otherAsAutomaton.getPersonas()) &&
      Objects.equals(getLanguages(), otherAsAutomaton.getLanguages()) &&
      Objects.equals(getIdentifiers(), otherAsAutomaton.getIdentifiers()) &&
      Objects.equals(getIdentities(), otherAsAutomaton.getIdentities()) &&
      Objects.equals(getLocationAddresses(), otherAsAutomaton.getLocationAddresses()) &&
      Objects.equals(getCommsAddresses(), otherAsAutomaton.getCommsAddresses()) &&
      Objects.equals(getAccountabilityTypes(), otherAsAutomaton.getAccountabilityTypes()) &&
      Objects.equals(getDomainType(), otherAsAutomaton.getDomainType()) &&
      Objects.equals(getRelationships(), otherAsAutomaton.getRelationships()) &&
      Objects.equals(getOtherDetails(), otherAsAutomaton.getOtherDetails()) &&
      Objects.equals(getUid(), otherAsAutomaton.getUid()) &&
      Objects.equals(getArchetypeNodeId(), otherAsAutomaton.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsAutomaton.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsAutomaton.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsAutomaton.getFeederAudit());
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode());
  }

  @Override
  public String bmmClassName() {
    return "Automaton";
  }

  @Override
  public String toString() {
    return "Automaton";
  }
}
