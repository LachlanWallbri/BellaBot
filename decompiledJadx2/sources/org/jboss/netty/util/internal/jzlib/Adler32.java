package org.jboss.netty.util.internal.jzlib;

/* loaded from: classes7.dex */
final class Adler32 {
    private static final int BASE = 65521;
    private static final int NMAX = 5552;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long adler32(long j, byte[] bArr, int i, int i2) {
        if (bArr == null) {
            return 1L;
        }
        long j2 = j & 65535;
        long j3 = (j >> 16) & 65535;
        while (i2 > 0) {
            int i3 = NMAX;
            if (i2 < NMAX) {
                i3 = i2;
            }
            i2 -= i3;
            while (i3 >= 16) {
                long j4 = j2 + (bArr[i] & 255);
                long j5 = j3 + j4;
                long j6 = j4 + (bArr[r1] & 255);
                long j7 = j5 + j6;
                long j8 = j6 + (bArr[r10] & 255);
                long j9 = j7 + j8;
                long j10 = j8 + (bArr[r1] & 255);
                long j11 = j9 + j10;
                long j12 = j10 + (bArr[r10] & 255);
                long j13 = j11 + j12;
                long j14 = j12 + (bArr[r1] & 255);
                long j15 = j13 + j14;
                long j16 = j14 + (bArr[r10] & 255);
                long j17 = j15 + j16;
                long j18 = j16 + (bArr[r1] & 255);
                long j19 = j17 + j18;
                long j20 = j18 + (bArr[r10] & 255);
                long j21 = j19 + j20;
                long j22 = j20 + (bArr[r1] & 255);
                long j23 = j21 + j22;
                long j24 = j22 + (bArr[r10] & 255);
                long j25 = j23 + j24;
                long j26 = j24 + (bArr[r1] & 255);
                long j27 = j25 + j26;
                long j28 = j26 + (bArr[r10] & 255);
                long j29 = j27 + j28;
                long j30 = j28 + (bArr[r1] & 255);
                long j31 = j29 + j30;
                long j32 = j30 + (bArr[r10] & 255);
                long j33 = j31 + j32;
                i = i + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1;
                j2 = j32 + (bArr[r1] & 255);
                j3 = j33 + j2;
                i3 -= 16;
            }
            if (i3 == 0) {
                j2 %= 65521;
                j3 %= 65521;
            }
            do {
                j2 += bArr[i] & 255;
                j3 += j2;
                i3--;
                i++;
            } while (i3 != 0);
            j2 %= 65521;
            j3 %= 65521;
        }
        return (j3 << 16) | j2;
    }

    private Adler32() {
    }
}
