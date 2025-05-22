package com.loc;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import com.pudutech.mirsdk.SolicitService;
import io.netty.handler.codec.memcache.binary.BinaryMemcacheOpcodes;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONObject;

/* compiled from: APS.java */
/* renamed from: com.loc.bu */
/* loaded from: classes4.dex */
public final class C3846bu {

    /* renamed from: S */
    private static long f3720S = 0;

    /* renamed from: T */
    private static volatile long f3721T = 0;

    /* renamed from: v */
    static int f3722v = -1;

    /* renamed from: w */
    static long f3723w = 0;

    /* renamed from: x */
    static long f3724x = 0;

    /* renamed from: y */
    static int f3725y = -1;

    /* renamed from: b */
    C3847bv f3774b;

    /* renamed from: A */
    private volatile Context f3726A = null;

    /* renamed from: B */
    private ConnectivityManager f3727B = null;

    /* renamed from: C */
    private volatile C3856cd f3728C = null;

    /* renamed from: D */
    private volatile C3855cc f3729D = null;

    /* renamed from: E */
    private ArrayList<ScanResult> f3730E = new ArrayList<>();

    /* renamed from: F */
    private volatile ArrayList<ScanResult> f3731F = new ArrayList<>();

    /* renamed from: G */
    private HashMap<String, ArrayList<ScanResult>> f3732G = new HashMap<>();

    /* renamed from: H */
    private ArrayList<a> f3733H = new ArrayList<>();

    /* renamed from: I */
    private ArrayList<a> f3734I = new ArrayList<>();

    /* renamed from: a */
    Object f3750a = new Object();

    /* renamed from: J */
    private c f3735J = null;

    /* renamed from: K */
    private volatile WifiInfo f3736K = null;

    /* renamed from: L */
    private JSONObject f3737L = null;

    /* renamed from: M */
    private volatile AMapLocationServer f3738M = null;

    /* renamed from: N */
    private volatile long f3739N = 0;

    /* renamed from: O */
    private long f3740O = 0;

    /* renamed from: P */
    private long f3741P = 0;

    /* renamed from: Q */
    private volatile boolean f3742Q = false;

    /* renamed from: R */
    private boolean f3743R = false;

    /* renamed from: U */
    private int f3744U = 0;

    /* renamed from: V */
    private volatile String f3745V = "00:00:00:00:00:00";

    /* renamed from: W */
    private C3868cp f3746W = null;

    /* renamed from: X */
    private volatile Timer f3747X = null;

    /* renamed from: Y */
    private volatile TimerTask f3748Y = null;

    /* renamed from: Z */
    private int f3749Z = 0;

    /* renamed from: aa */
    private Object f3751aa = null;

    /* renamed from: ab */
    private volatile Object f3752ab = null;

    /* renamed from: ac */
    private boolean f3753ac = false;

    /* renamed from: ad */
    private boolean f3754ad = false;

    /* renamed from: ae */
    private String f3755ae = null;

    /* renamed from: af */
    private String f3756af = null;

    /* renamed from: ag */
    private long f3757ag = 0;

    /* renamed from: ah */
    private volatile long f3758ah = 0;

    /* renamed from: ai */
    private volatile String f3759ai = null;

    /* renamed from: aj */
    private C3854cb f3760aj = null;

    /* renamed from: ak */
    private C3865cm f3761ak = null;

    /* renamed from: al */
    private StringBuilder f3762al = new StringBuilder();

    /* renamed from: c */
    boolean f3775c = false;

    /* renamed from: am */
    private boolean f3763am = true;

    /* renamed from: an */
    private boolean f3764an = true;

    /* renamed from: ao */
    private volatile boolean f3765ao = true;

    /* renamed from: ap */
    private boolean f3766ap = false;

    /* renamed from: aq */
    private boolean f3767aq = false;

    /* renamed from: d */
    Object f3776d = new Object();

    /* renamed from: e */
    public boolean f3777e = false;

    /* renamed from: f */
    public String f3778f = "com.data.carrier_v4.CollectorManager";

    /* renamed from: g */
    public String f3779g = "com.autonavi.aps.amapapi.offline.Off";

    /* renamed from: h */
    BluetoothAdapter f3780h = null;

    /* renamed from: i */
    boolean f3781i = false;

    /* renamed from: j */
    b f3782j = null;

    /* renamed from: k */
    boolean f3783k = false;

    /* renamed from: ar */
    private boolean f3768ar = false;

    /* renamed from: as */
    private boolean f3769as = false;

    /* renamed from: l */
    volatile boolean f3784l = false;

    /* renamed from: m */
    int f3785m = 12;

    /* renamed from: n */
    boolean f3786n = false;

    /* renamed from: o */
    StringBuilder f3787o = null;

    /* renamed from: p */
    StringBuilder f3788p = null;

    /* renamed from: q */
    String f3789q = null;

    /* renamed from: r */
    TreeMap<Integer, ScanResult> f3790r = null;

    /* renamed from: s */
    boolean f3791s = true;

    /* renamed from: t */
    boolean f3792t = true;

    /* renamed from: u */
    boolean f3793u = true;

    /* renamed from: at */
    private String f3770at = null;

    /* renamed from: au */
    private String f3771au = null;

    /* renamed from: z */
    StringBuilder f3794z = null;

    /* renamed from: av */
    private boolean f3772av = false;

    /* renamed from: aw */
    private Map<String, String> f3773aw = new HashMap();

    /* compiled from: APS.java */
    /* renamed from: com.loc.bu$a */
    /* loaded from: classes4.dex */
    public static class a implements Comparable<a> {

        /* renamed from: a */
        public String f3797a;

        /* renamed from: b */
        public String f3798b;

        /* renamed from: c */
        public byte[] f3799c;

        /* renamed from: d */
        public String f3800d;

        /* renamed from: e */
        public String f3801e;

        /* renamed from: f */
        public int f3802f;

        /* renamed from: g */
        public int f3803g;

        /* renamed from: h */
        public String f3804h;

        /* renamed from: i */
        public long f3805i;

        /* renamed from: j */
        public int f3806j = 0;

        public a(String str, String str2, byte[] bArr, String str3, int i, int i2, int i3, int i4, long j) {
            this.f3797a = null;
            this.f3798b = null;
            this.f3799c = null;
            this.f3800d = null;
            this.f3801e = null;
            this.f3802f = 0;
            this.f3803g = 0;
            this.f3804h = null;
            this.f3805i = 0L;
            this.f3797a = str;
            this.f3798b = str2;
            this.f3799c = bArr;
            this.f3800d = Integer.toHexString(i).trim().toUpperCase(Locale.CHINA);
            if (this.f3800d.length() < 4) {
                this.f3800d += "00000";
                this.f3800d = this.f3800d.substring(0, 4);
            }
            this.f3801e = Integer.toHexString(i2).trim().toUpperCase(Locale.CHINA);
            if (this.f3801e.length() < 4) {
                this.f3801e += "00000";
                this.f3801e = this.f3801e.substring(0, 4);
            }
            this.f3802f = i3;
            this.f3803g = i4;
            this.f3805i = j;
            this.f3804h = str3;
        }

        @Override // java.lang.Comparable
        public final /* bridge */ /* synthetic */ int compareTo(a aVar) {
            int i = this.f3803g;
            int i2 = aVar.f3803g;
            if (i < i2) {
                return 1;
            }
            return (i == i2 || i <= i2) ? 0 : -1;
        }

