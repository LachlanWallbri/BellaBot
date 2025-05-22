package org.bouncycastle.crypto.modes.gcm;

import org.bouncycastle.util.Arrays;

/* loaded from: classes9.dex */
public class BasicGCMExponentiator implements GCMExponentiator {

    /* renamed from: x */
    private long[] f9534x;

    @Override // org.bouncycastle.crypto.modes.gcm.GCMExponentiator
    public void exponentiateX(long j, byte[] bArr) {
        long[] oneAsLongs = GCMUtil.oneAsLongs();
        if (j > 0) {
            long[] clone = Arrays.clone(this.f9534x);
            do {
                if ((1 & j) != 0) {
                    GCMUtil.multiply(oneAsLongs, clone);
                }
                GCMUtil.square(clone, clone);
                j >>>= 1;
            } while (j > 0);
        }
        GCMUtil.asBytes(oneAsLongs, bArr);
    }

    @Override // org.bouncycastle.crypto.modes.gcm.GCMExponentiator
    public void init(byte[] bArr) {
        this.f9534x = GCMUtil.asLongs(bArr);
    }
}
