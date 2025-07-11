package org.apache.commons.compress.compressors.bzip2;

import android.support.v4.media.session.PlaybackStateCompat;
import java.util.BitSet;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes8.dex */
public class BlockSort {
    private static final int CLEARMASK = -2097153;
    private static final int DEPTH_THRESH = 10;
    private static final int FALLBACK_QSORT_SMALL_THRESH = 10;
    private static final int FALLBACK_QSORT_STACK_SIZE = 100;
    private static final int[] INCS = {1, 4, 13, 40, 121, 364, 1093, 3280, 9841, 29524, 88573, 265720, 797161, 2391484};
    private static final int QSORT_STACK_SIZE = 1000;
    private static final int SETMASK = 2097152;
    private static final int SMALL_THRESH = 20;
    private static final int STACK_SIZE = 1000;
    private static final int WORK_FACTOR = 30;
    private int[] eclass;
    private boolean firstAttempt;
    private final char[] quadrant;
    private int workDone;
    private int workLimit;
    private final int[] stack_ll = new int[1000];
    private final int[] stack_hh = new int[1000];
    private final int[] stack_dd = new int[1000];
    private final int[] mainSort_runningOrder = new int[256];
    private final int[] mainSort_copy = new int[256];
    private final boolean[] mainSort_bigDone = new boolean[256];
    private final int[] ftab = new int[65537];

    private int fmin(int i, int i2) {
        return i < i2 ? i : i2;
    }

