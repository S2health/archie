package org.s2.rm.admin;

import org.s2.util.enumerations.*;

/**
* BMM name: Encounter_state
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
public class EncounterStateEnum extends EnumerationTypeString {
  static String[] _itemNames = {"planned", "in_progress", "on_hold", "discharged", "completed", "cancelled", "discontinued"};
  static String[] _itemValues = {};

  public EncounterStateEnum() {
    super(_itemNames, _itemValues);
  }
}
