package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.f */
/* loaded from: classes7.dex */
public final class C5922f extends AbstractC5927k {

    /* renamed from: e */
    public byte[] f8153e;

    /* renamed from: i */
    private Map<String, String> f8157i;

    /* renamed from: j */
    private Map<String, String> f8158j;

    /* renamed from: m */
    private static /* synthetic */ boolean f8148m = !C5922f.class.desiredAssertionStatus();

    /* renamed from: k */
    private static byte[] f8146k = null;

    /* renamed from: l */
    private static Map<String, String> f8147l = null;

    /* renamed from: a */
    public short f8149a = 0;

    /* renamed from: f */
    private byte f8154f = 0;

    /* renamed from: g */
    private int f8155g = 0;

    /* renamed from: b */
    public int f8150b = 0;

    /* renamed from: c */
    public String f8151c = null;

    /* renamed from: d */
    public String f8152d = null;

    /* renamed from: h */
    private int f8156h = 0;

    public final boolean equals(Object obj) {
        C5922f c5922f = (C5922f) obj;
        return C5928l.m3713a(1, (int) c5922f.f8149a) && C5928l.m3713a(1, (int) c5922f.f8154f) && C5928l.m3713a(1, c5922f.f8155g) && C5928l.m3713a(1, c5922f.f8150b) && C5928l.m3715a((Object) 1, (Object) c5922f.f8151c) && C5928l.m3715a((Object) 1, (Object) c5922f.f8152d) && C5928l.m3715a((Object) 1, (Object) c5922f.f8153e) && C5928l.m3713a(1, c5922f.f8156h) && C5928l.m3715a((Object) 1, (Object) c5922f.f8157i) && C5928l.m3715a((Object) 1, (Object) c5922f.f8158j);
    }

    public final Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            if (f8148m) {
                return null;
            }
            throw new AssertionError();
        }
    }

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3650a(C5926j c5926j) {
        c5926j.m3709a(this.f8149a, 1);
        c5926j.m3701a(this.f8154f, 2);
        c5926j.m3702a(this.f8155g, 3);
        c5926j.m3702a(this.f8150b, 4);
        c5926j.m3706a(this.f8151c, 5);
        c5926j.m3706a(this.f8152d, 6);
        c5926j.m3711a(this.f8153e, 7);
        c5926j.m3702a(this.f8156h, 8);
        c5926j.m3708a((Map) this.f8157i, 9);
        c5926j.m3708a((Map) this.f8158j, 10);
    }

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3649a(C5925i c5925i) {
        try {
            this.f8149a = c5925i.m3692a(this.f8149a, 1, true);
            this.f8154f = c5925i.m3685a(this.f8154f, 2, true);
            this.f8155g = c5925i.m3686a(this.f8155g, 3, true);
            this.f8150b = c5925i.m3686a(this.f8150b, 4, true);
            this.f8151c = c5925i.m3695b(5, true);
            this.f8152d = c5925i.m3695b(6, true);
            if (f8146k == null) {
                f8146k = new byte[]{0};
            }
            byte[] bArr = f8146k;
            this.f8153e = c5925i.m3696c(7, true);
            this.f8156h = c5925i.m3686a(this.f8156h, 8, true);
            if (f8147l == null) {
                HashMap hashMap = new HashMap();
                f8147l = hashMap;
                hashMap.put("", "");
            }
            this.f8157i = (Map) c5925i.m3690a((C5925i) f8147l, 9, true);
            if (f8147l == null) {
                HashMap hashMap2 = new HashMap();
                f8147l = hashMap2;
                hashMap2.put("", "");
            }
            this.f8158j = (Map) c5925i.m3690a((C5925i) f8147l, 10, true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("RequestPacket decode error " + C5921e.m3657a(this.f8153e));
            throw new RuntimeException(e);
        }
    }

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3651a(StringBuilder sb, int i) {
        C5924h c5924h = new C5924h(sb, i);
        c5924h.m3667a(this.f8149a, "iVersion");
        c5924h.m3661a(this.f8154f, "cPacketType");
        c5924h.m3662a(this.f8155g, "iMessageType");
        c5924h.m3662a(this.f8150b, "iRequestId");
        c5924h.m3665a(this.f8151c, "sServantName");
        c5924h.m3665a(this.f8152d, "sFuncName");
        c5924h.m3669a(this.f8153e, "sBuffer");
        c5924h.m3662a(this.f8156h, "iTimeout");
        c5924h.m3666a((Map) this.f8157i, "context");
        c5924h.m3666a((Map) this.f8158j, "status");
    }
}
