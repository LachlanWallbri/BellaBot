package org.apache.commons.codec.digest;

import org.apache.commons.codec.binary.StringUtils;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes5.dex */
public final class MurmurHash3 {

    /* renamed from: C1 */
    private static final long f8897C1 = -8663945395140668459L;
    private static final int C1_32 = -862048943;

    /* renamed from: C2 */
    private static final long f8898C2 = 5545529020109919103L;
    private static final int C2_32 = 461845907;
    public static final int DEFAULT_SEED = 104729;
    static final int INTEGER_BYTES = 4;
    static final int LONG_BYTES = 8;

    /* renamed from: M */
    private static final int f8899M = 5;
    private static final int M_32 = 5;

    /* renamed from: N1 */
    private static final int f8900N1 = 1390208809;

    /* renamed from: N2 */
    private static final int f8901N2 = 944331445;

    @Deprecated
    public static final long NULL_HASHCODE = 2862933555777941757L;
    private static final int N_32 = -430675100;

    /* renamed from: R1 */
    private static final int f8902R1 = 31;
    private static final int R1_32 = 15;

    /* renamed from: R2 */
    private static final int f8903R2 = 27;
    private static final int R2_32 = 13;

    /* renamed from: R3 */
    private static final int f8904R3 = 33;
    static final int SHORT_BYTES = 2;

    /* JADX INFO: Access modifiers changed from: private */
    public static int fmix32(int i) {
        int i2 = (i ^ (i >>> 16)) * (-2048144789);
        int i3 = (i2 ^ (i2 >>> 13)) * (-1028477387);
        return i3 ^ (i3 >>> 16);
    }

    private static long fmix64(long j) {
        long j2 = (j ^ (j >>> 33)) * (-49064778989728563L);
        long j3 = (j2 ^ (j2 >>> 33)) * (-4265267296055464877L);
        return j3 ^ (j3 >>> 33);
    }

    private MurmurHash3() {
    }

    public static int hash32(long j, long j2) {
        return hash32(j, j2, DEFAULT_SEED);
    }

    public static int hash32(long j, long j2, int i) {
        long reverseBytes = Long.reverseBytes(j);
        long reverseBytes2 = Long.reverseBytes(j2);
        int i2 = (int) reverseBytes2;
        return fmix32(mix32((int) (reverseBytes2 >>> 32), mix32(i2, mix32((int) (reverseBytes >>> 32), mix32((int) reverseBytes, i)))) ^ 16);
    }

    public static int hash32(long j) {
        return hash32(j, DEFAULT_SEED);
    }

    public static int hash32(long j, int i) {
        long reverseBytes = Long.reverseBytes(j);
        return fmix32(mix32((int) (reverseBytes >>> 32), mix32((int) reverseBytes, i)) ^ 8);
    }

    @Deprecated
    public static int hash32(byte[] bArr) {
        return hash32(bArr, 0, bArr.length, DEFAULT_SEED);
    }

    @Deprecated
    public static int hash32(String str) {
        byte[] bytesUtf8 = StringUtils.getBytesUtf8(str);
        return hash32(bytesUtf8, 0, bytesUtf8.length, DEFAULT_SEED);
    }

    @Deprecated
    public static int hash32(byte[] bArr, int i) {
        return hash32(bArr, i, DEFAULT_SEED);
    }

    @Deprecated
    public static int hash32(byte[] bArr, int i, int i2) {
        return hash32(bArr, 0, i, i2);
    }

    @Deprecated
    public static int hash32(byte[] bArr, int i, int i2, int i3) {
        int i4 = i2 >> 2;
        int i5 = i3;
        for (int i6 = 0; i6 < i4; i6++) {
            i5 = mix32(getLittleEndianInt(bArr, (i6 << 2) + i), i5);
        }
        int i7 = (i4 << 2) + i;
        int i8 = (i + i2) - i7;
        if (i8 != 1) {
            if (i8 != 2) {
                r1 = i8 == 3 ? 0 ^ (bArr[i7 + 2] << 16) : 0;
                return fmix32(i5 ^ i2);
            }
            r1 ^= bArr[i7 + 1] << 8;
        }
        i5 ^= Integer.rotateLeft((bArr[i7] ^ r1) * C1_32, 15) * C2_32;
        return fmix32(i5 ^ i2);
    }

    public static int hash32x86(byte[] bArr) {
        return hash32x86(bArr, 0, bArr.length, 0);
    }

