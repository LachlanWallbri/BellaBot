package org.bouncycastle.math.ec.custom.djb;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.Arrays;

/* loaded from: classes9.dex */
public class Curve25519FieldElement extends ECFieldElement.AbstractFp {

    /* renamed from: x */
    protected int[] f9740x;

    /* renamed from: Q */
    public static final BigInteger f9739Q = Nat256.toBigInteger(Curve25519Field.f9737P);
    private static final int[] PRECOMP_POW2 = {1242472624, -991028441, -1389370248, 792926214, 1039914919, 726466713, 1338105611, 730014848};

    public Curve25519FieldElement() {
        this.f9740x = Nat256.create();
    }

    public Curve25519FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f9739Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for Curve25519FieldElement");
        }
        this.f9740x = Curve25519Field.fromBigInteger(bigInteger);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Curve25519FieldElement(int[] iArr) {
        this.f9740x = iArr;
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] create = Nat256.create();
        Curve25519Field.add(this.f9740x, ((Curve25519FieldElement) eCFieldElement).f9740x, create);
        return new Curve25519FieldElement(create);
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement addOne() {
        int[] create = Nat256.create();
        Curve25519Field.addOne(this.f9740x, create);
        return new Curve25519FieldElement(create);
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] create = Nat256.create();
        Mod.invert(Curve25519Field.f9737P, ((Curve25519FieldElement) eCFieldElement).f9740x, create);
        Curve25519Field.multiply(create, this.f9740x, create);
        return new Curve25519FieldElement(create);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Curve25519FieldElement) {
            return Nat256.m4119eq(this.f9740x, ((Curve25519FieldElement) obj).f9740x);
        }
        return false;
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public String getFieldName() {
        return "Curve25519Field";
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public int getFieldSize() {
        return f9739Q.bitLength();
    }

    public int hashCode() {
        return f9739Q.hashCode() ^ Arrays.hashCode(this.f9740x, 0, 8);
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement invert() {
        int[] create = Nat256.create();
        Mod.invert(Curve25519Field.f9737P, this.f9740x, create);
        return new Curve25519FieldElement(create);
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public boolean isOne() {
        return Nat256.isOne(this.f9740x);
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public boolean isZero() {
        return Nat256.isZero(this.f9740x);
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] create = Nat256.create();
        Curve25519Field.multiply(this.f9740x, ((Curve25519FieldElement) eCFieldElement).f9740x, create);
        return new Curve25519FieldElement(create);
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement negate() {
        int[] create = Nat256.create();
        Curve25519Field.negate(this.f9740x, create);
        return new Curve25519FieldElement(create);
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement sqrt() {
        int[] iArr = this.f9740x;
        if (Nat256.isZero(iArr) || Nat256.isOne(iArr)) {
            return this;
        }
        int[] create = Nat256.create();
        Curve25519Field.square(iArr, create);
        Curve25519Field.multiply(create, iArr, create);
        Curve25519Field.square(create, create);
        Curve25519Field.multiply(create, iArr, create);
        int[] create2 = Nat256.create();
        Curve25519Field.square(create, create2);
        Curve25519Field.multiply(create2, iArr, create2);
        int[] create3 = Nat256.create();
        Curve25519Field.squareN(create2, 3, create3);
        Curve25519Field.multiply(create3, create, create3);
        Curve25519Field.squareN(create3, 4, create);
        Curve25519Field.multiply(create, create2, create);
        Curve25519Field.squareN(create, 4, create3);
        Curve25519Field.multiply(create3, create2, create3);
        Curve25519Field.squareN(create3, 15, create2);
        Curve25519Field.multiply(create2, create3, create2);
        Curve25519Field.squareN(create2, 30, create3);
        Curve25519Field.multiply(create3, create2, create3);
        Curve25519Field.squareN(create3, 60, create2);
        Curve25519Field.multiply(create2, create3, create2);
        Curve25519Field.squareN(create2, 11, create3);
        Curve25519Field.multiply(create3, create, create3);
        Curve25519Field.squareN(create3, 120, create);
        Curve25519Field.multiply(create, create2, create);
        Curve25519Field.square(create, create);
        Curve25519Field.square(create, create2);
        if (Nat256.m4119eq(iArr, create2)) {
            return new Curve25519FieldElement(create);
        }
        Curve25519Field.multiply(create, PRECOMP_POW2, create);
        Curve25519Field.square(create, create2);
        if (Nat256.m4119eq(iArr, create2)) {
            return new Curve25519FieldElement(create);
        }
        return null;
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement square() {
        int[] create = Nat256.create();
        Curve25519Field.square(this.f9740x, create);
        return new Curve25519FieldElement(create);
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] create = Nat256.create();
        Curve25519Field.subtract(this.f9740x, ((Curve25519FieldElement) eCFieldElement).f9740x, create);
        return new Curve25519FieldElement(create);
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public boolean testBitZero() {
        return Nat256.getBit(this.f9740x, 0) == 1;
    }

    @Override // org.bouncycastle.math.ec.ECFieldElement
    public BigInteger toBigInteger() {
        return Nat256.toBigInteger(this.f9740x);
    }
}
