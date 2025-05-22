package com.loc;

import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.CoordinateConverter;
import com.amap.api.location.DPoint;
import com.loc.C3878d;
import java.util.Iterator;

/* compiled from: GPSLocation.java */
/* renamed from: com.loc.g */
/* loaded from: classes4.dex */
public final class C3881g {

    /* renamed from: a */
    Handler f4197a;

    /* renamed from: b */
    Context f4198b;

    /* renamed from: c */
    LocationManager f4199c;

    /* renamed from: d */
    AMapLocationClientOption f4200d;

    /* renamed from: e */
    C3872ct f4201e;

    /* renamed from: f */
    CoordinateConverter f4202f;

    /* renamed from: l */
    private long f4208l = 0;

    /* renamed from: g */
    boolean f4203g = false;

    /* renamed from: h */
    long f4204h = 0;

    /* renamed from: i */
    LocationListener f4205i = new LocationListener() { // from class: com.loc.g.1
        /* JADX WARN: Code restructure failed: missing block: B:46:0x0118, code lost:
        
            if ((r9 - r17.f4210a.f4204h) > (r17.f4210a.f4200d.getInterval() - 8000)) goto L34;
         */
        @Override // android.location.LocationListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void onLocationChanged(Location location) {
            Message obtain;
            Handler handler;
            if (C3881g.this.f4197a != null) {
                C3881g.this.f4197a.removeMessages(8);
            }
            if (location == null) {
                return;
            }
            try {
                AMapLocation aMapLocation = new AMapLocation(location);
                Bundle extras = location.getExtras();
                int i = extras != null ? extras.getInt("satellites") : 0;
                if (C3876cx.m2975a(location, C3881g.this.f4206j) && !C3881g.this.f4200d.isMockEnable()) {
                    aMapLocation.setErrorCode(15);
                    location.setLatitude(0.0d);
                    location.setLongitude(0.0d);
                }
                C3881g.this.f4201e.m2925b(C3876cx.m2985b());
                if (C3880f.m3098a(location.getLatitude(), location.getLongitude()) && C3881g.this.f4200d.isOffset()) {
                    aMapLocation.setLocationType(1);
                    DPoint convert = C3881g.this.f4202f.coord(new DPoint(location.getLatitude(), location.getLongitude())).from(CoordinateConverter.CoordType.GPS).convert();
                    aMapLocation.setLatitude(convert.getLatitude());
                    aMapLocation.setLongitude(convert.getLongitude());
                } else {
                    aMapLocation.setLatitude(location.getLatitude());
                    aMapLocation.setLongitude(location.getLongitude());
                    aMapLocation.setLocationType(1);
                }
                aMapLocation.setSatellites(i);
                long m2985b = C3876cx.m2985b();
                if (m2985b - C3881g.this.f4204h < C3881g.this.f4200d.getInterval() - 200) {
                    if (C3881g.this.f4197a != null) {
                        obtain = Message.obtain();
                        if (C3881g.this.f4200d.getInterval() > 8000) {
                        }
                        if (C3881g.this.f4200d.getInterval() <= 8000) {
                            obtain.obj = aMapLocation;
                        }
                        obtain.what = 5;
                        if (aMapLocation.getErrorCode() == 0) {
                            handler = C3881g.this.f4197a;
                            handler.sendMessage(obtain);
                        }
                    }
                    if (!C3880f.f4190l) {
                        C3880f.f4190l = true;
                        C3875cw.m2955a(C3881g.this.f4198b, "pref", "colde", true);
                    }
                    if (C3881g.this.f4208l != 0) {
                        return;
                    } else {
                        return;
                    }
                }
                C3881g.this.f4204h = C3876cx.m2985b();
                if (C3881g.this.f4197a != null) {
                    obtain = Message.obtain();
                    obtain.obj = aMapLocation;
                    obtain.what = 2;
                    handler = C3881g.this.f4197a;
                    handler.sendMessage(obtain);
                }
                if (!C3880f.f4190l && !C3875cw.m2958b(C3881g.this.f4198b, "pref", "colde", false)) {
                    C3880f.f4190l = true;
                    C3875cw.m2955a(C3881g.this.f4198b, "pref", "colde", true);
                }
                if (C3881g.this.f4208l != 0 || C3881g.this.f4203g) {
                    return;
                }
                C3873cu.m2937b(C3881g.this.f4198b, C3881g.this.f4201e);
                C3881g.this.f4203g = true;
                return;
            } catch (Throwable th) {
                C3880f.m3097a(th, "GPSLocation", "onLocationChanged");
            }
            C3880f.m3097a(th, "GPSLocation", "onLocationChanged");
        }

        @Override // android.location.LocationListener
        public final void onProviderDisabled(String str) {
            try {
                if (!"gps".equals(str) || C3881g.this.f4197a == null) {
                    return;
                }
                C3881g.this.f4197a.sendEmptyMessage(3);
            } catch (Throwable th) {
                C3880f.m3097a(th, "GPSLocation", "onProviderDisabled");
            }
        }

