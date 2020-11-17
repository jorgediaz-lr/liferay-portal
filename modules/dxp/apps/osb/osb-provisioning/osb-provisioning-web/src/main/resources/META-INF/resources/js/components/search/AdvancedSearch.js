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

import ClayPanel from '@clayui/panel';
import PropTypes from 'prop-types';
import React, {useEffect, useRef, useState} from 'react';

import {NAMESPACE} from '../../utilities/constants';
import {
	addClickOutsideListener,
	handleClickOutside,
	removeClickOutsideListener
} from '../../utilities/helpers';
import Account from './Account';
import Categorization from './Categorization';
import Dates from './Dates';

function AdvancedSearch({
	activeSLANames,
	countryNames,
	formAction,
	regionNames,
	selectAccountURL,
	selectFirstLineSupportURL,
	selectPartnerURL,
	setShowAdvancedSearch,
	subscriptionStateNames,
	tierNames
}) {
	const [isAndOperator, setIsAndOperator] = useState(true);

	const advancedSearchRef = useRef();

	useEffect(() => {
		const onClickOutside = handleClickOutside(() => {
			setShowAdvancedSearch(false);
		}, advancedSearchRef.current);

		addClickOutsideListener(onClickOutside);

		return () => removeClickOutsideListener(onClickOutside);
	}, [setShowAdvancedSearch]);

	function handleOnCheck() {
		setIsAndOperator(!isAndOperator);
	}

	return (
		<div
			className="advanced-search-container"
			id="advancedSearch"
			ref={advancedSearchRef}
		>
			<form action={formAction} method="get" name="advancedSearch">
				<input
					name="p_p_id"
					type="hidden"
					value="com_liferay_osb_provisioning_web_portlet_AccountsPortlet"
				/>
				<input name="p_p_lifecycle" type="hidden" value="0" />
				<input
					name={`${NAMESPACE}advancedSearch`}
					type="hidden"
					value="true"
				/>

				<div className="form-group search-match">
					<h5 className="form-check-inline">
						{Liferay.Language.get('match')}:
					</h5>

					<div className="form-check form-check-inline">
						<label className="form-check-label">
							<input
								checked={isAndOperator}
								className="form-check-input"
								name={`${NAMESPACE}andOperator`}
								onChange={() => handleOnCheck()}
								type="radio"
								value={true}
							/>
							<span className="form-check-label-text">
								{Liferay.Language.get('all')}
							</span>
						</label>
					</div>

					<div className="form-check form-check-inline">
						<label className="form-check-label">
							<input
								checked={!isAndOperator}
								className="form-check-input"
								name={`${NAMESPACE}andOperator`}
								onChange={() => handleOnCheck()}
								type="radio"
								value={false}
							/>
							<span className="form-check-label-text">
								{Liferay.Language.get('any')}
							</span>
						</label>
					</div>
				</div>

				<ClayPanel
					collapsable
					defaultExpanded={true}
					displayTitle={Liferay.Language.get('account')}
					displayType="secondary"
					showCollapseIcon={true}
				>
					<Account
						countryNames={countryNames}
						selectAccountURL={selectAccountURL}
						selectFirstLineSupportURL={selectFirstLineSupportURL}
						selectPartnerURL={selectPartnerURL}
					/>
				</ClayPanel>

				<ClayPanel
					collapsable
					displayTitle={Liferay.Language.get('categorization')}
					displayType="secondary"
					showCollapseIcon={true}
				>
					<Categorization
						activeSLANames={activeSLANames}
						regionNames={regionNames}
						subscriptionStateNames={subscriptionStateNames}
						tierNames={tierNames}
					/>
				</ClayPanel>

				<ClayPanel
					collapsable
					displayTitle={Liferay.Language.get('dates')}
					displayType="secondary"
					showCollapseIcon={true}
				>
					<Dates />
				</ClayPanel>

				<div className="button-holder button-holder-lg" role="group">
					<button
						className="btn btn-secondary"
						role="button"
						type="reset"
					>
						{Liferay.Language.get('clear')}
					</button>

					<button
						className="btn btn-primary"
						role="button"
						type="submit"
					>
						{Liferay.Language.get('search')}
					</button>
				</div>
			</form>
		</div>
	);
}

AdvancedSearch.propTypes = {
	activeSLANames: PropTypes.array.isRequired,
	countryNames: PropTypes.array.isRequired,
	formAction: PropTypes.string.isRequired,
	regionNames: PropTypes.array.isRequired,
	selectAccountURL: PropTypes.string.isRequired,
	selectFirstLineSupportURL: PropTypes.string.isRequired,
	selectPartnerURL: PropTypes.string.isRequired,
	subscriptionStateNames: PropTypes.array.isRequired,
	tierNames: PropTypes.array.isRequired
};

export default AdvancedSearch;
