package org.greenrobot.greendao;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class DaoLog {
    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    private static final String TAG = "greenDAO";
    public static final int VERBOSE = 2;
    public static final int WARN = 5;

    public static boolean isLoggable(int i) {
        return Log.isLoggable(TAG, i);
    }

    public static String getStackTraceString(Throwable th) {
        return Log.getStackTraceString(th);
    }

    public static int println(int i, String str) {
        return Log.println(i, TAG, str);
    }

    /* renamed from: v */
    public static int m4142v(String str) {
        return Log.v(TAG, str);
    }

    /* renamed from: v */
    public static int m4143v(String str, Throwable th) {
        return Log.v(TAG, str, th);
    }

    /* renamed from: d */
    public static int m4136d(String str) {
        return Log.d(TAG, str);
    }

    /* renamed from: d */
    public static int m4137d(String str, Throwable th) {
        return Log.d(TAG, str, th);
    }

    /* renamed from: i */
    public static int m4140i(String str) {
        return Log.i(TAG, str);
    }

    /* renamed from: i */
    public static int m4141i(String str, Throwable th) {
        return Log.i(TAG, str, th);
    }

    /* renamed from: w */
    public static int m4144w(String str) {
        return Log.w(TAG, str);
    }

    /* renamed from: w */
    public static int m4145w(String str, Throwable th) {
        return Log.w(TAG, str, th);
    }

    /* renamed from: w */
    public static int m4146w(Throwable th) {
        return Log.w(TAG, th);
    }

    /* renamed from: e */
    public static int m4138e(String str) {
        return Log.w(TAG, str);
    }

    /* renamed from: e */
    public static int m4139e(String str, Throwable th) {
        return Log.e(TAG, str, th);
    }
}
