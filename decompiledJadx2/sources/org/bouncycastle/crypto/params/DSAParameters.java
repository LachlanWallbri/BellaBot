package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;

/* loaded from: classes9.dex */
public class DSAParameters implements CipherParameters {

    /* renamed from: g */
    private BigInteger f9572g;

    /* renamed from: p */
    private BigInteger f9573p;

    /* renamed from: q */
    private BigInteger f9574q;
    private DSAValidationParameters validation;

    public DSAParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f9572g = bigInteger3;
        this.f9573p = bigInteger;
        this.f9574q = bigInteger2;
    }

    public DSAParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, DSAValidationParameters dSAValidationParameters) {
        this.f9572g = bigInteger3;
        this.f9573p = bigInteger;
        this.f9574q = bigInteger2;
        this.validation = dSAValidationParameters;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DSAParameters)) {
            return false;
        }
        DSAParameters dSAParameters = (DSAParameters) obj;
        return dSAParameters.getP().equals(this.f9573p) && dSAParameters.getQ().equals(this.f9574q) && dSAParameters.getG().equals(this.f9572g);
    }

    public BigInteger getG() {
        return this.f9572g;
    }

    public BigInteger getP() {
        return this.f9573p;
    }

    public BigInteger getQ() {
        return this.f9574q;
    }

    public DSAValidationParameters getValidationParameters() {
        return this.validation;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getQ().hashCode()) ^ getG().hashCode();
    }
}
