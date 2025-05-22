package com.aliyun.alink.linksdk.tmp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;
import com.aliyun.alink.linksdk.tools.ALog;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class WifiManagerUtil {
    public static int NO_PASSWORD_WIFI = 0;
    private static final String TAG = "WifiManagerUtil";
    public static int WEP_CIPHER_WIFI = 1;
    public static final int WIFI_AP_STATE_DISABLED = 11;
    public static final int WIFI_AP_STATE_DISABLING = 10;
    public static final int WIFI_AP_STATE_ENABLED = 13;
    public static final int WIFI_AP_STATE_ENABLING = 12;
    public static final int WIFI_AP_STATE_FAILED = 14;
    public static int WPA_CIPHER_WIFI = 2;
    private ConnectivityManager connectivityManager;
    private Context context;
    private WifiInfo currWifiInfo;
    private WifiManager.MulticastLock multicastLock;
    private WifiManager.WifiLock wifiLock;
    public WifiManager wifiManager;
    public final String ALINK_SOFT_AP_GATEWAY = "172.31.254.250";
    public final String ALINK_SOFT_AP_STATIC_IP = "172.31.254.153";
    public final String ALINK_SOFT_AP_DNS = "192.192.192.192";
    private List<ScanResult> scanResultList = new LinkedList();
    private List<WifiConfiguration> wifiConfigedList = new LinkedList();

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public enum NetworkType {
        WLAN,
        ETHERNET
    }

    public boolean is24GHz(int i) {
        return i > 2400 && i < 2500;
    }

    public boolean is5GHz(int i) {
        return i > 4900 && i < 5900;
    }

    public WifiManagerUtil(Context context) {
        this.context = context;
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        this.wifiManager = wifiManager;
        this.currWifiInfo = wifiManager.getConnectionInfo();
        this.wifiLock = this.wifiManager.createWifiLock("Test");
        this.multicastLock = this.wifiManager.createMulticastLock("Alink");
        this.connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
    }

    public WifiInfo getCurrWifiInfo() {
        return this.currWifiInfo;
    }

    public DhcpInfo getRouterDhcp() {
        return ((WifiManager) this.context.getSystemService("wifi")).getDhcpInfo();
    }

    public static void startScanWifiList(Context context) {
        ALog.m479d(TAG, "startScanWifiList()");
        ALog.m479d(TAG, "startScanWifiList()," + ((WifiManager) context.getSystemService("wifi")).startScan());
    }

    public void openWifi() {
        if (isWifiAvaiable().booleanValue()) {
            this.wifiManager.setWifiEnabled(true);
        }
    }

    public void updateWifi() {
        ALog.m479d(TAG, "updateWifi()");
        this.currWifiInfo = this.wifiManager.getConnectionInfo();
    }

    public void updateConfigedWifi() {
        ALog.m479d(TAG, "updateConfigedWifi()");
        try {
            if (this.wifiManager.getConfiguredNetworks() != null) {
                this.wifiConfigedList.clear();
                this.wifiConfigedList.addAll(this.wifiManager.getConfiguredNetworks());
            }
        } catch (Exception e) {
            ALog.m479d(TAG, "updateConfigedWifi(),error," + e);
            e.printStackTrace();
        }
    }

    public void enableWifiBySsid(String str) {
        try {
            ALog.m484w(TAG, "enableWifiBySsid ssid=" + str);
            if (this.wifiConfigedList == null) {
                return;
            }
            for (int i = 0; i < this.wifiConfigedList.size(); i++) {
                WifiConfiguration wifiConfiguration = this.wifiConfigedList.get(i);
                if (wifiConfiguration != null && wifiConfiguration.SSID != null) {
                    if (wifiConfiguration.SSID.equals("\"" + str + "\"")) {
                        this.wifiManager.disconnect();
                        this.wifiManager.enableNetwork(wifiConfiguration.networkId, true);
                        this.wifiManager.reconnect();
                    }
                }
            }
        } catch (Exception e) {
            ALog.m484w(TAG, "enableWifiBySsid e=" + e);
        }
    }

    public void closeWifi() {
        if (isWifiAvaiable().booleanValue()) {
            this.wifiManager.setWifiEnabled(false);
        }
    }

    public WifiConfiguration createWifiConfiguration(String str, String str2, int i, boolean z) {
        WifiConfiguration isWifiExist;
        ALog.m479d(TAG, "createWifiConfiguration(),SSID = " + str + "## Password = " + str2 + "## Type = " + i + ",isHotSpot=" + z);
        if (i == NO_PASSWORD_WIFI) {
            isWifiExist = isOpenWifiExist(str);
        } else {
            isWifiExist = isWifiExist(str);
        }
        if (isWifiExist != null) {
            removeWifi(isWifiExist.networkId);
        }
        if (isWifiExist == null) {
            isWifiExist = new WifiConfiguration();
        }
        isWifiExist.allowedAuthAlgorithms.clear();
        isWifiExist.allowedGroupCiphers.clear();
        isWifiExist.allowedKeyManagement.clear();
        isWifiExist.allowedPairwiseCiphers.clear();
        isWifiExist.allowedProtocols.clear();
        isWifiExist.SSID = str;
        if (i == NO_PASSWORD_WIFI) {
            isWifiExist.allowedKeyManagement.set(0);
        } else if (i == WEP_CIPHER_WIFI) {
            isWifiExist.wepKeys[0] = str2;
            isWifiExist.allowedAuthAlgorithms.set(1);
            isWifiExist.allowedGroupCiphers.set(3);
            isWifiExist.allowedGroupCiphers.set(2);
            isWifiExist.allowedGroupCiphers.set(0);
            isWifiExist.allowedGroupCiphers.set(1);
            isWifiExist.allowedKeyManagement.set(0);
            isWifiExist.wepTxKeyIndex = 0;
        } else if (i == WPA_CIPHER_WIFI) {
            isWifiExist.preSharedKey = str2;
            isWifiExist.hiddenSSID = false;
            isWifiExist.allowedAuthAlgorithms.set(0);
            isWifiExist.allowedGroupCiphers.set(2);
            isWifiExist.allowedKeyManagement.set(1);
            isWifiExist.allowedPairwiseCiphers.set(1);
            isWifiExist.allowedGroupCiphers.set(3);
            isWifiExist.allowedPairwiseCiphers.set(2);
            isWifiExist.status = 2;
        }
        try {
            setStaticIp(isWifiExist);
        } catch (Exception e) {
            ALog.m479d(TAG, "createWifiConfiguration(), setStaticIP error, e=" + e);
        }
        return isWifiExist;
    }

    private void setStaticIp(WifiConfiguration wifiConfiguration) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, IllegalArgumentException, NoSuchMethodException, InstantiationException, InvocationTargetException, UnknownHostException {
        setEnumField(wifiConfiguration, "STATIC", "ipAssignment");
        Object field = getField(wifiConfiguration, "linkProperties");
        if (field == null) {
            return;
        }
        Object newInstance = Class.forName("android.net.LinkAddress").getConstructor(InetAddress.class, Integer.TYPE).newInstance(InetAddress.getByName("172.31.254.153"), 24);
        ArrayList arrayList = (ArrayList) getDeclaredField(field, "mLinkAddresses");
        arrayList.clear();
        arrayList.add(newInstance);
        if (Build.VERSION.SDK_INT >= 14) {
            Object newInstance2 = Class.forName("android.net.RouteInfo").getConstructor(InetAddress.class).newInstance(InetAddress.getByName("172.31.254.250"));
            ArrayList arrayList2 = (ArrayList) getDeclaredField(field, "mRoutes");
            arrayList2.clear();
            arrayList2.add(newInstance2);
        } else {
            ArrayList arrayList3 = (ArrayList) getDeclaredField(field, "mGateways");
            arrayList3.clear();
            arrayList3.add(InetAddress.getByName("172.31.254.250"));
        }
        ArrayList arrayList4 = (ArrayList) getDeclaredField(field, "mDnses");
        arrayList4.clear();
        arrayList4.add(InetAddress.getByName("192.192.192.192"));
    }

    private Object getField(Object obj, String str) throws NoSuchFieldException, IllegalAccessException, IllegalArgumentException {
        return obj.getClass().getField(str).get(obj);
    }

    private static Object getDeclaredField(Object obj, String str) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field declaredField = obj.getClass().getDeclaredField(str);
        declaredField.setAccessible(true);
        return declaredField.get(obj);
    }

    private static void setEnumField(Object obj, String str, String str2) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field field = obj.getClass().getField(str2);
        field.set(obj, Enum.valueOf(field.getType(), str));
    }

    public void addWifi(WifiConfiguration wifiConfiguration) {
        ALog.m479d(TAG, "addWifi()");
        if (wifiConfiguration == null) {
            ALog.m479d(TAG, "addwifi(),config is null");
            return;
        }
        int i = wifiConfiguration.networkId;
        if (i == -1) {
            ALog.m479d(TAG, "addWifi(), addNetwork..");
            i = this.wifiManager.addNetwork(wifiConfiguration);
        }
        ALog.m479d(TAG, "addWifi(),netId = " + i);
        if (i == -1) {
            return;
        }
        try {
            disconnectAllConfiguredWifi();
            ALog.m479d(TAG, "addWifi(),enable = " + this.wifiManager.enableNetwork(i, true) + " reconnect = " + this.wifiManager.reconnect());
        } catch (Exception e) {
            ALog.m481e(TAG, "addWifi(),error,", e);
        }
    }

    public void removeWifi(int i) {
        this.wifiManager.removeNetwork(i);
    }

    private void disconnectAllConfiguredWifi() {
        try {
            ALog.m484w(TAG, "disconnectAllConfiguredWifi");
            for (int i = 0; i < this.wifiConfigedList.size(); i++) {
                if (this.wifiConfigedList.get(i) != null) {
                    disconnectWifi(this.wifiConfigedList.get(i).networkId);
                }
            }
        } catch (Exception e) {
            ALog.m484w(TAG, "disconnectAllConfiguredWifi e=" + e);
        }
    }

    public void disconnectWifi(int i) {
        this.wifiManager.disableNetwork(i);
        this.wifiManager.disconnect();
    }

    public NetworkInfo getCurrentNetInfo() {
        ALog.m479d(TAG, "getCurrentNetInfo(),call");
        if (this.connectivityManager == null) {
            return null;
        }
        ALog.m479d(TAG, "getCurrentNetInfo(),connectivityManager != null");
        return this.connectivityManager.getNetworkInfo(1);
    }

    public Boolean isAPNetworkReady(String str) {
        ALog.m479d(TAG, "isAPNetworkReady(),ssid" + str);
        updateWifi();
        NetworkInfo currentNetInfo = getCurrentNetInfo();
        if (currentNetInfo == null) {
            ALog.m479d(TAG, "isAPNetworkReady(),false,info is empty");
            return false;
        }
        if (currentNetInfo.getType() != 1) {
            ALog.m479d(TAG, "isAPNetworkReady: false,is not wifi");
            return false;
        }
        ALog.m479d(TAG, "isAPNetworkReady: State = " + currentNetInfo.getState() + ", detailState=" + currentNetInfo.getDetailedState().toString());
        if (!currentNetInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
            ALog.m479d(TAG, "isAPNetworkReady(),false,state=" + currentNetInfo.getState());
            return false;
        }
        if (!this.currWifiInfo.getSSID().replace("\"", "").equals(str)) {
            ALog.m479d(TAG, "isAPNetworkReady(),false,cur ssid=" + this.currWifiInfo.getSSID());
            return false;
        }
        return isWifiAvaiable();
    }

    public Boolean isCurrWifiOk() {
        updateWifi();
        NetworkInfo currentNetInfo = getCurrentNetInfo();
        if (currentNetInfo != null && currentNetInfo.getType() == 1 && currentNetInfo.getState().equals(NetworkInfo.State.CONNECTED) && isWifiAvaiable().booleanValue()) {
            return true;
        }
        return false;
    }

    public WifiConfiguration isOpenWifiExist(String str) {
        ALog.m479d(TAG, "isOpenWifiExist,ssid=" + str);
        updateConfigedWifi();
        for (int i = 0; i < this.wifiConfigedList.size(); i++) {
            WifiConfiguration wifiConfiguration = this.wifiConfigedList.get(i);
            if (wifiConfiguration != null && !TextUtils.isEmpty(wifiConfiguration.SSID)) {
                if (wifiConfiguration.SSID.equals("\"" + str + "\"") && wifiConfiguration.allowedKeyManagement.get(0)) {
                    ALog.m479d(TAG, "isOpenWifiExist(),found config");
                    return this.wifiConfigedList.get(i);
                }
            }
        }
        return null;
    }

    public void logWifiConfig() {
        ALog.m479d(TAG, "logWifiConfig()");
        updateConfigedWifi();
        for (int i = 0; i < this.wifiConfigedList.size(); i++) {
            WifiConfiguration wifiConfiguration = this.wifiConfigedList.get(i);
            ALog.m479d(TAG, "logWifiConfig(),networkId=" + wifiConfiguration.networkId + ",ssid=" + wifiConfiguration.SSID + ",config=" + wifiConfiguration.toString());
        }
    }

    public WifiConfiguration isWifiExist(String str) {
        ALog.m479d(TAG, "isWifiExist");
        updateConfigedWifi();
        for (int i = 0; i < this.wifiConfigedList.size(); i++) {
            WifiConfiguration wifiConfiguration = this.wifiConfigedList.get(i);
            if (wifiConfiguration != null && !TextUtils.isEmpty(wifiConfiguration.SSID)) {
                if (wifiConfiguration.SSID.equals("\"" + str + "\"")) {
                    ALog.m479d(TAG, "isWifiExist(),found config");
                    return wifiConfiguration;
                }
            }
        }
        return null;
    }

    public List<ScanResult> startScanWifi() {
        ALog.m479d(TAG, "startScanWifi()");
        ALog.m479d(TAG, "startScanWifi()," + this.wifiManager.startScan());
        List<ScanResult> scanResults = this.wifiManager.getScanResults();
        this.scanResultList = scanResults;
        return scanResults;
    }

    public List<WifiConfiguration> getWifiConfiged() {
        List<WifiConfiguration> configuredNetworks = this.wifiManager.getConfiguredNetworks();
        this.wifiConfigedList = configuredNetworks;
        return configuredNetworks;
    }

    public WifiConfiguration getCurWifiConfig(String str) {
        ALog.m479d(TAG, "getCurWifiConfig(),ssid=" + str);
        updateConfigedWifi();
        for (int i = 0; i < this.wifiConfigedList.size(); i++) {
            WifiConfiguration wifiConfiguration = this.wifiConfigedList.get(i);
            if (wifiConfiguration != null && !TextUtils.isEmpty(wifiConfiguration.SSID)) {
                if (wifiConfiguration.SSID.equals("\"" + str + "\"") && wifiConfiguration.status == 0) {
                    ALog.m479d(TAG, "getCurWifiConfig(),succ。  networkId=" + wifiConfiguration.networkId + ",ssid=" + wifiConfiguration.SSID + ",config=" + wifiConfiguration.toString());
                    return wifiConfiguration;
                }
            }
        }
        return null;
    }

    public Boolean isWifiAvaiable() {
        ALog.m479d(TAG, "isWifiAvaiable");
        WifiManager wifiManager = this.wifiManager;
        if (wifiManager == null) {
            ALog.m479d(TAG, "isWifiAvaiable,wifiManager is null");
            return false;
        }
        boolean isWifiEnabled = wifiManager.isWifiEnabled();
        ALog.m479d(TAG, "isWifiAvaiable,enable = " + isWifiEnabled);
        return Boolean.valueOf(isWifiEnabled);
    }

    public void acquireWifiLock() {
        this.wifiLock.acquire();
    }

    public void releaseWifiLock() {
        if (this.wifiLock.isHeld()) {
            this.wifiLock.acquire();
        }
    }

    public void acquireMulticastLock() {
        try {
            this.multicastLock.acquire();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void releaseMulticastLock() {
        try {
            if (this.multicastLock.isHeld()) {
                this.multicastLock.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean setWifiApEnabled(WifiConfiguration wifiConfiguration, boolean z) {
        if (this.wifiManager == null) {
            ALog.m479d(TAG, "setWifiApEnabled(), wifiManager is null");
            return false;
        }
        if (isHTC()) {
            ALog.m479d(TAG, "setWifiApEnabled(), isSucc = " + setWifiApConfigurationForHTC(wifiConfiguration));
        }
        if (z) {
            try {
                this.wifiManager.setWifiEnabled(false);
            } catch (Exception e) {
                ALog.m479d(TAG, " setWifiApEnabled(), e = " + e);
                e.printStackTrace();
                return false;
            }
        }
        return ((Boolean) this.wifiManager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, Boolean.TYPE).invoke(this.wifiManager, wifiConfiguration, Boolean.valueOf(z))).booleanValue();
    }

    public int getWifiApState() {
        try {
            return ((Integer) this.wifiManager.getClass().getMethod("getWifiApState", new Class[0]).invoke(this.wifiManager, new Object[0])).intValue();
        } catch (Exception e) {
            ALog.m479d(TAG, "getWifiApState(), error = " + e.toString());
            return 14;
        }
    }

    public boolean isWifiApEnabled() {
        return getWifiApState() == 13;
    }

    public void closeWiFiAP() {
        if (this.wifiManager == null) {
            ALog.m479d(TAG, "closeWiFiAP(), wifi manager == null");
            return;
        }
        if (isWifiApEnabled()) {
            try {
                Method method = this.wifiManager.getClass().getMethod("getWifiApConfiguration", new Class[0]);
                method.setAccessible(true);
                this.wifiManager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, Boolean.TYPE).invoke(this.wifiManager, (WifiConfiguration) method.invoke(this.wifiManager, new Object[0]), false);
                this.wifiManager.setWifiEnabled(true);
            } catch (Exception e) {
                ALog.m479d(TAG, "closeWiFiAP(), error,e= " + e.toString());
            }
        }
    }

    public boolean isHTC() {
        try {
            String str = Build.MANUFACTURER;
            ALog.m479d(TAG, "isHTC(), manu=" + str);
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            return str.trim().toLowerCase().contains("htc");
        } catch (Exception e) {
            ALog.m479d(TAG, "isHTC(),error+" + e);
            return false;
        }
    }

    public boolean setWifiApConfigurationForHTC(WifiConfiguration wifiConfiguration) {
        ALog.m479d(TAG, "setWifiApConfigurationForHTC, call, apConfig = " + wifiConfiguration.toString());
        try {
            Field declaredField = WifiConfiguration.class.getDeclaredField("mWifiApProfile");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(wifiConfiguration);
            declaredField.setAccessible(false);
            if (obj != null) {
                Field declaredField2 = obj.getClass().getDeclaredField("SSID");
                declaredField2.setAccessible(true);
                declaredField2.set(obj, wifiConfiguration.SSID);
                declaredField2.setAccessible(false);
                Field declaredField3 = obj.getClass().getDeclaredField("BSSID");
                declaredField3.setAccessible(true);
                declaredField3.set(obj, wifiConfiguration.BSSID);
                declaredField3.setAccessible(false);
                Field declaredField4 = obj.getClass().getDeclaredField("dhcpEnable");
                declaredField4.setAccessible(true);
                declaredField4.setInt(obj, 1);
                declaredField4.setAccessible(false);
                Field declaredField5 = obj.getClass().getDeclaredField(TransferTable.COLUMN_KEY);
                declaredField5.setAccessible(true);
                declaredField5.set(obj, wifiConfiguration.preSharedKey);
                declaredField5.setAccessible(false);
            }
            return true;
        } catch (Exception e) {
            ALog.m479d(TAG, "setWifiApConfigurationForHTC,error, e = " + e);
            e.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0028, code lost:
    
        r7 = r6.nextElement();
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0032, code lost:
    
        if (r7.isLoopbackAddress() != false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0036, code lost:
    
        if ((r7 instanceof java.net.Inet4Address) == false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0040, code lost:
    
        if (r5.getDisplayName().contains("wlan0") == false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0044, code lost:
    
        if (r10 != com.aliyun.alink.linksdk.tmp.utils.WifiManagerUtil.NetworkType.WLAN) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0046, code lost:
    
        return r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004f, code lost:
    
        if (r5.getDisplayName().contains("eth0") == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0053, code lost:
    
        if (r10 != com.aliyun.alink.linksdk.tmp.utils.WifiManagerUtil.NetworkType.ETHERNET) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0055, code lost:
    
        return r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x005e, code lost:
    
        if (r5.getDisplayName().contains("wlan0") != false) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0068, code lost:
    
        if (r5.getDisplayName().contains("eth0") != false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0074, code lost:
    
        if (r5.getDisplayName().contains("ap0") == false) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0076, code lost:
    
        r3 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0010, code lost:
    
        if (r4 != null) goto L5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0016, code lost:
    
        if (r4.hasMoreElements() == false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0018, code lost:
    
        r5 = r4.nextElement();
        r6 = r5.getInetAddresses();
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0026, code lost:
    
        if (r6.hasMoreElements() == false) goto L39;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static InetAddress getIpAddress(NetworkType networkType) {
        ALog.m479d(TAG, "getIpAddress()");
        InetAddress inetAddress = null;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (Exception e) {
            ALog.m479d(TAG, e.toString());
        }
        return inetAddress;
    }

    public static InetAddress getBroadcast(InetAddress inetAddress) {
        List<InterfaceAddress> interfaceAddresses;
        if (inetAddress == null) {
            return null;
        }
        ALog.m479d(TAG, "getBroadcast(),inetAddr = " + inetAddress);
        try {
            NetworkInterface byInetAddress = NetworkInterface.getByInetAddress(inetAddress);
            if (byInetAddress != null && (interfaceAddresses = byInetAddress.getInterfaceAddresses()) != null && !interfaceAddresses.isEmpty()) {
                InetAddress inetAddress2 = null;
                for (InterfaceAddress interfaceAddress : interfaceAddresses) {
                    if (interfaceAddress.getAddress() instanceof Inet4Address) {
                        inetAddress2 = interfaceAddress.getBroadcast();
                    }
                }
                ALog.m479d(TAG, "iAddr=" + inetAddress2);
                return inetAddress2;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            ALog.m479d(TAG, "getBroadcast" + e.getMessage());
            return null;
        }
    }

    public String getWifiType() {
        int frequency;
        if (Build.VERSION.SDK_INT < 21) {
            String ssid = this.currWifiInfo.getSSID();
            if (ssid != null && ssid.length() > 2) {
                String substring = ssid.substring(1, ssid.length() - 1);
                for (ScanResult scanResult : this.wifiManager.getScanResults()) {
                    if (scanResult.SSID.equals(substring)) {
                        frequency = scanResult.frequency;
                        break;
                    }
                }
            }
            frequency = 0;
        } else {
            frequency = this.currWifiInfo.getFrequency();
        }
        if (is5GHz(frequency)) {
            return WiFiFreqType.WIFI_5G.value();
        }
        return WiFiFreqType.WIFI_2_4G.value();
    }

    public int getWifiRssid() {
        return this.currWifiInfo.getRssi();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public enum WiFiFreqType {
        WIFI_5G("5GHZ"),
        WIFI_2_4G("2.4GHZ");

        private String name;

        WiFiFreqType(String str) {
            this.name = str;
        }

        public String value() {
            return this.name;
        }
    }
}
