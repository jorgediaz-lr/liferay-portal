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

import ClayList from '@clayui/list';
import PropTypes from 'prop-types';
import React, {useRef} from 'react';

import {FIELD_TYPE_TEXTAREA} from '../../utilities/constants';
import LiveUpdateableField from './LiveUpdateableField';

function Instructions({
	accountAttachmentURL,
	accountKey,
	fileName,
	instructions,
	updateAccountAttachmentURL,
	updateInstructionsURL
}) {
	function handleUpdateSupportInstructions(instructions) {
		return {
			instructions
		};
	}

	return (
		<ClayList className="instructions">
			<ClayList.Header>
				{Liferay.Language.get('instructions')}
			</ClayList.Header>

			{!!updateAccountAttachmentURL && !!updateInstructionsURL && (
				<>
					<FileUpload
						accountKey={accountKey}
						fieldLabel={Liferay.Language.get('oem-instructions')}
						fieldName="oemInstructions"
						fileName={fileName}
						fileURL={accountAttachmentURL}
						formAction={updateAccountAttachmentURL}
					/>

					<LiveUpdateableField
						fieldLabel={Liferay.Language.get(
							'support-instructions'
						)}
						fieldName="instructions"
						formAction={updateInstructionsURL}
						type={FIELD_TYPE_TEXTAREA}
						updateFormData={handleUpdateSupportInstructions}
						value={instructions}
					/>
				</>
			)}

			{!updateAccountAttachmentURL && !updateInstructionsURL && (
				<ClayList.Item flex>
					<div className="detail-field list-group-text text-muted">
						{Liferay.Language.get('support-project-does-not-exist')}
					</div>
				</ClayList.Item>
			)}
		</ClayList>
	);
}

Instructions.propTypes = {
	accountAttachmentURL: PropTypes.string,
	accountKey: PropTypes.string,
	fileName: PropTypes.string,
	instructions: PropTypes.string,
	updateAccountAttachmentURL: PropTypes.string,
	updateInstructionsURL: PropTypes.string
};

function FileUpload({
	accountKey,
	fieldLabel,
	fieldName,
	fileName,
	fileURL,
	formAction
}) {
	const formRef = useRef();

	function handleChange() {
		formRef.current.submit();
	}

	return (
		<ClayList.Item flex>
			<div className="detail-field">
				<ClayList.ItemTitle>{fieldLabel}</ClayList.ItemTitle>

				{!!fileName && (
					<a
						className="account-attachment"
						href={fileURL}
						target="_blank"
					>
						{fileName}
					</a>
				)}

				<form
					action={formAction}
					encType="multipart/form-data"
					method="post"
					ref={formRef}
				>
					<input
						name="koroneikiAccountKey"
						type="hidden"
						value={accountKey}
					/>

					<label
						aria-label={fieldName}
						className="form-control-label"
						htmlFor={fieldName}
					>
						<input
							className="form-control"
							id={fieldName}
							name={fieldName}
							onChange={handleChange}
							type="file"
						/>
					</label>
				</form>
			</div>
		</ClayList.Item>
	);
}

export default Instructions;
