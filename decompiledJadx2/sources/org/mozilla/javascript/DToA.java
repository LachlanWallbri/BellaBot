package org.mozilla.javascript;

import java.math.BigInteger;
import org.apache.commons.codec.language.Soundex;
import org.apache.commons.io.FilenameUtils;
import org.threeten.p095bp.chrono.HijrahDate;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
class DToA {
    private static final int Bias = 1023;
    private static final int Bletch = 16;
    private static final int Bndry_mask = 1048575;
    static final int DTOSTR_EXPONENTIAL = 3;
    static final int DTOSTR_FIXED = 2;
    static final int DTOSTR_PRECISION = 4;
    static final int DTOSTR_STANDARD = 0;
    static final int DTOSTR_STANDARD_EXPONENTIAL = 1;
    private static final int Exp_11 = 1072693248;
    private static final int Exp_mask = 2146435072;
    private static final int Exp_mask_shifted = 2047;
    private static final int Exp_msk1 = 1048576;
    private static final long Exp_msk1L = 4503599627370496L;
    private static final int Exp_shift = 20;
    private static final int Exp_shift1 = 20;
    private static final int Exp_shiftL = 52;
    private static final int Frac_mask = 1048575;
    private static final int Frac_mask1 = 1048575;
    private static final long Frac_maskL = 4503599627370495L;
    private static final int Int_max = 14;
    private static final int Log2P = 1;

    /* renamed from: P */
    private static final int f10197P = 53;
    private static final int Quick_max = 14;
    private static final int Sign_bit = Integer.MIN_VALUE;
    private static final int Ten_pmax = 22;
    private static final int n_bigtens = 5;
    private static final double[] tens = {1.0d, 10.0d, 100.0d, 1000.0d, 10000.0d, 100000.0d, 1000000.0d, 1.0E7d, 1.0E8d, 1.0E9d, 1.0E10d, 1.0E11d, 1.0E12d, 1.0E13d, 1.0E14d, 1.0E15d, 1.0E16d, 1.0E17d, 1.0E18d, 1.0E19d, 1.0E20d, 1.0E21d, 1.0E22d};
    private static final double[] bigtens = {1.0E16d, 1.0E32d, 1.0E64d, 1.0E128d, 1.0E256d};
    private static final int[] dtoaModes = {0, 0, 3, 2, 2};

    private static char BASEDIGIT(int i) {
        return (char) (i >= 10 ? i + 87 : i + 48);
    }

    private static int hi0bits(int i) {
        int i2;
        if (((-65536) & i) == 0) {
            i <<= 16;
            i2 = 16;
        } else {
            i2 = 0;
        }
        if (((-16777216) & i) == 0) {
            i2 += 8;
            i <<= 8;
        }
        if (((-268435456) & i) == 0) {
            i2 += 4;
            i <<= 4;
        }
        if (((-1073741824) & i) == 0) {
            i2 += 2;
            i <<= 2;
        }
        if ((Integer.MIN_VALUE & i) == 0) {
            i2++;
            if ((i & 1073741824) == 0) {
                return 32;
            }
        }
        return i2;
    }

    private static int lo0bits(int i) {
        int i2 = 0;
        if ((i & 7) != 0) {
            if ((i & 1) != 0) {
                return 0;
            }
            return (i & 2) != 0 ? 1 : 2;
        }
        if ((65535 & i) == 0) {
            i >>>= 16;
            i2 = 16;
        }
        if ((i & 255) == 0) {
            i2 += 8;
            i >>>= 8;
        }
        if ((i & 15) == 0) {
            i2 += 4;
            i >>>= 4;
        }
        if ((i & 3) == 0) {
            i2 += 2;
            i >>>= 2;
        }
        if ((i & 1) == 0) {
            i2++;
            if (((i >>> 1) & 1) == 0) {
                return 32;
            }
        }
        return i2;
    }

    DToA() {
    }

