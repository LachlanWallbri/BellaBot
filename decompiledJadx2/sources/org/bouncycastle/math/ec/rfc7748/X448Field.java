package org.bouncycastle.math.ec.rfc7748;

/* loaded from: classes9.dex */
public abstract class X448Field {
    private static final int M28 = 268435455;
    public static final int SIZE = 16;
    private static final long U32 = 4294967295L;

    protected X448Field() {
    }

    public static void add(int[] iArr, int[] iArr2, int[] iArr3) {
        for (int i = 0; i < 16; i++) {
            iArr3[i] = iArr[i] + iArr2[i];
        }
    }

    public static void addOne(int[] iArr) {
        iArr[0] = iArr[0] + 1;
    }

    public static void addOne(int[] iArr, int i) {
        iArr[i] = iArr[i] + 1;
    }

    public static void carry(int[] iArr) {
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = iArr[2];
        int i4 = iArr[3];
        int i5 = iArr[4];
        int i6 = iArr[5];
        int i7 = iArr[6];
        int i8 = iArr[7];
        int i9 = iArr[8];
        int i10 = iArr[9];
        int i11 = iArr[10];
        int i12 = iArr[11];
        int i13 = iArr[12];
        int i14 = iArr[13];
        int i15 = iArr[14];
        int i16 = iArr[15];
        int i17 = i3 + (i2 >>> 28);
        int i18 = i2 & M28;
        int i19 = i7 + (i6 >>> 28);
        int i20 = i6 & M28;
        int i21 = i11 + (i10 >>> 28);
        int i22 = i10 & M28;
        int i23 = i15 + (i14 >>> 28);
        int i24 = i14 & M28;
        int i25 = i4 + (i17 >>> 28);
        int i26 = i17 & M28;
        int i27 = i8 + (i19 >>> 28);
        int i28 = i19 & M28;
        int i29 = i12 + (i21 >>> 28);
        int i30 = i21 & M28;
        int i31 = i16 + (i23 >>> 28);
        int i32 = i23 & M28;
        int i33 = i31 >>> 28;
        int i34 = i31 & M28;
        int i35 = i + i33;
        int i36 = i5 + (i25 >>> 28);
        int i37 = i25 & M28;
        int i38 = i9 + i33 + (i27 >>> 28);
        int i39 = i27 & M28;
        int i40 = i13 + (i29 >>> 28);
        int i41 = i29 & M28;
        int i42 = i18 + (i35 >>> 28);
        int i43 = i35 & M28;
        int i44 = i20 + (i36 >>> 28);
        int i45 = i36 & M28;
        int i46 = i22 + (i38 >>> 28);
        int i47 = i38 & M28;
        int i48 = i24 + (i40 >>> 28);
        int i49 = i40 & M28;
        iArr[0] = i43;
        iArr[1] = i42;
        iArr[2] = i26;
        iArr[3] = i37;
        iArr[4] = i45;
        iArr[5] = i44;
        iArr[6] = i28;
        iArr[7] = i39;
        iArr[8] = i47;
        iArr[9] = i46;
        iArr[10] = i30;
        iArr[11] = i41;
        iArr[12] = i49;
        iArr[13] = i48;
        iArr[14] = i32;
        iArr[15] = i34;
    }

    public static void cmov(int i, int[] iArr, int i2, int[] iArr2, int i3) {
        for (int i4 = 0; i4 < 16; i4++) {
            int i5 = i3 + i4;
            int i6 = iArr2[i5];
            iArr2[i5] = i6 ^ ((iArr[i2 + i4] ^ i6) & i);
        }
    }

    public static void cnegate(int i, int[] iArr) {
        int[] create = create();
        sub(create, iArr, create);
        cmov(-i, create, 0, iArr, 0);
    }

    public static void copy(int[] iArr, int i, int[] iArr2, int i2) {
        for (int i3 = 0; i3 < 16; i3++) {
            iArr2[i2 + i3] = iArr[i + i3];
        }
    }

    public static int[] create() {
        return new int[16];
    }

    public static int[] createTable(int i) {
        return new int[i * 16];
    }

    public static void cswap(int i, int[] iArr, int[] iArr2) {
        int i2 = 0 - i;
        for (int i3 = 0; i3 < 16; i3++) {
            int i4 = iArr[i3];
            int i5 = iArr2[i3];
            int i6 = (i4 ^ i5) & i2;
            iArr[i3] = i4 ^ i6;
            iArr2[i3] = i5 ^ i6;
        }
    }

