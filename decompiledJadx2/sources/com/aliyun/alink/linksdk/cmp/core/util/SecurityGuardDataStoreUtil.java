package com.aliyun.alink.linksdk.cmp.core.util;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.wireless.security.open.SecurityGuardManager;
import com.alibaba.wireless.security.open.dynamicdatastore.IDynamicDataStoreComponent;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class SecurityGuardDataStoreUtil {
    private static final String TAG = "SecurityGuardDataStoreUtil";

    public static boolean putString(Context context, String str, String str2) {
        IDynamicDataStoreComponent dynamicDataStoreComp;
        ALog.m479d(TAG, "putString, key = " + str + ", value=" + str2);
        if (context != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                SecurityGuardManager securityGuardManager = SecurityGuardManager.getInstance(context);
                if (securityGuardManager != null && (dynamicDataStoreComp = securityGuardManager.getDynamicDataStoreComp()) != null) {
                    return dynamicDataStoreComp.putStringDDpEx(str, str2, 0);
                }
            } catch (Exception e) {
                ALog.m479d(TAG, "putString(),error, " + e.toString());
            }
        }
        return false;
    }

    public static String getString(Context context, String str) {
        IDynamicDataStoreComponent dynamicDataStoreComp;
        ALog.m479d(TAG, "getString, key = " + str);
        if (context != null && !TextUtils.isEmpty(str)) {
            try {
                SecurityGuardManager securityGuardManager = SecurityGuardManager.getInstance(context);
                if (securityGuardManager != null && (dynamicDataStoreComp = securityGuardManager.getDynamicDataStoreComp()) != null) {
                    return dynamicDataStoreComp.getStringDDpEx(str, 0);
                }
            } catch (Exception e) {
                ALog.m479d(TAG, "getString(),error, " + e.toString());
            }
        }
        return null;
    }

    public static void removeString(Context context, String str) {
        IDynamicDataStoreComponent dynamicDataStoreComp;
        ALog.m479d(TAG, "removeString, key = " + str);
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            SecurityGuardManager securityGuardManager = SecurityGuardManager.getInstance(context);
            if (securityGuardManager == null || (dynamicDataStoreComp = securityGuardManager.getDynamicDataStoreComp()) == null) {
                return;
            }
            dynamicDataStoreComp.removeStringDDpEx(str, 0);
        } catch (Exception e) {
            ALog.m479d(TAG, "removeString(),error, " + e.toString());
        }
    }
}
