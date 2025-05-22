package org.bouncycastle.cert.crmf;

/* loaded from: classes9.dex */
public interface EncryptedValuePadder {
    byte[] getPaddedData(byte[] bArr);

    byte[] getUnpaddedData(byte[] bArr);
}
