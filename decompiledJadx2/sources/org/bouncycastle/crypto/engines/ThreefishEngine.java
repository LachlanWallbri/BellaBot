package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.TweakableBlockCipherParameters;

/* loaded from: classes9.dex */
public class ThreefishEngine implements BlockCipher {
    public static final int BLOCKSIZE_1024 = 1024;
    public static final int BLOCKSIZE_256 = 256;
    public static final int BLOCKSIZE_512 = 512;
    private static final long C_240 = 2004413935125273122L;
    private static final int MAX_ROUNDS = 80;
    private static int[] MOD17 = null;
    private static int[] MOD3 = null;
    private static int[] MOD5 = null;
    private static int[] MOD9 = new int[80];
    private static final int ROUNDS_1024 = 80;
    private static final int ROUNDS_256 = 72;
    private static final int ROUNDS_512 = 72;
    private static final int TWEAK_SIZE_BYTES = 16;
    private static final int TWEAK_SIZE_WORDS = 2;
    private int blocksizeBytes;
    private int blocksizeWords;
    private ThreefishCipher cipher;
    private long[] currentBlock;
    private boolean forEncryption;

    /* renamed from: kw */
    private long[] f9429kw;

    /* renamed from: t */
    private long[] f9430t = new long[5];

    /* loaded from: classes9.dex */
    private static final class Threefish1024Cipher extends ThreefishCipher {
        private static final int ROTATION_0_0 = 24;
        private static final int ROTATION_0_1 = 13;
        private static final int ROTATION_0_2 = 8;
        private static final int ROTATION_0_3 = 47;
        private static final int ROTATION_0_4 = 8;
        private static final int ROTATION_0_5 = 17;
        private static final int ROTATION_0_6 = 22;
        private static final int ROTATION_0_7 = 37;
        private static final int ROTATION_1_0 = 38;
        private static final int ROTATION_1_1 = 19;
        private static final int ROTATION_1_2 = 10;
        private static final int ROTATION_1_3 = 55;
        private static final int ROTATION_1_4 = 49;
        private static final int ROTATION_1_5 = 18;
        private static final int ROTATION_1_6 = 23;
        private static final int ROTATION_1_7 = 52;
        private static final int ROTATION_2_0 = 33;
        private static final int ROTATION_2_1 = 4;
        private static final int ROTATION_2_2 = 51;
        private static final int ROTATION_2_3 = 13;
        private static final int ROTATION_2_4 = 34;
        private static final int ROTATION_2_5 = 41;
        private static final int ROTATION_2_6 = 59;
        private static final int ROTATION_2_7 = 17;
        private static final int ROTATION_3_0 = 5;
        private static final int ROTATION_3_1 = 20;
        private static final int ROTATION_3_2 = 48;
        private static final int ROTATION_3_3 = 41;
        private static final int ROTATION_3_4 = 47;
        private static final int ROTATION_3_5 = 28;
        private static final int ROTATION_3_6 = 16;
        private static final int ROTATION_3_7 = 25;
        private static final int ROTATION_4_0 = 41;
        private static final int ROTATION_4_1 = 9;
        private static final int ROTATION_4_2 = 37;
        private static final int ROTATION_4_3 = 31;
        private static final int ROTATION_4_4 = 12;
        private static final int ROTATION_4_5 = 47;
        private static final int ROTATION_4_6 = 44;
        private static final int ROTATION_4_7 = 30;
        private static final int ROTATION_5_0 = 16;
        private static final int ROTATION_5_1 = 34;
        private static final int ROTATION_5_2 = 56;
        private static final int ROTATION_5_3 = 51;
        private static final int ROTATION_5_4 = 4;
        private static final int ROTATION_5_5 = 53;
        private static final int ROTATION_5_6 = 42;
        private static final int ROTATION_5_7 = 41;
        private static final int ROTATION_6_0 = 31;
        private static final int ROTATION_6_1 = 44;
        private static final int ROTATION_6_2 = 47;
        private static final int ROTATION_6_3 = 46;
        private static final int ROTATION_6_4 = 19;
        private static final int ROTATION_6_5 = 42;
        private static final int ROTATION_6_6 = 44;
        private static final int ROTATION_6_7 = 25;
        private static final int ROTATION_7_0 = 9;
        private static final int ROTATION_7_1 = 48;
        private static final int ROTATION_7_2 = 35;
        private static final int ROTATION_7_3 = 52;
        private static final int ROTATION_7_4 = 23;
        private static final int ROTATION_7_5 = 31;
        private static final int ROTATION_7_6 = 37;
        private static final int ROTATION_7_7 = 20;

