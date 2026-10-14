/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/* eslint-disable no-console */

import {request} from '@playwright/test';

import {ApiHelpers} from '../helpers/ApiHelpers';
import {liferayConfig} from '../liferay.config';

const BATCH_SIZE = 1000;

const COUNT = Number(process.env.POPULATE_COUNT || 100);

const OBJECT_DEFINITION_NAME = 'PopulateTicket';

const OBJECT_PATH = process.env.POPULATE_OBJECT_PATH || '/o/c/populatetickets';

const SITE_ID = process.env.LIFERAY_SITE_ID || 'Guest';

async function addObjectDefinition(apiHelpers: ApiHelpers) {
	const objectDefinition =
		await apiHelpers.objectAdmin.getObjectDefinitionByName(
			OBJECT_DEFINITION_NAME
		);

	if (objectDefinition) {
		return;
	}

	await apiHelpers.objectAdmin.postRandomObjectDefinition({
		objectDefinitionExternalReferenceCode: OBJECT_DEFINITION_NAME,
		objectFields: [
			{
				DBType: 'String',
				businessType: 'Text',
				externalReferenceCode: 'title',
				indexed: true,
				indexedAsKeyword: false,
				indexedLanguageId: '',
				label: {en_US: 'Title'},
				localized: false,
				name: 'title',
				required: false,
				type: 'String',
			},
		],
		status: {code: 0},
		titleObjectFieldName: 'title',
	});
}

async function getAuthorization() {
	if (!process.env.LIFERAY_CLIENT_ID) {
		const credentials = `${process.env.LIFERAY_USER || 'test@liferay.com'}:${liferayConfig.environment.password}`;

		return `Basic ${btoa(credentials)}`;
	}

	const apiRequestContext = await request.newContext({
		baseURL: liferayConfig.environment.baseUrl,
	});

	const response = await apiRequestContext.post('/o/oauth2/token', {
		form: {
			client_id: process.env.LIFERAY_CLIENT_ID,
			client_secret: process.env.LIFERAY_CLIENT_SECRET!,
			grant_type: 'client_credentials',
		},
	});

	const {access_token} = await response.json();

	await apiRequestContext.dispose();

	return `Bearer ${access_token}`;
}

async function submit(apiHelpers: ApiHelpers, items: object[], path: string) {
	for (let i = 0; i < items.length; i += BATCH_SIZE) {
		const importTask = await apiHelpers.post(
			`${apiHelpers.baseUrl}${path}/batch?createStrategy=UPSERT`,
			{data: items.slice(i, i + BATCH_SIZE)}
		);

		await waitForImportTask(apiHelpers, importTask.id, path);
	}
}

function times<T>(count: number, fn: (i: number) => T): T[] {
	return Array.from({length: count}, (_, i) => fn(i));
}

async function waitForImportTask(
	apiHelpers: ApiHelpers,
	importTaskId: number,
	label: string
) {
	while (true) {
		const importTask =
			await apiHelpers.headlessBatchEngine.getImportTask(importTaskId);

		if (importTask.executeStatus === 'COMPLETED') {
			console.log(
				`${label}: ${importTask.processedItemsCount}/${importTask.totalItemsCount}`
			);

			return;
		}

		if (importTask.executeStatus === 'FAILED') {
			throw new Error(`${label}: ${JSON.stringify(importTask)}`);
		}

		await new Promise((resolve) => setTimeout(resolve, 1000));
	}
}

async function main() {
	const apiHelpers = await ApiHelpers.createWithoutBrowser(
		await getAuthorization()
	);

	try {
		await submit(
			apiHelpers,
			times(COUNT, (i) => ({
				alternateName: `populate${i}`,
				emailAddress: `populate${i}@liferay.com`,
				externalReferenceCode: `POPULATE_USER_${i}`,
				familyName: 'Populate',
				givenName: `User${i}`,
			})),
			'headless-admin-user/v1.0/user-accounts'
		);

		await submit(
			apiHelpers,
			times(COUNT, (i) => ({
				articleBody: `<p>Populated blog posting ${i}</p>`,
				externalReferenceCode: `POPULATE_BLOG_${i}`,
				headline: `Populated blog posting ${i}`,
			})),
			`headless-delivery/v1.0/sites/${SITE_ID}/blog-postings`
		);

		await addObjectDefinition(apiHelpers);

		await submit(
			apiHelpers,
			times(COUNT, (i) => ({
				externalReferenceCode: `POPULATE_OBJECT_ENTRY_${i}`,
				title: `Populated object entry ${i}`,
			})),
			OBJECT_PATH.replace(/^\/o\//, '')
		);
	}
	finally {
		await apiHelpers.dispose();
	}
}

main().catch((error) => {
	console.error(error);

	process.exit(1);
});
