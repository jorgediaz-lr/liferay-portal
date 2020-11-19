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

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.Format;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class ProductPurchaseViewDisplay {

	public ProductPurchaseViewDisplay(
		HttpServletRequest httpServletRequest, Account account,
		ProductPurchaseView productPurchaseView) {

		_httpServletRequest = httpServletRequest;
		_account = account;

		_dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"MMM dd, yyyy");
		_product = productPurchaseView.getProduct();

		ProductConsumption[] productConsumptions =
			productPurchaseView.getProductConsumptions();

		if (productConsumptions != null) {
			_provisionedCount = productConsumptions.length;
		}
		else {
			_provisionedCount = 0;
		}

		Date now = new Date();

		_initProductPurchases(productPurchaseView.getProductPurchases(), now);

		if (StringUtil.equalsIgnoreCase(_status, "approved")) {
			if (!_inSupportGap && (_perpetual || _startDate.before(now)) &&
				(_perpetual || _endDate.after(now))) {

				_state = "active";
			}
			else if (_endDate.before(now)) {
				_state = "expired";
			}
			else {
				_state = "future";
			}
		}
		else if (_purchasedCount == 0) {
			_state = StringPool.BLANK;
		}
		else {
			_state = "cancelled";
		}
	}

	public String getAccountKey() {
		return _account.getKey();
	}

	public String getGracePeriod() {
		if (_perpetual || (_originalEndDate == null)) {
			return StringPool.DASH;
		}

		StringBundler sb = new StringBundler(3);

		sb.append(_dateFormat.format(_originalEndDate));
		sb.append(" - ");

		if (_endDate != null) {
			sb.append(_dateFormat.format(_endDate));
		}
		else {
			sb.append(LanguageUtil.get(_httpServletRequest, "perpetual"));
		}

		return sb.toString();
	}

	public String getName() {
		return _product.getName();
	}

	public String getNextTermStartDate() {
		if (_nextTermStartDate != null) {
			return _dateFormat.format(_nextTermStartDate);
		}

		return StringPool.BLANK;
	}

	public String getProductKey() {
		return _product.getKey();
	}

	public String getProvisionedCount() {
		return String.valueOf(_provisionedCount);
	}

	public String getPurchasedCount() {
		return String.valueOf(_purchasedCount);
	}

	public String getSizing() {
		if (_sizing > 0) {
			return String.valueOf(_sizing);
		}

		return StringPool.DASH;
	}

	public String getSizingWithLabel() {
		if (_sizing > 0) {
			return LanguageUtil.get(_httpServletRequest, "instance-size") +
				": " + _sizing;
		}

		return StringPool.BLANK;
	}

	public String getState() {
		if (Validator.isNotNull(_state)) {
			return LanguageUtil.get(_httpServletRequest, _state);
		}

		return StringPool.DASH;
	}

	public String getStateStyle() {
		if (Validator.isNull(_state)) {
			return StringPool.BLANK;
		}

		if (_state.equals("active")) {
			return "label-success";
		}
		else if (_state.equals("expired") || _state.equals("future")) {
			return "label-warning";
		}
		else {
			return "label-danger";
		}
	}

	public String getSupportLife() {
		if (_perpetual) {
			return LanguageUtil.get(_httpServletRequest, "perpetual");
		}

		if (_startDate == null) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(3);

		sb.append(_dateFormat.format(_startDate));
		sb.append(" - ");

		if (_endDate != null) {
			sb.append(_dateFormat.format(_endDate));
		}
		else {
			sb.append(LanguageUtil.get(_httpServletRequest, "perpetual"));
		}

		return sb.toString();
	}

	public String getType() {
		Map<String, String> properties = _product.getProperties();

		if (properties != null) {
			String type = properties.get("type");

			if (Validator.isNotNull(type)) {
				return type;
			}
		}

		return StringPool.BLANK;
	}

	public boolean isInSupportGap() {
		if (_inSupportGap) {
			return true;
		}

		return false;
	}

	private void _initProductPurchases(
		ProductPurchase[] productPurchases, Date now) {

		_inSupportGap = true;

		for (ProductPurchase productPurchase : productPurchases) {
			Date startDate = productPurchase.getStartDate();
			Date originalEndDate = productPurchase.getOriginalEndDate();
			Date endDate = productPurchase.getEndDate();

			boolean approved = false;

			if (productPurchase.getStatus() ==
					ProductPurchase.Status.APPROVED) {

				approved = true;
			}

			if (approved && (startDate == null)) {
				_inSupportGap = false;
				_perpetual = true;
			}

			if (approved && !_perpetual &&
				((_startDate == null) || startDate.before(_startDate))) {

				_startDate = startDate;
			}

			if (approved && !_perpetual &&
				((_originalEndDate == null) ||
				 originalEndDate.after(_originalEndDate))) {

				_originalEndDate = originalEndDate;
			}

			if (approved && !_perpetual &&
				((_endDate == null) || endDate.after(_endDate))) {

				_endDate = endDate;
			}

			if (approved && (startDate != null) && startDate.before(now) &&
				(endDate != null) && endDate.after(now)) {

				_inSupportGap = false;
			}

			if (approved && !_perpetual && _inSupportGap &&
				((_nextTermStartDate == null) ||
				 ((_nextTermStartDate != null) &&
				  startDate.before(_nextTermStartDate))) &&
				startDate.after(now)) {

				_nextTermStartDate = startDate;
			}

			Map<String, String> properties = productPurchase.getProperties();

			if (properties != null) {
				int sizing = GetterUtil.getInteger(properties.get("sizing"));

				if ((sizing > _sizing) &&
					((startDate == null) || startDate.before(now)) &&
					((endDate == null) || endDate.after(now))) {

					_sizing = sizing;
				}
			}

			_purchasedCount += productPurchase.getQuantity();

			if (!StringUtil.equalsIgnoreCase(_status, "approved")) {
				_status = productPurchase.getStatusAsString();
			}
		}

		if (_inSupportGap && (_startDate != null) && _startDate.after(now)) {
			_inSupportGap = false;
		}
	}

	private final Account _account;
	private final Format _dateFormat;
	private Date _endDate;
	private final HttpServletRequest _httpServletRequest;
	private boolean _inSupportGap;
	private Date _nextTermStartDate;
	private Date _originalEndDate;
	private boolean _perpetual;
	private final Product _product;
	private final int _provisionedCount;
	private int _purchasedCount;
	private int _sizing;
	private Date _startDate;
	private final String _state;
	private String _status;

}