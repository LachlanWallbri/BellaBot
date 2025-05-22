package com.amazonaws.services.p048s3.internal.crypto;

@Deprecated
/* loaded from: classes.dex */
class AesCbc extends ContentCryptoScheme {
    private static final int DEFAULT_BLOCK_SIZE_IN_BYTES = 16;
    private static final int DEFAULT_IV_LENGTH_IN_BYTES = 16;
    private static final int DEFAULT_KEY_LENGTH_IN_BITS = 256;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazonaws.services.p048s3.internal.crypto.ContentCryptoScheme
    public int getBlockSizeInBytes() {
        return 16;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazonaws.services.p048s3.internal.crypto.ContentCryptoScheme
    public String getCipherAlgorithm() {
        return JceEncryptionConstants.SYMMETRIC_CIPHER_METHOD;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazonaws.services.p048s3.internal.crypto.ContentCryptoScheme
    public int getIVLengthInBytes() {
        return 16;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazonaws.services.p048s3.internal.crypto.ContentCryptoScheme
    public String getKeyGeneratorAlgorithm() {
        return JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazonaws.services.p048s3.internal.crypto.ContentCryptoScheme
    public int getKeyLengthInBits() {
        return 256;
    }

    @Override // com.amazonaws.services.p048s3.internal.crypto.ContentCryptoScheme
    long getMaxPlaintextSize() {
        return 4503599627370496L;
    }
}
