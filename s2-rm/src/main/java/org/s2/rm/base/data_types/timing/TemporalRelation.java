package org.s2.rm.base.data_types.timing;

import com.nedap.archie.base.RMObject;

import java.util.*;
import javax.xml.bind.annotation.*;

/**
* BMM name: Temporal_relation
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.0
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Temporal_relation", propOrder = {"value"})
public class TemporalRelation extends RMObject {
  /**
  * Enumeration value.
  */
  @XmlElement(name = "value")
  int value;

  /**
  * Enumeration type.
  */
  static final TemporalRelationEnum enumeration = new TemporalRelationEnum();

  public TemporalRelation() {}

  // Enumeration value constructor.
  public TemporalRelation(int value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    TemporalRelation otherAsTemporalRelation = (TemporalRelation) other;
    return Objects.equals(value, otherAsTemporalRelation.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), value);
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public String bmmClassName() {
    return "Temporal_relation";
  }

  @Override
  public String toString() {
    return "Temporal_relation";
  }
}
