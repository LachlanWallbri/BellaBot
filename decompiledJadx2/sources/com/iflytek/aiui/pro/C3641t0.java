package com.iflytek.aiui.pro;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.iflytek.cloud.SpeechConstant;
import java.net.NetworkInterface;
import java.util.Enumeration;
import org.apache.commons.codec.language.Soundex;

/* renamed from: com.iflytek.aiui.pro.t0 */
/* loaded from: classes4.dex */
public class C3641t0 {

    /* renamed from: a */
    private static boolean f2656a = true;

    /* renamed from: b */
    public static String[][] f2657b = {new String[]{"vendor", Build.MANUFACTURER}, new String[]{"model", Build.MODEL}};

    /* renamed from: c */
    private static C3631o0 f2658c = new C3631o0();

    /* renamed from: d */
    private static boolean f2659d = false;

    /* renamed from: a */
    private static void m1537a(Context context, C3631o0 c3631o0, Boolean bool) {
        NetworkInfo activeNetworkInfo;
        if (context == null || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) == null) {
            c3631o0.m1468h(SpeechConstant.NET_TYPE, "none", bool.booleanValue());
        } else {
            c3631o0.m1468h(SpeechConstant.NET_TYPE, C3653z0.m1642b(activeNetworkInfo), bool.booleanValue());
            c3631o0.m1468h("net_subtype", C3653z0.m1641a(activeNetworkInfo), bool.booleanValue());
        }
    }

    /* renamed from: b */
    private static boolean m1538b(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        return packageManager != null && packageManager.checkPermission(str, context.getPackageName()) == 0;
    }

    /* renamed from: c */
    public static String m1539c(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append(m1541e(context));
        if (f2656a) {
            String m1545i = m1545i(context);
            if (!TextUtils.isEmpty(m1545i)) {
                sb.append(Soundex.SILENT_MARKER);
                sb.append(m1545i);
            }
        }
        return sb.toString();
    }

    /* renamed from: d */
    public static C3631o0 m1540d(Context context) {
        synchronized (C3641t0.class) {
            if (f2659d) {
                m1537a(context, f2658c, Boolean.TRUE);
                return f2658c;
            }
            m1547k(context);
            return f2658c;
        }
    }

    /* renamed from: e */
    private static String m1541e(Context context) {
        TelephonyManager telephonyManager;
        if (!m1538b(context, "android.permission.READ_PHONE_STATE") || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null) {
            return "";
        }
        try {
            return telephonyManager.getDeviceId();
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: f */
    private static String m1542f(Context context) {
        TelephonyManager telephonyManager;
        if (!m1538b(context, "android.permission.READ_PHONE_STATE") || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null) {
            return "";
        }
        try {
            return telephonyManager.getSubscriberId();
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: g */
    private static String m1543g(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            if (displayMetrics == null) {
                return null;
            }
            return displayMetrics.heightPixels + "*" + displayMetrics.widthPixels;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: h */
    public static String m1544h(Context context, String str) {
        try {
            return Settings.System.getString(context.getContentResolver(), str);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: i */
    private static String m1545i(Context context) {
        String exc;
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces != null) {
                    if (!networkInterfaces.hasMoreElements()) {
                        break;
                    }
                    NetworkInterface nextElement = networkInterfaces.nextElement();
                    byte[] hardwareAddress = nextElement.getHardwareAddress();
                    if (hardwareAddress != null && hardwareAddress.length != 0 && ("wlan0".equalsIgnoreCase(nextElement.getName()) || "eth0".equalsIgnoreCase(nextElement.getName()))) {
                        StringBuilder sb = new StringBuilder();
                        for (byte b : hardwareAddress) {
                            sb.append(String.format("%02X:", Byte.valueOf(b)));
                        }
                        if (sb.length() > 0) {
                            sb.deleteCharAt(sb.length() - 1);
                        }
                        String sb2 = sb.toString();
                        if (sb2.length() > 0) {
                            return sb2;
                        }
                    }
                }
            } catch (Exception e) {
                exc = e.toString();
                C3651y0.m1629k("DeviceInfoUtil", exc);
                return "";
            }
        } else {
            try {
                WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
                if (wifiManager != null) {
                    return wifiManager.getConnectionInfo().getMacAddress();
                }
            } catch (Throwable unused) {
                exc = "Failed to get mac Info";
                C3651y0.m1629k("DeviceInfoUtil", exc);
                return "";
            }
        }
        return "";
    }

    /* renamed from: j */
    private static void m1546j(C3631o0 c3631o0, Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            c3631o0.m1467g("pkg_name", context.getPackageName());
            c3631o0.m1467g("app_ver", packageInfo.versionName);
            c3631o0.m1467g("app_name", applicationInfo.loadLabel(context.getPackageManager()).toString());
        } catch (Exception unused) {
        }
    }

    /* renamed from: k */
    private static void m1547k(Context context) {
        try {
            f2658c.m1461a();
            f2658c.m1467g("platform", "Android");
            m1546j(f2658c, context);
            f2658c.m1467g("imei", m1541e(context));
            f2658c.m1467g("imsi", m1542f(context));
            f2658c.m1467g("os_release", Build.VERSION.RELEASE);
            int i = 0;
            while (true) {
                String[][] strArr = f2657b;
                if (i >= strArr.length) {
                    m1537a(context, f2658c, Boolean.FALSE);
                    f2658c.m1467g("mac", m1545i(context));
                    f2658c.m1467g("resolution", m1543g(context));
                    f2659d = true;
                    return;
                }
                f2658c.m1467g(strArr[i][0], strArr[i][1]);
                i++;
            }
        } catch (Exception unused) {
            f2659d = false;
        }
    }

    /* renamed from: l */
    public static void m1548l(Context context, String str, String str2) {
        try {
            Settings.System.putString(context.getContentResolver(), str, str2);
        } catch (Exception unused) {
        }
    }
}
