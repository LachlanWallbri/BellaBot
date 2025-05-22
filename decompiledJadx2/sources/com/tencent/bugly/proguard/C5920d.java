package com.tencent.bugly.proguard;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.d */
/* loaded from: classes7.dex */
public final class C5920d extends C5919c {

    /* renamed from: f */
    private static HashMap<String, byte[]> f8142f;

    /* renamed from: g */
    private static HashMap<String, HashMap<String, byte[]>> f8143g;

    /* renamed from: e */
    private C5922f f8144e = new C5922f();

    public C5920d() {
        this.f8144e.f8149a = (short) 2;
    }

    @Override // com.tencent.bugly.proguard.C5919c, com.tencent.bugly.proguard.C5896a
    /* renamed from: a */
    public final <T> void mo3624a(String str, T t) {
        if (str.startsWith(".")) {
            throw new IllegalArgumentException("put name can not startwith . , now is " + str);
        }
        super.mo3624a(str, (String) t);
    }

    @Override // com.tencent.bugly.proguard.C5919c
    /* renamed from: c */
    public final void mo3653c() {
        super.mo3653c();
        this.f8144e.f8149a = (short) 3;
    }

    @Override // com.tencent.bugly.proguard.C5919c, com.tencent.bugly.proguard.C5896a
    /* renamed from: a */
    public final byte[] mo3626a() {
        if (this.f8144e.f8149a == 2) {
            if (this.f8144e.f8151c.equals("")) {
                throw new IllegalArgumentException("servantName can not is null");
            }
            if (this.f8144e.f8152d.equals("")) {
                throw new IllegalArgumentException("funcName can not is null");
            }
        } else {
            if (this.f8144e.f8151c == null) {
                this.f8144e.f8151c = "";
            }
            if (this.f8144e.f8152d == null) {
                this.f8144e.f8152d = "";
            }
        }
        C5926j c5926j = new C5926j(0);
        c5926j.m3699a(this.f8012b);
        if (this.f8144e.f8149a == 2) {
            c5926j.m3708a((Map) this.f8011a, 0);
        } else {
            c5926j.m3708a((Map) this.f8139d, 0);
        }
        this.f8144e.f8153e = C5928l.m3717a(c5926j.m3700a());
        C5926j c5926j2 = new C5926j(0);
        c5926j2.m3699a(this.f8012b);
        this.f8144e.mo3650a(c5926j2);
        byte[] m3717a = C5928l.m3717a(c5926j2.m3700a());
        int length = m3717a.length + 4;
        ByteBuffer allocate = ByteBuffer.allocate(length);
        allocate.putInt(length).put(m3717a).flip();
        return allocate.array();
    }

    @Override // com.tencent.bugly.proguard.C5919c, com.tencent.bugly.proguard.C5896a
    /* renamed from: a */
    public final void mo3625a(byte[] bArr) {
        if (bArr.length < 4) {
            throw new IllegalArgumentException("decode package must include size head");
        }
        try {
            C5925i c5925i = new C5925i(bArr, 4);
            c5925i.m3687a(this.f8012b);
            this.f8144e.mo3649a(c5925i);
            if (this.f8144e.f8149a == 3) {
                C5925i c5925i2 = new C5925i(this.f8144e.f8153e);
                c5925i2.m3687a(this.f8012b);
                if (f8142f == null) {
                    HashMap<String, byte[]> hashMap = new HashMap<>();
                    f8142f = hashMap;
                    hashMap.put("", new byte[0]);
                }
                this.f8139d = c5925i2.m3691a((Map) f8142f, 0, false);
                return;
            }
            C5925i c5925i3 = new C5925i(this.f8144e.f8153e);
            c5925i3.m3687a(this.f8012b);
            if (f8143g == null) {
                f8143g = new HashMap<>();
                HashMap<String, byte[]> hashMap2 = new HashMap<>();
                hashMap2.put("", new byte[0]);
                f8143g.put("", hashMap2);
            }
            this.f8011a = c5925i3.m3691a((Map) f8143g, 0, false);
            new HashMap();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: b */
    public final void m3655b(String str) {
        this.f8144e.f8151c = str;
    }

    /* renamed from: c */
    public final void m3656c(String str) {
        this.f8144e.f8152d = str;
    }

    /* renamed from: b */
    public final void m3654b(int i) {
        this.f8144e.f8150b = 1;
    }
}
