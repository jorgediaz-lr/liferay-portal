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

/* eslint-disable no-console */

import PropTypes from 'prop-types';
import React from 'react';

import config from '../utilities/config';

export default class ErrorBoundary extends React.Component {
	state = {
		error: '',
		hasError: false,
		info: ''
	};

	static propTypes = {
		children: PropTypes.any
	};

	componentDidCatch(error, info) {
		this.setState({
			error,
			hasError: true,
			info
		});

		if (config.env === 'development') {
			console.log(`Error: ${error}`);
			console.log(`Error Info: ${JSON.stringify(info)}`);
		}
	}

	render() {
		return this.state.hasError ? (
			<div className="alert alert-danger" role="alert">
				{Liferay.Language.get('your-component-failed-to-render')}
			</div>
		) : (
			this.props.children
		);
	}
}
