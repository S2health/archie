package org.s2.rm.base.model_support.identification;

import com.nedap.archie.base.RMObject;
import java.util.*;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;

/**
* BMM name: Object_ref
* isAbstract: false | isPrimitiveType: false | isOverride: false
* BMM schema: S2RM 0.8.6
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Object_ref", propOrder = {
  "id",
  "namespace",
  "type",
  "path"
})
public class ObjectRef extends RMObject {
  /**
  * BMM name: id | BMM type: Object_id
  * isMandatory: true | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 1..1
  */
  @XmlElement(name = "id")
  private ObjectId id;

  /**
  * BMM name: namespace | BMM type: String
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 0..1
  */
  @XmlElement(name = "namespace")
  private @Nullable String namespace;

  /**
  * BMM name: type | BMM type: String
  * isMandatory: true | isComputed: false | isImRuntime: false | isImInfrastructure: false | existence: 1..1
  */
  @XmlElement(name = "type")
  private String type;

  /**
  * BMM name: path | BMM type: String
  * isMandatory: false | isComputed: false | isImRuntime: false | isImInfrastructure: true | existence: 0..1
  */
  @XmlElement(name = "path")
  private @Nullable String path;

  public ObjectRef() {}

  public ObjectRef(ObjectId id, String type) {
    this.id = id;
    this.type = type;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    ObjectRef otherAsObjectRef = (ObjectRef) other;
    return Objects.equals(id, otherAsObjectRef.id) &&
      Objects.equals(namespace, otherAsObjectRef.namespace) &&
      Objects.equals(type, otherAsObjectRef.type) &&
      Objects.equals(path, otherAsObjectRef.path);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), id, namespace, type, path);
  }

  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public @Nullable String getNamespace() {
    return namespace;
  }

  public void setNamespace(@Nullable String namespace) {
    this.namespace = namespace;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public @Nullable String getPath() {
    return path;
  }

  public void setPath(@Nullable String path) {
    this.path = path;
  }

  public String bmmClassName() {
    return "Object_ref";
  }

  @Override
  public String toString() {
    return "Object_ref";
  }
}
