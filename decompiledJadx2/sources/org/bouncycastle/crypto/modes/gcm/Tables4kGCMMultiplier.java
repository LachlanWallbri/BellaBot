package org.bouncycastle.crypto.modes.gcm;

import java.lang.reflect.Array;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* loaded from: classes9.dex */
public class Tables4kGCMMultiplier implements GCMMultiplier {

    /* renamed from: H */
    private byte[] f9537H;

    /* renamed from: T */
    private long[][] f9538T;

    @Override // org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void init(byte[] bArr) {
        if (this.f9538T == null) {
            this.f9538T = (long[][]) Array.newInstance((Class<?>) long.class, 256, 2);
        } else if (Arrays.areEqual(this.f9537H, bArr)) {
            return;
        }
        this.f9537H = Arrays.clone(bArr);
        GCMUtil.asLongs(this.f9537H, this.f9538T[1]);
        long[][] jArr = this.f9538T;
        GCMUtil.multiplyP7(jArr[1], jArr[1]);
        for (int i = 2; i < 256; i += 2) {
            long[][] jArr2 = this.f9538T;
            GCMUtil.divideP(jArr2[i >> 1], jArr2[i]);
            long[][] jArr3 = this.f9538T;
            GCMUtil.xor(jArr3[i], jArr3[1], jArr3[i + 1]);
        }
    }

    @Override // org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void multiplyH(byte[] bArr) {
        long[] jArr = this.f9538T[bArr[15] & 255];
        long j = jArr[0];
        long j2 = jArr[1];
        for (int i = 14; i >= 0; i--) {
            long[] jArr2 = this.f9538T[bArr[i] & 255];
            long j3 = j2 << 56;
            j2 = ((j2 >>> 8) | (j << 56)) ^ jArr2[1];
            j = (((((j >>> 8) ^ jArr2[0]) ^ j3) ^ (j3 >>> 1)) ^ (j3 >>> 2)) ^ (j3 >>> 7);
        }
        Pack.longToBigEndian(j, bArr, 0);
        Pack.longToBigEndian(j2, bArr, 8);
    }
}
