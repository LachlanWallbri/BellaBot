package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;

/* loaded from: classes9.dex */
public class ParametersWithIV implements CipherParameters {

    /* renamed from: iv */
    private byte[] f9606iv;
    private CipherParameters parameters;

    public ParametersWithIV(CipherParameters cipherParameters, byte[] bArr) {
        this(cipherParameters, bArr, 0, bArr.length);
    }

    public ParametersWithIV(CipherParameters cipherParameters, byte[] bArr, int i, int i2) {
        this.f9606iv = new byte[i2];
        this.parameters = cipherParameters;
        System.arraycopy(bArr, i, this.f9606iv, 0, i2);
    }

    public byte[] getIV() {
        return this.f9606iv;
    }

    public CipherParameters getParameters() {
        return this.parameters;
    }
}
