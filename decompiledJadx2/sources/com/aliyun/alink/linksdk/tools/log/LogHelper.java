package com.aliyun.alink.linksdk.tools.log;

import android.util.Log;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LogHelper {
    public static String targetStackTraceMSg() {
        try {
            StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
            if (targetStackTraceElement == null) {
                return "";
            }
            return ":" + targetStackTraceElement.getLineNumber();
        } catch (Exception unused) {
            return "";
        }
    }

    private static StackTraceElement getTargetStackTraceElement() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int length = stackTrace.length;
        int i = 0;
        boolean z = false;
        while (i < length) {
            StackTraceElement stackTraceElement = stackTrace[i];
            boolean equals = stackTraceElement.getClassName().equals(ALog.class.getName());
            if (z && !equals) {
                return stackTraceElement;
            }
            i++;
            z = equals;
        }
        return null;
    }

    public static String getLogLevel(int i) {
        if (i <= 3) {
            return LogLevel.DEBUG.getTag();
        }
        if (i == 4) {
            return LogLevel.INFO.getTag();
        }
        if (i == 5) {
            return LogLevel.WRAN.getTag();
        }
        return LogLevel.ERROR.getTag();
    }

    public static void print(int i, String str, String str2) {
        Log.println(i, str, str2);
    }
}
