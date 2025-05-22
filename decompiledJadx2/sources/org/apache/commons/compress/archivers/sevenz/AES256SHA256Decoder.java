package org.apache.commons.compress.archivers.sevenz;

import com.amazonaws.services.p048s3.internal.crypto.JceEncryptionConstants;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.compress.PasswordRequiredException;

/* loaded from: classes8.dex */
class AES256SHA256Decoder extends CoderBase {
    /* JADX INFO: Access modifiers changed from: package-private */
    public AES256SHA256Decoder() {
        super(new Class[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.compress.archivers.sevenz.CoderBase
    public InputStream decode(final String str, final InputStream inputStream, long j, final Coder coder, final byte[] bArr, int i) throws IOException {
        return new InputStream() { // from class: org.apache.commons.compress.archivers.sevenz.AES256SHA256Decoder.1
            private CipherInputStream cipherInputStream;
            private boolean isInitialized;

            private CipherInputStream init() throws IOException {
                byte[] digest;
                if (this.isInitialized) {
                    return this.cipherInputStream;
                }
                if (coder.properties == null) {
                    throw new IOException("Missing AES256 properties in " + str);
                }
                if (coder.properties.length < 2) {
                    throw new IOException("AES256 properties too short in " + str);
                }
                int i2 = coder.properties[0] & 255;
                int i3 = i2 & 63;
                int i4 = coder.properties[1] & 255;
                int i5 = ((i2 >> 6) & 1) + (i4 & 15);
                int i6 = ((i2 >> 7) & 1) + (i4 >> 4);
                int i7 = i6 + 2;
                if (i7 + i5 > coder.properties.length) {
                    throw new IOException("Salt size + IV size too long in " + str);
                }
                byte[] bArr2 = new byte[i6];
                System.arraycopy(coder.properties, 2, bArr2, 0, i6);
                byte[] bArr3 = new byte[16];
                System.arraycopy(coder.properties, i7, bArr3, 0, i5);
                if (bArr == null) {
                    throw new PasswordRequiredException(str);
                }
                if (i3 == 63) {
                    digest = new byte[32];
                    System.arraycopy(bArr2, 0, digest, 0, i6);
                    byte[] bArr4 = bArr;
                    System.arraycopy(bArr4, 0, digest, i6, Math.min(bArr4.length, digest.length - i6));
                } else {
                    try {
                        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                        byte[] bArr5 = new byte[8];
                        for (long j2 = 0; j2 < (1 << i3); j2++) {
                            messageDigest.update(bArr2);
                            messageDigest.update(bArr);
                            messageDigest.update(bArr5);
                            for (int i8 = 0; i8 < bArr5.length; i8++) {
                                bArr5[i8] = (byte) (bArr5[i8] + 1);
                                if (bArr5[i8] != 0) {
                                    break;
                                }
                            }
                        }
                        digest = messageDigest.digest();
                    } catch (NoSuchAlgorithmException e) {
                        throw new IOException("SHA-256 is unsupported by your Java implementation", e);
                    }
                }
                SecretKeySpec secretKeySpec = new SecretKeySpec(digest, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
                try {
                    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
                    cipher.init(2, secretKeySpec, new IvParameterSpec(bArr3));
                    this.cipherInputStream = new CipherInputStream(inputStream, cipher);
                    this.isInitialized = true;
                    return this.cipherInputStream;
                } catch (GeneralSecurityException e2) {
                    throw new IOException("Decryption error (do you have the JCE Unlimited Strength Jurisdiction Policy Files installed?)", e2);
                }
            }

            @Override // java.io.InputStream
            public int read() throws IOException {
                return init().read();
            }

            @Override // java.io.InputStream
            public int read(byte[] bArr2, int i2, int i3) throws IOException {
                return init().read(bArr2, i2, i3);
            }

            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                CipherInputStream cipherInputStream = this.cipherInputStream;
                if (cipherInputStream != null) {
                    cipherInputStream.close();
                }
            }
        };
    }
}
