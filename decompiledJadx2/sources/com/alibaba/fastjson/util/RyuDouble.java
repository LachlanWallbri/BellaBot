package com.alibaba.fastjson.util;

import java.lang.reflect.Array;
import java.math.BigInteger;
import org.apache.commons.codec.language.Soundex;
import org.apache.commons.io.FilenameUtils;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public final class RyuDouble {
    private static final int[][] POW5_SPLIT = (int[][]) Array.newInstance((Class<?>) int.class, 326, 4);
    private static final int[][] POW5_INV_SPLIT = (int[][]) Array.newInstance((Class<?>) int.class, 291, 4);

    static {
        BigInteger subtract = BigInteger.ONE.shiftLeft(31).subtract(BigInteger.ONE);
        BigInteger subtract2 = BigInteger.ONE.shiftLeft(31).subtract(BigInteger.ONE);
        int i = 0;
        while (i < 326) {
            BigInteger pow = BigInteger.valueOf(5L).pow(i);
            int bitLength = pow.bitLength();
            int i2 = i == 0 ? 1 : (int) ((((i * 23219280) + 10000000) - 1) / 10000000);
            if (i2 != bitLength) {
                throw new IllegalStateException(bitLength + " != " + i2);
            }
            if (i < POW5_SPLIT.length) {
                for (int i3 = 0; i3 < 4; i3++) {
                    POW5_SPLIT[i][i3] = pow.shiftRight((bitLength - 121) + ((3 - i3) * 31)).and(subtract).intValue();
                }
            }
            if (i < POW5_INV_SPLIT.length) {
                BigInteger add = BigInteger.ONE.shiftLeft(bitLength + 121).divide(pow).add(BigInteger.ONE);
                for (int i4 = 0; i4 < 4; i4++) {
                    if (i4 == 0) {
                        POW5_INV_SPLIT[i][i4] = add.shiftRight((3 - i4) * 31).intValue();
                    } else {
                        POW5_INV_SPLIT[i][i4] = add.shiftRight((3 - i4) * 31).and(subtract2).intValue();
                    }
                }
            }
            i++;
        }
    }

    public static String toString(double d) {
        char[] cArr = new char[24];
        return new String(cArr, 0, toString(d, cArr, 0));
    }

    public static int toString(double d, char[] cArr, int i) {
        int i2;
        boolean z;
        boolean z2;
        long j;
        long j2;
        long j3;
        int i3;
        boolean z3;
        boolean z4;
        long j4;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        boolean z5;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        if (!Double.isNaN(d)) {
            if (d == Double.POSITIVE_INFINITY) {
                int i14 = i + 1;
                cArr[i] = 'I';
                int i15 = i14 + 1;
                cArr[i14] = 'n';
                int i16 = i15 + 1;
                cArr[i15] = 'f';
                int i17 = i16 + 1;
                cArr[i16] = 'i';
                int i18 = i17 + 1;
                cArr[i17] = 'n';
                int i19 = i18 + 1;
                cArr[i18] = 'i';
                int i20 = i19 + 1;
                cArr[i19] = 't';
                i12 = i20 + 1;
                cArr[i20] = 'y';
            } else if (d == Double.NEGATIVE_INFINITY) {
                int i21 = i + 1;
                cArr[i] = Soundex.SILENT_MARKER;
                int i22 = i21 + 1;
                cArr[i21] = 'I';
                int i23 = i22 + 1;
                cArr[i22] = 'n';
                int i24 = i23 + 1;
                cArr[i23] = 'f';
                int i25 = i24 + 1;
                cArr[i24] = 'i';
                int i26 = i25 + 1;
                cArr[i25] = 'n';
                int i27 = i26 + 1;
                cArr[i26] = 'i';
                int i28 = i27 + 1;
                cArr[i27] = 't';
                i13 = i28 + 1;
                cArr[i28] = 'y';
            } else {
                long doubleToLongBits = Double.doubleToLongBits(d);
                if (doubleToLongBits == 0) {
                    int i29 = i + 1;
                    cArr[i] = '0';
                    int i30 = i29 + 1;
                    cArr[i29] = FilenameUtils.EXTENSION_SEPARATOR;
                    i13 = i30 + 1;
                    cArr[i30] = '0';
                } else {
                    if (doubleToLongBits != Long.MIN_VALUE) {
                        int i31 = (int) ((doubleToLongBits >>> 52) & 2047);
                        long j5 = doubleToLongBits & 4503599627370495L;
                        if (i31 == 0) {
                            i2 = -1074;
                        } else {
                            i2 = (i31 - 1023) - 52;
                            j5 |= 4503599627370496L;
                        }
                        boolean z6 = doubleToLongBits < 0;
                        boolean z7 = (j5 & 1) == 0;
                        long j6 = 4 * j5;
                        long j7 = j6 + 2;
                        int i32 = (j5 != 4503599627370496L || i31 <= 1) ? 1 : 0;
                        long j8 = (j6 - 1) - i32;
                        int i33 = i2 - 2;
                        if (i33 >= 0) {
                            int max = Math.max(0, ((int) ((i33 * 3010299) / 10000000)) - 1);
                            int i34 = ((((-i33) + max) + (((max == 0 ? 1 : (int) ((((max * 23219280) + 10000000) - 1) / 10000000)) + 122) - 1)) - 93) - 21;
                            if (i34 < 0) {
                                throw new IllegalArgumentException("" + i34);
                            }
                            int[] iArr = POW5_INV_SPLIT[max];
                            long j9 = j6 >>> 31;
                            long j10 = j6 & 2147483647L;
                            z2 = z7;
                            z = z6;
                            long j11 = ((((((((((((j10 * iArr[3]) >>> 31) + (iArr[2] * j10)) + (j9 * iArr[3])) >>> 31) + (iArr[1] * j10)) + (iArr[2] * j9)) >>> 31) + (iArr[0] * j10)) + (iArr[1] * j9)) >>> 21) + ((iArr[0] * j9) << 10)) >>> i34;
                            long j12 = j7 >>> 31;
                            long j13 = j7 & 2147483647L;
                            long j14 = ((((((((((((j13 * iArr[3]) >>> 31) + (iArr[2] * j13)) + (j12 * iArr[3])) >>> 31) + (iArr[1] * j13)) + (iArr[2] * j12)) >>> 31) + (iArr[0] * j13)) + (iArr[1] * j12)) >>> 21) + ((iArr[0] * j12) << 10)) >>> i34;
                            long j15 = j8 >>> 31;
                            long j16 = j8 & 2147483647L;
                            j2 = j14;
                            long j17 = ((((((((((((j16 * iArr[3]) >>> 31) + (iArr[2] * j16)) + (j15 * iArr[3])) >>> 31) + (iArr[1] * j16)) + (iArr[2] * j15)) >>> 31) + (iArr[0] * j16)) + (iArr[1] * j15)) >>> 21) + ((iArr[0] * j15) << 10)) >>> i34;
                            if (max <= 21) {
                                long j18 = j6 % 5;
                                if (j18 == 0) {
                                    if (j18 != 0) {
                                        i11 = 0;
                                    } else if (j6 % 25 != 0) {
                                        i11 = 1;
                                    } else if (j6 % 125 != 0) {
                                        i11 = 2;
                                    } else if (j6 % 625 != 0) {
                                        i11 = 3;
                                    } else {
                                        long j19 = j6 / 625;
                                        i11 = 4;
                                        for (long j20 = 0; j19 > j20 && j19 % 5 == j20; j20 = 0) {
                                            j19 /= 5;
                                            i11++;
                                        }
                                    }
                                    z5 = i11 >= max;
                                    z4 = false;
                                    z3 = z5;
                                    j = j11;
                                    j3 = j17;
                                    i3 = max;
                                } else if (z2) {
                                    if (j8 % 5 != 0) {
                                        i10 = 0;
                                    } else if (j8 % 25 != 0) {
                                        i10 = 1;
                                    } else if (j8 % 125 != 0) {
                                        i10 = 2;
                                    } else if (j8 % 625 != 0) {
                                        i10 = 3;
                                    } else {
                                        i10 = 4;
                                        long j21 = j8 / 625;
                                        for (long j22 = 0; j21 > j22 && j21 % 5 == j22; j22 = 0) {
                                            j21 /= 5;
                                            i10++;
                                        }
                                    }
                                    z4 = i10 >= max;
                                    z5 = false;
                                    z3 = z5;
                                    j = j11;
                                    j3 = j17;
                                    i3 = max;
                                } else {
                                    if (j7 % 5 != 0) {
                                        i9 = 0;
                                    } else if (j7 % 25 != 0) {
                                        i9 = 1;
                                    } else if (j7 % 125 != 0) {
                                        i9 = 2;
                                    } else if (j7 % 625 != 0) {
                                        i9 = 3;
                                    } else {
                                        i9 = 4;
                                        long j23 = j7 / 625;
                                        for (long j24 = 0; j23 > j24 && j23 % 5 == j24; j24 = 0) {
                                            j23 /= 5;
                                            i9++;
                                        }
                                    }
                                    if (i9 >= max) {
                                        j2--;
                                    }
                                }
                            }
                            z5 = false;
                            z4 = false;
                            z3 = z5;
                            j = j11;
                            j3 = j17;
                            i3 = max;
                        } else {
                            z = z6;
                            z2 = z7;
                            int i35 = -i33;
                            int max2 = Math.max(0, ((int) ((i35 * 6989700) / 10000000)) - 1);
                            int i36 = i35 - max2;
                            int i37 = ((max2 - ((i36 == 0 ? 1 : (int) ((((i36 * 23219280) + 10000000) - 1) / 10000000)) - 121)) - 93) - 21;
                            if (i37 < 0) {
                                throw new IllegalArgumentException("" + i37);
                            }
                            int[] iArr2 = POW5_SPLIT[i36];
                            long j25 = j6 >>> 31;
                            long j26 = j6 & 2147483647L;
                            int i38 = i32;
                            long j27 = j7 >>> 31;
                            long j28 = j7 & 2147483647L;
                            j = ((((((((((((j26 * iArr2[3]) >>> 31) + (iArr2[2] * j26)) + (j25 * iArr2[3])) >>> 31) + (iArr2[1] * j26)) + (iArr2[2] * j25)) >>> 31) + (iArr2[0] * j26)) + (iArr2[1] * j25)) >>> 21) + ((iArr2[0] * j25) << 10)) >>> i37;
                            long j29 = j8 >>> 31;
                            long j30 = j8 & 2147483647L;
                            j2 = ((((((((((((j28 * iArr2[3]) >>> 31) + (iArr2[2] * j28)) + (j27 * iArr2[3])) >>> 31) + (iArr2[1] * j28)) + (iArr2[2] * j27)) >>> 31) + (iArr2[0] * j28)) + (iArr2[1] * j27)) >>> 21) + ((iArr2[0] * j27) << 10)) >>> i37;
                            j3 = ((((((((((((j30 * iArr2[3]) >>> 31) + (iArr2[2] * j30)) + (j29 * iArr2[3])) >>> 31) + (iArr2[1] * j30)) + (iArr2[2] * j29)) >>> 31) + (iArr2[0] * j30)) + (iArr2[1] * j29)) >>> 21) + ((iArr2[0] * j29) << 10)) >>> i37;
                            i3 = i33 + max2;
                            z3 = true;
                            if (max2 <= 1) {
                                if (!z2) {
                                    j2--;
                                } else if (i38 == 1) {
                                    z4 = true;
                                }
                            } else if (max2 < 63) {
                                z3 = (j6 & ((1 << (max2 - 1)) - 1)) == 0;
                            } else {
                                z3 = false;
                            }
                            z4 = false;
                        }
                        int i39 = j2 >= 1000000000000000000L ? 19 : j2 >= 100000000000000000L ? 18 : j2 >= 10000000000000000L ? 17 : j2 >= 1000000000000000L ? 16 : j2 >= 100000000000000L ? 15 : j2 >= 10000000000000L ? 14 : j2 >= 1000000000000L ? 13 : j2 >= 100000000000L ? 12 : j2 >= 10000000000L ? 11 : j2 >= 1000000000 ? 10 : j2 >= 100000000 ? 9 : j2 >= 10000000 ? 8 : j2 >= 1000000 ? 7 : j2 >= 100000 ? 6 : j2 >= 10000 ? 5 : j2 >= 1000 ? 4 : j2 >= 100 ? 3 : j2 >= 10 ? 2 : 1;
                        int i40 = (i3 + i39) - 1;
                        boolean z8 = i40 < -3 || i40 >= 7;
                        if (z4 || z3) {
                            int i41 = 0;
                            int i42 = 0;
                            while (true) {
                                long j31 = j2 / 10;
                                long j32 = j3 / 10;
                                if (j31 <= j32 || (j2 < 100 && z8)) {
                                    break;
                                }
                                z4 &= j3 % 10 == 0;
                                z3 &= i41 == 0;
                                i41 = (int) (j % 10);
                                j /= 10;
                                i42++;
                                j2 = j31;
                                j3 = j32;
                            }
                            if (z4 && z2) {
                                while (j3 % 10 == 0 && (j2 >= 100 || !z8)) {
                                    z3 &= i41 == 0;
                                    i41 = (int) (j % 10);
                                    j2 /= 10;
                                    j /= 10;
                                    j3 /= 10;
                                    i42++;
                                }
                            }
                            if (z3 && i41 == 5 && j % 2 == 0) {
                                i41 = 4;
                            }
                            j4 = j + (((j != j3 || (z4 && z2)) && i41 < 5) ? 0 : 1);
                            i4 = i42;
                        } else {
                            i4 = 0;
                            int i43 = 0;
                            while (true) {
                                long j33 = j2 / 10;
                                long j34 = j3 / 10;
                                if (j33 <= j34 || (j2 < 100 && z8)) {
                                    break;
                                }
                                i43 = (int) (j % 10);
                                j /= 10;
                                i4++;
                                j2 = j33;
                                j3 = j34;
                            }
                            j4 = j + ((j == j3 || i43 >= 5) ? 1 : 0);
                        }
                        int i44 = i39 - i4;
                        if (z) {
                            i5 = i + 1;
                            cArr[i] = Soundex.SILENT_MARKER;
                        } else {
                            i5 = i;
                        }
                        if (z8) {
                            for (int i45 = 0; i45 < i44 - 1; i45++) {
                                int i46 = (int) (j4 % 10);
                                j4 /= 10;
                                cArr[(i5 + i44) - i45] = (char) (i46 + 48);
                            }
                            cArr[i5] = (char) ((j4 % 10) + 48);
                            cArr[i5 + 1] = FilenameUtils.EXTENSION_SEPARATOR;
                            int i47 = i5 + i44 + 1;
                            if (i44 == 1) {
                                cArr[i47] = '0';
                                i47++;
                            }
                            int i48 = i47 + 1;
                            cArr[i47] = 'E';
                            if (i40 < 0) {
                                i7 = i48 + 1;
                                cArr[i48] = Soundex.SILENT_MARKER;
                                i40 = -i40;
                            } else {
                                i7 = i48;
                            }
                            if (i40 >= 100) {
                                int i49 = i7 + 1;
                                i8 = 48;
                                cArr[i7] = (char) ((i40 / 100) + 48);
                                i40 %= 100;
                                i7 = i49 + 1;
                                cArr[i49] = (char) ((i40 / 10) + 48);
                            } else {
                                i8 = 48;
                                if (i40 >= 10) {
                                    cArr[i7] = (char) ((i40 / 10) + 48);
                                    i7++;
                                }
                            }
                            cArr[i7] = (char) ((i40 % 10) + i8);
                            return (i7 + 1) - i;
                        }
                        char c = '0';
                        if (i40 < 0) {
                            int i50 = i5 + 1;
                            cArr[i5] = '0';
                            int i51 = i50 + 1;
                            cArr[i50] = FilenameUtils.EXTENSION_SEPARATOR;
                            int i52 = -1;
                            while (i52 > i40) {
                                cArr[i51] = c;
                                i52--;
                                i51++;
                                c = '0';
                            }
                            i6 = i51;
                            for (int i53 = 0; i53 < i44; i53++) {
                                cArr[((i51 + i44) - i53) - 1] = (char) ((j4 % 10) + 48);
                                j4 /= 10;
                                i6++;
                            }
                        } else {
                            int i54 = i40 + 1;
                            if (i54 >= i44) {
                                for (int i55 = 0; i55 < i44; i55++) {
                                    cArr[((i5 + i44) - i55) - 1] = (char) ((j4 % 10) + 48);
                                    j4 /= 10;
                                }
                                int i56 = i5 + i44;
                                while (i44 < i54) {
                                    cArr[i56] = '0';
                                    i44++;
                                    i56++;
                                }
                                int i57 = i56 + 1;
                                cArr[i56] = FilenameUtils.EXTENSION_SEPARATOR;
                                i6 = i57 + 1;
                                cArr[i57] = '0';
                            } else {
                                int i58 = i5 + 1;
                                for (int i59 = 0; i59 < i44; i59++) {
                                    if ((i44 - i59) - 1 == i40) {
                                        cArr[((i58 + i44) - i59) - 1] = FilenameUtils.EXTENSION_SEPARATOR;
                                        i58--;
                                    }
                                    cArr[((i58 + i44) - i59) - 1] = (char) ((j4 % 10) + 48);
                                    j4 /= 10;
                                }
                                i6 = i5 + i44 + 1;
                            }
                        }
                        return i6 - i;
                    }
                    int i60 = i + 1;
                    cArr[i] = Soundex.SILENT_MARKER;
                    int i61 = i60 + 1;
                    cArr[i60] = '0';
                    int i62 = i61 + 1;
                    cArr[i61] = FilenameUtils.EXTENSION_SEPARATOR;
                    i12 = i62 + 1;
                    cArr[i62] = '0';
                }
            }
            return i12 - i;
        }
        int i63 = i + 1;
        cArr[i] = 'N';
        int i64 = i63 + 1;
        cArr[i63] = 'a';
        i13 = i64 + 1;
        cArr[i64] = 'N';
        return i13 - i;
    }
}
