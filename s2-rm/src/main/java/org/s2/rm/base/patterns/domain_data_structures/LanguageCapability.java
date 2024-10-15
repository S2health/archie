package org.s2.rm.base.patterns.domain_data_structures;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.foundation_types.terminology.TerminologyCode;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.FeederAudit;
import org.s2.rm.base.patterns.archetyped.InfoItem;
import org.s2.rm.base.patterns.archetyped.Link;

/**
* BMM name: Language_capability
* BMM ancestors: Info_item
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Language_capability", propOrder = {
  "uid",
  "language",
  "preferred"
})
public class LanguageCapability extends InfoItem {
  /**
  * BMM name: language | BMM type: Terminology_code
  * isMandatory: true | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 1..1
  * valueConstraint: iso_639-1
  */
  @XmlElement(name = "language")
  private TerminologyCode language;

  /**
  * BMM name: preferred | BMM type: Boolean
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "preferred")
  private boolean preferred;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public LanguageCapability() {}

  public LanguageCapability(TerminologyCode language, String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
    this.language = language;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    LanguageCapability otherAsLanguageCapability = (LanguageCapability) other;
    return Objects.equals(getCode(), otherAsLanguageCapability.getCode()) &&
      Objects.equals(getOriginalCode(), otherAsLanguageCapability.getOriginalCode()) &&
      Objects.equals(getLinks(), otherAsLanguageCapability.getLinks()) &&
      Objects.equals(uid, otherAsLanguageCapability.uid) &&
      Objects.equals(getArchetypeNodeId(), otherAsLanguageCapability.getArchetypeNodeId()) &&
      Objects.equals(getName(), otherAsLanguageCapability.getName()) &&
      Objects.equals(getArchetypeDetails(), otherAsLanguageCapability.getArchetypeDetails()) &&
      Objects.equals(getFeederAudit(), otherAsLanguageCapability.getFeederAudit()) &&
      Objects.equals(language, otherAsLanguageCapability.language) &&
      Objects.equals(preferred, otherAsLanguageCapability.preferred);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), uid, language, preferred);
  }

  public TerminologyCode getLanguage() {
    return language;
  }

  public void setLanguage(TerminologyCode language) {
    this.language = language;
  }

  public boolean getPreferred() {
    return preferred;
  }

  public void setPreferred(boolean preferred) {
    this.preferred = preferred;
  }

  public @Nullable Uuid getUid() {
    return uid;
  }

  public void setUid(@Nullable Uuid uid) {
    this.uid = uid;
  }

  @Override
  public String bmmClassName() {
    return "Language_capability";
  }

  @Override
  public String toString() {
    return "Language_capability";
  }
}
