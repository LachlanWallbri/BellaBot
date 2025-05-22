package com.amazonaws.services.p048s3.internal.crypto;

@Deprecated
/* loaded from: classes.dex */
final class MultipartUploadCbcContext extends MultipartUploadCryptoContext {
    private byte[] nextIV;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MultipartUploadCbcContext(String str, String str2, ContentCryptoMaterial contentCryptoMaterial) {
        super(str, str2, contentCryptoMaterial);
    }

    public void setNextInitializationVector(byte[] bArr) {
        this.nextIV = bArr;
    }

    public byte[] getNextInitializationVector() {
        return this.nextIV;
    }
}
