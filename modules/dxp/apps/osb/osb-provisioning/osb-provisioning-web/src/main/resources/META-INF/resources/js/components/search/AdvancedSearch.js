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

import React from 'react';

function AdvancedSearch() {
	return (
		<div className="advanced-search-container">
			Advanced Search

			<div className="button-holder button-holder-lg" role="group">
				<button
					className="btn btn-secondary"
					disabled={true}
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
		</div>
	);
}

export default AdvancedSearch;
