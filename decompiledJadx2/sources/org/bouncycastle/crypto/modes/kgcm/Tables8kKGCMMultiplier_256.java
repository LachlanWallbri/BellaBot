package org.bouncycastle.crypto.modes.kgcm;

import java.lang.reflect.Array;

/* loaded from: classes9.dex */
public class Tables8kKGCMMultiplier_256 implements KGCMMultiplier {

    /* renamed from: T */
    private long[][] f9548T;

    @Override // org.bouncycastle.crypto.modes.kgcm.KGCMMultiplier
    public void init(long[] jArr) {
        long[][] jArr2 = this.f9548T;
        if (jArr2 == null) {
            this.f9548T = (long[][]) Array.newInstance((Class<?>) long.class, 256, 4);
        } else if (KGCMUtil_256.equal(jArr, jArr2[1])) {
            return;
        }
        KGCMUtil_256.copy(jArr, this.f9548T[1]);
        for (int i = 2; i < 256; i += 2) {
            long[][] jArr3 = this.f9548T;
            KGCMUtil_256.multiplyX(jArr3[i >> 1], jArr3[i]);
            long[][] jArr4 = this.f9548T;
            KGCMUtil_256.add(jArr4[i], jArr4[1], jArr4[i + 1]);
        }
    }

    @Override // org.bouncycastle.crypto.modes.kgcm.KGCMMultiplier
    public void multiplyH(long[] jArr) {
        long[] jArr2 = new long[4];
        KGCMUtil_256.copy(this.f9548T[((int) (jArr[3] >>> 56)) & 255], jArr2);
        for (int i = 30; i >= 0; i--) {
            KGCMUtil_256.multiplyX8(jArr2, jArr2);
            KGCMUtil_256.add(this.f9548T[((int) (jArr[i >>> 3] >>> ((i & 7) << 3))) & 255], jArr2, jArr2);
        }
        KGCMUtil_256.copy(jArr2, jArr);
    }
}
