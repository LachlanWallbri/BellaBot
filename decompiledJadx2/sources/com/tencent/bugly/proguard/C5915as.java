package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.as */
/* loaded from: classes7.dex */
public final class C5915as extends AbstractC5927k implements Cloneable {

    /* renamed from: o */
    private static /* synthetic */ boolean f8110o = !C5915as.class.desiredAssertionStatus();

    /* renamed from: m */
    private static C5914ar f8108m = new C5914ar();

    /* renamed from: n */
    private static Map<String, String> f8109n = new HashMap();

    /* renamed from: a */
    public boolean f8111a = true;

    /* renamed from: b */
    public boolean f8112b = true;

    /* renamed from: c */
    public boolean f8113c = true;

    /* renamed from: d */
    public String f8114d = "";

    /* renamed from: e */
    public String f8115e = "";

    /* renamed from: f */
    public C5914ar f8116f = null;

    /* renamed from: g */
    public Map<String, String> f8117g = null;

    /* renamed from: h */
    public long f8118h = 0;

    /* renamed from: j */
    private String f8120j = "";

    /* renamed from: k */
    private String f8121k = "";

    /* renamed from: l */
    private int f8122l = 0;

    /* renamed from: i */
    public int f8119i = 0;

    static {
        f8109n.put("", "");
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        C5915as c5915as = (C5915as) obj;
        return C5928l.m3716a(this.f8111a, c5915as.f8111a) && C5928l.m3716a(this.f8112b, c5915as.f8112b) && C5928l.m3716a(this.f8113c, c5915as.f8113c) && C5928l.m3715a(this.f8114d, c5915as.f8114d) && C5928l.m3715a(this.f8115e, c5915as.f8115e) && C5928l.m3715a(this.f8116f, c5915as.f8116f) && C5928l.m3715a(this.f8117g, c5915as.f8117g) && C5928l.m3714a(this.f8118h, c5915as.f8118h) && C5928l.m3715a(this.f8120j, c5915as.f8120j) && C5928l.m3715a(this.f8121k, c5915as.f8121k) && C5928l.m3713a(this.f8122l, c5915as.f8122l) && C5928l.m3713a(this.f8119i, c5915as.f8119i);
    }

    public final int hashCode() {
        try {
            throw new Exception("Need define key first!");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public final Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            if (f8110o) {
                return null;
            }
            throw new AssertionError();
        }
    }

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3650a(C5926j c5926j) {
        c5926j.m3710a(this.f8111a, 0);
        c5926j.m3710a(this.f8112b, 1);
        c5926j.m3710a(this.f8113c, 2);
        String str = this.f8114d;
        if (str != null) {
            c5926j.m3706a(str, 3);
        }
        String str2 = this.f8115e;
        if (str2 != null) {
            c5926j.m3706a(str2, 4);
        }
        C5914ar c5914ar = this.f8116f;
        if (c5914ar != null) {
            c5926j.m3704a((AbstractC5927k) c5914ar, 5);
        }
        Map<String, String> map = this.f8117g;
        if (map != null) {
            c5926j.m3708a((Map) map, 6);
        }
        c5926j.m3703a(this.f8118h, 7);
        String str3 = this.f8120j;
        if (str3 != null) {
            c5926j.m3706a(str3, 8);
        }
        String str4 = this.f8121k;
        if (str4 != null) {
            c5926j.m3706a(str4, 9);
        }
        c5926j.m3702a(this.f8122l, 10);
        c5926j.m3702a(this.f8119i, 11);
    }

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3649a(C5925i c5925i) {
        boolean z = this.f8111a;
        this.f8111a = c5925i.m3694a(0, true);
        boolean z2 = this.f8112b;
        this.f8112b = c5925i.m3694a(1, true);
        boolean z3 = this.f8113c;
        this.f8113c = c5925i.m3694a(2, true);
        this.f8114d = c5925i.m3695b(3, false);
        this.f8115e = c5925i.m3695b(4, false);
        this.f8116f = (C5914ar) c5925i.m3689a((AbstractC5927k) f8108m, 5, false);
        this.f8117g = (Map) c5925i.m3690a((C5925i) f8109n, 6, false);
        this.f8118h = c5925i.m3688a(this.f8118h, 7, false);
        this.f8120j = c5925i.m3695b(8, false);
        this.f8121k = c5925i.m3695b(9, false);
        this.f8122l = c5925i.m3686a(this.f8122l, 10, false);
        this.f8119i = c5925i.m3686a(this.f8119i, 11, false);
    }

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3651a(StringBuilder sb, int i) {
        C5924h c5924h = new C5924h(sb, i);
        c5924h.m3668a(this.f8111a, "enable");
        c5924h.m3668a(this.f8112b, "enableUserInfo");
        c5924h.m3668a(this.f8113c, "enableQuery");
        c5924h.m3665a(this.f8114d, "url");
        c5924h.m3665a(this.f8115e, "expUrl");
        c5924h.m3664a((AbstractC5927k) this.f8116f, "security");
        c5924h.m3666a((Map) this.f8117g, "valueMap");
        c5924h.m3663a(this.f8118h, "strategylastUpdateTime");
        c5924h.m3665a(this.f8120j, "httpsUrl");
        c5924h.m3665a(this.f8121k, "httpsExpUrl");
        c5924h.m3662a(this.f8122l, "eventRecordCount");
        c5924h.m3662a(this.f8119i, "eventTimeInterval");
    }
}
