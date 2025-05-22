package org.bouncycastle.math.raw;

import java.math.BigInteger;
import org.bouncycastle.util.Pack;

/* loaded from: classes9.dex */
public abstract class Nat192 {

    /* renamed from: M */
    private static final long f9848M = 4294967295L;

    public static int add(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = (iArr[0] & 4294967295L) + (iArr2[0] & 4294967295L) + 0;
        iArr3[0] = (int) j;
        long j2 = (j >>> 32) + (iArr[1] & 4294967295L) + (iArr2[1] & 4294967295L);
        iArr3[1] = (int) j2;
        long j3 = (j2 >>> 32) + (iArr[2] & 4294967295L) + (iArr2[2] & 4294967295L);
        iArr3[2] = (int) j3;
        long j4 = (j3 >>> 32) + (iArr[3] & 4294967295L) + (iArr2[3] & 4294967295L);
        iArr3[3] = (int) j4;
        long j5 = (j4 >>> 32) + (iArr[4] & 4294967295L) + (iArr2[4] & 4294967295L);
        iArr3[4] = (int) j5;
        long j6 = (j5 >>> 32) + (iArr[5] & 4294967295L) + (iArr2[5] & 4294967295L);
        iArr3[5] = (int) j6;
        return (int) (j6 >>> 32);
    }

    public static int addBothTo(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = (iArr[0] & 4294967295L) + (iArr2[0] & 4294967295L) + (iArr3[0] & 4294967295L) + 0;
        iArr3[0] = (int) j;
        long j2 = (j >>> 32) + (iArr[1] & 4294967295L) + (iArr2[1] & 4294967295L) + (iArr3[1] & 4294967295L);
        iArr3[1] = (int) j2;
        long j3 = (j2 >>> 32) + (iArr[2] & 4294967295L) + (iArr2[2] & 4294967295L) + (iArr3[2] & 4294967295L);
        iArr3[2] = (int) j3;
        long j4 = (j3 >>> 32) + (iArr[3] & 4294967295L) + (iArr2[3] & 4294967295L) + (iArr3[3] & 4294967295L);
        iArr3[3] = (int) j4;
        long j5 = (j4 >>> 32) + (iArr[4] & 4294967295L) + (iArr2[4] & 4294967295L) + (iArr3[4] & 4294967295L);
        iArr3[4] = (int) j5;
        long j6 = (j5 >>> 32) + (iArr[5] & 4294967295L) + (iArr2[5] & 4294967295L) + (iArr3[5] & 4294967295L);
        iArr3[5] = (int) j6;
        return (int) (j6 >>> 32);
    }

    public static int addTo(int[] iArr, int i, int[] iArr2, int i2, int i3) {
        long j = (i3 & 4294967295L) + (iArr[i + 0] & 4294967295L) + (iArr2[r13] & 4294967295L);
        iArr2[i2 + 0] = (int) j;
        long j2 = (j >>> 32) + (iArr[i + 1] & 4294967295L) + (iArr2[r6] & 4294967295L);
        iArr2[i2 + 1] = (int) j2;
        long j3 = (j2 >>> 32) + (iArr[i + 2] & 4294967295L) + (iArr2[r6] & 4294967295L);
        iArr2[i2 + 2] = (int) j3;
        long j4 = (j3 >>> 32) + (iArr[i + 3] & 4294967295L) + (iArr2[r6] & 4294967295L);
        iArr2[i2 + 3] = (int) j4;
        long j5 = (j4 >>> 32) + (iArr[i + 4] & 4294967295L) + (iArr2[r6] & 4294967295L);
        iArr2[i2 + 4] = (int) j5;
        long j6 = (j5 >>> 32) + (iArr[i + 5] & 4294967295L) + (4294967295L & iArr2[r12]);
        iArr2[i2 + 5] = (int) j6;
        return (int) (j6 >>> 32);
    }

    public static int addTo(int[] iArr, int[] iArr2) {
        long j = (iArr[0] & 4294967295L) + (iArr2[0] & 4294967295L) + 0;
        iArr2[0] = (int) j;
        long j2 = (j >>> 32) + (iArr[1] & 4294967295L) + (iArr2[1] & 4294967295L);
        iArr2[1] = (int) j2;
        long j3 = (j2 >>> 32) + (iArr[2] & 4294967295L) + (iArr2[2] & 4294967295L);
        iArr2[2] = (int) j3;
        long j4 = (j3 >>> 32) + (iArr[3] & 4294967295L) + (iArr2[3] & 4294967295L);
        iArr2[3] = (int) j4;
        long j5 = (j4 >>> 32) + (iArr[4] & 4294967295L) + (iArr2[4] & 4294967295L);
        iArr2[4] = (int) j5;
        long j6 = (j5 >>> 32) + (iArr[5] & 4294967295L) + (4294967295L & iArr2[5]);
        iArr2[5] = (int) j6;
        return (int) (j6 >>> 32);
    }

