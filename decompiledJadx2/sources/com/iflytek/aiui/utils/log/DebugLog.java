package com.iflytek.aiui.utils.log;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes.dex */
public class DebugLog {
    private static boolean DEBUG_MODE = true;
    private static String _TAG = "AIUILog";

    public static void LogD(String str) {
        if (DEBUG_MODE) {
            Log.d(_TAG, str);
        }
    }

    public static void LogD(String str, String str2) {
        if (DEBUG_MODE) {
            Log.d(str, str2);
        }
    }

    public static void LogE(String str) {
        if (DEBUG_MODE) {
            Log.e(_TAG, str);
        }
    }

    public static void LogE(String str, String str2) {
        if (DEBUG_MODE) {
            Log.e(str, str2);
        }
    }

    public static void LogE(Throwable th) {
        if (DEBUG_MODE) {
            th.printStackTrace();
        }
    }

    public static void LogS(String str) {
        if (DEBUG_MODE) {
            Log.d(_TAG, str);
        }
    }

    public static void LogS(String str, String str2) {
        if (DEBUG_MODE) {
            Log.d(str, str2);
        }
    }

    public static void setDebugMode(boolean z) {
        DEBUG_MODE = z;
    }

    public static void setTag(String str) {
        _TAG = str;
    }
}
