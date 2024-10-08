package org.s2.rm.care.composition;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.archetyped.Link;

/**
* BMM name: Section
* BMM ancestors: Content_item
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Section", propOrder = {
  "uid",
  "items"
})
public class Section extends ContentItem {
  /**
  * BMM name: items | BMM type: {@code List<Content_item>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "items")
  private @Nullable List<ContentItem> items;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public Section() {}

  public Section(String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    Section otherAsSection = (Section) other;
    return Objects.equals(getCode(), otherAsSection.getCode()) &&
      Objects.equals(getOriginalCode(), otherAsSection.getOriginalCode()) &&
      Objects.equals(getLinks(), otherAsSection.getLinks()) &&
      Objects.equals(uid, otherAsSection.uid) &&
      Objects.equals(getArchetypeNodeId(), otherAsSection.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsSection.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsSection.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsSection.getFeederAudit()) &&
      Objects.equals(items, otherAsSection.items);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(super.hashCode(), uid);
    result = items == null ? 0 : 31 * result + items.hashCode();
    return result;
  }

  public @Nullable List<ContentItem> getItems() {
    return items;
  }

  public void setItems(@Nullable List<ContentItem> items) {
    this.items = items;
  }

  public @Nullable Uuid getUid() {
    return uid;
  }

  public void setUid(@Nullable Uuid uid) {
    this.uid = uid;
  }

  @Override
  public String bmmClassName() {
    return "Section";
  }

  @Override
  public String toString() {
    return "Section";
  }
}
