package org.bouncycastle.pqc.crypto.qteslarnd1;

import com.pudutech.mirsdk.config.MapFilePathConfig;

/* loaded from: classes9.dex */
class Pack {
    Pack() {
    }

    public static void decodePrivateKeyI(byte[] bArr, short[] sArr, short[] sArr2, byte[] bArr2) {
        int i = 0;
        for (int i2 = 0; i2 < 512; i2 += 4) {
            int i3 = i2 + 0;
            sArr[i3] = (short) (bArr2[i + 0] & 255);
            int i4 = i + 1;
            sArr[i3] = (short) (((short) (((bArr2[i4] & 255) << 30) >> 22)) | sArr[i3]);
            int i5 = i2 + 1;
            sArr[i5] = (short) ((bArr2[i4] & 255) >> 2);
            int i6 = i + 2;
            sArr[i5] = (short) (((short) (((bArr2[i6] & 255) << 28) >> 22)) | sArr[i5]);
            int i7 = i2 + 2;
            sArr[i7] = (short) ((bArr2[i6] & 255) >> 4);
            int i8 = i + 3;
            sArr[i7] = (short) (((short) (((bArr2[i8] & 255) << 26) >> 22)) | sArr[i7]);
            int i9 = i2 + 3;
            sArr[i9] = (short) ((bArr2[i8] & 255) >> 6);
            sArr[i9] = (short) (((short) (bArr2[i + 4] << 2)) | sArr[i9]);
            i += 5;
        }
        for (int i10 = 0; i10 < 512; i10 += 4) {
            int i11 = i10 + 0;
            sArr2[i11] = (short) (bArr2[i + 0] & 255);
            int i12 = i + 1;
            sArr2[i11] = (short) (((short) (((bArr2[i12] & 255) << 30) >> 22)) | sArr2[i11]);
            int i13 = i10 + 1;
            sArr2[i13] = (short) ((bArr2[i12] & 255) >> 2);
            int i14 = i + 2;
            sArr2[i13] = (short) (((short) (((bArr2[i14] & 255) << 28) >> 22)) | sArr2[i13]);
            int i15 = i10 + 2;
            sArr2[i15] = (short) ((bArr2[i14] & 255) >> 4);
            int i16 = i + 3;
            sArr2[i15] = (short) (((short) (((bArr2[i16] & 255) << 26) >> 22)) | sArr2[i15]);
            int i17 = i10 + 3;
            sArr2[i17] = (short) ((bArr2[i16] & 255) >> 6);
            sArr2[i17] = (short) (((short) (bArr2[i + 4] << 2)) | sArr2[i17]);
            i += 5;
        }
        System.arraycopy(bArr2, 1280, bArr, 0, 64);
    }

    public static void decodePrivateKeyIIISize(byte[] bArr, short[] sArr, short[] sArr2, byte[] bArr2) {
        for (int i = 0; i < 1024; i++) {
            sArr[i] = bArr2[i];
        }
        for (int i2 = 0; i2 < 1024; i2++) {
            sArr2[i2] = bArr2[i2 + 1024];
        }
        System.arraycopy(bArr2, 2048, bArr, 0, 64);
    }

    public static void decodePrivateKeyIIISpeed(byte[] bArr, short[] sArr, short[] sArr2, byte[] bArr2) {
        int i = 0;
        for (int i2 = 0; i2 < 1024; i2 += 8) {
            int i3 = i2 + 0;
            sArr[i3] = (short) (bArr2[i + 0] & 255);
            int i4 = i + 1;
            sArr[i3] = (short) (((short) (((bArr2[i4] & 255) << 31) >> 23)) | sArr[i3]);
            int i5 = i2 + 1;
            sArr[i5] = (short) ((bArr2[i4] & 255) >> 1);
            int i6 = i + 2;
            sArr[i5] = (short) (((short) (((bArr2[i6] & 255) << 30) >> 23)) | sArr[i5]);
            int i7 = i2 + 2;
            sArr[i7] = (short) ((bArr2[i6] & 255) >> 2);
            int i8 = i + 3;
            sArr[i7] = (short) (((short) (((bArr2[i8] & 255) << 29) >> 23)) | sArr[i7]);
            int i9 = i2 + 3;
            sArr[i9] = (short) ((bArr2[i8] & 255) >> 3);
            int i10 = i + 4;
            sArr[i9] = (short) (((short) (((bArr2[i10] & 255) << 28) >> 23)) | sArr[i9]);
            int i11 = i2 + 4;
            sArr[i11] = (short) ((bArr2[i10] & 255) >> 4);
            int i12 = i + 5;
            sArr[i11] = (short) (((short) (((bArr2[i12] & 255) << 27) >> 23)) | sArr[i11]);
            int i13 = i2 + 5;
            sArr[i13] = (short) ((bArr2[i12] & 255) >> 5);
            int i14 = i + 6;
            sArr[i13] = (short) (((short) (((bArr2[i14] & 255) << 26) >> 23)) | sArr[i13]);
            int i15 = i2 + 6;
            sArr[i15] = (short) ((bArr2[i14] & 255) >> 6);
            int i16 = i + 7;
            sArr[i15] = (short) (((short) (((bArr2[i16] & 255) << 25) >> 23)) | sArr[i15]);
            int i17 = i2 + 7;
            sArr[i17] = (short) ((bArr2[i16] & 255) >> 7);
            sArr[i17] = (short) (((short) (bArr2[i + 8] << 1)) | sArr[i17]);
            i += 9;
        }
        for (int i18 = 0; i18 < 1024; i18 += 8) {
            int i19 = i18 + 0;
            sArr2[i19] = (short) (bArr2[i + 0] & 255);
            int i20 = i + 1;
            sArr2[i19] = (short) (((short) (((bArr2[i20] & 255) << 31) >> 23)) | sArr2[i19]);
            int i21 = i18 + 1;
            sArr2[i21] = (short) ((bArr2[i20] & 255) >> 1);
            int i22 = i + 2;
            sArr2[i21] = (short) (((short) (((bArr2[i22] & 255) << 30) >> 23)) | sArr2[i21]);
            int i23 = i18 + 2;
            sArr2[i23] = (short) ((bArr2[i22] & 255) >> 2);
            int i24 = i + 3;
            sArr2[i23] = (short) (((short) (((bArr2[i24] & 255) << 29) >> 23)) | sArr2[i23]);
            int i25 = i18 + 3;
            sArr2[i25] = (short) ((bArr2[i24] & 255) >> 3);
            int i26 = i + 4;
            sArr2[i25] = (short) (((short) (((bArr2[i26] & 255) << 28) >> 23)) | sArr2[i25]);
            int i27 = i18 + 4;
            sArr2[i27] = (short) ((bArr2[i26] & 255) >> 4);
            int i28 = i + 5;
            sArr2[i27] = (short) (((short) (((bArr2[i28] & 255) << 27) >> 23)) | sArr2[i27]);
            int i29 = i18 + 5;
            sArr2[i29] = (short) ((bArr2[i28] & 255) >> 5);
            int i30 = i + 6;
            sArr2[i29] = (short) (((short) (((bArr2[i30] & 255) << 26) >> 23)) | sArr2[i29]);
            int i31 = i18 + 6;
            sArr2[i31] = (short) ((bArr2[i30] & 255) >> 6);
            int i32 = i + 7;
            sArr2[i31] = (short) (((short) (((bArr2[i32] & 255) << 25) >> 23)) | sArr2[i31]);
            int i33 = i18 + 7;
            sArr2[i33] = (short) ((bArr2[i32] & 255) >> 7);
            sArr2[i33] = (short) (((short) (bArr2[i + 8] << 1)) | sArr2[i33]);
            i += 9;
        }
        System.arraycopy(bArr2, 2304, bArr, 0, 64);
    }

