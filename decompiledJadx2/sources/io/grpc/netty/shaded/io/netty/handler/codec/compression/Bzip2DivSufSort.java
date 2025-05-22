package io.grpc.netty.shaded.io.netty.handler.codec.compression;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class Bzip2DivSufSort {
    private static final int BUCKET_A_SIZE = 256;
    private static final int BUCKET_B_SIZE = 65536;
    private static final int INSERTIONSORT_THRESHOLD = 8;
    private static final int[] LOG_2_TABLE = {-1, 0, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
    private static final int SS_BLOCKSIZE = 1024;
    private static final int STACK_SIZE = 64;

    /* renamed from: SA */
    private final int[] f8351SA;

    /* renamed from: T */
    private final byte[] f8352T;

    /* renamed from: n */
    private final int f8353n;

    private static int BUCKET_B(int i, int i2) {
        return i | (i2 << 8);
    }

    private static int BUCKET_BSTAR(int i, int i2) {
        return (i << 8) | i2;
    }

    private static int getIDX(int i) {
        return i >= 0 ? i : ~i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Bzip2DivSufSort(byte[] bArr, int[] iArr, int i) {
        this.f8352T = bArr;
        this.f8351SA = iArr;
        this.f8353n = i;
    }

    private static void swapElements(int[] iArr, int i, int[] iArr2, int i2) {
        int i3 = iArr[i];
        iArr[i] = iArr2[i2];
        iArr2[i2] = i3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:?, code lost:
    
        return (r1[r5] & 255) - (r1[r7] & 255);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0033, code lost:
    
        return 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0027, code lost:
    
        if (r7 >= r3) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int ssCompare(int i, int i2, int i3) {
        int[] iArr = this.f8351SA;
        byte[] bArr = this.f8352T;
        int i4 = iArr[i + 1] + 2;
        int i5 = iArr[i2 + 1] + 2;
        int i6 = iArr[i] + i3;
        int i7 = i3 + iArr[i2];
        while (i6 < i4 && i7 < i5 && bArr[i6] == bArr[i7]) {
            i6++;
            i7++;
        }
        return i7 < i5 ? -1 : 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002d, code lost:
    
        return (r1[r7] & 255) - (r1[r9] & 255);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:?, code lost:
    
        return 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0047, code lost:
    
        if (r9 >= r8) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:?, code lost:
    
        return (r1[r7] & 255) - (r1[r9] & 255);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:?, code lost:
    
        return 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0021, code lost:
    
        if (r9 >= r8) goto L33;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int ssCompareLast(int i, int i2, int i3, int i4, int i5) {
        int[] iArr = this.f8351SA;
        byte[] bArr = this.f8352T;
        int i6 = iArr[i2] + i4;
        int i7 = i4 + iArr[i3];
        int i8 = iArr[i3 + 1] + 2;
        while (i6 < i5 && i7 < i8 && bArr[i6] == bArr[i7]) {
            i6++;
            i7++;
        }
        if (i7 == i8) {
            return 1;
        }
        int i9 = i6 % i5;
        int i10 = iArr[i] + 2;
        while (i9 < i10 && i7 < i8 && bArr[i9] == bArr[i7]) {
            i9++;
            i7++;
        }
        return i7 < i8 ? -1 : 0;
    }

    private void ssInsertionSort(int i, int i2, int i3, int i4) {
        int ssCompare;
        int[] iArr = this.f8351SA;
        for (int i5 = i3 - 2; i2 <= i5; i5--) {
            int i6 = iArr[i5];
            int i7 = i5 + 1;
            do {
                ssCompare = ssCompare(i + i6, iArr[i7] + i, i4);
                if (ssCompare <= 0) {
                    break;
                }
                do {
                    iArr[i7 - 1] = iArr[i7];
                    i7++;
                    if (i7 >= i3) {
                        break;
                    }
                } while (iArr[i7] < 0);
            } while (i3 > i7);
            if (ssCompare == 0) {
                iArr[i7] = ~iArr[i7];
            }
            iArr[i7 - 1] = i6;
        }
    }

    private void ssFixdown(int i, int i2, int i3, int i4, int i5) {
        int[] iArr = this.f8351SA;
        byte[] bArr = this.f8352T;
        int i6 = iArr[i3 + i4];
        int i7 = bArr[iArr[i2 + i6] + i] & 255;
        while (true) {
            int i8 = (i4 * 2) + 1;
            if (i8 >= i5) {
                break;
            }
            int i9 = i8 + 1;
            int i10 = bArr[iArr[iArr[i3 + i8] + i2] + i] & 255;
            int i11 = bArr[iArr[iArr[i3 + i9] + i2] + i] & 255;
            if (i10 < i11) {
                i8 = i9;
                i10 = i11;
            }
            if (i10 <= i7) {
                break;
            }
            iArr[i4 + i3] = iArr[i3 + i8];
            i4 = i8;
        }
        iArr[i3 + i4] = i6;
    }

    private void ssHeapSort(int i, int i2, int i3, int i4) {
        int i5;
        int[] iArr = this.f8351SA;
        byte[] bArr = this.f8352T;
        int i6 = i4 % 2;
        if (i6 == 0) {
            int i7 = i4 - 1;
            int i8 = (i7 / 2) + i3;
            int i9 = i3 + i7;
            if ((bArr[iArr[iArr[i8] + i2] + i] & 255) < (bArr[iArr[iArr[i9] + i2] + i] & 255)) {
                swapElements(iArr, i9, iArr, i8);
            }
            i5 = i7;
        } else {
            i5 = i4;
        }
        for (int i10 = (i5 / 2) - 1; i10 >= 0; i10--) {
            ssFixdown(i, i2, i3, i10, i5);
        }
        if (i6 == 0) {
            swapElements(iArr, i3, iArr, i3 + i5);
            ssFixdown(i, i2, i3, 0, i5);
        }
        for (int i11 = i5 - 1; i11 > 0; i11--) {
            int i12 = iArr[i3];
            int i13 = i3 + i11;
            iArr[i3] = iArr[i13];
            ssFixdown(i, i2, i3, 0, i11);
            iArr[i13] = i12;
        }
    }

    private int ssMedian3(int i, int i2, int i3, int i4, int i5) {
        int[] iArr = this.f8351SA;
        byte[] bArr = this.f8352T;
        int i6 = bArr[iArr[iArr[i3] + i2] + i] & 255;
        int i7 = bArr[iArr[iArr[i4] + i2] + i] & 255;
        int i8 = bArr[i + iArr[i2 + iArr[i5]]] & 255;
        if (i6 <= i7) {
            i4 = i3;
            i3 = i4;
            i7 = i6;
            i6 = i7;
        }
        return i6 > i8 ? i7 > i8 ? i4 : i5 : i3;
    }

    private int ssMedian5(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int[] iArr = this.f8351SA;
        byte[] bArr = this.f8352T;
        int i15 = bArr[iArr[iArr[i3] + i2] + i] & 255;
        int i16 = bArr[iArr[iArr[i4] + i2] + i] & 255;
        int i17 = bArr[iArr[iArr[i5] + i2] + i] & 255;
        int i18 = bArr[iArr[iArr[i6] + i2] + i] & 255;
        int i19 = bArr[iArr[iArr[i7] + i2] + i] & 255;
        if (i16 > i17) {
            i8 = i5;
            i9 = i16;
            i16 = i17;
            i10 = i4;
        } else {
            i8 = i4;
            i9 = i17;
            i10 = i5;
        }
        if (i18 > i19) {
            i11 = i7;
            i12 = i18;
            i13 = i6;
        } else {
            i11 = i6;
            i12 = i19;
            i19 = i18;
            i13 = i7;
        }
        if (i16 > i19) {
            i11 = i8;
            i19 = i16;
            int i20 = i13;
            i13 = i10;
            i10 = i20;
        } else {
            int i21 = i12;
            i12 = i9;
            i9 = i21;
        }
        if (i15 > i12) {
            i14 = i3;
        } else {
            i14 = i10;
            i10 = i3;
            int i22 = i12;
            i12 = i15;
            i15 = i22;
        }
        if (i12 > i19) {
            i11 = i10;
            i14 = i13;
            i15 = i9;
        } else {
            i12 = i19;
        }
        return i15 > i12 ? i11 : i14;
    }

    private int ssPivot(int i, int i2, int i3, int i4) {
        int i5 = i4 - i3;
        int i6 = i3 + (i5 / 2);
        if (i5 <= 512) {
            if (i5 <= 32) {
                return ssMedian3(i, i2, i3, i6, i4 - 1);
            }
            int i7 = i5 >> 2;
            int i8 = i4 - 1;
            return ssMedian5(i, i2, i3, i3 + i7, i6, i8 - i7, i8);
        }
        int i9 = i5 >> 3;
        int i10 = i9 << 1;
        int i11 = i4 - 1;
        return ssMedian3(i, i2, ssMedian3(i, i2, i3, i3 + i9, i3 + i10), ssMedian3(i, i2, i6 - i9, i6, i6 + i9), ssMedian3(i, i2, i11 - i10, i11 - i9, i11));
    }

    private static int ssLog(int i) {
        return (65280 & i) != 0 ? LOG_2_TABLE[(i >> 8) & 255] + 8 : LOG_2_TABLE[i & 255];
    }

    private int ssSubstringPartition(int i, int i2, int i3, int i4) {
        int[] iArr = this.f8351SA;
        int i5 = i2 - 1;
        while (true) {
            i5++;
            if (i5 < i3 && iArr[iArr[i5] + i] + i4 >= iArr[iArr[i5] + i + 1] + 1) {
                iArr[i5] = ~iArr[i5];
            } else {
                do {
                    i3--;
                    if (i5 >= i3) {
                        break;
                    }
                } while (iArr[iArr[i3] + i] + i4 < iArr[iArr[i3] + i + 1] + 1);
                if (i3 <= i5) {
                    break;
                }
                int i6 = ~iArr[i3];
                iArr[i3] = iArr[i5];
                iArr[i5] = i6;
            }
        }
        if (i2 < i5) {
            iArr[i2] = ~iArr[i2];
        }
        return i5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static class StackEntry {

        /* renamed from: a */
        final int f8354a;

        /* renamed from: b */
        final int f8355b;

        /* renamed from: c */
        final int f8356c;

        /* renamed from: d */
        final int f8357d;

        StackEntry(int i, int i2, int i3, int i4) {
            this.f8354a = i;
            this.f8355b = i2;
            this.f8356c = i3;
            this.f8357d = i4;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x014c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:77:0x017b -> B:63:0x0144). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void ssMultiKeyIntroSort(int i, int i2, int i3, int i4) {
        int ssLog;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        Bzip2DivSufSort bzip2DivSufSort = this;
        int[] iArr = bzip2DivSufSort.f8351SA;
        byte[] bArr = bzip2DivSufSort.f8352T;
        StackEntry[] stackEntryArr = new StackEntry[64];
        int i11 = -1;
        int i12 = i4;
        int ssLog2 = ssLog(i3 - i2);
        int i13 = 0;
        int i14 = 0;
        int i15 = i2;
        int i16 = i3;
        while (true) {
            int i17 = i16 - i15;
            if (i17 <= 8) {
                if (1 < i17) {
                    bzip2DivSufSort.ssInsertionSort(i, i15, i16, i12);
                }
                if (i13 == 0) {
                    return;
                }
                i13--;
                StackEntry stackEntry = stackEntryArr[i13];
                i15 = stackEntry.f8354a;
                int i18 = stackEntry.f8355b;
                int i19 = stackEntry.f8356c;
                ssLog2 = stackEntry.f8357d;
                i16 = i18;
                i12 = i19;
            } else {
                int i20 = ssLog2 - 1;
                if (ssLog2 == 0) {
                    bzip2DivSufSort.ssHeapSort(i12, i, i15, i17);
                }
                if (i20 < 0) {
                    int i21 = bArr[iArr[iArr[i15] + i] + i12] & 255;
                    int i22 = i15;
                    i15++;
                    while (i15 < i16) {
                        i14 = bArr[iArr[iArr[i15] + i] + i12] & 255;
                        if (i14 != i21) {
                            if (1 < i15 - i22) {
                                break;
                            }
                            i22 = i15;
                            i21 = i14;
                        }
                        i15++;
                    }
                    if ((bArr[(iArr[iArr[i22] + i] + i12) - 1] & 255) < i21) {
                        i22 = bzip2DivSufSort.ssSubstringPartition(i, i22, i15, i12);
                    }
                    int i23 = i15 - i22;
                    int i24 = i16 - i15;
                    if (i23 <= i24) {
                        if (1 < i23) {
                            stackEntryArr[i13] = new StackEntry(i15, i16, i12, i11);
                            i12++;
                            ssLog = ssLog(i23);
                            i13++;
                            int i25 = i22;
                            ssLog2 = ssLog;
                            i16 = i15;
                            i15 = i25;
                        } else {
                            ssLog2 = i11;
                        }
                    } else if (1 < i24) {
                        stackEntryArr[i13] = new StackEntry(i22, i15, i12 + 1, ssLog(i23));
                        ssLog2 = i11;
                        i13++;
                    } else {
                        i12++;
                        ssLog = ssLog(i23);
                        int i252 = i22;
                        ssLog2 = ssLog;
                        i16 = i15;
                        i15 = i252;
                    }
                } else {
                    int ssPivot = bzip2DivSufSort.ssPivot(i12, i, i15, i16);
                    int i26 = bArr[iArr[iArr[ssPivot] + i] + i12] & 255;
                    swapElements(iArr, i15, iArr, ssPivot);
                    int i27 = i15 + 1;
                    while (i27 < i16) {
                        i14 = bArr[iArr[iArr[i27] + i] + i12] & 255;
                        if (i14 != i26) {
                            break;
                        } else {
                            i27++;
                        }
                    }
                    if (i27 < i16 && i14 < i26) {
                        i5 = i27;
                        while (true) {
                            i27++;
                            if (i27 >= i16 || (i14 = bArr[iArr[iArr[i27] + i] + i12] & 255) > i26) {
                                break;
                            } else if (i14 == i26) {
                                swapElements(iArr, i27, iArr, i5);
                                i5++;
                            }
                        }
                    } else {
                        i5 = i27;
                    }
                    int i28 = i14;
                    int i29 = i16 - 1;
                    while (true) {
                        if (i27 < i29) {
                            i6 = bArr[i12 + iArr[i + iArr[i29]]] & 255;
                            if (i6 != i26) {
                                break;
                            }
                            i29--;
                            i28 = i6;
                        } else {
                            i6 = i28;
                            break;
                        }
                    }
                    if (i27 < i29 && i6 > i26) {
                        i7 = i6;
                        i8 = i29;
                        while (true) {
                            i29 += i11;
                            if (i27 < i29) {
                                i10 = bArr[i12 + iArr[i + iArr[i29]]] & 255;
                                if (i10 < i26) {
                                    break;
                                }
                                if (i10 == i26) {
                                    swapElements(iArr, i29, iArr, i8);
                                    i8--;
                                }
                                i7 = i10;
                                i11 = -1;
                            } else {
                                break;
                            }
                        }
                    } else {
                        i7 = i6;
                        i8 = i29;
                    }
                    while (i27 < i29) {
                        swapElements(iArr, i27, iArr, i29);
                        while (true) {
                            i27++;
                            if (i27 < i29) {
                                int i30 = bArr[iArr[iArr[i27] + i] + i12] & 255;
                                if (i30 > i26) {
                                    i7 = i30;
                                    break;
                                }
                                if (i30 == i26) {
                                    swapElements(iArr, i27, iArr, i5);
                                    i5++;
                                }
                                i7 = i30;
                            } else {
                                break;
                            }
                        }
                        while (true) {
                            i29--;
                            if (i27 < i29) {
                                i10 = bArr[i12 + iArr[i + iArr[i29]]] & 255;
                                if (i10 < i26) {
                                    break;
                                }
                                if (i10 == i26) {
                                    swapElements(iArr, i29, iArr, i8);
                                    i8--;
                                    i7 = i10;
                                } else {
                                    i7 = i10;
                                }
                            } else {
                                break;
                            }
                        }
                        i7 = i10;
                        while (i27 < i29) {
                        }
                    }
                    if (i5 <= i8) {
                        int i31 = i27 - 1;
                        int i32 = i5 - i15;
                        int i33 = i27 - i5;
                        if (i32 > i33) {
                            i32 = i33;
                        }
                        int i34 = i27 - i32;
                        int i35 = i27;
                        int i36 = i15;
                        while (i32 > 0) {
                            swapElements(iArr, i36, iArr, i34);
                            i32--;
                            i36++;
                            i34++;
                        }
                        int i37 = i8 - i31;
                        int i38 = (i16 - i8) - 1;
                        if (i37 <= i38) {
                            i38 = i37;
                        }
                        int i39 = i16 - i38;
                        int i40 = i35;
                        while (i38 > 0) {
                            swapElements(iArr, i40, iArr, i39);
                            i38--;
                            i40++;
                            i39++;
                        }
                        int i41 = i15 + i33;
                        int i42 = i16 - i37;
                        int ssSubstringPartition = i26 <= (bArr[(iArr[iArr[i41] + i] + i12) + (-1)] & 255) ? i41 : bzip2DivSufSort.ssSubstringPartition(i, i41, i42, i12);
                        int i43 = i41 - i15;
                        int i44 = i16 - i42;
                        if (i43 <= i44) {
                            int i45 = i42 - ssSubstringPartition;
                            if (i44 <= i45) {
                                int i46 = i13 + 1;
                                stackEntryArr[i13] = new StackEntry(ssSubstringPartition, i42, i12 + 1, ssLog(i45));
                                i13 = i46 + 1;
                                i9 = i20;
                                stackEntryArr[i46] = new StackEntry(i42, i16, i12, i9);
                            } else {
                                i9 = i20;
                                if (i43 <= i45) {
                                    int i47 = i13 + 1;
                                    stackEntryArr[i13] = new StackEntry(i42, i16, i12, i9);
                                    i13 = i47 + 1;
                                    stackEntryArr[i47] = new StackEntry(ssSubstringPartition, i42, i12 + 1, ssLog(i45));
                                } else {
                                    int i48 = i13 + 1;
                                    stackEntryArr[i13] = new StackEntry(i42, i16, i12, i9);
                                    i13 = i48 + 1;
                                    stackEntryArr[i48] = new StackEntry(i15, i41, i12, i9);
                                    i12++;
                                    ssLog2 = ssLog(i45);
                                    i16 = i42;
                                    i15 = ssSubstringPartition;
                                }
                            }
                            i16 = i41;
                            ssLog2 = i9;
                        } else {
                            i9 = i20;
                            int i49 = i42 - ssSubstringPartition;
                            if (i43 <= i49) {
                                int i50 = i13 + 1;
                                stackEntryArr[i13] = new StackEntry(ssSubstringPartition, i42, i12 + 1, ssLog(i49));
                                i13 = i50 + 1;
                                stackEntryArr[i50] = new StackEntry(i15, i41, i12, i9);
                            } else if (i44 <= i49) {
                                int i51 = i13 + 1;
                                stackEntryArr[i13] = new StackEntry(i15, i41, i12, i9);
                                i13 = i51 + 1;
                                stackEntryArr[i51] = new StackEntry(ssSubstringPartition, i42, i12 + 1, ssLog(i49));
                            } else {
                                int i52 = i13 + 1;
                                stackEntryArr[i13] = new StackEntry(i15, i41, i12, i9);
                                i13 = i52 + 1;
                                stackEntryArr[i52] = new StackEntry(i42, i16, i12, i9);
                                i12++;
                                ssLog2 = ssLog(i49);
                                bzip2DivSufSort = this;
                                i16 = i42;
                                i15 = ssSubstringPartition;
                            }
                            bzip2DivSufSort = this;
                            i15 = i42;
                            ssLog2 = i9;
                        }
                    } else {
                        int i53 = i20 + 1;
                        if ((bArr[(iArr[iArr[i15] + i] + i12) - 1] & 255) < i26) {
                            bzip2DivSufSort = this;
                            i15 = bzip2DivSufSort.ssSubstringPartition(i, i15, i16, i12);
                            ssLog2 = ssLog(i16 - i15);
                        } else {
                            bzip2DivSufSort = this;
                            ssLog2 = i53;
                        }
                        i12++;
                    }
                    i14 = i7;
                    i11 = -1;
                }
            }
        }
    }

    private static void ssBlockSwap(int[] iArr, int i, int[] iArr2, int i2, int i3) {
        while (i3 > 0) {
            swapElements(iArr, i, iArr2, i2);
            i3--;
            i++;
            i2++;
        }
    }

    private void ssMergeForward(int i, int[] iArr, int i2, int i3, int i4, int i5, int i6) {
        int i7;
        int[] iArr2 = this.f8351SA;
        int i8 = i4 - i3;
        int i9 = (i2 + i8) - 1;
        ssBlockSwap(iArr, i2, iArr2, i3, i8);
        int i10 = iArr2[i3];
        while (true) {
            int ssCompare = ssCompare(iArr[i2] + i, iArr2[i4] + i, i6);
            if (ssCompare < 0) {
                while (true) {
                    i7 = i3 + 1;
                    iArr2[i3] = iArr[i2];
                    if (i9 <= i2) {
                        iArr[i2] = i10;
                        return;
                    }
                    int i11 = i2 + 1;
                    iArr[i2] = iArr2[i7];
                    if (iArr[i11] >= 0) {
                        i2 = i11;
                        break;
                    } else {
                        i2 = i11;
                        i3 = i7;
                    }
                }
            } else if (ssCompare > 0) {
                while (true) {
                    i7 = i3 + 1;
                    iArr2[i3] = iArr2[i4];
                    int i12 = i4 + 1;
                    iArr2[i4] = iArr2[i7];
                    if (i5 <= i12) {
                        while (i2 < i9) {
                            int i13 = i7 + 1;
                            iArr2[i7] = iArr[i2];
                            iArr[i2] = iArr2[i13];
                            i7 = i13;
                            i2++;
                        }
                        iArr2[i7] = iArr[i2];
                        iArr[i2] = i10;
                        return;
                    }
                    if (iArr2[i12] >= 0) {
                        i4 = i12;
                        break;
                    } else {
                        i4 = i12;
                        i3 = i7;
                    }
                }
            } else {
                iArr2[i4] = ~iArr2[i4];
                while (true) {
                    int i14 = i3 + 1;
                    iArr2[i3] = iArr[i2];
                    if (i9 <= i2) {
                        iArr[i2] = i10;
                        return;
                    }
                    int i15 = i2 + 1;
                    iArr[i2] = iArr2[i14];
                    if (iArr[i15] >= 0) {
                        while (true) {
                            int i16 = i14 + 1;
                            iArr2[i14] = iArr2[i4];
                            int i17 = i4 + 1;
                            iArr2[i4] = iArr2[i16];
                            if (i5 <= i17) {
                                while (i15 < i9) {
                                    int i18 = i16 + 1;
                                    iArr2[i16] = iArr[i15];
                                    iArr[i15] = iArr2[i18];
                                    i15++;
                                    i16 = i18;
                                }
                                iArr2[i16] = iArr[i15];
                                iArr[i15] = i10;
                                return;
                            }
                            if (iArr2[i17] >= 0) {
                                i4 = i17;
                                i3 = i16;
                                i2 = i15;
                                break;
                            }
                            i4 = i17;
                            i14 = i16;
                        }
                    } else {
                        i2 = i15;
                        i3 = i14;
                    }
                }
            }
            i3 = i7;
        }
    }

    private void ssMergeBackward(int i, int[] iArr, int i2, int i3, int i4, int i5, int i6) {
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int[] iArr2 = this.f8351SA;
        int i23 = i5 - i4;
        ssBlockSwap(iArr, i2, iArr2, i4, i23);
        int i24 = (i2 + i23) - 1;
        if (iArr[i24] < 0) {
            i7 = (~iArr[i24]) + i;
            i8 = 1;
        } else {
            i7 = iArr[i24] + i;
            i8 = 0;
        }
        int i25 = i4 - 1;
        if (iArr2[i25] < 0) {
            i8 |= 2;
            i9 = ~iArr2[i25];
        } else {
            i9 = iArr2[i25];
        }
        int i26 = i9 + i;
        int i27 = i5 - 1;
        int i28 = iArr2[i27];
        while (true) {
            int ssCompare = ssCompare(i7, i26, i6);
            if (ssCompare > 0) {
                if ((i8 & 1) != 0) {
                    while (true) {
                        i11 = i27 - 1;
                        iArr2[i27] = iArr[i24];
                        i12 = i24 - 1;
                        iArr[i24] = iArr2[i11];
                        if (iArr[i12] >= 0) {
                            break;
                        }
                        i24 = i12;
                        i27 = i11;
                    }
                    i8 ^= 1;
                    i24 = i12;
                    i27 = i11;
                }
                int i29 = i27 - 1;
                iArr2[i27] = iArr[i24];
                if (i24 <= i2) {
                    iArr[i24] = i28;
                    return;
                }
                int i30 = i24 - 1;
                iArr[i24] = iArr2[i29];
                if (iArr[i30] < 0) {
                    i8 |= 1;
                    i10 = ~iArr[i30];
                } else {
                    i10 = iArr[i30];
                }
                int i31 = i10 + i;
                i24 = i30;
                i27 = i29;
                i7 = i31;
            } else {
                if (ssCompare < 0) {
                    if ((i8 & 2) != 0) {
                        while (true) {
                            i21 = i27 - 1;
                            iArr2[i27] = iArr2[i25];
                            i22 = i25 - 1;
                            iArr2[i25] = iArr2[i21];
                            if (iArr2[i22] >= 0) {
                                break;
                            }
                            i25 = i22;
                            i27 = i21;
                        }
                        i8 ^= 2;
                        i25 = i22;
                        i27 = i21;
                    }
                    int i32 = i27 - 1;
                    iArr2[i27] = iArr2[i25];
                    int i33 = i25 - 1;
                    iArr2[i25] = iArr2[i32];
                    if (i33 < i3) {
                        while (i2 < i24) {
                            int i34 = i32 - 1;
                            iArr2[i32] = iArr[i24];
                            iArr[i24] = iArr2[i34];
                            i32 = i34;
                            i24--;
                        }
                        iArr2[i32] = iArr[i24];
                        iArr[i24] = i28;
                        return;
                    }
                    if (iArr2[i33] < 0) {
                        i8 |= 2;
                        i20 = ~iArr2[i33];
                    } else {
                        i20 = iArr2[i33];
                    }
                    i16 = i32;
                    i26 = i20 + i;
                    i25 = i33;
                } else {
                    if ((i8 & 1) != 0) {
                        while (true) {
                            i18 = i27 - 1;
                            iArr2[i27] = iArr[i24];
                            i19 = i24 - 1;
                            iArr[i24] = iArr2[i18];
                            if (iArr[i19] >= 0) {
                                break;
                            }
                            i24 = i19;
                            i27 = i18;
                        }
                        i8 ^= 1;
                        i24 = i19;
                        i27 = i18;
                    }
                    int i35 = i27 - 1;
                    iArr2[i27] = ~iArr[i24];
                    if (i24 <= i2) {
                        iArr[i24] = i28;
                        return;
                    }
                    int i36 = i24 - 1;
                    iArr[i24] = iArr2[i35];
                    if ((i8 & 2) != 0) {
                        while (true) {
                            i13 = i35 - 1;
                            iArr2[i35] = iArr2[i25];
                            i17 = i25 - 1;
                            iArr2[i25] = iArr2[i13];
                            if (iArr2[i17] >= 0) {
                                break;
                            }
                            i25 = i17;
                            i35 = i13;
                        }
                        i8 ^= 2;
                        i25 = i17;
                    } else {
                        i13 = i35;
                    }
                    int i37 = i13 - 1;
                    iArr2[i13] = iArr2[i25];
                    int i38 = i25 - 1;
                    iArr2[i25] = iArr2[i37];
                    if (i38 < i3) {
                        while (i2 < i36) {
                            int i39 = i37 - 1;
                            iArr2[i37] = iArr[i36];
                            iArr[i36] = iArr2[i39];
                            i37 = i39;
                            i36--;
                        }
                        iArr2[i37] = iArr[i36];
                        iArr[i36] = i28;
                        return;
                    }
                    if (iArr[i36] < 0) {
                        i8 |= 1;
                        i14 = (~iArr[i36]) + i;
                    } else {
                        i14 = iArr[i36] + i;
                    }
                    if (iArr2[i38] < 0) {
                        i8 |= 2;
                        i15 = ~iArr2[i38];
                    } else {
                        i15 = iArr2[i38];
                    }
                    i26 = i15 + i;
                    i16 = i37;
                    i7 = i14;
                    i25 = i38;
                    i24 = i36;
                }
                i27 = i16;
            }
        }
    }

    private void ssMergeCheckEqual(int i, int i2, int i3) {
        int[] iArr = this.f8351SA;
        if (iArr[i3] < 0 || ssCompare(getIDX(iArr[i3 - 1]) + i, i + iArr[i3], i2) != 0) {
            return;
        }
        iArr[i3] = ~iArr[i3];
    }

    private void ssMerge(int i, int i2, int i3, int i4, int[] iArr, int i5, int i6, int i7) {
        int i8;
        int i9;
        int i10;
        int i11;
        int[] iArr2 = this.f8351SA;
        StackEntry[] stackEntryArr = new StackEntry[64];
        int i12 = i2;
        int i13 = i3;
        int i14 = i4;
        int i15 = 0;
        int i16 = 0;
        while (true) {
            int i17 = i14 - i13;
            if (i17 <= i6) {
                if (i12 >= i13 || i13 >= i14) {
                    i8 = i12;
                } else {
                    i8 = i12;
                    ssMergeBackward(i, iArr, i5, i12, i13, i14, i7);
                }
                if ((i15 & 1) != 0) {
                    ssMergeCheckEqual(i, i7, i8);
                }
                if ((i15 & 2) != 0) {
                    ssMergeCheckEqual(i, i7, i14);
                }
                if (i16 == 0) {
                    return;
                }
                i16--;
                StackEntry stackEntry = stackEntryArr[i16];
                i12 = stackEntry.f8354a;
                i13 = stackEntry.f8355b;
                i14 = stackEntry.f8356c;
                i9 = stackEntry.f8357d;
            } else {
                int i18 = i12;
                int i19 = i13 - i18;
                if (i19 <= i6) {
                    if (i18 < i13) {
                        ssMergeForward(i, iArr, i5, i18, i13, i14, i7);
                    }
                    if ((i15 & 1) != 0) {
                        ssMergeCheckEqual(i, i7, i18);
                    }
                    if ((i15 & 2) != 0) {
                        ssMergeCheckEqual(i, i7, i14);
                    }
                    if (i16 == 0) {
                        return;
                    }
                    i16--;
                    StackEntry stackEntry2 = stackEntryArr[i16];
                    i12 = stackEntry2.f8354a;
                    i13 = stackEntry2.f8355b;
                    i14 = stackEntry2.f8356c;
                    i9 = stackEntry2.f8357d;
                } else {
                    int min = Math.min(i19, i17);
                    int i20 = min >> 1;
                    int i21 = 0;
                    while (min > 0) {
                        if (ssCompare(getIDX(iArr2[i13 + i21 + i20]) + i, getIDX(iArr2[((i13 - i21) - i20) - 1]) + i, i7) < 0) {
                            i21 += i20 + 1;
                            i20 -= (min & 1) ^ 1;
                        }
                        min = i20;
                        i20 = min >> 1;
                    }
                    if (i21 > 0) {
                        int i22 = i13 - i21;
                        ssBlockSwap(iArr2, i22, iArr2, i13, i21);
                        int i23 = i21 + i13;
                        if (i23 < i14) {
                            if (iArr2[i23] < 0) {
                                i10 = i13;
                                while (iArr2[i10 - 1] < 0) {
                                    i10--;
                                }
                                iArr2[i23] = ~iArr2[i23];
                            } else {
                                i10 = i13;
                            }
                            int i24 = i13;
                            while (iArr2[i24] < 0) {
                                i24++;
                            }
                            i12 = i24;
                            i11 = 1;
                        } else {
                            i10 = i13;
                            i12 = i10;
                            i11 = 0;
                        }
                        if (i10 - i18 <= i14 - i12) {
                            stackEntryArr[i16] = new StackEntry(i12, i23, i14, (i11 & 1) | (i15 & 2));
                            i15 &= 1;
                            i13 = i22;
                            i14 = i10;
                            i16++;
                            i12 = i18;
                        } else {
                            if (i10 == i13 && i13 == i12) {
                                i11 <<= 1;
                            }
                            stackEntryArr[i16] = new StackEntry(i18, i22, i10, (i15 & 1) | (i11 & 2));
                            i15 = (i15 & 2) | (1 & i11);
                            i13 = i23;
                            i16++;
                        }
                    } else {
                        if ((i15 & 1) != 0) {
                            ssMergeCheckEqual(i, i7, i18);
                        }
                        ssMergeCheckEqual(i, i7, i13);
                        if ((i15 & 2) != 0) {
                            ssMergeCheckEqual(i, i7, i14);
                        }
                        if (i16 == 0) {
                            return;
                        }
                        i16--;
                        StackEntry stackEntry3 = stackEntryArr[i16];
                        i12 = stackEntry3.f8354a;
                        i13 = stackEntry3.f8355b;
                        i14 = stackEntry3.f8356c;
                        i9 = stackEntry3.f8357d;
                    }
                }
            }
            i15 = i9;
        }
    }

    private void subStringSort(int i, int i2, int i3, int[] iArr, int i4, int i5, int i6, boolean z, int i7) {
        int i8;
        int i9;
        int[] iArr2;
        int[] iArr3 = this.f8351SA;
        int i10 = z ? i2 + 1 : i2;
        int i11 = 0;
        int i12 = i10;
        while (true) {
            int i13 = i12 + 1024;
            if (i13 >= i3) {
                break;
            }
            ssMultiKeyIntroSort(i, i12, i13, i6);
            int i14 = i3 - i13;
            if (i14 <= i5) {
                iArr2 = iArr;
                i9 = i4;
                i8 = i5;
            } else {
                i8 = i14;
                i9 = i13;
                iArr2 = iArr3;
            }
            int i15 = i12;
            int i16 = 1024;
            int i17 = i11;
            while ((i17 & 1) != 0) {
                int i18 = i15 - i16;
                ssMerge(i, i18, i15, i15 + i16, iArr2, i9, i8, i6);
                i16 <<= 1;
                i17 >>>= 1;
                i15 = i18;
                i13 = i13;
            }
            i11++;
            i12 = i13;
        }
        ssMultiKeyIntroSort(i, i12, i3, i6);
        int i19 = i12;
        int i20 = 1024;
        while (i11 != 0) {
            if ((i11 & 1) != 0) {
                int i21 = i19 - i20;
                ssMerge(i, i21, i19, i3, iArr, i4, i5, i6);
                i19 = i21;
            }
            i20 <<= 1;
            i11 >>= 1;
        }
        if (z) {
            int i22 = iArr3[i10 - 1];
            int i23 = 1;
            while (i10 < i3 && (iArr3[i10] < 0 || (i23 = ssCompareLast(i, i + i22, i + iArr3[i10], i6, i7)) > 0)) {
                iArr3[i10 - 1] = iArr3[i10];
                i10++;
            }
            if (i23 == 0) {
                iArr3[i10] = ~iArr3[i10];
            }
            iArr3[i10 - 1] = i22;
        }
    }

    private int trGetC(int i, int i2, int i3, int i4) {
        int i5 = i2 + i4;
        return i5 < i3 ? this.f8351SA[i5] : this.f8351SA[i + (((i2 - i) + i4) % (i3 - i))];
    }

    private void trFixdown(int i, int i2, int i3, int i4, int i5, int i6) {
        int[] iArr = this.f8351SA;
        int i7 = iArr[i4 + i5];
        int trGetC = trGetC(i, i2, i3, i7);
        while (true) {
            int i8 = (i5 * 2) + 1;
            if (i8 >= i6) {
                break;
            }
            int i9 = i8 + 1;
            int trGetC2 = trGetC(i, i2, i3, iArr[i4 + i8]);
            int trGetC3 = trGetC(i, i2, i3, iArr[i4 + i9]);
            if (trGetC2 < trGetC3) {
                i8 = i9;
                trGetC2 = trGetC3;
            }
            if (trGetC2 <= trGetC) {
                break;
            }
            iArr[i5 + i4] = iArr[i4 + i8];
            i5 = i8;
        }
        iArr[i4 + i5] = i7;
    }

    private void trHeapSort(int i, int i2, int i3, int i4, int i5) {
        int i6;
        int[] iArr = this.f8351SA;
        int i7 = i5 % 2;
        if (i7 == 0) {
            int i8 = i5 - 1;
            int i9 = (i8 / 2) + i4;
            int i10 = i4 + i8;
            if (trGetC(i, i2, i3, iArr[i9]) < trGetC(i, i2, i3, iArr[i10])) {
                swapElements(iArr, i10, iArr, i9);
            }
            i6 = i8;
        } else {
            i6 = i5;
        }
        for (int i11 = (i6 / 2) - 1; i11 >= 0; i11--) {
            trFixdown(i, i2, i3, i4, i11, i6);
        }
        if (i7 == 0) {
            swapElements(iArr, i4, iArr, i4 + i6);
            trFixdown(i, i2, i3, i4, 0, i6);
        }
        for (int i12 = i6 - 1; i12 > 0; i12--) {
            int i13 = iArr[i4];
            int i14 = i4 + i12;
            iArr[i4] = iArr[i14];
            trFixdown(i, i2, i3, i4, 0, i12);
            iArr[i14] = i13;
        }
    }

    private void trInsertionSort(int i, int i2, int i3, int i4, int i5) {
        int trGetC;
        int[] iArr = this.f8351SA;
        for (int i6 = i4 + 1; i6 < i5; i6++) {
            int i7 = iArr[i6];
            int i8 = i6 - 1;
            do {
                trGetC = trGetC(i, i2, i3, i7) - trGetC(i, i2, i3, iArr[i8]);
                if (trGetC >= 0) {
                    break;
                }
                do {
                    iArr[i8 + 1] = iArr[i8];
                    i8--;
                    if (i4 > i8) {
                        break;
                    }
                } while (iArr[i8] < 0);
            } while (i8 >= i4);
            if (trGetC == 0) {
                iArr[i8] = ~iArr[i8];
            }
            iArr[i8 + 1] = i7;
        }
    }

    private static int trLog(int i) {
        return ((-65536) & i) != 0 ? ((-16777216) & i) != 0 ? LOG_2_TABLE[(i >> 24) & 255] + 24 : LOG_2_TABLE[(i >> 16) & 271] : (65280 & i) != 0 ? LOG_2_TABLE[(i >> 8) & 255] + 8 : LOG_2_TABLE[i & 255];
    }

    private int trMedian3(int i, int i2, int i3, int i4, int i5, int i6) {
        int[] iArr = this.f8351SA;
        int trGetC = trGetC(i, i2, i3, iArr[i4]);
        int trGetC2 = trGetC(i, i2, i3, iArr[i5]);
        int trGetC3 = trGetC(i, i2, i3, iArr[i6]);
        if (trGetC <= trGetC2) {
            i5 = i4;
            i4 = i5;
            trGetC2 = trGetC;
            trGetC = trGetC2;
        }
        return trGetC > trGetC3 ? trGetC2 > trGetC3 ? i5 : i6 : i4;
    }

    private int trMedian5(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int[] iArr = this.f8351SA;
        int trGetC = trGetC(i, i2, i3, iArr[i4]);
        int trGetC2 = trGetC(i, i2, i3, iArr[i5]);
        int trGetC3 = trGetC(i, i2, i3, iArr[i6]);
        int trGetC4 = trGetC(i, i2, i3, iArr[i7]);
        int trGetC5 = trGetC(i, i2, i3, iArr[i8]);
        if (trGetC2 > trGetC3) {
            i6 = i5;
            i5 = i6;
            trGetC3 = trGetC2;
            trGetC2 = trGetC3;
        }
        if (trGetC4 <= trGetC5) {
            trGetC4 = trGetC5;
            trGetC5 = trGetC4;
            i8 = i7;
            i7 = i8;
        }
        if (trGetC2 > trGetC5) {
            trGetC5 = trGetC2;
            int i9 = i7;
            i7 = i6;
            i6 = i9;
            int i10 = trGetC4;
            trGetC4 = trGetC3;
            trGetC3 = i10;
        } else {
            i5 = i8;
        }
        if (trGetC > trGetC3) {
            int i11 = i6;
            i6 = i4;
            i4 = i11;
            int i12 = trGetC3;
            trGetC3 = trGetC;
            trGetC = i12;
        }
        if (trGetC > trGetC5) {
            i6 = i7;
            trGetC5 = trGetC;
            trGetC3 = trGetC4;
        } else {
            i4 = i5;
        }
        return trGetC3 > trGetC5 ? i4 : i6;
    }

    private int trPivot(int i, int i2, int i3, int i4, int i5) {
        int i6 = i5 - i4;
        int i7 = i4 + (i6 / 2);
        if (i6 <= 512) {
            if (i6 <= 32) {
                return trMedian3(i, i2, i3, i4, i7, i5 - 1);
            }
            int i8 = i6 >> 2;
            int i9 = i5 - 1;
            return trMedian5(i, i2, i3, i4, i4 + i8, i7, i9 - i8, i9);
        }
        int i10 = i6 >> 3;
        int i11 = i10 << 1;
        int i12 = i5 - 1;
        return trMedian3(i, i2, i3, trMedian3(i, i2, i3, i4, i4 + i10, i4 + i11), trMedian3(i, i2, i3, i7 - i10, i7, i7 + i10), trMedian3(i, i2, i3, i12 - i11, i12 - i10, i12));
    }

    private void lsUpdateGroup(int i, int i2, int i3) {
        int[] iArr = this.f8351SA;
        while (i2 < i3) {
            if (iArr[i2] >= 0) {
                int i4 = i2;
                do {
                    iArr[iArr[i4] + i] = i4;
                    i4++;
                    if (i4 >= i3) {
                        break;
                    }
                } while (iArr[i4] >= 0);
                iArr[i2] = i2 - i4;
                if (i3 <= i4) {
                    return;
                } else {
                    i2 = i4;
                }
            }
            int i5 = i2;
            do {
                iArr[i5] = ~iArr[i5];
                i5++;
            } while (iArr[i5] < 0);
            do {
                iArr[iArr[i2] + i] = i5;
                i2++;
            } while (i2 <= i5);
            i2 = i5 + 1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01be A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0133 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:68:0x0127 -> B:54:0x00fb). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void lsIntroSort(int i, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int trGetC;
        int[] iArr = this.f8351SA;
        StackEntry[] stackEntryArr = new StackEntry[64];
        int trLog = trLog(i5 - i4);
        int i11 = i4;
        int i12 = i5;
        int i13 = 0;
        int i14 = 0;
        while (true) {
            int i15 = i12 - i11;
            if (i15 <= 8) {
                if (1 < i15) {
                    trInsertionSort(i, i2, i3, i11, i12);
                    lsUpdateGroup(i, i11, i12);
                } else if (i15 == 1) {
                    iArr[i11] = -1;
                }
                if (i13 == 0) {
                    return;
                }
                i13--;
                StackEntry stackEntry = stackEntryArr[i13];
                i11 = stackEntry.f8354a;
                i12 = stackEntry.f8355b;
                trLog = stackEntry.f8356c;
            } else {
                int i16 = trLog - 1;
                if (trLog == 0) {
                    trHeapSort(i, i2, i3, i11, i15);
                    int i17 = i12 - 1;
                    while (i11 < i17) {
                        int trGetC2 = trGetC(i, i2, i3, iArr[i17]);
                        i17--;
                        while (i11 <= i17 && trGetC(i, i2, i3, iArr[i17]) == trGetC2) {
                            iArr[i17] = ~iArr[i17];
                            i17--;
                        }
                        i14 = trGetC2;
                    }
                    lsUpdateGroup(i, i11, i12);
                    if (i13 == 0) {
                        return;
                    }
                    i13--;
                    StackEntry stackEntry2 = stackEntryArr[i13];
                    i11 = stackEntry2.f8354a;
                    i12 = stackEntry2.f8355b;
                    trLog = stackEntry2.f8356c;
                } else {
                    swapElements(iArr, i11, iArr, trPivot(i, i2, i3, i11, i12));
                    int trGetC3 = trGetC(i, i2, i3, iArr[i11]);
                    int i18 = i11 + 1;
                    while (true) {
                        if (i18 >= i12) {
                            i6 = i14;
                            break;
                        }
                        i6 = trGetC(i, i2, i3, iArr[i18]);
                        if (i6 != trGetC3) {
                            break;
                        }
                        i18++;
                        i14 = i6;
                    }
                    if (i18 < i12 && i6 < trGetC3) {
                        i7 = i18;
                        while (true) {
                            i18++;
                            if (i18 >= i12 || (i6 = trGetC(i, i2, i3, iArr[i18])) > trGetC3) {
                                break;
                            } else if (i6 == trGetC3) {
                                swapElements(iArr, i18, iArr, i7);
                                i7++;
                            }
                        }
                    } else {
                        i7 = i18;
                    }
                    int i19 = i12 - 1;
                    while (i18 < i19) {
                        i6 = trGetC(i, i2, i3, iArr[i19]);
                        if (i6 != trGetC3) {
                            break;
                        } else {
                            i19--;
                        }
                    }
                    if (i18 >= i19 || i6 <= trGetC3) {
                        i14 = i6;
                        i8 = i19;
                        while (i18 < i19) {
                            swapElements(iArr, i18, iArr, i19);
                            while (true) {
                                i18++;
                                if (i18 >= i19) {
                                    break;
                                }
                                int trGetC4 = trGetC(i, i2, i3, iArr[i18]);
                                if (trGetC4 > trGetC3) {
                                    i14 = trGetC4;
                                    break;
                                }
                                if (trGetC4 == trGetC3) {
                                    swapElements(iArr, i18, iArr, i7);
                                    i7++;
                                }
                                i14 = trGetC4;
                            }
                            while (true) {
                                i19--;
                                if (i18 >= i19) {
                                    break;
                                }
                                trGetC = trGetC(i, i2, i3, iArr[i19]);
                                if (trGetC < trGetC3) {
                                    break;
                                }
                                if (trGetC == trGetC3) {
                                    swapElements(iArr, i19, iArr, i8);
                                    i8--;
                                    i14 = trGetC;
                                } else {
                                    i14 = trGetC;
                                }
                            }
                            i14 = trGetC;
                            while (i18 < i19) {
                            }
                        }
                        if (i7 > i8) {
                            int i20 = i18 - 1;
                            int i21 = i7 - i11;
                            int i22 = i18 - i7;
                            if (i21 > i22) {
                                i21 = i22;
                            }
                            int i23 = i18 - i21;
                            int i24 = i11;
                            while (i21 > 0) {
                                swapElements(iArr, i24, iArr, i23);
                                i21--;
                                i24++;
                                i23++;
                            }
                            int i25 = i8 - i20;
                            int i26 = (i12 - i8) - 1;
                            if (i25 <= i26) {
                                i26 = i25;
                            }
                            int i27 = i12 - i26;
                            while (i26 > 0) {
                                swapElements(iArr, i18, iArr, i27);
                                i26--;
                                i18++;
                                i27++;
                            }
                            int i28 = i11 + i22;
                            int i29 = i12 - i25;
                            int i30 = i28 - 1;
                            for (int i31 = i11; i31 < i28; i31++) {
                                iArr[iArr[i31] + i] = i30;
                            }
                            if (i29 < i12) {
                                int i32 = i29 - 1;
                                for (int i33 = i28; i33 < i29; i33++) {
                                    iArr[iArr[i33] + i] = i32;
                                }
                            }
                            if (i29 - i28 == 1) {
                                iArr[i28] = -1;
                            }
                            if (i28 - i11 > i12 - i29) {
                                i9 = i16;
                                if (i29 < i12) {
                                    i10 = i13 + 1;
                                    stackEntryArr[i13] = new StackEntry(i11, i28, i9, 0);
                                    i11 = i29;
                                    i13 = i10;
                                    trLog = i9;
                                } else {
                                    i12 = i28;
                                    trLog = i9;
                                }
                            } else if (i11 < i28) {
                                i10 = i13 + 1;
                                i9 = i16;
                                stackEntryArr[i13] = new StackEntry(i29, i12, i9, 0);
                                i12 = i28;
                                i13 = i10;
                                trLog = i9;
                            } else {
                                i9 = i16;
                                i11 = i29;
                                trLog = i9;
                            }
                        } else {
                            if (i13 == 0) {
                                return;
                            }
                            i13--;
                            StackEntry stackEntry3 = stackEntryArr[i13];
                            i11 = stackEntry3.f8354a;
                            i12 = stackEntry3.f8355b;
                            trLog = stackEntry3.f8356c;
                        }
                    } else {
                        trGetC = i6;
                        i8 = i19;
                        while (true) {
                            i19--;
                            if (i18 >= i19 || (trGetC = trGetC(i, i2, i3, iArr[i19])) < trGetC3) {
                                break;
                            } else if (trGetC == trGetC3) {
                                swapElements(iArr, i19, iArr, i8);
                                i8--;
                            }
                        }
                        i14 = trGetC;
                        while (i18 < i19) {
                        }
                        if (i7 > i8) {
                        }
                    }
                }
            }
        }
    }

    private void lsSort(int i, int i2, int i3) {
        int i4;
        int[] iArr = this.f8351SA;
        int i5 = i3 + i;
        while (true) {
            int i6 = 0;
            if ((-i2) >= iArr[0]) {
                return;
            }
            int i7 = 0;
            int i8 = 0;
            do {
                int i9 = iArr[i8];
                if (i9 < 0) {
                    i8 -= i9;
                    i7 += i9;
                } else {
                    if (i7 != 0) {
                        iArr[i8 + i7] = i7;
                        i4 = 0;
                    } else {
                        i4 = i7;
                    }
                    int i10 = iArr[i9 + i] + 1;
                    lsIntroSort(i, i5, i + i2, i8, i10);
                    i7 = i4;
                    i8 = i10;
                }
            } while (i8 < i2);
            if (i7 != 0) {
                iArr[i8 + i7] = i7;
            }
            int i11 = i5 - i;
            if (i2 < i11) {
                do {
                    int i12 = iArr[i6];
                    if (i12 < 0) {
                        i6 -= i12;
                    } else {
                        int i13 = iArr[i12 + i] + 1;
                        while (i6 < i13) {
                            iArr[iArr[i6] + i] = i6;
                            i6++;
                        }
                        i6 = i13;
                    }
                } while (i6 < i2);
                return;
            }
            i5 += i11;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static class PartitionResult {
        final int first;
        final int last;

        PartitionResult(int i, int i2) {
            this.first = i;
            this.last = i2;
        }
    }

    private PartitionResult trPartition(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7;
        int i8;
        int trGetC;
        int trGetC2;
        int trGetC3;
        int[] iArr = this.f8351SA;
        int i9 = 0;
        int i10 = i4;
        while (i10 < i5) {
            i9 = trGetC(i, i2, i3, iArr[i10]);
            if (i9 != i6) {
                break;
            }
            i10++;
        }
        if (i10 < i5 && i9 < i6) {
            i7 = i10;
            while (true) {
                i10++;
                if (i10 >= i5 || (i9 = trGetC(i, i2, i3, iArr[i10])) > i6) {
                    break;
                }
                if (i9 == i6) {
                    swapElements(iArr, i10, iArr, i7);
                    i7++;
                }
            }
        } else {
            i7 = i10;
        }
        int i11 = i5 - 1;
        while (i10 < i11) {
            i9 = trGetC(i, i2, i3, iArr[i11]);
            if (i9 != i6) {
                break;
            }
            i11--;
        }
        if (i10 < i11 && i9 > i6) {
            i8 = i11;
            while (true) {
                i11--;
                if (i10 >= i11 || (trGetC3 = trGetC(i, i2, i3, iArr[i11])) < i6) {
                    break;
                }
                if (trGetC3 == i6) {
                    swapElements(iArr, i11, iArr, i8);
                    i8--;
                }
            }
        } else {
            i8 = i11;
        }
        while (i10 < i11) {
            swapElements(iArr, i10, iArr, i11);
            while (true) {
                i10++;
                if (i10 >= i11 || (trGetC2 = trGetC(i, i2, i3, iArr[i10])) > i6) {
                    break;
                }
                if (trGetC2 == i6) {
                    swapElements(iArr, i10, iArr, i7);
                    i7++;
                }
            }
            while (true) {
                i11--;
                if (i10 < i11 && (trGetC = trGetC(i, i2, i3, iArr[i11])) >= i6) {
                    if (trGetC == i6) {
                        swapElements(iArr, i11, iArr, i8);
                        i8--;
                    }
                }
            }
        }
        if (i7 <= i8) {
            int i12 = i10 - 1;
            int i13 = i7 - i4;
            int i14 = i10 - i7;
            if (i13 > i14) {
                i13 = i14;
            }
            int i15 = i10 - i13;
            int i16 = i4;
            while (i13 > 0) {
                swapElements(iArr, i16, iArr, i15);
                i13--;
                i16++;
                i15++;
            }
            int i17 = i8 - i12;
            int i18 = (i5 - i8) - 1;
            if (i17 <= i18) {
                i18 = i17;
            }
            int i19 = i5 - i18;
            while (i18 > 0) {
                swapElements(iArr, i10, iArr, i19);
                i18--;
                i10++;
                i19++;
            }
            i4 += i14;
            i5 -= i17;
        }
        return new PartitionResult(i4, i5);
    }

    private void trCopy(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        int[] iArr = this.f8351SA;
        int i8 = i5 - 1;
        int i9 = i4 - 1;
        while (i3 <= i9) {
            int i10 = iArr[i3] - i7;
            if (i10 < 0) {
                i10 += i2 - i;
            }
            int i11 = i + i10;
            if (iArr[i11] == i8) {
                i9++;
                iArr[i9] = i10;
                iArr[i11] = i9;
            }
            i3++;
        }
        int i12 = i6 - 1;
        int i13 = i9 + 1;
        while (i13 < i5) {
            int i14 = iArr[i12] - i7;
            if (i14 < 0) {
                i14 += i2 - i;
            }
            int i15 = i + i14;
            if (iArr[i15] == i8) {
                i5--;
                iArr[i5] = i14;
                iArr[i15] = i5;
            }
            i12--;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:259:0x015a, code lost:
    
        if (r13[r7] >= 0) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:260:0x015c, code lost:
    
        r13[r13[r7] + r23] = r7;
        r7 = r7 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:261:0x0162, code lost:
    
        if (r7 >= r8) goto L347;
     */
    /* JADX WARN: Code restructure failed: missing block: B:263:0x0166, code lost:
    
        if (r13[r7] >= 0) goto L349;
     */
    /* JADX WARN: Code restructure failed: missing block: B:267:0x0168, code lost:
    
        if (r7 >= r8) goto L267;
     */
    /* JADX WARN: Code restructure failed: missing block: B:268:0x016a, code lost:
    
        r0 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:269:0x016b, code lost:
    
        r13[r0] = ~r13[r0];
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:270:0x0173, code lost:
    
        if (r13[r0] < 0) goto L351;
     */
    /* JADX WARN: Code restructure failed: missing block: B:273:0x0180, code lost:
    
        if (r13[r13[r0] + r23] == r13[r6 + r13[r0]]) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:274:0x0182, code lost:
    
        r1 = trLog((r0 - r7) + 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:275:0x018b, code lost:
    
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:276:0x018d, code lost:
    
        if (r0 >= r8) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:277:0x018f, code lost:
    
        r2 = r0 - 1;
        r3 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:278:0x0192, code lost:
    
        if (r3 >= r0) goto L352;
     */
    /* JADX WARN: Code restructure failed: missing block: B:279:0x0194, code lost:
    
        r13[r13[r3] + r23] = r2;
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:281:0x019c, code lost:
    
        r3 = r8 - r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:282:0x01a0, code lost:
    
        if ((r0 - r7) > r3) goto L282;
     */
    /* JADX WARN: Code restructure failed: missing block: B:284:0x01a2, code lost:
    
        r3 = r15 + 1;
        r14[r15] = new io.grpc.netty.shaded.io.netty.handler.codec.compression.Bzip2DivSufSort.StackEntry(r6, r0, r8, -3);
        r4 = r6 + 1;
        r8 = r22;
        r6 = r0;
        r0 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:286:0x01b5, code lost:
    
        if (1 >= r3) goto L253;
     */
    /* JADX WARN: Code restructure failed: missing block: B:288:0x01c7, code lost:
    
        r4 = r6 + 1;
        r8 = r22;
        r6 = r0;
        r0 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:290:0x01b7, code lost:
    
        r3 = r15 + 1;
        r14[r15] = new io.grpc.netty.shaded.io.netty.handler.codec.compression.Bzip2DivSufSort.StackEntry(r6 + 1, r7, r0, r1);
        r5 = r0;
        r4 = r6;
        r6 = r8;
        r0 = -3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:291:0x018a, code lost:
    
        r1 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:294:0x01cf, code lost:
    
        if (r15 != 0) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:295:0x01d2, code lost:
    
        r3 = r15 - 1;
        r0 = r14[r3];
        r4 = r0.f8354a;
        r5 = r0.f8355b;
        r6 = r0.f8356c;
        r0 = r0.f8357d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:297:0x01d1, code lost:
    
        return;
     */
    /* JADX WARN: Removed duplicated region for block: B:51:0x02db  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:154:0x0300 -> B:140:0x02d3). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void trIntroSort(int i, int i2, int i3, int i4, int i5, TRBudget tRBudget, int i6) {
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int trGetC;
        Bzip2DivSufSort bzip2DivSufSort = this;
        int i20 = i3;
        TRBudget tRBudget2 = tRBudget;
        int i21 = i6;
        int[] iArr = bzip2DivSufSort.f8351SA;
        StackEntry[] stackEntryArr = new StackEntry[64];
        int trLog = trLog(i5 - i4);
        int i22 = i2;
        int i23 = i4;
        int i24 = i5;
        int i25 = 0;
        int i26 = 0;
        while (true) {
            if (trLog < 0) {
                if (trLog != -1) {
                    i7 = i25;
                    i14 = i23;
                    int i27 = i24;
                    int i28 = i22;
                    if (trLog == -2) {
                        int i29 = i7 - 1;
                        trCopy(i, i3, i14, stackEntryArr[i29].f8355b, stackEntryArr[i29].f8356c, i27, i28 - i);
                        if (i29 == 0) {
                            return;
                        }
                        i25 = i29 - 1;
                        StackEntry stackEntry = stackEntryArr[i25];
                        i22 = stackEntry.f8354a;
                        i23 = stackEntry.f8355b;
                        i24 = stackEntry.f8356c;
                        trLog = stackEntry.f8357d;
                    }
                } else {
                    if (!tRBudget2.update(i21, i24 - i23)) {
                        i7 = i25;
                        i8 = 0;
                        break;
                    }
                    int i30 = i22 - 1;
                    int i31 = i25;
                    int i32 = i22;
                    int i33 = i23;
                    int i34 = i24;
                    PartitionResult trPartition = trPartition(i, i30, i3, i23, i24, i24 - 1);
                    int i35 = trPartition.first;
                    int i36 = trPartition.last;
                    if (i33 < i35 || i36 < i34) {
                        if (i35 < i34) {
                            int i37 = i35 - 1;
                            for (int i38 = i33; i38 < i35; i38++) {
                                iArr[iArr[i38] + i] = i37;
                            }
                        }
                        if (i36 < i34) {
                            int i39 = i36 - 1;
                            for (int i40 = i35; i40 < i36; i40++) {
                                iArr[iArr[i40] + i] = i39;
                            }
                        }
                        int i41 = i31 + 1;
                        stackEntryArr[i31] = new StackEntry(0, i35, i36, 0);
                        int i42 = i41 + 1;
                        stackEntryArr[i41] = new StackEntry(i30, i33, i34, -2);
                        int i43 = i35 - i33;
                        int i44 = i34 - i36;
                        if (i43 > i44) {
                            i9 = i32;
                            if (1 < i44) {
                                stackEntryArr[i42] = new StackEntry(i9, i33, i35, trLog(i43));
                                int trLog2 = trLog(i44);
                                i25 = i42 + 1;
                                i22 = i9;
                                i24 = i34;
                                i23 = i36;
                                trLog = trLog2;
                            } else if (1 < i43) {
                                trLog = trLog(i43);
                                i25 = i42;
                                i22 = i9;
                                i23 = i33;
                                i24 = i35;
                            } else {
                                if (i42 == 0) {
                                    return;
                                }
                                i10 = i42 - 1;
                                StackEntry stackEntry2 = stackEntryArr[i10];
                                i11 = stackEntry2.f8354a;
                                i12 = stackEntry2.f8355b;
                                i13 = stackEntry2.f8356c;
                                trLog = stackEntry2.f8357d;
                                i23 = i12;
                                i24 = i13;
                                i22 = i11;
                                i25 = i10;
                            }
                        } else if (1 < i43) {
                            i9 = i32;
                            stackEntryArr[i42] = new StackEntry(i9, i36, i34, trLog(i44));
                            trLog = trLog(i43);
                            i25 = i42 + 1;
                            i22 = i9;
                            i23 = i33;
                            i24 = i35;
                        } else if (1 < i44) {
                            i23 = i36;
                            trLog = trLog(i44);
                            i25 = i42;
                            i22 = i32;
                            i24 = i34;
                        } else {
                            if (i42 == 0) {
                                return;
                            }
                            i10 = i42 - 1;
                            StackEntry stackEntry3 = stackEntryArr[i10];
                            i11 = stackEntry3.f8354a;
                            i12 = stackEntry3.f8355b;
                            i13 = stackEntry3.f8356c;
                            trLog = stackEntry3.f8357d;
                            i23 = i12;
                            i24 = i13;
                            i22 = i11;
                            i25 = i10;
                        }
                    } else {
                        while (i33 < i34) {
                            iArr[iArr[i33] + i] = i33;
                            i33++;
                        }
                        if (i31 == 0) {
                            return;
                        }
                        i25 = i31 - 1;
                        StackEntry stackEntry4 = stackEntryArr[i25];
                        int i45 = stackEntry4.f8354a;
                        int i46 = stackEntry4.f8355b;
                        int i47 = stackEntry4.f8356c;
                        trLog = stackEntry4.f8357d;
                        i23 = i46;
                        i24 = i47;
                        i22 = i45;
                    }
                }
                bzip2DivSufSort = this;
            } else {
                i7 = i25;
                i14 = i23;
                int i48 = i24;
                i8 = 0;
                int i49 = i22;
                int i50 = i48 - i14;
                if (i50 > 8) {
                    int i51 = trLog - 1;
                    if (trLog == 0) {
                        if (!tRBudget2.update(i21, i50)) {
                            break;
                        }
                        trHeapSort(i, i49, i3, i14, i50);
                        int i52 = i48 - 1;
                        while (i14 < i52) {
                            int i53 = i48;
                            int trGetC2 = trGetC(i, i49, i20, iArr[i52]);
                            i52--;
                            while (i14 <= i52 && trGetC(i, i49, i20, iArr[i52]) == trGetC2) {
                                iArr[i52] = ~iArr[i52];
                                i52--;
                            }
                            i26 = trGetC2;
                            i48 = i53;
                        }
                        i15 = i48;
                        trLog = -3;
                        bzip2DivSufSort = this;
                        i23 = i14;
                        i25 = i7;
                    } else {
                        bzip2DivSufSort = this;
                        swapElements(iArr, i14, iArr, trPivot(i, i49, i3, i14, i48));
                        int trGetC3 = bzip2DivSufSort.trGetC(i, i49, i20, iArr[i14]);
                        int i54 = i14 + 1;
                        i15 = i48;
                        while (true) {
                            if (i54 >= i15) {
                                i16 = i26;
                                break;
                            }
                            i16 = bzip2DivSufSort.trGetC(i, i49, i20, iArr[i54]);
                            if (i16 != trGetC3) {
                                break;
                            }
                            i54++;
                            i26 = i16;
                        }
                        if (i54 < i15 && i16 < trGetC3) {
                            i17 = i54;
                            while (true) {
                                i54++;
                                if (i54 >= i15 || (i16 = bzip2DivSufSort.trGetC(i, i49, i20, iArr[i54])) > trGetC3) {
                                    break;
                                } else if (i16 == trGetC3) {
                                    swapElements(iArr, i54, iArr, i17);
                                    i17++;
                                }
                            }
                        } else {
                            i17 = i54;
                        }
                        int i55 = i15 - 1;
                        while (i54 < i55) {
                            i16 = bzip2DivSufSort.trGetC(i, i49, i20, iArr[i55]);
                            if (i16 != trGetC3) {
                                break;
                            } else {
                                i55--;
                            }
                        }
                        if (i54 < i55 && i16 > trGetC3) {
                            i26 = i16;
                            i18 = i55;
                            while (true) {
                                i55--;
                                if (i54 >= i55) {
                                    break;
                                }
                                trGetC = bzip2DivSufSort.trGetC(i, i49, i20, iArr[i55]);
                                if (trGetC < trGetC3) {
                                    break;
                                }
                                if (trGetC == trGetC3) {
                                    swapElements(iArr, i55, iArr, i18);
                                    i18--;
                                }
                                i26 = trGetC;
                            }
                        } else {
                            i26 = i16;
                            i18 = i55;
                        }
                        while (i54 < i55) {
                            swapElements(iArr, i54, iArr, i55);
                            while (true) {
                                i54++;
                                if (i54 >= i55) {
                                    break;
                                }
                                int trGetC4 = bzip2DivSufSort.trGetC(i, i49, i20, iArr[i54]);
                                if (trGetC4 > trGetC3) {
                                    i26 = trGetC4;
                                    break;
                                }
                                if (trGetC4 == trGetC3) {
                                    swapElements(iArr, i54, iArr, i17);
                                    i17++;
                                }
                                i26 = trGetC4;
                            }
                            while (true) {
                                i55--;
                                if (i54 >= i55) {
                                    break;
                                }
                                trGetC = bzip2DivSufSort.trGetC(i, i49, i20, iArr[i55]);
                                if (trGetC < trGetC3) {
                                    break;
                                }
                                if (trGetC == trGetC3) {
                                    swapElements(iArr, i55, iArr, i18);
                                    i18--;
                                    i26 = trGetC;
                                } else {
                                    i26 = trGetC;
                                }
                            }
                            i26 = trGetC;
                            while (i54 < i55) {
                            }
                        }
                        if (i17 <= i18) {
                            int i56 = i54 - 1;
                            int i57 = i17 - i14;
                            int i58 = i54 - i17;
                            if (i57 > i58) {
                                i57 = i58;
                            }
                            int i59 = i54 - i57;
                            int i60 = i54;
                            int i61 = i14;
                            while (i57 > 0) {
                                swapElements(iArr, i61, iArr, i59);
                                i57--;
                                i61++;
                                i59++;
                            }
                            int i62 = i18 - i56;
                            int i63 = (i15 - i18) - 1;
                            if (i62 <= i63) {
                                i63 = i62;
                            }
                            int i64 = i15 - i63;
                            int i65 = i60;
                            while (i63 > 0) {
                                swapElements(iArr, i65, iArr, i64);
                                i63--;
                                i65++;
                                i64++;
                            }
                            i23 = i14 + i58;
                            int i66 = i15 - i62;
                            trLog = iArr[iArr[i23] + i] != trGetC3 ? trLog(i66 - i23) : -1;
                            int i67 = i23 - 1;
                            for (int i68 = i14; i68 < i23; i68++) {
                                iArr[iArr[i68] + i] = i67;
                            }
                            if (i66 < i15) {
                                int i69 = i66 - 1;
                                for (int i70 = i23; i70 < i66; i70++) {
                                    iArr[iArr[i70] + i] = i69;
                                }
                            }
                            int i71 = i23 - i14;
                            int i72 = i15 - i66;
                            if (i71 <= i72) {
                                int i73 = i66 - i23;
                                if (i72 <= i73) {
                                    if (1 < i71) {
                                        int i74 = i7 + 1;
                                        stackEntryArr[i7] = new StackEntry(i49 + 1, i23, i66, trLog);
                                        stackEntryArr[i74] = new StackEntry(i49, i66, i15, i51);
                                        i20 = i3;
                                        i21 = i6;
                                        i25 = i74 + 1;
                                    } else if (1 < i72) {
                                        i25 = i7 + 1;
                                        stackEntryArr[i7] = new StackEntry(i49 + 1, i23, i66, trLog);
                                        i20 = i3;
                                        i21 = i6;
                                        i23 = i66;
                                        trLog = i51;
                                        tRBudget2 = tRBudget;
                                    } else if (1 < i73) {
                                        i22 = i49 + 1;
                                        i20 = i3;
                                        tRBudget2 = tRBudget;
                                        i21 = i6;
                                        i24 = i66;
                                        i25 = i7;
                                    } else {
                                        if (i7 == 0) {
                                            return;
                                        }
                                        i25 = i7 - 1;
                                        StackEntry stackEntry5 = stackEntryArr[i25];
                                        i22 = stackEntry5.f8354a;
                                        i23 = stackEntry5.f8355b;
                                        i24 = stackEntry5.f8356c;
                                        trLog = stackEntry5.f8357d;
                                        i20 = i3;
                                        tRBudget2 = tRBudget;
                                        i21 = i6;
                                    }
                                } else if (i71 <= i73) {
                                    if (1 < i71) {
                                        int i75 = i7 + 1;
                                        stackEntryArr[i7] = new StackEntry(i49, i66, i15, i51);
                                        stackEntryArr[i75] = new StackEntry(i49 + 1, i23, i66, trLog);
                                        i20 = i3;
                                        i21 = i6;
                                        i25 = i75 + 1;
                                    } else if (1 < i73) {
                                        i25 = i7 + 1;
                                        stackEntryArr[i7] = new StackEntry(i49, i66, i15, i51);
                                        i22 = i49 + 1;
                                        i20 = i3;
                                        tRBudget2 = tRBudget;
                                        i21 = i6;
                                        i24 = i66;
                                    } else {
                                        i20 = i3;
                                        i21 = i6;
                                        i23 = i66;
                                        trLog = i51;
                                        i25 = i7;
                                        tRBudget2 = tRBudget;
                                    }
                                } else if (1 < i73) {
                                    int i76 = i7 + 1;
                                    stackEntryArr[i7] = new StackEntry(i49, i66, i15, i51);
                                    i19 = i76 + 1;
                                    stackEntryArr[i76] = new StackEntry(i49, i14, i23, i51);
                                    i22 = i49 + 1;
                                    i20 = i3;
                                    tRBudget2 = tRBudget;
                                    i21 = i6;
                                    i24 = i66;
                                    i25 = i19;
                                } else {
                                    i25 = i7 + 1;
                                    stackEntryArr[i7] = new StackEntry(i49, i66, i15, i51);
                                    i20 = i3;
                                    i21 = i6;
                                }
                                i22 = i49;
                                trLog = i51;
                                tRBudget2 = tRBudget;
                                i24 = i23;
                                i23 = i14;
                            } else {
                                int i77 = i66 - i23;
                                if (i71 <= i77) {
                                    if (1 < i72) {
                                        int i78 = i7 + 1;
                                        stackEntryArr[i7] = new StackEntry(i49 + 1, i23, i66, trLog);
                                        stackEntryArr[i78] = new StackEntry(i49, i14, i23, i51);
                                        i20 = i3;
                                        i21 = i6;
                                        i25 = i78 + 1;
                                        i23 = i66;
                                    } else if (1 < i71) {
                                        i25 = i7 + 1;
                                        stackEntryArr[i7] = new StackEntry(i49 + 1, i23, i66, trLog);
                                        i20 = i3;
                                        i21 = i6;
                                        i22 = i49;
                                        trLog = i51;
                                        tRBudget2 = tRBudget;
                                        i24 = i23;
                                        i23 = i14;
                                    } else if (1 < i77) {
                                        i22 = i49 + 1;
                                        i20 = i3;
                                        tRBudget2 = tRBudget;
                                        i21 = i6;
                                        i24 = i66;
                                        i25 = i7;
                                    } else {
                                        i25 = i7 + 1;
                                        stackEntryArr[i7] = new StackEntry(i49, i14, i15, i51);
                                        i20 = i3;
                                        i21 = i6;
                                        i23 = i14;
                                    }
                                } else if (i72 <= i77) {
                                    if (1 < i72) {
                                        int i79 = i7 + 1;
                                        stackEntryArr[i7] = new StackEntry(i49, i14, i23, i51);
                                        stackEntryArr[i79] = new StackEntry(i49 + 1, i23, i66, trLog);
                                        i20 = i3;
                                        i21 = i6;
                                        i23 = i66;
                                        i25 = i79 + 1;
                                    } else if (1 < i77) {
                                        i25 = i7 + 1;
                                        stackEntryArr[i7] = new StackEntry(i49, i14, i23, i51);
                                        i22 = i49 + 1;
                                        i20 = i3;
                                        tRBudget2 = tRBudget;
                                        i21 = i6;
                                        i24 = i66;
                                    } else {
                                        i20 = i3;
                                        i21 = i6;
                                        i22 = i49;
                                        trLog = i51;
                                        i25 = i7;
                                        tRBudget2 = tRBudget;
                                        i24 = i23;
                                        i23 = i14;
                                    }
                                } else if (1 < i77) {
                                    int i80 = i7 + 1;
                                    stackEntryArr[i7] = new StackEntry(i49, i14, i23, i51);
                                    i19 = i80 + 1;
                                    stackEntryArr[i80] = new StackEntry(i49, i66, i15, i51);
                                    i22 = i49 + 1;
                                    i20 = i3;
                                    tRBudget2 = tRBudget;
                                    i21 = i6;
                                    i24 = i66;
                                    i25 = i19;
                                } else {
                                    i25 = i7 + 1;
                                    stackEntryArr[i7] = new StackEntry(i49, i14, i23, i51);
                                    i20 = i3;
                                    i21 = i6;
                                    i23 = i66;
                                }
                                trLog = i51;
                                tRBudget2 = tRBudget;
                            }
                        } else {
                            if (!tRBudget.update(i6, i50)) {
                                break;
                            }
                            int i81 = i49 + 1;
                            i20 = i3;
                            tRBudget2 = tRBudget;
                            i21 = i6;
                            trLog = i51 + 1;
                            i24 = i15;
                            i22 = i81;
                            i23 = i14;
                            i25 = i7;
                        }
                    }
                    i24 = i15;
                    i22 = i49;
                } else {
                    if (!tRBudget2.update(i21, i50)) {
                        break;
                    }
                    trInsertionSort(i, i49, i3, i14, i48);
                    trLog = -3;
                    i22 = i49;
                    i23 = i14;
                    i24 = i48;
                    i25 = i7;
                    bzip2DivSufSort = this;
                }
            }
        }
        bzip2DivSufSort = this;
        for (int i82 = i8; i82 < i7; i82++) {
            if (stackEntryArr[i82].f8357d == -3) {
                bzip2DivSufSort.lsUpdateGroup(i, stackEntryArr[i82].f8355b, stackEntryArr[i82].f8356c);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static class TRBudget {
        int budget;
        int chance;

        TRBudget(int i, int i2) {
            this.budget = i;
            this.chance = i2;
        }

        boolean update(int i, int i2) {
            this.budget -= i2;
            int i3 = this.budget;
            if (i3 <= 0) {
                int i4 = this.chance - 1;
                this.chance = i4;
                if (i4 == 0) {
                    return false;
                }
                this.budget = i3 + i;
            }
            return true;
        }
    }

    private void trSort(int i, int i2, int i3) {
        int[] iArr = this.f8351SA;
        if ((-i2) < iArr[0]) {
            TRBudget tRBudget = new TRBudget(i2, ((trLog(i2) * 2) / 3) + 1);
            int i4 = 0;
            do {
                int i5 = iArr[i4];
                if (i5 < 0) {
                    i4 -= i5;
                } else {
                    int i6 = iArr[i + i5] + 1;
                    if (1 < i6 - i4) {
                        trIntroSort(i, i + i3, i + i2, i4, i6, tRBudget, i2);
                        if (tRBudget.chance == 0) {
                            if (i4 > 0) {
                                iArr[0] = -i4;
                            }
                            lsSort(i, i2, i3);
                            return;
                        }
                    }
                    i4 = i6;
                }
            } while (i4 < i2);
        }
    }

    private int sortTypeBstar(int[] iArr, int[] iArr2) {
        boolean z;
        int i;
        boolean z2;
        int i2;
        int i3;
        int i4;
        int i5;
        int[] iArr3;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        byte[] bArr = this.f8352T;
        int[] iArr4 = this.f8351SA;
        int i14 = this.f8353n;
        int[] iArr5 = new int[256];
        int i15 = 1;
        while (true) {
            z = false;
            i = 255;
            if (i15 >= i14) {
                break;
            }
            int i16 = i15 - 1;
            if (bArr[i16] == bArr[i15]) {
                i15++;
            } else if ((bArr[i16] & 255) > (bArr[i15] & 255)) {
                z2 = false;
            }
        }
        z2 = true;
        int i17 = i14 - 1;
        int i18 = bArr[i17] & 255;
        int i19 = bArr[0] & 255;
        if (i18 < i19 || (bArr[i17] == bArr[0] && z2)) {
            if (!z2) {
                int BUCKET_BSTAR = BUCKET_BSTAR(i18, i19);
                iArr2[BUCKET_BSTAR] = iArr2[BUCKET_BSTAR] + 1;
                i2 = i14 - 1;
                iArr4[i2] = i17;
            } else {
                int BUCKET_B = BUCKET_B(i18, i19);
                iArr2[BUCKET_B] = iArr2[BUCKET_B] + 1;
                i2 = i14;
            }
            i3 = i17 - 1;
            while (i3 >= 0) {
                int i20 = bArr[i3] & 255;
                int i21 = bArr[i3 + 1] & 255;
                if (i20 <= i21) {
                    int BUCKET_B2 = BUCKET_B(i20, i21);
                    iArr2[BUCKET_B2] = iArr2[BUCKET_B2] + 1;
                    i3--;
                }
            }
        } else {
            i2 = i14;
            i3 = i17;
        }
        while (i3 >= 0) {
            do {
                int i22 = bArr[i3] & 255;
                iArr[i22] = iArr[i22] + 1;
                i3--;
                if (i3 < 0) {
                    break;
                }
            } while ((bArr[i3] & 255) >= (bArr[i3 + 1] & 255));
            if (i3 >= 0) {
                int BUCKET_BSTAR2 = BUCKET_BSTAR(bArr[i3] & 255, bArr[i3 + 1] & 255);
                iArr2[BUCKET_BSTAR2] = iArr2[BUCKET_BSTAR2] + 1;
                i2--;
                iArr4[i2] = i3;
                while (true) {
                    i3--;
                    if (i3 >= 0 && (i12 = bArr[i3] & 255) <= (i13 = bArr[i3 + 1] & 255)) {
                        int BUCKET_B3 = BUCKET_B(i12, i13);
                        iArr2[BUCKET_B3] = iArr2[BUCKET_B3] + 1;
                    }
                }
            }
        }
        int i23 = i14 - i2;
        if (i23 == 0) {
            for (int i24 = 0; i24 < i14; i24++) {
                iArr4[i24] = i24;
            }
            return 0;
        }
        int i25 = 0;
        int i26 = 0;
        int i27 = -1;
        while (i25 < 256) {
            int i28 = iArr[i25] + i27;
            iArr[i25] = i27 + i26;
            int i29 = i28 + iArr2[BUCKET_B(i25, i25)];
            int i30 = i25 + 1;
            int i31 = i26;
            for (int i32 = i30; i32 < 256; i32++) {
                i31 += iArr2[BUCKET_BSTAR(i25, i32)];
                iArr2[(i25 << 8) | i32] = i31;
                i29 += iArr2[BUCKET_B(i25, i32)];
            }
            i25 = i30;
            i27 = i29;
            i26 = i31;
        }
        int i33 = i14 - i23;
        for (int i34 = i23 - 2; i34 >= 0; i34--) {
            int i35 = iArr4[i33 + i34];
            int BUCKET_BSTAR3 = BUCKET_BSTAR(bArr[i35] & 255, bArr[i35 + 1] & 255);
            int i36 = iArr2[BUCKET_BSTAR3] - 1;
            iArr2[BUCKET_BSTAR3] = i36;
            iArr4[i36] = i34;
        }
        int i37 = iArr4[(i33 + i23) - 1];
        int BUCKET_BSTAR4 = BUCKET_BSTAR(bArr[i37] & 255, bArr[i37 + 1] & 255);
        int i38 = iArr2[BUCKET_BSTAR4] - 1;
        iArr2[BUCKET_BSTAR4] = i38;
        int i39 = i23 - 1;
        iArr4[i38] = i39;
        int i40 = i14 - (i23 * 2);
        if (i40 <= 256) {
            i4 = 256;
            iArr3 = iArr5;
            i5 = 0;
        } else {
            i4 = i40;
            i5 = i23;
            iArr3 = iArr4;
        }
        int i41 = i23;
        int i42 = 255;
        while (i41 > 0) {
            int i43 = i41;
            int i44 = i;
            while (i42 < i44) {
                int i45 = iArr2[BUCKET_BSTAR(i42, i44)];
                if (1 < i43 - i45) {
                    boolean z3 = iArr4[i45] == i39 ? true : z;
                    i8 = i44;
                    i9 = i42;
                    i10 = i39;
                    i11 = i23;
                    subStringSort(i33, i45, i43, iArr3, i5, i4, 2, z3, i14);
                } else {
                    i8 = i44;
                    i9 = i42;
                    i10 = i39;
                    i11 = i23;
                }
                i44 = i8 - 1;
                i23 = i11;
                i43 = i45;
                i42 = i9;
                i39 = i10;
                z = false;
            }
            i42--;
            i41 = i43;
            i = 255;
            z = false;
        }
        int i46 = i39;
        int i47 = i23;
        int i48 = i46;
        while (i48 >= 0) {
            if (iArr4[i48] >= 0) {
                int i49 = i48;
                do {
                    iArr4[i47 + iArr4[i49]] = i49;
                    i49--;
                    if (i49 < 0) {
                        break;
                    }
                } while (iArr4[i49] >= 0);
                iArr4[i49 + 1] = i49 - i48;
                if (i49 <= 0) {
                    break;
                }
                i48 = i49;
            }
            int i50 = i48;
            do {
                int i51 = ~iArr4[i50];
                iArr4[i50] = i51;
                iArr4[i47 + i51] = i48;
                i50--;
            } while (iArr4[i50] < 0);
            iArr4[i47 + iArr4[i50]] = i48;
            i48 = i50 - 1;
        }
        trSort(i47, i47, 1);
        if ((bArr[i17] & 255) < (bArr[0] & 255) || (bArr[i17] == bArr[0] && z2)) {
            if (z2) {
                i6 = i47;
            } else {
                i6 = i47 - 1;
                iArr4[iArr4[i47 + i6]] = i17;
            }
            i7 = i17 - 1;
            while (i7 >= 0 && (bArr[i7] & 255) <= (bArr[i7 + 1] & 255)) {
                i7--;
            }
        } else {
            i6 = i47;
            i7 = i17;
        }
        while (i7 >= 0) {
            do {
                i7--;
                if (i7 < 0) {
                    break;
                }
            } while ((bArr[i7] & 255) >= (bArr[i7 + 1] & 255));
            if (i7 >= 0) {
                i6--;
                iArr4[iArr4[i47 + i6]] = i7;
                do {
                    i7--;
                    if (i7 >= 0) {
                    }
                } while ((bArr[i7] & 255) <= (bArr[i7 + 1] & 255));
            }
        }
        for (int i52 = 255; i52 >= 0; i52--) {
            int i53 = 255;
            while (i52 < i53) {
                int i54 = i17 - iArr2[BUCKET_B(i52, i53)];
                iArr2[BUCKET_B(i52, i53)] = i17 + 1;
                int i55 = iArr2[BUCKET_BSTAR(i52, i53)];
                i17 = i54;
                int i56 = i46;
                while (i55 <= i56) {
                    iArr4[i17] = iArr4[i56];
                    i17--;
                    i56--;
                }
                i53--;
                i46 = i56;
            }
            int i57 = i17 - iArr2[BUCKET_B(i52, i52)];
            iArr2[BUCKET_B(i52, i52)] = i17 + 1;
            if (i52 < 255) {
                iArr2[BUCKET_BSTAR(i52, i52 + 1)] = i57 + 1;
            }
            i17 = iArr[i52];
        }
        return i47;
    }

    private int constructBWT(int[] iArr, int[] iArr2) {
        byte[] bArr = this.f8352T;
        int[] iArr3 = this.f8351SA;
        int i = this.f8353n;
        int i2 = 254;
        int i3 = 0;
        int i4 = 0;
        while (i2 >= 0) {
            int i5 = i2 + 1;
            int i6 = iArr2[BUCKET_BSTAR(i2, i5)];
            int i7 = -1;
            int i8 = 0;
            for (int i9 = iArr[i5]; i6 <= i9; i9--) {
                int i10 = iArr3[i9];
                if (i10 >= 0) {
                    int i11 = i10 - 1;
                    if (i11 < 0) {
                        i11 = i - 1;
                    }
                    int i12 = bArr[i11] & 255;
                    if (i12 <= i2) {
                        iArr3[i9] = ~i10;
                        if (i11 > 0 && (bArr[i11 - 1] & 255) > i12) {
                            i11 = ~i11;
                        }
                        if (i7 == i12) {
                            i8--;
                            iArr3[i8] = i11;
                        } else {
                            if (i7 >= 0) {
                                iArr2[BUCKET_B(i7, i2)] = i8;
                            }
                            i8 = iArr2[BUCKET_B(i12, i2)] - 1;
                            iArr3[i8] = i11;
                            i7 = i12;
                        }
                    }
                } else {
                    iArr3[i9] = ~i10;
                }
            }
            i2--;
            i3 = i8;
            i4 = i7;
        }
        int i13 = -1;
        for (int i14 = 0; i14 < i; i14++) {
            int i15 = iArr3[i14];
            if (i15 >= 0) {
                int i16 = i15 - 1;
                if (i16 < 0) {
                    i16 = i - 1;
                }
                int i17 = bArr[i16] & 255;
                if (i17 >= (bArr[i16 + 1] & 255)) {
                    if (i16 > 0 && (bArr[i16 - 1] & 255) < i17) {
                        i16 = ~i16;
                    }
                    if (i17 == i4) {
                        i3++;
                        iArr3[i3] = i16;
                    } else {
                        if (i4 != -1) {
                            iArr[i4] = i3;
                        }
                        i3 = iArr[i17] + 1;
                        iArr3[i3] = i16;
                        i4 = i17;
                    }
                }
            } else {
                i15 = ~i15;
            }
            if (i15 == 0) {
                iArr3[i14] = bArr[i - 1];
                i13 = i14;
            } else {
                iArr3[i14] = bArr[i15 - 1];
            }
        }
        return i13;
    }

    public int bwt() {
        int[] iArr = this.f8351SA;
        byte[] bArr = this.f8352T;
        int i = this.f8353n;
        int[] iArr2 = new int[256];
        int[] iArr3 = new int[65536];
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            iArr[0] = bArr[0];
            return 0;
        }
        if (sortTypeBstar(iArr2, iArr3) > 0) {
            return constructBWT(iArr2, iArr3);
        }
        return 0;
    }
}
