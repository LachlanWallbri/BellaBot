package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;

/* loaded from: classes9.dex */
public class ParametersWithID implements CipherParameters {

    /* renamed from: id */
    private byte[] f9605id;
    private CipherParameters parameters;

    public ParametersWithID(CipherParameters cipherParameters, byte[] bArr) {
        this.parameters = cipherParameters;
        this.f9605id = bArr;
    }

    public byte[] getID() {
        return this.f9605id;
    }

    public CipherParameters getParameters() {
        return this.parameters;
    }
}
