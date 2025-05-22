package com.loc;

import java.util.HashMap;
import java.util.Map;

/* compiled from: LogUpdateRequest.java */
/* renamed from: com.loc.y */
/* loaded from: classes4.dex */
public final class C3899y extends AbstractC3839bn {

    /* renamed from: a */
    private byte[] f4350a;

    /* renamed from: b */
    private String f4351b;

    public C3899y(byte[] bArr) {
        this.f4351b = "1";
        this.f4350a = (byte[]) bArr.clone();
    }

    public C3899y(byte[] bArr, String str) {
        this.f4351b = "1";
        this.f4350a = (byte[]) bArr.clone();
        this.f4351b = str;
    }

    @Override // com.loc.AbstractC3839bn
    /* renamed from: a */
    public final Map<String, String> mo2487a() {
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", "application/zip");
        hashMap.put("Content-Length", String.valueOf(this.f4350a.length));
        return hashMap;
    }

    @Override // com.loc.AbstractC3839bn
    /* renamed from: b */
    public final String mo2488b() {
        String str = C3895u.f4326b;
        byte[] m3232a = C3894t.m3232a(C3895u.f4325a);
        byte[] bArr = new byte[m3232a.length + 50];
        System.arraycopy(this.f4350a, 0, bArr, 0, 50);
        System.arraycopy(m3232a, 0, bArr, 50, m3232a.length);
        return String.format(str, "1", this.f4351b, "1", "open", C3890p.m3186a(bArr));
    }

    @Override // com.loc.AbstractC3839bn
    /* renamed from: c */
    public final Map<String, String> mo2489c() {
        return null;
    }

    @Override // com.loc.AbstractC3839bn
    /* renamed from: d */
    public final byte[] mo2611d() {
        return this.f4350a;
    }
}
