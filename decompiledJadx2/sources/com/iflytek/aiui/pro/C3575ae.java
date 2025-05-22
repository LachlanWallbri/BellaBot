package com.iflytek.aiui.pro;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
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

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* renamed from: com.iflytek.aiui.pro.ae */
/* loaded from: classes.dex */
public class C3575ae {

    /* renamed from: b */
    C3597b f2276b;

    /* renamed from: g */
    private C3608d f2281g;

    /* renamed from: h */
    private a f2282h;

    /* renamed from: d */
    private Context f2278d = null;

    /* renamed from: e */
    private ConnectivityManager f2279e = null;

    /* renamed from: f */
    private WifiManager f2280f = null;

    /* renamed from: a */
    public boolean f2275a = false;

    /* renamed from: i */
    private String f2283i = null;

    /* renamed from: j */
    private String f2284j = null;

    /* renamed from: k */
    private String f2285k = null;

    /* renamed from: l */
    private String f2286l = null;

    /* renamed from: m */
    private List<ScanResult> f2287m = new ArrayList();

    /* renamed from: n */
    private WifiInfo f2288n = null;

    /* renamed from: o */
    private boolean f2289o = false;

    /* renamed from: p */
    private String f2290p = "00:00:00:00:00:00";

    /* renamed from: q */
    private C3610e f2291q = null;

