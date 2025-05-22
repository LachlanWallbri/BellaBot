package com.pudutech.bumblebee.robot_ui.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.iflytek.cloud.msc.util.NetworkUtil;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.disinfect.baselib.util.WifiUtil;
import com.pudutech.factory_test.test_pack.TestConstantKt;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/* loaded from: classes4.dex */
public class NetStatusUtil {
    public static final int CMNET = 6;
    public static final int CMWAP = 5;
    public static final int CTNET = 8;
    public static final int CTWAP = 7;
    public static final int LTE = 10;
    public static final int MOBILE = 9;
    public static final int NETTYPE_NOT_WIFI = 0;
    public static final int NETTYPE_WIFI = 1;
    public static final int NET_3G = 4;
    public static final int NON_NETWORK = -1;
    public static final int NO_SIM_OPERATOR = 0;
    public static final int POLICY_NONE = 0;
    public static final int POLICY_REJECT_METERED_BACKGROUND = 1;
    private static final String TAG = "NetStatusUtil";
    public static final int TBACKGROUND_DATA_LIMITED = 2;
    public static final int TBACKGROUND_NOT_LIMITED = 0;
    public static final int TBACKGROUND_PROCESS_LIMITED = 1;
    public static final int TBACKGROUND_WIFI_LIMITED = 3;
    public static final int UNINET = 1;
    public static final int UNIWAP = 2;
    public static final int UNKNOW_TYPE = 999;
    public static final int WAP_3G = 3;
    public static final int WIFI = 0;
    private static int nowStrength;

    public static boolean isLimited(int i) {
        return i == 2 || i == 1 || i == 3;
    }

    public static boolean isWap(int i) {
        return i == 2 || i == 5 || i == 7 || i == 3;
    }

    public static boolean isWifi(int i) {
        return i == 0;
    }

    public static void dumpNetStatus(Context context) {
        try {
            Log.i(TAG, ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo().toString());
        } catch (Exception e) {
            Log.e(TAG, "", e);
        }
    }

    public static boolean isConnected(Context context) {
        try {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo().isConnected();
        } catch (Exception unused) {
            return false;
        }
    }

