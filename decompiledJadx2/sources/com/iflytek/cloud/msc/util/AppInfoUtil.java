package com.iflytek.cloud.msc.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.webkit.WebView;
import androidx.core.os.EnvironmentCompat;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.thirdparty.C3692ad;
import java.lang.reflect.Field;
import java.net.NetworkInterface;
import java.net.SocketException;

/* loaded from: classes3.dex */
public class AppInfoUtil {
    public static final String APP_NAME = "app.name";
    public static final String APP_PATH = "app.path";
    public static final String APP_PKG_NAME = "app.pkg";
    public static final String APP_VER_CODE = "app.ver.code";
    public static final String APP_VER_NAME = "app.ver.name";
    public static final String BROWSER_UA = "user_agent";
    public static final String CARRIER = "carrier";
    public static final String DVC_TYPE = "device_type";
    public static final String DVC_TYPE_HANDSET = "handset";
    public static final String DVC_TYPE_TABLET = "tablet";
    public static final String DVC_TYPE_UNKNOW = "unknow";
    public static final String NET_MAC = "net.mac";
    public static final String OS_ANDROID_ID = "os.android_id";
    public static final String OS_DENSITY = "os.density";
    public static final String OS_IMEI = "os.imei";
    public static final String OS_IMSI = "os.imsi";
    public static final String OS_INCREMENTAL = "os.incremental";
    public static final String OS_RELEASE = "os.release";
    public static final String OS_RESOLUTION = "os.resolution";
    public static final String OS_SYSTEM = "os.system";
    public static final String OS_VERSION = "os.version";
    public static String[][] REPLACED_SYMBOLS = {new String[]{"=", ":"}, new String[]{",", "_"}};
    public static String[][] BUILD_INFO = {new String[]{"os.manufact", Build.MANUFACTURER}, new String[]{"os.model", Build.MODEL}, new String[]{"os.product", Build.PRODUCT}, new String[]{"os.display", Build.DISPLAY}, new String[]{"os.host", Build.HOST}, new String[]{"os.id", Build.ID}, new String[]{"os.device", Build.DEVICE}, new String[]{"os.board", Build.BOARD}, new String[]{"os.brand", Build.BRAND}, new String[]{"os.user", Build.USER}, new String[]{"os.type", Build.TYPE}};
    private static String[][] BUILD_INFO_KEYS = {new String[]{"os.cpu", "CPU_ABI"}, new String[]{"os.cpu2", "CPU_ABI2"}, new String[]{"os.serial", "SERIAL"}, new String[]{"os.hardware", "HARDWARE"}, new String[]{"os.bootloader", "BOOTLOADER"}, new String[]{"os.radio", "RADIO"}};
    private static C3692ad initInfo = new C3692ad();
    private static boolean initOK = false;
    private static String sSafariUserAgent = null;
    private static String sDeviceType = null;

    public static synchronized C3692ad getAppInfo(Context context) {
        synchronized (AppInfoUtil.class) {
            if (initOK) {
                return initInfo;
            }
            initialDevInfo(context);
            return initInfo;
        }
    }

    private static void initialDevInfo(Context context) {
        try {
            initInfo.m1818a();
            initInfo.m1822a(OS_SYSTEM, "Android");
            initAppVerInfo(initInfo, context);
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            initInfo.m1822a(OS_RESOLUTION, displayMetrics.widthPixels + "*" + displayMetrics.heightPixels);
            initInfo.m1822a(OS_DENSITY, "" + displayMetrics.density);
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            try {
                initInfo.m1822a(OS_IMEI, telephonyManager.getDeviceId());
            } catch (SecurityException unused) {
                DebugLog.LogD("Failed to get did Info:SecurityException");
            } catch (Throwable unused2) {
                DebugLog.LogD("Failed to get did Info:other exception");
            }
            try {
                initInfo.m1822a(OS_IMSI, telephonyManager.getSubscriberId());
            } catch (SecurityException unused3) {
                DebugLog.LogD("Failed to get sbid Info:SecurityException");
            } catch (Throwable unused4) {
                DebugLog.LogD("Failed to get sbid Info:other exception");
            }
            String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (!TextUtils.isEmpty(string)) {
                initInfo.m1822a(OS_ANDROID_ID, string);
            }
            initInfo.m1822a(OS_VERSION, Build.VERSION.SDK);
            initInfo.m1822a(OS_RELEASE, Build.VERSION.RELEASE);
            initInfo.m1822a(OS_INCREMENTAL, Build.VERSION.INCREMENTAL);
            for (int i = 0; i < BUILD_INFO.length; i++) {
                initInfo.m1822a(BUILD_INFO[i][0], BUILD_INFO[i][1]);
            }
            for (int i2 = 0; i2 < BUILD_INFO_KEYS.length; i2++) {
                initInfo.m1822a(BUILD_INFO_KEYS[i2][0], getBuildInfo(BUILD_INFO_KEYS[i2][1]));
            }
            initInfo.m1822a(NET_MAC, getMac(context));
            initInfo.m1822a(CARRIER, getCarrier(context));
            initInfo.m1832d();
            initOK = true;
        } catch (Exception unused5) {
            DebugLog.LogD("Failed to get prop Info");
            initOK = false;
        } catch (Throwable unused6) {
            DebugLog.LogD("Failed to get property Info");
            initOK = false;
        }
    }

