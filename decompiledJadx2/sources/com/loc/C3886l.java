package com.loc;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.text.TextUtils;
import com.amazonaws.services.p048s3.internal.crypto.JceEncryptionConstants;
import com.iflytek.cloud.SpeechUtility;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AuthConfigManager.java */
/* renamed from: com.loc.l */
/* loaded from: classes4.dex */
public final class C3886l {

    /* renamed from: a */
    public static int f4229a = -1;

    /* renamed from: b */
    public static String f4230b = "";

    /* compiled from: AuthConfigManager.java */
    /* renamed from: com.loc.l$a */
    /* loaded from: classes4.dex */
    public static class a {

        /* renamed from: a */
        public String f4231a;

        /* renamed from: b */
        public int f4232b = -1;

        /* renamed from: c */
        public JSONObject f4233c;

        /* renamed from: d */
        public JSONObject f4234d;

        /* renamed from: e */
        public JSONObject f4235e;

        /* renamed from: f */
        public JSONObject f4236f;

        /* renamed from: g */
        public JSONObject f4237g;

        /* renamed from: h */
        public JSONObject f4238h;

        /* renamed from: i */
        public JSONObject f4239i;

        /* renamed from: j */
        public JSONObject f4240j;

        /* renamed from: k */
        public JSONObject f4241k;

        /* renamed from: l */
        public JSONObject f4242l;

        /* renamed from: m */
        public JSONObject f4243m;

        /* renamed from: n */
        public JSONObject f4244n;

        /* renamed from: o */
        public JSONObject f4245o;

        /* renamed from: p */
        public C9033a f4246p;

        /* renamed from: q */
        public d f4247q;

        /* renamed from: r */
        public c f4248r;

        /* renamed from: s */
        public b f4249s;

        /* renamed from: t */
        public b f4250t;

        /* renamed from: u */
        public b f4251u;

        /* renamed from: v */
        public b f4252v;

        /* compiled from: AuthConfigManager.java */
        /* renamed from: com.loc.l$a$a, reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        public static class C9033a {

            /* renamed from: a */
            public boolean f4253a;

            /* renamed from: b */
            public boolean f4254b;

            /* renamed from: c */
            public JSONObject f4255c;
        }

        /* compiled from: AuthConfigManager.java */
        /* renamed from: com.loc.l$a$b */
        /* loaded from: classes4.dex */
        public static class b {

            /* renamed from: a */
            public boolean f4256a;

            /* renamed from: b */
            public String f4257b;

            /* renamed from: c */
            public String f4258c;

            /* renamed from: d */
            public String f4259d;
        }

        /* compiled from: AuthConfigManager.java */
        /* renamed from: com.loc.l$a$c */
        /* loaded from: classes4.dex */
        public static class c {

            /* renamed from: a */
            public String f4260a;

            /* renamed from: b */
            public String f4261b;
        }

        /* compiled from: AuthConfigManager.java */
        /* renamed from: com.loc.l$a$d */
        /* loaded from: classes4.dex */
        public static class d {

            /* renamed from: a */
            public String f4262a;

            /* renamed from: b */
            public String f4263b;

