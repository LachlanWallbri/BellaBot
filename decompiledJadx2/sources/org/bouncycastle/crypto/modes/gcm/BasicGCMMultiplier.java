package org.bouncycastle.crypto.modes.gcm;

/* loaded from: classes9.dex */
public class BasicGCMMultiplier implements GCMMultiplier {

    /* renamed from: H */
    private long[] f9535H;

    @Override // org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void init(byte[] bArr) {
        this.f9535H = GCMUtil.asLongs(bArr);
    }

    @Override // org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void multiplyH(byte[] bArr) {
        long[] asLongs = GCMUtil.asLongs(bArr);
        GCMUtil.multiply(asLongs, this.f9535H);
        GCMUtil.asBytes(asLongs, bArr);
    }
}
