package org.bouncycastle.math.ec.custom.djb;

import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.raw.Nat256;

/* loaded from: classes9.dex */
public class Curve25519Point extends ECPoint.AbstractFp {
    /* JADX INFO: Access modifiers changed from: package-private */
    public Curve25519Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Curve25519Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
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
        Curve25519FieldElement curve25519FieldElement = (Curve25519FieldElement) this.f9730x;
        Curve25519FieldElement curve25519FieldElement2 = (Curve25519FieldElement) this.f9731y;
        Curve25519FieldElement curve25519FieldElement3 = (Curve25519FieldElement) this.f9732zs[0];
        Curve25519FieldElement curve25519FieldElement4 = (Curve25519FieldElement) eCPoint.getXCoord();
        Curve25519FieldElement curve25519FieldElement5 = (Curve25519FieldElement) eCPoint.getYCoord();
        Curve25519FieldElement curve25519FieldElement6 = (Curve25519FieldElement) eCPoint.getZCoord(0);
        int[] createExt = Nat256.createExt();
        int[] create = Nat256.create();
        int[] create2 = Nat256.create();
        int[] create3 = Nat256.create();
        boolean isOne = curve25519FieldElement3.isOne();
        if (isOne) {
            iArr = curve25519FieldElement4.f9740x;
            iArr2 = curve25519FieldElement5.f9740x;
        } else {
            Curve25519Field.square(curve25519FieldElement3.f9740x, create2);
            Curve25519Field.multiply(create2, curve25519FieldElement4.f9740x, create);
            Curve25519Field.multiply(create2, curve25519FieldElement3.f9740x, create2);
            Curve25519Field.multiply(create2, curve25519FieldElement5.f9740x, create2);
            iArr = create;
            iArr2 = create2;
        }
        boolean isOne2 = curve25519FieldElement6.isOne();
        if (isOne2) {
            iArr3 = curve25519FieldElement.f9740x;
            iArr4 = curve25519FieldElement2.f9740x;
        } else {
            Curve25519Field.square(curve25519FieldElement6.f9740x, create3);
            Curve25519Field.multiply(create3, curve25519FieldElement.f9740x, createExt);
            Curve25519Field.multiply(create3, curve25519FieldElement6.f9740x, create3);
            Curve25519Field.multiply(create3, curve25519FieldElement2.f9740x, create3);
            iArr3 = createExt;
            iArr4 = create3;
        }
        int[] create4 = Nat256.create();
        Curve25519Field.subtract(iArr3, iArr, create4);
        Curve25519Field.subtract(iArr4, iArr2, create);
        if (Nat256.isZero(create4)) {
            return Nat256.isZero(create) ? twice() : curve.getInfinity();
        }
        int[] create5 = Nat256.create();
        Curve25519Field.square(create4, create5);
        int[] create6 = Nat256.create();
        Curve25519Field.multiply(create5, create4, create6);
        Curve25519Field.multiply(create5, iArr3, create2);
        Curve25519Field.negate(create6, create6);
        Nat256.mul(iArr4, create6, createExt);
        Curve25519Field.reduce27(Nat256.addBothTo(create2, create2, create6), create6);
        Curve25519FieldElement curve25519FieldElement7 = new Curve25519FieldElement(create3);
        Curve25519Field.square(create, curve25519FieldElement7.f9740x);
        Curve25519Field.subtract(curve25519FieldElement7.f9740x, create6, curve25519FieldElement7.f9740x);
        Curve25519FieldElement curve25519FieldElement8 = new Curve25519FieldElement(create6);
        Curve25519Field.subtract(create2, curve25519FieldElement7.f9740x, curve25519FieldElement8.f9740x);
        Curve25519Field.multiplyAddToExt(curve25519FieldElement8.f9740x, create, createExt);
        Curve25519Field.reduce(createExt, curve25519FieldElement8.f9740x);
        Curve25519FieldElement curve25519FieldElement9 = new Curve25519FieldElement(create4);
        if (!isOne) {
            Curve25519Field.multiply(curve25519FieldElement9.f9740x, curve25519FieldElement3.f9740x, curve25519FieldElement9.f9740x);
        }
        if (!isOne2) {
            Curve25519Field.multiply(curve25519FieldElement9.f9740x, curve25519FieldElement6.f9740x, curve25519FieldElement9.f9740x);
        }
        if (!isOne || !isOne2) {
            create5 = null;
        }
        return new Curve25519Point(curve, curve25519FieldElement7, curve25519FieldElement8, new ECFieldElement[]{curve25519FieldElement9, calculateJacobianModifiedW(curve25519FieldElement9, create5)});
    }

    protected Curve25519FieldElement calculateJacobianModifiedW(Curve25519FieldElement curve25519FieldElement, int[] iArr) {
        Curve25519FieldElement curve25519FieldElement2 = (Curve25519FieldElement) getCurve().getA();
        if (curve25519FieldElement.isOne()) {
            return curve25519FieldElement2;
        }
        Curve25519FieldElement curve25519FieldElement3 = new Curve25519FieldElement();
        if (iArr == null) {
            iArr = curve25519FieldElement3.f9740x;
            Curve25519Field.square(curve25519FieldElement.f9740x, iArr);
        }
        Curve25519Field.square(iArr, curve25519FieldElement3.f9740x);
        Curve25519Field.multiply(curve25519FieldElement3.f9740x, curve25519FieldElement2.f9740x, curve25519FieldElement3.f9740x);
        return curve25519FieldElement3;
    }

    @Override // org.bouncycastle.math.ec.ECPoint
    protected ECPoint detach() {
        return new Curve25519Point(null, getAffineXCoord(), getAffineYCoord());
    }

    protected Curve25519FieldElement getJacobianModifiedW() {
        Curve25519FieldElement curve25519FieldElement = (Curve25519FieldElement) this.f9732zs[1];
        if (curve25519FieldElement != null) {
            return curve25519FieldElement;
        }
        ECFieldElement[] eCFieldElementArr = this.f9732zs;
        Curve25519FieldElement calculateJacobianModifiedW = calculateJacobianModifiedW((Curve25519FieldElement) this.f9732zs[0], null);
        eCFieldElementArr[1] = calculateJacobianModifiedW;
        return calculateJacobianModifiedW;
    }

    @Override // org.bouncycastle.math.ec.ECPoint
    public ECFieldElement getZCoord(int i) {
        return i == 1 ? getJacobianModifiedW() : super.getZCoord(i);
    }

    @Override // org.bouncycastle.math.ec.ECPoint
    public ECPoint negate() {
        return isInfinity() ? this : new Curve25519Point(getCurve(), this.f9730x, this.f9731y.negate(), this.f9732zs);
    }

    @Override // org.bouncycastle.math.ec.ECPoint
    public ECPoint threeTimes() {
        return (isInfinity() || this.f9731y.isZero()) ? this : twiceJacobianModified(false).add(this);
    }

    @Override // org.bouncycastle.math.ec.ECPoint
    public ECPoint twice() {
        if (isInfinity()) {
            return this;
        }
        return this.f9731y.isZero() ? getCurve().getInfinity() : twiceJacobianModified(true);
    }

    protected Curve25519Point twiceJacobianModified(boolean z) {
        Curve25519FieldElement curve25519FieldElement = (Curve25519FieldElement) this.f9730x;
        Curve25519FieldElement curve25519FieldElement2 = (Curve25519FieldElement) this.f9731y;
        Curve25519FieldElement curve25519FieldElement3 = (Curve25519FieldElement) this.f9732zs[0];
        Curve25519FieldElement jacobianModifiedW = getJacobianModifiedW();
        int[] create = Nat256.create();
        Curve25519Field.square(curve25519FieldElement.f9740x, create);
        Curve25519Field.reduce27(Nat256.addBothTo(create, create, create) + Nat256.addTo(jacobianModifiedW.f9740x, create), create);
        int[] create2 = Nat256.create();
        Curve25519Field.twice(curve25519FieldElement2.f9740x, create2);
        int[] create3 = Nat256.create();
        Curve25519Field.multiply(create2, curve25519FieldElement2.f9740x, create3);
        int[] create4 = Nat256.create();
        Curve25519Field.multiply(create3, curve25519FieldElement.f9740x, create4);
        Curve25519Field.twice(create4, create4);
        int[] create5 = Nat256.create();
        Curve25519Field.square(create3, create5);
        Curve25519Field.twice(create5, create5);
        Curve25519FieldElement curve25519FieldElement4 = new Curve25519FieldElement(create3);
        Curve25519Field.square(create, curve25519FieldElement4.f9740x);
        Curve25519Field.subtract(curve25519FieldElement4.f9740x, create4, curve25519FieldElement4.f9740x);
        Curve25519Field.subtract(curve25519FieldElement4.f9740x, create4, curve25519FieldElement4.f9740x);
        Curve25519FieldElement curve25519FieldElement5 = new Curve25519FieldElement(create4);
        Curve25519Field.subtract(create4, curve25519FieldElement4.f9740x, curve25519FieldElement5.f9740x);
        Curve25519Field.multiply(curve25519FieldElement5.f9740x, create, curve25519FieldElement5.f9740x);
        Curve25519Field.subtract(curve25519FieldElement5.f9740x, create5, curve25519FieldElement5.f9740x);
        Curve25519FieldElement curve25519FieldElement6 = new Curve25519FieldElement(create2);
        if (!Nat256.isOne(curve25519FieldElement3.f9740x)) {
            Curve25519Field.multiply(curve25519FieldElement6.f9740x, curve25519FieldElement3.f9740x, curve25519FieldElement6.f9740x);
        }
        Curve25519FieldElement curve25519FieldElement7 = null;
        if (z) {
            curve25519FieldElement7 = new Curve25519FieldElement(create5);
            Curve25519Field.multiply(curve25519FieldElement7.f9740x, jacobianModifiedW.f9740x, curve25519FieldElement7.f9740x);
            Curve25519Field.twice(curve25519FieldElement7.f9740x, curve25519FieldElement7.f9740x);
        }
        return new Curve25519Point(getCurve(), curve25519FieldElement4, curve25519FieldElement5, new ECFieldElement[]{curve25519FieldElement6, curve25519FieldElement7});
    }

    @Override // org.bouncycastle.math.ec.ECPoint
    public ECPoint twicePlus(ECPoint eCPoint) {
        return this == eCPoint ? threeTimes() : isInfinity() ? eCPoint : eCPoint.isInfinity() ? twice() : this.f9731y.isZero() ? eCPoint : twiceJacobianModified(false).add(eCPoint);
    }
}
