package org.s2.rm.base.data_types.quantity;

import javax.xml.bind.annotation.*;

/**
* BMM name: Temporal_value
* BMM ancestors: Ordered_value
* isAbstract: true | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Temporal_value", propOrder = {
  "magnitude"
})
public abstract class TemporalValue extends OrderedValue {
  /**
  * BMM name: magnitude | BMM type: Temporal
  * isMandatory: true | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 1..1
  */
  // This property is in at least one descendant where it probably has a different type.
  // Skip the property in the parent class (this one).
  // private Temporal magnitude;

  public TemporalValue() {}


  @Override
  public String bmmClassName() {
    return "Temporal_value";
  }

  @Override
  public String toString() {
    return "Temporal_value";
  }
}
