package me.jessyan.autosize.utils;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes8.dex */
public class LogUtils {
    private static final String TAG = "AndroidAutoSize";
    private static boolean debug;

    private LogUtils() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean z) {
        debug = z;
    }

    /* renamed from: d */
    public static void m3984d(String str) {
        if (debug) {
            Log.d(TAG, str);
        }
    }

    /* renamed from: w */
    public static void m3986w(String str) {
        if (debug) {
            Log.w(TAG, str);
        }
    }

    /* renamed from: e */
    public static void m3985e(String str) {
        if (debug) {
            Log.e(TAG, str);
        }
    }
}
