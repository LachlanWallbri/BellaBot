package com.loc;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
import com.amap.api.fence.GeoFence;
import com.amap.api.fence.GeoFenceListener;
import com.amap.api.fence.GeoFenceManagerBase;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.DPoint;
import com.iflytek.cloud.SpeechEvent;
import com.pudutech.robot.peripherals.disinfection.device.BasePeripheral;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: GeoFenceManager.java */
/* renamed from: com.loc.a */
/* loaded from: classes4.dex */
public class C3798a implements GeoFenceManagerBase {

    /* renamed from: b */
    Context f3524b;

    /* renamed from: a */
    C3873cu f3523a = null;

    /* renamed from: c */
    PendingIntent f3525c = null;

    /* renamed from: d */
    String f3526d = null;

    /* renamed from: e */
    GeoFenceListener f3527e = null;

    /* renamed from: f */
    volatile int f3528f = 1;

    /* renamed from: g */
    ArrayList<GeoFence> f3529g = new ArrayList<>();

    /* renamed from: h */
    c f3530h = null;

    /* renamed from: i */
    Object f3531i = new Object();

    /* renamed from: j */
    a f3532j = null;

    /* renamed from: k */
    b f3533k = null;

    /* renamed from: l */
    volatile boolean f3534l = false;

    /* renamed from: m */
    volatile boolean f3535m = false;

    /* renamed from: n */
    C3825b f3536n = null;

    /* renamed from: o */
    C3852c f3537o = null;

    /* renamed from: p */
    AMapLocationClient f3538p = null;

    /* renamed from: q */
    volatile AMapLocation f3539q = null;

    /* renamed from: r */
    long f3540r = 0;

    /* renamed from: s */
    AMapLocationClientOption f3541s = null;

    /* renamed from: t */
    int f3542t = 0;

