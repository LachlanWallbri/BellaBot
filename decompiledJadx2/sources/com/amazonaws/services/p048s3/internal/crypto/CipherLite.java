package com.amazonaws.services.p048s3.internal.crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.NullCipher;
import javax.crypto.SecretKey;

/* JADX INFO: Access modifiers changed from: package-private */
@Deprecated
/* loaded from: classes.dex */
public class CipherLite {
    static final CipherLite NULL = new CipherLite() { // from class: com.amazonaws.services.s3.internal.crypto.CipherLite.1
        @Override // com.amazonaws.services.p048s3.internal.crypto.CipherLite
        CipherLite createAuxiliary(long j) {
            return this;
        }

        @Override // com.amazonaws.services.p048s3.internal.crypto.CipherLite
        CipherLite createInverse() {
            return this;
        }
    };
    private final Cipher cipher;
    private final int cipherMode;
    private final ContentCryptoScheme scheme;
    private final SecretKey secreteKey;

    /* JADX INFO: Access modifiers changed from: package-private */
    public long mark() {
        return -1L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean markSupported() {
        return false;
    }

    private CipherLite() {
        this.cipher = new NullCipher();
        this.scheme = null;
        this.secreteKey = null;
        this.cipherMode = -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CipherLite(Cipher cipher, ContentCryptoScheme contentCryptoScheme, SecretKey secretKey, int i) {
        this.cipher = cipher;
        this.scheme = contentCryptoScheme;
        this.secreteKey = secretKey;
        this.cipherMode = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CipherLite recreate() {
        return this.scheme.createCipherLite(this.secreteKey, this.cipher.getIV(), this.cipherMode, this.cipher.getProvider());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CipherLite createUsingIV(byte[] bArr) {
        return this.scheme.createCipherLite(this.secreteKey, bArr, this.cipherMode, this.cipher.getProvider());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CipherLite createAuxiliary(long j) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidAlgorithmParameterException {
        return this.scheme.createAuxillaryCipher(this.secreteKey, this.cipher.getIV(), this.cipherMode, this.cipher.getProvider(), j);
    }

    CipherLite createInverse() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidAlgorithmParameterException {
        int i = this.cipherMode;
        int i2 = 1;
        if (i != 2) {
            if (i != 1) {
                throw new UnsupportedOperationException();
            }
            i2 = 2;
        }
        return this.scheme.createCipherLite(this.secreteKey, this.cipher.getIV(), i2, this.cipher.getProvider());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] doFinal() throws IllegalBlockSizeException, BadPaddingException {
        return this.cipher.doFinal();
    }

    byte[] doFinal(byte[] bArr) throws IllegalBlockSizeException, BadPaddingException {
        return this.cipher.doFinal(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] doFinal(byte[] bArr, int i, int i2) throws IllegalBlockSizeException, BadPaddingException {
        return this.cipher.doFinal(bArr, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] update(byte[] bArr, int i, int i2) {
        return this.cipher.update(bArr, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String getCipherAlgorithm() {
        return this.cipher.getAlgorithm();
    }

    final Provider getCipherProvider() {
        return this.cipher.getProvider();
    }

    final String getSecretKeyAlgorithm() {
        return this.secreteKey.getAlgorithm();
    }

    final Cipher getCipher() {
        return this.cipher;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ContentCryptoScheme getContentCryptoScheme() {
        return this.scheme;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final byte[] getIV() {
        return this.cipher.getIV();
    }

    final int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getCipherMode() {
        return this.cipherMode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reset() {
        throw new IllegalStateException("mark/reset not supported");
    }

    int getOutputSize(int i) {
        return this.cipher.getOutputSize(i);
    }
}
