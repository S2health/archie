package org.s2.rm.base.data_types.timing;

import org.s2.util.enumerations.*;

/**
* BMM name: Temporal_relation
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
public class TemporalRelationEnum extends EnumerationTypeInteger {
  static String[] _itemNames = {"after", "before", "with"};
  static Integer[] _itemValues = {};

  public TemporalRelationEnum() {
    super(_itemNames, _itemValues);
  }
}
