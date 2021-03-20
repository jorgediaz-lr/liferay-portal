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

import capitalize from 'lodash.capitalize';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

import Purchases from './Purchases';
import SelectAccount from './SelectAccount';

function GeneralInformation({
	accountKey = '',
	accountName = '',
	licensableProducts = [],
	purchasedProducts,
	redirect,
	selectAccountActionURL,
	selectAccountRenderURL
}) {
	const [selectedProduct, setSelectedProduct] = useState(null);
	const [selectedType, setSelectedType] = useState('');
	const [selectedVersion, setSelectedVersion] = useState('');

	function getAvailableTypes() {
		if (selectedProduct && selectedVersion) {
			return selectedProduct.productVersions[selectedVersion];
		}
	}

	function getAvailableVersions() {
		if (selectedProduct) {
			return Object.keys(selectedProduct.productVersions);
		}
	}

	function handleProductOnChange(event) {
		const selectedProductKey = event.target.value;

		setSelectedProduct(
			licensableProducts.find(
				product => product.productKey === selectedProductKey
			)
		);
	}

	function handleTypeOnChange(event) {
		setSelectedType(event.target.value);
	}

	function handleVersionOnChange(event) {
		setSelectedVersion(event.target.value);
	}

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
								accountKey={accountKey}
								accountName={accountName}
								actionURL={selectAccountActionURL}
								dialogURL={selectAccountRenderURL}
							/>
						</div>
					</div>

					<div className="row">
						<div className="col-md-6 form-group">
							<label htmlFor="product">
								{Liferay.Language.get('product')}
							</label>

							<select
								className="form-control"
								disabled={!licensableProducts.length}
								id="product"
								onChange={handleProductOnChange}
							>
								{!!licensableProducts.length && (
									<>
										<option value=""></option>
										{licensableProducts.map(product => (
											<option
												key={product.productKey}
												value={product.productKey}
											>
												{product.productName}
											</option>
										))}
									</>
								)}
							</select>
						</div>
					</div>

					<div className="row">
						<div className="col-md-6 form-group">
							<label htmlFor="version">
								{Liferay.Language.get('version')}
							</label>

							<select
								className="form-control"
								disabled={selectedProduct === null}
								id="version"
								onChange={handleVersionOnChange}
							>
								{!!selectedProduct && (
									<>
										<option value=""></option>
										{!!getAvailableVersions() &&
											getAvailableVersions().map(
												version => (
													<option
														key={version}
														value={version}
													>
														{version}
													</option>
												)
											)}
									</>
								)}
							</select>
						</div>

						<div className="col-md-6 form-group">
							<label htmlFor="type">
								{Liferay.Language.get('type')}
							</label>

							<select
								className="form-control"
								disabled={!selectedVersion}
								id="type"
								onChange={handleTypeOnChange}
							>
								{!!selectedVersion && (
									<>
										<option value=""></option>
										{!!getAvailableTypes() &&
											getAvailableTypes().map(type => (
												<option
													key={type.licenseEntryId}
													value={type.licenseEntryId}
												>
													{`${
														type.licenseEntryName
													} (${capitalize(
														type.licenseEntryType
													)})`}
												</option>
											))}
									</>
								)}
							</select>
						</div>
					</div>
				</div>

				{!!selectedType && <Purchases purchased={purchasedProducts} />}

				<a className="btn btn-secondary" href={redirect}>
					{Liferay.Language.get('cancel')}
				</a>
			</div>
		</>
	);
}

GeneralInformation.propTypes = {
	accountKey: PropTypes.string,
	accountName: PropTypes.string,
	licensableProducts: PropTypes.arrayOf(
		PropTypes.shape({
			productKey: PropTypes.string,
			productName: PropTypes.string,
			productVersions: PropTypes.shape({
				[PropTypes.string]: PropTypes.arrayOf(
					PropTypes.shape({
						licenseEntryId: PropTypes.number,
						licenseEntryName: PropTypes.string,
						licenseEntryType: PropTypes.string
					})
				)
			})
		})
	),
	purchasedProducts: PropTypes.array,
	redirect: PropTypes.string.isRequired,
	selectAccountActionURL: PropTypes.string.isRequired,
	selectAccountRenderURL: PropTypes.string.isRequired
};

export default GeneralInformation;
