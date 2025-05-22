package com.iflytek.aiui.pro;

import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.webkit.WebSettings;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.iflytek.aiui.pro.C3595ay;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.pudutech.lib_update.util.ShellUtils;
import com.pudutech.mirsdk.compat.topo.ConfigJson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.apache.commons.codec.language.Soundex;
import org.apache.commons.logging.LogFactory;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.iflytek.aiui.pro.bd */
/* loaded from: classes3.dex */
public class C3603bd {
    /* renamed from: a */
    private static Location m1168a(LocationManager locationManager, String str) {
        if (!locationManager.isProviderEnabled(str)) {
            return null;
        }
        Location lastKnownLocation = locationManager.getLastKnownLocation(str);
        for (int i = 0; i < 10 && lastKnownLocation == null; i++) {
            lastKnownLocation = locationManager.getLastKnownLocation(str);
        }
        if (lastKnownLocation == null) {
            return null;
        }
        C3591au.d("Collector", "Get location from " + str + ":" + lastKnownLocation.getLatitude() + "," + lastKnownLocation.getLongitude());
        return lastKnownLocation;
    }

    /* renamed from: a */
    public static String m1169a() {
        return Locale.getDefault().getLanguage();
    }

    /* renamed from: a */
    public static String m1170a(Context context) {
        String str;
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
                return "";
            } catch (Exception e) {
                str = e.toString();
            }
        } else {
            try {
                WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
                return wifiManager != null ? wifiManager.getConnectionInfo().getMacAddress() : "";
            } catch (Throwable unused) {
                str = "Failed to get mac Info";
            }
        }
        C3591au.e("Collector", str);
        return "";
    }

    /* renamed from: a */
    public static String m1171a(String str) {
        BufferedReader bufferedReader;
        FileReader fileReader;
        if (!TextUtils.isEmpty(str)) {
            try {
                fileReader = new FileReader(str);
                try {
                    bufferedReader = new BufferedReader(fileReader);
                    try {
                        String trim = bufferedReader.readLine().trim();
                        try {
                            fileReader.close();
                        } catch (Throwable unused) {
                        }
                        try {
                            bufferedReader.close();
                        } catch (Throwable unused2) {
                        }
                        return trim;
                    } catch (Throwable unused3) {
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (Throwable unused4) {
                            }
                        }
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Throwable unused5) {
                            }
                        }
                        return null;
                    }
                } catch (Throwable unused6) {
                    bufferedReader = null;
                }
            } catch (Throwable unused7) {
                bufferedReader = null;
                fileReader = null;
            }
        }
        return null;
    }

    /* renamed from: a */
    public static String m1172a(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod(TmpConstant.PROPERTY_IDENTIFIER_GET, String.class, String.class).invoke(cls, str, "");
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }

    /* renamed from: a */
    private static JSONArray m1173a(BitSet bitSet) {
        if (bitSet == null || bitSet.cardinality() < 1) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        int i = 0;
        while (true) {
            int nextSetBit = bitSet.nextSetBit(i);
            if (nextSetBit < 0) {
                return jSONArray;
            }
            jSONArray.put(nextSetBit);
            i = nextSetBit + 1;
        }
    }

    /* renamed from: a */
    public static void m1174a(Context context, Map<String, String> map) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            map.put("an", applicationInfo.loadLabel(packageManager).toString());
            map.put("avn", packageInfo.versionName);
            map.put("avc", String.valueOf(packageInfo.versionCode));
            map.put("pn", packageInfo.packageName);
            map.put("aft", String.valueOf(packageInfo.firstInstallTime));
            map.put("alt", String.valueOf(packageInfo.lastUpdateTime));
            map.put("ap", applicationInfo.dataDir);
        } catch (Exception unused) {
            C3591au.f("Collector", "init app info error");
        }
    }

    /* renamed from: a */
    public static void m1175a(Context context, JSONObject jSONObject) {
        TelephonyManager telephonyManager;
        try {
            if ((m1177a(context, "android.permission.ACCESS_COARSE_LOCATION") || m1177a(context, "android.permission.ACCESS_FINE_LOCATION")) && (telephonyManager = (TelephonyManager) context.getSystemService("phone")) != null) {
                CellLocation cellLocation = telephonyManager.getCellLocation();
                if (cellLocation instanceof GsmCellLocation) {
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                    jSONObject.put("cc", gsmCellLocation.getLac());
                    jSONObject.put("cd", gsmCellLocation.getCid());
                    jSONObject.put("sd", gsmCellLocation.getPsc());
                    return;
                }
                if (cellLocation instanceof CdmaCellLocation) {
                    CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                    jSONObject.put("sd", cdmaCellLocation.getSystemId());
                    jSONObject.put("cc", cdmaCellLocation.getNetworkId());
                    jSONObject.put("cd", cdmaCellLocation.getBaseStationId());
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m1176a(Map<String, String> map) {
        try {
            String m1172a = m1172a("ro.miui.ui.version.name", "");
            if (!TextUtils.isEmpty(m1172a)) {
                map.put("son", "MIUI");
                map.put("sov", m1172a);
                return;
            }
            if (m1193f()) {
                map.put("son", "Flyme");
                map.put("sov", m1198i());
            } else if (!TextUtils.isEmpty(m1194g())) {
                map.put("son", "Emui");
                map.put("sov", m1194g());
            } else {
                if (TextUtils.isEmpty(m1196h())) {
                    return;
                }
                map.put("son", "YunOS");
                map.put("sov", m1196h());
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    public static boolean m1177a(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        return packageManager != null && packageManager.checkPermission(str, context.getPackageName()) == 0;
    }

    /* renamed from: b */
    public static String m1178b() {
        return TimeZone.getDefault().getDisplayName();
    }

    /* renamed from: b */
    public static String m1179b(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: b */
    public static void m1180b(Context context, Map<String, String> map) {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            long blockSize = statFs.getBlockSize();
            long blockCount = statFs.getBlockCount();
            long availableBlocks = statFs.getAvailableBlocks();
            String formatFileSize = Formatter.formatFileSize(context, blockCount * blockSize);
            String formatFileSize2 = Formatter.formatFileSize(context, availableBlocks * blockSize);
            map.put("rts", formatFileSize);
            map.put("ras", formatFileSize2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public static void m1181b(Context context, JSONObject jSONObject) {
        WifiManager wifiManager;
        List<WifiConfiguration> configuredNetworks;
        try {
            if (!m1177a(context, "android.permission.ACCESS_WIFI_STATE") || (wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi")) == null || (configuredNetworks = wifiManager.getConfiguredNetworks()) == null) {
                return;
            }
            JSONArray jSONArray = new JSONArray();
            for (WifiConfiguration wifiConfiguration : configuredNetworks) {
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("networkId", wifiConfiguration.networkId);
                    jSONObject2.put(LogFactory.PRIORITY_KEY, wifiConfiguration.priority);
                    jSONObject2.put("name", wifiConfiguration.SSID);
                    jSONObject2.put("id", wifiConfiguration.BSSID);
                    jSONObject2.put("allowedKeyManagement", m1173a(wifiConfiguration.allowedKeyManagement));
                    jSONObject2.put("allowedAuthAlgorithms", m1173a(wifiConfiguration.allowedAuthAlgorithms));
                    jSONObject2.put("allowedGroupCiphers", m1173a(wifiConfiguration.allowedGroupCiphers));
                    jSONObject2.put("allowedPairwiseCiphers", m1173a(wifiConfiguration.allowedPairwiseCiphers));
                    jSONArray.put(jSONObject2);
                } catch (Throwable unused) {
                }
            }
            jSONObject.put("cw", jSONArray);
        } catch (Throwable unused2) {
        }
    }

    /* renamed from: c */
    public static String m1182c() {
        return Locale.getDefault().getCountry();
    }

    /* renamed from: c */
    public static String m1183c(Context context) {
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

    /* renamed from: c */
    public static void m1184c(Context context, JSONObject jSONObject) {
        List<ActivityManager.RecentTaskInfo> recentTasks;
        JSONArray jSONArray = new JSONArray();
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager == null || (recentTasks = activityManager.getRecentTasks(64, 1)) == null || recentTasks.isEmpty()) {
                return;
            }
            Iterator<ActivityManager.RecentTaskInfo> it = recentTasks.iterator();
            while (it.hasNext()) {
                ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(it.next().baseIntent, 0);
                if (resolveActivity != null) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(resolveActivity.activityInfo.packageName, resolveActivity.loadLabel(context.getPackageManager()).toString());
                    jSONArray.put(jSONObject2);
                }
            }
            jSONObject.put(LinkFormat.RESOURCE_TYPE, jSONArray);
        } catch (Throwable th) {
            C3591au.f("Collector", th.toString());
        }
    }

    /* renamed from: d */
    public static String m1185d(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            if (displayMetrics != null) {
                return String.valueOf(displayMetrics.densityDpi);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: d */
    public static void m1186d(Context context, JSONObject jSONObject) {
        JSONArray jSONArray = new JSONArray();
        try {
            PackageManager packageManager = context.getPackageManager();
            List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
            if (installedPackages == null || installedPackages.isEmpty()) {
                return;
            }
            for (PackageInfo packageInfo : installedPackages) {
                if ((packageInfo.applicationInfo.flags & 1) == 0) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(packageInfo.packageName, packageInfo.applicationInfo.loadLabel(packageManager).toString());
                    jSONArray.put(jSONObject2);
                }
            }
            jSONObject.put("ia", jSONArray);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: d */
    public static boolean m1187d() {
        try {
            if (!m1203k()) {
                if (!m1201j()) {
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: e */
    public static String m1188e(Context context) {
        try {
            String networkOperatorName = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
            return !TextUtils.isEmpty(networkOperatorName) ? networkOperatorName : "";
        } catch (Exception e) {
            C3591au.a("Collector", "Get carrier failed. ", e);
            return "";
        }
    }

    /* renamed from: e */
    public static List<C3595ay> m1189e() {
        ArrayList arrayList = new ArrayList();
        for (File file : new File("/proc").listFiles()) {
            if (file.isDirectory()) {
                try {
                    arrayList.add(new C3595ay(Integer.parseInt(file.getName())));
                } catch (C3595ay.b | IOException | NumberFormatException unused) {
                }
            }
        }
        return arrayList;
    }

    /* renamed from: e */
    public static void m1190e(Context context, JSONObject jSONObject) {
        List<ScanResult> list;
        try {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
            WifiInfo wifiInfo = null;
            if (wifiManager == null || !wifiManager.isWifiEnabled()) {
                list = null;
            } else {
                wifiInfo = wifiManager.getConnectionInfo();
                list = wifiManager.getScanResults();
            }
            if (list == null || list.isEmpty()) {
                return;
            }
            JSONArray jSONArray = new JSONArray();
            for (ScanResult scanResult : list) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("name", scanResult.SSID);
                jSONObject2.put("addr", scanResult.BSSID);
                jSONObject2.put("level", scanResult.level);
                if (scanResult.BSSID.equals(wifiInfo.getBSSID())) {
                    jSONObject2.put(MqttServiceConstants.CONNECT_ACTION, 1);
                } else {
                    jSONObject2.put(MqttServiceConstants.CONNECT_ACTION, 0);
                }
                jSONArray.put(jSONObject2);
                jSONObject.put("sw", jSONArray);
            }
        } catch (Exception e) {
            C3591au.e("Collector", "get wifi list failed:" + e);
        }
    }

    /* renamed from: f */
    public static Location m1191f(Context context) {
        if (!(m1177a(context, "android.permission.ACCESS_COARSE_LOCATION") || m1177a(context, "android.permission.ACCESS_FINE_LOCATION"))) {
            C3591au.e("Collector", "No permission to access location");
            return null;
        }
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService(RequestParameters.SUBRESOURCE_LOCATION);
            List<String> providers = locationManager.getProviders(true);
            if (providers != null && !providers.isEmpty()) {
                Location m1168a = providers.contains("gps") ? m1168a(locationManager, "gps") : null;
                return (m1168a == null && providers.contains("network")) ? m1168a(locationManager, "network") : m1168a;
            }
            return null;
        } catch (Exception e) {
            C3591au.b("Collector", "Get location failed", e);
            return null;
        }
    }

    /* renamed from: f */
    public static void m1192f(Context context, JSONObject jSONObject) {
        try {
            Intent registerReceiver = context.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (registerReceiver == null) {
                return;
            }
            JSONObject jSONObject2 = new JSONObject();
            int intExtra = registerReceiver.getIntExtra("status", 0);
            int intExtra2 = registerReceiver.getIntExtra("health", 1);
            boolean booleanExtra = registerReceiver.getBooleanExtra("present", false);
            int intExtra3 = registerReceiver.getIntExtra("level", 0);
            int intExtra4 = registerReceiver.getIntExtra("scale", 0);
            int intExtra5 = registerReceiver.getIntExtra("plugged", 0);
            int intExtra6 = registerReceiver.getIntExtra("voltage", 0);
            int intExtra7 = registerReceiver.getIntExtra("temperature", 0);
            String stringExtra = registerReceiver.getStringExtra("technology");
            jSONObject2.put("status", intExtra);
            jSONObject2.put("health", intExtra2);
            jSONObject2.put("present", booleanExtra);
            jSONObject2.put("level", intExtra3);
            jSONObject2.put("scale", intExtra4);
            jSONObject2.put("plugged", intExtra5);
            jSONObject2.put("voltage", intExtra6);
            jSONObject2.put("temperature", intExtra7);
            jSONObject2.put("technology", stringExtra);
            jSONObject.put("bat", jSONObject2);
        } catch (Exception unused) {
        }
    }

    /* renamed from: f */
    private static boolean m1193f() {
        try {
            Build.class.getMethod("hasSmartBar", new Class[0]);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: g */
    private static String m1194g() {
        return m1172a("ro.build.hw_emui_api_level", "");
    }

    /* renamed from: g */
    public static String m1195g(Context context) {
        TelephonyManager telephonyManager;
        if (!m1177a(context, "android.permission.READ_PHONE_STATE") || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null) {
            return null;
        }
        return telephonyManager.getDeviceId();
    }

    /* renamed from: h */
    private static String m1196h() {
        return m1172a("ro.yunos.version", "");
    }

    /* renamed from: h */
    public static String m1197h(Context context) {
        TelephonyManager telephonyManager;
        if (!m1177a(context, "android.permission.READ_PHONE_STATE") || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null) {
            return null;
        }
        return telephonyManager.getSubscriberId();
    }

    /* renamed from: i */
    private static String m1198i() {
        try {
            String lowerCase = m1172a("ro.build.display.id", "").toLowerCase(Locale.getDefault());
            return lowerCase.contains("flyme os") ? lowerCase.split(" ")[2] : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: i */
    public static String m1199i(Context context) {
        try {
            StringBuilder sb = new StringBuilder();
            if (Build.VERSION.SDK_INT < 21) {
                ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
                if (activityManager != null) {
                    Iterator<ActivityManager.RunningAppProcessInfo> it = activityManager.getRunningAppProcesses().iterator();
                    while (it.hasNext()) {
                        sb.append(it.next().processName);
                        sb.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                    }
                }
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                return sb.toString();
            }
            List<C3595ay> m1189e = m1189e();
            if (m1189e != null && !m1189e.isEmpty()) {
                HashSet hashSet = new HashSet();
                for (C3595ay c3595ay : m1189e) {
                    if (c3595ay.a >= 10000) {
                        String b = c3595ay.b();
                        if (!hashSet.contains(b)) {
                            hashSet.add(b);
                            sb.append(b);
                            sb.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                        }
                    }
                }
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
            return sb.toString();
        } catch (Throwable th) {
            C3591au.f("Collector", th.toString());
            return null;
        }
    }

    /* renamed from: j */
    public static String m1200j(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        StringBuilder sb = new StringBuilder();
        List<ActivityManager.RunningServiceInfo> runningServices = activityManager.getRunningServices(100);
        String str = Build.BRAND;
        for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
            if (runningServiceInfo != null) {
                String className = runningServiceInfo.service.getClassName();
                if (!className.contains("google") && !className.startsWith("com.android") && (TextUtils.isEmpty(str) || !className.contains(str.toLowerCase()))) {
                    sb.append(className);
                    sb.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                }
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /* renamed from: j */
    private static boolean m1201j() {
        try {
            if (new File("/system/app/Superuser.apk").exists()) {
                return true;
            }
        } catch (Exception unused) {
        }
        try {
            return new File("/system/app/Kinguser.apk").exists();
        } catch (Exception unused2) {
            return false;
        }
    }

    /* renamed from: k */
    public static String m1202k(Context context) {
        List<Sensor> sensorList;
        try {
            SensorManager sensorManager = (SensorManager) context.getSystemService(ConfigJson.SENSOR);
            if (sensorManager == null || (sensorList = sensorManager.getSensorList(-1)) == null) {
                return null;
            }
            int size = sensorList.size();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                Sensor sensor = sensorList.get(i);
                if (sensor != null) {
                    sb.append(sensor.getType());
                    sb.append(Soundex.SILENT_MARKER);
                    sb.append(sensor.getVendor());
                    sb.append(Soundex.SILENT_MARKER);
                    sb.append(sensor.getName());
                    sb.append(Soundex.SILENT_MARKER);
                    sb.append(sensor.getVersion());
                    sb.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                }
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        } catch (Throwable th) {
            C3591au.f("Collector", th.toString());
            return null;
        }
    }

    /* renamed from: k */
    private static boolean m1203k() {
        for (String str : new String[]{"/bin", "/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/", "/su/bin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"}) {
            if (new File(str + ShellUtils.COMMAND_SU).exists()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: l */
    public static String m1204l(Context context) {
        BluetoothAdapter defaultAdapter;
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                return Settings.Secure.getString(context.getContentResolver(), "bluetooth_address");
            }
            if (!m1177a(context, "android.permission.BLUETOOTH") || (defaultAdapter = BluetoothAdapter.getDefaultAdapter()) == null) {
                return null;
            }
            return defaultAdapter.getAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0017  */
    /* renamed from: m */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String m1205m(Context context) {
        String defaultUserAgent;
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                defaultUserAgent = WebSettings.getDefaultUserAgent(context);
            } catch (Exception unused) {
            }
            if (!TextUtils.isEmpty(defaultUserAgent)) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            int length = defaultUserAgent.length();
            for (int i = 0; i < length; i++) {
                char charAt = defaultUserAgent.charAt(i);
                if (charAt <= 31 || charAt >= 127) {
                    sb.append(String.format("\\u%04x", Character.valueOf(charAt)));
                } else {
                    sb.append(charAt);
                }
            }
            return sb.toString();
        }
        defaultUserAgent = System.getProperty("http.agent");
        if (!TextUtils.isEmpty(defaultUserAgent)) {
        }
    }
}
