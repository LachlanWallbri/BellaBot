package com.loc;

import java.util.HashMap;
import java.util.Map;

/* compiled from: DynamicPlugin.java */
@InterfaceC3805ag(m2435a = "file")
/* renamed from: com.loc.bc */
/* loaded from: classes4.dex */
public class C3828bc {

    /* renamed from: a */
    @InterfaceC3806ah(m2437a = "fname", m2438b = 6)
    private String f3620a;

    /* renamed from: b */
    @InterfaceC3806ah(m2437a = "md", m2438b = 6)
    private String f3621b;

    /* renamed from: c */
    @InterfaceC3806ah(m2437a = "sname", m2438b = 6)
    private String f3622c;

    /* renamed from: d */
    @InterfaceC3806ah(m2437a = "version", m2438b = 6)
    private String f3623d;

    /* renamed from: e */
    @InterfaceC3806ah(m2437a = "dversion", m2438b = 6)
    private String f3624e;

    /* renamed from: f */
    @InterfaceC3806ah(m2437a = "status", m2438b = 6)
    private String f3625f;

    /* compiled from: DynamicPlugin.java */
    /* renamed from: com.loc.bc$a */
    /* loaded from: classes4.dex */
    public static class a {

        /* renamed from: a */
        private String f3626a;

        /* renamed from: b */
        private String f3627b;

        /* renamed from: c */
        private String f3628c;

        /* renamed from: d */
        private String f3629d;

        /* renamed from: e */
        private String f3630e;

        /* renamed from: f */
        private String f3631f = "copy";

        public a(String str, String str2, String str3, String str4, String str5) {
            this.f3626a = str;
            this.f3627b = str2;
            this.f3628c = str3;
            this.f3629d = str4;
            this.f3630e = str5;
        }

        /* renamed from: a */
        public final a m2535a(String str) {
            this.f3631f = str;
            return this;
        }

        /* renamed from: a */
        public final C3828bc m2536a() {
            return new C3828bc(this);
        }
    }

    private C3828bc() {
    }

    public C3828bc(a aVar) {
        this.f3620a = aVar.f3626a;
        this.f3621b = aVar.f3627b;
        this.f3622c = aVar.f3628c;
        this.f3623d = aVar.f3629d;
        this.f3624e = aVar.f3630e;
        this.f3625f = aVar.f3631f;
    }

    /* renamed from: a */
    public static String m2517a(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("sname", str);
        return C3804af.m2424a((Map<String, String>) hashMap);
    }

    /* renamed from: a */
    public static String m2518a(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("sname", str);
        hashMap.put("dversion", str2);
        return C3804af.m2424a((Map<String, String>) hashMap);
    }

    /* renamed from: a */
    public static String m2519a(String str, String str2, String str3, String str4) {
        HashMap hashMap = new HashMap();
        hashMap.put("fname", str);
        hashMap.put("sname", str2);
        hashMap.put("dversion", str4);
        hashMap.put("version", str3);
        return C3804af.m2424a((Map<String, String>) hashMap);
    }

    /* renamed from: b */
    public static String m2520b(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("fname", str);
        return C3804af.m2424a((Map<String, String>) hashMap);
    }

    /* renamed from: b */
    public static String m2521b(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("sname", str);
        hashMap.put("status", str2);
        return C3804af.m2424a((Map<String, String>) hashMap);
    }

    /* renamed from: a */
    public final String m2522a() {
        return this.f3620a;
    }

    /* renamed from: b */
    public final String m2523b() {
        return this.f3621b;
    }

    /* renamed from: c */
    public final String m2524c() {
        return this.f3622c;
    }

    /* renamed from: c */
    public final void m2525c(String str) {
        this.f3625f = str;
    }

    /* renamed from: d */
    public final String m2526d() {
        return this.f3623d;
    }

    /* renamed from: e */
    public final String m2527e() {
        return this.f3624e;
    }

    /* renamed from: f */
    public final String m2528f() {
        return this.f3625f;
    }
}
