package org.s2.rm.base.model_support.definitions;

import java.util.*;
import javax.xml.bind.annotation.*;
import org.s2.util.enumerations.*;

/**
* BMM name: Trend_kind
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trend_kind", propOrder = {"value"})
public class TrendKind extends EnumerationVarString {
  /**
  * Enumeration value.
  */
  @XmlElement(name = "value")
  String value;

  /**
  * Enumeration type.
  */
  static final TrendKindEnum enumeration = new TrendKindEnum();

  public TrendKind() {}

  // Enumeration value constructor.
  public TrendKind(String value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    TrendKind otherAsTrendKind = (TrendKind) other;
    return Objects.equals(value, otherAsTrendKind.value);
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
    return "Trend_kind";
  }

  @Override
  public String toString() {
    return "Trend_kind";
  }
}
