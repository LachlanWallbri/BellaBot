package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;

/* loaded from: classes9.dex */
public class ElGamalParameters implements CipherParameters {

    /* renamed from: g */
    private BigInteger f9582g;

    /* renamed from: l */
    private int f9583l;

    /* renamed from: p */
    private BigInteger f9584p;

    public ElGamalParameters(BigInteger bigInteger, BigInteger bigInteger2) {
        this(bigInteger, bigInteger2, 0);
    }

    public ElGamalParameters(BigInteger bigInteger, BigInteger bigInteger2, int i) {
        this.f9582g = bigInteger2;
        this.f9584p = bigInteger;
        this.f9583l = i;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ElGamalParameters)) {
            return false;
        }
        ElGamalParameters elGamalParameters = (ElGamalParameters) obj;
        return elGamalParameters.getP().equals(this.f9584p) && elGamalParameters.getG().equals(this.f9582g) && elGamalParameters.getL() == this.f9583l;
    }

    public BigInteger getG() {
        return this.f9582g;
    }

    public int getL() {
        return this.f9583l;
    }

    public BigInteger getP() {
        return this.f9584p;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getG().hashCode()) + this.f9583l;
    }
}
