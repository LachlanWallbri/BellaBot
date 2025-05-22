package org.bouncycastle.crypto.prng.drbg;

/* loaded from: classes9.dex */
public interface SP80090DRBG {
    int generate(byte[] bArr, byte[] bArr2, boolean z);

    int getBlockSize();

    void reseed(byte[] bArr);
}
