package com.amazonaws.services.p048s3.internal.crypto;

import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;

@Deprecated
/* loaded from: classes.dex */
final class GCMCipherLite extends CipherLite {
    private static final int BITS = 8;
    private static final int TAG_LENGTH = ContentCryptoScheme.AES_GCM.getTagLengthInBits() / 8;
    private CipherLite aux;
    private long currentCount;
    private boolean doneFinal;
    private byte[] finalBytes;
    private boolean invisiblyProcessed;
    private long markedCount;
    private long outputByteCount;
    private boolean securityViolated;
    private final int tagLen;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazonaws.services.p048s3.internal.crypto.CipherLite
    public boolean markSupported() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GCMCipherLite(Cipher cipher, SecretKey secretKey, int i) {
        super(cipher, ContentCryptoScheme.AES_GCM, secretKey, i);
        this.tagLen = i == 1 ? TAG_LENGTH : 0;
        if (i != 1 && i != 2) {
            throw new IllegalArgumentException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazonaws.services.p048s3.internal.crypto.CipherLite
    public byte[] doFinal() throws IllegalBlockSizeException, BadPaddingException {
        if (this.doneFinal) {
            if (this.securityViolated) {
                throw new SecurityException();
            }
            byte[] bArr = this.finalBytes;
            if (bArr == null) {
                return null;
            }
            return (byte[]) bArr.clone();
        }
        this.doneFinal = true;
        this.finalBytes = super.doFinal();
        if (this.finalBytes == null) {
            return null;
        }
        this.outputByteCount += checkMax(r0.length - this.tagLen);
        return (byte[]) this.finalBytes.clone();
    }

    @Override // com.amazonaws.services.p048s3.internal.crypto.CipherLite
    final byte[] doFinal(byte[] bArr) throws IllegalBlockSizeException, BadPaddingException {
        return doFinal0(bArr, 0, bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazonaws.services.p048s3.internal.crypto.CipherLite
    public final byte[] doFinal(byte[] bArr, int i, int i2) throws IllegalBlockSizeException, BadPaddingException {
        return doFinal0(bArr, i, i2);
    }

    private final byte[] doFinal0(byte[] bArr, int i, int i2) throws IllegalBlockSizeException, BadPaddingException {
        if (this.doneFinal) {
            if (this.securityViolated) {
                throw new SecurityException();
            }
            if (2 == getCipherMode()) {
                byte[] bArr2 = this.finalBytes;
                if (bArr2 == null) {
                    return null;
                }
                return (byte[]) bArr2.clone();
            }
            byte[] bArr3 = this.finalBytes;
            int length = bArr3.length;
            int i3 = this.tagLen;
            int i4 = length - i3;
            if (i2 == i4) {
                return (byte[]) bArr3.clone();
            }
            if (i2 < i4 && i2 + this.currentCount == this.outputByteCount) {
                return Arrays.copyOfRange(bArr3, (bArr3.length - i3) - i2, bArr3.length);
            }
            throw new IllegalStateException("Inconsistent re-rencryption");
        }
        this.doneFinal = true;
        this.finalBytes = super.doFinal(bArr, i, i2);
        if (this.finalBytes == null) {
            return null;
        }
        this.outputByteCount += checkMax(r6.length - this.tagLen);
        return (byte[]) this.finalBytes.clone();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazonaws.services.p048s3.internal.crypto.CipherLite
    public byte[] update(byte[] bArr, int i, int i2) {
        byte[] update;
        CipherLite cipherLite = this.aux;
        if (cipherLite == null) {
            update = super.update(bArr, i, i2);
            if (update == null) {
                this.invisiblyProcessed = bArr.length > 0;
                return null;
            }
            this.outputByteCount += checkMax(update.length);
            this.invisiblyProcessed = update.length == 0 && i2 > 0;
        } else {
            update = cipherLite.update(bArr, i, i2);
            if (update == null) {
                return null;
            }
            this.currentCount += update.length;
            long j = this.currentCount;
            long j2 = this.outputByteCount;
            if (j == j2) {
                this.aux = null;
            } else if (j > j2) {
                if (1 == getCipherMode()) {
                    throw new IllegalStateException("currentCount=" + this.currentCount + " > outputByteCount=" + this.outputByteCount);
                }
                byte[] bArr2 = this.finalBytes;
                int length = bArr2 != null ? bArr2.length : 0;
                long j3 = this.outputByteCount;
                long length2 = j3 - (this.currentCount - update.length);
                long j4 = length;
                this.currentCount = j3 - j4;
                this.aux = null;
                return Arrays.copyOf(update, (int) (length2 - j4));
            }
        }
        return update;
    }

    private int checkMax(int i) {
        if (this.outputByteCount + i <= 68719476704L) {
            return i;
        }
        this.securityViolated = true;
        throw new SecurityException("Number of bytes processed has exceeded the maximum allowed by AES/GCM; [outputByteCount=" + this.outputByteCount + ", delta=" + i + "]");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazonaws.services.p048s3.internal.crypto.CipherLite
    public long mark() {
        this.markedCount = this.aux == null ? this.outputByteCount : this.currentCount;
        return this.markedCount;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazonaws.services.p048s3.internal.crypto.CipherLite
    public void reset() {
        if (this.markedCount < this.outputByteCount || this.invisiblyProcessed) {
            try {
                this.aux = createAuxiliary(this.markedCount);
                this.currentCount = this.markedCount;
            } catch (Exception e) {
                if (!(e instanceof RuntimeException)) {
                    throw new IllegalStateException(e);
                }
            }
        }
    }

    byte[] getFinalBytes() {
        byte[] bArr = this.finalBytes;
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    byte[] getTag() {
        byte[] bArr;
        if (getCipherMode() != 1 || (bArr = this.finalBytes) == null) {
            return null;
        }
        return Arrays.copyOfRange(bArr, bArr.length - this.tagLen, bArr.length);
    }

    long getOutputByteCount() {
        return this.outputByteCount;
    }

    long getCurrentCount() {
        return this.currentCount;
    }

    long getMarkedCount() {
        return this.markedCount;
    }
}
