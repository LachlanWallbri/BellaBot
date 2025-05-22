package com.loc;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.text.TextUtils;
import androidx.core.view.PointerIconCompat;
import com.amap.api.fence.GeoFence;
import com.amap.api.fence.GeoFenceManagerBase;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.APSService;
import com.amap.api.location.DPoint;
import com.amap.api.location.LocationManagerBase;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import org.jetbrains.anko.DimensionsKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: AMapLocationManager.java */
/* renamed from: com.loc.d */
/* loaded from: classes4.dex */
public class C3878d implements LocationManagerBase {

    /* renamed from: D */
    C3873cu f4082D;

    /* renamed from: I */
    a f4087I;

    /* renamed from: O */
    private Context f4093O;

    /* renamed from: a */
    AMapLocationClientOption f4104a;

    /* renamed from: b */
    public d f4105b;

    /* renamed from: c */
    C3881g f4106c;

    /* renamed from: e */
    GeoFenceManagerBase f4108e;

    /* renamed from: i */
    C3882h f4112i;

    /* renamed from: k */
    Messenger f4114k;

    /* renamed from: l */
    Intent f4115l;

    /* renamed from: v */
    b f4125v;

    /* renamed from: w */
    volatile C3851bz f4126w;

    /* renamed from: M */
    private int f4091M = 0;

    /* renamed from: N */
    private boolean f4092N = false;

    /* renamed from: P */
    private boolean f4094P = false;

    /* renamed from: Q */
    private boolean f4095Q = false;

    /* renamed from: d */
    ArrayList<AMapLocationListener> f4107d = new ArrayList<>();

    /* renamed from: f */
    boolean f4109f = false;

    /* renamed from: g */
    public boolean f4110g = true;

    /* renamed from: h */
    public boolean f4111h = true;

    /* renamed from: j */
    Messenger f4113j = null;

    /* renamed from: m */
    int f4116m = 0;

    /* renamed from: n */
    boolean f4117n = false;

    /* renamed from: o */
    long f4118o = 0;

    /* renamed from: p */
    AMapLocation f4119p = null;

    /* renamed from: q */
    long f4120q = 0;

    /* renamed from: r */
    long f4121r = 0;

    /* renamed from: s */
    public boolean f4122s = false;

    /* renamed from: R */
    private JSONArray f4096R = null;

    /* renamed from: S */
    private int f4097S = 0;

    /* renamed from: T */
    private boolean f4098T = true;

    /* renamed from: t */
    int f4123t = DimensionsKt.HDPI;

    /* renamed from: u */
    int f4124u = 80;

    /* renamed from: x */
    volatile boolean f4127x = false;

    /* renamed from: y */
    volatile float f4128y = 0.0f;

    /* renamed from: z */
    volatile double f4129z = 0.0d;

    /* renamed from: A */
    boolean f4079A = false;

    /* renamed from: B */
    AMapLocationClientOption.AMapLocationMode f4080B = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy;

    /* renamed from: C */
    Object f4081C = new Object();

    /* renamed from: E */
    String f4083E = null;