    public static int addToEachOther(int[] iArr, int i, int[] iArr2, int i2) {
        long j = (iArr[r0] & 4294967295L) + (iArr2[r5] & 4294967295L) + 0;
        int i3 = (int) j;
        iArr[i + 0] = i3;
        iArr2[i2 + 0] = i3;
        long j2 = (j >>> 32) + (iArr[r5] & 4294967295L) + (iArr2[r8] & 4294967295L);
        int i4 = (int) j2;
        iArr[i + 1] = i4;
        iArr2[i2 + 1] = i4;
        long j3 = (j2 >>> 32) + (iArr[r5] & 4294967295L) + (iArr2[r8] & 4294967295L);
        int i5 = (int) j3;
        iArr[i + 2] = i5;
        iArr2[i2 + 2] = i5;
        long j4 = (j3 >>> 32) + (iArr[r5] & 4294967295L) + (iArr2[r8] & 4294967295L);
        int i6 = (int) j4;
        iArr[i + 3] = i6;
        iArr2[i2 + 3] = i6;
        long j5 = (j4 >>> 32) + (iArr[r5] & 4294967295L) + (iArr2[r8] & 4294967295L);
        int i7 = (int) j5;
        iArr[i + 4] = i7;
        iArr2[i2 + 4] = i7;
        long j6 = (j5 >>> 32) + (iArr[r12] & 4294967295L) + (4294967295L & iArr2[r14]);
        int i8 = (int) j6;
        iArr[i + 5] = i8;
        iArr2[i2 + 5] = i8;
        return (int) (j6 >>> 32);
    }

    public static void copy(int[] iArr, int i, int[] iArr2, int i2) {
        iArr2[i2 + 0] = iArr[i + 0];
        iArr2[i2 + 1] = iArr[i + 1];
        iArr2[i2 + 2] = iArr[i + 2];
        iArr2[i2 + 3] = iArr[i + 3];
        iArr2[i2 + 4] = iArr[i + 4];
        iArr2[i2 + 5] = iArr[i + 5];
    }

    public static void copy(int[] iArr, int[] iArr2) {
        iArr2[0] = iArr[0];
        iArr2[1] = iArr[1];
        iArr2[2] = iArr[2];
        iArr2[3] = iArr[3];
        iArr2[4] = iArr[4];
        iArr2[5] = iArr[5];
    }

    public static void copy64(long[] jArr, int i, long[] jArr2, int i2) {
        jArr2[i2 + 0] = jArr[i + 0];
        jArr2[i2 + 1] = jArr[i + 1];
        jArr2[i2 + 2] = jArr[i + 2];
    }

