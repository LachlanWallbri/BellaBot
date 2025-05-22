package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.c */
/* loaded from: classes7.dex */
public class C5919c extends C5896a {

    /* renamed from: d */
    protected HashMap<String, byte[]> f8139d = null;

    /* renamed from: e */
    private HashMap<String, Object> f8140e = new HashMap<>();

    /* renamed from: f */
    private C5925i f8141f = new C5925i();

    @Override // com.tencent.bugly.proguard.C5896a
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo3623a(String str) {
        super.mo3623a(str);
    }

    /* renamed from: c */
    public void mo3653c() {
        this.f8139d = new HashMap<>();
    }

    @Override // com.tencent.bugly.proguard.C5896a
    /* renamed from: a */
    public <T> void mo3624a(String str, T t) {
        if (this.f8139d == null) {
            super.mo3624a(str, (String) t);
            return;
        }
        if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        }
        if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        }
        if (t instanceof Set) {
            throw new IllegalArgumentException("can not support Set");
        }
        C5926j c5926j = new C5926j();
        c5926j.m3699a(this.f8012b);
        c5926j.m3705a(t, 0);
        this.f8139d.put(str, C5928l.m3717a(c5926j.m3700a()));
    }

    /* renamed from: b */
    public final <T> T m3652b(String str, T t) throws C5918b {
        HashMap<String, byte[]> hashMap = this.f8139d;
        if (hashMap != null) {
            if (!hashMap.containsKey(str)) {
                return null;
            }
            if (this.f8140e.containsKey(str)) {
                return (T) this.f8140e.get(str);
            }
            try {
                this.f8141f.m3693a(this.f8139d.get(str));
                this.f8141f.m3687a(this.f8012b);
                T t2 = (T) this.f8141f.m3690a((C5925i) t, 0, true);
                if (t2 != null) {
                    this.f8140e.put(str, t2);
                }
                return t2;
            } catch (Exception e) {
                throw new C5918b(e);
            }
        }
        if (!this.f8011a.containsKey(str)) {
            return null;
        }
        if (this.f8140e.containsKey(str)) {
            return (T) this.f8140e.get(str);
        }
        byte[] bArr = new byte[0];
        Iterator<Map.Entry<String, byte[]>> it = this.f8011a.get(str).entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry<String, byte[]> next = it.next();
            next.getKey();
            bArr = next.getValue();
        }
        try {
            this.f8141f.m3693a(bArr);
            this.f8141f.m3687a(this.f8012b);
            T t3 = (T) this.f8141f.m3690a((C5925i) t, 0, true);
            this.f8140e.put(str, t3);
            return t3;
        } catch (Exception e2) {
            throw new C5918b(e2);
        }
    }

    @Override // com.tencent.bugly.proguard.C5896a
    /* renamed from: a */
    public byte[] mo3626a() {
        if (this.f8139d != null) {
            C5926j c5926j = new C5926j(0);
            c5926j.m3699a(this.f8012b);
            c5926j.m3708a((Map) this.f8139d, 0);
            return C5928l.m3717a(c5926j.m3700a());
        }
        return super.mo3626a();
    }

    @Override // com.tencent.bugly.proguard.C5896a
    /* renamed from: a */
    public void mo3625a(byte[] bArr) {
        try {
            super.mo3625a(bArr);
        } catch (Exception unused) {
            this.f8141f.m3693a(bArr);
            this.f8141f.m3687a(this.f8012b);
            HashMap hashMap = new HashMap(1);
            hashMap.put("", new byte[0]);
            this.f8139d = this.f8141f.m3691a((Map) hashMap, 0, false);
        }
    }
}
