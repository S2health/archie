package org.s2.rm.admin;

import org.s2.util.enumerations.*;

/**
* BMM name: Service_event_state
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
public class ServiceEventStateEnum extends EnumerationTypeString {
  static String[] _itemNames = {"planned", "in_progress", "on_hold", "completed", "cancelled", "abandoned"};
  static String[] _itemValues = {};

  public ServiceEventStateEnum() {
    super(_itemNames, _itemValues);
  }
}