    public static String getNetTypeString(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) ? "NON_NETWORK" : activeNetworkInfo.getType() == 1 ? TestConstantKt.WIFI : activeNetworkInfo.getExtraInfo() != null ? activeNetworkInfo.getExtraInfo() : "MOBILE";
    }

    public static int getNetWorkType(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.getType();
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
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

    public static int getISPCode(Context context) {
        String simOperator;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null && (simOperator = telephonyManager.getSimOperator()) != null && simOperator.length() >= 5) {
            StringBuilder sb = new StringBuilder();
            try {
                int length = simOperator.length();
                if (length > 6) {
                    length = 6;
                }
                for (int i = 0; i < length; i++) {
                    if (!Character.isDigit(simOperator.charAt(i))) {
                        if (sb.length() > 0) {
                            break;
                        }
                    } else {
                        sb.append(simOperator.charAt(i));
                    }
                }
                return Integer.valueOf(sb.toString()).intValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static String getISPName(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return "";
        }
        if (telephonyManager.getSimOperatorName().length() <= 100) {
            return telephonyManager.getSimOperatorName();
        }
        return telephonyManager.getSimOperatorName().substring(0, 100);
    }

    public static int guessNetSpeed(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (activeNetworkInfo.getType() == 1) {
            return 102400;
        }
        switch (activeNetworkInfo.getSubtype()) {
            case 1:
                return 4096;
            case 2:
                return 8192;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                return 102400;
            default:
                return 102400;
        }
    }

    public static boolean isMobile(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo.getType() == 0) {
                if (activeNetworkInfo.isAvailable()) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean is2G(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (activeNetworkInfo.getType() == 1) {
            return false;
        }
        if (activeNetworkInfo.getSubtype() != 2 && activeNetworkInfo.getSubtype() != 1) {
            if (activeNetworkInfo.getSubtype() != 4) {
                return false;
            }
        }
        return true;
    }

    public static boolean is4G(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (activeNetworkInfo.getType() == 1) {
            return false;
        }
        return activeNetworkInfo.getSubtype() >= 13;
    }

    public static boolean isWap(Context context) {
        return isWap(getNetType(context));
    }

    public static boolean is3G(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo.getType() != 1 && activeNetworkInfo.getSubtype() >= 5) {
                if (activeNetworkInfo.getSubtype() < 13) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isWifi(Context context) {
        return isWifi(getNetType(context));
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

    private static Intent searchIntentByClass(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
            if (installedPackages == null || installedPackages.size() <= 0) {
                return null;
            }
            for (int i = 0; i < installedPackages.size(); i++) {
                try {
                    Intent intent = new Intent();
                    intent.setPackage(installedPackages.get(i).packageName);
                    List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
                    int size = queryIntentActivities != null ? queryIntentActivities.size() : 0;
                    if (size > 0) {
                        for (int i2 = 0; i2 < size; i2++) {
                            try {
                                ActivityInfo activityInfo = queryIntentActivities.get(i2).activityInfo;
                                if (activityInfo.name.contains(str)) {
                                    Intent intent2 = new Intent("/");
                                    intent2.setComponent(new ComponentName(activityInfo.packageName, activityInfo.name));
                                    intent2.setAction("android.intent.action.VIEW");
                                    context.startActivity(intent2);
                                    return intent2;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        continue;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return null;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static void startSettingItent(Context context, int i) {
        if (i != 0) {
            if (i == 1) {
                try {
                    Intent intent = new Intent("/");
                    intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.DevelopmentSettings"));
                    intent.setAction("android.intent.action.VIEW");
                    context.startActivity(intent);
                    return;
                } catch (Exception unused) {
                    searchIntentByClass(context, "DevelopmentSettings");
                    return;
                }
            }
            if (i != 2) {
                if (i != 3) {
                    return;
                }
                try {
                    Intent intent2 = new Intent();
                    intent2.setAction("android.settings.WIFI_IP_SETTINGS");
                    context.startActivity(intent2);
                    return;
                } catch (Exception unused2) {
                    searchIntentByClass(context, "AdvancedSettings");
                    return;
                }
            }
            try {
                try {
                    Intent intent3 = new Intent("/");
                    intent3.setComponent(new ComponentName("com.android.providers.subscribedfeeds", "com.android.settings.ManageAccountsSettings"));
                    intent3.setAction("android.intent.action.VIEW");
                    context.startActivity(intent3);
                } catch (Exception unused3) {
                    searchIntentByClass(context, "ManageAccountsSettings");
                }
            } catch (Exception unused4) {
                Intent intent4 = new Intent("/");
                intent4.setComponent(new ComponentName("com.htc.settings.accountsync", "com.htc.settings.accountsync.ManageAccountsSettings"));
                intent4.setAction("android.intent.action.VIEW");
                context.startActivity(intent4);
            }
        }
    }

    public static int getWifiSleeepPolicy(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "wifi_sleep_policy", 2);
    }

    public static int getBackgroundLimitType(Context context) {
        int wifiSleeepPolicy;
        if (Build.VERSION.SDK_INT >= 14) {
            try {
                Class<?> cls = Class.forName("android.app.ActivityManagerNative");
                Object invoke = cls.getMethod("getDefault", new Class[0]).invoke(cls, new Object[0]);
                if (((Integer) invoke.getClass().getMethod("getProcessLimit", new Class[0]).invoke(invoke, new Object[0])).intValue() == 0) {
                    return 1;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            wifiSleeepPolicy = getWifiSleeepPolicy(context);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (wifiSleeepPolicy != 2) {
            if (getNetType(context) == 0) {
                return (wifiSleeepPolicy == 1 || wifiSleeepPolicy == 0) ? 3 : 0;
            }
        }
        return 0;
    }

    public static boolean isImmediatelyDestroyActivities(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "always_finish_activities", 0) != 0;
    }

    public static int getProxyInfo(Context context, StringBuffer stringBuffer) {
        try {
            String defaultHost = Proxy.getDefaultHost();
            int defaultPort = Proxy.getDefaultPort();
            if (defaultHost != null && defaultHost.length() > 0 && defaultPort > 0) {
                stringBuffer.append(defaultHost);
                return defaultPort;
            }
            String property = System.getProperty("http.proxyHost");
            String property2 = System.getProperty("http.proxyPort");
            int i = 80;
            if (property2 != null && property2.length() > 0) {
                i = Integer.parseInt(property2);
            }
            if (property == null || property.length() <= 0) {
                return 0;
            }
            stringBuffer.append(property);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean isKnownDirectNet(Context context) {
        int netType = getNetType(context);
        return 6 == netType || 1 == netType || 4 == netType || 8 == netType || 10 == netType || netType == 0;
    }

    public static boolean isNetworkConnected(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || activeNetworkInfo.getState() != NetworkInfo.State.CONNECTED) ? false : true;
    }

    /* loaded from: classes4.dex */
    public static class StrengthListener extends PhoneStateListener {
        @Override // android.telephony.PhoneStateListener
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            if (!signalStrength.isGsm()) {
                int unused = NetStatusUtil.nowStrength = signalStrength.getCdmaDbm();
            } else {
                int unused2 = NetStatusUtil.nowStrength = signalStrength.getGsmSignalStrength();
            }
        }
    }

    public static int getNetTypeForStat(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null) {
            return 999;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return 999;
            }
            if (activeNetworkInfo.getType() == 1) {
                return 1;
            }
            int subtype = activeNetworkInfo.getSubtype();
            if (subtype == 0) {
                return 999;
            }
            return subtype * 1000;
        } catch (Exception e) {
            e.printStackTrace();
            return 999;
        }
    }

    public static int getStrength(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            if (getNetTypeForStat(context) == 1) {
                return Math.abs(((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getRssi());
            }
            ((TelephonyManager) context.getSystemService("phone")).listen(new StrengthListener(), 256);
            return Math.abs(nowStrength);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getIpfromDomain(String str) {
        String str2;
        try {
            str2 = InetAddress.getByName(str).getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            str2 = null;
        }
        Log.e("域名解析=====ip地址=====", str2);
        return str2 != null ? str2 : str;
    }

    public static boolean isWiFiOn(Context context) {
        int wifiState = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getWifiState();
        Pdlog.m3273d(TAG, "isWifiOn state = " + wifiState);
        return wifiState == 3 || wifiState == 2;
    }

    public static boolean isWiFiConnect(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
        if (connectionInfo == null) {
            return false;
        }
        int ipAddress = connectionInfo.getIpAddress();
        Pdlog.m3273d(TAG, "isWiFiConnect ipAddress = " + ipAddress);
        return ipAddress != 0;
    }

    public static void setWifiAutoOpen() {
        try {
            int wifiAutoOpenType = Constans.INSTANCE.getWifiAutoOpenType();
            if (wifiAutoOpenType == -1) {
                if (WifiUtil.INSTANCE.isWifiEnable()) {
                    Constans.INSTANCE.setWifiAutoOpenType(1);
                    Pdlog.m3273d(TAG, "setWifiAutoOpen()###1");
                } else {
                    Constans.INSTANCE.setWifiAutoOpenType(0);
                    Pdlog.m3273d(TAG, "setWifiAutoOpen()###0");
                }
            } else if (wifiAutoOpenType == 1) {
                WifiUtil.INSTANCE.openWifi();
                Pdlog.m3273d(TAG, "setWifiAutoOpen()##open");
            }
            Pdlog.m3273d(TAG, "setWifiAutoOpen()##wifiAutoOpenType：", Integer.valueOf(wifiAutoOpenType));
        } catch (Exception e) {
            e.printStackTrace();
            Pdlog.m3273d(TAG, "setWifiAutoOpen()##Exception", e.getMessage());
        }
    }
}
