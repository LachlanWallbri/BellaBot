package com.iflytek.cloud.thirdparty;

import android.util.Log;

/* renamed from: com.iflytek.cloud.thirdparty.bh */
/* loaded from: classes3.dex */
public class C3723bh {

    /* renamed from: a */
    private static boolean f3186a = false;

    /* renamed from: a */
    public static void m2046a(String str, String str2) {
        if (f3186a) {
            Log.d("iData_" + str, str2);
        }
    }

    /* renamed from: b */
    public static void m2047b(String str, String str2) {
        if (f3186a) {
            Log.w("iData_" + str, str2);
        }
    }

    /* renamed from: c */
    public static void m2048c(String str, String str2) {
        if (f3186a) {
            Log.e("iData_" + str, str2);
        }
    }
}