    /* renamed from: U */
    private ServiceConnection f4099U = new ServiceConnection() { // from class: com.loc.d.1
        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                C3878d.this.f4113j = new Messenger(iBinder);
                C3878d.this.f4094P = true;
            } catch (Throwable th) {
                C3880f.m3097a(th, "AMapLocationManager", "onServiceConnected");
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            C3878d c3878d = C3878d.this;
            c3878d.f4113j = null;
            c3878d.f4094P = false;
        }
    };

    /* renamed from: F */
    boolean f4084F = false;

    /* renamed from: V */
    private LinkedList<c> f4100V = new LinkedList<>();

    /* renamed from: W */
    private LinkedList<c> f4101W = new LinkedList<>();

    /* renamed from: X */
    private int f4102X = 0;

    /* renamed from: Y */
    private AMapLocation f4103Y = null;

    /* renamed from: G */
    String f4085G = null;

    /* renamed from: H */
    boolean f4086H = false;

    /* renamed from: J */
    AMapLocation f4088J = null;

    /* renamed from: K */
    String f4089K = null;

    /* renamed from: L */
    Hashtable<PendingIntent, ArrayList<GeoFence>> f4090L = new Hashtable<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AMapLocationManager.java */
    /* renamed from: com.loc.d$4, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass4 {

        /* renamed from: a */
        static final /* synthetic */ int[] f4133a = new int[AMapLocationClientOption.AMapLocationMode.values().length];

        static {
            try {
                f4133a[AMapLocationClientOption.AMapLocationMode.Battery_Saving.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4133a[AMapLocationClientOption.AMapLocationMode.Device_Sensors.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4133a[AMapLocationClientOption.AMapLocationMode.Hight_Accuracy.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* compiled from: AMapLocationManager.java */
    /* renamed from: com.loc.d$a */
    /* loaded from: classes4.dex */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1002:
                    try {
                        C3878d.m3038a(C3878d.this, (AMapLocationListener) message.obj);
                        return;
                    } catch (Throwable th) {
                        C3880f.m3097a(th, "AMapLocationManage$MHandlerr", "handleMessage SET_LISTENER");
                        return;
                    }
                case 1003:
                    try {
                        C3878d.this.m3057j();
                        return;
                    } catch (Throwable th2) {
                        C3880f.m3097a(th2, "AMapLocationManager$MHandler", "handleMessage START_LOCATION");
                        return;
                    }
                case 1004:
                    try {
                        C3878d.this.m3059k();
                        return;
                    } catch (Throwable th3) {
                        C3880f.m3097a(th3, "AMapLocationManager$MHandler", "handleMessage STOP_LOCATION");
                        return;
                    }
                case 1005:
                    try {
                        C3878d.m3043b(C3878d.this, (AMapLocationListener) message.obj);
                        return;
                    } catch (Throwable th4) {
                        C3880f.m3097a(th4, "AMapLocationManager$MHandler", "handleMessage REMOVE_LISTENER");
                        return;
                    }
                case PointerIconCompat.TYPE_CELL /* 1006 */:
                    try {
                        C3878d.m3036a(C3878d.this, (GeoFence) message.obj);
                        return;
                    } catch (Throwable th5) {
                        C3880f.m3097a(th5, "AMapLocationManager$MHandler", "handleMessage ADD_GEOFENCE");
                        return;
                    }
                case PointerIconCompat.TYPE_CROSSHAIR /* 1007 */:
                    try {
                        C3878d.m3032a(C3878d.this, (PendingIntent) message.obj);
                        return;
                    } catch (Throwable th6) {
                        C3880f.m3097a(th6, "AMapLocationManager$MHandler", "handleMessage REMOVE_GEOFENCE");
                        return;
                    }
                case PointerIconCompat.TYPE_TEXT /* 1008 */:
                    try {
                        C3878d.m3058j(C3878d.this);
                        return;
                    } catch (Throwable th7) {
                        C3880f.m3097a(th7, "AMapLocationManager$ActionHandler", "handleMessage START_SOCKET");
                        return;
                    }
                case PointerIconCompat.TYPE_VERTICAL_TEXT /* 1009 */:
                    try {
                        C3878d.m3060k(C3878d.this);
                        return;
                    } catch (Throwable th8) {
                        C3880f.m3097a(th8, "AMapLocationManager$ActionHandler", "handleMessage STOP_SOCKET");
                        return;
                    }
                case PointerIconCompat.TYPE_ALIAS /* 1010 */:
                    try {
                        C3878d.m3042b(C3878d.this, (GeoFence) message.obj);
                        return;
                    } catch (Throwable th9) {
                        C3880f.m3097a(th9, "AMapLocationManager$MHandler", "handleMessage REMOVE_GEOFENCE_ONE");
                        return;
                    }
                case PointerIconCompat.TYPE_COPY /* 1011 */:
                    try {
                        C3878d.m3049f(C3878d.this);
                        return;
                    } catch (Throwable th10) {
                        C3880f.m3097a(th10, "AMapLocationManager$MHandler", "handleMessage DESTROY");
                        return;
                    }
                case PointerIconCompat.TYPE_NO_DROP /* 1012 */:
                case PointerIconCompat.TYPE_ALL_SCROLL /* 1013 */:
                default:
                    return;
                case PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW /* 1014 */:
                    return;
                case PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW /* 1015 */:
                    try {
                        C3881g c3881g = C3878d.this.f4106c;
                        c3881g.f4200d = C3878d.this.f4104a;
                        c3881g.m3108b();
                        return;
                    } catch (Throwable th11) {
                        C3880f.m3097a(th11, "AMapLocationManager$ActionHandler", "handleMessage START_GPS_LOCATION");
                        return;
                    }
                case PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW /* 1016 */:
                    try {
                        if (C3878d.this.m3051g() || !C3878d.this.f4117n) {
                            C3878d.m3054h(C3878d.this);
                            return;
                        } else {
                            C3878d.this.m3026a(PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, (Object) null, 1000L);
                            return;
                        }
                    } catch (Throwable th12) {
                        C3880f.m3097a(th12, "AMapLocationManager$ActionHandler", "handleMessage START_LBS_LOCATION");
                        return;
                    }
                case PointerIconCompat.TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW /* 1017 */:
                    try {
                        C3878d.this.f4106c.m3107a();
                        return;
                    } catch (Throwable th13) {
                        C3880f.m3097a(th13, "AMapLocationManager$ActionHandler", "handleMessage STOP_GPS_LOCATION");
                        return;
                    }
                case PointerIconCompat.TYPE_ZOOM_IN /* 1018 */:
                    try {
                        C3878d.this.f4104a = (AMapLocationClientOption) message.obj;
                        if (C3878d.this.f4104a != null) {
                            C3878d.m3056i(C3878d.this);
                            return;
                        }
                        return;
                    } catch (Throwable th14) {
                        C3880f.m3097a(th14, "AMapLocationManager$ActionHandler", "handleMessage SET_OPTION");
                        return;
                    }
                case PointerIconCompat.TYPE_ZOOM_OUT /* 1019 */:
                    try {
                        C3878d.this.m3061a();
                        C3878d.this.m3026a(PointerIconCompat.TYPE_GRAB, (Object) null, 10000L);
                        C3878d.this.m3026a(1021, (Object) null, 10000L);
                        C3878d.this.m3026a(1022, (Object) null, 30000L);
                        return;
                    } catch (Throwable th15) {
                        C3880f.m3097a(th15, "AMapLocationManager$ActionHandler", "handleMessage ACTION_START_SENSOR");
                        return;
                    }
                case PointerIconCompat.TYPE_GRAB /* 1020 */:
                    try {
                        C3878d.this.m3062b();
                        return;
                    } catch (Throwable th16) {
                        C3880f.m3097a(th16, "AMapLocationManager$ActionHandler", "handleMessage ACTION_GET_PRESSURE");
                        return;
                    }
                case 1021:
                    try {
                        C3878d.this.m3063c();
                        return;
                    } catch (Throwable th17) {
                        C3880f.m3097a(th17, "AMapLocationManager$ActionHandler", "handleMessage ACTION_STOP_SENSOR");
                        return;
                    }
                case 1022:
                    try {
                        C3878d.this.m3064d();
                        return;
                    } catch (Throwable th18) {
                        C3880f.m3097a(th18, "AMapLocationManager$ActionHandler", "handleMessage ACTION_SAVE_GPSINFO");
                        return;
                    }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AMapLocationManager.java */
    /* renamed from: com.loc.d$b */
    /* loaded from: classes4.dex */
    public static class b extends HandlerThread {

        /* renamed from: a */
        C3878d f4135a;

        public b(String str, C3878d c3878d) {
            super(str);
            this.f4135a = null;
            this.f4135a = c3878d;
        }

        @Override // android.os.HandlerThread
        protected final void onLooperPrepared() {
            try {
                C3878d.m3033a(this.f4135a, this.f4135a.f4115l);
                super.onLooperPrepared();
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AMapLocationManager.java */
    /* renamed from: com.loc.d$c */
    /* loaded from: classes4.dex */
    public static class c {

        /* renamed from: a */
        double f4136a;

        /* renamed from: b */
        double f4137b;

        /* renamed from: c */
        long f4138c;

        /* renamed from: d */
        float f4139d;

        /* renamed from: e */
        float f4140e;

        /* renamed from: f */
        int f4141f;

        /* renamed from: g */
        String f4142g;

        c(AMapLocation aMapLocation, int i) {
            this.f4136a = aMapLocation.getLatitude();
            this.f4137b = aMapLocation.getLongitude();
            this.f4138c = aMapLocation.getTime();
            this.f4139d = aMapLocation.getAccuracy();
            this.f4140e = aMapLocation.getSpeed();
            this.f4141f = i;
            this.f4142g = aMapLocation.getProvider();
        }

        public final boolean equals(Object obj) {
            try {
                c cVar = (c) obj;
                if (cVar != null && this.f4136a == cVar.f4136a) {
                    if (this.f4137b == cVar.f4137b) {
                        return true;
                    }
                }
            } catch (Throwable unused) {
            }
            return false;
        }

        public final int hashCode() {
            return super.hashCode();
        }

        public final String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.f4136a);
            stringBuffer.append(",");
            stringBuffer.append(this.f4137b);
            stringBuffer.append(",");
            stringBuffer.append(this.f4139d);
            stringBuffer.append(",");
            stringBuffer.append(this.f4138c);
            stringBuffer.append(",");
            stringBuffer.append(this.f4140e);
            stringBuffer.append(",");
            stringBuffer.append(this.f4141f);
            stringBuffer.append(",");
            stringBuffer.append(this.f4142g);
            return stringBuffer.toString();
        }
    }

    /* compiled from: AMapLocationManager.java */
    /* renamed from: com.loc.d$d */
    /* loaded from: classes4.dex */
    public class d extends Handler {
        public d() {
        }

        public d(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            if (!C3878d.this.f4079A || C3880f.f4192n) {
                int i = message.what;
                if (i == 1) {
                    try {
                        C3878d.m3034a(C3878d.this, message.getData());
                        return;
                    } catch (Throwable th) {
                        C3880f.m3097a(th, "AMapLocationManager$ActionHandler", "handleMessage RESULT_LBS_LOCATIONSUCCESS");
                        return;
                    }
                }
                if (i != 2) {
                    if (i == 3) {
                        C3878d.this.f4117n = false;
                        return;
                    }
                    if (i == 5) {
                        try {
                            C3878d.this.m3039a(false);
                            if (message.obj != null) {
                                C3878d.this.m3028a((AMapLocation) message.obj, C3878d.this.f4088J);
                                return;
                            }
                            return;
                        } catch (Throwable th2) {
                            C3880f.m3097a(th2, "AMapLocationManager$ActionHandler", "handleMessage RESULT_GPS_LOCATIONCHANGE");
                            return;
                        }
                    }
                    if (i == 6) {
                        try {
                            Bundle data = message.getData();
                            if (data != null) {
                                C3878d.this.f4123t = data.getInt("lMaxGeoDis");
                                C3878d.this.f4124u = data.getInt("lMinGeoDis");
                                String string = data.getString("locationJson");
                                AMapLocation aMapLocation = new AMapLocation("");
                                C3880f.m3096a(aMapLocation, new JSONObject(string));
                                if (TextUtils.isEmpty(aMapLocation.getAdCode())) {
                                    return;
                                }
                                C3878d.this.f4088J = aMapLocation;
                                return;
                            }
                            return;
                        } catch (Throwable th3) {
                            C3880f.m3097a(th3, "AMapLocationManager$ActionHandler", "handleMessage RESULT_GPS_GEO_SUCCESS");
                            return;
                        }
                    }
                    if (i == 7) {
                        try {
                            Bundle data2 = message.getData();
                            C3878d.this.f4098T = data2.getBoolean("ngpsAble");
                            return;
                        } catch (Throwable th4) {
                            C3880f.m3097a(th4, "AMapLocationManager$ActionHandler", "handleMessage RESULT_NGPS_ABLE");
                            return;
                        }
                    }
                    if (i != 8) {
                        if (i != 100) {
                            return;
                        }
                        try {
                            C3878d.m3041b(C3878d.this);
                            return;
                        } catch (Throwable th5) {
                            C3880f.m3097a(th5, "AMapLocationManager$ActionHandler", "handleMessage RESULT_FASTSKY");
                            return;
                        }
                    }
                }
                try {
                    C3878d.m3035a(C3878d.this, message);
                } catch (Throwable th6) {
                    C3880f.m3097a(th6, "AMapLocationManager$ActionHandler", "handleMessage RESULT_GPS_LOCATIONSUCCESS");
                }
            }
        }
    }

    public C3878d(Context context, Intent intent) {
        this.f4106c = null;
        this.f4114k = null;
        this.f4115l = null;
        this.f4125v = null;
        this.f4126w = null;
        this.f4082D = null;
        this.f4087I = null;
        this.f4093O = context;
        this.f4115l = intent;
        if (C3880f.m3103c()) {
            try {
                C3874cv.m2946a(this.f4093O, C3880f.m3091a("loc"));
            } catch (Throwable unused) {
            }
        }
        try {
            this.f4105b = Looper.myLooper() == null ? new d(this.f4093O.getMainLooper()) : new d();
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationManager", "init 1");
        }
        try {
            this.f4125v = new b("amapLocManagerThread", this);
            this.f4125v.setPriority(5);
            this.f4125v.start();
            this.f4087I = m3023a(this.f4125v.getLooper());
            this.f4114k = new Messenger(this.f4105b);
        } catch (Throwable th2) {
            C3880f.m3097a(th2, "AMapLocationManager", "init 5");
        }
        try {
            this.f4112i = new C3882h(this.f4093O);
        } catch (Throwable th3) {
            C3880f.m3097a(th3, "AMapLocationManager", "init 2");
        }
        try {
            this.f4106c = new C3881g(this.f4093O, this.f4105b);
        } catch (Throwable th4) {
            C3880f.m3097a(th4, "AMapLocationManager", "init 3");
        }
        this.f4126w = new C3851bz(this.f4093O);
        if (this.f4082D == null) {
            this.f4082D = new C3873cu();
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(24:2|3|(3:4|5|(1:7))|9|(20:97|98|12|(3:93|94|(14:96|17|(1:19)|20|21|(1:35)|37|38|39|(1:41)(1:73)|42|43|44|(4:54|55|56|(2:58|59))(1:46)))|14|15|16|17|(0)|20|21|(7:23|25|27|29|31|33|35)|37|38|39|(0)(0)|42|43|44|(0)(0))|11|12|(0)|14|15|16|17|(0)|20|21|(0)|37|38|39|(0)(0)|42|43|44|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(28:1|2|3|4|5|(1:7)|9|(20:97|98|12|(3:93|94|(14:96|17|(1:19)|20|21|(1:35)|37|38|39|(1:41)(1:73)|42|43|44|(4:54|55|56|(2:58|59))(1:46)))|14|15|16|17|(0)|20|21|(7:23|25|27|29|31|33|35)|37|38|39|(0)(0)|42|43|44|(0)(0))|11|12|(0)|14|15|16|17|(0)|20|21|(0)|37|38|39|(0)(0)|42|43|44|(0)(0)|(5:(0)|(1:63)|(1:85)|(0)|(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0111, code lost:
    
        if (r10 != null) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0120, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0113, code lost:
    
        r10.m2715c();
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00eb, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00ec, code lost:
    
        com.loc.C3880f.m3097a(r1, "AMapLocationManager", "apsLocation:reportLBSLocUseTime");
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00df, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00e0, code lost:
    
        com.loc.C3880f.m3097a(r1, "AMapLocationManager", "apsLocation:callback");
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0064, code lost:
    
        r6 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0065, code lost:
    
        com.loc.C3880f.m3097a(r6, "AMapLocationManager", "apsLocation:doFirstNetLocate");
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x011d, code lost:
    
        if (r10 != null) goto L89;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0077 A[Catch: all -> 0x005a, TRY_LEAVE, TryCatch #10 {all -> 0x005a, blocks: (B:94:0x0051, B:17:0x006b, B:19:0x0077, B:71:0x00ec, B:66:0x00f9, B:75:0x00e0, B:79:0x0065, B:15:0x005e, B:44:0x00e5, B:55:0x00f3, B:38:0x00b0, B:41:0x00b9, B:42:0x00c9, B:73:0x00c6), top: B:93:0x0051, inners: #2, #6, #9, #12 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x007f A[Catch: all -> 0x00b0, TryCatch #7 {all -> 0x00b0, blocks: (B:21:0x007b, B:23:0x007f, B:25:0x0085, B:27:0x008d, B:29:0x0091, B:31:0x0097, B:33:0x009e, B:35:0x00aa), top: B:20:0x007b }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b9 A[Catch: all -> 0x00df, TRY_ENTER, TryCatch #12 {all -> 0x00df, blocks: (B:38:0x00b0, B:41:0x00b9, B:42:0x00c9, B:73:0x00c6), top: B:37:0x00b0, outer: #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00f3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00c6 A[Catch: all -> 0x00df, TryCatch #12 {all -> 0x00df, blocks: (B:38:0x00b0, B:41:0x00b9, B:42:0x00c9, B:73:0x00c6), top: B:37:0x00b0, outer: #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0051 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private AMapLocationServer m3022a(C3846bu c3846bu) {
        AMapLocationServer m2717e;
        byte b2;
        String str;
        AMapLocation m3113a;
        AMapLocationServer aMapLocationServer = null;
        aMapLocationServer = null;
        aMapLocationServer = null;
        aMapLocationServer = null;
        aMapLocationServer = null;
        try {
            C3872ct c3872ct = new C3872ct();
            c3872ct.m2922a(C3876cx.m2985b());
            try {
                String apikey = AMapLocationClientOption.getAPIKEY();
                if (!TextUtils.isEmpty(apikey)) {
                    C3886l.m3132a(apikey);
                }
            } catch (Throwable th) {
                C3880f.m3097a(th, "AMapLocationManager", "apsLocation setAuthKey");
            }
            AMapLocationClientOption aMapLocationClientOption = this.f4104a;
            Context context = this.f4093O;
            this.f4085G = C3880f.m3094a(aMapLocationClientOption);
            c3846bu.m2709a(this.f4093O, new JSONObject(this.f4085G));
            if (this.f4104a.isLocationCacheEnable()) {
                try {
                    m2717e = c3846bu.m2717e();
                } catch (Throwable th2) {
                    C3880f.m3097a(th2, "AMapLocationManager", "apsLocation:doFirstCacheLocate");
                }
                if (m2717e != null) {
                    try {
                        if (C3876cx.m2977a(m2717e)) {
                            b2 = false;
                            c3872ct.m2925b(C3876cx.m2985b());
                            c3872ct.m2923a(m2717e);
                            str = m2717e != null ? m2717e.toStr(1) : null;
                            this.f4084F = false;
                            if (m2717e != null && m2717e.getErrorCode() != 0 && this.f4104a.isLocationCacheEnable() && this.f4112i != null && m2717e.getLocationType() != 1 && m2717e.getErrorCode() != 7 && (m3113a = this.f4112i.m3113a(m2717e.m560j())) != null) {
                                this.f4084F = true;
                                str = m3113a.toStr(1);
                            }
                            Bundle bundle = new Bundle();
                            if (m2717e != null) {
                                bundle.putInt("originalLocType", m2717e.getLocationType());
                                bundle.putString("locationJson", str);
                            } else {
                                bundle.putInt("originalLocType", 0);
                            }
                            bundle.putBoolean("fixlastlocation", this.f4084F);
                            Message obtain = Message.obtain();
                            obtain.setData(bundle);
                            obtain.what = 1;
                            this.f4105b.sendMessage(obtain);
                            C3873cu.m2930a(this.f4093O, c3872ct);
                            if (b2 == true) {
                                try {
                                    aMapLocationServer = c3846bu.m2706a(true);
                                } catch (Throwable th3) {
                                    C3880f.m3097a(th3, "AMapLocationManager", "apsLocation:doFirstNetLocate 2");
                                    aMapLocationServer = m2717e;
                                }
                                if (aMapLocationServer.getErrorCode() == 0) {
                                    try {
                                        c3846bu.m2710a(aMapLocationServer);
                                    } catch (Throwable th4) {
                                        C3880f.m3097a(th4, "AMapLocationManager", "apsLocation:doFirstAddCache");
                                    }
                                }
                            } else {
                                aMapLocationServer = m2717e;
                            }
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        aMapLocationServer = m2717e;
                        try {
                            C3880f.m3097a(th, "AMapLocationManager", "apsLocation");
                        } catch (Throwable th6) {
                            if (c3846bu != null) {
                                try {
                                    c3846bu.m2715c();
                                } catch (Throwable unused) {
                                }
                            }
                            throw th6;
                        }
                    }
                }
                m2717e = c3846bu.m2706a(false);
                b2 = true;
                c3872ct.m2925b(C3876cx.m2985b());
                c3872ct.m2923a(m2717e);
                if (m2717e != null) {
                }
                this.f4084F = false;
                if (m2717e != null) {
                    this.f4084F = true;
                    str = m3113a.toStr(1);
                }
                Bundle bundle2 = new Bundle();
                if (m2717e != null) {
                }
                bundle2.putBoolean("fixlastlocation", this.f4084F);
                Message obtain2 = Message.obtain();
                obtain2.setData(bundle2);
                obtain2.what = 1;
                this.f4105b.sendMessage(obtain2);
                C3873cu.m2930a(this.f4093O, c3872ct);
                if (b2 == true) {
                }
            }
            m2717e = null;
            if (m2717e != null) {
            }
            m2717e = c3846bu.m2706a(false);
            b2 = true;
            c3872ct.m2925b(C3876cx.m2985b());
            c3872ct.m2923a(m2717e);
            if (m2717e != null) {
            }
            this.f4084F = false;
            if (m2717e != null) {
            }
            Bundle bundle22 = new Bundle();
            if (m2717e != null) {
            }
            bundle22.putBoolean("fixlastlocation", this.f4084F);
            Message obtain22 = Message.obtain();
            obtain22.setData(bundle22);
            obtain22.what = 1;
            this.f4105b.sendMessage(obtain22);
            C3873cu.m2930a(this.f4093O, c3872ct);
            if (b2 == true) {
            }
        } catch (Throwable th7) {
            th = th7;
            C3880f.m3097a(th, "AMapLocationManager", "apsLocation");
        }
    }

    /* renamed from: a */
    private a m3023a(Looper looper) {
        a aVar;
        synchronized (this.f4081C) {
            this.f4087I = new a(looper);
            aVar = this.f4087I;
        }
        return aVar;
    }

    /* renamed from: a */
    private static c m3024a(AMapLocation aMapLocation, int i) {
        return new c(aMapLocation, i);
    }

    /* renamed from: a */
    private void m3025a(int i, Bundle bundle) {
        if (bundle == null) {
            try {
                bundle = new Bundle();
            } catch (Throwable th) {
                C3880f.m3097a(th, "AMapLocationManager", "sendLocMessage");
                return;
            }
        }
        if (TextUtils.isEmpty(this.f4083E)) {
            this.f4083E = C3880f.m3102c(this.f4093O);
        }
        bundle.putString("c", this.f4083E);
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.setData(bundle);
        obtain.replyTo = this.f4114k;
        if (this.f4113j != null) {
            this.f4113j.send(obtain);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m3026a(int i, Object obj, long j) {
        synchronized (this.f4081C) {
            if (this.f4087I != null) {
                Message obtain = Message.obtain();
                obtain.what = i;
                obtain.obj = obj;
                this.f4087I.sendMessageDelayed(obtain, j);
            }
        }
    }

    /* renamed from: a */
    private void m3027a(AMapLocation aMapLocation) {
        try {
            if ("gps".equals(aMapLocation.getProvider()) || m3051g()) {
                aMapLocation.setAltitude(C3876cx.m2984b(aMapLocation.getAltitude()));
                aMapLocation.setBearing(C3876cx.m2961a(aMapLocation.getBearing()));
                aMapLocation.setSpeed(C3876cx.m2961a(aMapLocation.getSpeed()));
                Iterator<AMapLocationListener> it = this.f4107d.iterator();
                while (it.hasNext()) {
                    try {
                        it.next().onLocationChanged(aMapLocation);
                    } catch (Throwable unused) {
                    }
                }
            }
        } catch (Throwable unused2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m3028a(AMapLocation aMapLocation, AMapLocation aMapLocation2) {
        if (C3876cx.m2976a(aMapLocation)) {
            Bundle bundle = null;
            if (aMapLocation != null) {
                bundle = new Bundle();
                bundle.putDouble("lat", aMapLocation.getLatitude());
                bundle.putDouble("lon", aMapLocation.getLongitude());
            }
            if (this.f4104a.isNeedAddress() && aMapLocation2 == null) {
                m3025a(10, bundle);
                return;
            }
            if (aMapLocation2 == null || !this.f4104a.isNeedAddress()) {
                return;
            }
            float m2964a = C3876cx.m2964a(new double[]{aMapLocation2.getLatitude(), aMapLocation2.getLongitude(), aMapLocation.getLatitude(), aMapLocation.getLongitude()});
            if (m2964a < this.f4123t) {
                C3880f.m3090a(aMapLocation, aMapLocation2);
            }
            if (m2964a > this.f4124u) {
                m3025a(10, bundle);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0143 A[Catch: all -> 0x01e0, TryCatch #0 {all -> 0x01e0, blocks: (B:82:0x0096, B:84:0x009c, B:86:0x00a6, B:90:0x00ca, B:95:0x00fe, B:97:0x0106, B:98:0x010c, B:100:0x0115, B:101:0x0131, B:103:0x0143, B:105:0x014b, B:107:0x0154, B:108:0x0159, B:109:0x015b, B:110:0x0183, B:112:0x0194, B:113:0x01a6, B:115:0x01ac, B:117:0x01bf, B:125:0x015f, B:127:0x0163, B:129:0x016b, B:131:0x0173, B:132:0x0176, B:134:0x017e, B:143:0x011a, B:145:0x0122, B:146:0x0125, B:148:0x012d), top: B:66:0x0064 }] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0194 A[Catch: all -> 0x01e0, TryCatch #0 {all -> 0x01e0, blocks: (B:82:0x0096, B:84:0x009c, B:86:0x00a6, B:90:0x00ca, B:95:0x00fe, B:97:0x0106, B:98:0x010c, B:100:0x0115, B:101:0x0131, B:103:0x0143, B:105:0x014b, B:107:0x0154, B:108:0x0159, B:109:0x015b, B:110:0x0183, B:112:0x0194, B:113:0x01a6, B:115:0x01ac, B:117:0x01bf, B:125:0x015f, B:127:0x0163, B:129:0x016b, B:131:0x0173, B:132:0x0176, B:134:0x017e, B:143:0x011a, B:145:0x0122, B:146:0x0125, B:148:0x012d), top: B:66:0x0064 }] */
    /* JADX WARN: Removed duplicated region for block: B:124:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x015f A[Catch: all -> 0x01e0, TryCatch #0 {all -> 0x01e0, blocks: (B:82:0x0096, B:84:0x009c, B:86:0x00a6, B:90:0x00ca, B:95:0x00fe, B:97:0x0106, B:98:0x010c, B:100:0x0115, B:101:0x0131, B:103:0x0143, B:105:0x014b, B:107:0x0154, B:108:0x0159, B:109:0x015b, B:110:0x0183, B:112:0x0194, B:113:0x01a6, B:115:0x01ac, B:117:0x01bf, B:125:0x015f, B:127:0x0163, B:129:0x016b, B:131:0x0173, B:132:0x0176, B:134:0x017e, B:143:0x011a, B:145:0x0122, B:146:0x0125, B:148:0x012d), top: B:66:0x0064 }] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x02a9 A[Catch: all -> 0x02c1, TryCatch #2 {all -> 0x02c1, blocks: (B:3:0x0004, B:8:0x0010, B:11:0x001a, B:15:0x0026, B:16:0x0034, B:18:0x0040, B:154:0x02a5, B:156:0x02a9, B:159:0x02b0, B:161:0x02bd, B:165:0x02a0, B:20:0x0045, B:22:0x0049, B:26:0x01ec, B:28:0x01fe, B:31:0x0205, B:33:0x020b, B:35:0x021a, B:37:0x021e, B:42:0x0297, B:62:0x0292, B:122:0x01e5), top: B:2:0x0004, inners: #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:161:0x02bd A[Catch: all -> 0x02c1, TRY_LEAVE, TryCatch #2 {all -> 0x02c1, blocks: (B:3:0x0004, B:8:0x0010, B:11:0x001a, B:15:0x0026, B:16:0x0034, B:18:0x0040, B:154:0x02a5, B:156:0x02a9, B:159:0x02b0, B:161:0x02bd, B:165:0x02a0, B:20:0x0045, B:22:0x0049, B:26:0x01ec, B:28:0x01fe, B:31:0x0205, B:33:0x020b, B:35:0x021a, B:37:0x021e, B:42:0x0297, B:62:0x0292, B:122:0x01e5), top: B:2:0x0004, inners: #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:163:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01fe A[Catch: all -> 0x029f, TryCatch #6 {all -> 0x029f, blocks: (B:20:0x0045, B:22:0x0049, B:26:0x01ec, B:28:0x01fe, B:31:0x0205, B:33:0x020b, B:35:0x021a, B:37:0x021e, B:42:0x0297, B:62:0x0292, B:122:0x01e5, B:39:0x0230, B:44:0x0237, B:54:0x0249, B:56:0x024d, B:57:0x0254, B:59:0x028d), top: B:19:0x0045, outer: #2, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x020b A[Catch: all -> 0x029f, TryCatch #6 {all -> 0x029f, blocks: (B:20:0x0045, B:22:0x0049, B:26:0x01ec, B:28:0x01fe, B:31:0x0205, B:33:0x020b, B:35:0x021a, B:37:0x021e, B:42:0x0297, B:62:0x0292, B:122:0x01e5, B:39:0x0230, B:44:0x0237, B:54:0x0249, B:56:0x024d, B:57:0x0254, B:59:0x028d), top: B:19:0x0045, outer: #2, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x021a A[Catch: all -> 0x029f, TryCatch #6 {all -> 0x029f, blocks: (B:20:0x0045, B:22:0x0049, B:26:0x01ec, B:28:0x01fe, B:31:0x0205, B:33:0x020b, B:35:0x021a, B:37:0x021e, B:42:0x0297, B:62:0x0292, B:122:0x01e5, B:39:0x0230, B:44:0x0237, B:54:0x0249, B:56:0x024d, B:57:0x0254, B:59:0x028d), top: B:19:0x0045, outer: #2, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0237 A[Catch: all -> 0x0291, TryCatch #1 {all -> 0x0291, blocks: (B:39:0x0230, B:44:0x0237, B:54:0x0249, B:56:0x024d, B:57:0x0254, B:59:0x028d), top: B:38:0x0230, outer: #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0217  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x00fe A[Catch: all -> 0x01e0, TRY_ENTER, TryCatch #0 {all -> 0x01e0, blocks: (B:82:0x0096, B:84:0x009c, B:86:0x00a6, B:90:0x00ca, B:95:0x00fe, B:97:0x0106, B:98:0x010c, B:100:0x0115, B:101:0x0131, B:103:0x0143, B:105:0x014b, B:107:0x0154, B:108:0x0159, B:109:0x015b, B:110:0x0183, B:112:0x0194, B:113:0x01a6, B:115:0x01ac, B:117:0x01bf, B:125:0x015f, B:127:0x0163, B:129:0x016b, B:131:0x0173, B:132:0x0176, B:134:0x017e, B:143:0x011a, B:145:0x0122, B:146:0x0125, B:148:0x012d), top: B:66:0x0064 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void m3029a(AMapLocation aMapLocation, Throwable th) {
        AMapLocation aMapLocation2;
        int i;
        LinkedList<c> linkedList;
        boolean z;
        int i2;
        try {
            if (C3880f.m3103c() && aMapLocation == null) {
                if (th != null) {
                    C3874cv.m2947a(this.f4093O, "loc", th.getMessage());
                    return;
                } else {
                    C3874cv.m2947a(this.f4093O, "loc", "amaplocation is null");
                    return;
                }
            }
            if (aMapLocation == null) {
                AMapLocation aMapLocation3 = new AMapLocation("");
                aMapLocation3.setErrorCode(8);
                aMapLocation2 = aMapLocation3;
            } else {
                aMapLocation2 = aMapLocation;
            }
            if (!"gps".equals(aMapLocation2.getProvider())) {
                aMapLocation2.setProvider("lbs");
            }
            try {
                if (this.f4095Q) {
                    long time = aMapLocation2.getTime();
                    int i3 = 1;
                    if (aMapLocation2.getErrorCode() == 0) {
                        try {
                            AMapLocation aMapLocation4 = this.f4119p;
                            this.f4103Y = aMapLocation2;
                            long m2985b = C3876cx.m2985b();
                            this.f4121r = 0L;
                            this.f4102X = 0;
                            try {
                                if (aMapLocation4 != null && aMapLocation2 != null && aMapLocation4.getLocationType() == 1 && this.f4091M > 3) {
                                    if (aMapLocation2.getAccuracy() >= 0.0f && aMapLocation2.getSpeed() >= 0.0f) {
                                        long time2 = aMapLocation2.getTime() - aMapLocation4.getTime();
                                        if (time2 >= 0) {
                                            if (aMapLocation2.getLocationType() == 1) {
                                                if (m2985b - this.f4120q < 5000) {
                                                    float speed = ((aMapLocation4.getSpeed() + aMapLocation2.getSpeed()) * ((float) time2)) / 2000.0f;
                                                    float accuracy = (aMapLocation4.getAccuracy() + aMapLocation2.getAccuracy()) * 2.0f;
                                                    double[] dArr = new double[4];
                                                    try {
                                                        dArr[0] = aMapLocation4.getLatitude();
                                                        dArr[1] = aMapLocation4.getLongitude();
                                                        dArr[2] = aMapLocation2.getLatitude();
                                                        dArr[3] = aMapLocation2.getLongitude();
                                                        if (C3876cx.m2964a(dArr) > speed + accuracy + 3000.0f) {
                                                            z = true;
                                                            if (z) {
                                                                if (this.f4121r == 0) {
                                                                    this.f4121r = C3876cx.m2985b();
                                                                }
                                                                if (m2985b - this.f4121r < 30000) {
                                                                    this.f4092N = true;
                                                                    this.f4102X = 1;
                                                                    c m3024a = m3024a(aMapLocation4, this.f4102X);
                                                                    c m3024a2 = m3024a(this.f4103Y, this.f4102X);
                                                                    if (this.f4092N) {
                                                                        if (!this.f4100V.contains(m3024a)) {
                                                                            if (this.f4100V.size() >= 5) {
                                                                                this.f4100V.removeFirst();
                                                                            }
                                                                            linkedList = this.f4100V;
                                                                            linkedList.add(m3024a);
                                                                        }
                                                                        if (this.f4100V.size() + this.f4101W.size() < 10) {
                                                                            this.f4100V.addAll(this.f4101W);
                                                                            StringBuffer stringBuffer = new StringBuffer();
                                                                            Iterator<c> it = this.f4100V.iterator();
                                                                            while (it.hasNext()) {
                                                                                stringBuffer.append(it.next().toString());
                                                                                stringBuffer.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                                                                            }
                                                                            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                                                                            C3873cu.m2935a("gpsstatistics", stringBuffer.toString());
                                                                            this.f4100V.clear();
                                                                            this.f4101W.clear();
                                                                            i = 0;
                                                                            this.f4092N = false;
                                                                        } else {
                                                                            i = 0;
                                                                        }
                                                                    } else {
                                                                        if (this.f4102X == 0) {
                                                                            if (!this.f4100V.contains(m3024a) && !this.f4101W.contains(m3024a)) {
                                                                                linkedList = this.f4101W;
                                                                                linkedList.add(m3024a);
                                                                            }
                                                                        } else if (!this.f4101W.contains(m3024a2)) {
                                                                            this.f4101W.add(m3024a2);
                                                                        }
                                                                        if (this.f4100V.size() + this.f4101W.size() < 10) {
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } catch (Throwable th2) {
                                                        th = th2;
                                                        i = 0;
                                                        C3880f.m3097a(th, "AMapLocationManager", "handleMessage");
                                                        this.f4120q = C3876cx.m2985b();
                                                        this.f4119p = aMapLocation2;
                                                        aMapLocation2.setTime(time);
                                                        m3027a(aMapLocation2);
                                                        if (!this.f4079A) {
                                                        }
                                                        if (aMapLocation2.getLocationType() == 1) {
                                                        }
                                                        if (i2 == 0) {
                                                        }
                                                        try {
                                                            if (C3869cq.m2898o()) {
                                                            }
                                                        } catch (Throwable th3) {
                                                            C3880f.m3097a(th3, "AMapLocationManager", "handlerOfflineLog");
                                                        }
                                                        C3873cu.m2929a(this.f4093O, this.f4097S, aMapLocation2);
                                                        if (this.f4079A) {
                                                        }
                                                        C3874cv.m2948b(this.f4093O);
                                                        if (this.f4104a.isOnceLocation()) {
                                                        }
                                                    }
                                                } else {
                                                    i = 0;
                                                    try {
                                                        this.f4091M = 0;
                                                    } catch (Throwable th4) {
                                                        th = th4;
                                                        C3880f.m3097a(th, "AMapLocationManager", "handleMessage");
                                                        this.f4120q = C3876cx.m2985b();
                                                        this.f4119p = aMapLocation2;
                                                        aMapLocation2.setTime(time);
                                                        m3027a(aMapLocation2);
                                                        if (!this.f4079A) {
                                                        }
                                                        if (aMapLocation2.getLocationType() == 1) {
                                                        }
                                                        if (i2 == 0) {
                                                            C3862cj c3862cj = new C3862cj();
                                                            c3862cj.m2839a(aMapLocation2);
                                                            c3862cj.m2840a(this.f4089K);
                                                            this.f4112i.m3114a(c3862cj);
                                                        }
                                                        if (C3869cq.m2898o()) {
                                                        }
                                                        C3873cu.m2929a(this.f4093O, this.f4097S, aMapLocation2);
                                                        if (this.f4079A) {
                                                        }
                                                        C3874cv.m2948b(this.f4093O);
                                                        if (this.f4104a.isOnceLocation()) {
                                                        }
                                                    }
                                                }
                                            }
                                            z = false;
                                            if (z) {
                                            }
                                        }
                                    }
                                    if (aMapLocation2.getAccuracy() < 0.0f) {
                                        aMapLocation2.setAccuracy(0.0f);
                                    }
                                    if (aMapLocation2.getSpeed() < 0.0f) {
                                        aMapLocation2.setSpeed(0.0f);
                                    }
                                }
                                aMapLocation4 = aMapLocation2;
                                c m3024a3 = m3024a(aMapLocation4, this.f4102X);
                                c m3024a22 = m3024a(this.f4103Y, this.f4102X);
                                if (this.f4092N) {
                                }
                            } catch (Throwable th5) {
                                th = th5;
                                i = 0;
                            }
                        } catch (Throwable th6) {
                            th = th6;
                            i = 0;
                        }
                    } else {
                        i = 0;
                    }
                    this.f4120q = C3876cx.m2985b();
                    this.f4119p = aMapLocation2;
                    aMapLocation2.setTime(time);
                    m3027a(aMapLocation2);
                    if (!this.f4079A && !C3880f.m3103c()) {
                        return;
                    }
                    if (aMapLocation2.getLocationType() == 1) {
                        this.f4089K = null;
                        i2 = C3876cx.m2975a(aMapLocation2, this.f4106c.f4206j);
                    } else {
                        i2 = i;
                    }
                    if (i2 == 0 && !this.f4084F) {
                        C3862cj c3862cj2 = new C3862cj();
                        c3862cj2.m2839a(aMapLocation2);
                        c3862cj2.m2840a(this.f4089K);
                        this.f4112i.m3114a(c3862cj2);
                    }
                    if (C3869cq.m2898o()) {
                        int i4 = this.f4097S;
                        if (i4 != 1) {
                            if (i4 == 2 || i4 == 4 || i4 == 8) {
                                i = 1;
                            } else {
                                i3 = i;
                            }
                        }
                        if (i3 != 0) {
                            if (this.f4096R == null) {
                                this.f4096R = new JSONArray();
                            }
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("lon", aMapLocation2.getLongitude());
                            jSONObject.put("lat", aMapLocation2.getLatitude());
                            jSONObject.put("type", i);
                            jSONObject.put("timestamp", C3876cx.m2969a());
                            this.f4096R = this.f4096R.put(jSONObject);
                            if (this.f4096R.length() >= C3869cq.m2899p()) {
                                m3053h();
                            }
                        }
                    }
                    C3873cu.m2929a(this.f4093O, this.f4097S, aMapLocation2);
                }
            } catch (Throwable th7) {
                C3880f.m3097a(th7, "AMapLocationManager", "handlerLocation part2");
            }
            if (this.f4079A || C3880f.m3103c()) {
                C3874cv.m2948b(this.f4093O);
                if (this.f4104a.isOnceLocation()) {
                    m3059k();
                }
            }
        } catch (Throwable th8) {
            C3880f.m3097a(th8, "AMapLocationManager", "handlerLocation part3");
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m3032a(C3878d c3878d, PendingIntent pendingIntent) {
        if (pendingIntent != null) {
            try {
                if (c3878d.f4090L.containsKey(pendingIntent)) {
                    if (c3878d.f4108e != null) {
                        Iterator<GeoFence> it = c3878d.f4090L.get(pendingIntent).iterator();
                        while (it.hasNext()) {
                            c3878d.f4108e.removeGeoFence(it.next());
                        }
                    }
                    c3878d.f4090L.remove(pendingIntent);
                }
            } catch (Throwable th) {
                C3880f.m3097a(th, "AMapLocationManager", "doRemoveGeoFenceAlert2");
            }
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m3033a(C3878d c3878d, Intent intent) {
        if (intent == null) {
            try {
                intent = new Intent(c3878d.f4093O, (Class<?>) APSService.class);
            } catch (Throwable th) {
                C3880f.m3097a(th, "AMapLocationManager", "startServiceImpl");
                return;
            }
        }
        String str = "";
        try {
            str = !TextUtils.isEmpty(AMapLocationClientOption.getAPIKEY()) ? AMapLocationClientOption.getAPIKEY() : C3885k.m3127f(c3878d.f4093O);
        } catch (Throwable th2) {
            C3880f.m3097a(th2, "AMapLocationManager", "startServiceImpl p2");
        }
        intent.putExtra("a", str);
        intent.putExtra("b", c3878d.f4093O.getPackageName());
        intent.putExtra("c", C3880f.m3102c(c3878d.f4093O));
        c3878d.f4093O.bindService(intent, c3878d.f4099U, 1);
    }

    /* renamed from: a */
    static /* synthetic */ void m3034a(C3878d c3878d, Bundle bundle) {
        AMapLocation aMapLocation;
        Throwable th = null;
        try {
            c3878d.f4091M = 0;
            if (bundle != null) {
                String string = bundle.getString("locationJson");
                aMapLocation = new AMapLocation("");
                JSONObject jSONObject = new JSONObject(string);
                C3880f.m3096a(aMapLocation, jSONObject);
                try {
                    if (C3876cx.m2979a(jSONObject, "nb")) {
                        c3878d.f4089K = jSONObject.getString("nb");
                    } else {
                        c3878d.f4089K = null;
                    }
                    c3878d.f4097S = bundle.getInt("originalLocType", 0);
                    if (aMapLocation.getErrorCode() == 0 && !TextUtils.isEmpty(aMapLocation.getAdCode())) {
                        c3878d.f4088J = aMapLocation;
                    }
                    c3878d.f4084F = bundle.getBoolean("fixlastlocation", false);
                } catch (Throwable unused) {
                }
            } else {
                aMapLocation = null;
            }
        } catch (Throwable th2) {
            C3880f.m3097a(th2, "AMapLocationManager$MHandler", "handleMessage LBS_LOCATIONSUCCESS");
            aMapLocation = null;
            th = th2;
        }
        c3878d.m3029a(aMapLocation, th);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0077 A[Catch: all -> 0x0088, TryCatch #2 {all -> 0x0088, blocks: (B:25:0x0071, B:27:0x0077, B:29:0x0081), top: B:24:0x0071 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static /* synthetic */ void m3035a(C3878d c3878d, Message message) {
        Throwable th;
        AMapLocation aMapLocation;
        try {
            aMapLocation = (AMapLocation) message.obj;
        } catch (Throwable th2) {
            th = th2;
            aMapLocation = null;
        }
        try {
            c3878d.f4097S = aMapLocation.getLocationType();
            if (aMapLocation.getErrorCode() == 0) {
                c3878d.m3039a(true);
            }
        } catch (Throwable th3) {
            th = th3;
            C3880f.m3097a(th, "AMapLocationManager$ActionHandler", "handleMessage GPS_LOCATIONSUCCESS");
            if (c3878d.f4111h) {
                Bundle bundle = new Bundle();
                bundle.putString("extraJson", c3878d.f4085G);
                c3878d.m3025a(0, bundle);
                c3878d.f4111h = false;
            }
            c3878d.m3028a(aMapLocation, c3878d.f4088J);
            c3878d.m3029a(aMapLocation, th);
            try {
                if (aMapLocation.getErrorCode() == 0) {
                }
            } catch (Throwable unused) {
            }
            if (c3878d.f4098T) {
            }
        }
        if (aMapLocation.getErrorCode() != 15 || AMapLocationClientOption.AMapLocationMode.Device_Sensors.equals(c3878d.f4080B)) {
            if (c3878d.f4106c.f4206j >= 4) {
                aMapLocation.setGpsAccuracyStatus(1);
            } else if (c3878d.f4106c.f4206j == 0) {
                aMapLocation.setGpsAccuracyStatus(-1);
            } else {
                aMapLocation.setGpsAccuracyStatus(0);
            }
            th = null;
            if (c3878d.f4111h && c3878d.f4113j != null) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("extraJson", c3878d.f4085G);
                c3878d.m3025a(0, bundle2);
                c3878d.f4111h = false;
            }
            c3878d.m3028a(aMapLocation, c3878d.f4088J);
            c3878d.m3029a(aMapLocation, th);
            if (aMapLocation.getErrorCode() == 0) {
                c3878d.f4129z = aMapLocation.getAltitude();
                if (!c3878d.f4127x) {
                    c3878d.m3026a(PointerIconCompat.TYPE_ZOOM_OUT, (Object) null, 0L);
                }
            }
            if (c3878d.f4098T) {
                return;
            }
            c3878d.m3025a(7, (Bundle) null);
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m3036a(C3878d c3878d, GeoFence geoFence) {
        if (geoFence == null) {
            return;
        }
        try {
            if (c3878d.f4108e == null) {
                c3878d.f4108e = C3876cx.m3012g(c3878d.f4093O);
                c3878d.f4108e.setActivateAction(7);
            }
            PendingIntent pendingIntent = geoFence.getPendingIntent();
            ArrayList<GeoFence> arrayList = new ArrayList<>();
            if (!c3878d.f4090L.isEmpty()) {
                arrayList = c3878d.f4090L.get(pendingIntent);
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                } else {
                    GeoFence geoFence2 = null;
                    Iterator<GeoFence> it = arrayList.iterator();
                    while (it.hasNext()) {
                        GeoFence next = it.next();
                        if (next.getFenceId().equals(geoFence.getFenceId())) {
                            geoFence2 = next;
                        }
                    }
                    if (geoFence2 != null) {
                        arrayList.remove(geoFence2);
                    }
                }
            }
            arrayList.add(geoFence);
            c3878d.f4090L.put(pendingIntent, arrayList);
            c3878d.f4108e.addRoundGeoFence(geoFence.getCenter(), geoFence.getRadius(), null, geoFence.getFenceId(), geoFence.getExpiration(), geoFence.getPendingIntent());
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationManager", "doAddGeoFenceAlert");
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m3038a(C3878d c3878d, AMapLocationListener aMapLocationListener) {
        if (aMapLocationListener == null) {
            throw new IllegalArgumentException("listener参数不能为null");
        }
        if (c3878d.f4107d == null) {
            c3878d.f4107d = new ArrayList<>();
        }
        if (c3878d.f4107d.contains(aMapLocationListener)) {
            return;
        }
        c3878d.f4107d.add(aMapLocationListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m3039a(boolean z) {
        this.f4118o = C3876cx.m2985b();
        this.f4117n = true;
        if (z) {
            this.f4091M++;
        }
    }

    /* renamed from: b */
    static /* synthetic */ void m3041b(C3878d c3878d) {
        boolean z;
        try {
            boolean z2 = true;
            if (c3878d.f4093O.checkCallingOrSelfPermission("android.permission.SYSTEM_ALERT_WINDOW") == 0) {
                z = true;
            } else if (c3878d.f4093O instanceof Activity) {
                z = false;
            } else {
                z = false;
                z2 = false;
            }
            if (!z2) {
                c3878d.m3055i();
                return;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(c3878d.f4093O);
            builder.setMessage(C3869cq.m2887g());
            if (!"".equals(C3869cq.m2889h()) && C3869cq.m2889h() != null) {
                builder.setPositiveButton(C3869cq.m2889h(), new DialogInterface.OnClickListener() { // from class: com.loc.d.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        C3878d.this.m3055i();
                        dialogInterface.cancel();
                    }
                });
            }
            builder.setNegativeButton(C3869cq.m2891i(), new DialogInterface.OnClickListener() { // from class: com.loc.d.3
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog create = builder.create();
            if (z) {
                create.getWindow().setType(2003);
            }
            create.setCanceledOnTouchOutside(false);
            create.show();
        } catch (Throwable th) {
            c3878d.m3055i();
            C3880f.m3097a(th, "AMapLocationManager", "showDialog");
        }
    }

    /* renamed from: b */
    static /* synthetic */ void m3042b(C3878d c3878d, GeoFence geoFence) {
        if (geoFence == null) {
            return;
        }
        try {
            PendingIntent pendingIntent = geoFence.getPendingIntent();
            String fenceId = geoFence.getFenceId();
            if (pendingIntent == null || !c3878d.f4090L.containsKey(pendingIntent) || TextUtils.isEmpty(fenceId) || c3878d.f4090L.isEmpty() || c3878d.f4108e == null) {
                return;
            }
            Iterator<GeoFence> it = c3878d.f4090L.get(pendingIntent).iterator();
            while (it != null) {
                if (!it.hasNext()) {
                    return;
                }
                GeoFence next = it.next();
                if (!fenceId.equals(next.getFenceId())) {
                    if (next.getExpiration() != -1 && next.getExpiration() <= C3876cx.m2985b()) {
                    }
                }
                c3878d.f4108e.removeGeoFence(next);
                it.remove();
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationManager", "doRemoveGeoFenceAlert");
        }
    }

    /* renamed from: b */
    static /* synthetic */ void m3043b(C3878d c3878d, AMapLocationListener aMapLocationListener) {
        if (!c3878d.f4107d.isEmpty() && c3878d.f4107d.contains(aMapLocationListener)) {
            c3878d.f4107d.remove(aMapLocationListener);
        }
        if (c3878d.f4107d.isEmpty()) {
            c3878d.m3059k();
        }
    }

    /* renamed from: e */
    private void m3047e() {
        synchronized (this.f4081C) {
            if (this.f4087I != null) {
                this.f4087I.removeMessages(PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW);
            }
        }
    }

    /* renamed from: f */
    static /* synthetic */ void m3049f(C3878d c3878d) {
        c3878d.f4110g = true;
        c3878d.f4111h = true;
        c3878d.f4094P = false;
        c3878d.m3059k();
        C3873cu c3873cu = c3878d.f4082D;
        if (c3873cu != null) {
            c3873cu.m2940b(c3878d.f4093O);
        }
        C3873cu.m2927a(c3878d.f4093O);
        c3878d.m3053h();
        GeoFenceManagerBase geoFenceManagerBase = c3878d.f4108e;
        if (geoFenceManagerBase != null) {
            geoFenceManagerBase.removeGeoFence();
        }
        ServiceConnection serviceConnection = c3878d.f4099U;
        if (serviceConnection != null) {
            c3878d.f4093O.unbindService(serviceConnection);
        }
        ArrayList<AMapLocationListener> arrayList = c3878d.f4107d;
        if (arrayList != null) {
            arrayList.clear();
            c3878d.f4107d = null;
        }
        c3878d.f4099U = null;
        synchronized (c3878d.f4081C) {
            if (c3878d.f4087I != null) {
                c3878d.f4087I.removeCallbacksAndMessages(null);
            }
            c3878d.f4087I = null;
        }
        if (c3878d.f4125v != null) {
            if (Build.VERSION.SDK_INT >= 18) {
                try {
                    C3871cs.m2916a(c3878d.f4125v, (Class<?>) HandlerThread.class, "quitSafely", new Object[0]);
                } catch (Throwable unused) {
                }
            }
            c3878d.f4125v.quit();
        }
        c3878d.f4125v = null;
        d dVar = c3878d.f4105b;
        if (dVar != null) {
            dVar.removeCallbacksAndMessages(null);
        }
        c3878d.f4112i.m3115b();
        c3878d.f4112i = null;
        c3878d.m3064d();
        if (c3878d.f4126w != null) {
            c3878d.f4126w.m2740f();
            c3878d.f4126w = null;
        }
    }

    /* renamed from: f */
    private boolean m3050f() {
        int i = 0;
        while (this.f4113j == null) {
            try {
                Thread.sleep(100L);
                i++;
                if (i >= 50) {
                    break;
                }
            } catch (Throwable th) {
                C3880f.m3097a(th, "AMapLocationManager", "checkAPSManager");
                return false;
            }
        }
        if (this.f4113j != null) {
            return true;
        }
        Message obtain = Message.obtain();
        Bundle bundle = new Bundle();
        AMapLocation aMapLocation = new AMapLocation("");
        aMapLocation.setErrorCode(10);
        aMapLocation.setLocationDetail("请检查配置文件是否配置服务");
        bundle.putString("locationJson", aMapLocation.toStr(1));
        obtain.setData(bundle);
        obtain.what = 1;
        this.f4105b.sendMessage(obtain);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public boolean m3051g() {
        return C3876cx.m2985b() - this.f4118o > 10000;
    }

    /* renamed from: h */
    private synchronized void m3053h() {
        try {
            if (C3869cq.m2898o()) {
                if (this.f4096R != null && this.f4096R.length() > 0) {
                    C3842bq.m2634a(new C3841bp(this.f4093O, C3880f.m3091a("loc"), this.f4096R.toString()), this.f4093O);
                    this.f4096R = null;
                }
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationManager", "writeOfflineLog");
        }
    }

    /* renamed from: h */
    static /* synthetic */ void m3054h(C3878d c3878d) {
        boolean isOnceLocation;
        long j = 1000;
        try {
            if (c3878d.f4110g) {
                c3878d.f4110g = false;
                AMapLocationServer m3022a = c3878d.m3022a(new C3846bu());
                if (c3878d.m3050f()) {
                    Bundle bundle = new Bundle();
                    String str = "0";
                    if (m3022a != null && (m3022a.getLocationType() == 2 || m3022a.getLocationType() == 4)) {
                        str = "1";
                    }
                    bundle.putString("isCacheLoc", str);
                    bundle.putString("extraJson", c3878d.f4085G);
                    c3878d.m3025a(0, bundle);
                }
            } else if (c3878d.m3050f()) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("extraJson", c3878d.f4085G);
                if (c3878d.f4104a.isSensorEnable()) {
                    bundle2.putDouble(C3898x.f4338g, c3878d.f4129z);
                    bundle2.putFloat(C3898x.f4339h, c3878d.f4128y);
                }
                if (c3878d.m3051g() || !c3878d.f4117n) {
                    c3878d.m3025a(1, bundle2);
                }
            }
            try {
                if (isOnceLocation) {
                    return;
                }
            } catch (Throwable unused) {
            }
        } catch (Throwable th) {
            try {
                C3880f.m3097a(th, "AMapLocationManager", "doLBSLocation");
                try {
                    if (c3878d.f4104a.isOnceLocation() || c3878d.f4104a.getLocationMode() == AMapLocationClientOption.AMapLocationMode.Device_Sensors) {
                        return;
                    }
                    if (c3878d.f4104a.getInterval() >= 1000) {
                        j = c3878d.f4104a.getInterval();
                    }
                    c3878d.m3026a(PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, (Object) null, j);
                } catch (Throwable unused2) {
                }
            } finally {
                try {
                    if (!c3878d.f4104a.isOnceLocation() && c3878d.f4104a.getLocationMode() != AMapLocationClientOption.AMapLocationMode.Device_Sensors) {
                        if (c3878d.f4104a.getInterval() >= 1000) {
                            j = c3878d.f4104a.getInterval();
                        }
                        c3878d.m3026a(PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, (Object) null, j);
                    }
                } catch (Throwable unused3) {
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i */
    public void m3055i() {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.autonavi.minimap", C3869cq.m2895l()));
            intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
            intent.setData(Uri.parse(C3869cq.m2893j()));
            this.f4093O.startActivity(intent);
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationManager", "callAMap part1");
            try {
                Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(C3869cq.m2894k()));
                intent2.setFlags(ClientDefaults.MAX_MSG_SIZE);
                this.f4093O.startActivity(intent2);
            } catch (Throwable th2) {
                C3880f.m3097a(th2, "AMapLocationManager", "callAMap part2");
            }
        }
    }

    /* renamed from: i */
    static /* synthetic */ void m3056i(C3878d c3878d) {
        C3873cu c3873cu;
        Context context;
        int i;
        AMapLocationClientOption aMapLocationClientOption = c3878d.f4104a;
        Context context2 = c3878d.f4093O;
        c3878d.f4085G = C3880f.m3094a(aMapLocationClientOption);
        C3881g c3881g = c3878d.f4106c;
        c3881g.f4200d = c3878d.f4104a;
        if (c3881g.f4200d.getLocationMode() != AMapLocationClientOption.AMapLocationMode.Device_Sensors && c3881g.f4197a != null) {
            c3881g.f4197a.removeMessages(8);
        }
        if (c3878d.f4095Q && !c3878d.f4104a.getLocationMode().equals(c3878d.f4080B)) {
            c3878d.m3059k();
            c3878d.m3057j();
        }
        c3878d.f4080B = c3878d.f4104a.getLocationMode();
        if (c3878d.f4082D != null) {
            if (c3878d.f4104a.isOnceLocation()) {
                c3873cu = c3878d.f4082D;
                context = c3878d.f4093O;
                i = 0;
            } else {
                c3873cu = c3878d.f4082D;
                context = c3878d.f4093O;
                i = 1;
            }
            c3873cu.m2938a(context, i);
            c3878d.f4082D.m2939a(c3878d.f4093O, c3878d.f4104a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public void m3057j() {
        if (this.f4104a == null) {
            this.f4104a = new AMapLocationClientOption();
        }
        if (this.f4095Q) {
            return;
        }
        this.f4095Q = true;
        int i = AnonymousClass4.f4133a[this.f4104a.getLocationMode().ordinal()];
        long j = 0;
        if (i != 1) {
            if (i == 2) {
                m3047e();
                m3026a(PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW, (Object) null, 0L);
            } else if (i == 3) {
                m3026a(PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW, (Object) null, 0L);
                if (this.f4104a.isGpsFirst() && this.f4104a.isOnceLocation()) {
                    j = 30000;
                }
            }
            if (this.f4104a.isSensorEnable() || this.f4129z != 0.0d) {
            }
            try {
                JSONObject m3017j = C3876cx.m3017j();
                if (m3017j != null) {
                    this.f4129z = m3017j.getDouble("altitude");
                    this.f4128y = Float.valueOf(m3017j.getString("pressure")).floatValue();
                    return;
                }
                return;
            } catch (Throwable th) {
                C3880f.m3097a(th, "AMapLocationManager", "readAltitudePressureFromDB");
                return;
            }
        }
        m3026a(PointerIconCompat.TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW, (Object) null, 0L);
        m3026a(PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, (Object) null, j);
        if (this.f4104a.isSensorEnable()) {
        }
    }

    /* renamed from: j */
    static /* synthetic */ void m3058j(C3878d c3878d) {
        try {
            if (c3878d.f4113j != null) {
                c3878d.f4116m = 0;
                Bundle bundle = new Bundle();
                bundle.putString("extraJson", c3878d.f4085G);
                c3878d.m3025a(2, bundle);
                return;
            }
            c3878d.f4116m++;
            if (c3878d.f4116m < 10) {
                c3878d.m3026a(PointerIconCompat.TYPE_TEXT, (Object) null, 50L);
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationManager", "startAssistantLocationImpl");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k */
    public void m3059k() {
        try {
            this.f4106c.m3107a();
            m3047e();
            this.f4100V.clear();
            this.f4101W.clear();
            this.f4117n = false;
            this.f4095Q = false;
            this.f4118o = 0L;
            this.f4116m = 0;
            this.f4119p = null;
            this.f4120q = 0L;
            this.f4092N = false;
            this.f4102X = 0;
            this.f4091M = 0;
            this.f4103Y = null;
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationManager", "stopLocation");
        }
    }

    /* renamed from: k */
    static /* synthetic */ void m3060k(C3878d c3878d) {
        try {
            Bundle bundle = new Bundle();
            bundle.putString("extraJson", c3878d.f4085G);
            c3878d.m3025a(3, bundle);
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationManager", "stopAssistantLocationImpl");
        }
    }

    /* renamed from: a */
    final void m3061a() {
        try {
            if (this.f4127x) {
                return;
            }
            this.f4127x = true;
            this.f4126w.m2734a();
        } catch (Throwable unused) {
        }
    }

    @Override // com.amap.api.location.LocationManagerBase
    public void addGeoFenceAlert(String str, double d2, double d3, float f, long j, PendingIntent pendingIntent) {
        try {
            GeoFence geoFence = new GeoFence();
            geoFence.setFenceId(str);
            DPoint dPoint = new DPoint(d2, d3);
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(dPoint);
            arrayList.add(arrayList2);
            geoFence.setCenter(dPoint);
            geoFence.setPointList(arrayList);
            geoFence.setRadius(f);
            geoFence.setPendingIntent(pendingIntent);
            geoFence.setActivatesAction(7);
            geoFence.setType(0);
            geoFence.setExpiration(j);
            m3026a(PointerIconCompat.TYPE_CELL, geoFence, 0L);
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationManager", "addGeoFenceAlert");
        }
    }

    /* renamed from: b */
    final void m3062b() {
        try {
            if (this.f4126w != null) {
                this.f4128y = this.f4126w.m2736b();
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: c */
    final void m3063c() {
        try {
            if (this.f4126w == null || !this.f4127x) {
                return;
            }
            this.f4127x = false;
            this.f4126w.m2737c();
        } catch (Throwable unused) {
        }
    }

    /* renamed from: d */
    final void m3064d() {
        try {
            if (this.f4129z != 0.0d) {
                m3062b();
                C3876cx.m2971a(this.f4129z, this.f4128y);
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationManager", "doSaveGPSInfo");
        }
    }

    @Override // com.amap.api.location.LocationManagerBase
    public AMapLocation getLastKnownLocation() {
        try {
            if (this.f4112i != null) {
                return this.f4112i.m3112a();
            }
            return null;
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationManager", "getLastKnownLocation");
            return null;
        }
    }

    @Override // com.amap.api.location.LocationManagerBase
    public boolean isStarted() {
        return this.f4094P;
    }

    @Override // com.amap.api.location.LocationManagerBase
    public void onDestroy() {
        try {
            m3026a(PointerIconCompat.TYPE_COPY, (Object) null, 0L);
            this.f4079A = true;
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationManager", "onDestroy");
        }
    }

    @Override // com.amap.api.location.LocationManagerBase
    public void removeGeoFenceAlert(PendingIntent pendingIntent) {
        try {
            m3026a(PointerIconCompat.TYPE_CROSSHAIR, pendingIntent, 0L);
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationManager", "removeGeoFenceAlert 2");
        }
    }

    @Override // com.amap.api.location.LocationManagerBase
    public void removeGeoFenceAlert(PendingIntent pendingIntent, String str) {
        try {
            GeoFence geoFence = new GeoFence();
            geoFence.setFenceId(str);
            geoFence.setPendingIntent(pendingIntent);
            m3026a(PointerIconCompat.TYPE_ALIAS, geoFence, 0L);
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationManager", "removeGeoFenceAlert 1");
        }
    }

    @Override // com.amap.api.location.LocationManagerBase
    public void setLocationListener(AMapLocationListener aMapLocationListener) {
        try {
            m3026a(1002, aMapLocationListener, 0L);
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationManager", "setLocationListener");
        }
    }

    @Override // com.amap.api.location.LocationManagerBase
    public void setLocationOption(AMapLocationClientOption aMapLocationClientOption) {
        try {
            m3026a(PointerIconCompat.TYPE_ZOOM_IN, aMapLocationClientOption.m4268clone(), 0L);
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationManager", "setLocationOption");
        }
    }

    @Override // com.amap.api.location.LocationManagerBase
    public void startAssistantLocation() {
        try {
            m3026a(PointerIconCompat.TYPE_TEXT, (Object) null, 0L);
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationManager", "startAssistantLocation");
        }
    }

    @Override // com.amap.api.location.LocationManagerBase
    public void startLocation() {
        try {
            m3026a(1003, (Object) null, 0L);
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationManager", "startLocation");
        }
    }

    @Override // com.amap.api.location.LocationManagerBase
    public void stopAssistantLocation() {
        try {
            m3026a(PointerIconCompat.TYPE_VERTICAL_TEXT, (Object) null, 0L);
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationManager", "stopAssistantLocation");
        }
    }

    @Override // com.amap.api.location.LocationManagerBase
    public void stopLocation() {
        try {
            m3026a(1004, (Object) null, 0L);
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationManager", "stopLocation");
        }
    }

    @Override // com.amap.api.location.LocationManagerBase
    public void unRegisterLocationListener(AMapLocationListener aMapLocationListener) {
        try {
            m3026a(1005, aMapLocationListener, 0L);
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocationManager", "unRegisterLocationListener");
        }
    }
}
