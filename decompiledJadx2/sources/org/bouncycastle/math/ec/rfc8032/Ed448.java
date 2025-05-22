package org.bouncycastle.math.ec.rfc8032;

import com.google.common.base.Ascii;
import java.security.SecureRandom;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.math.ec.rfc7748.X448;
import org.bouncycastle.math.ec.rfc7748.X448Field;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

/* loaded from: classes9.dex */
public abstract class Ed448 {
    private static final int C_d = -39081;
    private static final int L4_0 = 43969588;
    private static final int L4_1 = 30366549;
    private static final int L4_2 = 163752818;
    private static final int L4_3 = 258169998;
    private static final int L4_4 = 96434764;
    private static final int L4_5 = 227822194;
    private static final int L4_6 = 149865618;
    private static final int L4_7 = 550336261;
    private static final int L_0 = 78101261;
    private static final int L_1 = 141809365;
    private static final int L_2 = 175155932;
    private static final int L_3 = 64542499;
    private static final int L_4 = 158326419;
    private static final int L_5 = 191173276;
    private static final int L_6 = 104575268;
    private static final int L_7 = 137584065;
    private static final long M26L = 67108863;
    private static final long M28L = 268435455;
    private static final long M32L = 4294967295L;
    private static final int POINT_BYTES = 57;
    private static final int PRECOMP_BLOCKS = 5;
    private static final int PRECOMP_MASK = 15;
    private static final int PRECOMP_POINTS = 16;
    private static final int PRECOMP_SPACING = 18;
    private static final int PRECOMP_TEETH = 5;
    public static final int PREHASH_SIZE = 64;
    public static final int PUBLIC_KEY_SIZE = 57;
    private static final int SCALAR_BYTES = 57;
    private static final int SCALAR_INTS = 14;
    public static final int SECRET_KEY_SIZE = 57;
    public static final int SIGNATURE_SIZE = 114;
    private static final int WNAF_WIDTH_BASE = 7;
    private static final byte[] DOM4_PREFIX = Strings.toByteArray("SigEd448");

    /* renamed from: P */
    private static final int[] f9837P = {-1, -1, -1, -1, -1, -1, -1, -2, -1, -1, -1, -1, -1, -1};

    /* renamed from: L */
    private static final int[] f9836L = {-1420278541, 595116690, -1916432555, 560775794, -1361693040, -1001465015, 2093622249, -1, -1, -1, -1, -1, -1, LockFreeTaskQueueCore.MAX_CAPACITY_MASK};
    private static final int[] B_x = {118276190, 40534716, 9670182, 135141552, 85017403, 259173222, 68333082, 171784774, 174973732, 15824510, 73756743, 57518561, 94773951, 248652241, 107736333, 82941708};
    private static final int[] B_y = {36764180, 8885695, 130592152, 20104429, 163904957, 30304195, 121295871, 5901357, 125344798, 171541512, 175338348, 209069246, 3626697, 38307682, 24032956, 110359655};
    private static final Object precompLock = new Object();
    private static PointExt[] precompBaseTable = null;
    private static int[] precompBase = null;

    /* loaded from: classes9.dex */
    public static final class Algorithm {
        public static final int Ed448 = 0;
        public static final int Ed448ph = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class PointExt {

        /* renamed from: x */
        int[] f9838x;

        /* renamed from: y */
        int[] f9839y;

        /* renamed from: z */
        int[] f9840z;

        private PointExt() {
            this.f9838x = X448Field.create();
            this.f9839y = X448Field.create();
            this.f9840z = X448Field.create();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class PointPrecomp {

        /* renamed from: x */
        int[] f9841x;

        /* renamed from: y */
        int[] f9842y;

        private PointPrecomp() {
            this.f9841x = X448Field.create();
            this.f9842y = X448Field.create();
        }
    }

    private static byte[] calculateS(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int[] iArr = new int[28];
        decodeScalar(bArr, 0, iArr);
        int[] iArr2 = new int[14];
        decodeScalar(bArr2, 0, iArr2);
        int[] iArr3 = new int[14];
        decodeScalar(bArr3, 0, iArr3);
        Nat.mulAddTo(14, iArr2, iArr3, iArr);
        byte[] bArr4 = new byte[114];
        for (int i = 0; i < iArr.length; i++) {
            encode32(iArr[i], bArr4, i * 4);
        }
        return reduceScalar(bArr4);
    }

    private static boolean checkContextVar(byte[] bArr) {
        return bArr != null && bArr.length < 256;
    }

    private static int checkPoint(int[] iArr, int[] iArr2) {
        int[] create = X448Field.create();
        int[] create2 = X448Field.create();
        int[] create3 = X448Field.create();
        X448Field.sqr(iArr, create2);
        X448Field.sqr(iArr2, create3);
        X448Field.mul(create2, create3, create);
        X448Field.add(create2, create3, create2);
        X448Field.mul(create, 39081, create);
        X448Field.subOne(create);
        X448Field.add(create, create2, create);
        X448Field.normalize(create);
        return X448Field.isZero(create);
    }

    private static int checkPoint(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] create = X448Field.create();
        int[] create2 = X448Field.create();
        int[] create3 = X448Field.create();
        int[] create4 = X448Field.create();
        X448Field.sqr(iArr, create2);
        X448Field.sqr(iArr2, create3);
        X448Field.sqr(iArr3, create4);
        X448Field.mul(create2, create3, create);
        X448Field.add(create2, create3, create2);
        X448Field.mul(create2, create4, create2);
        X448Field.sqr(create4, create4);
        X448Field.mul(create, 39081, create);
        X448Field.sub(create, create4, create);
        X448Field.add(create, create2, create);
        X448Field.normalize(create);
        return X448Field.isZero(create);
    }

    private static boolean checkPointVar(byte[] bArr) {
        if ((bArr[56] & Byte.MAX_VALUE) != 0) {
            return false;
        }
        decode32(bArr, 0, new int[14], 0, 14);
        return !Nat.gte(14, r2, f9837P);
    }

    private static boolean checkScalarVar(byte[] bArr) {
        if (bArr[56] != 0) {
            return false;
        }
        decodeScalar(bArr, 0, new int[14]);
        return !Nat.gte(14, r2, f9836L);
    }

    public static Xof createPrehash() {
        return createXof();
    }

    private static Xof createXof() {
        return new SHAKEDigest(256);
    }

    private static int decode16(byte[] bArr, int i) {
        return ((bArr[i + 1] & 255) << 8) | (bArr[i] & 255);
    }

    private static int decode24(byte[] bArr, int i) {
        int i2 = bArr[i] & 255;
        int i3 = i + 1;
        return ((bArr[i3 + 1] & 255) << 16) | i2 | ((bArr[i3] & 255) << 8);
    }

    private static int decode32(byte[] bArr, int i) {
        int i2 = bArr[i] & 255;
        int i3 = i + 1;
        int i4 = i2 | ((bArr[i3] & 255) << 8);
        int i5 = i3 + 1;
        return (bArr[i5 + 1] << 24) | i4 | ((bArr[i5] & 255) << 16);
    }

    private static void decode32(byte[] bArr, int i, int[] iArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            iArr[i2 + i4] = decode32(bArr, (i4 * 4) + i);
        }
    }

