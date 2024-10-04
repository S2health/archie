package org.s2.rm.base.change_control;

import org.s2.util.enumerations.*;

/**
* BMM name: Attestation_reason
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
public class AttestationReasonEnum extends EnumerationTypeString {
  static String[] _itemNames = {"signed", "witnessed"};
  static String[] _itemValues = {};

  public AttestationReasonEnum() {
    super(_itemNames, _itemValues);
  }
}
