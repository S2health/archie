package org.s2.rm.entity.social_entity;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.data_structures.Node;

/**
* BMM name: Org_entity
* BMM ancestors: Aggregate_agent
* isAbstract: true | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.7
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Org_entity", propOrder = {
  "uid"
})
public abstract class OrgEntity extends AggregateAgent {

  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public OrgEntity() {}

  public OrgEntity(List<Node> identities, String archetypeNodeId, String name) {
    super(identities, archetypeNodeId, name);
  }

  public @Nullable Uuid getUid() {
    return uid;
  }

  public void setUid(@Nullable Uuid uid) {
    this.uid = uid;
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
