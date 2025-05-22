package com.iflytek.aiui.pro;

import android.util.Log;
import com.iflytek.aiui.AIUISetting;

/* renamed from: com.iflytek.aiui.pro.y0 */
/* loaded from: classes4.dex */
public class C3651y0 {

    /* renamed from: a */
    private static String f2728a = "AIUILog";

    /* renamed from: b */
    private static boolean f2729b = true;

    /* renamed from: a */
    public static void m1619a(String str) {
        if (f2729b && AIUISetting.isLogPrintable(AIUISetting.LogLevel.debug)) {
            Log.d(f2728a, str);
        }
    }

    /* renamed from: b */
    public static void m1620b(String str, String str2) {
        if (f2729b && AIUISetting.isLogPrintable(AIUISetting.LogLevel.debug)) {
            Log.d(str, str2);
        }
    }

    /* renamed from: c */
    public static void m1621c(String str) {
        Log.e(f2728a, str);
    }

    /* renamed from: d */
    public static void m1622d(String str, String str2) {
        Log.e(str, str2);
    }

    /* renamed from: e */
    public static void m1623e(Throwable th) {
        th.printStackTrace();
    }

    /* renamed from: f */
    public static void m1624f(String str) {
        if (f2729b) {
            Log.d(f2728a, str);
        }
    }

    /* renamed from: g */
    public static void m1625g(String str, String str2) {
        Log.e(str, str2);
    }

    /* renamed from: h */
    public static void m1626h(String str, String str2, Throwable th) {
        Log.e(str, str2, th);
    }

    /* renamed from: i */
    public static void m1627i(String str, String str2) {
        if (f2729b) {
            Log.i(str, str2);
        }
    }

    /* renamed from: j */
    public static void m1628j(boolean z) {
        f2729b = z;
    }

    /* renamed from: k */
    public static void m1629k(String str, String str2) {
        if (f2729b) {
            Log.w(str, str2);
        }
    }
}
