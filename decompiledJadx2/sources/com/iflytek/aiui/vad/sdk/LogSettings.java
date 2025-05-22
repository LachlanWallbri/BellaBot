package com.iflytek.aiui.vad.sdk;

/* loaded from: classes4.dex */
public class LogSettings {

    /* loaded from: classes4.dex */
    public enum LogLevel {
        debug,
        info,
        warn,
        error,
        none
    }

    private static native void set_log_level(int i);

    static {
        System.loadLibrary("evad");
    }

    public static void setLogLevel(LogLevel logLevel) {
        set_log_level(logLevel.ordinal());
    }
}
