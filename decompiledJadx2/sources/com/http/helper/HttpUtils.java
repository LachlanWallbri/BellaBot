package com.http.helper;

import com.iflytek.cloud.msc.util.AppInfoUtil;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes.dex */
public class HttpUtils {
    public static HashMap<String, String> getDefaultRequestHeader() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Content-Type", "application/x-www-form-urlencoded");
        return hashMap;
    }

    private static String makeUA() {
        return System.getProperty(AppInfoUtil.OS_VERSION, "JavaUtil UA");
    }

    public static HashMap<String, String> getParamMap(String... strArr) {
        if (strArr == null) {
            return null;
        }
        int length = strArr.length;
        HashMap<String, String> hashMap = new HashMap<>();
        for (int i = 0; i < length; i += 2) {
            int i2 = i + 1;
            if (i2 >= length) {
                break;
            }
            if (!isEmpty(strArr[i])) {
                hashMap.put(strArr[i], strArr[i2]);
            }
        }
        return hashMap;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
