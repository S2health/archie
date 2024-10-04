package org.s2.rm.base.patterns.data_structures;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.data_types.DataValue;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.archetyped.Link;

/**
* BMM name: Info_node
* BMM ancestors: Node
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Info_node", propOrder = {
  "originalValue"
})
public class InfoNode extends Node {
  /**
  * BMM name: original_value | BMM type: Data_value
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "original_value")
  private @Nullable DataValue originalValue;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  // This property is in at least one descendant where it probably has a different type.
  // Skip the property in the parent class (this one).
  // private @Nullable Uuid uid;

  public InfoNode() {}

  public InfoNode(String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    InfoNode otherAsInfoNode = (InfoNode) other;
    return Objects.equals(getValue(), otherAsInfoNode.getValue()) &&
      Objects.equals(getNullFlavor(), otherAsInfoNode.getNullFlavor()) &&
      Objects.equals(getNullReason(), otherAsInfoNode.getNullReason()) &&
      Objects.equals(getItems(), otherAsInfoNode.getItems()) &&
      Objects.equals(getCode(), otherAsInfoNode.getCode()) &&
      Objects.equals(getOriginalCode(), otherAsInfoNode.getOriginalCode()) &&
      Objects.equals(getLinks(), otherAsInfoNode.getLinks()) &&
      Objects.equals(getUid(), otherAsInfoNode.getUid()) &&
      Objects.equals(getArchetypeNodeId(), otherAsInfoNode.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsInfoNode.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsInfoNode.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsInfoNode.getFeederAudit()) &&
      Objects.equals(originalValue, otherAsInfoNode.originalValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), originalValue);
  }

  public @Nullable DataValue getOriginalValue() {
    return originalValue;
  }

  public void setOriginalValue(@Nullable DataValue originalValue) {
    this.originalValue = originalValue;
  }

  @Override
  public String bmmClassName() {
    return "Info_node";
  }

  @Override
  public String toString() {
    return "Info_node";
  }
}
