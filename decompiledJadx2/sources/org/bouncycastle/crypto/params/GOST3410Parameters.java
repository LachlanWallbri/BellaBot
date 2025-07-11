package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;

/* loaded from: classes9.dex */
public class GOST3410Parameters implements CipherParameters {

    /* renamed from: a */
    private BigInteger f9587a;

    /* renamed from: p */
    private BigInteger f9588p;

    /* renamed from: q */
    private BigInteger f9589q;
    private GOST3410ValidationParameters validation;

    public GOST3410Parameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f9588p = bigInteger;
        this.f9589q = bigInteger2;
        this.f9587a = bigInteger3;
    }

    public GOST3410Parameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, GOST3410ValidationParameters gOST3410ValidationParameters) {
        this.f9587a = bigInteger3;
        this.f9588p = bigInteger;
        this.f9589q = bigInteger2;
        this.validation = gOST3410ValidationParameters;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GOST3410Parameters)) {
            return false;
        }
        GOST3410Parameters gOST3410Parameters = (GOST3410Parameters) obj;
        return gOST3410Parameters.getP().equals(this.f9588p) && gOST3410Parameters.getQ().equals(this.f9589q) && gOST3410Parameters.getA().equals(this.f9587a);
    }

    public BigInteger getA() {
        return this.f9587a;
    }

    public BigInteger getP() {
        return this.f9588p;
    }

    public BigInteger getQ() {
        return this.f9589q;
    }

    public GOST3410ValidationParameters getValidationParameters() {
        return this.validation;
    }

    public int hashCode() {
        return (this.f9588p.hashCode() ^ this.f9589q.hashCode()) ^ this.f9587a.hashCode();
    }
}
