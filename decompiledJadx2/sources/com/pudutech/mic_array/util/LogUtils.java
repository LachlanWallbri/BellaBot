package com.pudutech.mic_array.util;

import android.util.Log;

/* loaded from: classes5.dex */
public class LogUtils {
    public static boolean DEBUG = true;
    public static final String TAG = "LogUtils";

    /* renamed from: i */
    public static void m3294i(String str, String str2) {
        if (DEBUG) {
            Log.i(TAG, str + "-->" + str2);
        }
    }

    /* renamed from: i */
    public static void m3293i(String str) {
        if (DEBUG) {
            Log.i(TAG, "-->" + str);
        }
    }

    /* renamed from: d */
    public static void m3290d(String str, String str2) {
        Log.d(TAG, str + "-->" + str2);
    }

    /* renamed from: d */
    public static void m3289d(String str) {
        Log.d(TAG, "-->" + str);
    }

    /* renamed from: v */
    public static void m3296v(String str, String str2) {
        if (DEBUG) {
            Log.v(TAG, str + "-->" + str2);
        }
    }

    /* renamed from: v */
    public static void m3295v(String str) {
        if (DEBUG) {
            Log.v(TAG, "-->" + str);
        }
    }

    /* renamed from: e */
    public static void m3292e(String str, String str2) {
        Log.e(TAG, str + "-->" + str2);
    }

    /* renamed from: e */
    public static void m3291e(String str) {
        Log.e(TAG, "-->" + str);
    }

    public static void printStackTrace(Exception exc) {
        if (DEBUG) {
            exc.printStackTrace();
        }
    }

    /* renamed from: w */
    public static void m3297w(String str, String str2) {
        Log.w(TAG, str + "-->" + str2);
    }
}
