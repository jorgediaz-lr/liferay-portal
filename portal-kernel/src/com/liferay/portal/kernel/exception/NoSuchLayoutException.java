/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.kernel.exception;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchLayoutException extends NoSuchModelException {

	public NoSuchLayoutException() {
		if (_log.isDebugEnabled()) {
			_log.debug("new NoSuchLayoutException", new Exception());
		}
	}

	public NoSuchLayoutException(String msg) {
		super(msg);

		if (_log.isDebugEnabled()) {
			_log.debug("new NoSuchLayoutException " + msg, new Exception());
		}
	}

	public NoSuchLayoutException(String msg, Throwable throwable) {
		super(msg, throwable);

		if (_log.isDebugEnabled()) {
			_log.debug(
				"new NoSuchLayoutException " + msg, new Exception(throwable));
		}
	}

	public NoSuchLayoutException(Throwable throwable) {
		super(throwable);

		if (_log.isDebugEnabled()) {
			_log.debug("new NoSuchLayoutException", new Exception(throwable));
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		NoSuchLayoutException.class);

}