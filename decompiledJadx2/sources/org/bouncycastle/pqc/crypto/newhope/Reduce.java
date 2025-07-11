package org.bouncycastle.pqc.crypto.newhope;

import kotlin.UShort;

/* loaded from: classes9.dex */
class Reduce {
    static final int QInv = 12287;
    static final int RLog = 18;
    static final int RMask = 262143;

    Reduce() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static short barrett(short s) {
        int i = s & UShort.MAX_VALUE;
        return (short) (i - (((i * 5) >>> 16) * 12289));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static short montgomery(int i) {
        return (short) (((((i * QInv) & RMask) * 12289) + i) >>> 18);
    }
}