    /* renamed from: u */
    AMapLocationListener f3543u = new AMapLocationListener() { // from class: com.loc.a.1
        /* JADX WARN: Removed duplicated region for block: B:11:0x005c A[Catch: all -> 0x0093, TryCatch #0 {all -> 0x0093, blocks: (B:2:0x0000, B:4:0x000d, B:6:0x0017, B:8:0x0051, B:11:0x005c, B:13:0x0067, B:14:0x0079, B:16:0x0087, B:19:0x0027), top: B:1:0x0000 }] */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0051 A[Catch: all -> 0x0093, TryCatch #0 {all -> 0x0093, blocks: (B:2:0x0000, B:4:0x000d, B:6:0x0017, B:8:0x0051, B:11:0x005c, B:13:0x0067, B:14:0x0079, B:16:0x0087, B:19:0x0027), top: B:1:0x0000 }] */
        @Override // com.amap.api.location.AMapLocationListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void onLocationChanged(AMapLocation aMapLocation) {
            int i;
            boolean z;
            try {
                C3798a.this.f3539q = aMapLocation;
                if (aMapLocation != null) {
                    i = aMapLocation.getErrorCode();
                    if (aMapLocation.getErrorCode() == 0) {
                        C3798a.this.f3540r = C3876cx.m2985b();
                        C3798a.this.m2374a(5, null, 0L);
                        z = true;
                        if (!z) {
                            C3798a.this.f3542t = 0;
                            C3798a.this.m2374a(6, null, 0L);
                            return;
                        }
                        Bundle bundle = new Bundle();
                        if (!C3798a.this.f3534l) {
                            C3798a.this.m2372a(7);
                            bundle.putLong("interval", 2000L);
                            C3798a.this.m2374a(8, bundle, 2000L);
                        }
                        C3798a.this.f3542t++;
                        if (C3798a.this.f3542t >= 3) {
                            bundle.putInt(GeoFence.BUNDLE_KEY_LOCERRORCODE, i);
                            C3798a.this.m2373a(1002, bundle);
                            return;
                        }
                        return;
                    }
                    C3798a c3798a = C3798a.this;
                    C3798a.m2359a("定位失败", aMapLocation.getErrorCode(), aMapLocation.getErrorInfo(), "locationDetail:" + aMapLocation.getLocationDetail());
                } else {
                    i = 8;
                }
                z = false;
                if (!z) {
                }
            } catch (Throwable unused) {
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: GeoFenceManager.java */
    /* renamed from: com.loc.a$a */
    /* loaded from: classes4.dex */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            try {
                switch (message.what) {
                    case 0:
                        C3798a.this.m2380b(message.getData());
                        return;
                    case 1:
                        C3798a.this.m2381c(message.getData());
                        return;
                    case 2:
                        C3798a.this.m2383e(message.getData());
                        return;
                    case 3:
                        C3798a.this.m2382d(message.getData());
                        return;
                    case 4:
                        C3798a.this.m2384f(message.getData());
                        return;
                    case 5:
                        C3798a.this.m2378b();
                        return;
                    case 6:
                        C3798a.this.m2377a(C3798a.this.f3539q);
                        return;
                    case 7:
                        C3798a.this.m2371a();
                        return;
                    case 8:
                        C3798a.this.m2386h(message.getData());
                        return;
                    case 9:
                        C3798a.this.m2375a(message.getData());
                        return;
                    default:
                        return;
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: GeoFenceManager.java */
    /* renamed from: com.loc.a$b */
    /* loaded from: classes4.dex */
    public static class b extends HandlerThread {
        public b(String str) {
            super(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: GeoFenceManager.java */
    /* renamed from: com.loc.a$c */
    /* loaded from: classes4.dex */
    public class c extends Handler {
        public c() {
        }

        public c(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            Bundle data = message.getData();
            switch (message.what) {
                case 1000:
                    C3798a.this.m2385g(data);
                    return;
                case 1001:
                    try {
                        C3798a.this.m2376a((GeoFence) data.getParcelable("geoFence"));
                        return;
                    } catch (Throwable th) {
                        th.printStackTrace();
                        return;
                    }
                case 1002:
                    try {
                        C3798a.this.m2379b(data.getInt(GeoFence.BUNDLE_KEY_LOCERRORCODE));
                        return;
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                        return;
                    }
                default:
                    return;
            }
        }
    }

    public C3798a(Context context) {
        this.f3524b = null;
        try {
            this.f3524b = context.getApplicationContext();
            m2368c();
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceManger", "<init>");
        }
    }

    /* renamed from: a */
    public static float m2355a(DPoint dPoint, List<DPoint> list) {
        float f = Float.MAX_VALUE;
        if (dPoint != null && list != null && !list.isEmpty()) {
            Iterator<DPoint> it = list.iterator();
            while (it.hasNext()) {
                f = Math.min(f, C3876cx.m2963a(dPoint, it.next()));
            }
        }
        return f;
    }

    /* renamed from: a */
    private int m2356a(List<GeoFence> list) {
        try {
            if (this.f3529g == null) {
                this.f3529g = new ArrayList<>();
            }
            Iterator<GeoFence> it = list.iterator();
            while (it.hasNext()) {
                m2364b(it.next());
            }
            return 0;
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceManager", "addGeoFenceList");
            m2359a("添加围栏失败", 8, th.getMessage(), new String[0]);
            return 8;
        }
    }

    /* renamed from: a */
    private static Bundle m2357a(GeoFence geoFence, String str, String str2, int i, int i2) {
        Bundle bundle = new Bundle();
        if (str == null) {
            str = "";
        }
        bundle.putString(GeoFence.BUNDLE_KEY_FENCEID, str);
        bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str2);
        bundle.putInt("event", i);
        bundle.putInt(GeoFence.BUNDLE_KEY_LOCERRORCODE, i2);
        bundle.putParcelable(GeoFence.BUNDLE_KEY_FENCE, geoFence);
        return bundle;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a3  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private GeoFence m2358a(Bundle bundle, boolean z) {
        String str;
        PendingIntent pendingIntent;
        C3873cu c3873cu;
        GeoFence geoFence = new GeoFence();
        ArrayList arrayList = new ArrayList();
        DPoint dPoint = new DPoint();
        if (z) {
            geoFence.setType(1);
            arrayList = bundle.getParcelableArrayList("points");
            if (arrayList != null) {
                dPoint = m2365b(arrayList);
            }
            geoFence.setMaxDis2Center(m2363b(dPoint, arrayList));
            geoFence.setMinDis2Center(m2355a(dPoint, arrayList));
        } else {
            geoFence.setType(0);
            dPoint = (DPoint) bundle.getParcelable("point");
            if (dPoint != null) {
                arrayList.add(dPoint);
            }
            float f = bundle.getFloat("radius", 1000.0f);
            geoFence.setRadius(f);
            geoFence.setMinDis2Center(f);
            geoFence.setMaxDis2Center(f);
        }
        geoFence.setActivatesAction(this.f3528f);
        geoFence.setCustomId(bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(arrayList);
        geoFence.setPointList(arrayList2);
        geoFence.setCenter(dPoint);
        long j = -1;
        try {
            str = bundle.getString(GeoFence.BUNDLE_KEY_FENCEID);
            try {
                j = bundle.getLong("expiration", -1L);
                pendingIntent = (PendingIntent) bundle.getParcelable("pIntent");
            } catch (Throwable unused) {
                pendingIntent = null;
                if (TextUtils.isEmpty(str)) {
                }
                geoFence.setPendingIntentAction(this.f3526d);
                geoFence.setExpiration(j);
                if (pendingIntent == null) {
                }
                geoFence.setPendingIntent(pendingIntent);
                c3873cu = this.f3523a;
                if (c3873cu != null) {
                }
                return geoFence;
            }
        } catch (Throwable unused2) {
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            geoFence.setFenceId(str);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(C3852c.m2742a());
            geoFence.setFenceId(sb.toString());
        }
        geoFence.setPendingIntentAction(this.f3526d);
        geoFence.setExpiration(j);
        if (pendingIntent == null) {
            pendingIntent = this.f3525c;
        }
        geoFence.setPendingIntent(pendingIntent);
        c3873cu = this.f3523a;
        if (c3873cu != null) {
            c3873cu.m2938a(this.f3524b, 2);
        }
        return geoFence;
    }

    /* renamed from: a */
    static void m2359a(String str, int i, String str2, String... strArr) {
        Log.i("fenceErrLog", "===========================================");
        Log.i("fenceErrLog", "              " + str + "                ");
        Log.i("fenceErrLog", "===========================================");
        Log.i("fenceErrLog", "errorCode:" + i);
        Log.i("fenceErrLog", "错误信息:" + str2);
        if (strArr != null && strArr.length > 0) {
            for (String str3 : strArr) {
                Log.i("fenceErrLog", str3);
            }
        }
        Log.i("fenceErrLog", "===========================================");
    }

    /* renamed from: a */
    private static boolean m2360a(double d, double d2, double d3, double d4, double d5, double d6) {
        return Math.abs(((d3 - d) * (d6 - d2)) - ((d5 - d) * (d4 - d2))) < 1.0E-9d && (d - d3) * (d - d5) <= 0.0d && (d2 - d4) * (d2 - d6) <= 0.0d;
    }

    /* renamed from: a */
    private static boolean m2361a(GeoFence geoFence, int i) {
        boolean z = false;
        if ((i & 1) == 1) {
            try {
                if (geoFence.getStatus() == 1) {
                    z = true;
                }
            } catch (Throwable th) {
                C3880f.m3097a(th, "Utils", "remindStatus");
                return z;
            }
        }
        if ((i & 2) == 2 && geoFence.getStatus() == 2) {
            z = true;
        }
        if ((i & 4) != 4) {
            return z;
        }
        if (geoFence.getStatus() == 3) {
            return true;
        }
        return z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:74:0x0146, code lost:
    
        if ((r24 % 2) != 0) goto L31;
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x014c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0033 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0140 A[SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean m2362a(AMapLocation aMapLocation, GeoFence geoFence) {
        boolean z;
        boolean z2;
        boolean z3;
        try {
        } catch (Throwable th) {
            th = th;
            z = false;
        }
        if (C3876cx.m2976a(aMapLocation) && geoFence != null && geoFence.getPointList() != null && !geoFence.getPointList().isEmpty()) {
            int type = geoFence.getType();
            if (type != 0) {
                if (type != 1) {
                    if (type != 2) {
                        if (type != 3) {
                        }
                    }
                }
                z = false;
                for (List<DPoint> list : geoFence.getPointList()) {
                    try {
                        double longitude = aMapLocation.getLongitude();
                        double latitude = aMapLocation.getLatitude();
                        double latitude2 = aMapLocation.getLatitude();
                        if (list.size() >= 3) {
                            if (!list.get(0).equals(list.get(list.size() - 1))) {
                                list.add(list.get(0));
                            }
                            int i = 0;
                            int i2 = 0;
                            while (true) {
                                if (i < list.size() - 1) {
                                    double longitude2 = list.get(i).getLongitude();
                                    double latitude3 = list.get(i).getLatitude();
                                    int i3 = i + 1;
                                    double longitude3 = list.get(i3).getLongitude();
                                    double latitude4 = list.get(i3).getLatitude();
                                    if (m2360a(longitude, latitude, longitude2, latitude3, longitude3, latitude4)) {
                                        break;
                                    }
                                    double d = latitude4 - latitude3;
                                    if (Math.abs(d) >= 1.0E-9d) {
                                        if (m2360a(longitude2, latitude3, longitude, latitude, 180.0d, latitude2)) {
                                            if (latitude3 <= latitude4) {
                                            }
                                            i2++;
                                        } else if (!m2360a(longitude3, latitude4, longitude, latitude, 180.0d, latitude2)) {
                                            double d2 = longitude3 - longitude2;
                                            double d3 = latitude2 - latitude;
                                            double d4 = 180.0d - longitude;
                                            double d5 = (d2 * d3) - (d * d4);
                                            if (d5 != 0.0d) {
                                                double d6 = latitude3 - latitude;
                                                double d7 = longitude2 - longitude;
                                                double d8 = ((d4 * d6) - (d3 * d7)) / d5;
                                                double d9 = ((d6 * d2) - (d7 * d)) / d5;
                                                if (d8 >= 0.0d && d8 <= 1.0d && d9 >= 0.0d && d9 <= 1.0d) {
                                                    z3 = true;
                                                    if (!z3) {
                                                        i2++;
                                                    }
                                                }
                                            }
                                            z3 = false;
                                            if (!z3) {
                                            }
                                        } else if (latitude4 > latitude3) {
                                            i2++;
                                        }
                                    }
                                    i = i3;
                                }
                            }
                            z2 = true;
                            if (!z2) {
                                z = true;
                            }
                        }
                        z2 = false;
                        if (!z2) {
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        C3880f.m3097a(th, "Utils", "isInGeoFence");
                        return z;
                    }
                }
                return z;
            }
            DPoint center = geoFence.getCenter();
            if (C3876cx.m2964a(new double[]{center.getLatitude(), center.getLongitude(), aMapLocation.getLatitude(), aMapLocation.getLongitude()}) <= geoFence.getRadius()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    public static float m2363b(DPoint dPoint, List<DPoint> list) {
        float f = Float.MIN_VALUE;
        if (dPoint != null && list != null && !list.isEmpty()) {
            Iterator<DPoint> it = list.iterator();
            while (it.hasNext()) {
                f = Math.max(f, C3876cx.m2963a(dPoint, it.next()));
            }
        }
        return f;
    }

    /* renamed from: b */
    private int m2364b(GeoFence geoFence) {
        try {
            if (this.f3529g == null) {
                this.f3529g = new ArrayList<>();
            }
            if (this.f3529g.contains(geoFence)) {
                return 17;
            }
            this.f3529g.add(geoFence);
            return 0;
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceManager", "addGeoFence2List");
            m2359a("添加围栏失败", 8, th.getMessage(), new String[0]);
            return 8;
        }
    }

    /* renamed from: b */
    private static DPoint m2365b(List<DPoint> list) {
        DPoint dPoint = new DPoint();
        if (list == null) {
            return dPoint;
        }
        try {
            double d = 0.0d;
            double d2 = 0.0d;
            for (DPoint dPoint2 : list) {
                d += dPoint2.getLatitude();
                d2 += dPoint2.getLongitude();
            }
            return new DPoint(C3876cx.m2995c(d / list.size()), C3876cx.m2995c(d2 / list.size()));
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceUtil", "getPolygonCenter");
            return dPoint;
        }
    }

    /* renamed from: b */
    private static boolean m2366b(AMapLocation aMapLocation, GeoFence geoFence) {
        boolean z = true;
        try {
            if (!m2362a(aMapLocation, geoFence)) {
                if (geoFence.getStatus() != 2) {
                    try {
                        geoFence.setStatus(2);
                        geoFence.setEnterTime(-1L);
                    } catch (Throwable th) {
                        th = th;
                        C3880f.m3097a(th, "Utils", "isFenceStatusChanged");
                        return z;
                    }
                }
                z = false;
            } else if (geoFence.getEnterTime() == -1) {
                if (geoFence.getStatus() != 1) {
                    geoFence.setEnterTime(C3876cx.m2985b());
                    geoFence.setStatus(1);
                }
                z = false;
            } else {
                if (geoFence.getStatus() != 3 && C3876cx.m2985b() - geoFence.getEnterTime() > 600000) {
                    geoFence.setStatus(3);
                }
                z = false;
            }
        } catch (Throwable th2) {
            th = th2;
            z = false;
        }
        return z;
    }

    /* renamed from: c */
    private static int m2367c(int i) {
        int i2;
        switch (i) {
            case 10000:
                i2 = 0;
                break;
            case 10001:
            case BasePeripheral.RETRY_CLOSE /* 10002 */:
            case SpeechEvent.EVENT_IST_CACHE_LEFT /* 10007 */:
            case SpeechEvent.EVENT_IST_RESULT_TIME /* 10008 */:
            case SpeechEvent.EVENT_IST_SYNC_ID /* 10009 */:
            case SpeechEvent.EVENT_VOLUME /* 10012 */:
            case SpeechEvent.EVENT_VAD_EOS /* 10013 */:
                i2 = 7;
                break;
            case 10003:
            case SpeechEvent.EVENT_IST_AUDIO_FILE /* 10004 */:
            case 10005:
            case SpeechEvent.EVENT_IST_UPLOAD_BYTES /* 10006 */:
            case SpeechEvent.EVENT_SESSION_BEGIN /* 10010 */:
            case SpeechEvent.EVENT_SESSION_END /* 10011 */:
            case 10014:
            case 10015:
            case 10016:
            case 10017:
                i2 = 4;
                break;
            default:
                switch (i) {
                    case 20000:
                    case 20001:
                    case 20002:
                        i2 = 1;
                        break;
                    case 20003:
                    default:
                        i2 = 8;
                        break;
                }
        }
        if (i2 != 0) {
            m2359a("添加围栏失败", i2, "searchErrCode is " + i2, new String[0]);
        }
        return i2;
    }

    /* renamed from: c */
    private void m2368c() {
        if (this.f3535m) {
            return;
        }
        try {
            this.f3530h = Looper.myLooper() == null ? new c(this.f3524b.getMainLooper()) : new c();
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceManger", "init 1");
        }
        try {
            this.f3533k = new b("fenceActionThread");
            this.f3533k.setPriority(5);
            this.f3533k.start();
            this.f3532j = new a(this.f3533k.getLooper());
        } catch (Throwable th2) {
            C3880f.m3097a(th2, "GeoFenceManger", "init 2");
        }
        try {
            Context context = this.f3524b;
            this.f3536n = new C3825b();
            this.f3537o = new C3852c();
            this.f3541s = new AMapLocationClientOption();
            this.f3538p = new AMapLocationClient(this.f3524b);
            this.f3541s.setLocationCacheEnable(false);
            this.f3538p.setLocationListener(this.f3543u);
            if (this.f3523a == null) {
                this.f3523a = new C3873cu();
            }
        } catch (Throwable th3) {
            C3880f.m3097a(th3, "GeoFenceManger", "init 3");
        }
        this.f3535m = true;
        try {
            if (this.f3526d == null || this.f3525c != null) {
                return;
            }
            createPendingIntent(this.f3526d);
        } catch (Throwable th4) {
            C3880f.m3097a(th4, "GeoFenceManger", "init 4");
        }
    }

    /* renamed from: d */
    private void m2369d() {
        if (this.f3535m) {
            try {
                synchronized (this.f3531i) {
                    if (this.f3532j != null) {
                        this.f3532j.removeCallbacksAndMessages(null);
                    }
                    this.f3532j = null;
                }
            } catch (Throwable th) {
                C3880f.m3097a(th, "GeoFenceManager", "destroyActionHandler");
            }
            if (this.f3538p != null) {
                this.f3538p.stopLocation();
                this.f3538p.onDestroy();
            }
            this.f3538p = null;
            if (this.f3533k != null) {
                if (Build.VERSION.SDK_INT >= 18) {
                    this.f3533k.quitSafely();
                } else {
                    this.f3533k.quit();
                }
            }
            this.f3533k = null;
            if (this.f3529g != null) {
                this.f3529g.clear();
                this.f3529g = null;
            }
            this.f3536n = null;
            if (this.f3525c != null) {
                this.f3525c.cancel();
            }
            this.f3525c = null;
            if (this.f3523a != null) {
                this.f3523a.m2940b(this.f3524b);
            }
            this.f3535m = false;
        }
    }

    /* renamed from: e */
    private boolean m2370e() {
        return this.f3539q != null && C3876cx.m2976a(this.f3539q) && C3876cx.m2985b() - this.f3540r < 10000;
    }

    /* renamed from: a */
    final void m2371a() {
        try {
            if (this.f3538p != null) {
                try {
                    if (this.f3534l) {
                        m2372a(8);
                    }
                    if (this.f3538p != null) {
                        this.f3538p.stopLocation();
                    }
                    this.f3534l = false;
                } catch (Throwable unused) {
                }
                this.f3541s.setOnceLocation(true);
                this.f3538p.setLocationOption(this.f3541s);
                this.f3538p.startLocation();
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceManager", "doStartOnceLocation");
        }
    }

    /* renamed from: a */
    final void m2372a(int i) {
        try {
            synchronized (this.f3531i) {
                if (this.f3532j != null) {
                    this.f3532j.removeMessages(i);
                }
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceManager", "removeActionHandlerMessage");
        }
    }

    /* renamed from: a */
    final void m2373a(int i, Bundle bundle) {
        try {
            if (this.f3530h != null) {
                Message obtainMessage = this.f3530h.obtainMessage();
                obtainMessage.what = i;
                obtainMessage.setData(bundle);
                this.f3530h.sendMessage(obtainMessage);
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceManager", "sendResultHandlerMessage");
        }
    }

    /* renamed from: a */
    final void m2374a(int i, Bundle bundle, long j) {
        try {
            synchronized (this.f3531i) {
                if (this.f3532j != null) {
                    Message obtainMessage = this.f3532j.obtainMessage();
                    obtainMessage.what = i;
                    obtainMessage.setData(bundle);
                    this.f3532j.sendMessageDelayed(obtainMessage, j);
                }
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceManager", "sendActionHandlerMessage");
        }
    }

    /* renamed from: a */
    final void m2375a(Bundle bundle) {
        int i = 1;
        if (bundle != null) {
            try {
                i = bundle.getInt("activatesAction", 1);
            } catch (Throwable th) {
                C3880f.m3097a(th, "GeoFenceManager", "doSetActivatesAction");
                return;
            }
        }
        if (this.f3528f != i) {
            if (this.f3529g != null && !this.f3529g.isEmpty()) {
                Iterator<GeoFence> it = this.f3529g.iterator();
                while (it.hasNext()) {
                    GeoFence next = it.next();
                    next.setStatus(0);
                    next.setEnterTime(-1L);
                }
            }
            if (this.f3532j != null) {
                if (m2370e()) {
                    m2374a(6, null, 0L);
                } else {
                    m2372a(7);
                    m2374a(7, null, 1000L);
                }
            }
        }
        this.f3528f = i;
    }

    /* renamed from: a */
    final void m2376a(GeoFence geoFence) {
        try {
            if (this.f3524b != null) {
                if (this.f3525c == null && geoFence.getPendingIntent() == null) {
                    return;
                }
                Intent intent = new Intent();
                intent.putExtras(m2357a(geoFence, geoFence.getFenceId(), geoFence.getCustomId(), geoFence.getStatus(), 0));
                if (this.f3526d != null) {
                    intent.setAction(this.f3526d);
                }
                intent.setPackage(C3885k.m3124c(this.f3524b));
                if (geoFence.getPendingIntent() != null) {
                    geoFence.getPendingIntent().send(this.f3524b, 0, intent);
                } else {
                    this.f3525c.send(this.f3524b, 0, intent);
                }
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceManager", "resultTriggerGeoFence");
        }
    }

    /* renamed from: a */
    final void m2377a(AMapLocation aMapLocation) {
        try {
            if (this.f3529g == null || this.f3529g.isEmpty() || aMapLocation == null || aMapLocation.getErrorCode() != 0) {
                return;
            }
            Iterator<GeoFence> it = this.f3529g.iterator();
            while (it.hasNext()) {
                GeoFence next = it.next();
                if (m2366b(aMapLocation, next) && m2361a(next, this.f3528f)) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("geoFence", next);
                    m2373a(1001, bundle);
                }
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceManager", "doCheckFence");
        }
    }

    @Override // com.amap.api.fence.GeoFenceManagerBase
    public void addDistrictGeoFence(String str, String str2) {
        try {
            m2368c();
            Bundle bundle = new Bundle();
            bundle.putString("keyword", str);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str2);
            m2374a(4, bundle, 0L);
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceManager", "addDistricetGeoFence");
        }
    }

    @Override // com.amap.api.fence.GeoFenceManagerBase
    public void addKeywordGeoFence(String str, String str2, String str3, int i, String str4) {
        try {
            m2368c();
            if (i <= 0) {
                i = 10;
            }
            if (i > 25) {
                i = 25;
            }
            Bundle bundle = new Bundle();
            bundle.putString("keyword", str);
            bundle.putString("poiType", str2);
            bundle.putString("city", str3);
            bundle.putInt("size", i);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str4);
            m2374a(2, bundle, 0L);
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceManager", "addKeywordGeoFence");
        }
    }

    @Override // com.amap.api.fence.GeoFenceManagerBase
    public void addNearbyGeoFence(String str, String str2, DPoint dPoint, float f, int i, String str3) {
        try {
            m2368c();
            if (f <= 0.0f || f > 50000.0f) {
                f = 3000.0f;
            }
            if (i <= 0) {
                i = 10;
            }
            if (i > 25) {
                i = 25;
            }
            Bundle bundle = new Bundle();
            bundle.putString("keyword", str);
            bundle.putString("poiType", str2);
            bundle.putParcelable("centerPoint", dPoint);
            bundle.putFloat("aroundRadius", f);
            bundle.putInt("size", i);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str3);
            m2374a(3, bundle, 0L);
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceManager", "addNearbyGeoFence");
        }
    }

    @Override // com.amap.api.fence.GeoFenceManagerBase
    public void addPolygonGeoFence(List<DPoint> list, String str) {
        try {
            m2368c();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("points", new ArrayList<>(list));
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            m2374a(1, bundle, 0L);
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceManager", "addPolygonGeoFence");
        }
    }

    @Override // com.amap.api.fence.GeoFenceManagerBase
    public void addRoundGeoFence(DPoint dPoint, float f, String str, String str2, long j, PendingIntent pendingIntent) {
        try {
            m2368c();
            if (f <= 0.0f) {
                f = 1000.0f;
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable("point", dPoint);
            bundle.putFloat("radius", f);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            bundle.putString(GeoFence.BUNDLE_KEY_FENCEID, str2);
            bundle.putLong("expiration", j);
            bundle.putParcelable("pIntent", pendingIntent);
            m2374a(0, bundle, 0L);
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceManager", "addRoundGeoFence");
        }
    }

    /* renamed from: b */
    final void m2378b() {
        float f;
        try {
            if (C3876cx.m2976a(this.f3539q)) {
                AMapLocation aMapLocation = this.f3539q;
                ArrayList<GeoFence> arrayList = this.f3529g;
                float f2 = Float.MAX_VALUE;
                if (aMapLocation != null && aMapLocation.getErrorCode() == 0 && arrayList != null && !arrayList.isEmpty()) {
                    DPoint dPoint = new DPoint(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                    for (GeoFence geoFence : arrayList) {
                        float m2963a = C3876cx.m2963a(dPoint, geoFence.getCenter());
                        if (m2963a > geoFence.getMinDis2Center() && m2963a < geoFence.getMaxDis2Center()) {
                            f = 0.0f;
                            break;
                        }
                        if (m2963a > geoFence.getMaxDis2Center()) {
                            f2 = Math.min(f2, m2963a - geoFence.getMaxDis2Center());
                        }
                        if (m2963a < geoFence.getMinDis2Center()) {
                            f2 = Math.min(f2, geoFence.getMinDis2Center() - m2963a);
                        }
                    }
                }
                f = f2;
                if (f < 1000.0f) {
                    m2372a(7);
                    Bundle bundle = new Bundle();
                    bundle.putLong("interval", 2000L);
                    m2374a(8, bundle, 1000L);
                    return;
                }
                if (f < 5000.0f) {
                    m2372a(7);
                    m2374a(7, null, 10000L);
                } else {
                    m2372a(7);
                    m2374a(7, null, ((f - 4000.0f) / 100.0f) * 1000.0f);
                }
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceManager", "doCheckLocationPolicy");
        }
    }

    /* renamed from: b */
    final void m2379b(int i) {
        try {
            if (this.f3524b == null || this.f3525c == null) {
                return;
            }
            Intent intent = new Intent();
            intent.putExtras(m2357a(null, null, null, 4, i));
            this.f3525c.send(this.f3524b, 0, intent);
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceManager", "resultRemindLocationError");
        }
    }

    /* renamed from: b */
    final void m2380b(Bundle bundle) {
        String str;
        try {
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            int i = 1;
            if (bundle == null || bundle.isEmpty()) {
                str = "";
            } else {
                DPoint dPoint = (DPoint) bundle.getParcelable("point");
                str = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                if (dPoint != null) {
                    if (dPoint.getLatitude() <= 90.0d && dPoint.getLatitude() >= -90.0d && dPoint.getLongitude() <= 180.0d && dPoint.getLongitude() >= -180.0d) {
                        GeoFence m2358a = m2358a(bundle, false);
                        i = m2364b(m2358a);
                        if (i == 0) {
                            arrayList.add(m2358a);
                        }
                    }
                    m2359a("添加围栏失败", 1, "经纬度错误，传入的纬度：" + dPoint.getLatitude() + "传入的经度:" + dPoint.getLongitude(), new String[0]);
                }
            }
            Bundle bundle2 = new Bundle();
            bundle2.putInt(AUserTrack.UTKEY_ERROR_CODE, i);
            bundle2.putParcelableArrayList("resultList", arrayList);
            bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            m2373a(1000, bundle2);
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceManager", "doAddGeoFence_round");
        }
    }

    /* renamed from: c */
    final void m2381c(Bundle bundle) {
        String str;
        GeoFence m2358a;
        try {
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            int i = 1;
            if (bundle == null || bundle.isEmpty()) {
                str = "";
            } else {
                ArrayList parcelableArrayList = bundle.getParcelableArrayList("points");
                str = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                if (parcelableArrayList != null && parcelableArrayList.size() > 2 && (i = m2364b((m2358a = m2358a(bundle, true)))) == 0) {
                    arrayList.add(m2358a);
                }
            }
            Bundle bundle2 = new Bundle();
            bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            bundle2.putInt(AUserTrack.UTKEY_ERROR_CODE, i);
            bundle2.putParcelableArrayList("resultList", arrayList);
            m2373a(1000, bundle2);
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceManager", "doAddGeoFence_polygon");
        }
    }

    @Override // com.amap.api.fence.GeoFenceManagerBase
    public PendingIntent createPendingIntent(String str) {
        try {
            m2368c();
            Intent intent = new Intent();
            intent.setPackage(C3885k.m3124c(this.f3524b));
            intent.setAction(str);
            this.f3525c = PendingIntent.getBroadcast(this.f3524b, 0, intent, 0);
            this.f3526d = str;
            if (this.f3529g != null && !this.f3529g.isEmpty()) {
                Iterator<GeoFence> it = this.f3529g.iterator();
                while (it.hasNext()) {
                    GeoFence next = it.next();
                    next.setPendingIntent(this.f3525c);
                    next.setPendingIntentAction(this.f3526d);
                }
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceManager", "createPendingIntent");
        }
        return this.f3525c;
    }

    /* renamed from: d */
    final void m2382d(Bundle bundle) {
        String str;
        int m2367c;
        try {
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            int i = 1;
            if (bundle == null || bundle.isEmpty()) {
                str = "";
            } else {
                String string = bundle.getString("keyword");
                String string2 = bundle.getString("poiType");
                DPoint dPoint = (DPoint) bundle.getParcelable("centerPoint");
                float f = bundle.getFloat("aroundRadius", 3000.0f);
                int i2 = bundle.getInt("size", 10);
                str = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                if (dPoint != null && !TextUtils.isEmpty(string)) {
                    if (dPoint.getLatitude() <= 90.0d && dPoint.getLatitude() >= -90.0d && dPoint.getLongitude() <= 180.0d && dPoint.getLongitude() >= -180.0d) {
                        String m2514a = this.f3536n.m2514a(this.f3524b, "http://restapi.amap.com/v3/place/around?", string, string2, String.valueOf(i2), String.valueOf(C3876cx.m2995c(dPoint.getLatitude())), String.valueOf(C3876cx.m2995c(dPoint.getLongitude())), String.valueOf(Float.valueOf(f).intValue()));
                        if (m2514a != null) {
                            List<GeoFence> arrayList2 = new ArrayList<>();
                            Bundle bundle2 = new Bundle();
                            bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
                            bundle2.putString("pendingIntentAction", this.f3526d);
                            bundle2.putLong("expiration", -1L);
                            bundle2.putInt("activatesAction", this.f3528f);
                            bundle2.putFloat("geoRadius", 200.0f);
                            C3852c c3852c = this.f3537o;
                            int m2744b = C3852c.m2744b(m2514a, arrayList2, bundle2);
                            if (m2744b != 10000) {
                                m2367c = m2367c(m2744b);
                            } else if (arrayList2.isEmpty()) {
                                i = 16;
                            } else {
                                m2367c = m2356a(arrayList2);
                                if (m2367c == 0) {
                                    arrayList.addAll(arrayList2);
                                }
                            }
                            i = m2367c;
                        } else {
                            i = 4;
                        }
                    }
                    m2359a("添加围栏失败", 1, "经纬度错误，传入的纬度：" + dPoint.getLatitude() + "传入的经度:" + dPoint.getLongitude(), new String[0]);
                }
            }
            Bundle bundle3 = new Bundle();
            bundle3.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            bundle3.putInt(AUserTrack.UTKEY_ERROR_CODE, i);
            bundle3.putParcelableArrayList("resultList", arrayList);
            m2373a(1000, bundle3);
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceManager", "doAddGeoFence_nearby");
        }
    }

    /* renamed from: e */
    final void m2383e(Bundle bundle) {
        String str;
        try {
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            int i = 1;
            if (bundle == null || bundle.isEmpty()) {
                str = "";
            } else {
                String string = bundle.getString("keyword");
                String string2 = bundle.getString("poiType");
                String string3 = bundle.getString("city");
                int i2 = bundle.getInt("size", 10);
                str = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                Bundle bundle2 = new Bundle();
                bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
                bundle2.putString("pendingIntentAction", this.f3526d);
                bundle2.putLong("expiration", -1L);
                bundle2.putInt("activatesAction", this.f3528f);
                bundle2.putFloat("geoRadius", 1000.0f);
                String m2513a = this.f3536n.m2513a(this.f3524b, "http://restapi.amap.com/v3/place/text?", string, string2, string3, String.valueOf(i2));
                if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                    if (m2513a != null) {
                        List<GeoFence> arrayList2 = new ArrayList<>();
                        C3852c c3852c = this.f3537o;
                        int m2741a = C3852c.m2741a(m2513a, arrayList2, bundle2);
                        if (m2741a != 10000) {
                            i = m2367c(m2741a);
                        } else if (arrayList2.isEmpty()) {
                            i = 16;
                        } else {
                            i = m2356a(arrayList2);
                            if (i == 0) {
                                arrayList.addAll(arrayList2);
                            }
                        }
                    } else {
                        i = 4;
                    }
                }
            }
            Bundle bundle3 = new Bundle();
            bundle3.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            bundle3.putInt(AUserTrack.UTKEY_ERROR_CODE, i);
            bundle3.putParcelableArrayList("resultList", arrayList);
            m2373a(1000, bundle3);
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceManager", "doAddGeoFence_Keyword");
        }
    }

    /* renamed from: f */
    final void m2384f(Bundle bundle) {
        String str;
        int i;
        try {
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            if (bundle == null || bundle.isEmpty()) {
                str = "";
            } else {
                String string = bundle.getString("keyword");
                str = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                String m2512a = this.f3536n.m2512a(this.f3524b, "http://restapi.amap.com/v3/config/district?", string);
                if (!TextUtils.isEmpty(string)) {
                    if (m2512a != null) {
                        Bundle bundle2 = new Bundle();
                        bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
                        bundle2.putString("pendingIntentAction", this.f3526d);
                        bundle2.putLong("expiration", -1L);
                        bundle2.putInt("activatesAction", this.f3528f);
                        ArrayList arrayList2 = new ArrayList();
                        int m2745c = this.f3537o.m2745c(m2512a, arrayList2, bundle2);
                        if (m2745c != 10000) {
                            i = m2367c(m2745c);
                        } else if (arrayList2.isEmpty()) {
                            i = 16;
                        } else {
                            i = m2356a(arrayList2);
                            if (i == 0) {
                                arrayList.addAll(arrayList2);
                            }
                        }
                    } else {
                        i = 4;
                    }
                    Bundle bundle3 = new Bundle();
                    bundle3.putInt(AUserTrack.UTKEY_ERROR_CODE, i);
                    bundle3.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
                    bundle3.putParcelableArrayList("resultList", arrayList);
                    m2373a(1000, bundle3);
                }
            }
            i = 1;
            Bundle bundle32 = new Bundle();
            bundle32.putInt(AUserTrack.UTKEY_ERROR_CODE, i);
            bundle32.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            bundle32.putParcelableArrayList("resultList", arrayList);
            m2373a(1000, bundle32);
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceManager", "doAddGeoFence_district");
        }
    }

    /* renamed from: g */
    final void m2385g(Bundle bundle) {
        if (bundle != null) {
            try {
                if (bundle.isEmpty()) {
                    return;
                }
                int i = bundle.getInt(AUserTrack.UTKEY_ERROR_CODE);
                ArrayList parcelableArrayList = bundle.getParcelableArrayList("resultList");
                String string = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                if (string == null) {
                    string = "";
                }
                if (this.f3527e != null) {
                    this.f3527e.onGeoFenceCreateFinished(parcelableArrayList, i, string);
                }
                if (i != 0 || this.f3532j == null) {
                    return;
                }
                if (!m2370e()) {
                    m2372a(7);
                    m2374a(7, null, 1000L);
                } else {
                    new Bundle().putParcelable("loc", this.f3539q);
                    m2374a(6, null, 0L);
                    m2374a(5, bundle, 0L);
                }
            } catch (Throwable th) {
                C3880f.m3097a(th, "GeoFenceManager", "resultAddGeoFenceFinished");
            }
        }
    }

    @Override // com.amap.api.fence.GeoFenceManagerBase
    public List<GeoFence> getAllGeoFence() {
        if (this.f3529g == null) {
            this.f3529g = new ArrayList<>();
        }
        return (ArrayList) this.f3529g.clone();
    }

    /* renamed from: h */
    final void m2386h(Bundle bundle) {
        try {
            if (this.f3538p != null) {
                long j = 2000;
                if (bundle != null && !bundle.isEmpty()) {
                    j = bundle.getLong("interval");
                }
                this.f3541s.setOnceLocation(false);
                this.f3541s.setInterval(j);
                this.f3538p.setLocationOption(this.f3541s);
                if (this.f3534l) {
                    return;
                }
                this.f3538p.stopLocation();
                this.f3538p.startLocation();
                this.f3534l = true;
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceManager", "doStartContinueLocation");
        }
    }

    @Override // com.amap.api.fence.GeoFenceManagerBase
    public void removeGeoFence() {
        m2369d();
    }

    @Override // com.amap.api.fence.GeoFenceManagerBase
    public boolean removeGeoFence(GeoFence geoFence) {
        boolean z = false;
        try {
            if (this.f3529g != null) {
                m2368c();
                z = this.f3529g.remove(geoFence);
                if (z && this.f3529g.size() == 0) {
                    m2369d();
                }
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceManager", "removeGeoFence(GeoFence)");
        }
        return z;
    }

    @Override // com.amap.api.fence.GeoFenceManagerBase
    public void setActivateAction(int i) {
        try {
            m2368c();
            if (i > 7 || i <= 0) {
                i = 1;
            }
            Bundle bundle = new Bundle();
            bundle.putInt("activatesAction", i);
            m2374a(9, bundle, 0L);
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceManager", "setActivateAction");
        }
    }

    @Override // com.amap.api.fence.GeoFenceManagerBase
    public void setGeoFenceListener(GeoFenceListener geoFenceListener) {
        this.f3527e = geoFenceListener;
    }
}