    public static void copy64(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0];
        jArr2[1] = jArr[1];
        jArr2[2] = jArr[2];
    }

    public static int[] create() {
        return new int[6];
    }

    public static long[] create64() {
        return new long[3];
    }

    public static int[] createExt() {
        return new int[12];
    }

    public static long[] createExt64() {
        return new long[6];
    }

    public static boolean diff(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        boolean gte = gte(iArr, i, iArr2, i2);
        if (gte) {
            sub(iArr, i, iArr2, i2, iArr3, i3);
        } else {
            sub(iArr2, i2, iArr, i, iArr3, i3);
        }
        return gte;
    }

    /* renamed from: eq */
    public static boolean m4117eq(int[] iArr, int[] iArr2) {
        for (int i = 5; i >= 0; i--) {
            if (iArr[i] != iArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean eq64(long[] jArr, long[] jArr2) {
        for (int i = 2; i >= 0; i--) {
            if (jArr[i] != jArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 192) {
            throw new IllegalArgumentException();
        }
        int[] create = create();
        int i = 0;
        while (bigInteger.signum() != 0) {
            create[i] = bigInteger.intValue();
            bigInteger = bigInteger.shiftRight(32);
            i++;
        }
        return create;
    }

    public static long[] fromBigInteger64(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 192) {
            throw new IllegalArgumentException();
        }
        long[] create64 = create64();
        int i = 0;
        while (bigInteger.signum() != 0) {
            create64[i] = bigInteger.longValue();
            bigInteger = bigInteger.shiftRight(64);
            i++;
        }
        return create64;
    }

    public static int getBit(int[] iArr, int i) {
        int i2;
        if (i == 0) {
            i2 = iArr[0];
        } else {
            int i3 = i >> 5;
            if (i3 < 0 || i3 >= 6) {
                return 0;
            }
            i2 = iArr[i3] >>> (i & 31);
        }
        return i2 & 1;
    }

    public static boolean gte(int[] iArr, int i, int[] iArr2, int i2) {
        for (int i3 = 5; i3 >= 0; i3--) {
            int i4 = iArr[i + i3] ^ Integer.MIN_VALUE;
            int i5 = Integer.MIN_VALUE ^ iArr2[i2 + i3];
            if (i4 < i5) {
                return false;
            }
            if (i4 > i5) {
                return true;
            }
        }
        return true;
    }

    public static boolean gte(int[] iArr, int[] iArr2) {
        for (int i = 5; i >= 0; i--) {
            int i2 = iArr[i] ^ Integer.MIN_VALUE;
            int i3 = Integer.MIN_VALUE ^ iArr2[i];
            if (i2 < i3) {
                return false;
            }
            if (i2 > i3) {
                return true;
            }
        }
        return true;
    }

    public static boolean isOne(int[] iArr) {
        if (iArr[0] != 1) {
            return false;
        }
        for (int i = 1; i < 6; i++) {
            if (iArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isOne64(long[] jArr) {
        if (jArr[0] != 1) {
            return false;
        }
        for (int i = 1; i < 3; i++) {
            if (jArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero(int[] iArr) {
        for (int i = 0; i < 6; i++) {
            if (iArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero64(long[] jArr) {
        for (int i = 0; i < 3; i++) {
            if (jArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void mul(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        long j = iArr2[i2 + 0] & 4294967295L;
        long j2 = iArr2[i2 + 1] & 4294967295L;
        long j3 = iArr2[i2 + 2] & 4294967295L;
        long j4 = iArr2[i2 + 3] & 4294967295L;
        long j5 = iArr2[i2 + 4] & 4294967295L;
        long j6 = iArr2[i2 + 5] & 4294967295L;
        long j7 = iArr[i + 0] & 4294967295L;
        long j8 = (j7 * j) + 0;
        iArr3[i3 + 0] = (int) j8;
        long j9 = (j8 >>> 32) + (j7 * j2);
        iArr3[i3 + 1] = (int) j9;
        long j10 = (j9 >>> 32) + (j7 * j3);
        iArr3[i3 + 2] = (int) j10;
        long j11 = (j10 >>> 32) + (j7 * j4);
        iArr3[i3 + 3] = (int) j11;
        long j12 = (j11 >>> 32) + (j7 * j5);
        iArr3[i3 + 4] = (int) j12;
        long j13 = (j12 >>> 32) + (j7 * j6);
        iArr3[i3 + 5] = (int) j13;
        iArr3[i3 + 6] = (int) (j13 >>> 32);
        int i4 = 1;
        int i5 = i3;
        int i6 = 1;
        while (i6 < 6) {
            i5 += i4;
            long j14 = iArr[i + i6] & 4294967295L;
            long j15 = (j14 * j) + (iArr3[r24] & 4294967295L) + 0;
            iArr3[i5 + 0] = (int) j15;
            long j16 = j6;
            long j17 = (j15 >>> 32) + (j14 * j2) + (iArr3[r15] & 4294967295L);
            iArr3[i5 + 1] = (int) j17;
            long j18 = (j17 >>> 32) + (j14 * j3) + (iArr3[r15] & 4294967295L);
            iArr3[i5 + 2] = (int) j18;
            long j19 = (j18 >>> 32) + (j14 * j4) + (iArr3[r7] & 4294967295L);
            iArr3[i5 + 3] = (int) j19;
            long j20 = (j19 >>> 32) + (j14 * j5) + (iArr3[r7] & 4294967295L);
            iArr3[i5 + 4] = (int) j20;
            long j21 = (j20 >>> 32) + (j14 * j16) + (iArr3[r7] & 4294967295L);
            iArr3[i5 + 5] = (int) j21;
            iArr3[i5 + 6] = (int) (j21 >>> 32);
            i6++;
            j3 = j3;
            j6 = j16;
            i4 = 1;
        }
    }

    public static void mul(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = iArr2[0] & 4294967295L;
        long j2 = iArr2[1] & 4294967295L;
        long j3 = iArr2[2] & 4294967295L;
        long j4 = iArr2[3] & 4294967295L;
        long j5 = iArr2[4] & 4294967295L;
        long j6 = iArr2[5] & 4294967295L;
        long j7 = iArr[0] & 4294967295L;
        long j8 = (j7 * j) + 0;
        iArr3[0] = (int) j8;
        long j9 = (j8 >>> 32) + (j7 * j2);
        iArr3[1] = (int) j9;
        long j10 = (j9 >>> 32) + (j7 * j3);
        iArr3[2] = (int) j10;
        long j11 = (j10 >>> 32) + (j7 * j4);
        iArr3[3] = (int) j11;
        long j12 = (j11 >>> 32) + (j7 * j5);
        iArr3[4] = (int) j12;
        long j13 = (j12 >>> 32) + (j7 * j6);
        iArr3[5] = (int) j13;
        iArr3[6] = (int) (j13 >>> 32);
        int i = 1;
        for (int i2 = 6; i < i2; i2 = 6) {
            long j14 = iArr[i] & 4294967295L;
            long j15 = (j14 * j) + (iArr3[r5] & 4294967295L) + 0;
            iArr3[i + 0] = (int) j15;
            int i3 = i + 1;
            long j16 = j2;
            long j17 = (j15 >>> 32) + (j14 * j2) + (iArr3[i3] & 4294967295L);
            iArr3[i3] = (int) j17;
            int i4 = i;
            long j18 = (j17 >>> 32) + (j14 * j3) + (iArr3[r6] & 4294967295L);
            iArr3[i + 2] = (int) j18;
            long j19 = (j18 >>> 32) + (j14 * j4) + (iArr3[r5] & 4294967295L);
            iArr3[i4 + 3] = (int) j19;
            long j20 = (j19 >>> 32) + (j14 * j5) + (iArr3[r5] & 4294967295L);
            iArr3[i4 + 4] = (int) j20;
            long j21 = (j20 >>> 32) + (j14 * j6) + (iArr3[r3] & 4294967295L);
            iArr3[i4 + 5] = (int) j21;
            iArr3[i4 + 6] = (int) (j21 >>> 32);
            i = i3;
            j = j;
            j2 = j16;
        }
    }

    public static long mul33Add(int i, int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
        long j = i & 4294967295L;
        long j2 = iArr[i2 + 0] & 4294967295L;
        long j3 = (j * j2) + (iArr2[i3 + 0] & 4294967295L) + 0;
        iArr3[i4 + 0] = (int) j3;
        long j4 = iArr[i2 + 1] & 4294967295L;
        long j5 = (j3 >>> 32) + (j * j4) + j2 + (iArr2[i3 + 1] & 4294967295L);
        iArr3[i4 + 1] = (int) j5;
        long j6 = j5 >>> 32;
        long j7 = iArr[i2 + 2] & 4294967295L;
        long j8 = j6 + (j * j7) + j4 + (iArr2[i3 + 2] & 4294967295L);
        iArr3[i4 + 2] = (int) j8;
        long j9 = iArr[i2 + 3] & 4294967295L;
        long j10 = (j8 >>> 32) + (j * j9) + j7 + (iArr2[i3 + 3] & 4294967295L);
        iArr3[i4 + 3] = (int) j10;
        long j11 = iArr[i2 + 4] & 4294967295L;
        long j12 = (j10 >>> 32) + (j * j11) + j9 + (iArr2[i3 + 4] & 4294967295L);
        iArr3[i4 + 4] = (int) j12;
        long j13 = iArr[i2 + 5] & 4294967295L;
        long j14 = (j12 >>> 32) + (j * j13) + j11 + (4294967295L & iArr2[i3 + 5]);
        iArr3[i4 + 5] = (int) j14;
        return (j14 >>> 32) + j13;
    }

    public static int mul33DWordAdd(int i, long j, int[] iArr, int i2) {
        long j2 = i & 4294967295L;
        long j3 = j & 4294967295L;
        long j4 = (j2 * j3) + (iArr[r1] & 4294967295L) + 0;
        iArr[i2 + 0] = (int) j4;
        long j5 = j >>> 32;
        long j6 = (j2 * j5) + j3;
        long j7 = (j4 >>> 32) + j6 + (iArr[r7] & 4294967295L);
        iArr[i2 + 1] = (int) j7;
        long j8 = (j7 >>> 32) + j5 + (iArr[r7] & 4294967295L);
        iArr[i2 + 2] = (int) j8;
        long j9 = (j8 >>> 32) + (4294967295L & iArr[r7]);
        iArr[i2 + 3] = (int) j9;
        if ((j9 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(6, iArr, i2, 4);
    }

    public static int mul33WordAdd(int i, int i2, int[] iArr, int i3) {
        long j = i & 4294967295L;
        long j2 = i2 & 4294967295L;
        long j3 = (j * j2) + (iArr[r4] & 4294967295L) + 0;
        iArr[i3 + 0] = (int) j3;
        long j4 = (j3 >>> 32) + j2 + (iArr[r7] & 4294967295L);
        iArr[i3 + 1] = (int) j4;
        long j5 = j4 >>> 32;
        long j6 = j5 + (iArr[r0] & 4294967295L);
        iArr[i3 + 2] = (int) j6;
        if ((j6 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(6, iArr, i3, 3);
    }

    public static int mulAddTo(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        long j = iArr2[i2 + 0] & 4294967295L;
        long j2 = iArr2[i2 + 1] & 4294967295L;
        long j3 = iArr2[i2 + 2] & 4294967295L;
        long j4 = iArr2[i2 + 3] & 4294967295L;
        long j5 = iArr2[i2 + 4] & 4294967295L;
        long j6 = iArr2[i2 + 5] & 4294967295L;
        int i4 = i3;
        int i5 = 0;
        long j7 = 0;
        while (i5 < 6) {
            long j8 = iArr[i + i5] & 4294967295L;
            long j9 = j6;
            long j10 = (j8 * j) + (iArr3[r16] & 4294967295L) + 0;
            iArr3[i4 + 0] = (int) j10;
            int i6 = i4 + 1;
            long j11 = j2;
            long j12 = (j10 >>> 32) + (j8 * j2) + (iArr3[i6] & 4294967295L);
            iArr3[i6] = (int) j12;
            long j13 = (j12 >>> 32) + (j8 * j3) + (iArr3[r16] & 4294967295L);
            iArr3[i4 + 2] = (int) j13;
            long j14 = (j13 >>> 32) + (j8 * j4) + (iArr3[r1] & 4294967295L);
            iArr3[i4 + 3] = (int) j14;
            long j15 = (j14 >>> 32) + (j8 * j5) + (iArr3[r1] & 4294967295L);
            iArr3[i4 + 4] = (int) j15;
            long j16 = (j15 >>> 32) + (j8 * j9) + (iArr3[r1] & 4294967295L);
            iArr3[i4 + 5] = (int) j16;
            long j17 = (j16 >>> 32) + (iArr3[r15] & 4294967295L) + j7;
            iArr3[i4 + 6] = (int) j17;
            j7 = j17 >>> 32;
            i5++;
            j6 = j9;
            j = j;
            i4 = i6;
            j2 = j11;
            j3 = j3;
        }
        return (int) j7;
    }

    public static int mulAddTo(int[] iArr, int[] iArr2, int[] iArr3) {
        int i = 0;
        long j = 4294967295L;
        long j2 = iArr2[0] & 4294967295L;
        long j3 = iArr2[1] & 4294967295L;
        long j4 = iArr2[2] & 4294967295L;
        long j5 = iArr2[3] & 4294967295L;
        long j6 = iArr2[4] & 4294967295L;
        long j7 = iArr2[5] & 4294967295L;
        long j8 = 0;
        while (i < 6) {
            long j9 = j7;
            long j10 = iArr[i] & j;
            long j11 = j2;
            long j12 = (j10 * j2) + (iArr3[r23] & j) + 0;
            iArr3[i + 0] = (int) j12;
            int i2 = i + 1;
            long j13 = (j12 >>> 32) + (j10 * j3) + (iArr3[i2] & 4294967295L);
            iArr3[i2] = (int) j13;
            long j14 = (j13 >>> 32) + (j10 * j4) + (iArr3[r23] & 4294967295L);
            iArr3[i + 2] = (int) j14;
            long j15 = (j14 >>> 32) + (j10 * j5) + (iArr3[r6] & 4294967295L);
            iArr3[i + 3] = (int) j15;
            long j16 = (j15 >>> 32) + (j10 * j6) + (iArr3[r6] & 4294967295L);
            iArr3[i + 4] = (int) j16;
            long j17 = (j16 >>> 32) + (j10 * j9) + (iArr3[r4] & 4294967295L);
            iArr3[i + 5] = (int) j17;
            long j18 = (j17 >>> 32) + (iArr3[r0] & 4294967295L) + j8;
            iArr3[i + 6] = (int) j18;
            j8 = j18 >>> 32;
            i = i2;
            j = 4294967295L;
            j7 = j9;
            j2 = j11;
            j3 = j3;
            j4 = j4;
        }
        return (int) j8;
    }

    public static int mulWord(int i, int[] iArr, int[] iArr2, int i2) {
        long j = i & 4294967295L;
        long j2 = 0;
        int i3 = 0;
        do {
            long j3 = j2 + ((iArr[i3] & 4294967295L) * j);
            iArr2[i2 + i3] = (int) j3;
            j2 = j3 >>> 32;
            i3++;
        } while (i3 < 6);
        return (int) j2;
    }

    public static int mulWordAddExt(int i, int[] iArr, int i2, int[] iArr2, int i3) {
        long j = i & 4294967295L;
        long j2 = ((iArr[i2 + 0] & 4294967295L) * j) + (iArr2[r11] & 4294967295L) + 0;
        iArr2[i3 + 0] = (int) j2;
        long j3 = (j2 >>> 32) + ((iArr[i2 + 1] & 4294967295L) * j) + (iArr2[r8] & 4294967295L);
        iArr2[i3 + 1] = (int) j3;
        long j4 = (j3 >>> 32) + ((iArr[i2 + 2] & 4294967295L) * j) + (iArr2[r8] & 4294967295L);
        iArr2[i3 + 2] = (int) j4;
        long j5 = (j4 >>> 32) + ((iArr[i2 + 3] & 4294967295L) * j) + (iArr2[r8] & 4294967295L);
        iArr2[i3 + 3] = (int) j5;
        long j6 = (j5 >>> 32) + ((iArr[i2 + 4] & 4294967295L) * j) + (iArr2[r8] & 4294967295L);
        iArr2[i3 + 4] = (int) j6;
        long j7 = (j6 >>> 32) + (j * (iArr[i2 + 5] & 4294967295L)) + (iArr2[r15] & 4294967295L);
        iArr2[i3 + 5] = (int) j7;
        return (int) (j7 >>> 32);
    }

    public static int mulWordDwordAdd(int i, long j, int[] iArr, int i2) {
        long j2 = i & 4294967295L;
        long j3 = ((j & 4294967295L) * j2) + (iArr[r10] & 4294967295L) + 0;
        iArr[i2 + 0] = (int) j3;
        long j4 = j2 * (j >>> 32);
        long j5 = (j3 >>> 32) + j4 + (iArr[r11] & 4294967295L);
        iArr[i2 + 1] = (int) j5;
        long j6 = (j5 >>> 32) + (iArr[r0] & 4294967295L);
        iArr[i2 + 2] = (int) j6;
        if ((j6 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(6, iArr, i2, 3);
    }

    public static void square(int[] iArr, int i, int[] iArr2, int i2) {
        long j = iArr[i + 0] & 4294967295L;
        int i3 = 12;
        int i4 = 0;
        int i5 = 5;
        while (true) {
            int i6 = i5 - 1;
            long j2 = iArr[i + i5] & 4294967295L;
            long j3 = j2 * j2;
            int i7 = i3 - 1;
            iArr2[i2 + i7] = (i4 << 31) | ((int) (j3 >>> 33));
            i3 = i7 - 1;
            iArr2[i2 + i3] = (int) (j3 >>> 1);
            int i8 = (int) j3;
            if (i6 <= 0) {
                long j4 = j * j;
                iArr2[i2 + 0] = (int) j4;
                long j5 = iArr[i + 1] & 4294967295L;
                long j6 = (((i8 << 31) & 4294967295L) | (j4 >>> 33)) + (j5 * j);
                int i9 = (int) j6;
                iArr2[i2 + 1] = (i9 << 1) | (((int) (j4 >>> 32)) & 1);
                int i10 = i9 >>> 31;
                long j7 = (iArr2[r9] & 4294967295L) + (j6 >>> 32);
                long j8 = iArr[i + 2] & 4294967295L;
                long j9 = j7 + (j8 * j);
                int i11 = (int) j9;
                iArr2[i2 + 2] = (i11 << 1) | i10;
                int i12 = i11 >>> 31;
                long j10 = (iArr2[r15] & 4294967295L) + (j9 >>> 32) + (j8 * j5);
                long j11 = (iArr2[r7] & 4294967295L) + (j10 >>> 32);
                long j12 = iArr[i + 3] & 4294967295L;
                long j13 = (iArr2[r6] & 4294967295L) + (j11 >>> 32);
                long j14 = (iArr2[r24] & 4294967295L) + (j13 >>> 32);
                long j15 = (j10 & 4294967295L) + (j12 * j);
                int i13 = (int) j15;
                iArr2[i2 + 3] = (i13 << 1) | i12;
                long j16 = (j11 & 4294967295L) + (j15 >>> 32) + (j12 * j5);
                long j17 = (j13 & 4294967295L) + (j16 >>> 32) + (j12 * j8);
                long j18 = j14 + (j17 >>> 32);
                long j19 = j17 & 4294967295L;
                long j20 = iArr[i + 4] & 4294967295L;
                long j21 = (iArr2[r3] & 4294967295L) + (j18 >>> 32);
                long j22 = j18 & 4294967295L;
                long j23 = (j16 & 4294967295L) + (j20 * j);
                int i14 = (int) j23;
                iArr2[i2 + 4] = (i13 >>> 31) | (i14 << 1);
                int i15 = i14 >>> 31;
                long j24 = j19 + (j23 >>> 32) + (j20 * j5);
                long j25 = j22 + (j24 >>> 32) + (j20 * j8);
                long j26 = (j21 & 4294967295L) + (j25 >>> 32) + (j20 * j12);
                long j27 = (iArr2[r22] & 4294967295L) + (j21 >>> 32) + (j26 >>> 32);
                long j28 = j26 & 4294967295L;
                long j29 = iArr[i + 5] & 4294967295L;
                long j30 = (iArr2[r5] & 4294967295L) + (j27 >>> 32);
                long j31 = (iArr2[r23] & 4294967295L) + (j30 >>> 32);
                long j32 = (j24 & 4294967295L) + (j * j29);
                int i16 = (int) j32;
                iArr2[i2 + 5] = (i16 << 1) | i15;
                long j33 = (j25 & 4294967295L) + (j32 >>> 32) + (j29 * j5);
                long j34 = j28 + (j33 >>> 32) + (j29 * j8);
                long j35 = (j27 & 4294967295L) + (j34 >>> 32) + (j29 * j12);
                long j36 = (j30 & 4294967295L) + (j35 >>> 32) + (j29 * j20);
                long j37 = j31 + (j36 >>> 32);
                int i17 = (int) j33;
                iArr2[i2 + 6] = (i16 >>> 31) | (i17 << 1);
                int i18 = (int) j34;
                iArr2[i2 + 7] = (i17 >>> 31) | (i18 << 1);
                int i19 = i18 >>> 31;
                int i20 = (int) j35;
                iArr2[i2 + 8] = i19 | (i20 << 1);
                int i21 = i20 >>> 31;
                int i22 = (int) j36;
                iArr2[i2 + 9] = i21 | (i22 << 1);
                int i23 = i22 >>> 31;
                int i24 = (int) j37;
                iArr2[i2 + 10] = i23 | (i24 << 1);
                int i25 = i24 >>> 31;
                int i26 = i2 + 11;
                iArr2[i26] = i25 | ((iArr2[i26] + ((int) (j37 >>> 32))) << 1);
                return;
            }
            i4 = i8;
            i5 = i6;
        }
    }

    public static void square(int[] iArr, int[] iArr2) {
        long j = iArr[0] & 4294967295L;
        int i = 0;
        int i2 = 12;
        int i3 = 5;
        while (true) {
            int i4 = i3 - 1;
            long j2 = iArr[i3] & 4294967295L;
            long j3 = j2 * j2;
            int i5 = i2 - 1;
            iArr2[i5] = (i << 31) | ((int) (j3 >>> 33));
            i2 = i5 - 1;
            iArr2[i2] = (int) (j3 >>> 1);
            int i6 = (int) j3;
            if (i4 <= 0) {
                long j4 = j * j;
                long j5 = (j4 >>> 33) | ((i6 << 31) & 4294967295L);
                iArr2[0] = (int) j4;
                long j6 = iArr[1] & 4294967295L;
                long j7 = j5 + (j6 * j);
                int i7 = (int) j7;
                iArr2[1] = (i7 << 1) | (((int) (j4 >>> 32)) & 1);
                long j8 = (iArr2[2] & 4294967295L) + (j7 >>> 32);
                long j9 = iArr[2] & 4294967295L;
                long j10 = j8 + (j9 * j);
                int i8 = (int) j10;
                iArr2[2] = (i8 << 1) | (i7 >>> 31);
                long j11 = (iArr2[3] & 4294967295L) + (j10 >>> 32) + (j9 * j6);
                long j12 = (iArr2[4] & 4294967295L) + (j11 >>> 32);
                long j13 = iArr[3] & 4294967295L;
                long j14 = (iArr2[5] & 4294967295L) + (j12 >>> 32);
                long j15 = (iArr2[6] & 4294967295L) + (j14 >>> 32);
                long j16 = (j11 & 4294967295L) + (j13 * j);
                int i9 = (int) j16;
                iArr2[3] = (i8 >>> 31) | (i9 << 1);
                int i10 = i9 >>> 31;
                long j17 = (j12 & 4294967295L) + (j16 >>> 32) + (j13 * j6);
                long j18 = (j14 & 4294967295L) + (j17 >>> 32) + (j13 * j9);
                long j19 = j15 + (j18 >>> 32);
                long j20 = iArr[4] & 4294967295L;
                long j21 = (iArr2[7] & 4294967295L) + (j19 >>> 32);
                long j22 = j19 & 4294967295L;
                long j23 = (iArr2[8] & 4294967295L) + (j21 >>> 32);
                long j24 = (j17 & 4294967295L) + (j20 * j);
                int i11 = (int) j24;
                iArr2[4] = (i11 << 1) | i10;
                long j25 = (j18 & 4294967295L) + (j24 >>> 32) + (j20 * j6);
                long j26 = j22 + (j25 >>> 32) + (j20 * j9);
                long j27 = (j21 & 4294967295L) + (j26 >>> 32) + (j20 * j13);
                long j28 = j23 + (j27 >>> 32);
                long j29 = j27 & 4294967295L;
                long j30 = iArr[5] & 4294967295L;
                long j31 = (iArr2[9] & 4294967295L) + (j28 >>> 32);
                long j32 = j28 & 4294967295L;
                long j33 = (iArr2[10] & 4294967295L) + (j31 >>> 32);
                long j34 = (j25 & 4294967295L) + (j30 * j);
                int i12 = (int) j34;
                iArr2[5] = (i11 >>> 31) | (i12 << 1);
                int i13 = i12 >>> 31;
                long j35 = (j26 & 4294967295L) + (j34 >>> 32) + (j6 * j30);
                long j36 = j29 + (j35 >>> 32) + (j30 * j9);
                long j37 = j32 + (j36 >>> 32) + (j30 * j13);
                long j38 = (j31 & 4294967295L) + (j37 >>> 32) + (j30 * j20);
                long j39 = j33 + (j38 >>> 32);
                int i14 = (int) j35;
                iArr2[6] = i13 | (i14 << 1);
                int i15 = (int) j36;
                iArr2[7] = (i14 >>> 31) | (i15 << 1);
                int i16 = i15 >>> 31;
                int i17 = (int) j37;
                iArr2[8] = i16 | (i17 << 1);
                int i18 = (int) j38;
                iArr2[9] = (i18 << 1) | (i17 >>> 31);
                int i19 = (int) j39;
                iArr2[10] = (i18 >>> 31) | (i19 << 1);
                iArr2[11] = (i19 >>> 31) | ((iArr2[11] + ((int) (j39 >>> 32))) << 1);
                return;
            }
            i3 = i4;
            i = i6;
        }
    }

    public static int sub(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        long j = ((iArr[i + 0] & 4294967295L) - (iArr2[i2 + 0] & 4294967295L)) + 0;
        iArr3[i3 + 0] = (int) j;
        long j2 = (j >> 32) + ((iArr[i + 1] & 4294967295L) - (iArr2[i2 + 1] & 4294967295L));
        iArr3[i3 + 1] = (int) j2;
        long j3 = (j2 >> 32) + ((iArr[i + 2] & 4294967295L) - (iArr2[i2 + 2] & 4294967295L));
        iArr3[i3 + 2] = (int) j3;
        long j4 = (j3 >> 32) + ((iArr[i + 3] & 4294967295L) - (iArr2[i2 + 3] & 4294967295L));
        iArr3[i3 + 3] = (int) j4;
        long j5 = (j4 >> 32) + ((iArr[i + 4] & 4294967295L) - (iArr2[i2 + 4] & 4294967295L));
        iArr3[i3 + 4] = (int) j5;
        long j6 = (j5 >> 32) + ((iArr[i + 5] & 4294967295L) - (iArr2[i2 + 5] & 4294967295L));
        iArr3[i3 + 5] = (int) j6;
        return (int) (j6 >> 32);
    }

    public static int sub(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = ((iArr[0] & 4294967295L) - (iArr2[0] & 4294967295L)) + 0;
        iArr3[0] = (int) j;
        long j2 = (j >> 32) + ((iArr[1] & 4294967295L) - (iArr2[1] & 4294967295L));
        iArr3[1] = (int) j2;
        long j3 = (j2 >> 32) + ((iArr[2] & 4294967295L) - (iArr2[2] & 4294967295L));
        iArr3[2] = (int) j3;
        long j4 = (j3 >> 32) + ((iArr[3] & 4294967295L) - (iArr2[3] & 4294967295L));
        iArr3[3] = (int) j4;
        long j5 = (j4 >> 32) + ((iArr[4] & 4294967295L) - (iArr2[4] & 4294967295L));
        iArr3[4] = (int) j5;
        long j6 = (j5 >> 32) + ((iArr[5] & 4294967295L) - (iArr2[5] & 4294967295L));
        iArr3[5] = (int) j6;
        return (int) (j6 >> 32);
    }

    public static int subBothFrom(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = (((iArr3[0] & 4294967295L) - (iArr[0] & 4294967295L)) - (iArr2[0] & 4294967295L)) + 0;
        iArr3[0] = (int) j;
        long j2 = (j >> 32) + (((iArr3[1] & 4294967295L) - (iArr[1] & 4294967295L)) - (iArr2[1] & 4294967295L));
        iArr3[1] = (int) j2;
        long j3 = (j2 >> 32) + (((iArr3[2] & 4294967295L) - (iArr[2] & 4294967295L)) - (iArr2[2] & 4294967295L));
        iArr3[2] = (int) j3;
        long j4 = (j3 >> 32) + (((iArr3[3] & 4294967295L) - (iArr[3] & 4294967295L)) - (iArr2[3] & 4294967295L));
        iArr3[3] = (int) j4;
        long j5 = (j4 >> 32) + (((iArr3[4] & 4294967295L) - (iArr[4] & 4294967295L)) - (iArr2[4] & 4294967295L));
        iArr3[4] = (int) j5;
        long j6 = (j5 >> 32) + (((iArr3[5] & 4294967295L) - (iArr[5] & 4294967295L)) - (iArr2[5] & 4294967295L));
        iArr3[5] = (int) j6;
        return (int) (j6 >> 32);
    }

    public static int subFrom(int[] iArr, int i, int[] iArr2, int i2) {
        long j = ((iArr2[r0] & 4294967295L) - (iArr[i + 0] & 4294967295L)) + 0;
        iArr2[i2 + 0] = (int) j;
        long j2 = (j >> 32) + ((iArr2[r5] & 4294967295L) - (iArr[i + 1] & 4294967295L));
        iArr2[i2 + 1] = (int) j2;
        long j3 = (j2 >> 32) + ((iArr2[r5] & 4294967295L) - (iArr[i + 2] & 4294967295L));
        iArr2[i2 + 2] = (int) j3;
        long j4 = (j3 >> 32) + ((iArr2[r5] & 4294967295L) - (iArr[i + 3] & 4294967295L));
        iArr2[i2 + 3] = (int) j4;
        long j5 = (j4 >> 32) + ((iArr2[r5] & 4294967295L) - (iArr[i + 4] & 4294967295L));
        iArr2[i2 + 4] = (int) j5;
        long j6 = (j5 >> 32) + ((iArr2[r13] & 4294967295L) - (iArr[i + 5] & 4294967295L));
        iArr2[i2 + 5] = (int) j6;
        return (int) (j6 >> 32);
    }

    public static int subFrom(int[] iArr, int[] iArr2) {
        long j = ((iArr2[0] & 4294967295L) - (iArr[0] & 4294967295L)) + 0;
        iArr2[0] = (int) j;
        long j2 = (j >> 32) + ((iArr2[1] & 4294967295L) - (iArr[1] & 4294967295L));
        iArr2[1] = (int) j2;
        long j3 = (j2 >> 32) + ((iArr2[2] & 4294967295L) - (iArr[2] & 4294967295L));
        iArr2[2] = (int) j3;
        long j4 = (j3 >> 32) + ((iArr2[3] & 4294967295L) - (iArr[3] & 4294967295L));
        iArr2[3] = (int) j4;
        long j5 = (j4 >> 32) + ((iArr2[4] & 4294967295L) - (iArr[4] & 4294967295L));
        iArr2[4] = (int) j5;
        long j6 = (j5 >> 32) + ((iArr2[5] & 4294967295L) - (4294967295L & iArr[5]));
        iArr2[5] = (int) j6;
        return (int) (j6 >> 32);
    }

    public static BigInteger toBigInteger(int[] iArr) {
        byte[] bArr = new byte[24];
        for (int i = 0; i < 6; i++) {
            int i2 = iArr[i];
            if (i2 != 0) {
                Pack.intToBigEndian(i2, bArr, (5 - i) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static BigInteger toBigInteger64(long[] jArr) {
        byte[] bArr = new byte[24];
        for (int i = 0; i < 3; i++) {
            long j = jArr[i];
            if (j != 0) {
                Pack.longToBigEndian(j, bArr, (2 - i) << 3);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static void zero(int[] iArr) {
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        iArr[4] = 0;
        iArr[5] = 0;
    }
}
