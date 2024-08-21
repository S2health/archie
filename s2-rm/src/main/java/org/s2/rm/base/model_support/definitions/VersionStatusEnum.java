package org.s2.rm.base.model_support.definitions;

import org.s2.util.enumerations.*;

/**
* BMM name: Version_status
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.0
*/
public class VersionStatusEnum extends EnumerationTypeString {
  static String[] _itemNames = {"alpha", "beta", "release_candidate", "released", "build"};
  static String[] _itemValues = {};

  public VersionStatusEnum() {
    super(_itemNames, _itemValues);
  }
}
