package org.bouncycastle.pqc.crypto.rainbow.util;

import java.lang.reflect.Array;

/* loaded from: classes9.dex */
public class ComputeInField {

    /* renamed from: A */
    private short[][] f9940A;

    /* renamed from: x */
    short[] f9941x;

    private void computeZerosAbove() throws RuntimeException {
        for (int length = this.f9940A.length - 1; length > 0; length--) {
            for (int i = length - 1; i >= 0; i--) {
                short[][] sArr = this.f9940A;
                short s = sArr[i][length];
                short invElem = GF2Field.invElem(sArr[length][length]);
                if (invElem == 0) {
                    throw new RuntimeException("The matrix is not invertible");
                }
                int i2 = length;
                while (true) {
                    short[][] sArr2 = this.f9940A;
                    if (i2 < sArr2.length * 2) {
                        short multElem = GF2Field.multElem(s, GF2Field.multElem(sArr2[length][i2], invElem));
                        short[][] sArr3 = this.f9940A;
                        sArr3[i][i2] = GF2Field.addElem(sArr3[i][i2], multElem);
                        i2++;
                    }
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0058, code lost:
    
        r0 = r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void computeZerosUnder(boolean z) throws RuntimeException {
        int length = z ? this.f9940A.length * 2 : this.f9940A.length + 1;
        int i = 0;
        while (i < this.f9940A.length - 1) {
            int i2 = i + 1;
            int i3 = i2;
            while (true) {
                short[][] sArr = this.f9940A;
                if (i3 < sArr.length) {
                    short s = sArr[i3][i];
                    short invElem = GF2Field.invElem(sArr[i][i]);
                    if (invElem == 0) {
                        throw new IllegalStateException("Matrix not invertible! We have to choose another one!");
                    }
                    for (int i4 = i; i4 < length; i4++) {
                        short multElem = GF2Field.multElem(s, GF2Field.multElem(this.f9940A[i][i4], invElem));
                        short[][] sArr2 = this.f9940A;
                        sArr2[i3][i4] = GF2Field.addElem(sArr2[i3][i4], multElem);
                    }
                    i3++;
                }
            }
        }
    }

    private void substitute() throws IllegalStateException {
        short invElem = GF2Field.invElem(this.f9940A[r0.length - 1][r0.length - 1]);
        if (invElem == 0) {
            throw new IllegalStateException("The equation system is not solvable");
        }
        short[] sArr = this.f9941x;
        short[][] sArr2 = this.f9940A;
        sArr[sArr2.length - 1] = GF2Field.multElem(sArr2[sArr2.length - 1][sArr2.length], invElem);
        for (int length = this.f9940A.length - 2; length >= 0; length--) {
            short[][] sArr3 = this.f9940A;
            short s = sArr3[length][sArr3.length];
            for (int length2 = sArr3.length - 1; length2 > length; length2--) {
                s = GF2Field.addElem(s, GF2Field.multElem(this.f9940A[length][length2], this.f9941x[length2]));
            }
            short invElem2 = GF2Field.invElem(this.f9940A[length][length]);
            if (invElem2 == 0) {
                throw new IllegalStateException("Not solvable equation system");
            }
            this.f9941x[length] = GF2Field.multElem(s, invElem2);
        }
    }

    public short[][] addSquareMatrix(short[][] sArr, short[][] sArr2) {
        if (sArr.length != sArr2.length || sArr[0].length != sArr2[0].length) {
            throw new RuntimeException("Addition is not possible!");
        }
        short[][] sArr3 = (short[][]) Array.newInstance((Class<?>) short.class, sArr.length, sArr.length);
        for (int i = 0; i < sArr.length; i++) {
            for (int i2 = 0; i2 < sArr2.length; i2++) {
                sArr3[i][i2] = GF2Field.addElem(sArr[i][i2], sArr2[i][i2]);
            }
        }
        return sArr3;
    }

    public short[] addVect(short[] sArr, short[] sArr2) {
        if (sArr.length != sArr2.length) {
            throw new RuntimeException("Multiplication is not possible!");
        }
        short[] sArr3 = new short[sArr.length];
        for (int i = 0; i < sArr3.length; i++) {
            sArr3[i] = GF2Field.addElem(sArr[i], sArr2[i]);
        }
        return sArr3;
    }

    public short[][] inverse(short[][] sArr) {
        try {
            this.f9940A = (short[][]) Array.newInstance((Class<?>) short.class, sArr.length, sArr.length * 2);
            if (sArr.length != sArr[0].length) {
                throw new RuntimeException("The matrix is not invertible. Please choose another one!");
            }
            for (int i = 0; i < sArr.length; i++) {
                for (int i2 = 0; i2 < sArr.length; i2++) {
                    this.f9940A[i][i2] = sArr[i][i2];
                }
                for (int length = sArr.length; length < sArr.length * 2; length++) {
                    this.f9940A[i][length] = 0;
                }
                this.f9940A[i][this.f9940A.length + i] = 1;
            }
            computeZerosUnder(true);
            for (int i3 = 0; i3 < this.f9940A.length; i3++) {
                short invElem = GF2Field.invElem(this.f9940A[i3][i3]);
                for (int i4 = i3; i4 < this.f9940A.length * 2; i4++) {
                    this.f9940A[i3][i4] = GF2Field.multElem(this.f9940A[i3][i4], invElem);
                }
            }
            computeZerosAbove();
            short[][] sArr2 = (short[][]) Array.newInstance((Class<?>) short.class, this.f9940A.length, this.f9940A.length);
            for (int i5 = 0; i5 < this.f9940A.length; i5++) {
                for (int length2 = this.f9940A.length; length2 < this.f9940A.length * 2; length2++) {
                    sArr2[i5][length2 - this.f9940A.length] = this.f9940A[i5][length2];
                }
            }
            return sArr2;
        } catch (RuntimeException unused) {
            return (short[][]) null;
        }
    }

    public short[][] multMatrix(short s, short[][] sArr) {
        short[][] sArr2 = (short[][]) Array.newInstance((Class<?>) short.class, sArr.length, sArr[0].length);
        for (int i = 0; i < sArr.length; i++) {
            for (int i2 = 0; i2 < sArr[0].length; i2++) {
                sArr2[i][i2] = GF2Field.multElem(s, sArr[i][i2]);
            }
        }
        return sArr2;
    }

    public short[] multVect(short s, short[] sArr) {
        short[] sArr2 = new short[sArr.length];
        for (int i = 0; i < sArr2.length; i++) {
            sArr2[i] = GF2Field.multElem(s, sArr[i]);
        }
        return sArr2;
    }

    public short[][] multVects(short[] sArr, short[] sArr2) {
        if (sArr.length != sArr2.length) {
            throw new RuntimeException("Multiplication is not possible!");
        }
        short[][] sArr3 = (short[][]) Array.newInstance((Class<?>) short.class, sArr.length, sArr2.length);
        for (int i = 0; i < sArr.length; i++) {
            for (int i2 = 0; i2 < sArr2.length; i2++) {
                sArr3[i][i2] = GF2Field.multElem(sArr[i], sArr2[i2]);
            }
        }
        return sArr3;
    }

    public short[] multiplyMatrix(short[][] sArr, short[] sArr2) throws RuntimeException {
        if (sArr[0].length != sArr2.length) {
            throw new RuntimeException("Multiplication is not possible!");
        }
        short[] sArr3 = new short[sArr.length];
        for (int i = 0; i < sArr.length; i++) {
            for (int i2 = 0; i2 < sArr2.length; i2++) {
                sArr3[i] = GF2Field.addElem(sArr3[i], GF2Field.multElem(sArr[i][i2], sArr2[i2]));
            }
        }
        return sArr3;
    }

    public short[][] multiplyMatrix(short[][] sArr, short[][] sArr2) throws RuntimeException {
        if (sArr[0].length != sArr2.length) {
            throw new RuntimeException("Multiplication is not possible!");
        }
        this.f9940A = (short[][]) Array.newInstance((Class<?>) short.class, sArr.length, sArr2[0].length);
        for (int i = 0; i < sArr.length; i++) {
            for (int i2 = 0; i2 < sArr2.length; i2++) {
                for (int i3 = 0; i3 < sArr2[0].length; i3++) {
                    short multElem = GF2Field.multElem(sArr[i][i2], sArr2[i2][i3]);
                    short[][] sArr3 = this.f9940A;
                    sArr3[i][i3] = GF2Field.addElem(sArr3[i][i3], multElem);
                }
            }
        }
        return this.f9940A;
    }

    public short[] solveEquation(short[][] sArr, short[] sArr2) {
        if (sArr.length != sArr2.length) {
            return null;
        }
        try {
            this.f9940A = (short[][]) Array.newInstance((Class<?>) short.class, sArr.length, sArr.length + 1);
            this.f9941x = new short[sArr.length];
            for (int i = 0; i < sArr.length; i++) {
                for (int i2 = 0; i2 < sArr[0].length; i2++) {
                    this.f9940A[i][i2] = sArr[i][i2];
                }
            }
            for (int i3 = 0; i3 < sArr2.length; i3++) {
                this.f9940A[i3][sArr2.length] = GF2Field.addElem(sArr2[i3], this.f9940A[i3][sArr2.length]);
            }
            computeZerosUnder(false);
            substitute();
            return this.f9941x;
        } catch (RuntimeException unused) {
            return null;
        }
    }
}
