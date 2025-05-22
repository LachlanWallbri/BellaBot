package com.pudutech.mirsdk;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.pudutech.base.Pdlog;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes5.dex */
public class WifiHotUtil {
    public static final String TAG = "WifiApAdmin";
    private Context context;
    private WifiManager wifiManager;

    public WifiHotUtil(Context context) {
        this.wifiManager = null;
        this.context = null;
        this.context = context;
        this.wifiManager = (WifiManager) context.getSystemService("wifi");
    }

    public boolean startWifiAp(String str, String str2) {
        if (this.wifiManager.isWifiEnabled()) {
            Pdlog.m3273d(TAG, "CLOSE WIFI");
            this.wifiManager.setWifiEnabled(false);
        }
        if (!isWifiApEnabled()) {
            Pdlog.m3273d(TAG, "START WIFI HOTSPOT");
            return startAp(str, str2);
        }
        Pdlog.m3273d(TAG, "WIFI HOTSPOT IS ALREADY STARTED");
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0167  */
    /* JADX WARN: Type inference failed for: r10v1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean startAp(String str, String str2) {
        boolean z;
        try {
            try {
                Method method = this.wifiManager.getClass().getMethod("startSoftAp", WifiConfiguration.class);
                WifiConfiguration wifiConfiguration = new WifiConfiguration();
                wifiConfiguration.SSID = str;
                wifiConfiguration.preSharedKey = str2;
                wifiConfiguration.hiddenSSID = true;
                wifiConfiguration.allowedAuthAlgorithms.set(0);
                wifiConfiguration.allowedProtocols.set(1);
                wifiConfiguration.allowedProtocols.set(0);
                wifiConfiguration.allowedKeyManagement.set(4);
                wifiConfiguration.allowedPairwiseCiphers.set(2);
                wifiConfiguration.allowedPairwiseCiphers.set(1);
                wifiConfiguration.allowedGroupCiphers.set(3);
                wifiConfiguration.allowedGroupCiphers.set(2);
                z = ((Boolean) method.invoke(this.wifiManager, wifiConfiguration)).booleanValue();
                try {
                    Pdlog.m3273d(TAG, "startAp ret = " + z);
                    if (!TextUtils.isEmpty(null)) {
                        Pdlog.m3274e(TAG, "startAp error_msg = " + ((String) null));
                    }
                    return z;
                } catch (IllegalAccessException e) {
                    e = e;
                    e.printStackTrace();
                    String illegalAccessException = e.toString();
                    if (!TextUtils.isEmpty(illegalAccessException)) {
                        Pdlog.m3274e(TAG, "startAp error_msg = " + illegalAccessException);
                    }
                    return z;
                } catch (IllegalArgumentException e2) {
                    e = e2;
                    e.printStackTrace();
                    String illegalArgumentException = e.toString();
                    if (!TextUtils.isEmpty(illegalArgumentException)) {
                        Pdlog.m3274e(TAG, "startAp error_msg = " + illegalArgumentException);
                    }
                    return z;
                } catch (SecurityException e3) {
                    e = e3;
                    e.printStackTrace();
                    String securityException = e.toString();
                    if (!TextUtils.isEmpty(securityException)) {
                        Pdlog.m3274e(TAG, "startAp error_msg = " + securityException);
                    }
                    return z;
                } catch (InvocationTargetException e4) {
                    e = e4;
                    e.printStackTrace();
                    String invocationTargetException = e.toString();
                    if (!TextUtils.isEmpty(invocationTargetException)) {
                        Pdlog.m3274e(TAG, "startAp error_msg = " + invocationTargetException);
                    }
                    return z;
                } catch (Exception e5) {
                    e = e5;
                    e.printStackTrace();
                    String exc = e.toString();
                    if (!TextUtils.isEmpty(exc)) {
                        Pdlog.m3274e(TAG, "startAp error_msg = " + exc);
                    }
                    return z;
                }
            } catch (IllegalAccessException e6) {
                e = e6;
                z = false;
            } catch (IllegalArgumentException e7) {
                e = e7;
                z = false;
            } catch (SecurityException e8) {
                e = e8;
                z = false;
            } catch (InvocationTargetException e9) {
                e = e9;
                z = false;
            } catch (Exception e10) {
                e = e10;
                z = false;
            } catch (Throwable unused) {
                str = 0;
                if (!TextUtils.isEmpty(null)) {
                }
                return str;
            }
        } catch (Throwable unused2) {
            if (!TextUtils.isEmpty(null)) {
                Pdlog.m3274e(TAG, "startAp error_msg = " + ((String) null));
            }
            return str;
        }
    }

    public WifiConfiguration getWifiApConfig() {
        try {
            try {
                try {
                    Method method = this.wifiManager.getClass().getMethod("getWifiApConfiguration", new Class[0]);
                    method.setAccessible(true);
                    WifiConfiguration wifiConfiguration = (WifiConfiguration) method.invoke(this.wifiManager, new Object[0]);
                    if (!TextUtils.isEmpty(null)) {
                        Pdlog.m3274e(TAG, "WifiConfiguration error_msg = " + ((String) null));
                    }
                    return wifiConfiguration;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    String illegalAccessException = e.toString();
                    if (!TextUtils.isEmpty(illegalAccessException)) {
                        Pdlog.m3274e(TAG, "WifiConfiguration error_msg = " + illegalAccessException);
                    }
                    return null;
                } catch (IllegalArgumentException e2) {
                    e2.printStackTrace();
                    String illegalArgumentException = e2.toString();
                    if (!TextUtils.isEmpty(illegalArgumentException)) {
                        Pdlog.m3274e(TAG, "WifiConfiguration error_msg = " + illegalArgumentException);
                    }
                    return null;
                }
            } catch (NoSuchMethodException e3) {
                e3.printStackTrace();
                String noSuchMethodException = e3.toString();
                if (!TextUtils.isEmpty(noSuchMethodException)) {
                    Pdlog.m3274e(TAG, "WifiConfiguration error_msg = " + noSuchMethodException);
                }
                return null;
            } catch (InvocationTargetException e4) {
                e4.printStackTrace();
                String invocationTargetException = e4.toString();
                if (!TextUtils.isEmpty(invocationTargetException)) {
                    Pdlog.m3274e(TAG, "WifiConfiguration error_msg = " + invocationTargetException);
                }
                return null;
            }
        } catch (Throwable th) {
            if (!TextUtils.isEmpty(null)) {
                Pdlog.m3274e(TAG, "WifiConfiguration error_msg = " + ((String) null));
            }
            throw th;
        }
    }

    public boolean isWifiApEnabled() {
        try {
            Method method = this.wifiManager.getClass().getMethod("isWifiApEnabled", new Class[0]);
            method.setAccessible(true);
            return ((Boolean) method.invoke(this.wifiManager, new Object[0])).booleanValue();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void closeWifiAp() {
        if (isWifiApEnabled()) {
            try {
                try {
                    try {
                        Method method = this.wifiManager.getClass().getMethod("stopSoftAp", new Class[0]);
                        method.setAccessible(true);
                        boolean booleanValue = ((Boolean) method.invoke(this.wifiManager, new Object[0])).booleanValue();
                        Pdlog.m3273d(TAG, "closeWifiAp ret = " + booleanValue);
                        if (booleanValue) {
                            this.wifiManager.setWifiEnabled(true);
                        }
                        if (TextUtils.isEmpty("")) {
                            return;
                        }
                        Pdlog.m3274e(TAG, "closeWifiAp error_msg = ");
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        String illegalArgumentException = e.toString();
                        if (TextUtils.isEmpty(illegalArgumentException)) {
                            return;
                        }
                        Pdlog.m3274e(TAG, "closeWifiAp error_msg = " + illegalArgumentException);
                    } catch (InvocationTargetException e2) {
                        e2.printStackTrace();
                        String invocationTargetException = e2.toString();
                        if (TextUtils.isEmpty(invocationTargetException)) {
                            return;
                        }
                        Pdlog.m3274e(TAG, "closeWifiAp error_msg = " + invocationTargetException);
                    }
                } catch (IllegalAccessException e3) {
                    e3.printStackTrace();
                    String illegalAccessException = e3.toString();
                    if (TextUtils.isEmpty(illegalAccessException)) {
                        return;
                    }
                    Pdlog.m3274e(TAG, "closeWifiAp error_msg = " + illegalAccessException);
                } catch (NoSuchMethodException e4) {
                    e4.printStackTrace();
                    String noSuchMethodException = e4.toString();
                    if (TextUtils.isEmpty(noSuchMethodException)) {
                        return;
                    }
                    Pdlog.m3274e(TAG, "closeWifiAp error_msg = " + noSuchMethodException);
                }
            } catch (Throwable th) {
                if (!TextUtils.isEmpty("")) {
                    Pdlog.m3274e(TAG, "closeWifiAp error_msg = ");
                }
                throw th;
            }
        }
    }
}
