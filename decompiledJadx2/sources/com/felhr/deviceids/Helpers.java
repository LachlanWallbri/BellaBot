package com.felhr.deviceids;

import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
class Helpers {
    static long createDevice(int i, int i2) {
        return (i2 & 4294967295L) | (i << 32);
    }

    Helpers() {
    }

    static long[] createTable(long... jArr) {
        Arrays.sort(jArr);
        return jArr;
    }

    static boolean exists(long[] jArr, int i, int i2) {
        return Arrays.binarySearch(jArr, createDevice(i, i2)) >= 0;
    }
}