    public static void decode(byte[] bArr, int i, int[] iArr) {
        decode56(bArr, i, iArr, 0);
        decode56(bArr, i + 7, iArr, 2);
        decode56(bArr, i + 14, iArr, 4);
        decode56(bArr, i + 21, iArr, 6);
        decode56(bArr, i + 28, iArr, 8);
        decode56(bArr, i + 35, iArr, 10);
        decode56(bArr, i + 42, iArr, 12);
        decode56(bArr, i + 49, iArr, 14);
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

    private static void decode56(byte[] bArr, int i, int[] iArr, int i2) {
        int decode32 = decode32(bArr, i);
        int decode24 = decode24(bArr, i + 4);
        iArr[i2] = M28 & decode32;
        iArr[i2 + 1] = (decode24 << 4) | (decode32 >>> 28);
    }

    public static void encode(int[] iArr, byte[] bArr, int i) {
        encode56(iArr, 0, bArr, i);
        encode56(iArr, 2, bArr, i + 7);
        encode56(iArr, 4, bArr, i + 14);
        encode56(iArr, 6, bArr, i + 21);
        encode56(iArr, 8, bArr, i + 28);
        encode56(iArr, 10, bArr, i + 35);
        encode56(iArr, 12, bArr, i + 42);
        encode56(iArr, 14, bArr, i + 49);
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

    private static void encode56(int[] iArr, int i, byte[] bArr, int i2) {
        int i3 = iArr[i];
        int i4 = iArr[i + 1];
        encode32((i4 << 28) | i3, bArr, i2);
        encode24(i4 >>> 4, bArr, i2 + 4);
    }

    public static void inv(int[] iArr, int[] iArr2) {
        int[] create = create();
        powPm3d4(iArr, create);
        sqr(create, 2, create);
        mul(create, iArr, iArr2);
    }

    public static int isZero(int[] iArr) {
        int i = 0;
        for (int i2 = 0; i2 < 16; i2++) {
            i |= iArr[i2];
        }
        return (((i >>> 1) | (i & 1)) - 1) >> 31;
    }

    public static boolean isZeroVar(int[] iArr) {
        return isZero(iArr) != 0;
    }

    public static void mul(int[] iArr, int i, int[] iArr2) {
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = iArr[2];
        int i5 = iArr[3];
        int i6 = iArr[4];
        int i7 = iArr[5];
        int i8 = iArr[6];
        int i9 = iArr[7];
        int i10 = iArr[8];
        int i11 = iArr[9];
        int i12 = iArr[10];
        int i13 = iArr[11];
        int i14 = iArr[12];
        int i15 = iArr[13];
        int i16 = iArr[14];
        int i17 = iArr[15];
        long j = i3;
        long j2 = i;
        long j3 = j * j2;
        int i18 = ((int) j3) & M28;
        long j4 = i7 * j2;
        int i19 = ((int) j4) & M28;
        long j5 = i11 * j2;
        int i20 = ((int) j5) & M28;
        long j6 = i15 * j2;
        int i21 = ((int) j6) & M28;
        long j7 = (j3 >>> 28) + (i4 * j2);
        iArr2[2] = ((int) j7) & M28;
        long j8 = (j4 >>> 28) + (i8 * j2);
        iArr2[6] = ((int) j8) & M28;
        long j9 = (j5 >>> 28) + (i12 * j2);
        iArr2[10] = ((int) j9) & M28;
        long j10 = (j6 >>> 28) + (i16 * j2);
        iArr2[14] = ((int) j10) & M28;
        long j11 = (j7 >>> 28) + (i5 * j2);
        iArr2[3] = ((int) j11) & M28;
        long j12 = (j8 >>> 28) + (i9 * j2);
        iArr2[7] = ((int) j12) & M28;
        long j13 = (j9 >>> 28) + (i13 * j2);
        iArr2[11] = ((int) j13) & M28;
        long j14 = (j10 >>> 28) + (i17 * j2);
        iArr2[15] = ((int) j14) & M28;
        long j15 = j14 >>> 28;
        long j16 = (j11 >>> 28) + (i6 * j2);
        iArr2[4] = ((int) j16) & M28;
        long j17 = (j12 >>> 28) + j15 + (i10 * j2);
        iArr2[8] = ((int) j17) & M28;
        long j18 = (j13 >>> 28) + (i14 * j2);
        iArr2[12] = ((int) j18) & M28;
        long j19 = j15 + (i2 * j2);
        iArr2[0] = ((int) j19) & M28;
        iArr2[1] = i18 + ((int) (j19 >>> 28));
        iArr2[5] = i19 + ((int) (j16 >>> 28));
        iArr2[9] = i20 + ((int) (j17 >>> 28));
        iArr2[13] = i21 + ((int) (j18 >>> 28));
    }

    public static void mul(int[] iArr, int[] iArr2, int[] iArr3) {
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = iArr[2];
        int i4 = iArr[3];
        int i5 = iArr[4];
        int i6 = iArr[5];
        int i7 = iArr[6];
        int i8 = iArr[7];
        int i9 = iArr[8];
        int i10 = iArr[9];
        int i11 = iArr[10];
        int i12 = iArr[11];
        int i13 = iArr[12];
        int i14 = iArr[13];
        int i15 = iArr[14];
        int i16 = iArr[15];
        int i17 = iArr2[0];
        int i18 = iArr2[1];
        int i19 = iArr2[2];
        int i20 = iArr2[3];
        int i21 = iArr2[4];
        int i22 = iArr2[5];
        int i23 = iArr2[6];
        int i24 = iArr2[7];
        int i25 = iArr2[8];
        int i26 = iArr2[9];
        int i27 = iArr2[10];
        int i28 = iArr2[11];
        int i29 = iArr2[12];
        int i30 = iArr2[13];
        int i31 = iArr2[14];
        int i32 = iArr2[15];
        int i33 = i + i9;
        int i34 = i3 + i11;
        int i35 = i4 + i12;
        int i36 = i5 + i13;
        int i37 = i6 + i14;
        int i38 = i7 + i15;
        int i39 = i17 + i25;
        int i40 = i18 + i26;
        int i41 = i19 + i27;
        int i42 = i20 + i28;
        int i43 = i21 + i29;
        int i44 = i22 + i30;
        int i45 = i23 + i31;
        int i46 = i24 + i32;
        long j = i;
        long j2 = i17;
        long j3 = j * j2;
        long j4 = i8;
        long j5 = i18;
        long j6 = j4 * j5;
        long j7 = i7;
        long j8 = i19;
        long j9 = j6 + (j7 * j8);
        long j10 = i6;
        long j11 = i20;
        long j12 = j9 + (j10 * j11);
        long j13 = i5;
        long j14 = i21;
        long j15 = j12 + (j13 * j14);
        long j16 = i4;
        long j17 = i22;
        long j18 = j15 + (j16 * j17);
        long j19 = i3;
        long j20 = i23;
        long j21 = j18 + (j19 * j20);
        long j22 = i2;
        long j23 = i24;
        long j24 = j21 + (j22 * j23);
        long j25 = i9;
        long j26 = i25;
        long j27 = j25 * j26;
        long j28 = i16;
        long j29 = i26;
        long j30 = j28 * j29;
        long j31 = i15;
        long j32 = i27;
        long j33 = j30 + (j31 * j32);
        long j34 = i14;
        long j35 = i28;
        long j36 = j33 + (j34 * j35);
        long j37 = i13;
        long j38 = i29;
        long j39 = j36 + (j37 * j38);
        long j40 = i12;
        long j41 = i30;
        long j42 = j39 + (j40 * j41);
        long j43 = i11;
        long j44 = i31;
        long j45 = j42 + (j43 * j44);
        long j46 = i10;
        long j47 = i32;
        long j48 = j45 + (j46 * j47);
        long j49 = i33;
        long j50 = i39;
        long j51 = j49 * j50;
        long j52 = i8 + i16;
        long j53 = i40;
        long j54 = j52 * j53;
        long j55 = i38;
        long j56 = i41;
        long j57 = j54 + (j55 * j56);
        long j58 = i37;
        long j59 = i42;
        long j60 = j57 + (j58 * j59);
        long j61 = i36;
        long j62 = i43;
        long j63 = j60 + (j61 * j62);
        long j64 = i35;
        long j65 = i44;
        long j66 = j63 + (j64 * j65);
        long j67 = i34;
        long j68 = i45;
        long j69 = j66 + (j67 * j68);
        long j70 = i2 + i10;
        long j71 = i46;
        long j72 = j69 + (j70 * j71);
        long j73 = ((j3 + j27) + j72) - j24;
        int i47 = ((int) j73) & M28;
        long j74 = j73 >>> 28;
        long j75 = ((j48 + j51) - j3) + j72;
        int i48 = ((int) j75) & M28;
        long j76 = (j22 * j2) + (j * j5);
        long j77 = (j28 * j32) + (j31 * j35) + (j34 * j38) + (j37 * j41) + (j40 * j44) + (j43 * j47);
        long j78 = (j70 * j50) + (j49 * j53);
        long j79 = (j52 * j56) + (j55 * j59) + (j58 * j62) + (j61 * j65) + (j64 * j68) + (j67 * j71);
        long j80 = j74 + (((j76 + ((j46 * j26) + (j25 * j29))) + j79) - ((((((j4 * j8) + (j7 * j11)) + (j10 * j14)) + (j13 * j17)) + (j16 * j20)) + (j19 * j23)));
        int i49 = ((int) j80) & M28;
        long j81 = (j75 >>> 28) + ((j77 + j78) - j76) + j79;
        int i50 = ((int) j81) & M28;
        long j82 = (j19 * j2) + (j22 * j5) + (j * j8);
        long j83 = (j28 * j35) + (j31 * j38) + (j34 * j41) + (j37 * j44) + (j40 * j47);
        long j84 = (j67 * j50) + (j70 * j53) + (j49 * j56);
        long j85 = (j52 * j59) + (j55 * j62) + (j58 * j65) + (j61 * j68) + (j64 * j71);
        long j86 = (j80 >>> 28) + (((j82 + (((j43 * j26) + (j46 * j29)) + (j25 * j32))) + j85) - (((((j4 * j11) + (j7 * j14)) + (j10 * j17)) + (j13 * j20)) + (j16 * j23)));
        int i51 = ((int) j86) & M28;
        long j87 = (j81 >>> 28) + ((j83 + j84) - j82) + j85;
        int i52 = ((int) j87) & M28;
        long j88 = (j16 * j2) + (j19 * j5) + (j22 * j8) + (j * j11);
        long j89 = (j28 * j38) + (j31 * j41) + (j34 * j44) + (j37 * j47);
        long j90 = (j64 * j50) + (j67 * j53) + (j70 * j56) + (j49 * j59);
        long j91 = (j52 * j62) + (j55 * j65) + (j58 * j68) + (j61 * j71);
        long j92 = (j86 >>> 28) + (((j88 + ((((j40 * j26) + (j43 * j29)) + (j46 * j32)) + (j25 * j35))) + j91) - ((((j4 * j14) + (j7 * j17)) + (j10 * j20)) + (j13 * j23)));
        int i53 = ((int) j92) & M28;
        long j93 = (j87 >>> 28) + ((j89 + j90) - j88) + j91;
        int i54 = ((int) j93) & M28;
        long j94 = (j13 * j2) + (j16 * j5) + (j19 * j8) + (j22 * j11) + (j * j14);
        long j95 = (j28 * j41) + (j31 * j44) + (j34 * j47);
        long j96 = (j61 * j50) + (j64 * j53) + (j67 * j56) + (j70 * j59) + (j49 * j62);
        long j97 = (j52 * j65) + (j55 * j68) + (j58 * j71);
        long j98 = (j92 >>> 28) + (((j94 + (((((j37 * j26) + (j40 * j29)) + (j43 * j32)) + (j46 * j35)) + (j25 * j38))) + j97) - (((j4 * j17) + (j7 * j20)) + (j10 * j23)));
        int i55 = ((int) j98) & M28;
        long j99 = (j93 >>> 28) + ((j95 + j96) - j94) + j97;
        int i56 = ((int) j99) & M28;
        long j100 = (j10 * j2) + (j13 * j5) + (j16 * j8) + (j19 * j11) + (j22 * j14) + (j * j17);
        long j101 = (j28 * j44) + (j31 * j47);
        long j102 = (j58 * j50) + (j61 * j53) + (j64 * j56) + (j67 * j59) + (j70 * j62) + (j49 * j65);
        long j103 = (j52 * j68) + (j55 * j71);
        long j104 = (j98 >>> 28) + (((j100 + ((((((j34 * j26) + (j37 * j29)) + (j40 * j32)) + (j43 * j35)) + (j46 * j38)) + (j25 * j41))) + j103) - ((j4 * j20) + (j7 * j23)));
        int i57 = ((int) j104) & M28;
        long j105 = (j99 >>> 28) + ((j101 + j102) - j100) + j103;
        int i58 = ((int) j105) & M28;
        long j106 = (j7 * j2) + (j10 * j5) + (j13 * j8) + (j16 * j11) + (j19 * j14) + (j22 * j17) + (j * j20);
        long j107 = j28 * j47;
        long j108 = (j55 * j50) + (j58 * j53) + (j61 * j56) + (j64 * j59) + (j67 * j62) + (j70 * j65) + (j49 * j68);
        long j109 = j52 * j71;
        long j110 = (j104 >>> 28) + (((j106 + (((((((j31 * j26) + (j34 * j29)) + (j37 * j32)) + (j40 * j35)) + (j43 * j38)) + (j46 * j41)) + (j25 * j44))) + j109) - (j4 * j23));
        int i59 = ((int) j110) & M28;
        long j111 = (j105 >>> 28) + ((j107 + j108) - j106) + j109;
        int i60 = ((int) j111) & M28;
        long j112 = (j2 * j4) + (j7 * j5) + (j8 * j10) + (j13 * j11) + (j16 * j14) + (j19 * j17) + (j22 * j20) + (j * j23);
        long j113 = (j28 * j26) + (j29 * j31) + (j34 * j32) + (j37 * j35) + (j40 * j38) + (j43 * j41) + (j46 * j44) + (j25 * j47);
        long j114 = (j52 * j50) + (j55 * j53) + (j58 * j56) + (j61 * j59) + (j64 * j62) + (j67 * j65) + (j70 * j68) + (j49 * j71);
        long j115 = (j110 >>> 28) + j112 + j113;
        int i61 = ((int) j115) & M28;
        long j116 = (j111 >>> 28) + (j114 - j112);
        int i62 = ((int) j116) & M28;
        long j117 = j116 >>> 28;
        long j118 = (j115 >>> 28) + j117 + i48;
        int i63 = ((int) j118) & M28;
        long j119 = j117 + i47;
        iArr3[0] = ((int) j119) & M28;
        iArr3[1] = i49 + ((int) (j119 >>> 28));
        iArr3[2] = i51;
        iArr3[3] = i53;
        iArr3[4] = i55;
        iArr3[5] = i57;
        iArr3[6] = i59;
        iArr3[7] = i61;
        iArr3[8] = i63;
        iArr3[9] = i50 + ((int) (j118 >>> 28));
        iArr3[10] = i52;
        iArr3[11] = i54;
        iArr3[12] = i56;
        iArr3[13] = i58;
        iArr3[14] = i60;
        iArr3[15] = i62;
    }

    public static void negate(int[] iArr, int[] iArr2) {
        sub(create(), iArr, iArr2);
    }

    public static void normalize(int[] iArr) {
        reduce(iArr, 1);
        reduce(iArr, -1);
    }

    public static void one(int[] iArr) {
        iArr[0] = 1;
        for (int i = 1; i < 16; i++) {
            iArr[i] = 0;
        }
    }

    private static void powPm3d4(int[] iArr, int[] iArr2) {
        int[] create = create();
        sqr(iArr, create);
        mul(iArr, create, create);
        int[] create2 = create();
        sqr(create, create2);
        mul(iArr, create2, create2);
        int[] create3 = create();
        sqr(create2, 3, create3);
        mul(create2, create3, create3);
        int[] create4 = create();
        sqr(create3, 3, create4);
        mul(create2, create4, create4);
        int[] create5 = create();
        sqr(create4, 9, create5);
        mul(create4, create5, create5);
        int[] create6 = create();
        sqr(create5, create6);
        mul(iArr, create6, create6);
        int[] create7 = create();
        sqr(create6, 18, create7);
        mul(create5, create7, create7);
        int[] create8 = create();
        sqr(create7, 37, create8);
        mul(create7, create8, create8);
        int[] create9 = create();
        sqr(create8, 37, create9);
        mul(create7, create9, create9);
        int[] create10 = create();
        sqr(create9, 111, create10);
        mul(create9, create10, create10);
        int[] create11 = create();
        sqr(create10, create11);
        mul(iArr, create11, create11);
        int[] create12 = create();
        sqr(create11, 223, create12);
        mul(create12, create10, iArr2);
    }

    private static void reduce(int[] iArr, int i) {
        int i2 = iArr[15];
        int i3 = i2 & M28;
        int i4 = (i2 >> 28) + i;
        iArr[8] = iArr[8] + i4;
        for (int i5 = 0; i5 < 15; i5++) {
            int i6 = i4 + iArr[i5];
            iArr[i5] = i6 & M28;
            i4 = i6 >> 28;
        }
        iArr[15] = i3 + i4;
    }

    public static void sqr(int[] iArr, int i, int[] iArr2) {
        sqr(iArr, iArr2);
        while (true) {
            i--;
            if (i <= 0) {
                return;
            } else {
                sqr(iArr2, iArr2);
            }
        }
    }

    public static void sqr(int[] iArr, int[] iArr2) {
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = iArr[2];
        int i4 = iArr[3];
        int i5 = iArr[4];
        int i6 = iArr[5];
        int i7 = iArr[6];
        int i8 = iArr[7];
        int i9 = iArr[8];
        int i10 = iArr[9];
        int i11 = iArr[10];
        int i12 = iArr[11];
        int i13 = iArr[12];
        int i14 = iArr[13];
        int i15 = iArr[14];
        int i16 = iArr[15];
        int i17 = i * 2;
        int i18 = i2 * 2;
        int i19 = i3 * 2;
        int i20 = i4 * 2;
        int i21 = i5 * 2;
        int i22 = i6 * 2;
        int i23 = i7 * 2;
        int i24 = i9 * 2;
        int i25 = i10 * 2;
        int i26 = i11 * 2;
        int i27 = i12 * 2;
        int i28 = i13 * 2;
        int i29 = i14 * 2;
        int i30 = i15 * 2;
        int i31 = i + i9;
        int i32 = i2 + i10;
        int i33 = i3 + i11;
        int i34 = i4 + i12;
        int i35 = i5 + i13;
        int i36 = i6 + i14;
        int i37 = i7 + i15;
        int i38 = i8 + i16;
        int i39 = i31 * 2;
        int i40 = i32 * 2;
        int i41 = i33 * 2;
        int i42 = i34 * 2;
        int i43 = i36 * 2;
        long j = i;
        long j2 = j * j;
        long j3 = i8;
        long j4 = i18;
        long j5 = j3 * j4;
        long j6 = i7;
        long j7 = i19;
        long j8 = j5 + (j6 * j7);
        long j9 = i6;
        long j10 = i20;
        long j11 = i5;
        long j12 = j8 + (j9 * j10) + (j11 * j11);
        long j13 = i9;
        long j14 = i16;
        long j15 = i25;
        long j16 = j14 * j15;
        long j17 = i15;
        long j18 = i26;
        long j19 = j16 + (j17 * j18);
        long j20 = i14;
        long j21 = i27;
        long j22 = j19 + (j20 * j21);
        long j23 = i13;
        long j24 = i31;
        long j25 = i38;
        long j26 = i40 & 4294967295L;
        long j27 = j25 * j26;
        long j28 = i37;
        long j29 = i41 & 4294967295L;
        long j30 = j27 + (j28 * j29);
        long j31 = i36;
        long j32 = i42 & 4294967295L;
        long j33 = j30 + (j31 * j32);
        long j34 = i35;
        long j35 = j33 + (j34 * j34);
        long j36 = ((j2 + (j13 * j13)) + j35) - j12;
        int i44 = ((int) j36) & M28;
        long j37 = (((j22 + (j23 * j23)) + (j24 * j24)) - j2) + j35;
        int i45 = ((int) j37) & M28;
        long j38 = j37 >>> 28;
        long j39 = i2;
        long j40 = i17;
        long j41 = j39 * j40;
        long j42 = i21;
        long j43 = (j3 * j7) + (j6 * j10) + (j9 * j42);
        long j44 = i10;
        long j45 = i24;
        long j46 = j44 * j45;
        long j47 = (j14 * j18) + (j17 * j21);
        long j48 = i28;
        long j49 = j47 + (j20 * j48);
        long j50 = i32;
        long j51 = i39 & 4294967295L;
        long j52 = (j25 * j29) + (j28 * j32);
        long j53 = (i35 * 2) & 4294967295L;
        long j54 = j52 + (j31 * j53);
        long j55 = (j36 >>> 28) + (((j41 + j46) + j54) - j43);
        int i46 = ((int) j55) & M28;
        long j56 = j38 + ((j49 + (j50 * j51)) - j41) + j54;
        int i47 = ((int) j56) & M28;
        long j57 = j56 >>> 28;
        long j58 = i3;
        long j59 = (j58 * j40) + (j39 * j39);
        long j60 = (j3 * j10) + (j6 * j42) + (j9 * j9);
        long j61 = i11;
        long j62 = (j61 * j45) + (j44 * j44);
        long j63 = (j14 * j21) + (j17 * j48) + (j20 * j20);
        long j64 = i33;
        long j65 = (j64 * j51) + (j50 * j50);
        long j66 = (j25 * j32) + (j28 * j53) + (j31 * j31);
        long j67 = (j55 >>> 28) + (((j59 + j62) + j66) - j60);
        int i48 = ((int) j67) & M28;
        long j68 = j57 + ((j63 + j65) - j59) + j66;
        int i49 = ((int) j68) & M28;
        long j69 = i4;
        long j70 = (j69 * j40) + (j58 * j4);
        long j71 = i22;
        long j72 = (j3 * j42) + (j6 * j71);
        long j73 = i12;
        long j74 = (j73 * j45) + (j61 * j15);
        long j75 = i29;
        long j76 = (j14 * j48) + (j17 * j75);
        long j77 = i34;
        long j78 = (j77 * j51) + (j64 * j26);
        long j79 = j53 * j25;
        long j80 = i43 & 4294967295L;
        long j81 = j79 + (j28 * j80);
        long j82 = (j67 >>> 28) + (((j70 + j74) + j81) - j72);
        int i50 = ((int) j82) & M28;
        long j83 = (j68 >>> 28) + ((j76 + j78) - j70) + j81;
        int i51 = ((int) j83) & M28;
        long j84 = (j11 * j40) + (j69 * j4) + (j58 * j58);
        long j85 = (j23 * j45) + (j73 * j15) + (j61 * j61);
        long j86 = (j34 * j51) + (j77 * j26) + (j64 * j64);
        long j87 = (j25 * j80) + (j28 * j28);
        long j88 = (j82 >>> 28) + (((j84 + j85) + j87) - ((j3 * j71) + (j6 * j6)));
        int i52 = ((int) j88) & M28;
        long j89 = (j83 >>> 28) + ((((j14 * j75) + (j17 * j17)) + j86) - j84) + j87;
        int i53 = ((int) j89) & M28;
        long j90 = (j9 * j40) + (j11 * j4) + (j69 * j7);
        long j91 = (j20 * j45) + (j23 * j15) + (j73 * j18);
        long j92 = (j31 * j51) + (j34 * j26) + (j77 * j29);
        long j93 = ((i37 * 2) & 4294967295L) * j25;
        long j94 = (j88 >>> 28) + (((j90 + j91) + j93) - (i23 * j3));
        int i54 = ((int) j94) & M28;
        long j95 = (j89 >>> 28) + (((i30 * j14) + j92) - j90) + j93;
        int i55 = ((int) j95) & M28;
        long j96 = (j6 * j40) + (j9 * j4) + (j11 * j7) + (j69 * j69);
        long j97 = j25 * j25;
        long j98 = (j94 >>> 28) + (((j96 + ((((j17 * j45) + (j20 * j15)) + (j23 * j18)) + (j73 * j73))) + j97) - (j3 * j3));
        int i56 = ((int) j98) & M28;
        long j99 = (j95 >>> 28) + (((j14 * j14) + ((((j28 * j51) + (j31 * j26)) + (j34 * j29)) + (j77 * j77))) - j96) + j97;
        int i57 = ((int) j99) & M28;
        long j100 = (j3 * j40) + (j6 * j4) + (j9 * j7) + (j11 * j10);
        long j101 = (j98 >>> 28) + (j45 * j14) + (j17 * j15) + (j20 * j18) + (j23 * j21) + j100;
        int i58 = ((int) j101) & M28;
        long j102 = (j99 >>> 28) + (((((j51 * j25) + (j28 * j26)) + (j31 * j29)) + (j34 * j32)) - j100);
        int i59 = ((int) j102) & M28;
        long j103 = j102 >>> 28;
        long j104 = (j101 >>> 28) + j103 + i45;
        int i60 = ((int) j104) & M28;
        long j105 = j103 + i44;
        iArr2[0] = ((int) j105) & M28;
        iArr2[1] = i46 + ((int) (j105 >>> 28));
        iArr2[2] = i48;
        iArr2[3] = i50;
        iArr2[4] = i52;
        iArr2[5] = i54;
        iArr2[6] = i56;
        iArr2[7] = i58;
        iArr2[8] = i60;
        iArr2[9] = i47 + ((int) (j104 >>> 28));
        iArr2[10] = i49;
        iArr2[11] = i51;
        iArr2[12] = i53;
        iArr2[13] = i55;
        iArr2[14] = i57;
        iArr2[15] = i59;
    }

    public static boolean sqrtRatioVar(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] create = create();
        int[] create2 = create();
        sqr(iArr, create);
        mul(create, iArr2, create);
        sqr(create, create2);
        mul(create, iArr, create);
        mul(create2, iArr, create2);
        mul(create2, iArr2, create2);
        int[] create3 = create();
        powPm3d4(create2, create3);
        mul(create3, create, create3);
        int[] create4 = create();
        sqr(create3, create4);
        mul(create4, iArr2, create4);
        sub(iArr, create4, create4);
        normalize(create4);
        if (!isZeroVar(create4)) {
            return false;
        }
        copy(create3, 0, iArr3, 0);
        return true;
    }

    public static void sub(int[] iArr, int[] iArr2, int[] iArr3) {
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = iArr[2];
        int i4 = iArr[3];
        int i5 = iArr[4];
        int i6 = iArr[5];
        int i7 = iArr[6];
        int i8 = iArr[7];
        int i9 = iArr[8];
        int i10 = iArr[9];
        int i11 = iArr[10];
        int i12 = iArr[11];
        int i13 = iArr[12];
        int i14 = iArr[13];
        int i15 = iArr[14];
        int i16 = iArr[15];
        int i17 = iArr2[0];
        int i18 = iArr2[1];
        int i19 = iArr2[2];
        int i20 = iArr2[3];
        int i21 = iArr2[4];
        int i22 = iArr2[5];
        int i23 = iArr2[6];
        int i24 = iArr2[7];
        int i25 = iArr2[8];
        int i26 = iArr2[9];
        int i27 = iArr2[10];
        int i28 = iArr2[11];
        int i29 = iArr2[12];
        int i30 = iArr2[13];
        int i31 = iArr2[14];
        int i32 = (i2 + 536870910) - i18;
        int i33 = (i6 + 536870910) - i22;
        int i34 = (i10 + 536870910) - i26;
        int i35 = (i14 + 536870910) - i30;
        int i36 = (i16 + 536870910) - iArr2[15];
        int i37 = ((i3 + 536870910) - i19) + (i32 >>> 28);
        int i38 = i32 & M28;
        int i39 = ((i7 + 536870910) - i23) + (i33 >>> 28);
        int i40 = i33 & M28;
        int i41 = ((i11 + 536870910) - i27) + (i34 >>> 28);
        int i42 = i34 & M28;
        int i43 = ((i15 + 536870910) - i31) + (i35 >>> 28);
        int i44 = i35 & M28;
        int i45 = ((i4 + 536870910) - i20) + (i37 >>> 28);
        int i46 = i37 & M28;
        int i47 = ((i8 + 536870910) - i24) + (i39 >>> 28);
        int i48 = i39 & M28;
        int i49 = ((i12 + 536870910) - i28) + (i41 >>> 28);
        int i50 = i41 & M28;
        int i51 = i36 + (i43 >>> 28);
        int i52 = i43 & M28;
        int i53 = i51 >>> 28;
        int i54 = i51 & M28;
        int i55 = ((i + 536870910) - i17) + i53;
        int i56 = ((i5 + 536870910) - i21) + (i45 >>> 28);
        int i57 = i45 & M28;
        int i58 = ((i9 + 536870908) - i25) + i53 + (i47 >>> 28);
        int i59 = i47 & M28;
        int i60 = ((i13 + 536870910) - i29) + (i49 >>> 28);
        int i61 = i49 & M28;
        int i62 = i38 + (i55 >>> 28);
        int i63 = i55 & M28;
        int i64 = i40 + (i56 >>> 28);
        int i65 = i56 & M28;
        int i66 = i42 + (i58 >>> 28);
        int i67 = i58 & M28;
        int i68 = i44 + (i60 >>> 28);
        int i69 = i60 & M28;
        iArr3[0] = i63;
        iArr3[1] = i62;
        iArr3[2] = i46;
        iArr3[3] = i57;
        iArr3[4] = i65;
        iArr3[5] = i64;
        iArr3[6] = i48;
        iArr3[7] = i59;
        iArr3[8] = i67;
        iArr3[9] = i66;
        iArr3[10] = i50;
        iArr3[11] = i61;
        iArr3[12] = i69;
        iArr3[13] = i68;
        iArr3[14] = i52;
        iArr3[15] = i54;
    }

    public static void subOne(int[] iArr) {
        int[] create = create();
        create[0] = 1;
        sub(iArr, create, iArr);
    }

    public static void zero(int[] iArr) {
        for (int i = 0; i < 16; i++) {
            iArr[i] = 0;
        }
    }
}
