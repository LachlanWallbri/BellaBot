package com.amazonaws.services.p048s3.internal.crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

@Deprecated
/* loaded from: classes.dex */
class AesGcm extends ContentCryptoScheme {
    private static final int DEFAULT_BLOCK_SIZE_IN_BYTES = 16;
    private static final int DEFAULT_IV_LENGTH_IN_BYTES = 12;
    private static final int DEFAULT_KEY_LENGTH_IN_BITS = 256;
    private static final int DEFAULT_TAG_LENGTH_IN_BITS = 128;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazonaws.services.p048s3.internal.crypto.ContentCryptoScheme
    public int getBlockSizeInBytes() {
        return 16;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazonaws.services.p048s3.internal.crypto.ContentCryptoScheme
    public String getCipherAlgorithm() {
        return "AES/GCM/NoPadding";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazonaws.services.p048s3.internal.crypto.ContentCryptoScheme
    public int getIVLengthInBytes() {
        return 12;
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
        return 68719476704L;
    }

    @Override // com.amazonaws.services.p048s3.internal.crypto.ContentCryptoScheme
    String getSpecificCipherProvider() {
        return BouncyCastleProvider.PROVIDER_NAME;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazonaws.services.p048s3.internal.crypto.ContentCryptoScheme
    public int getTagLengthInBits() {
        return 128;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazonaws.services.p048s3.internal.crypto.ContentCryptoScheme
    public CipherLite createAuxillaryCipher(SecretKey secretKey, byte[] bArr, int i, Provider provider, long j) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidAlgorithmParameterException {
        return AES_CTR.createCipherLite(secretKey, AES_CTR.adjustIV(bArr, j), i, provider);
    }

    @Override // com.amazonaws.services.p048s3.internal.crypto.ContentCryptoScheme
    protected CipherLite newCipherLite(Cipher cipher, SecretKey secretKey, int i) {
        return new GCMCipherLite(cipher, secretKey, i);
    }
}
