package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import java.util.Hashtable;
import org.json.JSONObject;

/* compiled from: HeatMap.java */
/* renamed from: com.loc.ci */
/* loaded from: classes4.dex */
public final class C3861ci {

    /* renamed from: a */
    private static C3861ci f3913a;

    /* renamed from: b */
    private Hashtable<String, JSONObject> f3914b = new Hashtable<>();

    /* renamed from: c */
    private boolean f3915c = false;

    private C3861ci() {
    }

    /* renamed from: a */
    public static synchronized C3861ci m2832a() {
        C3861ci c3861ci;
        synchronized (C3861ci.class) {
            if (f3913a == null) {
                f3913a = new C3861ci();
            }
            c3861ci = f3913a;
        }
        return c3861ci;
    }

    /* renamed from: a */
    public final void m2833a(Context context) {
        if (C3848bw.f3831a && !this.f3915c) {
            C3876cx.m2985b();
            try {
                C3860ch.m2824a().m2831b(context);
            } catch (Throwable th) {
                C3880f.m3097a(th, "HeatMap", "loadDB");
            }
            this.f3915c = true;
        }
    }

    /* renamed from: a */
    public final synchronized void m2834a(Context context, String str, AMapLocationServer aMapLocationServer) {
        String str2 = null;
        if (C3876cx.m2977a(aMapLocationServer) && context != null && C3848bw.f3831a) {
            if (this.f3914b.size() > 500) {
                str2 = C3850by.m2732a(aMapLocationServer.getLatitude(), aMapLocationServer.getLongitude());
                if (!this.f3914b.containsKey(str2)) {
                    return;
                }
            }
            if (str2 == null) {
                str2 = C3850by.m2732a(aMapLocationServer.getLatitude(), aMapLocationServer.getLongitude());
            }
            String str3 = str2;
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(TransferTable.COLUMN_KEY, str);
                jSONObject.put("lat", aMapLocationServer.getLatitude());
                jSONObject.put("lon", aMapLocationServer.getLongitude());
                m2835a(context, str3, jSONObject.toString(), 1, C3876cx.m2969a(), true);
            } catch (Throwable th) {
                C3880f.m3097a(th, "HeatMap", "update");
            }
        }
    }

    /* renamed from: a */
    public final synchronized void m2835a(Context context, String str, String str2, int i, long j, boolean z) {
        if (context != null) {
            if (!TextUtils.isEmpty(str)) {
                if (C3848bw.f3831a) {
                    JSONObject jSONObject = this.f3914b.get(str);
                    if (jSONObject == null) {
                        jSONObject = new JSONObject();
                    }
                    try {
                        jSONObject.put("x", str2);
                        jSONObject.put("time", j);
                        if (this.f3914b.containsKey(str)) {
                            jSONObject.put("num", jSONObject.getInt("num") + i);
                        } else {
                            jSONObject.put("num", i);
                        }
                    } catch (Throwable th) {
                        C3880f.m3097a(th, "HeatMap", "update1");
                    }
                    this.f3914b.put(str, jSONObject);
                    if (!C3880f.f4189k && !C3875cw.m2958b(context, "pref", "ddex", false) && i >= 120) {
                        C3880f.f4189k = true;
                        C3875cw.m2955a(context, "pref", "ddex", true);
                    }
                    if (z) {
                        try {
                            C3860ch.m2824a().m2829a(context, str, str2, j);
                        } catch (Throwable th2) {
                            C3880f.m3097a(th2, "HeatMap", "update");
                        }
                    }
                }
            }
        }
    }

    /* renamed from: b */
    public final void m2836b() {
        C3861ci m2832a = m2832a();
        if (!m2832a.f3914b.isEmpty()) {
            m2832a.f3914b.clear();
        }
        this.f3915c = false;
    }
}
