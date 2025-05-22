package io.grpc.netty.shaded.io.netty.internal.tcnative;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface SSLPrivateKeyMethod {
    public static final int SSL_SIGN_RSA_PKCS1_SHA1 = NativeStaticallyReferencedJniMethods.sslSignRsaPkcsSha1();
    public static final int SSL_SIGN_RSA_PKCS1_SHA256 = NativeStaticallyReferencedJniMethods.sslSignRsaPkcsSha256();
    public static final int SSL_SIGN_RSA_PKCS1_SHA384 = NativeStaticallyReferencedJniMethods.sslSignRsaPkcsSha384();
    public static final int SSL_SIGN_RSA_PKCS1_SHA512 = NativeStaticallyReferencedJniMethods.sslSignRsaPkcsSha512();
    public static final int SSL_SIGN_ECDSA_SHA1 = NativeStaticallyReferencedJniMethods.sslSignEcdsaPkcsSha1();
    public static final int SSL_SIGN_ECDSA_SECP256R1_SHA256 = NativeStaticallyReferencedJniMethods.sslSignEcdsaSecp256r1Sha256();
    public static final int SSL_SIGN_ECDSA_SECP384R1_SHA384 = NativeStaticallyReferencedJniMethods.sslSignEcdsaSecp384r1Sha384();
    public static final int SSL_SIGN_ECDSA_SECP521R1_SHA512 = NativeStaticallyReferencedJniMethods.sslSignEcdsaSecp521r1Sha512();
    public static final int SSL_SIGN_RSA_PSS_RSAE_SHA256 = NativeStaticallyReferencedJniMethods.sslSignRsaPssRsaeSha256();
    public static final int SSL_SIGN_RSA_PSS_RSAE_SHA384 = NativeStaticallyReferencedJniMethods.sslSignRsaPssRsaeSha384();
    public static final int SSL_SIGN_RSA_PSS_RSAE_SHA512 = NativeStaticallyReferencedJniMethods.sslSignRsaPssRsaeSha512();
    public static final int SSL_SIGN_ED25519 = NativeStaticallyReferencedJniMethods.sslSignEd25519();
    public static final int SSL_SIGN_RSA_PKCS1_MD5_SHA1 = NativeStaticallyReferencedJniMethods.sslSignRsaPkcs1Md5Sha1();

    byte[] decrypt(long j, byte[] bArr) throws Exception;

    byte[] sign(long j, int i, byte[] bArr) throws Exception;
}
