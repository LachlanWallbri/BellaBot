package com.http.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes.dex */
public class LogUtils {
    private static final String LOG_PREFIX = "[NetworkCore]";

    /* renamed from: fm */
    private static SimpleDateFormat f2130fm = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
    private static boolean showLog = true;

    public static void setShowLog(boolean z) {
        showLog = z;
    }

    public static void print(String str, String str2) {
        if (showLog) {
            String str3 = null;
            try {
                StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];
                str3 = "[" + stackTraceElement.getFileName() + "] - " + stackTraceElement.getMethodName() + "(" + stackTraceElement.getLineNumber() + ")";
            } catch (Exception unused) {
            }
            System.out.println(f2130fm.format(new Date()) + " - " + str3 + ":" + str + ":" + str2);
        }
    }

    public static void print(String str) {
        print(LOG_PREFIX, str);
    }

    public static void error(String str) {
        error(LOG_PREFIX, str);
    }

    public static void error(String str, String str2) {
        if (showLog) {
            String str3 = null;
            try {
                StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];
                str3 = "[" + stackTraceElement.getFileName() + "] - " + stackTraceElement.getMethodName() + "(" + stackTraceElement.getLineNumber() + ")";
            } catch (Exception unused) {
            }
            System.err.println(f2130fm.format(new Date()) + " - " + str3 + ":" + str + ":" + str2);
        }
    }
}
