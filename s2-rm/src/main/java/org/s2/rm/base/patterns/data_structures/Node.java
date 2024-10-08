package org.s2.rm.base.patterns.data_structures;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.data_types.DataValue;
import org.s2.rm.base.data_types.text.Text;
import org.s2.rm.base.foundation_types.terminology.TerminologyTerm;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.InfoItem;

/**
* BMM name: Node
* BMM ancestors: Info_item
* isAbstract: true | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Node", propOrder = {
  "uid",
  "value",
  "nullFlavor",
  "nullReason",
  "items"
})
public abstract class Node extends InfoItem {
  /**
  * BMM name: value | BMM type: Data_value
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "value")
  private @Nullable DataValue value;

  /**
  * BMM name: null_flavor | BMM type: Terminology_term
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  * valueConstraint: s2.NullFlavor
  */
  @XmlElement(name = "null_flavor")
  private @Nullable TerminologyTerm nullFlavor;

  /**
  * BMM name: null_reason | BMM type: Text
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "null_reason")
  private @Nullable Text nullReason;

  /**
  * BMM name: items | BMM type: {@code List<Node>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "items")
  private @Nullable List<Node> items;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public Node() {}

  public Node(String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
  }

  public @Nullable DataValue getValue() {
    return value;
  }

  public void setValue(@Nullable DataValue value) {
    this.value = value;
  }

  public @Nullable TerminologyTerm getNullFlavor() {
    return nullFlavor;
  }

  public void setNullFlavor(@Nullable TerminologyTerm nullFlavor) {
    this.nullFlavor = nullFlavor;
  }

  public @Nullable Text getNullReason() {
    return nullReason;
  }

  public void setNullReason(@Nullable Text nullReason) {
    this.nullReason = nullReason;
  }

  public @Nullable List<Node> getItems() {
    return items;
  }

  public void setItems(@Nullable List<Node> items) {
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
    return "Node";
  }

  @Override
  public String toString() {
    return "Node";
  }
}