    private static byte med3(byte b, byte b2, byte b3) {
        if (b < b2) {
            if (b2 >= b3) {
                if (b >= b3) {
                    return b;
                }
                return b3;
            }
            return b2;
        }
        if (b2 <= b3) {
            if (b <= b3) {
                return b;
            }
            return b3;
        }
        return b2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BlockSort(BZip2CompressorOutputStream.Data data) {
        this.quadrant = data.sfmap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void blockSort(BZip2CompressorOutputStream.Data data, int i) {
        this.workLimit = i * 30;
        this.workDone = 0;
        this.firstAttempt = true;
        if (i + 1 < 10000) {
            fallbackSort(data, i);
        } else {
            mainSort(data, i);
            if (this.firstAttempt && this.workDone > this.workLimit) {
                fallbackSort(data, i);
            }
        }
        int[] iArr = data.fmap;
        data.origPtr = -1;
        for (int i2 = 0; i2 <= i; i2++) {
            if (iArr[i2] == 0) {
                data.origPtr = i2;
                return;
            }
        }
    }

    final void fallbackSort(BZip2CompressorOutputStream.Data data, int i) {
        int i2 = i + 1;
        data.block[0] = data.block[i2];
        fallbackSort(data.fmap, data.block, i2);
        for (int i3 = 0; i3 < i2; i3++) {
            data.fmap[i3] = r1[i3] - 1;
        }
        for (int i4 = 0; i4 < i2; i4++) {
            if (data.fmap[i4] == -1) {
                data.fmap[i4] = i;
                return;
            }
        }
    }

    private void fallbackSimpleSort(int[] iArr, int[] iArr2, int i, int i2) {
        if (i == i2) {
            return;
        }
        if (i2 - i > 3) {
            for (int i3 = i2 - 4; i3 >= i; i3--) {
                int i4 = iArr[i3];
                int i5 = iArr2[i4];
                int i6 = i3 + 4;
                while (i6 <= i2 && i5 > iArr2[iArr[i6]]) {
                    iArr[i6 - 4] = iArr[i6];
                    i6 += 4;
                }
                iArr[i6 - 4] = i4;
            }
        }
        for (int i7 = i2 - 1; i7 >= i; i7--) {
            int i8 = iArr[i7];
            int i9 = iArr2[i8];
            int i10 = i7 + 1;
            while (i10 <= i2 && i9 > iArr2[iArr[i10]]) {
                iArr[i10 - 1] = iArr[i10];
                i10++;
            }
            iArr[i10 - 1] = i8;
        }
    }

    private void fswap(int[] iArr, int i, int i2) {
        int i3 = iArr[i];
        iArr[i] = iArr[i2];
        iArr[i2] = i3;
    }

    private void fvswap(int[] iArr, int i, int i2, int i3) {
        while (i3 > 0) {
            fswap(iArr, i, i2);
            i++;
            i2++;
            i3--;
        }
    }

    private void fpush(int i, int i2, int i3) {
        this.stack_ll[i] = i2;
        this.stack_hh[i] = i3;
    }

    private int[] fpop(int i) {
        return new int[]{this.stack_ll[i], this.stack_hh[i]};
    }

    private void fallbackQSort3(int[] iArr, int[] iArr2, int i, int i2) {
        int i3;
        int i4;
        char c = 0;
        fpush(0, i, i2);
        long j = 0;
        int i5 = 1;
        long j2 = 0;
        int i6 = 1;
        while (i6 > 0) {
            i6--;
            int[] fpop = fpop(i6);
            int i7 = fpop[c];
            int i8 = fpop[i5];
            if (i8 - i7 < 10) {
                fallbackSimpleSort(iArr, iArr2, i7, i8);
            } else {
                j2 = ((j2 * 7621) + 1) % PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                long j3 = j2 % 3;
                if (j3 == j) {
                    i3 = iArr2[iArr[i7]];
                } else if (j3 == 1) {
                    i3 = iArr2[iArr[(i7 + i8) >>> i5]];
                } else {
                    i3 = iArr2[iArr[i8]];
                }
                long j4 = i3;
                int i9 = i8;
                int i10 = i9;
                int i11 = i7;
                int i12 = i11;
                while (true) {
                    if (i12 <= i9) {
                        int i13 = iArr2[iArr[i12]] - ((int) j4);
                        if (i13 == 0) {
                            fswap(iArr, i12, i11);
                            i11++;
                        } else if (i13 <= 0) {
                        }
                        i12++;
                    }
                    i4 = i10;
                    while (i12 <= i9) {
                        int i14 = iArr2[iArr[i9]] - ((int) j4);
                        if (i14 == 0) {
                            fswap(iArr, i9, i4);
                            i4--;
                            i9--;
                        } else if (i14 < 0) {
                            break;
                        } else {
                            i9--;
                        }
                    }
                    if (i12 > i9) {
                        break;
                    }
                    fswap(iArr, i12, i9);
                    i12++;
                    i9--;
                    i10 = i4;
                    i5 = 1;
                }
                if (i4 < i11) {
                    c = 0;
                    j = 0;
                    i5 = 1;
                } else {
                    int fmin = fmin(i11 - i7, i12 - i11);
                    fvswap(iArr, i7, i12 - fmin, fmin);
                    int i15 = i8 - i4;
                    int i16 = i4 - i9;
                    int fmin2 = fmin(i15, i16);
                    fvswap(iArr, i9 + 1, (i8 - fmin2) + 1, fmin2);
                    int i17 = ((i12 + i7) - i11) - 1;
                    int i18 = (i8 - i16) + 1;
                    if (i17 - i7 > i8 - i18) {
                        int i19 = i6 + 1;
                        fpush(i6, i7, i17);
                        fpush(i19, i18, i8);
                        i6 = i19 + 1;
                    } else {
                        int i20 = i6 + 1;
                        fpush(i6, i18, i8);
                        fpush(i20, i7, i17);
                        i6 = i20 + 1;
                    }
                    i5 = 1;
                    c = 0;
                    j = 0;
                }
            }
        }
    }

    private int[] getEclass() {
        if (this.eclass == null) {
            this.eclass = new int[this.quadrant.length / 2];
        }
        return this.eclass;
    }

    final void fallbackSort(int[] iArr, byte[] bArr, int i) {
        int i2;
        int[] iArr2 = new int[257];
        int[] eclass = getEclass();
        for (int i3 = 0; i3 < i; i3++) {
            eclass[i3] = 0;
        }
        for (int i4 = 0; i4 < i; i4++) {
            int i5 = bArr[i4] & 255;
            iArr2[i5] = iArr2[i5] + 1;
        }
        for (int i6 = 1; i6 < 257; i6++) {
            iArr2[i6] = iArr2[i6] + iArr2[i6 - 1];
        }
        for (int i7 = 0; i7 < i; i7++) {
            int i8 = bArr[i7] & 255;
            int i9 = iArr2[i8] - 1;
            iArr2[i8] = i9;
            iArr[i9] = i7;
        }
        BitSet bitSet = new BitSet(i + 64);
        for (int i10 = 0; i10 < 256; i10++) {
            bitSet.set(iArr2[i10]);
        }
        for (int i11 = 0; i11 < 32; i11++) {
            int i12 = (i11 * 2) + i;
            bitSet.set(i12);
            bitSet.clear(i12 + 1);
        }
        int i13 = 1;
        do {
            int i14 = 0;
            for (int i15 = 0; i15 < i; i15++) {
                if (bitSet.get(i15)) {
                    i14 = i15;
                }
                int i16 = iArr[i15] - i13;
                if (i16 < 0) {
                    i16 += i;
                }
                eclass[i16] = i14;
            }
            int i17 = -1;
            i2 = 0;
            while (true) {
                int nextClearBit = bitSet.nextClearBit(i17 + 1);
                int i18 = nextClearBit - 1;
                if (i18 < i && (i17 = bitSet.nextSetBit(nextClearBit + 1) - 1) < i) {
                    if (i17 > i18) {
                        i2 += (i17 - i18) + 1;
                        fallbackQSort3(iArr, eclass, i18, i17);
                        int i19 = -1;
                        while (i18 <= i17) {
                            int i20 = eclass[iArr[i18]];
                            if (i19 != i20) {
                                bitSet.set(i18);
                                i19 = i20;
                            }
                            i18++;
                        }
                    }
                }
            }
            i13 *= 2;
            if (i13 > i) {
                return;
            }
        } while (i2 != 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:110:0x005e, code lost:
    
        r27 = r12;
        r28 = r14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean mainSimpleSort(BZip2CompressorOutputStream.Data data, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        boolean z;
        int i8 = (i2 - i) + 1;
        if (i8 < 2) {
            return this.firstAttempt && this.workDone > this.workLimit;
        }
        int i9 = 0;
        while (INCS[i9] < i8) {
            i9++;
        }
        int[] iArr = data.fmap;
        char[] cArr = this.quadrant;
        byte[] bArr = data.block;
        int i10 = i4 + 1;
        boolean z2 = this.firstAttempt;
        int i11 = this.workLimit;
        int i12 = this.workDone;
        loop1: while (true) {
            i9--;
            if (i9 < 0) {
                break;
            }
            int i13 = INCS[i9];
            int i14 = i + i13;
            int i15 = i14 - 1;
            while (i14 <= i2) {
                int i16 = 3;
                while (i14 <= i2) {
                    i16--;
                    if (i16 < 0) {
                        break;
                    }
                    int i17 = iArr[i14];
                    int i18 = i17 + i3;
                    int i19 = i12;
                    int i20 = i14;
                    boolean z3 = false;
                    int i21 = 0;
                    while (true) {
                        if (z3) {
                            iArr[i20] = i21;
                            int i22 = i20 - i13;
                            i20 = i22;
                            if (i22 <= i15) {
                                i5 = i9;
                                break;
                            }
                        } else {
                            z3 = true;
                        }
                        int i23 = iArr[i20 - i13];
                        int i24 = i23 + i3;
                        int i25 = i24 + 1;
                        int i26 = i18 + 1;
                        if (bArr[i25] == bArr[i26]) {
                            int i27 = i24 + 2;
                            int i28 = i18 + 2;
                            i5 = i9;
                            if (bArr[i27] == bArr[i28]) {
                                int i29 = i24 + 3;
                                int i30 = i18 + 3;
                                z = z3;
                                if (bArr[i29] == bArr[i30]) {
                                    int i31 = i24 + 4;
                                    int i32 = i18 + 4;
                                    if (bArr[i31] == bArr[i32]) {
                                        int i33 = i24 + 5;
                                        int i34 = i18 + 5;
                                        if (bArr[i33] == bArr[i34]) {
                                            int i35 = i24 + 6;
                                            int i36 = i18 + 6;
                                            if (bArr[i35] != bArr[i36]) {
                                                i6 = i13;
                                                i7 = i15;
                                                if ((bArr[i35] & 255) <= (bArr[i36] & 255)) {
                                                    break;
                                                }
                                                i21 = i23;
                                                i9 = i5;
                                                z3 = z;
                                                i13 = i6;
                                                i15 = i7;
                                            } else {
                                                int i37 = i4;
                                                while (i37 > 0) {
                                                    int i38 = i37 - 4;
                                                    int i39 = i35 + 1;
                                                    int i40 = i36 + 1;
                                                    if (bArr[i39] == bArr[i40]) {
                                                        if (cArr[i35] == cArr[i36]) {
                                                            int i41 = i35 + 2;
                                                            int i42 = i36 + 2;
                                                            i6 = i13;
                                                            if (bArr[i41] == bArr[i42]) {
                                                                if (cArr[i39] == cArr[i40]) {
                                                                    int i43 = i35 + 3;
                                                                    int i44 = i36 + 3;
                                                                    i7 = i15;
                                                                    if (bArr[i43] == bArr[i44]) {
                                                                        if (cArr[i41] == cArr[i42]) {
                                                                            int i45 = i35 + 4;
                                                                            i36 += 4;
                                                                            if (bArr[i45] == bArr[i36]) {
                                                                                if (cArr[i43] != cArr[i44]) {
                                                                                    if (cArr[i43] <= cArr[i44]) {
                                                                                        break;
                                                                                    }
                                                                                    i21 = i23;
                                                                                    i9 = i5;
                                                                                    z3 = z;
                                                                                    i13 = i6;
                                                                                    i15 = i7;
                                                                                } else {
                                                                                    if (i45 >= i10) {
                                                                                        i45 -= i10;
                                                                                    }
                                                                                    i35 = i45;
                                                                                    if (i36 >= i10) {
                                                                                        i36 -= i10;
                                                                                    }
                                                                                    i19++;
                                                                                    i37 = i38;
                                                                                    i13 = i6;
                                                                                    i15 = i7;
                                                                                }
                                                                            } else {
                                                                                if ((bArr[i45] & 255) <= (bArr[i36] & 255)) {
                                                                                    break;
                                                                                }
                                                                                i21 = i23;
                                                                                i9 = i5;
                                                                                z3 = z;
                                                                                i13 = i6;
                                                                                i15 = i7;
                                                                            }
                                                                        } else {
                                                                            if (cArr[i41] <= cArr[i42]) {
                                                                                break;
                                                                            }
                                                                            i21 = i23;
                                                                            i9 = i5;
                                                                            z3 = z;
                                                                            i13 = i6;
                                                                            i15 = i7;
                                                                        }
                                                                    } else {
                                                                        if ((bArr[i43] & 255) <= (bArr[i44] & 255)) {
                                                                            break;
                                                                        }
                                                                        i21 = i23;
                                                                        i9 = i5;
                                                                        z3 = z;
                                                                        i13 = i6;
                                                                        i15 = i7;
                                                                    }
                                                                } else {
                                                                    i7 = i15;
                                                                    if (cArr[i39] <= cArr[i40]) {
                                                                        break;
                                                                    }
                                                                    i21 = i23;
                                                                    i9 = i5;
                                                                    z3 = z;
                                                                    i13 = i6;
                                                                    i15 = i7;
                                                                }
                                                            } else {
                                                                i7 = i15;
                                                                if ((bArr[i41] & 255) <= (bArr[i42] & 255)) {
                                                                    break;
                                                                }
                                                                i21 = i23;
                                                                i9 = i5;
                                                                z3 = z;
                                                                i13 = i6;
                                                                i15 = i7;
                                                            }
                                                        } else {
                                                            i6 = i13;
                                                            i7 = i15;
                                                            if (cArr[i35] <= cArr[i36]) {
                                                                break;
                                                            }
                                                            i21 = i23;
                                                            i9 = i5;
                                                            z3 = z;
                                                            i13 = i6;
                                                            i15 = i7;
                                                        }
                                                    } else {
                                                        i6 = i13;
                                                        i7 = i15;
                                                        if ((bArr[i39] & 255) <= (bArr[i40] & 255)) {
                                                            break;
                                                        }
                                                        i21 = i23;
                                                        i9 = i5;
                                                        z3 = z;
                                                        i13 = i6;
                                                        i15 = i7;
                                                    }
                                                }
                                                break;
                                            }
                                        } else {
                                            i6 = i13;
                                            i7 = i15;
                                            if ((bArr[i33] & 255) <= (bArr[i34] & 255)) {
                                                break;
                                            }
                                            i21 = i23;
                                            i9 = i5;
                                            z3 = z;
                                            i13 = i6;
                                            i15 = i7;
                                        }
                                    } else {
                                        i6 = i13;
                                        i7 = i15;
                                        if ((bArr[i31] & 255) <= (bArr[i32] & 255)) {
                                            break;
                                        }
                                        i21 = i23;
                                        i9 = i5;
                                        z3 = z;
                                        i13 = i6;
                                        i15 = i7;
                                    }
                                } else {
                                    i6 = i13;
                                    i7 = i15;
                                    if ((bArr[i29] & 255) <= (bArr[i30] & 255)) {
                                        break;
                                    }
                                    i21 = i23;
                                    i9 = i5;
                                    z3 = z;
                                    i13 = i6;
                                    i15 = i7;
                                }
                            } else {
                                z = z3;
                                i6 = i13;
                                i7 = i15;
                                if ((bArr[i27] & 255) <= (bArr[i28] & 255)) {
                                    break;
                                }
                                i21 = i23;
                                i9 = i5;
                                z3 = z;
                                i13 = i6;
                                i15 = i7;
                            }
                        } else {
                            i5 = i9;
                            z = z3;
                            i6 = i13;
                            i7 = i15;
                            if ((bArr[i25] & 255) <= (bArr[i26] & 255)) {
                                break;
                            }
                            i21 = i23;
                            i9 = i5;
                            z3 = z;
                            i13 = i6;
                            i15 = i7;
                        }
                    }
                    i12 = i19;
                    iArr[i20] = i17;
                    i14++;
                    i9 = i5;
                    i13 = i6;
                    i15 = i7;
                }
                int i46 = i9;
                int i47 = i13;
                int i48 = i15;
                if (z2 && i14 <= i2 && i12 > i11) {
                    break loop1;
                }
                i9 = i46;
                i13 = i47;
                i15 = i48;
            }
        }
        this.workDone = i12;
        return z2 && i12 > i11;
    }

    private static void vswap(int[] iArr, int i, int i2, int i3) {
        int i4 = i3 + i;
        while (i < i4) {
            int i5 = iArr[i];
            iArr[i] = iArr[i2];
            iArr[i2] = i5;
            i2++;
            i++;
        }
    }

    private void mainQSort3(BZip2CompressorOutputStream.Data data, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int[] iArr = this.stack_ll;
        int[] iArr2 = this.stack_hh;
        int[] iArr3 = this.stack_dd;
        int[] iArr4 = data.fmap;
        byte[] bArr = data.block;
        iArr[0] = i;
        iArr2[0] = i2;
        iArr3[0] = i3;
        int i8 = 1;
        int i9 = 1;
        while (true) {
            int i10 = i9 - 1;
            if (i10 < 0) {
                return;
            }
            int i11 = iArr[i10];
            int i12 = iArr2[i10];
            int i13 = iArr3[i10];
            if (i12 - i11 < 20 || i13 > 10) {
                i5 = i8;
                if (mainSimpleSort(data, i11, i12, i13, i4)) {
                    return;
                }
            } else {
                int i14 = i13 + 1;
                int med3 = med3(bArr[iArr4[i11] + i14], bArr[iArr4[i12] + i14], bArr[iArr4[(i11 + i12) >>> i8] + i14]) & 255;
                int i15 = i11;
                int i16 = i15;
                int i17 = i12;
                int i18 = i17;
                while (true) {
                    if (i15 <= i17) {
                        int i19 = (bArr[iArr4[i15] + i14] & 255) - med3;
                        if (i19 == 0) {
                            int i20 = iArr4[i15];
                            i7 = i15 + 1;
                            iArr4[i15] = iArr4[i16];
                            iArr4[i16] = i20;
                            i16++;
                            i15 = i7;
                        } else if (i19 < 0) {
                            i15++;
                        }
                    }
                    i6 = i18;
                    while (i15 <= i17) {
                        int i21 = (bArr[iArr4[i17] + i14] & 255) - med3;
                        if (i21 != 0) {
                            if (i21 <= 0) {
                                break;
                            } else {
                                i17--;
                            }
                        } else {
                            int i22 = iArr4[i17];
                            iArr4[i17] = iArr4[i6];
                            iArr4[i6] = i22;
                            i6--;
                            i17--;
                        }
                    }
                    if (i15 > i17) {
                        break;
                    }
                    int i23 = iArr4[i15];
                    i7 = i15 + 1;
                    iArr4[i15] = iArr4[i17];
                    iArr4[i17] = i23;
                    i17--;
                    i18 = i6;
                    i15 = i7;
                }
                if (i6 < i16) {
                    iArr[i10] = i11;
                    iArr2[i10] = i12;
                    iArr3[i10] = i14;
                    i9 = i10 + 1;
                    i5 = 1;
                    i8 = i5;
                } else {
                    int i24 = i16 - i11;
                    int i25 = i15 - i16;
                    if (i24 >= i25) {
                        i24 = i25;
                    }
                    vswap(iArr4, i11, i15 - i24, i24);
                    int i26 = i12 - i6;
                    int i27 = i6 - i17;
                    if (i26 >= i27) {
                        i26 = i27;
                    }
                    i5 = 1;
                    vswap(iArr4, i15, (i12 - i26) + 1, i26);
                    int i28 = ((i15 + i11) - i16) - 1;
                    int i29 = (i12 - i27) + 1;
                    iArr[i10] = i11;
                    iArr2[i10] = i28;
                    iArr3[i10] = i13;
                    int i30 = i10 + 1;
                    iArr[i30] = i28 + 1;
                    iArr2[i30] = i29 - 1;
                    iArr3[i30] = i14;
                    int i31 = i30 + 1;
                    iArr[i31] = i29;
                    iArr2[i31] = i12;
                    iArr3[i31] = i13;
                    i10 = i31 + 1;
                }
            }
            i9 = i10;
            i8 = i5;
        }
    }

    final void mainSort(BZip2CompressorOutputStream.Data data, int i) {
        boolean z;
        int i2;
        int i3;
        int[] iArr;
        int i4;
        int i5;
        int i6;
        int i7;
        int[] iArr2 = this.mainSort_runningOrder;
        int[] iArr3 = this.mainSort_copy;
        boolean[] zArr = this.mainSort_bigDone;
        int[] iArr4 = this.ftab;
        byte[] bArr = data.block;
        int[] iArr5 = data.fmap;
        char[] cArr = this.quadrant;
        int i8 = this.workLimit;
        boolean z2 = this.firstAttempt;
        int i9 = 65537;
        while (true) {
            i9--;
            if (i9 < 0) {
                break;
            } else {
                iArr4[i9] = 0;
            }
        }
        int i10 = 0;
        while (true) {
            z = true;
            if (i10 >= 20) {
                break;
            }
            bArr[i + i10 + 2] = bArr[(i10 % (i + 1)) + 1];
            i10++;
        }
        int i11 = i + 20 + 1;
        while (true) {
            i11--;
            if (i11 < 0) {
                break;
            } else {
                cArr[i11] = 0;
            }
        }
        int i12 = i + 1;
        bArr[0] = bArr[i12];
        int i13 = 255;
        int i14 = bArr[0] & 255;
        int i15 = 0;
        while (i15 <= i) {
            i15++;
            int i16 = bArr[i15] & 255;
            int i17 = (i14 << 8) + i16;
            iArr4[i17] = iArr4[i17] + 1;
            i14 = i16;
        }
        for (int i18 = 1; i18 <= 65536; i18++) {
            iArr4[i18] = iArr4[i18] + iArr4[i18 - 1];
        }
        int i19 = bArr[1] & 255;
        int i20 = 0;
        while (i20 < i) {
            int i21 = bArr[i20 + 2] & 255;
            int i22 = (i19 << 8) + i21;
            int i23 = iArr4[i22] - 1;
            iArr4[i22] = i23;
            iArr5[i23] = i20;
            i20++;
            i19 = i21;
            z = true;
        }
        int i24 = ((bArr[i12] & 255) << 8) + (bArr[z ? 1 : 0] & 255);
        int i25 = iArr4[i24] - 1;
        iArr4[i24] = i25;
        iArr5[i25] = i;
        int i26 = 256;
        while (true) {
            i26--;
            if (i26 < 0) {
                break;
            }
            zArr[i26] = false;
            iArr2[i26] = i26;
        }
        int i27 = 364;
        while (i27 != 1) {
            i27 /= 3;
            int i28 = i27;
            while (i28 <= i13) {
                int i29 = iArr2[i28];
                int i30 = iArr4[(i29 + 1) << 8] - iArr4[i29 << 8];
                int i31 = i27 - 1;
                int i32 = iArr2[i28 - i27];
                int i33 = i28;
                while (true) {
                    i6 = i8;
                    if (iArr4[(i32 + 1) << 8] - iArr4[i32 << 8] <= i30) {
                        i7 = i33;
                        break;
                    }
                    iArr2[i33] = i32;
                    i7 = i33 - i27;
                    if (i7 <= i31) {
                        break;
                    }
                    i32 = iArr2[i7 - i27];
                    i33 = i7;
                    i8 = i6;
                }
                iArr2[i7] = i29;
                i28++;
                i8 = i6;
                i13 = 255;
            }
        }
        int i34 = i8;
        int i35 = 0;
        while (i35 <= i13) {
            int i36 = iArr2[i35];
            int i37 = 0;
            while (i37 <= i13) {
                int i38 = (i36 << 8) + i37;
                int i39 = iArr4[i38];
                if ((i39 & 2097152) != 2097152) {
                    int i40 = i39 & CLEARMASK;
                    int i41 = (iArr4[i38 + 1] & CLEARMASK) - 1;
                    if (i41 > i40) {
                        i5 = 2097152;
                        i2 = i37;
                        i3 = i34;
                        iArr = iArr2;
                        i4 = i35;
                        mainQSort3(data, i40, i41, 2, i);
                        if (z2 && this.workDone > i3) {
                            return;
                        }
                    } else {
                        i2 = i37;
                        i3 = i34;
                        i5 = 2097152;
                        iArr = iArr2;
                        i4 = i35;
                    }
                    iArr4[i38] = i39 | i5;
                } else {
                    i2 = i37;
                    i3 = i34;
                    iArr = iArr2;
                    i4 = i35;
                }
                i37 = i2 + 1;
                i35 = i4;
                iArr2 = iArr;
                i13 = 255;
                i34 = i3;
            }
            int i42 = i34;
            int[] iArr6 = iArr2;
            int i43 = i35;
            int i44 = 0;
            for (int i45 = i13; i44 <= i45; i45 = 255) {
                iArr3[i44] = iArr4[(i44 << 8) + i36] & CLEARMASK;
                i44++;
            }
            int i46 = i36 << 8;
            int i47 = iArr4[i46] & CLEARMASK;
            int i48 = (i36 + 1) << 8;
            int i49 = iArr4[i48] & CLEARMASK;
            while (i47 < i49) {
                int i50 = iArr5[i47];
                int i51 = i49;
                int i52 = bArr[i50] & 255;
                if (!zArr[i52]) {
                    iArr5[iArr3[i52]] = i50 == 0 ? i : i50 - 1;
                    iArr3[i52] = iArr3[i52] + 1;
                }
                i47++;
                i49 = i51;
            }
            int i53 = 256;
            while (true) {
                i53--;
                if (i53 < 0) {
                    break;
                }
                int i54 = (i53 << 8) + i36;
                iArr4[i54] = iArr4[i54] | 2097152;
            }
            zArr[i36] = true;
            if (i43 < 255) {
                int i55 = iArr4[i46] & CLEARMASK;
                int i56 = (CLEARMASK & iArr4[i48]) - i55;
                int i57 = 0;
                while ((i56 >> i57) > 65534) {
                    i57++;
                }
                int i58 = 0;
                while (i58 < i56) {
                    int i59 = iArr5[i55 + i58];
                    char c = (char) (i58 >> i57);
                    cArr[i59] = c;
                    int i60 = i55;
                    if (i59 < 20) {
                        cArr[i59 + i + 1] = c;
                    }
                    i58++;
                    i55 = i60;
                }
            }
            i35 = i43 + 1;
            iArr2 = iArr6;
            i13 = 255;
            i34 = i42;
        }
    }
}
