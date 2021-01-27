/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.function.UnsafeSupplier;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.EntitlementSerDes;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public class Entitlement implements Cloneable {

	public static Entitlement toDTO(String json) {
		return EntitlementSerDes.toDTO(json);
	}

	public String getEntitlementDefinitionKey() {
		return entitlementDefinitionKey;
	}

	public void setEntitlementDefinitionKey(String entitlementDefinitionKey) {
		this.entitlementDefinitionKey = entitlementDefinitionKey;
	}

	public void setEntitlementDefinitionKey(
		UnsafeSupplier<String, Exception>
			entitlementDefinitionKeyUnsafeSupplier) {

		try {
			entitlementDefinitionKey =
				entitlementDefinitionKeyUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String entitlementDefinitionKey;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setName(UnsafeSupplier<String, Exception> nameUnsafeSupplier) {
		try {
			name = nameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String name;

	@Override
	public Entitlement clone() throws CloneNotSupportedException {
		return (Entitlement)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Entitlement)) {
			return false;
		}

		Entitlement entitlement = (Entitlement)object;

		return Objects.equals(toString(), entitlement.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return EntitlementSerDes.toJSON(this);
	}

}