package com.iflytek.aiui;

import android.os.Environment;
import com.iflytek.aiui.jni.AIUI;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public final class AIUISetting {

    /* renamed from: a */
    private static String f2134a = Environment.getExternalStorageDirectory() + "/AIUI/";

    /* renamed from: b */
    private static String f2135b = "";

    /* renamed from: c */
    private static String f2136c = "";

    /* renamed from: d */
    private static boolean f2137d = false;

    /* renamed from: e */
    private static boolean f2138e = true;

    /* renamed from: f */
    private static LogLevel f2139f = LogLevel.info;

    /* renamed from: g */
    private static LogLevel f2140g = LogLevel.none;

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* loaded from: classes.dex */
    public enum LogLevel {
        info,
        debug,
        warn,
        error,
        none
    }

    private AIUISetting() {
    }

    public static String getAIUIDir() {
        return f2134a;
    }

    public static String getDataLogDir() {
        return f2135b;
    }

    public static boolean getLocationEnable() {
        return f2138e;
    }

    public static LogLevel getLogLevel() {
        return f2139f;
    }

    public static String getMscCfg() {
        return f2136c;
    }

    public static LogLevel getNetLogLevel() {
        return f2140g;
    }

    public static boolean getSaveDataLog() {
        return f2137d;
    }

    public static boolean isLogPrintable(LogLevel logLevel) {
        return logLevel.ordinal() >= f2139f.ordinal();
    }

    public static void setAIUIDir(String str) {
        f2134a = str;
    }

    public static void setDataLogDir(String str) {
        f2135b = str;
    }

    public static void setLibName(String str) {
        AIUI.m867a(str);
    }

    public static void setLibPath(String str) {
        AIUI.m870b(str);
    }

    public static void setLocationEnable(boolean z) {
        f2138e = z;
    }

    public static void setLogLevel(LogLevel logLevel) {
        f2139f = logLevel;
        if (!AIUI.m868a() || logLevel == null) {
            return;
        }
        AIUI.setLogLevel(logLevel.ordinal());
    }

    public static void setMscCfg(String str) {
        f2136c = str;
    }

    public static void setNetLogLevel(LogLevel logLevel) {
        f2140g = logLevel;
        if (!AIUI.m868a() || logLevel == null) {
            return;
        }
        AIUI.setNetLogLevel(logLevel.ordinal());
    }

    public static void setSaveDataLog(boolean z) {
        f2137d = z;
    }

    public static void setShowLog(boolean z) {
        setLogLevel(z ? LogLevel.debug : LogLevel.error);
    }
}
