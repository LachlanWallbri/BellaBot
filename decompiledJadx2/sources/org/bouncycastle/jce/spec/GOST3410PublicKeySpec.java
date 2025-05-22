package org.bouncycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.KeySpec;

/* loaded from: classes9.dex */
public class GOST3410PublicKeySpec implements KeySpec {

    /* renamed from: a */
    private BigInteger f9711a;

    /* renamed from: p */
    private BigInteger f9712p;

    /* renamed from: q */
    private BigInteger f9713q;

    /* renamed from: y */
    private BigInteger f9714y;

    public GOST3410PublicKeySpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        this.f9714y = bigInteger;
        this.f9712p = bigInteger2;
        this.f9713q = bigInteger3;
        this.f9711a = bigInteger4;
    }

    public BigInteger getA() {
        return this.f9711a;
    }

    public BigInteger getP() {
        return this.f9712p;
    }

    public BigInteger getQ() {
        return this.f9713q;
    }

    public BigInteger getY() {
        return this.f9714y;
    }
}
