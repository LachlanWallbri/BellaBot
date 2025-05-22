package com.aliyun.alink.linksdk.tmp.utils;

import android.content.Context;
import com.alibaba.wireless.security.open.SecurityGuardManager;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class SecurityGuardProxy {
    protected static final String TAG = "[Tmp]SecurityGuardProxy";
    protected Context mContext;

    public SecurityGuardProxy(Context context) {
        this.mContext = context;
        try {
            SecurityGuardManager.getInitializer().initialize(context);
        } catch (Exception unused) {
            ALog.m480e(TAG, "SecurityGuardProxy Exception context:" + context);
        } catch (Throwable unused2) {
            ALog.m480e(TAG, "SecurityGuardProxy throwable context:" + context);
        }
    }

    public void removeStringDDpEx(String str) {
        LogCat.m469d(TAG, "removeStringDDpEx key:" + str);
        try {
            SecurityGuardManager.getInstance(this.mContext).getDynamicDataStoreComp().removeStringDDpEx(str, 0);
        } catch (Exception unused) {
            LogCat.m471e(TAG, "removeStringDDpEx SecurityGuardManager Exception key:" + str);
        } catch (Throwable unused2) {
            ALog.m480e(TAG, "removeStringDDpEx SecurityGuardProxy Throwable key:" + str);
        }
    }

    public boolean addStringDDpEx(String str, String str2) {
        LogCat.m469d(TAG, "addStringDDpEx key:" + str);
        try {
            boolean putStringDDpEx = SecurityGuardManager.getInstance(this.mContext).getDynamicDataStoreComp().putStringDDpEx(str, str2, 0);
            LogCat.m469d(TAG, "addStringDDpEx key:" + str + " ret:" + putStringDDpEx);
            return putStringDDpEx;
        } catch (Exception unused) {
            LogCat.m471e(TAG, "addStringDDpEx SecurityGuardManager Exception key:" + str);
            return false;
        } catch (Throwable unused2) {
            LogCat.m471e(TAG, "addStringDDpEx SecurityGuardManager Throwable key:" + str);
            return false;
        }
    }

    public String getStringDDpEx(String str) {
        LogCat.m469d(TAG, "getStringDDpEx key:" + str);
        try {
            String stringDDpEx = SecurityGuardManager.getInstance(this.mContext).getDynamicDataStoreComp().getStringDDpEx(str, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getStringDDpEx key:");
            sb.append(str);
            sb.append(" ret:");
            sb.append(stringDDpEx == null ? "false" : "true");
            LogCat.m469d(TAG, sb.toString());
            return stringDDpEx;
        } catch (Exception unused) {
            LogCat.m471e(TAG, "getStringDDpEx SecurityGuardManager Exception key:" + str);
            return null;
        } catch (Throwable unused2) {
            LogCat.m471e(TAG, "getStringDDpEx SecurityGuardManager throwable key:" + str);
            return null;
        }
    }
}
