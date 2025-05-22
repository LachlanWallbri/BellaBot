package com.iflytek.aiui.pro;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.tencent.bugly.BuglyStrategy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/* renamed from: com.iflytek.aiui.pro.e0 */
/* loaded from: classes4.dex */
public class C3611e0 {

    /* renamed from: d */
    private C3608d f2505d;

    /* renamed from: f */
    private a f2507f;

    /* renamed from: p */
    C3597b f2517p;

    /* renamed from: a */
    private Context f2502a = null;

    /* renamed from: b */
    private ConnectivityManager f2503b = null;

    /* renamed from: c */
    private WifiManager f2504c = null;

    /* renamed from: e */
    public boolean f2506e = false;

    /* renamed from: g */
    private String f2508g = null;

    /* renamed from: h */
    private String f2509h = null;

    /* renamed from: i */
    private String f2510i = null;

    /* renamed from: j */
    private String f2511j = null;

    /* renamed from: k */
    private List<ScanResult> f2512k = new ArrayList();

    /* renamed from: l */
    private WifiInfo f2513l = null;

    /* renamed from: m */
    private boolean f2514m = false;

    /* renamed from: n */
    private String f2515n = "00:00:00:00:00:00";

    /* renamed from: o */
    private C3610e f2516o = null;

    /* renamed from: q */
    TreeMap<Integer, ScanResult> f2518q = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.iflytek.aiui.pro.e0$a */
    /* loaded from: classes4.dex */
    public class a extends Thread {
        a() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            C3614g.K(C3611e0.this.f2502a);
        }
    }

    /* renamed from: d */
    private boolean m1271d(int i) {
        int i2 = 20;
        try {
            i2 = WifiManager.calculateSignalLevel(i, 20);
        } catch (ArithmeticException e) {
            C3614g.n(e, "APS", "wifiSigFine");
        }
        return i2 >= 1;
    }

    /* renamed from: e */
    private boolean m1272e(WifiInfo wifiInfo) {
        return (wifiInfo == null || TextUtils.isEmpty(wifiInfo.getBSSID()) || wifiInfo.getBSSID().equals("00:00:00:00:00:00") || wifiInfo.getBSSID().contains(" :")) ? false : true;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.iflytek.aiui.pro.e, com.iflytek.aiui.pro.a0] */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.iflytek.aiui.pro.e, com.iflytek.aiui.pro.a0] */
    /* renamed from: j */
    private C3610e m1273j(boolean z) {
        this.f2516o.m891n(this.f2516o.u());
        this.f2516o.m880c(String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s&loc_channel=%s", "1.5.1", "channelloc", z ? "1" : "0"));
        return this.f2516o;
    }

    /* JADX WARN: Code restructure failed: missing block: B:84:0x0206, code lost:
    
        if (r4 > 127) goto L73;
     */
    /* JADX WARN: Type inference failed for: r6v0, types: [android.telephony.TelephonyManager] */
    /* renamed from: m */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void m1274m() {
        NetworkInfo networkInfo;
        String str;
        String str2;
        int i;
        synchronized (this) {
            this.f2511j = "";
            String str3 = "";
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            StringBuilder sb3 = new StringBuilder();
            C3608d c3608d = this.f2505d;
            int g = c3608d.m1261g();
            ?? k = c3608d.m1249k();
            ArrayList c = c3608d.m1257c();
            if (k != 0) {
                if (TextUtils.isEmpty(this.f2509h)) {
                    try {
                        this.f2509h = C3618i.q(this.f2502a);
                    } catch (Throwable th) {
                        C3614g.n(th, "APS", "getApsReq part4");
                    }
                }
                if (TextUtils.isEmpty(this.f2509h)) {
                    this.f2509h = "888888888888888";
                }
                if (TextUtils.isEmpty(this.f2510i)) {
                    try {
                        this.f2510i = k.getSubscriberId();
                    } catch (Throwable th2) {
                        C3614g.n(th2, "APS", "getApsReq part2");
                    }
                }
                if (TextUtils.isEmpty(this.f2510i)) {
                    this.f2510i = "888888888888888";
                }
            }
            try {
                networkInfo = this.f2503b.getActiveNetworkInfo();
            } catch (Throwable th3) {
                C3614g.n(th3, "APS", "getApsReq part");
                networkInfo = null;
            }
            if (C3614g.c(networkInfo) != -1) {
                str = C3614g.D(k);
                str2 = (m1277p() && m1272e(this.f2513l)) ? "2" : "1";
                if (!m1277p()) {
                    m1276o();
                }
            } else {
                this.f2513l = null;
                str = "";
                str2 = "";
            }
            if (!c.isEmpty()) {
                StringBuilder sb4 = new StringBuilder();
                if (g == 1) {
                    C3605c c3605c = (C3605c) c.get(0);
                    sb4.delete(0, sb4.length());
                    sb4.append("<mcc>");
                    sb4.append(c3605c.f2440a);
                    sb4.append("</mcc>");
                    sb4.append("<mnc>");
                    sb4.append(c3605c.f2441b);
                    sb4.append("</mnc>");
                    sb4.append("<lac>");
                    sb4.append(c3605c.f2442c);
                    sb4.append("</lac>");
                    sb4.append("<cellid>");
                    sb4.append(c3605c.f2443d);
                    sb4.append("</cellid>");
                    sb4.append("<signal>");
                    sb4.append(c3605c.f2449j);
                    sb4.append("</signal>");
                    str3 = sb4.toString();
                    for (int i2 = 1; i2 < c.size(); i2++) {
                        C3605c c3605c2 = (C3605c) c.get(i2);
                        sb.append(c3605c2.f2442c);
                        sb.append(",");
                        sb.append(c3605c2.f2443d);
                        sb.append(",");
                        sb.append(c3605c2.f2449j);
                        if (i2 < c.size() - 1) {
                            sb.append("*");
                        }
                    }
                } else if (g == 2) {
                    C3605c c3605c3 = (C3605c) c.get(0);
                    sb4.delete(0, sb4.length());
                    sb4.append("<mcc>");
                    sb4.append(c3605c3.f2440a);
                    sb4.append("</mcc>");
                    sb4.append("<sid>");
                    sb4.append(c3605c3.f2446g);
                    sb4.append("</sid>");
                    sb4.append("<nid>");
                    sb4.append(c3605c3.f2447h);
                    sb4.append("</nid>");
                    sb4.append("<bid>");
                    sb4.append(c3605c3.f2448i);
                    sb4.append("</bid>");
                    if (c3605c3.f2445f > 0 && c3605c3.f2444e > 0) {
                        sb4.append("<lon>");
                        sb4.append(c3605c3.f2445f);
                        sb4.append("</lon>");
                        sb4.append("<lat>");
                        sb4.append(c3605c3.f2444e);
                        sb4.append("</lat>");
                    }
                    sb4.append("<signal>");
                    sb4.append(c3605c3.f2449j);
                    sb4.append("</signal>");
                    str3 = sb4.toString();
                }
                sb4.delete(0, sb4.length());
            }
            if (m1277p()) {
                if (m1272e(this.f2513l)) {
                    sb3.append(this.f2513l.getBSSID());
                    sb3.append(",");
                    int rssi = this.f2513l.getRssi();
                    if (rssi >= -128) {
                    }
                    rssi = 0;
                    sb3.append(rssi);
                    sb3.append(",");
                    String ssid = this.f2513l.getSSID();
                    try {
                        i = this.f2513l.getSSID().getBytes("UTF-8").length;
                    } catch (Throwable th4) {
                        C3614g.n(th4, "APS", "getApsReq");
                        i = 32;
                    }
                    if (i >= 32) {
                        ssid = "unkwn";
                    }
                    sb3.append(ssid.replace("*", "."));
                }
                List<ScanResult> list = this.f2512k;
                int min = Math.min(list.size(), 15);
                for (int i3 = 0; i3 < min; i3++) {
                    ScanResult scanResult = list.get(i3);
                    sb2.append(scanResult.BSSID);
                    sb2.append(",");
                    sb2.append(scanResult.level);
                    sb2.append(",");
                    sb2.append(scanResult.SSID);
                    sb2.append("*");
                }
            } else {
                m1276o();
            }
            C3610e c3610e = this.f2516o;
            c3610e.f2494p = "api_serverSDK_130905";
            c3610e.f2495q = "S128DF1572465B890OE3F7A13167KLEI";
            c3610e.f2496r = C3614g.R();
            this.f2516o.f2497s = "android" + C3614g.T();
            this.f2516o.f2498t = C3614g.C(this.f2502a, this.f2508g);
            C3610e c3610e2 = this.f2516o;
            c3610e2.f2499u = "0";
            c3610e2.f2500v = "";
            c3610e2.w = this.f2509h;
            c3610e2.x = this.f2510i;
            c3610e2.y = this.f2515n;
            c3610e2.f2253A = m1280b();
            C3610e c3610e3 = this.f2516o;
            c3610e3.z = this.f2511j;
            c3610e3.B = str;
            c3610e3.C = str2;
            c3610e3.D = String.valueOf(g);
            C3610e c3610e4 = this.f2516o;
            c3610e4.E = str3;
            c3610e4.f2258F = sb.toString();
            this.f2516o.H = sb2.toString();
            C3610e c3610e5 = this.f2516o;
            c3610e5.I = "0";
            c3610e5.G = sb3.toString();
            sb.delete(0, sb.length());
            sb2.delete(0, sb2.length());
            sb3.delete(0, sb3.length());
        }
    }

    /* renamed from: n */
    private String m1275n() {
        if (this.f2502a == null) {
            throw new Exception("context is null");
        }
        try {
            byte[] m1283g = m1283g(C3614g.i());
            if (m1283g == null) {
                return null;
            }
            String str = new String(m1283g, "UTF-8");
            return str.contains("\"status\":\"0\"") ? str : C3614g.k(m1283g);
        } catch (Throwable th) {
            C3614g.n(th, "APS", "getApsLoc");
            throw new Exception("connection error");
        }
    }

    /* renamed from: o */
    private void m1276o() {
        this.f2512k.clear();
        this.f2513l = null;
    }

    /* renamed from: p */
    private boolean m1277p() {
        boolean z;
        try {
            z = this.f2504c.isWifiEnabled();
        } catch (Throwable th) {
            C3614g.n(th, "APS", "wifiEnabled");
            z = false;
        }
        if (z || C3614g.N() <= 17) {
            return z;
        }
        try {
            return String.valueOf(C3614g.g(this.f2504c, "isScanAlwaysAvailable", new Object[0])).equals("true");
        } catch (Throwable th2) {
            C3614g.n(th2, "WifiManagerWrapper", "wifiEnabled");
            return z;
        }
    }

    /* renamed from: q */
    private void m1278q() {
        synchronized (this) {
            if (this.f2507f == null) {
                a aVar = new a();
                this.f2507f = aVar;
                aVar.start();
            }
        }
    }

    /* renamed from: r */
    private C3626m m1279r() {
        return new C3626m("channelloc", "1.5.1");
    }

    /* renamed from: b */
    public String m1280b() {
        return "1.5.1";
    }

    /* renamed from: c */
    public void m1281c(Context context) {
        synchronized (this) {
            if (context == null) {
                return;
            }
            Context applicationContext = context.getApplicationContext();
            this.f2502a = applicationContext;
            this.f2508g = C3614g.C(applicationContext, this.f2508g);
            try {
                this.f2504c = (WifiManager) C3614g.f(this.f2502a, "wifi");
                this.f2503b = (ConnectivityManager) C3614g.f(this.f2502a, "connectivity");
            } catch (Throwable th) {
                C3614g.n(th, "APS", "initBroadcastListener");
            }
            this.f2505d = new C3608d(this.f2502a);
            this.f2516o = new C3610e(this.f2502a, m1279r());
            this.f2505d.m1247i();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0029, code lost:
    
        if (r0.isEmpty() != false) goto L16;
     */
    /* renamed from: f */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean m1282f(boolean z) {
        boolean z2;
        synchronized (this) {
            ArrayList c = this.f2505d.m1257c();
            if (m1277p()) {
                this.f2513l = this.f2504c.getConnectionInfo();
            } else {
                m1276o();
            }
            List<ScanResult> list = this.f2512k;
            if (c == null || c.isEmpty()) {
                if (list != null) {
                }
                z2 = false;
            }
            z2 = true;
        }
        return z2;
    }

    /* JADX WARN: Type inference failed for: r7v1, types: [com.iflytek.aiui.pro.e, com.iflytek.aiui.pro.a0] */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.iflytek.aiui.pro.e, com.iflytek.aiui.pro.a0] */
    /* JADX WARN: Type inference failed for: r7v3, types: [com.iflytek.aiui.pro.e, com.iflytek.aiui.pro.a0] */
    /* JADX WARN: Type inference failed for: r7v4, types: [com.iflytek.aiui.pro.e, com.iflytek.aiui.pro.a0] */
    /* JADX WARN: Type inference failed for: r7v5, types: [com.iflytek.aiui.pro.e, com.iflytek.aiui.pro.a0] */
    /* renamed from: g */
    public byte[] m1283g(String str) {
        if (C3614g.c(C3614g.A(this.f2502a)) == -1) {
            return new byte[0];
        }
        HashMap hashMap = new HashMap();
        hashMap.clear();
        hashMap.put("encr", "1");
        C3610e m1273j = m1273j(true);
        this.f2516o = m1273j;
        m1273j.e(hashMap);
        String j = C3618i.m1366j(this.f2502a);
        String m1385a = C3622k.m1385a();
        String b = C3622k.b(this.f2502a, m1385a, "key=" + j);
        HashMap hashMap2 = new HashMap();
        hashMap2.put(TransferTable.COLUMN_KEY, j);
        hashMap2.put("ts", m1385a);
        hashMap2.put("scode", b);
        hashMap2.put("output", "json");
        this.f2516o.h(str);
        this.f2516o.m886i(hashMap2);
        this.f2516o.m881d(C3628n.n(this.f2502a));
        this.f2516o.m879b(BuglyStrategy.C5863a.MAX_USERDATA_VALUE_LENGTH);
        this.f2516o.m884g(BuglyStrategy.C5863a.MAX_USERDATA_VALUE_LENGTH);
        return C3652z.h(this.f2516o, false);
    }

    /* renamed from: h */
    public String m1284h(boolean z) {
        boolean m1282f;
        boolean q = C3614g.q(this.f2502a);
        this.f2514m = q;
        if (!q) {
            try {
                this.f2505d.m();
                this.f2505d.m1247i();
            } catch (Throwable unused) {
            }
        }
        if (C3614g.S(this.f2502a)) {
            m1278q();
        }
        if (this.f2517p == null) {
            C3597b c3597b = new C3597b(this.f2502a);
            this.f2517p = c3597b;
            c3597b.m1115c();
            this.f2517p.m1116d();
        }
        this.f2504c.startScan();
        synchronized (this) {
            this.f2512k = this.f2504c.getScanResults();
            m1286k();
            m1282f = m1282f(false);
            synchronized (this) {
                WifiInfo wifiInfo = this.f2513l;
                if (wifiInfo != null) {
                    this.f2515n = wifiInfo.getMacAddress();
                }
            }
        }
        if (!m1282f) {
            throw new Exception("can't wifi cell");
        }
        if (this.f2505d.f(this.f2514m) && this.f2506e) {
            this.f2505d.n();
        }
        m1274m();
        if (z) {
            return m1275n();
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.iflytek.aiui.pro.e, com.iflytek.aiui.pro.a0] */
    /* renamed from: i */
    public byte[] m1285i() {
        m1284h(false);
        return m1273j(false).m894q();
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0073, code lost:
    
        if (r4 < 32) goto L35;
     */
    /* renamed from: k */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void m1286k() {
        String str;
        int i;
        synchronized (this) {
            List<ScanResult> list = this.f2512k;
            if (list != null && !list.isEmpty()) {
                boolean P = C3614g.P();
                int size = this.f2512k.size();
                if (this.f2518q == null) {
                    this.f2518q = new TreeMap<>(Collections.reverseOrder());
                }
                this.f2518q.clear();
                for (int i2 = 0; i2 < size; i2++) {
                    ScanResult scanResult = this.f2512k.get(i2);
                    if (C3614g.s(scanResult) && (size <= 20 || m1271d(scanResult.level))) {
                        if (TextUtils.isEmpty(scanResult.SSID)) {
                            str = "unkwn";
                        } else {
                            if (P) {
                                String replace = scanResult.SSID.replace("*", ".");
                                scanResult.SSID = replace;
                                try {
                                    i = replace.getBytes("UTF-8").length;
                                } catch (Throwable th) {
                                    C3614g.n(th, "APS", "setWifiOrder");
                                    i = 32;
                                }
                            }
                            str = String.valueOf(i2);
                        }
                        scanResult.SSID = str;
                        this.f2518q.put(Integer.valueOf((scanResult.level * 30) + i2), scanResult);
                    }
                }
                this.f2512k.clear();
                Iterator<Map.Entry<Integer, ScanResult>> it = this.f2518q.entrySet().iterator();
                while (it.hasNext()) {
                    this.f2512k.add(it.next().getValue());
                }
                this.f2518q.clear();
            }
        }
    }

    /* renamed from: l */
    public void m1287l() {
        synchronized (this) {
            C3597b c3597b = this.f2517p;
            if (c3597b != null) {
                c3597b.m1112a();
            }
        }
    }
}
