package io.grpc.netty.shaded.io.netty.handler.codec.compression;

import com.google.common.base.Ascii;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class FastLz {
    static final byte BLOCK_TYPE_COMPRESSED = 1;
    static final byte BLOCK_TYPE_NON_COMPRESSED = 0;
    static final byte BLOCK_WITHOUT_CHECKSUM = 0;
    static final byte BLOCK_WITH_CHECKSUM = 16;
    static final int CHECKSUM_OFFSET = 4;
    private static final int HASH_LOG = 13;
    private static final int HASH_MASK = 8191;
    private static final int HASH_SIZE = 8192;
    static final int LEVEL_1 = 1;
    static final int LEVEL_2 = 2;
    static final int LEVEL_AUTO = 0;
    static final int MAGIC_NUMBER = 4607066;
    static final int MAX_CHUNK_LENGTH = 65535;
    private static final int MAX_COPY = 32;
    private static final int MAX_DISTANCE = 8191;
    private static final int MAX_FARDISTANCE = 73725;
    private static final int MAX_LEN = 264;
    static final int MIN_LENGTH_TO_COMPRESSION = 32;
    private static final int MIN_RECOMENDED_LENGTH_FOR_LEVEL_2 = 65536;
    static final int OPTIONS_OFFSET = 3;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int calculateOutputBufferLength(int i) {
        return Math.max((int) (i * 1.06d), 66);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0143 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x02c9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int compress(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        int i5;
        long j;
        int i6;
        int i7;
        long j2;
        int i8;
        long j3;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        char c;
        int i14 = 2;
        int i15 = 1;
        int i16 = i4 == 0 ? i2 < 65536 ? 1 : 2 : i4;
        int i17 = i2 + 0;
        int i18 = i17 - 2;
        int i19 = i17 - 12;
        int[] iArr = new int[8192];
        if (i2 < 4) {
            if (i2 == 0) {
                return 0;
            }
            bArr2[i3 + 0] = (byte) (i2 - 1);
            int i20 = i18 + 1;
            int i21 = 1;
            for (int i22 = 0; i22 <= i20; i22++) {
                bArr2[i3 + i21] = bArr[i + i22];
                i21++;
            }
            return i2 + 1;
        }
        for (int i23 = 0; i23 < 8192; i23++) {
            iArr[i23] = 0;
        }
        bArr2[i3 + 0] = Ascii.f1926US;
        bArr2[i3 + 1] = bArr[i + 0];
        bArr2[i3 + 2] = bArr[i + 1];
        int i24 = 2;
        int i25 = 2;
        int i26 = 3;
        while (i24 < i19) {
            if (i16 == i14) {
                int i27 = i + i24;
                int i28 = i27 - 1;
                if (bArr[i27] == bArr[i28] && readU16(bArr, i28) == readU16(bArr, i27 + 1)) {
                    j = 1;
                    i7 = i24 + 2;
                    i5 = i24 + 3;
                    i6 = i15;
                    if (i6 != 0) {
                        int i29 = i + i5;
                        int hashFunction = hashFunction(bArr, i29);
                        int i30 = iArr[hashFunction];
                        j2 = i24 - i30;
                        iArr[hashFunction] = i24;
                        if (j2 != 0 && (i16 != 1 ? j2 < 73725 : j2 < 8191)) {
                            int i31 = i30 + 1;
                            int i32 = i5 + 1;
                            if (bArr[i + i30] == bArr[i29]) {
                                int i33 = i31 + 1;
                                int i34 = i32 + 1;
                                if (bArr[i + i31] == bArr[i + i32]) {
                                    i7 = i33 + 1;
                                    int i35 = i34 + 1;
                                    if (bArr[i + i33] == bArr[i + i34]) {
                                        if (i16 == 2 && j2 >= 8191) {
                                            int i36 = i35 + 1;
                                            int i37 = i7 + 1;
                                            if (bArr[i + i35] == bArr[i + i7]) {
                                                i7 = i37 + 1;
                                                if (bArr[i + i36] == bArr[i + i37]) {
                                                    i8 = 5;
                                                    int i38 = i8 + i24;
                                                    j3 = j2 - 1;
                                                    if (j3 == 0) {
                                                        byte b = bArr[(i + i38) - 1];
                                                        while (i38 < i18) {
                                                            int i39 = i7 + 1;
                                                            if (bArr[i + i7] != b) {
                                                                break;
                                                            }
                                                            i38++;
                                                            i7 = i39;
                                                        }
                                                    } else {
                                                        int i40 = i7 + 1;
                                                        i9 = i38 + 1;
                                                        if (bArr[i + i7] == bArr[i + i38]) {
                                                            int i41 = i40 + 1;
                                                            int i42 = i9 + 1;
                                                            if (bArr[i + i40] == bArr[i + i9]) {
                                                                int i43 = i41 + 1;
                                                                i9 = i42 + 1;
                                                                if (bArr[i + i41] == bArr[i + i42]) {
                                                                    int i44 = i43 + 1;
                                                                    i42 = i9 + 1;
                                                                    if (bArr[i + i43] == bArr[i + i9]) {
                                                                        int i45 = i44 + 1;
                                                                        i9 = i42 + 1;
                                                                        if (bArr[i + i44] == bArr[i + i42]) {
                                                                            int i46 = i45 + 1;
                                                                            i42 = i9 + 1;
                                                                            if (bArr[i + i45] == bArr[i + i9]) {
                                                                                int i47 = i46 + 1;
                                                                                i9 = i42 + 1;
                                                                                if (bArr[i + i46] == bArr[i + i42]) {
                                                                                    int i48 = i47 + 1;
                                                                                    i42 = i9 + 1;
                                                                                    if (bArr[i + i47] == bArr[i + i9]) {
                                                                                        int i49 = i48;
                                                                                        i38 = i42;
                                                                                        while (i38 < i18) {
                                                                                            int i50 = i49 + 1;
                                                                                            i9 = i38 + 1;
                                                                                            if (bArr[i + i49] != bArr[i + i38]) {
                                                                                                break;
                                                                                            }
                                                                                            i49 = i50;
                                                                                            i38 = i9;
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            i9 = i42;
                                                        }
                                                        if (i25 == 0) {
                                                            bArr2[((i3 + i26) - i25) - 1] = (byte) (i25 - 1);
                                                        } else {
                                                            i26--;
                                                        }
                                                        int i51 = i9 - 3;
                                                        int i52 = i51 - i24;
                                                        char c2 = '\b';
                                                        if (i16 != 2) {
                                                            if (j3 < 8191) {
                                                                if (i52 < 7) {
                                                                    int i53 = i26 + 1;
                                                                    bArr2[i3 + i26] = (byte) ((i52 << 5) + (j3 >>> 8));
                                                                    i11 = i53 + 1;
                                                                    bArr2[i3 + i53] = (byte) (j3 & 255);
                                                                } else {
                                                                    int i54 = i26 + 1;
                                                                    bArr2[i3 + i26] = (byte) ((j3 >>> 8) + 224);
                                                                    int i55 = i52 - 7;
                                                                    while (i55 >= 255) {
                                                                        bArr2[i3 + i54] = -1;
                                                                        i55 -= 255;
                                                                        i54++;
                                                                    }
                                                                    int i56 = i54 + 1;
                                                                    bArr2[i3 + i54] = (byte) i55;
                                                                    i11 = i56 + 1;
                                                                    bArr2[i3 + i56] = (byte) (j3 & 255);
                                                                }
                                                            } else if (i52 < 7) {
                                                                long j4 = j3 - 8191;
                                                                int i57 = i26 + 1;
                                                                bArr2[i3 + i26] = (byte) ((i52 << 5) + 31);
                                                                int i58 = i57 + 1;
                                                                bArr2[i3 + i57] = -1;
                                                                int i59 = i58 + 1;
                                                                bArr2[i3 + i58] = (byte) (j4 >>> 8);
                                                                i11 = i59 + 1;
                                                                bArr2[i3 + i59] = (byte) (j4 & 255);
                                                            } else {
                                                                long j5 = j3 - 8191;
                                                                int i60 = i26 + 1;
                                                                bArr2[i3 + i26] = -1;
                                                                int i61 = i52 - 7;
                                                                while (i61 >= 255) {
                                                                    bArr2[i3 + i60] = -1;
                                                                    i61 -= 255;
                                                                    i60++;
                                                                }
                                                                int i62 = i60 + 1;
                                                                bArr2[i3 + i60] = (byte) i61;
                                                                int i63 = i62 + 1;
                                                                bArr2[i3 + i62] = -1;
                                                                int i64 = i63 + 1;
                                                                bArr2[i3 + i63] = (byte) (j5 >>> 8);
                                                                i11 = i64 + 1;
                                                                bArr2[i3 + i64] = (byte) (j5 & 255);
                                                            }
                                                            i10 = i51;
                                                        } else {
                                                            if (i52 > 262) {
                                                                while (i52 > 262) {
                                                                    int i65 = i26 + 1;
                                                                    bArr2[i3 + i26] = (byte) ((j3 >>> c2) + 224);
                                                                    int i66 = i65 + 1;
                                                                    bArr2[i3 + i65] = -3;
                                                                    i26 = i66 + 1;
                                                                    bArr2[i3 + i66] = (byte) (j3 & 255);
                                                                    i52 -= 262;
                                                                    i51 = i51;
                                                                    c2 = '\b';
                                                                }
                                                            }
                                                            i10 = i51;
                                                            if (i52 < 7) {
                                                                int i67 = i26 + 1;
                                                                bArr2[i3 + i26] = (byte) ((i52 << 5) + (j3 >>> 8));
                                                                i11 = i67 + 1;
                                                                bArr2[i3 + i67] = (byte) (j3 & 255);
                                                            } else {
                                                                int i68 = i26 + 1;
                                                                bArr2[i3 + i26] = (byte) ((j3 >>> 8) + 224);
                                                                int i69 = i68 + 1;
                                                                bArr2[i3 + i68] = (byte) (i52 - 7);
                                                                i11 = i69 + 1;
                                                                bArr2[i3 + i69] = (byte) (j3 & 255);
                                                            }
                                                        }
                                                        int i70 = i10 + 1;
                                                        iArr[hashFunction(bArr, i + i10)] = i10;
                                                        i24 = i70 + 1;
                                                        iArr[hashFunction(bArr, i + i70)] = i70;
                                                        i26 = i11 + 1;
                                                        bArr2[i3 + i11] = Ascii.f1926US;
                                                        i14 = 2;
                                                        i15 = 1;
                                                        i25 = 0;
                                                    }
                                                    i9 = i38;
                                                    if (i25 == 0) {
                                                    }
                                                    int i512 = i9 - 3;
                                                    int i522 = i512 - i24;
                                                    char c22 = '\b';
                                                    if (i16 != 2) {
                                                    }
                                                    int i702 = i10 + 1;
                                                    iArr[hashFunction(bArr, i + i10)] = i10;
                                                    i24 = i702 + 1;
                                                    iArr[hashFunction(bArr, i + i702)] = i702;
                                                    i26 = i11 + 1;
                                                    bArr2[i3 + i11] = Ascii.f1926US;
                                                    i14 = 2;
                                                    i15 = 1;
                                                    i25 = 0;
                                                }
                                            }
                                            i12 = i26 + 1;
                                            i13 = i24 + 1;
                                            bArr2[i3 + i26] = bArr[i + i24];
                                            i25++;
                                            if (i25 == 32) {
                                                i26 = i12 + 1;
                                                c = 31;
                                                bArr2[i3 + i12] = Ascii.f1926US;
                                                i24 = i13;
                                                i14 = 2;
                                                i15 = 1;
                                                i25 = 0;
                                            } else {
                                                i26 = i12;
                                                i24 = i13;
                                                i14 = 2;
                                                i15 = 1;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        i12 = i26 + 1;
                        i13 = i24 + 1;
                        bArr2[i3 + i26] = bArr[i + i24];
                        i25++;
                        if (i25 == 32) {
                            i26 = i12 + 1;
                            c = 31;
                            bArr2[i3 + i12] = Ascii.f1926US;
                            i24 = i13;
                            i14 = 2;
                            i15 = 1;
                            i25 = 0;
                        } else {
                            i26 = i12;
                            i24 = i13;
                            i14 = 2;
                            i15 = 1;
                        }
                    } else {
                        j2 = j;
                    }
                    i8 = 3;
                    int i382 = i8 + i24;
                    j3 = j2 - 1;
                    if (j3 == 0) {
                    }
                    i9 = i382;
                    if (i25 == 0) {
                    }
                    int i5122 = i9 - 3;
                    int i5222 = i5122 - i24;
                    char c222 = '\b';
                    if (i16 != 2) {
                    }
                    int i7022 = i10 + 1;
                    iArr[hashFunction(bArr, i + i10)] = i10;
                    i24 = i7022 + 1;
                    iArr[hashFunction(bArr, i + i7022)] = i7022;
                    i26 = i11 + 1;
                    bArr2[i3 + i11] = Ascii.f1926US;
                    i14 = 2;
                    i15 = 1;
                    i25 = 0;
                }
            }
            i5 = i24;
            j = 0;
            i6 = 0;
            i7 = 0;
            if (i6 != 0) {
            }
            i8 = 3;
            int i3822 = i8 + i24;
            j3 = j2 - 1;
            if (j3 == 0) {
            }
            i9 = i3822;
            if (i25 == 0) {
            }
            int i51222 = i9 - 3;
            int i52222 = i51222 - i24;
            char c2222 = '\b';
            if (i16 != 2) {
            }
            int i70222 = i10 + 1;
            iArr[hashFunction(bArr, i + i10)] = i10;
            i24 = i70222 + 1;
            iArr[hashFunction(bArr, i + i70222)] = i70222;
            i26 = i11 + 1;
            bArr2[i3 + i11] = Ascii.f1926US;
            i14 = 2;
            i15 = 1;
            i25 = 0;
        }
        int i71 = i18 + i15;
        while (i24 <= i71) {
            int i72 = i26 + 1;
            int i73 = i24 + 1;
            bArr2[i3 + i26] = bArr[i + i24];
            i25++;
            if (i25 == 32) {
                i26 = i72 + 1;
                bArr2[i3 + i72] = Ascii.f1926US;
                i24 = i73;
                i25 = 0;
            } else {
                i26 = i72;
                i24 = i73;
            }
        }
        if (i25 != 0) {
            bArr2[((i3 + i26) - i25) - 1] = (byte) (i25 - 1);
        } else {
            i26--;
        }
        if (i16 == 2) {
            bArr2[i3] = (byte) (bArr2[i3] | 32);
        }
        return i26;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decompress(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        boolean z;
        boolean z2;
        int i5;
        long j;
        boolean z3;
        byte b;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        char c = 5;
        boolean z4 = true;
        int i11 = (bArr[i] >> 5) + 1;
        if (i11 != 1 && i11 != 2) {
            throw new DecompressionException(String.format("invalid level: %d (expected: %d or %d)", Integer.valueOf(i11), 1, 2));
        }
        int i12 = 1;
        boolean z5 = true;
        long j2 = bArr[i + 0] & Ascii.f1926US;
        int i13 = 0;
        while (true) {
            long j3 = j2 >> c;
            long j4 = (31 & j2) << 8;
            if (j2 >= 32) {
                long j5 = j3 - 1;
                long j6 = i13;
                int i14 = i13;
                int i15 = (int) (j6 - j4);
                if (j5 != 6) {
                    j = j2;
                    z3 = z5;
                    b = 255;
                    i6 = i12;
                } else if (i11 == 1) {
                    i6 = i12 + 1;
                    b = 255;
                    z3 = z5;
                    j5 += bArr[i + i12] & 255;
                    j = j2;
                } else {
                    z3 = z5;
                    b = 255;
                    while (true) {
                        i10 = i12 + 1;
                        int i16 = bArr[i + i12] & 255;
                        j = j2;
                        j5 += i16;
                        if (i16 != 255) {
                            break;
                        }
                        i12 = i10;
                        j2 = j;
                    }
                    i6 = i10;
                }
                if (i11 == 1) {
                    i7 = i6 + 1;
                    i8 = i15 - (bArr[i + i6] & b);
                } else {
                    i7 = i6 + 1;
                    int i17 = bArr[i + i6] & b;
                    i8 = i15 - i17;
                    if (i17 == b && j4 == 7936) {
                        int i18 = i7 + 1 + 1;
                        i8 = (int) ((j6 - (((bArr[i + i7] & b) << 8) + (bArr[i + r5] & b))) - 8191);
                        i7 = i18;
                    }
                }
                if (j6 + j5 + 3 > i4 || i8 - 1 < 0) {
                    return 0;
                }
                if (i7 < i2) {
                    j = bArr[i + i7] & 255;
                    i7++;
                    i9 = i14;
                } else {
                    i9 = i14;
                    z3 = false;
                }
                if (i8 == i9) {
                    z = true;
                    byte b2 = bArr2[(i3 + i8) - 1];
                    int i19 = i9 + 1;
                    bArr2[i3 + i9] = b2;
                    int i20 = i19 + 1;
                    bArr2[i3 + i19] = b2;
                    i5 = i20 + 1;
                    bArr2[i3 + i20] = b2;
                    while (j5 != 0) {
                        bArr2[i3 + i5] = b2;
                        j5--;
                        i5++;
                    }
                } else {
                    z = true;
                    int i21 = i8 - 1;
                    int i22 = i9 + 1;
                    int i23 = i21 + 1;
                    bArr2[i3 + i9] = bArr2[i3 + i21];
                    int i24 = i22 + 1;
                    int i25 = i23 + 1;
                    bArr2[i3 + i22] = bArr2[i3 + i23];
                    int i26 = i24 + 1;
                    int i27 = i25 + 1;
                    bArr2[i3 + i24] = bArr2[i3 + i25];
                    while (j5 != 0) {
                        bArr2[i3 + i26] = bArr2[i3 + i27];
                        j5--;
                        i26++;
                        i27++;
                    }
                    i5 = i26;
                }
                i12 = i7;
                z5 = z3;
                j2 = j;
                z2 = false;
            } else {
                int i28 = i13;
                z = z4;
                long j7 = j2 + 1;
                if (i28 + j7 > i4) {
                    return 0;
                }
                z2 = false;
                if (i12 + j7 > i2) {
                    return 0;
                }
                i5 = i28 + 1;
                int i29 = i12 + 1;
                bArr2[i3 + i28] = bArr[i + i12];
                j2 = j7 - 1;
                while (j2 != 0) {
                    bArr2[i3 + i5] = bArr[i + i29];
                    j2--;
                    i5++;
                    i29++;
                }
                boolean z6 = i29 < i2 ? z : false;
                if (z6) {
                    int i30 = i29 + 1;
                    long j8 = bArr[i + i29] & 255;
                    z5 = z6;
                    i12 = i30;
                    j2 = j8;
                } else {
                    i12 = i29;
                    z5 = z6;
                }
            }
            if (!z5) {
                return i5;
            }
            c = 5;
            z4 = z;
            i13 = i5;
        }
    }

    private static int hashFunction(byte[] bArr, int i) {
        int readU16 = readU16(bArr, i);
        return ((readU16(bArr, i + 1) ^ (readU16 >> 3)) ^ readU16) & 8191;
    }

    private static int readU16(byte[] bArr, int i) {
        int i2 = i + 1;
        if (i2 >= bArr.length) {
            return bArr[i] & 255;
        }
        return (bArr[i] & 255) | ((bArr[i2] & 255) << 8);
    }

    private FastLz() {
    }
}
