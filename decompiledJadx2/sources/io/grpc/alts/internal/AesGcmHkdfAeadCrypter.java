package io.grpc.alts.internal;

import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class AesGcmHkdfAeadCrypter implements AeadCrypter {
    private static final int KDF_COUNTER_LENGTH = 6;
    private static final int KDF_COUNTER_OFFSET = 2;
    private static final int KDF_KEY_LENGTH = 32;
    private static final int KEY_LENGTH = 44;
    private static final int NONCE_LENGTH = 12;
    private AeadCrypter aeadCrypter;
    private final byte[] kdfKey;
    private final byte[] nonceMask;
    private final byte[] kdfCounter = new byte[6];
    private final byte[] nonceBuffer = new byte[12];

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getKeyLength() {
        return 44;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AesGcmHkdfAeadCrypter(byte[] bArr) {
        Preconditions.checkArgument(bArr.length == 44);
        this.kdfKey = Arrays.copyOf(bArr, 32);
        this.nonceMask = Arrays.copyOfRange(bArr, 32, 44);
    }

    @Override // io.grpc.alts.internal.AeadCrypter
    public void encrypt(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, byte[] bArr) throws GeneralSecurityException {
        maybeRekey(bArr);
        maskNonce(this.nonceBuffer, this.nonceMask, bArr);
        this.aeadCrypter.encrypt(byteBuffer, byteBuffer2, this.nonceBuffer);
    }

    @Override // io.grpc.alts.internal.AeadCrypter
    public void encrypt(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, byte[] bArr) throws GeneralSecurityException {
        maybeRekey(bArr);
        maskNonce(this.nonceBuffer, this.nonceMask, bArr);
        this.aeadCrypter.encrypt(byteBuffer, byteBuffer2, byteBuffer3, this.nonceBuffer);
    }

    @Override // io.grpc.alts.internal.AeadCrypter
    public void decrypt(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, byte[] bArr) throws GeneralSecurityException {
        maybeRekey(bArr);
        maskNonce(this.nonceBuffer, this.nonceMask, bArr);
        this.aeadCrypter.decrypt(byteBuffer, byteBuffer2, this.nonceBuffer);
    }

    @Override // io.grpc.alts.internal.AeadCrypter
    public void decrypt(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, byte[] bArr) throws GeneralSecurityException {
        maybeRekey(bArr);
        maskNonce(this.nonceBuffer, this.nonceMask, bArr);
        this.aeadCrypter.decrypt(byteBuffer, byteBuffer2, byteBuffer3, this.nonceBuffer);
    }

    private void maybeRekey(byte[] bArr) throws GeneralSecurityException {
        if (this.aeadCrypter == null || !arrayEqualOn(bArr, 2, this.kdfCounter, 0, 6)) {
            System.arraycopy(bArr, 2, this.kdfCounter, 0, 6);
            this.aeadCrypter = new AesGcmAeadCrypter(Arrays.copyOf(hkdfExpandSha256(this.kdfKey, this.kdfCounter), AesGcmAeadCrypter.getKeyLength()));
        }
    }

    private static void maskNonce(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        Preconditions.checkArgument(bArr3.length == 12);
        for (int i = 0; i < 12; i++) {
            bArr[i] = (byte) (bArr2[i] ^ bArr3[i]);
        }
    }

    private static byte[] hkdfExpandSha256(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        Mac mac = Mac.getInstance("HMACSHA256");
        mac.init(new SecretKeySpec(bArr, mac.getAlgorithm()));
        mac.update(bArr2);
        mac.update((byte) 1);
        return mac.doFinal();
    }

    private static boolean arrayEqualOn(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            if (bArr[i + i4] != bArr2[i2 + i4]) {
                return false;
            }
        }
        return true;
    }
}
