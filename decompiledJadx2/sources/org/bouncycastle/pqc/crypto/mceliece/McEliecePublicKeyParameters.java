package org.bouncycastle.pqc.crypto.mceliece;

import org.bouncycastle.pqc.math.linearalgebra.GF2Matrix;

/* loaded from: classes9.dex */
public class McEliecePublicKeyParameters extends McElieceKeyParameters {

    /* renamed from: g */
    private GF2Matrix f9920g;

    /* renamed from: n */
    private int f9921n;

    /* renamed from: t */
    private int f9922t;

    public McEliecePublicKeyParameters(int i, int i2, GF2Matrix gF2Matrix) {
        super(false, null);
        this.f9921n = i;
        this.f9922t = i2;
        this.f9920g = new GF2Matrix(gF2Matrix);
    }

    public GF2Matrix getG() {
        return this.f9920g;
    }

    public int getK() {
        return this.f9920g.getNumRows();
    }

    public int getN() {
        return this.f9921n;
    }

    public int getT() {
        return this.f9922t;
    }
}
