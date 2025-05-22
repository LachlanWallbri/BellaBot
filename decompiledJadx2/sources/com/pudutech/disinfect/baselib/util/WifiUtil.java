package com.pudutech.disinfect.baselib.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.disinfect.baselib.base.BaseApp;
import com.pudutech.disinfect.baselib.ext.util.SystemServiceExtKt;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: WifiUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0004J\u0016\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0004J\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015J\u001e\u0010\u0016\u001a\u00020\u00062\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u001a\u001a\u00020\u0019H\u0002J\u000e\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018J\n\u0010\u001f\u001a\u0004\u0018\u00010\u0004H\u0002J\"\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\tH\u0002J\u000e\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018J\u000e\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u0006J\u0012\u0010&\u001a\u0004\u0018\u00010!2\u0006\u0010\u0011\u001a\u00020\u0004H\u0003J\u000e\u0010'\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u0010(\u001a\u00020\tJ\u0006\u0010)\u001a\u00020\u000fJ\u000e\u0010)\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020\u001dJ\u000e\u0010+\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020\u001dR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u000b\u001a\u0004\u0018\u00010\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u00048F@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006,"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/util/WifiUtil;", "", "()V", "KEY_MAC", "", "PRODUCT_CODE", "", "TAG", "isOpening", "", "<set-?>", "mac", "getMac", "()Ljava/lang/String;", "closeWifi", "", "connectWifiNoPws", "ssid", "connectWifiPws", "pws", "getConnectWifiSsid", "Landroid/net/wifi/WifiInfo;", "getItemPosition", "list", "", "Landroid/net/wifi/ScanResult;", "item", "getLocalIpAddress", "context", "Landroid/content/Context;", "getScanList", "getWIFIMac", "getWifiConfig", "Landroid/net/wifi/WifiConfiguration;", "isHasPws", "getWifiList", "int2ip", "ipInt", "isExist", "isNetworkAvailable", "isWifiEnable", "openWifi", "appContext", "openWifiAsync", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class WifiUtil {
    public static final int PRODUCT_CODE = 62;
    private static boolean isOpening;
    private static String mac;
    public static final WifiUtil INSTANCE = new WifiUtil();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final String KEY_MAC = KEY_MAC;
    private static final String KEY_MAC = KEY_MAC;

    private WifiUtil() {
    }

    public final String getMac() {
        String str = mac;
        if (str != null) {
            return str;
        }
        String wIFIMac = getWIFIMac();
        if (wIFIMac != null) {
            SpUtils.set(BaseApp.INSTANCE.getINSTANCE(), KEY_MAC, wIFIMac);
        } else {
            wIFIMac = SpUtils.get(BaseApp.INSTANCE.getINSTANCE(), KEY_MAC, "");
        }
        Pdlog.m3277w(TAG, "should open wifi once since device boot");
        return wIFIMac;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getWIFIMac() {
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                NetworkInterface nif = (NetworkInterface) it.next();
                Intrinsics.checkExpressionValueIsNotNull(nif, "nif");
                if (StringsKt.equals(nif.getName(), "wlan0", true)) {
                    byte[] hardwareAddress = nif.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return null;
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte b : hardwareAddress) {
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        Object[] objArr = {Byte.valueOf(b)};
                        String format = String.format("%02X:", Arrays.copyOf(objArr, objArr.length));
                        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                        sb.append(format);
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public final void openWifiAsync(Context appContext) {
        Intrinsics.checkParameterIsNotNull(appContext, "appContext");
        Pdlog.m3275i(TAG, "opening wifi");
        final WifiManager wifiManger = SystemServiceExtKt.getWifiManger(appContext);
        if (wifiManger != null) {
            if (!wifiManger.isWifiEnabled()) {
                if (isOpening) {
                    return;
                }
                isOpening = true;
                new Thread(new Runnable() { // from class: com.pudutech.disinfect.baselib.util.WifiUtil$openWifiAsync$1$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        String wIFIMac;
                        String str;
                        String str2;
                        WifiManager wifiManager = wifiManger;
                        while (!wifiManager.isWifiEnabled()) {
                            WifiUtil wifiUtil = WifiUtil.INSTANCE;
                            str2 = WifiUtil.TAG;
                            Pdlog.m3277w(str2, "open wifi try again");
                            Thread.sleep(500L);
                            wifiManager.setWifiEnabled(true);
                        }
                        WifiUtil wifiUtil2 = WifiUtil.INSTANCE;
                        wIFIMac = WifiUtil.INSTANCE.getWIFIMac();
                        WifiUtil.mac = wIFIMac;
                        WifiUtil wifiUtil3 = WifiUtil.INSTANCE;
                        WifiUtil.isOpening = false;
                        WifiUtil wifiUtil4 = WifiUtil.INSTANCE;
                        str = WifiUtil.TAG;
                        Pdlog.m3275i(str, "open wifi done");
                    }
                }).start();
                return;
            }
            mac = INSTANCE.getWIFIMac();
            Pdlog.m3275i(TAG, "open wifi done");
        }
    }

    public final void openWifi(Context appContext) {
        Intrinsics.checkParameterIsNotNull(appContext, "appContext");
        Pdlog.m3275i(TAG, "opening wifi");
        WifiManager wifiManger = SystemServiceExtKt.getWifiManger(appContext);
        if (wifiManger != null) {
            int i = 0;
            while (!wifiManger.isWifiEnabled() && i <= 10) {
                i++;
                Pdlog.m3277w(TAG, "open wifi try again");
                Thread.sleep(50L);
                wifiManger.setWifiEnabled(true);
            }
        }
        mac = getWIFIMac();
        Pdlog.m3275i(TAG, "open wifi done");
    }

    public final String int2ip(int ipInt) {
        String str = (ipInt & 255) + "." + ((ipInt >> 8) & 255) + "." + ((ipInt >> 16) & 255) + "." + ((ipInt >> 24) & 255);
        Intrinsics.checkExpressionValueIsNotNull(str, "sb.toString()");
        return str;
    }

    public final String getLocalIpAddress(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        try {
            WifiManager wifiManger = SystemServiceExtKt.getWifiManger(context);
            if (wifiManger == null) {
                return "wifiManger_null";
            }
            WifiInfo wifiInfo = wifiManger.getConnectionInfo();
            Intrinsics.checkExpressionValueIsNotNull(wifiInfo, "wifiInfo");
            return INSTANCE.int2ip(wifiInfo.getIpAddress());
        } catch (Exception e) {
            return " 获取IP出错鸟!!!!请保证是WIFI,或者请重新打开网络!\n" + e.getMessage();
        }
    }

    public final boolean isNetworkAvailable(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("connectivity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            try {
                Intrinsics.throwNpe();
            } catch (Exception unused) {
                return false;
            }
        }
        return activeNetworkInfo.isConnected();
    }

    public final boolean isWifiEnable() {
        Context applicationContext = BaseApp.INSTANCE.getINSTANCE().getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "BaseApp.INSTANCE.applicationContext");
        WifiManager wifiManger = SystemServiceExtKt.getWifiManger(applicationContext);
        return wifiManger != null && wifiManger.isWifiEnabled();
    }

    public final void openWifi() {
        Context applicationContext = BaseApp.INSTANCE.getINSTANCE().getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "BaseApp.INSTANCE.applicationContext");
        WifiManager wifiManger = SystemServiceExtKt.getWifiManger(applicationContext);
        if (wifiManger == null || isWifiEnable()) {
            return;
        }
        wifiManger.setWifiEnabled(true);
    }

    public final void closeWifi() {
        Context applicationContext = BaseApp.INSTANCE.getINSTANCE().getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "BaseApp.INSTANCE.applicationContext");
        WifiManager wifiManger = SystemServiceExtKt.getWifiManger(applicationContext);
        if (wifiManger == null || !isWifiEnable()) {
            return;
        }
        wifiManger.setWifiEnabled(false);
    }

    public final List<ScanResult> getWifiList() {
        Context applicationContext = BaseApp.INSTANCE.getINSTANCE().getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "BaseApp.INSTANCE.applicationContext");
        WifiManager wifiManger = SystemServiceExtKt.getWifiManger(applicationContext);
        ArrayList arrayList = new ArrayList();
        if (wifiManger != null && isWifiEnable()) {
            List<ScanResult> scanResults = wifiManger.getScanResults();
            Intrinsics.checkExpressionValueIsNotNull(scanResults, "wifiManager.getScanResults()");
            arrayList.addAll(scanResults);
        }
        return arrayList;
    }

    public final List<ScanResult> getScanList() {
        Context applicationContext = BaseApp.INSTANCE.getINSTANCE().getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "BaseApp.INSTANCE.applicationContext");
        WifiManager wifiManger = SystemServiceExtKt.getWifiManger(applicationContext);
        if (wifiManger == null) {
            return null;
        }
        wifiManger.startScan();
        List<ScanResult> scanResults = wifiManger.getScanResults();
        Intrinsics.checkExpressionValueIsNotNull(scanResults, "wifiManager.getScanResults()");
        if (scanResults == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int size = scanResults.size();
        for (int i = 0; i < size; i++) {
            int itemPosition = getItemPosition(arrayList, scanResults.get(i));
            if (itemPosition != -1) {
                if (((ScanResult) arrayList.get(itemPosition)).level < scanResults.get(i).level) {
                    arrayList.remove(itemPosition);
                    arrayList.add(itemPosition, scanResults.get(i));
                }
            } else {
                arrayList.add(scanResults.get(i));
            }
        }
        return arrayList;
    }

    private final int getItemPosition(List<? extends ScanResult> list, ScanResult item) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (Intrinsics.areEqual(item.SSID, list.get(i).SSID)) {
                return i;
            }
        }
        return -1;
    }

    public final void connectWifiPws(String ssid, String pws) {
        Intrinsics.checkParameterIsNotNull(ssid, "ssid");
        Intrinsics.checkParameterIsNotNull(pws, "pws");
        Context applicationContext = BaseApp.INSTANCE.getINSTANCE().getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "BaseApp.INSTANCE.applicationContext");
        WifiManager wifiManger = SystemServiceExtKt.getWifiManger(applicationContext);
        if (wifiManger != null) {
            wifiManger.disableNetwork(wifiManger.getConnectionInfo().getNetworkId());
            wifiManger.enableNetwork(wifiManger.addNetwork(INSTANCE.getWifiConfig(ssid, pws, true)), true);
            wifiManger.reconnect();
        }
    }

    public final void connectWifiNoPws(String ssid) {
        Intrinsics.checkParameterIsNotNull(ssid, "ssid");
        Context applicationContext = BaseApp.INSTANCE.getINSTANCE().getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "BaseApp.INSTANCE.applicationContext");
        WifiManager wifiManger = SystemServiceExtKt.getWifiManger(applicationContext);
        if (wifiManger != null) {
            wifiManger.disableNetwork(wifiManger.getConnectionInfo().getNetworkId());
            wifiManger.enableNetwork(wifiManger.addNetwork(INSTANCE.getWifiConfig(ssid, "", false)), true);
            wifiManger.reconnect();
        }
    }

    public final WifiInfo getConnectWifiSsid() {
        Context applicationContext = BaseApp.INSTANCE.getINSTANCE().getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "BaseApp.INSTANCE.applicationContext");
        WifiManager wifiManger = SystemServiceExtKt.getWifiManger(applicationContext);
        if (wifiManger != null) {
            return wifiManger.getConnectionInfo();
        }
        return null;
    }

    private final WifiConfiguration getWifiConfig(String ssid, String pws, boolean isHasPws) {
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.allowedAuthAlgorithms.clear();
        wifiConfiguration.allowedGroupCiphers.clear();
        wifiConfiguration.allowedKeyManagement.clear();
        wifiConfiguration.allowedPairwiseCiphers.clear();
        wifiConfiguration.allowedProtocols.clear();
        wifiConfiguration.SSID = "\"" + ssid + "\"";
        WifiConfiguration isExist = isExist(ssid);
        if (isExist != null) {
            Object systemService = BaseApp.INSTANCE.getINSTANCE().getApplicationContext().getSystemService("wifi");
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.net.wifi.WifiManager");
            }
            ((WifiManager) systemService).removeNetwork(isExist.networkId);
        }
        if (isHasPws) {
            wifiConfiguration.preSharedKey = "\"" + pws + "\"";
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

    private final WifiConfiguration isExist(String ssid) {
        Context applicationContext = BaseApp.INSTANCE.getINSTANCE().getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "BaseApp.INSTANCE.applicationContext");
        WifiManager wifiManger = SystemServiceExtKt.getWifiManger(applicationContext);
        if (wifiManger == null) {
            return null;
        }
        List<WifiConfiguration> configuredNetworks = wifiManger.getConfiguredNetworks();
        Intrinsics.checkExpressionValueIsNotNull(configuredNetworks, "wifiManager.getConfiguredNetworks()");
        for (WifiConfiguration wifiConfiguration : configuredNetworks) {
            if (Intrinsics.areEqual(wifiConfiguration.SSID, "\"" + ssid + "\"")) {
                return wifiConfiguration;
            }
        }
        return null;
    }
}
