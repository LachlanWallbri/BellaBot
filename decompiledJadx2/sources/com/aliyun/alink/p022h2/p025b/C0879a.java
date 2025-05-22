package com.aliyun.alink.p022h2.p025b;

import com.aliyun.alink.p022h2.utils.HLoggerFactory;
import com.aliyun.alink.p022h2.utils.ILogger;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ALog.java */
/* renamed from: com.aliyun.alink.h2.b.a */
/* loaded from: classes.dex */
public class C0879a {

    /* renamed from: a */
    private static ILogger f526a = new HLoggerFactory().getInstance("H2Base-");

    /* renamed from: a */
    public static void m206a(String str, String str2) {
        f526a.mo212d(str, str2);
    }

    /* renamed from: b */
    public static void m208b(String str, String str2) {
        f526a.mo215i(str, str2);
    }

    /* renamed from: c */
    public static void m209c(String str, String str2) {
        f526a.mo216w(str, str2);
    }

    /* renamed from: d */
    public static void m210d(String str, String str2) {
        f526a.mo213e(str, str2);
    }

    /* renamed from: a */
    public static void m207a(String str, String str2, Exception exc) {
        if (exc != null) {
            ILogger iLogger = f526a;
            StringBuilder sb = new StringBuilder();
            if (str2 == null) {
                str2 = "";
            }
            sb.append(str2);
            sb.append(" EXCEPTION: ");
            sb.append(exc.getMessage());
            iLogger.mo213e(str, sb.toString());
            exc.printStackTrace();
            return;
        }
        ILogger iLogger2 = f526a;
        StringBuilder sb2 = new StringBuilder();
        if (str2 == null) {
            str2 = "";
        }
        sb2.append(str2);
        sb2.append(" EXCEPTION: unknown");
        iLogger2.mo213e(str, sb2.toString());
    }
}
