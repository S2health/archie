package org.s2.rm.base.model_support.identification;

import javax.xml.bind.annotation.*;

/**
* BMM name: Object_id
* isAbstract: true | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.0
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Object_id", propOrder = {
  "value"
})
public abstract class ObjectId {
  /**
  * BMM name: value | BMM type: String
  * isMandatory: true | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 1..1
  */
  @XmlElement(name = "value")
  private String value;

  public ObjectId() {}

  public ObjectId(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String bmmClassName() {
    return "Object_id";
  }

  @Override
  public String toString() {
    return "Object_id";
  }
}
