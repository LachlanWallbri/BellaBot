package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.handler.ssl.ReferenceCountedOpenSslContext;
import io.grpc.netty.shaded.io.netty.internal.tcnative.CertificateCallback;
import io.grpc.netty.shaded.io.netty.internal.tcnative.SSLContext;
import io.grpc.netty.shaded.io.netty.internal.tcnative.SniHostNameMatcher;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.SystemPropertyUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ReferenceCountedOpenSslServerContext extends ReferenceCountedOpenSslContext {
    private final OpenSslServerSessionContext sessionContext;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) ReferenceCountedOpenSslServerContext.class);

    /* renamed from: ID */
    private static final byte[] f8422ID = {110, 101, 116, 116, 121};
    private static final boolean ENABLE_SESSION_TICKET = SystemPropertyUtil.getBoolean("jdk.tls.server.enableSessionTicketExtension", false);

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReferenceCountedOpenSslServerContext(X509Certificate[] x509CertificateArr, TrustManagerFactory trustManagerFactory, X509Certificate[] x509CertificateArr2, PrivateKey privateKey, String str, KeyManagerFactory keyManagerFactory, Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, ApplicationProtocolConfig applicationProtocolConfig, long j, long j2, ClientAuth clientAuth, String[] strArr, boolean z, boolean z2, String str2) throws SSLException {
        this(x509CertificateArr, trustManagerFactory, x509CertificateArr2, privateKey, str, keyManagerFactory, iterable, cipherSuiteFilter, toNegotiator(applicationProtocolConfig), j, j2, clientAuth, strArr, z, z2, str2);
    }

    ReferenceCountedOpenSslServerContext(X509Certificate[] x509CertificateArr, TrustManagerFactory trustManagerFactory, X509Certificate[] x509CertificateArr2, PrivateKey privateKey, String str, KeyManagerFactory keyManagerFactory, Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, OpenSslApplicationProtocolNegotiator openSslApplicationProtocolNegotiator, long j, long j2, ClientAuth clientAuth, String[] strArr, boolean z, boolean z2, String str2) throws SSLException {
        super(iterable, cipherSuiteFilter, openSslApplicationProtocolNegotiator, j, j2, 1, (Certificate[]) x509CertificateArr2, clientAuth, strArr, z, z2, true);
        try {
            this.sessionContext = newSessionContext(this, this.ctx, this.engineMap, x509CertificateArr, trustManagerFactory, x509CertificateArr2, privateKey, str, keyManagerFactory, str2);
            if (ENABLE_SESSION_TICKET) {
                this.sessionContext.setTicketKeys(new OpenSslSessionTicketKey[0]);
            }
        } catch (Throwable th) {
            release();
            throw th;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.ReferenceCountedOpenSslContext, io.grpc.netty.shaded.io.netty.handler.ssl.SslContext
    public OpenSslServerSessionContext sessionContext() {
        return this.sessionContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static OpenSslServerSessionContext newSessionContext(ReferenceCountedOpenSslContext referenceCountedOpenSslContext, long j, OpenSslEngineMap openSslEngineMap, X509Certificate[] x509CertificateArr, TrustManagerFactory trustManagerFactory, X509Certificate[] x509CertificateArr2, PrivateKey privateKey, String str, KeyManagerFactory keyManagerFactory, String str2) throws SSLException {
        OpenSslKeyMaterialProvider openSslKeyMaterialProvider;
        KeyManagerFactory keyManagerFactory2;
        try {
            try {
                SSLContext.setVerify(j, 0, 10);
                if (OpenSsl.useKeyManagerFactory()) {
                    if (keyManagerFactory == null) {
                        char[] keyStorePassword = keyStorePassword(str);
                        KeyStore buildKeyStore = buildKeyStore(x509CertificateArr2, privateKey, keyStorePassword, str2);
                        if (buildKeyStore.aliases().hasMoreElements()) {
                            keyManagerFactory2 = new OpenSslX509KeyManagerFactory();
                        } else {
                            keyManagerFactory2 = new OpenSslCachingX509KeyManagerFactory(KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm()));
                        }
                        keyManagerFactory2.init(buildKeyStore, keyStorePassword);
                    } else {
                        keyManagerFactory2 = keyManagerFactory;
                    }
                    openSslKeyMaterialProvider = providerFor(keyManagerFactory2, str);
                    try {
                        try {
                            SSLContext.setCertificateCallback(j, new OpenSslServerCertificateCallback(openSslEngineMap, new OpenSslKeyMaterialManager(openSslKeyMaterialProvider)));
                        } catch (Throwable th) {
                            th = th;
                            if (openSslKeyMaterialProvider != null) {
                                openSslKeyMaterialProvider.destroy();
                            }
                            throw th;
                        }
                    } catch (Exception e) {
                        e = e;
                        throw new SSLException("failed to set certificate and key", e);
                    }
                } else {
                    if (keyManagerFactory != null) {
                        throw new IllegalArgumentException("KeyManagerFactory not supported");
                    }
                    ObjectUtil.checkNotNull(x509CertificateArr2, "keyCertChain");
                    setKeyMaterial(j, x509CertificateArr2, privateKey, str);
                    openSslKeyMaterialProvider = null;
                }
                try {
                    if (x509CertificateArr != null) {
                        trustManagerFactory = buildTrustManagerFactory(x509CertificateArr, trustManagerFactory, str2);
                    } else if (trustManagerFactory == null) {
                        trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                        trustManagerFactory.init((KeyStore) null);
                    }
                    X509TrustManager chooseTrustManager = chooseTrustManager(trustManagerFactory.getTrustManagers());
                    setVerifyCallback(j, openSslEngineMap, chooseTrustManager);
                    X509Certificate[] acceptedIssuers = chooseTrustManager.getAcceptedIssuers();
                    if (acceptedIssuers != null && acceptedIssuers.length > 0) {
                        long j2 = 0;
                        try {
                            j2 = toBIO(ByteBufAllocator.DEFAULT, acceptedIssuers);
                            if (!SSLContext.setCACertificateBio(j, j2)) {
                                throw new SSLException("unable to setup accepted issuers for trustmanager " + chooseTrustManager);
                            }
                        } finally {
                            freeBio(j2);
                        }
                    }
                    if (PlatformDependent.javaVersion() >= 8) {
                        SSLContext.setSniHostnameMatcher(j, new OpenSslSniHostnameMatcher(openSslEngineMap));
                    }
                    OpenSslServerSessionContext openSslServerSessionContext = new OpenSslServerSessionContext(referenceCountedOpenSslContext, openSslKeyMaterialProvider);
                    openSslServerSessionContext.setSessionIdContext(f8422ID);
                    return openSslServerSessionContext;
                } catch (SSLException e2) {
                    throw e2;
                } catch (Exception e3) {
                    throw new SSLException("unable to setup trustmanager", e3);
                }
            } catch (Exception e4) {
                e = e4;
            }
        } catch (Throwable th2) {
            th = th2;
            openSslKeyMaterialProvider = null;
        }
    }

    private static void setVerifyCallback(long j, OpenSslEngineMap openSslEngineMap, X509TrustManager x509TrustManager) {
        if (useExtendedTrustManager(x509TrustManager)) {
            SSLContext.setCertVerifyCallback(j, new ExtendedTrustManagerVerifyCallback(openSslEngineMap, (X509ExtendedTrustManager) x509TrustManager));
        } else {
            SSLContext.setCertVerifyCallback(j, new TrustManagerVerifyCallback(openSslEngineMap, x509TrustManager));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class OpenSslServerCertificateCallback implements CertificateCallback {
        private final OpenSslEngineMap engineMap;
        private final OpenSslKeyMaterialManager keyManagerHolder;

        OpenSslServerCertificateCallback(OpenSslEngineMap openSslEngineMap, OpenSslKeyMaterialManager openSslKeyMaterialManager) {
            this.engineMap = openSslEngineMap;
            this.keyManagerHolder = openSslKeyMaterialManager;
        }

        @Override // io.grpc.netty.shaded.io.netty.internal.tcnative.CertificateCallback
        public void handle(long j, byte[] bArr, byte[][] bArr2) throws Exception {
            ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine = this.engineMap.get(j);
            if (referenceCountedOpenSslEngine == null) {
                return;
            }
            try {
                this.keyManagerHolder.setKeyMaterialServerSide(referenceCountedOpenSslEngine);
            } catch (Throwable th) {
                ReferenceCountedOpenSslServerContext.logger.debug("Failed to set the server-side key material", th);
                referenceCountedOpenSslEngine.initHandshakeException(th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class TrustManagerVerifyCallback extends ReferenceCountedOpenSslContext.AbstractCertificateVerifier {
        private final X509TrustManager manager;

        TrustManagerVerifyCallback(OpenSslEngineMap openSslEngineMap, X509TrustManager x509TrustManager) {
            super(openSslEngineMap);
            this.manager = x509TrustManager;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.ssl.ReferenceCountedOpenSslContext.AbstractCertificateVerifier
        void verify(ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine, X509Certificate[] x509CertificateArr, String str) throws Exception {
            this.manager.checkClientTrusted(x509CertificateArr, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class ExtendedTrustManagerVerifyCallback extends ReferenceCountedOpenSslContext.AbstractCertificateVerifier {
        private final X509ExtendedTrustManager manager;

        ExtendedTrustManagerVerifyCallback(OpenSslEngineMap openSslEngineMap, X509ExtendedTrustManager x509ExtendedTrustManager) {
            super(openSslEngineMap);
            this.manager = OpenSslTlsv13X509ExtendedTrustManager.wrap(x509ExtendedTrustManager);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.ssl.ReferenceCountedOpenSslContext.AbstractCertificateVerifier
        void verify(ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine, X509Certificate[] x509CertificateArr, String str) throws Exception {
            this.manager.checkClientTrusted(x509CertificateArr, str, referenceCountedOpenSslEngine);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class OpenSslSniHostnameMatcher implements SniHostNameMatcher {
        private final OpenSslEngineMap engineMap;

        OpenSslSniHostnameMatcher(OpenSslEngineMap openSslEngineMap) {
            this.engineMap = openSslEngineMap;
        }

        @Override // io.grpc.netty.shaded.io.netty.internal.tcnative.SniHostNameMatcher
        public boolean match(long j, String str) {
            ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine = this.engineMap.get(j);
            if (referenceCountedOpenSslEngine == null) {
                ReferenceCountedOpenSslServerContext.logger.warn("No ReferenceCountedOpenSslEngine found for SSL pointer: {}", Long.valueOf(j));
                return false;
            }
            return referenceCountedOpenSslEngine.checkSniHostnameMatch(str.getBytes(CharsetUtil.UTF_8));
        }
    }
}
