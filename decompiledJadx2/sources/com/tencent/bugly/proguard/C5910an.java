package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.an */
/* loaded from: classes7.dex */
public final class C5910an extends AbstractC5927k {

    /* renamed from: A */
    private static ArrayList<C5909am> f8039A;

    /* renamed from: B */
    private static Map<String, String> f8040B;

    /* renamed from: C */
    private static Map<String, String> f8041C;

    /* renamed from: v */
    private static Map<String, String> f8042v = new HashMap();

    /* renamed from: w */
    private static C5908al f8043w;

    /* renamed from: x */
    private static C5907ak f8044x;

    /* renamed from: y */
    private static ArrayList<C5907ak> f8045y;

    /* renamed from: z */
    private static ArrayList<C5907ak> f8046z;

    /* renamed from: a */
    public String f8047a = "";

    /* renamed from: b */
    public long f8048b = 0;

    /* renamed from: c */
    public String f8049c = "";

    /* renamed from: d */
    public String f8050d = "";

    /* renamed from: e */
    public String f8051e = "";

    /* renamed from: f */
    public String f8052f = "";

    /* renamed from: g */
    public String f8053g = "";

    /* renamed from: h */
    public Map<String, String> f8054h = null;

    /* renamed from: i */
    public String f8055i = "";

    /* renamed from: j */
    public C5908al f8056j = null;

    /* renamed from: k */
    public int f8057k = 0;

    /* renamed from: l */
    public String f8058l = "";

    /* renamed from: m */
    public String f8059m = "";

    /* renamed from: n */
    public C5907ak f8060n = null;

    /* renamed from: o */
    public ArrayList<C5907ak> f8061o = null;

    /* renamed from: p */
    public ArrayList<C5907ak> f8062p = null;

    /* renamed from: q */
    public ArrayList<C5909am> f8063q = null;

    /* renamed from: r */
    public Map<String, String> f8064r = null;

    /* renamed from: s */
    public Map<String, String> f8065s = null;

    /* renamed from: t */
    public String f8066t = "";

    /* renamed from: u */
    private boolean f8067u = true;

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3650a(C5926j c5926j) {
        c5926j.m3706a(this.f8047a, 0);
        c5926j.m3703a(this.f8048b, 1);
        c5926j.m3706a(this.f8049c, 2);
        String str = this.f8050d;
        if (str != null) {
            c5926j.m3706a(str, 3);
        }
        String str2 = this.f8051e;
        if (str2 != null) {
            c5926j.m3706a(str2, 4);
        }
        String str3 = this.f8052f;
        if (str3 != null) {
            c5926j.m3706a(str3, 5);
        }
        String str4 = this.f8053g;
        if (str4 != null) {
            c5926j.m3706a(str4, 6);
        }
        Map<String, String> map = this.f8054h;
        if (map != null) {
            c5926j.m3708a((Map) map, 7);
        }
        String str5 = this.f8055i;
        if (str5 != null) {
            c5926j.m3706a(str5, 8);
        }
        C5908al c5908al = this.f8056j;
        if (c5908al != null) {
            c5926j.m3704a((AbstractC5927k) c5908al, 9);
        }
        c5926j.m3702a(this.f8057k, 10);
        String str6 = this.f8058l;
        if (str6 != null) {
            c5926j.m3706a(str6, 11);
        }
        String str7 = this.f8059m;
        if (str7 != null) {
            c5926j.m3706a(str7, 12);
        }
        C5907ak c5907ak = this.f8060n;
        if (c5907ak != null) {
            c5926j.m3704a((AbstractC5927k) c5907ak, 13);
        }
        ArrayList<C5907ak> arrayList = this.f8061o;
        if (arrayList != null) {
            c5926j.m3707a((Collection) arrayList, 14);
        }
        ArrayList<C5907ak> arrayList2 = this.f8062p;
        if (arrayList2 != null) {
            c5926j.m3707a((Collection) arrayList2, 15);
        }
        ArrayList<C5909am> arrayList3 = this.f8063q;
        if (arrayList3 != null) {
            c5926j.m3707a((Collection) arrayList3, 16);
        }
        Map<String, String> map2 = this.f8064r;
        if (map2 != null) {
            c5926j.m3708a((Map) map2, 17);
        }
        Map<String, String> map3 = this.f8065s;
        if (map3 != null) {
            c5926j.m3708a((Map) map3, 18);
        }
        String str8 = this.f8066t;
        if (str8 != null) {
            c5926j.m3706a(str8, 19);
        }
        c5926j.m3710a(this.f8067u, 20);
    }

    static {
        f8042v.put("", "");
        f8043w = new C5908al();
        f8044x = new C5907ak();
        f8045y = new ArrayList<>();
        f8045y.add(new C5907ak());
        f8046z = new ArrayList<>();
        f8046z.add(new C5907ak());
        f8039A = new ArrayList<>();
        f8039A.add(new C5909am());
        f8040B = new HashMap();
        f8040B.put("", "");
        f8041C = new HashMap();
        f8041C.put("", "");
    }

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3649a(C5925i c5925i) {
        this.f8047a = c5925i.m3695b(0, true);
        this.f8048b = c5925i.m3688a(this.f8048b, 1, true);
        this.f8049c = c5925i.m3695b(2, true);
        this.f8050d = c5925i.m3695b(3, false);
        this.f8051e = c5925i.m3695b(4, false);
        this.f8052f = c5925i.m3695b(5, false);
        this.f8053g = c5925i.m3695b(6, false);
        this.f8054h = (Map) c5925i.m3690a((C5925i) f8042v, 7, false);
        this.f8055i = c5925i.m3695b(8, false);
        this.f8056j = (C5908al) c5925i.m3689a((AbstractC5927k) f8043w, 9, false);
        this.f8057k = c5925i.m3686a(this.f8057k, 10, false);
        this.f8058l = c5925i.m3695b(11, false);
        this.f8059m = c5925i.m3695b(12, false);
        this.f8060n = (C5907ak) c5925i.m3689a((AbstractC5927k) f8044x, 13, false);
        this.f8061o = (ArrayList) c5925i.m3690a((C5925i) f8045y, 14, false);
        this.f8062p = (ArrayList) c5925i.m3690a((C5925i) f8046z, 15, false);
        this.f8063q = (ArrayList) c5925i.m3690a((C5925i) f8039A, 16, false);
        this.f8064r = (Map) c5925i.m3690a((C5925i) f8040B, 17, false);
        this.f8065s = (Map) c5925i.m3690a((C5925i) f8041C, 18, false);
        this.f8066t = c5925i.m3695b(19, false);
        boolean z = this.f8067u;
        this.f8067u = c5925i.m3694a(20, false);
    }
}
