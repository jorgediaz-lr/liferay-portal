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
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ContactAccountViewSerDes;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public class ContactAccountView implements Cloneable {

	public static ContactAccountView toDTO(String json) {
		return ContactAccountViewSerDes.toDTO(json);
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setAccount(
		UnsafeSupplier<Account, Exception> accountUnsafeSupplier) {

		try {
			account = accountUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Account account;

	public ContactRole[] getCustomerContactRoles() {
		return customerContactRoles;
	}

	public void setCustomerContactRoles(ContactRole[] customerContactRoles) {
		this.customerContactRoles = customerContactRoles;
	}

	public void setCustomerContactRoles(
		UnsafeSupplier<ContactRole[], Exception>
			customerContactRolesUnsafeSupplier) {

		try {
			customerContactRoles = customerContactRolesUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected ContactRole[] customerContactRoles;

	public ContactRole[] getWorkerContactRoles() {
		return workerContactRoles;
	}

	public void setWorkerContactRoles(ContactRole[] workerContactRoles) {
		this.workerContactRoles = workerContactRoles;
	}

	public void setWorkerContactRoles(
		UnsafeSupplier<ContactRole[], Exception>
			workerContactRolesUnsafeSupplier) {

		try {
			workerContactRoles = workerContactRolesUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected ContactRole[] workerContactRoles;

	@Override
	public ContactAccountView clone() throws CloneNotSupportedException {
		return (ContactAccountView)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ContactAccountView)) {
			return false;
		}

		ContactAccountView contactAccountView = (ContactAccountView)object;

		return Objects.equals(toString(), contactAccountView.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return ContactAccountViewSerDes.toJSON(this);
	}

}