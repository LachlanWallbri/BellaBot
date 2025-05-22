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
import com.iflytek.aiui.pro.C3593aw;
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

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* renamed from: com.iflytek.aiui.pro.bb */
/* loaded from: classes.dex */
public class C3601bb {
    /* renamed from: a */
    private static Location m1127a(LocationManager locationManager, String str) {
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
        C3589as.m1066c("Collector", "Get location from " + str + ":" + lastKnownLocation.getLatitude() + "," + lastKnownLocation.getLongitude());
        return lastKnownLocation;
    }

    /* renamed from: a */
    public static String m1128a() {
        return Locale.getDefault().getLanguage();
    }

    /* renamed from: a */
    public static String m1129a(Context context) {
        String exc;
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces != null) {
                    if (!networkInterfaces.hasMoreElements()) {
                        return "";
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
                exc = e.toString();
            }
        } else {
            try {
                WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
                return wifiManager != null ? wifiManager.getConnectionInfo().getMacAddress() : "";
            } catch (Throwable unused) {
                exc = "Failed to get mac Info";
            }
        }
        C3589as.m1067d("Collector", exc);
        return "";
    }

    /* renamed from: a */
    public static String m1130a(String str) {
        BufferedReader bufferedReader;
        FileReader fileReader;
        if (!TextUtils.isEmpty(str)) {
            try {
                fileReader = new FileReader(str);
                try {
                    bufferedReader = new BufferedReader(fileReader);
                } catch (Throwable unused) {
                    bufferedReader = null;
                }
            } catch (Throwable unused2) {
                bufferedReader = null;
                fileReader = null;
            }
            try {
                String trim = bufferedReader.readLine().trim();
                try {
                    fileReader.close();
                } catch (Throwable unused3) {
                }
                try {
                    bufferedReader.close();
                } catch (Throwable unused4) {
                }
                return trim;
            } catch (Throwable unused5) {
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (Throwable unused6) {
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable unused7) {
                    }
                }
                return null;
            }
        }
        return null;
    }

    /* renamed from: a */
    public static String m1131a(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod(TmpConstant.PROPERTY_IDENTIFIER_GET, String.class, String.class).invoke(cls, str, "");
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }

    /* renamed from: a */
    private static JSONArray m1132a(BitSet bitSet) {
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
    public static void m1133a(Context context, Map<String, String> map) {
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
            C3589as.m1068e("Collector", "init app info error");
        }
    }

    /* renamed from: a */
    public static void m1134a(Context context, JSONObject jSONObject) {
        TelephonyManager telephonyManager;
        try {
            if ((m1136a(context, "android.permission.ACCESS_COARSE_LOCATION") || m1136a(context, "android.permission.ACCESS_FINE_LOCATION")) && (telephonyManager = (TelephonyManager) context.getSystemService("phone")) != null) {
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
    public static void m1135a(Map<String, String> map) {
        try {
            String m1131a = m1131a("ro.miui.ui.version.name", "");
            if (!TextUtils.isEmpty(m1131a)) {
                map.put("son", "MIUI");
                map.put("sov", m1131a);
                return;
            }
            if (m1152f()) {
                map.put("son", "Flyme");
                map.put("sov", m1157i());
            } else if (!TextUtils.isEmpty(m1153g())) {
                map.put("son", "Emui");
                map.put("sov", m1153g());
            } else {
                if (TextUtils.isEmpty(m1155h())) {
                    return;
                }
                map.put("son", "YunOS");
                map.put("sov", m1155h());
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    public static boolean m1136a(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        return packageManager != null && packageManager.checkPermission(str, context.getPackageName()) == 0;
    }

    /* renamed from: b */
    public static String m1137b() {
        return TimeZone.getDefault().getDisplayName();
    }

    /* renamed from: b */
    public static String m1138b(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: b */
    public static void m1139b(Context context, Map<String, String> map) {
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
    public static void m1140b(Context context, JSONObject jSONObject) {
        WifiManager wifiManager;
        List<WifiConfiguration> configuredNetworks;
        try {
            if (!m1136a(context, "android.permission.ACCESS_WIFI_STATE") || (wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi")) == null || (configuredNetworks = wifiManager.getConfiguredNetworks()) == null) {
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
                    jSONObject2.put("allowedKeyManagement", m1132a(wifiConfiguration.allowedKeyManagement));
                    jSONObject2.put("allowedAuthAlgorithms", m1132a(wifiConfiguration.allowedAuthAlgorithms));
                    jSONObject2.put("allowedGroupCiphers", m1132a(wifiConfiguration.allowedGroupCiphers));
                    jSONObject2.put("allowedPairwiseCiphers", m1132a(wifiConfiguration.allowedPairwiseCiphers));
                    jSONArray.put(jSONObject2);
                } catch (Throwable unused) {
                }
            }
            jSONObject.put("cw", jSONArray);
        } catch (Throwable unused2) {
        }
    }

    /* renamed from: c */
    public static String m1141c() {
        return Locale.getDefault().getCountry();
    }

    /* renamed from: c */
    public static String m1142c(Context context) {
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
    public static void m1143c(Context context, JSONObject jSONObject) {
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
            C3589as.m1068e("Collector", th.toString());
        }
    }

    /* renamed from: d */
    public static String m1144d(Context context) {
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
    public static void m1145d(Context context, JSONObject jSONObject) {
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
    public static boolean m1146d() {
        try {
            if (!m1162k()) {
                if (!m1160j()) {
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: e */
    public static String m1147e(Context context) {
        try {
            String networkOperatorName = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
            return !TextUtils.isEmpty(networkOperatorName) ? networkOperatorName : "";
        } catch (Exception e) {
            C3589as.m1060a("Collector", "Get carrier failed. ", e);
            return "";
        }
    }

    /* renamed from: e */
    public static List<C3593aw> m1148e() {
        ArrayList arrayList = new ArrayList();
        for (File file : new File("/proc").listFiles()) {
            if (file.isDirectory()) {
                try {
                    arrayList.add(new C3593aw(Integer.parseInt(file.getName())));
                } catch (C3593aw.b | IOException | NumberFormatException unused) {
                }
            }
        }
        return arrayList;
    }

    /* renamed from: e */
    public static void m1149e(Context context, JSONObject jSONObject) {
        WifiInfo wifiInfo;
        try {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
            List<ScanResult> list = null;
            if (wifiManager == null || !wifiManager.isWifiEnabled()) {
                wifiInfo = null;
            } else {
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                list = wifiManager.getScanResults();
                wifiInfo = connectionInfo;
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
            C3589as.m1067d("Collector", "get wifi list failed:" + e);
        }
    }

    /* renamed from: f */
    public static Location m1150f(Context context) {
        if (!(m1136a(context, "android.permission.ACCESS_COARSE_LOCATION") || m1136a(context, "android.permission.ACCESS_FINE_LOCATION"))) {
            C3589as.m1067d("Collector", "No permission to access location");
            return null;
        }
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService(RequestParameters.SUBRESOURCE_LOCATION);
            List<String> providers = locationManager.getProviders(true);
            if (providers != null && !providers.isEmpty()) {
                Location m1127a = providers.contains("gps") ? m1127a(locationManager, "gps") : null;
                return (m1127a == null && providers.contains("network")) ? m1127a(locationManager, "network") : m1127a;
            }
            return null;
        } catch (Exception e) {
            C3589as.m1064b("Collector", "Get location failed", e);
            return null;
        }
    }

    /* renamed from: f */
    public static void m1151f(Context context, JSONObject jSONObject) {
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
    private static boolean m1152f() {
        try {
            Build.class.getMethod("hasSmartBar", new Class[0]);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: g */
    private static String m1153g() {
        return m1131a("ro.build.hw_emui_api_level", "");
    }

    /* renamed from: g */
    public static String m1154g(Context context) {
        TelephonyManager telephonyManager;
        if (!m1136a(context, "android.permission.READ_PHONE_STATE") || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null) {
            return null;
        }
        return telephonyManager.getDeviceId();
    }

    /* renamed from: h */
    private static String m1155h() {
        return m1131a("ro.yunos.version", "");
    }

    /* renamed from: h */
    public static String m1156h(Context context) {
        TelephonyManager telephonyManager;
        if (!m1136a(context, "android.permission.READ_PHONE_STATE") || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null) {
            return null;
        }
        return telephonyManager.getSubscriberId();
    }

    /* renamed from: i */
    private static String m1157i() {
        try {
            String lowerCase = m1131a("ro.build.display.id", "").toLowerCase(Locale.getDefault());
            return lowerCase.contains("flyme os") ? lowerCase.split(" ")[2] : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: i */
    public static String m1158i(Context context) {
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
            List<C3593aw> m1148e = m1148e();
            if (m1148e != null && !m1148e.isEmpty()) {
                HashSet hashSet = new HashSet();
                for (C3593aw c3593aw : m1148e) {
                    if (c3593aw.f2394a >= 10000) {
                        String m1105b = c3593aw.m1105b();
                        if (!hashSet.contains(m1105b)) {
                            hashSet.add(m1105b);
                            sb.append(m1105b);
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
            C3589as.m1068e("Collector", th.toString());
            return null;
        }
    }

    /* renamed from: j */
    public static String m1159j(Context context) {
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
    private static boolean m1160j() {
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
    public static String m1161k(Context context) {
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
            C3589as.m1068e("Collector", th.toString());
            return null;
        }
    }

    /* renamed from: k */
    private static boolean m1162k() {
        String[] strArr = {"/bin", "/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/", "/su/bin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"};
        for (int i = 0; i < 12; i++) {
            if (new File(strArr[i] + ShellUtils.COMMAND_SU).exists()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: l */
    public static String m1163l(Context context) {
        BluetoothAdapter defaultAdapter;
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                return Settings.Secure.getString(context.getContentResolver(), "bluetooth_address");
            }
            if (!m1136a(context, "android.permission.BLUETOOTH") || (defaultAdapter = BluetoothAdapter.getDefaultAdapter()) == null) {
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
    public static String m1164m(Context context) {
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
