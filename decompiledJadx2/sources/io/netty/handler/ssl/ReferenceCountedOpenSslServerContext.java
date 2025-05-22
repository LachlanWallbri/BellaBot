package io.netty.handler.ssl;

import io.netty.handler.ssl.ReferenceCountedOpenSslContext;
import io.netty.internal.tcnative.CertificateCallback;
import io.netty.internal.tcnative.SSLContext;
import io.netty.internal.tcnative.SniHostNameMatcher;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509KeyManager;
import javax.net.ssl.X509TrustManager;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class ReferenceCountedOpenSslServerContext extends ReferenceCountedOpenSslContext {
    private final OpenSslKeyMaterialManager keyMaterialManager;
    private final OpenSslServerSessionContext sessionContext;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) ReferenceCountedOpenSslServerContext.class);

    /* renamed from: ID */
    private static final byte[] f8561ID = {110, 101, 116, 116, 121};

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReferenceCountedOpenSslServerContext(X509Certificate[] x509CertificateArr, TrustManagerFactory trustManagerFactory, X509Certificate[] x509CertificateArr2, PrivateKey privateKey, String str, KeyManagerFactory keyManagerFactory, Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, ApplicationProtocolConfig applicationProtocolConfig, long j, long j2, ClientAuth clientAuth, String[] strArr, boolean z, boolean z2) throws SSLException {
        this(x509CertificateArr, trustManagerFactory, x509CertificateArr2, privateKey, str, keyManagerFactory, iterable, cipherSuiteFilter, toNegotiator(applicationProtocolConfig), j, j2, clientAuth, strArr, z, z2);
    }

    private ReferenceCountedOpenSslServerContext(X509Certificate[] x509CertificateArr, TrustManagerFactory trustManagerFactory, X509Certificate[] x509CertificateArr2, PrivateKey privateKey, String str, KeyManagerFactory keyManagerFactory, Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, OpenSslApplicationProtocolNegotiator openSslApplicationProtocolNegotiator, long j, long j2, ClientAuth clientAuth, String[] strArr, boolean z, boolean z2) throws SSLException {
        super(iterable, cipherSuiteFilter, openSslApplicationProtocolNegotiator, j, j2, 1, (Certificate[]) x509CertificateArr2, clientAuth, strArr, z, z2, true);
        try {
            ServerContext newSessionContext = newSessionContext(this, this.ctx, this.engineMap, x509CertificateArr, trustManagerFactory, x509CertificateArr2, privateKey, str, keyManagerFactory);
            this.sessionContext = newSessionContext.sessionContext;
            this.keyMaterialManager = newSessionContext.keyMaterialManager;
        } catch (Throwable th) {
            release();
            throw th;
        }
    }

    @Override // io.netty.handler.ssl.ReferenceCountedOpenSslContext, io.netty.handler.ssl.SslContext
    public OpenSslServerSessionContext sessionContext() {
        return this.sessionContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.netty.handler.ssl.ReferenceCountedOpenSslContext
    public OpenSslKeyMaterialManager keyMaterialManager() {
        return this.keyMaterialManager;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class ServerContext {
        OpenSslKeyMaterialManager keyMaterialManager;
        OpenSslServerSessionContext sessionContext;

        ServerContext() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ServerContext newSessionContext(ReferenceCountedOpenSslContext referenceCountedOpenSslContext, long j, OpenSslEngineMap openSslEngineMap, X509Certificate[] x509CertificateArr, TrustManagerFactory trustManagerFactory, X509Certificate[] x509CertificateArr2, PrivateKey privateKey, String str, KeyManagerFactory keyManagerFactory) throws SSLException {
        ServerContext serverContext = new ServerContext();
        try {
            SSLContext.setVerify(j, 0, 10);
            if (OpenSsl.useKeyManagerFactory()) {
                if (keyManagerFactory == null) {
                    keyManagerFactory = buildKeyManagerFactory(x509CertificateArr2, privateKey, str, keyManagerFactory);
                }
                X509KeyManager chooseX509KeyManager = chooseX509KeyManager(keyManagerFactory.getKeyManagers());
                serverContext.keyMaterialManager = useExtendedKeyManager(chooseX509KeyManager) ? new OpenSslExtendedKeyMaterialManager((X509ExtendedKeyManager) chooseX509KeyManager, str) : new OpenSslKeyMaterialManager(chooseX509KeyManager, str);
            } else {
                if (keyManagerFactory != null) {
                    throw new IllegalArgumentException("KeyManagerFactory not supported");
                }
                ObjectUtil.checkNotNull(x509CertificateArr2, "keyCertChain");
                setKeyMaterial(j, x509CertificateArr2, privateKey, str);
            }
            try {
                if (x509CertificateArr != null) {
                    trustManagerFactory = buildTrustManagerFactory(x509CertificateArr, trustManagerFactory);
                } else if (trustManagerFactory == null) {
                    trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                    trustManagerFactory.init((KeyStore) null);
                }
                X509TrustManager chooseTrustManager = chooseTrustManager(trustManagerFactory.getTrustManagers());
                if (useExtendedTrustManager(chooseTrustManager)) {
                    SSLContext.setCertVerifyCallback(j, new ExtendedTrustManagerVerifyCallback(openSslEngineMap, (X509ExtendedTrustManager) chooseTrustManager));
                } else {
                    SSLContext.setCertVerifyCallback(j, new TrustManagerVerifyCallback(openSslEngineMap, chooseTrustManager));
                }
                X509Certificate[] acceptedIssuers = chooseTrustManager.getAcceptedIssuers();
                if (acceptedIssuers != null && acceptedIssuers.length > 0) {
                    long j2 = 0;
                    try {
                        j2 = toBIO(acceptedIssuers);
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
                serverContext.sessionContext = new OpenSslServerSessionContext(referenceCountedOpenSslContext);
                serverContext.sessionContext.setSessionIdContext(f8561ID);
                return serverContext;
            } catch (SSLException e) {
                throw e;
            } catch (Exception e2) {
                throw new SSLException("unable to setup trustmanager", e2);
            }
        } catch (Exception e3) {
            throw new SSLException("failed to set certificate and key", e3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public static final class TrustManagerVerifyCallback extends ReferenceCountedOpenSslContext.AbstractCertificateVerifier {
        private final X509TrustManager manager;

        TrustManagerVerifyCallback(OpenSslEngineMap openSslEngineMap, X509TrustManager x509TrustManager) {
            super(openSslEngineMap);
            this.manager = x509TrustManager;
        }

        @Override // io.netty.handler.ssl.ReferenceCountedOpenSslContext.AbstractCertificateVerifier
        void verify(ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine, X509Certificate[] x509CertificateArr, String str) throws Exception {
            this.manager.checkClientTrusted(x509CertificateArr, str);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes8.dex */
    private static final class OpenSslServerCertificateCallback implements CertificateCallback {
        private final OpenSslEngineMap engineMap;
        private final OpenSslKeyMaterialManager keyManagerHolder;

        OpenSslServerCertificateCallback(OpenSslEngineMap openSslEngineMap, OpenSslKeyMaterialManager openSslKeyMaterialManager) {
            this.engineMap = openSslEngineMap;
            this.keyManagerHolder = openSslKeyMaterialManager;
        }

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
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public static final class ExtendedTrustManagerVerifyCallback extends ReferenceCountedOpenSslContext.AbstractCertificateVerifier {
        private final X509ExtendedTrustManager manager;

        ExtendedTrustManagerVerifyCallback(OpenSslEngineMap openSslEngineMap, X509ExtendedTrustManager x509ExtendedTrustManager) {
            super(openSslEngineMap);
            this.manager = x509ExtendedTrustManager;
        }

        @Override // io.netty.handler.ssl.ReferenceCountedOpenSslContext.AbstractCertificateVerifier
        void verify(ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine, X509Certificate[] x509CertificateArr, String str) throws Exception {
            this.manager.checkClientTrusted(x509CertificateArr, str, referenceCountedOpenSslEngine);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public static final class OpenSslSniHostnameMatcher implements SniHostNameMatcher {
        private final OpenSslEngineMap engineMap;

        OpenSslSniHostnameMatcher(OpenSslEngineMap openSslEngineMap) {
            this.engineMap = openSslEngineMap;
        }

        public boolean match(long j, String str) {
            ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine = this.engineMap.get(j);
            if (referenceCountedOpenSslEngine == null) {
                ReferenceCountedOpenSslServerContext.logger.warn("No ReferenceCountedOpenSslEngine found for SSL pointer: {}", Long.valueOf(j));
                return false;
            }
            return referenceCountedOpenSslEngine.checkSniHostnameMatch(str);
        }
    }
}
