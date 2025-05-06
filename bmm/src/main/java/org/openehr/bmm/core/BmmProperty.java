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
public class BmmProperty<T extends BmmType> extends BmmFeature<T> implements Serializable {

	public static final String P_BMM_GENERIC_PROPERTY = "P_BMM_GENERIC_PROPERTY";
	public static final String P_BMM_CONTAINER_PROPERTY = "P_BMM_CONTAINER_PROPERTY";


	/**
	 * True if this property is computed rather than stored in objects of this class.
	 */
	private boolean isComputed;

	/**
	 * True if this property is marked with info model 'im_runtime' property.
	 */
	private boolean isImRuntime;

	/**
	 * True if this property was marked with info model 'im_infrastructure' flag.
	 */
	private boolean isImInfrastructure;

	public BmmProperty(String aName, T aType, String aDocumentation, boolean isMandatoryFlag, boolean isComputedFlag) {
		super (aName, aType, isMandatoryFlag, aDocumentation);
		isComputed = isComputedFlag;
	}

	public BmmProperty(BmmProperty<T> other) {
		super (other.getName(), other.getType(), other.getMandatory(), other.getDocumentation());
		isComputed = other.getComputed();
		isImInfrastructure = other.getImInfrastructure();
		isImRuntime = other.getImRuntime();
	}

	public BmmProperty() {
	}

	/**
	 * Returns true if this property is computed rather than stored in objects of this class.
	 *
	 * @return
	 */
	public boolean getComputed() {
		return isComputed;
	}

	/**
	 * Set to true if this property is computed rather than stored in objects of this class.
	 *
	 * @param computed
	 */
	public void setComputed(boolean computed) {
		isComputed = computed;
	}

	/**
	 * Returns true if this property is marked with info model 'im_runtime' property.
	 *
	 * @return
	 */
	public boolean getImRuntime() {
		return isImRuntime;
	}

	/**
	 * Set to true if this property is marked with info model 'im_runtime' property.
	 *
	 * @param imRuntime
	 */
	public void setImRuntime(boolean imRuntime) {
		isImRuntime = imRuntime;
	}

	/**
	 * Returns true if this property was marked with info model 'im_infrastructure' flag.
	 *
	 * @return
	 */
	public boolean getImInfrastructure() {
		return isImInfrastructure;
	}

	/**
	 * Set to true if this property was marked with info model 'im_infrastructure' flag.
	 *
	 * @param imInfrastructure
	 */
	public void setImInfrastructure(boolean imInfrastructure) {
		isImInfrastructure =imInfrastructure;
	}

	/**
	 * Method returns true if BMM Type argument is a container type.
	 *
	 * @param bmmType
	 * @return
	 */
	public static boolean isContainerType(String bmmType) {
		return bmmType.equals(BmmEntity.P_BMM_CONTAINER_TYPE) || bmmType.equals(BmmEntity.BMM_CONTAINER_TYPE);
	}

	/**
	 * Interval form of 0..1, 1..1 etc, generated from is_mandatory.
	 *
	 * @return
	 */
	public MultiplicityInterval existence() {
		MultiplicityInterval result;
		if (isMandatory) {
			result = MultiplicityInterval.createMandatory();
		} else {
			result = MultiplicityInterval.createOptional();
		}
		return result;
	}

	/**
	 * Returns the name of this attribute to display in UI.
	 *
	 * @return
	 */
	public String displayName() {
		throw new UnsupportedOperationException("Not implemented yet"); // TODO To be implemented
	}

	public BmmProperty<T> duplicate() {
		return new BmmProperty<>(this);
	}
}
