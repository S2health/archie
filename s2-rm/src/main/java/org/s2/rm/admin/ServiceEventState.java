package org.s2.rm.admin;

import java.util.*;
import javax.xml.bind.annotation.*;
import org.s2.util.enumerations.*;

/**
* BMM name: Service_event_state
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Service_event_state", propOrder = {"value"})
public class ServiceEventState extends EnumerationVarString {
  /**
  * Enumeration value.
  */
  @XmlElement(name = "value")
  String value;

  /**
  * Enumeration type.
  */
  static final ServiceEventStateEnum enumeration = new ServiceEventStateEnum();

  public ServiceEventState() {}

  // Enumeration value constructor.
  public ServiceEventState(String value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    ServiceEventState otherAsServiceEventState = (ServiceEventState) other;
    return Objects.equals(value, otherAsServiceEventState.value);
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
    return "Service_event_state";
  }

  @Override
  public String toString() {
    return "Service_event_state";
  }
}
