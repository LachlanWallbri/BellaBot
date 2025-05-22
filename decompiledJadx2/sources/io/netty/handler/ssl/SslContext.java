package io.netty.handler.ssl;

import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufInputStream;
import io.netty.handler.ssl.ApplicationProtocolConfig;
import io.netty.util.internal.EmptyArrays;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyException;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.EncryptedPrivateKeyInfo;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.TrustManagerFactory;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public abstract class SslContext {
    static final CertificateFactory X509_CERT_FACTORY;
    private final boolean startTls;

    public abstract ApplicationProtocolNegotiator applicationProtocolNegotiator();

    public abstract List<String> cipherSuites();

    public abstract boolean isClient();

    public abstract SSLEngine newEngine(ByteBufAllocator byteBufAllocator);

    public abstract SSLEngine newEngine(ByteBufAllocator byteBufAllocator, String str, int i);

    public abstract long sessionCacheSize();

    public abstract SSLSessionContext sessionContext();

    public abstract long sessionTimeout();

    static {
        try {
            X509_CERT_FACTORY = CertificateFactory.getInstance("X.509");
        } catch (CertificateException e) {
            throw new IllegalStateException("unable to instance X.509 CertificateFactory", e);
        }
    }

    public static SslProvider defaultServerProvider() {
        return defaultProvider();
    }

    public static SslProvider defaultClientProvider() {
        return defaultProvider();
    }

    private static SslProvider defaultProvider() {
        if (OpenSsl.isAvailable()) {
            return SslProvider.OPENSSL;
        }
        return SslProvider.JDK;
    }

    @Deprecated
    public static SslContext newServerContext(File file, File file2) throws SSLException {
        return newServerContext(file, file2, (String) null);
    }

    @Deprecated
    public static SslContext newServerContext(File file, File file2, String str) throws SSLException {
        return newServerContext(null, file, file2, str);
    }

    @Deprecated
    public static SslContext newServerContext(File file, File file2, String str, Iterable<String> iterable, Iterable<String> iterable2, long j, long j2) throws SSLException {
        return newServerContext((SslProvider) null, file, file2, str, iterable, iterable2, j, j2);
    }

    @Deprecated
    public static SslContext newServerContext(File file, File file2, String str, Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, ApplicationProtocolConfig applicationProtocolConfig, long j, long j2) throws SSLException {
        return newServerContext((SslProvider) null, file, file2, str, iterable, cipherSuiteFilter, applicationProtocolConfig, j, j2);
    }

    @Deprecated
    public static SslContext newServerContext(SslProvider sslProvider, File file, File file2) throws SSLException {
        return newServerContext(sslProvider, file, file2, null);
    }

    @Deprecated
    public static SslContext newServerContext(SslProvider sslProvider, File file, File file2, String str) throws SSLException {
        return newServerContext(sslProvider, file, file2, str, (Iterable<String>) null, IdentityCipherSuiteFilter.INSTANCE, (ApplicationProtocolConfig) null, 0L, 0L);
    }

    @Deprecated
    public static SslContext newServerContext(SslProvider sslProvider, File file, File file2, String str, Iterable<String> iterable, Iterable<String> iterable2, long j, long j2) throws SSLException {
        return newServerContext(sslProvider, file, file2, str, iterable, IdentityCipherSuiteFilter.INSTANCE, toApplicationProtocolConfig(iterable2), j, j2);
    }

    @Deprecated
    public static SslContext newServerContext(SslProvider sslProvider, File file, File file2, String str, TrustManagerFactory trustManagerFactory, Iterable<String> iterable, Iterable<String> iterable2, long j, long j2) throws SSLException {
        return newServerContext(sslProvider, null, trustManagerFactory, file, file2, str, null, iterable, IdentityCipherSuiteFilter.INSTANCE, toApplicationProtocolConfig(iterable2), j, j2);
    }

    @Deprecated
    public static SslContext newServerContext(SslProvider sslProvider, File file, File file2, String str, Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, ApplicationProtocolConfig applicationProtocolConfig, long j, long j2) throws SSLException {
        return newServerContext(sslProvider, null, null, file, file2, str, null, iterable, cipherSuiteFilter, applicationProtocolConfig, j, j2);
    }

    @Deprecated
    public static SslContext newServerContext(SslProvider sslProvider, File file, TrustManagerFactory trustManagerFactory, File file2, File file3, String str, KeyManagerFactory keyManagerFactory, Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, ApplicationProtocolConfig applicationProtocolConfig, long j, long j2) throws SSLException {
        try {
            return newServerContextInternal(sslProvider, null, toX509Certificates(file), trustManagerFactory, toX509Certificates(file2), toPrivateKey(file3, str), str, keyManagerFactory, iterable, cipherSuiteFilter, applicationProtocolConfig, j, j2, ClientAuth.NONE, null, false, false);
        } catch (Exception e) {
            if (e instanceof SSLException) {
                throw ((SSLException) e);
            }
            throw new SSLException("failed to initialize the server-side SSL context", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SslContext newServerContextInternal(SslProvider sslProvider, Provider provider, X509Certificate[] x509CertificateArr, TrustManagerFactory trustManagerFactory, X509Certificate[] x509CertificateArr2, PrivateKey privateKey, String str, KeyManagerFactory keyManagerFactory, Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, ApplicationProtocolConfig applicationProtocolConfig, long j, long j2, ClientAuth clientAuth, String[] strArr, boolean z, boolean z2) throws SSLException {
        SslProvider defaultServerProvider = sslProvider == null ? defaultServerProvider() : sslProvider;
        int i = C73391.$SwitchMap$io$netty$handler$ssl$SslProvider[defaultServerProvider.ordinal()];
        if (i == 1) {
            if (z2) {
                throw new IllegalArgumentException("OCSP is not supported with this SslProvider: " + defaultServerProvider);
            }
            return new JdkSslServerContext(provider, x509CertificateArr, trustManagerFactory, x509CertificateArr2, privateKey, str, keyManagerFactory, iterable, cipherSuiteFilter, applicationProtocolConfig, j, j2, clientAuth, strArr, z);
        }
        if (i == 2) {
            verifyNullSslContextProvider(defaultServerProvider, provider);
            return new OpenSslServerContext(x509CertificateArr, trustManagerFactory, x509CertificateArr2, privateKey, str, keyManagerFactory, iterable, cipherSuiteFilter, applicationProtocolConfig, j, j2, clientAuth, strArr, z, z2);
        }
        if (i == 3) {
            verifyNullSslContextProvider(defaultServerProvider, provider);
            return new ReferenceCountedOpenSslServerContext(x509CertificateArr, trustManagerFactory, x509CertificateArr2, privateKey, str, keyManagerFactory, iterable, cipherSuiteFilter, applicationProtocolConfig, j, j2, clientAuth, strArr, z, z2);
        }
        throw new Error(defaultServerProvider.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* renamed from: io.netty.handler.ssl.SslContext$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C73391 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$ssl$SslProvider;

        static {
            int[] iArr = new int[SslProvider.values().length];
            $SwitchMap$io$netty$handler$ssl$SslProvider = iArr;
            try {
                iArr[SslProvider.JDK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$ssl$SslProvider[SslProvider.OPENSSL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$handler$ssl$SslProvider[SslProvider.OPENSSL_REFCNT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static void verifyNullSslContextProvider(SslProvider sslProvider, Provider provider) {
        if (provider == null) {
            return;
        }
        throw new IllegalArgumentException("Java Security Provider unsupported for SslProvider: " + sslProvider);
    }

    @Deprecated
    public static SslContext newClientContext() throws SSLException {
        return newClientContext(null, null, null);
    }

    @Deprecated
    public static SslContext newClientContext(File file) throws SSLException {
        return newClientContext((SslProvider) null, file);
    }

    @Deprecated
    public static SslContext newClientContext(TrustManagerFactory trustManagerFactory) throws SSLException {
        return newClientContext(null, null, trustManagerFactory);
    }

    @Deprecated
    public static SslContext newClientContext(File file, TrustManagerFactory trustManagerFactory) throws SSLException {
        return newClientContext(null, file, trustManagerFactory);
    }

    @Deprecated
    public static SslContext newClientContext(File file, TrustManagerFactory trustManagerFactory, Iterable<String> iterable, Iterable<String> iterable2, long j, long j2) throws SSLException {
        return newClientContext((SslProvider) null, file, trustManagerFactory, iterable, iterable2, j, j2);
    }

    @Deprecated
    public static SslContext newClientContext(File file, TrustManagerFactory trustManagerFactory, Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, ApplicationProtocolConfig applicationProtocolConfig, long j, long j2) throws SSLException {
        return newClientContext(null, file, trustManagerFactory, iterable, cipherSuiteFilter, applicationProtocolConfig, j, j2);
    }

    @Deprecated
    public static SslContext newClientContext(SslProvider sslProvider) throws SSLException {
        return newClientContext(sslProvider, null, null);
    }

    @Deprecated
    public static SslContext newClientContext(SslProvider sslProvider, File file) throws SSLException {
        return newClientContext(sslProvider, file, null);
    }

    @Deprecated
    public static SslContext newClientContext(SslProvider sslProvider, TrustManagerFactory trustManagerFactory) throws SSLException {
        return newClientContext(sslProvider, null, trustManagerFactory);
    }

    @Deprecated
    public static SslContext newClientContext(SslProvider sslProvider, File file, TrustManagerFactory trustManagerFactory) throws SSLException {
        return newClientContext(sslProvider, file, trustManagerFactory, null, IdentityCipherSuiteFilter.INSTANCE, null, 0L, 0L);
    }

    @Deprecated
    public static SslContext newClientContext(SslProvider sslProvider, File file, TrustManagerFactory trustManagerFactory, Iterable<String> iterable, Iterable<String> iterable2, long j, long j2) throws SSLException {
        return newClientContext(sslProvider, file, trustManagerFactory, null, null, null, null, iterable, IdentityCipherSuiteFilter.INSTANCE, toApplicationProtocolConfig(iterable2), j, j2);
    }

    @Deprecated
    public static SslContext newClientContext(SslProvider sslProvider, File file, TrustManagerFactory trustManagerFactory, Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, ApplicationProtocolConfig applicationProtocolConfig, long j, long j2) throws SSLException {
        return newClientContext(sslProvider, file, trustManagerFactory, null, null, null, null, iterable, cipherSuiteFilter, applicationProtocolConfig, j, j2);
    }

    @Deprecated
    public static SslContext newClientContext(SslProvider sslProvider, File file, TrustManagerFactory trustManagerFactory, File file2, File file3, String str, KeyManagerFactory keyManagerFactory, Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, ApplicationProtocolConfig applicationProtocolConfig, long j, long j2) throws SSLException {
        try {
            return newClientContextInternal(sslProvider, null, toX509Certificates(file), trustManagerFactory, toX509Certificates(file2), toPrivateKey(file3, str), str, keyManagerFactory, iterable, cipherSuiteFilter, applicationProtocolConfig, null, j, j2, false);
        } catch (Exception e) {
            if (e instanceof SSLException) {
                throw ((SSLException) e);
            }
            throw new SSLException("failed to initialize the client-side SSL context", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SslContext newClientContextInternal(SslProvider sslProvider, Provider provider, X509Certificate[] x509CertificateArr, TrustManagerFactory trustManagerFactory, X509Certificate[] x509CertificateArr2, PrivateKey privateKey, String str, KeyManagerFactory keyManagerFactory, Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, ApplicationProtocolConfig applicationProtocolConfig, String[] strArr, long j, long j2, boolean z) throws SSLException {
        SslProvider defaultClientProvider = sslProvider == null ? defaultClientProvider() : sslProvider;
        int i = C73391.$SwitchMap$io$netty$handler$ssl$SslProvider[defaultClientProvider.ordinal()];
        if (i == 1) {
            if (z) {
                throw new IllegalArgumentException("OCSP is not supported with this SslProvider: " + defaultClientProvider);
            }
            return new JdkSslClientContext(provider, x509CertificateArr, trustManagerFactory, x509CertificateArr2, privateKey, str, keyManagerFactory, iterable, cipherSuiteFilter, applicationProtocolConfig, strArr, j, j2);
        }
        if (i == 2) {
            verifyNullSslContextProvider(defaultClientProvider, provider);
            return new OpenSslClientContext(x509CertificateArr, trustManagerFactory, x509CertificateArr2, privateKey, str, keyManagerFactory, iterable, cipherSuiteFilter, applicationProtocolConfig, strArr, j, j2, z);
        }
        if (i == 3) {
            verifyNullSslContextProvider(defaultClientProvider, provider);
            return new ReferenceCountedOpenSslClientContext(x509CertificateArr, trustManagerFactory, x509CertificateArr2, privateKey, str, keyManagerFactory, iterable, cipherSuiteFilter, applicationProtocolConfig, strArr, j, j2, z);
        }
        throw new Error(defaultClientProvider.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ApplicationProtocolConfig toApplicationProtocolConfig(Iterable<String> iterable) {
        if (iterable == null) {
            return ApplicationProtocolConfig.DISABLED;
        }
        return new ApplicationProtocolConfig(ApplicationProtocolConfig.Protocol.NPN_AND_ALPN, ApplicationProtocolConfig.SelectorFailureBehavior.CHOOSE_MY_LAST_PROTOCOL, ApplicationProtocolConfig.SelectedListenerFailureBehavior.ACCEPT, iterable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SslContext() {
        this(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SslContext(boolean z) {
        this.startTls = z;
    }

    public final boolean isServer() {
        return !isClient();
    }

    @Deprecated
    public final List<String> nextProtocols() {
        return applicationProtocolNegotiator().protocols();
    }

    public final SslHandler newHandler(ByteBufAllocator byteBufAllocator) {
        return newHandler(byteBufAllocator, this.startTls);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SslHandler newHandler(ByteBufAllocator byteBufAllocator, boolean z) {
        return new SslHandler(newEngine(byteBufAllocator), z);
    }

    public final SslHandler newHandler(ByteBufAllocator byteBufAllocator, String str, int i) {
        return newHandler(byteBufAllocator, str, i, this.startTls);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SslHandler newHandler(ByteBufAllocator byteBufAllocator, String str, int i, boolean z) {
        return new SslHandler(newEngine(byteBufAllocator, str, i), z);
    }

    protected static PKCS8EncodedKeySpec generateKeySpec(char[] cArr, byte[] bArr) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidKeyException, InvalidAlgorithmParameterException {
        if (cArr == null) {
            return new PKCS8EncodedKeySpec(bArr);
        }
        EncryptedPrivateKeyInfo encryptedPrivateKeyInfo = new EncryptedPrivateKeyInfo(bArr);
        SecretKey generateSecret = SecretKeyFactory.getInstance(encryptedPrivateKeyInfo.getAlgName()).generateSecret(new PBEKeySpec(cArr));
        Cipher cipher = Cipher.getInstance(encryptedPrivateKeyInfo.getAlgName());
        cipher.init(2, generateSecret, encryptedPrivateKeyInfo.getAlgParameters());
        return encryptedPrivateKeyInfo.getKeySpec(cipher);
    }

    static KeyStore buildKeyStore(X509Certificate[] x509CertificateArr, PrivateKey privateKey, char[] cArr) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null, null);
        keyStore.setKeyEntry(TransferTable.COLUMN_KEY, privateKey, cArr, x509CertificateArr);
        return keyStore;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PrivateKey toPrivateKey(File file, String str) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, KeyException, IOException {
        if (file == null) {
            return null;
        }
        return getPrivateKeyFromByteBuffer(PemReader.readPrivateKey(file), str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PrivateKey toPrivateKey(InputStream inputStream, String str) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, KeyException, IOException {
        if (inputStream == null) {
            return null;
        }
        return getPrivateKeyFromByteBuffer(PemReader.readPrivateKey(inputStream), str);
    }

    private static PrivateKey getPrivateKeyFromByteBuffer(ByteBuf byteBuf, String str) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, KeyException, IOException {
        byte[] bArr = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bArr).release();
        PKCS8EncodedKeySpec generateKeySpec = generateKeySpec(str == null ? null : str.toCharArray(), bArr);
        try {
            try {
                try {
                    return KeyFactory.getInstance("RSA").generatePrivate(generateKeySpec);
                } catch (InvalidKeySpecException unused) {
                    return KeyFactory.getInstance("EC").generatePrivate(generateKeySpec);
                }
            } catch (InvalidKeySpecException e) {
                throw new InvalidKeySpecException("Neither RSA, DSA nor EC worked", e);
            }
        } catch (InvalidKeySpecException unused2) {
            return KeyFactory.getInstance("DSA").generatePrivate(generateKeySpec);
        }
    }

    @Deprecated
    protected static TrustManagerFactory buildTrustManagerFactory(File file, TrustManagerFactory trustManagerFactory) throws NoSuchAlgorithmException, CertificateException, KeyStoreException, IOException {
        return buildTrustManagerFactory(toX509Certificates(file), trustManagerFactory);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static X509Certificate[] toX509Certificates(File file) throws CertificateException {
        if (file == null) {
            return null;
        }
        return getCertificatesFromBuffers(PemReader.readCertificates(file));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static X509Certificate[] toX509Certificates(InputStream inputStream) throws CertificateException {
        if (inputStream == null) {
            return null;
        }
        return getCertificatesFromBuffers(PemReader.readCertificates(inputStream));
    }

    private static X509Certificate[] getCertificatesFromBuffers(ByteBuf[] byteBufArr) throws CertificateException {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        X509Certificate[] x509CertificateArr = new X509Certificate[byteBufArr.length];
        int i = 0;
        while (i < byteBufArr.length) {
            try {
                ByteBufInputStream byteBufInputStream = new ByteBufInputStream(byteBufArr[i], true);
                try {
                    x509CertificateArr[i] = (X509Certificate) certificateFactory.generateCertificate(byteBufInputStream);
                    try {
                        byteBufInputStream.close();
                        i++;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } catch (Throwable th) {
                    try {
                        byteBufInputStream.close();
                        throw th;
                    } catch (IOException e2) {
                        throw new RuntimeException(e2);
                    }
                }
            } finally {
                while (i < byteBufArr.length) {
                    byteBufArr[i].release();
                    i++;
                }
            }
        }
        return x509CertificateArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TrustManagerFactory buildTrustManagerFactory(X509Certificate[] x509CertificateArr, TrustManagerFactory trustManagerFactory) throws NoSuchAlgorithmException, CertificateException, KeyStoreException, IOException {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null, null);
        int i = 1;
        for (X509Certificate x509Certificate : x509CertificateArr) {
            keyStore.setCertificateEntry(Integer.toString(i), x509Certificate);
            i++;
        }
        if (trustManagerFactory == null) {
            trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        }
        trustManagerFactory.init(keyStore);
        return trustManagerFactory;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PrivateKey toPrivateKeyInternal(File file, String str) throws SSLException {
        try {
            return toPrivateKey(file, str);
        } catch (Exception e) {
            throw new SSLException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static X509Certificate[] toX509CertificatesInternal(File file) throws SSLException {
        try {
            return toX509Certificates(file);
        } catch (CertificateException e) {
            throw new SSLException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KeyManagerFactory buildKeyManagerFactory(X509Certificate[] x509CertificateArr, PrivateKey privateKey, String str, KeyManagerFactory keyManagerFactory) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
        return buildKeyManagerFactory(x509CertificateArr, KeyManagerFactory.getDefaultAlgorithm(), privateKey, str, keyManagerFactory);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KeyManagerFactory buildKeyManagerFactory(X509Certificate[] x509CertificateArr, String str, PrivateKey privateKey, String str2, KeyManagerFactory keyManagerFactory) throws KeyStoreException, NoSuchAlgorithmException, IOException, CertificateException, UnrecoverableKeyException {
        char[] charArray = str2 == null ? EmptyArrays.EMPTY_CHARS : str2.toCharArray();
        KeyStore buildKeyStore = buildKeyStore(x509CertificateArr, privateKey, charArray);
        if (keyManagerFactory == null) {
            keyManagerFactory = KeyManagerFactory.getInstance(str);
        }
        keyManagerFactory.init(buildKeyStore, charArray);
        return keyManagerFactory;
    }
}
