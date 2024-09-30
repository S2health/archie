package org.s2.rm.base.data_types.quantity;

import org.s2.util.enumerations.*;

/**
* BMM name: Comparison_operator
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
public class ComparisonOperatorEnum extends EnumerationString {
  static String[] _itemNames = {"<", "<=", ">", ">=", "="};
  static String[] _itemValues = {};

  public ComparisonOperatorEnum() {
    super(_itemNames, _itemValues);
  }
}
