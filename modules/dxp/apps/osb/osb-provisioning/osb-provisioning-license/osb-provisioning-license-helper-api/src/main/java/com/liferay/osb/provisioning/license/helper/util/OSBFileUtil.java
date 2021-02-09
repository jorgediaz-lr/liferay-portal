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

package com.liferay.osb.customer.license.internal.util;

import com.liferay.document.library.kernel.store.DLStoreUtil;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Time;

import java.io.File;

/**
 * @author Amos Fong
 * @author Calvin Keum
 */
public class OSBFileUtil {

    public static File createTempFile(String fileName) throws Exception {
        cleanUpDir(new File(_TEMP_DIR), System.currentTimeMillis() - Time.DAY);

        DLStoreUtil.validate(fileName, false);

        return createFile(new File(_TEMP_DIR + fileName));
    }

    protected static void cleanUpDir(File dir, long timeInterval) {
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File[] files = dir.listFiles();

        for (File file : files) {
            if (file.lastModified() < timeInterval) {
                FileUtil.delete(file);
            }
        }
    }

    protected static File createFile(File file) throws Exception {
        file.createNewFile();

        return file;
    }

    private static final String _TEMP_DIR =
        PropsUtil.get(PropsKeys.LIFERAY_HOME) + "/data/" +
            OSBCustomerConstants.TEMP_DIR;

}