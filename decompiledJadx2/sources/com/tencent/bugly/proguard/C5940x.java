package com.tencent.bugly.proguard;

import android.util.Log;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.x */
/* loaded from: classes7.dex */
public final class C5940x {

    /* renamed from: a */
    public static String f8272a = "CrashReport";

    /* renamed from: b */
    public static boolean f8273b = false;

    /* renamed from: c */
    private static String f8274c = "CrashReportInfo";

    /* renamed from: a */
    private static boolean m3816a(int i, String str, Object... objArr) {
        if (!f8273b) {
            return false;
        }
        if (str == null) {
            str = "null";
        } else if (objArr != null && objArr.length != 0) {
            str = String.format(Locale.US, str, objArr);
        }
        if (i == 0) {
            Log.i(f8272a, str);
            return true;
        }
        if (i == 1) {
            Log.d(f8272a, str);
            return true;
        }
        if (i == 2) {
            Log.w(f8272a, str);
            return true;
        }
        if (i == 3) {
            Log.e(f8272a, str);
            return true;
        }
        if (i != 5) {
            return false;
        }
        Log.i(f8274c, str);
        return true;
    }

    /* renamed from: a */
    public static boolean m3818a(String str, Object... objArr) {
        return m3816a(0, str, objArr);
    }

    /* renamed from: a */
    public static boolean m3817a(Class cls, String str, Object... objArr) {
        return m3816a(0, String.format(Locale.US, "[%s] %s", cls.getSimpleName(), str), objArr);
    }

    /* renamed from: b */
    public static boolean m3821b(String str, Object... objArr) {
        return m3816a(5, str, objArr);
    }

    /* renamed from: c */
    public static boolean m3823c(String str, Object... objArr) {
        return m3816a(1, str, objArr);
    }

    /* renamed from: b */
    public static boolean m3820b(Class cls, String str, Object... objArr) {
        return m3816a(1, String.format(Locale.US, "[%s] %s", cls.getSimpleName(), str), objArr);
    }

    /* renamed from: d */
    public static boolean m3824d(String str, Object... objArr) {
        return m3816a(2, str, objArr);
    }

    /* renamed from: a */
    public static boolean m3819a(Throwable th) {
        if (f8273b) {
            return m3816a(2, C5942z.m3857a(th), new Object[0]);
        }
        return false;
    }

    /* renamed from: e */
    public static boolean m3825e(String str, Object... objArr) {
        return m3816a(3, str, objArr);
    }

    /* renamed from: b */
    public static boolean m3822b(Throwable th) {
        if (f8273b) {
            return m3816a(3, C5942z.m3857a(th), new Object[0]);
        }
        return false;
    }
}
