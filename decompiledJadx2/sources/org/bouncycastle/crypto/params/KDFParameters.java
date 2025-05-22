package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.DerivationParameters;

/* loaded from: classes9.dex */
public class KDFParameters implements DerivationParameters {

    /* renamed from: iv */
    byte[] f9602iv;
    byte[] shared;

    public KDFParameters(byte[] bArr, byte[] bArr2) {
        this.shared = bArr;
        this.f9602iv = bArr2;
    }

    public byte[] getIV() {
        return this.f9602iv;
    }

    public byte[] getSharedSecret() {
        return this.shared;
    }
}
