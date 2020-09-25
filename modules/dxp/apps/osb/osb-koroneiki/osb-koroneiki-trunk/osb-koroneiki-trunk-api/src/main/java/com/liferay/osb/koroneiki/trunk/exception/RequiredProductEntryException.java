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

package com.liferay.osb.koroneiki.trunk.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Kyle Bischof
 */
public class RequiredProductEntryException extends PortalException {

	public RequiredProductEntryException() {
	}

	public RequiredProductEntryException(String msg) {
		super(msg);
	}

	public RequiredProductEntryException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public RequiredProductEntryException(Throwable cause) {
		super(cause);
	}

	public static class MustNotDeleteProductEntryReferencedByProductConsumption
		extends RequiredProductEntryException {

		public MustNotDeleteProductEntryReferencedByProductConsumption(
			long productEntryId) {

			super(
				String.format(
					"Product %s cannot be deleted because it is referenced " +
						"by one or more product consumptions",
					productEntryId));
		}

	}

	public static class MustNotDeleteProductEntryReferencedByProductPurchase
		extends RequiredProductEntryException {

		public MustNotDeleteProductEntryReferencedByProductPurchase(
			long productEntryId) {

			super(
				String.format(
					"Product %s cannot be deleted because it is referenced " +
						"by one or more product purchases",
					productEntryId));
		}

	}

}