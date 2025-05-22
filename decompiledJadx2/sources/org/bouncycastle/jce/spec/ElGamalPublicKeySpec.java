package org.bouncycastle.jce.spec;

import java.math.BigInteger;

/* loaded from: classes9.dex */
public class ElGamalPublicKeySpec extends ElGamalKeySpec {

    /* renamed from: y */
    private BigInteger f9703y;

    public ElGamalPublicKeySpec(BigInteger bigInteger, ElGamalParameterSpec elGamalParameterSpec) {
        super(elGamalParameterSpec);
        this.f9703y = bigInteger;
    }

    public BigInteger getY() {
        return this.f9703y;
    }
}
