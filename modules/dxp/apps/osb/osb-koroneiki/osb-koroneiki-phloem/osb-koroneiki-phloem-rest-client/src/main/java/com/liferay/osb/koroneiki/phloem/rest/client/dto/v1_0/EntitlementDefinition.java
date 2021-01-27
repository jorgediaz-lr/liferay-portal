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
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.EntitlementDefinitionSerDes;

import java.util.Date;
import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public class EntitlementDefinition implements Cloneable {

	public static EntitlementDefinition toDTO(String json) {
		return EntitlementDefinitionSerDes.toDTO(json);
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setDateCreated(
		UnsafeSupplier<Date, Exception> dateCreatedUnsafeSupplier) {

		try {
			dateCreated = dateCreatedUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Date dateCreated;

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public void setDateModified(
		UnsafeSupplier<Date, Exception> dateModifiedUnsafeSupplier) {

		try {
			dateModified = dateModifiedUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Date dateModified;

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public void setDefinition(
		UnsafeSupplier<String, Exception> definitionUnsafeSupplier) {

		try {
			definition = definitionUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String definition;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDescription(
		UnsafeSupplier<String, Exception> descriptionUnsafeSupplier) {

		try {
			description = descriptionUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String description;

	public ExternalLink[] getExternalLinks() {
		return externalLinks;
	}

	public void setExternalLinks(ExternalLink[] externalLinks) {
		this.externalLinks = externalLinks;
	}

	public void setExternalLinks(
		UnsafeSupplier<ExternalLink[], Exception> externalLinksUnsafeSupplier) {

		try {
			externalLinks = externalLinksUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected ExternalLink[] externalLinks;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setKey(UnsafeSupplier<String, Exception> keyUnsafeSupplier) {
		try {
			key = keyUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String key;

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

	public Status getStatus() {
		return status;
	}

	public String getStatusAsString() {
		if (status == null) {
			return null;
		}

		return status.toString();
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setStatus(
		UnsafeSupplier<Status, Exception> statusUnsafeSupplier) {

		try {
			status = statusUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Status status;

	@Override
	public EntitlementDefinition clone() throws CloneNotSupportedException {
		return (EntitlementDefinition)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof EntitlementDefinition)) {
			return false;
		}

		EntitlementDefinition entitlementDefinition =
			(EntitlementDefinition)object;

		return Objects.equals(toString(), entitlementDefinition.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return EntitlementDefinitionSerDes.toJSON(this);
	}

	public static enum Status {

		APPROVED("Approved"), INACTIVE("Inactive");

		public static Status create(String value) {
			for (Status status : values()) {
				if (Objects.equals(status.getValue(), value)) {
					return status;
				}
			}

			return null;
		}

		public String getValue() {
			return _value;
		}

		@Override
		public String toString() {
			return _value;
		}

		private Status(String value) {
			_value = value;
		}

		private final String _value;

	}

}