package org.s2.rm.care.ehr;

import com.nedap.archie.base.RMObject;
import javax.xml.bind.annotation.*;

/**
* BMM name: Access_control_settings
* isAbstract: true | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Access_control_settings")
public abstract class AccessControlSettings extends RMObject {
  public AccessControlSettings() {}


  public String bmmClassName() {
    return "Access_control_settings";
  }

  @Override
  public String toString() {
    return "Access_control_settings";
  }
}
