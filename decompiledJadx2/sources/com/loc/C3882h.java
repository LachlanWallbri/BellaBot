package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import java.util.List;
import java.util.concurrent.ExecutorService;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.json.JSONObject;

/* compiled from: LastLocationManager.java */
/* renamed from: com.loc.h */
/* loaded from: classes4.dex */
public final class C3882h {

    /* renamed from: b */
    private Context f4213b;

    /* renamed from: c */
    private String f4214c;

    /* renamed from: f */
    private C3804af f4217f;

    /* renamed from: d */
    private C3862cj f4215d = null;

    /* renamed from: e */
    private C3862cj f4216e = null;

    /* renamed from: g */
    private ExecutorService f4218g = null;

    /* renamed from: h */
    private long f4219h = 0;

    /* renamed from: a */
    Runnable f4212a = new Runnable() { // from class: com.loc.h.1
        @Override // java.lang.Runnable
        public final void run() {
            C3882h.this.m3110c();
        }
    };

    public C3882h(Context context) {
        this.f4214c = null;
        this.f4217f = null;
        this.f4213b = context.getApplicationContext();
        try {
            this.f4214c = C3857ce.m2796a(MessageDigestAlgorithms.MD5, C3888n.m3168q(this.f4213b));
            InterfaceC3803ae m2421a = C3804af.m2421a((Class<? extends InterfaceC3803ae>) C3863ck.class);
            if (m2421a != null) {
                this.f4217f = new C3804af(context, m2421a, C3876cx.m3016i());
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "LastLocationManager", "<init>:DBOperation");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public synchronized void m3110c() {
        String str;
        try {
            if (this.f4215d != null && C3876cx.m2976a(this.f4215d.m2837a()) && this.f4217f != null && this.f4215d != this.f4216e) {
                String str2 = this.f4215d.m2837a().toStr();
                String m2841b = this.f4215d.m2841b();
                this.f4216e = this.f4215d;
                String str3 = null;
                if (TextUtils.isEmpty(str2)) {
                    str = null;
                } else {
                    str = C3889o.m3178a(C3857ce.m2804c(str2.getBytes("UTF-8"), this.f4214c));
                    if (!TextUtils.isEmpty(m2841b)) {
                        str3 = C3889o.m3178a(C3857ce.m2804c(m2841b.getBytes("UTF-8"), this.f4214c));
                    }
                }
                if (!TextUtils.isEmpty(str)) {
                    C3862cj c3862cj = new C3862cj();
                    c3862cj.m2842b(str);
                    c3862cj.m2838a(C3876cx.m2985b());
                    c3862cj.m2840a(str3);
                    this.f4217f.m2429a(c3862cj, "_id=1");
                    this.f4219h = C3876cx.m2985b();
                }
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "LastLocationManager", "saveLastFix");
        }
    }

    /* renamed from: d */
    private C3862cj m3111d() {
        Throwable th;
        C3862cj c3862cj;
        byte[] m2805d;
        byte[] m2805d2;
        String str = null;
        if (this.f4213b == null) {
            return null;
        }
        try {
        } catch (Throwable th2) {
            th = th2;
            c3862cj = null;
        }
        if (this.f4217f == null) {
            return null;
        }
        List m2433b = this.f4217f.m2433b("_id=1", C3862cj.class);
        if (m2433b == null || m2433b.size() <= 0) {
            c3862cj = null;
        } else {
            c3862cj = (C3862cj) m2433b.get(0);
            try {
                byte[] m3182b = C3889o.m3182b(c3862cj.m2843c());
                String str2 = (m3182b == null || m3182b.length <= 0 || (m2805d2 = C3857ce.m2805d(m3182b, this.f4214c)) == null || m2805d2.length <= 0) ? null : new String(m2805d2, "UTF-8");
                byte[] m3182b2 = C3889o.m3182b(c3862cj.m2841b());
                if (m3182b2 != null && m3182b2.length > 0 && (m2805d = C3857ce.m2805d(m3182b2, this.f4214c)) != null && m2805d.length > 0) {
                    str = new String(m2805d, "UTF-8");
                }
                c3862cj.m2840a(str);
                str = str2;
            } catch (Throwable th3) {
                th = th3;
                C3880f.m3097a(th, "LastLocationManager", "readLastFix");
                return c3862cj;
            }
        }
        if (!TextUtils.isEmpty(str)) {
            AMapLocation aMapLocation = new AMapLocation("");
            C3880f.m3096a(aMapLocation, new JSONObject(str));
            if (C3876cx.m2992b(aMapLocation)) {
                c3862cj.m2839a(aMapLocation);
            }
        }
        return c3862cj;
    }

    /* renamed from: a */
    public final AMapLocation m3112a() {
        if (this.f4215d == null) {
            this.f4215d = m3111d();
        }
        C3862cj c3862cj = this.f4215d;
        if (c3862cj != null && C3876cx.m2976a(c3862cj.m2837a())) {
            return this.f4215d.m2837a();
        }
        return null;
    }

    /* renamed from: a */
    public final AMapLocation m3113a(String str) {
        if (this.f4215d == null) {
            this.f4215d = m3111d();
        }
        C3862cj c3862cj = this.f4215d;
        if (c3862cj == null || c3862cj.m2837a() == null) {
            return null;
        }
        if (!TextUtils.isEmpty(str)) {
            C3858cf.m2807a();
            if (!C3858cf.m2809a(this.f4215d.m2841b(), str)) {
                return null;
            }
        } else if (C3876cx.m2985b() - this.f4215d.m2844d() > 30000) {
            return null;
        }
        AMapLocation m2837a = this.f4215d.m2837a();
        m2837a.setLocationType(4);
        return m2837a;
    }

    /* renamed from: a */
    public final void m3114a(C3862cj c3862cj) {
        if (this.f4213b == null || c3862cj == null || !C3876cx.m2976a(c3862cj.m2837a()) || c3862cj.m2837a().getLocationType() == 2) {
            return;
        }
        try {
            this.f4215d = c3862cj;
            if ((this.f4216e == null || C3876cx.m2962a(this.f4216e.m2837a(), c3862cj.m2837a()) > 50.0f) && C3876cx.m2985b() - this.f4219h > 30000) {
                if (this.f4218g == null) {
                    this.f4218g = C3900z.m3265b();
                }
                if (this.f4218g.isShutdown()) {
                    return;
                }
                this.f4218g.submit(this.f4212a);
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "LastLocationManager", "setLastFix");
        }
    }

    /* renamed from: b */
    public final synchronized void m3115b() {
        try {
            m3110c();
            if (this.f4218g != null) {
                this.f4218g.shutdown();
                this.f4218g = null;
            }
            this.f4219h = 0L;
        } catch (Throwable th) {
            C3880f.m3097a(th, "LastLocationManager", "destroy");
        }
    }
}
