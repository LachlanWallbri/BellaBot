package org.bouncycastle.crypto.params;

/* loaded from: classes9.dex */
public class GOST3410ValidationParameters {

    /* renamed from: c */
    private int f9592c;

    /* renamed from: cL */
    private long f9593cL;

    /* renamed from: x0 */
    private int f9594x0;
    private long x0L;

    public GOST3410ValidationParameters(int i, int i2) {
        this.f9594x0 = i;
        this.f9592c = i2;
    }

    public GOST3410ValidationParameters(long j, long j2) {
        this.x0L = j;
        this.f9593cL = j2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GOST3410ValidationParameters)) {
            return false;
        }
        GOST3410ValidationParameters gOST3410ValidationParameters = (GOST3410ValidationParameters) obj;
        return gOST3410ValidationParameters.f9592c == this.f9592c && gOST3410ValidationParameters.f9594x0 == this.f9594x0 && gOST3410ValidationParameters.f9593cL == this.f9593cL && gOST3410ValidationParameters.x0L == this.x0L;
    }

    public int getC() {
        return this.f9592c;
    }

    public long getCL() {
        return this.f9593cL;
    }

    public int getX0() {
        return this.f9594x0;
    }

    public long getX0L() {
        return this.x0L;
    }

    public int hashCode() {
        int i = this.f9594x0 ^ this.f9592c;
        long j = this.x0L;
        int i2 = (i ^ ((int) j)) ^ ((int) (j >> 32));
        long j2 = this.f9593cL;
        return (i2 ^ ((int) j2)) ^ ((int) (j2 >> 32));
    }
}