    public static void initAppVerInfo(C3692ad c3692ad, Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            c3692ad.m1822a(APP_VER_NAME, packageInfo.versionName);
            c3692ad.m1822a(APP_VER_CODE, "" + packageInfo.versionCode);
            c3692ad.m1822a(APP_PKG_NAME, applicationInfo.packageName);
            c3692ad.m1822a(APP_PATH, applicationInfo.dataDir);
            c3692ad.m1822a(APP_NAME, applicationInfo.loadLabel(context.getPackageManager()).toString());
        } catch (Exception unused) {
        }
    }

    public static C3692ad getShortAppInfo(Context context) {
        C3692ad appInfo = getAppInfo(context);
        C3692ad c3692ad = new C3692ad();
        c3692ad.m1820a(appInfo, APP_NAME);
        c3692ad.m1820a(appInfo, APP_PATH);
        c3692ad.m1820a(appInfo, APP_PKG_NAME);
        c3692ad.m1820a(appInfo, APP_VER_NAME);
        c3692ad.m1820a(appInfo, APP_VER_CODE);
        c3692ad.m1820a(appInfo, OS_SYSTEM);
        c3692ad.m1820a(appInfo, OS_RESOLUTION);
        c3692ad.m1820a(appInfo, OS_DENSITY);
        c3692ad.m1820a(appInfo, NET_MAC);
        c3692ad.m1820a(appInfo, OS_IMEI);
        c3692ad.m1820a(appInfo, OS_IMSI);
        c3692ad.m1820a(appInfo, OS_VERSION);
        c3692ad.m1820a(appInfo, OS_RELEASE);
        c3692ad.m1820a(appInfo, OS_INCREMENTAL);
        c3692ad.m1820a(appInfo, OS_ANDROID_ID);
        c3692ad.m1820a(appInfo, CARRIER);
        c3692ad.m1820a(appInfo, BUILD_INFO[0][0]);
        c3692ad.m1820a(appInfo, BUILD_INFO[1][0]);
        c3692ad.m1820a(appInfo, BUILD_INFO[2][0]);
        c3692ad.m1820a(appInfo, BUILD_INFO[3][0]);
        return c3692ad;
    }

    public static String getCarrier(Context context) {
        try {
            String networkOperatorName = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
            return !TextUtils.isEmpty(networkOperatorName) ? networkOperatorName : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String getMac(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                NetworkInterface byName = NetworkInterface.getByName("wlan0");
                if (byName == null) {
                    return "";
                }
                byte[] hardwareAddress = byName.getHardwareAddress();
                StringBuffer stringBuffer = new StringBuffer();
                for (byte b : hardwareAddress) {
                    stringBuffer.append(String.format("%02x:", Byte.valueOf(b)));
                }
                if (stringBuffer.length() > 0) {
                    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                }
                String stringBuffer2 = stringBuffer.toString();
                return stringBuffer2 != null ? stringBuffer2.length() > 0 ? stringBuffer2 : "" : "";
            } catch (NullPointerException e) {
                DebugLog.LogW(e + "");
                return "";
            } catch (SocketException e2) {
                DebugLog.LogW(e2 + "");
                return "";
            }
        }
        try {
            return ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        } catch (Throwable unused) {
            DebugLog.LogD("Failed to get mac Info");
            return "";
        }
    }

    private static String getBuildInfo(String str) {
        try {
            Field field = Build.class.getField(str);
            if (field != null) {
                return field.get(new Build()).toString();
            }
        } catch (Exception unused) {
        }
        return EnvironmentCompat.MEDIA_UNKNOWN;
    }

    public static String getBrowserUserAgent(final Context context) {
        if (TextUtils.isEmpty(sSafariUserAgent)) {
            try {
                new Handler(context.getMainLooper(), new Handler.Callback() { // from class: com.iflytek.cloud.msc.util.AppInfoUtil.1
                    @Override // android.os.Handler.Callback
                    public boolean handleMessage(Message message) {
                        if (message.what != 1) {
                            return false;
                        }
                        String unused = AppInfoUtil.sSafariUserAgent = new WebView(context).getSettings().getUserAgentString();
                        DebugLog.LogS("user agent: " + AppInfoUtil.sSafariUserAgent);
                        return false;
                    }
                }).sendEmptyMessage(1);
            } catch (Throwable th) {
                DebugLog.LogE(th);
            }
        }
        DebugLog.LogS("get user agent: " + sSafariUserAgent);
        return sSafariUserAgent;
    }

    public static String getDeviceType(Context context) {
        if (TextUtils.isEmpty(sDeviceType)) {
            try {
                int i = context.getResources().getConfiguration().screenLayout & 15;
                if (i >= 3) {
                    sDeviceType = DVC_TYPE_TABLET;
                } else if (i >= 1) {
                    sDeviceType = DVC_TYPE_HANDSET;
                } else {
                    sDeviceType = DVC_TYPE_UNKNOW;
                }
            } catch (Throwable th) {
                DebugLog.LogE(th);
            }
        }
        DebugLog.LogS("get device type: " + sDeviceType);
        return sDeviceType;
    }

    public static String checkAndReplaceIllegalSymbols(String str) {
        String str2;
        DebugLog.LogS("check and replace src: " + str);
        if (str != null) {
            str2 = str;
            int i = 0;
            while (true) {
                String[][] strArr = REPLACED_SYMBOLS;
                if (i >= strArr.length) {
                    break;
                }
                str2 = str2.replace(strArr[i][0], strArr[i][1]);
                i++;
            }
        } else {
            str2 = null;
        }
        DebugLog.LogS("check and replace result: " + str2);
        return str2;
    }

    public static String getActivityMsg(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        try {
            int i = Build.VERSION.SDK_INT;
            boolean z = context.getPackageManager().checkPermission("android.permission.GET_TASKS", context.getPackageName()) == 0;
            if (i < 23 && z) {
                try {
                    return activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
                } catch (Exception unused) {
                }
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
