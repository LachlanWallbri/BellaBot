package com.amap.api.location;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.loc.C3819au;
import com.loc.C3874cv;
import com.loc.C3878d;
import com.loc.C3880f;
import com.loc.C3893s;

/* loaded from: classes.dex */
public class AMapLocationClient {

    /* renamed from: a */
    Context f1100a;

    /* renamed from: b */
    LocationManagerBase f1101b;

    public AMapLocationClient(Context context) {
        try {
            if (context == null) {
                throw new IllegalArgumentException("Context参数不能为null");
            }
            this.f1100a = context.getApplicationContext();
            this.f1101b = m491a(this.f1100a, null);
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationClient", "AMapLocationClient 1");
        }
    }

    public AMapLocationClient(Context context, Intent intent) {
        try {
            if (context == null) {
                throw new IllegalArgumentException("Context参数不能为null");
            }
            this.f1100a = context.getApplicationContext();
            this.f1101b = m491a(this.f1100a, intent);
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationClient", "AMapLocationClient 2");
        }
    }

    /* renamed from: a */
    private static LocationManagerBase m491a(Context context, Intent intent) {
        LocationManagerBase c3878d;
        try {
            C3893s m3091a = C3880f.m3091a("loc");
            C3874cv.m2946a(context, m3091a);
            boolean m2949c = C3874cv.m2949c(context);
            C3874cv.m2944a(context);
            c3878d = m2949c ? (LocationManagerBase) C3819au.m2476a(context, m3091a, "com.amap.api.location.LocationManagerWrapper", C3878d.class, new Class[]{Context.class, Intent.class}, new Object[]{context, intent}) : new C3878d(context, intent);
        } catch (Throwable unused) {
            c3878d = new C3878d(context, intent);
        }
        return c3878d == null ? new C3878d(context, intent) : c3878d;
    }

    public static void setApiKey(String str) {
        try {
            AMapLocationClientOption.f1102a = str;
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationClient", "setApiKey");
        }
    }

    public void addGeoFenceAlert(String str, double d, double d2, float f, long j, PendingIntent pendingIntent) {
        try {
            this.f1101b.addGeoFenceAlert(str, d, d2, f, j, pendingIntent);
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationClient", "addGeoFenceAlert");
        }
    }

    public AMapLocation getLastKnownLocation() {
        try {
            if (this.f1101b != null) {
                return this.f1101b.getLastKnownLocation();
            }
            return null;
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationClient", "getLastKnownLocation");
            return null;
        }
    }

    public String getVersion() {
        return "3.3.0";
    }

    public boolean isStarted() {
        try {
            if (this.f1101b != null) {
                return this.f1101b.isStarted();
            }
            return false;
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationClient", "isStarted");
            return false;
        }
    }

    public void onDestroy() {
        try {
            this.f1101b.onDestroy();
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationClient", "onDestroy");
        }
    }

    public void removeGeoFenceAlert(PendingIntent pendingIntent) {
        try {
            this.f1101b.removeGeoFenceAlert(pendingIntent);
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationClient", "removeGeoFenceAlert 2");
        }
    }

    public void removeGeoFenceAlert(PendingIntent pendingIntent, String str) {
        try {
            this.f1101b.removeGeoFenceAlert(pendingIntent, str);
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationClient", "removeGeoFenceAlert 1");
        }
    }

    public void setLocationListener(AMapLocationListener aMapLocationListener) {
        try {
            if (aMapLocationListener == null) {
                throw new IllegalArgumentException("listener参数不能为null");
            }
            this.f1101b.setLocationListener(aMapLocationListener);
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationClient", "setLocationListener");
        }
    }

    public void setLocationOption(AMapLocationClientOption aMapLocationClientOption) {
        try {
            if (aMapLocationClientOption == null) {
                throw new IllegalArgumentException("LocationManagerOption参数不能为null");
            }
            this.f1101b.setLocationOption(aMapLocationClientOption);
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationClient", "setLocationOption");
        }
    }

    public void startAssistantLocation() {
        try {
            this.f1101b.startAssistantLocation();
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationClient", "startAssistantLocation");
        }
    }

    public void startLocation() {
        try {
            this.f1101b.startLocation();
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationClient", "startLocation");
        }
    }

    public void stopAssistantLocation() {
        try {
            this.f1101b.startAssistantLocation();
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationClient", "stopAssistantLocation");
        }
    }

    public void stopLocation() {
        try {
            this.f1101b.stopLocation();
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationClient", "stopLocation");
        }
    }

    public void unRegisterLocationListener(AMapLocationListener aMapLocationListener) {
        try {
            this.f1101b.unRegisterLocationListener(aMapLocationListener);
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationClient", "unRegisterLocationListener");
        }
    }
}
