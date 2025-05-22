package org.bouncycastle.jce.spec;

import java.math.BigInteger;

/* loaded from: classes9.dex */
public class GOST3410PublicKeyParameterSetSpec {

    /* renamed from: a */
    private BigInteger f9708a;

    /* renamed from: p */
    private BigInteger f9709p;

    /* renamed from: q */
    private BigInteger f9710q;

    public GOST3410PublicKeyParameterSetSpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f9709p = bigInteger;
        this.f9710q = bigInteger2;
        this.f9708a = bigInteger3;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GOST3410PublicKeyParameterSetSpec)) {
            return false;
        }
        GOST3410PublicKeyParameterSetSpec gOST3410PublicKeyParameterSetSpec = (GOST3410PublicKeyParameterSetSpec) obj;
        return this.f9708a.equals(gOST3410PublicKeyParameterSetSpec.f9708a) && this.f9709p.equals(gOST3410PublicKeyParameterSetSpec.f9709p) && this.f9710q.equals(gOST3410PublicKeyParameterSetSpec.f9710q);
    }

    public BigInteger getA() {
        return this.f9708a;
    }

    public BigInteger getP() {
        return this.f9709p;
    }

    public BigInteger getQ() {
        return this.f9710q;
    }

    public int hashCode() {
        return (this.f9708a.hashCode() ^ this.f9709p.hashCode()) ^ this.f9710q.hashCode();
    }
}