        public Threefish1024Cipher(long[] jArr, long[] jArr2) {
            super(jArr, jArr2);
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        void decryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.f9431kw;
            long[] jArr4 = this.f9432t;
            int[] iArr = ThreefishEngine.MOD17;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 33) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j = jArr[0];
            int i = 1;
            long j2 = jArr[1];
            long j3 = jArr[2];
            long j4 = jArr[3];
            long j5 = jArr[4];
            long j6 = jArr[5];
            long j7 = jArr[6];
            long j8 = jArr[7];
            long j9 = jArr[8];
            long j10 = jArr[9];
            long j11 = jArr[10];
            long j12 = jArr[11];
            long j13 = jArr[12];
            long j14 = jArr[13];
            long j15 = jArr[14];
            int i2 = 19;
            long j16 = jArr[15];
            long j17 = j15;
            long j18 = j14;
            long j19 = j13;
            long j20 = j12;
            long j21 = j11;
            long j22 = j10;
            long j23 = j9;
            long j24 = j8;
            long j25 = j7;
            long j26 = j6;
            long j27 = j5;
            long j28 = j4;
            long j29 = j3;
            long j30 = j2;
            long j31 = j;
            while (i2 >= i) {
                int i3 = iArr[i2];
                int i4 = iArr2[i2];
                int i5 = i3 + 1;
                long j32 = j31 - jArr3[i5];
                int i6 = i3 + 2;
                long j33 = j30 - jArr3[i6];
                int i7 = i3 + 3;
                long j34 = j29 - jArr3[i7];
                int i8 = i3 + 4;
                long j35 = j28 - jArr3[i8];
                int i9 = i3 + 5;
                long j36 = j27 - jArr3[i9];
                int i10 = i3 + 6;
                int[] iArr3 = iArr;
                int[] iArr4 = iArr2;
                long j37 = j26 - jArr3[i10];
                int i11 = i3 + 7;
                long j38 = j25 - jArr3[i11];
                int i12 = i3 + 8;
                long j39 = j24 - jArr3[i12];
                int i13 = i3 + 9;
                long j40 = j23 - jArr3[i13];
                int i14 = i3 + 10;
                long j41 = j22 - jArr3[i14];
                int i15 = i3 + 11;
                long j42 = j21 - jArr3[i15];
                int i16 = i3 + 12;
                long j43 = j20 - jArr3[i16];
                int i17 = i3 + 13;
                long j44 = j19 - jArr3[i17];
                int i18 = i3 + 14;
                int i19 = i4 + 1;
                long j45 = j18 - (jArr3[i18] + jArr4[i19]);
                int i20 = i3 + 15;
                long j46 = j17 - (jArr3[i20] + jArr4[i4 + 2]);
                long[] jArr5 = jArr3;
                long j47 = i2;
                int i21 = i2;
                long[] jArr6 = jArr4;
                long xorRotr = ThreefishEngine.xorRotr(j16 - ((jArr3[i3 + 16] + j47) + 1), 9, j32);
                long j48 = j32 - xorRotr;
                long xorRotr2 = ThreefishEngine.xorRotr(j43, 48, j34);
                long j49 = j34 - xorRotr2;
                long xorRotr3 = ThreefishEngine.xorRotr(j45, 35, j38);
                long j50 = j38 - xorRotr3;
                long xorRotr4 = ThreefishEngine.xorRotr(j41, 52, j36);
                long j51 = j36 - xorRotr4;
                long xorRotr5 = ThreefishEngine.xorRotr(j33, 23, j46);
                long j52 = j46 - xorRotr5;
                long xorRotr6 = ThreefishEngine.xorRotr(j37, 31, j40);
                long j53 = j40 - xorRotr6;
                long xorRotr7 = ThreefishEngine.xorRotr(j35, 37, j42);
                long j54 = j42 - xorRotr7;
                long xorRotr8 = ThreefishEngine.xorRotr(j39, 20, j44);
                long j55 = j44 - xorRotr8;
                long xorRotr9 = ThreefishEngine.xorRotr(xorRotr8, 31, j48);
                long j56 = j48 - xorRotr9;
                long xorRotr10 = ThreefishEngine.xorRotr(xorRotr6, 44, j49);
                long j57 = j49 - xorRotr10;
                long xorRotr11 = ThreefishEngine.xorRotr(xorRotr7, 47, j51);
                long j58 = j51 - xorRotr11;
                long xorRotr12 = ThreefishEngine.xorRotr(xorRotr5, 46, j50);
                long j59 = j50 - xorRotr12;
                long xorRotr13 = ThreefishEngine.xorRotr(xorRotr, 19, j55);
                long j60 = j55 - xorRotr13;
                long xorRotr14 = ThreefishEngine.xorRotr(xorRotr3, 42, j52);
                long j61 = j52 - xorRotr14;
                long xorRotr15 = ThreefishEngine.xorRotr(xorRotr2, 44, j53);
                long j62 = j53 - xorRotr15;
                long xorRotr16 = ThreefishEngine.xorRotr(xorRotr4, 25, j54);
                long j63 = j54 - xorRotr16;
                long xorRotr17 = ThreefishEngine.xorRotr(xorRotr16, 16, j56);
                long j64 = j56 - xorRotr17;
                long xorRotr18 = ThreefishEngine.xorRotr(xorRotr14, 34, j57);
                long j65 = j57 - xorRotr18;
                long xorRotr19 = ThreefishEngine.xorRotr(xorRotr15, 56, j59);
                long j66 = j59 - xorRotr19;
                long xorRotr20 = ThreefishEngine.xorRotr(xorRotr13, 51, j58);
                long j67 = j58 - xorRotr20;
                long xorRotr21 = ThreefishEngine.xorRotr(xorRotr9, 4, j63);
                long j68 = j63 - xorRotr21;
                long xorRotr22 = ThreefishEngine.xorRotr(xorRotr11, 53, j60);
                long j69 = j60 - xorRotr22;
                long xorRotr23 = ThreefishEngine.xorRotr(xorRotr10, 42, j61);
                long j70 = j61 - xorRotr23;
                long xorRotr24 = ThreefishEngine.xorRotr(xorRotr12, 41, j62);
                long j71 = j62 - xorRotr24;
                long xorRotr25 = ThreefishEngine.xorRotr(xorRotr24, 41, j64);
                long xorRotr26 = ThreefishEngine.xorRotr(xorRotr22, 9, j65);
                long xorRotr27 = ThreefishEngine.xorRotr(xorRotr23, 37, j67);
                long j72 = j67 - xorRotr27;
                long xorRotr28 = ThreefishEngine.xorRotr(xorRotr21, 31, j66);
                long j73 = j66 - xorRotr28;
                long xorRotr29 = ThreefishEngine.xorRotr(xorRotr17, 12, j71);
                long j74 = j71 - xorRotr29;
                long xorRotr30 = ThreefishEngine.xorRotr(xorRotr19, 47, j68);
                long j75 = j68 - xorRotr30;
                long xorRotr31 = ThreefishEngine.xorRotr(xorRotr18, 44, j69);
                long j76 = j69 - xorRotr31;
                long xorRotr32 = ThreefishEngine.xorRotr(xorRotr20, 30, j70);
                long j77 = j70 - xorRotr32;
                long j78 = (j64 - xorRotr25) - jArr5[i3];
                long j79 = xorRotr25 - jArr5[i5];
                long j80 = (j65 - xorRotr26) - jArr5[i6];
                long j81 = xorRotr26 - jArr5[i7];
                long j82 = j72 - jArr5[i8];
                long j83 = xorRotr27 - jArr5[i9];
                long j84 = j73 - jArr5[i10];
                long j85 = xorRotr28 - jArr5[i11];
                long j86 = j74 - jArr5[i12];
                long j87 = xorRotr29 - jArr5[i13];
                long j88 = j75 - jArr5[i14];
                long j89 = xorRotr30 - jArr5[i15];
                long j90 = j76 - jArr5[i16];
                long j91 = xorRotr31 - (jArr5[i17] + jArr6[i4]);
                long j92 = j77 - (jArr5[i18] + jArr6[i19]);
                long xorRotr33 = ThreefishEngine.xorRotr(xorRotr32 - (jArr5[i20] + j47), 5, j78);
                long j93 = j78 - xorRotr33;
                long xorRotr34 = ThreefishEngine.xorRotr(j89, 20, j80);
                long j94 = j80 - xorRotr34;
                long xorRotr35 = ThreefishEngine.xorRotr(j91, 48, j84);
                long j95 = j84 - xorRotr35;
                long xorRotr36 = ThreefishEngine.xorRotr(j87, 41, j82);
                long j96 = j82 - xorRotr36;
                long xorRotr37 = ThreefishEngine.xorRotr(j79, 47, j92);
                long j97 = j92 - xorRotr37;
                long xorRotr38 = ThreefishEngine.xorRotr(j83, 28, j86);
                long j98 = j86 - xorRotr38;
                long xorRotr39 = ThreefishEngine.xorRotr(j81, 16, j88);
                long j99 = j88 - xorRotr39;
                long xorRotr40 = ThreefishEngine.xorRotr(j85, 25, j90);
                long j100 = j90 - xorRotr40;
                long xorRotr41 = ThreefishEngine.xorRotr(xorRotr40, 33, j93);
                long j101 = j93 - xorRotr41;
                long xorRotr42 = ThreefishEngine.xorRotr(xorRotr38, 4, j94);
                long j102 = j94 - xorRotr42;
                long xorRotr43 = ThreefishEngine.xorRotr(xorRotr39, 51, j96);
                long j103 = j96 - xorRotr43;
                long xorRotr44 = ThreefishEngine.xorRotr(xorRotr37, 13, j95);
                long j104 = j95 - xorRotr44;
                long xorRotr45 = ThreefishEngine.xorRotr(xorRotr33, 34, j100);
                long j105 = j100 - xorRotr45;
                long xorRotr46 = ThreefishEngine.xorRotr(xorRotr35, 41, j97);
                long j106 = j97 - xorRotr46;
                long xorRotr47 = ThreefishEngine.xorRotr(xorRotr34, 59, j98);
                long j107 = j98 - xorRotr47;
                long xorRotr48 = ThreefishEngine.xorRotr(xorRotr36, 17, j99);
                long j108 = j99 - xorRotr48;
                long xorRotr49 = ThreefishEngine.xorRotr(xorRotr48, 38, j101);
                long j109 = j101 - xorRotr49;
                long xorRotr50 = ThreefishEngine.xorRotr(xorRotr46, 19, j102);
                long j110 = j102 - xorRotr50;
                long xorRotr51 = ThreefishEngine.xorRotr(xorRotr47, 10, j104);
                long j111 = j104 - xorRotr51;
                long xorRotr52 = ThreefishEngine.xorRotr(xorRotr45, 55, j103);
                long j112 = j103 - xorRotr52;
                long xorRotr53 = ThreefishEngine.xorRotr(xorRotr41, 49, j108);
                long j113 = j108 - xorRotr53;
                long xorRotr54 = ThreefishEngine.xorRotr(xorRotr43, 18, j105);
                long j114 = j105 - xorRotr54;
                long xorRotr55 = ThreefishEngine.xorRotr(xorRotr42, 23, j106);
                long j115 = j106 - xorRotr55;
                long xorRotr56 = ThreefishEngine.xorRotr(xorRotr44, 52, j107);
                long j116 = j107 - xorRotr56;
                long xorRotr57 = ThreefishEngine.xorRotr(xorRotr56, 24, j109);
                long j117 = j109 - xorRotr57;
                long xorRotr58 = ThreefishEngine.xorRotr(xorRotr54, 13, j110);
                long xorRotr59 = ThreefishEngine.xorRotr(xorRotr55, 8, j112);
                long j118 = j112 - xorRotr59;
                long xorRotr60 = ThreefishEngine.xorRotr(xorRotr53, 47, j111);
                long j119 = j111 - xorRotr60;
                j22 = ThreefishEngine.xorRotr(xorRotr49, 8, j116);
                long j120 = j116 - j22;
                long xorRotr61 = ThreefishEngine.xorRotr(xorRotr51, 17, j113);
                long j121 = j113 - xorRotr61;
                j18 = ThreefishEngine.xorRotr(xorRotr50, 22, j114);
                long j122 = j114 - j18;
                j16 = ThreefishEngine.xorRotr(xorRotr52, 37, j115);
                j17 = j115 - j16;
                j21 = j121;
                j20 = xorRotr61;
                jArr4 = jArr6;
                iArr = iArr3;
                iArr2 = iArr4;
                jArr3 = jArr5;
                j27 = j118;
                j26 = xorRotr59;
                j29 = j110 - xorRotr58;
                j28 = xorRotr58;
                j30 = xorRotr57;
                j31 = j117;
                j25 = j119;
                i = 1;
                i2 = i21 - 2;
                j23 = j120;
                j19 = j122;
                j24 = xorRotr60;
            }
            long[] jArr7 = jArr3;
            long[] jArr8 = jArr4;
            long j123 = j31 - jArr7[0];
            long j124 = j30 - jArr7[1];
            long j125 = j29 - jArr7[2];
            long j126 = j28 - jArr7[3];
            long j127 = j27 - jArr7[4];
            long j128 = j26 - jArr7[5];
            long j129 = j25 - jArr7[6];
            long j130 = j24 - jArr7[7];
            long j131 = j23 - jArr7[8];
            long j132 = j22 - jArr7[9];
            long j133 = j21 - jArr7[10];
            long j134 = j20 - jArr7[11];
            long j135 = j19 - jArr7[12];
            long j136 = j18 - (jArr7[13] + jArr8[0]);
            long j137 = j17 - (jArr7[14] + jArr8[1]);
            long j138 = j16 - jArr7[15];
            jArr2[0] = j123;
            jArr2[1] = j124;
            jArr2[2] = j125;
            jArr2[3] = j126;
            jArr2[4] = j127;
            jArr2[5] = j128;
            jArr2[6] = j129;
            jArr2[7] = j130;
            jArr2[8] = j131;
            jArr2[9] = j132;
            jArr2[10] = j133;
            jArr2[11] = j134;
            jArr2[12] = j135;
            jArr2[13] = j136;
            jArr2[14] = j137;
            jArr2[15] = j138;
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        void encryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.f9431kw;
            long[] jArr4 = this.f9432t;
            int[] iArr = ThreefishEngine.MOD17;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 33) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = jArr[2];
            long j4 = jArr[3];
            long j5 = jArr[4];
            long j6 = jArr[5];
            long j7 = jArr[6];
            long j8 = jArr[7];
            long j9 = jArr[8];
            long j10 = jArr[9];
            long j11 = jArr[10];
            long j12 = jArr[11];
            long j13 = jArr[12];
            long j14 = jArr[13];
            long j15 = jArr[14];
            long j16 = jArr[15];
            long j17 = j + jArr3[0];
            long j18 = j2 + jArr3[1];
            long j19 = j3 + jArr3[2];
            long j20 = j4 + jArr3[3];
            long j21 = j5 + jArr3[4];
            long j22 = j6 + jArr3[5];
            long j23 = j7 + jArr3[6];
            long j24 = j8 + jArr3[7];
            long j25 = j9 + jArr3[8];
            long j26 = j10 + jArr3[9];
            long j27 = j11 + jArr3[10];
            long j28 = j12 + jArr3[11];
            long j29 = j13 + jArr3[12];
            long j30 = j14 + jArr3[13] + jArr4[0];
            long j31 = j15 + jArr3[14] + jArr4[1];
            long j32 = j20;
            long j33 = j24;
            long j34 = j26;
            long j35 = j28;
            long j36 = j30;
            long j37 = j16 + jArr3[15];
            long j38 = j19;
            long j39 = j18;
            long j40 = j17;
            int i = 1;
            long j41 = j22;
            long j42 = j21;
            while (i < 20) {
                int i2 = iArr[i];
                int i3 = iArr2[i];
                long j43 = j40 + j39;
                long rotlXor = ThreefishEngine.rotlXor(j39, 24, j43);
                int[] iArr3 = iArr;
                long j44 = j38 + j32;
                long rotlXor2 = ThreefishEngine.rotlXor(j32, 13, j44);
                long[] jArr5 = jArr3;
                long j45 = j42 + j41;
                long rotlXor3 = ThreefishEngine.rotlXor(j41, 8, j45);
                long j46 = j33;
                int i4 = i;
                long j47 = j23 + j46;
                long rotlXor4 = ThreefishEngine.rotlXor(j46, 47, j47);
                long j48 = j34;
                long j49 = j25 + j48;
                long rotlXor5 = ThreefishEngine.rotlXor(j48, 8, j49);
                long j50 = j35;
                long j51 = j27 + j50;
                long rotlXor6 = ThreefishEngine.rotlXor(j50, 17, j51);
                long j52 = j36;
                long j53 = j29 + j52;
                long rotlXor7 = ThreefishEngine.rotlXor(j52, 22, j53);
                long j54 = j37;
                long j55 = j31 + j54;
                long rotlXor8 = ThreefishEngine.rotlXor(j54, 37, j55);
                long j56 = j43 + rotlXor5;
                long rotlXor9 = ThreefishEngine.rotlXor(rotlXor5, 38, j56);
                long j57 = j44 + rotlXor7;
                long rotlXor10 = ThreefishEngine.rotlXor(rotlXor7, 19, j57);
                long j58 = j47 + rotlXor6;
                long rotlXor11 = ThreefishEngine.rotlXor(rotlXor6, 10, j58);
                long j59 = j45 + rotlXor8;
                long rotlXor12 = ThreefishEngine.rotlXor(rotlXor8, 55, j59);
                long j60 = j51 + rotlXor4;
                long rotlXor13 = ThreefishEngine.rotlXor(rotlXor4, 49, j60);
                long j61 = j53 + rotlXor2;
                long rotlXor14 = ThreefishEngine.rotlXor(rotlXor2, 18, j61);
                long j62 = j55 + rotlXor3;
                long rotlXor15 = ThreefishEngine.rotlXor(rotlXor3, 23, j62);
                long j63 = j49 + rotlXor;
                long rotlXor16 = ThreefishEngine.rotlXor(rotlXor, 52, j63);
                long j64 = j56 + rotlXor13;
                long rotlXor17 = ThreefishEngine.rotlXor(rotlXor13, 33, j64);
                long j65 = j57 + rotlXor15;
                long rotlXor18 = ThreefishEngine.rotlXor(rotlXor15, 4, j65);
                long j66 = j59 + rotlXor14;
                long rotlXor19 = ThreefishEngine.rotlXor(rotlXor14, 51, j66);
                long j67 = j58 + rotlXor16;
                long rotlXor20 = ThreefishEngine.rotlXor(rotlXor16, 13, j67);
                long j68 = j61 + rotlXor12;
                long rotlXor21 = ThreefishEngine.rotlXor(rotlXor12, 34, j68);
                long j69 = j62 + rotlXor10;
                long rotlXor22 = ThreefishEngine.rotlXor(rotlXor10, 41, j69);
                long j70 = j63 + rotlXor11;
                long rotlXor23 = ThreefishEngine.rotlXor(rotlXor11, 59, j70);
                long j71 = j60 + rotlXor9;
                long rotlXor24 = ThreefishEngine.rotlXor(rotlXor9, 17, j71);
                long j72 = j64 + rotlXor21;
                long rotlXor25 = ThreefishEngine.rotlXor(rotlXor21, 5, j72);
                long j73 = j65 + rotlXor23;
                long rotlXor26 = ThreefishEngine.rotlXor(rotlXor23, 20, j73);
                long j74 = j67 + rotlXor22;
                long rotlXor27 = ThreefishEngine.rotlXor(rotlXor22, 48, j74);
                long j75 = j66 + rotlXor24;
                long rotlXor28 = ThreefishEngine.rotlXor(rotlXor24, 41, j75);
                long j76 = j69 + rotlXor20;
                long rotlXor29 = ThreefishEngine.rotlXor(rotlXor20, 47, j76);
                long j77 = j70 + rotlXor18;
                long rotlXor30 = ThreefishEngine.rotlXor(rotlXor18, 28, j77);
                long j78 = j71 + rotlXor19;
                long rotlXor31 = ThreefishEngine.rotlXor(rotlXor19, 16, j78);
                long j79 = j68 + rotlXor17;
                long rotlXor32 = ThreefishEngine.rotlXor(rotlXor17, 25, j79);
                long j80 = j72 + jArr5[i2];
                int i5 = i2 + 1;
                long j81 = rotlXor29 + jArr5[i5];
                int i6 = i2 + 2;
                long j82 = j73 + jArr5[i6];
                int i7 = i2 + 3;
                long j83 = rotlXor31 + jArr5[i7];
                int i8 = i2 + 4;
                long j84 = j75 + jArr5[i8];
                int i9 = i2 + 5;
                long j85 = rotlXor30 + jArr5[i9];
                int i10 = i2 + 6;
                long j86 = j74 + jArr5[i10];
                int i11 = i2 + 7;
                long j87 = rotlXor32 + jArr5[i11];
                int i12 = i2 + 8;
                long j88 = j77 + jArr5[i12];
                int i13 = i2 + 9;
                long j89 = rotlXor28 + jArr5[i13];
                int i14 = i2 + 10;
                long j90 = j78 + jArr5[i14];
                int i15 = i2 + 11;
                long j91 = rotlXor26 + jArr5[i15];
                int i16 = i2 + 12;
                long j92 = j79 + jArr5[i16];
                int i17 = i2 + 13;
                long j93 = rotlXor27 + jArr5[i17] + jArr4[i3];
                int i18 = i2 + 14;
                int i19 = i3 + 1;
                long j94 = j76 + jArr5[i18] + jArr4[i19];
                int i20 = i2 + 15;
                long[] jArr6 = jArr4;
                long j95 = i4;
                long j96 = rotlXor25 + jArr5[i20] + j95;
                long j97 = j80 + j81;
                long rotlXor33 = ThreefishEngine.rotlXor(j81, 41, j97);
                long j98 = j82 + j83;
                long rotlXor34 = ThreefishEngine.rotlXor(j83, 9, j98);
                long j99 = j84 + j85;
                long rotlXor35 = ThreefishEngine.rotlXor(j85, 37, j99);
                long j100 = j86 + j87;
                long rotlXor36 = ThreefishEngine.rotlXor(j87, 31, j100);
                long j101 = j88 + j89;
                long rotlXor37 = ThreefishEngine.rotlXor(j89, 12, j101);
                long j102 = j90 + j91;
                long rotlXor38 = ThreefishEngine.rotlXor(j91, 47, j102);
                long j103 = j92 + j93;
                long rotlXor39 = ThreefishEngine.rotlXor(j93, 44, j103);
                long j104 = j94 + j96;
                long rotlXor40 = ThreefishEngine.rotlXor(j96, 30, j104);
                long j105 = j97 + rotlXor37;
                long rotlXor41 = ThreefishEngine.rotlXor(rotlXor37, 16, j105);
                long j106 = j98 + rotlXor39;
                long rotlXor42 = ThreefishEngine.rotlXor(rotlXor39, 34, j106);
                long j107 = j100 + rotlXor38;
                long rotlXor43 = ThreefishEngine.rotlXor(rotlXor38, 56, j107);
                long j108 = j99 + rotlXor40;
                long rotlXor44 = ThreefishEngine.rotlXor(rotlXor40, 51, j108);
                long j109 = j102 + rotlXor36;
                long rotlXor45 = ThreefishEngine.rotlXor(rotlXor36, 4, j109);
                long j110 = j103 + rotlXor34;
                long rotlXor46 = ThreefishEngine.rotlXor(rotlXor34, 53, j110);
                long j111 = j104 + rotlXor35;
                long rotlXor47 = ThreefishEngine.rotlXor(rotlXor35, 42, j111);
                long j112 = j101 + rotlXor33;
                long rotlXor48 = ThreefishEngine.rotlXor(rotlXor33, 41, j112);
                long j113 = j105 + rotlXor45;
                long rotlXor49 = ThreefishEngine.rotlXor(rotlXor45, 31, j113);
                long j114 = j106 + rotlXor47;
                long rotlXor50 = ThreefishEngine.rotlXor(rotlXor47, 44, j114);
                long j115 = j108 + rotlXor46;
                long rotlXor51 = ThreefishEngine.rotlXor(rotlXor46, 47, j115);
                long j116 = j107 + rotlXor48;
                long rotlXor52 = ThreefishEngine.rotlXor(rotlXor48, 46, j116);
                long j117 = j110 + rotlXor44;
                long rotlXor53 = ThreefishEngine.rotlXor(rotlXor44, 19, j117);
                long j118 = j111 + rotlXor42;
                long rotlXor54 = ThreefishEngine.rotlXor(rotlXor42, 42, j118);
                long j119 = j112 + rotlXor43;
                long rotlXor55 = ThreefishEngine.rotlXor(rotlXor43, 44, j119);
                long j120 = j109 + rotlXor41;
                long rotlXor56 = ThreefishEngine.rotlXor(rotlXor41, 25, j120);
                long j121 = j113 + rotlXor53;
                long rotlXor57 = ThreefishEngine.rotlXor(rotlXor53, 9, j121);
                long j122 = j114 + rotlXor55;
                long rotlXor58 = ThreefishEngine.rotlXor(rotlXor55, 48, j122);
                long j123 = j116 + rotlXor54;
                long rotlXor59 = ThreefishEngine.rotlXor(rotlXor54, 35, j123);
                long j124 = j115 + rotlXor56;
                long rotlXor60 = ThreefishEngine.rotlXor(rotlXor56, 52, j124);
                long j125 = j118 + rotlXor52;
                long rotlXor61 = ThreefishEngine.rotlXor(rotlXor52, 23, j125);
                long j126 = j119 + rotlXor50;
                long rotlXor62 = ThreefishEngine.rotlXor(rotlXor50, 31, j126);
                long j127 = j120 + rotlXor51;
                long rotlXor63 = ThreefishEngine.rotlXor(rotlXor51, 37, j127);
                long j128 = j117 + rotlXor49;
                long rotlXor64 = ThreefishEngine.rotlXor(rotlXor49, 20, j128);
                j40 = j121 + jArr5[i5];
                long j129 = rotlXor61 + jArr5[i6];
                long j130 = j122 + jArr5[i7];
                long j131 = rotlXor63 + jArr5[i8];
                long j132 = j124 + jArr5[i9];
                long j133 = rotlXor62 + jArr5[i10];
                long j134 = j123 + jArr5[i11];
                long j135 = rotlXor64 + jArr5[i12];
                long j136 = j126 + jArr5[i13];
                j34 = rotlXor60 + jArr5[i14];
                j27 = j127 + jArr5[i15];
                j35 = rotlXor58 + jArr5[i16];
                long j137 = j128 + jArr5[i17];
                long j138 = rotlXor59 + jArr5[i18] + jArr6[i19];
                j31 = j125 + jArr5[i20] + jArr6[i3 + 2];
                j37 = rotlXor57 + jArr5[i2 + 16] + j95 + 1;
                j42 = j132;
                j33 = j135;
                jArr3 = jArr5;
                i = i4 + 2;
                j41 = j133;
                j23 = j134;
                iArr2 = iArr2;
                j25 = j136;
                j29 = j137;
                j39 = j129;
                j32 = j131;
                iArr = iArr3;
                jArr4 = jArr6;
                j36 = j138;
                j38 = j130;
            }
            jArr2[0] = j40;
            jArr2[1] = j39;
            jArr2[2] = j38;
            jArr2[3] = j32;
            jArr2[4] = j42;
            jArr2[5] = j41;
            jArr2[6] = j23;
            jArr2[7] = j33;
            jArr2[8] = j25;
            jArr2[9] = j34;
            jArr2[10] = j27;
            jArr2[11] = j35;
            jArr2[12] = j29;
            jArr2[13] = j36;
            jArr2[14] = j31;
            jArr2[15] = j37;
        }
    }

    /* loaded from: classes9.dex */
    private static final class Threefish256Cipher extends ThreefishCipher {
        private static final int ROTATION_0_0 = 14;
        private static final int ROTATION_0_1 = 16;
        private static final int ROTATION_1_0 = 52;
        private static final int ROTATION_1_1 = 57;
        private static final int ROTATION_2_0 = 23;
        private static final int ROTATION_2_1 = 40;
        private static final int ROTATION_3_0 = 5;
        private static final int ROTATION_3_1 = 37;
        private static final int ROTATION_4_0 = 25;
        private static final int ROTATION_4_1 = 33;
        private static final int ROTATION_5_0 = 46;
        private static final int ROTATION_5_1 = 12;
        private static final int ROTATION_6_0 = 58;
        private static final int ROTATION_6_1 = 22;
        private static final int ROTATION_7_0 = 32;
        private static final int ROTATION_7_1 = 32;

        public Threefish256Cipher(long[] jArr, long[] jArr2) {
            super(jArr, jArr2);
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        void decryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.f9431kw;
            long[] jArr4 = this.f9432t;
            int[] iArr = ThreefishEngine.MOD5;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 9) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            boolean z = false;
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = jArr[2];
            int i = 17;
            long j4 = jArr[3];
            long j5 = j3;
            long j6 = j2;
            long j7 = j;
            for (int i2 = 1; i >= i2; i2 = 1) {
                int i3 = iArr[i];
                int i4 = iArr2[i];
                int i5 = i3 + 1;
                long j8 = j7 - jArr3[i5];
                int i6 = i3 + 2;
                int i7 = i4 + 1;
                long j9 = j6 - (jArr3[i6] + jArr4[i7]);
                int i8 = i3 + 3;
                long j10 = j5 - (jArr3[i8] + jArr4[i4 + 2]);
                long j11 = i;
                int[] iArr3 = iArr;
                long xorRotr = ThreefishEngine.xorRotr(j4 - ((jArr3[i3 + 4] + j11) + 1), 32, j8);
                long j12 = j8 - xorRotr;
                long xorRotr2 = ThreefishEngine.xorRotr(j9, 32, j10);
                long j13 = j10 - xorRotr2;
                long xorRotr3 = ThreefishEngine.xorRotr(xorRotr2, 58, j12);
                long j14 = j12 - xorRotr3;
                long xorRotr4 = ThreefishEngine.xorRotr(xorRotr, 22, j13);
                long j15 = j13 - xorRotr4;
                long xorRotr5 = ThreefishEngine.xorRotr(xorRotr4, 46, j14);
                long j16 = j14 - xorRotr5;
                long xorRotr6 = ThreefishEngine.xorRotr(xorRotr3, 12, j15);
                long j17 = j15 - xorRotr6;
                long xorRotr7 = ThreefishEngine.xorRotr(xorRotr6, 25, j16);
                long xorRotr8 = ThreefishEngine.xorRotr(xorRotr5, 33, j17);
                long j18 = (j16 - xorRotr7) - jArr3[i3];
                long j19 = xorRotr7 - (jArr3[i5] + jArr4[i4]);
                long j20 = (j17 - xorRotr8) - (jArr3[i6] + jArr4[i7]);
                long xorRotr9 = ThreefishEngine.xorRotr(xorRotr8 - (jArr3[i8] + j11), 5, j18);
                long j21 = j18 - xorRotr9;
                long xorRotr10 = ThreefishEngine.xorRotr(j19, 37, j20);
                long j22 = j20 - xorRotr10;
                long xorRotr11 = ThreefishEngine.xorRotr(xorRotr10, 23, j21);
                long j23 = j21 - xorRotr11;
                long xorRotr12 = ThreefishEngine.xorRotr(xorRotr9, 40, j22);
                long j24 = j22 - xorRotr12;
                long xorRotr13 = ThreefishEngine.xorRotr(xorRotr12, 52, j23);
                long j25 = j23 - xorRotr13;
                long xorRotr14 = ThreefishEngine.xorRotr(xorRotr11, 57, j24);
                long j26 = j24 - xorRotr14;
                long xorRotr15 = ThreefishEngine.xorRotr(xorRotr14, 14, j25);
                j4 = ThreefishEngine.xorRotr(xorRotr13, 16, j26);
                j5 = j26 - j4;
                i -= 2;
                j6 = xorRotr15;
                j7 = j25 - xorRotr15;
                iArr = iArr3;
                iArr2 = iArr2;
                z = false;
            }
            boolean z2 = z;
            long j27 = j7 - jArr3[z2 ? 1 : 0];
            long j28 = j6 - (jArr3[1] + jArr4[z2 ? 1 : 0]);
            long j29 = j5 - (jArr3[2] + jArr4[1]);
            long j30 = j4 - jArr3[3];
            jArr2[z2 ? 1 : 0] = j27;
            jArr2[1] = j28;
            jArr2[2] = j29;
            jArr2[3] = j30;
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        void encryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.f9431kw;
            long[] jArr4 = this.f9432t;
            int[] iArr = ThreefishEngine.MOD5;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 9) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = jArr[2];
            long j4 = jArr[3];
            long j5 = j + jArr3[0];
            long j6 = j2 + jArr3[1] + jArr4[0];
            long j7 = j3 + jArr3[2] + jArr4[1];
            long j8 = j4 + jArr3[3];
            long j9 = j7;
            long j10 = j6;
            long j11 = j5;
            int i = 1;
            while (i < 18) {
                int i2 = iArr[i];
                int i3 = iArr2[i];
                long j12 = j11 + j10;
                long rotlXor = ThreefishEngine.rotlXor(j10, 14, j12);
                long j13 = j9 + j8;
                long rotlXor2 = ThreefishEngine.rotlXor(j8, 16, j13);
                long j14 = j12 + rotlXor2;
                long rotlXor3 = ThreefishEngine.rotlXor(rotlXor2, 52, j14);
                long j15 = j13 + rotlXor;
                long rotlXor4 = ThreefishEngine.rotlXor(rotlXor, 57, j15);
                int[] iArr3 = iArr;
                long j16 = j14 + rotlXor4;
                long rotlXor5 = ThreefishEngine.rotlXor(rotlXor4, 23, j16);
                long j17 = j15 + rotlXor3;
                long rotlXor6 = ThreefishEngine.rotlXor(rotlXor3, 40, j17);
                long j18 = j16 + rotlXor6;
                long rotlXor7 = ThreefishEngine.rotlXor(rotlXor6, 5, j18);
                long j19 = j17 + rotlXor5;
                long rotlXor8 = ThreefishEngine.rotlXor(rotlXor5, 37, j19);
                long j20 = j18 + jArr3[i2];
                int i4 = i2 + 1;
                long j21 = rotlXor8 + jArr3[i4] + jArr4[i3];
                int i5 = i2 + 2;
                int i6 = i3 + 1;
                long j22 = j19 + jArr3[i5] + jArr4[i6];
                int i7 = i2 + 3;
                long j23 = i;
                long j24 = rotlXor7 + jArr3[i7] + j23;
                long j25 = j20 + j21;
                long rotlXor9 = ThreefishEngine.rotlXor(j21, 25, j25);
                long j26 = j22 + j24;
                long rotlXor10 = ThreefishEngine.rotlXor(j24, 33, j26);
                long j27 = j25 + rotlXor10;
                long rotlXor11 = ThreefishEngine.rotlXor(rotlXor10, 46, j27);
                long j28 = j26 + rotlXor9;
                long rotlXor12 = ThreefishEngine.rotlXor(rotlXor9, 12, j28);
                long j29 = j27 + rotlXor12;
                long rotlXor13 = ThreefishEngine.rotlXor(rotlXor12, 58, j29);
                long j30 = j28 + rotlXor11;
                long rotlXor14 = ThreefishEngine.rotlXor(rotlXor11, 22, j30);
                long j31 = j29 + rotlXor14;
                long rotlXor15 = ThreefishEngine.rotlXor(rotlXor14, 32, j31);
                long j32 = j30 + rotlXor13;
                long rotlXor16 = ThreefishEngine.rotlXor(rotlXor13, 32, j32);
                long j33 = j31 + jArr3[i4];
                j10 = jArr3[i5] + jArr4[i6] + rotlXor16;
                j9 = j32 + jArr3[i7] + jArr4[i3 + 2];
                j8 = rotlXor15 + jArr3[i2 + 4] + j23 + 1;
                i += 2;
                j11 = j33;
                iArr = iArr3;
                iArr2 = iArr2;
            }
            jArr2[0] = j11;
            jArr2[1] = j10;
            jArr2[2] = j9;
            jArr2[3] = j8;
        }
    }

    /* loaded from: classes9.dex */
    private static final class Threefish512Cipher extends ThreefishCipher {
        private static final int ROTATION_0_0 = 46;
        private static final int ROTATION_0_1 = 36;
        private static final int ROTATION_0_2 = 19;
        private static final int ROTATION_0_3 = 37;
        private static final int ROTATION_1_0 = 33;
        private static final int ROTATION_1_1 = 27;
        private static final int ROTATION_1_2 = 14;
        private static final int ROTATION_1_3 = 42;
        private static final int ROTATION_2_0 = 17;
        private static final int ROTATION_2_1 = 49;
        private static final int ROTATION_2_2 = 36;
        private static final int ROTATION_2_3 = 39;
        private static final int ROTATION_3_0 = 44;
        private static final int ROTATION_3_1 = 9;
        private static final int ROTATION_3_2 = 54;
        private static final int ROTATION_3_3 = 56;
        private static final int ROTATION_4_0 = 39;
        private static final int ROTATION_4_1 = 30;
        private static final int ROTATION_4_2 = 34;
        private static final int ROTATION_4_3 = 24;
        private static final int ROTATION_5_0 = 13;
        private static final int ROTATION_5_1 = 50;
        private static final int ROTATION_5_2 = 10;
        private static final int ROTATION_5_3 = 17;
        private static final int ROTATION_6_0 = 25;
        private static final int ROTATION_6_1 = 29;
        private static final int ROTATION_6_2 = 39;
        private static final int ROTATION_6_3 = 43;
        private static final int ROTATION_7_0 = 8;
        private static final int ROTATION_7_1 = 35;
        private static final int ROTATION_7_2 = 56;
        private static final int ROTATION_7_3 = 22;

        protected Threefish512Cipher(long[] jArr, long[] jArr2) {
            super(jArr, jArr2);
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        public void decryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.f9431kw;
            long[] jArr4 = this.f9432t;
            int[] iArr = ThreefishEngine.MOD9;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 17) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            boolean z = false;
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = jArr[2];
            long j4 = jArr[3];
            long j5 = jArr[4];
            long j6 = jArr[5];
            long j7 = jArr[6];
            long j8 = jArr[7];
            long j9 = j7;
            long j10 = j6;
            long j11 = j5;
            long j12 = j4;
            long j13 = j3;
            long j14 = j2;
            long j15 = j;
            int i = 17;
            for (int i2 = 1; i >= i2; i2 = 1) {
                int i3 = iArr[i];
                int i4 = iArr2[i];
                int i5 = i3 + 1;
                long j16 = j15 - jArr3[i5];
                int i6 = i3 + 2;
                long j17 = j14 - jArr3[i6];
                int i7 = i3 + 3;
                long j18 = j13 - jArr3[i7];
                int i8 = i3 + 4;
                long j19 = j12 - jArr3[i8];
                int i9 = i3 + 5;
                long j20 = j11 - jArr3[i9];
                int i10 = i3 + 6;
                int i11 = i4 + 1;
                int i12 = i;
                long j21 = j10 - (jArr3[i10] + jArr4[i11]);
                int i13 = i3 + 7;
                int[] iArr3 = iArr;
                int[] iArr4 = iArr2;
                long j22 = j9 - (jArr3[i13] + jArr4[i4 + 2]);
                long j23 = jArr3[i3 + 8];
                long[] jArr5 = jArr3;
                long[] jArr6 = jArr4;
                long j24 = i12;
                long xorRotr = ThreefishEngine.xorRotr(j17, 8, j22);
                long j25 = j22 - xorRotr;
                long xorRotr2 = ThreefishEngine.xorRotr(j8 - ((j23 + j24) + 1), 35, j16);
                long j26 = j16 - xorRotr2;
                long xorRotr3 = ThreefishEngine.xorRotr(j21, 56, j18);
                long j27 = j18 - xorRotr3;
                long xorRotr4 = ThreefishEngine.xorRotr(j19, 22, j20);
                long j28 = j20 - xorRotr4;
                long xorRotr5 = ThreefishEngine.xorRotr(xorRotr, 25, j28);
                long j29 = j28 - xorRotr5;
                long xorRotr6 = ThreefishEngine.xorRotr(xorRotr4, 29, j25);
                long j30 = j25 - xorRotr6;
                long xorRotr7 = ThreefishEngine.xorRotr(xorRotr3, 39, j26);
                long j31 = j26 - xorRotr7;
                long xorRotr8 = ThreefishEngine.xorRotr(xorRotr2, 43, j27);
                long j32 = j27 - xorRotr8;
                long xorRotr9 = ThreefishEngine.xorRotr(xorRotr5, 13, j32);
                long j33 = j32 - xorRotr9;
                long xorRotr10 = ThreefishEngine.xorRotr(xorRotr8, 50, j29);
                long j34 = j29 - xorRotr10;
                long xorRotr11 = ThreefishEngine.xorRotr(xorRotr7, 10, j30);
                long j35 = j30 - xorRotr11;
                long xorRotr12 = ThreefishEngine.xorRotr(xorRotr6, 17, j31);
                long j36 = j31 - xorRotr12;
                long xorRotr13 = ThreefishEngine.xorRotr(xorRotr9, 39, j36);
                long xorRotr14 = ThreefishEngine.xorRotr(xorRotr12, 30, j33);
                long xorRotr15 = ThreefishEngine.xorRotr(xorRotr11, 34, j34);
                long j37 = j34 - xorRotr15;
                long xorRotr16 = ThreefishEngine.xorRotr(xorRotr10, 24, j35);
                long j38 = (j36 - xorRotr13) - jArr5[i3];
                long j39 = xorRotr13 - jArr5[i5];
                long j40 = (j33 - xorRotr14) - jArr5[i6];
                long j41 = xorRotr14 - jArr5[i7];
                long j42 = j37 - jArr5[i8];
                long j43 = xorRotr15 - (jArr5[i9] + jArr6[i4]);
                long j44 = (j35 - xorRotr16) - (jArr5[i10] + jArr6[i11]);
                long j45 = xorRotr16 - (jArr5[i13] + j24);
                long xorRotr17 = ThreefishEngine.xorRotr(j39, 44, j44);
                long j46 = j44 - xorRotr17;
                long xorRotr18 = ThreefishEngine.xorRotr(j45, 9, j38);
                long j47 = j38 - xorRotr18;
                long xorRotr19 = ThreefishEngine.xorRotr(j43, 54, j40);
                long j48 = j40 - xorRotr19;
                long xorRotr20 = ThreefishEngine.xorRotr(j41, 56, j42);
                long j49 = j42 - xorRotr20;
                long xorRotr21 = ThreefishEngine.xorRotr(xorRotr17, 17, j49);
                long j50 = j49 - xorRotr21;
                long xorRotr22 = ThreefishEngine.xorRotr(xorRotr20, 49, j46);
                long j51 = j46 - xorRotr22;
                long xorRotr23 = ThreefishEngine.xorRotr(xorRotr19, 36, j47);
                long j52 = j47 - xorRotr23;
                long xorRotr24 = ThreefishEngine.xorRotr(xorRotr18, 39, j48);
                long j53 = j48 - xorRotr24;
                long xorRotr25 = ThreefishEngine.xorRotr(xorRotr21, 33, j53);
                long j54 = j53 - xorRotr25;
                long xorRotr26 = ThreefishEngine.xorRotr(xorRotr24, 27, j50);
                long j55 = j50 - xorRotr26;
                long xorRotr27 = ThreefishEngine.xorRotr(xorRotr23, 14, j51);
                long j56 = j51 - xorRotr27;
                long xorRotr28 = ThreefishEngine.xorRotr(xorRotr22, 42, j52);
                long j57 = j52 - xorRotr28;
                long xorRotr29 = ThreefishEngine.xorRotr(xorRotr25, 46, j57);
                j15 = j57 - xorRotr29;
                j12 = ThreefishEngine.xorRotr(xorRotr28, 36, j54);
                j10 = ThreefishEngine.xorRotr(xorRotr27, 19, j55);
                j11 = j55 - j10;
                j8 = ThreefishEngine.xorRotr(xorRotr26, 37, j56);
                j9 = j56 - j8;
                i = i12 - 2;
                j13 = j54 - j12;
                j14 = xorRotr29;
                iArr = iArr3;
                iArr2 = iArr4;
                jArr3 = jArr5;
                jArr4 = jArr6;
                z = false;
            }
            long[] jArr7 = jArr3;
            long[] jArr8 = jArr4;
            boolean z2 = z;
            long j58 = j15 - jArr7[z2 ? 1 : 0];
            long j59 = j14 - jArr7[1];
            long j60 = j13 - jArr7[2];
            long j61 = j12 - jArr7[3];
            long j62 = j11 - jArr7[4];
            long j63 = j10 - (jArr7[5] + jArr8[z2 ? 1 : 0]);
            long j64 = j9 - (jArr7[6] + jArr8[1]);
            long j65 = j8 - jArr7[7];
            jArr2[z2 ? 1 : 0] = j58;
            jArr2[1] = j59;
            jArr2[2] = j60;
            jArr2[3] = j61;
            jArr2[4] = j62;
            jArr2[5] = j63;
            jArr2[6] = j64;
            jArr2[7] = j65;
        }

        @Override // org.bouncycastle.crypto.engines.ThreefishEngine.ThreefishCipher
        public void encryptBlock(long[] jArr, long[] jArr2) {
            long[] jArr3 = this.f9431kw;
            long[] jArr4 = this.f9432t;
            int[] iArr = ThreefishEngine.MOD9;
            int[] iArr2 = ThreefishEngine.MOD3;
            if (jArr3.length != 17) {
                throw new IllegalArgumentException();
            }
            if (jArr4.length != 5) {
                throw new IllegalArgumentException();
            }
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = jArr[2];
            long j4 = jArr[3];
            long j5 = jArr[4];
            long j6 = jArr[5];
            long j7 = jArr[6];
            long j8 = jArr[7];
            long j9 = j + jArr3[0];
            long j10 = j2 + jArr3[1];
            long j11 = j3 + jArr3[2];
            long j12 = j4 + jArr3[3];
            long j13 = j5 + jArr3[4];
            long j14 = j6 + jArr3[5] + jArr4[0];
            long j15 = j7 + jArr3[6] + jArr4[1];
            long j16 = j12;
            long j17 = j8 + jArr3[7];
            long j18 = j11;
            long j19 = j10;
            long j20 = j9;
            int i = 1;
            long j21 = j14;
            long j22 = j13;
            while (i < 18) {
                int i2 = iArr[i];
                int i3 = iArr2[i];
                long j23 = j20 + j19;
                long rotlXor = ThreefishEngine.rotlXor(j19, 46, j23);
                int[] iArr3 = iArr;
                int[] iArr4 = iArr2;
                long j24 = j18 + j16;
                long rotlXor2 = ThreefishEngine.rotlXor(j16, 36, j24);
                long[] jArr5 = jArr3;
                long j25 = j22 + j21;
                long rotlXor3 = ThreefishEngine.rotlXor(j21, 19, j25);
                int i4 = i;
                long j26 = j17;
                long j27 = j15 + j26;
                long rotlXor4 = ThreefishEngine.rotlXor(j26, 37, j27);
                long j28 = j24 + rotlXor;
                long rotlXor5 = ThreefishEngine.rotlXor(rotlXor, 33, j28);
                long j29 = j25 + rotlXor4;
                long rotlXor6 = ThreefishEngine.rotlXor(rotlXor4, 27, j29);
                long j30 = j27 + rotlXor3;
                long rotlXor7 = ThreefishEngine.rotlXor(rotlXor3, 14, j30);
                long j31 = j23 + rotlXor2;
                long rotlXor8 = ThreefishEngine.rotlXor(rotlXor2, 42, j31);
                long j32 = j29 + rotlXor5;
                long rotlXor9 = ThreefishEngine.rotlXor(rotlXor5, 17, j32);
                long j33 = j30 + rotlXor8;
                long rotlXor10 = ThreefishEngine.rotlXor(rotlXor8, 49, j33);
                long j34 = j31 + rotlXor7;
                long rotlXor11 = ThreefishEngine.rotlXor(rotlXor7, 36, j34);
                long j35 = j28 + rotlXor6;
                long rotlXor12 = ThreefishEngine.rotlXor(rotlXor6, 39, j35);
                long j36 = j33 + rotlXor9;
                long rotlXor13 = ThreefishEngine.rotlXor(rotlXor9, 44, j36);
                long j37 = j34 + rotlXor12;
                long rotlXor14 = ThreefishEngine.rotlXor(rotlXor12, 9, j37);
                long j38 = j35 + rotlXor11;
                long rotlXor15 = ThreefishEngine.rotlXor(rotlXor11, 54, j38);
                long j39 = j32 + rotlXor10;
                long rotlXor16 = ThreefishEngine.rotlXor(rotlXor10, 56, j39);
                long j40 = j37 + jArr5[i2];
                int i5 = i2 + 1;
                long j41 = rotlXor13 + jArr5[i5];
                int i6 = i2 + 2;
                long j42 = j38 + jArr5[i6];
                int i7 = i2 + 3;
                long j43 = rotlXor16 + jArr5[i7];
                int i8 = i2 + 4;
                long j44 = j39 + jArr5[i8];
                int i9 = i2 + 5;
                long j45 = rotlXor15 + jArr5[i9] + jArr4[i3];
                int i10 = i2 + 6;
                int i11 = i3 + 1;
                long j46 = j36 + jArr5[i10] + jArr4[i11];
                int i12 = i2 + 7;
                long j47 = i4;
                long j48 = rotlXor14 + jArr5[i12] + j47;
                long j49 = j40 + j41;
                long rotlXor17 = ThreefishEngine.rotlXor(j41, 39, j49);
                long j50 = j42 + j43;
                long rotlXor18 = ThreefishEngine.rotlXor(j43, 30, j50);
                long j51 = j44 + j45;
                long rotlXor19 = ThreefishEngine.rotlXor(j45, 34, j51);
                long j52 = j46 + j48;
                long rotlXor20 = ThreefishEngine.rotlXor(j48, 24, j52);
                long j53 = j50 + rotlXor17;
                long rotlXor21 = ThreefishEngine.rotlXor(rotlXor17, 13, j53);
                long j54 = j51 + rotlXor20;
                long rotlXor22 = ThreefishEngine.rotlXor(rotlXor20, 50, j54);
                long j55 = j52 + rotlXor19;
                long rotlXor23 = ThreefishEngine.rotlXor(rotlXor19, 10, j55);
                long j56 = j49 + rotlXor18;
                long[] jArr6 = jArr4;
                long rotlXor24 = ThreefishEngine.rotlXor(rotlXor18, 17, j56);
                long j57 = j54 + rotlXor21;
                long rotlXor25 = ThreefishEngine.rotlXor(rotlXor21, 25, j57);
                long j58 = j55 + rotlXor24;
                long rotlXor26 = ThreefishEngine.rotlXor(rotlXor24, 29, j58);
                long j59 = j56 + rotlXor23;
                long rotlXor27 = ThreefishEngine.rotlXor(rotlXor23, 39, j59);
                long j60 = j53 + rotlXor22;
                long rotlXor28 = ThreefishEngine.rotlXor(rotlXor22, 43, j60);
                long j61 = j58 + rotlXor25;
                long rotlXor29 = ThreefishEngine.rotlXor(rotlXor25, 8, j61);
                long j62 = j59 + rotlXor28;
                long rotlXor30 = ThreefishEngine.rotlXor(rotlXor28, 35, j62);
                long j63 = j60 + rotlXor27;
                long rotlXor31 = ThreefishEngine.rotlXor(rotlXor27, 56, j63);
                long j64 = j57 + rotlXor26;
                long rotlXor32 = ThreefishEngine.rotlXor(rotlXor26, 22, j64);
                j20 = j62 + jArr5[i5];
                long j65 = rotlXor29 + jArr5[i6];
                j18 = j63 + jArr5[i7];
                j16 = rotlXor32 + jArr5[i8];
                j22 = j64 + jArr5[i9];
                j21 = rotlXor31 + jArr5[i10] + jArr6[i11];
                j15 = j61 + jArr5[i12] + jArr6[i3 + 2];
                j17 = rotlXor30 + jArr5[i2 + 8] + j47 + 1;
                i = i4 + 2;
                j19 = j65;
                iArr = iArr3;
                iArr2 = iArr4;
                jArr3 = jArr5;
                jArr4 = jArr6;
            }
            jArr2[0] = j20;
            jArr2[1] = j19;
            jArr2[2] = j18;
            jArr2[3] = j16;
            jArr2[4] = j22;
            jArr2[5] = j21;
            jArr2[6] = j15;
            jArr2[7] = j17;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static abstract class ThreefishCipher {

        /* renamed from: kw */
        protected final long[] f9431kw;

        /* renamed from: t */
        protected final long[] f9432t;

        protected ThreefishCipher(long[] jArr, long[] jArr2) {
            this.f9431kw = jArr;
            this.f9432t = jArr2;
        }

        abstract void decryptBlock(long[] jArr, long[] jArr2);

        abstract void encryptBlock(long[] jArr, long[] jArr2);
    }

    static {
        int[] iArr = MOD9;
        MOD17 = new int[iArr.length];
        MOD5 = new int[iArr.length];
        MOD3 = new int[iArr.length];
        int i = 0;
        while (true) {
            int[] iArr2 = MOD9;
            if (i >= iArr2.length) {
                return;
            }
            MOD17[i] = i % 17;
            iArr2[i] = i % 9;
            MOD5[i] = i % 5;
            MOD3[i] = i % 3;
            i++;
        }
    }

    public ThreefishEngine(int i) {
        ThreefishCipher threefish256Cipher;
        this.blocksizeBytes = i / 8;
        this.blocksizeWords = this.blocksizeBytes / 8;
        int i2 = this.blocksizeWords;
        this.currentBlock = new long[i2];
        this.f9429kw = new long[(i2 * 2) + 1];
        if (i == 256) {
            threefish256Cipher = new Threefish256Cipher(this.f9429kw, this.f9430t);
        } else if (i == 512) {
            threefish256Cipher = new Threefish512Cipher(this.f9429kw, this.f9430t);
        } else {
            if (i != 1024) {
                throw new IllegalArgumentException("Invalid blocksize - Threefish is defined with block size of 256, 512, or 1024 bits");
            }
            threefish256Cipher = new Threefish1024Cipher(this.f9429kw, this.f9430t);
        }
        this.cipher = threefish256Cipher;
    }

    public static long bytesToWord(byte[] bArr, int i) {
        if (i + 8 > bArr.length) {
            throw new IllegalArgumentException();
        }
        long j = bArr[i] & 255;
        int i2 = i + 1 + 1 + 1;
        long j2 = j | ((bArr[r0] & 255) << 8) | ((bArr[r8] & 255) << 16);
        long j3 = j2 | ((bArr[i2] & 255) << 24);
        long j4 = j3 | ((bArr[r8] & 255) << 32);
        long j5 = j4 | ((bArr[r2] & 255) << 40);
        int i3 = i2 + 1 + 1 + 1 + 1;
        return ((bArr[i3] & 255) << 56) | j5 | ((bArr[r8] & 255) << 48);
    }

    static long rotlXor(long j, int i, long j2) {
        return ((j >>> (-i)) | (j << i)) ^ j2;
    }

    private void setKey(long[] jArr) {
        if (jArr.length != this.blocksizeWords) {
            throw new IllegalArgumentException("Threefish key must be same size as block (" + this.blocksizeWords + " words)");
        }
        long j = 2004413935125273122L;
        int i = 0;
        while (true) {
            int i2 = this.blocksizeWords;
            if (i >= i2) {
                long[] jArr2 = this.f9429kw;
                jArr2[i2] = j;
                System.arraycopy(jArr2, 0, jArr2, i2 + 1, i2);
                return;
            } else {
                long[] jArr3 = this.f9429kw;
                jArr3[i] = jArr[i];
                j ^= jArr3[i];
                i++;
            }
        }
    }

    private void setTweak(long[] jArr) {
        if (jArr.length != 2) {
            throw new IllegalArgumentException("Tweak must be 2 words.");
        }
        long[] jArr2 = this.f9430t;
        jArr2[0] = jArr[0];
        jArr2[1] = jArr[1];
        jArr2[2] = jArr2[0] ^ jArr2[1];
        jArr2[3] = jArr2[0];
        jArr2[4] = jArr2[1];
    }

    public static void wordToBytes(long j, byte[] bArr, int i) {
        if (i + 8 > bArr.length) {
            throw new IllegalArgumentException();
        }
        int i2 = i + 1;
        bArr[i] = (byte) j;
        int i3 = i2 + 1;
        bArr[i2] = (byte) (j >> 8);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (j >> 16);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (j >> 24);
        int i6 = i5 + 1;
        bArr[i5] = (byte) (j >> 32);
        int i7 = i6 + 1;
        bArr[i6] = (byte) (j >> 40);
        bArr[i7] = (byte) (j >> 48);
        bArr[i7 + 1] = (byte) (j >> 56);
    }

    static long xorRotr(long j, int i, long j2) {
        long j3 = j ^ j2;
        return (j3 << (-i)) | (j3 >>> i);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Threefish-" + (this.blocksizeBytes * 8);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.blocksizeBytes;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        byte[] key;
        byte[] bArr;
        long[] jArr;
        long[] jArr2 = null;
        if (cipherParameters instanceof TweakableBlockCipherParameters) {
            TweakableBlockCipherParameters tweakableBlockCipherParameters = (TweakableBlockCipherParameters) cipherParameters;
            key = tweakableBlockCipherParameters.getKey().getKey();
            bArr = tweakableBlockCipherParameters.getTweak();
        } else {
            if (!(cipherParameters instanceof KeyParameter)) {
                throw new IllegalArgumentException("Invalid parameter passed to Threefish init - " + cipherParameters.getClass().getName());
            }
            key = ((KeyParameter) cipherParameters).getKey();
            bArr = null;
        }
        if (key == null) {
            jArr = null;
        } else {
            if (key.length != this.blocksizeBytes) {
                throw new IllegalArgumentException("Threefish key must be same size as block (" + this.blocksizeBytes + " bytes)");
            }
            jArr = new long[this.blocksizeWords];
            for (int i = 0; i < jArr.length; i++) {
                jArr[i] = bytesToWord(key, i * 8);
            }
        }
        if (bArr != null) {
            if (bArr.length != 16) {
                throw new IllegalArgumentException("Threefish tweak must be 16 bytes");
            }
            jArr2 = new long[]{bytesToWord(bArr, 0), bytesToWord(bArr, 8)};
        }
        init(z, jArr, jArr2);
    }

    public void init(boolean z, long[] jArr, long[] jArr2) {
        this.forEncryption = z;
        if (jArr != null) {
            setKey(jArr);
        }
        if (jArr2 != null) {
            setTweak(jArr2);
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        int i3 = this.blocksizeBytes;
        if (i + i3 > bArr.length) {
            throw new DataLengthException("Input buffer too short");
        }
        if (i3 + i2 > bArr2.length) {
            throw new OutputLengthException("Output buffer too short");
        }
        int i4 = 0;
        for (int i5 = 0; i5 < this.blocksizeBytes; i5 += 8) {
            this.currentBlock[i5 >> 3] = bytesToWord(bArr, i + i5);
        }
        long[] jArr = this.currentBlock;
        processBlock(jArr, jArr);
        while (true) {
            int i6 = this.blocksizeBytes;
            if (i4 >= i6) {
                return i6;
            }
            wordToBytes(this.currentBlock[i4 >> 3], bArr2, i2 + i4);
            i4 += 8;
        }
    }

    public int processBlock(long[] jArr, long[] jArr2) throws DataLengthException, IllegalStateException {
        long[] jArr3 = this.f9429kw;
        int i = this.blocksizeWords;
        if (jArr3[i] == 0) {
            throw new IllegalStateException("Threefish engine not initialised");
        }
        if (jArr.length != i) {
            throw new DataLengthException("Input buffer too short");
        }
        if (jArr2.length != i) {
            throw new OutputLengthException("Output buffer too short");
        }
        if (this.forEncryption) {
            this.cipher.encryptBlock(jArr, jArr2);
        } else {
            this.cipher.decryptBlock(jArr, jArr2);
        }
        return this.blocksizeWords;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
