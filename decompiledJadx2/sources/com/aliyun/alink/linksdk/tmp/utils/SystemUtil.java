package com.aliyun.alink.linksdk.tmp.utils;

import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class SystemUtil {
    private static final String TAG = "SystemUtil";
    private static Class<?> mClassType;
    private static Method mGetIntMethod;
    private static Method mGetMethod;

    private static void init() {
        try {
            if (mClassType == null) {
                Class<?> cls = Class.forName("android.os.SystemProperties");
                mClassType = cls;
                mGetMethod = cls.getDeclaredMethod(TmpConstant.PROPERTY_IDENTIFIER_GET, String.class);
                mGetIntMethod = mClassType.getDeclaredMethod("getInt", String.class, Integer.TYPE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static String get(String str) {
        init();
        String str2 = null;
        try {
            String str3 = (String) mGetMethod.invoke(mClassType, str);
            try {
                LogCat.m469d(TAG, "get key:" + str + " value:" + str3);
                return str3;
            } catch (Exception e) {
                e = e;
                str2 = str3;
                e.printStackTrace();
                return str2;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public static int getInt(String str, int i) {
        init();
        try {
            Integer num = (Integer) mGetIntMethod.invoke(mClassType, str, Integer.valueOf(i));
            i = num.intValue();
            LogCat.m469d(TAG, "getInt key:" + str + " value:" + num);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        }
    }
}
