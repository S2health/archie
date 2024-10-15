package org.s2.rm.entity.resource;

import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import org.s2.rm.base.foundation_types.time.RmDateTime;
import org.s2.rm.base.foundation_types.time.RmDuration;
import org.s2.rm.base.model_support.identification.Uuid;
import org.s2.rm.base.patterns.archetyped.Locatable;
import org.s2.rm.base.patterns.data_structures.Node;

/**
* BMM name: Resource_use
* BMM ancestors: Locatable
* isAbstract: true | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Resource_use", propOrder = {
  "uid",
  "startTime",
  "duration",
  "costData",
  "description"
})
public abstract class ResourceUse extends Locatable {
  /**
  * BMM name: start_time | BMM type: Date_time
  * isMandatory: true | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 1..1
  */
  @XmlElement(name = "start_time")
  private RmDateTime startTime;

  /**
  * BMM name: duration | BMM type: Duration
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "duration")
  private @Nullable RmDuration duration;

  /**
  * BMM name: cost_data | BMM type: {@code List<Node>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "cost_data")
  private @Nullable List<Node> costData;

  /**
  * BMM name: description | BMM type: {@code List<Node>}
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "description")
  private @Nullable List<Node> description;


  // Properties added from the extended class: Locatable

  /**
  * BMM name: uid | BMM type: Uuid
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "uid")
  private @Nullable Uuid uid;

  public ResourceUse() {}

  public ResourceUse(RmDateTime startTime, String archetypeNodeId, String name) {
    super(archetypeNodeId, name);
    this.startTime = startTime;
  }

  public RmDateTime getStartTime() {
    return startTime;
  }

  public void setStartTime(RmDateTime startTime) {
    this.startTime = startTime;
  }

  public @Nullable RmDuration getDuration() {
    return duration;
  }

  public void setDuration(@Nullable RmDuration duration) {
    this.duration = duration;
  }

  public @Nullable List<Node> getCostData() {
    return costData;
  }

  public void setCostData(@Nullable List<Node> costData) {
    this.costData = costData;
  }

  public @Nullable List<Node> getDescription() {
    return description;
  }

  public void setDescription(@Nullable List<Node> description) {
    this.description = description;
  }

  public @Nullable Uuid getUid() {
    return uid;
  }

  public void setUid(@Nullable Uuid uid) {
    this.uid = uid;
  }

  @Override
  public String bmmClassName() {
    return "Resource_use";
  }

  @Override
  public String toString() {
    return "Resource_use";
  }
}
