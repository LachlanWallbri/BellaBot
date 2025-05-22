package com.iflytek.aiui.pro;

import android.content.Context;
import android.content.SharedPreferences;
import com.iflytek.cloud.msc.util.Config;
import com.iflytek.location.LocationListener;
import com.iflytek.location.PosLocator;
import com.iflytek.location.result.LocResult;
import com.iflytek.location.result.NetLocResult;

/* renamed from: com.iflytek.aiui.pro.r0 */
/* loaded from: classes4.dex */
public class C3637r0 {

    /* renamed from: d */
    public static C3637r0 f2633d;

    /* renamed from: a */
    private SharedPreferences f2634a;

    /* renamed from: b */
    private Context f2635b;

    /* renamed from: c */
    private long f2636c = 0;

    /* renamed from: com.iflytek.aiui.pro.r0$a */
    /* loaded from: classes4.dex */
    class a implements LocationListener {
        a() {
        }

        @Override // com.iflytek.location.LocationListener
        public void onResult(LocResult locResult) {
            if (locResult != null) {
                NetLocResult netLocResult = (NetLocResult) locResult;
                C3637r0.this.m1521d((float) netLocResult.getLat(), (float) netLocResult.getLon());
                C3651y0.m1619a("use PosLocator get NetLocation description: " + netLocResult.getAoiname() + " lat: " + netLocResult.getLat() + ", lng: " + netLocResult.getLon());
            }
        }
    }

    private C3637r0(Context context) {
        this.f2634a = null;
        this.f2635b = null;
        this.f2635b = context;
        this.f2634a = context.getSharedPreferences(Config.PREFERENCES_NAME, 0);
    }

    /* renamed from: a */
    public static C3637r0 m1518a(Context context) {
        if (f2633d == null && context != null) {
            m1519c(context);
        }
        return f2633d;
    }

    /* renamed from: c */
    private static C3637r0 m1519c(Context context) {
        C3637r0 c3637r0;
        synchronized (C3637r0.class) {
            if (f2633d == null) {
                f2633d = new C3637r0(context);
            }
            C3641t0.m1540d(context);
            c3637r0 = f2633d;
        }
        return c3637r0;
    }

    /* renamed from: b */
    public float m1520b(String str) {
        float f;
        synchronized (this) {
            try {
                if (System.currentTimeMillis() - this.f2636c > (this.f2634a.getFloat(str, -0.1f) == -0.1f ? 5000 : 3600000)) {
                    C3651y0.m1619a("get Location start");
                    PosLocator.getInstance(this.f2635b).asyncGetLocation(0, new a());
                    this.f2636c = System.currentTimeMillis();
                }
            } catch (Exception e) {
                C3651y0.m1623e(e);
            }
            f = this.f2634a.getFloat(str, -0.1f);
        }
        return f;
    }

    /* renamed from: d */
    public void m1521d(float f, float f2) {
        SharedPreferences.Editor edit = this.f2634a.edit();
        edit.putFloat(Config.KEY_LATITUDE, f);
        edit.putFloat(Config.KEY_LONGITUDE, f2);
        edit.commit();
    }
}
