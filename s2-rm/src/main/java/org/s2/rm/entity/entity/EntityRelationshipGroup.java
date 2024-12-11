package org.s2.rm.entity.entity;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.model_support.identification.ObjectRef;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.InfoItem;

/**
* BMM name: Entity_relationship_group
* BMM ancestors: Info_item
* isAbstract: true | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.7
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Entity_relationship_group", propOrder = {
  "uid",
  "members"
})
public abstract class EntityRelationshipGroup extends InfoItem {
  /**
  * BMM name: members | BMM type: {@code List<Object_ref>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "members")
  private @Nullable List<ObjectRef> members;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public EntityRelationshipGroup() {}

  public EntityRelationshipGroup(String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
  }

  public @Nullable List<ObjectRef> getMembers() {
    return members;
  }

  public void setMembers(@Nullable List<ObjectRef> members) {
    this.members = members;
  }

  public @Nullable Uuid getUid() {
    return uid;
  }

  public void setUid(@Nullable Uuid uid) {
    this.uid = uid;
  }

  @Override
  public String bmmClassName() {
    return "Entity_relationship_group";
  }

  @Override
  public String toString() {
    return "Entity_relationship_group";
  }
}
