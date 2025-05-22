package org.mozilla.javascript.v8dtoa;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
class DiyFp {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int kSignificandSize = 64;
    static final long kUint64MSB = Long.MIN_VALUE;

    /* renamed from: e */
    private int f10282e;

    /* renamed from: f */
    private long f10283f;

    private static boolean uint64_gte(long j, long j2) {
        if (j != j2) {
            if (!(((j < 0) ^ (j > j2)) ^ (j2 < 0))) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DiyFp() {
        this.f10283f = 0L;
        this.f10282e = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DiyFp(long j, int i) {
        this.f10283f = j;
        this.f10282e = i;
    }

    void subtract(DiyFp diyFp) {
        this.f10283f -= diyFp.f10283f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DiyFp minus(DiyFp diyFp, DiyFp diyFp2) {
        DiyFp diyFp3 = new DiyFp(diyFp.f10283f, diyFp.f10282e);
        diyFp3.subtract(diyFp2);
        return diyFp3;
    }

    void multiply(DiyFp diyFp) {
        long j = this.f10283f;
        long j2 = j >>> 32;
        long j3 = j & 4294967295L;
        long j4 = diyFp.f10283f;
        long j5 = j4 >>> 32;
        long j6 = j4 & 4294967295L;
        long j7 = j2 * j5;
        long j8 = j5 * j3;
        long j9 = j2 * j6;
        this.f10282e += diyFp.f10282e + 64;
        this.f10283f = j7 + (j9 >>> 32) + (j8 >>> 32) + ((((((j3 * j6) >>> 32) + (j9 & 4294967295L)) + (4294967295L & j8)) + 2147483648L) >>> 32);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DiyFp times(DiyFp diyFp, DiyFp diyFp2) {
        DiyFp diyFp3 = new DiyFp(diyFp.f10283f, diyFp.f10282e);
        diyFp3.multiply(diyFp2);
        return diyFp3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void normalize() {
        long j = this.f10283f;
        int i = this.f10282e;
        while (((-18014398509481984L) & j) == 0) {
            j <<= 10;
            i -= 10;
        }
        while ((Long.MIN_VALUE & j) == 0) {
            j <<= 1;
            i--;
        }
        this.f10283f = j;
        this.f10282e = i;
    }

    static DiyFp normalize(DiyFp diyFp) {
        DiyFp diyFp2 = new DiyFp(diyFp.f10283f, diyFp.f10282e);
        diyFp2.normalize();
        return diyFp2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public long m4194f() {
        return this.f10283f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public int m4193e() {
        return this.f10282e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setF(long j) {
        this.f10283f = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setE(int i) {
        this.f10282e = i;
    }

    public String toString() {
        return "[DiyFp f:" + this.f10283f + ", e:" + this.f10282e + "]";
    }
}
