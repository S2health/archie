package org.s2.rm.admin;

import com.nedap.archie.base.RMObject;
import java.util.*;
import javax.xml.bind.annotation.*;

/**
* BMM name: Encounter_state
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Encounter_state", propOrder = {"value"})
public class EncounterState extends RMObject {
  /**
  * Enumeration value.
  */
  @XmlElement(name = "value")
  String value;

  /**
  * Enumeration type.
  */
  static final EncounterStateEnum enumeration = new EncounterStateEnum();

  public EncounterState() {}

  // Enumeration value constructor.
  public EncounterState(String value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    EncounterState otherAsEncounterState = (EncounterState) other;
    return Objects.equals(value, otherAsEncounterState.value);
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
    return "Encounter_state";
  }

  @Override
  public String toString() {
    return "Encounter_state";
  }
}
