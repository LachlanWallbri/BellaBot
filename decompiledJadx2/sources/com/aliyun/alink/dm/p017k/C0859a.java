package com.aliyun.alink.dm.p017k;

import com.aliyun.alink.linksdk.tools.log.HLoggerFactory;
import com.aliyun.alink.linksdk.tools.log.ILogger;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ALog.java */
/* renamed from: com.aliyun.alink.dm.k.a */
/* loaded from: classes.dex */
public class C0859a {

    /* renamed from: a */
    private static ILogger f409a = new HLoggerFactory().getInstance("LK-DM-");

    /* renamed from: a */
    public static void m131a(String str, String str2) {
        f409a.mo485d(str, str2);
    }

    /* renamed from: b */
    public static void m133b(String str, String str2) {
        f409a.mo488i(str, str2);
    }

    /* renamed from: c */
    public static void m134c(String str, String str2) {
        f409a.mo489w(str, str2);
    }

    /* renamed from: d */
    public static void m135d(String str, String str2) {
        f409a.mo486e(str, str2);
    }

    /* renamed from: a */
    public static void m132a(String str, String str2, Exception exc) {
        if (exc != null) {
            ILogger iLogger = f409a;
            StringBuilder sb = new StringBuilder();
            if (str2 == null) {
                str2 = "";
            }
            sb.append(str2);
            sb.append(" EXCEPTION: ");
            sb.append(exc.getMessage());
            iLogger.mo486e(str, sb.toString());
            exc.printStackTrace();
            return;
        }
        ILogger iLogger2 = f409a;
        StringBuilder sb2 = new StringBuilder();
        if (str2 == null) {
            str2 = "";
        }
        sb2.append(str2);
        sb2.append(" EXCEPTION: unknown");
        iLogger2.mo486e(str, sb2.toString());
    }
}
