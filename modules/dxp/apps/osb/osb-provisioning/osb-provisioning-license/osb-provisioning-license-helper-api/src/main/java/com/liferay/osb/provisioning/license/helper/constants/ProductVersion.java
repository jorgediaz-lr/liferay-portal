package com.liferay.osb.provisioning.license.helper.constants;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.service.ListTypeServiceUtil;

/**
 * @author Kyle Bischof
 */
public class ProductVersion {

    public static final int PORTAL_VERSION_6_1_10 = 20060;

    public static String getProductVersionLabel(int productVersion) {
        try {
            ListType listType = ListTypeServiceUtil.getListType(productVersion);

            return listType.getName();
        }
        catch (Exception e) {
            return StringPool.BLANK;
        }
    }

}