    private static boolean decodePointVar(byte[] bArr, int i, boolean z, PointExt pointExt) {
        byte[] copyOfRange = Arrays.copyOfRange(bArr, i, i + 57);
        if (!checkPointVar(copyOfRange)) {
            return false;
        }
        int i2 = (copyOfRange[56] & 128) >>> 7;
        copyOfRange[56] = (byte) (copyOfRange[56] & Byte.MAX_VALUE);
        X448Field.decode(copyOfRange, 0, pointExt.f9839y);
        int[] create = X448Field.create();
        int[] create2 = X448Field.create();
        X448Field.sqr(pointExt.f9839y, create);
        X448Field.mul(create, 39081, create2);
        X448Field.negate(create, create);
        X448Field.addOne(create);
        X448Field.addOne(create2);
        if (!X448Field.sqrtRatioVar(create, create2, pointExt.f9838x)) {
            return false;
        }
        X448Field.normalize(pointExt.f9838x);
        if (i2 == 1 && X448Field.isZeroVar(pointExt.f9838x)) {
            return false;
        }
        if (z ^ (i2 != (pointExt.f9838x[0] & 1))) {
            X448Field.negate(pointExt.f9838x, pointExt.f9838x);
        }
        pointExtendXY(pointExt);
        return true;
    }

    private static void decodeScalar(byte[] bArr, int i, int[] iArr) {
        decode32(bArr, i, iArr, 0, 14);
    }

    private static void dom4(Xof xof, byte b, byte[] bArr) {
        byte[] bArr2 = DOM4_PREFIX;
        xof.update(bArr2, 0, bArr2.length);
        xof.update(b);
        xof.update((byte) bArr.length);
        xof.update(bArr, 0, bArr.length);
    }

    private static void encode24(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >>> 8);
        bArr[i3 + 1] = (byte) (i >>> 16);
    }

