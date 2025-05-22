package org.bouncycastle.crypto.params;

import java.math.BigInteger;

/* loaded from: classes9.dex */
public class RSAPrivateCrtKeyParameters extends RSAKeyParameters {

    /* renamed from: dP */
    private BigInteger f9607dP;

    /* renamed from: dQ */
    private BigInteger f9608dQ;

    /* renamed from: e */
    private BigInteger f9609e;

    /* renamed from: p */
    private BigInteger f9610p;

    /* renamed from: q */
    private BigInteger f9611q;
    private BigInteger qInv;

    public RSAPrivateCrtKeyParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, BigInteger bigInteger6, BigInteger bigInteger7, BigInteger bigInteger8) {
        super(true, bigInteger, bigInteger3);
        this.f9609e = bigInteger2;
        this.f9610p = bigInteger4;
        this.f9611q = bigInteger5;
        this.f9607dP = bigInteger6;
        this.f9608dQ = bigInteger7;
        this.qInv = bigInteger8;
    }

    public BigInteger getDP() {
        return this.f9607dP;
    }

    public BigInteger getDQ() {
        return this.f9608dQ;
    }

    public BigInteger getP() {
        return this.f9610p;
    }

    public BigInteger getPublicExponent() {
        return this.f9609e;
    }

    public BigInteger getQ() {
        return this.f9611q;
    }

    public BigInteger getQInv() {
        return this.qInv;
    }
}
