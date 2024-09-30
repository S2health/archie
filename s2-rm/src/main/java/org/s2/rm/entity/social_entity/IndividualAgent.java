package org.s2.rm.entity.social_entity;

import java.util.*;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.foundation_types.terminology.TerminologyTerm;

/**
* BMM name: Individual_agent
* BMM ancestors: Agent
* isAbstract: true | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Individual_agent")
public abstract class IndividualAgent extends Agent {
  public IndividualAgent() {}

  public IndividualAgent(List<PartyIdentity> identities, TerminologyTerm domainType, String archetypeNodeId, String name) {
    super(identities, domainType, archetypeNodeId, name);
  }

  @Override
  public String bmmClassName() {
    return "Individual_agent";
  }

  @Override
  public String toString() {
    return "Individual_agent";
  }
}
