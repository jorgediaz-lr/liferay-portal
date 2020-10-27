/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import Component from 'metal-component';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import './FloatingToolbarLayoutSearchPanelDelegateTemplate.soy';
import {updateRowConfigAction} from '../../../actions/updateRowConfig.es';
import getConnectedComponent from '../../../store/ConnectedComponent.es';
import {CONFIG_KEYS} from '../../../utils/rowConstants';
import templates from './FloatingToolbarLayoutSearchPanel.soy';

/**
 * FloatingToolbarLayoutSearchPanel
 */
class FloatingToolbarLayoutSearchPanel extends Component {
	/**
	 * Handle layout nonIndexable option change
	 * @param {Event} event
	 */
	_handleLayoutNonIndexableOptionChange(event) {
		this._updateRowConfig({
			[CONFIG_KEYS.nonIndexable]: event.target.checked
		});
	}

	/**
	 * Handle layout nonIndexable checkbox mousedown
	 * @param {Event} event
	 */
	_handleLayoutNonIndexableOptionMousedown(event) {
		event.preventDefault();
	}

	/**
	 * Updates row configuration
	 * @param {object} config Row configuration
	 * @private
	 * @review
	 */
	_updateRowConfig(config) {
		this.store.dispatch(updateRowConfigAction(this.itemId, config));
	}
}

/**
 * State definition.
 * @review
 * @static
 * @type {!Object}
 */
FloatingToolbarLayoutSearchPanel.STATE = {
	/**
	 * @default undefined
	 * @memberof FloatingToolbarLayoutSearchPanel
	 * @review
	 * @type {!string}
	 */
	itemId: Config.string().required()
};

const ConnectedFloatingToolbarLayoutSearchPanel = getConnectedComponent(
	FloatingToolbarLayoutSearchPanel,
	['layoutData', 'spritemap']
);

Soy.register(ConnectedFloatingToolbarLayoutSearchPanel, templates);

export {FloatingToolbarLayoutSearchPanel};
export default FloatingToolbarLayoutSearchPanel;
