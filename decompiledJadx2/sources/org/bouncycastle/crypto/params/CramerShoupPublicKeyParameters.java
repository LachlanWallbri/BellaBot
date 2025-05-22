package org.bouncycastle.crypto.params;

import java.math.BigInteger;

/* loaded from: classes9.dex */
public class CramerShoupPublicKeyParameters extends CramerShoupKeyParameters {

    /* renamed from: c */
    private BigInteger f9559c;

    /* renamed from: d */
    private BigInteger f9560d;

    /* renamed from: h */
    private BigInteger f9561h;

    public CramerShoupPublicKeyParameters(CramerShoupParameters cramerShoupParameters, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        super(false, cramerShoupParameters);
        this.f9559c = bigInteger;
        this.f9560d = bigInteger2;
        this.f9561h = bigInteger3;
    }

    @Override // org.bouncycastle.crypto.params.CramerShoupKeyParameters
    public boolean equals(Object obj) {
        if (!(obj instanceof CramerShoupPublicKeyParameters)) {
            return false;
        }
        CramerShoupPublicKeyParameters cramerShoupPublicKeyParameters = (CramerShoupPublicKeyParameters) obj;
        return cramerShoupPublicKeyParameters.getC().equals(this.f9559c) && cramerShoupPublicKeyParameters.getD().equals(this.f9560d) && cramerShoupPublicKeyParameters.getH().equals(this.f9561h) && super.equals(obj);
    }

    public BigInteger getC() {
        return this.f9559c;
    }

    public BigInteger getD() {
        return this.f9560d;
    }

    public BigInteger getH() {
        return this.f9561h;
    }

    @Override // org.bouncycastle.crypto.params.CramerShoupKeyParameters
    public int hashCode() {
        return ((this.f9559c.hashCode() ^ this.f9560d.hashCode()) ^ this.f9561h.hashCode()) ^ super.hashCode();
    }
}
