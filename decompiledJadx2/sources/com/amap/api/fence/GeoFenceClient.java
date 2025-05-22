package com.amap.api.fence;

import android.app.PendingIntent;
import android.content.Context;
import com.amap.api.location.DPoint;
import com.loc.C3876cx;
import com.loc.C3880f;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class GeoFenceClient {
    public static final int GEOFENCE_IN = 1;
    public static final int GEOFENCE_OUT = 2;
    public static final int GEOFENCE_STAYED = 4;

    /* renamed from: a */
    Context f1063a;

    /* renamed from: b */
    private GeoFenceManagerBase f1064b;

    public GeoFenceClient(Context context) {
        this.f1063a = null;
        this.f1064b = null;
        try {
            if (context == null) {
                throw new IllegalArgumentException("Context参数不能为null");
            }
            this.f1063a = context.getApplicationContext();
            this.f1064b = C3876cx.m3012g(this.f1063a);
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceClient", "<init>");
        }
    }

    public void addGeoFence(DPoint dPoint, float f, String str) {
        try {
            this.f1064b.addRoundGeoFence(dPoint, f, str, null, -1L, null);
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceClient", "addGeoFence round");
        }
    }

    public void addGeoFence(String str, String str2) {
        try {
            this.f1064b.addDistrictGeoFence(str, str2);
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceClient", "addGeoFence district");
        }
    }

    public void addGeoFence(String str, String str2, DPoint dPoint, float f, int i, String str3) {
        try {
            this.f1064b.addNearbyGeoFence(str, str2, dPoint, f, i, str3);
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceClient", "addGeoFence searche");
        }
    }

    public void addGeoFence(String str, String str2, String str3, int i, String str4) {
        try {
            this.f1064b.addKeywordGeoFence(str, str2, str3, i, str4);
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceClient", "addGeoFence searche");
        }
    }

    public void addGeoFence(List<DPoint> list, String str) {
        try {
            this.f1064b.addPolygonGeoFence(list, str);
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceClient", "addGeoFence polygon");
        }
    }

    public PendingIntent createPendingIntent(String str) {
        try {
            return this.f1064b.createPendingIntent(str);
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceClient", "creatPendingIntent");
            return null;
        }
    }

    public List<GeoFence> getAllGeoFence() {
        ArrayList arrayList = new ArrayList();
        try {
            return this.f1064b.getAllGeoFence();
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceClient", "getGeoFenceList");
            return arrayList;
        }
    }

    public void removeGeoFence() {
        try {
            this.f1064b.removeGeoFence();
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceClient", "removeGeoFence");
        }
    }

    public boolean removeGeoFence(GeoFence geoFence) {
        try {
            return this.f1064b.removeGeoFence(geoFence);
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceClient", "removeGeoFence1");
            return false;
        }
    }

    public void setActivateAction(int i) {
        try {
            this.f1064b.setActivateAction(i);
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceClient", "setActivatesAction");
        }
    }

    public void setGeoFenceListener(GeoFenceListener geoFenceListener) {
        try {
            this.f1064b.setGeoFenceListener(geoFenceListener);
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceClient", "setGeoFenceListener");
        }
    }
}
