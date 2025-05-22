package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.at */
/* loaded from: classes7.dex */
public final class C5916at extends AbstractC5927k {

    /* renamed from: i */
    private static Map<String, String> f8123i = new HashMap();

    /* renamed from: a */
    public long f8124a = 0;

    /* renamed from: b */
    public byte f8125b = 0;

    /* renamed from: c */
    public String f8126c = "";

    /* renamed from: d */
    public String f8127d = "";

    /* renamed from: e */
    public String f8128e = "";

    /* renamed from: f */
    public Map<String, String> f8129f = null;

    /* renamed from: g */
    public String f8130g = "";

    /* renamed from: h */
    public boolean f8131h = true;

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3650a(C5926j c5926j) {
        c5926j.m3703a(this.f8124a, 0);
        c5926j.m3701a(this.f8125b, 1);
        String str = this.f8126c;
        if (str != null) {
            c5926j.m3706a(str, 2);
        }
        String str2 = this.f8127d;
        if (str2 != null) {
            c5926j.m3706a(str2, 3);
        }
        String str3 = this.f8128e;
        if (str3 != null) {
            c5926j.m3706a(str3, 4);
        }
        Map<String, String> map = this.f8129f;
        if (map != null) {
            c5926j.m3708a((Map) map, 5);
        }
        String str4 = this.f8130g;
        if (str4 != null) {
            c5926j.m3706a(str4, 6);
        }
        c5926j.m3710a(this.f8131h, 7);
    }

    static {
        f8123i.put("", "");
    }

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3649a(C5925i c5925i) {
        this.f8124a = c5925i.m3688a(this.f8124a, 0, true);
        this.f8125b = c5925i.m3685a(this.f8125b, 1, true);
        this.f8126c = c5925i.m3695b(2, false);
        this.f8127d = c5925i.m3695b(3, false);
        this.f8128e = c5925i.m3695b(4, false);
        this.f8129f = (Map) c5925i.m3690a((C5925i) f8123i, 5, false);
        this.f8130g = c5925i.m3695b(6, false);
        boolean z = this.f8131h;
        this.f8131h = c5925i.m3694a(7, false);
    }
}
