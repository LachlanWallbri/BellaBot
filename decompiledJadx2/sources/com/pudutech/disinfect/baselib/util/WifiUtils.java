package com.pudutech.disinfect.baselib.util;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class WifiUtils {
    private static WifiUtils utils;
    private WifiManager wifiManager;

    public WifiUtils(Context context) {
        this.wifiManager = (WifiManager) context.getSystemService("wifi");
    }

    public static WifiUtils getInstance(Context context) {
        if (utils == null) {
            synchronized (WifiUtils.class) {
                if (utils == null) {
                    utils = new WifiUtils(context);
                }
            }
        }
        return utils;
    }

    public boolean isWifiEnable() {
        WifiManager wifiManager = this.wifiManager;
        return wifiManager != null && wifiManager.isWifiEnabled();
    }

    public void openWifi() {
        if (this.wifiManager == null || isWifiEnable()) {
            return;
        }
        this.wifiManager.setWifiEnabled(true);
    }

    public void closeWifi() {
        if (this.wifiManager == null || !isWifiEnable()) {
            return;
        }
        this.wifiManager.setWifiEnabled(false);
    }

    public List<ScanResult> getWifiList() {
        ArrayList arrayList = new ArrayList();
        if (this.wifiManager != null && isWifiEnable()) {
            arrayList.addAll(this.wifiManager.getScanResults());
        }
        return arrayList;
    }

    public List<ScanResult> getScanList() {
        WifiManager wifiManager = this.wifiManager;
        if (wifiManager == null) {
            return null;
        }
        wifiManager.startScan();
        List<ScanResult> scanResults = this.wifiManager.getScanResults();
        if (scanResults == null) {
            return null;
        }
        List<ScanResult> arrayList = new ArrayList<>();
        for (int i = 0; i < scanResults.size(); i++) {
            int itemPosition = getItemPosition(arrayList, scanResults.get(i));
            if (itemPosition != -1) {
                if (arrayList.get(itemPosition).level < scanResults.get(i).level) {
                    arrayList.remove(itemPosition);
                    arrayList.add(itemPosition, scanResults.get(i));
                }
            } else {
                arrayList.add(scanResults.get(i));
            }
        }
        return arrayList;
    }

    private int getItemPosition(List<ScanResult> list, ScanResult scanResult) {
        for (int i = 0; i < list.size(); i++) {
            if (scanResult.SSID.equals(list.get(i).SSID)) {
                return i;
            }
        }
        return -1;
    }

    public void connectWifiPws(String str, String str2) {
        WifiManager wifiManager = this.wifiManager;
        wifiManager.disableNetwork(wifiManager.getConnectionInfo().getNetworkId());
        this.wifiManager.enableNetwork(this.wifiManager.addNetwork(getWifiConfig(str, str2, true)), true);
        this.wifiManager.reconnect();
    }

    public void connectWifiNoPws(String str) {
        WifiManager wifiManager = this.wifiManager;
        wifiManager.disableNetwork(wifiManager.getConnectionInfo().getNetworkId());
        this.wifiManager.enableNetwork(this.wifiManager.addNetwork(getWifiConfig(str, "", false)), true);
        this.wifiManager.reconnect();
    }

    public WifiInfo getConnectWifiSsid() {
        return this.wifiManager.getConnectionInfo();
    }

    private WifiConfiguration getWifiConfig(String str, String str2, boolean z) {
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.allowedAuthAlgorithms.clear();
        wifiConfiguration.allowedGroupCiphers.clear();
        wifiConfiguration.allowedKeyManagement.clear();
        wifiConfiguration.allowedPairwiseCiphers.clear();
        wifiConfiguration.allowedProtocols.clear();
        wifiConfiguration.SSID = "\"" + str + "\"";
        WifiConfiguration isExist = isExist(str);
        if (isExist != null) {
            this.wifiManager.removeNetwork(isExist.networkId);
        }
        if (z) {
            wifiConfiguration.preSharedKey = "\"" + str2 + "\"";
            wifiConfiguration.hiddenSSID = true;
            wifiConfiguration.allowedAuthAlgorithms.set(0);
            wifiConfiguration.allowedGroupCiphers.set(2);
            wifiConfiguration.allowedKeyManagement.set(1);
            wifiConfiguration.allowedPairwiseCiphers.set(1);
            wifiConfiguration.allowedGroupCiphers.set(3);
            wifiConfiguration.allowedPairwiseCiphers.set(2);
            wifiConfiguration.status = 2;
        } else {
            wifiConfiguration.allowedKeyManagement.set(0);
        }
        return wifiConfiguration;
    }

    private WifiConfiguration isExist(String str) {
        for (WifiConfiguration wifiConfiguration : this.wifiManager.getConfiguredNetworks()) {
            if (wifiConfiguration.SSID.equals("\"" + str + "\"")) {
                return wifiConfiguration;
            }
        }
        return null;
    }
}
