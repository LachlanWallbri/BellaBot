package com.loc;

import android.app.ActivityManager;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.location.Location;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.Base64;
import com.amap.api.fence.GeoFenceManagerBase;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.DPoint;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.json.JSONObject;

/* compiled from: Utils.java */
/* renamed from: com.loc.cx */
/* loaded from: classes4.dex */
public final class C3876cx {

    /* renamed from: b */
    private static int f4073b;

    /* renamed from: c */
    private static String[] f4074c;

    /* renamed from: d */
    private static Hashtable<String, Long> f4075d = new Hashtable<>();

    /* renamed from: e */
    private static SimpleDateFormat f4076e = null;

    /* renamed from: f */
    private static String[] f4077f = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};

    /* renamed from: a */
    static String f4072a = null;

    /* renamed from: a */
    public static double m2960a(double d) {
        return ((long) (d * 1000000.0d)) / 1000000.0d;
    }

    /* renamed from: a */
    public static float m2961a(float f) {
        return (float) (((long) (f * 100.0d)) / 100.0d);
    }

    /* renamed from: a */
    public static float m2962a(AMapLocation aMapLocation, AMapLocation aMapLocation2) {
        return m2964a(new double[]{aMapLocation.getLatitude(), aMapLocation.getLongitude(), aMapLocation2.getLatitude(), aMapLocation2.getLongitude()});
    }

    /* renamed from: a */
    public static float m2963a(DPoint dPoint, DPoint dPoint2) {
        return m2964a(new double[]{dPoint.getLatitude(), dPoint.getLongitude(), dPoint2.getLatitude(), dPoint2.getLongitude()});
    }

    /* renamed from: a */
    public static float m2964a(double[] dArr) {
        if (dArr.length != 4) {
            return 0.0f;
        }
        float[] fArr = new float[1];
        Location.distanceBetween(dArr[0], dArr[1], dArr[2], dArr[3], fArr);
        return fArr[0];
    }

    /* renamed from: a */
    public static int m2965a(int i) {
        return (i * 2) - 113;
    }

    /* renamed from: a */
    public static int m2966a(int i, int i2) {
        return new Random().nextInt((i2 - i) + 1) + i;
    }

    /* renamed from: a */
    public static int m2967a(NetworkInfo networkInfo) {
        if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
            return networkInfo.getType();
        }
        return -1;
    }

    /* renamed from: a */
    public static int m2968a(boolean z, CellLocation cellLocation) {
        if (z || cellLocation == null) {
            return 0;
        }
        if (cellLocation instanceof GsmCellLocation) {
            return 1;
        }
        try {
            Class.forName("android.telephony.cdma.CdmaCellLocation");
            return 2;
        } catch (Throwable th) {
            C3880f.m3097a(th, "Utils", "getCellLocT");
            return 0;
        }
    }

    /* renamed from: a */
    public static long m2969a() {
        return System.currentTimeMillis();
    }

    /* renamed from: a */
    public static Object m2970a(Context context, String str) {
        if (context == null) {
            return null;
        }
        try {
            return context.getApplicationContext().getSystemService(str);
        } catch (Throwable th) {
            C3880f.m3097a(th, "Utils", "getServ");
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0055 A[Catch: all -> 0x0058, DONT_GENERATE, TRY_LEAVE, TryCatch #2 {all -> 0x0058, blocks: (B:33:0x0050, B:26:0x0055), top: B:32:0x0050 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0050 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m2971a(double d, float f) {
        FileOutputStream fileOutputStream;
        String m3019l;
        DataOutputStream dataOutputStream;
        DataOutputStream dataOutputStream2 = null;
        try {
            if (!m3018k() || (m3019l = m3019l()) == null) {
                return;
            }
            if (!TextUtils.isEmpty(m3019l)) {
                File file = new File(m3019l);
                if (file.canWrite()) {
                    if (file.exists()) {
                        file.delete();
                        file.createNewFile();
                    }
                    fileOutputStream = new FileOutputStream(file);
                    try {
                        dataOutputStream = new DataOutputStream(fileOutputStream);
                    } catch (Throwable th) {
                        th = th;
                    }
                    try {
                        dataOutputStream.writeDouble(d);
                        dataOutputStream.writeFloat(f);
                        dataOutputStream.writeLong(System.currentTimeMillis());
                        dataOutputStream.flush();
                        dataOutputStream2 = dataOutputStream;
                        if (dataOutputStream2 != null) {
                            try {
                                dataOutputStream2.close();
                            } catch (Throwable unused) {
                                return;
                            }
                        }
                        if (fileOutputStream == null) {
                            return;
                        } else {
                            return;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        dataOutputStream2 = dataOutputStream;
                        try {
                            th.printStackTrace();
                            if (dataOutputStream2 != null) {
                                try {
                                    dataOutputStream2.close();
                                } catch (Throwable unused2) {
                                    return;
                                }
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                                return;
                            }
                            return;
                        } finally {
                            if (dataOutputStream2 != null) {
                                try {
                                    dataOutputStream2.close();
                                } catch (Throwable unused3) {
                                }
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                        }
                    }
                }
            }
            fileOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
        }
    }

    /* renamed from: a */
    public static void m2972a(Context context, int i) {
        if (context == null) {
            return;
        }
        try {
            C3804af c3804af = new C3804af(context, C3804af.m2421a((Class<? extends InterfaceC3803ae>) C3863ck.class), m3016i());
            C3859cg c3859cg = new C3859cg();
            c3859cg.m2823a(i);
            c3804af.m2429a(c3859cg, "_id=1");
        } catch (Throwable th) {
            C3880f.m3097a(th, "Utils", "getDBConfigVersion");
        }
    }

    /* renamed from: a */
    public static boolean m2973a(long j, long j2) {
        SimpleDateFormat simpleDateFormat = f4076e;
        if (simpleDateFormat == null) {
            try {
                f4076e = new SimpleDateFormat("yyyyMMddHH", Locale.CHINA);
            } catch (Throwable th) {
                C3880f.m3097a(th, "Utils", "isSameDay part1");
            }
        } else {
            simpleDateFormat.applyPattern("yyyyMMddHH");
        }
        try {
            if (f4076e != null) {
                return f4076e.format(Long.valueOf(j)).equals(f4076e.format(Long.valueOf(j2)));
            }
            return false;
        } catch (Throwable th2) {
            C3880f.m3097a(th2, "Utils", "isSameHour");
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m2974a(Context context) {
        if (context == null) {
            return false;
        }
        try {
            return m2996c() < 17 ? m3000c(context, "android.provider.Settings$System") : m3000c(context, "android.provider.Settings$Global");
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m2975a(Location location, int i) {
        Bundle extras = location.getExtras();
        if ((extras != null ? extras.getInt("satellites") : 0) <= 0) {
            return true;
        }
        return i == 0 && location.getAltitude() == 0.0d && location.getBearing() == 0.0f && location.getSpeed() == 0.0f;
    }

    /* renamed from: a */
    public static boolean m2976a(AMapLocation aMapLocation) {
        if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
            return m2992b(aMapLocation);
        }
        return false;
    }

    /* renamed from: a */
    public static boolean m2977a(AMapLocationServer aMapLocationServer) {
        if (aMapLocationServer == null || aMapLocationServer.m549c().equals("8") || aMapLocationServer.m549c().equals("5") || aMapLocationServer.m549c().equals("6")) {
            return false;
        }
        return m2992b(aMapLocationServer);
    }

    /* renamed from: a */
    public static boolean m2978a(String str) {
        if (TextUtils.isEmpty(str) || !TextUtils.isDigitsOnly(str)) {
            return false;
        }
        return ",111,123,134,199,202,204,206,208,212,213,214,216,218,219,220,222,225,226,228,230,231,232,234,235,238,240,242,244,246,247,248,250,255,257,259,260,262,266,268,270,272,274,276,278,280,282,283,284,286,288,289,290,292,293,294,295,297,302,308,310,311,312,313,314,315,316,310,330,332,334,338,340,342,344,346,348,350,352,354,356,358,360,362,363,364,365,366,368,370,372,374,376,400,401,402,404,405,406,410,412,413,414,415,416,417,418,419,420,421,422,424,425,426,427,428,429,430,431,432,434,436,437,438,440,441,450,452,454,455,456,457,466,467,470,472,502,505,510,514,515,520,525,528,530,534,535,536,537,539,540,541,542,543,544,545,546,547,548,549,550,551,552,553,555,560,598,602,603,604,605,606,607,608,609,610,611,612,613,614,615,616,617,618,619,620,621,622,623,624,625,626,627,628,629,630,631,632,633,634,635,636,637,638,639,640,641,642,643,645,646,647,648,649,650,651,652,653,654,655,657,659,665,702,704,706,708,710,712,714,716,722,724,730,732,734,736,738,740,742,744,746,748,750,850,901,".contains("," + str + ",");
    }

    /* renamed from: a */
    public static boolean m2979a(JSONObject jSONObject, String str) {
        return C3894t.m3230a(jSONObject, str);
    }

    /* renamed from: a */
    public static byte[] m2980a(int i, byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            bArr = new byte[2];
        }
        bArr[0] = (byte) (i & 255);
        bArr[1] = (byte) ((i & 65280) >> 8);
        return bArr;
    }

    /* renamed from: a */
    public static byte[] m2981a(long j) {
        byte[] bArr = new byte[8];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) ((j >> (i * 8)) & 255);
        }
        return bArr;
    }

    /* renamed from: a */
    public static byte[] m2982a(byte[] bArr) {
        try {
            return C3894t.m3236b(bArr);
        } catch (Throwable th) {
            C3880f.m3097a(th, "Utils", CompressorStreamFactory.GZIP);
            return null;
        }
    }

    /* renamed from: a */
    public static String[] m2983a(TelephonyManager telephonyManager) {
        int i;
        String[] strArr;
        String networkOperator = telephonyManager != null ? telephonyManager.getNetworkOperator() : null;
        String[] strArr2 = {"0", "0"};
        if (!TextUtils.isEmpty(networkOperator) && TextUtils.isDigitsOnly(networkOperator) && networkOperator.length() > 4) {
            strArr2[0] = networkOperator.substring(0, 3);
            char[] charArray = networkOperator.substring(3).toCharArray();
            int i2 = 0;
            while (i2 < charArray.length && Character.isDigit(charArray[i2])) {
                i2++;
            }
            strArr2[1] = networkOperator.substring(3, i2 + 3);
        }
        try {
            i = Integer.parseInt(strArr2[0]);
        } catch (Throwable th) {
            C3880f.m3097a(th, "Utils", "getMccMnc");
            i = 0;
        }
        if (i == 0) {
            strArr2[0] = "0";
        }
        if (!strArr2[0].equals("0") && !strArr2[1].equals("0")) {
            f4074c = strArr2;
        } else if (strArr2[0].equals("0") && strArr2[1].equals("0") && (strArr = f4074c) != null) {
            return strArr;
        }
        return strArr2;
    }

    /* renamed from: b */
    public static double m2984b(double d) {
        return ((long) (d * 100.0d)) / 100.0d;
    }

    /* renamed from: b */
    public static long m2985b() {
        return SystemClock.elapsedRealtime();
    }

    /* renamed from: b */
    public static String m2986b(int i) {
        switch (i) {
            case 0:
                return "success";
            case 1:
                return "重要参数为空";
            case 2:
                return "WIFI信息不足";
            case 3:
                return "请求参数获取出现异常";
            case 4:
                return "网络连接异常";
            case 5:
                return "解析数据异常";
            case 6:
                return "定位结果错误";
            case 7:
                return "KEY错误";
            case 8:
            default:
                return "其他错误";
            case 9:
                return "初始化异常";
            case 10:
                return "定位服务启动失败";
            case 11:
                return "错误的基站信息，请检查是否插入SIM卡";
            case 12:
                return "缺少定位权限";
            case 13:
                return "网络定位失败，请检查设备是否插入sim卡，是否开启移动网络或开启了wifi模块";
            case 14:
                return "GPS 定位失败，由于设备当前 GPS 状态差,建议持设备到相对开阔的露天场所再次尝试";
            case 15:
                return "当前返回位置为模拟软件返回，请关闭模拟软件，或者在option中设置允许模拟";
        }
    }

    /* renamed from: b */
    public static String m2987b(Context context) {
        PackageInfo packageInfo;
        if (!TextUtils.isEmpty(C3880f.f4182d)) {
            return C3880f.f4182d;
        }
        if (context == null) {
            return null;
        }
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 64);
        } catch (Throwable th) {
            C3880f.m3097a(th, "Utils", "getAppName part");
            packageInfo = null;
        }
        try {
            if (TextUtils.isEmpty(C3880f.f4183e)) {
                C3880f.f4183e = null;
            }
        } catch (Throwable th2) {
            C3880f.m3097a(th2, "Utils", "getAppName");
        }
        StringBuilder sb = new StringBuilder();
        if (packageInfo != null) {
            CharSequence loadLabel = packageInfo.applicationInfo != null ? packageInfo.applicationInfo.loadLabel(context.getPackageManager()) : null;
            if (loadLabel != null) {
                sb.append(loadLabel.toString());
            }
            if (!TextUtils.isEmpty(packageInfo.versionName)) {
                sb.append(packageInfo.versionName);
            }
        }
        String m3124c = C3885k.m3124c(context);
        if (!TextUtils.isEmpty(m3124c)) {
            sb.append(",");
            sb.append(m3124c);
        }
        if (!TextUtils.isEmpty(C3880f.f4183e)) {
            sb.append(",");
            sb.append(C3880f.f4183e);
        }
        String sb2 = sb.toString();
        C3880f.f4182d = sb2;
        return sb2;
    }

    /* renamed from: b */
    public static String m2988b(TelephonyManager telephonyManager) {
        return C3880f.f4188j.get(telephonyManager != null ? telephonyManager.getNetworkType() : 0, "UNKWN");
    }

    /* renamed from: b */
    public static String m2989b(byte[] bArr) {
        return C3894t.m3243f(bArr);
    }

    /* renamed from: b */
    public static boolean m2990b(long j, long j2) {
        SimpleDateFormat simpleDateFormat = f4076e;
        if (simpleDateFormat == null) {
            try {
                f4076e = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
            } catch (Throwable th) {
                C3880f.m3097a(th, "Utils", "isSameDay part1");
            }
        } else {
            simpleDateFormat.applyPattern("yyyyMMdd");
        }
        try {
            if (f4076e != null) {
                return f4076e.format(Long.valueOf(j)).equals(f4076e.format(Long.valueOf(j2)));
            }
            return false;
        } catch (Throwable th2) {
            C3880f.m3097a(th2, "Utils", "isSameDay");
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m2991b(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0) != null;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m2992b(AMapLocation aMapLocation) {
        double longitude = aMapLocation.getLongitude();
        double latitude = aMapLocation.getLatitude();
        return !(longitude == 0.0d && latitude == 0.0d) && longitude <= 180.0d && latitude <= 90.0d && longitude >= -180.0d && latitude >= -90.0d;
    }

    /* renamed from: b */
    public static boolean m2993b(String str) {
        return (TextUtils.isEmpty(str) || str.equals("00:00:00:00:00:00") || str.contains(" :")) ? false : true;
    }

    /* renamed from: b */
    public static byte[] m2994b(int i, byte[] bArr) {
        if (bArr == null || bArr.length < 4) {
            bArr = new byte[4];
        }
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = (byte) ((i >> (i2 * 8)) & 255);
        }
        return bArr;
    }

    /* renamed from: c */
    public static double m2995c(double d) {
        return ((long) (d * 1000000.0d)) / 1000000.0d;
    }

    /* renamed from: c */
    public static int m2996c() {
        int i = f4073b;
        if (i > 0) {
            return i;
        }
        try {
            try {
                return ((Integer) C3871cs.m2918a("android.os.Build$VERSION", "SDK_INT")).intValue();
            } catch (Throwable unused) {
                return Integer.parseInt(C3871cs.m2918a("android.os.Build$VERSION", "SDK").toString());
            }
        } catch (Throwable unused2) {
            return 0;
        }
    }

    /* renamed from: c */
    public static NetworkInfo m2997c(Context context) {
        try {
            return C3888n.m3165n(context);
        } catch (Throwable th) {
            C3880f.m3097a(th, "Utils", "getNetWorkInfo");
            return null;
        }
    }

    /* renamed from: c */
    public static String m2998c(String str) {
        return m3013g(str);
    }

    /* renamed from: c */
    public static boolean m2999c(long j, long j2) {
        if (!m2990b(j, j2)) {
            return false;
        }
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTimeInMillis(j);
        int i = calendar.get(11);
        calendar.setTimeInMillis(j2);
        int i2 = calendar.get(11);
        if (i > 12) {
            if (i2 > 12) {
                return true;
            }
        } else if (i2 <= 12) {
            return true;
        }
        return false;
    }

    /* renamed from: c */
    private static boolean m3000c(Context context, String str) throws Throwable {
        return ((Integer) C3871cs.m2919a(str, "getInt", new Object[]{context.getContentResolver(), ((String) C3871cs.m2918a(str, "AIRPLANE_MODE_ON")).toString()}, (Class<?>[]) new Class[]{ContentResolver.class, String.class})).intValue() == 1;
    }

    /* renamed from: c */
    public static byte[] m3001c(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length + 1];
        bArr2[0] = 0;
        for (int i = 1; i <= bArr.length; i++) {
            bArr2[i] = bArr[bArr.length - i];
        }
        return bArr2;
    }

    /* renamed from: d */
    public static String m3002d() {
        return Build.MODEL;
    }

    /* renamed from: d */
    public static String m3003d(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        try {
            return new String(Base64.decode(str, 0), "UTF-8");
        } catch (Throwable th) {
            C3880f.m3097a(th, "Utils", "base642Str");
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002e, code lost:
    
        if (r2.importance == 100) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0030, code lost:
    
        return true;
     */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean m3004d(Context context) {
        try {
            Iterator<ActivityManager.RunningAppProcessInfo> it = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ActivityManager.RunningAppProcessInfo next = it.next();
                if (next.processName.equals(context.getPackageName())) {
                    break;
                }
            }
            return false;
        } catch (Throwable th) {
            C3880f.m3097a(th, "Utils", "isApplicationBroughtToBackground");
            return true;
        }
    }

    /* renamed from: d */
    public static byte[] m3005d(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        System.arraycopy(bArr, 10, bArr2, 0, Math.min(26, bArr.length) - 10);
        return bArr2;
    }

    /* renamed from: e */
    public static int m3006e(Context context) {
        try {
            List m2433b = new C3804af(context, C3804af.m2421a((Class<? extends InterfaceC3803ae>) C3863ck.class), m3016i()).m2433b("_id=1", C3859cg.class);
            if (m2433b == null || m2433b.size() <= 0) {
                return -1;
            }
            return ((C3859cg) m2433b.get(0)).m2822a();
        } catch (Throwable th) {
            C3880f.m3097a(th, "Utils", "getDBConfigVersion");
            return -1;
        }
    }

    /* renamed from: e */
    public static String m3007e() {
        return Build.VERSION.RELEASE;
    }

    /* renamed from: e */
    public static byte[] m3008e(String str) {
        return m2980a(Integer.parseInt(str), (byte[]) null);
    }

    /* renamed from: f */
    public static boolean m3009f() {
        return m2966a(0, 1) == 1;
    }

    /* renamed from: f */
    public static boolean m3010f(Context context) {
        int i;
        if (Build.VERSION.SDK_INT < 23 || context.getApplicationInfo().targetSdkVersion < 23) {
            for (String str : f4077f) {
                if (context.checkCallingOrSelfPermission(str) != 0) {
                    return false;
                }
            }
        } else {
            Application application = (Application) context;
            for (String str2 : f4077f) {
                try {
                    i = C3871cs.m2920b(application.getBaseContext(), "checkSelfPermission", str2);
                } catch (Throwable unused) {
                    i = 0;
                }
                if (i != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /* renamed from: f */
    public static byte[] m3011f(String str) {
        return m2994b(Integer.parseInt(str), (byte[]) null);
    }

    /* renamed from: g */
    public static GeoFenceManagerBase m3012g(Context context) {
        GeoFenceManagerBase c3798a;
        try {
            c3798a = (GeoFenceManagerBase) C3819au.m2476a(context, C3880f.m3091a("loc"), "com.amap.api.fence.GeoFenceManagerWrapper", C3798a.class, new Class[]{Context.class}, new Object[]{context});
        } catch (Throwable unused) {
            c3798a = new C3798a(context);
        }
        return c3798a == null ? new C3798a(context) : c3798a;
    }

    /* renamed from: g */
    private static String m3013g(String str) {
        byte[] bArr;
        try {
            bArr = str.getBytes("UTF-8");
        } catch (Throwable th) {
            C3880f.m3097a(th, "Utils", "str2Base64");
            bArr = null;
        }
        return Base64.encodeToString(bArr, 0);
    }

    /* renamed from: g */
    public static void m3014g() {
        f4075d.clear();
    }

    /* renamed from: h */
    public static String m3015h() {
        try {
            return C3889o.m3178a("S128DF1572465B890OE3F7A13167KLEI".getBytes("UTF-8")).substring(20);
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: i */
    public static String m3016i() {
        if (!m3018k()) {
            return null;
        }
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "amap" + File.separator + "openamaplocationsdk" + File.separator;
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0068, code lost:
    
        if (r1 != null) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x006a, code lost:
    
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x007b, code lost:
    
        if (r1 != null) goto L32;
     */
    /* renamed from: j */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static JSONObject m3017j() {
        DataInputStream dataInputStream;
        JSONObject jSONObject;
        Throwable th;
        FileInputStream fileInputStream;
        String m3019l;
        DataInputStream dataInputStream2 = null;
        try {
            try {
                if (!m3018k() || (m3019l = m3019l()) == null) {
                    return null;
                }
                if (TextUtils.isEmpty(m3019l)) {
                    fileInputStream = null;
                    jSONObject = null;
                } else {
                    File file = new File(m3019l);
                    if (!file.exists() || !file.canWrite()) {
                        return null;
                    }
                    fileInputStream = new FileInputStream(file);
                    try {
                        dataInputStream = new DataInputStream(fileInputStream);
                        try {
                            jSONObject = new JSONObject();
                        } catch (Throwable th2) {
                            jSONObject = null;
                            th = th2;
                        }
                    } catch (Throwable th3) {
                        jSONObject = null;
                        th = th3;
                        dataInputStream = null;
                    }
                    try {
                        jSONObject.put("altitude", dataInputStream.readDouble());
                        jSONObject.put("pressure", dataInputStream.readFloat());
                        jSONObject.put("sysTime", dataInputStream.readLong());
                        dataInputStream2 = dataInputStream;
                    } catch (Throwable th4) {
                        th = th4;
                        try {
                            th.printStackTrace();
                            if (dataInputStream != null) {
                                dataInputStream.close();
                            }
                        } catch (Throwable th5) {
                            if (dataInputStream != null) {
                                try {
                                    dataInputStream.close();
                                } catch (Throwable unused) {
                                    throw th5;
                                }
                            }
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            throw th5;
                        }
                    }
                }
                if (dataInputStream2 != null) {
                    dataInputStream2.close();
                }
            } catch (Throwable th6) {
                dataInputStream = null;
                jSONObject = null;
                th = th6;
                fileInputStream = null;
            }
        } catch (Throwable unused2) {
        }
        return jSONObject;
    }

    /* renamed from: k */
    private static boolean m3018k() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    /* renamed from: l */
    private static String m3019l() {
        try {
        } catch (Throwable th) {
            C3880f.m3097a(th, "GPSInfoManager", "getFilePath");
        }
        if (f4072a != null) {
            return f4072a;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(m3016i());
        sb.append("a.dat");
        f4072a = sb.toString();
        File parentFile = new File(f4072a).getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        sb.delete(0, sb.length());
        return f4072a;
    }
}
