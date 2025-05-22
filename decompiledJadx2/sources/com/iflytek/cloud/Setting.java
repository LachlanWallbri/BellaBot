package com.iflytek.cloud;

import android.os.Environment;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.resource.Resource;
import java.util.Locale;

/* loaded from: classes3.dex */
public class Setting {

    /* renamed from: a */
    public static final String f2776a = Environment.getExternalStorageDirectory().getPath() + "/msc/msc.log";

    /* renamed from: b */
    private static String f2777b = f2776a;

    /* renamed from: c */
    private static boolean f2778c = false;

    /* renamed from: d */
    private static boolean f2779d = true;

    /* loaded from: classes3.dex */
    public enum LOG_LEVEL {
        all,
        detail,
        normal,
        low,
        none
    }

    private Setting() {
    }

    public static void setLogLevel(LOG_LEVEL log_level) {
        if (log_level != null) {
            try {
                DebugLog.setLogLevel(DebugLog.LOG_LEVEL.values()[log_level.ordinal()]);
            } catch (Exception e) {
                DebugLog.LogE(e);
            }
        }
    }

    public static LOG_LEVEL getLogLevel() {
        LOG_LEVEL log_level = LOG_LEVEL.none;
        try {
            return LOG_LEVEL.values()[DebugLog.getLogLevel().ordinal()];
        } catch (Exception e) {
            DebugLog.LogE(e);
            return log_level;
        }
    }

    public static void setLogPath(String str) {
        f2777b = str;
    }

    public static String getLogPath() {
        return f2777b;
    }

    public static void setShowLog(boolean z) {
        DebugLog.setShowLog(z);
    }

    public static boolean getShowLog() {
        return DebugLog.getShowLog();
    }

    public static void setSaveTestLog(boolean z) {
        f2778c = z;
    }

    public static boolean getSaveTestLog() {
        return f2778c;
    }

    public static void setLocationEnable(boolean z) {
        f2779d = z;
    }

    public static boolean getLocationEnable() {
        return f2779d;
    }

    public static void setLocale(Locale locale) {
        Resource.setUILanguage(locale);
    }
}
