package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.ap */
/* loaded from: classes7.dex */
public final class C5912ap extends AbstractC5927k {

    /* renamed from: y */
    private static byte[] f8070y;

    /* renamed from: z */
    private static Map<String, String> f8071z = new HashMap();

    /* renamed from: a */
    public int f8072a = 0;

    /* renamed from: b */
    public String f8073b = "";

    /* renamed from: c */
    public String f8074c = "";

    /* renamed from: d */
    public String f8075d = "";

    /* renamed from: e */
    public String f8076e = "";

    /* renamed from: f */
    public String f8077f = "";

    /* renamed from: g */
    public int f8078g = 0;

    /* renamed from: h */
    public byte[] f8079h = null;

    /* renamed from: i */
    public String f8080i = "";

    /* renamed from: j */
    public String f8081j = "";

    /* renamed from: k */
    public Map<String, String> f8082k = null;

    /* renamed from: l */
    public String f8083l = "";

    /* renamed from: m */
    public long f8084m = 0;

    /* renamed from: n */
    public String f8085n = "";

    /* renamed from: o */
    public String f8086o = "";

    /* renamed from: p */
    public String f8087p = "";

    /* renamed from: q */
    public long f8088q = 0;

    /* renamed from: r */
    public String f8089r = "";

    /* renamed from: s */
    public String f8090s = "";

    /* renamed from: t */
    public String f8091t = "";

    /* renamed from: u */
    public String f8092u = "";

    /* renamed from: v */
    public String f8093v = "";

    /* renamed from: w */
    public String f8094w = "";

    /* renamed from: x */
    private String f8095x = "";

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3650a(C5926j c5926j) {
        c5926j.m3702a(this.f8072a, 0);
        c5926j.m3706a(this.f8073b, 1);
        c5926j.m3706a(this.f8074c, 2);
        c5926j.m3706a(this.f8075d, 3);
        String str = this.f8076e;
        if (str != null) {
            c5926j.m3706a(str, 4);
        }
        c5926j.m3706a(this.f8077f, 5);
        c5926j.m3702a(this.f8078g, 6);
        c5926j.m3711a(this.f8079h, 7);
        String str2 = this.f8080i;
        if (str2 != null) {
            c5926j.m3706a(str2, 8);
        }
        String str3 = this.f8081j;
        if (str3 != null) {
            c5926j.m3706a(str3, 9);
        }
        Map<String, String> map = this.f8082k;
        if (map != null) {
            c5926j.m3708a((Map) map, 10);
        }
        String str4 = this.f8083l;
        if (str4 != null) {
            c5926j.m3706a(str4, 11);
        }
        c5926j.m3703a(this.f8084m, 12);
        String str5 = this.f8085n;
        if (str5 != null) {
            c5926j.m3706a(str5, 13);
        }
        String str6 = this.f8086o;
        if (str6 != null) {
            c5926j.m3706a(str6, 14);
        }
        String str7 = this.f8087p;
        if (str7 != null) {
            c5926j.m3706a(str7, 15);
        }
        c5926j.m3703a(this.f8088q, 16);
        String str8 = this.f8089r;
        if (str8 != null) {
            c5926j.m3706a(str8, 17);
        }
        String str9 = this.f8090s;
        if (str9 != null) {
            c5926j.m3706a(str9, 18);
        }
        String str10 = this.f8091t;
        if (str10 != null) {
            c5926j.m3706a(str10, 19);
        }
        String str11 = this.f8092u;
        if (str11 != null) {
            c5926j.m3706a(str11, 20);
        }
        String str12 = this.f8093v;
        if (str12 != null) {
            c5926j.m3706a(str12, 21);
        }
        String str13 = this.f8094w;
        if (str13 != null) {
            c5926j.m3706a(str13, 22);
        }
        String str14 = this.f8095x;
        if (str14 != null) {
            c5926j.m3706a(str14, 23);
        }
    }

    static {
        f8070y = r0;
        byte[] bArr = {0};
        f8071z.put("", "");
    }

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3649a(C5925i c5925i) {
        this.f8072a = c5925i.m3686a(this.f8072a, 0, true);
        this.f8073b = c5925i.m3695b(1, true);
        this.f8074c = c5925i.m3695b(2, true);
        this.f8075d = c5925i.m3695b(3, true);
        this.f8076e = c5925i.m3695b(4, false);
        this.f8077f = c5925i.m3695b(5, true);
        this.f8078g = c5925i.m3686a(this.f8078g, 6, true);
        byte[] bArr = f8070y;
        this.f8079h = c5925i.m3696c(7, true);
        this.f8080i = c5925i.m3695b(8, false);
        this.f8081j = c5925i.m3695b(9, false);
        this.f8082k = (Map) c5925i.m3690a((C5925i) f8071z, 10, false);
        this.f8083l = c5925i.m3695b(11, false);
        this.f8084m = c5925i.m3688a(this.f8084m, 12, false);
        this.f8085n = c5925i.m3695b(13, false);
        this.f8086o = c5925i.m3695b(14, false);
        this.f8087p = c5925i.m3695b(15, false);
        this.f8088q = c5925i.m3688a(this.f8088q, 16, false);
        this.f8089r = c5925i.m3695b(17, false);
        this.f8090s = c5925i.m3695b(18, false);
        this.f8091t = c5925i.m3695b(19, false);
        this.f8092u = c5925i.m3695b(20, false);
        this.f8093v = c5925i.m3695b(21, false);
        this.f8094w = c5925i.m3695b(22, false);
        this.f8095x = c5925i.m3695b(23, false);
    }
}
