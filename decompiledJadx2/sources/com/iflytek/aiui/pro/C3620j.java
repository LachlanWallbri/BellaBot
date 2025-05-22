package com.iflytek.aiui.pro;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.text.TextUtils;
import com.amazonaws.services.p048s3.internal.crypto.JceEncryptionConstants;
import com.iflytek.cloud.SpeechUtility;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.http.client.config.CookieSpecs;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* renamed from: com.iflytek.aiui.pro.j */
/* loaded from: classes.dex */
public class C3620j {

    /* renamed from: a */
    public static int f2561a = -1;

    /* renamed from: b */
    public static String f2562b = "";

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* renamed from: com.iflytek.aiui.pro.j$a */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a */
        public String f2563a;

        /* renamed from: b */
        public C9029a f2564b;

        /* renamed from: c */
        public JSONObject f2565c;

        /* renamed from: d */
        public JSONObject f2566d;

        /* renamed from: e */
        public JSONObject f2567e;

        /* JADX WARN: Classes with same name are omitted:
          classes3.dex
          classes4.dex
         */
        /* renamed from: com.iflytek.aiui.pro.j$a$a, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static class C9029a {

            /* renamed from: a */
            public String f2568a;

            /* renamed from: b */
            public String f2569b;

            /* renamed from: c */
            public String f2570c;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0080 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0081  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static a m1379a(Context context, C3626m c3626m, String str, Map<String, String> map) {
        byte[] bArr;
        String str2;
        a aVar = new a();
        if (context != null && c3626m != null) {
            try {
                C3571aa c3571aa = new C3571aa(context, c3626m);
                c3571aa.mo921a("https://restapi.amap.com/v3/iasdkauth");
                c3571aa.m931d("3.0");
                c3571aa.m930c(C3628n.m1415a(C3628n.m1408a(m1383b(context, c3626m, str, map))));
                bArr = C3652z.m1634a(c3571aa, true);
            } catch (IllegalBlockSizeException unused) {
                bArr = null;
            } catch (Throwable th) {
                th = th;
                bArr = null;
            }
            try {
                byte[] bArr2 = new byte[16];
                byte[] bArr3 = new byte[bArr.length - 16];
                System.arraycopy(bArr, 0, bArr2, 0, 16);
                System.arraycopy(bArr, 16, bArr3, 0, bArr.length - 16);
                SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
                Cipher cipher = Cipher.getInstance(JceEncryptionConstants.SYMMETRIC_CIPHER_METHOD);
                cipher.init(2, secretKeySpec, new IvParameterSpec(C3628n.m1414a()));
                str2 = C3628n.m1418b(cipher.doFinal(bArr3));
            } catch (IllegalBlockSizeException unused2) {
                str2 = null;
                if (bArr == null) {
                }
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
                str2 = null;
                if (bArr == null) {
                }
            }
            if (bArr == null) {
                return aVar;
            }
            if (TextUtils.isEmpty(str2)) {
                str2 = C3628n.m1418b(bArr);
            }
            try {
                JSONObject jSONObject = new JSONObject(str2);
                if (jSONObject.has("status")) {
                    int i = jSONObject.getInt("status");
                    if (i == 1) {
                        f2561a = 1;
                    } else if (i == 0) {
                        f2561a = 0;
                        if (jSONObject.has("info")) {
                            f2562b = jSONObject.getString("info");
                        }
                        aVar.f2563a = f2562b;
                        return aVar;
                    }
                    if (C3628n.m1413a(jSONObject, SpeechUtility.TAG_RESOURCE_RESULT)) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject(SpeechUtility.TAG_RESOURCE_RESULT);
                        if (C3628n.m1413a(jSONObject2, "001")) {
                            JSONObject jSONObject3 = jSONObject2.getJSONObject("001");
                            a.C9029a c9029a = new a.C9029a();
                            m1381a(jSONObject3, c9029a);
                            aVar.f2564b = c9029a;
                        }
                        if (C3628n.m1413a(jSONObject2, "133")) {
                            aVar.f2565c = jSONObject2.getJSONObject("133");
                        }
                        if (C3628n.m1413a(jSONObject2, "132")) {
                            aVar.f2566d = jSONObject2.getJSONObject("132");
                        }
                        if (C3628n.m1413a(jSONObject2, "134")) {
                            aVar.f2567e = jSONObject2.getJSONObject("134");
                        }
                    }
                    return aVar;
                }
            } catch (Throwable th3) {
                th3.printStackTrace();
            }
        }
        return aVar;
    }

    /* renamed from: a */
    public static String m1380a(JSONObject jSONObject, String str) throws JSONException {
        return (jSONObject == null || !jSONObject.has(str) || jSONObject.getString(str).equals("[]")) ? "" : jSONObject.optString(str);
    }

    /* renamed from: a */
    private static void m1381a(JSONObject jSONObject, a.C9029a c9029a) {
        if (jSONObject != null) {
            try {
                String m1380a = m1380a(jSONObject, "md5");
                String m1380a2 = m1380a(jSONObject, "url");
                String m1380a3 = m1380a(jSONObject, "sdkversion");
                if (TextUtils.isEmpty(m1380a) || TextUtils.isEmpty(m1380a2) || TextUtils.isEmpty(m1380a3)) {
                    return;
                }
                c9029a.f2568a = m1380a2;
                c9029a.f2569b = m1380a;
                c9029a.f2570c = m1380a3;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public static boolean m1382a(String str, boolean z) {
        try {
            String[] split = URLDecoder.decode(str).split("/");
            return split[split.length - 1].charAt(4) % 2 == 1;
        } catch (Throwable unused) {
            return z;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0093  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Map<String, String> m1383b(Context context, C3626m c3626m, String str, Map<String, String> map) {
        String str2;
        String m1368l = C3618i.m1368l(context);
        if (!TextUtils.isEmpty(m1368l)) {
            m1368l = C3624l.m1396b(new StringBuilder(m1368l).reverse().toString());
        }
        HashMap hashMap = new HashMap();
        hashMap.put("authkey", str);
        hashMap.put("plattype", "android");
        hashMap.put("product", c3626m.f2579a);
        hashMap.put("version", c3626m.f2580b);
        hashMap.put("output", "json");
        hashMap.put("androidversion", Build.VERSION.SDK_INT + "");
        hashMap.put("deviceId", m1368l);
        if (map != null) {
            hashMap.putAll(map);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                ApplicationInfo applicationInfo = context.getApplicationInfo();
                Field declaredField = Class.forName(ApplicationInfo.class.getName()).getDeclaredField("primaryCpuAbi");
                declaredField.setAccessible(true);
                str2 = (String) declaredField.get(applicationInfo);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (TextUtils.isEmpty(str2)) {
                str2 = Build.CPU_ABI;
            }
            hashMap.put("abitype", str2);
            hashMap.put("ext", CookieSpecs.STANDARD);
            return hashMap;
        }
        str2 = null;
        if (TextUtils.isEmpty(str2)) {
        }
        hashMap.put("abitype", str2);
        hashMap.put("ext", CookieSpecs.STANDARD);
        return hashMap;
    }
}
