package org.s2.rm.base.patterns.archetyped;

import com.nedap.archie.base.RMObject;
import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.foundation_types.time.RmDateTime;
import org.s2.rm.base.patterns.data_structures.EntityRefNode;

/**
* BMM name: Feeder_audit_details
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Feeder_audit_details", propOrder = {
  "systemId",
  "location",
  "provider",
  "subject",
  "time",
  "versionId",
  "otherDetails"
})
public class FeederAuditDetails extends RMObject {
  /**
  * BMM name: system_id | BMM type: String
  * isMandatory: true | isComputed: false | isImRuntime: true | isImInfrastructure: false | existence: 1..1
  */
  @XmlElement(name = "system_id")
  private String systemId;

  /**
  * BMM name: location | BMM type: Entity_ref_node
  * isMandatory: false | isComputed: false | isImRuntime: true | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "location")
  private @Nullable EntityRefNode location;

  /**
  * BMM name: provider | BMM type: Entity_ref_node
  * isMandatory: false | isComputed: false | isImRuntime: true | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "provider")
  private @Nullable EntityRefNode provider;

  /**
  * BMM name: subject | BMM type: Entity_ref_node
  * isMandatory: false | isComputed: false | isImRuntime: true | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "subject")
  private @Nullable EntityRefNode subject;

  /**
  * BMM name: time | BMM type: Date_time
  * isMandatory: false | isComputed: false | isImRuntime: true | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "time")
  private @Nullable RmDateTime time;

  /**
  * BMM name: version_id | BMM type: String
  * isMandatory: false | isComputed: false | isImRuntime: true | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "version_id")
  private @Nullable String versionId;

  /**
  * BMM name: other_details | BMM type: {@code Hash<String,String>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "other_details")
  private @Nullable Map<String, String> otherDetails;

  public FeederAuditDetails() {}

  public FeederAuditDetails(String systemId) {
    this.systemId = systemId;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    FeederAuditDetails otherAsFeederAuditDetails = (FeederAuditDetails) other;
    return Objects.equals(systemId, otherAsFeederAuditDetails.systemId) &&
      Objects.equals(location, otherAsFeederAuditDetails.location) &&
      Objects.equals(provider, otherAsFeederAuditDetails.provider) &&
      Objects.equals(subject, otherAsFeederAuditDetails.subject) &&
      Objects.equals(time, otherAsFeederAuditDetails.time) &&
      Objects.equals(versionId, otherAsFeederAuditDetails.versionId) &&
      Objects.equals(otherDetails, otherAsFeederAuditDetails.otherDetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), systemId, location, provider, subject, time, versionId, otherDetails);
  }

  public String getSystemId() {
    return systemId;
  }

  public void setSystemId(String systemId) {
    this.systemId = systemId;
  }

  public @Nullable EntityRefNode getLocation() {
    return location;
  }

  public void setLocation(@Nullable EntityRefNode location) {
    this.location = location;
  }

  public @Nullable EntityRefNode getProvider() {
    return provider;
  }

  public void setProvider(@Nullable EntityRefNode provider) {
    this.provider = provider;
  }

  public @Nullable EntityRefNode getSubject() {
    return subject;
  }

  public void setSubject(@Nullable EntityRefNode subject) {
    this.subject = subject;
  }

  public @Nullable RmDateTime getTime() {
    return time;
  }

  public void setTime(@Nullable RmDateTime time) {
    this.time = time;
  }

  public @Nullable String getVersionId() {
    return versionId;
  }

  public void setVersionId(@Nullable String versionId) {
    this.versionId = versionId;
  }

  public @Nullable Map<String, String> getOtherDetails() {
    return otherDetails;
  }

  public void setOtherDetails(@Nullable Map<String, String> otherDetails) {
    this.otherDetails = otherDetails;
  }

  public String bmmClassName() {
    return "Feeder_audit_details";
  }

  @Override
  public String toString() {
    return "Feeder_audit_details";
  }
}
