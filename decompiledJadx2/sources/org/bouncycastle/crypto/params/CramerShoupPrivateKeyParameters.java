package org.bouncycastle.crypto.params;

import java.math.BigInteger;

/* loaded from: classes9.dex */
public class CramerShoupPrivateKeyParameters extends CramerShoupKeyParameters {

    /* renamed from: pk */
    private CramerShoupPublicKeyParameters f9553pk;

    /* renamed from: x1 */
    private BigInteger f9554x1;

    /* renamed from: x2 */
    private BigInteger f9555x2;

    /* renamed from: y1 */
    private BigInteger f9556y1;

    /* renamed from: y2 */
    private BigInteger f9557y2;

    /* renamed from: z */
    private BigInteger f9558z;

    public CramerShoupPrivateKeyParameters(CramerShoupParameters cramerShoupParameters, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5) {
        super(true, cramerShoupParameters);
        this.f9554x1 = bigInteger;
        this.f9555x2 = bigInteger2;
        this.f9556y1 = bigInteger3;
        this.f9557y2 = bigInteger4;
        this.f9558z = bigInteger5;
    }

    @Override // org.bouncycastle.crypto.params.CramerShoupKeyParameters
    public boolean equals(Object obj) {
        if (!(obj instanceof CramerShoupPrivateKeyParameters)) {
            return false;
        }
        CramerShoupPrivateKeyParameters cramerShoupPrivateKeyParameters = (CramerShoupPrivateKeyParameters) obj;
        return cramerShoupPrivateKeyParameters.getX1().equals(this.f9554x1) && cramerShoupPrivateKeyParameters.getX2().equals(this.f9555x2) && cramerShoupPrivateKeyParameters.getY1().equals(this.f9556y1) && cramerShoupPrivateKeyParameters.getY2().equals(this.f9557y2) && cramerShoupPrivateKeyParameters.getZ().equals(this.f9558z) && super.equals(obj);
    }

    public CramerShoupPublicKeyParameters getPk() {
        return this.f9553pk;
    }

    public BigInteger getX1() {
        return this.f9554x1;
    }

    public BigInteger getX2() {
        return this.f9555x2;
    }

    public BigInteger getY1() {
        return this.f9556y1;
    }

    public BigInteger getY2() {
        return this.f9557y2;
    }

    public BigInteger getZ() {
        return this.f9558z;
    }

    @Override // org.bouncycastle.crypto.params.CramerShoupKeyParameters
    public int hashCode() {
        return ((((this.f9554x1.hashCode() ^ this.f9555x2.hashCode()) ^ this.f9556y1.hashCode()) ^ this.f9557y2.hashCode()) ^ this.f9558z.hashCode()) ^ super.hashCode();
    }

    public void setPk(CramerShoupPublicKeyParameters cramerShoupPublicKeyParameters) {
        this.f9553pk = cramerShoupPublicKeyParameters;
    }
}
