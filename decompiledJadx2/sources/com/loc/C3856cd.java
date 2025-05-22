package com.loc;

import android.content.ContentResolver;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import java.util.List;

/* compiled from: WifiManagerWrapper.java */
/* renamed from: com.loc.cd */
/* loaded from: classes4.dex */
public final class C3856cd {

    /* renamed from: c */
    private WifiManager f3897c;

    /* renamed from: d */
    private Context f3898d;

    /* renamed from: a */
    long f3895a = 0;

    /* renamed from: b */
    String f3896b = "isScanAlwaysAvailable";

    /* renamed from: e */
    private String f3899e = null;

    public C3856cd(Context context, WifiManager wifiManager) {
        this.f3897c = wifiManager;
        this.f3898d = context;
    }

    /* renamed from: a */
    public static boolean m2785a(WifiInfo wifiInfo) {
        return (wifiInfo == null || TextUtils.isEmpty(wifiInfo.getSSID()) || !C3876cx.m2993b(wifiInfo.getBSSID())) ? false : true;
    }

    /* renamed from: a */
    public final List<ScanResult> m2786a() {
        WifiManager wifiManager = this.f3897c;
        if (wifiManager != null) {
            try {
                List<ScanResult> scanResults = wifiManager.getScanResults();
                this.f3899e = null;
                return scanResults;
            } catch (SecurityException e) {
                this.f3899e = e.getMessage();
            } catch (Throwable th) {
                this.f3899e = null;
                C3880f.m3097a(th, "WifiManagerWrapper", "getScanResults");
            }
        }
        return null;
    }

    /* renamed from: a */
    public final void m2787a(boolean z) {
        Context context = this.f3898d;
        if (this.f3897c == null || context == null || !z || C3876cx.m2996c() <= 17) {
            return;
        }
        ContentResolver contentResolver = context.getContentResolver();
        try {
            if (((Integer) C3871cs.m2919a("android.provider.Settings$Global", "getInt", new Object[]{contentResolver, "wifi_scan_always_enabled"}, (Class<?>[]) new Class[]{ContentResolver.class, String.class})).intValue() == 0) {
                C3871cs.m2919a("android.provider.Settings$Global", "putInt", new Object[]{contentResolver, "wifi_scan_always_enabled", 1}, (Class<?>[]) new Class[]{ContentResolver.class, String.class, Integer.TYPE});
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "WifiManagerWrapper", "enableWifiAlwaysScan");
        }
    }

    /* renamed from: a */
    public final boolean m2788a(ConnectivityManager connectivityManager) {
        WifiManager wifiManager = this.f3897c;
        if (wifiManager == null) {
            return false;
        }
        try {
            if (C3876cx.m2967a(connectivityManager.getActiveNetworkInfo()) == 1) {
                return m2785a(wifiManager.getConnectionInfo());
            }
            return false;
        } catch (Throwable th) {
            C3880f.m3097a(th, "WifiManagerWrapper", "wifiAccess");
            return false;
        }
    }

    /* renamed from: b */
    public final WifiInfo m2789b() {
        try {
            if (this.f3897c != null) {
                return this.f3897c.getConnectionInfo();
            }
            return null;
        } catch (Throwable th) {
            C3880f.m3097a(th, "WifiManagerWrapper", "getConnectionInfo");
            return null;
        }
    }

    /* renamed from: c */
    public final int m2790c() {
        WifiManager wifiManager = this.f3897c;
        if (wifiManager != null) {
            return wifiManager.getWifiState();
        }
        return 4;
    }

    /* renamed from: d */
    public final boolean m2791d() {
        if (C3876cx.m2985b() - this.f3895a < 5000 || this.f3897c == null) {
            return false;
        }
        this.f3895a = C3876cx.m2985b();
        return this.f3897c.startScan();
    }

    /* renamed from: e */
    public final boolean m2792e() {
        boolean z;
        WifiManager wifiManager = this.f3897c;
        if (wifiManager == null) {
            return false;
        }
        try {
            z = wifiManager.isWifiEnabled();
        } catch (Throwable th) {
            C3880f.m3097a(th, "WifiManagerWrapper", "wifiEnabled1");
            z = false;
        }
        if (z || C3876cx.m2996c() <= 17) {
            return z;
        }
        try {
            return String.valueOf(C3871cs.m2917a(wifiManager, this.f3896b, new Object[0])).equals("true");
        } catch (Throwable th2) {
            C3880f.m3097a(th2, "WifiManagerWrapper", "wifiEnabled");
            return z;
        }
    }

    /* renamed from: f */
    public final String m2793f() {
        return this.f3899e;
    }

    /* renamed from: g */
    public final List<WifiConfiguration> m2794g() {
        WifiManager wifiManager = this.f3897c;
        if (wifiManager != null) {
            return wifiManager.getConfiguredNetworks();
        }
        return null;
    }
}
