package org.conscrypt.p090ct;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class DigitallySigned {
    private final HashAlgorithm hashAlgorithm;
    private final byte[] signature;
    private final SignatureAlgorithm signatureAlgorithm;

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public enum HashAlgorithm {
        NONE,
        MD5,
        SHA1,
        SHA224,
        SHA256,
        SHA384,
        SHA512;

        private static HashAlgorithm[] values = values();

        public static HashAlgorithm valueOf(int i) {
            try {
                return values[i];
            } catch (IndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Invalid hash algorithm " + i, e);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public enum SignatureAlgorithm {
        ANONYMOUS,
        RSA,
        DSA,
        ECDSA;

        private static SignatureAlgorithm[] values = values();

        public static SignatureAlgorithm valueOf(int i) {
            try {
                return values[i];
            } catch (IndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Invalid signature algorithm " + i, e);
            }
        }
    }

    public DigitallySigned(HashAlgorithm hashAlgorithm, SignatureAlgorithm signatureAlgorithm, byte[] bArr) {
        this.hashAlgorithm = hashAlgorithm;
        this.signatureAlgorithm = signatureAlgorithm;
        this.signature = bArr;
    }

    public DigitallySigned(int i, int i2, byte[] bArr) {
        this(HashAlgorithm.valueOf(i), SignatureAlgorithm.valueOf(i2), bArr);
    }

    public HashAlgorithm getHashAlgorithm() {
        return this.hashAlgorithm;
    }

    public SignatureAlgorithm getSignatureAlgorithm() {
        return this.signatureAlgorithm;
    }

    public byte[] getSignature() {
        return this.signature;
    }

    public String getAlgorithm() {
        return String.format("%swith%s", this.hashAlgorithm, this.signatureAlgorithm);
    }

    public static DigitallySigned decode(InputStream inputStream) throws SerializationException {
        try {
            return new DigitallySigned(Serialization.readNumber(inputStream, 1), Serialization.readNumber(inputStream, 1), Serialization.readVariableBytes(inputStream, 2));
        } catch (IllegalArgumentException e) {
            throw new SerializationException(e);
        }
    }

    public static DigitallySigned decode(byte[] bArr) throws SerializationException {
        return decode(new ByteArrayInputStream(bArr));
    }
}
