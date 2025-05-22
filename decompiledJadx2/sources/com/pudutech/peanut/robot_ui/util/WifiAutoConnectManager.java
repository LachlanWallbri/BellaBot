package com.pudutech.peanut.robot_ui.util;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Log;

/* loaded from: classes5.dex */
public class WifiAutoConnectManager {
    private static final String TAG = "WifiAutoConnectManager";
    WifiManager wifiManager;

    /* loaded from: classes5.dex */
    public enum WifiCipherType {
        WIFICIPHER_WEP,
        WIFICIPHER_WPA,
        WIFICIPHER_NOPASS,
        WIFICIPHER_INVALID
    }

    public WifiAutoConnectManager(WifiManager wifiManager) {
        this.wifiManager = wifiManager;
    }

    public void connect(String str, String str2, WifiCipherType wifiCipherType) {
        new Thread(new ConnectRunnable(str, str2, wifiCipherType)).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WifiConfiguration isExsits(String str) {
        for (WifiConfiguration wifiConfiguration : this.wifiManager.getConfiguredNetworks()) {
            if (wifiConfiguration.SSID.equals("\"" + str + "\"")) {
                return wifiConfiguration;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WifiConfiguration createWifiInfo(String str, String str2, WifiCipherType wifiCipherType) {
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.allowedAuthAlgorithms.clear();
        wifiConfiguration.allowedGroupCiphers.clear();
        wifiConfiguration.allowedKeyManagement.clear();
        wifiConfiguration.allowedPairwiseCiphers.clear();
        wifiConfiguration.allowedProtocols.clear();
        wifiConfiguration.SSID = "\"" + str + "\"";
        if (wifiCipherType == WifiCipherType.WIFICIPHER_NOPASS) {
            wifiConfiguration.allowedKeyManagement.set(0);
        }
        if (wifiCipherType == WifiCipherType.WIFICIPHER_WEP) {
            if (!TextUtils.isEmpty(str2)) {
                if (isHexWepKey(str2)) {
                    wifiConfiguration.wepKeys[0] = str2;
                } else {
                    wifiConfiguration.wepKeys[0] = "\"" + str2 + "\"";
                }
            }
            wifiConfiguration.allowedAuthAlgorithms.set(0);
            wifiConfiguration.allowedAuthAlgorithms.set(1);
            wifiConfiguration.allowedKeyManagement.set(0);
            wifiConfiguration.wepTxKeyIndex = 0;
        }
        if (wifiCipherType == WifiCipherType.WIFICIPHER_WPA) {
            wifiConfiguration.preSharedKey = "\"" + str2 + "\"";
            wifiConfiguration.hiddenSSID = true;
            wifiConfiguration.allowedAuthAlgorithms.set(0);
            wifiConfiguration.allowedGroupCiphers.set(2);
            wifiConfiguration.allowedKeyManagement.set(1);
            wifiConfiguration.allowedPairwiseCiphers.set(1);
            wifiConfiguration.allowedGroupCiphers.set(3);
            wifiConfiguration.allowedPairwiseCiphers.set(2);
            wifiConfiguration.status = 2;
        }
        return wifiConfiguration;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean openWifi() {
        if (this.wifiManager.isWifiEnabled()) {
            return true;
        }
        return this.wifiManager.setWifiEnabled(true);
    }

    /* loaded from: classes5.dex */
    class ConnectRunnable implements Runnable {
        private String password;
        private String ssid;
        private WifiCipherType type;

        public ConnectRunnable(String str, String str2, WifiCipherType wifiCipherType) {
            this.ssid = str;
            this.password = str2;
            this.type = wifiCipherType;
        }

        @Override // java.lang.Runnable
        public void run() {
            WifiAutoConnectManager.this.openWifi();
            while (WifiAutoConnectManager.this.wifiManager.getWifiState() == 2) {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException unused) {
                }
            }
            WifiConfiguration createWifiInfo = WifiAutoConnectManager.this.createWifiInfo(this.ssid, this.password, this.type);
            if (createWifiInfo != null) {
                WifiConfiguration isExsits = WifiAutoConnectManager.this.isExsits(this.ssid);
                if (isExsits != null) {
                    WifiAutoConnectManager.this.wifiManager.removeNetwork(isExsits.networkId);
                }
                Log.d(WifiAutoConnectManager.TAG, "enableNetwork status enable=" + WifiAutoConnectManager.this.wifiManager.enableNetwork(WifiAutoConnectManager.this.wifiManager.addNetwork(createWifiInfo), true));
                Log.d(WifiAutoConnectManager.TAG, "enableNetwork connected=" + WifiAutoConnectManager.this.wifiManager.reconnect());
                return;
            }
            Log.d(WifiAutoConnectManager.TAG, "wifiConfig is null!");
        }
    }

    private static boolean isHexWepKey(String str) {
        int length = str.length();
        if (length == 10 || length == 26 || length == 58) {
            return isHex(str);
        }
        return false;
    }

    private static boolean isHex(String str) {
        for (int length = str.length() - 1; length >= 0; length--) {
            char charAt = str.charAt(length);
            if ((charAt < '0' || charAt > '9') && ((charAt < 'A' || charAt > 'F') && (charAt < 'a' || charAt > 'f'))) {
                return false;
            }
        }
        return true;
    }

    public static WifiCipherType getCipherType(Context context, String str) {
        for (ScanResult scanResult : ((WifiManager) context.getSystemService("wifi")).getScanResults()) {
            if (!TextUtils.isEmpty(scanResult.SSID) && scanResult.SSID.equals(str)) {
                String str2 = scanResult.capabilities;
                if (!TextUtils.isEmpty(str2)) {
                    if (str2.contains("WPA") || str2.contains("wpa")) {
                        Log.i("hefeng", "wpa");
                        return WifiCipherType.WIFICIPHER_WPA;
                    }
                    if (str2.contains("WEP") || str2.contains("wep")) {
                        Log.i("hefeng", "wep");
                        return WifiCipherType.WIFICIPHER_WEP;
                    }
                    Log.i("hefeng", "no");
                    return WifiCipherType.WIFICIPHER_NOPASS;
                }
            }
        }
        return WifiCipherType.WIFICIPHER_INVALID;
    }
}
