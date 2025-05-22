package com.alibaba.sdk.android.oss.common.utils;

import android.os.Build;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.iflytek.speech.VoiceWakeuperAidl;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class VersionInfoUtils {
    private static String userAgent;

    public static String getVersion() {
        return "2.5.0";
    }

    public static String getUserAgent(String str) {
        if (OSSUtils.isEmptyString(userAgent)) {
            userAgent = "aliyun-sdk-android/" + getVersion() + getSystemInfo();
        }
        if (OSSUtils.isEmptyString(str)) {
            return userAgent;
        }
        return userAgent + "/" + str;
    }

    private static String getSystemInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(System.getProperty("os.name"));
        sb.append("/Android " + Build.VERSION.RELEASE);
        sb.append("/");
        sb.append(Build.MODEL + VoiceWakeuperAidl.PARAMS_SEPARATE + Build.ID);
        sb.append(")");
        String sb2 = sb.toString();
        OSSLog.logDebug("user agent : " + sb2);
        return OSSUtils.isEmptyString(sb2) ? System.getProperty("http.agent").replaceAll("[^\\p{ASCII}]", "?") : sb2;
    }
}
