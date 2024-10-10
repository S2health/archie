package org.s2.rm.entity.social_entity;

import javax.xml.bind.annotation.*;
import org.s2.rm.base.foundation_types.terminology.TerminologyTerm;
import org.s2.rm.entity.entity.Entity;

/**
* BMM name: Social_entity
* BMM ancestors: Entity
* isAbstract: true | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Social_entity")
public abstract class SocialEntity extends Entity {

  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  // This property is in at least one descendant where it probably has a different type.
  // Skip the property in the parent class (this one).
  // private @Nullable Uuid uid;

  public SocialEntity() {}

  public SocialEntity(TerminologyTerm domainType, String archetypeNodeId, String name) {
    super(domainType, archetypeNodeId, name);
  }

  @Override
  public String bmmClassName() {
    return "Social_entity";
  }

  @Override
  public String toString() {
    return "Social_entity";
  }
}
