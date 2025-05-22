package org.bouncycastle.crypto.params;

import java.math.BigInteger;

/* loaded from: classes9.dex */
public class SRP6GroupParameters {

    /* renamed from: N */
    private BigInteger f9612N;

    /* renamed from: g */
    private BigInteger f9613g;

    public SRP6GroupParameters(BigInteger bigInteger, BigInteger bigInteger2) {
        this.f9612N = bigInteger;
        this.f9613g = bigInteger2;
    }

    public BigInteger getG() {
        return this.f9613g;
    }

    public BigInteger getN() {
        return this.f9612N;
    }
}
