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
import React, {useRef, useState} from 'react';

import {useClickOutside} from '../../../hooks/useClickOutside';
import {NAMESPACE} from '../../../utilities/constants';
import CheckboxGroups from '../CheckboxGroups';
import LicenseDetails from './LicenseDetails';

const AdvancedSearch = React.forwardRef(
	(
		{
			clickOutsideCallback,
			formAction,
			licenseTypes,
			productNames,
			productVersions
		},
		ref
	) => {
		const [isAndOperator, setIsAndOperator] = useState(true);

		const formRef = useRef();

		useClickOutside(clickOutsideCallback, ref);

		function handleOnCheck() {
			setIsAndOperator(!isAndOperator);
		}

		function handleOnKeyDown(event) {
			if (event.keyCode === 13) {
				formRef.current.submit();
			}
		}

		return (
			<div className="advanced-search-container" id="advancedSearch">
				<form
					action={formAction}
					method="get"
					name="advancedSearch"
					onKeyDown={handleOnKeyDown}
					ref={formRef}
				>
					<input
						name="p_p_id"
						type="hidden"
						value="com_liferay_osb_provisioning_web_portlet_LicensesPortlet"
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
						displayTitle={Liferay.Language.get('general-details')}
						displayType="secondary"
						showCollapseIcon={true}
					>
						<LicenseDetails />
					</ClayPanel>

					{licenseTypes.length && (
						<ClayPanel
							collapsable
							displayTitle={Liferay.Language.get('license-type')}
							displayType="secondary"
							showCollapseIcon={true}
						>
							<div className="panel-body">
								<CheckboxGroups
									fieldValues={licenseTypes}
									inputName="types"
								/>
							</div>
						</ClayPanel>
					)}

					{productNames.length && (
						<ClayPanel
							collapsable
							displayTitle={Liferay.Language.get('product')}
							displayType="secondary"
							showCollapseIcon={true}
						>
							<div className="panel-body">
								<CheckboxGroups
									fieldValues={productNames}
									inputName="productNames"
								/>
							</div>
						</ClayPanel>
					)}

					{productVersions.length && (
						<ClayPanel
							collapsable
							displayTitle={Liferay.Language.get(
								'product-version'
							)}
							displayType="secondary"
							showCollapseIcon={true}
						>
							<div className="panel-body">
								<CheckboxGroups
									fieldValues={productVersions}
									inputName="productVersions"
								/>
							</div>
						</ClayPanel>
					)}

					<ClayPanel
						collapsable
						displayTitle={Liferay.Language.get('dates')}
						displayType="secondary"
						showCollapseIcon={true}
					>
						{/* TODO */}
					</ClayPanel>

					<div
						className="button-holder button-holder-lg"
						role="group"
					>
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
);

AdvancedSearch.propTypes = {
	clickOutsideCallback: PropTypes.func.isRequired,
	formAction: PropTypes.string.isRequired,
	licenseTypes: PropTypes.arrayOf(
		PropTypes.shape({
			label: PropTypes.string,
			value: PropTypes.oneOfType[(PropTypes.number, PropTypes.string)]
		})
	)
};

export default AdvancedSearch;
