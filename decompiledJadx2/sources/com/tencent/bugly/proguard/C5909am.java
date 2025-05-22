package com.tencent.bugly.proguard;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.am */
/* loaded from: classes7.dex */
public final class C5909am extends AbstractC5927k implements Cloneable {

    /* renamed from: d */
    private static byte[] f8035d;

    /* renamed from: a */
    private byte f8036a;

    /* renamed from: b */
    private String f8037b;

    /* renamed from: c */
    private byte[] f8038c;

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3651a(StringBuilder sb, int i) {
    }

    public C5909am() {
        this.f8036a = (byte) 0;
        this.f8037b = "";
        this.f8038c = null;
    }

    public C5909am(byte b, String str, byte[] bArr) {
        this.f8036a = (byte) 0;
        this.f8037b = "";
        this.f8038c = null;
        this.f8036a = b;
        this.f8037b = str;
        this.f8038c = bArr;
    }

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3650a(C5926j c5926j) {
        c5926j.m3701a(this.f8036a, 0);
        c5926j.m3706a(this.f8037b, 1);
        byte[] bArr = this.f8038c;
        if (bArr != null) {
            c5926j.m3711a(bArr, 2);
        }
    }

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3649a(C5925i c5925i) {
        this.f8036a = c5925i.m3685a(this.f8036a, 0, true);
        this.f8037b = c5925i.m3695b(1, true);
        if (f8035d == null) {
            f8035d = r0;
            byte[] bArr = {0};
        }
        byte[] bArr2 = f8035d;
        this.f8038c = c5925i.m3696c(2, false);
    }
}
