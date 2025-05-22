package com.iflytek.aiui.pro;

import android.content.Context;
import android.location.Location;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* renamed from: com.iflytek.aiui.pro.a */
/* loaded from: classes.dex */
public class C3568a {

    /* renamed from: a */
    boolean f2222a;

    /* renamed from: b */
    private boolean f2223b;

    /* renamed from: c */
    private C3575ae f2224c;

    /* renamed from: d */
    private Context f2225d;

    /* renamed from: e */
    private long f2226e = 0;

    /* renamed from: f */
    private String f2227f;

    /* renamed from: g */
    private C3616h f2228g;

    public C3568a(Context context) {
        this.f2223b = true;
        this.f2224c = null;
        this.f2222a = false;
        try {
            if (context == null) {
                throw new IllegalArgumentException("Context参数不能为null");
            }
            this.f2225d = context.getApplicationContext();
            this.f2224c = new C3575ae();
            this.f2228g = new C3616h(context);
            this.f2224c.m968a(this.f2225d);
            C3573ac.m944a(this.f2225d);
            this.f2222a = false;
        } catch (Throwable th) {
            this.f2223b = false;
            C3614g.m1308a(th, "AMapLocationClient", "AMapLocationClient 1");
        }
    }

    /* renamed from: a */
    public void m871a() {
        C3575ae c3575ae = this.f2224c;
        if (c3575ae != null) {
            c3575ae.m974d();
        }
        C3616h c3616h = this.f2228g;
        if (c3616h != null) {
            c3616h.m1350c();
        }
        this.f2222a = true;
    }

    /* renamed from: a */
    public void m872a(String str) {
        try {
            C3618i.m1353a(str);
        } catch (Throwable th) {
            C3614g.m1308a(th, "AMapLocationManager", "setApiKey");
        }
    }

    /* renamed from: b */
    public String m873b() throws Exception {
        if (!this.f2223b) {
            return null;
        }
        if (C3614g.m1320b() - this.f2226e < 1000) {
            return this.f2227f;
        }
        String m971b = this.f2224c.m971b(true);
        this.f2226e = C3614g.m1320b();
        this.f2227f = m971b;
        return m971b;
    }

    /* renamed from: c */
    public String m874c() throws Exception {
        Location m1351d;
        C3616h c3616h = this.f2228g;
        if (c3616h == null) {
            return null;
        }
        c3616h.m1348a();
        while (true) {
            m1351d = this.f2228g.m1351d();
            if (m1351d != null || this.f2222a) {
                break;
            }
            try {
                Thread.sleep(1000L);
            } catch (Throwable unused) {
            }
        }
        this.f2228g.m1349b();
        if (m1351d != null) {
            return C3614g.m1306a(m1351d).toString();
        }
        return null;
    }

    /* renamed from: d */
    public byte[] m875d() throws Exception {
        return this.f2224c.m972b();
    }
}