    private static void encode32(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >>> 8);
        int i4 = i3 + 1;
        bArr[i4] = (byte) (i >>> 16);
        bArr[i4 + 1] = (byte) (i >>> 24);
    }

    private static void encode56(long j, byte[] bArr, int i) {
        encode32((int) j, bArr, i);
        encode24((int) (j >>> 32), bArr, i + 4);
    }

    private static int encodePoint(PointExt pointExt, byte[] bArr, int i) {
        int[] create = X448Field.create();
        int[] create2 = X448Field.create();
        X448Field.inv(pointExt.f9840z, create2);
        X448Field.mul(pointExt.f9838x, create2, create);
        X448Field.mul(pointExt.f9839y, create2, create2);
        X448Field.normalize(create);
        X448Field.normalize(create2);
        int checkPoint = checkPoint(create, create2);
        X448Field.encode(create2, bArr, i);
        bArr[(i + 57) - 1] = (byte) ((create[0] & 1) << 7);
        return checkPoint;
    }

    public static void generatePrivateKey(SecureRandom secureRandom, byte[] bArr) {
        secureRandom.nextBytes(bArr);
    }

    public static void generatePublicKey(byte[] bArr, int i, byte[] bArr2, int i2) {
        Xof createXof = createXof();
        byte[] bArr3 = new byte[114];
        createXof.update(bArr, i, 57);
        createXof.doFinal(bArr3, 0, bArr3.length);
        byte[] bArr4 = new byte[57];
        pruneScalar(bArr3, 0, bArr4);
        scalarMultBaseEncoded(bArr4, bArr2, i2);
    }

    private static byte[] getWNAF(int[] iArr, int i) {
        int[] iArr2 = new int[28];
        int length = iArr2.length;
        int i2 = 0;
        int i3 = 14;
        int i4 = 0;
        while (true) {
            i3--;
            if (i3 < 0) {
                break;
            }
            int i5 = iArr[i3];
            int i6 = length - 1;
            iArr2[i6] = (i4 << 16) | (i5 >>> 16);
            length = i6 - 1;
            iArr2[length] = i5;
            i4 = i5;
        }
        byte[] bArr = new byte[447];
        int i7 = 1 << i;
        int i8 = i7 - 1;
        int i9 = i7 >>> 1;
        int i10 = 0;
        int i11 = 0;
        while (i2 < iArr2.length) {
            int i12 = iArr2[i2];
            while (i10 < 16) {
                int i13 = i12 >>> i10;
                if ((i13 & 1) == i11) {
                    i10++;
                } else {
                    int i14 = (i13 & i8) + i11;
                    int i15 = i14 & i9;
                    int i16 = i14 - (i15 << 1);
                    i11 = i15 >>> (i - 1);
                    bArr[(i2 << 4) + i10] = (byte) i16;
                    i10 += i;
                }
            }
            i2++;
            i10 -= 16;
        }
        return bArr;
    }

    private static int getWindow4(int[] iArr, int i) {
        return (iArr[i >>> 3] >>> ((i & 7) << 2)) & 15;
    }

    private static void implSign(Xof xof, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, byte b, byte[] bArr5, int i2, int i3, byte[] bArr6, int i4) {
        dom4(xof, b, bArr4);
        xof.update(bArr, 57, 57);
        xof.update(bArr5, i2, i3);
        xof.doFinal(bArr, 0, bArr.length);
        byte[] reduceScalar = reduceScalar(bArr);
        byte[] bArr7 = new byte[57];
        scalarMultBaseEncoded(reduceScalar, bArr7, 0);
        dom4(xof, b, bArr4);
        xof.update(bArr7, 0, 57);
        xof.update(bArr3, i, 57);
        xof.update(bArr5, i2, i3);
        xof.doFinal(bArr, 0, bArr.length);
        byte[] calculateS = calculateS(reduceScalar, reduceScalar(bArr), bArr2);
        System.arraycopy(bArr7, 0, bArr6, i4, 57);
        System.arraycopy(calculateS, 0, bArr6, i4 + 57, 57);
    }

    private static void implSign(byte[] bArr, int i, byte[] bArr2, byte b, byte[] bArr3, int i2, int i3, byte[] bArr4, int i4) {
        if (!checkContextVar(bArr2)) {
            throw new IllegalArgumentException("ctx");
        }
        Xof createXof = createXof();
        byte[] bArr5 = new byte[114];
        createXof.update(bArr, i, 57);
        createXof.doFinal(bArr5, 0, bArr5.length);
        byte[] bArr6 = new byte[57];
        pruneScalar(bArr5, 0, bArr6);
        byte[] bArr7 = new byte[57];
        scalarMultBaseEncoded(bArr6, bArr7, 0);
        implSign(createXof, bArr5, bArr6, bArr7, 0, bArr2, b, bArr3, i2, i3, bArr4, i4);
    }

    private static void implSign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte b, byte[] bArr4, int i3, int i4, byte[] bArr5, int i5) {
        if (!checkContextVar(bArr3)) {
            throw new IllegalArgumentException("ctx");
        }
        Xof createXof = createXof();
        byte[] bArr6 = new byte[114];
        createXof.update(bArr, i, 57);
        createXof.doFinal(bArr6, 0, bArr6.length);
        byte[] bArr7 = new byte[57];
        pruneScalar(bArr6, 0, bArr7);
        implSign(createXof, bArr6, bArr7, bArr2, i2, bArr3, b, bArr4, i3, i4, bArr5, i5);
    }

    private static boolean implVerify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte b, byte[] bArr4, int i3, int i4) {
        if (!checkContextVar(bArr3)) {
            throw new IllegalArgumentException("ctx");
        }
        int i5 = i + 57;
        byte[] copyOfRange = Arrays.copyOfRange(bArr, i, i5);
        byte[] copyOfRange2 = Arrays.copyOfRange(bArr, i5, i + 114);
        if (!checkPointVar(copyOfRange) || !checkScalarVar(copyOfRange2)) {
            return false;
        }
        PointExt pointExt = new PointExt();
        if (!decodePointVar(bArr2, i2, true, pointExt)) {
            return false;
        }
        Xof createXof = createXof();
        byte[] bArr5 = new byte[114];
        dom4(createXof, b, bArr3);
        createXof.update(copyOfRange, 0, 57);
        createXof.update(bArr2, i2, 57);
        createXof.update(bArr4, i3, i4);
        createXof.doFinal(bArr5, 0, bArr5.length);
        byte[] reduceScalar = reduceScalar(bArr5);
        int[] iArr = new int[14];
        decodeScalar(copyOfRange2, 0, iArr);
        int[] iArr2 = new int[14];
        decodeScalar(reduceScalar, 0, iArr2);
        PointExt pointExt2 = new PointExt();
        scalarMultStrausVar(iArr, iArr2, pointExt, pointExt2);
        byte[] bArr6 = new byte[57];
        return encodePoint(pointExt2, bArr6, 0) != 0 && Arrays.areEqual(bArr6, copyOfRange);
    }

    private static void pointAdd(PointExt pointExt, PointExt pointExt2) {
        int[] create = X448Field.create();
        int[] create2 = X448Field.create();
        int[] create3 = X448Field.create();
        int[] create4 = X448Field.create();
        int[] create5 = X448Field.create();
        int[] create6 = X448Field.create();
        int[] create7 = X448Field.create();
        int[] create8 = X448Field.create();
        X448Field.mul(pointExt.f9840z, pointExt2.f9840z, create);
        X448Field.sqr(create, create2);
        X448Field.mul(pointExt.f9838x, pointExt2.f9838x, create3);
        X448Field.mul(pointExt.f9839y, pointExt2.f9839y, create4);
        X448Field.mul(create3, create4, create5);
        X448Field.mul(create5, 39081, create5);
        X448Field.add(create2, create5, create6);
        X448Field.sub(create2, create5, create7);
        X448Field.add(pointExt.f9838x, pointExt.f9839y, create2);
        X448Field.add(pointExt2.f9838x, pointExt2.f9839y, create5);
        X448Field.mul(create2, create5, create8);
        X448Field.add(create4, create3, create2);
        X448Field.sub(create4, create3, create5);
        X448Field.carry(create2);
        X448Field.sub(create8, create2, create8);
        X448Field.mul(create8, create, create8);
        X448Field.mul(create5, create, create5);
        X448Field.mul(create6, create8, pointExt2.f9838x);
        X448Field.mul(create5, create7, pointExt2.f9839y);
        X448Field.mul(create6, create7, pointExt2.f9840z);
    }

    private static void pointAddPrecomp(PointPrecomp pointPrecomp, PointExt pointExt) {
        int[] create = X448Field.create();
        int[] create2 = X448Field.create();
        int[] create3 = X448Field.create();
        int[] create4 = X448Field.create();
        int[] create5 = X448Field.create();
        int[] create6 = X448Field.create();
        int[] create7 = X448Field.create();
        X448Field.sqr(pointExt.f9840z, create);
        X448Field.mul(pointPrecomp.f9841x, pointExt.f9838x, create2);
        X448Field.mul(pointPrecomp.f9842y, pointExt.f9839y, create3);
        X448Field.mul(create2, create3, create4);
        X448Field.mul(create4, 39081, create4);
        X448Field.add(create, create4, create5);
        X448Field.sub(create, create4, create6);
        X448Field.add(pointPrecomp.f9841x, pointPrecomp.f9842y, create);
        X448Field.add(pointExt.f9838x, pointExt.f9839y, create4);
        X448Field.mul(create, create4, create7);
        X448Field.add(create3, create2, create);
        X448Field.sub(create3, create2, create4);
        X448Field.carry(create);
        X448Field.sub(create7, create, create7);
        X448Field.mul(create7, pointExt.f9840z, create7);
        X448Field.mul(create4, pointExt.f9840z, create4);
        X448Field.mul(create5, create7, pointExt.f9838x);
        X448Field.mul(create4, create6, pointExt.f9839y);
        X448Field.mul(create5, create6, pointExt.f9840z);
    }

    private static void pointAddVar(boolean z, PointExt pointExt, PointExt pointExt2) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int[] create = X448Field.create();
        int[] create2 = X448Field.create();
        int[] create3 = X448Field.create();
        int[] create4 = X448Field.create();
        int[] create5 = X448Field.create();
        int[] create6 = X448Field.create();
        int[] create7 = X448Field.create();
        int[] create8 = X448Field.create();
        if (z) {
            X448Field.sub(pointExt.f9839y, pointExt.f9838x, create8);
            iArr2 = create2;
            iArr = create5;
            iArr4 = create6;
            iArr3 = create7;
        } else {
            X448Field.add(pointExt.f9839y, pointExt.f9838x, create8);
            iArr = create2;
            iArr2 = create5;
            iArr3 = create6;
            iArr4 = create7;
        }
        X448Field.mul(pointExt.f9840z, pointExt2.f9840z, create);
        X448Field.sqr(create, create2);
        X448Field.mul(pointExt.f9838x, pointExt2.f9838x, create3);
        X448Field.mul(pointExt.f9839y, pointExt2.f9839y, create4);
        X448Field.mul(create3, create4, create5);
        X448Field.mul(create5, 39081, create5);
        X448Field.add(create2, create5, iArr3);
        X448Field.sub(create2, create5, iArr4);
        X448Field.add(pointExt2.f9838x, pointExt2.f9839y, create5);
        X448Field.mul(create8, create5, create8);
        X448Field.add(create4, create3, iArr);
        X448Field.sub(create4, create3, iArr2);
        X448Field.carry(iArr);
        X448Field.sub(create8, create2, create8);
        X448Field.mul(create8, create, create8);
        X448Field.mul(create5, create, create5);
        X448Field.mul(create6, create8, pointExt2.f9838x);
        X448Field.mul(create5, create7, pointExt2.f9839y);
        X448Field.mul(create6, create7, pointExt2.f9840z);
    }

    private static PointExt pointCopy(PointExt pointExt) {
        PointExt pointExt2 = new PointExt();
        pointCopy(pointExt, pointExt2);
        return pointExt2;
    }

    private static void pointCopy(PointExt pointExt, PointExt pointExt2) {
        X448Field.copy(pointExt.f9838x, 0, pointExt2.f9838x, 0);
        X448Field.copy(pointExt.f9839y, 0, pointExt2.f9839y, 0);
        X448Field.copy(pointExt.f9840z, 0, pointExt2.f9840z, 0);
    }

    private static void pointDouble(PointExt pointExt) {
        int[] create = X448Field.create();
        int[] create2 = X448Field.create();
        int[] create3 = X448Field.create();
        int[] create4 = X448Field.create();
        int[] create5 = X448Field.create();
        int[] create6 = X448Field.create();
        X448Field.add(pointExt.f9838x, pointExt.f9839y, create);
        X448Field.sqr(create, create);
        X448Field.sqr(pointExt.f9838x, create2);
        X448Field.sqr(pointExt.f9839y, create3);
        X448Field.add(create2, create3, create4);
        X448Field.carry(create4);
        X448Field.sqr(pointExt.f9840z, create5);
        X448Field.add(create5, create5, create5);
        X448Field.carry(create5);
        X448Field.sub(create4, create5, create6);
        X448Field.sub(create, create4, create);
        X448Field.sub(create2, create3, create2);
        X448Field.mul(create, create6, pointExt.f9838x);
        X448Field.mul(create4, create2, pointExt.f9839y);
        X448Field.mul(create4, create6, pointExt.f9840z);
    }

    private static void pointExtendXY(PointExt pointExt) {
        X448Field.one(pointExt.f9840z);
    }

    private static void pointLookup(int i, int i2, PointPrecomp pointPrecomp) {
        int i3 = i * 16 * 2 * 16;
        for (int i4 = 0; i4 < 16; i4++) {
            int i5 = ((i4 ^ i2) - 1) >> 31;
            X448Field.cmov(i5, precompBase, i3, pointPrecomp.f9841x, 0);
            int i6 = i3 + 16;
            X448Field.cmov(i5, precompBase, i6, pointPrecomp.f9842y, 0);
            i3 = i6 + 16;
        }
    }

    private static void pointLookup(int[] iArr, int i, int[] iArr2, PointExt pointExt) {
        int window4 = getWindow4(iArr, i);
        int i2 = (window4 >>> 3) ^ 1;
        int i3 = (window4 ^ (-i2)) & 7;
        int i4 = 0;
        for (int i5 = 0; i5 < 8; i5++) {
            int i6 = ((i5 ^ i3) - 1) >> 31;
            X448Field.cmov(i6, iArr2, i4, pointExt.f9838x, 0);
            int i7 = i4 + 16;
            X448Field.cmov(i6, iArr2, i7, pointExt.f9839y, 0);
            int i8 = i7 + 16;
            X448Field.cmov(i6, iArr2, i8, pointExt.f9840z, 0);
            i4 = i8 + 16;
        }
        X448Field.cnegate(i2, pointExt.f9838x);
    }

    private static int[] pointPrecomp(PointExt pointExt, int i) {
        PointExt pointCopy = pointCopy(pointExt);
        PointExt pointCopy2 = pointCopy(pointCopy);
        pointDouble(pointCopy2);
        int[] createTable = X448Field.createTable(i * 3);
        int i2 = 0;
        int i3 = 0;
        while (true) {
            X448Field.copy(pointCopy.f9838x, 0, createTable, i2);
            int i4 = i2 + 16;
            X448Field.copy(pointCopy.f9839y, 0, createTable, i4);
            int i5 = i4 + 16;
            X448Field.copy(pointCopy.f9840z, 0, createTable, i5);
            i2 = i5 + 16;
            i3++;
            if (i3 == i) {
                return createTable;
            }
            pointAdd(pointCopy2, pointCopy);
        }
    }

    private static PointExt[] pointPrecompVar(PointExt pointExt, int i) {
        PointExt pointCopy = pointCopy(pointExt);
        pointDouble(pointCopy);
        PointExt[] pointExtArr = new PointExt[i];
        pointExtArr[0] = pointCopy(pointExt);
        for (int i2 = 1; i2 < i; i2++) {
            pointExtArr[i2] = pointCopy(pointExtArr[i2 - 1]);
            pointAddVar(false, pointCopy, pointExtArr[i2]);
        }
        return pointExtArr;
    }

    private static void pointSetNeutral(PointExt pointExt) {
        X448Field.zero(pointExt.f9838x);
        X448Field.one(pointExt.f9839y);
        X448Field.one(pointExt.f9840z);
    }

    public static void precompute() {
        synchronized (precompLock) {
            if (precompBase != null) {
                return;
            }
            PointExt pointExt = new PointExt();
            X448Field.copy(B_x, 0, pointExt.f9838x, 0);
            X448Field.copy(B_y, 0, pointExt.f9839y, 0);
            pointExtendXY(pointExt);
            precompBaseTable = pointPrecompVar(pointExt, 32);
            precompBase = X448Field.createTable(160);
            int i = 0;
            int i2 = 0;
            while (i < 5) {
                PointExt[] pointExtArr = new PointExt[5];
                PointExt pointExt2 = new PointExt();
                pointSetNeutral(pointExt2);
                int i3 = 0;
                while (true) {
                    if (i3 >= 5) {
                        break;
                    }
                    pointAddVar(true, pointExt, pointExt2);
                    pointDouble(pointExt);
                    pointExtArr[i3] = pointCopy(pointExt);
                    if (i + i3 != 8) {
                        for (int i4 = 1; i4 < 18; i4++) {
                            pointDouble(pointExt);
                        }
                    }
                    i3++;
                }
                PointExt[] pointExtArr2 = new PointExt[16];
                pointExtArr2[0] = pointExt2;
                int i5 = 0;
                int i6 = 1;
                while (i5 < 4) {
                    int i7 = 1 << i5;
                    int i8 = i6;
                    int i9 = 0;
                    while (i9 < i7) {
                        pointExtArr2[i8] = pointCopy(pointExtArr2[i8 - i7]);
                        pointAddVar(false, pointExtArr[i5], pointExtArr2[i8]);
                        i9++;
                        i8++;
                    }
                    i5++;
                    i6 = i8;
                }
                int i10 = i2;
                for (int i11 = 0; i11 < 16; i11++) {
                    PointExt pointExt3 = pointExtArr2[i11];
                    X448Field.inv(pointExt3.f9840z, pointExt3.f9840z);
                    X448Field.mul(pointExt3.f9838x, pointExt3.f9840z, pointExt3.f9838x);
                    X448Field.mul(pointExt3.f9839y, pointExt3.f9840z, pointExt3.f9839y);
                    X448Field.copy(pointExt3.f9838x, 0, precompBase, i10);
                    int i12 = i10 + 16;
                    X448Field.copy(pointExt3.f9839y, 0, precompBase, i12);
                    i10 = i12 + 16;
                }
                i++;
                i2 = i10;
            }
        }
    }

    private static void pruneScalar(byte[] bArr, int i, byte[] bArr2) {
        System.arraycopy(bArr, i, bArr2, 0, 56);
        bArr2[0] = (byte) (bArr2[0] & 252);
        bArr2[55] = (byte) (bArr2[55] | 128);
        bArr2[56] = 0;
    }

    private static byte[] reduceScalar(byte[] bArr) {
        long decode32 = decode32(bArr, 84) & 4294967295L;
        long decode322 = decode32(bArr, 91) & 4294967295L;
        long decode323 = decode32(bArr, 98) & 4294967295L;
        long decode324 = decode32(bArr, 105) & 4294967295L;
        long decode16 = decode16(bArr, 112) & 4294967295L;
        long decode24 = ((decode24(bArr, 109) << 4) & 4294967295L) + (decode324 >>> 28);
        long j = decode324 & M28L;
        long decode242 = ((decode24(bArr, 74) << 4) & 4294967295L) + (decode16 * 227822194) + (decode24 * 149865618);
        long decode325 = (decode32(bArr, 77) & 4294967295L) + (decode16 * 149865618) + (decode24 * 550336261);
        long decode326 = (decode32(bArr, 49) & 4294967295L) + (j * 43969588);
        long decode243 = ((decode24(bArr, 53) << 4) & 4294967295L) + (decode24 * 43969588) + (j * 30366549);
        long decode327 = (decode32(bArr, 56) & 4294967295L) + (decode16 * 43969588) + (decode24 * 30366549) + (j * 163752818);
        long decode244 = ((decode24(bArr, 60) << 4) & 4294967295L) + (decode16 * 30366549) + (decode24 * 163752818) + (j * 258169998);
        long decode328 = (decode32(bArr, 63) & 4294967295L) + (decode16 * 163752818) + (decode24 * 258169998) + (j * 96434764);
        long decode245 = ((decode24(bArr, 67) << 4) & 4294967295L) + (decode16 * 258169998) + (decode24 * 96434764) + (j * 227822194);
        long decode329 = (decode32(bArr, 70) & 4294967295L) + (decode16 * 96434764) + (decode24 * 227822194) + (j * 149865618);
        long decode246 = ((decode24(bArr, 102) << 4) & 4294967295L) + (decode323 >>> 28);
        long j2 = decode323 & M28L;
        long decode247 = ((decode24(bArr, 46) << 4) & 4294967295L) + (decode246 * 43969588);
        long j3 = decode245 + (decode246 * 149865618);
        long j4 = decode329 + (decode246 * 550336261);
        long decode3210 = (decode32(bArr, 42) & 4294967295L) + (j2 * 43969588);
        long j5 = decode326 + (decode246 * 30366549) + (j2 * 163752818);
        long j6 = decode243 + (decode246 * 163752818) + (j2 * 258169998);
        long j7 = decode327 + (decode246 * 258169998) + (j2 * 96434764);
        long j8 = decode244 + (decode246 * 96434764) + (j2 * 227822194);
        long j9 = decode328 + (decode246 * 227822194) + (j2 * 149865618);
        long decode248 = ((decode24(bArr, 95) << 4) & 4294967295L) + (decode322 >>> 28);
        long j10 = decode322 & M28L;
        long decode249 = ((decode24(bArr, 39) << 4) & 4294967295L) + (decode248 * 43969588);
        long j11 = j9 + (decode248 * 550336261);
        long decode3211 = (decode32(bArr, 35) & 4294967295L) + (j10 * 43969588);
        long j12 = decode3210 + (decode248 * 30366549) + (j10 * 163752818);
        long j13 = decode247 + (j2 * 30366549) + (decode248 * 163752818) + (j10 * 258169998);
        long j14 = j5 + (decode248 * 258169998) + (j10 * 96434764);
        long j15 = j6 + (decode248 * 96434764) + (j10 * 227822194);
        long j16 = j7 + (decode248 * 227822194) + (j10 * 149865618);
        long j17 = j8 + (decode248 * 149865618) + (j10 * 550336261);
        long decode2410 = ((decode24(bArr, 88) << 4) & 4294967295L) + (decode32 >>> 28);
        long j18 = decode32 & M28L;
        long j19 = decode242 + (j * 550336261) + (j4 >>> 28);
        long j20 = j4 & M28L;
        long j21 = decode325 + (j19 >>> 28);
        long j22 = j19 & M28L;
        long decode2411 = ((decode24(bArr, 81) << 4) & 4294967295L) + (decode16 * 550336261) + (j21 >>> 28);
        long j23 = j21 & M28L;
        long j24 = j18 + (decode2411 >>> 28);
        long j25 = decode2411 & M28L;
        long decode2412 = ((decode24(bArr, 25) << 4) & 4294967295L) + (j25 * 43969588);
        long decode3212 = (decode32(bArr, 28) & 4294967295L) + (j24 * 43969588) + (j25 * 30366549);
        long decode2413 = ((decode24(bArr, 32) << 4) & 4294967295L) + (decode2410 * 43969588) + (j24 * 30366549) + (j25 * 163752818);
        long j26 = decode3211 + (decode2410 * 30366549) + (j24 * 163752818) + (j25 * 258169998);
        long j27 = decode249 + (j10 * 30366549) + (decode2410 * 163752818) + (j24 * 258169998) + (j25 * 96434764);
        long j28 = j12 + (decode2410 * 258169998) + (j24 * 96434764) + (j25 * 227822194);
        long j29 = j13 + (decode2410 * 96434764) + (j24 * 227822194) + (j25 * 149865618);
        long j30 = j14 + (decode2410 * 227822194) + (j24 * 149865618) + (j25 * 550336261);
        long decode3213 = (decode32(bArr, 21) & 4294967295L) + (j23 * 43969588);
        long j31 = j11 + (j17 >>> 28);
        long j32 = j17 & M28L;
        long j33 = j3 + (j2 * 550336261) + (j31 >>> 28);
        long j34 = j31 & M28L;
        long j35 = j20 + (j33 >>> 28);
        long j36 = j33 & M28L;
        long j37 = j22 + (j35 >>> 28);
        long j38 = j35 & M28L;
        long decode3214 = (decode32(bArr, 14) & 4294967295L) + (j38 * 43969588);
        long decode2414 = ((decode24(bArr, 18) << 4) & 4294967295L) + (j37 * 43969588) + (j38 * 30366549);
        long j39 = decode3213 + (j37 * 30366549) + (j38 * 163752818);
        long j40 = decode2412 + (j23 * 30366549) + (j37 * 163752818) + (j38 * 258169998);
        long j41 = decode3212 + (j23 * 163752818) + (j37 * 258169998) + (j38 * 96434764);
        long j42 = decode2413 + (j23 * 258169998) + (j37 * 96434764) + (j38 * 227822194);
        long j43 = j26 + (j23 * 96434764) + (j37 * 227822194) + (j38 * 149865618);
        long j44 = j27 + (j23 * 227822194) + (j37 * 149865618) + (j38 * 550336261);
        long decode2415 = ((decode24(bArr, 11) << 4) & 4294967295L) + (j36 * 43969588);
        long j45 = decode3214 + (j36 * 30366549);
        long j46 = decode2414 + (j36 * 163752818);
        long j47 = j39 + (j36 * 258169998);
        long j48 = j40 + (j36 * 96434764);
        long j49 = j41 + (j36 * 227822194);
        long j50 = j42 + (j36 * 149865618);
        long j51 = j43 + (j36 * 550336261);
        long j52 = j15 + (decode2410 * 149865618) + (j24 * 550336261) + (j30 >>> 28);
        long j53 = j30 & M28L;
        long j54 = j16 + (decode2410 * 550336261) + (j52 >>> 28);
        long j55 = j52 & M28L;
        long j56 = j32 + (j54 >>> 28);
        long j57 = j54 & M28L;
        long j58 = j34 + (j56 >>> 28);
        long j59 = j56 & M28L;
        long decode3215 = (decode32(bArr, 7) & 4294967295L) + (j58 * 43969588);
        long j60 = decode2415 + (j58 * 30366549);
        long j61 = j45 + (j58 * 163752818);
        long j62 = j46 + (j58 * 258169998);
        long j63 = j47 + (j58 * 96434764);
        long j64 = j48 + (j58 * 227822194);
        long j65 = j49 + (j58 * 149865618);
        long j66 = j50 + (j58 * 550336261);
        long decode2416 = ((decode24(bArr, 4) << 4) & 4294967295L) + (43969588 * j59);
        long j67 = decode3215 + (30366549 * j59);
        long j68 = j60 + (163752818 * j59);
        long j69 = j61 + (258169998 * j59);
        long j70 = j62 + (96434764 * j59);
        long j71 = j63 + (227822194 * j59);
        long j72 = j64 + (149865618 * j59);
        long j73 = j65 + (j59 * 550336261);
        long j74 = j55 & M26L;
        long j75 = (j57 * 4) + (j55 >>> 26) + 1;
        long decode3216 = (decode32(bArr, 0) & 4294967295L) + (78101261 * j75);
        long j76 = decode2416 + (141809365 * j75) + (decode3216 >>> 28);
        long j77 = decode3216 & M28L;
        long j78 = j67 + (175155932 * j75) + (j76 >>> 28);
        long j79 = j76 & M28L;
        long j80 = j68 + (64542499 * j75) + (j78 >>> 28);
        long j81 = j78 & M28L;
        long j82 = j69 + (158326419 * j75) + (j80 >>> 28);
        long j83 = j80 & M28L;
        long j84 = j70 + (191173276 * j75) + (j82 >>> 28);
        long j85 = j82 & M28L;
        long j86 = j71 + (104575268 * j75) + (j84 >>> 28);
        long j87 = j84 & M28L;
        long j88 = j72 + (j75 * 137584065) + (j86 >>> 28);
        long j89 = j86 & M28L;
        long j90 = j73 + (j88 >>> 28);
        long j91 = j88 & M28L;
        long j92 = j66 + (j90 >>> 28);
        long j93 = j90 & M28L;
        long j94 = j51 + (j92 >>> 28);
        long j95 = j92 & M28L;
        long j96 = j44 + (j94 >>> 28);
        long j97 = j94 & M28L;
        long j98 = j28 + (j23 * 149865618) + (j37 * 550336261) + (j96 >>> 28);
        long j99 = j96 & M28L;
        long j100 = j29 + (j23 * 550336261) + (j98 >>> 28);
        long j101 = j98 & M28L;
        long j102 = j53 + (j100 >>> 28);
        long j103 = j100 & M28L;
        long j104 = j74 + (j102 >>> 28);
        long j105 = j102 & M28L;
        long j106 = j104 >>> 26;
        long j107 = j104 & M26L;
        long j108 = j106 - 1;
        long j109 = j77 - (j108 & 78101261);
        long j110 = (j79 - (j108 & 141809365)) + (j109 >> 28);
        long j111 = j109 & M28L;
        long j112 = (j81 - (j108 & 175155932)) + (j110 >> 28);
        long j113 = j110 & M28L;
        long j114 = (j83 - (j108 & 64542499)) + (j112 >> 28);
        long j115 = j112 & M28L;
        long j116 = (j85 - (j108 & 158326419)) + (j114 >> 28);
        long j117 = j114 & M28L;
        long j118 = (j87 - (j108 & 191173276)) + (j116 >> 28);
        long j119 = j116 & M28L;
        long j120 = (j89 - (j108 & 104575268)) + (j118 >> 28);
        long j121 = j118 & M28L;
        long j122 = (j91 - (j108 & 137584065)) + (j120 >> 28);
        long j123 = j120 & M28L;
        long j124 = j93 + (j122 >> 28);
        long j125 = j122 & M28L;
        long j126 = j95 + (j124 >> 28);
        long j127 = j124 & M28L;
        long j128 = j97 + (j126 >> 28);
        long j129 = j126 & M28L;
        long j130 = j99 + (j128 >> 28);
        long j131 = j128 & M28L;
        long j132 = j101 + (j130 >> 28);
        long j133 = j130 & M28L;
        long j134 = j103 + (j132 >> 28);
        long j135 = j132 & M28L;
        long j136 = j105 + (j134 >> 28);
        long j137 = j134 & M28L;
        long j138 = j107 + (j136 >> 28);
        long j139 = j136 & M28L;
        byte[] bArr2 = new byte[57];
        encode56((j113 << 28) | j111, bArr2, 0);
        encode56((j117 << 28) | j115, bArr2, 7);
        encode56(j119 | (j121 << 28), bArr2, 14);
        encode56(j123 | (j125 << 28), bArr2, 21);
        encode56(j127 | (j129 << 28), bArr2, 28);
        encode56(j131 | (j133 << 28), bArr2, 35);
        encode56(j135 | (j137 << 28), bArr2, 42);
        encode56((j138 << 28) | j139, bArr2, 49);
        return bArr2;
    }

    private static void scalarMult(byte[] bArr, PointExt pointExt, PointExt pointExt2) {
        precompute();
        int[] iArr = new int[14];
        decodeScalar(bArr, 0, iArr);
        Nat.shiftDownBits(14, iArr, 2, 0);
        Nat.cadd(14, (~iArr[0]) & 1, iArr, f9836L, iArr);
        Nat.shiftDownBit(14, iArr, 1);
        int[] pointPrecomp = pointPrecomp(pointExt, 8);
        pointLookup(iArr, 111, pointPrecomp, pointExt2);
        PointExt pointExt3 = new PointExt();
        for (int i = 110; i >= 0; i--) {
            for (int i2 = 0; i2 < 4; i2++) {
                pointDouble(pointExt2);
            }
            pointLookup(iArr, i, pointPrecomp, pointExt3);
            pointAdd(pointExt3, pointExt2);
        }
        for (int i3 = 0; i3 < 2; i3++) {
            pointDouble(pointExt2);
        }
    }

    private static void scalarMultBase(byte[] bArr, PointExt pointExt) {
        precompute();
        pointSetNeutral(pointExt);
        int[] iArr = new int[15];
        decodeScalar(bArr, 0, iArr);
        iArr[14] = Nat.cadd(14, (~iArr[0]) & 1, iArr, f9836L, iArr) + 4;
        Nat.shiftDownBit(iArr.length, iArr, 0);
        PointPrecomp pointPrecomp = new PointPrecomp();
        int i = 17;
        while (true) {
            int i2 = 0;
            int i3 = i;
            while (i2 < 5) {
                int i4 = 0;
                int i5 = i3;
                for (int i6 = 0; i6 < 5; i6++) {
                    i4 = (i4 & (~(1 << i6))) ^ ((iArr[i5 >>> 5] >>> (i5 & 31)) << i6);
                    i5 += 18;
                }
                int i7 = (i4 >>> 4) & 1;
                pointLookup(i2, ((-i7) ^ i4) & 15, pointPrecomp);
                X448Field.cnegate(i7, pointPrecomp.f9841x);
                pointAddPrecomp(pointPrecomp, pointExt);
                i2++;
                i3 = i5;
            }
            i--;
            if (i < 0) {
                return;
            } else {
                pointDouble(pointExt);
            }
        }
    }

    private static void scalarMultBaseEncoded(byte[] bArr, byte[] bArr2, int i) {
        PointExt pointExt = new PointExt();
        scalarMultBase(bArr, pointExt);
        if (encodePoint(pointExt, bArr2, i) == 0) {
            throw new IllegalStateException();
        }
    }

    public static void scalarMultBaseXY(X448.Friend friend, byte[] bArr, int i, int[] iArr, int[] iArr2) {
        if (friend == null) {
            throw new NullPointerException("This method is only for use by X448");
        }
        byte[] bArr2 = new byte[57];
        pruneScalar(bArr, i, bArr2);
        PointExt pointExt = new PointExt();
        scalarMultBase(bArr2, pointExt);
        if (checkPoint(pointExt.f9838x, pointExt.f9839y, pointExt.f9840z) == 0) {
            throw new IllegalStateException();
        }
        X448Field.copy(pointExt.f9838x, 0, iArr, 0);
        X448Field.copy(pointExt.f9839y, 0, iArr2, 0);
    }

    private static void scalarMultStrausVar(int[] iArr, int[] iArr2, PointExt pointExt, PointExt pointExt2) {
        precompute();
        byte[] wnaf = getWNAF(iArr, 7);
        byte[] wnaf2 = getWNAF(iArr2, 5);
        PointExt[] pointPrecompVar = pointPrecompVar(pointExt, 8);
        pointSetNeutral(pointExt2);
        int i = 446;
        while (true) {
            byte b = wnaf[i];
            if (b != 0) {
                int i2 = b >> Ascii.f1926US;
                pointAddVar(i2 != 0, precompBaseTable[(b ^ i2) >>> 1], pointExt2);
            }
            byte b2 = wnaf2[i];
            if (b2 != 0) {
                int i3 = b2 >> Ascii.f1926US;
                pointAddVar(i3 != 0, pointPrecompVar[(b2 ^ i3) >>> 1], pointExt2);
            }
            i--;
            if (i < 0) {
                return;
            } else {
                pointDouble(pointExt2);
            }
        }
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, int i4, byte[] bArr5, int i5) {
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 0, bArr4, i3, i4, bArr5, i5);
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2, int i3, byte[] bArr4, int i4) {
        implSign(bArr, i, bArr2, (byte) 0, bArr3, i2, i3, bArr4, i4);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, Xof xof, byte[] bArr4, int i3) {
        byte[] bArr5 = new byte[64];
        if (64 != xof.doFinal(bArr5, 0, 64)) {
            throw new IllegalArgumentException("ph");
        }
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr5, 0, bArr5.length, bArr4, i3);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, byte[] bArr5, int i4) {
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, i3, 64, bArr5, i4);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, Xof xof, byte[] bArr3, int i2) {
        byte[] bArr4 = new byte[64];
        if (64 != xof.doFinal(bArr4, 0, 64)) {
            throw new IllegalArgumentException("ph");
        }
        implSign(bArr, i, bArr2, (byte) 1, bArr4, 0, bArr4.length, bArr3, i2);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2, byte[] bArr4, int i3) {
        implSign(bArr, i, bArr2, (byte) 1, bArr3, i2, 64, bArr4, i3);
    }

    public static boolean verify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, int i4) {
        return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 0, bArr4, i3, i4);
    }

    public static boolean verifyPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, Xof xof) {
        byte[] bArr4 = new byte[64];
        if (64 == xof.doFinal(bArr4, 0, 64)) {
            return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, 0, bArr4.length);
        }
        throw new IllegalArgumentException("ph");
    }

    public static boolean verifyPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3) {
        return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, i3, 64);
    }
}
