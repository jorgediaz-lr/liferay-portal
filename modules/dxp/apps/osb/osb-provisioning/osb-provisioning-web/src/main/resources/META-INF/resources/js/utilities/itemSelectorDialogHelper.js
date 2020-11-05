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

// Blackbox the AUI dependency Liferay Item Selector Dialog

import {NAMESPACE} from '../utilities/constants';

export function itemSelectorDialogSelection({formField, title, url}) {
	const A = AUI();

	if (A) {
		A.use('liferay-item-selector-dialog', A => {
			const itemSelectorDialog = new A.LiferayItemSelectorDialog({
				eventName: 'selectedItemChange',
				on: {
					selectedItemChange: event => {
						const newVal = event.newVal;

						if (newVal) {
							const selectedItems = JSON.parse(newVal);

							const formInput = document.querySelector(
								`input[name = "${NAMESPACE}${formField}"]`
							);

							if (formInput) {
								formInput.value = selectedItems.key;
							}

							const displayInput = document.getElementById(
								formField
							);

							if (displayInput) {
								displayInput.value = selectedItems.name;
							}
						}
					}
				},
				strings: {
					add: Liferay.Language.get('done'),
					cancel: Liferay.Language.get('cancel')
				},
				title,
				url
			});

			itemSelectorDialog.open();
		});
	}
}

export function itemSelectorDialogWrapper({formField, formName, title, url}) {
	const A = AUI();

	if (A) {
		A.use('liferay-item-selector-dialog', A => {
			const itemSelectorDialog = new A.LiferayItemSelectorDialog({
				eventName: 'selectedItemChange',
				on: {
					selectedItemChange: event => {
						const newVal = event.newVal;

						if (newVal) {
							const selectedItems = JSON.parse(newVal);

							Liferay.Util.postForm(document[formName], {
								data: {
									[`${NAMESPACE}${formField}`]: selectedItems.key
								}
							});
						}
					}
				},
				strings: {
					add: Liferay.Language.get('done'),
					cancel: Liferay.Language.get('cancel')
				},
				title,
				url
			});

			itemSelectorDialog.open();
		});
	}
}
