package io.netty.handler.ssl;

import io.netty.handler.ssl.ReferenceCountedOpenSslContext;
import io.netty.internal.tcnative.CertificateCallback;
import io.netty.internal.tcnative.CertificateRequestedCallback;
import io.netty.internal.tcnative.SSLContext;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Set;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509KeyManager;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class ReferenceCountedOpenSslClientContext extends ReferenceCountedOpenSslContext {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) ReferenceCountedOpenSslClientContext.class);
    private final OpenSslSessionContext sessionContext;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.netty.handler.ssl.ReferenceCountedOpenSslContext
    public OpenSslKeyMaterialManager keyMaterialManager() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReferenceCountedOpenSslClientContext(X509Certificate[] x509CertificateArr, TrustManagerFactory trustManagerFactory, X509Certificate[] x509CertificateArr2, PrivateKey privateKey, String str, KeyManagerFactory keyManagerFactory, Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, ApplicationProtocolConfig applicationProtocolConfig, String[] strArr, long j, long j2, boolean z) throws SSLException {
        super(iterable, cipherSuiteFilter, applicationProtocolConfig, j, j2, 0, (Certificate[]) x509CertificateArr2, ClientAuth.NONE, strArr, false, z, true);
        try {
            this.sessionContext = newSessionContext(this, this.ctx, this.engineMap, x509CertificateArr, trustManagerFactory, x509CertificateArr2, privateKey, str, keyManagerFactory);
        } catch (Throwable th) {
            release();
            throw th;
        }
    }

    @Override // io.netty.handler.ssl.ReferenceCountedOpenSslContext, io.netty.handler.ssl.SslContext
    public OpenSslSessionContext sessionContext() {
        return this.sessionContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static OpenSslSessionContext newSessionContext(ReferenceCountedOpenSslContext referenceCountedOpenSslContext, long j, OpenSslEngineMap openSslEngineMap, X509Certificate[] x509CertificateArr, TrustManagerFactory trustManagerFactory, X509Certificate[] x509CertificateArr2, PrivateKey privateKey, String str, KeyManagerFactory keyManagerFactory) throws SSLException {
        if ((privateKey == null && x509CertificateArr2 != null) || (privateKey != null && x509CertificateArr2 == null)) {
            throw new IllegalArgumentException("Either both keyCertChain and key needs to be null or none of them");
        }
        try {
            if (OpenSsl.useKeyManagerFactory()) {
                if (keyManagerFactory == null && x509CertificateArr2 != null) {
                    keyManagerFactory = buildKeyManagerFactory(x509CertificateArr2, privateKey, str, keyManagerFactory);
                }
                if (keyManagerFactory != null) {
                    X509KeyManager chooseX509KeyManager = chooseX509KeyManager(keyManagerFactory.getKeyManagers());
                    SSLContext.setCertRequestedCallback(j, new OpenSslCertificateRequestedCallback(openSslEngineMap, useExtendedKeyManager(chooseX509KeyManager) ? new OpenSslExtendedKeyMaterialManager((X509ExtendedKeyManager) chooseX509KeyManager, str) : new OpenSslKeyMaterialManager(chooseX509KeyManager, str)));
                }
            } else {
                if (keyManagerFactory != null) {
                    throw new IllegalArgumentException("KeyManagerFactory not supported");
                }
                if (x509CertificateArr2 != null) {
                    setKeyMaterial(j, x509CertificateArr2, privateKey, str);
                }
            }
            SSLContext.setVerify(j, 0, 10);
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
                return new OpenSslClientSessionContext(referenceCountedOpenSslContext);
            } catch (Exception e) {
                throw new SSLException("unable to setup trustmanager", e);
            }
        } catch (Exception e2) {
            throw new SSLException("failed to set certificate and key", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public static final class OpenSslClientSessionContext extends OpenSslSessionContext {
        @Override // javax.net.ssl.SSLSessionContext
        public int getSessionCacheSize() {
            return 0;
        }

        @Override // javax.net.ssl.SSLSessionContext
        public int getSessionTimeout() {
            return 0;
        }

        @Override // io.netty.handler.ssl.OpenSslSessionContext
        public boolean isSessionCacheEnabled() {
            return false;
        }

        @Override // io.netty.handler.ssl.OpenSslSessionContext
        public void setSessionCacheEnabled(boolean z) {
        }

        OpenSslClientSessionContext(ReferenceCountedOpenSslContext referenceCountedOpenSslContext) {
            super(referenceCountedOpenSslContext);
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
            this.manager.checkServerTrusted(x509CertificateArr, str);
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
            this.manager.checkServerTrusted(x509CertificateArr, str, referenceCountedOpenSslEngine);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class OpenSslCertificateRequestedCallback implements CertificateRequestedCallback {
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

        OpenSslCertificateRequestedCallback(OpenSslEngineMap openSslEngineMap, OpenSslKeyMaterialManager openSslKeyMaterialManager) {
            this.engineMap = openSslEngineMap;
            this.keyManagerHolder = openSslKeyMaterialManager;
        }

        public CertificateRequestedCallback.KeyMaterial requested(long j, byte[] bArr, byte[][] bArr2) {
            X500Principal[] x500PrincipalArr;
            ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine = this.engineMap.get(j);
            try {
                Set<String> supportedClientKeyTypes = supportedClientKeyTypes(bArr);
                String[] strArr = (String[]) supportedClientKeyTypes.toArray(new String[supportedClientKeyTypes.size()]);
                if (bArr2 == null) {
                    x500PrincipalArr = null;
                } else {
                    x500PrincipalArr = new X500Principal[bArr2.length];
                    for (int i = 0; i < bArr2.length; i++) {
                        x500PrincipalArr[i] = new X500Principal(bArr2[i]);
                    }
                }
                return this.keyManagerHolder.keyMaterial(referenceCountedOpenSslEngine, strArr, x500PrincipalArr);
            } catch (Throwable th) {
                ReferenceCountedOpenSslClientContext.logger.debug("request of key failed", th);
                SSLHandshakeException sSLHandshakeException = new SSLHandshakeException("General OpenSslEngine problem");
                sSLHandshakeException.initCause(th);
                referenceCountedOpenSslEngine.handshakeException = sSLHandshakeException;
                return null;
            }
        }

        private static Set<String> supportedClientKeyTypes(byte[] bArr) {
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

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes8.dex */
    private static final class OpenSslClientCertificateCallback implements CertificateCallback {
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
                return ReferenceCountedOpenSslClientContext.access$100();
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