    public static void decodePublicKey(int[] iArr, byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        int i4 = (1 << i3) - 1;
        int i5 = 0;
        for (int i6 = 0; i6 < i2; i6 += 32) {
            int i7 = (i5 + 0) * 4;
            iArr[i6 + 0] = CommonFunction.load32(bArr2, i7) & i4;
            int i8 = (i5 + 1) * 4;
            iArr[i6 + 1] = ((CommonFunction.load32(bArr2, i7) >>> 23) | (CommonFunction.load32(bArr2, i8) << 9)) & i4;
            int load32 = CommonFunction.load32(bArr2, i8) >>> 14;
            int i9 = (i5 + 2) * 4;
            iArr[i6 + 2] = (load32 | (CommonFunction.load32(bArr2, i9) << 18)) & i4;
            iArr[i6 + 3] = (CommonFunction.load32(bArr2, i9) >>> 5) & i4;
            int load322 = CommonFunction.load32(bArr2, i9) >>> 28;
            int i10 = (i5 + 3) * 4;
            iArr[i6 + 4] = (load322 | (CommonFunction.load32(bArr2, i10) << 4)) & i4;
            int load323 = CommonFunction.load32(bArr2, i10) >>> 19;
            int i11 = (i5 + 4) * 4;
            iArr[i6 + 5] = (load323 | (CommonFunction.load32(bArr2, i11) << 13)) & i4;
            int load324 = CommonFunction.load32(bArr2, i11) >>> 10;
            int i12 = (i5 + 5) * 4;
            iArr[i6 + 6] = (load324 | (CommonFunction.load32(bArr2, i12) << 22)) & i4;
            iArr[i6 + 7] = (CommonFunction.load32(bArr2, i12) >>> 1) & i4;
            int load325 = CommonFunction.load32(bArr2, i12) >>> 24;
            int i13 = (i5 + 6) * 4;
            iArr[i6 + 8] = (load325 | (CommonFunction.load32(bArr2, i13) << 8)) & i4;
            int load326 = CommonFunction.load32(bArr2, i13) >>> 15;
            int i14 = (i5 + 7) * 4;
            iArr[i6 + 9] = (load326 | (CommonFunction.load32(bArr2, i14) << 17)) & i4;
            iArr[i6 + 10] = (CommonFunction.load32(bArr2, i14) >>> 6) & i4;
            int load327 = CommonFunction.load32(bArr2, i14) >>> 29;
            int i15 = (i5 + 8) * 4;
            iArr[i6 + 11] = (load327 | (CommonFunction.load32(bArr2, i15) << 3)) & i4;
            int load328 = CommonFunction.load32(bArr2, i15) >>> 20;
            int i16 = (i5 + 9) * 4;
            iArr[i6 + 12] = (load328 | (CommonFunction.load32(bArr2, i16) << 12)) & i4;
            int load329 = CommonFunction.load32(bArr2, i16) >>> 11;
            int i17 = (i5 + 10) * 4;
            iArr[i6 + 13] = (load329 | (CommonFunction.load32(bArr2, i17) << 21)) & i4;
            iArr[i6 + 14] = (CommonFunction.load32(bArr2, i17) >>> 2) & i4;
            int load3210 = CommonFunction.load32(bArr2, i17) >>> 25;
            int i18 = (i5 + 11) * 4;
            iArr[i6 + 15] = (load3210 | (CommonFunction.load32(bArr2, i18) << 7)) & i4;
            int load3211 = CommonFunction.load32(bArr2, i18) >>> 16;
            int i19 = (i5 + 12) * 4;
            iArr[i6 + 16] = (load3211 | (CommonFunction.load32(bArr2, i19) << 16)) & i4;
            iArr[i6 + 17] = (CommonFunction.load32(bArr2, i19) >>> 7) & i4;
            int load3212 = CommonFunction.load32(bArr2, i19) >>> 30;
            int i20 = (i5 + 13) * 4;
            iArr[i6 + 18] = (load3212 | (CommonFunction.load32(bArr2, i20) << 2)) & i4;
            int load3213 = CommonFunction.load32(bArr2, i20) >>> 21;
            int i21 = (i5 + 14) * 4;
            iArr[i6 + 19] = (load3213 | (CommonFunction.load32(bArr2, i21) << 11)) & i4;
            int load3214 = CommonFunction.load32(bArr2, i21) >>> 12;
            int i22 = (i5 + 15) * 4;
            iArr[i6 + 20] = (load3214 | (CommonFunction.load32(bArr2, i22) << 20)) & i4;
            iArr[i6 + 21] = (CommonFunction.load32(bArr2, i22) >>> 3) & i4;
            int load3215 = CommonFunction.load32(bArr2, i22) >>> 26;
            int i23 = (i5 + 16) * 4;
            iArr[i6 + 22] = (load3215 | (CommonFunction.load32(bArr2, i23) << 6)) & i4;
            int load3216 = CommonFunction.load32(bArr2, i23) >>> 17;
            int i24 = (i5 + 17) * 4;
            iArr[i6 + 23] = (load3216 | (CommonFunction.load32(bArr2, i24) << 15)) & i4;
            iArr[i6 + 24] = (CommonFunction.load32(bArr2, i24) >>> 8) & i4;
            int load3217 = CommonFunction.load32(bArr2, i24) >>> 31;
            int i25 = (i5 + 18) * 4;
            iArr[i6 + 25] = (load3217 | (CommonFunction.load32(bArr2, i25) << 1)) & i4;
            int load3218 = CommonFunction.load32(bArr2, i25) >>> 22;
            int i26 = (i5 + 19) * 4;
            iArr[i6 + 26] = (load3218 | (CommonFunction.load32(bArr2, i26) << 10)) & i4;
            int load3219 = CommonFunction.load32(bArr2, i26) >>> 13;
            int i27 = (i5 + 20) * 4;
            iArr[i6 + 27] = (load3219 | (CommonFunction.load32(bArr2, i27) << 19)) & i4;
            iArr[i6 + 28] = (CommonFunction.load32(bArr2, i27) >>> 4) & i4;
            int load3220 = CommonFunction.load32(bArr2, i27) >>> 27;
            int i28 = (i5 + 21) * 4;
            iArr[i6 + 29] = (load3220 | (CommonFunction.load32(bArr2, i28) << 5)) & i4;
            int load3221 = CommonFunction.load32(bArr2, i28) >>> 18;
            int i29 = (i5 + 22) * 4;
            iArr[i6 + 30] = (load3221 | (CommonFunction.load32(bArr2, i29) << 14)) & i4;
            iArr[i6 + 31] = CommonFunction.load32(bArr2, i29) >>> 9;
            i5 += i3;
        }
        System.arraycopy(bArr2, (i2 * i3) / 8, bArr, i, 32);
    }

    public static void decodePublicKeyIIIP(int[] iArr, byte[] bArr, int i, byte[] bArr2) {
        int i2 = 0;
        for (int i3 = 0; i3 < 10240; i3 += 32) {
            iArr[i3] = CommonFunction.load32(bArr2, i2 * 4) & Integer.MAX_VALUE;
            for (int i4 = 1; i4 < 31; i4++) {
                int i5 = i2 + i4;
                iArr[i3 + i4] = ((CommonFunction.load32(bArr2, i5 * 4) << i4) | (CommonFunction.load32(bArr2, (i5 - 1) * 4) >>> (32 - i4))) & Integer.MAX_VALUE;
            }
            i2 += 31;
            iArr[i3 + 31] = CommonFunction.load32(bArr2, (i2 - 1) * 4) >>> 1;
        }
        System.arraycopy(bArr2, 39680, bArr, i, 32);
    }

    public static void decodePublicKeyIIISpeed(int[] iArr, byte[] bArr, int i, byte[] bArr2) {
        int i2 = 0;
        for (int i3 = 0; i3 < 1024; i3 += 4) {
            int i4 = (i2 + 0) * 4;
            iArr[i3 + 0] = CommonFunction.load32(bArr2, i4) & 16777215;
            int i5 = (i2 + 1) * 4;
            iArr[i3 + 1] = ((CommonFunction.load32(bArr2, i4) >>> 24) | (CommonFunction.load32(bArr2, i5) << 8)) & 16777215;
            int load32 = CommonFunction.load32(bArr2, i5) >>> 16;
            int i6 = (i2 + 2) * 4;
            iArr[i3 + 2] = (load32 | (CommonFunction.load32(bArr2, i6) << 16)) & 16777215;
            iArr[i3 + 3] = CommonFunction.load32(bArr2, i6) >>> 8;
            i2 += 3;
        }
        System.arraycopy(bArr2, 3072, bArr, i, 32);
    }