    public static int hash32x86(byte[] bArr, int i, int i2, int i3) {
        int i4 = i2 >> 2;
        int i5 = i3;
        for (int i6 = 0; i6 < i4; i6++) {
            i5 = mix32(getLittleEndianInt(bArr, (i6 << 2) + i), i5);
        }
        int i7 = (i4 << 2) + i;
        int i8 = (i + i2) - i7;
        if (i8 != 1) {
            if (i8 != 2) {
                r1 = i8 == 3 ? 0 ^ ((bArr[i7 + 2] & 255) << 16) : 0;
                return fmix32(i5 ^ i2);
            }
            r1 ^= (bArr[i7 + 1] & 255) << 8;
        }
        i5 ^= Integer.rotateLeft(((bArr[i7] & 255) ^ r1) * C1_32, 15) * C2_32;
        return fmix32(i5 ^ i2);
    }

    @Deprecated
    public static long hash64(long j) {
        return fmix64(((Long.rotateLeft((Long.rotateLeft(Long.reverseBytes(j) * f8897C1, 31) * f8898C2) ^ 104729, 27) * 5) + 1390208809) ^ 8);
    }

    @Deprecated
    public static long hash64(int i) {
        return fmix64(((Long.rotateLeft((Integer.reverseBytes(i) & 4294967295L) * f8897C1, 31) * f8898C2) ^ 104729) ^ 4);
    }

    @Deprecated
    public static long hash64(short s) {
        return fmix64(((Long.rotateLeft(((((s & 255) << 8) ^ 0) ^ (255 & ((s & 65280) >> 8))) * f8897C1, 31) * f8898C2) ^ 104729) ^ 2);
    }

    @Deprecated
    public static long hash64(byte[] bArr) {
        return hash64(bArr, 0, bArr.length, DEFAULT_SEED);
    }

