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

import ClayAutocomplete from '@clayui/autocomplete';
import ClayDropDown from '@clayui/drop-down';
import fuzzy from 'fuzzy';
import debounce from 'lodash.debounce';
import PropTypes from 'prop-types';
import React, {useRef, useState} from 'react';

import {NAMESPACE} from '../../utilities/constants';
import {request} from '../../utilities/helpers';
import {
	formatFilterValue,
	getFilterDisplayName
} from '../../utilities/searchFilters';
import AdvancedSearch from './AdvancedSearch';

const MAX_RESULTS = 7;

const AutocompleteItem = React.forwardRef(
	(
		{innerRef, match = '', secondaryValue = '', value, ...otherProps},
		ref
	) => {
		const fuzzyMatch = fuzzy.match(match, value);

		return (
			<ClayDropDown.Item {...otherProps} innerRef={innerRef} ref={ref}>
				<>
					{match && fuzzyMatch ? (
						<div className="main-item">{fuzzyMatch.rendered}</div>
					) : (
						<div className="main-item">{value}</div>
					)}

					{!!secondaryValue && (
						<div className="secondary-information">
							{secondaryValue}
						</div>
					)}
				</>
			</ClayDropDown.Item>
		);
	}
);

AutocompleteItem.propTypes = {
	href: PropTypes.string,
	match: PropTypes.string,
	secondaryValue: PropTypes.string,
	value: PropTypes.string
};

function Search({
	accountsHomeURL = '',
	activeSLANames,
	countryNames,
	regionNames,
	resourceURL,
	selectAccountURL,
	selectFirstLineSupportURL,
	selectPartnerURL,
	subscriptionStateNames,
	tierNames
}) {
	const [error, setError] = useState(false);
	const [keywords, setKeywords] = useState(getSearchParameter());
	const [results, setResults] = useState([]);
	const [showAdvancedSearch, setShowAdvancedSearch] = useState(false);

	const searchRef = useRef();
	const {current: requestSearchResults} = useRef(
		debounce(value => {
			request(resourceURL, {
				autocompleteKeywords: value,
				maxResults: MAX_RESULTS
			})
				.then(({data}) => {
					if (data.length === 0) {
						setError(true);
					}
					else {
						setError(false);
						setResults(data);
					}
				})
				.catch(err => {
					setError(true);

					console.error(err);
				});
		}, 200)
	);

	function buildSearchResultsURL() {
		return `${accountsHomeURL}&${NAMESPACE}accountSearchKeywords=${keywords}`;
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

		return searchParams.has(`${NAMESPACE}accountSearchKeywords`)
			? searchParams.get(`${NAMESPACE}accountSearchKeywords`)
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
			return Liferay.Language.get('search-accounts');
		}
	}

	function handleClickOutside() {
		setShowAdvancedSearch(false);

		handleOnToggle();
	}

	function handleKeyDown(event) {
		if (event.keyCode === 13) {
			window.location.assign(buildSearchResultsURL());
		}
	}

	function handleOnChange(event) {
		setKeywords(event.target.value);

		requestSearchResults(event.target.value);
	}

	function handleOnToggle() {
		const newState = !showAdvancedSearch;

		setShowAdvancedSearch(newState);

		const advancedSearchBtn = document.getElementById('advancedSearchBtn');

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
		<div ref={searchRef}>
			<ClayAutocomplete>
				<ClayAutocomplete.Input
					className="search-input"
					disabled={showAdvancedSearch}
					onChange={handleOnChange}
					onKeyDown={handleKeyDown}
					placeholder={getSearchPlaceholder()}
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
						id="advancedSearchBtn"
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

				{!showAdvancedSearch && (
					<ClayAutocomplete.DropDown active={keywords}>
						{error && (
							<ul className="list-unstyled">
								<ClayDropDown.Item className="disabled">
									{Liferay.Language.get(
										'no-results-were-found'
									)}
								</ClayDropDown.Item>
							</ul>
						)}

						{!error && (
							<>
								<ClayDropDown.ItemList>
									{results.map(result => (
										<AutocompleteItem
											href={`${result.url}&${NAMESPACE}accountSearchKeywords=${keywords}`}
											key={result.key}
											match={keywords}
											secondaryValue={result.code}
											value={result.name}
										/>
									))}
								</ClayDropDown.ItemList>

								{results.length === MAX_RESULTS && (
									<a
										className="all-results dropdown-item"
										href={buildSearchResultsURL()}
									>
										{Liferay.Language.get(
											'see-all-results'
										)}
									</a>
								)}
							</>
						)}
					</ClayAutocomplete.DropDown>
				)}
			</ClayAutocomplete>

			{showAdvancedSearch && (
				<AdvancedSearch
					activeSLANames={activeSLANames}
					clickOutsideCallback={handleClickOutside}
					countryNames={countryNames}
					formAction={accountsHomeURL}
					ref={searchRef}
					regionNames={regionNames}
					selectAccountURL={selectAccountURL}
					selectFirstLineSupportURL={selectFirstLineSupportURL}
					selectPartnerURL={selectPartnerURL}
					subscriptionStateNames={subscriptionStateNames}
					tierNames={tierNames}
				/>
			)}
		</div>
	);
}

Search.propTypes = {
	accountsHomeURL: PropTypes.string.isRequired,
	activeSLANames: PropTypes.array.isRequired,
	countryNames: PropTypes.array.isRequired,
	regionNames: PropTypes.array.isRequired,
	resourceURL: PropTypes.string.isRequired,
	selectAccountURL: PropTypes.string.isRequired,
	selectFirstLineSupportURL: PropTypes.string.isRequired,
	selectPartnerURL: PropTypes.string.isRequired,
	subscriptionStateNames: PropTypes.array.isRequired,
	tierNames: PropTypes.array.isRequired
};

export default Search;
