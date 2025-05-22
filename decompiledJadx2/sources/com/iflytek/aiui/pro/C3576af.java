package com.iflytek.aiui.pro;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* renamed from: com.iflytek.aiui.pro.af */
/* loaded from: classes.dex */
public class C3576af {

    /* renamed from: a */
    C3568a f2293a;

    /* renamed from: b */
    Object f2294b;

    public C3576af(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context参数不能为null");
        }
        try {
            this.f2294b = C3634q.m1484a(context.getApplicationContext(), C3614g.m1299a("1.5.1"), "com.amap.api.netlocation.AMapNetworkLocationManagerWrapper", new Class[]{Context.class}, new Object[]{context});
        } catch (Throwable unused) {
            this.f2293a = new C3568a(context);
        }
    }

    /* renamed from: a */
    public void m975a(String str) {
        try {
            if (this.f2294b != null) {
                C3614g.m1301a(this.f2294b, "setApiKey", str);
            }
            if (this.f2293a != null) {
                this.f2293a.m872a(str);
            }
        } catch (Throwable th) {
            C3614g.m1308a(th, "AMapLocationClient", "setApiKey");
        }
    }

    /* renamed from: a */
    public byte[] m976a() {
        try {
        } catch (Throwable th) {
            C3614g.m1308a(th, "AMapLocationClient", "getNetworkLocationParameter");
        }
        if (this.f2294b != null) {
            return (byte[]) C3614g.m1301a(this.f2294b, "getNetworkLocationParameter", new Object[0]);
        }
        if (this.f2293a != null) {
            return this.f2293a.m875d();
        }
        return new byte[0];
    }

    /* renamed from: b */
    public String m977b() {
        try {
            if (this.f2294b != null) {
                return (String) C3614g.m1301a(this.f2294b, "getNetworkLocation", new Object[0]);
            }
            if (this.f2293a != null) {
                return this.f2293a.m873b();
            }
            return null;
        } catch (Throwable th) {
            C3614g.m1308a(th, "AMapLocationClient", "getNetworkLocation");
            return null;
        }
    }

    /* renamed from: c */
    public String m978c() {
        try {
            if (this.f2294b != null) {
                return (String) C3614g.m1301a(this.f2294b, "getGPSLocation", new Object[0]);
            }
            if (this.f2293a != null) {
                return this.f2293a.m874c();
            }
            return null;
        } catch (Throwable th) {
            C3614g.m1308a(th, "AMapLocationClient", "getGPSLocation");
            return null;
        }
    }

    /* renamed from: d */
    public void m979d() {
        try {
            if (this.f2294b != null) {
                C3614g.m1301a(this.f2294b, "destroy", new Object[0]);
            }
            if (this.f2293a != null) {
                this.f2293a.m871a();
            }
        } catch (Throwable th) {
            C3614g.m1308a(th, "AMapLocationClient", "destroy");
        }
    }
}
