package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.handler.ssl.ReferenceCountedOpenSslContext;
import io.grpc.netty.shaded.io.netty.internal.tcnative.CertificateCallback;
import io.grpc.netty.shaded.io.netty.internal.tcnative.SSLContext;
import io.grpc.netty.shaded.io.netty.util.internal.SystemPropertyUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ReferenceCountedOpenSslClientContext extends ReferenceCountedOpenSslContext {
    private final OpenSslSessionContext sessionContext;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) ReferenceCountedOpenSslClientContext.class);
    private static final Set<String> SUPPORTED_KEY_TYPES = Collections.unmodifiableSet(new LinkedHashSet(Arrays.asList("RSA", "DH_RSA", "EC", "EC_RSA", "EC_EC")));
    private static final boolean ENABLE_SESSION_TICKET = SystemPropertyUtil.getBoolean("jdk.tls.client.enableSessionTicketExtension", false);

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReferenceCountedOpenSslClientContext(X509Certificate[] x509CertificateArr, TrustManagerFactory trustManagerFactory, X509Certificate[] x509CertificateArr2, PrivateKey privateKey, String str, KeyManagerFactory keyManagerFactory, Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, ApplicationProtocolConfig applicationProtocolConfig, String[] strArr, long j, long j2, boolean z, String str2) throws SSLException {
        super(iterable, cipherSuiteFilter, applicationProtocolConfig, j, j2, 0, (Certificate[]) x509CertificateArr2, ClientAuth.NONE, strArr, false, z, true);
        try {
            this.sessionContext = newSessionContext(this, this.ctx, this.engineMap, x509CertificateArr, trustManagerFactory, x509CertificateArr2, privateKey, str, keyManagerFactory, str2);
        } catch (Throwable th) {
            release();
            throw th;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.ReferenceCountedOpenSslContext, io.grpc.netty.shaded.io.netty.handler.ssl.SslContext
    public OpenSslSessionContext sessionContext() {
        return this.sessionContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static OpenSslSessionContext newSessionContext(ReferenceCountedOpenSslContext referenceCountedOpenSslContext, long j, OpenSslEngineMap openSslEngineMap, X509Certificate[] x509CertificateArr, TrustManagerFactory trustManagerFactory, X509Certificate[] x509CertificateArr2, PrivateKey privateKey, String str, KeyManagerFactory keyManagerFactory, String str2) throws SSLException {
        OpenSslKeyMaterialProvider openSslKeyMaterialProvider;
        KeyManagerFactory openSslCachingX509KeyManagerFactory;
        if ((privateKey == null && x509CertificateArr2 != null) || (privateKey != null && x509CertificateArr2 == null)) {
            throw new IllegalArgumentException("Either both keyCertChain and key needs to be null or none of them");
        }
        try {
            try {
                if (OpenSsl.useKeyManagerFactory()) {
                    if (keyManagerFactory == null && x509CertificateArr2 != null) {
                        char[] keyStorePassword = keyStorePassword(str);
                        KeyStore buildKeyStore = buildKeyStore(x509CertificateArr2, privateKey, keyStorePassword, str2);
                        if (buildKeyStore.aliases().hasMoreElements()) {
                            openSslCachingX509KeyManagerFactory = new OpenSslX509KeyManagerFactory();
                        } else {
                            openSslCachingX509KeyManagerFactory = new OpenSslCachingX509KeyManagerFactory(KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm()));
                        }
                        openSslCachingX509KeyManagerFactory.init(buildKeyStore, keyStorePassword);
                        openSslKeyMaterialProvider = providerFor(openSslCachingX509KeyManagerFactory, str);
                    } else {
                        openSslKeyMaterialProvider = keyManagerFactory != null ? providerFor(keyManagerFactory, str) : null;
                    }
                    if (openSslKeyMaterialProvider != null) {
                        try {
                            try {
                                SSLContext.setCertificateCallback(j, new OpenSslClientCertificateCallback(openSslEngineMap, new OpenSslKeyMaterialManager(openSslKeyMaterialProvider)));
                            } catch (Exception e) {
                                e = e;
                                throw new SSLException("failed to set certificate and key", e);
                            }
                        } catch (Throwable th) {
                            th = th;
                            if (openSslKeyMaterialProvider != null) {
                                openSslKeyMaterialProvider.destroy();
                            }
                            throw th;
                        }
                    }
                } else {
                    if (keyManagerFactory != null) {
                        throw new IllegalArgumentException("KeyManagerFactory not supported");
                    }
                    if (x509CertificateArr2 != null) {
                        setKeyMaterial(j, x509CertificateArr2, privateKey, str);
                    }
                    openSslKeyMaterialProvider = null;
                }
                SSLContext.setVerify(j, 1, 10);
                try {
                    if (x509CertificateArr != null) {
                        trustManagerFactory = buildTrustManagerFactory(x509CertificateArr, trustManagerFactory, str2);
                    } else if (trustManagerFactory == null) {
                        trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                        trustManagerFactory.init((KeyStore) null);
                    }
                    setVerifyCallback(j, openSslEngineMap, chooseTrustManager(trustManagerFactory.getTrustManagers()));
                    OpenSslClientSessionContext openSslClientSessionContext = new OpenSslClientSessionContext(referenceCountedOpenSslContext, openSslKeyMaterialProvider);
                    if (ENABLE_SESSION_TICKET) {
                        openSslClientSessionContext.setTicketKeys(new OpenSslSessionTicketKey[0]);
                    }
                    return openSslClientSessionContext;
                } catch (Exception e2) {
                    if (openSslKeyMaterialProvider != null) {
                        openSslKeyMaterialProvider.destroy();
                    }
                    throw new SSLException("unable to setup trustmanager", e2);
                }
            } catch (Throwable th2) {
                th = th2;
                openSslKeyMaterialProvider = null;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    private static void setVerifyCallback(long j, OpenSslEngineMap openSslEngineMap, X509TrustManager x509TrustManager) {
        if (useExtendedTrustManager(x509TrustManager)) {
            SSLContext.setCertVerifyCallback(j, new ExtendedTrustManagerVerifyCallback(openSslEngineMap, (X509ExtendedTrustManager) x509TrustManager));
        } else {
            SSLContext.setCertVerifyCallback(j, new TrustManagerVerifyCallback(openSslEngineMap, x509TrustManager));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class OpenSslClientSessionContext extends OpenSslSessionContext {
        @Override // javax.net.ssl.SSLSessionContext
        public int getSessionCacheSize() {
            return 0;
        }

        @Override // javax.net.ssl.SSLSessionContext
        public int getSessionTimeout() {
            return 0;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.ssl.OpenSslSessionContext
        public boolean isSessionCacheEnabled() {
            return false;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.ssl.OpenSslSessionContext
        public void setSessionCacheEnabled(boolean z) {
        }

        OpenSslClientSessionContext(ReferenceCountedOpenSslContext referenceCountedOpenSslContext, OpenSslKeyMaterialProvider openSslKeyMaterialProvider) {
            super(referenceCountedOpenSslContext, openSslKeyMaterialProvider);
        }

        @Override // javax.net.ssl.SSLSessionContext
        public void setSessionTimeout(int i) {
            if (i < 0) {
                throw new IllegalArgumentException();
            }
        }

        @Override // javax.net.ssl.SSLSessionContext
        public void setSessionCacheSize(int i) {
            if (i < 0) {
                throw new IllegalArgumentException();
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
            this.manager.checkServerTrusted(x509CertificateArr, str);
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
            this.manager.checkServerTrusted(x509CertificateArr, str, referenceCountedOpenSslEngine);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class OpenSslClientCertificateCallback implements CertificateCallback {
        private final OpenSslEngineMap engineMap;
        private final OpenSslKeyMaterialManager keyManagerHolder;

        private static String clientKeyType(byte b) {
            if (b == 1) {
                return "RSA";
            }
            if (b == 3) {
                return "DH_RSA";
            }
            switch (b) {
                case 64:
                    return "EC";
                case 65:
                    return "EC_RSA";
                case 66:
                    return "EC_EC";
                default:
                    return null;
            }
        }

        OpenSslClientCertificateCallback(OpenSslEngineMap openSslEngineMap, OpenSslKeyMaterialManager openSslKeyMaterialManager) {
            this.engineMap = openSslEngineMap;
            this.keyManagerHolder = openSslKeyMaterialManager;
        }

        @Override // io.grpc.netty.shaded.io.netty.internal.tcnative.CertificateCallback
        public void handle(long j, byte[] bArr, byte[][] bArr2) throws Exception {
            X500Principal[] x500PrincipalArr;
            ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine = this.engineMap.get(j);
            if (referenceCountedOpenSslEngine == null) {
                return;
            }
            try {
                Set<String> supportedClientKeyTypes = supportedClientKeyTypes(bArr);
                String[] strArr = (String[]) supportedClientKeyTypes.toArray(new String[0]);
                if (bArr2 == null) {
                    x500PrincipalArr = null;
                } else {
                    X500Principal[] x500PrincipalArr2 = new X500Principal[bArr2.length];
                    for (int i = 0; i < bArr2.length; i++) {
                        x500PrincipalArr2[i] = new X500Principal(bArr2[i]);
                    }
                    x500PrincipalArr = x500PrincipalArr2;
                }
                this.keyManagerHolder.setKeyMaterialClientSide(referenceCountedOpenSslEngine, strArr, x500PrincipalArr);
            } catch (Throwable th) {
                ReferenceCountedOpenSslClientContext.logger.debug("request of key failed", th);
                referenceCountedOpenSslEngine.initHandshakeException(th);
            }
        }

        private static Set<String> supportedClientKeyTypes(byte[] bArr) {
            if (bArr == null) {
                return ReferenceCountedOpenSslClientContext.SUPPORTED_KEY_TYPES;
            }
            HashSet hashSet = new HashSet(bArr.length);
            for (byte b : bArr) {
                String clientKeyType = clientKeyType(b);
                if (clientKeyType != null) {
                    hashSet.add(clientKeyType);
                }
            }
            return hashSet;
        }
    }
}
