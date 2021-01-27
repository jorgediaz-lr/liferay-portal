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
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ContactPermissionSerDes;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public class ContactPermission implements Cloneable {

	public static ContactPermission toDTO(String json) {
		return ContactPermissionSerDes.toDTO(json);
	}

	public Boolean getDelete() {
		return delete;
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

	public void setDelete(
		UnsafeSupplier<Boolean, Exception> deleteUnsafeSupplier) {

		try {
			delete = deleteUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Boolean delete;

	public Boolean getPermissions() {
		return permissions;
	}

	public void setPermissions(Boolean permissions) {
		this.permissions = permissions;
	}

	public void setPermissions(
		UnsafeSupplier<Boolean, Exception> permissionsUnsafeSupplier) {

		try {
			permissions = permissionsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Boolean permissions;

	public String[] getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String[] roleNames) {
		this.roleNames = roleNames;
	}

	public void setRoleNames(
		UnsafeSupplier<String[], Exception> roleNamesUnsafeSupplier) {

		try {
			roleNames = roleNamesUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String[] roleNames;

	public Boolean getUpdate() {
		return update;
	}

	public void setUpdate(Boolean update) {
		this.update = update;
	}

	public void setUpdate(
		UnsafeSupplier<Boolean, Exception> updateUnsafeSupplier) {

		try {
			update = updateUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Boolean update;

	public Boolean getView() {
		return view;
	}

	public void setView(Boolean view) {
		this.view = view;
	}

	public void setView(UnsafeSupplier<Boolean, Exception> viewUnsafeSupplier) {
		try {
			view = viewUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Boolean view;

	@Override
	public ContactPermission clone() throws CloneNotSupportedException {
		return (ContactPermission)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ContactPermission)) {
			return false;
		}

		ContactPermission contactPermission = (ContactPermission)object;

		return Objects.equals(toString(), contactPermission.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return ContactPermissionSerDes.toJSON(this);
	}

}