        public final String toString() {
            return "name = " + this.f3798b + ",uuid = " + this.f3797a + ",major = " + this.f3800d + ",minor = " + this.f3801e + ",TxPower = " + this.f3802f + ",rssi = " + this.f3803g + ",time = " + this.f3805i;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: APS.java */
    /* renamed from: com.loc.bu$b */
    /* loaded from: classes4.dex */
    public class b implements BluetoothAdapter.LeScanCallback {
        b() {
        }

        @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
        public final void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            try {
                a m2707a = C3846bu.this.m2707a(bluetoothDevice, i, bArr);
                synchronized (C3846bu.this.f3750a) {
                    int i2 = 0;
                    while (i2 < C3846bu.this.f3733H.size()) {
                        a aVar = (a) C3846bu.this.f3733H.get(i2);
                        if (aVar.f3804h.equals(m2707a.f3804h)) {
                            C3846bu.this.f3733H.remove(aVar);
                            C3846bu.this.f3733H.add(m2707a);
                            return;
                        } else {
                            if (C3876cx.m2985b() - aVar.f3805i > SolicitService.CAMERA_OPEN_TIME_OUT) {
                                C3846bu.this.f3733H.remove(aVar);
                                i2--;
                            }
                            i2++;
                        }
                    }
                    C3846bu.this.f3733H.add(m2707a);
                }
            } catch (Throwable th) {
                C3880f.m3097a(th, "APS", "onLeScan");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: APS.java */
    /* renamed from: com.loc.bu$c */
    /* loaded from: classes4.dex */
    public class c extends BroadcastReceiver {
        private c() {
        }

        /* synthetic */ c(C3846bu c3846bu, byte b) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            String action;
            int i;
            if (context == null || intent == null) {
                return;
            }
            try {
                action = intent.getAction();
            } catch (Throwable th) {
                C3880f.m3097a(th, "APS", "onReceive");
            }
            if (TextUtils.isEmpty(action)) {
                return;
            }
            C3856cd c3856cd = C3846bu.this.f3728C;
            if (action.equals("android.net.wifi.SCAN_RESULTS")) {
                if (c3856cd != null && C3876cx.m2985b() - C3846bu.f3721T > 5000) {
                    List<ScanResult> list = null;
                    try {
                        list = c3856cd.m2786a();
                    } catch (Throwable th2) {
                        C3880f.m3097a(th2, "APS", "onReceive part1");
                    }
                    if (list == null) {
                        synchronized (C3846bu.this.f3776d) {
                            C3846bu.this.f3731F.clear();
                        }
                        return;
                    } else {
                        synchronized (C3846bu.this.f3776d) {
                            C3846bu.this.f3731F.clear();
                            C3846bu.this.f3731F.addAll(list);
                            long unused = C3846bu.f3721T = C3876cx.m2985b();
                        }
                        return;
                    }
                }
                return;
            }
            if (!action.equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                if (action.equals("android.intent.action.SCREEN_ON")) {
                    C3846bu.this.f3792t = true;
                    return;
                }
                if (action.equals("android.intent.action.SCREEN_OFF")) {
                    C3846bu.this.f3792t = false;
                    if (C3846bu.this.f3751aa != null) {
                        C3846bu.this.m2655A();
                        C3846bu.this.m2701y();
                        return;
                    }
                    return;
                }
                if (!action.equals("android.intent.action.AIRPLANE_MODE") && action.equals("android.net.conn.CONNECTIVITY_CHANGE") && C3846bu.this.m2656B() && C3846bu.this.f3792t) {
                    C3846bu.m2681i(C3846bu.this);
                    return;
                }
                return;
            }
            if (C3846bu.this.f3728C == null) {
                return;
            }
            try {
                i = c3856cd.m2790c();
            } catch (Throwable th3) {
                C3880f.m3097a(th3, "APS", "onReceive part");
                i = 4;
            }
            if (C3846bu.this.f3731F == null) {
                C3846bu.this.f3731F = new ArrayList();
            }
            if (i == 0) {
                C3846bu.this.m2694r();
                return;
            } else if (i == 1) {
                C3846bu.this.m2694r();
                return;
            } else {
                if (i != 4) {
                    return;
                }
                C3846bu.this.m2694r();
                return;
            }
            C3880f.m3097a(th, "APS", "onReceive");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: A */
    public synchronized void m2655A() {
        if (this.f3748Y != null) {
            this.f3748Y.cancel();
            this.f3748Y = null;
        }
        if (this.f3747X != null) {
            this.f3747X.cancel();
            this.f3747X.purge();
            this.f3747X = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: B */
    public boolean m2656B() {
        if (this.f3728C == null || this.f3727B == null) {
            return false;
        }
        return this.f3728C.m2788a(this.f3727B);
    }

    /* renamed from: C */
    private String m2657C() {
        try {
            if (this.f3751aa != null) {
                return (String) C3871cs.m2917a(this.f3751aa, "getInnerString", "version");
            }
            return null;
        } catch (Throwable th) {
            C3880f.m3097a(th, "APS", "getCollVer");
            return null;
        }
    }

    /* renamed from: D */
    private boolean m2658D() {
        return C3869cq.m2886f() && this.f3752ab != null && C3869cq.m2906w();
    }

    /* renamed from: a */
    private AMapLocationServer m2660a(String str, String str2, JSONObject jSONObject, String str3) {
        Object obj;
        try {
            if (m2658D()) {
                try {
                    obj = C3871cs.m2917a(this.f3752ab, "getPureOfflineLocation", str, str2, jSONObject, str3);
                } catch (Throwable unused) {
                    obj = null;
                }
                AMapLocationServer aMapLocationServer = new AMapLocationServer("");
                aMapLocationServer.m548b(new JSONObject((String) obj));
                return aMapLocationServer;
            }
        } catch (Throwable unused2) {
        }
        return null;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(47:12|13|(1:15)|16|(1:18)|19|(1:21)(1:303)|22|(1:24)(1:302)|25|26|(1:28)|29|(10:279|280|(2:296|297)|282|283|(1:285)|286|(2:291|292)|288|(1:290))(1:31)|(2:32|33)|34|(4:36|(2:38|(2:40|41))|270|41)(1:271)|42|(4:44|(2:46|(2:48|49)(3:247|(1:258)(3:251|(1:253)(1:257)|254)|255))(3:259|(4:262|(2:264|265)(1:267)|266|260)|268)|256|49)(1:269)|50|(1:246)(1:54)|55|(4:57|(9:59|(8:71|(1:73)|62|63|64|65|(1:67)|68)|61|62|63|64|65|(0)|68)|74|(2:76|373))(2:243|(1:245))|87|(4:(26:238|239|(1:91)(1:237)|92|93|(3:230|231|(1:233))|95|(1:97)(1:229)|98|(1:100)(1:228)|101|102|103|(1:105)|106|107|108|109|110|111|112|(1:114)(1:219)|115|116|117|(1:(4:207|(1:209)|210|211)(2:122|(2:124|125)(2:126|(6:128|(1:130)(1:136)|131|(1:133)|134|135)(2:137|(4:139|(1:141)|142|143)(2:144|(4:146|(1:148)|149|150)(6:151|152|153|(1:205)(2:157|(3:161|(1:163)|164))|165|(3:167|(1:169)|170)(2:171|(4:173|(5:177|(1:194)(1:189)|190|(1:192)|193)|195|196)(6:197|(1:199)|200|(1:202)|203|204))))))))(2:212|213))|116|117|(0)(0))|89|(0)(0)|92|93|(0)|95|(0)(0)|98|(0)(0)|101|102|103|(0)|106|107|108|109|110|111|112|(0)(0)|115) */
    /* JADX WARN: Can't wrap try/catch for region: R(9:59|(8:71|(1:73)|62|63|64|65|(1:67)|68)|61|62|63|64|65|(0)|68) */
    /* JADX WARN: Code restructure failed: missing block: B:220:0x07aa, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:221:0x07ab, code lost:
    
        r4 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x035b, code lost:
    
        r12 = 32;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0449  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x04db A[Catch: all -> 0x04e9, TryCatch #5 {all -> 0x04e9, blocks: (B:103:0x04d3, B:105:0x04db, B:106:0x04e3), top: B:102:0x04d3 }] */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0523 A[Catch: all -> 0x07aa, TRY_LEAVE, TryCatch #6 {all -> 0x07aa, blocks: (B:112:0x050b, B:114:0x0523), top: B:111:0x050b }] */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0533  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x07a6  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0528  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x044c  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x03fd A[Catch: all -> 0x00c4, TryCatch #2 {all -> 0x00c4, blocks: (B:280:0x00cd, B:283:0x00eb, B:285:0x00f3, B:286:0x00f7, B:288:0x010e, B:290:0x0116, B:274:0x0125, B:34:0x012d, B:36:0x0134, B:38:0x013c, B:42:0x0154, B:44:0x015a, B:48:0x0167, B:49:0x02d8, B:50:0x02e7, B:52:0x02ee, B:54:0x02f4, B:55:0x030a, B:57:0x0310, B:59:0x031a, B:62:0x033b, B:65:0x034d, B:68:0x0360, B:74:0x036d, B:76:0x0371, B:77:0x0373, B:85:0x038d, B:86:0x038e, B:87:0x039f, B:91:0x03bd, B:92:0x03c0, B:93:0x03c7, B:95:0x03f3, B:97:0x03f9, B:98:0x0409, B:101:0x044e, B:107:0x04e9, B:229:0x03fd, B:236:0x03ec, B:237:0x03c3, B:242:0x03b3, B:243:0x038f, B:245:0x0398, B:246:0x0303, B:247:0x0172, B:249:0x01c0, B:251:0x01c4, B:253:0x01ca, B:254:0x01f1, B:255:0x0219, B:258:0x0210, B:259:0x0230, B:260:0x0297, B:262:0x029d, B:264:0x02cc, B:266:0x02d3, B:271:0x014d, B:295:0x0107, B:300:0x00df, B:33:0x011d, B:292:0x00ff, B:231:0x03d1, B:233:0x03e1, B:79:0x0374, B:81:0x037a, B:82:0x038a, B:239:0x03a9, B:297:0x00d5), top: B:279:0x00cd, inners: #1, #3, #7, #9, #11, #13 }] */
    /* JADX WARN: Removed duplicated region for block: B:230:0x03d1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:237:0x03c3 A[Catch: all -> 0x00c4, TryCatch #2 {all -> 0x00c4, blocks: (B:280:0x00cd, B:283:0x00eb, B:285:0x00f3, B:286:0x00f7, B:288:0x010e, B:290:0x0116, B:274:0x0125, B:34:0x012d, B:36:0x0134, B:38:0x013c, B:42:0x0154, B:44:0x015a, B:48:0x0167, B:49:0x02d8, B:50:0x02e7, B:52:0x02ee, B:54:0x02f4, B:55:0x030a, B:57:0x0310, B:59:0x031a, B:62:0x033b, B:65:0x034d, B:68:0x0360, B:74:0x036d, B:76:0x0371, B:77:0x0373, B:85:0x038d, B:86:0x038e, B:87:0x039f, B:91:0x03bd, B:92:0x03c0, B:93:0x03c7, B:95:0x03f3, B:97:0x03f9, B:98:0x0409, B:101:0x044e, B:107:0x04e9, B:229:0x03fd, B:236:0x03ec, B:237:0x03c3, B:242:0x03b3, B:243:0x038f, B:245:0x0398, B:246:0x0303, B:247:0x0172, B:249:0x01c0, B:251:0x01c4, B:253:0x01ca, B:254:0x01f1, B:255:0x0219, B:258:0x0210, B:259:0x0230, B:260:0x0297, B:262:0x029d, B:264:0x02cc, B:266:0x02d3, B:271:0x014d, B:295:0x0107, B:300:0x00df, B:33:0x011d, B:292:0x00ff, B:231:0x03d1, B:233:0x03e1, B:79:0x0374, B:81:0x037a, B:82:0x038a, B:239:0x03a9, B:297:0x00d5), top: B:279:0x00cd, inners: #1, #3, #7, #9, #11, #13 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x035e  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x03bd A[Catch: all -> 0x00c4, TryCatch #2 {all -> 0x00c4, blocks: (B:280:0x00cd, B:283:0x00eb, B:285:0x00f3, B:286:0x00f7, B:288:0x010e, B:290:0x0116, B:274:0x0125, B:34:0x012d, B:36:0x0134, B:38:0x013c, B:42:0x0154, B:44:0x015a, B:48:0x0167, B:49:0x02d8, B:50:0x02e7, B:52:0x02ee, B:54:0x02f4, B:55:0x030a, B:57:0x0310, B:59:0x031a, B:62:0x033b, B:65:0x034d, B:68:0x0360, B:74:0x036d, B:76:0x0371, B:77:0x0373, B:85:0x038d, B:86:0x038e, B:87:0x039f, B:91:0x03bd, B:92:0x03c0, B:93:0x03c7, B:95:0x03f3, B:97:0x03f9, B:98:0x0409, B:101:0x044e, B:107:0x04e9, B:229:0x03fd, B:236:0x03ec, B:237:0x03c3, B:242:0x03b3, B:243:0x038f, B:245:0x0398, B:246:0x0303, B:247:0x0172, B:249:0x01c0, B:251:0x01c4, B:253:0x01ca, B:254:0x01f1, B:255:0x0219, B:258:0x0210, B:259:0x0230, B:260:0x0297, B:262:0x029d, B:264:0x02cc, B:266:0x02d3, B:271:0x014d, B:295:0x0107, B:300:0x00df, B:33:0x011d, B:292:0x00ff, B:231:0x03d1, B:233:0x03e1, B:79:0x0374, B:81:0x037a, B:82:0x038a, B:239:0x03a9, B:297:0x00d5), top: B:279:0x00cd, inners: #1, #3, #7, #9, #11, #13 }] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x03f9 A[Catch: all -> 0x00c4, TryCatch #2 {all -> 0x00c4, blocks: (B:280:0x00cd, B:283:0x00eb, B:285:0x00f3, B:286:0x00f7, B:288:0x010e, B:290:0x0116, B:274:0x0125, B:34:0x012d, B:36:0x0134, B:38:0x013c, B:42:0x0154, B:44:0x015a, B:48:0x0167, B:49:0x02d8, B:50:0x02e7, B:52:0x02ee, B:54:0x02f4, B:55:0x030a, B:57:0x0310, B:59:0x031a, B:62:0x033b, B:65:0x034d, B:68:0x0360, B:74:0x036d, B:76:0x0371, B:77:0x0373, B:85:0x038d, B:86:0x038e, B:87:0x039f, B:91:0x03bd, B:92:0x03c0, B:93:0x03c7, B:95:0x03f3, B:97:0x03f9, B:98:0x0409, B:101:0x044e, B:107:0x04e9, B:229:0x03fd, B:236:0x03ec, B:237:0x03c3, B:242:0x03b3, B:243:0x038f, B:245:0x0398, B:246:0x0303, B:247:0x0172, B:249:0x01c0, B:251:0x01c4, B:253:0x01ca, B:254:0x01f1, B:255:0x0219, B:258:0x0210, B:259:0x0230, B:260:0x0297, B:262:0x029d, B:264:0x02cc, B:266:0x02d3, B:271:0x014d, B:295:0x0107, B:300:0x00df, B:33:0x011d, B:292:0x00ff, B:231:0x03d1, B:233:0x03e1, B:79:0x0374, B:81:0x037a, B:82:0x038a, B:239:0x03a9, B:297:0x00d5), top: B:279:0x00cd, inners: #1, #3, #7, #9, #11, #13 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private AMapLocationServer m2661a(boolean z, boolean z2) throws Exception {
        AMapLocationServer aMapLocationServer;
        String str;
        C3855cc c3855cc;
        NetworkInfo networkInfo;
        String str2;
        String str3;
        String str4;
        String str5;
        int i;
        boolean z3;
        C3868cp c3868cp;
        short s;
        AMapLocationServer aMapLocationServer2;
        C3840bo m2853a;
        String str6;
        byte[] bArr;
        StringBuilder sb;
        String str7;
        int i2;
        String sb2;
        AMapLocationServer aMapLocationServer3 = new AMapLocationServer("");
        if (this.f3786n) {
            aMapLocationServer3.setErrorCode(15);
            this.f3786n = false;
            return aMapLocationServer3;
        }
        if (!z) {
            this.f3739N = C3876cx.m2985b();
            if (this.f3726A == null) {
                this.f3762al.append("context is null");
                aMapLocationServer3.setErrorCode(1);
                aMapLocationServer3.setLocationDetail(this.f3762al.toString());
                return aMapLocationServer3;
            }
        }
        try {
            String m3127f = C3885k.m3127f(this.f3726A);
            int m2966a = C3876cx.m2966a(-32768, 32767);
            String str8 = "api_serverSDK_130905";
            String str9 = "S128DF1572465B890OE3F7A13167KLEI";
            if (this.f3746W == null) {
                this.f3746W = new C3868cp();
            }
            if (!this.f3764an) {
                str8 = "UC_nlp_20131029";
                str9 = "BKZCHMBBSSUK7U8GLUKHBB56CCFF78U";
            }
            String str10 = str9;
            if (this.f3787o == null) {
                this.f3787o = new StringBuilder();
                str = "";
            } else {
                str = "";
                this.f3787o.delete(0, this.f3787o.length());
            }
            if (this.f3788p == null) {
                this.f3788p = new StringBuilder();
            } else {
                this.f3788p.delete(0, this.f3788p.length());
            }
            C3855cc c3855cc2 = this.f3729D;
            int m2775d = c3855cc2.m2775d();
            int m2776e = c3855cc2.m2776e();
            TelephonyManager m2778g = c3855cc2.m2778g();
            ArrayList<C3854cb> m2773b = c3855cc2.m2773b();
            String str11 = "";
            ArrayList<C3854cb> m2784m = c3855cc2.m2784m();
            String str12 = m2776e == 2 ? "1" : "0";
            if (m2778g != null) {
                try {
                    if (TextUtils.isEmpty(C3880f.f4179a)) {
                        try {
                            C3880f.f4179a = C3888n.m3168q(this.f3726A);
                        } catch (Throwable th) {
                            c3855cc = c3855cc2;
                            C3880f.m3097a(th, "APS", "getApsReq part4");
                        }
                    }
                    c3855cc = c3855cc2;
                    if (TextUtils.isEmpty(C3880f.f4179a)) {
                        C3880f.f4179a = "888888888888888";
                    }
                    if (TextUtils.isEmpty(C3880f.f4180b)) {
                        try {
                            C3880f.f4180b = m2778g.getSubscriberId();
                        } catch (Throwable th2) {
                            C3880f.m3097a(th2, "APS", "getApsReq part2");
                        }
                    }
                    if (TextUtils.isEmpty(C3880f.f4180b)) {
                        C3880f.f4180b = "888888888888888";
                    }
                } catch (Throwable th3) {
                    th = th3;
                    aMapLocationServer = aMapLocationServer3;
                    this.f3762al.append("get parames error:" + th.getMessage());
                    aMapLocationServer.setErrorCode(3);
                    aMapLocationServer.setLocationDetail(this.f3762al.toString());
                    aMapLocationServer.m556f(this.f3794z.toString());
                    return aMapLocationServer;
                }
            } else {
                c3855cc = c3855cc2;
            }
            try {
                networkInfo = this.f3727B.getActiveNetworkInfo();
            } catch (Throwable th4) {
                C3880f.m3097a(th4, "APS", "getApsReq part");
                networkInfo = null;
            }
            if (C3876cx.m2967a(networkInfo) != -1) {
                String m2988b = C3876cx.m2988b(m2778g);
                if (this.f3791s) {
                    C3856cd c3856cd = this.f3728C;
                    if (C3856cd.m2785a(this.f3736K)) {
                        str3 = "2";
                        str2 = m2988b;
                    }
                }
                str3 = "1";
                str2 = m2988b;
            } else {
                this.f3736K = null;
                str2 = str;
                str3 = "";
            }
            if (m2773b.isEmpty()) {
                str4 = str3;
                str5 = str2;
                i = m2966a;
            } else {
                StringBuilder sb3 = new StringBuilder();
                str4 = str3;
                if (m2776e == 1) {
                    str5 = str2;
                    i = m2966a;
                    m2667a("resetCdmaData");
                    C3854cb c3854cb = m2773b.get(0);
                    sb3.delete(0, sb3.length());
                    sb3.append("<mcc>");
                    sb3.append(c3854cb.f3866a);
                    sb3.append("</mcc>");
                    sb3.append("<mnc>");
                    sb3.append(c3854cb.f3867b);
                    sb3.append("</mnc>");
                    sb3.append("<lac>");
                    sb3.append(c3854cb.f3868c);
                    sb3.append("</lac>");
                    sb3.append("<cellid>");
                    sb3.append(c3854cb.f3869d);
                    sb3.append("</cellid>");
                    sb3.append("<signal>");
                    sb3.append(c3854cb.f3875j);
                    sb3.append("</signal>");
                    sb2 = sb3.toString();
                    int i3 = 1;
                    while (i3 < m2773b.size()) {
                        C3854cb c3854cb2 = m2773b.get(i3);
                        StringBuilder sb4 = this.f3787o;
                        String str13 = sb2;
                        sb4.append(c3854cb2.f3868c);
                        sb4.append(",");
                        StringBuilder sb5 = this.f3787o;
                        sb5.append(c3854cb2.f3869d);
                        sb5.append(",");
                        this.f3787o.append(c3854cb2.f3875j);
                        if (i3 < m2773b.size() - 1) {
                            this.f3787o.append("*");
                        }
                        i3++;
                        sb2 = str13;
                    }
                } else if (m2776e != 2) {
                    m2667a("resetCdmaData");
                    str5 = str2;
                    i = m2966a;
                    sb3.delete(0, sb3.length());
                } else {
                    C3854cb c3854cb3 = m2773b.get(0);
                    sb3.delete(0, sb3.length());
                    sb3.append("<mcc>");
                    sb3.append(c3854cb3.f3866a);
                    sb3.append("</mcc>");
                    sb3.append("<sid>");
                    sb3.append(c3854cb3.f3872g);
                    sb3.append("</sid>");
                    sb3.append("<nid>");
                    sb3.append(c3854cb3.f3873h);
                    sb3.append("</nid>");
                    sb3.append("<bid>");
                    sb3.append(c3854cb3.f3874i);
                    sb3.append("</bid>");
                    if (c3854cb3.f3871f <= 0 || c3854cb3.f3870e <= 0) {
                        str5 = str2;
                        i = m2966a;
                        m2667a("resetCdmaData");
                    } else {
                        if (m2658D()) {
                            str5 = str2;
                            i = m2966a;
                            C3871cs.m2917a(this.f3752ab, "setCdmaLatLon", Integer.valueOf(c3854cb3.f3870e), Integer.valueOf(c3854cb3.f3871f));
                        } else {
                            str5 = str2;
                            i = m2966a;
                        }
                        sb3.append("<lon>");
                        sb3.append(c3854cb3.f3871f);
                        sb3.append("</lon>");
                        sb3.append("<lat>");
                        sb3.append(c3854cb3.f3870e);
                        sb3.append("</lat>");
                    }
                    sb3.append("<signal>");
                    sb3.append(c3854cb3.f3875j);
                    sb3.append("</signal>");
                    sb2 = sb3.toString();
                }
                str11 = sb2;
                sb3.delete(0, sb3.length());
            }
            String str14 = str11;
            if ((m2775d & 4) != 4 || m2784m.isEmpty()) {
                this.f3746W.f3948C.clear();
            } else {
                this.f3746W.f3948C.clear();
                this.f3746W.f3948C.addAll(m2784m);
            }
            if (m2696t()) {
                C3856cd c3856cd2 = this.f3728C;
                if (C3856cd.m2785a(this.f3736K)) {
                    StringBuilder sb6 = this.f3788p;
                    sb6.append(this.f3736K.getBSSID());
                    sb6.append(",");
                    int rssi = this.f3736K.getRssi();
                    if (rssi >= -128) {
                        if (rssi > 127) {
                        }
                        StringBuilder sb7 = this.f3788p;
                        sb7.append(rssi);
                        sb7.append(",");
                        String ssid = this.f3736K.getSSID();
                        i2 = this.f3736K.getSSID().getBytes("UTF-8").length;
                        if (i2 >= 32) {
                            ssid = "unkwn";
                        }
                        this.f3788p.append(ssid.replace("*", "."));
                    }
                    rssi = 0;
                    StringBuilder sb72 = this.f3788p;
                    sb72.append(rssi);
                    sb72.append(",");
                    String ssid2 = this.f3736K.getSSID();
                    i2 = this.f3736K.getSSID().getBytes("UTF-8").length;
                    if (i2 >= 32) {
                    }
                    this.f3788p.append(ssid2.replace("*", "."));
                }
                if (this.f3730E != null) {
                    synchronized (this.f3776d) {
                        if (this.f3746W.f3951F != null) {
                            this.f3746W.f3951F.clear();
                            this.f3746W.f3951F.addAll(this.f3730E);
                        }
                    }
                }
            } else {
                m2694r();
                if (this.f3746W.f3951F != null) {
                    this.f3746W.f3951F.clear();
                }
            }
            try {
                try {
                    if (C3876cx.m2979a(this.f3737L, "reversegeo")) {
                        try {
                            z3 = this.f3737L.getBoolean("reversegeo");
                        } catch (Throwable th5) {
                            C3880f.m3097a(th5, "APS", "getApsReq part");
                        }
                        if (z3) {
                            c3868cp = this.f3746W;
                            s = 2;
                        } else {
                            c3868cp = this.f3746W;
                            s = 0;
                        }
                        c3868cp.f3960b = s;
                        if (C3876cx.m2979a(this.f3737L, "multi")) {
                            try {
                                if (this.f3737L.getString("multi").equals("1")) {
                                    C3868cp c3868cp2 = this.f3746W;
                                    c3868cp2.f3960b = (short) (c3868cp2.f3960b + 1);
                                }
                            } catch (Throwable th6) {
                                C3880f.m3097a(th6, "APS", "getApsReq");
                            }
                        }
                        if (m2682i()) {
                            this.f3734I.clear();
                            this.f3746W.f3952G.clear();
                        } else {
                            m2692p();
                        }
                        this.f3746W.f3961c = str8;
                        this.f3746W.f3962d = str10;
                        this.f3746W.f3964f = C3876cx.m3002d();
                        this.f3746W.f3965g = "android" + C3876cx.m3007e();
                        this.f3746W.f3966h = C3876cx.m2987b(this.f3726A);
                        this.f3746W.f3967i = str12;
                        this.f3746W.f3968j = "0";
                        this.f3746W.f3969k = !this.f3743R ? "1" : "0";
                        this.f3746W.f3970l = "0";
                        this.f3746W.f3971m = "0";
                        this.f3746W.f3972n = "0";
                        this.f3746W.f3973o = m3127f;
                        this.f3746W.f3974p = C3880f.f4179a;
                        this.f3746W.f3975q = C3880f.f4180b;
                        this.f3746W.f3977s = String.valueOf(i);
                        this.f3746W.f3978t = this.f3745V;
                        this.f3746W.f3980v = "3.3.0";
                        this.f3746W.f3981w = m2657C();
                        this.f3746W.f3979u = "";
                        this.f3746W.f3982x = str5;
                        this.f3746W.f3983y = str4;
                        this.f3746W.f3984z = m2775d;
                        this.f3746W.f3946A = str14;
                        this.f3746W.f3947B = this.f3787o.toString();
                        this.f3746W.f3949D = c3855cc.m2783l();
                        this.f3746W.f3953H = String.valueOf(C3876cx.m2985b() - f3721T);
                        this.f3746W.f3950E = this.f3788p.toString();
                        this.f3746W.f3952G = this.f3734I;
                        if (TextUtils.isEmpty(this.f3789q)) {
                            this.f3789q = C3888n.m3157f(this.f3726A);
                        }
                        this.f3746W.f3956K = this.f3789q;
                        this.f3787o.delete(0, this.f3787o.length());
                        this.f3788p.delete(0, this.f3788p.length());
                        C3867co c3867co = new C3867co();
                        m2853a = this.f3761ak.m2853a(this.f3726A, this.f3737L, this.f3746W.m2865a(), C3880f.m3093a(), z2);
                        int m2852a = this.f3761ak.m2852a();
                        if (m2853a == null) {
                            bArr = m2853a.f3699a;
                            str6 = m2853a.f3701c;
                        } else {
                            str6 = "";
                            bArr = null;
                        }
                        long j = m2852a;
                        aMapLocationServer2 = aMapLocationServer3;
                        aMapLocationServer2.m542a(j);
                        if (!z) {
                            return aMapLocationServer2;
                        }
                        if (bArr == null || bArr.length == 0) {
                            aMapLocationServer2.setErrorCode(4);
                            this.f3762al.append("please check the network");
                            if (!TextUtils.isEmpty(str6)) {
                                this.f3762al.append(" #csid:" + str6);
                            }
                            aMapLocationServer2.m556f(this.f3794z.toString());
                            aMapLocationServer2.setLocationDetail(this.f3762al.toString());
                            return aMapLocationServer2;
                        }
                        String str15 = new String(bArr, "UTF-8");
                        if (str15.contains("\"status\":\"0\"")) {
                            AMapLocationServer m2860a = c3867co.m2860a(str15, this.f3726A, m2853a);
                            m2860a.m556f(this.f3794z.toString());
                            m2860a.m542a(j);
                            return m2860a;
                        }
                        if (str15.contains("</body></html>")) {
                            aMapLocationServer2.setErrorCode(5);
                            if (m2656B()) {
                                sb = this.f3762al;
                                str7 = "make sure you are logged in to the network";
                            } else {
                                sb = this.f3762al;
                                str7 = "request may be intercepted";
                            }
                            sb.append(str7);
                            if (!TextUtils.isEmpty(str6)) {
                                this.f3762al.append(" #csid:" + str6);
                            }
                            aMapLocationServer2.setLocationDetail(this.f3762al.toString());
                            return aMapLocationServer2;
                        }
                        byte[] m2797a = C3857ce.m2797a(bArr);
                        if (m2797a == null) {
                            aMapLocationServer2.setErrorCode(5);
                            this.f3762al.append("decrypt response data error");
                            if (!TextUtils.isEmpty(str6)) {
                                this.f3762al.append(" #csid:" + str6);
                            }
                            aMapLocationServer2.setLocationDetail(this.f3762al.toString());
                            return aMapLocationServer2;
                        }
                        AMapLocationServer m2861a = c3867co.m2861a(m2797a);
                        if (m2861a == null) {
                            AMapLocationServer aMapLocationServer4 = new AMapLocationServer("");
                            aMapLocationServer4.setErrorCode(5);
                            this.f3762al.append("location is null");
                            if (!TextUtils.isEmpty(str6)) {
                                this.f3762al.append(" #csid:" + str6);
                            }
                            aMapLocationServer4.setLocationDetail(this.f3762al.toString());
                            return aMapLocationServer4;
                        }
                        m2861a.m556f(this.f3794z.toString());
                        try {
                            if (!"-1".equals(m2861a.m549c()) || m2861a.getAccuracy() > 5.0f) {
                                m2684j();
                            } else if (!this.f3781i && m2682i()) {
                                if (this.f3782j == null) {
                                    this.f3782j = new b();
                                }
                                this.f3780h.startLeScan(this.f3782j);
                                this.f3781i = true;
                            }
                        } catch (Throwable unused) {
                        }
                        m2861a.m542a(j);
                        if (m2861a.getErrorCode() != 0) {
                            if (!TextUtils.isEmpty(str6)) {
                                m2861a.setLocationDetail(m2861a.getLocationDetail() + " #csid:" + str6);
                            }
                            return m2861a;
                        }
                        if (!C3876cx.m2977a(m2861a)) {
                            this.f3755ae = m2861a.m541a();
                            m2861a.setErrorCode(6);
                            StringBuilder sb8 = this.f3762al;
                            StringBuilder sb9 = new StringBuilder("location faile retype:");
                            sb9.append(m2861a.m549c());
                            sb9.append(" rdesc:");
                            String str16 = this.f3755ae;
                            if (str16 == null) {
                                str16 = "null";
                            }
                            sb9.append(str16);
                            sb8.append(sb9.toString());
                            if (!TextUtils.isEmpty(str6)) {
                                this.f3762al.append(" #csid:" + str6);
                            }
                            m2861a.m556f(this.f3794z.toString());
                            m2861a.setLocationDetail(this.f3762al.toString());
                            return m2861a;
                        }
                        m2861a.m553e();
                        if (m2861a.getErrorCode() == 0 && m2861a.getLocationType() == 0) {
                            if ("-5".equals(m2861a.m549c()) || "1".equals(m2861a.m549c()) || "2".equals(m2861a.m549c()) || "14".equals(m2861a.m549c()) || "24".equals(m2861a.m549c()) || "-1".equals(m2861a.m549c())) {
                                m2861a.setLocationType(5);
                            } else {
                                m2861a.setLocationType(6);
                            }
                            this.f3762al.append(m2861a.m549c());
                            if (!TextUtils.isEmpty(str6)) {
                                this.f3762al.append(" #csid:" + str6);
                            }
                            m2861a.setLocationDetail(this.f3762al.toString());
                        }
                        m2861a.setOffset(this.f3764an);
                        m2861a.m545a(this.f3763am);
                        return m2861a;
                    }
                    aMapLocationServer2.m542a(j);
                    if (!z) {
                    }
                } catch (Throwable th7) {
                    th = th7;
                    C3880f.m3097a(th, "APS", "getApsLoc req");
                    aMapLocationServer2.setErrorCode(4);
                    aMapLocationServer2.m556f(this.f3794z.toString());
                    this.f3762al.append("please check the network");
                    aMapLocationServer2.setLocationDetail(this.f3762al.toString());
                    return aMapLocationServer2;
                }
                m2853a = this.f3761ak.m2853a(this.f3726A, this.f3737L, this.f3746W.m2865a(), C3880f.m3093a(), z2);
                int m2852a2 = this.f3761ak.m2852a();
                if (m2853a == null) {
                }
                long j2 = m2852a2;
                aMapLocationServer2 = aMapLocationServer3;
            } catch (Throwable th8) {
                C3880f.m3097a(th8, "APS", "getApsLoc buildV4Dot2");
                aMapLocationServer3.setErrorCode(3);
                this.f3762al.append("buildV4Dot2 error " + th8.getMessage());
                aMapLocationServer3.setLocationDetail(this.f3762al.toString());
                return aMapLocationServer3;
            }
            z3 = true;
            if (z3) {
            }
            c3868cp.f3960b = s;
            if (C3876cx.m2979a(this.f3737L, "multi")) {
            }
            if (m2682i()) {
            }
            this.f3746W.f3961c = str8;
            this.f3746W.f3962d = str10;
            this.f3746W.f3964f = C3876cx.m3002d();
            this.f3746W.f3965g = "android" + C3876cx.m3007e();
            this.f3746W.f3966h = C3876cx.m2987b(this.f3726A);
            this.f3746W.f3967i = str12;
            this.f3746W.f3968j = "0";
            this.f3746W.f3969k = !this.f3743R ? "1" : "0";
            this.f3746W.f3970l = "0";
            this.f3746W.f3971m = "0";
            this.f3746W.f3972n = "0";
            this.f3746W.f3973o = m3127f;
            this.f3746W.f3974p = C3880f.f4179a;
            this.f3746W.f3975q = C3880f.f4180b;
            this.f3746W.f3977s = String.valueOf(i);
            this.f3746W.f3978t = this.f3745V;
            this.f3746W.f3980v = "3.3.0";
            this.f3746W.f3981w = m2657C();
            this.f3746W.f3979u = "";
            this.f3746W.f3982x = str5;
            this.f3746W.f3983y = str4;
            this.f3746W.f3984z = m2775d;
            this.f3746W.f3946A = str14;
            this.f3746W.f3947B = this.f3787o.toString();
            this.f3746W.f3949D = c3855cc.m2783l();
            this.f3746W.f3953H = String.valueOf(C3876cx.m2985b() - f3721T);
            this.f3746W.f3950E = this.f3788p.toString();
            this.f3746W.f3952G = this.f3734I;
            if (TextUtils.isEmpty(this.f3789q)) {
            }
            this.f3746W.f3956K = this.f3789q;
            this.f3787o.delete(0, this.f3787o.length());
            this.f3788p.delete(0, this.f3788p.length());
            C3867co c3867co2 = new C3867co();
        } catch (Throwable th9) {
            th = th9;
            aMapLocationServer = aMapLocationServer3;
        }
    }

    /* renamed from: a */
    private String m2662a(byte[] bArr) {
        if (bArr != null && bArr.length > 24) {
            if (bArr[0] == 2 && bArr[1] == 1 && ((bArr[2] == 5 || bArr[2] == 6) && bArr[3] == 23)) {
                byte[] bArr2 = new byte[16];
                System.arraycopy(bArr, 9, bArr2, 0, 16);
                StringBuffer stringBuffer = new StringBuffer();
                for (byte b2 : bArr2) {
                    stringBuffer.append(String.format("%02X", Byte.valueOf(b2)));
                }
                String stringBuffer2 = stringBuffer.toString();
                String str = this.f3773aw.get(stringBuffer2);
                if (str != null) {
                    return str;
                }
                byte[] m2799a = C3857ce.m2799a(C3876cx.m3001c(bArr2), new BigInteger("8021267762677846189778330391499"), new BigInteger("49549924105414102803086139689747"));
                if (m2799a == null || m2799a.length < 8) {
                    return "";
                }
                StringBuffer stringBuffer3 = new StringBuffer();
                for (int i = 6; i > 0; i--) {
                    stringBuffer3.append(String.format("%02X", Byte.valueOf(m2799a[i])));
                }
                String stringBuffer4 = stringBuffer3.toString();
                this.f3773aw.put(stringBuffer2, stringBuffer4);
                return stringBuffer4;
            }
            if (bArr[0] == 2 && bArr[1] == 1 && bArr[2] == 6 && bArr[3] == 22 && bArr[5] == -88 && bArr[6] == 1 && bArr[7] == 32) {
                try {
                    byte[] m2803b = C3857ce.m2803b(C3876cx.m3005d(bArr), new byte[]{-1, -15, TarConstants.LF_CONTIG, BinaryMemcacheOpcodes.SASL_AUTH, 4, 21, 16, 20, -85, 9, 0, 2, -91, -43, -59, -75});
                    if (m2803b != null) {
                        StringBuffer stringBuffer5 = new StringBuffer();
                        for (int i2 = 0; i2 < 8; i2++) {
                            stringBuffer5.append(String.format("%02X", Byte.valueOf(m2803b[i2])));
                        }
                        return stringBuffer5.toString();
                    }
                } catch (Throwable unused) {
                }
            }
        }
        return "";
    }

    /* JADX WARN: Removed duplicated region for block: B:82:0x0186 A[Catch: all -> 0x018b, TRY_LEAVE, TryCatch #3 {, blocks: (B:3:0x0001, B:5:0x0007, B:8:0x000d, B:9:0x001c, B:13:0x0064, B:15:0x0076, B:17:0x007a, B:19:0x0086, B:21:0x008e, B:25:0x00ac, B:27:0x00b2, B:30:0x00a5, B:31:0x00bf, B:33:0x00c7, B:34:0x00cb, B:36:0x00d7, B:37:0x00dd, B:39:0x00ea, B:41:0x00f8, B:44:0x010b, B:48:0x0116, B:51:0x012b, B:71:0x0161, B:75:0x0169, B:77:0x016d, B:79:0x0173, B:80:0x0180, B:82:0x0186, B:93:0x006c, B:96:0x0029, B:98:0x002f, B:100:0x0015, B:24:0x0097), top: B:2:0x0001, inners: #2 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private synchronized StringBuilder m2663a(StringBuilder sb, boolean z) {
        boolean z2;
        C3855cc c3855cc = this.f3729D;
        if (this.f3742Q) {
            c3855cc.m2779h();
        }
        if (sb == null) {
            sb = new StringBuilder(700);
        } else {
            sb.delete(0, sb.length());
        }
        int m2776e = c3855cc.m2776e();
        ArrayList<C3854cb> m2773b = c3855cc.m2773b();
        if (m2776e == 1) {
            for (int i = 1; i < m2773b.size(); i++) {
                sb.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                sb.append(m2773b.get(i).f3867b);
                sb.append("|");
                sb.append(m2773b.get(i).f3868c);
                sb.append("|");
                sb.append(m2773b.get(i).f3869d);
            }
        }
        if (((!z && TextUtils.isEmpty(this.f3745V)) || this.f3745V.equals("00:00:00:00:00:00")) && this.f3736K != null) {
            this.f3745V = C3888n.m3160i(this.f3726A);
            if (this.f3726A != null && !TextUtils.isEmpty(this.f3745V)) {
                SharedPreferences sharedPreferences = this.f3726A.getSharedPreferences("pref", 0);
                String str = null;
                try {
                    str = C3889o.m3178a(this.f3745V.getBytes("UTF-8"));
                } catch (Throwable th) {
                    C3880f.m3097a(th, "APS", "setSmac");
                }
                if (!TextUtils.isEmpty(str)) {
                    sharedPreferences.edit().putString("smac", str).commit();
                }
            }
            if (TextUtils.isEmpty(this.f3745V)) {
                this.f3745V = "00:00:00:00:00:00";
            }
        }
        C3856cd c3856cd = this.f3728C;
        String bssid = C3856cd.m2785a(this.f3736K) ? this.f3736K.getBSSID() : "";
        this.f3786n = false;
        int size = this.f3730E.size();
        boolean z3 = false;
        boolean z4 = false;
        for (int i2 = 0; i2 < size; i2++) {
            String str2 = this.f3730E.get(i2).BSSID;
            if (!this.f3783k && !"<unknown ssid>".equals(this.f3730E.get(i2).SSID)) {
                z3 = true;
            }
            String str3 = "nb";
            if (bssid.equals(str2)) {
                str3 = "access";
                z4 = true;
            }
            sb.append(String.format(Locale.US, "#%s,%s", str2, str3));
        }
        if (this.f3730E.size() == 0) {
            z3 = true;
        }
        if (!this.f3783k && !z3) {
            List<WifiConfiguration> m2794g = this.f3728C.m2794g();
            int i3 = 0;
            z2 = false;
            while (m2794g != null) {
                try {
                    if (i3 >= m2794g.size()) {
                        break;
                    }
                    if (sb.toString().contains(m2794g.get(i3).BSSID)) {
                        z2 = true;
                    }
                    i3++;
                } catch (Throwable unused) {
                }
            }
            if (!this.f3783k && !z3 && !z2) {
                this.f3786n = true;
            }
            if (!z4 && !TextUtils.isEmpty(bssid)) {
                sb.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                sb.append(bssid);
                sb.append(",access");
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(0);
            }
        }
        z2 = false;
        if (!this.f3783k) {
            this.f3786n = true;
        }
        if (!z4) {
            sb.append(MqttTopic.MULTI_LEVEL_WILDCARD);
            sb.append(bssid);
            sb.append(",access");
        }
        if (sb.length() > 0) {
        }
        return sb;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(14:5|(10:(1:(3:9|10|(1:12)))|17|18|19|20|21|22|(2:24|(2:26|27))|30|(2:36|(2:38|39)(1:40))(2:34|35))|46|17|18|19|20|21|22|(0)|30|(1:32)|36|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0060, code lost:
    
        r2 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0051, code lost:
    
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0052, code lost:
    
        r2.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0064 A[Catch: all -> 0x0029, TRY_ENTER, TRY_LEAVE, TryCatch #3 {all -> 0x0029, blocks: (B:10:0x001e, B:24:0x0064, B:29:0x0093, B:30:0x0095, B:32:0x009e, B:34:0x00a4, B:36:0x00a8, B:38:0x00ac, B:45:0x0052, B:19:0x002d, B:27:0x0078), top: B:9:0x001e, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00ac A[Catch: all -> 0x0029, TRY_LEAVE, TryCatch #3 {all -> 0x0029, blocks: (B:10:0x001e, B:24:0x0064, B:29:0x0093, B:30:0x0095, B:32:0x009e, B:34:0x00a4, B:36:0x00a8, B:38:0x00ac, B:45:0x0052, B:19:0x002d, B:27:0x0078), top: B:9:0x001e, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static /* synthetic */ void m2666a(C3846bu c3846bu, int i) {
        Object obj;
        if (!c3846bu.m2697u() || !C3869cq.m2905v()) {
            return;
        }
        int i2 = 674234367;
        if (i != 0) {
            if (i != 1) {
                if (i == 2) {
                    try {
                        if (c3846bu.m2656B()) {
                            i2 = 2083520511;
                        }
                    } catch (Throwable th) {
                        C3880f.m3097a(th, "APS", "up");
                        return;
                    }
                }
            }
            Object obj2 = c3846bu.f3751aa;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(C3898x.f4338g, 1);
            jSONObject.put(LinkFormat.DOMAIN, i2);
            jSONObject.put("u", 1);
            C3871cs.m2917a(obj2, "callBackWrapData", jSONObject.toString());
            obj = C3871cs.m2917a(c3846bu.f3751aa, "getReportDate", new Object[0]);
            if (obj != null) {
                String m2856b = c3846bu.f3761ak.m2856b((byte[]) obj, c3846bu.f3726A, "http://cgicol.amap.com/collection/writedata?ver=v1.0_ali&");
                if (c3846bu.m2697u()) {
                    try {
                        c3846bu.f3749Z = ((Integer) C3871cs.m2917a(c3846bu.f3751aa, "setUploadResult", m2856b, Integer.valueOf(i2))).intValue();
                    } catch (Throwable unused) {
                        c3846bu.f3749Z = 3;
                    }
                }
            }
            c3846bu.m2700x();
            if (!c3846bu.m2697u() && c3846bu.m2702z() == 0) {
                c3846bu.m2655A();
                return;
            } else {
                if (c3846bu.f3749Z < 3) {
                    c3846bu.m2655A();
                    return;
                }
                return;
            }
        }
        i2 = 70254591;
        Object obj22 = c3846bu.f3751aa;
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(C3898x.f4338g, 1);
        jSONObject2.put(LinkFormat.DOMAIN, i2);
        jSONObject2.put("u", 1);
        C3871cs.m2917a(obj22, "callBackWrapData", jSONObject2.toString());
        obj = C3871cs.m2917a(c3846bu.f3751aa, "getReportDate", new Object[0]);
        if (obj != null) {
        }
        c3846bu.m2700x();
        if (!c3846bu.m2697u()) {
        }
        if (c3846bu.f3749Z < 3) {
        }
    }

    /* renamed from: a */
    private void m2667a(String str) {
        try {
            if (m2658D()) {
                try {
                    C3871cs.m2917a(this.f3752ab, str, new Object[0]);
                } catch (Throwable unused) {
                }
                if (this.f3726A == null) {
                }
            } else if (this.f3752ab != null) {
                C3871cs.m2917a(this.f3752ab, "stopOff", new Object[0]);
                this.f3784l = false;
            }
        } catch (Throwable unused2) {
        }
    }

    /* renamed from: a */
    private static boolean m2668a(int i) {
        int i2 = 20;
        try {
            i2 = WifiManager.calculateSignalLevel(i, 20);
        } catch (ArithmeticException e) {
            C3880f.m3097a(e, "APS", "wifiSigFine");
        }
        return i2 > 0;
    }

    /* renamed from: b */
    private void m2670b(JSONObject jSONObject) {
        try {
            if (m2658D()) {
                C3871cs.m2917a(this.f3752ab, "setLastLoc", jSONObject);
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: c */
    private void m2672c(Context context) {
        try {
            if (this.f3726A != null) {
                return;
            }
            this.f3726A = context.getApplicationContext();
            C3869cq.m2883e(this.f3726A);
            C3876cx.m2987b(this.f3726A);
            if (this.f3728C == null) {
                WifiManager wifiManager = (WifiManager) C3876cx.m2970a(this.f3726A, "wifi");
                Context context2 = this.f3726A;
                JSONObject jSONObject = this.f3737L;
                this.f3728C = new C3856cd(context2, wifiManager);
            }
            if (this.f3729D == null) {
                Context context3 = this.f3726A;
                JSONObject jSONObject2 = this.f3737L;
                this.f3729D = new C3855cc(context3);
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "APS", "initBase");
        }
    }

    /* renamed from: d */
    private void m2673d(Context context) {
        try {
            if (C3869cq.m2906w() && this.f3752ab == null && !this.f3769as) {
                C3893s m3092a = C3880f.m3092a("OfflineLocation", "1.0.0");
                this.f3769as = C3873cu.m2936a(context, m3092a);
                if (!this.f3769as) {
                    this.f3769as = true;
                } else {
                    try {
                        this.f3752ab = C3819au.m2476a(context, m3092a, this.f3779g, null, new Class[]{Context.class}, new Object[]{context});
                    } catch (Throwable unused) {
                    }
                }
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "APS", "initOffLocation");
        }
    }

    /* renamed from: h */
    private void m2679h() {
        this.f3761ak = C3865cm.m2848a(this.f3726A);
        if (this.f3727B == null) {
            this.f3727B = (ConnectivityManager) C3876cx.m2970a(this.f3726A, "connectivity");
        }
        this.f3746W = new C3868cp();
    }

    /* renamed from: i */
    static /* synthetic */ void m2681i(C3846bu c3846bu) {
        if (c3846bu.m2697u()) {
            if (!C3869cq.m2905v()) {
                c3846bu.m2655A();
                return;
            }
            if (c3846bu.f3748Y == null) {
                c3846bu.f3748Y = new TimerTask() { // from class: com.loc.bu.1

                    /* renamed from: a */
                    final /* synthetic */ int f3795a = 2;

                    @Override // java.util.TimerTask, java.lang.Runnable
                    public final void run() {
                        try {
                            Thread.currentThread().setPriority(1);
                            long m2985b = C3876cx.m2985b() - C3846bu.this.f3741P;
                            if (C3846bu.this.m2697u() && C3846bu.this.m2702z() == 0) {
                                C3846bu.this.m2655A();
                            }
                            if (m2985b < 10000) {
                                return;
                            }
                            if (C3846bu.this.m2656B()) {
                                C3846bu.m2666a(C3846bu.this, this.f3795a);
                            } else {
                                C3846bu.this.m2655A();
                            }
                        } catch (Throwable th) {
                            C3880f.m3097a(th, "APS", "timerTaskU run");
                        }
                    }
                };
            }
            if (c3846bu.f3747X == null) {
                c3846bu.f3747X = new Timer("T-U", false);
                c3846bu.f3747X.schedule(c3846bu.f3748Y, 2000L, 2000L);
            }
        }
    }

    /* renamed from: i */
    private boolean m2682i() {
        try {
            if (this.f3780h != null && this.f3780h.isEnabled() && C3869cq.m2868C()) {
                return C3876cx.m2996c() >= 18;
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: j */
    private void m2684j() {
        try {
            if (C3876cx.m2996c() < 18 || this.f3780h == null) {
                return;
            }
            if (this.f3782j == null) {
                this.f3782j = new b();
            }
            if (this.f3781i) {
                this.f3780h.stopLeScan(this.f3782j);
            }
            this.f3781i = false;
            this.f3734I.clear();
            this.f3733H.clear();
        } catch (Throwable unused) {
        }
    }

    /* renamed from: k */
    private void m2685k() {
        try {
            if (m2658D()) {
                C3871cs.m2917a(this.f3752ab, "resetPureOfflineCache", new Object[0]);
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: l */
    private void m2688l() {
        try {
            if (this.f3735J == null) {
                this.f3735J = new c(this, (byte) 0);
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
            intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.AIRPLANE_MODE");
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            this.f3726A.registerReceiver(this.f3735J, intentFilter);
            m2695s();
        } catch (Throwable th) {
            C3880f.m3097a(th, "APS", "initBroadcastListener");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x01b4 A[Catch: all -> 0x0218, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0005, B:6:0x0010, B:8:0x002a, B:14:0x0047, B:15:0x004f, B:16:0x01ae, B:18:0x01b4, B:20:0x01bc, B:21:0x01ca, B:25:0x0054, B:27:0x005a, B:29:0x00a7, B:33:0x00b7, B:34:0x00ba, B:36:0x00c0, B:38:0x00c6, B:40:0x0109, B:44:0x0119, B:46:0x011d, B:48:0x0125, B:52:0x0133, B:54:0x013d, B:56:0x0145, B:57:0x014b, B:58:0x0184, B:60:0x0192, B:61:0x01a4, B:62:0x0150, B:64:0x0158, B:66:0x0164, B:67:0x0169, B:69:0x017f, B:71:0x0030, B:73:0x0034, B:76:0x01df, B:80:0x01f2, B:82:0x01fa, B:85:0x01fe, B:88:0x020b, B:91:0x0213, B:95:0x000b), top: B:2:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0192 A[Catch: all -> 0x0218, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0005, B:6:0x0010, B:8:0x002a, B:14:0x0047, B:15:0x004f, B:16:0x01ae, B:18:0x01b4, B:20:0x01bc, B:21:0x01ca, B:25:0x0054, B:27:0x005a, B:29:0x00a7, B:33:0x00b7, B:34:0x00ba, B:36:0x00c0, B:38:0x00c6, B:40:0x0109, B:44:0x0119, B:46:0x011d, B:48:0x0125, B:52:0x0133, B:54:0x013d, B:56:0x0145, B:57:0x014b, B:58:0x0184, B:60:0x0192, B:61:0x01a4, B:62:0x0150, B:64:0x0158, B:66:0x0164, B:67:0x0169, B:69:0x017f, B:71:0x0030, B:73:0x0034, B:76:0x01df, B:80:0x01f2, B:82:0x01fa, B:85:0x01fe, B:88:0x020b, B:91:0x0213, B:95:0x000b), top: B:2:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01a4 A[Catch: all -> 0x0218, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0005, B:6:0x0010, B:8:0x002a, B:14:0x0047, B:15:0x004f, B:16:0x01ae, B:18:0x01b4, B:20:0x01bc, B:21:0x01ca, B:25:0x0054, B:27:0x005a, B:29:0x00a7, B:33:0x00b7, B:34:0x00ba, B:36:0x00c0, B:38:0x00c6, B:40:0x0109, B:44:0x0119, B:46:0x011d, B:48:0x0125, B:52:0x0133, B:54:0x013d, B:56:0x0145, B:57:0x014b, B:58:0x0184, B:60:0x0192, B:61:0x01a4, B:62:0x0150, B:64:0x0158, B:66:0x0164, B:67:0x0169, B:69:0x017f, B:71:0x0030, B:73:0x0034, B:76:0x01df, B:80:0x01f2, B:82:0x01fa, B:85:0x01fe, B:88:0x020b, B:91:0x0213, B:95:0x000b), top: B:2:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0158 A[Catch: all -> 0x0218, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0005, B:6:0x0010, B:8:0x002a, B:14:0x0047, B:15:0x004f, B:16:0x01ae, B:18:0x01b4, B:20:0x01bc, B:21:0x01ca, B:25:0x0054, B:27:0x005a, B:29:0x00a7, B:33:0x00b7, B:34:0x00ba, B:36:0x00c0, B:38:0x00c6, B:40:0x0109, B:44:0x0119, B:46:0x011d, B:48:0x0125, B:52:0x0133, B:54:0x013d, B:56:0x0145, B:57:0x014b, B:58:0x0184, B:60:0x0192, B:61:0x01a4, B:62:0x0150, B:64:0x0158, B:66:0x0164, B:67:0x0169, B:69:0x017f, B:71:0x0030, B:73:0x0034, B:76:0x01df, B:80:0x01f2, B:82:0x01fa, B:85:0x01fe, B:88:0x020b, B:91:0x0213, B:95:0x000b), top: B:2:0x0001 }] */
    /* renamed from: m */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private synchronized String m2689m() {
        boolean z;
        StringBuilder sb;
        String str;
        StringBuilder sb2;
        String str2;
        StringBuilder sb3;
        String str3;
        String str4;
        if (this.f3742Q) {
            this.f3729D.m2779h();
        } else {
            this.f3729D.m2781j();
        }
        String str5 = "";
        this.f3736K = this.f3728C.m2789b();
        int m2776e = this.f3729D.m2776e();
        ArrayList<C3854cb> m2773b = this.f3729D.m2773b();
        if ((m2773b != null && !m2773b.isEmpty()) || (this.f3730E != null && !this.f3730E.isEmpty())) {
            if (m2776e != 0) {
                if (m2776e != 1) {
                    if (m2776e != 2) {
                        this.f3785m = 11;
                        sb2 = this.f3762al;
                        str2 = "get cgi failure";
                        sb2.append(str2);
                    } else if (!m2773b.isEmpty()) {
                        C3854cb c3854cb = m2773b.get(0);
                        sb3 = new StringBuilder();
                        sb3.append(c3854cb.f3866a);
                        sb3.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                        sb3.append(c3854cb.f3867b);
                        sb3.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                        sb3.append(c3854cb.f3872g);
                        sb3.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                        sb3.append(c3854cb.f3873h);
                        sb3.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                        sb3.append(c3854cb.f3874i);
                        sb3.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                        sb3.append("network");
                        sb3.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                        if (this.f3730E.isEmpty()) {
                            C3856cd c3856cd = this.f3728C;
                            if (!C3856cd.m2785a(this.f3736K)) {
                                str4 = "cgi";
                                sb3.append(str4);
                                str5 = sb3.toString();
                            }
                        }
                        str4 = "cgiwifi";
                        sb3.append(str4);
                        str5 = sb3.toString();
                    }
                } else if (!m2773b.isEmpty()) {
                    C3854cb c3854cb2 = m2773b.get(0);
                    sb3 = new StringBuilder();
                    sb3.append(c3854cb2.f3866a);
                    sb3.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                    sb3.append(c3854cb2.f3867b);
                    sb3.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                    sb3.append(c3854cb2.f3868c);
                    sb3.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                    sb3.append(c3854cb2.f3869d);
                    sb3.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                    sb3.append("network");
                    sb3.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                    if (this.f3730E.isEmpty()) {
                        C3856cd c3856cd2 = this.f3728C;
                        if (!C3856cd.m2785a(this.f3736K)) {
                            str3 = "cgi";
                            sb3.append(str3);
                            str5 = sb3.toString();
                        }
                    }
                    str3 = "cgiwifi";
                    sb3.append(str3);
                    str5 = sb3.toString();
                }
                if (!TextUtils.isEmpty(str5)) {
                    if (!str5.startsWith(MqttTopic.MULTI_LEVEL_WILDCARD)) {
                        str5 = MqttTopic.MULTI_LEVEL_WILDCARD + str5;
                    }
                    str5 = C3876cx.m3015h() + str5;
                }
                return str5;
            }
            if (this.f3730E.isEmpty()) {
                C3856cd c3856cd3 = this.f3728C;
                if (!C3856cd.m2785a(this.f3736K)) {
                    z = false;
                    C3856cd c3856cd4 = this.f3728C;
                    if (C3856cd.m2785a(this.f3736K) || !this.f3730E.isEmpty()) {
                        if (this.f3730E.size() == 1) {
                            this.f3785m = 2;
                            C3856cd c3856cd5 = this.f3728C;
                            if (!C3856cd.m2785a(this.f3736K)) {
                                sb = this.f3762al;
                                str = "⊗ access wifi & around wifi 1";
                            } else if (this.f3736K.getBSSID().equals(this.f3730E.get(0).BSSID)) {
                                sb = this.f3762al;
                                str = "same access wifi & around wifi 1";
                            }
                        }
                        String format = String.format(Locale.US, "#%s#", "network");
                        if (z) {
                            str5 = format + "wifi";
                            if (!TextUtils.isEmpty(str5)) {
                            }
                            return str5;
                        }
                        str5 = "";
                        this.f3785m = 2;
                        sb2 = this.f3762al;
                        str2 = "is network & no wifi";
                        sb2.append(str2);
                        if (!TextUtils.isEmpty(str5)) {
                        }
                        return str5;
                    }
                    this.f3785m = 2;
                    sb = this.f3762al;
                    str = "⊗ around wifi(s) & has access wifi";
                    sb.append(str);
                    z = false;
                    String format2 = String.format(Locale.US, "#%s#", "network");
                    if (z) {
                    }
                }
            }
            z = true;
            C3856cd c3856cd42 = this.f3728C;
            if (C3856cd.m2785a(this.f3736K)) {
            }
            if (this.f3730E.size() == 1) {
            }
            String format22 = String.format(Locale.US, "#%s#", "network");
            if (z) {
            }
        }
        String m2782k = this.f3729D.m2782k();
        String m2793f = this.f3728C.m2793f();
        if (m2782k == null && m2793f == null) {
            if (!C3876cx.m3010f(this.f3726A)) {
                this.f3785m = 12;
                return "";
            }
            this.f3762al.append("⊗ lstCgi & ⊗ wifis");
            this.f3785m = 13;
            return "";
        }
        this.f3785m = 12;
        StringBuilder sb4 = this.f3762al;
        if (m2782k == null) {
            m2782k = m2793f;
        }
        sb4.append(m2782k);
        return "";
    }

    /* renamed from: n */
    private void m2690n() {
        long m2985b = C3876cx.m2985b();
        boolean z = false;
        if (!m2696t()) {
            this.f3791s = false;
        } else if (f3720S == 0 || (C3876cx.m2985b() - f3720S >= 5000 && C3876cx.m2985b() - f3721T >= 1500)) {
            z = true;
        }
        if (z || C3876cx.m2985b() - f3721T > 5000) {
            if (m2985b - this.f3740O >= 10000) {
                synchronized (this.f3776d) {
                    this.f3731F.clear();
                }
            }
            m2695s();
            if (m2985b - this.f3740O >= 10000) {
                for (int i = 20; i > 0 && this.f3731F.isEmpty(); i--) {
                    try {
                        Thread.sleep(150L);
                    } catch (Throwable unused) {
                    }
                }
            }
            synchronized (this.f3776d) {
            }
        }
        if (!this.f3731F.isEmpty() || this.f3728C == null) {
            return;
        }
        f3721T = C3876cx.m2985b();
        List<ScanResult> m2786a = this.f3728C.m2786a();
        if (m2786a != null) {
            synchronized (this.f3776d) {
                this.f3731F.addAll(m2786a);
            }
        }
    }

    /* renamed from: o */
    private synchronized void m2691o() {
        ArrayList<ScanResult> arrayList = this.f3730E;
        ArrayList<ScanResult> arrayList2 = this.f3731F;
        arrayList.clear();
        synchronized (this.f3776d) {
            if (arrayList2 != null) {
                if (arrayList2.size() > 0) {
                    arrayList.addAll(arrayList2);
                }
            }
        }
    }

    /* renamed from: p */
    private synchronized void m2692p() {
        ArrayList<a> arrayList = this.f3734I;
        ArrayList<a> arrayList2 = this.f3733H;
        arrayList.clear();
        synchronized (this.f3750a) {
            if (arrayList2 != null) {
                if (arrayList2.size() > 0) {
                    if (arrayList2.size() > 20) {
                        Collections.sort(arrayList2);
                    }
                    for (int i = 0; i < arrayList2.size(); i++) {
                        arrayList.add(arrayList2.get(i));
                        if (i >= 30) {
                            break;
                        }
                    }
                }
            }
        }
    }

    /* renamed from: q */
    private synchronized void m2693q() {
        String str;
        int i;
        if (this.f3730E != null && !this.f3730E.isEmpty()) {
            if (C3876cx.m2985b() - f3721T > 3600000) {
                m2694r();
                this.f3730E.clear();
            }
            boolean m3009f = C3876cx.m3009f();
            if (C3876cx.m2979a(this.f3737L, "nbssid")) {
                try {
                    if (this.f3737L.getString("nbssid").equals("1")) {
                        m3009f = true;
                    } else if (this.f3737L.getString("nbssid").equals("0")) {
                        m3009f = false;
                    }
                } catch (Throwable th) {
                    C3880f.m3097a(th, "APS", "setWifiOrder part");
                }
            }
            if (this.f3790r == null) {
                this.f3790r = new TreeMap<>(Collections.reverseOrder());
            }
            this.f3790r.clear();
            int size = this.f3730E.size();
            for (int i2 = 0; i2 < size; i2++) {
                ScanResult scanResult = this.f3730E.get(i2);
                if (C3876cx.m2993b(scanResult != null ? scanResult.BSSID : "") && (size <= 20 || m2668a(scanResult.level))) {
                    if (TextUtils.isEmpty(scanResult.SSID)) {
                        str = "unkwn";
                    } else if (m3009f) {
                        scanResult.SSID = scanResult.SSID.replace("*", ".");
                        try {
                            i = scanResult.SSID.getBytes("UTF-8").length;
                        } catch (Throwable th2) {
                            C3880f.m3097a(th2, "APS", "setWifiOrder");
                            i = 32;
                        }
                        if (i >= 32 && !"<unknown ssid>".equals(scanResult.SSID)) {
                            str = String.valueOf(i2);
                        }
                        this.f3790r.put(Integer.valueOf((scanResult.level * 30) + i2), scanResult);
                    } else {
                        if (!"<unknown ssid>".equals(scanResult.SSID)) {
                            str = String.valueOf(i2);
                        }
                        this.f3790r.put(Integer.valueOf((scanResult.level * 30) + i2), scanResult);
                    }
                    scanResult.SSID = str;
                    this.f3790r.put(Integer.valueOf((scanResult.level * 30) + i2), scanResult);
                }
            }
            this.f3730E.clear();
            Iterator<ScanResult> it = this.f3790r.values().iterator();
            while (it.hasNext()) {
                this.f3730E.add(it.next());
            }
            this.f3790r.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r */
    public void m2694r() {
        this.f3736K = null;
        synchronized (this.f3776d) {
            this.f3731F.clear();
            this.f3732G.clear();
        }
    }

    /* renamed from: s */
    private void m2695s() {
        if (this.f3793u) {
            try {
                if (this.f3728C.m2791d()) {
                    f3720S = C3876cx.m2985b();
                }
            } catch (Throwable th) {
                C3880f.m3097a(th, "APS", "updateWifi");
            }
        }
    }

    /* renamed from: t */
    private boolean m2696t() {
        if (this.f3728C != null) {
            return this.f3728C.m2792e();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u */
    public boolean m2697u() {
        return this.f3751aa != null;
    }

    /* renamed from: v */
    private boolean m2698v() {
        try {
            if (m2697u()) {
                return m2699w();
            }
            return false;
        } catch (Throwable th) {
            C3880f.m3097a(th, "APS", "collStarted");
            return false;
        }
    }

    /* renamed from: w */
    private boolean m2699w() {
        try {
            return ((Boolean) C3871cs.m2917a(this.f3751aa, "isStarted", new Object[0])).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: x */
    private void m2700x() {
        if (m2697u() && m2697u() && m2702z() > 0) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y */
    public void m2701y() {
        if (m2697u() && m2698v()) {
            C3880f.f4184f = 20;
            try {
                C3871cs.m2917a(this.f3751aa, "destroy", new Object[0]);
            } catch (Throwable th) {
                C3880f.m3097a(th, "APS", "stop3rdCM");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: z */
    public int m2702z() {
        try {
            return ((Integer) C3871cs.m2917a(this.f3751aa, "getLeftUploadNum", new Object[0])).intValue();
        } catch (Throwable unused) {
            return 0;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:13|(4:15|(1:22)|23|(7:25|(2:55|56)|27|28|(1:30)(2:51|(1:53))|31|(2:35|(5:38|(4:46|47|41|(1:45))|40|41|(2:43|45)))))|59|(4:61|(1:63)(1:211)|64|(3:66|67|(5:79|80|81|82|(9:84|(1:86)|87|(1:91)|93|(2:95|(3:97|98|99))|100|101|102)(21:104|105|(1:109)|111|(1:113)(1:205)|(15:117|118|(12:198|121|(2:123|(9:127|128|(1:193)(4:138|(2:140|(1:144))(1:192)|145|(3:147|148|149))|(1:151)(1:191)|152|(2:187|(2:189|186)(1:190))(2:156|(1:158)(2:185|186))|159|(1:163)|(7:(7:181|(1:183)|171|(2:173|(1:175))|176|177|178)(1:184)|170|171|(0)|176|177|178)(7:167|(2:169|170)|171|(0)|176|177|178)))|194|128|(1:130)|193|(0)(0)|152|(1:154)|187|(0)(0))|120|121|(0)|194|128|(0)|193|(0)(0)|152|(0)|187|(0)(0))|204|118|(13:195|198|121|(0)|194|128|(0)|193|(0)(0)|152|(0)|187|(0)(0))|120|121|(0)|194|128|(0)|193|(0)(0)|152|(0)|187|(0)(0)))(5:71|(1:75)|76|77|78)))|212|67|(1:69)|79|80|81|82|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:209:0x014f, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:210:0x0150, code lost:
    
        com.loc.C3880f.m3097a(r0, "APS", "getLocation");
     */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01ac A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0217 A[Catch: all -> 0x03e3, TryCatch #1 {, blocks: (B:4:0x0003, B:6:0x000c, B:7:0x0017, B:9:0x001c, B:13:0x0038, B:15:0x0042, B:17:0x0049, B:19:0x004d, B:22:0x0052, B:23:0x0059, B:25:0x005d, B:31:0x008b, B:33:0x0093, B:35:0x009d, B:38:0x00ab, B:41:0x00d7, B:43:0x00dd, B:45:0x00e5, B:50:0x00c4, B:59:0x00e7, B:61:0x00f6, B:63:0x00fe, B:69:0x0117, B:71:0x011f, B:73:0x0123, B:75:0x012f, B:76:0x0134, B:79:0x0138, B:82:0x0157, B:84:0x0165, B:86:0x0169, B:89:0x0171, B:91:0x0177, B:93:0x017f, B:95:0x0185, B:97:0x0191, B:100:0x0195, B:111:0x01d2, B:113:0x01de, B:115:0x01e8, B:118:0x01fc, B:121:0x0213, B:123:0x0217, B:128:0x0230, B:130:0x0236, B:134:0x023e, B:136:0x0242, B:138:0x024e, B:140:0x025f, B:142:0x0266, B:145:0x0273, B:147:0x027b, B:151:0x028e, B:152:0x0297, B:154:0x029b, B:156:0x02a5, B:158:0x02b0, B:159:0x02d3, B:163:0x02ff, B:165:0x030f, B:167:0x0317, B:169:0x0327, B:170:0x0338, B:171:0x036a, B:173:0x038f, B:175:0x03a9, B:176:0x03d3, B:181:0x033e, B:183:0x034e, B:184:0x0359, B:185:0x02b5, B:186:0x02bd, B:187:0x02c0, B:189:0x02c4, B:190:0x02cd, B:191:0x0295, B:195:0x0204, B:199:0x01ec, B:201:0x01f0, B:207:0x01cb, B:210:0x0150, B:47:0x00b3, B:105:0x01ac, B:107:0x01b2, B:109:0x01b6, B:81:0x013f), top: B:3:0x0003, inners: #0, #2, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0236 A[Catch: all -> 0x03e3, TryCatch #1 {, blocks: (B:4:0x0003, B:6:0x000c, B:7:0x0017, B:9:0x001c, B:13:0x0038, B:15:0x0042, B:17:0x0049, B:19:0x004d, B:22:0x0052, B:23:0x0059, B:25:0x005d, B:31:0x008b, B:33:0x0093, B:35:0x009d, B:38:0x00ab, B:41:0x00d7, B:43:0x00dd, B:45:0x00e5, B:50:0x00c4, B:59:0x00e7, B:61:0x00f6, B:63:0x00fe, B:69:0x0117, B:71:0x011f, B:73:0x0123, B:75:0x012f, B:76:0x0134, B:79:0x0138, B:82:0x0157, B:84:0x0165, B:86:0x0169, B:89:0x0171, B:91:0x0177, B:93:0x017f, B:95:0x0185, B:97:0x0191, B:100:0x0195, B:111:0x01d2, B:113:0x01de, B:115:0x01e8, B:118:0x01fc, B:121:0x0213, B:123:0x0217, B:128:0x0230, B:130:0x0236, B:134:0x023e, B:136:0x0242, B:138:0x024e, B:140:0x025f, B:142:0x0266, B:145:0x0273, B:147:0x027b, B:151:0x028e, B:152:0x0297, B:154:0x029b, B:156:0x02a5, B:158:0x02b0, B:159:0x02d3, B:163:0x02ff, B:165:0x030f, B:167:0x0317, B:169:0x0327, B:170:0x0338, B:171:0x036a, B:173:0x038f, B:175:0x03a9, B:176:0x03d3, B:181:0x033e, B:183:0x034e, B:184:0x0359, B:185:0x02b5, B:186:0x02bd, B:187:0x02c0, B:189:0x02c4, B:190:0x02cd, B:191:0x0295, B:195:0x0204, B:199:0x01ec, B:201:0x01f0, B:207:0x01cb, B:210:0x0150, B:47:0x00b3, B:105:0x01ac, B:107:0x01b2, B:109:0x01b6, B:81:0x013f), top: B:3:0x0003, inners: #0, #2, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:151:0x028e A[Catch: all -> 0x03e3, TRY_ENTER, TryCatch #1 {, blocks: (B:4:0x0003, B:6:0x000c, B:7:0x0017, B:9:0x001c, B:13:0x0038, B:15:0x0042, B:17:0x0049, B:19:0x004d, B:22:0x0052, B:23:0x0059, B:25:0x005d, B:31:0x008b, B:33:0x0093, B:35:0x009d, B:38:0x00ab, B:41:0x00d7, B:43:0x00dd, B:45:0x00e5, B:50:0x00c4, B:59:0x00e7, B:61:0x00f6, B:63:0x00fe, B:69:0x0117, B:71:0x011f, B:73:0x0123, B:75:0x012f, B:76:0x0134, B:79:0x0138, B:82:0x0157, B:84:0x0165, B:86:0x0169, B:89:0x0171, B:91:0x0177, B:93:0x017f, B:95:0x0185, B:97:0x0191, B:100:0x0195, B:111:0x01d2, B:113:0x01de, B:115:0x01e8, B:118:0x01fc, B:121:0x0213, B:123:0x0217, B:128:0x0230, B:130:0x0236, B:134:0x023e, B:136:0x0242, B:138:0x024e, B:140:0x025f, B:142:0x0266, B:145:0x0273, B:147:0x027b, B:151:0x028e, B:152:0x0297, B:154:0x029b, B:156:0x02a5, B:158:0x02b0, B:159:0x02d3, B:163:0x02ff, B:165:0x030f, B:167:0x0317, B:169:0x0327, B:170:0x0338, B:171:0x036a, B:173:0x038f, B:175:0x03a9, B:176:0x03d3, B:181:0x033e, B:183:0x034e, B:184:0x0359, B:185:0x02b5, B:186:0x02bd, B:187:0x02c0, B:189:0x02c4, B:190:0x02cd, B:191:0x0295, B:195:0x0204, B:199:0x01ec, B:201:0x01f0, B:207:0x01cb, B:210:0x0150, B:47:0x00b3, B:105:0x01ac, B:107:0x01b2, B:109:0x01b6, B:81:0x013f), top: B:3:0x0003, inners: #0, #2, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:154:0x029b A[Catch: all -> 0x03e3, TryCatch #1 {, blocks: (B:4:0x0003, B:6:0x000c, B:7:0x0017, B:9:0x001c, B:13:0x0038, B:15:0x0042, B:17:0x0049, B:19:0x004d, B:22:0x0052, B:23:0x0059, B:25:0x005d, B:31:0x008b, B:33:0x0093, B:35:0x009d, B:38:0x00ab, B:41:0x00d7, B:43:0x00dd, B:45:0x00e5, B:50:0x00c4, B:59:0x00e7, B:61:0x00f6, B:63:0x00fe, B:69:0x0117, B:71:0x011f, B:73:0x0123, B:75:0x012f, B:76:0x0134, B:79:0x0138, B:82:0x0157, B:84:0x0165, B:86:0x0169, B:89:0x0171, B:91:0x0177, B:93:0x017f, B:95:0x0185, B:97:0x0191, B:100:0x0195, B:111:0x01d2, B:113:0x01de, B:115:0x01e8, B:118:0x01fc, B:121:0x0213, B:123:0x0217, B:128:0x0230, B:130:0x0236, B:134:0x023e, B:136:0x0242, B:138:0x024e, B:140:0x025f, B:142:0x0266, B:145:0x0273, B:147:0x027b, B:151:0x028e, B:152:0x0297, B:154:0x029b, B:156:0x02a5, B:158:0x02b0, B:159:0x02d3, B:163:0x02ff, B:165:0x030f, B:167:0x0317, B:169:0x0327, B:170:0x0338, B:171:0x036a, B:173:0x038f, B:175:0x03a9, B:176:0x03d3, B:181:0x033e, B:183:0x034e, B:184:0x0359, B:185:0x02b5, B:186:0x02bd, B:187:0x02c0, B:189:0x02c4, B:190:0x02cd, B:191:0x0295, B:195:0x0204, B:199:0x01ec, B:201:0x01f0, B:207:0x01cb, B:210:0x0150, B:47:0x00b3, B:105:0x01ac, B:107:0x01b2, B:109:0x01b6, B:81:0x013f), top: B:3:0x0003, inners: #0, #2, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:173:0x038f A[Catch: all -> 0x03e3, TryCatch #1 {, blocks: (B:4:0x0003, B:6:0x000c, B:7:0x0017, B:9:0x001c, B:13:0x0038, B:15:0x0042, B:17:0x0049, B:19:0x004d, B:22:0x0052, B:23:0x0059, B:25:0x005d, B:31:0x008b, B:33:0x0093, B:35:0x009d, B:38:0x00ab, B:41:0x00d7, B:43:0x00dd, B:45:0x00e5, B:50:0x00c4, B:59:0x00e7, B:61:0x00f6, B:63:0x00fe, B:69:0x0117, B:71:0x011f, B:73:0x0123, B:75:0x012f, B:76:0x0134, B:79:0x0138, B:82:0x0157, B:84:0x0165, B:86:0x0169, B:89:0x0171, B:91:0x0177, B:93:0x017f, B:95:0x0185, B:97:0x0191, B:100:0x0195, B:111:0x01d2, B:113:0x01de, B:115:0x01e8, B:118:0x01fc, B:121:0x0213, B:123:0x0217, B:128:0x0230, B:130:0x0236, B:134:0x023e, B:136:0x0242, B:138:0x024e, B:140:0x025f, B:142:0x0266, B:145:0x0273, B:147:0x027b, B:151:0x028e, B:152:0x0297, B:154:0x029b, B:156:0x02a5, B:158:0x02b0, B:159:0x02d3, B:163:0x02ff, B:165:0x030f, B:167:0x0317, B:169:0x0327, B:170:0x0338, B:171:0x036a, B:173:0x038f, B:175:0x03a9, B:176:0x03d3, B:181:0x033e, B:183:0x034e, B:184:0x0359, B:185:0x02b5, B:186:0x02bd, B:187:0x02c0, B:189:0x02c4, B:190:0x02cd, B:191:0x0295, B:195:0x0204, B:199:0x01ec, B:201:0x01f0, B:207:0x01cb, B:210:0x0150, B:47:0x00b3, B:105:0x01ac, B:107:0x01b2, B:109:0x01b6, B:81:0x013f), top: B:3:0x0003, inners: #0, #2, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:189:0x02c4 A[Catch: all -> 0x03e3, TryCatch #1 {, blocks: (B:4:0x0003, B:6:0x000c, B:7:0x0017, B:9:0x001c, B:13:0x0038, B:15:0x0042, B:17:0x0049, B:19:0x004d, B:22:0x0052, B:23:0x0059, B:25:0x005d, B:31:0x008b, B:33:0x0093, B:35:0x009d, B:38:0x00ab, B:41:0x00d7, B:43:0x00dd, B:45:0x00e5, B:50:0x00c4, B:59:0x00e7, B:61:0x00f6, B:63:0x00fe, B:69:0x0117, B:71:0x011f, B:73:0x0123, B:75:0x012f, B:76:0x0134, B:79:0x0138, B:82:0x0157, B:84:0x0165, B:86:0x0169, B:89:0x0171, B:91:0x0177, B:93:0x017f, B:95:0x0185, B:97:0x0191, B:100:0x0195, B:111:0x01d2, B:113:0x01de, B:115:0x01e8, B:118:0x01fc, B:121:0x0213, B:123:0x0217, B:128:0x0230, B:130:0x0236, B:134:0x023e, B:136:0x0242, B:138:0x024e, B:140:0x025f, B:142:0x0266, B:145:0x0273, B:147:0x027b, B:151:0x028e, B:152:0x0297, B:154:0x029b, B:156:0x02a5, B:158:0x02b0, B:159:0x02d3, B:163:0x02ff, B:165:0x030f, B:167:0x0317, B:169:0x0327, B:170:0x0338, B:171:0x036a, B:173:0x038f, B:175:0x03a9, B:176:0x03d3, B:181:0x033e, B:183:0x034e, B:184:0x0359, B:185:0x02b5, B:186:0x02bd, B:187:0x02c0, B:189:0x02c4, B:190:0x02cd, B:191:0x0295, B:195:0x0204, B:199:0x01ec, B:201:0x01f0, B:207:0x01cb, B:210:0x0150, B:47:0x00b3, B:105:0x01ac, B:107:0x01b2, B:109:0x01b6, B:81:0x013f), top: B:3:0x0003, inners: #0, #2, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:190:0x02cd A[Catch: all -> 0x03e3, TryCatch #1 {, blocks: (B:4:0x0003, B:6:0x000c, B:7:0x0017, B:9:0x001c, B:13:0x0038, B:15:0x0042, B:17:0x0049, B:19:0x004d, B:22:0x0052, B:23:0x0059, B:25:0x005d, B:31:0x008b, B:33:0x0093, B:35:0x009d, B:38:0x00ab, B:41:0x00d7, B:43:0x00dd, B:45:0x00e5, B:50:0x00c4, B:59:0x00e7, B:61:0x00f6, B:63:0x00fe, B:69:0x0117, B:71:0x011f, B:73:0x0123, B:75:0x012f, B:76:0x0134, B:79:0x0138, B:82:0x0157, B:84:0x0165, B:86:0x0169, B:89:0x0171, B:91:0x0177, B:93:0x017f, B:95:0x0185, B:97:0x0191, B:100:0x0195, B:111:0x01d2, B:113:0x01de, B:115:0x01e8, B:118:0x01fc, B:121:0x0213, B:123:0x0217, B:128:0x0230, B:130:0x0236, B:134:0x023e, B:136:0x0242, B:138:0x024e, B:140:0x025f, B:142:0x0266, B:145:0x0273, B:147:0x027b, B:151:0x028e, B:152:0x0297, B:154:0x029b, B:156:0x02a5, B:158:0x02b0, B:159:0x02d3, B:163:0x02ff, B:165:0x030f, B:167:0x0317, B:169:0x0327, B:170:0x0338, B:171:0x036a, B:173:0x038f, B:175:0x03a9, B:176:0x03d3, B:181:0x033e, B:183:0x034e, B:184:0x0359, B:185:0x02b5, B:186:0x02bd, B:187:0x02c0, B:189:0x02c4, B:190:0x02cd, B:191:0x0295, B:195:0x0204, B:199:0x01ec, B:201:0x01f0, B:207:0x01cb, B:210:0x0150, B:47:0x00b3, B:105:0x01ac, B:107:0x01b2, B:109:0x01b6, B:81:0x013f), top: B:3:0x0003, inners: #0, #2, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0295 A[Catch: all -> 0x03e3, TryCatch #1 {, blocks: (B:4:0x0003, B:6:0x000c, B:7:0x0017, B:9:0x001c, B:13:0x0038, B:15:0x0042, B:17:0x0049, B:19:0x004d, B:22:0x0052, B:23:0x0059, B:25:0x005d, B:31:0x008b, B:33:0x0093, B:35:0x009d, B:38:0x00ab, B:41:0x00d7, B:43:0x00dd, B:45:0x00e5, B:50:0x00c4, B:59:0x00e7, B:61:0x00f6, B:63:0x00fe, B:69:0x0117, B:71:0x011f, B:73:0x0123, B:75:0x012f, B:76:0x0134, B:79:0x0138, B:82:0x0157, B:84:0x0165, B:86:0x0169, B:89:0x0171, B:91:0x0177, B:93:0x017f, B:95:0x0185, B:97:0x0191, B:100:0x0195, B:111:0x01d2, B:113:0x01de, B:115:0x01e8, B:118:0x01fc, B:121:0x0213, B:123:0x0217, B:128:0x0230, B:130:0x0236, B:134:0x023e, B:136:0x0242, B:138:0x024e, B:140:0x025f, B:142:0x0266, B:145:0x0273, B:147:0x027b, B:151:0x028e, B:152:0x0297, B:154:0x029b, B:156:0x02a5, B:158:0x02b0, B:159:0x02d3, B:163:0x02ff, B:165:0x030f, B:167:0x0317, B:169:0x0327, B:170:0x0338, B:171:0x036a, B:173:0x038f, B:175:0x03a9, B:176:0x03d3, B:181:0x033e, B:183:0x034e, B:184:0x0359, B:185:0x02b5, B:186:0x02bd, B:187:0x02c0, B:189:0x02c4, B:190:0x02cd, B:191:0x0295, B:195:0x0204, B:199:0x01ec, B:201:0x01f0, B:207:0x01cb, B:210:0x0150, B:47:0x00b3, B:105:0x01ac, B:107:0x01b2, B:109:0x01b6, B:81:0x013f), top: B:3:0x0003, inners: #0, #2, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0165 A[Catch: all -> 0x03e3, TryCatch #1 {, blocks: (B:4:0x0003, B:6:0x000c, B:7:0x0017, B:9:0x001c, B:13:0x0038, B:15:0x0042, B:17:0x0049, B:19:0x004d, B:22:0x0052, B:23:0x0059, B:25:0x005d, B:31:0x008b, B:33:0x0093, B:35:0x009d, B:38:0x00ab, B:41:0x00d7, B:43:0x00dd, B:45:0x00e5, B:50:0x00c4, B:59:0x00e7, B:61:0x00f6, B:63:0x00fe, B:69:0x0117, B:71:0x011f, B:73:0x0123, B:75:0x012f, B:76:0x0134, B:79:0x0138, B:82:0x0157, B:84:0x0165, B:86:0x0169, B:89:0x0171, B:91:0x0177, B:93:0x017f, B:95:0x0185, B:97:0x0191, B:100:0x0195, B:111:0x01d2, B:113:0x01de, B:115:0x01e8, B:118:0x01fc, B:121:0x0213, B:123:0x0217, B:128:0x0230, B:130:0x0236, B:134:0x023e, B:136:0x0242, B:138:0x024e, B:140:0x025f, B:142:0x0266, B:145:0x0273, B:147:0x027b, B:151:0x028e, B:152:0x0297, B:154:0x029b, B:156:0x02a5, B:158:0x02b0, B:159:0x02d3, B:163:0x02ff, B:165:0x030f, B:167:0x0317, B:169:0x0327, B:170:0x0338, B:171:0x036a, B:173:0x038f, B:175:0x03a9, B:176:0x03d3, B:181:0x033e, B:183:0x034e, B:184:0x0359, B:185:0x02b5, B:186:0x02bd, B:187:0x02c0, B:189:0x02c4, B:190:0x02cd, B:191:0x0295, B:195:0x0204, B:199:0x01ec, B:201:0x01f0, B:207:0x01cb, B:210:0x0150, B:47:0x00b3, B:105:0x01ac, B:107:0x01b2, B:109:0x01b6, B:81:0x013f), top: B:3:0x0003, inners: #0, #2, #5 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final synchronized AMapLocationServer m2703a() throws Throwable {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        String str;
        String str2;
        if (this.f3762al.length() > 0) {
            this.f3762al.delete(0, this.f3762al.length());
        }
        if (this.f3726A == null) {
            this.f3762al.append("context is null");
            AMapLocationServer aMapLocationServer = new AMapLocationServer("");
            aMapLocationServer.setErrorCode(1);
            aMapLocationServer.setLocationDetail(this.f3762al.toString());
            return aMapLocationServer;
        }
        this.f3744U++;
        AMapLocationServer aMapLocationServer2 = null;
        if (this.f3744U == 1) {
            m2700x();
            if (this.f3728C != null && this.f3726A != null && this.f3753ac) {
                this.f3728C.m2787a(this.f3753ac);
            }
            if (this.f3726A != null) {
                if (m2697u()) {
                    try {
                        C3871cs.m2917a(this.f3751aa, "getColUpHist", new Object[0]);
                    } catch (Throwable unused) {
                    }
                }
                try {
                    if (m2658D()) {
                        C3871cs.m2917a(this.f3752ab, "getOffDlHist", new Object[0]);
                    } else if (this.f3752ab != null) {
                        C3871cs.m2917a(this.f3752ab, "stopOff", new Object[0]);
                        this.f3784l = false;
                    }
                } catch (Throwable unused2) {
                }
                if (TextUtils.isEmpty(this.f3745V) || this.f3745V.equals("00:00:00:00:00:00")) {
                    SharedPreferences sharedPreferences = this.f3726A.getSharedPreferences("pref", 0);
                    if (this.f3726A != null && sharedPreferences != null) {
                        if (sharedPreferences.contains("smac")) {
                            try {
                                str2 = new String(C3889o.m3182b(sharedPreferences.getString("smac", null)), "UTF-8");
                            } catch (Throwable th) {
                                C3880f.m3097a(th, "APS", "getSmac");
                                sharedPreferences.edit().remove("smac").commit();
                            }
                            if (!TextUtils.isEmpty(str2) && !str2.equals("00:00:00:00:00:00")) {
                                this.f3745V = str2;
                            }
                        }
                        str2 = null;
                        if (!TextUtils.isEmpty(str2)) {
                            this.f3745V = str2;
                        }
                    }
                }
            }
        }
        if (C3876cx.m2985b() - this.f3739N < 800) {
            if ((C3876cx.m2977a(this.f3738M) ? C3876cx.m2969a() - this.f3738M.getTime() : 0L) <= 10000) {
                z = true;
                if (!z && C3876cx.m2977a(this.f3738M)) {
                    if (this.f3765ao && C3869cq.m2877b(this.f3738M.getTime())) {
                        this.f3738M.setLocationType(2);
                    }
                    return this.f3738M;
                }
                this.f3729D.m2772a(this.f3742Q, false);
                m2690n();
                this.f3740O = C3876cx.m2985b();
                m2691o();
                m2693q();
                this.f3770at = m2689m();
                if (!TextUtils.isEmpty(this.f3770at)) {
                    if (!this.f3775c) {
                        m2712a(false, this.f3726A);
                    }
                    for (int i = 4; i > 0 && !this.f3774b.f3809a; i--) {
                        SystemClock.sleep(500L);
                    }
                    if (this.f3774b.f3809a) {
                        this.f3738M = this.f3774b.m2725e();
                        if (this.f3738M != null) {
                            return this.f3738M;
                        }
                    }
                    AMapLocationServer aMapLocationServer3 = new AMapLocationServer("");
                    aMapLocationServer3.setErrorCode(this.f3785m);
                    aMapLocationServer3.setLocationDetail(this.f3762al.toString());
                    return aMapLocationServer3;
                }
                try {
                    if (m2658D() && !this.f3784l) {
                        C3871cs.m2917a(this.f3752ab, "startOff", this.f3737L, this.f3770at);
                        this.f3784l = true;
                    }
                } catch (Throwable th2) {
                    C3880f.m3097a(th2, "APS", "getLocation:isOffLineLoc");
                }
                this.f3794z = m2663a(this.f3794z, false);
                C3854cb m2774c = !this.f3742Q ? this.f3729D.m2774c() : null;
                if ((m2774c == null && this.f3760aj == null) || (this.f3760aj != null && this.f3760aj.m2751a(m2774c))) {
                    z2 = false;
                    if (this.f3739N != 0 && C3876cx.m2985b() - this.f3739N <= 20000) {
                        z3 = false;
                        if (this.f3738M != null) {
                            int size = this.f3730E.size();
                            if (this.f3738M.getAccuracy() > 299.0f && size > 5) {
                                z4 = true;
                                if (this.f3738M != null || this.f3759ai == null || z4 || z2 || !this.f3765ao || !C3869cq.m2877b(this.f3738M.getTime())) {
                                    z5 = false;
                                } else {
                                    C3858cf.m2807a();
                                    boolean m2809a = C3858cf.m2809a(this.f3759ai, this.f3794z.toString());
                                    if (m2809a) {
                                        z5 = m2809a;
                                    } else {
                                        z5 = m2809a;
                                        if (this.f3758ah != 0 && C3876cx.m2985b() - this.f3758ah < SolicitService.CAMERA_OPEN_TIME_OUT) {
                                        }
                                    }
                                    if (C3876cx.m2977a(this.f3738M)) {
                                        this.f3738M.m552d("mem");
                                        this.f3738M.setLocationType(2);
                                        return this.f3738M;
                                    }
                                }
                                if (z5) {
                                    this.f3758ah = 0L;
                                } else {
                                    this.f3758ah = C3876cx.m2985b();
                                }
                                if (this.f3756af != null || this.f3770at.equals(this.f3756af)) {
                                    if (this.f3756af == null) {
                                        this.f3757ag = C3876cx.m2969a();
                                        str = this.f3770at;
                                        this.f3756af = str;
                                    } else {
                                        this.f3757ag = C3876cx.m2969a();
                                    }
                                } else if (C3876cx.m2969a() - this.f3757ag < SolicitService.CAMERA_OPEN_TIME_OUT) {
                                    this.f3770at = this.f3756af;
                                } else {
                                    this.f3757ag = C3876cx.m2969a();
                                    str = this.f3770at;
                                    this.f3756af = str;
                                }
                                this.f3771au = this.f3770at + "&" + this.f3764an + "&" + this.f3763am;
                                if (this.f3765ao && !z4 && !z3) {
                                    aMapLocationServer2 = C3858cf.m2807a().m2813a(this.f3771au, this.f3794z, this.f3765ao);
                                }
                                if ((!z3 && !C3876cx.m2977a(aMapLocationServer2)) || z4) {
                                    this.f3738M = m2661a(false, true);
                                    if (C3876cx.m2977a(this.f3738M)) {
                                        this.f3738M.m552d("new");
                                        this.f3759ai = this.f3794z.toString();
                                        this.f3760aj = m2774c;
                                        m2685k();
                                    }
                                    C3858cf.m2807a().m2816a(this.f3771au, this.f3794z, this.f3738M, this.f3726A, true);
                                    C3861ci.m2832a().m2834a(this.f3726A, this.f3770at, this.f3738M);
                                    if (!C3876cx.m2977a(this.f3738M)) {
                                    }
                                    this.f3794z.delete(0, this.f3794z.length());
                                    return this.f3738M;
                                }
                                if (z3) {
                                    this.f3738M = m2661a(false, true);
                                    if (C3876cx.m2977a(this.f3738M)) {
                                        this.f3759ai = this.f3794z.toString();
                                        this.f3760aj = m2774c;
                                    }
                                    C3858cf.m2807a().m2816a(this.f3771au, this.f3794z, this.f3738M, this.f3726A, true);
                                    C3861ci.m2832a().m2834a(this.f3726A, this.f3770at, this.f3738M);
                                    if (!C3876cx.m2977a(this.f3738M)) {
                                        String str3 = this.f3770at;
                                        String sb = this.f3794z.toString();
                                        JSONObject jSONObject = this.f3737L;
                                        Context context = this.f3726A;
                                        AMapLocationServer m2660a = m2660a(str3, sb, jSONObject, m2689m());
                                        if (C3876cx.m2977a(m2660a)) {
                                            this.f3759ai = this.f3794z.toString();
                                            AMapLocationServer aMapLocationServer4 = this.f3738M;
                                            this.f3738M = m2660a;
                                            this.f3738M.setLocationType(8);
                                            this.f3738M.setLocationDetail("离线定位，在线定位失败原因:" + aMapLocationServer4.getErrorInfo());
                                        }
                                    }
                                    this.f3794z.delete(0, this.f3794z.length());
                                    return this.f3738M;
                                }
                                this.f3758ah = 0L;
                                aMapLocationServer2.setLocationType(4);
                                this.f3738M = aMapLocationServer2;
                                m2670b(aMapLocationServer2.mo490a(1));
                                m2685k();
                                C3858cf.m2807a().m2816a(this.f3771au, this.f3794z, this.f3738M, this.f3726A, true);
                                C3861ci.m2832a().m2834a(this.f3726A, this.f3770at, this.f3738M);
                                if (!C3876cx.m2977a(this.f3738M)) {
                                }
                                this.f3794z.delete(0, this.f3794z.length());
                                return this.f3738M;
                            }
                        }
                        z4 = false;
                        if (this.f3738M != null) {
                        }
                        z5 = false;
                        if (z5) {
                        }
                        if (this.f3756af != null) {
                        }
                        if (this.f3756af == null) {
                        }
                    }
                    z3 = true;
                    if (this.f3738M != null) {
                    }
                    z4 = false;
                    if (this.f3738M != null) {
                    }
                    z5 = false;
                    if (z5) {
                    }
                    if (this.f3756af != null) {
                    }
                    if (this.f3756af == null) {
                    }
                }
                z2 = true;
                if (this.f3739N != 0) {
                    z3 = false;
                    if (this.f3738M != null) {
                    }
                    z4 = false;
                    if (this.f3738M != null) {
                    }
                    z5 = false;
                    if (z5) {
                    }
                    if (this.f3756af != null) {
                    }
                    if (this.f3756af == null) {
                    }
                }
                z3 = true;
                if (this.f3738M != null) {
                }
                z4 = false;
                if (this.f3738M != null) {
                }
                z5 = false;
                if (z5) {
                }
                if (this.f3756af != null) {
                }
                if (this.f3756af == null) {
                }
            }
        }
        z = false;
        if (!z) {
        }
        this.f3729D.m2772a(this.f3742Q, false);
        m2690n();
        this.f3740O = C3876cx.m2985b();
        m2691o();
        m2693q();
        this.f3770at = m2689m();
        if (!TextUtils.isEmpty(this.f3770at)) {
        }
    }

    /* renamed from: a */
    public final AMapLocationServer m2704a(double d, double d2) {
        try {
            String m2854a = this.f3761ak.m2854a(("output=json&radius=1000&extensions=all&location=" + d2 + "," + d).getBytes("UTF-8"), this.f3726A, "http://restapi.amap.com/v3/geocode/regeo");
            new C3867co();
            if (!m2854a.contains("\"status\":\"1\"")) {
                return null;
            }
            AMapLocationServer m2858a = C3867co.m2858a(m2854a);
            m2858a.setLatitude(d);
            m2858a.setLongitude(d2);
            return m2858a;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: a */
    public final AMapLocationServer m2705a(AMapLocationServer aMapLocationServer, String... strArr) {
        C3849bx.m2726a().m2730a(this.f3765ao);
        if (strArr == null || strArr.length == 0) {
            return C3849bx.m2726a().m2729a(aMapLocationServer);
        }
        if (strArr[0].equals("shake")) {
            return C3849bx.m2726a().m2729a(aMapLocationServer);
        }
        if (!strArr[0].equals("fusion")) {
            return aMapLocationServer;
        }
        C3849bx.m2726a();
        return C3849bx.m2727b(aMapLocationServer);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0021 A[Catch: all -> 0x00ae, TRY_LEAVE, TryCatch #2 {, blocks: (B:3:0x0001, B:9:0x0019, B:11:0x0021, B:19:0x003d, B:21:0x0044, B:22:0x0060, B:24:0x007f, B:33:0x0011, B:6:0x0005, B:8:0x0009), top: B:2:0x0001, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0037  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final synchronized AMapLocationServer m2706a(boolean z) {
        C3854cb c3854cb;
        AMapLocationServer aMapLocationServer;
        m2679h();
        AMapLocationServer aMapLocationServer2 = null;
        try {
        } catch (Throwable th) {
            C3880f.m3097a(th, "APS", "doFirstNetLocate");
        }
        if (!this.f3742Q) {
            c3854cb = this.f3729D.m2774c();
            if (TextUtils.isEmpty(this.f3770at)) {
                try {
                    aMapLocationServer2 = m2661a(false, z);
                } catch (Throwable unused) {
                }
                aMapLocationServer = aMapLocationServer2;
                if (C3876cx.m2977a(aMapLocationServer)) {
                    aMapLocationServer.m552d("new");
                    this.f3759ai = this.f3794z.toString();
                    this.f3760aj = c3854cb;
                    this.f3738M = aMapLocationServer;
                    m2670b(aMapLocationServer.mo490a(1));
                    m2685k();
                } else {
                    String sb = this.f3794z.toString();
                    m2673d(this.f3726A);
                    String str = this.f3770at;
                    JSONObject jSONObject = this.f3737L;
                    Context context = this.f3726A;
                    AMapLocationServer m2660a = m2660a(str, sb, jSONObject, m2689m());
                    if (!C3876cx.m2977a(m2660a)) {
                        return aMapLocationServer;
                    }
                    this.f3759ai = sb;
                    m2660a.m552d("file");
                    m2660a.setLocationType(8);
                    m2660a.setLocationDetail("离线定位结果，在线定位失败原因:" + aMapLocationServer.getErrorInfo());
                    this.f3738M = m2660a;
                    m2670b(m2660a.mo490a(1));
                    aMapLocationServer = m2660a;
                }
            } else {
                aMapLocationServer = new AMapLocationServer("");
                aMapLocationServer.setErrorCode(this.f3785m);
                aMapLocationServer.setLocationDetail(this.f3762al.toString());
            }
            return aMapLocationServer;
        }
        c3854cb = null;
        if (TextUtils.isEmpty(this.f3770at)) {
        }
        return aMapLocationServer;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x00aa A[Catch: all -> 0x00d4, TryCatch #0 {all -> 0x00d4, blocks: (B:6:0x0009, B:8:0x0016, B:9:0x0023, B:10:0x0096, B:12:0x00aa, B:14:0x00b7, B:16:0x00c5, B:21:0x002b, B:23:0x0033, B:24:0x0041), top: B:5:0x0009 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final a m2707a(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        long m2985b;
        int i2;
        int i3;
        String str;
        StringBuilder sb;
        String address;
        try {
            m2985b = C3876cx.m2985b();
        } catch (Throwable th) {
            th = th;
        }
        try {
            String m2662a = m2662a(bArr);
            if (m2662a.length() == 16) {
                sb = new StringBuilder();
                sb.append(m2662a);
                sb.append("0000000000000000");
            } else {
                if (m2662a.length() != 12) {
                    byte[] bArr2 = new byte[16];
                    System.arraycopy(bArr, 9, bArr2, 0, 16);
                    String upperCase = (C3876cx.m2989b(bArr2).substring(0, 32)).toUpperCase(Locale.getDefault());
                    int i4 = ((bArr[25] & 255) * 256) + (bArr[26] & 255);
                    int i5 = ((bArr[27] & 255) * 256) + (bArr[28] & 255);
                    if (i5 != 11669 && i4 != 2080 && i4 != 1796 && bluetoothDevice != null) {
                        i2 = i4;
                        i3 = i5;
                        str = upperCase;
                        byte b2 = bArr[29];
                        address = bluetoothDevice.getAddress();
                        if (BluetoothAdapter.checkBluetoothAddress(address.toUpperCase(Locale.CHINA))) {
                            byte[] bArr3 = new byte[6];
                            int i6 = 0;
                            for (String str2 : address.split(":")) {
                                bArr3[i6] = (byte) Integer.parseInt(str2, 16);
                                i6++;
                            }
                            return new a(str, bluetoothDevice.getName(), bArr3, address, i2, i3, b2, i, m2985b);
                        }
                    }
                    return null;
                }
                sb = new StringBuilder();
                sb.append(m2662a);
                sb.append("00000000000000000000");
            }
            str = sb.toString();
            i2 = 0;
            i3 = 0;
            byte b22 = bArr[29];
            address = bluetoothDevice.getAddress();
            if (BluetoothAdapter.checkBluetoothAddress(address.toUpperCase(Locale.CHINA))) {
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            C3880f.m3097a(th, "APS", "createFromScanData");
            return null;
        }
    }

    /* renamed from: a */
    public final synchronized void m2708a(Context context) {
        m2672c(context);
        m2679h();
        C3880f.f4185g = true;
        m2673d(this.f3726A);
        this.f3741P = C3876cx.m2985b();
        if (!this.f3766ap) {
            m2688l();
            this.f3766ap = true;
        }
        this.f3729D.m2771a();
        this.f3729D.m2777f();
        C3858cf.m2807a().m2815a(this.f3726A, (String) null);
        C3861ci.m2832a().m2833a(this.f3726A);
        Context context2 = this.f3726A;
        try {
            if (context2.checkCallingOrSelfPermission("android.permission.WRITE_SECURE_SETTINGS") == 0) {
                this.f3753ac = true;
            }
            if (context2.checkCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN") == 0 && context2.checkCallingOrSelfPermission("android.permission.BLUETOOTH") == 0) {
                this.f3754ad = true;
            }
        } catch (Throwable unused) {
        }
        try {
            if (C3876cx.m2996c() >= 18 && this.f3754ad) {
                this.f3780h = BluetoothAdapter.getDefaultAdapter();
            }
        } catch (Throwable unused2) {
        }
        this.f3777e = true;
    }

    /* renamed from: a */
    public final synchronized void m2709a(Context context, JSONObject jSONObject) {
        try {
        } catch (Throwable th) {
            C3880f.m3097a(th, "APS", "initFirstLocateParam");
        }
        if (this.f3772av) {
            return;
        }
        m2711a(jSONObject);
        m2672c(context);
        if (this.f3770at != null) {
            this.f3770at = null;
            this.f3771au = null;
            if (this.f3794z != null) {
                this.f3794z.delete(0, this.f3794z.length());
            }
        }
        if (!this.f3742Q) {
            this.f3729D.m2772a(this.f3742Q, true);
        }
        this.f3767aq = this.f3737L.optBoolean("isOnceLocationLatest", false);
        if (this.f3767aq) {
            if (!this.f3766ap) {
                m2688l();
                this.f3766ap = true;
            }
            m2690n();
        }
        m2691o();
        if (this.f3730E != null && this.f3730E.isEmpty()) {
            f3721T = C3876cx.m2985b();
            List<ScanResult> m2786a = this.f3728C.m2786a();
            if (m2786a != null) {
                this.f3730E.addAll(m2786a);
                synchronized (this.f3776d) {
                    if (this.f3731F != null && this.f3731F.isEmpty()) {
                        this.f3731F.addAll(m2786a);
                    }
                }
            }
        }
        m2693q();
        this.f3770at = m2689m();
        if (!TextUtils.isEmpty(this.f3770at)) {
            this.f3771au = this.f3770at + "&" + this.f3764an + "&" + this.f3763am;
            this.f3794z = m2663a(this.f3794z, true);
        }
        this.f3772av = true;
    }

    /* renamed from: a */
    public final synchronized void m2710a(AMapLocationServer aMapLocationServer) {
        if (C3876cx.m2977a(aMapLocationServer)) {
            C3858cf.m2807a().m2816a(this.f3771au, this.f3794z, aMapLocationServer, this.f3726A, true);
        }
    }

    /* renamed from: a */
    public final void m2711a(JSONObject jSONObject) {
        boolean z;
        boolean z2;
        this.f3737L = jSONObject;
        if (C3876cx.m2979a(this.f3737L, "collwifiscan")) {
            try {
                String string = this.f3737L.getString("collwifiscan");
                if (TextUtils.isEmpty(string)) {
                    C3880f.f4184f = 20;
                } else {
                    C3880f.f4184f = Integer.parseInt(string) / 1000;
                }
            } catch (Throwable th) {
                C3880f.m3097a(th, "APS", "setExtra");
            }
        }
        try {
            z2 = C3876cx.m2979a(this.f3737L, "reversegeo") ? this.f3737L.getBoolean("reversegeo") : true;
            try {
                z = C3876cx.m2979a(this.f3737L, "isOffset") ? this.f3737L.getBoolean("isOffset") : true;
                try {
                    r8 = C3876cx.m2979a(this.f3737L, "isLocationCacheEnable") ? this.f3737L.getBoolean("isLocationCacheEnable") : true;
                    if (C3876cx.m2979a(this.f3737L, "isWifiPassiveScan")) {
                        this.f3793u = this.f3737L.getBoolean("isWifiPassiveScan");
                    }
                    if (C3876cx.m2979a(this.f3737L, "isMock")) {
                        this.f3783k = this.f3737L.getBoolean("isMock");
                    }
                    if (z != this.f3764an || z2 != this.f3763am || r8 != this.f3765ao) {
                        try {
                            this.f3759ai = null;
                            this.f3738M = null;
                            m2670b((JSONObject) null);
                            this.f3758ah = 0L;
                            this.f3739N = 0L;
                            C3849bx.m2726a().m2731b();
                        } catch (Throwable th2) {
                            C3880f.m3097a(th2, "APS", "cleanCache");
                        }
                    }
                } catch (Throwable unused) {
                }
            } catch (Throwable unused2) {
                z = true;
            }
        } catch (Throwable unused3) {
            z = true;
            z2 = true;
        }
        this.f3764an = z;
        this.f3763am = z2;
        this.f3765ao = r8;
        this.f3742Q = C3876cx.m2974a(this.f3726A);
    }

    /* renamed from: a */
    public final synchronized void m2712a(boolean z, Context context) {
        if (!this.f3775c) {
            if (this.f3774b == null) {
                this.f3774b = new C3847bv(context.getApplicationContext());
                this.f3774b.m2722b();
            }
            try {
                if (this.f3774b != null) {
                    if (z) {
                        this.f3774b.m2724d();
                    } else {
                        this.f3774b.m2723c();
                    }
                }
                this.f3775c = true;
            } catch (Throwable th) {
                C3880f.m3097a(th, "APS", "bindService");
                this.f3775c = true;
            }
        }
    }

    /* renamed from: b */
    public final void m2713b() {
        try {
            m2709a(this.f3726A, this.f3737L);
            m2710a(m2661a(true, true));
        } catch (Throwable th) {
            C3880f.m3097a(th, "APS", "doFusionLocation");
        }
    }

    /* renamed from: b */
    public final synchronized void m2714b(Context context) {
        try {
            if (f3722v == -1 || m2716d()) {
                f3722v = 1;
                C3869cq.m2872a(context);
                f3725y = C3869cq.m2904u();
                C3876cx.m2972a(context, f3725y);
                f3724x = C3876cx.m2985b();
                f3723w = C3876cx.m2985b();
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "APS", "initAuth");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0062 A[Catch: all -> 0x00ae, TryCatch #2 {, blocks: (B:4:0x0002, B:6:0x0019, B:7:0x0022, B:10:0x0037, B:18:0x0050, B:19:0x005c, B:21:0x0062, B:22:0x0067, B:24:0x0085, B:25:0x008a, B:27:0x0092, B:28:0x0099, B:37:0x00ab, B:38:0x00ad, B:41:0x0030, B:12:0x0041, B:14:0x0045, B:16:0x0049, B:34:0x0054, B:9:0x0025), top: B:3:0x0002, inners: #0, #1, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0085 A[Catch: all -> 0x00ae, TryCatch #2 {, blocks: (B:4:0x0002, B:6:0x0019, B:7:0x0022, B:10:0x0037, B:18:0x0050, B:19:0x005c, B:21:0x0062, B:22:0x0067, B:24:0x0085, B:25:0x008a, B:27:0x0092, B:28:0x0099, B:37:0x00ab, B:38:0x00ad, B:41:0x0030, B:12:0x0041, B:14:0x0045, B:16:0x0049, B:34:0x0054, B:9:0x0025), top: B:3:0x0002, inners: #0, #1, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0092 A[Catch: all -> 0x00ae, TryCatch #2 {, blocks: (B:4:0x0002, B:6:0x0019, B:7:0x0022, B:10:0x0037, B:18:0x0050, B:19:0x005c, B:21:0x0062, B:22:0x0067, B:24:0x0085, B:25:0x008a, B:27:0x0092, B:28:0x0099, B:37:0x00ab, B:38:0x00ad, B:41:0x0030, B:12:0x0041, B:14:0x0045, B:16:0x0049, B:34:0x0054, B:9:0x0025), top: B:3:0x0002, inners: #0, #1, #3 }] */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final synchronized void m2715c() {
        AMapLocationServer.f1212d = null;
        this.f3772av = false;
        this.f3777e = false;
        C3880f.f4185g = false;
        m2701y();
        this.f3751aa = null;
        this.f3759ai = null;
        m2685k();
        if (this.f3774b != null) {
            this.f3774b.m2721a();
            this.f3774b = null;
            this.f3775c = false;
        }
        m2655A();
        try {
            C3860ch.m2824a().m2827a(this.f3726A);
        } catch (Throwable th) {
            C3880f.m3097a(th, "APS", "destroy part");
        }
        C3849bx.m2726a().m2731b();
        C3876cx.m3014g();
        try {
            if (this.f3726A != null && this.f3735J != null) {
                this.f3726A.unregisterReceiver(this.f3735J);
            }
        } finally {
            try {
                this.f3766ap = false;
                if (this.f3729D != null) {
                }
                C3861ci.m2832a().m2836b();
                m2667a("stopOff");
                m2667a("destroy");
                this.f3784l = false;
                this.f3757ag = 0L;
                m2694r();
                if (this.f3730E != null) {
                }
                this.f3738M = null;
                this.f3726A = null;
                if (this.f3790r != null) {
                }
                this.f3751aa = null;
                this.f3752ab = null;
                this.f3794z = null;
                this.f3787o = null;
                this.f3788p = null;
                m2684j();
                this.f3780h = null;
            } finally {
            }
        }
        this.f3766ap = false;
        if (this.f3729D != null) {
            this.f3729D.m2780i();
        }
        C3861ci.m2832a().m2836b();
        m2667a("stopOff");
        m2667a("destroy");
        this.f3784l = false;
        this.f3757ag = 0L;
        m2694r();
        if (this.f3730E != null) {
            this.f3730E.clear();
        }
        this.f3738M = null;
        this.f3726A = null;
        if (this.f3790r != null) {
            this.f3790r.clear();
            this.f3790r = null;
        }
        this.f3751aa = null;
        this.f3752ab = null;
        this.f3794z = null;
        this.f3787o = null;
        this.f3788p = null;
        m2684j();
        this.f3780h = null;
    }

    /* renamed from: d */
    public final boolean m2716d() {
        try {
        } catch (Throwable th) {
            C3880f.m3097a(th, "APS", "isConfigNeedUpdate");
        }
        if (this.f3726A == null) {
            return false;
        }
        if (C3876cx.m2985b() - f3723w >= C3869cq.m2866A()) {
            f3723w = C3876cx.m2985b();
            if (C3876cx.m3006e(this.f3726A) > f3725y) {
                return true;
            }
        }
        return C3876cx.m2985b() - f3724x >= ((long) C3869cq.m2867B());
    }

    /* renamed from: e */
    public final synchronized AMapLocationServer m2717e() {
        AMapLocationServer m2813a;
        if (this.f3762al.length() > 0) {
            this.f3762al.delete(0, this.f3762al.length());
        }
        if (TextUtils.isEmpty(this.f3770at)) {
            m2813a = new AMapLocationServer("");
            m2813a.setErrorCode(this.f3785m);
            m2813a.setLocationDetail(this.f3762al.toString());
        } else {
            if (!C3858cf.m2807a().m2817b()) {
                C3858cf.m2807a().m2815a(this.f3726A, C3858cf.m2807a().m2814a(this.f3771au, this.f3794z, this.f3726A));
            }
            m2813a = C3858cf.m2807a().m2813a(this.f3771au, this.f3794z, this.f3765ao);
            if (C3876cx.m2977a(m2813a)) {
                this.f3758ah = 0L;
                m2813a.setLocationType(4);
                this.f3738M = m2813a;
                m2670b(m2813a.mo490a(1));
                m2685k();
                return m2813a;
            }
        }
        return m2813a;
    }

    /* renamed from: f */
    public final void m2718f() {
        boolean z = true;
        if (C3869cq.m2905v()) {
            Context context = this.f3726A;
            try {
                if (this.f3751aa == null && !this.f3768ar) {
                    C3893s m3092a = C3880f.m3092a("Collection", "1.0.0");
                    this.f3768ar = C3873cu.m2936a(context, m3092a);
                    if (this.f3768ar) {
                        try {
                            this.f3751aa = C3819au.m2476a(context, m3092a, this.f3778f, null, new Class[]{Context.class}, new Object[]{context});
                        } catch (Throwable unused) {
                        }
                    } else {
                        this.f3768ar = true;
                    }
                }
            } catch (Throwable th) {
                C3880f.m3097a(th, "APS", "initCollection");
            }
        }
        if (this.f3792t && C3869cq.m2905v() && !m2698v() && m2697u()) {
            if (!C3869cq.m2905v()) {
                m2701y();
                return;
            }
            if (C3876cx.m2979a(this.f3737L, "coll")) {
                try {
                    z = true ^ this.f3737L.getString("coll").equals("0");
                } catch (Throwable th2) {
                    C3880f.m3097a(th2, "APS", "start3rdCM");
                }
            }
            if (!z) {
                m2701y();
                return;
            }
            if (m2698v()) {
                return;
            }
            try {
                m2700x();
                try {
                    C3871cs.m2917a(this.f3751aa, "start", new Object[0]);
                } catch (Throwable unused2) {
                }
                Context context2 = this.f3726A;
            } catch (Throwable th3) {
                C3880f.m3097a(th3, "APS", "start3rdCM");
            }
        }
    }
}
