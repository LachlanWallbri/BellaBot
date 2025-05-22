package org.bouncycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.KeySpec;

/* loaded from: classes9.dex */
public class GOST3410PrivateKeySpec implements KeySpec {

    /* renamed from: a */
    private BigInteger f9704a;

    /* renamed from: p */
    private BigInteger f9705p;

    /* renamed from: q */
    private BigInteger f9706q;

    /* renamed from: x */
    private BigInteger f9707x;

    public GOST3410PrivateKeySpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        this.f9707x = bigInteger;
        this.f9705p = bigInteger2;
        this.f9706q = bigInteger3;
        this.f9704a = bigInteger4;
    }

    public BigInteger getA() {
        return this.f9704a;
    }

    public BigInteger getP() {
        return this.f9705p;
    }

    public BigInteger getQ() {
        return this.f9706q;
    }

    public BigInteger getX() {
        return this.f9707x;
    }
}