    public static void decodePublicKeyIP(int[] iArr, byte[] bArr, int i, byte[] bArr2) {
        int i2 = 0;
        for (int i3 = 0; i3 < 4096; i3 += 32) {
            int i4 = (i2 + 0) * 4;
            iArr[i3 + 0] = CommonFunction.load32(bArr2, i4) & 536870911;
            int i5 = (i2 + 1) * 4;
            iArr[i3 + 1] = ((CommonFunction.load32(bArr2, i4) >>> 29) | (CommonFunction.load32(bArr2, i5) << 3)) & 536870911;
            int load32 = CommonFunction.load32(bArr2, i5) >>> 26;
            int i6 = (i2 + 2) * 4;
            iArr[i3 + 2] = (load32 | (CommonFunction.load32(bArr2, i6) << 6)) & 536870911;
            int load322 = CommonFunction.load32(bArr2, i6) >>> 23;
            int i7 = (i2 + 3) * 4;
            iArr[i3 + 3] = (load322 | (CommonFunction.load32(bArr2, i7) << 9)) & 536870911;
            int load323 = CommonFunction.load32(bArr2, i7) >>> 20;
            int i8 = (i2 + 4) * 4;
            iArr[i3 + 4] = (load323 | (CommonFunction.load32(bArr2, i8) << 12)) & 536870911;
            int load324 = CommonFunction.load32(bArr2, i8) >>> 17;
            int i9 = (i2 + 5) * 4;
            iArr[i3 + 5] = (load324 | (CommonFunction.load32(bArr2, i9) << 15)) & 536870911;
            int load325 = CommonFunction.load32(bArr2, i9) >>> 14;
            int i10 = (i2 + 6) * 4;
            iArr[i3 + 6] = (load325 | (CommonFunction.load32(bArr2, i10) << 18)) & 536870911;
            int load326 = CommonFunction.load32(bArr2, i10) >>> 11;
            int i11 = (i2 + 7) * 4;
            iArr[i3 + 7] = (load326 | (CommonFunction.load32(bArr2, i11) << 21)) & 536870911;
            int load327 = CommonFunction.load32(bArr2, i11) >>> 8;
            int i12 = (i2 + 8) * 4;
            iArr[i3 + 8] = (load327 | (CommonFunction.load32(bArr2, i12) << 24)) & 536870911;
            int load328 = CommonFunction.load32(bArr2, i12) >>> 5;
            int i13 = (i2 + 9) * 4;
            iArr[i3 + 9] = (load328 | (CommonFunction.load32(bArr2, i13) << 27)) & 536870911;
            iArr[i3 + 10] = (CommonFunction.load32(bArr2, i13) >>> 2) & 536870911;
            int load329 = CommonFunction.load32(bArr2, i13) >>> 31;
            int i14 = (i2 + 10) * 4;
            iArr[i3 + 11] = (load329 | (CommonFunction.load32(bArr2, i14) << 1)) & 536870911;
            int load3210 = CommonFunction.load32(bArr2, i14) >>> 28;
            int i15 = (i2 + 11) * 4;
            iArr[i3 + 12] = (load3210 | (CommonFunction.load32(bArr2, i15) << 4)) & 536870911;
            int load3211 = CommonFunction.load32(bArr2, i15) >>> 25;
            int i16 = (i2 + 12) * 4;
            iArr[i3 + 13] = (load3211 | (CommonFunction.load32(bArr2, i16) << 7)) & 536870911;
            int load3212 = CommonFunction.load32(bArr2, i16) >>> 22;
            int i17 = (i2 + 13) * 4;
            iArr[i3 + 14] = (load3212 | (CommonFunction.load32(bArr2, i17) << 10)) & 536870911;
            int load3213 = CommonFunction.load32(bArr2, i17) >>> 19;
            int i18 = (i2 + 14) * 4;
            iArr[i3 + 15] = (load3213 | (CommonFunction.load32(bArr2, i18) << 13)) & 536870911;
            int load3214 = CommonFunction.load32(bArr2, i18) >>> 16;
            int i19 = (i2 + 15) * 4;
            iArr[i3 + 16] = (load3214 | (CommonFunction.load32(bArr2, i19) << 16)) & 536870911;
            int load3215 = CommonFunction.load32(bArr2, i19) >>> 13;
            int i20 = (i2 + 16) * 4;
            iArr[i3 + 17] = (load3215 | (CommonFunction.load32(bArr2, i20) << 19)) & 536870911;
            int load3216 = CommonFunction.load32(bArr2, i20) >>> 10;
            int i21 = (i2 + 17) * 4;
            iArr[i3 + 18] = (load3216 | (CommonFunction.load32(bArr2, i21) << 22)) & 536870911;
            int load3217 = CommonFunction.load32(bArr2, i21) >>> 7;
            int i22 = (i2 + 18) * 4;
            iArr[i3 + 19] = (load3217 | (CommonFunction.load32(bArr2, i22) << 25)) & 536870911;
            int load3218 = CommonFunction.load32(bArr2, i22) >>> 4;
            int i23 = (i2 + 19) * 4;
            iArr[i3 + 20] = (load3218 | (CommonFunction.load32(bArr2, i23) << 28)) & 536870911;
            iArr[i3 + 21] = (CommonFunction.load32(bArr2, i23) >>> 1) & 536870911;
            int load3219 = CommonFunction.load32(bArr2, i23) >>> 30;
            int i24 = (i2 + 20) * 4;
            iArr[i3 + 22] = (load3219 | (CommonFunction.load32(bArr2, i24) << 2)) & 536870911;
            int load3220 = CommonFunction.load32(bArr2, i24) >>> 27;
            int i25 = (i2 + 21) * 4;
            iArr[i3 + 23] = (load3220 | (CommonFunction.load32(bArr2, i25) << 5)) & 536870911;
            int load3221 = CommonFunction.load32(bArr2, i25) >>> 24;
            int i26 = (i2 + 22) * 4;
            iArr[i3 + 24] = (load3221 | (CommonFunction.load32(bArr2, i26) << 8)) & 536870911;
            int load3222 = CommonFunction.load32(bArr2, i26) >>> 21;
            int i27 = (i2 + 23) * 4;
            iArr[i3 + 25] = (load3222 | (CommonFunction.load32(bArr2, i27) << 11)) & 536870911;
            int load3223 = CommonFunction.load32(bArr2, i27) >>> 18;
            int i28 = (i2 + 24) * 4;
            iArr[i3 + 26] = (load3223 | (CommonFunction.load32(bArr2, i28) << 14)) & 536870911;
            int load3224 = CommonFunction.load32(bArr2, i28) >>> 15;
            int i29 = (i2 + 25) * 4;
            iArr[i3 + 27] = (load3224 | (CommonFunction.load32(bArr2, i29) << 17)) & 536870911;
            int load3225 = CommonFunction.load32(bArr2, i29) >>> 12;
            int i30 = (i2 + 26) * 4;
            iArr[i3 + 28] = (load3225 | (CommonFunction.load32(bArr2, i30) << 20)) & 536870911;
            int load3226 = CommonFunction.load32(bArr2, i30) >>> 9;
            int i31 = (i2 + 27) * 4;
            iArr[i3 + 29] = (load3226 | (CommonFunction.load32(bArr2, i31) << 23)) & 536870911;
            int load3227 = CommonFunction.load32(bArr2, i31) >>> 6;
            int i32 = (i2 + 28) * 4;
            iArr[i3 + 30] = (load3227 | (CommonFunction.load32(bArr2, i32) << 26)) & 536870911;
            iArr[i3 + 31] = CommonFunction.load32(bArr2, i32) >>> 3;
            i2 += 29;
        }
        System.arraycopy(bArr2, 14848, bArr, i, 32);
    }

