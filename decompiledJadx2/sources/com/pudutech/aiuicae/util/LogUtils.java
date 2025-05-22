package com.pudutech.aiuicae.util;

import com.pudutech.base.Pdlog;

/* loaded from: classes3.dex */
public class LogUtils {
    /* renamed from: d */
    public static void m3271d(String str, String str2, Object... objArr) {
        Pdlog.m3273d(str, getContent(str2, 4, objArr));
    }

    protected static String getNameFromTrace(StackTraceElement[] stackTraceElementArr, int i) {
        StringBuilder sb = new StringBuilder();
        if (stackTraceElementArr != null && stackTraceElementArr.length > i) {
            StackTraceElement stackTraceElement = stackTraceElementArr[i];
            sb.append(stackTraceElement.getMethodName());
            sb.append("(");
            sb.append(stackTraceElement.getFileName());
            sb.append(":");
            sb.append(stackTraceElement.getLineNumber());
            sb.append(")");
        }
        return sb.toString();
    }

    private static String getContent(String str, int i, Object... objArr) {
        try {
            return getNameFromTrace(Thread.currentThread().getStackTrace(), i) + String.format(str, objArr);
        } catch (Throwable unused) {
            return str;
        }
    }
}
