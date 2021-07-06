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

import ClayTable from '@clayui/table';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

import IconButton from '../IconButton';
import Terms from './Terms';

function ExtensionDetails({licenseKeyId, productName, terms}) {
	const [values, setValues] = useState({
		expirationDate: '',
		licenseKeyId,
		productPurchaseKey: '',
		startDate: ''
	});

	const disableDelete = true; // TODO ISSUP-4038

	function handleUpdate(name, value) {
		const newValue = {};

		newValue[name] = value;

		setValues({...values, ...newValue});
	}

	return (
		<ClayTable.Body id={licenseKeyId}>
			<ClayTable.Row>
				<ClayTable.Cell>{productName}</ClayTable.Cell>
				<ClayTable.Cell>
					<Terms
						terms={terms}
						termSelected={values.productPurchaseKey}
						updateTerms={handleUpdate}
					/>
				</ClayTable.Cell>
				<ClayTable.Cell>start date</ClayTable.Cell>
				<ClayTable.Cell>expiration date</ClayTable.Cell>
				<ClayTable.Cell>
					<button className="btn btn-secondary btn-sm" type="submit">
						{Liferay.Language.get('extend')}
					</button>
				</ClayTable.Cell>
				<ClayTable.Cell>
					<IconButton
						cssClass="btn-icon btn-sm"
						disabled={disableDelete}
						labelName={Liferay.Language.get('delete-license-icon')}
						onClick={() => {
							// TODO ISSUP-4038
						}}
						svgId="#delete-icon"
						title={Liferay.Language.get('delete')}
					/>
				</ClayTable.Cell>
			</ClayTable.Row>
		</ClayTable.Body>
	);
}

ExtensionDetails.propTypes = {
	extensionURL: PropTypes.string.isRequired,
	hasUpdateLicenseDatePermission: PropTypes.bool.isRequired,
	licenseKeyId: PropTypes.string,
	licenseType: PropTypes.string.isRequired,
	productName: PropTypes.string.isRequired,
	terms: PropTypes.arrayOf(
		PropTypes.shape({
			endDate: PropTypes.string,
			perpetual: PropTypes.bool,
			productPurchaseKey: PropTypes.string,
			startDate: PropTypes.string
		})
	)
};

export default ExtensionDetails;