    /* renamed from: c */
    TreeMap<Integer, ScanResult> f2277c = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* renamed from: com.iflytek.aiui.pro.ae$a */
    /* loaded from: classes.dex */
    public class a extends Thread {
        a() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            C3614g.m1331c(C3575ae.this.f2278d);
        }
    }

    /* renamed from: a */
    private boolean m958a(int i) {
        int i2 = 20;
        try {
            i2 = WifiManager.calculateSignalLevel(i, 20);
        } catch (ArithmeticException e) {
            C3614g.m1308a(e, "APS", "wifiSigFine");
        }
        return i2 >= 1;
    }

    /* renamed from: a */
    private boolean m959a(WifiInfo wifiInfo) {
        return (wifiInfo == null || TextUtils.isEmpty(wifiInfo.getBSSID()) || wifiInfo.getBSSID().equals("00:00:00:00:00:00") || wifiInfo.getBSSID().contains(" :")) ? false : true;
    }

    /* renamed from: c */
    private C3610e m960c(boolean z) {
        this.f2291q.m930c(this.f2291q.m1268c());
        this.f2291q.m929c(String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s&loc_channel=%s", "1.5.1", "channelloc", z ? "1" : "0"));
        return this.f2291q;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:51|(8:65|(1:67)|54|55|56|57|(1:59)|60)|53|54|55|56|57|(0)|60) */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x022c, code lost:
    
        r10 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x022d, code lost:
    
        com.iflytek.aiui.pro.C3614g.m1308a(r10, "APS", "getApsReq");
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0234, code lost:
    
        r10 = 32;
     */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0238  */
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void m961e() {
        NetworkInfo networkInfo;
        String str;
        String str2;
        String str3;
        int i;
        synchronized (this) {
            this.f2286l = "";
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            StringBuilder sb3 = new StringBuilder();
            C3608d c3608d = this.f2281g;
            int m1256b = c3608d.m1256b();
            TelephonyManager m1258d = c3608d.m1258d();
            ArrayList<C3605c> m1252a = c3608d.m1252a();
            if (m1258d != null) {
                if (TextUtils.isEmpty(this.f2284j)) {
                    try {
                        this.f2284j = C3618i.m1368l(this.f2278d);
                    } catch (Throwable th) {
                        C3614g.m1308a(th, "APS", "getApsReq part4");
                    }
                }
                if (TextUtils.isEmpty(this.f2284j)) {
                    this.f2284j = "888888888888888";
                }
                if (TextUtils.isEmpty(this.f2285k)) {
                    try {
                        this.f2285k = m1258d.getSubscriberId();
                    } catch (Throwable th2) {
                        C3614g.m1308a(th2, "APS", "getApsReq part2");
                    }
                }
                if (TextUtils.isEmpty(this.f2285k)) {
                    this.f2285k = "888888888888888";
                }
            }
            try {
                networkInfo = this.f2279e.getActiveNetworkInfo();
            } catch (Throwable th3) {
                C3614g.m1308a(th3, "APS", "getApsReq part");
                networkInfo = null;
            }
            if (C3614g.m1297a(networkInfo) != -1) {
                str2 = C3614g.m1324b(m1258d);
                str = (m964h() && m959a(this.f2288n)) ? "2" : "1";
                if (!m964h()) {
                    m963g();
                }
            } else {
                this.f2288n = null;
                str = "";
                str2 = "";
            }
            if (m1252a.isEmpty()) {
                str3 = "";
            } else {
                StringBuilder sb4 = new StringBuilder();
                if (m1256b == 1) {
                    C3605c c3605c = m1252a.get(0);
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
                    String sb5 = sb4.toString();
                    for (int i2 = 1; i2 < m1252a.size(); i2++) {
                        C3605c c3605c2 = m1252a.get(i2);
                        sb.append(c3605c2.f2442c);
                        sb.append(",");
                        sb.append(c3605c2.f2443d);
                        sb.append(",");
                        sb.append(c3605c2.f2449j);
                        if (i2 < m1252a.size() - 1) {
                            sb.append("*");
                        }
                    }
                    str3 = sb5;
                } else if (m1256b != 2) {
                    str3 = "";
                } else {
                    C3605c c3605c3 = m1252a.get(0);
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
            if (m964h()) {
                if (m959a(this.f2288n)) {
                    sb3.append(this.f2288n.getBSSID());
                    sb3.append(",");
                    int rssi = this.f2288n.getRssi();
                    if (rssi >= -128) {
                        if (rssi > 127) {
                        }
                        sb3.append(rssi);
                        sb3.append(",");
                        String ssid = this.f2288n.getSSID();
                        i = this.f2288n.getSSID().getBytes("UTF-8").length;
                        if (i >= 32) {
                            ssid = "unkwn";
                        }
                        sb3.append(ssid.replace("*", "."));
                    }
                    rssi = 0;
                    sb3.append(rssi);
                    sb3.append(",");
                    String ssid2 = this.f2288n.getSSID();
                    i = this.f2288n.getSSID().getBytes("UTF-8").length;
                    if (i >= 32) {
                    }
                    sb3.append(ssid2.replace("*", "."));
                }
                List<ScanResult> list = this.f2287m;
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
                m963g();
            }
            this.f2291q.f2481c = "api_serverSDK_130905";
            this.f2291q.f2482d = "S128DF1572465B890OE3F7A13167KLEI";
            this.f2291q.f2483e = C3614g.m1338f();
            this.f2291q.f2484f = "android" + C3614g.m1340g();
            this.f2291q.f2485g = C3614g.m1323b(this.f2278d, this.f2283i);
            this.f2291q.f2486h = "0";
            this.f2291q.f2487i = "";
            this.f2291q.f2488j = this.f2284j;
            this.f2291q.f2489k = this.f2285k;
            this.f2291q.f2490l = this.f2290p;
            this.f2291q.f2492n = m967a();
            this.f2291q.f2491m = this.f2286l;
            this.f2291q.f2493o = str2;
            this.f2291q.f2494p = str;
            this.f2291q.f2495q = String.valueOf(m1256b);
            this.f2291q.f2496r = str3;
            this.f2291q.f2497s = sb.toString();
            this.f2291q.f2499u = sb2.toString();
            this.f2291q.f2500v = "0";
            this.f2291q.f2498t = sb3.toString();
            sb.delete(0, sb.length());
            sb2.delete(0, sb2.length());
            sb3.delete(0, sb3.length());
        }
    }

    /* renamed from: f */
    private String m962f() throws Exception {
        if (this.f2278d == null) {
            throw new Exception("context is null");
        }
        try {
            byte[] m970a = m970a(C3614g.m1303a());
            if (m970a == null) {
                return null;
            }
            String str = new String(m970a, "UTF-8");
            return !str.contains("\"status\":\"0\"") ? C3614g.m1305a(m970a) : str;
        } catch (Throwable th) {
            C3614g.m1308a(th, "APS", "getApsLoc");
            throw new Exception("connection error");
        }
    }

    /* renamed from: g */
    private void m963g() {
        this.f2287m.clear();
        this.f2288n = null;
    }

    /* renamed from: h */
    private boolean m964h() {
        boolean z;
        try {
            z = this.f2280f.isWifiEnabled();
        } catch (Throwable th) {
            C3614g.m1308a(th, "APS", "wifiEnabled");
            z = false;
        }
        if (z || C3614g.m1334d() <= 17) {
            return z;
        }
        try {
            return String.valueOf(C3614g.m1301a(this.f2280f, "isScanAlwaysAvailable", new Object[0])).equals("true");
        } catch (Throwable th2) {
            C3614g.m1308a(th2, "WifiManagerWrapper", "wifiEnabled");
            return z;
        }
    }

    /* renamed from: i */
    private void m965i() {
        synchronized (this) {
            if (this.f2282h == null) {
                a aVar = new a();
                this.f2282h = aVar;
                aVar.start();
            }
        }
    }

    /* renamed from: j */
    private C3626m m966j() {
        return new C3626m("channelloc", "1.5.1");
    }

    /* renamed from: a */
    public String m967a() {
        return "1.5.1";
    }

    /* renamed from: a */
    public void m968a(Context context) {
        synchronized (this) {
            if (context != null) {
                Context applicationContext = context.getApplicationContext();
                this.f2278d = applicationContext;
                this.f2283i = C3614g.m1323b(applicationContext, this.f2283i);
                try {
                    this.f2280f = (WifiManager) C3614g.m1300a(this.f2278d, "wifi");
                    this.f2279e = (ConnectivityManager) C3614g.m1300a(this.f2278d, "connectivity");
                } catch (Throwable th) {
                    C3614g.m1308a(th, "APS", "initBroadcastListener");
                }
                this.f2281g = new C3608d(this.f2278d);
                this.f2291q = new C3610e(this.f2278d, m966j());
                this.f2281g.m1257c();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0029, code lost:
    
        if (r0.isEmpty() != false) goto L16;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean m969a(boolean z) {
        boolean z2;
        synchronized (this) {
            ArrayList<C3605c> m1252a = this.f2281g.m1252a();
            if (m964h()) {
                this.f2288n = this.f2280f.getConnectionInfo();
            } else {
                m963g();
            }
            List<ScanResult> list = this.f2287m;
            if (m1252a == null || m1252a.isEmpty()) {
                if (list != null) {
                }
                z2 = false;
            }
            z2 = true;
        }
        return z2;
    }

    /* renamed from: a */
    public byte[] m970a(String str) throws Exception {
        if (C3614g.m1297a(C3614g.m1321b(this.f2278d)) == -1) {
            return new byte[0];
        }
        HashMap hashMap = new HashMap();
        hashMap.clear();
        hashMap.put("encr", "1");
        C3610e m960c = m960c(true);
        this.f2291q = m960c;
        m960c.mo923a(hashMap);
        String m1361e = C3618i.m1361e(this.f2278d);
        String m1385a = C3622k.m1385a();
        String m1386a = C3622k.m1386a(this.f2278d, m1385a, "key=" + m1361e);
        HashMap hashMap2 = new HashMap();
        hashMap2.put(TransferTable.COLUMN_KEY, m1361e);
        hashMap2.put("ts", m1385a);
        hashMap2.put("scode", m1386a);
        hashMap2.put("output", "json");
        this.f2291q.mo921a(str);
        this.f2291q.m927b(hashMap2);
        this.f2291q.m922a(C3628n.m1419b(this.f2278d));
        this.f2291q.m920a(BuglyStrategy.C5863a.MAX_USERDATA_VALUE_LENGTH);
        this.f2291q.m926b(BuglyStrategy.C5863a.MAX_USERDATA_VALUE_LENGTH);
        return C3652z.m1634a((C3571aa) this.f2291q, false);
    }

    /* renamed from: b */
    public String m971b(boolean z) throws Exception {
        boolean m969a;
        boolean m1311a = C3614g.m1311a(this.f2278d);
        this.f2289o = m1311a;
        if (!m1311a) {
            try {
                this.f2281g.m1259e();
                this.f2281g.m1257c();
            } catch (Throwable unused) {
            }
        }
        if (C3614g.m1339f(this.f2278d)) {
            m965i();
        }
        if (this.f2276b == null) {
            C3597b c3597b = new C3597b(this.f2278d);
            this.f2276b = c3597b;
            c3597b.m1114b();
            this.f2276b.m1115c();
        }
        this.f2280f.startScan();
        synchronized (this) {
            this.f2287m = this.f2280f.getScanResults();
            m973c();
            m969a = m969a(false);
            synchronized (this) {
                if (this.f2288n != null) {
                    this.f2290p = this.f2288n.getMacAddress();
                }
            }
        }
        if (!m969a) {
            throw new Exception("can't wifi cell");
        }
        if (this.f2281g.m1255a(this.f2289o) && this.f2275a) {
            this.f2281g.m1260f();
        }
        m961e();
        if (z) {
            return m962f();
        }
        return null;
    }

    /* renamed from: b */
    public byte[] m972b() throws Exception {
        m971b(false);
        return m960c(false).m935h();
    }

    /* renamed from: c */
    public void m973c() {
        String str;
        int i;
        synchronized (this) {
            if (this.f2287m != null && !this.f2287m.isEmpty()) {
                boolean m1336e = C3614g.m1336e();
                int size = this.f2287m.size();
                if (this.f2277c == null) {
                    this.f2277c = new TreeMap<>(Collections.reverseOrder());
                }
                this.f2277c.clear();
                for (int i2 = 0; i2 < size; i2++) {
                    ScanResult scanResult = this.f2287m.get(i2);
                    if (C3614g.m1313a(scanResult) && (size <= 20 || m958a(scanResult.level))) {
                        if (TextUtils.isEmpty(scanResult.SSID)) {
                            str = "unkwn";
                        } else if (m1336e) {
                            scanResult.SSID = scanResult.SSID.replace("*", ".");
                            try {
                                i = scanResult.SSID.getBytes("UTF-8").length;
                            } catch (Throwable th) {
                                C3614g.m1308a(th, "APS", "setWifiOrder");
                                i = 32;
                            }
                            if (i >= 32) {
                                str = String.valueOf(i2);
                            }
                            this.f2277c.put(Integer.valueOf((scanResult.level * 30) + i2), scanResult);
                        } else {
                            str = String.valueOf(i2);
                        }
                        scanResult.SSID = str;
                        this.f2277c.put(Integer.valueOf((scanResult.level * 30) + i2), scanResult);
                    }
                }
                this.f2287m.clear();
                Iterator<Map.Entry<Integer, ScanResult>> it = this.f2277c.entrySet().iterator();
                while (it.hasNext()) {
                    this.f2287m.add(it.next().getValue());
                }
                this.f2277c.clear();
            }
        }
    }

    /* renamed from: d */
    public void m974d() {
        synchronized (this) {
            if (this.f2276b != null) {
                this.f2276b.m1112a();
            }
        }
    }
}
