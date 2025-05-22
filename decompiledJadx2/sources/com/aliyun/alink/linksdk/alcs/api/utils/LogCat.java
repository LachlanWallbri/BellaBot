package com.aliyun.alink.linksdk.alcs.api.utils;

import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LogCat {
    /* renamed from: e */
    public static void m332e(String str, String str2, Throwable th) {
        ALog.m482e(str, str2, th == null ? null : th.toString());
    }

    /* renamed from: e */
    public static void m331e(String str, String str2) {
        ALog.m480e(str, str2);
    }

    /* renamed from: d */
    public static void m330d(String str, String str2, Throwable th) {
        ALog.m479d(str, str2);
    }

    /* renamed from: d */
    public static void m329d(String str, String str2) {
        ALog.m479d(str, str2);
    }

    /* renamed from: i */
    public static void m334i(String str, String str2, Throwable th) {
        ALog.m483i(str, str2);
    }

    /* renamed from: i */
    public static void m333i(String str, String str2) {
        ALog.m483i(str, str2);
    }

    /* renamed from: v */
    public static void m336v(String str, String str2, Throwable th) {
        m334i(str, str2, th);
    }

    /* renamed from: v */
    public static void m335v(String str, String str2) {
        m333i(str, str2);
    }

    /* renamed from: w */
    public static void m338w(String str, String str2, Throwable th) {
        ALog.m484w(str, str2);
    }

    /* renamed from: w */
    public static void m337w(String str, String str2) {
        ALog.m484w(str, str2);
    }
}
