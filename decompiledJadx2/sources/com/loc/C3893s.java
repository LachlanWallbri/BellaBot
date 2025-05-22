package com.loc;

import android.text.TextUtils;
import com.iflytek.speech.VoiceWakeuperAidl;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.config.CookieSpecs;

/* compiled from: SDKInfo.java */
@InterfaceC3805ag(m2435a = "a")
/* renamed from: com.loc.s */
/* loaded from: classes4.dex */
public class C3893s {

    /* renamed from: a */
    @InterfaceC3806ah(m2437a = "a1", m2438b = 6)
    private String f4305a;

    /* renamed from: b */
    @InterfaceC3806ah(m2437a = "a2", m2438b = 6)
    private String f4306b;

    /* renamed from: c */
    @InterfaceC3806ah(m2437a = "a6", m2438b = 2)
    private int f4307c;

    /* renamed from: d */
    @InterfaceC3806ah(m2437a = "a3", m2438b = 6)
    private String f4308d;

    /* renamed from: e */
    @InterfaceC3806ah(m2437a = "a4", m2438b = 6)
    private String f4309e;

    /* renamed from: f */
    @InterfaceC3806ah(m2437a = "a5", m2438b = 6)
    private String f4310f;

    /* renamed from: g */
    private String f4311g;

    /* renamed from: h */
    private String f4312h;

    /* renamed from: i */
    private String f4313i;

    /* renamed from: j */
    private String f4314j;

    /* renamed from: k */
    private String f4315k;

    /* renamed from: l */
    private String[] f4316l;

    /* compiled from: SDKInfo.java */
    /* renamed from: com.loc.s$a */
    /* loaded from: classes4.dex */
    public static class a {

        /* renamed from: a */
        private String f4317a;

        /* renamed from: b */
        private String f4318b;

        /* renamed from: c */
        private String f4319c;

        /* renamed from: d */
        private String f4320d;

        /* renamed from: e */
        private boolean f4321e = true;

        /* renamed from: f */
        private String f4322f = CookieSpecs.STANDARD;

        /* renamed from: g */
        private String[] f4323g = null;

        public a(String str, String str2, String str3) {
            this.f4317a = str2;
            this.f4318b = str2;
            this.f4320d = str3;
            this.f4319c = str;
        }

        /* renamed from: a */
        public final a m3220a(String str) {
            this.f4318b = str;
            return this;
        }

        /* renamed from: a */
        public final a m3221a(String[] strArr) {
            this.f4323g = (String[]) strArr.clone();
            return this;
        }

        /* renamed from: a */
        public final C3893s m3222a() throws C3884j {
            if (this.f4323g != null) {
                return new C3893s(this, (byte) 0);
            }
            throw new C3884j("sdk packages is null");
        }
    }

    private C3893s() {
        this.f4307c = 1;
        this.f4316l = null;
    }

    private C3893s(a aVar) {
        this.f4307c = 1;
        this.f4316l = null;
        this.f4311g = aVar.f4317a;
        this.f4312h = aVar.f4318b;
        this.f4314j = aVar.f4319c;
        this.f4313i = aVar.f4320d;
        this.f4307c = aVar.f4321e ? 1 : 0;
        this.f4315k = aVar.f4322f;
        this.f4316l = aVar.f4323g;
        this.f4306b = C3894t.m3233b(this.f4312h);
        this.f4305a = C3894t.m3233b(this.f4314j);
        this.f4308d = C3894t.m3233b(this.f4313i);
        this.f4309e = C3894t.m3233b(m3203a(this.f4316l));
        this.f4310f = C3894t.m3233b(this.f4315k);
    }

    /* synthetic */ C3893s(a aVar, byte b) {
        this(aVar);
    }

    /* renamed from: a */
    public static String m3202a(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("a1", C3894t.m3233b(str));
        return C3804af.m2424a((Map<String, String>) hashMap);
    }

    /* renamed from: a */
    private static String m3203a(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            for (String str : strArr) {
                sb.append(str);
                sb.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
            }
            return sb.toString();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    private static String[] m3204b(String str) {
        try {
            return str.split(VoiceWakeuperAidl.PARAMS_SEPARATE);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: g */
    public static String m3205g() {
        return "a6=1";
    }

    /* renamed from: a */
    public final String m3206a() {
        if (TextUtils.isEmpty(this.f4314j) && !TextUtils.isEmpty(this.f4305a)) {
            this.f4314j = C3894t.m3237c(this.f4305a);
        }
        return this.f4314j;
    }

    /* renamed from: a */
    public final void m3207a(boolean z) {
        this.f4307c = z ? 1 : 0;
    }

    /* renamed from: b */
    public final String m3208b() {
        return this.f4311g;
    }

    /* renamed from: c */
    public final String m3209c() {
        if (TextUtils.isEmpty(this.f4312h) && !TextUtils.isEmpty(this.f4306b)) {
            this.f4312h = C3894t.m3237c(this.f4306b);
        }
        return this.f4312h;
    }

    /* renamed from: d */
    public final String m3210d() {
        if (TextUtils.isEmpty(this.f4315k) && !TextUtils.isEmpty(this.f4310f)) {
            this.f4315k = C3894t.m3237c(this.f4310f);
        }
        if (TextUtils.isEmpty(this.f4315k)) {
            this.f4315k = CookieSpecs.STANDARD;
        }
        return this.f4315k;
    }

    /* renamed from: e */
    public final boolean m3211e() {
        return this.f4307c == 1;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return getClass() == obj.getClass() && hashCode() == ((C3893s) obj).hashCode();
    }

    /* renamed from: f */
    public final String[] m3212f() {
        String[] strArr = this.f4316l;
        if ((strArr == null || strArr.length == 0) && !TextUtils.isEmpty(this.f4309e)) {
            this.f4316l = m3204b(C3894t.m3237c(this.f4309e));
        }
        return (String[]) this.f4316l.clone();
    }

    public int hashCode() {
        C3896v c3896v = new C3896v();
        c3896v.m3247a(this.f4314j).m3247a(this.f4311g).m3247a(this.f4312h).m3248a((Object[]) this.f4316l);
        return c3896v.m3246a();
    }
}
