package org.s2.rm.base.data_types.timing;

import com.nedap.archie.base.RMObject;
import javax.xml.bind.annotation.*;

/**
* BMM name: Hour_specifier
* isAbstract: true | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Hour_specifier")
public abstract class HourSpecifier extends RMObject {
  public HourSpecifier() {}


  public String bmmClassName() {
    return "Hour_specifier";
  }

  @Override
  public String toString() {
    return "Hour_specifier";
  }
}