    @Deprecated
    public static long hash64(byte[] bArr, int i, int i2) {
        return hash64(bArr, i, i2, DEFAULT_SEED);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Deprecated
    public static long hash64(byte[] bArr, int i, int i2, int i3) {
        long j = i3;
        int i4 = i2 >> 3;
        for (int i5 = 0; i5 < i4; i5++) {
            j = (Long.rotateLeft(j ^ (Long.rotateLeft(getLittleEndianLong(bArr, i + (i5 << 3)) * f8897C1, 31) * f8898C2), 27) * 5) + 1390208809;
        }
        long j2 = 0;
        switch ((i + i2) - (i + (i4 << 3))) {
            case 1:
                j ^= Long.rotateLeft(((bArr[r4] & 255) ^ j2) * f8897C1, 31) * f8898C2;
                break;
            case 2:
                j2 ^= (bArr[r4 + 1] & 255) << 8;
                j ^= Long.rotateLeft(((bArr[r4] & 255) ^ j2) * f8897C1, 31) * f8898C2;
                break;
            case 3:
                j2 ^= (bArr[r4 + 2] & 255) << 16;
                j2 ^= (bArr[r4 + 1] & 255) << 8;
                j ^= Long.rotateLeft(((bArr[r4] & 255) ^ j2) * f8897C1, 31) * f8898C2;
                break;
            case 4:
                j2 ^= (bArr[r4 + 3] & 255) << 24;
                j2 ^= (bArr[r4 + 2] & 255) << 16;
                j2 ^= (bArr[r4 + 1] & 255) << 8;
                j ^= Long.rotateLeft(((bArr[r4] & 255) ^ j2) * f8897C1, 31) * f8898C2;
                break;
            case 5:
                j2 ^= (bArr[r4 + 4] & 255) << 32;
                j2 ^= (bArr[r4 + 3] & 255) << 24;
                j2 ^= (bArr[r4 + 2] & 255) << 16;
                j2 ^= (bArr[r4 + 1] & 255) << 8;
                j ^= Long.rotateLeft(((bArr[r4] & 255) ^ j2) * f8897C1, 31) * f8898C2;
                break;
            case 6:
                j2 ^= (bArr[r4 + 5] & 255) << 40;
                j2 ^= (bArr[r4 + 4] & 255) << 32;
                j2 ^= (bArr[r4 + 3] & 255) << 24;
                j2 ^= (bArr[r4 + 2] & 255) << 16;
                j2 ^= (bArr[r4 + 1] & 255) << 8;
                j ^= Long.rotateLeft(((bArr[r4] & 255) ^ j2) * f8897C1, 31) * f8898C2;
                break;
            case 7:
                j2 = 0 ^ ((bArr[r4 + 6] & 255) << 48);
                j2 ^= (bArr[r4 + 5] & 255) << 40;
                j2 ^= (bArr[r4 + 4] & 255) << 32;
                j2 ^= (bArr[r4 + 3] & 255) << 24;
                j2 ^= (bArr[r4 + 2] & 255) << 16;
                j2 ^= (bArr[r4 + 1] & 255) << 8;
                j ^= Long.rotateLeft(((bArr[r4] & 255) ^ j2) * f8897C1, 31) * f8898C2;
                break;
        }
        return fmix64(i2 ^ j);
    }

    public static long[] hash128(byte[] bArr) {
        return hash128(bArr, 0, bArr.length, DEFAULT_SEED);
    }

    public static long[] hash128x64(byte[] bArr) {
        return hash128x64(bArr, 0, bArr.length, 0);
    }

    @Deprecated
    public static long[] hash128(String str) {
        byte[] bytesUtf8 = StringUtils.getBytesUtf8(str);
        return hash128(bytesUtf8, 0, bytesUtf8.length, DEFAULT_SEED);
    }

    @Deprecated
    public static long[] hash128(byte[] bArr, int i, int i2, int i3) {
        return hash128x64Internal(bArr, i, i2, i3);
    }

    public static long[] hash128x64(byte[] bArr, int i, int i2, int i3) {
        return hash128x64Internal(bArr, i, i2, i3 & 4294967295L);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static long[] hash128x64Internal(byte[] bArr, int i, int i2, long j) {
        long j2;
        long j3;
        long j4;
        long j5;
        long j6;
        long j7;
        byte[] bArr2 = bArr;
        int i3 = i2 >> 4;
        long j8 = j;
        long j9 = j8;
        int i4 = 0;
        while (i4 < i3) {
            int i5 = i + (i4 << 4);
            long littleEndianLong = getLittleEndianLong(bArr2, i5);
            long littleEndianLong2 = getLittleEndianLong(bArr2, i5 + 8);
            long rotateLeft = ((Long.rotateLeft((Long.rotateLeft(littleEndianLong * f8897C1, 31) * f8898C2) ^ j8, 27) + j9) * 5) + 1390208809;
            j9 = ((Long.rotateLeft(j9 ^ (Long.rotateLeft(f8898C2 * littleEndianLong2, 33) * f8897C1), 31) + rotateLeft) * 5) + 944331445;
            i4++;
            j8 = rotateLeft;
            bArr2 = bArr;
        }
        long j10 = 0;
        switch ((i + i2) - (i + (i3 << 4))) {
            case 1:
                j8 ^= Long.rotateLeft((j10 ^ (bArr[r0] & 255)) * f8897C1, 31) * f8898C2;
                break;
            case 2:
                j10 ^= (bArr[r0 + 1] & 255) << 8;
                j8 ^= Long.rotateLeft((j10 ^ (bArr[r0] & 255)) * f8897C1, 31) * f8898C2;
                break;
            case 3:
                j10 ^= (bArr[r0 + 2] & 255) << 16;
                j10 ^= (bArr[r0 + 1] & 255) << 8;
                j8 ^= Long.rotateLeft((j10 ^ (bArr[r0] & 255)) * f8897C1, 31) * f8898C2;
                break;
            case 4:
                j10 ^= (bArr[r0 + 3] & 255) << 24;
                j10 ^= (bArr[r0 + 2] & 255) << 16;
                j10 ^= (bArr[r0 + 1] & 255) << 8;
                j8 ^= Long.rotateLeft((j10 ^ (bArr[r0] & 255)) * f8897C1, 31) * f8898C2;
                break;
            case 5:
                j10 ^= (bArr[r0 + 4] & 255) << 32;
                j10 ^= (bArr[r0 + 3] & 255) << 24;
                j10 ^= (bArr[r0 + 2] & 255) << 16;
                j10 ^= (bArr[r0 + 1] & 255) << 8;
                j8 ^= Long.rotateLeft((j10 ^ (bArr[r0] & 255)) * f8897C1, 31) * f8898C2;
                break;
            case 6:
                j10 ^= (bArr[r0 + 5] & 255) << 40;
                j10 ^= (bArr[r0 + 4] & 255) << 32;
                j10 ^= (bArr[r0 + 3] & 255) << 24;
                j10 ^= (bArr[r0 + 2] & 255) << 16;
                j10 ^= (bArr[r0 + 1] & 255) << 8;
                j8 ^= Long.rotateLeft((j10 ^ (bArr[r0] & 255)) * f8897C1, 31) * f8898C2;
                break;
            case 7:
                j10 ^= (bArr[r0 + 6] & 255) << 48;
                j10 ^= (bArr[r0 + 5] & 255) << 40;
                j10 ^= (bArr[r0 + 4] & 255) << 32;
                j10 ^= (bArr[r0 + 3] & 255) << 24;
                j10 ^= (bArr[r0 + 2] & 255) << 16;
                j10 ^= (bArr[r0 + 1] & 255) << 8;
                j8 ^= Long.rotateLeft((j10 ^ (bArr[r0] & 255)) * f8897C1, 31) * f8898C2;
                break;
            case 8:
                j10 = 0 ^ ((bArr[r0 + 7] & 255) << 56);
                j10 ^= (bArr[r0 + 6] & 255) << 48;
                j10 ^= (bArr[r0 + 5] & 255) << 40;
                j10 ^= (bArr[r0 + 4] & 255) << 32;
                j10 ^= (bArr[r0 + 3] & 255) << 24;
                j10 ^= (bArr[r0 + 2] & 255) << 16;
                j10 ^= (bArr[r0 + 1] & 255) << 8;
                j8 ^= Long.rotateLeft((j10 ^ (bArr[r0] & 255)) * f8897C1, 31) * f8898C2;
                break;
            case 9:
                j2 = 0;
                j9 ^= Long.rotateLeft((j2 ^ (bArr[r0 + 8] & 255)) * f8898C2, 33) * f8897C1;
                j10 = 0 ^ ((bArr[r0 + 7] & 255) << 56);
                j10 ^= (bArr[r0 + 6] & 255) << 48;
                j10 ^= (bArr[r0 + 5] & 255) << 40;
                j10 ^= (bArr[r0 + 4] & 255) << 32;
                j10 ^= (bArr[r0 + 3] & 255) << 24;
                j10 ^= (bArr[r0 + 2] & 255) << 16;
                j10 ^= (bArr[r0 + 1] & 255) << 8;
                j8 ^= Long.rotateLeft((j10 ^ (bArr[r0] & 255)) * f8897C1, 31) * f8898C2;
                break;
            case 10:
                j3 = 0;
                j2 = j3 ^ ((bArr[r0 + 9] & 255) << 8);
                j9 ^= Long.rotateLeft((j2 ^ (bArr[r0 + 8] & 255)) * f8898C2, 33) * f8897C1;
                j10 = 0 ^ ((bArr[r0 + 7] & 255) << 56);
                j10 ^= (bArr[r0 + 6] & 255) << 48;
                j10 ^= (bArr[r0 + 5] & 255) << 40;
                j10 ^= (bArr[r0 + 4] & 255) << 32;
                j10 ^= (bArr[r0 + 3] & 255) << 24;
                j10 ^= (bArr[r0 + 2] & 255) << 16;
                j10 ^= (bArr[r0 + 1] & 255) << 8;
                j8 ^= Long.rotateLeft((j10 ^ (bArr[r0] & 255)) * f8897C1, 31) * f8898C2;
                break;
            case 11:
                j4 = 0;
                j3 = j4 ^ ((bArr[r0 + 10] & 255) << 16);
                j2 = j3 ^ ((bArr[r0 + 9] & 255) << 8);
                j9 ^= Long.rotateLeft((j2 ^ (bArr[r0 + 8] & 255)) * f8898C2, 33) * f8897C1;
                j10 = 0 ^ ((bArr[r0 + 7] & 255) << 56);
                j10 ^= (bArr[r0 + 6] & 255) << 48;
                j10 ^= (bArr[r0 + 5] & 255) << 40;
                j10 ^= (bArr[r0 + 4] & 255) << 32;
                j10 ^= (bArr[r0 + 3] & 255) << 24;
                j10 ^= (bArr[r0 + 2] & 255) << 16;
                j10 ^= (bArr[r0 + 1] & 255) << 8;
                j8 ^= Long.rotateLeft((j10 ^ (bArr[r0] & 255)) * f8897C1, 31) * f8898C2;
                break;
            case 12:
                j5 = 0;
                j4 = j5 ^ ((bArr[r0 + 11] & 255) << 24);
                j3 = j4 ^ ((bArr[r0 + 10] & 255) << 16);
                j2 = j3 ^ ((bArr[r0 + 9] & 255) << 8);
                j9 ^= Long.rotateLeft((j2 ^ (bArr[r0 + 8] & 255)) * f8898C2, 33) * f8897C1;
                j10 = 0 ^ ((bArr[r0 + 7] & 255) << 56);
                j10 ^= (bArr[r0 + 6] & 255) << 48;
                j10 ^= (bArr[r0 + 5] & 255) << 40;
                j10 ^= (bArr[r0 + 4] & 255) << 32;
                j10 ^= (bArr[r0 + 3] & 255) << 24;
                j10 ^= (bArr[r0 + 2] & 255) << 16;
                j10 ^= (bArr[r0 + 1] & 255) << 8;
                j8 ^= Long.rotateLeft((j10 ^ (bArr[r0] & 255)) * f8897C1, 31) * f8898C2;
                break;
            case 13:
                j6 = 0;
                j5 = j6 ^ ((bArr[r0 + 12] & 255) << 32);
                j4 = j5 ^ ((bArr[r0 + 11] & 255) << 24);
                j3 = j4 ^ ((bArr[r0 + 10] & 255) << 16);
                j2 = j3 ^ ((bArr[r0 + 9] & 255) << 8);
                j9 ^= Long.rotateLeft((j2 ^ (bArr[r0 + 8] & 255)) * f8898C2, 33) * f8897C1;
                j10 = 0 ^ ((bArr[r0 + 7] & 255) << 56);
                j10 ^= (bArr[r0 + 6] & 255) << 48;
                j10 ^= (bArr[r0 + 5] & 255) << 40;
                j10 ^= (bArr[r0 + 4] & 255) << 32;
                j10 ^= (bArr[r0 + 3] & 255) << 24;
                j10 ^= (bArr[r0 + 2] & 255) << 16;
                j10 ^= (bArr[r0 + 1] & 255) << 8;
                j8 ^= Long.rotateLeft((j10 ^ (bArr[r0] & 255)) * f8897C1, 31) * f8898C2;
                break;
            case 14:
                j7 = 0;
                j6 = ((bArr[r0 + 13] & 255) << 40) ^ j7;
                j5 = j6 ^ ((bArr[r0 + 12] & 255) << 32);
                j4 = j5 ^ ((bArr[r0 + 11] & 255) << 24);
                j3 = j4 ^ ((bArr[r0 + 10] & 255) << 16);
                j2 = j3 ^ ((bArr[r0 + 9] & 255) << 8);
                j9 ^= Long.rotateLeft((j2 ^ (bArr[r0 + 8] & 255)) * f8898C2, 33) * f8897C1;
                j10 = 0 ^ ((bArr[r0 + 7] & 255) << 56);
                j10 ^= (bArr[r0 + 6] & 255) << 48;
                j10 ^= (bArr[r0 + 5] & 255) << 40;
                j10 ^= (bArr[r0 + 4] & 255) << 32;
                j10 ^= (bArr[r0 + 3] & 255) << 24;
                j10 ^= (bArr[r0 + 2] & 255) << 16;
                j10 ^= (bArr[r0 + 1] & 255) << 8;
                j8 ^= Long.rotateLeft((j10 ^ (bArr[r0] & 255)) * f8897C1, 31) * f8898C2;
                break;
            case 15:
                j7 = ((bArr[r0 + 14] & 255) << 48) ^ 0;
                j6 = ((bArr[r0 + 13] & 255) << 40) ^ j7;
                j5 = j6 ^ ((bArr[r0 + 12] & 255) << 32);
                j4 = j5 ^ ((bArr[r0 + 11] & 255) << 24);
                j3 = j4 ^ ((bArr[r0 + 10] & 255) << 16);
                j2 = j3 ^ ((bArr[r0 + 9] & 255) << 8);
                j9 ^= Long.rotateLeft((j2 ^ (bArr[r0 + 8] & 255)) * f8898C2, 33) * f8897C1;
                j10 = 0 ^ ((bArr[r0 + 7] & 255) << 56);
                j10 ^= (bArr[r0 + 6] & 255) << 48;
                j10 ^= (bArr[r0 + 5] & 255) << 40;
                j10 ^= (bArr[r0 + 4] & 255) << 32;
                j10 ^= (bArr[r0 + 3] & 255) << 24;
                j10 ^= (bArr[r0 + 2] & 255) << 16;
                j10 ^= (bArr[r0 + 1] & 255) << 8;
                j8 ^= Long.rotateLeft((j10 ^ (bArr[r0] & 255)) * f8897C1, 31) * f8898C2;
                break;
        }
        long j11 = i2;
        long j12 = j8 ^ j11;
        long j13 = j11 ^ j9;
        long j14 = j12 + j13;
        long j15 = j13 + j14;
        long fmix64 = fmix64(j14);
        long fmix642 = fmix64(j15);
        long j16 = fmix64 + fmix642;
        return new long[]{j16, fmix642 + j16};
    }

    private static long getLittleEndianLong(byte[] bArr, int i) {
        return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getLittleEndianInt(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int mix32(int i, int i2) {
        return (Integer.rotateLeft((Integer.rotateLeft(i * C1_32, 15) * C2_32) ^ i2, 13) * 5) + N_32;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes5.dex */
    public static class IncrementalHash32x86 {
        private static final int BLOCK_SIZE = 4;
        private int hash;
        private int totalLen;
        private final byte[] unprocessed = new byte[3];
        private int unprocessedLength;

        private static int orBytes(byte b, byte b2, byte b3, byte b4) {
            return (b & 255) | ((b2 & 255) << 8) | ((b3 & 255) << 16) | ((b4 & 255) << 24);
        }

        public final void start(int i) {
            this.totalLen = 0;
            this.unprocessedLength = 0;
            this.hash = i;
        }

        public final void add(byte[] bArr, int i, int i2) {
            int orBytes;
            if (i2 <= 0) {
                return;
            }
            this.totalLen += i2;
            int i3 = this.unprocessedLength;
            if ((i3 + i2) - 4 < 0) {
                System.arraycopy(bArr, i, this.unprocessed, i3, i2);
                this.unprocessedLength += i2;
                return;
            }
            if (i3 > 0) {
                if (i3 == 1) {
                    orBytes = orBytes(this.unprocessed[0], bArr[i], bArr[i + 1], bArr[i + 2]);
                } else if (i3 == 2) {
                    byte[] bArr2 = this.unprocessed;
                    orBytes = orBytes(bArr2[0], bArr2[1], bArr[i], bArr[i + 1]);
                } else if (i3 == 3) {
                    byte[] bArr3 = this.unprocessed;
                    orBytes = orBytes(bArr3[0], bArr3[1], bArr3[2], bArr[i]);
                } else {
                    throw new IllegalStateException("Unprocessed length should be 1, 2, or 3: " + this.unprocessedLength);
                }
                this.hash = MurmurHash3.mix32(orBytes, this.hash);
                int i4 = 4 - this.unprocessedLength;
                i += i4;
                i2 -= i4;
            }
            int i5 = i2 >> 2;
            for (int i6 = 0; i6 < i5; i6++) {
                this.hash = MurmurHash3.mix32(MurmurHash3.getLittleEndianInt(bArr, (i6 << 2) + i), this.hash);
            }
            int i7 = i5 << 2;
            this.unprocessedLength = i2 - i7;
            int i8 = this.unprocessedLength;
            if (i8 != 0) {
                System.arraycopy(bArr, i + i7, this.unprocessed, 0, i8);
            }
        }

        public final int end() {
            return finalise(this.hash, this.unprocessedLength, this.unprocessed, this.totalLen);
        }

        int finalise(int i, int i2, byte[] bArr, int i3) {
            int i4;
            int i5;
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 == 3) {
                        i5 = ((bArr[2] & 255) << 16) ^ 0;
                    }
                    return MurmurHash3.fmix32(i ^ i3);
                }
                i5 = 0;
                i4 = i5 ^ ((bArr[1] & 255) << 8);
            } else {
                i4 = 0;
            }
            i ^= Integer.rotateLeft((i4 ^ (bArr[0] & 255)) * MurmurHash3.C1_32, 15) * MurmurHash3.C2_32;
            return MurmurHash3.fmix32(i ^ i3);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    @Deprecated
    /* loaded from: classes5.dex */
    public static class IncrementalHash32 extends IncrementalHash32x86 {
        @Override // org.apache.commons.codec.digest.MurmurHash3.IncrementalHash32x86
        @Deprecated
        int finalise(int i, int i2, byte[] bArr, int i3) {
            int i4;
            int i5;
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 == 3) {
                        i5 = (bArr[2] << 16) ^ 0;
                    }
                    return MurmurHash3.fmix32(i ^ i3);
                }
                i5 = 0;
                i4 = i5 ^ (bArr[1] << 8);
            } else {
                i4 = 0;
            }
            i ^= Integer.rotateLeft((i4 ^ bArr[0]) * MurmurHash3.C1_32, 15) * MurmurHash3.C2_32;
            return MurmurHash3.fmix32(i ^ i3);
        }
    }
}
