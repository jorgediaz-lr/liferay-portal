/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.provisioning.license.exporter;

import com.liferay.osb.customer.license.model.LicenseKey;

import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.Map;

/**
 * @author Amos Fong
 */
public interface LicenseKeyExporter {

    public String getFileName(LicenseKey licenseKey);

    public String toEncodedLicenseFile(LicenseKey licenseKey) throws Exception;

    public File toFile(LicenseKey licenseKey) throws Exception;

    public String toLI(LicenseKey licenseKey) throws IOException;

    public String toXML(LicenseKey licenseKey) throws Exception;

    public String toXML(List<LicenseKey> licenseKeys) throws Exception;

    public String toXML(Map<String, String> properties, String key)
        throws Exception;

}