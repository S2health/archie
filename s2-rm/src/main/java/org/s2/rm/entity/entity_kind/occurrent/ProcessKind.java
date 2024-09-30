package org.s2.rm.entity.entity_kind.occurrent;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.data_structures.Node;
import org.s2.rm.entity.entity_kind.EntityKind;

/**
* BMM name: Process_kind
* BMM ancestors: Entity_kind
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.5
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Process_kind", propOrder = {
  "uid"
})
public class ProcessKind extends EntityKind {

  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public ProcessKind() {}

  public ProcessKind(String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    ProcessKind otherAsProcessKind = (ProcessKind) other;
    return Objects.equals(getItems(), otherAsProcessKind.getItems()) &&
      Objects.equals(uid, otherAsProcessKind.uid) &&
      Objects.equals(getArchetypeNodeId(), otherAsProcessKind.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsProcessKind.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsProcessKind.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsProcessKind.getFeederAudit());
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), uid);
  }

  public @Nullable Uuid getUid() {
    return uid;
  }

  public void setUid(@Nullable Uuid uid) {
    this.uid = uid;
  }

  @Override
  public String bmmClassName() {
    return "Process_kind";
  }

  @Override
  public String toString() {
    return "Process_kind";
  }
}
