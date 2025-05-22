package com.pudutech.location.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import com.iflytek.cloud.msc.util.NetworkUtil;
import com.pudutech.base.Pdlog;
import java.lang.reflect.Method;

/* loaded from: classes5.dex */
public class NetStatusUtil {
    public static final int CMNET = 6;
    public static final int CMWAP = 5;
    public static final int CTNET = 8;
    public static final int CTWAP = 7;
    public static final int LTE = 10;
    public static final int MOBILE = 9;
    public static final int NET_3G = 4;
    public static final int NON_NETWORK = -1;
    private static final String TAG = "NetStatusUtil";
    public static final int UNINET = 1;
    public static final int UNIWAP = 2;
    public static final int WAP_3G = 3;
    public static final int WIFI = 0;

    public static boolean isWifi(int i) {
        return i == 0;
    }

    public static void enableAggressiveHandover(Context context, int i) {
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        try {
            Method method = Class.forName("android.net.wifi.WifiManager").getMethod("enableAggressiveHandover", Integer.TYPE);
            method.setAccessible(true);
            method.invoke(wifiManager, Integer.valueOf(i));
            Pdlog.m3273d(TAG, "enableAggressiveHandover = " + i);
        } catch (Exception e) {
            e.printStackTrace();
            Pdlog.m3273d(TAG, "enableAggressiveHandover = " + e.toString());
        }
    }

    public static WifiInfo getWifiInfo(Context context) {
        NetworkInfo activeNetworkInfo;
        WifiManager wifiManager;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || 1 != activeNetworkInfo.getType() || (wifiManager = (WifiManager) context.getSystemService("wifi")) == null) {
                return null;
            }
            return wifiManager.getConnectionInfo();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isConnected(Context context) {
        try {
            if (((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo().isConnected()) {
                return isWifi(context);
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isWifi(Context context) {
        int netType = getNetType(context);
        Log.d(TAG, "isWifi = " + netType);
        return isWifi(netType);
    }

    public static int getNetType(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
            return -1;
        }
        if (activeNetworkInfo.getType() == 1) {
            return 0;
        }
        if (activeNetworkInfo.getExtraInfo() == null) {
            return 9;
        }
        if (activeNetworkInfo.getExtraInfo().equalsIgnoreCase("uninet")) {
            return 1;
        }
        if (activeNetworkInfo.getExtraInfo().equalsIgnoreCase(NetworkUtil.NET_UNIWAP)) {
            return 2;
        }
        if (activeNetworkInfo.getExtraInfo().equalsIgnoreCase("3gwap")) {
            return 3;
        }
        if (activeNetworkInfo.getExtraInfo().equalsIgnoreCase("3gnet")) {
            return 4;
        }
        if (activeNetworkInfo.getExtraInfo().equalsIgnoreCase(NetworkUtil.NET_CMWAP)) {
            return 5;
        }
        if (activeNetworkInfo.getExtraInfo().equalsIgnoreCase("cmnet")) {
            return 6;
        }
        if (activeNetworkInfo.getExtraInfo().equalsIgnoreCase(NetworkUtil.NET_CTWAP)) {
            return 7;
        }
        if (activeNetworkInfo.getExtraInfo().equalsIgnoreCase(NetworkUtil.NET_CTNET)) {
            return 8;
        }
        return activeNetworkInfo.getExtraInfo().equalsIgnoreCase("LTE") ? 10 : 9;
    }
}
