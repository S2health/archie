package org.s2.rm.base.model_support.identification;

import com.nedap.archie.base.RMObject;
import java.util.*;
import javax.xml.bind.annotation.*;

/**
* BMM name: Object_id
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Object_id", propOrder = {
  "value"
})
public class ObjectId extends RMObject {
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

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    ObjectId otherAsObjectId = (ObjectId) other;
    return Objects.equals(value, otherAsObjectId.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), value);
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
