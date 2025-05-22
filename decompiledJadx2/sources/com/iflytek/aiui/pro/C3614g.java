package com.iflytek.aiui.pro;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.location.Location;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.os.AsyncTask;
import android.os.Build;
import android.os.SystemClock;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.SparseArray;
import com.amazonaws.services.p048s3.internal.crypto.JceEncryptionConstants;
import com.iflytek.aiui.pro.C3620j;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* renamed from: com.iflytek.aiui.pro.g */
/* loaded from: classes.dex */
public class C3614g {

    /* renamed from: a */
    public static final SparseArray<String> f2523a;

    /* renamed from: b */
    private static boolean f2524b = false;

    /* renamed from: c */
    private static boolean f2525c = false;

    /* renamed from: d */
    private static long f2526d = 7;

    /* renamed from: e */
    private static long f2527e = 1;

    /* renamed from: f */
    private static int f2528f;

    /* renamed from: g */
    private static String[] f2529g;

    /* renamed from: h */
    private static SimpleDateFormat f2530h;

    /* renamed from: i */
    private static volatile Method f2531i;

    /* renamed from: com.iflytek.aiui.pro.g$a */
    /* loaded from: classes4.dex */
    static final class a extends AsyncTask<Void, Void, Void> {

        /* renamed from: a */
        final /* synthetic */ SharedPreferences.Editor f2533a;

        a(SharedPreferences.Editor editor) {
            this.f2533a = editor;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            try {
                SharedPreferences.Editor editor = this.f2533a;
                if (editor == null) {
                    return null;
                }
                editor.commit();
                return null;
            } catch (Throwable th) {
                C3614g.n(th, "SPUtil", "commit");
                return null;
            }
        }
    }

    static {
        SparseArray<String> sparseArray = new SparseArray<>();
        f2523a = sparseArray;
        sparseArray.append(0, "UNKWN");
        f2523a.append(1, "GPRS");
        f2523a.append(2, "EDGE");
        f2523a.append(3, "UMTS");
        f2523a.append(4, "CDMA");
        f2523a.append(5, "EVDO_0");
        f2523a.append(6, "EVDO_A");
        f2523a.append(7, "1xRTT");
        f2523a.append(8, "HSDPA");
        f2523a.append(9, "HSUPA");
        f2523a.append(10, "HSPA");
        f2523a.append(11, "IDEN");
        f2523a.append(12, "EVDO_B");
        f2523a.append(13, "LTE");
        f2523a.append(14, "EHRPD");
        f2523a.append(15, "HSPAP");
        f2530h = null;
    }

    /* renamed from: a */
    public static int m1295a(int i) {
        return (i * 2) - 113;
    }

    /* renamed from: a */
    public static int m1296a(int i, int i2) {
        return new Random().nextInt((i2 - i) + 1) + i;
    }

