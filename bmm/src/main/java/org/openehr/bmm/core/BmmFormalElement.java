package org.openehr.bmm.core;

/*
 * #%L
 * OpenEHR - Java Model Stack
 * %%
 * Copyright (C) 2016 - 2017 Cognitive Medical Systems
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 * Author: Claude Nanjo
 */

import com.nedap.archie.base.MultiplicityInterval;

import java.io.Serializable;

/**
 * Created by cnanjo on 4/11/16.
 */
public abstract class BmmFormalElement<T extends BmmType> extends BmmModelElement {

	/**
	 * Formal type of this property.
	 */
	protected T type;

	/**
	 * True if this property is mandatory in its class.
	 */
	protected boolean isMandatory;

	public BmmFormalElement(String aName, T aType, boolean isMandatoryFlag, String aDocumentation) {
		name = aName;
		type = aType;
		isMandatory = isMandatoryFlag;
		setDocumentation(aDocumentation);
	}

	public BmmFormalElement(BmmFormalElement<T> other) {
		name = other.name;
		type = other.type;
		isMandatory = other.isMandatory;
		setDocumentation(other.getDocumentation());
	}

	public BmmFormalElement() {
	}

	/**
	 * Returns the formal type of this property.
	 *
	 * @return
	 */
	public T getType() {
		return type;
	}

	/**
	 * Sets the formal type of this property.
	 *
	 * @param type
	 */
	public void setType(T type) {
		this.type = type;
	}


	/**
	 * Returns true if this property is mandatory in its class.
	 *
	 * @return
	 */
	public boolean getMandatory() {
		return isMandatory;
	}

	/**
	 * Set to true if this property is mandatory in its class.
	 * @param mandatory
	 */
	public void setMandatory(boolean mandatory) {
		this.isMandatory = mandatory;
	}

}
