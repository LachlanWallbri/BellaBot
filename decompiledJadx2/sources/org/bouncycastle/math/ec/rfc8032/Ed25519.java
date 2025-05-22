package org.bouncycastle.math.ec.rfc8032;

import com.google.common.base.Ascii;
import java.security.SecureRandom;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.math.ec.rfc7748.X25519;
import org.bouncycastle.math.ec.rfc7748.X25519Field;
import org.bouncycastle.math.raw.Interleave;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

/* loaded from: classes9.dex */
public abstract class Ed25519 {

    /* renamed from: L0 */
    private static final int f9819L0 = -50998291;

    /* renamed from: L1 */
    private static final int f9820L1 = 19280294;

    /* renamed from: L2 */
    private static final int f9821L2 = 127719000;

    /* renamed from: L3 */
    private static final int f9822L3 = -6428113;

    /* renamed from: L4 */
    private static final int f9823L4 = 5343;
    private static final long M28L = 268435455;
    private static final long M32L = 4294967295L;
    private static final int POINT_BYTES = 32;
    private static final int PRECOMP_BLOCKS = 8;
    private static final int PRECOMP_MASK = 7;
    private static final int PRECOMP_POINTS = 8;
    private static final int PRECOMP_SPACING = 8;
    private static final int PRECOMP_TEETH = 4;
    public static final int PREHASH_SIZE = 64;
    public static final int PUBLIC_KEY_SIZE = 32;
    private static final int SCALAR_BYTES = 32;
    private static final int SCALAR_INTS = 8;
    public static final int SECRET_KEY_SIZE = 32;
    public static final int SIGNATURE_SIZE = 64;
    private static final int WNAF_WIDTH_BASE = 7;
    private static final byte[] DOM2_PREFIX = Strings.toByteArray("SigEd25519 no Ed25519 collisions");

    /* renamed from: P */
    private static final int[] f9824P = {-19, -1, -1, -1, -1, -1, -1, Integer.MAX_VALUE};

    /* renamed from: L */
    private static final int[] f9818L = {1559614445, 1477600026, -1560830762, 350157278, 0, 0, 0, ClientDefaults.MAX_MSG_SIZE};
    private static final int[] B_x = {52811034, 25909283, 8072341, 50637101, 13785486, 30858332, 20483199, 20966410, 43936626, 4379245};
    private static final int[] B_y = {40265304, 26843545, 6710886, 53687091, 13421772, 40265318, 26843545, 6710886, 53687091, 13421772};
    private static final int[] C_d = {56195235, 47411844, 25868126, 40503822, 57364, 58321048, 30416477, 31930572, 57760639, 10749657};
    private static final int[] C_d2 = {45281625, 27714825, 18181821, 13898781, 114729, 49533232, 60832955, 30306712, 48412415, 4722099};
    private static final int[] C_d4 = {23454386, 55429651, 2809210, 27797563, 229458, 31957600, 54557047, 27058993, 29715967, 9444199};
    private static final Object precompLock = new Object();
    private static PointExt[] precompBaseTable = null;
    private static int[] precompBase = null;

