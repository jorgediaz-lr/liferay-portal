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
import React, {useState} from 'react';

import {NAMESPACE} from '../../../utilities/constants';
import {
	formatFilterValue,
	getFilterDisplayName
} from '../../../utilities/searchFilters';
import AdvancedSearch from './AdvancedSearch';

function Search({licenseHomeURL = ''}) {
	const [keywords, setKeywords] = useState(getSearchParameter());
	const [showAdvancedSearch, setShowAdvancedSearch] = useState(false);

	function buildSearchResultsURL() {
		return `${licenseHomeURL}&${NAMESPACE}licenseSearchKeywords=${keywords}`;
	}

	function formatPlaceholder(filters) {
		return Object.entries(filters)
			.filter(([key]) => getFilterDisplayName(key))
			.map(
				([key, value]) =>
					getFilterDisplayName(key) + ': ' + formatFilterValue(value)
			)
			.join(', ');
	}

	function getSearchParameter() {
		const searchParams = new URLSearchParams(window.location.search);

		return searchParams.has(`${NAMESPACE}licenseSearchKeywords`)
			? searchParams.get(`${NAMESPACE}licenseSearchKeywords`)
			: '';
	}

	function getSearchPlaceholder() {
		const searchParams = new URLSearchParams(window.location.search);

		const searchFilters = {};

		// Suppress eslint false alarm for unused var
		/* eslint-disable no-unused-vars */

		// Project has no IE11 constraint, prefer to use for...of loop
		/* eslint-disable-next-line no-for-of-loops/no-for-of-loops */
		for (const [key, value] of searchParams.entries()) {
			if (validateParameterNames(key) && value) {
				searchFilters[key.replace(NAMESPACE, '')] = value;
			}
		}
		/* eslint-enable no-unused-vars */

		if (formatPlaceholder(searchFilters)) {
			return formatPlaceholder(searchFilters);
		}
		else {
			return Liferay.Language.get('search-licenses');
		}
	}

	function handleClickOutside() {
		// TODO
	}

	function handleOnChange(event) {
		setKeywords(event.target.value);
	}

	function handleOnKeyDown(event) {
		if (event.keyCode === 13) {
			window.location.assign(buildSearchResultsURL());
		}
	}

	function handleOnToggle() {
		const newState = !showAdvancedSearch;

		setShowAdvancedSearch(newState);

		const advancedSearchBtn = document.getElementById(
			'licenseAdvancedSearchBtn'
		);

		if (advancedSearchBtn) {
			advancedSearchBtn.setAttribute('aria-expanded', newState);

			const ariaLabel = newState
				? Liferay.Language.get('close-advanced-search')
				: Liferay.Language.get('open-advanced-search');

			advancedSearchBtn.setAttribute('aria-label', ariaLabel);
		}
	}

	function validateParameterNames(name) {
		return (
			name.startsWith(NAMESPACE) &&
			!name.endsWith('advancedSearch') &&
			!name.endsWith('andOperator') &&
			!name.endsWith('cur') &&
			!name.endsWith('delta')
		);
	}

	return (
		<>
			<div className="input-group">
				<div className="input-group-item">
					<input
						className="form-control search-input"
						disabled={showAdvancedSearch}
						onChange={handleOnChange}
						onKeyDown={handleOnKeyDown}
						placeholder={getSearchPlaceholder()}
						type=""
						value={keywords}
					/>

					<div className="advanced-search-trigger">
						<button
							aria-controls="advancedSearch"
							aria-expanded="false"
							aria-label={Liferay.Language.get(
								'open-advanced-search'
							)}
							className="advanced-search-btn btn btn-monospaced btn-sm"
							id="licenseAdvancedSearchBtn"
							onClick={handleOnToggle}
						>
							<svg
								aria-label={Liferay.Language.get(
									'advanced-search-icon'
								)}
								className="lexicon-icon lexicon-icon-advanced-search"
								role="image"
							>
								<use xlinkHref="#caret-bottom" />
							</svg>
						</button>
					</div>

					<a
						className="btn btn-default search-btn"
						href={buildSearchResultsURL()}
						role="button"
					>
						<svg
							aria-hidden="true"
							aria-label={Liferay.Language.get('search-icon')}
							className="lexicon-icon lexicon-icon-search"
							role="image"
						>
							<use xlinkHref="#search" />
						</svg>
					</a>
				</div>
			</div>

			{showAdvancedSearch && (
				<AdvancedSearch
					clickOutsideCallback={handleClickOutside}
					formAction={licenseHomeURL}
				/>
			)}
		</>
	);
}

Search.propTypes = {
	licenseHomeURL: PropTypes.string.isRequired
};

export default Search;