    /* renamed from: a */
    public static int m1297a(NetworkInfo networkInfo) {
        if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
            return networkInfo.getType();
        }
        return -1;
    }

    /* renamed from: a */
    public static int m1298a(CellLocation cellLocation, Context context) {
        if (!m1311a(context) && cellLocation != null) {
            if (cellLocation instanceof GsmCellLocation) {
                return 1;
            }
            try {
                Class.forName("android.telephony.cdma.CdmaCellLocation");
                return 2;
            } catch (Throwable th) {
                m1308a(th, "CoreUtil", "getCellLocT");
            }
        }
        return 9;
    }

    /* renamed from: a */
    public static C3626m m1299a(String str) {
        try {
            return new C3626m("channelloc", str);
        } catch (Throwable th) {
            m1308a(th, "CoreUtil", "getSDKInfo");
            return null;
        }
    }

    /* renamed from: a */
    public static Object m1300a(Context context, String str) {
        if (context == null) {
            return null;
        }
        try {
            return context.getApplicationContext().getSystemService(str);
        } catch (Throwable th) {
            m1308a(th, "CoreUtil", "getServ");
            return null;
        }
    }

    /* renamed from: a */
    public static Object m1301a(Object obj, String str, Object... objArr) throws Exception {
        Class<?> cls = obj.getClass();
        Class<?>[] clsArr = new Class[objArr.length];
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            clsArr[i] = objArr[i].getClass();
            if (clsArr[i] == Integer.class) {
                clsArr[i] = Integer.TYPE;
            }
        }
        Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
        if (!declaredMethod.isAccessible()) {
            declaredMethod.setAccessible(true);
        }
        return declaredMethod.invoke(obj, objArr);
    }

    /* renamed from: a */
    public static Object m1302a(String str, String str2, Object[] objArr, Class<?>[] clsArr) throws Exception {
        Method declaredMethod = Class.forName(str).getDeclaredMethod(str2, clsArr);
        if (!declaredMethod.isAccessible()) {
            declaredMethod.setAccessible(true);
        }
        return declaredMethod.invoke(null, objArr);
    }

    /* renamed from: a */
    public static String m1303a() {
        return "http://apilocate.amap.com/mobile/sdkchannel";
    }

    /* renamed from: a */
    public static String m1304a(Context context, String str, String str2, String str3) {
        try {
            return context.getSharedPreferences(str, 0).getString(str2, str3);
        } catch (Throwable th) {
            m1308a(th, "CoreUtil", "getPrefsLong");
            return str3;
        }
    }

    /* renamed from: a */
    public static String m1305a(byte[] bArr) {
        try {
            byte[] bArr2 = new byte[16];
            byte[] bArr3 = new byte[bArr.length - 16];
            System.arraycopy(bArr, 0, bArr2, 0, 16);
            System.arraycopy(bArr, 16, bArr3, 0, bArr.length - 16);
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(JceEncryptionConstants.SYMMETRIC_CIPHER_METHOD);
            cipher.init(2, secretKeySpec, new IvParameterSpec(C3628n.m1414a()));
            return new String(cipher.doFinal(bArr3), "UTF-8");
        } catch (Throwable th) {
            m1308a(th, "CoreUtil", "decryptRsponse");
            return null;
        }
    }

    /* renamed from: a */
    public static JSONObject m1306a(Location location) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject();
            try {
                jSONObject.put("altitude", location.getAltitude());
                jSONObject.put("speed", location.getSpeed());
                jSONObject.put("bearing", location.getBearing());
            } catch (Throwable unused) {
            }
        } catch (Throwable th) {
            th = th;
            jSONObject = null;
        }
        try {
            jSONObject.put("provider", location.getProvider());
            jSONObject.put("lon", location.getLongitude());
            jSONObject.put("lat", location.getLatitude());
            jSONObject.put("accuracy", location.getAccuracy());
            return jSONObject;
        } catch (Throwable th2) {
            th = th2;
            m1308a(th, "AmapLoc", "toStr");
            return jSONObject;
        }
    }

    /* renamed from: a */
    public static void m1307a(SharedPreferences.Editor editor) {
        if (editor == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 9) {
            m1326b(editor);
            return;
        }
        try {
            if (f2531i == null) {
                f2531i = SharedPreferences.Editor.class.getDeclaredMethod("apply", new Class[0]);
            }
            f2531i.invoke(editor, new Object[0]);
        } catch (Throwable th) {
            m1308a(th, "CoreUtil", "applySharedPreference");
            m1326b(editor);
        }
    }

    /* renamed from: a */
    public static void m1308a(Throwable th, String str, String str2) {
    }

    /* renamed from: a */
    public static boolean m1309a(double d, double d2) {
        int i = (int) ((d2 - 73.0d) / 0.5d);
        int i2 = (int) ((d - 3.5d) / 0.5d);
        if (i2 < 0 || i2 >= 101 || i < 0 || i >= 124) {
            return false;
        }
        try {
            return "00000000000000000000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001100000001011000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011101010111100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000110111111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111101111110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001000110111100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011010111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001110011100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010001000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000110000001000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001010011100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111100110001000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011111000111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111110011000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111000000000111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111100000000000010111110100000011000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111110000000001111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111111111000000111111111111111110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011111111111101111111111111111111111100000000000000000000000000000000000000000000000000000000000000000000000000000000000000011111111111111111111111111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000101111111111111111111111111111111111111110000000000000000000000000000000000000000000000000000000000000000000000000000000000011111111111111111111111111111111111111111100000000000000000000000000000000000000000000000000000000000000000000000000000000001111111111111111111111111111111111111111111100000000000000000000000000000000000000000000000000000000000000000000000000000000111111111111111111111111111111111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000001111111111111111111111111111111111111111111100000000000000000000000000000000000000000000000000000000000000000000000000000000011111111111111111111111111111111111111111111100000000000000000000000000000000000000000000000000000000000000000011110000000001111111111111111111111111111111111111111111110000000000000000000000000000000000000000000000000000000000011000011111100000000111111111111111111111111111111111111111111111100000000000000000000000000000000000000000000000000001111111100111111111100110111111111111111111111111111111111111111111111110000000000000000000000000000000000000000000000000001111111111111111111111111111111111111111111111111111111111111111111111111100000000000000000000000000000000000000000000000011111111111111111111111111111111111111111111111111111111111111111111111111111000000000000000000000000000000000000000000000111111111111111111111111111111111111111111111111111111111111111111111111111111100000000000000000000000000000000000000000101111111111111111111111111111111111111111111111111111111111111111111111111111111111000000000000000000000000000000000000001111111111111111111111111111111111111111111111111111111111111111111111111111111111111100000000000000000000000000000000000011111111111111111111111111111111111111111111111111111111111111111111111111111111111111110000000000000000000000000000000000011111111111111111111111111111111111111111111111111111111111111111111111111111111111111110000000000000000000000000000000000001111111111111111111111111111111111111111111111111111111111111111111111111111111111111110000000000000000000000000000000000001111111111111111111111111111111111111111111111111111111111111111111111111111111111111111000000000000000000000000000000000000111111111111111111111111111111111111111111111111111111111111111111111111111111111111111000000000000000000000000000000000000001111111111111111111111111111111111111111111111111111111111111111111111111111111111111000000000000000000000000000000000000000111111111111111111111111111111111111111111111111111111111111111111111111111111111111100000000000000000000000000000000000000011111111111111111111111111111111111111111111111111111111111111111111111111111111111100000000000000000000000000000000000000011111111111111111111111111111111111111111111111111111111111111111111111111111111111100000000000000000000000000000000000000111111111111111111111111111111111111111111111111111111111111111111111111111111111111110000000000000000000000000000000000001111111111111111111111111111111111111111111111111111111111111111111111111111111111111111110000000000000000000000000000000001111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111100000000000000000000000000000011111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111110000000000000000000000000011111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111000000000000000000000000000111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111011111000000000000000000000000000111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111100100000000000000000000000000000111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111100011100000000000000000000000000011111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111000111110000000000000000000000001111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111110011111110000000000000000000000111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111110111111110000000000000000000000111011111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111110000000000000000000000001111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111100000000000000000000000011111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111000000000000000000000000011111111111111111111111111111111111111111111111100001111111111111111111111111111111111111111111111111010000000000000000000000111111111111111111111111111111111111111111110000000000000001111111111111111111111111111111111111111111100000000000000000000011111111111111111111111111111111100000000000000000000000000001111111111111111111111111111111111111111110000000000000000000001111111111111111111111111111111100000000000000000000000000000001111111111111111111111111111111111111111000000000000000000000111111111111111111111111111111110000000000000000000000000000001111111111111111111111111111111111111111100000000000000000000111111111111111111111111111111000000000000000000000000000000000111111111111111111111111111111111111111111000000000000000000001111111111111111111111111110000000000000000000000000000000000001110011111111111111111111111111111111111111100000000000000000000011111111111111111100000000000000000000000000000000000000000000000001111111111111111111111111111111111111000000000000000000001111111111111111111000000000000000000000000000000000000000000000000011111111111111111111111111111111111100000000000000000000011111111111111111100000000000000000000000000000000000000000000000000011111111111111111111111111111111111000000000000000000001111111111111111100000000000000000000000000000000000000000000000000000000111111111111111111111111111111110000000000000000000000000111111111100000000000000000000000000000000000000000000000000111111111111111111111111111111111111111000000000000000000000000011111111100000000000000000000000000000000000000000000000000011111111111111111111111111111110001111100000000000000000000000000111110000000000000000000000000000000000000000000000000000001111111111111111111111111111111000000000000000000000000000000000001110000000000000000000000000000000000000000000000000000000011111111111111111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111111111111111111111100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111111111111111100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011111111111111100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011111111111110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011111111111110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111111110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010110000000000000000000000".charAt(i + (i2 * 124)) == '1';
        } catch (Throwable th) {
            m1308a(th, "CoreUtil", "isChina");
            return true;
        }
    }

    /* renamed from: a */
    private static boolean m1310a(ContentResolver contentResolver, String str) {
        try {
            return ((Integer) m1302a(str, "getInt", new Object[]{contentResolver, ((String) m1322b(str, "AIRPLANE_MODE_ON")).toString()}, (Class<?>[]) new Class[]{ContentResolver.class, String.class})).intValue() == 1;
        } catch (Throwable th) {
            m1308a(th, "CoreUtil", "getAirPlanemodeOn");
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m1311a(Context context) {
        if (context == null) {
            return false;
        }
        return m1310a(context.getContentResolver(), m1334d() < 17 ? "android.provider.Settings$System" : "android.provider.Settings$Global");
    }

    /* renamed from: a */
    private static boolean m1312a(Context context, C3620j.a aVar) {
        try {
            JSONObject jSONObject = aVar.f2565c;
            if (jSONObject != null) {
                f2524b = m1315a(jSONObject);
                m1325b(context, "a1018183c8", "dc92db9bcc", "" + f2524b);
            }
            JSONObject jSONObject2 = aVar.f2566d;
            if (jSONObject2 != null) {
                f2525c = m1315a(jSONObject2);
                m1325b(context, "a1018183c8", "c20878faf7", "" + f2525c);
            }
            JSONObject jSONObject3 = aVar.f2567e;
            if (jSONObject3 != null && m1315a(jSONObject3)) {
                if (jSONObject3.has("m")) {
                    String string = jSONObject3.getString("m");
                    f2527e = Long.parseLong(string);
                    m1325b(context, "a1018183c8", "f13ea469d6", new BigInteger(string).toString(2));
                }
                if (jSONObject3.has("n")) {
                    String string2 = jSONObject3.getString("n");
                    f2526d = Long.parseLong(string2);
                    m1325b(context, "a1018183c8", "e9e74d9323", new BigInteger(string2).toString(2));
                }
            }
            C3626m m1299a = m1299a("1.5.1");
            C3620j.a.C9029a c9029a = aVar.f2564b;
            if (c9029a != null) {
                C3634q.m1485a(context, new C3632p(c9029a.f2568a, c9029a.f2569b, c9029a.f2570c), m1299a);
                return true;
            }
            C3634q.m1485a(context, null, m1299a);
            return true;
        } catch (Throwable th) {
            m1308a(th, "CoreUtil", "loadconfig");
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m1313a(ScanResult scanResult) {
        return (scanResult == null || TextUtils.isEmpty(scanResult.BSSID) || scanResult.BSSID.equals("00:00:00:00:00:00") || scanResult.BSSID.contains(" :")) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x002f  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean m1314a(String str, String str2) {
        long j;
        boolean z;
        synchronized (C3614g.class) {
            long j2 = 0;
            try {
                j = Long.parseLong(str);
                try {
                    j2 = Long.parseLong(str2);
                } catch (NumberFormatException e) {
                    e = e;
                    m1308a(e, "CoreUtil", "isSameDay");
                    if ((((j / 1000) / 60) / 60) / 24 != (((j2 / 1000) / 60) / 60) / 24) {
                    }
                    return z;
                }
            } catch (NumberFormatException e2) {
                e = e2;
                j = 0;
            }
            z = (((j / 1000) / 60) / 60) / 24 != (((j2 / 1000) / 60) / 60) / 24;
        }
        return z;
    }

    /* renamed from: a */
    private static boolean m1315a(JSONObject jSONObject) {
        String optString;
        if (jSONObject == null || (optString = jSONObject.optString("able")) == null || optString.equals("")) {
            return false;
        }
        return C3620j.m1382a(optString, false);
    }

    /* renamed from: a */
    public static byte[] m1316a(long j) {
        byte[] bArr = new byte[8];
        for (int i = 0; i < 8; i++) {
            bArr[i] = (byte) ((j >> (i * 8)) & 255);
        }
        return bArr;
    }

    /* renamed from: a */
    public static byte[] m1317a(byte[] bArr, String str) throws Exception {
        byte[] byteArray;
        synchronized (C3614g.class) {
            PrivateKey generatePrivate = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(C3624l.m1401d(str)));
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(1, generatePrivate);
            int length = bArr.length;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i = 0;
            int i2 = 0;
            while (true) {
                int i3 = length - i;
                if (i3 > 0) {
                    byte[] doFinal = i3 > 245 ? cipher.doFinal(bArr, i, 245) : cipher.doFinal(bArr, i, i3);
                    byteArrayOutputStream.write(doFinal, 0, doFinal.length);
                    i2++;
                    i = i2 * 245;
                } else {
                    byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                }
            }
        }
        return byteArray;
    }

    /* renamed from: a */
    public static String[] m1318a(TelephonyManager telephonyManager) {
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
            m1308a(th, "CoreUtil", "getMccMnc");
            i = 0;
        }
        if (i == 0) {
            strArr2[0] = "0";
        }
        if (strArr2[0].equals("0") || strArr2[1].equals("0")) {
            return (strArr2[0].equals("0") && strArr2[1].equals("0") && (strArr = f2529g) != null) ? strArr : strArr2;
        }
        f2529g = strArr2;
        return strArr2;
    }

    /* renamed from: b */
    public static int m1319b(Object obj, String str, Object... objArr) throws Exception {
        return ((Integer) m1301a(obj, str, objArr)).intValue();
    }

    /* renamed from: b */
    public static long m1320b() {
        return System.currentTimeMillis();
    }

    /* renamed from: b */
    public static NetworkInfo m1321b(Context context) {
        try {
            return C3618i.m1366j(context);
        } catch (Throwable th) {
            m1308a(th, "CoreUtil", "getNetWorkInfo");
            return null;
        }
    }

    /* renamed from: b */
    public static Object m1322b(String str, String str2) throws Exception {
        Class<?> cls = Class.forName(str);
        Field field = cls.getField(str2);
        field.setAccessible(true);
        return field.get(cls);
    }

    /* renamed from: b */
    public static String m1323b(Context context, String str) {
        PackageInfo packageInfo;
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        if (context == null) {
            return null;
        }
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 64);
        } catch (Throwable th) {
            m1308a(th, "CoreUtil", "getAppName part");
            packageInfo = null;
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
        String m1357b = C3618i.m1357b(context);
        if (!TextUtils.isEmpty(m1357b)) {
            sb.append(",");
            sb.append(m1357b);
        }
        return sb.toString();
    }

    /* renamed from: b */
    public static String m1324b(TelephonyManager telephonyManager) {
        return f2523a.get(telephonyManager != null ? telephonyManager.getNetworkType() : 0, "UNKWN");
    }

    /* renamed from: b */
    public static void m1325b(Context context, String str, String str2, String str3) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.putString(str2, str3);
            m1307a(edit);
        } catch (Throwable th) {
            m1308a(th, "CoreUtil", "setPrefsLong");
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.iflytek.aiui.pro.g$1] */
    /* renamed from: b */
    private static void m1326b(final SharedPreferences.Editor editor) {
        try {
            new AsyncTask<Void, Void, Void>() { // from class: com.iflytek.aiui.pro.g.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public Void doInBackground(Void... voidArr) {
                    try {
                        if (editor == null) {
                            return null;
                        }
                        editor.commit();
                        return null;
                    } catch (Throwable th) {
                        C3614g.m1308a(th, "SPUtil", "commit");
                        return null;
                    }
                }
            }.execute(null, null, null);
        } catch (Throwable th) {
            m1308a(th, "CoreUtil", "commit1");
        }
    }

    /* renamed from: b */
    public static byte[] m1327b(int i) {
        byte[] bArr = new byte[2];
        for (int i2 = 0; i2 < 2; i2++) {
            bArr[i2] = (byte) ((i >> (i2 * 8)) & 255);
        }
        return bArr;
    }

    /* renamed from: b */
    public static byte[] m1328b(String str) {
        return m1327b(Integer.parseInt(str));
    }

    /* renamed from: c */
    public static int m1329c(String str, String str2) throws Exception {
        return ((Integer) m1322b(str, str2)).intValue();
    }

    /* renamed from: c */
    public static long m1330c() {
        return SystemClock.elapsedRealtime();
    }

    /* renamed from: c */
    public static boolean m1331c(Context context) {
        boolean m1312a;
        C3620j.a m1379a;
        synchronized (C3614g.class) {
            try {
                m1379a = C3620j.m1379a(context, m1299a("1.5.1"), "132;001;133;134", null);
            } finally {
                return m1312a;
            }
            m1312a = m1379a != null ? m1312a(context, m1379a) : false;
        }
        return m1312a;
    }

    /* renamed from: c */
    public static byte[] m1332c(int i) {
        byte[] bArr = new byte[4];
        for (int i2 = 0; i2 < 4; i2++) {
            bArr[i2] = (byte) ((i >> (i2 * 8)) & 255);
        }
        return bArr;
    }

    /* renamed from: c */
    public static byte[] m1333c(String str) {
        return m1332c(Integer.parseInt(str));
    }

    /* renamed from: d */
    public static int m1334d() {
        int i = f2528f;
        if (i > 0) {
            return i;
        }
        try {
            try {
                return m1329c("android.os.Build$VERSION", "SDK_INT");
            } catch (Throwable unused) {
                return Integer.parseInt(m1322b("android.os.Build$VERSION", "SDK").toString());
            }
        } catch (Throwable th) {
            m1308a(th, "CoreUtil", "getSdk");
            return 0;
        }
    }

    /* renamed from: d */
    public static boolean m1335d(Context context) {
        boolean parseBoolean = Boolean.parseBoolean(m1304a(context, "a1018183c8", "dc92db9bcc", "false"));
        f2524b = parseBoolean;
        return parseBoolean;
    }

    /* renamed from: e */
    public static boolean m1336e() {
        return m1296a(0, 1) == 1;
    }

    /* renamed from: e */
    public static boolean m1337e(Context context) {
        boolean parseBoolean = Boolean.parseBoolean(m1304a(context, "a1018183c8", "c20878faf7", "0"));
        f2525c = parseBoolean;
        return parseBoolean;
    }

    /* renamed from: f */
    public static String m1338f() {
        return Build.MODEL;
    }

    /* renamed from: f */
    public static boolean m1339f(Context context) {
        boolean z;
        long parseLong;
        synchronized (C3614g.class) {
            z = true;
            try {
                parseLong = Long.parseLong(m1304a(context, "a1018183c8", "e9e74d9323", "111"), 2);
                f2526d = parseLong;
            } catch (Throwable unused) {
            }
            if (parseLong != 0) {
                int i = Calendar.getInstance().get(5);
                if (f2526d == 100 || i % f2526d == 0) {
                    String valueOf = String.valueOf(m1320b());
                    String m1304a = m1304a(context, "a1018183c8", "b843017420", "0");
                    f2527e = Long.parseLong(m1304a(context, "a1018183c8", "f13ea469d6", "1"), 2);
                    if (m1314a(valueOf, m1304a)) {
                        long parseLong2 = Long.parseLong(m1304a(context, "a1018183c8", "gcf2c2f0b8", "0"), 2);
                        if (parseLong2 < f2527e) {
                            m1325b(context, "a1018183c8", "gcf2c2f0b8", String.valueOf(parseLong2 + 1));
                        }
                    } else {
                        m1325b(context, "a1018183c8", "gcf2c2f0b8", "1");
                        m1325b(context, "a1018183c8", "b843017420", valueOf);
                    }
                }
            }
            z = false;
        }
        return z;
    }

    /* renamed from: g */
    public static String m1340g() {
        return Build.VERSION.RELEASE;
    }
}
