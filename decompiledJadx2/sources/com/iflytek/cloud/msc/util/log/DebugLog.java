package com.iflytek.cloud.msc.util.log;

import android.util.Log;
import com.iflytek.msc.MSC;

/* loaded from: classes3.dex */
public class DebugLog {
    public static final boolean DEFAULT_IS_SHOW_LOG = true;
    private static String _TAG = "MscSpeechLog";
    private static LOG_LEVEL sLogLevel = LOG_LEVEL.normal;
    private static boolean sIsShowLog = true;
    private static boolean DEBUG_MODE = false;

    /* loaded from: classes3.dex */
    public enum LOG_LEVEL {
        all,
        detail,
        normal,
        low,
        none
    }

    public static void setTag(String str) {
        _TAG = str;
    }

    public static void LogD(String str, String str2) {
        if (isShowDebugLog()) {
            Log.d(str, str2);
        }
    }

    public static void LogD(String str) {
        LogD(_TAG, str);
    }

    public static void LogI(String str, String str2) {
        if (isShowInfoLog()) {
            Log.i(str, str2);
        }
    }

    public static void LogI(String str) {
        LogI(_TAG, str);
    }

    public static void LogE(String str) {
        LogE(_TAG, str);
    }

    public static void LogE(String str, String str2) {
        if (isShowErrorLog()) {
            Log.e(str, str2);
        }
    }

    public static void LogE(Throwable th) {
        if (!isShowErrorLog() || th == null) {
            return;
        }
        th.printStackTrace();
    }

    public static void LogS(String str) {
        LogS(_TAG, str);
    }

    public static void LogS(String str, String str2) {
        if (isShowSafeLog()) {
            Log.d(str, str2);
        }
    }

    public static void LogS(Throwable th) {
        if (isShowSafeLog()) {
            th.printStackTrace();
        }
    }

    public static void LogW(String str, String str2) {
        if (isShowErrorLog()) {
            Log.w(str, str2);
        }
    }

    public static void LogW(String str) {
        LogW(_TAG, str);
    }

    public static boolean getShowLog() {
        return sIsShowLog;
    }

    public static void setShowLog(boolean z) {
        sIsShowLog = z;
        updateJniLogStatus();
    }

    public static LOG_LEVEL getLogLevel() {
        return sLogLevel;
    }

    public static void setLogLevel(LOG_LEVEL log_level) {
        sLogLevel = log_level;
        updateJniLogStatus();
    }

    private static boolean isShowErrorLog() {
        return getShowLog() && LOG_LEVEL.none != getLogLevel();
    }

    private static boolean isShowDebugLog() {
        return getShowLog() && getLogLevel().ordinal() <= LOG_LEVEL.normal.ordinal();
    }

    private static boolean isShowInfoLog() {
        return getShowLog() && getLogLevel().ordinal() <= LOG_LEVEL.detail.ordinal();
    }

    private static boolean isShowSafeLog() {
        return DEBUG_MODE && getShowLog();
    }

    public static void updateJniLogStatus() {
        try {
            if (MSC.isLoaded()) {
                MSC.DebugLog(getShowLog() && isShowDebugLog());
                MSC.SetLogLevel(sLogLevel.ordinal());
            }
        } catch (Throwable th) {
            LogD("updateJniLogStatus exception: " + th.getLocalizedMessage());
        }
    }
}