    /* loaded from: classes9.dex */
    public static final class Algorithm {
        public static final int Ed25519 = 0;
        public static final int Ed25519ctx = 1;
        public static final int Ed25519ph = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class PointAccum {

        /* renamed from: u */
        int[] f9825u;

        /* renamed from: v */
        int[] f9826v;

        /* renamed from: x */
        int[] f9827x;

        /* renamed from: y */
        int[] f9828y;

        /* renamed from: z */
        int[] f9829z;

        private PointAccum() {
            this.f9827x = X25519Field.create();
            this.f9828y = X25519Field.create();
            this.f9829z = X25519Field.create();
            this.f9825u = X25519Field.create();
            this.f9826v = X25519Field.create();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class PointAffine {

        /* renamed from: x */
        int[] f9830x;

        /* renamed from: y */
        int[] f9831y;

        private PointAffine() {
            this.f9830x = X25519Field.create();
            this.f9831y = X25519Field.create();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class PointExt {

        /* renamed from: t */
        int[] f9832t;

        /* renamed from: x */
        int[] f9833x;

        /* renamed from: y */
        int[] f9834y;

        /* renamed from: z */
        int[] f9835z;

        private PointExt() {
            this.f9833x = X25519Field.create();
            this.f9834y = X25519Field.create();
            this.f9835z = X25519Field.create();
            this.f9832t = X25519Field.create();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class PointPrecomp {
        int[] xyd;
        int[] ymx_h;
        int[] ypx_h;

        private PointPrecomp() {
            this.ypx_h = X25519Field.create();
            this.ymx_h = X25519Field.create();
            this.xyd = X25519Field.create();
        }
    }

    private static byte[] calculateS(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int[] iArr = new int[16];
        decodeScalar(bArr, 0, iArr);
        int[] iArr2 = new int[8];
        decodeScalar(bArr2, 0, iArr2);
        int[] iArr3 = new int[8];
        decodeScalar(bArr3, 0, iArr3);
        Nat256.mulAddTo(iArr2, iArr3, iArr);
        byte[] bArr4 = new byte[64];
        for (int i = 0; i < iArr.length; i++) {
            encode32(iArr[i], bArr4, i * 4);
        }
        return reduceScalar(bArr4);
    }

    private static boolean checkContextVar(byte[] bArr, byte b) {
        return (bArr == null && b == 0) || (bArr != null && bArr.length < 256);
    }

    private static int checkPoint(int[] iArr, int[] iArr2) {
        int[] create = X25519Field.create();
        int[] create2 = X25519Field.create();
        int[] create3 = X25519Field.create();
        X25519Field.sqr(iArr, create2);
        X25519Field.sqr(iArr2, create3);
        X25519Field.mul(create2, create3, create);
        X25519Field.sub(create3, create2, create3);
        X25519Field.mul(create, C_d, create);
        X25519Field.addOne(create);
        X25519Field.sub(create, create3, create);
        X25519Field.normalize(create);
        return X25519Field.isZero(create);
    }

    private static int checkPoint(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] create = X25519Field.create();
        int[] create2 = X25519Field.create();
        int[] create3 = X25519Field.create();
        int[] create4 = X25519Field.create();
        X25519Field.sqr(iArr, create2);
        X25519Field.sqr(iArr2, create3);
        X25519Field.sqr(iArr3, create4);
        X25519Field.mul(create2, create3, create);
        X25519Field.sub(create3, create2, create3);
        X25519Field.mul(create3, create4, create3);
        X25519Field.sqr(create4, create4);
        X25519Field.mul(create, C_d, create);
        X25519Field.add(create, create4, create);
        X25519Field.sub(create, create3, create);
        X25519Field.normalize(create);
        return X25519Field.isZero(create);
    }

    private static boolean checkPointVar(byte[] bArr) {
        int[] iArr = new int[8];
        decode32(bArr, 0, iArr, 0, 8);
        iArr[7] = iArr[7] & Integer.MAX_VALUE;
        return !Nat256.gte(iArr, f9824P);
    }

    private static boolean checkScalarVar(byte[] bArr) {
        decodeScalar(bArr, 0, new int[8]);
        return !Nat256.gte(r0, f9818L);
    }

    private static Digest createDigest() {
        return new SHA512Digest();
    }

    public static Digest createPrehash() {
        return createDigest();
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

    private static boolean decodePointVar(byte[] bArr, int i, boolean z, PointAffine pointAffine) {
        byte[] copyOfRange = Arrays.copyOfRange(bArr, i, i + 32);
        if (!checkPointVar(copyOfRange)) {
            return false;
        }
        int i2 = (copyOfRange[31] & 128) >>> 7;
        copyOfRange[31] = (byte) (copyOfRange[31] & Byte.MAX_VALUE);
        X25519Field.decode(copyOfRange, 0, pointAffine.f9831y);
        int[] create = X25519Field.create();
        int[] create2 = X25519Field.create();
        X25519Field.sqr(pointAffine.f9831y, create);
        X25519Field.mul(C_d, create, create2);
        X25519Field.subOne(create);
        X25519Field.addOne(create2);
        if (!X25519Field.sqrtRatioVar(create, create2, pointAffine.f9830x)) {
            return false;
        }
        X25519Field.normalize(pointAffine.f9830x);
        if (i2 == 1 && X25519Field.isZeroVar(pointAffine.f9830x)) {
            return false;
        }
        if (z ^ (i2 != (pointAffine.f9830x[0] & 1))) {
            X25519Field.negate(pointAffine.f9830x, pointAffine.f9830x);
        }
        return true;
    }

    private static void decodeScalar(byte[] bArr, int i, int[] iArr) {
        decode32(bArr, i, iArr, 0, 8);
    }

    private static void dom2(Digest digest, byte b, byte[] bArr) {
        if (bArr != null) {
            byte[] bArr2 = DOM2_PREFIX;
            digest.update(bArr2, 0, bArr2.length);
            digest.update(b);
            digest.update((byte) bArr.length);
            digest.update(bArr, 0, bArr.length);
        }
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

    private static int encodePoint(PointAccum pointAccum, byte[] bArr, int i) {
        int[] create = X25519Field.create();
        int[] create2 = X25519Field.create();
        X25519Field.inv(pointAccum.f9829z, create2);
        X25519Field.mul(pointAccum.f9827x, create2, create);
        X25519Field.mul(pointAccum.f9828y, create2, create2);
        X25519Field.normalize(create);
        X25519Field.normalize(create2);
        int checkPoint = checkPoint(create, create2);
        X25519Field.encode(create2, bArr, i);
        int i2 = (i + 32) - 1;
        bArr[i2] = (byte) (((create[0] & 1) << 7) | bArr[i2]);
        return checkPoint;
    }

    public static void generatePrivateKey(SecureRandom secureRandom, byte[] bArr) {
        secureRandom.nextBytes(bArr);
    }

    public static void generatePublicKey(byte[] bArr, int i, byte[] bArr2, int i2) {
        Digest createDigest = createDigest();
        byte[] bArr3 = new byte[createDigest.getDigestSize()];
        createDigest.update(bArr, i, 32);
        createDigest.doFinal(bArr3, 0);
        byte[] bArr4 = new byte[32];
        pruneScalar(bArr3, 0, bArr4);
        scalarMultBaseEncoded(bArr4, bArr2, i2);
    }

    private static byte[] getWNAF(int[] iArr, int i) {
        int[] iArr2 = new int[16];
        int length = iArr2.length;
        int i2 = 0;
        int i3 = 8;
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
        byte[] bArr = new byte[253];
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

    private static void implSign(Digest digest, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, byte b, byte[] bArr5, int i2, int i3, byte[] bArr6, int i4) {
        dom2(digest, b, bArr4);
        digest.update(bArr, 32, 32);
        digest.update(bArr5, i2, i3);
        digest.doFinal(bArr, 0);
        byte[] reduceScalar = reduceScalar(bArr);
        byte[] bArr7 = new byte[32];
        scalarMultBaseEncoded(reduceScalar, bArr7, 0);
        dom2(digest, b, bArr4);
        digest.update(bArr7, 0, 32);
        digest.update(bArr3, i, 32);
        digest.update(bArr5, i2, i3);
        digest.doFinal(bArr, 0);
        byte[] calculateS = calculateS(reduceScalar, reduceScalar(bArr), bArr2);
        System.arraycopy(bArr7, 0, bArr6, i4, 32);
        System.arraycopy(calculateS, 0, bArr6, i4 + 32, 32);
    }

    private static void implSign(byte[] bArr, int i, byte[] bArr2, byte b, byte[] bArr3, int i2, int i3, byte[] bArr4, int i4) {
        if (!checkContextVar(bArr2, b)) {
            throw new IllegalArgumentException("ctx");
        }
        Digest createDigest = createDigest();
        byte[] bArr5 = new byte[createDigest.getDigestSize()];
        createDigest.update(bArr, i, 32);
        createDigest.doFinal(bArr5, 0);
        byte[] bArr6 = new byte[32];
        pruneScalar(bArr5, 0, bArr6);
        byte[] bArr7 = new byte[32];
        scalarMultBaseEncoded(bArr6, bArr7, 0);
        implSign(createDigest, bArr5, bArr6, bArr7, 0, bArr2, b, bArr3, i2, i3, bArr4, i4);
    }

    private static void implSign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte b, byte[] bArr4, int i3, int i4, byte[] bArr5, int i5) {
        if (!checkContextVar(bArr3, b)) {
            throw new IllegalArgumentException("ctx");
        }
        Digest createDigest = createDigest();
        byte[] bArr6 = new byte[createDigest.getDigestSize()];
        createDigest.update(bArr, i, 32);
        createDigest.doFinal(bArr6, 0);
        byte[] bArr7 = new byte[32];
        pruneScalar(bArr6, 0, bArr7);
        implSign(createDigest, bArr6, bArr7, bArr2, i2, bArr3, b, bArr4, i3, i4, bArr5, i5);
    }

    private static boolean implVerify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte b, byte[] bArr4, int i3, int i4) {
        if (!checkContextVar(bArr3, b)) {
            throw new IllegalArgumentException("ctx");
        }
        int i5 = i + 32;
        byte[] copyOfRange = Arrays.copyOfRange(bArr, i, i5);
        byte[] copyOfRange2 = Arrays.copyOfRange(bArr, i5, i + 64);
        if (!checkPointVar(copyOfRange) || !checkScalarVar(copyOfRange2)) {
            return false;
        }
        PointAffine pointAffine = new PointAffine();
        if (!decodePointVar(bArr2, i2, true, pointAffine)) {
            return false;
        }
        Digest createDigest = createDigest();
        byte[] bArr5 = new byte[createDigest.getDigestSize()];
        dom2(createDigest, b, bArr3);
        createDigest.update(copyOfRange, 0, 32);
        createDigest.update(bArr2, i2, 32);
        createDigest.update(bArr4, i3, i4);
        createDigest.doFinal(bArr5, 0);
        byte[] reduceScalar = reduceScalar(bArr5);
        int[] iArr = new int[8];
        decodeScalar(copyOfRange2, 0, iArr);
        int[] iArr2 = new int[8];
        decodeScalar(reduceScalar, 0, iArr2);
        PointAccum pointAccum = new PointAccum();
        scalarMultStrausVar(iArr, iArr2, pointAffine, pointAccum);
        byte[] bArr6 = new byte[32];
        return encodePoint(pointAccum, bArr6, 0) != 0 && Arrays.areEqual(bArr6, copyOfRange);
    }

    private static void pointAdd(PointExt pointExt, PointAccum pointAccum) {
        int[] create = X25519Field.create();
        int[] create2 = X25519Field.create();
        int[] create3 = X25519Field.create();
        int[] create4 = X25519Field.create();
        int[] iArr = pointAccum.f9825u;
        int[] create5 = X25519Field.create();
        int[] create6 = X25519Field.create();
        int[] iArr2 = pointAccum.f9826v;
        X25519Field.apm(pointAccum.f9828y, pointAccum.f9827x, create2, create);
        X25519Field.apm(pointExt.f9834y, pointExt.f9833x, create4, create3);
        X25519Field.mul(create, create3, create);
        X25519Field.mul(create2, create4, create2);
        X25519Field.mul(pointAccum.f9825u, pointAccum.f9826v, create3);
        X25519Field.mul(create3, pointExt.f9832t, create3);
        X25519Field.mul(create3, C_d2, create3);
        X25519Field.mul(pointAccum.f9829z, pointExt.f9835z, create4);
        X25519Field.add(create4, create4, create4);
        X25519Field.apm(create2, create, iArr2, iArr);
        X25519Field.apm(create4, create3, create6, create5);
        X25519Field.carry(create6);
        X25519Field.mul(iArr, create5, pointAccum.f9827x);
        X25519Field.mul(create6, iArr2, pointAccum.f9828y);
        X25519Field.mul(create5, create6, pointAccum.f9829z);
    }

    private static void pointAdd(PointExt pointExt, PointExt pointExt2) {
        int[] create = X25519Field.create();
        int[] create2 = X25519Field.create();
        int[] create3 = X25519Field.create();
        int[] create4 = X25519Field.create();
        int[] create5 = X25519Field.create();
        int[] create6 = X25519Field.create();
        int[] create7 = X25519Field.create();
        int[] create8 = X25519Field.create();
        X25519Field.apm(pointExt.f9834y, pointExt.f9833x, create2, create);
        X25519Field.apm(pointExt2.f9834y, pointExt2.f9833x, create4, create3);
        X25519Field.mul(create, create3, create);
        X25519Field.mul(create2, create4, create2);
        X25519Field.mul(pointExt.f9832t, pointExt2.f9832t, create3);
        X25519Field.mul(create3, C_d2, create3);
        X25519Field.mul(pointExt.f9835z, pointExt2.f9835z, create4);
        X25519Field.add(create4, create4, create4);
        X25519Field.apm(create2, create, create8, create5);
        X25519Field.apm(create4, create3, create7, create6);
        X25519Field.carry(create7);
        X25519Field.mul(create5, create6, pointExt2.f9833x);
        X25519Field.mul(create7, create8, pointExt2.f9834y);
        X25519Field.mul(create6, create7, pointExt2.f9835z);
        X25519Field.mul(create5, create8, pointExt2.f9832t);
    }

    private static void pointAddPrecomp(PointPrecomp pointPrecomp, PointAccum pointAccum) {
        int[] create = X25519Field.create();
        int[] create2 = X25519Field.create();
        int[] create3 = X25519Field.create();
        int[] iArr = pointAccum.f9825u;
        int[] create4 = X25519Field.create();
        int[] create5 = X25519Field.create();
        int[] iArr2 = pointAccum.f9826v;
        X25519Field.apm(pointAccum.f9828y, pointAccum.f9827x, create2, create);
        X25519Field.mul(create, pointPrecomp.ymx_h, create);
        X25519Field.mul(create2, pointPrecomp.ypx_h, create2);
        X25519Field.mul(pointAccum.f9825u, pointAccum.f9826v, create3);
        X25519Field.mul(create3, pointPrecomp.xyd, create3);
        X25519Field.apm(create2, create, iArr2, iArr);
        X25519Field.apm(pointAccum.f9829z, create3, create5, create4);
        X25519Field.carry(create5);
        X25519Field.mul(iArr, create4, pointAccum.f9827x);
        X25519Field.mul(create5, iArr2, pointAccum.f9828y);
        X25519Field.mul(create4, create5, pointAccum.f9829z);
    }

    private static void pointAddVar(boolean z, PointExt pointExt, PointAccum pointAccum) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int[] create = X25519Field.create();
        int[] create2 = X25519Field.create();
        int[] create3 = X25519Field.create();
        int[] create4 = X25519Field.create();
        int[] iArr5 = pointAccum.f9825u;
        int[] create5 = X25519Field.create();
        int[] create6 = X25519Field.create();
        int[] iArr6 = pointAccum.f9826v;
        if (z) {
            iArr2 = create3;
            iArr = create4;
            iArr4 = create5;
            iArr3 = create6;
        } else {
            iArr = create3;
            iArr2 = create4;
            iArr3 = create5;
            iArr4 = create6;
        }
        X25519Field.apm(pointAccum.f9828y, pointAccum.f9827x, create2, create);
        X25519Field.apm(pointExt.f9834y, pointExt.f9833x, iArr2, iArr);
        X25519Field.mul(create, create3, create);
        X25519Field.mul(create2, create4, create2);
        X25519Field.mul(pointAccum.f9825u, pointAccum.f9826v, create3);
        X25519Field.mul(create3, pointExt.f9832t, create3);
        X25519Field.mul(create3, C_d2, create3);
        X25519Field.mul(pointAccum.f9829z, pointExt.f9835z, create4);
        X25519Field.add(create4, create4, create4);
        X25519Field.apm(create2, create, iArr6, iArr5);
        X25519Field.apm(create4, create3, iArr4, iArr3);
        X25519Field.carry(iArr4);
        X25519Field.mul(iArr5, create5, pointAccum.f9827x);
        X25519Field.mul(create6, iArr6, pointAccum.f9828y);
        X25519Field.mul(create5, create6, pointAccum.f9829z);
    }

    private static void pointAddVar(boolean z, PointExt pointExt, PointExt pointExt2, PointExt pointExt3) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int[] create = X25519Field.create();
        int[] create2 = X25519Field.create();
        int[] create3 = X25519Field.create();
        int[] create4 = X25519Field.create();
        int[] create5 = X25519Field.create();
        int[] create6 = X25519Field.create();
        int[] create7 = X25519Field.create();
        int[] create8 = X25519Field.create();
        if (z) {
            iArr2 = create3;
            iArr = create4;
            iArr4 = create6;
            iArr3 = create7;
        } else {
            iArr = create3;
            iArr2 = create4;
            iArr3 = create6;
            iArr4 = create7;
        }
        X25519Field.apm(pointExt.f9834y, pointExt.f9833x, create2, create);
        X25519Field.apm(pointExt2.f9834y, pointExt2.f9833x, iArr2, iArr);
        X25519Field.mul(create, create3, create);
        X25519Field.mul(create2, create4, create2);
        X25519Field.mul(pointExt.f9832t, pointExt2.f9832t, create3);
        X25519Field.mul(create3, C_d2, create3);
        X25519Field.mul(pointExt.f9835z, pointExt2.f9835z, create4);
        X25519Field.add(create4, create4, create4);
        X25519Field.apm(create2, create, create8, create5);
        X25519Field.apm(create4, create3, iArr4, iArr3);
        X25519Field.carry(iArr4);
        X25519Field.mul(create5, create6, pointExt3.f9833x);
        X25519Field.mul(create7, create8, pointExt3.f9834y);
        X25519Field.mul(create6, create7, pointExt3.f9835z);
        X25519Field.mul(create5, create8, pointExt3.f9832t);
    }

    private static PointExt pointCopy(PointAccum pointAccum) {
        PointExt pointExt = new PointExt();
        X25519Field.copy(pointAccum.f9827x, 0, pointExt.f9833x, 0);
        X25519Field.copy(pointAccum.f9828y, 0, pointExt.f9834y, 0);
        X25519Field.copy(pointAccum.f9829z, 0, pointExt.f9835z, 0);
        X25519Field.mul(pointAccum.f9825u, pointAccum.f9826v, pointExt.f9832t);
        return pointExt;
    }

    private static PointExt pointCopy(PointAffine pointAffine) {
        PointExt pointExt = new PointExt();
        X25519Field.copy(pointAffine.f9830x, 0, pointExt.f9833x, 0);
        X25519Field.copy(pointAffine.f9831y, 0, pointExt.f9834y, 0);
        pointExtendXY(pointExt);
        return pointExt;
    }

    private static PointExt pointCopy(PointExt pointExt) {
        PointExt pointExt2 = new PointExt();
        pointCopy(pointExt, pointExt2);
        return pointExt2;
    }

    private static void pointCopy(PointAffine pointAffine, PointAccum pointAccum) {
        X25519Field.copy(pointAffine.f9830x, 0, pointAccum.f9827x, 0);
        X25519Field.copy(pointAffine.f9831y, 0, pointAccum.f9828y, 0);
        pointExtendXY(pointAccum);
    }

    private static void pointCopy(PointExt pointExt, PointExt pointExt2) {
        X25519Field.copy(pointExt.f9833x, 0, pointExt2.f9833x, 0);
        X25519Field.copy(pointExt.f9834y, 0, pointExt2.f9834y, 0);
        X25519Field.copy(pointExt.f9835z, 0, pointExt2.f9835z, 0);
        X25519Field.copy(pointExt.f9832t, 0, pointExt2.f9832t, 0);
    }

    private static void pointDouble(PointAccum pointAccum) {
        int[] create = X25519Field.create();
        int[] create2 = X25519Field.create();
        int[] create3 = X25519Field.create();
        int[] iArr = pointAccum.f9825u;
        int[] create4 = X25519Field.create();
        int[] create5 = X25519Field.create();
        int[] iArr2 = pointAccum.f9826v;
        X25519Field.sqr(pointAccum.f9827x, create);
        X25519Field.sqr(pointAccum.f9828y, create2);
        X25519Field.sqr(pointAccum.f9829z, create3);
        X25519Field.add(create3, create3, create3);
        X25519Field.apm(create, create2, iArr2, create5);
        X25519Field.add(pointAccum.f9827x, pointAccum.f9828y, iArr);
        X25519Field.sqr(iArr, iArr);
        X25519Field.sub(iArr2, iArr, iArr);
        X25519Field.add(create3, create5, create4);
        X25519Field.carry(create4);
        X25519Field.mul(iArr, create4, pointAccum.f9827x);
        X25519Field.mul(create5, iArr2, pointAccum.f9828y);
        X25519Field.mul(create4, create5, pointAccum.f9829z);
    }

    private static void pointExtendXY(PointAccum pointAccum) {
        X25519Field.one(pointAccum.f9829z);
        X25519Field.copy(pointAccum.f9827x, 0, pointAccum.f9825u, 0);
        X25519Field.copy(pointAccum.f9828y, 0, pointAccum.f9826v, 0);
    }

    private static void pointExtendXY(PointExt pointExt) {
        X25519Field.one(pointExt.f9835z);
        X25519Field.mul(pointExt.f9833x, pointExt.f9834y, pointExt.f9832t);
    }

    private static void pointLookup(int i, int i2, PointPrecomp pointPrecomp) {
        int i3 = i * 8 * 3 * 10;
        for (int i4 = 0; i4 < 8; i4++) {
            int i5 = ((i4 ^ i2) - 1) >> 31;
            X25519Field.cmov(i5, precompBase, i3, pointPrecomp.ypx_h, 0);
            int i6 = i3 + 10;
            X25519Field.cmov(i5, precompBase, i6, pointPrecomp.ymx_h, 0);
            int i7 = i6 + 10;
            X25519Field.cmov(i5, precompBase, i7, pointPrecomp.xyd, 0);
            i3 = i7 + 10;
        }
    }

    private static void pointLookup(int[] iArr, int i, PointExt pointExt) {
        int i2 = i * 40;
        X25519Field.copy(iArr, i2, pointExt.f9833x, 0);
        int i3 = i2 + 10;
        X25519Field.copy(iArr, i3, pointExt.f9834y, 0);
        int i4 = i3 + 10;
        X25519Field.copy(iArr, i4, pointExt.f9835z, 0);
        X25519Field.copy(iArr, i4 + 10, pointExt.f9832t, 0);
    }

    private static void pointLookup(int[] iArr, int i, int[] iArr2, PointExt pointExt) {
        int window4 = getWindow4(iArr, i);
        int i2 = (window4 >>> 3) ^ 1;
        int i3 = (window4 ^ (-i2)) & 7;
        int i4 = 0;
        for (int i5 = 0; i5 < 8; i5++) {
            int i6 = ((i5 ^ i3) - 1) >> 31;
            X25519Field.cmov(i6, iArr2, i4, pointExt.f9833x, 0);
            int i7 = i4 + 10;
            X25519Field.cmov(i6, iArr2, i7, pointExt.f9834y, 0);
            int i8 = i7 + 10;
            X25519Field.cmov(i6, iArr2, i8, pointExt.f9835z, 0);
            int i9 = i8 + 10;
            X25519Field.cmov(i6, iArr2, i9, pointExt.f9832t, 0);
            i4 = i9 + 10;
        }
        X25519Field.cnegate(i2, pointExt.f9833x);
        X25519Field.cnegate(i2, pointExt.f9832t);
    }

    private static int[] pointPrecomp(PointAffine pointAffine, int i) {
        PointExt pointCopy = pointCopy(pointAffine);
        PointExt pointCopy2 = pointCopy(pointCopy);
        pointAdd(pointCopy, pointCopy2);
        int[] createTable = X25519Field.createTable(i * 4);
        int i2 = 0;
        int i3 = 0;
        while (true) {
            X25519Field.copy(pointCopy.f9833x, 0, createTable, i2);
            int i4 = i2 + 10;
            X25519Field.copy(pointCopy.f9834y, 0, createTable, i4);
            int i5 = i4 + 10;
            X25519Field.copy(pointCopy.f9835z, 0, createTable, i5);
            int i6 = i5 + 10;
            X25519Field.copy(pointCopy.f9832t, 0, createTable, i6);
            i2 = i6 + 10;
            i3++;
            if (i3 == i) {
                return createTable;
            }
            pointAdd(pointCopy2, pointCopy);
        }
    }

    private static PointExt[] pointPrecompVar(PointExt pointExt, int i) {
        PointExt pointExt2 = new PointExt();
        pointAddVar(false, pointExt, pointExt, pointExt2);
        PointExt[] pointExtArr = new PointExt[i];
        pointExtArr[0] = pointCopy(pointExt);
        for (int i2 = 1; i2 < i; i2++) {
            PointExt pointExt3 = pointExtArr[i2 - 1];
            PointExt pointExt4 = new PointExt();
            pointExtArr[i2] = pointExt4;
            pointAddVar(false, pointExt3, pointExt2, pointExt4);
        }
        return pointExtArr;
    }

    private static void pointSetNeutral(PointAccum pointAccum) {
        X25519Field.zero(pointAccum.f9827x);
        X25519Field.one(pointAccum.f9828y);
        X25519Field.one(pointAccum.f9829z);
        X25519Field.zero(pointAccum.f9825u);
        X25519Field.one(pointAccum.f9826v);
    }

    private static void pointSetNeutral(PointExt pointExt) {
        X25519Field.zero(pointExt.f9833x);
        X25519Field.one(pointExt.f9834y);
        X25519Field.one(pointExt.f9835z);
        X25519Field.zero(pointExt.f9832t);
    }

    public static void precompute() {
        int i;
        synchronized (precompLock) {
            if (precompBase != null) {
                return;
            }
            PointExt pointExt = new PointExt();
            X25519Field.copy(B_x, 0, pointExt.f9833x, 0);
            X25519Field.copy(B_y, 0, pointExt.f9834y, 0);
            pointExtendXY(pointExt);
            precompBaseTable = pointPrecompVar(pointExt, 32);
            PointAccum pointAccum = new PointAccum();
            X25519Field.copy(B_x, 0, pointAccum.f9827x, 0);
            X25519Field.copy(B_y, 0, pointAccum.f9828y, 0);
            pointExtendXY(pointAccum);
            precompBase = X25519Field.createTable(192);
            int i2 = 0;
            int i3 = 0;
            while (i2 < 8) {
                PointExt[] pointExtArr = new PointExt[4];
                PointExt pointExt2 = new PointExt();
                pointSetNeutral(pointExt2);
                int i4 = 0;
                while (true) {
                    i = 1;
                    if (i4 >= 4) {
                        break;
                    }
                    pointAddVar(true, pointExt2, pointCopy(pointAccum), pointExt2);
                    pointDouble(pointAccum);
                    pointExtArr[i4] = pointCopy(pointAccum);
                    if (i2 + i4 != 10) {
                        while (i < 8) {
                            pointDouble(pointAccum);
                            i++;
                        }
                    }
                    i4++;
                }
                PointExt[] pointExtArr2 = new PointExt[8];
                pointExtArr2[0] = pointExt2;
                int i5 = 0;
                int i6 = 1;
                while (i5 < 3) {
                    int i7 = i << i5;
                    int i8 = i6;
                    int i9 = 0;
                    while (i9 < i7) {
                        PointExt pointExt3 = pointExtArr2[i8 - i7];
                        PointExt pointExt4 = pointExtArr[i5];
                        PointExt pointExt5 = new PointExt();
                        pointExtArr2[i8] = pointExt5;
                        pointAddVar(false, pointExt3, pointExt4, pointExt5);
                        i9++;
                        i8++;
                    }
                    i5++;
                    i6 = i8;
                    i = 1;
                }
                int i10 = i3;
                for (int i11 = 0; i11 < 8; i11++) {
                    PointExt pointExt6 = pointExtArr2[i11];
                    int[] create = X25519Field.create();
                    int[] create2 = X25519Field.create();
                    X25519Field.add(pointExt6.f9835z, pointExt6.f9835z, create);
                    X25519Field.inv(create, create2);
                    X25519Field.mul(pointExt6.f9833x, create2, create);
                    X25519Field.mul(pointExt6.f9834y, create2, create2);
                    PointPrecomp pointPrecomp = new PointPrecomp();
                    X25519Field.apm(create2, create, pointPrecomp.ypx_h, pointPrecomp.ymx_h);
                    X25519Field.mul(create, create2, pointPrecomp.xyd);
                    X25519Field.mul(pointPrecomp.xyd, C_d4, pointPrecomp.xyd);
                    X25519Field.normalize(pointPrecomp.ypx_h);
                    X25519Field.normalize(pointPrecomp.ymx_h);
                    X25519Field.copy(pointPrecomp.ypx_h, 0, precompBase, i10);
                    int i12 = i10 + 10;
                    X25519Field.copy(pointPrecomp.ymx_h, 0, precompBase, i12);
                    int i13 = i12 + 10;
                    X25519Field.copy(pointPrecomp.xyd, 0, precompBase, i13);
                    i10 = i13 + 10;
                }
                i2++;
                i3 = i10;
            }
        }
    }

    private static void pruneScalar(byte[] bArr, int i, byte[] bArr2) {
        System.arraycopy(bArr, i, bArr2, 0, 32);
        bArr2[0] = (byte) (bArr2[0] & 248);
        bArr2[31] = (byte) (bArr2[31] & Byte.MAX_VALUE);
        bArr2[31] = (byte) (bArr2[31] | 64);
    }

    private static byte[] reduceScalar(byte[] bArr) {
        long decode32 = decode32(bArr, 49) & 4294967295L;
        long decode322 = decode32(bArr, 56) & 4294967295L;
        long j = bArr[63] & 255;
        long decode24 = ((decode24(bArr, 60) << 4) & 4294967295L) + (decode322 >> 28);
        long j2 = decode322 & M28L;
        long decode323 = (decode32(bArr, 28) & 4294967295L) - (decode24 * (-50998291));
        long decode242 = (((decode24(bArr, 32) << 4) & 4294967295L) - (j * (-50998291))) - (decode24 * 19280294);
        long decode324 = ((decode32(bArr, 42) & 4294967295L) - (j * (-6428113))) - (decode24 * 5343);
        long decode243 = ((((decode24(bArr, 39) << 4) & 4294967295L) - (j * 127719000)) - (decode24 * (-6428113))) - (j2 * 5343);
        long decode244 = ((decode24(bArr, 53) << 4) & 4294967295L) + (decode32 >> 28);
        long j3 = decode32 & M28L;
        long decode325 = ((((decode32(bArr, 35) & 4294967295L) - (j * 19280294)) - (decode24 * 127719000)) - (j2 * (-6428113))) - (decode244 * 5343);
        long decode245 = ((((decode24(bArr, 25) << 4) & 4294967295L) - (j2 * (-50998291))) - (decode244 * 19280294)) - (j3 * 127719000);
        long j4 = ((decode242 - (j2 * 127719000)) - (decode244 * (-6428113))) - (j3 * 5343);
        long decode246 = (((decode24(bArr, 46) << 4) & 4294967295L) - (j * 5343)) + (decode324 >> 28);
        long j5 = (decode324 & M28L) + (decode243 >> 28);
        long decode247 = ((decode24(bArr, 11) << 4) & 4294967295L) - (j5 * (-50998291));
        long decode326 = ((decode32(bArr, 14) & 4294967295L) - (decode246 * (-50998291))) - (j5 * 19280294);
        long decode248 = ((((decode24(bArr, 18) << 4) & 4294967295L) - (j3 * (-50998291))) - (decode246 * 19280294)) - (j5 * 127719000);
        long decode327 = ((((decode32(bArr, 21) & 4294967295L) - (decode244 * (-50998291))) - (j3 * 19280294)) - (decode246 * 127719000)) - (j5 * (-6428113));
        long j6 = (decode245 - (decode246 * (-6428113))) - (j5 * 5343);
        long j7 = (decode243 & M28L) + (decode325 >> 28);
        long j8 = decode325 & M28L;
        long decode328 = (decode32(bArr, 7) & 4294967295L) - (j7 * (-50998291));
        long j9 = decode247 - (j7 * 19280294);
        long j10 = decode326 - (j7 * 127719000);
        long j11 = decode248 - (j7 * (-6428113));
        long j12 = decode327 - (j7 * 5343);
        long j13 = j8 + (j4 >> 28);
        long j14 = j4 & M28L;
        long decode249 = ((decode24(bArr, 4) << 4) & 4294967295L) - (j13 * (-50998291));
        long j15 = decode328 - (j13 * 19280294);
        long j16 = j9 - (j13 * 127719000);
        long j17 = j10 - (j13 * (-6428113));
        long j18 = j11 - (j13 * 5343);
        long j19 = ((((decode323 - (j2 * 19280294)) - (decode244 * 127719000)) - (j3 * (-6428113))) - (decode246 * 5343)) + (j6 >> 28);
        long j20 = j6 & M28L;
        long j21 = j19 & M28L;
        long j22 = j21 >>> 27;
        long j23 = j14 + (j19 >> 28) + j22;
        long decode329 = (decode32(bArr, 0) & 4294967295L) - (j23 * (-50998291));
        long j24 = (decode249 - (j23 * 19280294)) + (decode329 >> 28);
        long j25 = decode329 & M28L;
        long j26 = (j15 - (j23 * 127719000)) + (j24 >> 28);
        long j27 = j24 & M28L;
        long j28 = (j16 - (j23 * (-6428113))) + (j26 >> 28);
        long j29 = j26 & M28L;
        long j30 = (j17 - (j23 * 5343)) + (j28 >> 28);
        long j31 = j28 & M28L;
        long j32 = j18 + (j30 >> 28);
        long j33 = j30 & M28L;
        long j34 = j12 + (j32 >> 28);
        long j35 = j32 & M28L;
        long j36 = j20 + (j34 >> 28);
        long j37 = j34 & M28L;
        long j38 = j21 + (j36 >> 28);
        long j39 = j36 & M28L;
        long j40 = j38 >> 28;
        long j41 = j38 & M28L;
        long j42 = j40 - j22;
        long j43 = j25 + (j42 & (-50998291));
        long j44 = j27 + (j42 & 19280294) + (j43 >> 28);
        long j45 = j43 & M28L;
        long j46 = j29 + (j42 & 127719000) + (j44 >> 28);
        long j47 = j44 & M28L;
        long j48 = j31 + (j42 & (-6428113)) + (j46 >> 28);
        long j49 = j46 & M28L;
        long j50 = j33 + (j42 & 5343) + (j48 >> 28);
        long j51 = j48 & M28L;
        long j52 = j35 + (j50 >> 28);
        long j53 = j50 & M28L;
        long j54 = j37 + (j52 >> 28);
        long j55 = j52 & M28L;
        long j56 = j39 + (j54 >> 28);
        long j57 = j54 & M28L;
        long j58 = j41 + (j56 >> 28);
        long j59 = j56 & M28L;
        byte[] bArr2 = new byte[32];
        encode56(j45 | (j47 << 28), bArr2, 0);
        encode56((j51 << 28) | j49, bArr2, 7);
        encode56(j53 | (j55 << 28), bArr2, 14);
        encode56(j57 | (j59 << 28), bArr2, 21);
        encode32((int) j58, bArr2, 28);
        return bArr2;
    }

    private static void scalarMult(byte[] bArr, PointAffine pointAffine, PointAccum pointAccum) {
        precompute();
        int[] iArr = new int[8];
        decodeScalar(bArr, 0, iArr);
        Nat.shiftDownBits(8, iArr, 3, 1);
        Nat.cadd(8, 1 & (~iArr[0]), iArr, f9818L, iArr);
        Nat.shiftDownBit(8, iArr, 0);
        pointCopy(pointAffine, pointAccum);
        int[] pointPrecomp = pointPrecomp(pointAffine, 8);
        PointExt pointExt = new PointExt();
        pointLookup(pointPrecomp, 7, pointExt);
        pointAdd(pointExt, pointAccum);
        int i = 62;
        while (true) {
            pointLookup(iArr, i, pointPrecomp, pointExt);
            pointAdd(pointExt, pointAccum);
            pointDouble(pointAccum);
            pointDouble(pointAccum);
            pointDouble(pointAccum);
            i--;
            if (i < 0) {
                return;
            } else {
                pointDouble(pointAccum);
            }
        }
    }

    private static void scalarMultBase(byte[] bArr, PointAccum pointAccum) {
        precompute();
        pointSetNeutral(pointAccum);
        int[] iArr = new int[8];
        decodeScalar(bArr, 0, iArr);
        Nat.cadd(8, (~iArr[0]) & 1, iArr, f9818L, iArr);
        Nat.shiftDownBit(8, iArr, 1);
        for (int i = 0; i < 8; i++) {
            iArr[i] = Interleave.shuffle2(iArr[i]);
        }
        PointPrecomp pointPrecomp = new PointPrecomp();
        int i2 = 28;
        while (true) {
            for (int i3 = 0; i3 < 8; i3++) {
                int i4 = iArr[i3] >>> i2;
                int i5 = (i4 >>> 3) & 1;
                pointLookup(i3, (i4 ^ (-i5)) & 7, pointPrecomp);
                X25519Field.cswap(i5, pointPrecomp.ypx_h, pointPrecomp.ymx_h);
                X25519Field.cnegate(i5, pointPrecomp.xyd);
                pointAddPrecomp(pointPrecomp, pointAccum);
            }
            i2 -= 4;
            if (i2 < 0) {
                return;
            } else {
                pointDouble(pointAccum);
            }
        }
    }

    private static void scalarMultBaseEncoded(byte[] bArr, byte[] bArr2, int i) {
        PointAccum pointAccum = new PointAccum();
        scalarMultBase(bArr, pointAccum);
        if (encodePoint(pointAccum, bArr2, i) == 0) {
            throw new IllegalStateException();
        }
    }

    public static void scalarMultBaseYZ(X25519.Friend friend, byte[] bArr, int i, int[] iArr, int[] iArr2) {
        if (friend == null) {
            throw new NullPointerException("This method is only for use by X25519");
        }
        byte[] bArr2 = new byte[32];
        pruneScalar(bArr, i, bArr2);
        PointAccum pointAccum = new PointAccum();
        scalarMultBase(bArr2, pointAccum);
        if (checkPoint(pointAccum.f9827x, pointAccum.f9828y, pointAccum.f9829z) == 0) {
            throw new IllegalStateException();
        }
        X25519Field.copy(pointAccum.f9828y, 0, iArr, 0);
        X25519Field.copy(pointAccum.f9829z, 0, iArr2, 0);
    }

    private static void scalarMultStrausVar(int[] iArr, int[] iArr2, PointAffine pointAffine, PointAccum pointAccum) {
        precompute();
        byte[] wnaf = getWNAF(iArr, 7);
        byte[] wnaf2 = getWNAF(iArr2, 5);
        PointExt[] pointPrecompVar = pointPrecompVar(pointCopy(pointAffine), 8);
        pointSetNeutral(pointAccum);
        int i = 252;
        while (true) {
            byte b = wnaf[i];
            if (b != 0) {
                int i2 = b >> Ascii.f1926US;
                pointAddVar(i2 != 0, precompBaseTable[(b ^ i2) >>> 1], pointAccum);
            }
            byte b2 = wnaf2[i];
            if (b2 != 0) {
                int i3 = b2 >> Ascii.f1926US;
                pointAddVar(i3 != 0, pointPrecompVar[(b2 ^ i3) >>> 1], pointAccum);
            }
            i--;
            if (i < 0) {
                return;
            } else {
                pointDouble(pointAccum);
            }
        }
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, int i2, int i3, byte[] bArr3, int i4) {
        implSign(bArr, i, null, (byte) 0, bArr2, i2, i3, bArr3, i4);
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3, int i4, byte[] bArr4, int i5) {
        implSign(bArr, i, bArr2, i2, null, (byte) 0, bArr3, i3, i4, bArr4, i5);
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, int i4, byte[] bArr5, int i5) {
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 0, bArr4, i3, i4, bArr5, i5);
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2, int i3, byte[] bArr4, int i4) {
        implSign(bArr, i, bArr2, (byte) 0, bArr3, i2, i3, bArr4, i4);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, Digest digest, byte[] bArr4, int i3) {
        byte[] bArr5 = new byte[64];
        if (64 != digest.doFinal(bArr5, 0)) {
            throw new IllegalArgumentException("ph");
        }
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr5, 0, bArr5.length, bArr4, i3);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, byte[] bArr5, int i4) {
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, i3, 64, bArr5, i4);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, Digest digest, byte[] bArr3, int i2) {
        byte[] bArr4 = new byte[64];
        if (64 != digest.doFinal(bArr4, 0)) {
            throw new IllegalArgumentException("ph");
        }
        implSign(bArr, i, bArr2, (byte) 1, bArr4, 0, bArr4.length, bArr3, i2);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2, byte[] bArr4, int i3) {
        implSign(bArr, i, bArr2, (byte) 1, bArr3, i2, 64, bArr4, i3);
    }

    public static boolean verify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3, int i4) {
        return implVerify(bArr, i, bArr2, i2, null, (byte) 0, bArr3, i3, i4);
    }

    public static boolean verify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, int i4) {
        return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 0, bArr4, i3, i4);
    }

    public static boolean verifyPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, Digest digest) {
        byte[] bArr4 = new byte[64];
        if (64 == digest.doFinal(bArr4, 0)) {
            return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, 0, bArr4.length);
        }
        throw new IllegalArgumentException("ph");
    }

    public static boolean verifyPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3) {
        return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, i3, 64);
    }
}
