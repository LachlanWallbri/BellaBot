package org.bouncycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;

/* loaded from: classes9.dex */
public class ElGamalParameterSpec implements AlgorithmParameterSpec {

    /* renamed from: g */
    private BigInteger f9700g;

    /* renamed from: p */
    private BigInteger f9701p;

    public ElGamalParameterSpec(BigInteger bigInteger, BigInteger bigInteger2) {
        this.f9701p = bigInteger;
        this.f9700g = bigInteger2;
    }

    public BigInteger getG() {
        return this.f9700g;
    }

    public BigInteger getP() {
        return this.f9701p;
    }
}
