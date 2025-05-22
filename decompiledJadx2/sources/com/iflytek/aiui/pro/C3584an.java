package com.iflytek.aiui.pro;

import android.content.Context;
import android.content.SharedPreferences;
import com.iflytek.aiui.utils.log.DebugLog;
import com.iflytek.cloud.msc.util.Config;
import com.iflytek.location.LocationListener;
import com.iflytek.location.PosLocator;
import com.iflytek.location.result.LocResult;
import com.iflytek.location.result.NetLocResult;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* renamed from: com.iflytek.aiui.pro.an */
/* loaded from: classes.dex */
public class C3584an {

    /* renamed from: a */
    public static C3584an f2350a;

    /* renamed from: b */
    private SharedPreferences f2351b;

    /* renamed from: c */
    private Context f2352c;

    /* renamed from: d */
    private boolean f2353d = true;

    /* renamed from: e */
    private long f2354e = 0;

    private C3584an(Context context) {
        this.f2351b = null;
        this.f2352c = null;
        this.f2352c = context;
        this.f2351b = context.getSharedPreferences(Config.PREFERENCES_NAME, 0);
    }

    /* renamed from: a */
    public static C3584an m1046a(Context context) {
        if (f2350a == null && context != null) {
            m1047b(context);
        }
        return f2350a;
    }

    /* renamed from: b */
    private static C3584an m1047b(Context context) {
        C3584an c3584an;
        synchronized (C3584an.class) {
            if (f2350a == null) {
                f2350a = new C3584an(context);
            }
            C3594ax.m1106a(context);
            c3584an = f2350a;
        }
        return c3584an;
    }

    /* renamed from: a */
    public float m1048a(String str) {
        float f;
        synchronized (this) {
            try {
                if (System.currentTimeMillis() - this.f2354e > (this.f2351b.getFloat(str, -0.1f) == -0.1f ? 5000 : 3600000)) {
                    DebugLog.LogD("get Location start");
                    PosLocator.getInstance(this.f2352c).asyncGetLocation(0, new LocationListener() { // from class: com.iflytek.aiui.pro.an.1
                        @Override // com.iflytek.location.LocationListener
                        public void onResult(LocResult locResult) {
                            if (locResult != null) {
                                NetLocResult netLocResult = (NetLocResult) locResult;
                                C3584an.this.m1049a((float) netLocResult.getLat(), (float) netLocResult.getLon());
                                DebugLog.LogD("use PosLocator get NetLocation description: " + netLocResult.getAoiname() + " lat: " + netLocResult.getLat() + ", lng: " + netLocResult.getLon());
                            }
                        }
                    });
                    this.f2354e = System.currentTimeMillis();
                }
            } catch (Exception e) {
                DebugLog.LogE(e);
            }
            f = this.f2351b.getFloat(str, -0.1f);
        }
        return f;
    }

    /* renamed from: a */
    public void m1049a(float f, float f2) {
        SharedPreferences.Editor edit = this.f2351b.edit();
        edit.putFloat(Config.KEY_LATITUDE, f);
        edit.putFloat(Config.KEY_LONGITUDE, f2);
        edit.commit();
    }
}
