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

package com.liferay.osb.provisioning.koroneiki.web.service.internal;

import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.problem.Problem;

/**
 * @author Amos Fong
 */
public class BaseWebService {

	protected void validateResponse(HttpInvoker.HttpResponse httpResponse)
		throws Problem.ProblemException {

		int statusCode = httpResponse.getStatusCode();

		if (statusCode >= 300) {
			Problem problem = null;

			try {
				problem = Problem.toDTO(httpResponse.getContent());
			}
			catch (Exception exception) {
				problem = new Problem();

				problem.setTitle(httpResponse.getContent());
			}

			throw new Problem.ProblemException(problem);
		}
	}

}