package com.iflytek.aiui.pro;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* renamed from: com.iflytek.aiui.pro.h */
/* loaded from: classes.dex */
public class C3616h {

    /* renamed from: a */
    Context f2534a;

    /* renamed from: b */
    LocationManager f2535b;

    /* renamed from: c */
    boolean f2536c = true;

    /* renamed from: d */
    JSONArray f2537d = null;

    /* renamed from: e */
    Location f2538e = null;

    /* renamed from: f */
    LocationListener f2539f = new LocationListener() { // from class: com.iflytek.aiui.pro.h.1
        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            try {
                if (C3614g.m1309a(location.getLatitude(), location.getLongitude())) {
                    double[] m1289a = C3612f.m1289a(location.getLongitude(), location.getLatitude());
                    location.setLatitude(m1289a[1]);
                    location.setLongitude(m1289a[0]);
                }
                C3616h.this.f2538e = location;
                if (C3616h.this.f2536c) {
                    if (C3616h.this.f2537d == null) {
                        C3616h.this.f2537d = new JSONArray();
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("lon", location.getLongitude());
                    jSONObject.put("lat", location.getLatitude());
                    jSONObject.put("type", 0);
                    jSONObject.put("timestamp", C3614g.m1320b());
                    C3616h.this.f2537d = C3616h.this.f2537d.put(jSONObject);
                    if (C3616h.this.f2537d.length() >= 200) {
                        C3616h.this.m1347e();
                    }
                }
            } catch (Throwable th) {
                C3614g.m1308a(th, "GPSLocation", "onLocationChanged");
            }
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
        }
    };

    /* renamed from: com.iflytek.aiui.pro.h$a */
    /* loaded from: classes4.dex */
    class a implements LocationListener {
        a() {
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            try {
                if (C3614g.o(location.getLatitude(), location.getLongitude())) {
                    double[] b = C3612f.b(location.getLongitude(), location.getLatitude());
                    location.setLatitude(b[1]);
                    location.setLongitude(b[0]);
                }
                C3616h c3616h = C3616h.this;
                c3616h.f2538e = location;
                if (c3616h.f2536c) {
                    if (c3616h.f2537d == null) {
                        c3616h.f2537d = new JSONArray();
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("lon", location.getLongitude());
                    jSONObject.put("lat", location.getLatitude());
                    jSONObject.put("type", 0);
                    jSONObject.put("timestamp", C3614g.z());
                    C3616h c3616h2 = C3616h.this;
                    c3616h2.f2537d = c3616h2.f2537d.put(jSONObject);
                    if (C3616h.this.f2537d.length() >= 200) {
                        C3616h.b(C3616h.this);
                    }
                }
            } catch (Throwable th) {
                C3614g.n(th, "GPSLocation", "onLocationChanged");
            }
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
        }
    }

    public C3616h(Context context) {
        this.f2534a = context;
        this.f2535b = (LocationManager) context.getSystemService(RequestParameters.SUBRESOURCE_LOCATION);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m1347e() {
        try {
            if (this.f2537d == null || this.f2537d.length() <= 0) {
                return;
            }
            C3573ac.m946a(new C3572ab(this.f2534a, C3614g.m1299a("channelloc"), this.f2537d.toString()), this.f2534a);
            this.f2537d = null;
        } catch (Throwable th) {
            C3614g.m1308a(th, "AMapLocationManager", "writeOfflineLog");
        }
    }

    /* renamed from: a */
    public void m1348a() {
        try {
            if (this.f2535b == null) {
                return;
            }
            this.f2535b.requestLocationUpdates("gps", 1000L, 0.0f, this.f2539f, this.f2534a.getMainLooper());
        } catch (Throwable unused) {
        }
    }

    /* renamed from: b */
    public void m1349b() {
        LocationManager locationManager = this.f2535b;
        if (locationManager == null) {
            return;
        }
        LocationListener locationListener = this.f2539f;
        if (locationListener != null) {
            locationManager.removeUpdates(locationListener);
        }
        this.f2538e = null;
    }

    /* renamed from: c */
    public void m1350c() {
        m1349b();
        m1347e();
        this.f2535b = null;
        C3573ac.m943a();
    }

    /* renamed from: d */
    public Location m1351d() {
        return this.f2538e;
    }
}
