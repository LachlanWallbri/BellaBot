package io.grpc.netty.shaded.io.netty.handler.ssl;

import java.io.File;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManagerFactory;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class OpenSslServerContext extends OpenSslContext {
    private final OpenSslServerSessionContext sessionContext;

    @Deprecated
    public OpenSslServerContext(File file, File file2) throws SSLException {
        this(file, file2, null);
    }

    @Deprecated
    public OpenSslServerContext(File file, File file2, String str) throws SSLException {
        this(file, file2, str, (Iterable<String>) null, IdentityCipherSuiteFilter.INSTANCE, ApplicationProtocolConfig.DISABLED, 0L, 0L);
    }

    @Deprecated
    public OpenSslServerContext(File file, File file2, String str, Iterable<String> iterable, ApplicationProtocolConfig applicationProtocolConfig, long j, long j2) throws SSLException {
        this(file, file2, str, iterable, IdentityCipherSuiteFilter.INSTANCE, applicationProtocolConfig, j, j2);
    }

    @Deprecated
    public OpenSslServerContext(File file, File file2, String str, Iterable<String> iterable, Iterable<String> iterable2, long j, long j2) throws SSLException {
        this(file, file2, str, iterable, toApplicationProtocolConfig(iterable2), j, j2);
    }

    @Deprecated
    public OpenSslServerContext(File file, File file2, String str, TrustManagerFactory trustManagerFactory, Iterable<String> iterable, ApplicationProtocolConfig applicationProtocolConfig, long j, long j2) throws SSLException {
        this(file, file2, str, trustManagerFactory, iterable, toNegotiator(applicationProtocolConfig), j, j2);
    }

    @Deprecated
    public OpenSslServerContext(File file, File file2, String str, TrustManagerFactory trustManagerFactory, Iterable<String> iterable, OpenSslApplicationProtocolNegotiator openSslApplicationProtocolNegotiator, long j, long j2) throws SSLException {
        this((File) null, trustManagerFactory, file, file2, str, (KeyManagerFactory) null, iterable, (CipherSuiteFilter) null, openSslApplicationProtocolNegotiator, j, j2);
    }

    @Deprecated
    public OpenSslServerContext(File file, File file2, String str, Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, ApplicationProtocolConfig applicationProtocolConfig, long j, long j2) throws SSLException {
        this((File) null, (TrustManagerFactory) null, file, file2, str, (KeyManagerFactory) null, iterable, cipherSuiteFilter, applicationProtocolConfig, j, j2);
    }

    @Deprecated
    public OpenSslServerContext(File file, TrustManagerFactory trustManagerFactory, File file2, File file3, String str, KeyManagerFactory keyManagerFactory, Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, ApplicationProtocolConfig applicationProtocolConfig, long j, long j2) throws SSLException {
        this(file, trustManagerFactory, file2, file3, str, keyManagerFactory, iterable, cipherSuiteFilter, toNegotiator(applicationProtocolConfig), j, j2);
    }

    @Deprecated
    public OpenSslServerContext(File file, File file2, String str, TrustManagerFactory trustManagerFactory, Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, ApplicationProtocolConfig applicationProtocolConfig, long j, long j2) throws SSLException {
        this((File) null, trustManagerFactory, file, file2, str, (KeyManagerFactory) null, iterable, cipherSuiteFilter, toNegotiator(applicationProtocolConfig), j, j2);
    }

    @Deprecated
    public OpenSslServerContext(File file, File file2, String str, TrustManagerFactory trustManagerFactory, Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, OpenSslApplicationProtocolNegotiator openSslApplicationProtocolNegotiator, long j, long j2) throws SSLException {
        this((File) null, trustManagerFactory, file, file2, str, (KeyManagerFactory) null, iterable, cipherSuiteFilter, openSslApplicationProtocolNegotiator, j, j2);
    }

    @Deprecated
    public OpenSslServerContext(File file, TrustManagerFactory trustManagerFactory, File file2, File file3, String str, KeyManagerFactory keyManagerFactory, Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, OpenSslApplicationProtocolNegotiator openSslApplicationProtocolNegotiator, long j, long j2) throws SSLException {
        this(toX509CertificatesInternal(file), trustManagerFactory, toX509CertificatesInternal(file2), toPrivateKeyInternal(file3, str), str, keyManagerFactory, iterable, cipherSuiteFilter, openSslApplicationProtocolNegotiator, j, j2, ClientAuth.NONE, (String[]) null, false, false, KeyStore.getDefaultType());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OpenSslServerContext(X509Certificate[] x509CertificateArr, TrustManagerFactory trustManagerFactory, X509Certificate[] x509CertificateArr2, PrivateKey privateKey, String str, KeyManagerFactory keyManagerFactory, Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, ApplicationProtocolConfig applicationProtocolConfig, long j, long j2, ClientAuth clientAuth, String[] strArr, boolean z, boolean z2, String str2) throws SSLException {
        this(x509CertificateArr, trustManagerFactory, x509CertificateArr2, privateKey, str, keyManagerFactory, iterable, cipherSuiteFilter, toNegotiator(applicationProtocolConfig), j, j2, clientAuth, strArr, z, z2, str2);
    }

    private OpenSslServerContext(X509Certificate[] x509CertificateArr, TrustManagerFactory trustManagerFactory, X509Certificate[] x509CertificateArr2, PrivateKey privateKey, String str, KeyManagerFactory keyManagerFactory, Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, OpenSslApplicationProtocolNegotiator openSslApplicationProtocolNegotiator, long j, long j2, ClientAuth clientAuth, String[] strArr, boolean z, boolean z2, String str2) throws SSLException {
        super(iterable, cipherSuiteFilter, openSslApplicationProtocolNegotiator, j, j2, 1, x509CertificateArr2, clientAuth, strArr, z, z2);
        try {
            OpenSslKeyMaterialProvider.validateKeyMaterialSupported(x509CertificateArr2, privateKey, str);
            this.sessionContext = ReferenceCountedOpenSslServerContext.newSessionContext(this, this.ctx, this.engineMap, x509CertificateArr, trustManagerFactory, x509CertificateArr2, privateKey, str, keyManagerFactory, str2);
        } catch (Throwable th) {
            release();
            throw th;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.ReferenceCountedOpenSslContext, io.grpc.netty.shaded.io.netty.handler.ssl.SslContext
    public OpenSslServerSessionContext sessionContext() {
        return this.sessionContext;
    }
}