        @Override // android.location.LocationListener
        public final void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public final void onStatusChanged(String str, int i, Bundle bundle) {
            if (i == 0 || i == 1) {
                try {
                    if (C3881g.this.f4197a != null) {
                        C3881g.this.f4197a.sendEmptyMessage(3);
                    }
                } catch (Throwable th) {
                    C3880f.m3097a(th, "GPSLocation", "onStatusChanged");
                }
            }
        }
    };

    /* renamed from: j */
    public int f4206j = 0;

    /* renamed from: k */
    GpsStatus f4207k = null;

    /* renamed from: m */
    private GpsStatus.Listener f4209m = new GpsStatus.Listener() { // from class: com.loc.g.2
        @Override // android.location.GpsStatus.Listener
        public final void onGpsStatusChanged(int i) {
            try {
                C3881g.this.f4207k = C3881g.this.f4199c.getGpsStatus(C3881g.this.f4207k);
                if (i != 4) {
                    return;
                }
                Iterator<GpsSatellite> it = C3881g.this.f4207k.getSatellites().iterator();
                int i2 = 0;
                int maxSatellites = C3881g.this.f4207k.getMaxSatellites();
                while (it.hasNext() && i2 < maxSatellites) {
                    if (it.next().usedInFix()) {
                        i2++;
                    }
                }
                C3881g.this.f4206j = i2;
            } catch (Throwable th) {
                C3880f.m3097a(th, "GPSLocation", "onGpsStatusChanged");
            }
        }
    };

    public C3881g(Context context, C3878d.d dVar) {
        this.f4201e = null;
        this.f4202f = null;
        this.f4198b = context;
        this.f4202f = new CoordinateConverter(this.f4198b.getApplicationContext());
        this.f4197a = dVar;
        this.f4199c = (LocationManager) this.f4198b.getSystemService(RequestParameters.SUBRESOURCE_LOCATION);
        this.f4201e = new C3872ct();
    }

    /* renamed from: a */
    public final void m3107a() {
        LocationManager locationManager = this.f4199c;
        if (locationManager == null) {
            return;
        }
        LocationListener locationListener = this.f4205i;
        if (locationListener != null) {
            locationManager.removeUpdates(locationListener);
        }
        GpsStatus.Listener listener = this.f4209m;
        if (listener != null) {
            this.f4199c.removeGpsStatusListener(listener);
        }
        Handler handler = this.f4197a;
        if (handler != null) {
            handler.removeMessages(8);
        }
        this.f4206j = 0;
        this.f4208l = 0L;
        this.f4204h = 0L;
        this.f4203g = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final void m3108b() {
        try {
            Looper myLooper = Looper.myLooper();
            if (myLooper == null) {
                myLooper = this.f4198b.getMainLooper();
            }
            Looper looper = myLooper;
            this.f4208l = C3876cx.m2985b();
            this.f4201e.m2922a(this.f4208l);
            try {
                this.f4199c.sendExtraCommand("gps", "force_xtra_injection", new Bundle());
            } catch (Throwable unused) {
            }
            this.f4199c.requestLocationUpdates("gps", (this.f4200d == null || this.f4200d.getInterval() >= 1000) ? 1000L : this.f4200d.getInterval(), 0.0f, this.f4205i, looper);
            this.f4199c.addGpsStatusListener(this.f4209m);
            if (this.f4197a == null || this.f4200d.getLocationMode() != AMapLocationClientOption.AMapLocationMode.Device_Sensors) {
                return;
            }
            Message obtain = Message.obtain();
            AMapLocation aMapLocation = new AMapLocation("");
            aMapLocation.setProvider("gps");
            aMapLocation.setErrorCode(14);
            aMapLocation.setLocationDetail("no enough satellites");
            aMapLocation.setLocationType(1);
            obtain.obj = aMapLocation;
            obtain.what = 8;
            this.f4197a.sendMessageDelayed(obtain, this.f4200d.getHttpTimeOut());
        } catch (SecurityException e) {
            if (AMapLocationClientOption.AMapLocationMode.Device_Sensors.equals(this.f4200d.getLocationMode())) {
                Message obtain2 = Message.obtain();
                AMapLocation aMapLocation2 = new AMapLocation("");
                aMapLocation2.setProvider("gps");
                aMapLocation2.setErrorCode(12);
                aMapLocation2.setLocationDetail(e.getMessage());
                aMapLocation2.setLocationType(1);
                obtain2.what = 2;
                obtain2.obj = aMapLocation2;
                Handler handler = this.f4197a;
                if (handler != null) {
                    handler.sendMessage(obtain2);
                }
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "GPSLocation", "requestLocationUpdates part2");
        }
    }
}
