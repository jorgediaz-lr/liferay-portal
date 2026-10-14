/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/* eslint-disable no-console */

import {APIRequestContext, request} from '@playwright/test';

import {liferayConfig} from '../liferay.config';

const BATCH_SIZE = 1000;

const COUNT = Number(process.env.POPULATE_COUNT || 100);

const OBJECT_DEFINITION_NAME = 'PopulateTicket';

const OBJECT_PATH = process.env.POPULATE_OBJECT_PATH || '/o/c/populatetickets';

const SITE_ID = process.env.LIFERAY_SITE_ID || 'Guest';

async function addObjectDefinition(apiRequestContext: APIRequestContext) {
	const getResponse = await apiRequestContext.get(
		'/o/object-admin/v1.0/object-definitions',
		{params: {filter: `name eq '${OBJECT_DEFINITION_NAME}'`}}
	);

	const {items} = await getResponse.json();

	if (items.length) {
		return;
	}

	const postResponse = await apiRequestContext.post(
		'/o/object-admin/v1.0/object-definitions',
		{
			data: {
				active: true,
				externalReferenceCode: OBJECT_DEFINITION_NAME,
				label: {en_US: OBJECT_DEFINITION_NAME},
				name: OBJECT_DEFINITION_NAME,
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
				pluralLabel: {en_US: OBJECT_DEFINITION_NAME},
				scope: 'company',
				status: {code: 0},
				titleObjectFieldName: 'title',
			},
		}
	);

	if (!postResponse.ok()) {
		throw new Error(
			`${OBJECT_DEFINITION_NAME}: ${postResponse.status()} ${await postResponse.text()}`
		);
	}
}

async function createAPIRequestContext(): Promise<APIRequestContext> {
	const baseURL = liferayConfig.environment.baseUrl;

	if (!process.env.LIFERAY_CLIENT_ID) {
		return request.newContext({
			baseURL,
			httpCredentials: {
				password: liferayConfig.environment.password,
				send: 'always',
				username: process.env.LIFERAY_USER || 'test@liferay.com',
			},
		});
	}

	const authAPIRequestContext = await request.newContext({baseURL});

	const response = await authAPIRequestContext.post('/o/oauth2/token', {
		form: {
			client_id: process.env.LIFERAY_CLIENT_ID,
			client_secret: process.env.LIFERAY_CLIENT_SECRET!,
			grant_type: 'client_credentials',
		},
	});

	const {access_token} = await response.json();

	await authAPIRequestContext.dispose();

	return request.newContext({
		baseURL,
		extraHTTPHeaders: {Authorization: `Bearer ${access_token}`},
	});
}

async function submit(
	apiRequestContext: APIRequestContext,
	items: object[],
	path: string
) {
	for (let i = 0; i < items.length; i += BATCH_SIZE) {
		const response = await apiRequestContext.post(`${path}/batch`, {
			data: items.slice(i, i + BATCH_SIZE),
			params: {createStrategy: 'UPSERT'},
		});

		if (!response.ok()) {
			throw new Error(
				`${path}: ${response.status()} ${await response.text()}`
			);
		}

		const importTask = await response.json();

		await waitForImportTask(apiRequestContext, importTask.id, path);
	}
}

function times<T>(count: number, fn: (i: number) => T): T[] {
	return Array.from({length: count}, (_, i) => fn(i));
}

async function waitForImportTask(
	apiRequestContext: APIRequestContext,
	importTaskId: number,
	label: string
) {
	while (true) {
		const response = await apiRequestContext.get(
			`/o/headless-batch-engine/v1.0/import-task/${importTaskId}`
		);

		const importTask = await response.json();

		if (importTask.executeStatus === 'COMPLETED') {
			console.log(
				`${label}: ${importTask.processedItemsCount}/${importTask.totalItemsCount}`
			);

			return;
		}

		if (importTask.executeStatus === 'FAILED') {
			throw new Error(
				`${label}: ${importTask.errorMessage} ${JSON.stringify(importTask.failedItems)}`
			);
		}

		await new Promise((resolve) => setTimeout(resolve, 1000));
	}
}

async function main() {
	const apiRequestContext = await createAPIRequestContext();

	try {
		await submit(
			apiRequestContext,
			times(COUNT, (i) => ({
				alternateName: `populate${i}`,
				emailAddress: `populate${i}@liferay.com`,
				externalReferenceCode: `POPULATE_USER_${i}`,
				familyName: 'Populate',
				givenName: `User${i}`,
			})),
			'/o/headless-admin-user/v1.0/user-accounts'
		);

		await submit(
			apiRequestContext,
			times(COUNT, (i) => ({
				articleBody: `<p>Populated blog posting ${i}</p>`,
				externalReferenceCode: `POPULATE_BLOG_${i}`,
				headline: `Populated blog posting ${i}`,
			})),
			`/o/headless-delivery/v1.0/sites/${SITE_ID}/blog-postings`
		);

		await addObjectDefinition(apiRequestContext);

		await submit(
			apiRequestContext,
			times(COUNT, (i) => ({
				externalReferenceCode: `POPULATE_OBJECT_ENTRY_${i}`,
				title: `Populated object entry ${i}`,
			})),
			OBJECT_PATH
		);
	}
	finally {
		await apiRequestContext.dispose();
	}
}

main().catch((error) => {
	console.error(error);

	process.exit(1);
});
