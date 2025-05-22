package com.loc;

import android.content.Context;
import android.text.TextUtils;
import java.lang.Thread;

/* compiled from: DynamicExceptionHandler.java */
/* renamed from: com.loc.ba */
/* loaded from: classes4.dex */
public final class C3826ba implements Thread.UncaughtExceptionHandler {

    /* renamed from: a */
    private static C3826ba f3615a;

    /* renamed from: b */
    private Thread.UncaughtExceptionHandler f3616b = Thread.getDefaultUncaughtExceptionHandler();

    /* renamed from: c */
    private Context f3617c;

    /* renamed from: d */
    private C3893s f3618d;

    private C3826ba(Context context, C3893s c3893s) {
        this.f3617c = context.getApplicationContext();
        this.f3618d = c3893s;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static synchronized C3826ba m2515a(Context context, C3893s c3893s) {
        C3826ba c3826ba;
        synchronized (C3826ba.class) {
            if (f3615a == null) {
                f3615a = new C3826ba(context, c3893s);
            }
            c3826ba = f3615a;
        }
        return c3826ba;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final void uncaughtException(Thread thread, Throwable th) {
        C3804af c3804af;
        Context context;
        String str;
        String m3224a = C3894t.m3224a(th);
        try {
            if (!TextUtils.isEmpty(m3224a)) {
                if ((m3224a.contains("amapdynamic") || m3224a.contains("admic")) && m3224a.contains("com.amap.api")) {
                    C3804af c3804af2 = new C3804af(this.f3617c, C3827bb.m2516b());
                    if (m3224a.contains("loc")) {
                        C3823ay.m2498a(c3804af2, this.f3617c, "loc");
                    }
                    if (m3224a.contains("navi")) {
                        C3823ay.m2498a(c3804af2, this.f3617c, "navi");
                    }
                    if (m3224a.contains("sea")) {
                        C3823ay.m2498a(c3804af2, this.f3617c, "sea");
                    }
                    if (m3224a.contains("2dmap")) {
                        C3823ay.m2498a(c3804af2, this.f3617c, "2dmap");
                    }
                    if (m3224a.contains("3dmap")) {
                        C3823ay.m2498a(c3804af2, this.f3617c, "3dmap");
                    }
                } else {
                    if (m3224a.contains("com.autonavi.aps.amapapi.offline")) {
                        c3804af = new C3804af(this.f3617c, C3827bb.m2516b());
                        context = this.f3617c;
                        str = "OfflineLocation";
                    } else if (m3224a.contains("com.data.carrier_v4")) {
                        c3804af = new C3804af(this.f3617c, C3827bb.m2516b());
                        context = this.f3617c;
                        str = "Collection";
                    } else if (m3224a.contains("com.autonavi.aps.amapapi.httpdns") || m3224a.contains("com.autonavi.httpdns")) {
                        c3804af = new C3804af(this.f3617c, C3827bb.m2516b());
                        context = this.f3617c;
                        str = "HttpDNS";
                    }
                    C3823ay.m2498a(c3804af, context, str);
                }
            }
        } catch (Throwable th2) {
            C3897w.m3249a(th2, "DynamicExceptionHandler", "uncaughtException");
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f3616b;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
    }
}
