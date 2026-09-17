/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PropsValues;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.net.IDN;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author István András Dézsi
 */
public class VirtualHostRegistry {

	public long fetchCompanyId(String hostname) {
		if (!isEnabled() || !CompanyThreadLocal.isDefaultCompany() ||
			Validator.isNull(hostname)) {

			return 0;
		}

		Long companyId = _companyIdsByHostnameMap.get(
			StringUtil.toLowerCase(hostname));

		if ((companyId == null) && hostname.contains("xn--")) {
			companyId = _companyIdsByHostnameMap.get(
				StringUtil.toLowerCase(IDN.toUnicode(hostname)));
		}

		return GetterUtil.getLong(companyId);
	}

	public List<String> getRegisteredHostnames(List<String> hostnames) {
		if (!isEnabled()) {
			return Collections.emptyList();
		}

		return ListUtil.filter(
			hostnames,
			hostname -> _companyIdsByHostnameMap.containsKey(
				StringUtil.toLowerCase(hostname)));
	}

	public boolean isEnabled() {
		return PropsValues.DATABASE_PARTITION_ENABLED;
	}

	public void register(long companyId, String hostname) {
		if (!isEnabled() || Validator.isNull(hostname)) {
			return;
		}

		_companyIdsByHostnameMap.put(
			StringUtil.toLowerCase(hostname), companyId);
	}

	public boolean registerIfAbsent(long companyId, String hostname) {
		if (!isEnabled()) {
			return false;
		}

		Long virtualHostCompanyId = _companyIdsByHostnameMap.putIfAbsent(
			StringUtil.toLowerCase(hostname), companyId);

		if (virtualHostCompanyId == null) {
			return true;
		}

		if (virtualHostCompanyId != companyId) {
			throw new DuplicateVirtualHostnameException(hostname);
		}

		return false;
	}

	public void reset(Map<String, Long> companyIdsByHostnameMap) {
		if (!isEnabled()) {
			return;
		}

		_companyIdsByHostnameMap = new ConcurrentHashMap<>(
			companyIdsByHostnameMap);
	}

	public void unregister(long companyId) {
		if (!isEnabled()) {
			return;
		}

		Collection<Long> companyIds = _companyIdsByHostnameMap.values();

		companyIds.removeIf(curCompanyId -> curCompanyId == companyId);
	}

	public void unregister(String hostname) {
		if (!isEnabled() || Validator.isNull(hostname)) {
			return;
		}

		_companyIdsByHostnameMap.remove(StringUtil.toLowerCase(hostname));
	}

	private volatile Map<String, Long> _companyIdsByHostnameMap =
		new ConcurrentHashMap<>();

}