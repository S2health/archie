package org.s2.rm.base.patterns.domain_data_structures;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.data_types.RweIdRef;
import org.s2.rm.base.foundation_types.terminology.TerminologyTerm;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.archetyped.Link;
import org.s2.rm.base.patterns.data_structures.InfoNode;
import org.s2.rm.base.patterns.data_structures.Node;

/**
* BMM name: Id_use
* BMM ancestors: Info_node
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Id_use", propOrder = {
  "uid",
  "tag",
  "originalId"
})
public class IdUse extends InfoNode {
  /**
  * BMM name: tag | BMM type: Terminology_term
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "tag")
  private @Nullable TerminologyTerm tag;

  /**
  * BMM name: original_id | BMM type: Rwe_id_ref
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "original_id")
  private @Nullable RweIdRef originalId;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public IdUse() {}

  public IdUse(String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    IdUse otherAsIdUse = (IdUse) other;
    return Objects.equals(getOriginalValue(), otherAsIdUse.getOriginalValue()) &&
      Objects.equals(getValue(), otherAsIdUse.getValue()) &&
      Objects.equals(getNullFlavor(), otherAsIdUse.getNullFlavor()) &&
      Objects.equals(getNullReason(), otherAsIdUse.getNullReason()) &&
      Objects.equals(getItems(), otherAsIdUse.getItems()) &&
      Objects.equals(getCode(), otherAsIdUse.getCode()) &&
      Objects.equals(getOriginalCode(), otherAsIdUse.getOriginalCode()) &&
      Objects.equals(getLinks(), otherAsIdUse.getLinks()) &&
      Objects.equals(uid, otherAsIdUse.uid) &&
      Objects.equals(getArchetypeNodeId(), otherAsIdUse.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsIdUse.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsIdUse.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsIdUse.getFeederAudit()) &&
      Objects.equals(tag, otherAsIdUse.tag) &&
      Objects.equals(originalId, otherAsIdUse.originalId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), uid, tag, originalId);
  }

  public @Nullable TerminologyTerm getTag() {
    return tag;
  }

  public void setTag(@Nullable TerminologyTerm tag) {
    this.tag = tag;
  }

  public @Nullable RweIdRef getOriginalId() {
    return originalId;
  }

  public void setOriginalId(@Nullable RweIdRef originalId) {
    this.originalId = originalId;
  }

  public @Nullable Uuid getUid() {
    return uid;
  }

  public void setUid(@Nullable Uuid uid) {
    this.uid = uid;
  }

  @Override
  public String bmmClassName() {
    return "Id_use";
  }

  @Override
  public String toString() {
    return "Id_use";
  }
}
