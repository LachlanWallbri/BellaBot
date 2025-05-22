package org.bouncycastle.math.ec.custom.sec;

import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat192;

/* loaded from: classes9.dex */
public class SecP192K1Point extends ECPoint.AbstractFp {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SecP192K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SecP192K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
    }

    @Override // org.bouncycastle.math.ec.ECPoint
    public ECPoint add(ECPoint eCPoint) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        if (isInfinity()) {
            return eCPoint;
        }
        if (eCPoint.isInfinity()) {
            return this;
        }
        if (this == eCPoint) {
            return twice();
        }
        ECCurve curve = getCurve();
        SecP192K1FieldElement secP192K1FieldElement = (SecP192K1FieldElement) this.f9730x;
        SecP192K1FieldElement secP192K1FieldElement2 = (SecP192K1FieldElement) this.f9731y;
        SecP192K1FieldElement secP192K1FieldElement3 = (SecP192K1FieldElement) eCPoint.getXCoord();
        SecP192K1FieldElement secP192K1FieldElement4 = (SecP192K1FieldElement) eCPoint.getYCoord();
        SecP192K1FieldElement secP192K1FieldElement5 = (SecP192K1FieldElement) this.f9732zs[0];
        SecP192K1FieldElement secP192K1FieldElement6 = (SecP192K1FieldElement) eCPoint.getZCoord(0);
        int[] createExt = Nat192.createExt();
        int[] create = Nat192.create();
        int[] create2 = Nat192.create();
        int[] create3 = Nat192.create();
        boolean isOne = secP192K1FieldElement5.isOne();
        if (isOne) {
            iArr = secP192K1FieldElement3.f9767x;
            iArr2 = secP192K1FieldElement4.f9767x;
        } else {
            SecP192K1Field.square(secP192K1FieldElement5.f9767x, create2);
            SecP192K1Field.multiply(create2, secP192K1FieldElement3.f9767x, create);
            SecP192K1Field.multiply(create2, secP192K1FieldElement5.f9767x, create2);
            SecP192K1Field.multiply(create2, secP192K1FieldElement4.f9767x, create2);
            iArr = create;
            iArr2 = create2;
        }
        boolean isOne2 = secP192K1FieldElement6.isOne();
        if (isOne2) {
            iArr3 = secP192K1FieldElement.f9767x;
            iArr4 = secP192K1FieldElement2.f9767x;
        } else {
            SecP192K1Field.square(secP192K1FieldElement6.f9767x, create3);
            SecP192K1Field.multiply(create3, secP192K1FieldElement.f9767x, createExt);
            SecP192K1Field.multiply(create3, secP192K1FieldElement6.f9767x, create3);
            SecP192K1Field.multiply(create3, secP192K1FieldElement2.f9767x, create3);
            iArr3 = createExt;
            iArr4 = create3;
        }
        int[] create4 = Nat192.create();
        SecP192K1Field.subtract(iArr3, iArr, create4);
        SecP192K1Field.subtract(iArr4, iArr2, create);
        if (Nat192.isZero(create4)) {
            return Nat192.isZero(create) ? twice() : curve.getInfinity();
        }
        SecP192K1Field.square(create4, create2);
        int[] create5 = Nat192.create();
        SecP192K1Field.multiply(create2, create4, create5);
        SecP192K1Field.multiply(create2, iArr3, create2);
        SecP192K1Field.negate(create5, create5);
        Nat192.mul(iArr4, create5, createExt);
        SecP192K1Field.reduce32(Nat192.addBothTo(create2, create2, create5), create5);
        SecP192K1FieldElement secP192K1FieldElement7 = new SecP192K1FieldElement(create3);
        SecP192K1Field.square(create, secP192K1FieldElement7.f9767x);
        SecP192K1Field.subtract(secP192K1FieldElement7.f9767x, create5, secP192K1FieldElement7.f9767x);
        SecP192K1FieldElement secP192K1FieldElement8 = new SecP192K1FieldElement(create5);
        SecP192K1Field.subtract(create2, secP192K1FieldElement7.f9767x, secP192K1FieldElement8.f9767x);
        SecP192K1Field.multiplyAddToExt(secP192K1FieldElement8.f9767x, create, createExt);
        SecP192K1Field.reduce(createExt, secP192K1FieldElement8.f9767x);
        SecP192K1FieldElement secP192K1FieldElement9 = new SecP192K1FieldElement(create4);
        if (!isOne) {
            SecP192K1Field.multiply(secP192K1FieldElement9.f9767x, secP192K1FieldElement5.f9767x, secP192K1FieldElement9.f9767x);
        }
        if (!isOne2) {
            SecP192K1Field.multiply(secP192K1FieldElement9.f9767x, secP192K1FieldElement6.f9767x, secP192K1FieldElement9.f9767x);
        }
        return new SecP192K1Point(curve, secP192K1FieldElement7, secP192K1FieldElement8, new ECFieldElement[]{secP192K1FieldElement9});
    }

    @Override // org.bouncycastle.math.ec.ECPoint
    protected ECPoint detach() {
        return new SecP192K1Point(null, getAffineXCoord(), getAffineYCoord());
    }

    @Override // org.bouncycastle.math.ec.ECPoint
    public ECPoint negate() {
        return isInfinity() ? this : new SecP192K1Point(this.curve, this.f9730x, this.f9731y.negate(), this.f9732zs);
    }

    @Override // org.bouncycastle.math.ec.ECPoint
    public ECPoint threeTimes() {
        return (isInfinity() || this.f9731y.isZero()) ? this : twice().add(this);
    }

    @Override // org.bouncycastle.math.ec.ECPoint
    public ECPoint twice() {
        if (isInfinity()) {
            return this;
        }
        ECCurve curve = getCurve();
        SecP192K1FieldElement secP192K1FieldElement = (SecP192K1FieldElement) this.f9731y;
        if (secP192K1FieldElement.isZero()) {
            return curve.getInfinity();
        }
        SecP192K1FieldElement secP192K1FieldElement2 = (SecP192K1FieldElement) this.f9730x;
        SecP192K1FieldElement secP192K1FieldElement3 = (SecP192K1FieldElement) this.f9732zs[0];
        int[] create = Nat192.create();
        SecP192K1Field.square(secP192K1FieldElement.f9767x, create);
        int[] create2 = Nat192.create();
        SecP192K1Field.square(create, create2);
        int[] create3 = Nat192.create();
        SecP192K1Field.square(secP192K1FieldElement2.f9767x, create3);
        SecP192K1Field.reduce32(Nat192.addBothTo(create3, create3, create3), create3);
        SecP192K1Field.multiply(create, secP192K1FieldElement2.f9767x, create);
        SecP192K1Field.reduce32(Nat.shiftUpBits(6, create, 2, 0), create);
        int[] create4 = Nat192.create();
        SecP192K1Field.reduce32(Nat.shiftUpBits(6, create2, 3, 0, create4), create4);
        SecP192K1FieldElement secP192K1FieldElement4 = new SecP192K1FieldElement(create2);
        SecP192K1Field.square(create3, secP192K1FieldElement4.f9767x);
        SecP192K1Field.subtract(secP192K1FieldElement4.f9767x, create, secP192K1FieldElement4.f9767x);
        SecP192K1Field.subtract(secP192K1FieldElement4.f9767x, create, secP192K1FieldElement4.f9767x);
        SecP192K1FieldElement secP192K1FieldElement5 = new SecP192K1FieldElement(create);
        SecP192K1Field.subtract(create, secP192K1FieldElement4.f9767x, secP192K1FieldElement5.f9767x);
        SecP192K1Field.multiply(secP192K1FieldElement5.f9767x, create3, secP192K1FieldElement5.f9767x);
        SecP192K1Field.subtract(secP192K1FieldElement5.f9767x, create4, secP192K1FieldElement5.f9767x);
        SecP192K1FieldElement secP192K1FieldElement6 = new SecP192K1FieldElement(create3);
        SecP192K1Field.twice(secP192K1FieldElement.f9767x, secP192K1FieldElement6.f9767x);
        if (!secP192K1FieldElement3.isOne()) {
            SecP192K1Field.multiply(secP192K1FieldElement6.f9767x, secP192K1FieldElement3.f9767x, secP192K1FieldElement6.f9767x);
        }
        return new SecP192K1Point(curve, secP192K1FieldElement4, secP192K1FieldElement5, new ECFieldElement[]{secP192K1FieldElement6});
    }

    @Override // org.bouncycastle.math.ec.ECPoint
    public ECPoint twicePlus(ECPoint eCPoint) {
        return this == eCPoint ? threeTimes() : isInfinity() ? eCPoint : eCPoint.isInfinity() ? twice() : this.f9731y.isZero() ? eCPoint : twice().add(eCPoint);
    }
}
