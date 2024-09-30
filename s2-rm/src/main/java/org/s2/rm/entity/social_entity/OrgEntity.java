package org.s2.rm.entity.social_entity;

import java.util.*;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.foundation_types.terminology.TerminologyTerm;

/**
* BMM name: Org_entity
* BMM ancestors: Aggregate_agent
* isAbstract: true | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Org_entity")
public abstract class OrgEntity extends AggregateAgent {
  public OrgEntity() {}

  public OrgEntity(List<PartyIdentity> identities, TerminologyTerm domainType, String archetypeNodeId, String name) {
    super(identities, domainType, archetypeNodeId, name);
  }

  @Override
  public String bmmClassName() {
    return "Org_entity";
  }

  @Override
  public String toString() {
    return "Org_entity";
  }
}