    public static void decodeSignature(byte[] bArr, int[] iArr, byte[] bArr2, int i, int i2, int i3) {
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5 += 32) {
            int i6 = ((i4 + 0) * 4) + i;
            iArr[i5 + 0] = (CommonFunction.load32(bArr2, i6) << 11) >> 11;
            int i7 = ((i4 + 1) * 4) + i;
            iArr[i5 + 1] = (CommonFunction.load32(bArr2, i6) >>> 21) | ((CommonFunction.load32(bArr2, i7) << 22) >> 11);
            iArr[i5 + 2] = (CommonFunction.load32(bArr2, i7) << 1) >> 11;
            int load32 = CommonFunction.load32(bArr2, i7) >>> 31;
            int i8 = ((i4 + 2) * 4) + i;
            iArr[i5 + 3] = load32 | ((CommonFunction.load32(bArr2, i8) << 12) >> 11);
            int load322 = CommonFunction.load32(bArr2, i8) >>> 20;
            int i9 = ((i4 + 3) * 4) + i;
            iArr[i5 + 4] = load322 | ((CommonFunction.load32(bArr2, i9) << 23) >> 11);
            iArr[i5 + 5] = (CommonFunction.load32(bArr2, i9) << 2) >> 11;
            int load323 = CommonFunction.load32(bArr2, i9) >>> 30;
            int i10 = ((i4 + 4) * 4) + i;
            iArr[i5 + 6] = load323 | ((CommonFunction.load32(bArr2, i10) << 13) >> 11);
            int load324 = CommonFunction.load32(bArr2, i10) >>> 19;
            int i11 = ((i4 + 5) * 4) + i;
            iArr[i5 + 7] = load324 | ((CommonFunction.load32(bArr2, i11) << 24) >> 11);
            iArr[i5 + 8] = (CommonFunction.load32(bArr2, i11) << 3) >> 11;
            int load325 = CommonFunction.load32(bArr2, i11) >>> 29;
            int i12 = ((i4 + 6) * 4) + i;
            iArr[i5 + 9] = load325 | ((CommonFunction.load32(bArr2, i12) << 14) >> 11);
            int load326 = CommonFunction.load32(bArr2, i12) >>> 18;
            int i13 = ((i4 + 7) * 4) + i;
            iArr[i5 + 10] = load326 | ((CommonFunction.load32(bArr2, i13) << 25) >> 11);
            iArr[i5 + 11] = (CommonFunction.load32(bArr2, i13) << 4) >> 11;
            int load327 = CommonFunction.load32(bArr2, i13) >>> 28;
            int i14 = ((i4 + 8) * 4) + i;
            iArr[i5 + 12] = load327 | ((CommonFunction.load32(bArr2, i14) << 15) >> 11);
            int load328 = CommonFunction.load32(bArr2, i14) >>> 17;
            int i15 = ((i4 + 9) * 4) + i;
            iArr[i5 + 13] = load328 | ((CommonFunction.load32(bArr2, i15) << 26) >> 11);
            iArr[i5 + 14] = (CommonFunction.load32(bArr2, i15) << 5) >> 11;
            int load329 = CommonFunction.load32(bArr2, i15) >>> 27;
            int i16 = ((i4 + 10) * 4) + i;
            iArr[i5 + 15] = load329 | ((CommonFunction.load32(bArr2, i16) << 16) >> 11);
            int load3210 = CommonFunction.load32(bArr2, i16) >>> 16;
            int i17 = ((i4 + 11) * 4) + i;
            iArr[i5 + 16] = load3210 | ((CommonFunction.load32(bArr2, i17) << 27) >> 11);
            iArr[i5 + 17] = (CommonFunction.load32(bArr2, i17) << 6) >> 11;
            int load3211 = CommonFunction.load32(bArr2, i17) >>> 26;
            int i18 = ((i4 + 12) * 4) + i;
            iArr[i5 + 18] = load3211 | ((CommonFunction.load32(bArr2, i18) << 17) >> 11);
            int load3212 = CommonFunction.load32(bArr2, i18) >>> 15;
            int i19 = ((i4 + 13) * 4) + i;
            iArr[i5 + 19] = load3212 | ((CommonFunction.load32(bArr2, i19) << 28) >> 11);
            iArr[i5 + 20] = (CommonFunction.load32(bArr2, i19) << 7) >> 11;
            int load3213 = CommonFunction.load32(bArr2, i19) >>> 25;
            int i20 = ((i4 + 14) * 4) + i;
            iArr[i5 + 21] = load3213 | ((CommonFunction.load32(bArr2, i20) << 18) >> 11);
            int load3214 = CommonFunction.load32(bArr2, i20) >>> 14;
            int i21 = ((i4 + 15) * 4) + i;
            iArr[i5 + 22] = load3214 | ((CommonFunction.load32(bArr2, i21) << 29) >> 11);
            iArr[i5 + 23] = (CommonFunction.load32(bArr2, i21) << 8) >> 11;
            int load3215 = CommonFunction.load32(bArr2, i21) >>> 24;
            int i22 = ((i4 + 16) * 4) + i;
            iArr[i5 + 24] = load3215 | ((CommonFunction.load32(bArr2, i22) << 19) >> 11);
            int load3216 = CommonFunction.load32(bArr2, i22) >>> 13;
            int i23 = ((i4 + 17) * 4) + i;
            iArr[i5 + 25] = load3216 | ((CommonFunction.load32(bArr2, i23) << 30) >> 11);
            iArr[i5 + 26] = (CommonFunction.load32(bArr2, i23) << 9) >> 11;
            int load3217 = CommonFunction.load32(bArr2, i23) >>> 23;
            int i24 = ((i4 + 18) * 4) + i;
            iArr[i5 + 27] = load3217 | ((CommonFunction.load32(bArr2, i24) << 20) >> 11);
            int load3218 = CommonFunction.load32(bArr2, i24) >>> 12;
            int i25 = ((i4 + 19) * 4) + i;
            iArr[i5 + 28] = load3218 | ((CommonFunction.load32(bArr2, i25) << 31) >> 11);
            iArr[i5 + 29] = (CommonFunction.load32(bArr2, i25) << 10) >> 11;
            int load3219 = CommonFunction.load32(bArr2, i25) >>> 22;
            int i26 = ((i4 + 20) * 4) + i;
            iArr[i5 + 30] = load3219 | ((CommonFunction.load32(bArr2, i26) << 21) >> 11);
            iArr[i5 + 31] = CommonFunction.load32(bArr2, i26) >> 11;
            i4 += i3;
        }
        System.arraycopy(bArr2, i + ((i2 * i3) / 8), bArr, 0, 32);
    }

    public static void decodeSignatureIIIP(byte[] bArr, long[] jArr, byte[] bArr2, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < 2048; i3 += 4) {
            int i4 = ((i2 + 0) * 4) + i;
            jArr[i3 + 0] = (CommonFunction.load32(bArr2, i4) << 8) >> 8;
            jArr[i3 + 1] = ((CommonFunction.load32(bArr2, i4) >>> 24) & 255) | ((CommonFunction.load32(bArr2, r5) << 16) >> 8);
            int load32 = (CommonFunction.load32(bArr2, ((i2 + 1) * 4) + i) >>> 16) & 65535;
            int i5 = ((i2 + 2) * 4) + i;
            jArr[i3 + 2] = load32 | ((CommonFunction.load32(bArr2, i5) << 24) >> 8);
            jArr[i3 + 3] = CommonFunction.load32(bArr2, i5) >> 8;
            i2 += 3;
        }
        System.arraycopy(bArr2, i + 6144, bArr, 0, 32);
    }

    public static void decodeSignatureIIISpeed(byte[] bArr, int[] iArr, byte[] bArr2, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < 1024; i3 += 16) {
            int i4 = ((i2 + 0) * 4) + i;
            iArr[i3 + 0] = (CommonFunction.load32(bArr2, i4) << 10) >> 10;
            int i5 = ((i2 + 1) * 4) + i;
            iArr[i3 + 1] = (CommonFunction.load32(bArr2, i4) >>> 22) | ((CommonFunction.load32(bArr2, i5) << 20) >> 10);
            int load32 = CommonFunction.load32(bArr2, i5) >>> 12;
            int i6 = ((i2 + 2) * 4) + i;
            iArr[i3 + 2] = load32 | ((CommonFunction.load32(bArr2, i6) << 30) >> 10);
            iArr[i3 + 3] = (CommonFunction.load32(bArr2, i6) << 8) >> 10;
            int load322 = CommonFunction.load32(bArr2, i6) >>> 24;
            int i7 = ((i2 + 3) * 4) + i;
            iArr[i3 + 4] = load322 | ((CommonFunction.load32(bArr2, i7) << 18) >> 10);
            int load323 = CommonFunction.load32(bArr2, i7) >>> 14;
            int i8 = ((i2 + 4) * 4) + i;
            iArr[i3 + 5] = load323 | ((CommonFunction.load32(bArr2, i8) << 28) >> 10);
            iArr[i3 + 6] = (CommonFunction.load32(bArr2, i8) << 6) >> 10;
            int load324 = CommonFunction.load32(bArr2, i8) >>> 26;
            int i9 = ((i2 + 5) * 4) + i;
            iArr[i3 + 7] = load324 | ((CommonFunction.load32(bArr2, i9) << 16) >> 10);
            int load325 = CommonFunction.load32(bArr2, i9) >>> 16;
            int i10 = ((i2 + 6) * 4) + i;
            iArr[i3 + 8] = load325 | ((CommonFunction.load32(bArr2, i10) << 26) >> 10);
            iArr[i3 + 9] = (CommonFunction.load32(bArr2, i10) << 4) >> 10;
            int load326 = CommonFunction.load32(bArr2, i10) >>> 28;
            int i11 = ((i2 + 7) * 4) + i;
            iArr[i3 + 10] = load326 | ((CommonFunction.load32(bArr2, i11) << 14) >> 10);
            int load327 = CommonFunction.load32(bArr2, i11) >>> 18;
            int i12 = ((i2 + 8) * 4) + i;
            iArr[i3 + 11] = load327 | ((CommonFunction.load32(bArr2, i12) << 24) >> 10);
            iArr[i3 + 12] = (CommonFunction.load32(bArr2, i12) << 2) >> 10;
            int load328 = CommonFunction.load32(bArr2, i12) >>> 30;
            int i13 = ((i2 + 9) * 4) + i;
            iArr[i3 + 13] = load328 | ((CommonFunction.load32(bArr2, i13) << 12) >> 10);
            int load329 = CommonFunction.load32(bArr2, i13) >>> 20;
            int i14 = ((i2 + 10) * 4) + i;
            iArr[i3 + 14] = load329 | ((CommonFunction.load32(bArr2, i14) << 22) >> 10);
            iArr[i3 + 15] = CommonFunction.load32(bArr2, i14) >> 10;
            i2 += 11;
        }
        System.arraycopy(bArr2, i + 2816, bArr, 0, 32);
    }

    public static void decodeSignatureIP(byte[] bArr, long[] jArr, byte[] bArr2, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < 1024; i3 += 16) {
            int i4 = ((i2 + 0) * 4) + i;
            jArr[i3 + 0] = (CommonFunction.load32(bArr2, i4) << 10) >> 10;
            jArr[i3 + 1] = (CommonFunction.load32(bArr2, i4) >>> 22) | ((CommonFunction.load32(bArr2, r5) << 20) >> 10);
            int load32 = CommonFunction.load32(bArr2, ((i2 + 1) * 4) + i) >>> 12;
            jArr[i3 + 2] = load32 | ((CommonFunction.load32(bArr2, r5) << 30) >> 10);
            jArr[i3 + 3] = (CommonFunction.load32(bArr2, r5) << 8) >> 10;
            int load322 = CommonFunction.load32(bArr2, ((i2 + 2) * 4) + i) >>> 24;
            jArr[i3 + 4] = load322 | ((CommonFunction.load32(bArr2, r5) << 18) >> 10);
            int load323 = CommonFunction.load32(bArr2, ((i2 + 3) * 4) + i) >>> 14;
            jArr[i3 + 5] = load323 | ((CommonFunction.load32(bArr2, r5) << 28) >> 10);
            jArr[i3 + 6] = (CommonFunction.load32(bArr2, r5) << 6) >> 10;
            int load324 = CommonFunction.load32(bArr2, ((i2 + 4) * 4) + i) >>> 26;
            jArr[i3 + 7] = load324 | ((CommonFunction.load32(bArr2, r5) << 16) >> 10);
            int load325 = CommonFunction.load32(bArr2, ((i2 + 5) * 4) + i) >>> 16;
            jArr[i3 + 8] = load325 | ((CommonFunction.load32(bArr2, r5) << 26) >> 10);
            jArr[i3 + 9] = (CommonFunction.load32(bArr2, r5) << 4) >> 10;
            int load326 = CommonFunction.load32(bArr2, ((i2 + 6) * 4) + i) >>> 28;
            jArr[i3 + 10] = load326 | ((CommonFunction.load32(bArr2, r5) << 14) >> 10);
            int load327 = CommonFunction.load32(bArr2, ((i2 + 7) * 4) + i) >>> 18;
            jArr[i3 + 11] = load327 | ((CommonFunction.load32(bArr2, r5) << 24) >> 10);
            jArr[i3 + 12] = (CommonFunction.load32(bArr2, r5) << 2) >> 10;
            int load328 = CommonFunction.load32(bArr2, ((i2 + 8) * 4) + i) >>> 30;
            jArr[i3 + 13] = load328 | ((CommonFunction.load32(bArr2, r5) << 12) >> 10);
            int load329 = CommonFunction.load32(bArr2, ((i2 + 9) * 4) + i) >>> 20;
            int i5 = ((i2 + 10) * 4) + i;
            jArr[i3 + 14] = load329 | ((CommonFunction.load32(bArr2, i5) << 22) >> 10);
            jArr[i3 + 15] = CommonFunction.load32(bArr2, i5) >> 10;
            i2 += 11;
        }
        System.arraycopy(bArr2, i + 2816, bArr, 0, 32);
    }

    public static void encodePrivateKeyI(byte[] bArr, int[] iArr, int[] iArr2, byte[] bArr2, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < 512; i3 += 4) {
            int i4 = i3 + 0;
            bArr[i2 + 0] = (byte) iArr[i4];
            int i5 = i3 + 1;
            bArr[i2 + 1] = (byte) (((iArr[i4] >> 8) & 3) | (iArr[i5] << 2));
            int i6 = (iArr[i5] >> 6) & 15;
            int i7 = i3 + 2;
            bArr[i2 + 2] = (byte) (i6 | (iArr[i7] << 4));
            int i8 = (iArr[i7] >> 4) & 63;
            int i9 = i3 + 3;
            bArr[i2 + 3] = (byte) (i8 | (iArr[i9] << 6));
            bArr[i2 + 4] = (byte) (iArr[i9] >> 2);
            i2 += 5;
        }
        for (int i10 = 0; i10 < 512; i10 += 4) {
            int i11 = i10 + 0;
            bArr[i2 + 0] = (byte) iArr2[i11];
            int i12 = i10 + 1;
            bArr[i2 + 1] = (byte) (((iArr2[i11] >> 8) & 3) | (iArr2[i12] << 2));
            int i13 = (iArr2[i12] >> 6) & 15;
            int i14 = i10 + 2;
            bArr[i2 + 2] = (byte) (i13 | (iArr2[i14] << 4));
            int i15 = (iArr2[i14] >> 4) & 63;
            int i16 = i10 + 3;
            bArr[i2 + 3] = (byte) (i15 | (iArr2[i16] << 6));
            bArr[i2 + 4] = (byte) (iArr2[i16] >> 2);
            i2 += 5;
        }
        System.arraycopy(bArr2, i, bArr, 1280, 64);
    }

    public static void encodePrivateKeyIIISize(byte[] bArr, int[] iArr, int[] iArr2, byte[] bArr2, int i) {
        for (int i2 = 0; i2 < 1024; i2++) {
            bArr[i2] = (byte) iArr[i2];
        }
        for (int i3 = 0; i3 < 1024; i3++) {
            bArr[i3 + 1024] = (byte) iArr2[i3];
        }
        System.arraycopy(bArr2, i, bArr, 2048, 64);
    }

    public static void encodePrivateKeyIIISpeed(byte[] bArr, int[] iArr, int[] iArr2, byte[] bArr2, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < 1024; i3 += 8) {
            int i4 = i3 + 0;
            bArr[i2 + 0] = (byte) iArr[i4];
            int i5 = i3 + 1;
            bArr[i2 + 1] = (byte) (((iArr[i4] >> 8) & 1) | (iArr[i5] << 1));
            int i6 = (iArr[i5] >> 7) & 3;
            int i7 = i3 + 2;
            bArr[i2 + 2] = (byte) (i6 | (iArr[i7] << 2));
            int i8 = (iArr[i7] >> 6) & 7;
            int i9 = i3 + 3;
            bArr[i2 + 3] = (byte) (i8 | (iArr[i9] << 3));
            int i10 = (iArr[i9] >> 5) & 15;
            int i11 = i3 + 4;
            bArr[i2 + 4] = (byte) (i10 | (iArr[i11] << 4));
            int i12 = (iArr[i11] >> 4) & 31;
            int i13 = i3 + 5;
            bArr[i2 + 5] = (byte) (i12 | (iArr[i13] << 5));
            int i14 = (iArr[i13] >> 3) & 63;
            int i15 = i3 + 6;
            bArr[i2 + 6] = (byte) (i14 | (iArr[i15] << 6));
            int i16 = (iArr[i15] >> 2) & 127;
            int i17 = i3 + 7;
            bArr[i2 + 7] = (byte) (i16 | (iArr[i17] << 7));
            bArr[i2 + 8] = (byte) (iArr[i17] >> 1);
            i2 += 9;
        }
        for (int i18 = 0; i18 < 1024; i18 += 8) {
            int i19 = i18 + 0;
            bArr[i2 + 0] = (byte) iArr2[i19];
            int i20 = i18 + 1;
            bArr[i2 + 1] = (byte) (((iArr2[i19] >> 8) & 1) | (iArr2[i20] << 1));
            int i21 = (iArr2[i20] >> 7) & 3;
            int i22 = i18 + 2;
            bArr[i2 + 2] = (byte) (i21 | (iArr2[i22] << 2));
            int i23 = (iArr2[i22] >> 6) & 7;
            int i24 = i18 + 3;
            bArr[i2 + 3] = (byte) (i23 | (iArr2[i24] << 3));
            int i25 = (iArr2[i24] >> 5) & 15;
            int i26 = i18 + 4;
            bArr[i2 + 4] = (byte) (i25 | (iArr2[i26] << 4));
            int i27 = (iArr2[i26] >> 4) & 31;
            int i28 = i18 + 5;
            bArr[i2 + 5] = (byte) (i27 | (iArr2[i28] << 5));
            int i29 = (iArr2[i28] >> 3) & 63;
            int i30 = i18 + 6;
            bArr[i2 + 6] = (byte) (i29 | (iArr2[i30] << 6));
            int i31 = (iArr2[i30] >> 2) & 127;
            int i32 = i18 + 7;
            bArr[i2 + 7] = (byte) (i31 | (iArr2[i32] << 7));
            bArr[i2 + 8] = (byte) (iArr2[i32] >> 1);
            i2 += 9;
        }
        System.arraycopy(bArr2, i, bArr, 2304, 64);
    }

    public static void encodePublicKey(byte[] bArr, int[] iArr, byte[] bArr2, int i, int i2, int i3) {
        int i4 = 0;
        int i5 = 0;
        while (true) {
            int i6 = i2 * i3;
            if (i4 >= i6 / 32) {
                System.arraycopy(bArr2, i, bArr, i6 / 8, 32);
                return;
            }
            int i7 = i5 + 1;
            CommonFunction.store32(bArr, (i4 + 0) * 4, iArr[i5 + 0] | (iArr[i7] << 23));
            int i8 = iArr[i7] >> 9;
            int i9 = i5 + 2;
            CommonFunction.store32(bArr, (i4 + 1) * 4, i8 | (iArr[i9] << 14));
            int i10 = (iArr[i9] >> 18) | (iArr[i5 + 3] << 5);
            int i11 = i5 + 4;
            CommonFunction.store32(bArr, (i4 + 2) * 4, i10 | (iArr[i11] << 28));
            int i12 = iArr[i11] >> 4;
            int i13 = i5 + 5;
            CommonFunction.store32(bArr, (i4 + 3) * 4, i12 | (iArr[i13] << 19));
            int i14 = iArr[i13] >> 13;
            int i15 = i5 + 6;
            CommonFunction.store32(bArr, (i4 + 4) * 4, i14 | (iArr[i15] << 10));
            int i16 = (iArr[i15] >> 22) | (iArr[i5 + 7] << 1);
            int i17 = i5 + 8;
            CommonFunction.store32(bArr, (i4 + 5) * 4, i16 | (iArr[i17] << 24));
            int i18 = iArr[i17] >> 8;
            int i19 = i5 + 9;
            CommonFunction.store32(bArr, (i4 + 6) * 4, i18 | (iArr[i19] << 15));
            int i20 = (iArr[i19] >> 17) | (iArr[i5 + 10] << 6);
            int i21 = i5 + 11;
            CommonFunction.store32(bArr, (i4 + 7) * 4, i20 | (iArr[i21] << 29));
            int i22 = iArr[i21] >> 3;
            int i23 = i5 + 12;
            CommonFunction.store32(bArr, (i4 + 8) * 4, i22 | (iArr[i23] << 20));
            int i24 = iArr[i23] >> 12;
            int i25 = i5 + 13;
            CommonFunction.store32(bArr, (i4 + 9) * 4, i24 | (iArr[i25] << 11));
            int i26 = (iArr[i25] >> 21) | (iArr[i5 + 14] << 2);
            int i27 = i5 + 15;
            CommonFunction.store32(bArr, (i4 + 10) * 4, i26 | (iArr[i27] << 25));
            int i28 = iArr[i27] >> 7;
            int i29 = i5 + 16;
            CommonFunction.store32(bArr, (i4 + 11) * 4, i28 | (iArr[i29] << 16));
            int i30 = (iArr[i29] >> 16) | (iArr[i5 + 17] << 7);
            int i31 = i5 + 18;
            CommonFunction.store32(bArr, (i4 + 12) * 4, i30 | (iArr[i31] << 30));
            int i32 = iArr[i31] >> 2;
            int i33 = i5 + 19;
            CommonFunction.store32(bArr, (i4 + 13) * 4, i32 | (iArr[i33] << 21));
            int i34 = iArr[i33] >> 11;
            int i35 = i5 + 20;
            CommonFunction.store32(bArr, (i4 + 14) * 4, i34 | (iArr[i35] << 12));
            int i36 = (iArr[i35] >> 20) | (iArr[i5 + 21] << 3);
            int i37 = i5 + 22;
            CommonFunction.store32(bArr, (i4 + 15) * 4, i36 | (iArr[i37] << 26));
            int i38 = iArr[i37] >> 6;
            int i39 = i5 + 23;
            CommonFunction.store32(bArr, (i4 + 16) * 4, i38 | (iArr[i39] << 17));
            int i40 = (iArr[i39] >> 15) | (iArr[i5 + 24] << 8);
            int i41 = i5 + 25;
            CommonFunction.store32(bArr, (i4 + 17) * 4, i40 | (iArr[i41] << 31));
            int i42 = iArr[i41] >> 1;
            int i43 = i5 + 26;
            CommonFunction.store32(bArr, (i4 + 18) * 4, i42 | (iArr[i43] << 22));
            int i44 = iArr[i43] >> 10;
            int i45 = i5 + 27;
            CommonFunction.store32(bArr, (i4 + 19) * 4, i44 | (iArr[i45] << 13));
            int i46 = (iArr[i45] >> 19) | (iArr[i5 + 28] << 4);
            int i47 = i5 + 29;
            CommonFunction.store32(bArr, (i4 + 20) * 4, i46 | (iArr[i47] << 27));
            int i48 = iArr[i47] >> 5;
            int i49 = i5 + 30;
            CommonFunction.store32(bArr, (i4 + 21) * 4, i48 | (iArr[i49] << 18));
            CommonFunction.store32(bArr, (i4 + 22) * 4, (iArr[i49] >> 14) | (iArr[i5 + 31] << 9));
            i5 += 32;
            i4 += i3;
        }
    }

    public static void encodePublicKeyIIIP(byte[] bArr, long[] jArr, byte[] bArr2, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < 9920; i3 += 31) {
            for (int i4 = 0; i4 < 31; i4++) {
                int i5 = i2 + i4;
                CommonFunction.store32(bArr, (i3 + i4) * 4, (int) ((jArr[i5] >> i4) | (jArr[i5 + 1] << (31 - i4))));
            }
            i2 += 32;
        }
        System.arraycopy(bArr2, i, bArr, 39680, 32);
    }

    public static void encodePublicKeyIIISpeed(byte[] bArr, int[] iArr, byte[] bArr2, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < 768; i3 += 3) {
            int i4 = i2 + 1;
            CommonFunction.store32(bArr, (i3 + 0) * 4, iArr[i2 + 0] | (iArr[i4] << 24));
            int i5 = iArr[i4] >> 8;
            int i6 = i2 + 2;
            CommonFunction.store32(bArr, (i3 + 1) * 4, i5 | (iArr[i6] << 16));
            CommonFunction.store32(bArr, (i3 + 2) * 4, (iArr[i6] >> 16) | (iArr[i2 + 3] << 8));
            i2 += 4;
        }
        System.arraycopy(bArr2, i, bArr, 3072, 32);
    }

    public static void encodePublicKeyIP(byte[] bArr, long[] jArr, byte[] bArr2, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < 3712; i3 += 29) {
            int i4 = i2 + 1;
            CommonFunction.store32(bArr, (i3 + 0) * 4, (int) (jArr[i2 + 0] | (jArr[i4] << 29)));
            int i5 = i2 + 2;
            CommonFunction.store32(bArr, (i3 + 1) * 4, (int) ((jArr[i4] >> 3) | (jArr[i5] << 26)));
            int i6 = i2 + 3;
            CommonFunction.store32(bArr, (i3 + 2) * 4, (int) ((jArr[i5] >> 6) | (jArr[i6] << 23)));
            int i7 = i2 + 4;
            CommonFunction.store32(bArr, (i3 + 3) * 4, (int) ((jArr[i6] >> 9) | (jArr[i7] << 20)));
            int i8 = i2 + 5;
            CommonFunction.store32(bArr, (i3 + 4) * 4, (int) ((jArr[i7] >> 12) | (jArr[i8] << 17)));
            int i9 = i2 + 6;
            CommonFunction.store32(bArr, (i3 + 5) * 4, (int) ((jArr[i8] >> 15) | (jArr[i9] << 14)));
            long j = jArr[i9] >> 18;
            int i10 = i2 + 7;
            CommonFunction.store32(bArr, (i3 + 6) * 4, (int) (j | (jArr[i10] << 11)));
            long j2 = jArr[i10] >> 21;
            int i11 = i2 + 8;
            CommonFunction.store32(bArr, (i3 + 7) * 4, (int) (j2 | (jArr[i11] << 8)));
            long j3 = jArr[i11] >> 24;
            int i12 = i2 + 9;
            CommonFunction.store32(bArr, (i3 + 8) * 4, (int) (j3 | (jArr[i12] << 5)));
            long j4 = (jArr[i12] >> 27) | (jArr[i2 + 10] << 2);
            int i13 = i2 + 11;
            CommonFunction.store32(bArr, (i3 + 9) * 4, (int) (j4 | (jArr[i13] << 31)));
            int i14 = i2 + 12;
            CommonFunction.store32(bArr, (i3 + 10) * 4, (int) ((jArr[i13] >> 1) | (jArr[i14] << 28)));
            long j5 = jArr[i14] >> 4;
            int i15 = i2 + 13;
            CommonFunction.store32(bArr, (i3 + 11) * 4, (int) (j5 | (jArr[i15] << 25)));
            long j6 = jArr[i15] >> 7;
            int i16 = i2 + 14;
            CommonFunction.store32(bArr, (i3 + 12) * 4, (int) (j6 | (jArr[i16] << 22)));
            long j7 = jArr[i16] >> 10;
            int i17 = i2 + 15;
            CommonFunction.store32(bArr, (i3 + 13) * 4, (int) (j7 | (jArr[i17] << 19)));
            long j8 = jArr[i17] >> 13;
            int i18 = i2 + 16;
            CommonFunction.store32(bArr, (i3 + 14) * 4, (int) (j8 | (jArr[i18] << 16)));
            long j9 = jArr[i18] >> 16;
            int i19 = i2 + 17;
            CommonFunction.store32(bArr, (i3 + 15) * 4, (int) (j9 | (jArr[i19] << 13)));
            long j10 = jArr[i19] >> 19;
            int i20 = i2 + 18;
            CommonFunction.store32(bArr, (i3 + 16) * 4, (int) (j10 | (jArr[i20] << 10)));
            long j11 = jArr[i20] >> 22;
            int i21 = i2 + 19;
            CommonFunction.store32(bArr, (i3 + 17) * 4, (int) (j11 | (jArr[i21] << 7)));
            long j12 = jArr[i21] >> 25;
            int i22 = i2 + 20;
            CommonFunction.store32(bArr, (i3 + 18) * 4, (int) (j12 | (jArr[i22] << 4)));
            int i23 = i2 + 22;
            CommonFunction.store32(bArr, (i3 + 19) * 4, (int) ((jArr[i22] >> 28) | (jArr[i2 + 21] << 1) | (jArr[i23] << 30)));
            long j13 = jArr[i23] >> 2;
            int i24 = i2 + 23;
            CommonFunction.store32(bArr, (i3 + 20) * 4, (int) (j13 | (jArr[i24] << 27)));
            long j14 = jArr[i24] >> 5;
            int i25 = i2 + 24;
            CommonFunction.store32(bArr, (i3 + 21) * 4, (int) (j14 | (jArr[i25] << 24)));
            long j15 = jArr[i25] >> 8;
            int i26 = i2 + 25;
            CommonFunction.store32(bArr, (i3 + 22) * 4, (int) (j15 | (jArr[i26] << 21)));
            long j16 = jArr[i26] >> 11;
            int i27 = i2 + 26;
            CommonFunction.store32(bArr, (i3 + 23) * 4, (int) (j16 | (jArr[i27] << 18)));
            long j17 = jArr[i27] >> 14;
            int i28 = i2 + 27;
            CommonFunction.store32(bArr, (i3 + 24) * 4, (int) (j17 | (jArr[i28] << 15)));
            int i29 = i2 + 28;
            CommonFunction.store32(bArr, (i3 + 25) * 4, (int) ((jArr[i28] >> 17) | (jArr[i29] << 12)));
            int i30 = i2 + 29;
            CommonFunction.store32(bArr, (i3 + 26) * 4, (int) ((jArr[i29] >> 20) | (jArr[i30] << 9)));
            int i31 = i2 + 30;
            CommonFunction.store32(bArr, (i3 + 27) * 4, (int) ((jArr[i30] >> 23) | (jArr[i31] << 6)));
            CommonFunction.store32(bArr, (i3 + 28) * 4, (int) ((jArr[i31] >> 26) | (jArr[i2 + 31] << 3)));
            i2 += 32;
        }
        System.arraycopy(bArr2, i, bArr, 14848, 32);
    }

    public static void encodeSignature(byte[] bArr, int i, byte[] bArr2, int i2, int[] iArr, int i3, int i4) {
        int i5 = 0;
        int i6 = 0;
        while (true) {
            int i7 = i3 * i4;
            if (i5 >= i7 / 32) {
                System.arraycopy(bArr2, i2, bArr, i + (i7 / 8), 32);
                return;
            }
            int i8 = i6 + 1;
            CommonFunction.store32(bArr, ((i5 + 0) * 4) + i, (iArr[i6 + 0] & 2097151) | (iArr[i8] << 21));
            int i9 = ((iArr[i8] >>> 11) & 1023) | ((iArr[i6 + 2] & 2097151) << 10);
            int i10 = i6 + 3;
            CommonFunction.store32(bArr, ((i5 + 1) * 4) + i, i9 | (iArr[i10] << 31));
            int i11 = (iArr[i10] >>> 1) & 1048575;
            int i12 = i6 + 4;
            CommonFunction.store32(bArr, ((i5 + 2) * 4) + i, i11 | (iArr[i12] << 20));
            int i13 = ((iArr[i12] >>> 12) & 511) | ((iArr[i6 + 5] & 2097151) << 9);
            int i14 = i6 + 6;
            CommonFunction.store32(bArr, ((i5 + 3) * 4) + i, i13 | (iArr[i14] << 30));
            int i15 = (iArr[i14] >>> 2) & 524287;
            int i16 = i6 + 7;
            CommonFunction.store32(bArr, ((i5 + 4) * 4) + i, i15 | (iArr[i16] << 19));
            int i17 = ((iArr[i16] >>> 13) & 255) | ((iArr[i6 + 8] & 2097151) << 8);
            int i18 = i6 + 9;
            CommonFunction.store32(bArr, ((i5 + 5) * 4) + i, i17 | (iArr[i18] << 29));
            int i19 = (iArr[i18] >>> 3) & 262143;
            int i20 = i6 + 10;
            CommonFunction.store32(bArr, ((i5 + 6) * 4) + i, i19 | (iArr[i20] << 18));
            int i21 = ((iArr[i20] >>> 14) & 127) | ((iArr[i6 + 11] & 2097151) << 7);
            int i22 = i6 + 12;
            CommonFunction.store32(bArr, ((i5 + 7) * 4) + i, i21 | (iArr[i22] << 28));
            int i23 = (iArr[i22] >>> 4) & 131071;
            int i24 = i6 + 13;
            CommonFunction.store32(bArr, ((i5 + 8) * 4) + i, i23 | (iArr[i24] << 17));
            int i25 = ((iArr[i24] >>> 15) & 63) | ((iArr[i6 + 14] & 2097151) << 6);
            int i26 = i6 + 15;
            CommonFunction.store32(bArr, ((i5 + 9) * 4) + i, i25 | (iArr[i26] << 27));
            int i27 = (iArr[i26] >>> 5) & 65535;
            int i28 = i6 + 16;
            CommonFunction.store32(bArr, ((i5 + 10) * 4) + i, i27 | (iArr[i28] << 16));
            int i29 = ((iArr[i28] >>> 16) & 31) | ((iArr[i6 + 17] & 2097151) << 5);
            int i30 = i6 + 18;
            CommonFunction.store32(bArr, ((i5 + 11) * 4) + i, i29 | (iArr[i30] << 26));
            int i31 = (iArr[i30] >>> 6) & 32767;
            int i32 = i6 + 19;
            CommonFunction.store32(bArr, ((i5 + 12) * 4) + i, i31 | (iArr[i32] << 15));
            int i33 = ((iArr[i32] >>> 17) & 15) | ((iArr[i6 + 20] & 2097151) << 4);
            int i34 = i6 + 21;
            CommonFunction.store32(bArr, ((i5 + 13) * 4) + i, i33 | (iArr[i34] << 25));
            int i35 = (iArr[i34] >>> 7) & 16383;
            int i36 = i6 + 22;
            CommonFunction.store32(bArr, ((i5 + 14) * 4) + i, i35 | (iArr[i36] << 14));
            int i37 = ((iArr[i36] >>> 18) & 7) | ((iArr[i6 + 23] & 2097151) << 3);
            int i38 = i6 + 24;
            CommonFunction.store32(bArr, ((i5 + 15) * 4) + i, i37 | (iArr[i38] << 24));
            int i39 = (iArr[i38] >>> 8) & 8191;
            int i40 = i6 + 25;
            CommonFunction.store32(bArr, ((i5 + 16) * 4) + i, i39 | (iArr[i40] << 13));
            int i41 = ((iArr[i40] >>> 19) & 3) | ((iArr[i6 + 26] & 2097151) << 2);
            int i42 = i6 + 27;
            CommonFunction.store32(bArr, ((i5 + 17) * 4) + i, i41 | (iArr[i42] << 23));
            int i43 = (iArr[i42] >>> 9) & 4095;
            int i44 = i6 + 28;
            CommonFunction.store32(bArr, ((i5 + 18) * 4) + i, i43 | (iArr[i44] << 12));
            int i45 = i6 + 30;
            CommonFunction.store32(bArr, ((i5 + 19) * 4) + i, ((iArr[i44] >>> 20) & 1) | ((2097151 & iArr[i6 + 29]) << 1) | (iArr[i45] << 22));
            CommonFunction.store32(bArr, ((i5 + 20) * 4) + i, ((iArr[i45] >>> 10) & MapFilePathConfig.MAX_DELETE_CODE_VALUE) | (iArr[i6 + 31] << 11));
            i6 += 32;
            i5 += i4;
        }
    }

    public static void encodeSignatureIIIP(byte[] bArr, int i, byte[] bArr2, int i2, long[] jArr) {
        int i3 = 0;
        for (int i4 = 0; i4 < 1536; i4 += 3) {
            int i5 = i3 + 1;
            CommonFunction.store32(bArr, ((i4 + 0) * 4) + i, (int) ((jArr[i3 + 0] & 16777215) | (jArr[i5] << 24)));
            int i6 = i3 + 2;
            CommonFunction.store32(bArr, ((i4 + 1) * 4) + i, (int) (((jArr[i5] >>> 8) & 65535) | (jArr[i6] << 16)));
            CommonFunction.store32(bArr, ((i4 + 2) * 4) + i, (int) (((jArr[i6] >>> 16) & 255) | (jArr[i3 + 3] << 8)));
            i3 += 4;
        }
        System.arraycopy(bArr2, i2, bArr, i + 6144, 32);
    }

    public static void encodeSignatureIIISpeed(byte[] bArr, int i, byte[] bArr2, int i2, int[] iArr) {
        int i3 = 0;
        for (int i4 = 0; i4 < 704; i4 += 11) {
            int i5 = i3 + 1;
            CommonFunction.store32(bArr, ((i4 + 0) * 4) + i, (iArr[i3 + 0] & 4194303) | (iArr[i5] << 22));
            int i6 = (iArr[i5] >>> 10) & 4095;
            int i7 = i3 + 2;
            CommonFunction.store32(bArr, ((i4 + 1) * 4) + i, i6 | (iArr[i7] << 12));
            int i8 = ((iArr[i7] >>> 20) & 3) | ((iArr[i3 + 3] & 4194303) << 2);
            int i9 = i3 + 4;
            CommonFunction.store32(bArr, ((i4 + 2) * 4) + i, i8 | (iArr[i9] << 24));
            int i10 = (iArr[i9] >>> 8) & 16383;
            int i11 = i3 + 5;
            CommonFunction.store32(bArr, ((i4 + 3) * 4) + i, i10 | (iArr[i11] << 14));
            int i12 = ((iArr[i11] >>> 18) & 15) | ((iArr[i3 + 6] & 4194303) << 4);
            int i13 = i3 + 7;
            CommonFunction.store32(bArr, ((i4 + 4) * 4) + i, i12 | (iArr[i13] << 26));
            int i14 = (iArr[i13] >>> 6) & 65535;
            int i15 = i3 + 8;
            CommonFunction.store32(bArr, ((i4 + 5) * 4) + i, i14 | (iArr[i15] << 16));
            int i16 = ((iArr[i15] >>> 16) & 63) | ((iArr[i3 + 9] & 4194303) << 6);
            int i17 = i3 + 10;
            CommonFunction.store32(bArr, ((i4 + 6) * 4) + i, i16 | (iArr[i17] << 28));
            int i18 = (iArr[i17] >>> 4) & 262143;
            int i19 = i3 + 11;
            CommonFunction.store32(bArr, ((i4 + 7) * 4) + i, i18 | (iArr[i19] << 18));
            int i20 = i3 + 13;
            CommonFunction.store32(bArr, ((i4 + 8) * 4) + i, ((iArr[i19] >>> 14) & 255) | ((4194303 & iArr[i3 + 12]) << 8) | (iArr[i20] << 30));
            int i21 = (iArr[i20] >>> 2) & 1048575;
            int i22 = i3 + 14;
            CommonFunction.store32(bArr, ((i4 + 9) * 4) + i, i21 | (iArr[i22] << 20));
            CommonFunction.store32(bArr, ((i4 + 10) * 4) + i, ((iArr[i22] >>> 12) & 1023) | (iArr[i3 + 15] << 10));
            i3 += 16;
        }
        System.arraycopy(bArr2, i2, bArr, i + 2816, 32);
    }

    public static void encodeSignatureIP(byte[] bArr, int i, byte[] bArr2, int i2, long[] jArr) {
        int i3 = 0;
        for (int i4 = 0; i4 < 704; i4 += 11) {
            int i5 = i3 + 1;
            CommonFunction.store32(bArr, ((i4 + 0) * 4) + i, (int) ((jArr[i3 + 0] & 4194303) | (jArr[i5] << 22)));
            int i6 = i3 + 2;
            CommonFunction.store32(bArr, ((i4 + 1) * 4) + i, (int) (((jArr[i5] >>> 10) & 4095) | (jArr[i6] << 12)));
            int i7 = i3 + 4;
            CommonFunction.store32(bArr, ((i4 + 2) * 4) + i, (int) (((jArr[i6] >>> 20) & 3) | ((jArr[i3 + 3] & 4194303) << 2) | (jArr[i7] << 24)));
            int i8 = i3 + 5;
            CommonFunction.store32(bArr, ((i4 + 3) * 4) + i, (int) (((jArr[i7] >>> 8) & 16383) | (jArr[i8] << 14)));
            int i9 = i3 + 7;
            CommonFunction.store32(bArr, ((i4 + 4) * 4) + i, (int) (((jArr[i8] >>> 18) & 15) | ((jArr[i3 + 6] & 4194303) << 4) | (jArr[i9] << 26)));
            int i10 = i3 + 8;
            CommonFunction.store32(bArr, ((i4 + 5) * 4) + i, (int) (((jArr[i9] >>> 6) & 65535) | (jArr[i10] << 16)));
            int i11 = i3 + 10;
            CommonFunction.store32(bArr, ((i4 + 6) * 4) + i, (int) (((jArr[i10] >>> 16) & 63) | ((jArr[i3 + 9] & 4194303) << 6) | (jArr[i11] << 28)));
            long j = (jArr[i11] >>> 4) & 262143;
            int i12 = i3 + 11;
            CommonFunction.store32(bArr, ((i4 + 7) * 4) + i, (int) (j | (jArr[i12] << 18)));
            int i13 = i3 + 13;
            CommonFunction.store32(bArr, ((i4 + 8) * 4) + i, (int) (((jArr[i12] >>> 14) & 255) | ((jArr[i3 + 12] & 4194303) << 8) | (jArr[i13] << 30)));
            long j2 = (jArr[i13] >>> 2) & 1048575;
            int i14 = i3 + 14;
            CommonFunction.store32(bArr, ((i4 + 9) * 4) + i, (int) (j2 | (jArr[i14] << 20)));
            CommonFunction.store32(bArr, ((i4 + 10) * 4) + i, (int) (((jArr[i14] >>> 12) & 1023) | (jArr[i3 + 15] << 10)));
            i3 += 16;
        }
        System.arraycopy(bArr2, i2, bArr, i + 2816, 32);
    }

    public static void packPrivateKey(byte[] bArr, long[] jArr, long[] jArr2, byte[] bArr2, int i, int i2, int i3) {
        for (int i4 = 0; i4 < i2; i4++) {
            bArr[i4] = (byte) jArr[i4];
        }
        for (int i5 = 0; i5 < i3; i5++) {
            for (int i6 = 0; i6 < i2; i6++) {
                bArr[i2 + (i5 * i2) + i6] = (byte) jArr2[r2 + i6];
            }
        }
        System.arraycopy(bArr2, i, bArr, i2 + (i3 * i2), 64);
    }
}
