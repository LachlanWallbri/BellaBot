package com.iflytek.aiui.pro;

import android.content.Context;

/* renamed from: com.iflytek.aiui.pro.f0 */
/* loaded from: classes4.dex */
public class C3613f0 {

    /* renamed from: a */
    C3568a f2521a;

    /* renamed from: b */
    Object f2522b;

    public C3613f0(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context参数不能为null");
        }
        try {
            this.f2522b = C3634q.m1484a(context.getApplicationContext(), C3614g.e("1.5.1"), "com.amap.api.netlocation.AMapNetworkLocationManagerWrapper", new Class[]{Context.class}, new Object[]{context});
        } catch (Throwable unused) {
            this.f2521a = new C3568a(context);
        }
    }

    /* renamed from: a */
    public void m1290a() {
        try {
            Object obj = this.f2522b;
            if (obj != null) {
                C3614g.g(obj, "destroy", new Object[0]);
            }
            C3568a c3568a = this.f2521a;
            if (c3568a != null) {
                c3568a.m873b();
            }
        } catch (Throwable th) {
            C3614g.n(th, "AMapLocationClient", "destroy");
        }
    }

    /* renamed from: b */
    public String m1291b() {
        try {
            Object obj = this.f2522b;
            if (obj != null) {
                return (String) C3614g.g(obj, "getGPSLocation", new Object[0]);
            }
            C3568a c3568a = this.f2521a;
            if (c3568a != null) {
                return c3568a.m875d();
            }
            return null;
        } catch (Throwable th) {
            C3614g.n(th, "AMapLocationClient", "getGPSLocation");
            return null;
        }
    }

    /* renamed from: c */
    public String m1292c() {
        try {
            Object obj = this.f2522b;
            if (obj != null) {
                return (String) C3614g.g(obj, "getNetworkLocation", new Object[0]);
            }
            C3568a c3568a = this.f2521a;
            if (c3568a != null) {
                return c3568a.m874c();
            }
            return null;
        } catch (Throwable th) {
            C3614g.n(th, "AMapLocationClient", "getNetworkLocation");
            return null;
        }
    }

    /* renamed from: d */
    public byte[] m1293d() {
        Object obj;
        try {
            obj = this.f2522b;
        } catch (Throwable th) {
            C3614g.n(th, "AMapLocationClient", "getNetworkLocationParameter");
        }
        if (obj != null) {
            return (byte[]) C3614g.g(obj, "getNetworkLocationParameter", new Object[0]);
        }
        C3568a c3568a = this.f2521a;
        if (c3568a != null) {
            return c3568a.e();
        }
        return new byte[0];
    }

    /* renamed from: e */
    public void m1294e(String str) {
        try {
            Object obj = this.f2522b;
            if (obj != null) {
                C3614g.g(obj, "setApiKey", new Object[]{str});
            }
            C3568a c3568a = this.f2521a;
            if (c3568a != null) {
                c3568a.m872a(str);
            }
        } catch (Throwable th) {
            C3614g.n(th, "AMapLocationClient", "setApiKey");
        }
    }
}
