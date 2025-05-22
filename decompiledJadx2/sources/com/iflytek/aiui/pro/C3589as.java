package com.iflytek.aiui.pro;

import android.util.Log;
import com.iflytek.aiui.AIUISetting;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* renamed from: com.iflytek.aiui.pro.as */
/* loaded from: classes.dex */
public class C3589as {

    /* renamed from: a */
    private static String f2370a = "AIUILog";

    /* renamed from: b */
    private static boolean f2371b = true;

    /* renamed from: a */
    public static void m1058a(String str) {
        if (f2371b && AIUISetting.isLogPrintable(AIUISetting.LogLevel.debug)) {
            Log.d(f2370a, str);
        }
    }

    /* renamed from: a */
    public static void m1059a(String str, String str2) {
        if (f2371b && AIUISetting.isLogPrintable(AIUISetting.LogLevel.debug)) {
            Log.d(str, str2);
        }
    }

    /* renamed from: a */
    public static void m1060a(String str, String str2, Throwable th) {
        if (f2371b) {
            Log.d(str, str2, th);
        }
    }

    /* renamed from: a */
    public static void m1061a(Throwable th) {
        if (f2371b) {
            th.printStackTrace();
        }
    }

    /* renamed from: b */
    public static void m1062b(String str) {
        Log.e(f2370a, str);
    }

    /* renamed from: b */
    public static void m1063b(String str, String str2) {
        Log.e(str, str2);
    }

    /* renamed from: b */
    public static void m1064b(String str, String str2, Throwable th) {
        if (f2371b) {
            Log.w(str, str2, th);
        }
    }

    /* renamed from: c */
    public static void m1065c(String str) {
        if (f2371b) {
            Log.d(f2370a, str);
        }
    }

    /* renamed from: c */
    public static void m1066c(String str, String str2) {
        if (f2371b) {
            Log.i(str, str2);
        }
    }

    /* renamed from: d */
    public static void m1067d(String str, String str2) {
        if (f2371b) {
            Log.w(str, str2);
        }
    }

    /* renamed from: e */
    public static void m1068e(String str, String str2) {
        Log.e(str, str2);
    }
}
