package com.acs.smartcard;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* renamed from: com.acs.smartcard.c */
/* loaded from: classes.dex */
final class C0739c {

    /* renamed from: a */
    public int f195a;

    /* renamed from: b */
    public int f196b;

    /* renamed from: c */
    public int f197c;

    /* renamed from: d */
    public int f198d;

    /* renamed from: e */
    public int f199e;

    /* renamed from: f */
    public int f200f;

    /* renamed from: g */
    public int f201g;

    /* renamed from: h */
    public int f202h;

    /* renamed from: i */
    public int f203i;

    /* renamed from: j */
    public int f204j;

    /* renamed from: k */
    public int f205k;

    /* renamed from: l */
    public int f206l;

    /* renamed from: m */
    private int f207m;

    /* renamed from: n */
    private int f208n;

    /* renamed from: o */
    private int f209o;

    /* renamed from: p */
    private int f210p;

    /* renamed from: q */
    private int f211q;

    /* renamed from: r */
    private int f212r;

    /* renamed from: s */
    private int f213s;

    /* renamed from: t */
    private int f214t;

    /* renamed from: u */
    private int f215u;

    /* renamed from: v */
    private int f216v;

    public C0739c() {
    }

    public C0739c(byte[] bArr) {
        this.f207m = bArr[0] & 255;
        this.f208n = bArr[1] & 255;
        this.f209o = (bArr[2] & 255) | ((bArr[3] & 255) << 8);
        this.f195a = bArr[4] & 255;
        this.f196b = bArr[5] & 255;
        this.f197c = (bArr[6] & 255) | ((bArr[7] & 255) << 8) | ((bArr[8] & 255) << 16) | ((bArr[9] & 255) << 24);
        this.f198d = (bArr[10] & 255) | ((bArr[11] & 255) << 8) | ((bArr[12] & 255) << 16) | ((bArr[13] & 255) << 24);
        this.f199e = (bArr[14] & 255) | ((bArr[15] & 255) << 8) | ((bArr[16] & 255) << 16) | ((bArr[17] & 255) << 24);
        this.f210p = bArr[18] & 255;
        this.f200f = (bArr[19] & 255) | ((bArr[20] & 255) << 8) | ((bArr[21] & 255) << 16) | ((bArr[22] & 255) << 24);
        this.f201g = (bArr[23] & 255) | ((bArr[24] & 255) << 8) | ((bArr[25] & 255) << 16) | ((bArr[26] & 255) << 24);
        this.f211q = bArr[27] & 255;
        this.f202h = (bArr[28] & 255) | ((bArr[29] & 255) << 8) | ((bArr[30] & 255) << 16) | ((bArr[31] & 255) << 24);
        this.f212r = (bArr[32] & 255) | ((bArr[33] & 255) << 8) | ((bArr[34] & 255) << 16) | ((bArr[35] & 255) << 24);
        this.f213s = (bArr[36] & 255) | ((bArr[37] & 255) << 8) | ((bArr[38] & 255) << 16) | ((bArr[39] & 255) << 24);
        this.f203i = (bArr[40] & 255) | ((bArr[41] & 255) << 8) | ((bArr[42] & 255) << 16) | ((bArr[43] & 255) << 24);
        this.f204j = (bArr[44] & 255) | ((bArr[45] & 255) << 8) | ((bArr[46] & 255) << 16) | ((bArr[47] & 255) << 24);
        this.f214t = bArr[48] & 255;
        this.f215u = bArr[49] & 255;
        this.f205k = (bArr[50] & 255) | ((bArr[51] & 255) << 8);
        this.f206l = bArr[52] & 255;
        this.f216v = bArr[53] & 255;
    }

    public C0739c(C0739c c0739c) {
        this.f207m = c0739c.f207m;
        this.f208n = c0739c.f208n;
        this.f209o = c0739c.f209o;
        this.f195a = c0739c.f195a;
        this.f196b = c0739c.f196b;
        this.f197c = c0739c.f197c;
        this.f198d = c0739c.f198d;
        this.f199e = c0739c.f199e;
        this.f210p = c0739c.f210p;
        this.f200f = c0739c.f200f;
        this.f201g = c0739c.f201g;
        this.f211q = c0739c.f211q;
        this.f202h = c0739c.f202h;
        this.f212r = c0739c.f212r;
        this.f213s = c0739c.f213s;
        this.f203i = c0739c.f203i;
        this.f204j = c0739c.f204j;
        this.f214t = c0739c.f214t;
        this.f215u = c0739c.f215u;
        this.f205k = c0739c.f205k;
        this.f206l = c0739c.f206l;
        this.f216v = c0739c.f216v;
    }
}
