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
import React from 'react';

import {useGenerateLicense} from '../../hooks/generateLicense';
import {displayInMDYDateFormat, getUTCAdjustedDate} from '../../utilities/date';
import CancelLink from '../CancelLink';

function SpecificDetails({redirect}) {
	const [generateLicense, {updateLicense}] = useGenerateLicense();

	const {
		accountName,
		complimentary,
		description,
		expirationDate,
		licenseEntry,
		licenseKeysGenerated,
		owner,
		product,
		startDate,
		version
	} = generateLicense;

	function formatDate(date) {
		const utcAdjustedDate = getUTCAdjustedDate(date);

		return displayInMDYDateFormat(utcAdjustedDate) || utcAdjustedDate;
	}

	function handleAccountNameChange(event) {
		updateLicense(generateLicense =>
			generateLicense.set('accountName', event.currentTarget)
		);
	}

	function handleComplimentaryChange() {
		updateLicense(generateLicense =>
			generateLicense.update(
				'complimentary',
				complimentary => !complimentary
			)
		);
	}

	function handleDescriptionChange(event) {
		updateLicense(generateLicense =>
			generateLicense.set('description', event.currentTarget)
		);
	}

	function handleDisplayPreviousPage() {
		updateLicense(generateLicense =>
			generateLicense.set('showSpecificDetails', false)
		);
	}

	function handleOwnerChange(event) {
		updateLicense(generateLicense =>
			generateLicense.set('owner', event.currentTarget)
		);
	}

	return (
		<>
			<div className="page-steps">
				<span>{Liferay.Language.get('specific-details')}</span>

				<span>{Liferay.Language.get('step-2-of-2')}</span>
			</div>

			<div className="container-fluid-max-xl generate-license-sheet row">
				<div className="col-md-9 generate-license-container specific-details">
					<div className="specific-details-content">
						<h3>{Liferay.Language.get('specific-details')}</h3>

						<div className="row">
							<div className="col-md-12 form-group">
								<label htmlFor="name">
									{Liferay.Language.get('name')}
								</label>

								<input
									className="form-control"
									name="name"
									onChange={handleAccountNameChange}
									type="text"
									value={accountName}
								/>
							</div>

							<div className="col-md-6 form-group">
								<label htmlFor="owner">
									{Liferay.Language.get('owner')}
								</label>

								<input
									className="form-control"
									name="owner"
									onChange={handleOwnerChange}
									type="text"
									value={owner}
								/>
							</div>

							<div className="col-md-6 form-group">
								<label htmlFor="description">
									{Liferay.Language.get('description')}
								</label>

								<input
									className="form-control"
									name="description"
									onChange={handleDescriptionChange}
									type="text"
									value={description}
								/>
							</div>

							<div className="col-md-12 form-group">
								<label
									className="form-check-label"
									htmlFor="complimentary"
								>
									<input
										aria-checked={complimentary}
										aria-label={Liferay.Language.get(
											'complimentary'
										)}
										checked={complimentary}
										className="form-check-input"
										id="complimentary"
										onChange={handleComplimentaryChange}
										role="checkbox"
										type="checkbox"
									/>
									<span className="form-check-label-text">
										{Liferay.Language.get('complimentary')}{' '}
										<span
											title={Liferay.Language.get(
												'do-not-count-this-license-towards-the-customers-purchase'
											)}
										>
											<svg
												aria-label={Liferay.Language.get(
													'complimentary-tooltip'
												)}
												className="lexicon-icon"
												role="img"
												title={Liferay.Language.get(
													'complimentary-tooltip'
												)}
											>
												<use xlinkHref="#question-circle-full" />
											</svg>
										</span>
									</span>
								</label>
							</div>
						</div>
					</div>

					<div className="button-holder">
						<button
							className="btn btn-secondary"
							onClick={handleDisplayPreviousPage}
							type="button"
						>
							{Liferay.Language.get('previous-step')}
						</button>

						<CancelLink redirect={redirect} />
					</div>
				</div>

				<div className="additional-information col-md-3">
					<div className="specific-details-content">
						<h4>{Liferay.Language.get('general-information')}</h4>

						<dl>
							<div>
								<dt>{Liferay.Language.get('product')}</dt>
								<dd>{product.productName}</dd>
							</div>
							<div>
								<dt>{Liferay.Language.get('version')}</dt>
								<dd>{version}</dd>
							</div>
							<div>
								<dt>{Liferay.Language.get('type')}</dt>
								<dd>
									{capitalize(licenseEntry.licenseEntryType)}
								</dd>
							</div>
						</dl>

						<dl>
							<div>
								<dt>{Liferay.Language.get('start-date')}</dt>
								<dd>{formatDate(startDate)}</dd>
							</div>

							<div>
								<dt>
									{Liferay.Language.get('expiration-date')}
								</dt>
								<dd>{formatDate(expirationDate)}</dd>
							</div>
						</dl>

						<dl>
							<div>
								<dt>
									{Liferay.Language.get(
										'license-keys-generated'
									)}
								</dt>
								<dd>{licenseKeysGenerated}</dd>
							</div>
						</dl>
					</div>
				</div>
			</div>
		</>
	);
}

SpecificDetails.propTypes = {
	redirect: PropTypes.string
};

export default SpecificDetails;
