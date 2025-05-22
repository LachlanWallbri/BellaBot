package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.aq */
/* loaded from: classes7.dex */
public final class C5913aq extends AbstractC5927k {

    /* renamed from: i */
    private static byte[] f8096i;

    /* renamed from: j */
    private static Map<String, String> f8097j = new HashMap();

    /* renamed from: a */
    public byte f8098a = 0;

    /* renamed from: b */
    public int f8099b = 0;

    /* renamed from: c */
    public byte[] f8100c = null;

    /* renamed from: d */
    public String f8101d = "";

    /* renamed from: e */
    public long f8102e = 0;

    /* renamed from: h */
    private String f8105h = "";

    /* renamed from: f */
    public String f8103f = "";

    /* renamed from: g */
    public Map<String, String> f8104g = null;

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3650a(C5926j c5926j) {
        c5926j.m3701a(this.f8098a, 0);
        c5926j.m3702a(this.f8099b, 1);
        byte[] bArr = this.f8100c;
        if (bArr != null) {
            c5926j.m3711a(bArr, 2);
        }
        String str = this.f8101d;
        if (str != null) {
            c5926j.m3706a(str, 3);
        }
        c5926j.m3703a(this.f8102e, 4);
        String str2 = this.f8105h;
        if (str2 != null) {
            c5926j.m3706a(str2, 5);
        }
        String str3 = this.f8103f;
        if (str3 != null) {
            c5926j.m3706a(str3, 6);
        }
        Map<String, String> map = this.f8104g;
        if (map != null) {
            c5926j.m3708a((Map) map, 7);
        }
    }

    static {
        f8096i = r0;
        byte[] bArr = {0};
        f8097j.put("", "");
    }

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3649a(C5925i c5925i) {
        this.f8098a = c5925i.m3685a(this.f8098a, 0, true);
        this.f8099b = c5925i.m3686a(this.f8099b, 1, true);
        byte[] bArr = f8096i;
        this.f8100c = c5925i.m3696c(2, false);
        this.f8101d = c5925i.m3695b(3, false);
        this.f8102e = c5925i.m3688a(this.f8102e, 4, false);
        this.f8105h = c5925i.m3695b(5, false);
        this.f8103f = c5925i.m3695b(6, false);
        this.f8104g = (Map) c5925i.m3690a((C5925i) f8097j, 7, false);
    }
}
