package com.loc;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.pudutech.mirsdk.update.ApiConstants;
import java.io.File;
import java.io.IOException;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/* compiled from: DeviceInfo.java */
/* renamed from: com.loc.n */
/* loaded from: classes4.dex */
public final class C3888n {

    /* renamed from: a */
    private static String f4287a = "";

    /* renamed from: b */
    private static boolean f4288b = false;

    /* renamed from: c */
    private static String f4289c = "";

    /* renamed from: d */
    private static String f4290d = "";

    /* renamed from: e */
    private static String f4291e = "";

    /* renamed from: f */
    private static String f4292f = "";

    /* compiled from: DeviceInfo.java */
    /* renamed from: com.loc.n$a */
    /* loaded from: classes4.dex */
    static class a extends DefaultHandler {
        a() {
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public final void characters(char[] cArr, int i, int i2) throws SAXException {
            if (C3888n.f4288b) {
                String unused = C3888n.f4287a = new String(cArr, i, i2);
            }
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public final void endElement(String str, String str2, String str3) throws SAXException {
            boolean unused = C3888n.f4288b = false;
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public final void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            if (str2.equals("string") && "UTDID".equals(attributes.getValue("name"))) {
                boolean unused = C3888n.f4288b = true;
            }
        }
    }

    /* renamed from: a */
    public static String m3145a(Context context) {
        try {
            return m3172u(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    /* renamed from: a */
    private static List<ScanResult> m3147a(List<ScanResult> list) {
        int size = list.size();
        for (int i = 0; i < size - 1; i++) {
            for (int i2 = 1; i2 < size - i; i2++) {
                int i3 = i2 - 1;
                if (list.get(i3).level > list.get(i2).level) {
                    ScanResult scanResult = list.get(i3);
                    list.set(i3, list.get(i2));
                    list.set(i2, scanResult);
                }
            }
        }
        return list;
    }

    /* renamed from: a */
    public static void m3148a() {
        try {
            if (Build.VERSION.SDK_INT > 14) {
                TrafficStats.class.getDeclaredMethod("setThreadStatsTag", Integer.TYPE).invoke(null, 40964);
            }
        } catch (Throwable th) {
            C3897w.m3249a(th, "DeviceInfo", "setTraficTag");
        }
    }

    /* renamed from: a */
    private static boolean m3149a(Context context, String str) {
        return context != null && context.checkCallingOrSelfPermission(str) == 0;
    }

    /* renamed from: b */
    public static String m3151b(Context context) {
        try {
            String m3169r = m3169r(context);
            if (m3169r != null && m3169r.length() >= 5) {
                return m3169r.substring(3, 5);
            }
            return "";
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    /* renamed from: c */
    public static int m3153c(Context context) {
        try {
            return m3175x(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    /* renamed from: c */
    private static String m3154c() {
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                    byte[] hardwareAddress = Build.VERSION.SDK_INT >= 9 ? networkInterface.getHardwareAddress() : null;
                    if (hardwareAddress == null) {
                        return "";
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte b : hardwareAddress) {
                        String upperCase = Integer.toHexString(b & 255).toUpperCase();
                        if (upperCase.length() == 1) {
                            sb.append("0");
                        }
                        sb.append(upperCase);
                        sb.append(":");
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
        } catch (Exception e) {
            C3897w.m3249a(e, "DeviceInfo", "getMacAddr");
        }
        return "";
    }

    /* renamed from: d */
    public static int m3155d(Context context) {
        try {
            return m3173v(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    /* renamed from: e */
    public static String m3156e(Context context) {
        try {
            return m3171t(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    /* renamed from: f */
    public static String m3157f(Context context) {
        try {
        } catch (Throwable th) {
            C3897w.m3249a(th, "DeviceInfo", "getUTDID");
        }
        if (f4287a != null && !"".equals(f4287a)) {
            return f4287a;
        }
        if (m3149a(context, "android.permission.WRITE_SETTINGS")) {
            f4287a = Settings.System.getString(context.getContentResolver(), "mqBRboGZkQPcAkyk");
        }
        if (f4287a != null && !"".equals(f4287a)) {
            return f4287a;
        }
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/.UTSystemConfig/Global/Alvin2.xml");
                if (file.exists()) {
                    SAXParserFactory.newInstance().newSAXParser().parse(file, new a());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Throwable th2) {
            C3897w.m3249a(th2, "DeviceInfo", "getUTDID");
        }
        String str = f4287a;
        return str == null ? "" : str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: g */
    public static String m3158g(Context context) {
        WifiManager wifiManager;
        if (context == null) {
            return "";
        }
        try {
            return (m3149a(context, "android.permission.ACCESS_WIFI_STATE") && (wifiManager = (WifiManager) context.getSystemService("wifi")) != null && wifiManager.isWifiEnabled()) ? wifiManager.getConnectionInfo().getBSSID() : "";
        } catch (Throwable th) {
            C3897w.m3249a(th, "DeviceInfo", "getWifiMacs");
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: h */
    public static String m3159h(Context context) {
        StringBuilder sb = new StringBuilder();
        if (context != null) {
            try {
            } catch (Throwable th) {
                C3897w.m3249a(th, "DeviceInfo", "getWifiMacs");
            }
            if (m3149a(context, "android.permission.ACCESS_WIFI_STATE")) {
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                if (wifiManager == null) {
                    return "";
                }
                if (wifiManager.isWifiEnabled()) {
                    List<ScanResult> scanResults = wifiManager.getScanResults();
                    if (scanResults != null && scanResults.size() != 0) {
                        List<ScanResult> m3147a = m3147a(scanResults);
                        boolean z = true;
                        for (int i = 0; i < m3147a.size() && i < 7; i++) {
                            ScanResult scanResult = m3147a.get(i);
                            if (z) {
                                z = false;
                            } else {
                                sb.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                            }
                            sb.append(scanResult.BSSID);
                        }
                    }
                    return sb.toString();
                }
                return sb.toString();
            }
        }
        return sb.toString();
    }

    /* renamed from: i */
    public static String m3160i(Context context) {
        try {
        } catch (Throwable th) {
            C3897w.m3249a(th, "DeviceInfo", "getDeviceMac");
        }
        if ((f4289c == null || "".equals(f4289c)) && m3149a(context, "android.permission.ACCESS_WIFI_STATE")) {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager == null) {
                return "";
            }
            f4289c = wifiManager.getConnectionInfo().getMacAddress();
            if (ApiConstants.MAC_ADDRESS.equals(f4289c) || "00:00:00:00:00:00".equals(f4289c)) {
                f4289c = m3154c();
            }
            return f4289c;
        }
        return f4289c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: j */
    public static String[] m3161j(Context context) {
        try {
        } catch (Throwable th) {
            C3897w.m3249a(th, "DeviceInfo", "cellInfo");
        }
        if (m3149a(context, "android.permission.READ_PHONE_STATE") && m3149a(context, "android.permission.ACCESS_COARSE_LOCATION")) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                return new String[]{"", ""};
            }
            CellLocation cellLocation = telephonyManager.getCellLocation();
            if (cellLocation instanceof GsmCellLocation) {
                GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                int cid = gsmCellLocation.getCid();
                return new String[]{gsmCellLocation.getLac() + "||" + cid, "gsm"};
            }
            if (cellLocation instanceof CdmaCellLocation) {
                CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                return new String[]{cdmaCellLocation.getSystemId() + "||" + cdmaCellLocation.getNetworkId() + "||" + cdmaCellLocation.getBaseStationId(), "cdma"};
            }
            return new String[]{"", ""};
        }
        return new String[]{"", ""};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: k */
    public static String m3162k(Context context) {
        TelephonyManager m3176y;
        try {
            if (!m3149a(context, "android.permission.READ_PHONE_STATE") || (m3176y = m3176y(context)) == null) {
                return "";
            }
            String networkOperator = m3176y.getNetworkOperator();
            if (!TextUtils.isEmpty(networkOperator) && networkOperator.length() >= 3) {
                return networkOperator.substring(3);
            }
            return "";
        } catch (Throwable th) {
            C3897w.m3249a(th, "DeviceInfo", "getMNC");
            return "";
        }
    }

    /* renamed from: l */
    public static int m3163l(Context context) {
        try {
            return m3175x(context);
        } catch (Throwable th) {
            C3897w.m3249a(th, "DeviceInfo", "getNetWorkType");
            return -1;
        }
    }

    /* renamed from: m */
    public static int m3164m(Context context) {
        try {
            return m3173v(context);
        } catch (Throwable th) {
            C3897w.m3249a(th, "DeviceInfo", "getActiveNetWorkType");
            return -1;
        }
    }

    /* renamed from: n */
    public static NetworkInfo m3165n(Context context) {
        ConnectivityManager m3174w;
        if (m3149a(context, "android.permission.ACCESS_NETWORK_STATE") && (m3174w = m3174w(context)) != null) {
            return m3174w.getActiveNetworkInfo();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: o */
    public static String m3166o(Context context) {
        try {
            NetworkInfo m3165n = m3165n(context);
            if (m3165n == null) {
                return null;
            }
            return m3165n.getExtraInfo();
        } catch (Throwable th) {
            C3897w.m3249a(th, "DeviceInfo", "getNetworkExtraInfo");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: p */
    public static String m3167p(Context context) {
        StringBuilder sb;
        try {
        } catch (Throwable th) {
            C3897w.m3249a(th, "DeviceInfo", "getReslution");
        }
        if (f4290d != null && !"".equals(f4290d)) {
            return f4290d;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            return "";
        }
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        if (i2 > i) {
            sb = new StringBuilder();
            sb.append(i);
            sb.append("*");
            sb.append(i2);
        } else {
            sb = new StringBuilder();
            sb.append(i2);
            sb.append("*");
            sb.append(i);
        }
        f4290d = sb.toString();
        return f4290d;
    }

    /* renamed from: q */
    public static String m3168q(Context context) {
        try {
        } catch (Throwable th) {
            C3897w.m3249a(th, "DeviceInfo", "getDeviceID");
        }
        if ((f4291e == null || "".equals(f4291e)) && m3149a(context, "android.permission.READ_PHONE_STATE")) {
            TelephonyManager m3176y = m3176y(context);
            if (m3176y == null) {
                return "";
            }
            String deviceId = m3176y.getDeviceId();
            f4291e = deviceId;
            if (deviceId == null) {
                f4291e = "";
            }
            return f4291e;
        }
        return f4291e;
    }

    /* renamed from: r */
    public static String m3169r(Context context) {
        try {
            return m3171t(context);
        } catch (Throwable th) {
            C3897w.m3249a(th, "DeviceInfo", "getSubscriberId");
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: s */
    public static String m3170s(Context context) {
        try {
            return m3172u(context);
        } catch (Throwable th) {
            C3897w.m3249a(th, "DeviceInfo", "getNetworkOperatorName");
            return "";
        }
    }

    /* renamed from: t */
    private static String m3171t(Context context) {
        String str = f4292f;
        if ((str == null || "".equals(str)) && m3149a(context, "android.permission.READ_PHONE_STATE")) {
            TelephonyManager m3176y = m3176y(context);
            if (m3176y == null) {
                return "";
            }
            String subscriberId = m3176y.getSubscriberId();
            f4292f = subscriberId;
            if (subscriberId == null) {
                f4292f = "";
            }
            return f4292f;
        }
        return f4292f;
    }

    /* renamed from: u */
    private static String m3172u(Context context) {
        if (!m3149a(context, "android.permission.READ_PHONE_STATE")) {
            return null;
        }
        TelephonyManager m3176y = m3176y(context);
        if (m3176y == null) {
            return "";
        }
        String simOperatorName = m3176y.getSimOperatorName();
        return TextUtils.isEmpty(simOperatorName) ? m3176y.getNetworkOperatorName() : simOperatorName;
    }

    /* renamed from: v */
    private static int m3173v(Context context) {
        ConnectivityManager m3174w;
        NetworkInfo activeNetworkInfo;
        if (context == null || !m3149a(context, "android.permission.ACCESS_NETWORK_STATE") || (m3174w = m3174w(context)) == null || (activeNetworkInfo = m3174w.getActiveNetworkInfo()) == null) {
            return -1;
        }
        return activeNetworkInfo.getType();
    }

    /* renamed from: w */
    private static ConnectivityManager m3174w(Context context) {
        return (ConnectivityManager) context.getSystemService("connectivity");
    }

    /* renamed from: x */
    private static int m3175x(Context context) {
        TelephonyManager m3176y;
        if (m3149a(context, "android.permission.READ_PHONE_STATE") && (m3176y = m3176y(context)) != null) {
            return m3176y.getNetworkType();
        }
        return -1;
    }

    /* renamed from: y */
    private static TelephonyManager m3176y(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }
}
