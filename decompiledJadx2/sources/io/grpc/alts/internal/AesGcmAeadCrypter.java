package io.grpc.alts.internal;

import com.google.common.base.Preconditions;
import io.grpc.internal.ConscryptLoader;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class AesGcmAeadCrypter implements AeadCrypter {
    private static final String AES = "AES";
    private static final String AES_GCM = "AES/GCM/NoPadding";
    private static final int KEY_LENGTH = 16;
    static final int NONCE_LENGTH = 12;
    private static final int TAG_LENGTH = 16;
    private final Cipher cipher;
    private final byte[] key;
    private static final Logger logger = Logger.getLogger(AesGcmAeadCrypter.class.getName());
    private static final Provider CONSCRYPT = getConscrypt();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getKeyLength() {
        return 16;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AesGcmAeadCrypter(byte[] bArr) throws GeneralSecurityException {
        Preconditions.checkArgument(bArr.length == 16);
        this.key = bArr;
        Provider provider = CONSCRYPT;
        if (provider != null) {
            this.cipher = Cipher.getInstance(AES_GCM, provider);
        } else {
            this.cipher = Cipher.getInstance(AES_GCM);
        }
    }

    private int encryptAad(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, @Nullable ByteBuffer byteBuffer3, byte[] bArr) throws GeneralSecurityException {
        Preconditions.checkArgument(bArr.length == 12);
        this.cipher.init(1, new SecretKeySpec(this.key, "AES"), new GCMParameterSpec(128, bArr));
        if (byteBuffer3 != null) {
            this.cipher.updateAAD(byteBuffer3);
        }
        return this.cipher.doFinal(byteBuffer2, byteBuffer);
    }

    private void decryptAad(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, @Nullable ByteBuffer byteBuffer3, byte[] bArr) throws GeneralSecurityException {
        Preconditions.checkArgument(bArr.length == 12);
        this.cipher.init(2, new SecretKeySpec(this.key, "AES"), new GCMParameterSpec(128, bArr));
        if (byteBuffer3 != null) {
            this.cipher.updateAAD(byteBuffer3);
        }
        this.cipher.doFinal(byteBuffer2, byteBuffer);
    }

    @Override // io.grpc.alts.internal.AeadCrypter
    public void encrypt(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, byte[] bArr) throws GeneralSecurityException {
        encryptAad(byteBuffer, byteBuffer2, null, bArr);
    }

    @Override // io.grpc.alts.internal.AeadCrypter
    public void encrypt(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, byte[] bArr) throws GeneralSecurityException {
        encryptAad(byteBuffer, byteBuffer2, byteBuffer3, bArr);
    }

    @Override // io.grpc.alts.internal.AeadCrypter
    public void decrypt(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, byte[] bArr) throws GeneralSecurityException {
        decryptAad(byteBuffer, byteBuffer2, null, bArr);
    }

    @Override // io.grpc.alts.internal.AeadCrypter
    public void decrypt(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, byte[] bArr) throws GeneralSecurityException {
        decryptAad(byteBuffer, byteBuffer2, byteBuffer3, bArr);
    }

    static Provider getConscrypt() {
        if (!ConscryptLoader.isPresent()) {
            return null;
        }
        try {
            Provider newProvider = ConscryptLoader.newProvider();
            try {
                Cipher.getInstance(AES_GCM, newProvider);
                return newProvider;
            } catch (SecurityException e) {
                logger.log(Level.WARNING, "Could not load Conscrypt. Will try slower JDK implementation. This may be because the JDK is older than Java 7 update 121 or Java 8 update 111. If so, please update", (Throwable) e);
                return null;
            } catch (Throwable th) {
                logger.log(Level.INFO, "Could not load Conscrypt. Will use slower JDK implementation", th);
                return null;
            }
        } catch (Throwable th2) {
            logger.log(Level.INFO, "Could not load Conscrypt. Will use slower JDK implementation", th2);
            return null;
        }
    }
}
