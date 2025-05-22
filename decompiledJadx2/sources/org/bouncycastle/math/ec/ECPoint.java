package org.bouncycastle.math.ec;

import java.math.BigInteger;
import java.util.Hashtable;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;

/* loaded from: classes9.dex */
public abstract class ECPoint {
    protected static final ECFieldElement[] EMPTY_ZS = new ECFieldElement[0];
    protected ECCurve curve;
    protected Hashtable preCompTable;

    /* renamed from: x */
    protected ECFieldElement f9730x;

    /* renamed from: y */
    protected ECFieldElement f9731y;

    /* renamed from: zs */
    protected ECFieldElement[] f9732zs;

    /* loaded from: classes9.dex */
    public static abstract class AbstractF2m extends ECPoint {
        /* JADX INFO: Access modifiers changed from: protected */
        public AbstractF2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public AbstractF2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        protected boolean satisfiesCurveEquation() {
            ECFieldElement multiplyPlusProduct;
            ECFieldElement squarePlusProduct;
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement = this.f9730x;
            ECFieldElement a = curve.getA();
            ECFieldElement b = curve.getB();
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem != 6) {
                ECFieldElement eCFieldElement2 = this.f9731y;
                ECFieldElement multiply = eCFieldElement2.add(eCFieldElement).multiply(eCFieldElement2);
                if (coordinateSystem != 0) {
                    if (coordinateSystem != 1) {
                        throw new IllegalStateException("unsupported coordinate system");
                    }
                    ECFieldElement eCFieldElement3 = this.f9732zs[0];
                    if (!eCFieldElement3.isOne()) {
                        ECFieldElement multiply2 = eCFieldElement3.multiply(eCFieldElement3.square());
                        multiply = multiply.multiply(eCFieldElement3);
                        a = a.multiply(eCFieldElement3);
                        b = b.multiply(multiply2);
                    }
                }
                return multiply.equals(eCFieldElement.add(a).multiply(eCFieldElement.square()).add(b));
            }
            ECFieldElement eCFieldElement4 = this.f9732zs[0];
            boolean isOne = eCFieldElement4.isOne();
            if (eCFieldElement.isZero()) {
                ECFieldElement square = this.f9731y.square();
                if (!isOne) {
                    b = b.multiply(eCFieldElement4.square());
                }
                return square.equals(b);
            }
            ECFieldElement eCFieldElement5 = this.f9731y;
            ECFieldElement square2 = eCFieldElement.square();
            if (isOne) {
                multiplyPlusProduct = eCFieldElement5.square().add(eCFieldElement5).add(a);
                squarePlusProduct = square2.square().add(b);
            } else {
                ECFieldElement square3 = eCFieldElement4.square();
                ECFieldElement square4 = square3.square();
                multiplyPlusProduct = eCFieldElement5.add(eCFieldElement4).multiplyPlusProduct(eCFieldElement5, a, square3);
                squarePlusProduct = square2.squarePlusProduct(b, square4);
            }
            return multiplyPlusProduct.multiply(square2).equals(squarePlusProduct);
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        protected boolean satisfiesOrder() {
            BigInteger cofactor = this.curve.getCofactor();
            if (ECConstants.TWO.equals(cofactor)) {
                return ((ECFieldElement.AbstractF2m) normalize().getAffineXCoord()).trace() != 0;
            }
            if (!ECConstants.FOUR.equals(cofactor)) {
                return super.satisfiesOrder();
            }
            ECPoint normalize = normalize();
            ECFieldElement affineXCoord = normalize.getAffineXCoord();
            ECFieldElement solveQuadraticEquation = ((ECCurve.AbstractF2m) this.curve).solveQuadraticEquation(affineXCoord.add(this.curve.getA()));
            if (solveQuadraticEquation == null) {
                return false;
            }
            return ((ECFieldElement.AbstractF2m) affineXCoord.multiply(solveQuadraticEquation).add(normalize.getAffineYCoord())).trace() == 0;
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint scaleX(ECFieldElement eCFieldElement) {
            if (isInfinity()) {
                return this;
            }
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem == 5) {
                ECFieldElement rawXCoord = getRawXCoord();
                return getCurve().createRawPoint(rawXCoord, getRawYCoord().add(rawXCoord).divide(eCFieldElement).add(rawXCoord.multiply(eCFieldElement)), getRawZCoords());
            }
            if (curveCoordinateSystem != 6) {
                return super.scaleX(eCFieldElement);
            }
            ECFieldElement rawXCoord2 = getRawXCoord();
            ECFieldElement rawYCoord = getRawYCoord();
            ECFieldElement eCFieldElement2 = getRawZCoords()[0];
            ECFieldElement multiply = rawXCoord2.multiply(eCFieldElement.square());
            return getCurve().createRawPoint(multiply, rawYCoord.add(rawXCoord2).add(multiply), new ECFieldElement[]{eCFieldElement2.multiply(eCFieldElement)});
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint scaleXNegateY(ECFieldElement eCFieldElement) {
            return scaleX(eCFieldElement);
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint scaleY(ECFieldElement eCFieldElement) {
            if (isInfinity()) {
                return this;
            }
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem != 5 && curveCoordinateSystem != 6) {
                return super.scaleY(eCFieldElement);
            }
            ECFieldElement rawXCoord = getRawXCoord();
            return getCurve().createRawPoint(rawXCoord, getRawYCoord().add(rawXCoord).multiply(eCFieldElement).add(rawXCoord), getRawZCoords());
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint scaleYNegateX(ECFieldElement eCFieldElement) {
            return scaleY(eCFieldElement);
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint subtract(ECPoint eCPoint) {
            return eCPoint.isInfinity() ? this : add(eCPoint.negate());
        }

        public AbstractF2m tau() {
            ECPoint createRawPoint;
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement = this.f9730x;
            if (coordinateSystem != 0) {
                if (coordinateSystem != 1) {
                    if (coordinateSystem != 5) {
                        if (coordinateSystem != 6) {
                            throw new IllegalStateException("unsupported coordinate system");
                        }
                    }
                }
                createRawPoint = curve.createRawPoint(eCFieldElement.square(), this.f9731y.square(), new ECFieldElement[]{this.f9732zs[0].square()});
                return (AbstractF2m) createRawPoint;
            }
            createRawPoint = curve.createRawPoint(eCFieldElement.square(), this.f9731y.square());
            return (AbstractF2m) createRawPoint;
        }

        public AbstractF2m tauPow(int i) {
            ECPoint createRawPoint;
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement = this.f9730x;
            if (coordinateSystem != 0) {
                if (coordinateSystem != 1) {
                    if (coordinateSystem != 5) {
                        if (coordinateSystem != 6) {
                            throw new IllegalStateException("unsupported coordinate system");
                        }
                    }
                }
                createRawPoint = curve.createRawPoint(eCFieldElement.squarePow(i), this.f9731y.squarePow(i), new ECFieldElement[]{this.f9732zs[0].squarePow(i)});
                return (AbstractF2m) createRawPoint;
            }
            createRawPoint = curve.createRawPoint(eCFieldElement.squarePow(i), this.f9731y.squarePow(i));
            return (AbstractF2m) createRawPoint;
        }
    }

    /* loaded from: classes9.dex */
    public static abstract class AbstractFp extends ECPoint {
        /* JADX INFO: Access modifiers changed from: protected */
        public AbstractFp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public AbstractFp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        protected boolean getCompressionYTilde() {
            return getAffineYCoord().testBitZero();
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        protected boolean satisfiesCurveEquation() {
            ECFieldElement eCFieldElement = this.f9730x;
            ECFieldElement eCFieldElement2 = this.f9731y;
            ECFieldElement a = this.curve.getA();
            ECFieldElement b = this.curve.getB();
            ECFieldElement square = eCFieldElement2.square();
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem != 0) {
                if (curveCoordinateSystem == 1) {
                    ECFieldElement eCFieldElement3 = this.f9732zs[0];
                    if (!eCFieldElement3.isOne()) {
                        ECFieldElement square2 = eCFieldElement3.square();
                        ECFieldElement multiply = eCFieldElement3.multiply(square2);
                        square = square.multiply(eCFieldElement3);
                        a = a.multiply(square2);
                        b = b.multiply(multiply);
                    }
                } else {
                    if (curveCoordinateSystem != 2 && curveCoordinateSystem != 3 && curveCoordinateSystem != 4) {
                        throw new IllegalStateException("unsupported coordinate system");
                    }
                    ECFieldElement eCFieldElement4 = this.f9732zs[0];
                    if (!eCFieldElement4.isOne()) {
                        ECFieldElement square3 = eCFieldElement4.square();
                        ECFieldElement square4 = square3.square();
                        ECFieldElement multiply2 = square3.multiply(square4);
                        a = a.multiply(square4);
                        b = b.multiply(multiply2);
                    }
                }
            }
            return square.equals(eCFieldElement.square().add(a).multiply(eCFieldElement).add(b));
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint subtract(ECPoint eCPoint) {
            return eCPoint.isInfinity() ? this : add(eCPoint.negate());
        }
    }

    /* loaded from: classes9.dex */
    public static class F2m extends AbstractF2m {
        /* JADX INFO: Access modifiers changed from: package-private */
        public F2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public F2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint add(ECPoint eCPoint) {
            ECFieldElement eCFieldElement;
            ECFieldElement eCFieldElement2;
            ECFieldElement eCFieldElement3;
            ECFieldElement multiply;
            ECFieldElement multiply2;
            ECFieldElement squarePlusProduct;
            if (isInfinity()) {
                return eCPoint;
            }
            if (eCPoint.isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement4 = this.f9730x;
            ECFieldElement eCFieldElement5 = eCPoint.f9730x;
            if (coordinateSystem == 0) {
                ECFieldElement eCFieldElement6 = this.f9731y;
                ECFieldElement eCFieldElement7 = eCPoint.f9731y;
                ECFieldElement add = eCFieldElement4.add(eCFieldElement5);
                ECFieldElement add2 = eCFieldElement6.add(eCFieldElement7);
                if (add.isZero()) {
                    return add2.isZero() ? twice() : curve.getInfinity();
                }
                ECFieldElement divide = add2.divide(add);
                ECFieldElement add3 = divide.square().add(divide).add(add).add(curve.getA());
                return new F2m(curve, add3, divide.multiply(eCFieldElement4.add(add3)).add(add3).add(eCFieldElement6));
            }
            if (coordinateSystem == 1) {
                ECFieldElement eCFieldElement8 = this.f9731y;
                ECFieldElement eCFieldElement9 = this.f9732zs[0];
                ECFieldElement eCFieldElement10 = eCPoint.f9731y;
                ECFieldElement eCFieldElement11 = eCPoint.f9732zs[0];
                boolean isOne = eCFieldElement11.isOne();
                ECFieldElement add4 = eCFieldElement9.multiply(eCFieldElement10).add(isOne ? eCFieldElement8 : eCFieldElement8.multiply(eCFieldElement11));
                ECFieldElement add5 = eCFieldElement9.multiply(eCFieldElement5).add(isOne ? eCFieldElement4 : eCFieldElement4.multiply(eCFieldElement11));
                if (add5.isZero()) {
                    return add4.isZero() ? twice() : curve.getInfinity();
                }
                ECFieldElement square = add5.square();
                ECFieldElement multiply3 = square.multiply(add5);
                if (!isOne) {
                    eCFieldElement9 = eCFieldElement9.multiply(eCFieldElement11);
                }
                ECFieldElement add6 = add4.add(add5);
                ECFieldElement add7 = add6.multiplyPlusProduct(add4, square, curve.getA()).multiply(eCFieldElement9).add(multiply3);
                ECFieldElement multiply4 = add5.multiply(add7);
                if (!isOne) {
                    square = square.multiply(eCFieldElement11);
                }
                return new F2m(curve, multiply4, add4.multiplyPlusProduct(eCFieldElement4, add5, eCFieldElement8).multiplyPlusProduct(square, add6, add7), new ECFieldElement[]{multiply3.multiply(eCFieldElement9)});
            }
            if (coordinateSystem != 6) {
                throw new IllegalStateException("unsupported coordinate system");
            }
            if (eCFieldElement4.isZero()) {
                return eCFieldElement5.isZero() ? curve.getInfinity() : eCPoint.add(this);
            }
            ECFieldElement eCFieldElement12 = this.f9731y;
            ECFieldElement eCFieldElement13 = this.f9732zs[0];
            ECFieldElement eCFieldElement14 = eCPoint.f9731y;
            ECFieldElement eCFieldElement15 = eCPoint.f9732zs[0];
            boolean isOne2 = eCFieldElement13.isOne();
            if (isOne2) {
                eCFieldElement = eCFieldElement5;
                eCFieldElement2 = eCFieldElement14;
            } else {
                eCFieldElement = eCFieldElement5.multiply(eCFieldElement13);
                eCFieldElement2 = eCFieldElement14.multiply(eCFieldElement13);
            }
            boolean isOne3 = eCFieldElement15.isOne();
            if (isOne3) {
                eCFieldElement3 = eCFieldElement12;
            } else {
                eCFieldElement4 = eCFieldElement4.multiply(eCFieldElement15);
                eCFieldElement3 = eCFieldElement12.multiply(eCFieldElement15);
            }
            ECFieldElement add8 = eCFieldElement3.add(eCFieldElement2);
            ECFieldElement add9 = eCFieldElement4.add(eCFieldElement);
            if (add9.isZero()) {
                return add8.isZero() ? twice() : curve.getInfinity();
            }
            if (eCFieldElement5.isZero()) {
                ECPoint normalize = normalize();
                ECFieldElement xCoord = normalize.getXCoord();
                ECFieldElement yCoord = normalize.getYCoord();
                ECFieldElement divide2 = yCoord.add(eCFieldElement14).divide(xCoord);
                ECFieldElement add10 = divide2.square().add(divide2).add(xCoord).add(curve.getA());
                if (add10.isZero()) {
                    return new F2m(curve, add10, curve.getB().sqrt());
                }
                ECFieldElement add11 = divide2.multiply(xCoord.add(add10)).add(add10).add(yCoord).divide(add10).add(add10);
                multiply = add10;
                squarePlusProduct = add11;
                multiply2 = curve.fromBigInteger(ECConstants.ONE);
            } else {
                ECFieldElement square2 = add9.square();
                ECFieldElement multiply5 = add8.multiply(eCFieldElement4);
                ECFieldElement multiply6 = add8.multiply(eCFieldElement);
                multiply = multiply5.multiply(multiply6);
                if (multiply.isZero()) {
                    return new F2m(curve, multiply, curve.getB().sqrt());
                }
                ECFieldElement multiply7 = add8.multiply(square2);
                multiply2 = !isOne3 ? multiply7.multiply(eCFieldElement15) : multiply7;
                squarePlusProduct = multiply6.add(square2).squarePlusProduct(multiply2, eCFieldElement12.add(eCFieldElement13));
                if (!isOne2) {
                    multiply2 = multiply2.multiply(eCFieldElement13);
                }
            }
            return new F2m(curve, multiply, squarePlusProduct, new ECFieldElement[]{multiply2});
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        protected ECPoint detach() {
            return new F2m(null, getAffineXCoord(), getAffineYCoord());
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        protected boolean getCompressionYTilde() {
            ECFieldElement rawXCoord = getRawXCoord();
            if (rawXCoord.isZero()) {
                return false;
            }
            ECFieldElement rawYCoord = getRawYCoord();
            int curveCoordinateSystem = getCurveCoordinateSystem();
            return (curveCoordinateSystem == 5 || curveCoordinateSystem == 6) ? rawYCoord.testBitZero() != rawXCoord.testBitZero() : rawYCoord.divide(rawXCoord).testBitZero();
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECFieldElement getYCoord() {
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem != 5 && curveCoordinateSystem != 6) {
                return this.f9731y;
            }
            ECFieldElement eCFieldElement = this.f9730x;
            ECFieldElement eCFieldElement2 = this.f9731y;
            if (isInfinity() || eCFieldElement.isZero()) {
                return eCFieldElement2;
            }
            ECFieldElement multiply = eCFieldElement2.add(eCFieldElement).multiply(eCFieldElement);
            if (6 != curveCoordinateSystem) {
                return multiply;
            }
            ECFieldElement eCFieldElement3 = this.f9732zs[0];
            return !eCFieldElement3.isOne() ? multiply.divide(eCFieldElement3) : multiply;
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint negate() {
            if (isInfinity()) {
                return this;
            }
            ECFieldElement eCFieldElement = this.f9730x;
            if (eCFieldElement.isZero()) {
                return this;
            }
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem == 0) {
                return new F2m(this.curve, eCFieldElement, this.f9731y.add(eCFieldElement));
            }
            if (curveCoordinateSystem == 1) {
                return new F2m(this.curve, eCFieldElement, this.f9731y.add(eCFieldElement), new ECFieldElement[]{this.f9732zs[0]});
            }
            if (curveCoordinateSystem == 5) {
                return new F2m(this.curve, eCFieldElement, this.f9731y.addOne());
            }
            if (curveCoordinateSystem != 6) {
                throw new IllegalStateException("unsupported coordinate system");
            }
            ECFieldElement eCFieldElement2 = this.f9731y;
            ECFieldElement eCFieldElement3 = this.f9732zs[0];
            return new F2m(this.curve, eCFieldElement, eCFieldElement2.add(eCFieldElement3), new ECFieldElement[]{eCFieldElement3});
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint twice() {
            ECFieldElement add;
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement = this.f9730x;
            if (eCFieldElement.isZero()) {
                return curve.getInfinity();
            }
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem == 0) {
                ECFieldElement add2 = this.f9731y.divide(eCFieldElement).add(eCFieldElement);
                ECFieldElement add3 = add2.square().add(add2).add(curve.getA());
                return new F2m(curve, add3, eCFieldElement.squarePlusProduct(add3, add2.addOne()));
            }
            if (coordinateSystem == 1) {
                ECFieldElement eCFieldElement2 = this.f9731y;
                ECFieldElement eCFieldElement3 = this.f9732zs[0];
                boolean isOne = eCFieldElement3.isOne();
                ECFieldElement multiply = isOne ? eCFieldElement : eCFieldElement.multiply(eCFieldElement3);
                if (!isOne) {
                    eCFieldElement2 = eCFieldElement2.multiply(eCFieldElement3);
                }
                ECFieldElement square = eCFieldElement.square();
                ECFieldElement add4 = square.add(eCFieldElement2);
                ECFieldElement square2 = multiply.square();
                ECFieldElement add5 = add4.add(multiply);
                ECFieldElement multiplyPlusProduct = add5.multiplyPlusProduct(add4, square2, curve.getA());
                return new F2m(curve, multiply.multiply(multiplyPlusProduct), square.square().multiplyPlusProduct(multiply, multiplyPlusProduct, add5), new ECFieldElement[]{multiply.multiply(square2)});
            }
            if (coordinateSystem != 6) {
                throw new IllegalStateException("unsupported coordinate system");
            }
            ECFieldElement eCFieldElement4 = this.f9731y;
            ECFieldElement eCFieldElement5 = this.f9732zs[0];
            boolean isOne2 = eCFieldElement5.isOne();
            ECFieldElement multiply2 = isOne2 ? eCFieldElement4 : eCFieldElement4.multiply(eCFieldElement5);
            ECFieldElement square3 = isOne2 ? eCFieldElement5 : eCFieldElement5.square();
            ECFieldElement a = curve.getA();
            ECFieldElement multiply3 = isOne2 ? a : a.multiply(square3);
            ECFieldElement add6 = eCFieldElement4.square().add(multiply2).add(multiply3);
            if (add6.isZero()) {
                return new F2m(curve, add6, curve.getB().sqrt());
            }
            ECFieldElement square4 = add6.square();
            ECFieldElement multiply4 = isOne2 ? add6 : add6.multiply(square3);
            ECFieldElement b = curve.getB();
            if (b.bitLength() < (curve.getFieldSize() >> 1)) {
                ECFieldElement square5 = eCFieldElement4.add(eCFieldElement).square();
                add = square5.add(add6).add(square3).multiply(square5).add(b.isOne() ? multiply3.add(square3).square() : multiply3.squarePlusProduct(b, square3.square())).add(square4);
                if (!a.isZero()) {
                    if (!a.isOne()) {
                        add = add.add(a.addOne().multiply(multiply4));
                    }
                    return new F2m(curve, square4, add, new ECFieldElement[]{multiply4});
                }
            } else {
                if (!isOne2) {
                    eCFieldElement = eCFieldElement.multiply(eCFieldElement5);
                }
                add = eCFieldElement.squarePlusProduct(add6, multiply2).add(square4);
            }
            add = add.add(multiply4);
            return new F2m(curve, square4, add, new ECFieldElement[]{multiply4});
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint twicePlus(ECPoint eCPoint) {
            if (isInfinity()) {
                return eCPoint;
            }
            if (eCPoint.isInfinity()) {
                return twice();
            }
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement = this.f9730x;
            if (eCFieldElement.isZero()) {
                return eCPoint;
            }
            if (curve.getCoordinateSystem() != 6) {
                return twice().add(eCPoint);
            }
            ECFieldElement eCFieldElement2 = eCPoint.f9730x;
            ECFieldElement eCFieldElement3 = eCPoint.f9732zs[0];
            if (eCFieldElement2.isZero() || !eCFieldElement3.isOne()) {
                return twice().add(eCPoint);
            }
            ECFieldElement eCFieldElement4 = this.f9731y;
            ECFieldElement eCFieldElement5 = this.f9732zs[0];
            ECFieldElement eCFieldElement6 = eCPoint.f9731y;
            ECFieldElement square = eCFieldElement.square();
            ECFieldElement square2 = eCFieldElement4.square();
            ECFieldElement square3 = eCFieldElement5.square();
            ECFieldElement add = curve.getA().multiply(square3).add(square2).add(eCFieldElement4.multiply(eCFieldElement5));
            ECFieldElement addOne = eCFieldElement6.addOne();
            ECFieldElement multiplyPlusProduct = curve.getA().add(addOne).multiply(square3).add(square2).multiplyPlusProduct(add, square, square3);
            ECFieldElement multiply = eCFieldElement2.multiply(square3);
            ECFieldElement square4 = multiply.add(add).square();
            if (square4.isZero()) {
                return multiplyPlusProduct.isZero() ? eCPoint.twice() : curve.getInfinity();
            }
            if (multiplyPlusProduct.isZero()) {
                return new F2m(curve, multiplyPlusProduct, curve.getB().sqrt());
            }
            ECFieldElement multiply2 = multiplyPlusProduct.square().multiply(multiply);
            ECFieldElement multiply3 = multiplyPlusProduct.multiply(square4).multiply(square3);
            return new F2m(curve, multiply2, multiplyPlusProduct.add(square4).square().multiplyPlusProduct(add, addOne, multiply3), new ECFieldElement[]{multiply3});
        }
    }

    /* renamed from: org.bouncycastle.math.ec.ECPoint$Fp */
    /* loaded from: classes9.dex */
    public static class C8422Fp extends AbstractFp {
        /* JADX INFO: Access modifiers changed from: package-private */
        public C8422Fp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public C8422Fp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        }

        /* JADX WARN: Code restructure failed: missing block: B:57:0x0120, code lost:
        
            if (r1 == r6) goto L58;
         */
        @Override // org.bouncycastle.math.ec.ECPoint
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public ECPoint add(ECPoint eCPoint) {
            ECFieldElement square;
            ECFieldElement subtract;
            ECFieldElement multiplyMinusProduct;
            ECFieldElement multiply;
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
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement = this.f9730x;
            ECFieldElement eCFieldElement2 = this.f9731y;
            ECFieldElement eCFieldElement3 = eCPoint.f9730x;
            ECFieldElement eCFieldElement4 = eCPoint.f9731y;
            if (coordinateSystem == 0) {
                ECFieldElement subtract2 = eCFieldElement3.subtract(eCFieldElement);
                ECFieldElement subtract3 = eCFieldElement4.subtract(eCFieldElement2);
                if (subtract2.isZero()) {
                    return subtract3.isZero() ? twice() : curve.getInfinity();
                }
                ECFieldElement divide = subtract3.divide(subtract2);
                ECFieldElement subtract4 = divide.square().subtract(eCFieldElement).subtract(eCFieldElement3);
                return new C8422Fp(curve, subtract4, divide.multiply(eCFieldElement.subtract(subtract4)).subtract(eCFieldElement2));
            }
            if (coordinateSystem == 1) {
                ECFieldElement eCFieldElement5 = this.f9732zs[0];
                ECFieldElement eCFieldElement6 = eCPoint.f9732zs[0];
                boolean isOne = eCFieldElement5.isOne();
                boolean isOne2 = eCFieldElement6.isOne();
                if (!isOne) {
                    eCFieldElement4 = eCFieldElement4.multiply(eCFieldElement5);
                }
                if (!isOne2) {
                    eCFieldElement2 = eCFieldElement2.multiply(eCFieldElement6);
                }
                ECFieldElement subtract5 = eCFieldElement4.subtract(eCFieldElement2);
                if (!isOne) {
                    eCFieldElement3 = eCFieldElement3.multiply(eCFieldElement5);
                }
                if (!isOne2) {
                    eCFieldElement = eCFieldElement.multiply(eCFieldElement6);
                }
                ECFieldElement subtract6 = eCFieldElement3.subtract(eCFieldElement);
                if (subtract6.isZero()) {
                    return subtract5.isZero() ? twice() : curve.getInfinity();
                }
                if (isOne) {
                    eCFieldElement5 = eCFieldElement6;
                } else if (!isOne2) {
                    eCFieldElement5 = eCFieldElement5.multiply(eCFieldElement6);
                }
                ECFieldElement square2 = subtract6.square();
                ECFieldElement multiply2 = square2.multiply(subtract6);
                ECFieldElement multiply3 = square2.multiply(eCFieldElement);
                ECFieldElement subtract7 = subtract5.square().multiply(eCFieldElement5).subtract(multiply2).subtract(two(multiply3));
                return new C8422Fp(curve, subtract6.multiply(subtract7), multiply3.subtract(subtract7).multiplyMinusProduct(subtract5, eCFieldElement2, multiply2), new ECFieldElement[]{multiply2.multiply(eCFieldElement5)});
            }
            if (coordinateSystem != 2 && coordinateSystem != 4) {
                throw new IllegalStateException("unsupported coordinate system");
            }
            ECFieldElement eCFieldElement7 = this.f9732zs[0];
            ECFieldElement eCFieldElement8 = eCPoint.f9732zs[0];
            boolean isOne3 = eCFieldElement7.isOne();
            if (isOne3 || !eCFieldElement7.equals(eCFieldElement8)) {
                if (!isOne3) {
                    ECFieldElement square3 = eCFieldElement7.square();
                    eCFieldElement3 = square3.multiply(eCFieldElement3);
                    eCFieldElement4 = square3.multiply(eCFieldElement7).multiply(eCFieldElement4);
                }
                boolean isOne4 = eCFieldElement8.isOne();
                if (!isOne4) {
                    ECFieldElement square4 = eCFieldElement8.square();
                    eCFieldElement = square4.multiply(eCFieldElement);
                    eCFieldElement2 = square4.multiply(eCFieldElement8).multiply(eCFieldElement2);
                }
                ECFieldElement subtract8 = eCFieldElement.subtract(eCFieldElement3);
                ECFieldElement subtract9 = eCFieldElement2.subtract(eCFieldElement4);
                if (subtract8.isZero()) {
                    return subtract9.isZero() ? twice() : curve.getInfinity();
                }
                square = subtract8.square();
                ECFieldElement multiply4 = square.multiply(subtract8);
                ECFieldElement multiply5 = square.multiply(eCFieldElement);
                subtract = subtract9.square().add(multiply4).subtract(two(multiply5));
                multiplyMinusProduct = multiply5.subtract(subtract).multiplyMinusProduct(subtract9, multiply4, eCFieldElement2);
                ECFieldElement multiply6 = !isOne3 ? subtract8.multiply(eCFieldElement7) : subtract8;
                multiply = !isOne4 ? multiply6.multiply(eCFieldElement8) : multiply6;
            } else {
                ECFieldElement subtract10 = eCFieldElement.subtract(eCFieldElement3);
                ECFieldElement subtract11 = eCFieldElement2.subtract(eCFieldElement4);
                if (subtract10.isZero()) {
                    return subtract11.isZero() ? twice() : curve.getInfinity();
                }
                ECFieldElement square5 = subtract10.square();
                ECFieldElement multiply7 = eCFieldElement.multiply(square5);
                ECFieldElement multiply8 = eCFieldElement3.multiply(square5);
                ECFieldElement multiply9 = multiply7.subtract(multiply8).multiply(eCFieldElement2);
                ECFieldElement subtract12 = subtract11.square().subtract(multiply7).subtract(multiply8);
                multiplyMinusProduct = multiply7.subtract(subtract12).multiply(subtract11).subtract(multiply9);
                multiply = subtract10.multiply(eCFieldElement7);
                subtract = subtract12;
            }
            square = null;
            return new C8422Fp(curve, subtract, multiplyMinusProduct, coordinateSystem == 4 ? new ECFieldElement[]{multiply, calculateJacobianModifiedW(multiply, square)} : new ECFieldElement[]{multiply});
        }

        protected ECFieldElement calculateJacobianModifiedW(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            ECFieldElement a = getCurve().getA();
            if (a.isZero() || eCFieldElement.isOne()) {
                return a;
            }
            if (eCFieldElement2 == null) {
                eCFieldElement2 = eCFieldElement.square();
            }
            ECFieldElement square = eCFieldElement2.square();
            ECFieldElement negate = a.negate();
            return negate.bitLength() < a.bitLength() ? square.multiply(negate).negate() : square.multiply(a);
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        protected ECPoint detach() {
            return new C8422Fp(null, getAffineXCoord(), getAffineYCoord());
        }

        protected ECFieldElement doubleProductFromSquares(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3, ECFieldElement eCFieldElement4) {
            return eCFieldElement.add(eCFieldElement2).square().subtract(eCFieldElement3).subtract(eCFieldElement4);
        }

        protected ECFieldElement eight(ECFieldElement eCFieldElement) {
            return four(two(eCFieldElement));
        }

        protected ECFieldElement four(ECFieldElement eCFieldElement) {
            return two(two(eCFieldElement));
        }

        protected ECFieldElement getJacobianModifiedW() {
            ECFieldElement eCFieldElement = this.f9732zs[1];
            if (eCFieldElement != null) {
                return eCFieldElement;
            }
            ECFieldElement[] eCFieldElementArr = this.f9732zs;
            ECFieldElement calculateJacobianModifiedW = calculateJacobianModifiedW(this.f9732zs[0], null);
            eCFieldElementArr[1] = calculateJacobianModifiedW;
            return calculateJacobianModifiedW;
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECFieldElement getZCoord(int i) {
            return (i == 1 && 4 == getCurveCoordinateSystem()) ? getJacobianModifiedW() : super.getZCoord(i);
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint negate() {
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            return curve.getCoordinateSystem() != 0 ? new C8422Fp(curve, this.f9730x, this.f9731y.negate(), this.f9732zs) : new C8422Fp(curve, this.f9730x, this.f9731y.negate());
        }

        protected ECFieldElement three(ECFieldElement eCFieldElement) {
            return two(eCFieldElement).add(eCFieldElement);
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint threeTimes() {
            if (isInfinity()) {
                return this;
            }
            ECFieldElement eCFieldElement = this.f9731y;
            if (eCFieldElement.isZero()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem != 0) {
                return coordinateSystem != 4 ? twice().add(this) : twiceJacobianModified(false).add(this);
            }
            ECFieldElement eCFieldElement2 = this.f9730x;
            ECFieldElement two = two(eCFieldElement);
            ECFieldElement square = two.square();
            ECFieldElement add = three(eCFieldElement2.square()).add(getCurve().getA());
            ECFieldElement subtract = three(eCFieldElement2).multiply(square).subtract(add.square());
            if (subtract.isZero()) {
                return getCurve().getInfinity();
            }
            ECFieldElement invert = subtract.multiply(two).invert();
            ECFieldElement multiply = subtract.multiply(invert).multiply(add);
            ECFieldElement subtract2 = square.square().multiply(invert).subtract(multiply);
            ECFieldElement add2 = subtract2.subtract(multiply).multiply(multiply.add(subtract2)).add(eCFieldElement2);
            return new C8422Fp(curve, add2, eCFieldElement2.subtract(add2).multiply(subtract2).subtract(eCFieldElement));
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint timesPow2(int i) {
            ECFieldElement square;
            if (i < 0) {
                throw new IllegalArgumentException("'e' cannot be negative");
            }
            if (i == 0 || isInfinity()) {
                return this;
            }
            if (i == 1) {
                return twice();
            }
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement = this.f9731y;
            if (eCFieldElement.isZero()) {
                return curve.getInfinity();
            }
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement a = curve.getA();
            ECFieldElement eCFieldElement2 = this.f9730x;
            ECFieldElement fromBigInteger = this.f9732zs.length < 1 ? curve.fromBigInteger(ECConstants.ONE) : this.f9732zs[0];
            if (!fromBigInteger.isOne() && coordinateSystem != 0) {
                if (coordinateSystem == 1) {
                    square = fromBigInteger.square();
                    eCFieldElement2 = eCFieldElement2.multiply(fromBigInteger);
                    eCFieldElement = eCFieldElement.multiply(square);
                } else if (coordinateSystem == 2) {
                    square = null;
                } else {
                    if (coordinateSystem != 4) {
                        throw new IllegalStateException("unsupported coordinate system");
                    }
                    a = getJacobianModifiedW();
                }
                a = calculateJacobianModifiedW(fromBigInteger, square);
            }
            ECFieldElement eCFieldElement3 = a;
            ECFieldElement eCFieldElement4 = eCFieldElement;
            int i2 = 0;
            while (i2 < i) {
                if (eCFieldElement4.isZero()) {
                    return curve.getInfinity();
                }
                ECFieldElement three = three(eCFieldElement2.square());
                ECFieldElement two = two(eCFieldElement4);
                ECFieldElement multiply = two.multiply(eCFieldElement4);
                ECFieldElement two2 = two(eCFieldElement2.multiply(multiply));
                ECFieldElement two3 = two(multiply.square());
                if (!eCFieldElement3.isZero()) {
                    three = three.add(eCFieldElement3);
                    eCFieldElement3 = two(two3.multiply(eCFieldElement3));
                }
                ECFieldElement subtract = three.square().subtract(two(two2));
                eCFieldElement4 = three.multiply(two2.subtract(subtract)).subtract(two3);
                fromBigInteger = fromBigInteger.isOne() ? two : two.multiply(fromBigInteger);
                i2++;
                eCFieldElement2 = subtract;
            }
            if (coordinateSystem == 0) {
                ECFieldElement invert = fromBigInteger.invert();
                ECFieldElement square2 = invert.square();
                return new C8422Fp(curve, eCFieldElement2.multiply(square2), eCFieldElement4.multiply(square2.multiply(invert)));
            }
            if (coordinateSystem == 1) {
                return new C8422Fp(curve, eCFieldElement2.multiply(fromBigInteger), eCFieldElement4, new ECFieldElement[]{fromBigInteger.multiply(fromBigInteger.square())});
            }
            if (coordinateSystem == 2) {
                return new C8422Fp(curve, eCFieldElement2, eCFieldElement4, new ECFieldElement[]{fromBigInteger});
            }
            if (coordinateSystem == 4) {
                return new C8422Fp(curve, eCFieldElement2, eCFieldElement4, new ECFieldElement[]{fromBigInteger, eCFieldElement3});
            }
            throw new IllegalStateException("unsupported coordinate system");
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint twice() {
            ECFieldElement eCFieldElement;
            ECFieldElement multiply;
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement2 = this.f9731y;
            if (eCFieldElement2.isZero()) {
                return curve.getInfinity();
            }
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement3 = this.f9730x;
            if (coordinateSystem == 0) {
                ECFieldElement divide = three(eCFieldElement3.square()).add(getCurve().getA()).divide(two(eCFieldElement2));
                ECFieldElement subtract = divide.square().subtract(two(eCFieldElement3));
                return new C8422Fp(curve, subtract, divide.multiply(eCFieldElement3.subtract(subtract)).subtract(eCFieldElement2));
            }
            if (coordinateSystem == 1) {
                ECFieldElement eCFieldElement4 = this.f9732zs[0];
                boolean isOne = eCFieldElement4.isOne();
                ECFieldElement a = curve.getA();
                if (!a.isZero() && !isOne) {
                    a = a.multiply(eCFieldElement4.square());
                }
                ECFieldElement add = a.add(three(eCFieldElement3.square()));
                ECFieldElement multiply2 = isOne ? eCFieldElement2 : eCFieldElement2.multiply(eCFieldElement4);
                ECFieldElement square = isOne ? eCFieldElement2.square() : multiply2.multiply(eCFieldElement2);
                ECFieldElement four = four(eCFieldElement3.multiply(square));
                ECFieldElement subtract2 = add.square().subtract(two(four));
                ECFieldElement two = two(multiply2);
                ECFieldElement multiply3 = subtract2.multiply(two);
                ECFieldElement two2 = two(square);
                return new C8422Fp(curve, multiply3, four.subtract(subtract2).multiply(add).subtract(two(two2.square())), new ECFieldElement[]{two(isOne ? two(two2) : two.square()).multiply(multiply2)});
            }
            if (coordinateSystem != 2) {
                if (coordinateSystem == 4) {
                    return twiceJacobianModified(true);
                }
                throw new IllegalStateException("unsupported coordinate system");
            }
            ECFieldElement eCFieldElement5 = this.f9732zs[0];
            boolean isOne2 = eCFieldElement5.isOne();
            ECFieldElement square2 = eCFieldElement2.square();
            ECFieldElement square3 = square2.square();
            ECFieldElement a2 = curve.getA();
            ECFieldElement negate = a2.negate();
            if (negate.toBigInteger().equals(BigInteger.valueOf(3L))) {
                ECFieldElement square4 = isOne2 ? eCFieldElement5 : eCFieldElement5.square();
                eCFieldElement = three(eCFieldElement3.add(square4).multiply(eCFieldElement3.subtract(square4)));
                multiply = square2.multiply(eCFieldElement3);
            } else {
                ECFieldElement three = three(eCFieldElement3.square());
                if (!isOne2) {
                    if (a2.isZero()) {
                        eCFieldElement = three;
                    } else {
                        ECFieldElement square5 = eCFieldElement5.square().square();
                        if (negate.bitLength() < a2.bitLength()) {
                            eCFieldElement = three.subtract(square5.multiply(negate));
                        } else {
                            a2 = square5.multiply(a2);
                        }
                    }
                    multiply = eCFieldElement3.multiply(square2);
                }
                eCFieldElement = three.add(a2);
                multiply = eCFieldElement3.multiply(square2);
            }
            ECFieldElement four2 = four(multiply);
            ECFieldElement subtract3 = eCFieldElement.square().subtract(two(four2));
            ECFieldElement subtract4 = four2.subtract(subtract3).multiply(eCFieldElement).subtract(eight(square3));
            ECFieldElement two3 = two(eCFieldElement2);
            if (!isOne2) {
                two3 = two3.multiply(eCFieldElement5);
            }
            return new C8422Fp(curve, subtract3, subtract4, new ECFieldElement[]{two3});
        }

        protected C8422Fp twiceJacobianModified(boolean z) {
            ECFieldElement eCFieldElement = this.f9730x;
            ECFieldElement eCFieldElement2 = this.f9731y;
            ECFieldElement eCFieldElement3 = this.f9732zs[0];
            ECFieldElement jacobianModifiedW = getJacobianModifiedW();
            ECFieldElement add = three(eCFieldElement.square()).add(jacobianModifiedW);
            ECFieldElement two = two(eCFieldElement2);
            ECFieldElement multiply = two.multiply(eCFieldElement2);
            ECFieldElement two2 = two(eCFieldElement.multiply(multiply));
            ECFieldElement subtract = add.square().subtract(two(two2));
            ECFieldElement two3 = two(multiply.square());
            ECFieldElement subtract2 = add.multiply(two2.subtract(subtract)).subtract(two3);
            ECFieldElement two4 = z ? two(two3.multiply(jacobianModifiedW)) : null;
            if (!eCFieldElement3.isOne()) {
                two = two.multiply(eCFieldElement3);
            }
            return new C8422Fp(getCurve(), subtract, subtract2, new ECFieldElement[]{two, two4});
        }

        @Override // org.bouncycastle.math.ec.ECPoint
        public ECPoint twicePlus(ECPoint eCPoint) {
            if (this == eCPoint) {
                return threeTimes();
            }
            if (isInfinity()) {
                return eCPoint;
            }
            if (eCPoint.isInfinity()) {
                return twice();
            }
            ECFieldElement eCFieldElement = this.f9731y;
            if (eCFieldElement.isZero()) {
                return eCPoint;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem != 0) {
                return coordinateSystem != 4 ? twice().add(eCPoint) : twiceJacobianModified(false).add(eCPoint);
            }
            ECFieldElement eCFieldElement2 = this.f9730x;
            ECFieldElement eCFieldElement3 = eCPoint.f9730x;
            ECFieldElement eCFieldElement4 = eCPoint.f9731y;
            ECFieldElement subtract = eCFieldElement3.subtract(eCFieldElement2);
            ECFieldElement subtract2 = eCFieldElement4.subtract(eCFieldElement);
            if (subtract.isZero()) {
                return subtract2.isZero() ? threeTimes() : this;
            }
            ECFieldElement square = subtract.square();
            ECFieldElement subtract3 = square.multiply(two(eCFieldElement2).add(eCFieldElement3)).subtract(subtract2.square());
            if (subtract3.isZero()) {
                return curve.getInfinity();
            }
            ECFieldElement invert = subtract3.multiply(subtract).invert();
            ECFieldElement multiply = subtract3.multiply(invert).multiply(subtract2);
            ECFieldElement subtract4 = two(eCFieldElement).multiply(square).multiply(subtract).multiply(invert).subtract(multiply);
            ECFieldElement add = subtract4.subtract(multiply).multiply(multiply.add(subtract4)).add(eCFieldElement3);
            return new C8422Fp(curve, add, eCFieldElement2.subtract(add).multiply(subtract4).subtract(eCFieldElement));
        }

        protected ECFieldElement two(ECFieldElement eCFieldElement) {
            return eCFieldElement.add(eCFieldElement);
        }
    }

    protected ECPoint(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, getInitialZCoords(eCCurve));
    }

    protected ECPoint(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        this.preCompTable = null;
        this.curve = eCCurve;
        this.f9730x = eCFieldElement;
        this.f9731y = eCFieldElement2;
        this.f9732zs = eCFieldElementArr;
    }

    protected static ECFieldElement[] getInitialZCoords(ECCurve eCCurve) {
        int coordinateSystem = eCCurve == null ? 0 : eCCurve.getCoordinateSystem();
        if (coordinateSystem == 0 || coordinateSystem == 5) {
            return EMPTY_ZS;
        }
        ECFieldElement fromBigInteger = eCCurve.fromBigInteger(ECConstants.ONE);
        if (coordinateSystem != 1 && coordinateSystem != 2) {
            if (coordinateSystem == 3) {
                return new ECFieldElement[]{fromBigInteger, fromBigInteger, fromBigInteger};
            }
            if (coordinateSystem == 4) {
                return new ECFieldElement[]{fromBigInteger, eCCurve.getA()};
            }
            if (coordinateSystem != 6) {
                throw new IllegalArgumentException("unknown coordinate system");
            }
        }
        return new ECFieldElement[]{fromBigInteger};
    }

    public abstract ECPoint add(ECPoint eCPoint);

    protected void checkNormalized() {
        if (!isNormalized()) {
            throw new IllegalStateException("point not in normal form");
        }
    }

    protected ECPoint createScaledPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return getCurve().createRawPoint(getRawXCoord().multiply(eCFieldElement), getRawYCoord().multiply(eCFieldElement2));
    }

    protected abstract ECPoint detach();

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ECPoint) {
            return equals((ECPoint) obj);
        }
        return false;
    }

    public boolean equals(ECPoint eCPoint) {
        ECPoint eCPoint2;
        ECPoint eCPoint3;
        if (eCPoint == null) {
            return false;
        }
        ECCurve curve = getCurve();
        ECCurve curve2 = eCPoint.getCurve();
        boolean z = curve == null;
        boolean z2 = curve2 == null;
        boolean isInfinity = isInfinity();
        boolean isInfinity2 = eCPoint.isInfinity();
        if (isInfinity || isInfinity2) {
            if (isInfinity && isInfinity2) {
                return z || z2 || curve.equals(curve2);
            }
            return false;
        }
        if (!z || !z2) {
            if (!z) {
                if (z2) {
                    eCPoint3 = eCPoint;
                    eCPoint2 = normalize();
                } else {
                    if (!curve.equals(curve2)) {
                        return false;
                    }
                    ECPoint[] eCPointArr = {this, curve.importPoint(eCPoint)};
                    curve.normalizeAll(eCPointArr);
                    eCPoint2 = eCPointArr[0];
                    eCPoint3 = eCPointArr[1];
                }
                return eCPoint2.getXCoord().equals(eCPoint3.getXCoord()) && eCPoint2.getYCoord().equals(eCPoint3.getYCoord());
            }
            eCPoint = eCPoint.normalize();
        }
        eCPoint3 = eCPoint;
        eCPoint2 = this;
        if (eCPoint2.getXCoord().equals(eCPoint3.getXCoord())) {
            return false;
        }
    }

    public ECFieldElement getAffineXCoord() {
        checkNormalized();
        return getXCoord();
    }

    public ECFieldElement getAffineYCoord() {
        checkNormalized();
        return getYCoord();
    }

    protected abstract boolean getCompressionYTilde();

    public ECCurve getCurve() {
        return this.curve;
    }

    protected int getCurveCoordinateSystem() {
        ECCurve eCCurve = this.curve;
        if (eCCurve == null) {
            return 0;
        }
        return eCCurve.getCoordinateSystem();
    }

    public final ECPoint getDetachedPoint() {
        return normalize().detach();
    }

    public byte[] getEncoded(boolean z) {
        if (isInfinity()) {
            return new byte[1];
        }
        ECPoint normalize = normalize();
        byte[] encoded = normalize.getXCoord().getEncoded();
        if (z) {
            byte[] bArr = new byte[encoded.length + 1];
            bArr[0] = (byte) (normalize.getCompressionYTilde() ? 3 : 2);
            System.arraycopy(encoded, 0, bArr, 1, encoded.length);
            return bArr;
        }
        byte[] encoded2 = normalize.getYCoord().getEncoded();
        byte[] bArr2 = new byte[encoded.length + encoded2.length + 1];
        bArr2[0] = 4;
        System.arraycopy(encoded, 0, bArr2, 1, encoded.length);
        System.arraycopy(encoded2, 0, bArr2, encoded.length + 1, encoded2.length);
        return bArr2;
    }

    public final ECFieldElement getRawXCoord() {
        return this.f9730x;
    }

    public final ECFieldElement getRawYCoord() {
        return this.f9731y;
    }

    protected final ECFieldElement[] getRawZCoords() {
        return this.f9732zs;
    }

    public ECFieldElement getXCoord() {
        return this.f9730x;
    }

    public ECFieldElement getYCoord() {
        return this.f9731y;
    }

    public ECFieldElement getZCoord(int i) {
        if (i >= 0) {
            ECFieldElement[] eCFieldElementArr = this.f9732zs;
            if (i < eCFieldElementArr.length) {
                return eCFieldElementArr[i];
            }
        }
        return null;
    }

    public ECFieldElement[] getZCoords() {
        ECFieldElement[] eCFieldElementArr = this.f9732zs;
        int length = eCFieldElementArr.length;
        if (length == 0) {
            return EMPTY_ZS;
        }
        ECFieldElement[] eCFieldElementArr2 = new ECFieldElement[length];
        System.arraycopy(eCFieldElementArr, 0, eCFieldElementArr2, 0, length);
        return eCFieldElementArr2;
    }

    public int hashCode() {
        ECCurve curve = getCurve();
        int i = curve == null ? 0 : ~curve.hashCode();
        if (isInfinity()) {
            return i;
        }
        ECPoint normalize = normalize();
        return (i ^ (normalize.getXCoord().hashCode() * 17)) ^ (normalize.getYCoord().hashCode() * 257);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean implIsValid(final boolean z, final boolean z2) {
        if (isInfinity()) {
            return true;
        }
        return !((ValidityPrecompInfo) getCurve().precompute(this, "bc_validity", new PreCompCallback() { // from class: org.bouncycastle.math.ec.ECPoint.1
            @Override // org.bouncycastle.math.ec.PreCompCallback
            public PreCompInfo precompute(PreCompInfo preCompInfo) {
                ValidityPrecompInfo validityPrecompInfo = preCompInfo instanceof ValidityPrecompInfo ? (ValidityPrecompInfo) preCompInfo : null;
                if (validityPrecompInfo == null) {
                    validityPrecompInfo = new ValidityPrecompInfo();
                }
                if (validityPrecompInfo.hasFailed()) {
                    return validityPrecompInfo;
                }
                if (!validityPrecompInfo.hasCurveEquationPassed()) {
                    if (!z && !ECPoint.this.satisfiesCurveEquation()) {
                        validityPrecompInfo.reportFailed();
                        return validityPrecompInfo;
                    }
                    validityPrecompInfo.reportCurveEquationPassed();
                }
                if (z2 && !validityPrecompInfo.hasOrderPassed()) {
                    if (!ECPoint.this.satisfiesOrder()) {
                        validityPrecompInfo.reportFailed();
                        return validityPrecompInfo;
                    }
                    validityPrecompInfo.reportOrderPassed();
                }
                return validityPrecompInfo;
            }
        })).hasFailed();
    }

    public boolean isInfinity() {
        if (this.f9730x != null && this.f9731y != null) {
            ECFieldElement[] eCFieldElementArr = this.f9732zs;
            if (eCFieldElementArr.length <= 0 || !eCFieldElementArr[0].isZero()) {
                return false;
            }
        }
        return true;
    }

    public boolean isNormalized() {
        int curveCoordinateSystem = getCurveCoordinateSystem();
        return curveCoordinateSystem == 0 || curveCoordinateSystem == 5 || isInfinity() || this.f9732zs[0].isOne();
    }

    public boolean isValid() {
        return implIsValid(false, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isValidPartial() {
        return implIsValid(false, false);
    }

    public ECPoint multiply(BigInteger bigInteger) {
        return getCurve().getMultiplier().multiply(this, bigInteger);
    }

    public abstract ECPoint negate();

    public ECPoint normalize() {
        int curveCoordinateSystem;
        if (isInfinity() || (curveCoordinateSystem = getCurveCoordinateSystem()) == 0 || curveCoordinateSystem == 5) {
            return this;
        }
        ECFieldElement zCoord = getZCoord(0);
        return zCoord.isOne() ? this : normalize(zCoord.invert());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ECPoint normalize(ECFieldElement eCFieldElement) {
        int curveCoordinateSystem = getCurveCoordinateSystem();
        if (curveCoordinateSystem != 1) {
            if (curveCoordinateSystem == 2 || curveCoordinateSystem == 3 || curveCoordinateSystem == 4) {
                ECFieldElement square = eCFieldElement.square();
                return createScaledPoint(square, square.multiply(eCFieldElement));
            }
            if (curveCoordinateSystem != 6) {
                throw new IllegalStateException("not a projective coordinate system");
            }
        }
        return createScaledPoint(eCFieldElement, eCFieldElement);
    }

    protected abstract boolean satisfiesCurveEquation();

    protected boolean satisfiesOrder() {
        BigInteger order;
        return ECConstants.ONE.equals(this.curve.getCofactor()) || (order = this.curve.getOrder()) == null || ECAlgorithms.referenceMultiply(this, order).isInfinity();
    }

    public ECPoint scaleX(ECFieldElement eCFieldElement) {
        return isInfinity() ? this : getCurve().createRawPoint(getRawXCoord().multiply(eCFieldElement), getRawYCoord(), getRawZCoords());
    }

    public ECPoint scaleXNegateY(ECFieldElement eCFieldElement) {
        return isInfinity() ? this : getCurve().createRawPoint(getRawXCoord().multiply(eCFieldElement), getRawYCoord().negate(), getRawZCoords());
    }

    public ECPoint scaleY(ECFieldElement eCFieldElement) {
        return isInfinity() ? this : getCurve().createRawPoint(getRawXCoord(), getRawYCoord().multiply(eCFieldElement), getRawZCoords());
    }

    public ECPoint scaleYNegateX(ECFieldElement eCFieldElement) {
        return isInfinity() ? this : getCurve().createRawPoint(getRawXCoord().negate(), getRawYCoord().multiply(eCFieldElement), getRawZCoords());
    }

    public abstract ECPoint subtract(ECPoint eCPoint);

    public ECPoint threeTimes() {
        return twicePlus(this);
    }

    public ECPoint timesPow2(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("'e' cannot be negative");
        }
        ECPoint eCPoint = this;
        while (true) {
            i--;
            if (i < 0) {
                return eCPoint;
            }
            eCPoint = eCPoint.twice();
        }
    }

    public String toString() {
        if (isInfinity()) {
            return "INF";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append('(');
        stringBuffer.append(getRawXCoord());
        stringBuffer.append(',');
        stringBuffer.append(getRawYCoord());
        for (int i = 0; i < this.f9732zs.length; i++) {
            stringBuffer.append(',');
            stringBuffer.append(this.f9732zs[i]);
        }
        stringBuffer.append(')');
        return stringBuffer.toString();
    }

    public abstract ECPoint twice();

    public ECPoint twicePlus(ECPoint eCPoint) {
        return twice().add(eCPoint);
    }
}