    private static void stuffBits(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 >> 24);
        bArr[i + 1] = (byte) (i2 >> 16);
        bArr[i + 2] = (byte) (i2 >> 8);
        bArr[i + 3] = (byte) i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0058  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static BigInteger d2b(double d, int[] iArr, int[] iArr2) {
        byte[] bArr;
        int i;
        int i2;
        long doubleToLongBits = Double.doubleToLongBits(d);
        int i3 = (int) (doubleToLongBits >>> 32);
        int i4 = (int) doubleToLongBits;
        int i5 = 1048575 & i3;
        int i6 = (i3 & Integer.MAX_VALUE) >>> 20;
        if (i6 != 0) {
            i5 |= 1048576;
        }
        if (i4 != 0) {
            bArr = new byte[8];
            i = lo0bits(i4);
            int i7 = i4 >>> i;
            if (i != 0) {
                stuffBits(bArr, 4, i7 | (i5 << (32 - i)));
                i5 >>= i;
            } else {
                stuffBits(bArr, 4, i7);
            }
            stuffBits(bArr, 0, i5);
            if (i5 != 0) {
                i2 = 2;
                if (i6 == 0) {
                    iArr[0] = ((i6 - 1023) - 52) + i;
                    iArr2[0] = 53 - i;
                } else {
                    iArr[0] = ((i6 - 1023) - 52) + 1 + i;
                    iArr2[0] = (i2 * 32) - hi0bits(i5);
                }
                return new BigInteger(bArr);
            }
        } else {
            bArr = new byte[4];
            int lo0bits = lo0bits(i5);
            i5 >>>= lo0bits;
            stuffBits(bArr, 0, i5);
            i = lo0bits + 32;
        }
        i2 = 1;
        if (i6 == 0) {
        }
        return new BigInteger(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0115, code lost:
    
        if (r7 > 0) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0121, code lost:
    
        if (r9 > 0) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0130, code lost:
    
        if (r6.compareTo(r3) > 0) goto L76;
     */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0142 A[LOOP:0: B:40:0x00d9->B:53:0x0142, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x013d A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String JS_dtobasestr(int i, double d) {
        boolean z;
        String bigInteger;
        BigInteger bigInteger2;
        if (2 > i || i > 36) {
            throw new IllegalArgumentException("Bad base: " + i);
        }
        if (Double.isNaN(d)) {
            return "NaN";
        }
        if (Double.isInfinite(d)) {
            return d > 0.0d ? "Infinity" : "-Infinity";
        }
        if (d == 0.0d) {
            return "0";
        }
        if (d >= 0.0d) {
            z = false;
        } else {
            d = -d;
            z = true;
        }
        double floor = Math.floor(d);
        long j = (long) floor;
        if (j == floor) {
            if (z) {
                j = -j;
            }
            bigInteger = Long.toString(j, i);
        } else {
            long doubleToLongBits = Double.doubleToLongBits(floor);
            int i2 = ((int) (doubleToLongBits >> 52)) & 2047;
            long j2 = i2 == 0 ? (doubleToLongBits & Frac_maskL) << 1 : (doubleToLongBits & Frac_maskL) | Exp_msk1L;
            if (z) {
                j2 = -j2;
            }
            int i3 = i2 - 1075;
            BigInteger valueOf = BigInteger.valueOf(j2);
            if (i3 > 0) {
                valueOf = valueOf.shiftLeft(i3);
            } else if (i3 < 0) {
                valueOf = valueOf.shiftRight(-i3);
            }
            bigInteger = valueOf.toString(i);
        }
        if (d == floor) {
            return bigInteger;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(bigInteger);
        sb.append(FilenameUtils.EXTENSION_SEPARATOR);
        double d2 = d - floor;
        long doubleToLongBits2 = Double.doubleToLongBits(d);
        int i4 = (int) (doubleToLongBits2 >> 32);
        int i5 = (int) doubleToLongBits2;
        int[] iArr = new int[1];
        BigInteger d2b = d2b(d2, iArr, new int[1]);
        int i6 = -((i4 >>> 20) & 2047);
        if (i6 == 0) {
            i6 = -1;
        }
        int i7 = i6 + 1076;
        BigInteger valueOf2 = BigInteger.valueOf(1L);
        if (i5 == 0 && (1048575 & i4) == 0 && (i4 & 2145386496) != 0) {
            i7++;
            bigInteger2 = BigInteger.valueOf(2L);
        } else {
            bigInteger2 = valueOf2;
        }
        BigInteger shiftLeft = d2b.shiftLeft(iArr[0] + i7);
        BigInteger shiftLeft2 = BigInteger.valueOf(1L).shiftLeft(i7);
        BigInteger valueOf3 = BigInteger.valueOf(i);
        boolean z2 = false;
        while (true) {
            BigInteger[] divideAndRemainder = shiftLeft.multiply(valueOf3).divideAndRemainder(shiftLeft2);
            BigInteger bigInteger3 = divideAndRemainder[1];
            int intValue = (char) divideAndRemainder[0].intValue();
            if (valueOf2 == bigInteger2) {
                bigInteger2 = valueOf2.multiply(valueOf3);
                valueOf2 = bigInteger2;
            } else {
                BigInteger multiply = valueOf2.multiply(valueOf3);
                bigInteger2 = bigInteger2.multiply(valueOf3);
                valueOf2 = multiply;
            }
            int compareTo = bigInteger3.compareTo(valueOf2);
            BigInteger subtract = shiftLeft2.subtract(bigInteger2);
            int compareTo2 = subtract.signum() <= 0 ? 1 : bigInteger3.compareTo(subtract);
            if (compareTo2 != 0 || (i5 & 1) != 0) {
                if (compareTo < 0 || (compareTo == 0 && (i5 & 1) == 0)) {
                    if (compareTo2 > 0) {
                        bigInteger3 = bigInteger3.shiftLeft(1);
                    }
                    z2 = true;
                    sb.append(BASEDIGIT(intValue));
                    if (!z2) {
                        return sb.toString();
                    }
                    shiftLeft = bigInteger3;
                }
                intValue++;
                z2 = true;
                sb.append(BASEDIGIT(intValue));
                if (!z2) {
                }
            }
        }
    }

    static int word0(double d) {
        return (int) (Double.doubleToLongBits(d) >> 32);
    }

    static double setWord0(double d, int i) {
        return Double.longBitsToDouble((Double.doubleToLongBits(d) & 4294967295L) | (i << 32));
    }

    static int word1(double d) {
        return (int) Double.doubleToLongBits(d);
    }

    static BigInteger pow5mult(BigInteger bigInteger, int i) {
        return bigInteger.multiply(BigInteger.valueOf(5L).pow(i));
    }

    static boolean roundOff(StringBuilder sb) {
        int length = sb.length();
        while (length != 0) {
            length--;
            char charAt = sb.charAt(length);
            if (charAt != '9') {
                sb.setCharAt(length, (char) (charAt + 1));
                sb.setLength(length + 1);
                return false;
            }
        }
        sb.setLength(0);
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:261:0x05f2, code lost:
    
        if (r43 == false) goto L375;
     */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x033a  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x03c8  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0411 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0415  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0417  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x041e  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0449  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0450  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x047b  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x048c  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x049d  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x04a2  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x04b2  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x04b8  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x04c0  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x04e1  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0514  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x063c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:297:0x064f  */
    /* JADX WARN: Removed duplicated region for block: B:325:0x061a  */
    /* JADX WARN: Removed duplicated region for block: B:332:0x04a9  */
    /* JADX WARN: Removed duplicated region for block: B:334:0x0495  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x043e  */
    /* JADX WARN: Removed duplicated region for block: B:348:0x0408  */
    /* JADX WARN: Removed duplicated region for block: B:349:0x031c  */
    /* JADX WARN: Removed duplicated region for block: B:387:0x030d  */
    /* JADX WARN: Removed duplicated region for block: B:388:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x023f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static int JS_dtoa(double d, int i, boolean z, int i2, boolean[] zArr, StringBuilder sb) {
        double d2;
        int[] iArr;
        long word1;
        double word0;
        int i3;
        boolean z2;
        boolean z3;
        int i4;
        int i5;
        int i6;
        int i7;
        boolean z4;
        int i8;
        int i9;
        boolean z5;
        int i10;
        BigInteger bigInteger;
        int i11;
        boolean z6;
        int i12;
        int[] iArr2;
        int i13;
        boolean z7;
        double d3;
        boolean z8;
        int i14;
        char c;
        double d4;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        boolean z9;
        int i20;
        int i21;
        int hi0bits;
        int i22;
        int i23;
        int i24;
        BigInteger bigInteger4;
        char intValue;
        char c2;
        int compareTo;
        int compareTo2;
        int i25;
        int i26;
        int i27;
        int compareTo3;
        int i28;
        int i29;
        int i30;
        char c3;
        double d5;
        int i31;
        int i32;
        boolean z10;
        boolean z11;
        int i33;
        int i34;
        char c4;
        char c5;
        double d6;
        int i35;
        boolean z12;
        int i36 = i;
        int[] iArr3 = new int[1];
        int[] iArr4 = new int[1];
        if ((word0(d) & Integer.MIN_VALUE) != 0) {
            zArr[0] = true;
            d2 = setWord0(d, word0(d) & Integer.MAX_VALUE);
        } else {
            d2 = d;
            zArr[0] = false;
        }
        if ((word0(d2) & Exp_mask) == Exp_mask) {
            sb.append((word1(d2) == 0 && (word0(d2) & 1048575) == 0) ? "Infinity" : "NaN");
            return HijrahDate.MAX_VALUE_OF_ERA;
        }
        if (d2 == 0.0d) {
            sb.setLength(0);
            sb.append('0');
            return 1;
        }
        BigInteger d2b = d2b(d2, iArr3, iArr4);
        int word02 = (word0(d2) >>> 20) & 2047;
        if (word02 != 0) {
            i3 = word02 - 1023;
            iArr = iArr3;
            z2 = false;
            word0 = setWord0(d2, (word0(d2) & 1048575) | Exp_11);
        } else {
            int i37 = iArr4[0] + iArr3[0] + 1074;
            if (i37 > 32) {
                iArr = iArr3;
                word1 = (word1(d2) >>> (i37 - 32)) | (word0(d2) << (64 - i37));
            } else {
                iArr = iArr3;
                word1 = word1(d2) << (32 - i37);
            }
            double d7 = word1;
            word0 = setWord0(d7, word0(d7) - 32505856);
            i3 = i37 - 1075;
            z2 = true;
        }
        double d8 = ((word0 - 1.5d) * 0.289529654602168d) + 0.1760912590558d + (i3 * 0.301029995663981d);
        int i38 = (int) d8;
        if (d8 < 0.0d && d8 != i38) {
            i38--;
        }
        if (i38 < 0 || i38 > 22) {
            z3 = true;
        } else {
            if (d2 < tens[i38]) {
                i38--;
            }
            z3 = false;
        }
        int i39 = (iArr4[0] - i3) - 1;
        if (i39 >= 0) {
            i5 = i39;
            i4 = 0;
        } else {
            i4 = -i39;
            i5 = 0;
        }
        if (i38 >= 0) {
            i5 += i38;
            i6 = 0;
            i7 = i38;
        } else {
            i4 -= i38;
            i6 = -i38;
            i7 = 0;
        }
        if (i36 < 0 || i36 > 9) {
            i36 = 0;
        }
        if (i36 > 5) {
            i36 -= 4;
            z4 = false;
        } else {
            z4 = true;
        }
        if (i36 != 0) {
            z5 = true;
            if (i36 != 1) {
                if (i36 != 2) {
                    if (i36 == 3) {
                        z12 = false;
                    } else if (i36 != 4) {
                        if (i36 != 5) {
                            i10 = i2;
                            bigInteger = d2b;
                            i8 = i5;
                            i11 = 0;
                            i9 = 0;
                            if (i11 >= 0 || i11 > 14 || !z4) {
                                z6 = z3;
                                i12 = i4;
                                iArr2 = iArr4;
                                i13 = i11;
                                z7 = z5;
                                d3 = d2;
                                z8 = z2;
                                i14 = i6;
                                c = 0;
                            } else {
                                if (i38 > 0) {
                                    double d9 = tens[i38 & 15];
                                    int i40 = i38 >> 4;
                                    if ((i40 & 16) != 0) {
                                        i40 &= 15;
                                        d6 = d2 / bigtens[4];
                                        i35 = 0;
                                        i31 = 3;
                                    } else {
                                        d6 = d2;
                                        i35 = 0;
                                        i31 = 2;
                                    }
                                    while (i40 != 0) {
                                        if ((i40 & 1) != 0) {
                                            i31++;
                                            d9 *= bigtens[i35];
                                        }
                                        i40 >>= 1;
                                        i35++;
                                    }
                                    d5 = d6 / d9;
                                } else {
                                    int i41 = -i38;
                                    if (i41 != 0) {
                                        double d10 = tens[i41 & 15] * d2;
                                        int i42 = i41 >> 4;
                                        d5 = d10;
                                        int i43 = 0;
                                        i31 = 2;
                                        while (i42 != 0) {
                                            if ((i42 & 1) != 0) {
                                                i31++;
                                                d5 *= bigtens[i43];
                                            }
                                            i42 >>= 1;
                                            i43++;
                                        }
                                    } else {
                                        d5 = d2;
                                        i31 = 2;
                                    }
                                }
                                if (!z3 || d5 >= 1.0d || i11 <= 0) {
                                    i12 = i4;
                                    d3 = d2;
                                    i32 = i38;
                                    z10 = false;
                                } else if (i9 <= 0) {
                                    i12 = i4;
                                    d3 = d2;
                                    i32 = i38;
                                    z10 = true;
                                } else {
                                    i32 = i38 - 1;
                                    d5 *= 10.0d;
                                    int i44 = i31 + 1;
                                    i12 = i4;
                                    d3 = d2;
                                    i33 = i9;
                                    z10 = false;
                                    z11 = z3;
                                    i13 = i11;
                                    i34 = i44;
                                    double d11 = (i34 * d5) + 7.0d;
                                    double word03 = setWord0(d11, word0(d11) - 54525952);
                                    if (i33 != 0) {
                                        d5 -= 5.0d;
                                        if (d5 > word03) {
                                            sb.append('1');
                                            return i32 + 1 + 1;
                                        }
                                        z7 = z5;
                                        if (d5 < (-word03)) {
                                            sb.setLength(0);
                                            sb.append('0');
                                            return 1;
                                        }
                                        z10 = true;
                                    } else {
                                        z7 = z5;
                                    }
                                    if (z10) {
                                        if (z7) {
                                            double d12 = (0.5d / tens[i33 - 1]) - word03;
                                            iArr2 = iArr4;
                                            i14 = i6;
                                            double d13 = d5;
                                            int i45 = 0;
                                            while (true) {
                                                z6 = z11;
                                                z8 = z2;
                                                d5 = d13 - ((long) d13);
                                                sb.append((char) (r3 + 48));
                                                if (d5 < d12) {
                                                    return i32 + 1;
                                                }
                                                if (1.0d - d5 < d12) {
                                                    while (true) {
                                                        char charAt = sb.charAt(sb.length() - 1);
                                                        sb.setLength(sb.length() - 1);
                                                        if (charAt != '9') {
                                                            c5 = charAt;
                                                            break;
                                                        }
                                                        if (sb.length() == 0) {
                                                            i32++;
                                                            c5 = '0';
                                                            break;
                                                        }
                                                    }
                                                    sb.append((char) (c5 + 1));
                                                    return i32 + 1;
                                                }
                                                i45++;
                                                if (i45 >= i33) {
                                                    break;
                                                }
                                                d12 *= 10.0d;
                                                d13 = d5 * 10.0d;
                                                z2 = z8;
                                                z11 = z6;
                                            }
                                        } else {
                                            iArr2 = iArr4;
                                            z6 = z11;
                                            z8 = z2;
                                            i14 = i6;
                                            double d14 = word03 * tens[i33 - 1];
                                            double d15 = d5;
                                            int i46 = 1;
                                            while (true) {
                                                d5 = d15 - ((long) d15);
                                                sb.append((char) (r13 + 48));
                                                if (i46 == i33) {
                                                    break;
                                                }
                                                i46++;
                                                d15 = d5 * 10.0d;
                                            }
                                            if (d5 <= d14 + 0.5d) {
                                                if (d5 < 0.5d - d14) {
                                                    stripTrailingZeroes(sb);
                                                    return i32 + 1;
                                                }
                                            }
                                            while (true) {
                                                char charAt2 = sb.charAt(sb.length() - 1);
                                                sb.setLength(sb.length() - 1);
                                                if (charAt2 != '9') {
                                                    c4 = charAt2;
                                                    break;
                                                }
                                                if (sb.length() == 0) {
                                                    i32++;
                                                    c4 = '0';
                                                    break;
                                                }
                                            }
                                            sb.append((char) (c4 + 1));
                                            return i32 + 1;
                                        }
                                        z10 = true;
                                    } else {
                                        iArr2 = iArr4;
                                        z6 = z11;
                                        z8 = z2;
                                        i14 = i6;
                                    }
                                    c = 0;
                                    if (!z10) {
                                        sb.setLength(0);
                                    } else {
                                        i15 = i33;
                                        i38 = i32;
                                        d4 = d5;
                                        if (iArr[c] < 0 && i38 <= 14) {
                                            double d16 = tens[i38];
                                            if (i10 < 0 && i15 <= 0) {
                                                if (i15 >= 0) {
                                                    double d17 = d16 * 5.0d;
                                                    if (d4 >= d17 && (z || d4 != d17)) {
                                                        sb.append('1');
                                                        return i38 + 1 + 1;
                                                    }
                                                }
                                                sb.setLength(0);
                                                sb.append('0');
                                                return 1;
                                            }
                                            int i47 = 1;
                                            while (true) {
                                                long j = (long) (d4 / d16);
                                                double d18 = d4 - (j * d16);
                                                sb.append((char) (j + 48));
                                                if (i47 == i15) {
                                                    double d19 = d18 + d18;
                                                    if (d19 > d16 || (d19 == d16 && ((1 & j) != 0 || z))) {
                                                        while (true) {
                                                            i30 = 1;
                                                            char charAt3 = sb.charAt(sb.length() - 1);
                                                            sb.setLength(sb.length() - 1);
                                                            if (charAt3 != '9') {
                                                                c3 = charAt3;
                                                                break;
                                                            }
                                                            if (sb.length() == 0) {
                                                                i38++;
                                                                c3 = '0';
                                                                break;
                                                            }
                                                        }
                                                        sb.append((char) (c3 + 1));
                                                    } else {
                                                        i30 = 1;
                                                    }
                                                } else {
                                                    i30 = 1;
                                                    d4 = d18 * 10.0d;
                                                    if (d4 == 0.0d) {
                                                        break;
                                                    }
                                                    i47++;
                                                }
                                            }
                                            return i38 + i30;
                                        }
                                        BigInteger bigInteger5 = null;
                                        if (z7) {
                                            if (i36 < 2) {
                                                i29 = z8 ? iArr[0] + 1075 : 54 - iArr2[0];
                                                i19 = i12;
                                                i16 = i14;
                                            } else {
                                                int i48 = i15 - 1;
                                                int i49 = i14;
                                                if (i49 >= i48) {
                                                    i28 = i49 - i48;
                                                } else {
                                                    int i50 = i48 - i49;
                                                    i7 += i50;
                                                    i49 += i50;
                                                    i28 = 0;
                                                }
                                                if (i15 < 0) {
                                                    i19 = i12 - i15;
                                                    i14 = i49;
                                                    i16 = i28;
                                                    i29 = 0;
                                                } else {
                                                    i14 = i49;
                                                    i19 = i12;
                                                    i16 = i28;
                                                    i29 = i15;
                                                }
                                            }
                                            int i51 = i12 + i29;
                                            i18 = i8 + i29;
                                            bigInteger5 = BigInteger.valueOf(1L);
                                            i12 = i51;
                                            i17 = i14;
                                        } else {
                                            i16 = i14;
                                            i17 = i16;
                                            i18 = i8;
                                            i19 = i12;
                                        }
                                        if (i19 > 0 && i18 > 0) {
                                            int i52 = i19 >= i18 ? i19 : i18;
                                            i12 -= i52;
                                            i19 -= i52;
                                            i18 -= i52;
                                        }
                                        if (i17 > 0) {
                                            if (z7) {
                                                if (i16 > 0) {
                                                    bigInteger5 = pow5mult(bigInteger5, i16);
                                                    bigInteger2 = bigInteger5.multiply(bigInteger);
                                                } else {
                                                    bigInteger2 = bigInteger;
                                                }
                                                int i53 = i17 - i16;
                                                if (i53 != 0) {
                                                    bigInteger3 = pow5mult(bigInteger2, i53);
                                                }
                                            } else {
                                                bigInteger3 = pow5mult(bigInteger, i17);
                                            }
                                            BigInteger valueOf = BigInteger.valueOf(1L);
                                            if (i7 > 0) {
                                                valueOf = pow5mult(valueOf, i7);
                                            }
                                            if (i36 < 2 || word1(d4) != 0 || (word0(d4) & 1048575) != 0 || (word0(d4) & 2145386496) == 0) {
                                                z9 = false;
                                            } else {
                                                i12++;
                                                i18++;
                                                z9 = true;
                                            }
                                            byte[] byteArray = valueOf.toByteArray();
                                            int i54 = i15;
                                            i21 = 0;
                                            int i55 = 0;
                                            for (i20 = 4; i21 < i20; i20 = 4) {
                                                int i56 = i55 << 8;
                                                if (i21 < byteArray.length) {
                                                    i56 |= byteArray[i21] & 255;
                                                }
                                                i55 = i56;
                                                i21++;
                                            }
                                            hi0bits = ((i7 == 0 ? 32 - hi0bits(i55) : 1) + i18) & 31;
                                            if (hi0bits != 0) {
                                                hi0bits = 32 - hi0bits;
                                            }
                                            if (hi0bits > 4) {
                                                if (hi0bits < 4) {
                                                    i22 = hi0bits + 28;
                                                }
                                                i23 = i12;
                                                if (i23 > 0) {
                                                    bigInteger3 = bigInteger3.shiftLeft(i23);
                                                }
                                                if (i18 > 0) {
                                                    valueOf = valueOf.shiftLeft(i18);
                                                }
                                                if (z6 || bigInteger3.compareTo(valueOf) >= 0) {
                                                    i24 = i54;
                                                } else {
                                                    i38--;
                                                    bigInteger3 = bigInteger3.multiply(BigInteger.valueOf(10L));
                                                    if (z7) {
                                                        bigInteger5 = bigInteger5.multiply(BigInteger.valueOf(10L));
                                                    }
                                                    i24 = i9;
                                                }
                                                if (i24 > 0 && i36 > 2) {
                                                    if (i24 < 0 || (compareTo3 = bigInteger3.compareTo(valueOf.multiply(BigInteger.valueOf(5L)))) < 0) {
                                                        i26 = 1;
                                                        i27 = 0;
                                                    } else {
                                                        if (compareTo3 != 0 || z) {
                                                            sb.append('1');
                                                            return i38 + 1 + 1;
                                                        }
                                                        i27 = 0;
                                                        i26 = 1;
                                                    }
                                                    sb.setLength(i27);
                                                    sb.append('0');
                                                    return i26;
                                                }
                                                int i57 = 1;
                                                if (z7) {
                                                    if (i19 > 0) {
                                                        bigInteger5 = bigInteger5.shiftLeft(i19);
                                                    }
                                                    BigInteger shiftLeft = z9 ? bigInteger5.shiftLeft(1) : bigInteger5;
                                                    int i58 = 1;
                                                    while (true) {
                                                        BigInteger[] divideAndRemainder = bigInteger3.divideAndRemainder(valueOf);
                                                        bigInteger4 = divideAndRemainder[i57];
                                                        c2 = (char) (divideAndRemainder[0].intValue() + 48);
                                                        int compareTo4 = bigInteger4.compareTo(bigInteger5);
                                                        BigInteger subtract = valueOf.subtract(shiftLeft);
                                                        compareTo2 = subtract.signum() <= 0 ? 1 : bigInteger4.compareTo(subtract);
                                                        if (compareTo2 == 0 && i36 == 0) {
                                                            if ((word1(d4) & 1) == 0) {
                                                                if (c2 == '9') {
                                                                    sb.append('9');
                                                                    if (roundOff(sb)) {
                                                                        i38++;
                                                                        sb.append('1');
                                                                    }
                                                                    return i38 + 1;
                                                                }
                                                                if (compareTo4 > 0) {
                                                                    c2 = (char) (c2 + 1);
                                                                }
                                                                sb.append(c2);
                                                                return i38 + 1;
                                                            }
                                                        }
                                                        if (compareTo4 < 0 || (compareTo4 == 0 && i36 == 0 && (word1(d4) & 1) == 0)) {
                                                            break;
                                                        }
                                                        if (compareTo2 > 0) {
                                                            if (c2 == '9') {
                                                                sb.append('9');
                                                                if (roundOff(sb)) {
                                                                    i38++;
                                                                    sb.append('1');
                                                                }
                                                                return i38 + 1;
                                                            }
                                                            sb.append((char) (c2 + 1));
                                                            return i38 + 1;
                                                        }
                                                        sb.append(c2);
                                                        if (i58 == i24) {
                                                            i57 = 1;
                                                            break;
                                                        }
                                                        bigInteger3 = bigInteger4.multiply(BigInteger.valueOf(10L));
                                                        if (bigInteger5 == shiftLeft) {
                                                            bigInteger5 = shiftLeft.multiply(BigInteger.valueOf(10L));
                                                            shiftLeft = bigInteger5;
                                                        } else {
                                                            bigInteger5 = bigInteger5.multiply(BigInteger.valueOf(10L));
                                                            shiftLeft = shiftLeft.multiply(BigInteger.valueOf(10L));
                                                        }
                                                        i58++;
                                                        i57 = 1;
                                                    }
                                                    if (compareTo2 > 0) {
                                                        int compareTo5 = bigInteger4.shiftLeft(1).compareTo(valueOf);
                                                        if (compareTo5 <= 0) {
                                                            if (compareTo5 != 0) {
                                                                i25 = 1;
                                                                sb.append(c2);
                                                                return i38 + i25;
                                                            }
                                                            if ((c2 & 1) != 1) {
                                                            }
                                                        }
                                                        char c6 = (char) (c2 + 1);
                                                        if (c2 == '9') {
                                                            sb.append('9');
                                                            if (roundOff(sb)) {
                                                                i38++;
                                                                sb.append('1');
                                                            }
                                                            return i38 + 1;
                                                        }
                                                        i25 = 1;
                                                        c2 = c6;
                                                        sb.append(c2);
                                                        return i38 + i25;
                                                    }
                                                    i25 = 1;
                                                    sb.append(c2);
                                                    return i38 + i25;
                                                }
                                                int i59 = 1;
                                                while (true) {
                                                    BigInteger[] divideAndRemainder2 = bigInteger3.divideAndRemainder(valueOf);
                                                    bigInteger4 = divideAndRemainder2[1];
                                                    intValue = (char) (divideAndRemainder2[0].intValue() + 48);
                                                    sb.append(intValue);
                                                    if (i59 >= i24) {
                                                        break;
                                                    }
                                                    i59++;
                                                    bigInteger3 = bigInteger4.multiply(BigInteger.valueOf(10L));
                                                }
                                                c2 = intValue;
                                                compareTo = bigInteger4.shiftLeft(i57).compareTo(valueOf);
                                                if (compareTo <= 0 || (compareTo == 0 && ((c2 & 1) == i57 || z))) {
                                                    if (roundOff(sb)) {
                                                        sb.append('1');
                                                        return i38 + i57 + i57;
                                                    }
                                                } else {
                                                    stripTrailingZeroes(sb);
                                                }
                                                return i38 + i57;
                                            }
                                            i22 = hi0bits - 4;
                                            i12 += i22;
                                            i19 += i22;
                                            i18 += i22;
                                            i23 = i12;
                                            if (i23 > 0) {
                                            }
                                            if (i18 > 0) {
                                            }
                                            if (z6) {
                                            }
                                            i24 = i54;
                                            if (i24 > 0) {
                                            }
                                            int i572 = 1;
                                            if (z7) {
                                            }
                                            compareTo = bigInteger4.shiftLeft(i572).compareTo(valueOf);
                                            if (compareTo <= 0) {
                                            }
                                            if (roundOff(sb)) {
                                            }
                                            return i38 + i572;
                                        }
                                        bigInteger2 = bigInteger;
                                        bigInteger3 = bigInteger2;
                                        BigInteger valueOf2 = BigInteger.valueOf(1L);
                                        if (i7 > 0) {
                                        }
                                        if (i36 < 2) {
                                        }
                                        z9 = false;
                                        byte[] byteArray2 = valueOf2.toByteArray();
                                        int i542 = i15;
                                        i21 = 0;
                                        int i552 = 0;
                                        while (i21 < i20) {
                                        }
                                        hi0bits = ((i7 == 0 ? 32 - hi0bits(i552) : 1) + i18) & 31;
                                        if (hi0bits != 0) {
                                        }
                                        if (hi0bits > 4) {
                                        }
                                        i12 += i22;
                                        i19 += i22;
                                        i18 += i22;
                                        i23 = i12;
                                        if (i23 > 0) {
                                        }
                                        if (i18 > 0) {
                                        }
                                        if (z6) {
                                        }
                                        i24 = i542;
                                        if (i24 > 0) {
                                        }
                                        int i5722 = 1;
                                        if (z7) {
                                        }
                                        compareTo = bigInteger4.shiftLeft(i5722).compareTo(valueOf2);
                                        if (compareTo <= 0) {
                                        }
                                        if (roundOff(sb)) {
                                        }
                                        return i38 + i5722;
                                    }
                                }
                                z11 = z3;
                                i33 = i11;
                                i34 = i31;
                                i13 = i33;
                                double d112 = (i34 * d5) + 7.0d;
                                double word032 = setWord0(d112, word0(d112) - 54525952);
                                if (i33 != 0) {
                                }
                                if (z10) {
                                }
                                c = 0;
                                if (!z10) {
                                }
                            }
                            i15 = i13;
                            d4 = d3;
                            if (iArr[c] < 0) {
                            }
                            BigInteger bigInteger52 = null;
                            if (z7) {
                            }
                            if (i19 > 0) {
                                if (i19 >= i18) {
                                }
                                i12 -= i52;
                                i19 -= i52;
                                i18 -= i52;
                            }
                            if (i17 > 0) {
                            }
                            bigInteger3 = bigInteger2;
                            BigInteger valueOf22 = BigInteger.valueOf(1L);
                            if (i7 > 0) {
                            }
                            if (i36 < 2) {
                            }
                            z9 = false;
                            byte[] byteArray22 = valueOf22.toByteArray();
                            int i5422 = i15;
                            i21 = 0;
                            int i5522 = 0;
                            while (i21 < i20) {
                            }
                            hi0bits = ((i7 == 0 ? 32 - hi0bits(i5522) : 1) + i18) & 31;
                            if (hi0bits != 0) {
                            }
                            if (hi0bits > 4) {
                            }
                            i12 += i22;
                            i19 += i22;
                            i18 += i22;
                            i23 = i12;
                            if (i23 > 0) {
                            }
                            if (i18 > 0) {
                            }
                            if (z6) {
                            }
                            i24 = i5422;
                            if (i24 > 0) {
                            }
                            int i57222 = 1;
                            if (z7) {
                            }
                            compareTo = bigInteger4.shiftLeft(i57222).compareTo(valueOf22);
                            if (compareTo <= 0) {
                            }
                            if (roundOff(sb)) {
                            }
                            return i38 + i57222;
                        }
                        z12 = true;
                    }
                    int i60 = i2 + i38 + 1;
                    i9 = i60 - 1;
                    z5 = z12;
                    i8 = i5;
                    i10 = i2;
                    bigInteger = d2b;
                    i11 = i60;
                    if (i11 >= 0) {
                    }
                    z6 = z3;
                    i12 = i4;
                    iArr2 = iArr4;
                    i13 = i11;
                    z7 = z5;
                    d3 = d2;
                    z8 = z2;
                    i14 = i6;
                    c = 0;
                    i15 = i13;
                    d4 = d3;
                    if (iArr[c] < 0) {
                    }
                    BigInteger bigInteger522 = null;
                    if (z7) {
                    }
                    if (i19 > 0) {
                    }
                    if (i17 > 0) {
                    }
                    bigInteger3 = bigInteger2;
                    BigInteger valueOf222 = BigInteger.valueOf(1L);
                    if (i7 > 0) {
                    }
                    if (i36 < 2) {
                    }
                    z9 = false;
                    byte[] byteArray222 = valueOf222.toByteArray();
                    int i54222 = i15;
                    i21 = 0;
                    int i55222 = 0;
                    while (i21 < i20) {
                    }
                    hi0bits = ((i7 == 0 ? 32 - hi0bits(i55222) : 1) + i18) & 31;
                    if (hi0bits != 0) {
                    }
                    if (hi0bits > 4) {
                    }
                    i12 += i22;
                    i19 += i22;
                    i18 += i22;
                    i23 = i12;
                    if (i23 > 0) {
                    }
                    if (i18 > 0) {
                    }
                    if (z6) {
                    }
                    i24 = i54222;
                    if (i24 > 0) {
                    }
                    int i572222 = 1;
                    if (z7) {
                    }
                    compareTo = bigInteger4.shiftLeft(i572222).compareTo(valueOf222);
                    if (compareTo <= 0) {
                    }
                    if (roundOff(sb)) {
                    }
                    return i38 + i572222;
                }
                z5 = false;
                i10 = i2 <= 0 ? 1 : i2;
                bigInteger = d2b;
                i11 = i10;
                i9 = i11;
                i8 = i5;
                if (i11 >= 0) {
                }
                z6 = z3;
                i12 = i4;
                iArr2 = iArr4;
                i13 = i11;
                z7 = z5;
                d3 = d2;
                z8 = z2;
                i14 = i6;
                c = 0;
                i15 = i13;
                d4 = d3;
                if (iArr[c] < 0) {
                }
                BigInteger bigInteger5222 = null;
                if (z7) {
                }
                if (i19 > 0) {
                }
                if (i17 > 0) {
                }
                bigInteger3 = bigInteger2;
                BigInteger valueOf2222 = BigInteger.valueOf(1L);
                if (i7 > 0) {
                }
                if (i36 < 2) {
                }
                z9 = false;
                byte[] byteArray2222 = valueOf2222.toByteArray();
                int i542222 = i15;
                i21 = 0;
                int i552222 = 0;
                while (i21 < i20) {
                }
                hi0bits = ((i7 == 0 ? 32 - hi0bits(i552222) : 1) + i18) & 31;
                if (hi0bits != 0) {
                }
                if (hi0bits > 4) {
                }
                i12 += i22;
                i19 += i22;
                i18 += i22;
                i23 = i12;
                if (i23 > 0) {
                }
                if (i18 > 0) {
                }
                if (z6) {
                }
                i24 = i542222;
                if (i24 > 0) {
                }
                int i5722222 = 1;
                if (z7) {
                }
                compareTo = bigInteger4.shiftLeft(i5722222).compareTo(valueOf2222);
                if (compareTo <= 0) {
                }
                if (roundOff(sb)) {
                }
                return i38 + i5722222;
            }
        }
        i8 = i5;
        i9 = -1;
        z5 = true;
        i10 = 0;
        bigInteger = d2b;
        i11 = -1;
        if (i11 >= 0) {
        }
        z6 = z3;
        i12 = i4;
        iArr2 = iArr4;
        i13 = i11;
        z7 = z5;
        d3 = d2;
        z8 = z2;
        i14 = i6;
        c = 0;
        i15 = i13;
        d4 = d3;
        if (iArr[c] < 0) {
        }
        BigInteger bigInteger52222 = null;
        if (z7) {
        }
        if (i19 > 0) {
        }
        if (i17 > 0) {
        }
        bigInteger3 = bigInteger2;
        BigInteger valueOf22222 = BigInteger.valueOf(1L);
        if (i7 > 0) {
        }
        if (i36 < 2) {
        }
        z9 = false;
        byte[] byteArray22222 = valueOf22222.toByteArray();
        int i5422222 = i15;
        i21 = 0;
        int i5522222 = 0;
        while (i21 < i20) {
        }
        hi0bits = ((i7 == 0 ? 32 - hi0bits(i5522222) : 1) + i18) & 31;
        if (hi0bits != 0) {
        }
        if (hi0bits > 4) {
        }
        i12 += i22;
        i19 += i22;
        i18 += i22;
        i23 = i12;
        if (i23 > 0) {
        }
        if (i18 > 0) {
        }
        if (z6) {
        }
        i24 = i5422222;
        if (i24 > 0) {
        }
        int i57222222 = 1;
        if (z7) {
        }
        compareTo = bigInteger4.shiftLeft(i57222222).compareTo(valueOf22222);
        if (compareTo <= 0) {
        }
        if (roundOff(sb)) {
        }
        return i38 + i57222222;
    }

    private static void stripTrailingZeroes(StringBuilder sb) {
        int i;
        int length = sb.length();
        while (true) {
            i = length - 1;
            if (length <= 0 || sb.charAt(i) != '0') {
                break;
            } else {
                length = i;
            }
        }
        sb.setLength(i + 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x005d, code lost:
    
        if (r2 < r13) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x005f, code lost:
    
        r11.append('0');
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0066, code lost:
    
        if (r11.length() != r13) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0068, code lost:
    
        r2 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x006b, code lost:
    
        if (r12 == false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x006d, code lost:
    
        if (r2 == 1) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x006f, code lost:
    
        r11.insert(1, org.apache.commons.io.FilenameUtils.EXTENSION_SEPARATOR);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0072, code lost:
    
        r11.append('e');
        r1 = r1 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0078, code lost:
    
        if (r1 < 0) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x007a, code lost:
    
        r11.append('+');
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x007f, code lost:
    
        r11.append(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0083, code lost:
    
        if (r1 == r2) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0085, code lost:
    
        if (r1 <= 0) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0087, code lost:
    
        r11.insert(r1, org.apache.commons.io.FilenameUtils.EXTENSION_SEPARATOR);
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x008b, code lost:
    
        r12 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x008e, code lost:
    
        if (r12 >= (1 - r1)) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0090, code lost:
    
        r11.insert(0, '0');
        r12 = r12 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0096, code lost:
    
        r11.insert(1, org.apache.commons.io.FilenameUtils.EXTENSION_SEPARATOR);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0045, code lost:
    
        if (r1 <= r13) goto L36;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void JS_dtostr(StringBuilder sb, int i, int i2, double d) {
        boolean z;
        boolean[] zArr = new boolean[1];
        if (i == 2 && (d >= 1.0E21d || d <= -1.0E21d)) {
            i = 0;
        }
        int JS_dtoa = JS_dtoa(d, dtoaModes[i], i >= 2, i2, zArr, sb);
        int length = sb.length();
        if (JS_dtoa != 9999) {
            if (i != 0) {
                if (i != 1) {
                    if (i == 2) {
                        if (i2 >= 0) {
                            i2 += JS_dtoa;
                        }
                        i2 = JS_dtoa;
                    } else if (i != 3) {
                        if (i != 4) {
                            z = false;
                            i2 = 0;
                        } else if (JS_dtoa >= -5) {
                        }
                    }
                    z = false;
                } else {
                    i2 = 0;
                }
                z = true;
            } else {
                if (JS_dtoa < -5 || JS_dtoa > 21) {
                    z = true;
                    i2 = 0;
                }
                i2 = JS_dtoa;
                z = false;
            }
        }
        if (zArr[0]) {
            if (word0(d) == Integer.MIN_VALUE && word1(d) == 0) {
                return;
            }
            if ((word0(d) & Exp_mask) != Exp_mask || (word1(d) == 0 && (word0(d) & 1048575) == 0)) {
                sb.insert(0, Soundex.SILENT_MARKER);
            }
        }
    }
}
