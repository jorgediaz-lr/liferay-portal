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

import Account from './Account';

function AdvancedSearch({countryNames}) {
	return (
		<div className="advanced-search-container" id="advancedSearch">
			<div className="form-group search-match">
				<h5 className="form-check-inline">
					{Liferay.Language.get('match')}:
				</h5>

				<div className="form-check form-check-inline">
					<label className="form-check-label">
						<input
							className="form-check-input"
							id="matchAny"
							name="matchAny"
							type="radio"
							value={Liferay.Language.get('any')}
						/>
						<span className="form-check-label-text">
							{Liferay.Language.get('any')}
						</span>
					</label>
				</div>

				<div className="form-check form-check-inline">
					<label className="form-check-label">
						<input
							className="form-check-input"
							id="matchAll"
							name="matchAll"
							type="radio"
							value={Liferay.Language.get('all')}
						/>
						<span className="form-check-label-text">
							{Liferay.Language.get('all')}
						</span>
					</label>
				</div>
			</div>

			<Account countryNames={countryNames} />

			<div className="button-holder button-holder-lg" role="group">
				<button
					className="btn btn-secondary"
					disabled={true}
					role="button"
					type="reset"
				>
					{Liferay.Language.get('clear')}
				</button>

				<button className="btn btn-primary" role="button" type="submit">
					{Liferay.Language.get('search')}
				</button>
			</div>
		</div>
	);
}

AdvancedSearch.propTypes = {
	countryNames: PropTypes.array.isRequired
};

export default AdvancedSearch;
