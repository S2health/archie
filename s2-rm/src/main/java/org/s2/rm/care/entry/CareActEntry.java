package org.s2.rm.care.entry;

import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.foundation_types.terminology.TerminologyCode;
import org.s2.rm.base.foundation_types.time.DateTime;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.participation.PartyProxy;

/**
* Entry type for billable and/or ordered acts
* BMM name: Care_act_entry
* BMM ancestors: Care_entry
* isAbstract: true | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.0
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Care_act_entry", propOrder = {
  "orderTracking"
})
public abstract class CareActEntry extends CareEntry {
  /**
  * BMM name: order_tracking | BMM type: Order_tracking
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "order_tracking")
  private @Nullable OrderTracking orderTracking;

  public CareActEntry() {}

  public CareActEntry(Uuid uid, DateTime time, TerminologyCode language, PartyProxy subject, String archetypeNodeId, String name) {
    super(uid, time, language, subject, archetypeNodeId, name);
  }

  public @Nullable OrderTracking getOrderTracking() {
    return orderTracking;
  }

  public void setOrderTracking(@Nullable OrderTracking orderTracking) {
    this.orderTracking = orderTracking;
  }

  @Override
  public String bmmClassName() {
    return "Care_act_entry";
  }

  @Override
  public String toString() {
    return "Care_act_entry";
  }
}
