package com.loc;

/* compiled from: HashCodeBuilder.java */
/* renamed from: com.loc.v */
/* loaded from: classes4.dex */
public final class C3896v {

    /* renamed from: a */
    private final int f4327a = 37;

    /* renamed from: b */
    private int f4328b;

    public C3896v() {
        this.f4328b = 0;
        this.f4328b = 17;
    }

    /* renamed from: a */
    private C3896v m3245a(long j) {
        this.f4328b = (this.f4328b * this.f4327a) + ((int) (j ^ (j >> 32)));
        return this;
    }

    /* renamed from: a */
    public final int m3246a() {
        return this.f4328b;
    }

    /* renamed from: a */
    public final C3896v m3247a(Object obj) {
        if (obj != null) {
            if (obj.getClass().isArray()) {
                int i = 0;
                if (obj instanceof long[]) {
                    long[] jArr = (long[]) obj;
                    if (jArr != null) {
                        while (i < jArr.length) {
                            m3245a(jArr[i]);
                            i++;
                        }
                    }
                } else if (obj instanceof int[]) {
                    int[] iArr = (int[]) obj;
                    if (iArr != null) {
                        while (i < iArr.length) {
                            this.f4328b = (this.f4328b * this.f4327a) + iArr[i];
                            i++;
                        }
                    }
                } else if (obj instanceof short[]) {
                    short[] sArr = (short[]) obj;
                    if (sArr != null) {
                        while (i < sArr.length) {
                            this.f4328b = (this.f4328b * this.f4327a) + sArr[i];
                            i++;
                        }
                    }
                } else if (obj instanceof char[]) {
                    char[] cArr = (char[]) obj;
                    if (cArr != null) {
                        while (i < cArr.length) {
                            this.f4328b = (this.f4328b * this.f4327a) + cArr[i];
                            i++;
                        }
                    }
                } else if (obj instanceof byte[]) {
                    byte[] bArr = (byte[]) obj;
                    if (bArr != null) {
                        while (i < bArr.length) {
                            this.f4328b = (this.f4328b * this.f4327a) + bArr[i];
                            i++;
                        }
                    }
                } else if (obj instanceof double[]) {
                    double[] dArr = (double[]) obj;
                    if (dArr != null) {
                        while (i < dArr.length) {
                            m3245a(Double.doubleToLongBits(dArr[i]));
                            i++;
                        }
                    }
                } else if (obj instanceof float[]) {
                    float[] fArr = (float[]) obj;
                    if (fArr != null) {
                        while (i < fArr.length) {
                            this.f4328b = (this.f4328b * this.f4327a) + Float.floatToIntBits(fArr[i]);
                            i++;
                        }
                    }
                } else if (obj instanceof boolean[]) {
                    boolean[] zArr = (boolean[]) obj;
                    if (zArr != null) {
                        while (i < zArr.length) {
                            this.f4328b = (this.f4328b * this.f4327a) + (!zArr[i] ? 1 : 0);
                            i++;
                        }
                    }
                } else {
                    m3248a((Object[]) obj);
                }
            } else {
                this.f4328b = (this.f4328b * this.f4327a) + obj.hashCode();
            }
            return this;
        }
        this.f4328b *= this.f4327a;
        return this;
    }

    /* renamed from: a */
    public final C3896v m3248a(Object[] objArr) {
        if (objArr == null) {
            this.f4328b *= this.f4327a;
        } else {
            for (Object obj : objArr) {
                m3247a(obj);
            }
        }
        return this;
    }

    public final int hashCode() {
        return this.f4328b;
    }
}