            /* renamed from: c */
            public String f4264c;
        }
    }

    /* compiled from: AuthConfigManager.java */
    /* renamed from: com.loc.l$b */
    /* loaded from: classes4.dex */
    static class b extends AbstractC3835bj {

        /* renamed from: f */
        private String f4265f;

        /* renamed from: g */
        private Map<String, String> f4266g;

        b(Context context, C3893s c3893s, String str) {
            super(context, c3893s);
            this.f4265f = str;
            this.f4266g = null;
        }

        /* renamed from: h */
        private Map<String, String> m3135h() {
            String m3168q = C3888n.m3168q(this.f3679a);
            if (!TextUtils.isEmpty(m3168q)) {
                m3168q = C3890p.m3188b(new StringBuilder(m3168q).reverse().toString());
            }
            HashMap hashMap = new HashMap();
            hashMap.put("authkey", this.f4265f);
            hashMap.put("plattype", "android");
            hashMap.put("product", this.f3680b.m3206a());
            hashMap.put("version", this.f3680b.m3208b());
            hashMap.put("output", "json");
            StringBuilder sb = new StringBuilder();
            sb.append(Build.VERSION.SDK_INT);
            hashMap.put("androidversion", sb.toString());
            hashMap.put("deviceId", m3168q);
            Map<String, String> map = this.f4266g;
            if (map != null && !map.isEmpty()) {
                hashMap.putAll(this.f4266g);
            }
            String str = null;
            if (Build.VERSION.SDK_INT >= 21) {
                try {
                    ApplicationInfo applicationInfo = this.f3679a.getApplicationInfo();
                    Field declaredField = Class.forName(ApplicationInfo.class.getName()).getDeclaredField("primaryCpuAbi");
                    declaredField.setAccessible(true);
                    str = (String) declaredField.get(applicationInfo);
                } catch (Throwable th) {
                    C3897w.m3249a(th, "ConfigManager", "getcpu");
                }
            }
            if (TextUtils.isEmpty(str)) {
                str = Build.CPU_ABI;
            }
            hashMap.put("abitype", str);
            hashMap.put("ext", this.f3680b.m3210d());
            return hashMap;
        }

        @Override // com.loc.AbstractC3839bn
        /* renamed from: a */
        public final Map<String, String> mo2487a() {
            return null;
        }

        @Override // com.loc.AbstractC3835bj
        /* renamed from: a_ */
        public final byte[] mo2609a_() {
            return null;
        }

        @Override // com.loc.AbstractC3839bn
        /* renamed from: b */
        public final String mo2488b() {
            return "https://restapi.amap.com/v3/iasdkauth";
        }

        @Override // com.loc.AbstractC3835bj
        /* renamed from: b_ */
        public final byte[] mo2610b_() {
            return C3894t.m3232a(C3894t.m3225a(m3135h()));
        }

        @Override // com.loc.AbstractC3835bj
        /* renamed from: e */
        protected final String mo2612e() {
            return "3.0";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0102 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0103  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static a m3130a(Context context, C3893s c3893s, String str) {
        String str2;
        String str3;
        String str4;
        byte[] bArr;
        C3840bo c3840bo;
        String str5;
        List<String> list;
        byte[] bArr2;
        a aVar = new a();
        try {
            try {
                new C3834bi();
            } catch (C3884j e) {
                e = e;
                str2 = "11I";
                str3 = "11C";
                str4 = "11B";
                bArr = null;
                c3840bo = null;
                aVar.f4231a = e.m3118a();
                str5 = null;
                if (bArr == null) {
                }
            } catch (IllegalBlockSizeException unused) {
                str2 = "11I";
                str3 = "11C";
                str4 = "11B";
                bArr = null;
                c3840bo = null;
                str5 = null;
                if (bArr == null) {
                }
            } catch (Throwable th) {
                th = th;
                str2 = "11I";
                str3 = "11C";
                str4 = "11B";
                bArr = null;
                c3840bo = null;
                C3897w.m3249a(th, "ConfigManager", "loadConfig");
                str5 = null;
                if (bArr == null) {
                }
            }
        } catch (C3884j e2) {
            e = e2;
            bArr = null;
            c3840bo = null;
            aVar.f4231a = e.m3118a();
            str5 = null;
            if (bArr == null) {
            }
        } catch (IllegalBlockSizeException unused2) {
            bArr = null;
            c3840bo = null;
            str5 = null;
            if (bArr == null) {
            }
        } catch (Throwable th2) {
            th = th2;
            bArr = null;
            c3840bo = null;
            C3897w.m3249a(th, "ConfigManager", "loadConfig");
            str5 = null;
            if (bArr == null) {
            }
        }
        try {
            c3840bo = C3834bi.m2601a(new b(context, c3893s, str), true);
            if (c3840bo != null) {
                try {
                    bArr = c3840bo.f3699a;
                } catch (C3884j e3) {
                    e = e3;
                    str2 = "11I";
                    str3 = "11C";
                    str4 = "11B";
                    bArr = null;
                    aVar.f4231a = e.m3118a();
                    str5 = null;
                    if (bArr == null) {
                    }
                } catch (IllegalBlockSizeException unused3) {
                    str2 = "11I";
                    str3 = "11C";
                    str4 = "11B";
                    bArr = null;
                    str5 = null;
                    if (bArr == null) {
                    }
                } catch (Throwable th3) {
                    th = th3;
                    str2 = "11I";
                    str3 = "11C";
                    str4 = "11B";
                    bArr = null;
                    C3897w.m3249a(th, "ConfigManager", "loadConfig");
                    str5 = null;
                    if (bArr == null) {
                    }
                }
            } else {
                bArr = null;
            }
            try {
                byte[] bArr3 = new byte[16];
                str2 = "11I";
                try {
                    bArr2 = new byte[bArr.length - 16];
                    str3 = "11C";
                } catch (C3884j e4) {
                    e = e4;
                    str3 = "11C";
                    str4 = "11B";
                    c3840bo = c3840bo;
                    aVar.f4231a = e.m3118a();
                    str5 = null;
                    if (bArr == null) {
                    }
                } catch (IllegalBlockSizeException unused4) {
                    str3 = "11C";
                    str4 = "11B";
                    c3840bo = c3840bo;
                    str5 = null;
                    if (bArr == null) {
                    }
                } catch (Throwable th4) {
                    th = th4;
                    str3 = "11C";
                    str4 = "11B";
                    c3840bo = c3840bo;
                    C3897w.m3249a(th, "ConfigManager", "loadConfig");
                    str5 = null;
                    if (bArr == null) {
                    }
                }
                try {
                    System.arraycopy(bArr, 0, bArr3, 0, 16);
                    str4 = "11B";
                } catch (C3884j e5) {
                    e = e5;
                    str4 = "11B";
                    c3840bo = c3840bo;
                    aVar.f4231a = e.m3118a();
                    str5 = null;
                    if (bArr == null) {
                    }
                } catch (IllegalBlockSizeException unused5) {
                    str4 = "11B";
                    c3840bo = c3840bo;
                    str5 = null;
                    if (bArr == null) {
                    }
                } catch (Throwable th5) {
                    th = th5;
                    str4 = "11B";
                    c3840bo = c3840bo;
                    C3897w.m3249a(th, "ConfigManager", "loadConfig");
                    str5 = null;
                    if (bArr == null) {
                    }
                }
                try {
                    System.arraycopy(bArr, 16, bArr2, 0, bArr.length - 16);
                    SecretKeySpec secretKeySpec = new SecretKeySpec(bArr3, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
                    Cipher cipher = Cipher.getInstance(JceEncryptionConstants.SYMMETRIC_CIPHER_METHOD);
                    cipher.init(2, secretKeySpec, new IvParameterSpec(C3894t.m3231a()));
                    str5 = C3894t.m3226a(cipher.doFinal(bArr2));
                    c3840bo = c3840bo;
                } catch (C3884j e6) {
                    e = e6;
                    c3840bo = c3840bo;
                    aVar.f4231a = e.m3118a();
                    str5 = null;
                    if (bArr == null) {
                    }
                } catch (IllegalBlockSizeException unused6) {
                    c3840bo = c3840bo;
                    str5 = null;
                    if (bArr == null) {
                    }
                } catch (Throwable th6) {
                    th = th6;
                    c3840bo = c3840bo;
                    C3897w.m3249a(th, "ConfigManager", "loadConfig");
                    str5 = null;
                    if (bArr == null) {
                    }
                }
            } catch (C3884j e7) {
                e = e7;
                str2 = "11I";
            } catch (IllegalBlockSizeException unused7) {
                str2 = "11I";
            } catch (Throwable th7) {
                th = th7;
                str2 = "11I";
            }
            if (bArr == null) {
                return aVar;
            }
            if (TextUtils.isEmpty(str5)) {
                str5 = C3894t.m3226a(bArr);
            }
            try {
                JSONObject jSONObject = new JSONObject(str5);
                if (jSONObject.has("status")) {
                    int i = jSONObject.getInt("status");
                    if (i == 1) {
                        f4229a = 1;
                    } else if (i == 0) {
                        String str6 = "authcsid";
                        String str7 = "authgsid";
                        if (c3840bo != null) {
                            str6 = c3840bo.f3701c;
                            if (c3840bo.f3700b != null && (list = c3840bo.f3700b.get("gsid")) != null && list.size() > 0) {
                                str7 = list.get(0);
                            }
                        }
                        C3894t.m3227a(context, str6, str7, jSONObject.toString());
                        f4229a = 0;
                        if (jSONObject.has("info")) {
                            f4230b = jSONObject.getString("info");
                        }
                        if (f4229a == 0) {
                            aVar.f4231a = f4230b;
                            return aVar;
                        }
                    }
                    try {
                        if (jSONObject.has("ver")) {
                            aVar.f4232b = jSONObject.getInt("ver");
                        }
                    } catch (Throwable th8) {
                        C3897w.m3249a(th8, "AuthConfigManager", "loadConfigVer");
                    }
                    if (C3894t.m3230a(jSONObject, SpeechUtility.TAG_RESOURCE_RESULT)) {
                        a.C9033a c9033a = new a.C9033a();
                        c9033a.f4253a = false;
                        c9033a.f4254b = false;
                        aVar.f4246p = c9033a;
                        JSONObject jSONObject2 = jSONObject.getJSONObject(SpeechUtility.TAG_RESOURCE_RESULT);
                        if (C3894t.m3230a(jSONObject2, "11K")) {
                            try {
                                JSONObject jSONObject3 = jSONObject2.getJSONObject("11K");
                                c9033a.f4253a = m3134a(jSONObject3.getString("able"), false);
                                if (jSONObject3.has("off")) {
                                    c9033a.f4255c = jSONObject3.getJSONObject("off");
                                }
                            } catch (Throwable th9) {
                                C3897w.m3249a(th9, "AuthConfigManager", "loadException");
                            }
                        }
                        String str8 = str4;
                        if (C3894t.m3230a(jSONObject2, str8)) {
                            aVar.f4234d = jSONObject2.getJSONObject(str8);
                        }
                        String str9 = str3;
                        if (C3894t.m3230a(jSONObject2, str9)) {
                            aVar.f4237g = jSONObject2.getJSONObject(str9);
                        }
                        String str10 = str2;
                        if (C3894t.m3230a(jSONObject2, str10)) {
                            aVar.f4238h = jSONObject2.getJSONObject(str10);
                        }
                        if (C3894t.m3230a(jSONObject2, "11H")) {
                            aVar.f4239i = jSONObject2.getJSONObject("11H");
                        }
                        if (C3894t.m3230a(jSONObject2, "11E")) {
                            aVar.f4240j = jSONObject2.getJSONObject("11E");
                        }
                        if (C3894t.m3230a(jSONObject2, "11F")) {
                            aVar.f4241k = jSONObject2.getJSONObject("11F");
                        }
                        if (C3894t.m3230a(jSONObject2, "13A")) {
                            aVar.f4243m = jSONObject2.getJSONObject("13A");
                        }
                        if (C3894t.m3230a(jSONObject2, "13J")) {
                            aVar.f4235e = jSONObject2.getJSONObject("13J");
                        }
                        if (C3894t.m3230a(jSONObject2, "11G")) {
                            aVar.f4242l = jSONObject2.getJSONObject("11G");
                        }
                        if (C3894t.m3230a(jSONObject2, "001")) {
                            JSONObject jSONObject4 = jSONObject2.getJSONObject("001");
                            a.d dVar = new a.d();
                            if (jSONObject4 != null) {
                                try {
                                    String m3131a = m3131a(jSONObject4, "md5");
                                    String m3131a2 = m3131a(jSONObject4, "url");
                                    String m3131a3 = m3131a(jSONObject4, "sdkversion");
                                    if (!TextUtils.isEmpty(m3131a) && !TextUtils.isEmpty(m3131a2) && !TextUtils.isEmpty(m3131a3)) {
                                        dVar.f4262a = m3131a2;
                                        dVar.f4263b = m3131a;
                                        dVar.f4264c = m3131a3;
                                    }
                                } catch (Throwable th10) {
                                    C3897w.m3249a(th10, "ConfigManager", "parseSDKUpdate");
                                }
                            }
                            aVar.f4247q = dVar;
                        }
                        if (C3894t.m3230a(jSONObject2, "002")) {
                            JSONObject jSONObject5 = jSONObject2.getJSONObject("002");
                            a.c cVar = new a.c();
                            if (jSONObject5 != null) {
                                try {
                                    String m3131a4 = m3131a(jSONObject5, "md5");
                                    String m3131a5 = m3131a(jSONObject5, "url");
                                    cVar.f4261b = m3131a4;
                                    cVar.f4260a = m3131a5;
                                } catch (Throwable th11) {
                                    C3897w.m3249a(th11, "ConfigManager", "parseSDKCoordinate");
                                }
                            }
                            aVar.f4248r = cVar;
                        }
                        if (C3894t.m3230a(jSONObject2, "006")) {
                            aVar.f4244n = jSONObject2.getJSONObject("006");
                        }
                        if (C3894t.m3230a(jSONObject2, "010")) {
                            aVar.f4245o = jSONObject2.getJSONObject("010");
                        }
                        if (C3894t.m3230a(jSONObject2, "11Z")) {
                            JSONObject jSONObject6 = jSONObject2.getJSONObject("11Z");
                            a.b bVar = new a.b();
                            m3133a(jSONObject6, bVar);
                            aVar.f4249s = bVar;
                        }
                        if (C3894t.m3230a(jSONObject2, "135")) {
                            aVar.f4236f = jSONObject2.getJSONObject("135");
                        }
                        if (C3894t.m3230a(jSONObject2, "13S")) {
                            aVar.f4233c = jSONObject2.getJSONObject("13S");
                        }
                        if (C3894t.m3230a(jSONObject2, "121")) {
                            JSONObject jSONObject7 = jSONObject2.getJSONObject("121");
                            a.b bVar2 = new a.b();
                            m3133a(jSONObject7, bVar2);
                            aVar.f4250t = bVar2;
                        }
                        if (C3894t.m3230a(jSONObject2, "122")) {
                            JSONObject jSONObject8 = jSONObject2.getJSONObject("122");
                            a.b bVar3 = new a.b();
                            m3133a(jSONObject8, bVar3);
                            aVar.f4251u = bVar3;
                        }
                        if (C3894t.m3230a(jSONObject2, "123")) {
                            JSONObject jSONObject9 = jSONObject2.getJSONObject("123");
                            a.b bVar4 = new a.b();
                            m3133a(jSONObject9, bVar4);
                            aVar.f4252v = bVar4;
                        }
                    }
                    return aVar;
                }
            } catch (Throwable th12) {
                C3897w.m3249a(th12, "AuthConfigManager", "loadConfig");
            }
            return aVar;
        } catch (C3884j e8) {
            throw e8;
        } catch (Throwable unused8) {
            throw new C3884j("未知的错误");
        }
    }

    /* renamed from: a */
    private static String m3131a(JSONObject jSONObject, String str) throws JSONException {
        return (jSONObject == null || !jSONObject.has(str) || jSONObject.getString(str).equals("[]")) ? "" : jSONObject.optString(str);
    }

    @Deprecated
    /* renamed from: a */
    public static void m3132a(String str) {
        C3885k.m3123b(str);
    }

    /* renamed from: a */
    private static void m3133a(JSONObject jSONObject, a.b bVar) {
        if (bVar != null) {
            try {
                String m3131a = m3131a(jSONObject, "m");
                String m3131a2 = m3131a(jSONObject, "u");
                String m3131a3 = m3131a(jSONObject, "v");
                String m3131a4 = m3131a(jSONObject, "able");
                bVar.f4258c = m3131a;
                bVar.f4257b = m3131a2;
                bVar.f4259d = m3131a3;
                bVar.f4256a = m3134a(m3131a4, false);
            } catch (Throwable th) {
                C3897w.m3249a(th, "ConfigManager", "parsePluginEntity");
            }
        }
    }

    /* renamed from: a */
    public static boolean m3134a(String str, boolean z) {
        try {
            String[] split = URLDecoder.decode(str).split("/");
            return split[split.length - 1].charAt(4) % 2 == 1;
        } catch (Throwable unused) {
            return z;
        }
    }
}
