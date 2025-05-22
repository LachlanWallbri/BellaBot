package io.netty.handler.codec.compression;

import com.google.common.base.Ascii;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
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
    /* JADX WARN: Removed duplicated region for block: B:141:0x0146 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x02cc  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0166  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int compress(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        int i5;
        long j;
        int i6;
        int i7;
        int i8;
        long j2;
        int i9;
        int i10;
        int i11;
        char c;
        int i12 = 2;
        int i13 = 1;
        int i14 = i4 == 0 ? i2 < 65536 ? 1 : 2 : i4;
        int i15 = i2 + 0;
        int i16 = i15 - 2;
        int i17 = i15 - 12;
        int[] iArr = new int[8192];
        if (i2 < 4) {
            if (i2 == 0) {
                return 0;
            }
            bArr2[i3 + 0] = (byte) (i2 - 1);
            int i18 = i16 + 1;
            int i19 = 1;
            for (int i20 = 0; i20 <= i18; i20++) {
                bArr2[i3 + i19] = bArr[i + i20];
                i19++;
            }
            return i2 + 1;
        }
        for (int i21 = 0; i21 < 8192; i21++) {
            iArr[i21] = 0;
        }
        bArr2[i3 + 0] = Ascii.f1926US;
        bArr2[i3 + 1] = bArr[i + 0];
        bArr2[i3 + 2] = bArr[i + 1];
        int i22 = 2;
        int i23 = 2;
        int i24 = 3;
        while (i22 < i17) {
            if (i14 == i12) {
                int i25 = i + i22;
                int i26 = i25 - 1;
                if (bArr[i25] == bArr[i26] && readU16(bArr, i26) == readU16(bArr, i25 + 1)) {
                    i5 = i22 + 3;
                    i6 = i22 + 2;
                    i7 = i13;
                    j = 1;
                    if (i7 != 0) {
                        int i27 = i + i5;
                        int hashFunction = hashFunction(bArr, i27);
                        int i28 = iArr[hashFunction];
                        long j3 = i22 - i28;
                        iArr[hashFunction] = i22;
                        if (j3 != 0 && (i14 != 1 ? j3 < 73725 : j3 < 8191)) {
                            int i29 = i28 + 1;
                            int i30 = i5 + 1;
                            if (bArr[i + i28] == bArr[i27]) {
                                int i31 = i29 + 1;
                                int i32 = i30 + 1;
                                if (bArr[i + i29] == bArr[i + i30]) {
                                    int i33 = i31 + 1;
                                    byte b = bArr[i + i31];
                                    int i34 = i32 + 1;
                                    if (b == bArr[i + i32]) {
                                        if (i14 != 2 || j3 < 8191) {
                                            i6 = i33;
                                            j = j3;
                                        } else {
                                            int i35 = i34 + 1;
                                            int i36 = i33 + 1;
                                            if (bArr[i + i34] == bArr[i + i33]) {
                                                i6 = i36 + 1;
                                                if (bArr[i + i35] == bArr[i + i36]) {
                                                    i8 = 5;
                                                    j = j3;
                                                    int i37 = i8 + i22;
                                                    j2 = j - 1;
                                                    if (j2 == 0) {
                                                        byte b2 = bArr[(i + i37) - 1];
                                                        while (i37 < i16) {
                                                            int i38 = i6 + 1;
                                                            if (bArr[i + i6] != b2) {
                                                                break;
                                                            }
                                                            i37++;
                                                            i6 = i38;
                                                        }
                                                    } else {
                                                        int i39 = i6 + 1;
                                                        int i40 = i37 + 1;
                                                        if (bArr[i + i6] == bArr[i + i37]) {
                                                            int i41 = i39 + 1;
                                                            int i42 = i40 + 1;
                                                            if (bArr[i + i39] == bArr[i + i40]) {
                                                                int i43 = i41 + 1;
                                                                i40 = i42 + 1;
                                                                if (bArr[i + i41] == bArr[i + i42]) {
                                                                    int i44 = i43 + 1;
                                                                    i42 = i40 + 1;
                                                                    if (bArr[i + i43] == bArr[i + i40]) {
                                                                        int i45 = i44 + 1;
                                                                        i40 = i42 + 1;
                                                                        if (bArr[i + i44] == bArr[i + i42]) {
                                                                            int i46 = i45 + 1;
                                                                            i42 = i40 + 1;
                                                                            if (bArr[i + i45] == bArr[i + i40]) {
                                                                                int i47 = i46 + 1;
                                                                                i40 = i42 + 1;
                                                                                if (bArr[i + i46] == bArr[i + i42]) {
                                                                                    int i48 = i47 + 1;
                                                                                    i42 = i40 + 1;
                                                                                    if (bArr[i + i47] == bArr[i + i40]) {
                                                                                        int i49 = i48;
                                                                                        i37 = i42;
                                                                                        while (i37 < i16) {
                                                                                            int i50 = i49 + 1;
                                                                                            i40 = i37 + 1;
                                                                                            if (bArr[i + i49] == bArr[i + i37]) {
                                                                                                i49 = i50;
                                                                                                i37 = i40;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            i37 = i42;
                                                        }
                                                        i37 = i40;
                                                        break;
                                                    }
                                                    if (i23 != 0) {
                                                        bArr2[((i3 + i24) - i23) - 1] = (byte) (i23 - 1);
                                                    } else {
                                                        i24--;
                                                    }
                                                    int i51 = i37 - 3;
                                                    int i52 = i51 - i22;
                                                    long j4 = 224;
                                                    char c2 = '\b';
                                                    if (i14 != 2) {
                                                        if (i52 > 262) {
                                                            for (int i53 = 262; i52 > i53; i53 = 262) {
                                                                int i54 = i24 + 1;
                                                                bArr2[i3 + i24] = (byte) ((j2 >>> c2) + j4);
                                                                int i55 = i54 + 1;
                                                                bArr2[i3 + i54] = -3;
                                                                bArr2[i3 + i55] = (byte) (j2 & 255);
                                                                i52 -= 262;
                                                                i24 = i55 + 1;
                                                                j4 = 224;
                                                                c2 = '\b';
                                                            }
                                                        }
                                                        if (i52 < 7) {
                                                            int i56 = i24 + 1;
                                                            bArr2[i3 + i24] = (byte) ((i52 << 5) + (j2 >>> 8));
                                                            i9 = i56 + 1;
                                                            bArr2[i3 + i56] = (byte) (j2 & 255);
                                                        } else {
                                                            int i57 = i24 + 1;
                                                            bArr2[i3 + i24] = (byte) ((j2 >>> 8) + 224);
                                                            int i58 = i57 + 1;
                                                            bArr2[i3 + i57] = (byte) (i52 - 7);
                                                            i9 = i58 + 1;
                                                            bArr2[i3 + i58] = (byte) (j2 & 255);
                                                        }
                                                    } else if (j2 < 8191) {
                                                        if (i52 < 7) {
                                                            int i59 = i24 + 1;
                                                            bArr2[i3 + i24] = (byte) ((i52 << 5) + (j2 >>> 8));
                                                            i9 = i59 + 1;
                                                            bArr2[i3 + i59] = (byte) (j2 & 255);
                                                        } else {
                                                            int i60 = i24 + 1;
                                                            bArr2[i3 + i24] = (byte) ((j2 >>> 8) + 224);
                                                            int i61 = i52 - 7;
                                                            while (i61 >= 255) {
                                                                bArr2[i3 + i60] = -1;
                                                                i61 -= 255;
                                                                i60++;
                                                            }
                                                            int i62 = i60 + 1;
                                                            bArr2[i3 + i60] = (byte) i61;
                                                            i9 = i62 + 1;
                                                            bArr2[i3 + i62] = (byte) (j2 & 255);
                                                        }
                                                    } else if (i52 < 7) {
                                                        long j5 = j2 - 8191;
                                                        int i63 = i24 + 1;
                                                        bArr2[i3 + i24] = (byte) ((i52 << 5) + 31);
                                                        int i64 = i63 + 1;
                                                        bArr2[i3 + i63] = -1;
                                                        int i65 = i64 + 1;
                                                        bArr2[i3 + i64] = (byte) (j5 >>> 8);
                                                        i9 = i65 + 1;
                                                        bArr2[i3 + i65] = (byte) (j5 & 255);
                                                    } else {
                                                        long j6 = j2 - 8191;
                                                        int i66 = i24 + 1;
                                                        bArr2[i3 + i24] = -1;
                                                        int i67 = i52 - 7;
                                                        while (i67 >= 255) {
                                                            bArr2[i3 + i66] = -1;
                                                            i67 -= 255;
                                                            i66++;
                                                        }
                                                        int i68 = i66 + 1;
                                                        bArr2[i3 + i66] = (byte) i67;
                                                        int i69 = i68 + 1;
                                                        bArr2[i3 + i68] = -1;
                                                        int i70 = i69 + 1;
                                                        bArr2[i3 + i69] = (byte) (j6 >>> 8);
                                                        bArr2[i3 + i70] = (byte) (j6 & 255);
                                                        i9 = i70 + 1;
                                                    }
                                                    int i71 = i51 + 1;
                                                    iArr[hashFunction(bArr, i + i51)] = i51;
                                                    i22 = i71 + 1;
                                                    iArr[hashFunction(bArr, i + i71)] = i71;
                                                    i24 = i9 + 1;
                                                    bArr2[i3 + i9] = Ascii.f1926US;
                                                    i12 = 2;
                                                    i13 = 1;
                                                    i23 = 0;
                                                }
                                            }
                                            i10 = i24 + 1;
                                            i11 = i22 + 1;
                                            bArr2[i3 + i24] = bArr[i + i22];
                                            i23++;
                                            if (i23 == 32) {
                                                i24 = i10 + 1;
                                                c = 31;
                                                bArr2[i3 + i10] = Ascii.f1926US;
                                                i22 = i11;
                                                i12 = 2;
                                                i13 = 1;
                                                i23 = 0;
                                            } else {
                                                i24 = i10;
                                                i22 = i11;
                                                i12 = 2;
                                                i13 = 1;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        i10 = i24 + 1;
                        i11 = i22 + 1;
                        bArr2[i3 + i24] = bArr[i + i22];
                        i23++;
                        if (i23 == 32) {
                            i24 = i10 + 1;
                            c = 31;
                            bArr2[i3 + i10] = Ascii.f1926US;
                            i22 = i11;
                            i12 = 2;
                            i13 = 1;
                            i23 = 0;
                        } else {
                            i24 = i10;
                            i22 = i11;
                            i12 = 2;
                            i13 = 1;
                        }
                    }
                    i8 = 3;
                    int i372 = i8 + i22;
                    j2 = j - 1;
                    if (j2 == 0) {
                    }
                    if (i23 != 0) {
                    }
                    int i512 = i372 - 3;
                    int i522 = i512 - i22;
                    long j42 = 224;
                    char c22 = '\b';
                    if (i14 != 2) {
                    }
                    int i712 = i512 + 1;
                    iArr[hashFunction(bArr, i + i512)] = i512;
                    i22 = i712 + 1;
                    iArr[hashFunction(bArr, i + i712)] = i712;
                    i24 = i9 + 1;
                    bArr2[i3 + i9] = Ascii.f1926US;
                    i12 = 2;
                    i13 = 1;
                    i23 = 0;
                }
            }
            i5 = i22;
            j = 0;
            i6 = 0;
            i7 = 0;
            if (i7 != 0) {
            }
            i8 = 3;
            int i3722 = i8 + i22;
            j2 = j - 1;
            if (j2 == 0) {
            }
            if (i23 != 0) {
            }
            int i5122 = i3722 - 3;
            int i5222 = i5122 - i22;
            long j422 = 224;
            char c222 = '\b';
            if (i14 != 2) {
            }
            int i7122 = i5122 + 1;
            iArr[hashFunction(bArr, i + i5122)] = i5122;
            i22 = i7122 + 1;
            iArr[hashFunction(bArr, i + i7122)] = i7122;
            i24 = i9 + 1;
            bArr2[i3 + i9] = Ascii.f1926US;
            i12 = 2;
            i13 = 1;
            i23 = 0;
        }
        int i72 = i16 + i13;
        while (i22 <= i72) {
            int i73 = i24 + 1;
            int i74 = i22 + 1;
            bArr2[i3 + i24] = bArr[i + i22];
            i23++;
            if (i23 == 32) {
                i24 = i73 + 1;
                bArr2[i3 + i73] = Ascii.f1926US;
                i22 = i74;
                i23 = 0;
            } else {
                i24 = i73;
                i22 = i74;
            }
        }
        if (i23 != 0) {
            bArr2[((i3 + i24) - i23) - 1] = (byte) (i23 - 1);
        } else {
            i24--;
        }
        if (i14 == 2) {
            bArr2[i3] = (byte) (bArr2[i3] | 32);
        }
        return i24;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x009b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int decompress(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        boolean z;
        int i5;
        boolean z2;
        byte b;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        char c = 5;
        boolean z3 = true;
        int i11 = (bArr[i] >> 5) + 1;
        if (i11 != 1 && i11 != 2) {
            throw new DecompressionException(String.format("invalid level: %d (expected: %d or %d)", Integer.valueOf(i11), 1, 2));
        }
        long j = bArr[i + 0] & Ascii.f1926US;
        int i12 = 1;
        boolean z4 = true;
        int i13 = 0;
        while (true) {
            long j2 = j >> c;
            long j3 = (31 & j) << 8;
            if (j >= 32) {
                long j4 = j2 - 1;
                long j5 = j;
                long j6 = i13;
                int i14 = (int) (j6 - j3);
                if (j4 != 6) {
                    i5 = i13;
                    z2 = z4;
                    b = 255;
                } else if (i11 == 1) {
                    i5 = i13;
                    j4 += bArr[i + i12] & 255;
                    i12++;
                    z2 = z4;
                    i6 = 1;
                    b = 255;
                    if (i11 != i6) {
                        i7 = i12 + 1;
                        i8 = i14 - (bArr[i + i12] & b);
                    } else {
                        i7 = i12 + 1;
                        int i15 = bArr[i + i12] & b;
                        i8 = i14 - i15;
                        if (i15 == b && j3 == 7936) {
                            long j7 = (bArr[i + i7] & b) << 8;
                            i7 = i7 + 1 + 1;
                            i8 = (int) ((j6 - (j7 + (bArr[i + r3] & b))) - 8191);
                        }
                    }
                    if (j6 + j4 + 3 <= i4 || i8 - 1 < 0) {
                        return 0;
                    }
                    if (i7 >= i2) {
                        i9 = i7 + 1;
                        j = bArr[i + i7] & 255;
                        i10 = i5;
                        z4 = z2;
                    } else {
                        i9 = i7;
                        j = j5;
                        i10 = i5;
                        z4 = false;
                    }
                    if (i8 != i10) {
                        z3 = true;
                        byte b2 = bArr2[(i3 + i8) - 1];
                        int i16 = i10 + 1;
                        bArr2[i3 + i10] = b2;
                        int i17 = i16 + 1;
                        bArr2[i3 + i16] = b2;
                        int i18 = i17 + 1;
                        bArr2[i3 + i17] = b2;
                        while (j4 != 0) {
                            bArr2[i3 + i18] = b2;
                            j4--;
                            i18++;
                        }
                        i13 = i18;
                        z = false;
                        i12 = i9;
                    } else {
                        z3 = true;
                        int i19 = i8 - 1;
                        int i20 = i10 + 1;
                        int i21 = i19 + 1;
                        bArr2[i3 + i10] = bArr2[i3 + i19];
                        int i22 = i20 + 1;
                        int i23 = i3 + i20;
                        int i24 = i21 + 1;
                        bArr2[i23] = bArr2[i3 + i21];
                        i13 = i22 + 1;
                        int i25 = i24 + 1;
                        bArr2[i3 + i22] = bArr2[i3 + i24];
                        while (j4 != 0) {
                            bArr2[i3 + i13] = bArr2[i3 + i25];
                            j4--;
                            i13++;
                            i25++;
                        }
                        i12 = i9;
                        z = false;
                    }
                } else {
                    i5 = i13;
                    while (true) {
                        b = 255;
                        int i26 = bArr[i + i12] & 255;
                        z2 = z4;
                        j4 += i26;
                        i12++;
                        if (i26 != 255) {
                            break;
                        }
                        z4 = z2;
                    }
                }
                i6 = 1;
                if (i11 != i6) {
                }
                if (j6 + j4 + 3 <= i4) {
                    return 0;
                }
                if (i7 >= i2) {
                }
                if (i8 != i10) {
                }
            } else {
                long j8 = j + 1;
                if (i13 + j8 > i4) {
                    return 0;
                }
                z = false;
                if (i12 + j8 > i2) {
                    return 0;
                }
                int i27 = i13 + 1;
                int i28 = i12 + 1;
                bArr2[i3 + i13] = bArr[i + i12];
                j = j8 - 1;
                while (j != 0) {
                    bArr2[i3 + i27] = bArr[i + i28];
                    j--;
                    i27++;
                    i28++;
                }
                boolean z5 = i28 < i2 ? z3 : false;
                if (z5) {
                    long j9 = bArr[i + i28] & 255;
                    boolean z6 = z5;
                    i13 = i27;
                    i12 = i28 + 1;
                    z4 = z6;
                    j = j9;
                } else {
                    i12 = i28;
                    z4 = z5;
                    i13 = i27;
                }
            }
            if (!z4) {
                return i13;
            }
            c = 5;
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
