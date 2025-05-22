package org.mozilla.javascript.v8dtoa;

import org.apache.commons.codec.language.Soundex;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class FastDtoa {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int kFastDtoaMaximalLength = 17;
    static final int kTen4 = 10000;
    static final int kTen5 = 100000;
    static final int kTen6 = 1000000;
    static final int kTen7 = 10000000;
    static final int kTen8 = 100000000;
    static final int kTen9 = 1000000000;
    static final int maximal_target_exponent = -32;
    static final int minimal_target_exponent = -60;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static long biggestPowerTen(int i, int i2) {
        int i3 = 1000000000;
        int i4 = 1;
        switch (i2) {
            case 0:
                i4 = -1;
                i3 = 0;
                break;
            case 1:
            case 2:
            case 3:
                if (1 <= i) {
                    i3 = 1;
                    i4 = 0;
                    break;
                }
                i4 = -1;
                i3 = 0;
                break;
            case 4:
            case 5:
            case 6:
                if (10 <= i) {
                    i3 = 10;
                    break;
                }
                if (1 <= i) {
                }
                i4 = -1;
                i3 = 0;
                break;
            case 7:
            case 8:
            case 9:
                if (100 <= i) {
                    i4 = 2;
                    i3 = 100;
                    break;
                }
                if (10 <= i) {
                }
                if (1 <= i) {
                }
                i4 = -1;
                i3 = 0;
                break;
            case 10:
            case 11:
            case 12:
            case 13:
                if (1000 <= i) {
                    i4 = 3;
                    i3 = 1000;
                    break;
                }
                if (100 <= i) {
                }
                if (10 <= i) {
                }
                if (1 <= i) {
                }
                i4 = -1;
                i3 = 0;
                break;
            case 14:
            case 15:
            case 16:
                if (10000 <= i) {
                    i4 = 4;
                    i3 = 10000;
                    break;
                }
                if (1000 <= i) {
                }
                if (100 <= i) {
                }
                if (10 <= i) {
                }
                if (1 <= i) {
                }
                i4 = -1;
                i3 = 0;
                break;
            case 17:
            case 18:
            case 19:
                if (100000 <= i) {
                    i4 = 5;
                    i3 = 100000;
                    break;
                }
                if (10000 <= i) {
                }
                if (1000 <= i) {
                }
                if (100 <= i) {
                }
                if (10 <= i) {
                }
                if (1 <= i) {
                }
                i4 = -1;
                i3 = 0;
                break;
            case 20:
            case 21:
            case 22:
            case 23:
                if (kTen6 <= i) {
                    i4 = 6;
                    i3 = kTen6;
                    break;
                }
                if (100000 <= i) {
                }
                if (10000 <= i) {
                }
                if (1000 <= i) {
                }
                if (100 <= i) {
                }
                if (10 <= i) {
                }
                if (1 <= i) {
                }
                i4 = -1;
                i3 = 0;
                break;
            case 24:
            case 25:
            case 26:
                if (kTen7 <= i) {
                    i4 = 7;
                    i3 = kTen7;
                    break;
                }
                if (kTen6 <= i) {
                }
                if (100000 <= i) {
                }
                if (10000 <= i) {
                }
                if (1000 <= i) {
                }
                if (100 <= i) {
                }
                if (10 <= i) {
                }
                if (1 <= i) {
                }
                i4 = -1;
                i3 = 0;
                break;
            case 27:
            case 28:
            case 29:
                if (kTen8 <= i) {
                    i4 = 8;
                    i3 = kTen8;
                    break;
                }
                if (kTen7 <= i) {
                }
                if (kTen6 <= i) {
                }
                if (100000 <= i) {
                }
                if (10000 <= i) {
                }
                if (1000 <= i) {
                }
                if (100 <= i) {
                }
                if (10 <= i) {
                }
                if (1 <= i) {
                }
                i4 = -1;
                i3 = 0;
                break;
            case 30:
            case 31:
            case 32:
                if (1000000000 <= i) {
                    i4 = 9;
                    break;
                }
                if (kTen8 <= i) {
                }
                if (kTen7 <= i) {
                }
                if (kTen6 <= i) {
                }
                if (100000 <= i) {
                }
                if (10000 <= i) {
                }
                if (1000 <= i) {
                }
                if (100 <= i) {
                }
                if (10 <= i) {
                }
                if (1 <= i) {
                }
                i4 = -1;
                i3 = 0;
                break;
            default:
                i3 = 0;
                i4 = 0;
                break;
        }
        return (i3 << 32) | (4294967295L & i4);
    }

    private static boolean uint64_lte(long j, long j2) {
        if (j != j2) {
            if (!(((j < 0) ^ (j < j2)) ^ (j2 < 0))) {
                return false;
            }
        }
        return true;
    }

    static boolean roundWeed(FastDtoaBuilder fastDtoaBuilder, long j, long j2, long j3, long j4, long j5) {
        long j6 = j - j5;
        long j7 = j + j5;
        long j8 = j3;
        while (j8 < j6 && j2 - j8 >= j4) {
            long j9 = j8 + j4;
            if (j9 >= j6 && j6 - j8 < j9 - j6) {
                break;
            }
            fastDtoaBuilder.decreaseLast();
            j8 = j9;
        }
        if (j8 < j7 && j2 - j8 >= j4) {
            long j10 = j8 + j4;
            if (j10 < j7 || j7 - j8 > j10 - j7) {
                return false;
            }
        }
        return 2 * j5 <= j8 && j8 <= j2 - (4 * j5);
    }

    static boolean digitGen(DiyFp diyFp, DiyFp diyFp2, DiyFp diyFp3, FastDtoaBuilder fastDtoaBuilder, int i) {
        DiyFp diyFp4 = new DiyFp(diyFp.m4194f() - 1, diyFp.m4193e());
        DiyFp diyFp5 = new DiyFp(diyFp3.m4194f() + 1, diyFp3.m4193e());
        DiyFp minus = DiyFp.minus(diyFp5, diyFp4);
        DiyFp diyFp6 = new DiyFp(1 << (-diyFp2.m4193e()), diyFp2.m4193e());
        int m4194f = (int) ((diyFp5.m4194f() >>> (-diyFp6.m4193e())) & 4294967295L);
        long m4194f2 = diyFp5.m4194f() & (diyFp6.m4194f() - 1);
        long biggestPowerTen = biggestPowerTen(m4194f, 64 - (-diyFp6.m4193e()));
        int i2 = (int) ((biggestPowerTen >>> 32) & 4294967295L);
        int i3 = ((int) (biggestPowerTen & 4294967295L)) + 1;
        while (i3 > 0) {
            fastDtoaBuilder.append((char) ((m4194f / i2) + 48));
            m4194f %= i2;
            i3--;
            long j = (m4194f << (-diyFp6.m4193e())) + m4194f2;
            if (j < minus.m4194f()) {
                fastDtoaBuilder.point = (fastDtoaBuilder.end - i) + i3;
                return roundWeed(fastDtoaBuilder, DiyFp.minus(diyFp5, diyFp2).m4194f(), minus.m4194f(), j, i2 << (-diyFp6.m4193e()), 1L);
            }
            i2 /= 10;
        }
        long j2 = 1;
        do {
            long j3 = m4194f2 * 5;
            j2 *= 5;
            minus.setF(minus.m4194f() * 5);
            minus.setE(minus.m4193e() + 1);
            diyFp6.setF(diyFp6.m4194f() >>> 1);
            diyFp6.setE(diyFp6.m4193e() + 1);
            fastDtoaBuilder.append((char) (((int) ((j3 >>> (-diyFp6.m4193e())) & 4294967295L)) + 48));
            m4194f2 = j3 & (diyFp6.m4194f() - 1);
            i3--;
        } while (m4194f2 >= minus.m4194f());
        fastDtoaBuilder.point = (fastDtoaBuilder.end - i) + i3;
        return roundWeed(fastDtoaBuilder, DiyFp.minus(diyFp5, diyFp2).m4194f() * j2, minus.m4194f(), m4194f2, diyFp6.m4194f(), j2);
    }

    static boolean grisu3(double d, FastDtoaBuilder fastDtoaBuilder) {
        long doubleToLongBits = Double.doubleToLongBits(d);
        DiyFp asNormalizedDiyFp = DoubleHelper.asNormalizedDiyFp(doubleToLongBits);
        DiyFp diyFp = new DiyFp();
        DiyFp diyFp2 = new DiyFp();
        DoubleHelper.normalizedBoundaries(doubleToLongBits, diyFp, diyFp2);
        DiyFp diyFp3 = new DiyFp();
        int cachedPower = CachedPowers.getCachedPower(asNormalizedDiyFp.m4193e() + 64, minimal_target_exponent, maximal_target_exponent, diyFp3);
        return digitGen(DiyFp.times(diyFp, diyFp3), DiyFp.times(asNormalizedDiyFp, diyFp3), DiyFp.times(diyFp2, diyFp3), fastDtoaBuilder, cachedPower);
    }

    public static boolean dtoa(double d, FastDtoaBuilder fastDtoaBuilder) {
        return grisu3(d, fastDtoaBuilder);
    }

    public static String numberToString(double d) {
        FastDtoaBuilder fastDtoaBuilder = new FastDtoaBuilder();
        if (numberToString(d, fastDtoaBuilder)) {
            return fastDtoaBuilder.format();
        }
        return null;
    }

    public static boolean numberToString(double d, FastDtoaBuilder fastDtoaBuilder) {
        fastDtoaBuilder.reset();
        if (d < 0.0d) {
            fastDtoaBuilder.append(Soundex.SILENT_MARKER);
            d = -d;
        }
        return dtoa(d, fastDtoaBuilder);
    }
}
