package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Memoable;

/* loaded from: classes9.dex */
public class CramerShoupParameters implements CipherParameters {

    /* renamed from: H */
    private Digest f9549H;

    /* renamed from: g1 */
    private BigInteger f9550g1;

    /* renamed from: g2 */
    private BigInteger f9551g2;

    /* renamed from: p */
    private BigInteger f9552p;

    public CramerShoupParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, Digest digest) {
        this.f9552p = bigInteger;
        this.f9550g1 = bigInteger2;
        this.f9551g2 = bigInteger3;
        this.f9549H = (Digest) ((Memoable) digest).copy();
        this.f9549H.reset();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CramerShoupParameters)) {
            return false;
        }
        CramerShoupParameters cramerShoupParameters = (CramerShoupParameters) obj;
        return cramerShoupParameters.getP().equals(this.f9552p) && cramerShoupParameters.getG1().equals(this.f9550g1) && cramerShoupParameters.getG2().equals(this.f9551g2);
    }

    public BigInteger getG1() {
        return this.f9550g1;
    }

    public BigInteger getG2() {
        return this.f9551g2;
    }

    public Digest getH() {
        return (Digest) ((Memoable) this.f9549H).copy();
    }

    public BigInteger getP() {
        return this.f9552p;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getG1().hashCode()) ^ getG2().hashCode();
    }
}
