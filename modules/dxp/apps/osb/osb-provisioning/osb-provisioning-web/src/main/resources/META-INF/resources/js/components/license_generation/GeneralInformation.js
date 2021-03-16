/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import PropTypes from 'prop-types';
import React from 'react';

import SelectAccount from './SelectAccount';

function GeneralInformation({
	redirect,
	selectAccountActionURL,
	selectAccountRenderURL
}) {
	return (
		<>
			<div className="page-steps">
				<span>{Liferay.Language.get('general-information')}</span>

				<span>{Liferay.Language.get('step-1-of-2')}</span>
			</div>

			<div className="container-fluid-max-xl generate-license-sheet sheet">
				<div className="generate-license-container">
					<h3>{Liferay.Language.get('general-information')}</h3>

					<div className="row">
						<div className="col-md-6 form-group">
							<h5 className="form-check-inline">
								{Liferay.Language.get('account')}
							</h5>

							<SelectAccount
								actionURL={selectAccountActionURL}
								dialogURL={selectAccountRenderURL}
							/>
						</div>
					</div>

					<a className="btn btn-secondary" href={redirect}>
						{Liferay.Language.get('cancel')}
					</a>
				</div>
			</div>
		</>
	);
}

GeneralInformation.propTypes = {
	redirect: PropTypes.string.isRequired,
	selectAccountActionURL: PropTypes.string.isRequired,
	selectAccountRenderURL: PropTypes.string.isRequired
};

export default GeneralInformation;
