package org.s2.rm.care.composition;

import javax.xml.bind.annotation.*;
import org.s2.rm.base.patterns.archetyped.InfoItem;

/**
* BMM name: Content_item
* BMM ancestors: Info_item
* isAbstract: true | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Content_item")
public abstract class ContentItem extends InfoItem {

  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  // This property is in at least one descendant where it probably has a different type.
  // Skip the property in the parent class (this one).
  // private @Nullable Uuid uid;

  public ContentItem() {}

  public ContentItem(String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
  }

  @Override
  public String bmmClassName() {
    return "Content_item";
  }

  @Override
  public String toString() {
    return "Content_item";
  }
}
