package io.netty.handler.ssl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.handler.ssl.ApplicationProtocolConfig;
import io.netty.internal.tcnative.CertificateVerifier;
import io.netty.internal.tcnative.SSL;
import io.netty.internal.tcnative.SSLContext;
import io.netty.internal.tcnative.SSLPrivateKeyMethod;
import io.netty.util.AbstractReferenceCounted;
import io.netty.util.ReferenceCounted;
import io.netty.util.ResourceLeakDetector;
import io.netty.util.ResourceLeakDetectorFactory;
import io.netty.util.ResourceLeakTracker;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.security.AccessController;
import java.security.PrivateKey;
import java.security.PrivilegedAction;
import java.security.SignatureException;
import java.security.cert.CertPathValidatorException;
import java.security.cert.Certificate;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CertificateRevokedException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509KeyManager;
import javax.net.ssl.X509TrustManager;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public abstract class ReferenceCountedOpenSslContext extends SslContext implements ReferenceCounted {
    private static final Integer DH_KEY_LENGTH;
    protected static final int VERIFY_DEPTH = 10;
    private final OpenSslApplicationProtocolNegotiator apn;
    private volatile int bioNonApplicationBufferSize;
    final ClientAuth clientAuth;
    protected long ctx;
    final ReadWriteLock ctxLock;
    final boolean enableOcsp;
    final OpenSslEngineMap engineMap;
    final Certificate[] keyCertChain;
    private final ResourceLeakTracker<ReferenceCountedOpenSslContext> leak;
    private final int mode;
    final String[] protocols;
    private final AbstractReferenceCounted refCnt;
    private final long sessionCacheSize;
    private final long sessionTimeout;
    private final List<String> unmodifiableCiphers;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) ReferenceCountedOpenSslContext.class);
    private static final int DEFAULT_BIO_NON_APPLICATION_BUFFER_SIZE = ((Integer) AccessController.doPrivileged(new PrivilegedAction<Integer>() { // from class: io.netty.handler.ssl.ReferenceCountedOpenSslContext.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.security.PrivilegedAction
        public Integer run() {
            return Integer.valueOf(Math.max(1, SystemPropertyUtil.getInt("io.netty.handler.ssl.openssl.bioNonApplicationBufferSize", 2048)));
        }
    })).intValue();
    private static final ResourceLeakDetector<ReferenceCountedOpenSslContext> leakDetector = ResourceLeakDetectorFactory.instance().newResourceLeakDetector(ReferenceCountedOpenSslContext.class);
    static final OpenSslApplicationProtocolNegotiator NONE_PROTOCOL_NEGOTIATOR = new OpenSslApplicationProtocolNegotiator() { // from class: io.netty.handler.ssl.ReferenceCountedOpenSslContext.3
        @Override // io.netty.handler.ssl.OpenSslApplicationProtocolNegotiator
        public ApplicationProtocolConfig.Protocol protocol() {
            return ApplicationProtocolConfig.Protocol.NONE;
        }

        @Override // io.netty.handler.ssl.ApplicationProtocolNegotiator
        public List<String> protocols() {
            return Collections.emptyList();
        }

        @Override // io.netty.handler.ssl.OpenSslApplicationProtocolNegotiator
        public ApplicationProtocolConfig.SelectorFailureBehavior selectorFailureBehavior() {
            return ApplicationProtocolConfig.SelectorFailureBehavior.CHOOSE_MY_LAST_PROTOCOL;
        }

        @Override // io.netty.handler.ssl.OpenSslApplicationProtocolNegotiator
        public ApplicationProtocolConfig.SelectedListenerFailureBehavior selectedListenerFailureBehavior() {
            return ApplicationProtocolConfig.SelectedListenerFailureBehavior.ACCEPT;
        }
    };

    @Deprecated
    public boolean getRejectRemoteInitiatedRenegotiation() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract OpenSslKeyMaterialManager keyMaterialManager();

    @Override // io.netty.handler.ssl.SslContext
    public abstract OpenSslSessionContext sessionContext();

    static {
        Integer num = null;
        try {
            String str = (String) AccessController.doPrivileged(new PrivilegedAction<String>() { // from class: io.netty.handler.ssl.ReferenceCountedOpenSslContext.4
                @Override // java.security.PrivilegedAction
                public String run() {
                    return SystemPropertyUtil.get("jdk.tls.ephemeralDHKeySize");
                }
            });
            if (str != null) {
                try {
                    num = Integer.valueOf(str);
                } catch (NumberFormatException unused) {
                    logger.debug("ReferenceCountedOpenSslContext supports -Djdk.tls.ephemeralDHKeySize={int}, but got: " + str);
                }
            }
        } catch (Throwable unused2) {
        }
        DH_KEY_LENGTH = num;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReferenceCountedOpenSslContext(Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, ApplicationProtocolConfig applicationProtocolConfig, long j, long j2, int i, Certificate[] certificateArr, ClientAuth clientAuth, String[] strArr, boolean z, boolean z2, boolean z3) throws SSLException {
        this(iterable, cipherSuiteFilter, toNegotiator(applicationProtocolConfig), j, j2, i, certificateArr, clientAuth, strArr, z, z2, z3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReferenceCountedOpenSslContext(Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, OpenSslApplicationProtocolNegotiator openSslApplicationProtocolNegotiator, long j, long j2, int i, Certificate[] certificateArr, ClientAuth clientAuth, String[] strArr, boolean z, boolean z2, boolean z3) throws SSLException {
        super(z);
        this.refCnt = new AbstractReferenceCounted() { // from class: io.netty.handler.ssl.ReferenceCountedOpenSslContext.2
            static final /* synthetic */ boolean $assertionsDisabled = false;

            @Override // io.netty.util.ReferenceCounted
            public ReferenceCounted touch(Object obj) {
                if (ReferenceCountedOpenSslContext.this.leak != null) {
                    ReferenceCountedOpenSslContext.this.leak.record(obj);
                }
                return ReferenceCountedOpenSslContext.this;
            }

            @Override // io.netty.util.AbstractReferenceCounted
            protected void deallocate() {
                ReferenceCountedOpenSslContext.this.destroy();
                if (ReferenceCountedOpenSslContext.this.leak != null) {
                    ReferenceCountedOpenSslContext.this.leak.close(ReferenceCountedOpenSslContext.this);
                }
            }
        };
        this.engineMap = new DefaultOpenSslEngineMap();
        this.ctxLock = new ReentrantReadWriteLock();
        this.bioNonApplicationBufferSize = DEFAULT_BIO_NON_APPLICATION_BUFFER_SIZE;
        OpenSsl.ensureAvailability();
        if (z2 && !OpenSsl.isOcspSupported()) {
            throw new IllegalStateException("OCSP is not supported.");
        }
        if (i != 1 && i != 0) {
            throw new IllegalArgumentException("mode most be either SSL.SSL_MODE_SERVER or SSL.SSL_MODE_CLIENT");
        }
        this.leak = z3 ? leakDetector.track(this) : null;
        this.mode = i;
        this.clientAuth = isServer() ? (ClientAuth) ObjectUtil.checkNotNull(clientAuth, "clientAuth") : ClientAuth.NONE;
        this.protocols = strArr;
        this.enableOcsp = z2;
        this.keyCertChain = certificateArr != null ? (Certificate[]) certificateArr.clone() : null;
        this.unmodifiableCiphers = Arrays.asList(((CipherSuiteFilter) ObjectUtil.checkNotNull(cipherSuiteFilter, "cipherFilter")).filterCipherSuites(iterable, OpenSsl.DEFAULT_CIPHERS, OpenSsl.availableJavaCipherSuites()));
        this.apn = (OpenSslApplicationProtocolNegotiator) ObjectUtil.checkNotNull(openSslApplicationProtocolNegotiator, "apn");
        try {
            try {
                long make = SSLContext.make(31, i);
                this.ctx = make;
                SSLContext.setOptions(make, SSLContext.getOptions(make) | SSL.SSL_OP_NO_SSLv2 | SSL.SSL_OP_NO_SSLv3 | SSL.SSL_OP_CIPHER_SERVER_PREFERENCE | SSL.SSL_OP_NO_COMPRESSION | SSL.SSL_OP_NO_TICKET);
                SSLContext.setMode(this.ctx, SSLContext.getMode(this.ctx) | SSL.SSL_MODE_ACCEPT_MOVING_WRITE_BUFFER);
                if (DH_KEY_LENGTH != null) {
                    SSLContext.setTmpDHLength(this.ctx, DH_KEY_LENGTH.intValue());
                }
                try {
                    SSLContext.setCipherSuite(this.ctx, CipherSuiteConverter.toOpenSsl(this.unmodifiableCiphers));
                    List<String> protocols = openSslApplicationProtocolNegotiator.protocols();
                    if (!protocols.isEmpty()) {
                        String[] strArr2 = (String[]) protocols.toArray(new String[protocols.size()]);
                        int opensslSelectorFailureBehavior = opensslSelectorFailureBehavior(openSslApplicationProtocolNegotiator.selectorFailureBehavior());
                        int i2 = C73335.f8552xc16482e4[openSslApplicationProtocolNegotiator.protocol().ordinal()];
                        if (i2 == 1) {
                            SSLContext.setNpnProtos(this.ctx, strArr2, opensslSelectorFailureBehavior);
                        } else if (i2 == 2) {
                            SSLContext.setAlpnProtos(this.ctx, strArr2, opensslSelectorFailureBehavior);
                        } else if (i2 == 3) {
                            SSLContext.setNpnProtos(this.ctx, strArr2, opensslSelectorFailureBehavior);
                            SSLContext.setAlpnProtos(this.ctx, strArr2, opensslSelectorFailureBehavior);
                        } else {
                            throw new Error();
                        }
                    }
                    if (j > 0) {
                        this.sessionCacheSize = j;
                        SSLContext.setSessionCacheSize(this.ctx, j);
                    } else {
                        long sessionCacheSize = SSLContext.setSessionCacheSize(this.ctx, 20480L);
                        this.sessionCacheSize = sessionCacheSize;
                        SSLContext.setSessionCacheSize(this.ctx, sessionCacheSize);
                    }
                    if (j2 > 0) {
                        this.sessionTimeout = j2;
                        SSLContext.setSessionCacheTimeout(this.ctx, j2);
                    } else {
                        long sessionCacheTimeout = SSLContext.setSessionCacheTimeout(this.ctx, 300L);
                        this.sessionTimeout = sessionCacheTimeout;
                        SSLContext.setSessionCacheTimeout(this.ctx, sessionCacheTimeout);
                    }
                    if (z2) {
                        SSLContext.enableOcsp(this.ctx, isClient());
                    }
                } catch (SSLException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new SSLException("failed to set cipher suite: " + this.unmodifiableCiphers, e2);
                }
            } catch (Exception e3) {
                throw new SSLException("failed to create an SSL_CTX", e3);
            }
        } catch (Throwable th) {
            release();
            throw th;
        }
    }

    private static int opensslSelectorFailureBehavior(ApplicationProtocolConfig.SelectorFailureBehavior selectorFailureBehavior) {
        int i = C73335.f8554xb32e3251[selectorFailureBehavior.ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 1;
        }
        throw new Error();
    }

    @Override // io.netty.handler.ssl.SslContext
    public final List<String> cipherSuites() {
        return this.unmodifiableCiphers;
    }

    @Override // io.netty.handler.ssl.SslContext
    public final long sessionCacheSize() {
        return this.sessionCacheSize;
    }

    @Override // io.netty.handler.ssl.SslContext
    public final long sessionTimeout() {
        return this.sessionTimeout;
    }

    @Override // io.netty.handler.ssl.SslContext
    public ApplicationProtocolNegotiator applicationProtocolNegotiator() {
        return this.apn;
    }

    @Override // io.netty.handler.ssl.SslContext
    public final boolean isClient() {
        return this.mode == 0;
    }

    @Override // io.netty.handler.ssl.SslContext
    public final SSLEngine newEngine(ByteBufAllocator byteBufAllocator, String str, int i) {
        return newEngine0(byteBufAllocator, str, i, true);
    }

    @Override // io.netty.handler.ssl.SslContext
    protected final SslHandler newHandler(ByteBufAllocator byteBufAllocator, boolean z) {
        return new SslHandler(newEngine0(byteBufAllocator, null, -1, false), z);
    }

    @Override // io.netty.handler.ssl.SslContext
    protected final SslHandler newHandler(ByteBufAllocator byteBufAllocator, String str, int i, boolean z) {
        return new SslHandler(newEngine0(byteBufAllocator, str, i, false), z);
    }

    SSLEngine newEngine0(ByteBufAllocator byteBufAllocator, String str, int i, boolean z) {
        return new ReferenceCountedOpenSslEngine(this, byteBufAllocator, str, i, z, true);
    }

    @Override // io.netty.handler.ssl.SslContext
    public final SSLEngine newEngine(ByteBufAllocator byteBufAllocator) {
        return newEngine(byteBufAllocator, null, -1);
    }

    @Deprecated
    public final long context() {
        Lock readLock = this.ctxLock.readLock();
        readLock.lock();
        try {
            return this.ctx;
        } finally {
            readLock.unlock();
        }
    }

    @Deprecated
    public final OpenSslSessionStats stats() {
        return sessionContext().stats();
    }

    @Deprecated
    public void setRejectRemoteInitiatedRenegotiation(boolean z) {
        if (!z) {
            throw new UnsupportedOperationException("Renegotiation is not supported");
        }
    }

    public void setBioNonApplicationBufferSize(int i) {
        this.bioNonApplicationBufferSize = ObjectUtil.checkPositiveOrZero(i, "bioNonApplicationBufferSize");
    }

    public int getBioNonApplicationBufferSize() {
        return this.bioNonApplicationBufferSize;
    }

    @Deprecated
    public final void setTicketKeys(byte[] bArr) {
        sessionContext().setTicketKeys(bArr);
    }

    @Deprecated
    public final long sslCtxPointer() {
        Lock readLock = this.ctxLock.readLock();
        readLock.lock();
        try {
            return this.ctx;
        } finally {
            readLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void destroy() {
        Lock writeLock = this.ctxLock.writeLock();
        writeLock.lock();
        try {
            if (this.ctx != 0) {
                if (this.enableOcsp) {
                    SSLContext.disableOcsp(this.ctx);
                }
                SSLContext.free(this.ctx);
                this.ctx = 0L;
            }
        } finally {
            writeLock.unlock();
        }
    }

    protected static X509Certificate[] certificates(byte[][] bArr) {
        int length = bArr.length;
        X509Certificate[] x509CertificateArr = new X509Certificate[length];
        for (int i = 0; i < length; i++) {
            x509CertificateArr[i] = new OpenSslX509Certificate(bArr[i]);
        }
        return x509CertificateArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static X509TrustManager chooseTrustManager(TrustManager[] trustManagerArr) {
        for (TrustManager trustManager : trustManagerArr) {
            if (trustManager instanceof X509TrustManager) {
                return (X509TrustManager) trustManager;
            }
        }
        throw new IllegalStateException("no X509TrustManager found");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static X509KeyManager chooseX509KeyManager(KeyManager[] keyManagerArr) {
        for (KeyManager keyManager : keyManagerArr) {
            if (keyManager instanceof X509KeyManager) {
                return (X509KeyManager) keyManager;
            }
        }
        throw new IllegalStateException("no X509KeyManager found");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static OpenSslApplicationProtocolNegotiator toNegotiator(ApplicationProtocolConfig applicationProtocolConfig) {
        if (applicationProtocolConfig == null) {
            return NONE_PROTOCOL_NEGOTIATOR;
        }
        int i = C73335.f8552xc16482e4[applicationProtocolConfig.protocol().ordinal()];
        if (i != 1 && i != 2 && i != 3) {
            if (i == 4) {
                return NONE_PROTOCOL_NEGOTIATOR;
            }
            throw new Error();
        }
        int i2 = C73335.f8553xcbdfafc1[applicationProtocolConfig.selectedListenerFailureBehavior().ordinal()];
        if (i2 == 1 || i2 == 2) {
            int i3 = C73335.f8554xb32e3251[applicationProtocolConfig.selectorFailureBehavior().ordinal()];
            if (i3 == 1 || i3 == 2) {
                return new OpenSslDefaultApplicationProtocolNegotiator(applicationProtocolConfig);
            }
            throw new UnsupportedOperationException("OpenSSL provider does not support " + applicationProtocolConfig.selectorFailureBehavior() + " behavior");
        }
        throw new UnsupportedOperationException("OpenSSL provider does not support " + applicationProtocolConfig.selectedListenerFailureBehavior() + " behavior");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: io.netty.handler.ssl.ReferenceCountedOpenSslContext$5 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C73335 {

        /* renamed from: $SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$Protocol */
        static final /* synthetic */ int[] f8552xc16482e4;

        /* renamed from: $SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$SelectedListenerFailureBehavior */
        static final /* synthetic */ int[] f8553xcbdfafc1;

        /* renamed from: $SwitchMap$io$netty$handler$ssl$ApplicationProtocolConfig$SelectorFailureBehavior */
        static final /* synthetic */ int[] f8554xb32e3251;

        static {
            int[] iArr = new int[ApplicationProtocolConfig.SelectedListenerFailureBehavior.values().length];
            f8553xcbdfafc1 = iArr;
            try {
                iArr[ApplicationProtocolConfig.SelectedListenerFailureBehavior.CHOOSE_MY_LAST_PROTOCOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8553xcbdfafc1[ApplicationProtocolConfig.SelectedListenerFailureBehavior.ACCEPT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[ApplicationProtocolConfig.SelectorFailureBehavior.values().length];
            f8554xb32e3251 = iArr2;
            try {
                iArr2[ApplicationProtocolConfig.SelectorFailureBehavior.NO_ADVERTISE.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8554xb32e3251[ApplicationProtocolConfig.SelectorFailureBehavior.CHOOSE_MY_LAST_PROTOCOL.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr3 = new int[ApplicationProtocolConfig.Protocol.values().length];
            f8552xc16482e4 = iArr3;
            try {
                iArr3[ApplicationProtocolConfig.Protocol.NPN.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f8552xc16482e4[ApplicationProtocolConfig.Protocol.ALPN.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f8552xc16482e4[ApplicationProtocolConfig.Protocol.NPN_AND_ALPN.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f8552xc16482e4[ApplicationProtocolConfig.Protocol.NONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean useExtendedTrustManager(X509TrustManager x509TrustManager) {
        return PlatformDependent.javaVersion() >= 7 && (x509TrustManager instanceof X509ExtendedTrustManager);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean useExtendedKeyManager(X509KeyManager x509KeyManager) {
        return PlatformDependent.javaVersion() >= 7 && (x509KeyManager instanceof X509ExtendedKeyManager);
    }

    @Override // io.netty.util.ReferenceCounted
    public final int refCnt() {
        return this.refCnt.refCnt();
    }

    @Override // io.netty.util.ReferenceCounted
    public final ReferenceCounted retain() {
        this.refCnt.retain();
        return this;
    }

    @Override // io.netty.util.ReferenceCounted
    public final ReferenceCounted retain(int i) {
        this.refCnt.retain(i);
        return this;
    }

    @Override // io.netty.util.ReferenceCounted
    public final ReferenceCounted touch() {
        this.refCnt.touch();
        return this;
    }

    @Override // io.netty.util.ReferenceCounted
    public final ReferenceCounted touch(Object obj) {
        this.refCnt.touch(obj);
        return this;
    }

    @Override // io.netty.util.ReferenceCounted
    public final boolean release() {
        return this.refCnt.release();
    }

    @Override // io.netty.util.ReferenceCounted
    public final boolean release(int i) {
        return this.refCnt.release(i);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    static abstract class AbstractCertificateVerifier extends CertificateVerifier {
        private final OpenSslEngineMap engineMap;

        abstract void verify(ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine, X509Certificate[] x509CertificateArr, String str) throws Exception;

        /* JADX INFO: Access modifiers changed from: package-private */
        public AbstractCertificateVerifier(OpenSslEngineMap openSslEngineMap) {
            this.engineMap = openSslEngineMap;
        }

        public final int verify(long j, byte[][] bArr, String str) {
            X509Certificate[] certificates = ReferenceCountedOpenSslContext.certificates(bArr);
            ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine = this.engineMap.get(j);
            try {
                verify(referenceCountedOpenSslEngine, certificates, str);
                return CertificateVerifier.X509_V_OK;
            } catch (Throwable th) {
                ReferenceCountedOpenSslContext.logger.debug("verification of certificate failed", (Throwable) th);
                SSLHandshakeException sSLHandshakeException = new SSLHandshakeException("General OpenSslEngine problem");
                sSLHandshakeException.initCause(th);
                referenceCountedOpenSslEngine.handshakeException = sSLHandshakeException;
                if (th instanceof OpenSslCertificateException) {
                    return th.errorCode();
                }
                if (th instanceof CertificateExpiredException) {
                    return CertificateVerifier.X509_V_ERR_CERT_HAS_EXPIRED;
                }
                if (th instanceof CertificateNotYetValidException) {
                    return CertificateVerifier.X509_V_ERR_CERT_NOT_YET_VALID;
                }
                if (PlatformDependent.javaVersion() >= 7) {
                    if (th instanceof CertificateRevokedException) {
                        return CertificateVerifier.X509_V_ERR_CERT_REVOKED;
                    }
                    for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
                        if (cause instanceof CertPathValidatorException) {
                            CertPathValidatorException.Reason reason = ((CertPathValidatorException) cause).getReason();
                            if (reason == CertPathValidatorException.BasicReason.EXPIRED) {
                                return CertificateVerifier.X509_V_ERR_CERT_HAS_EXPIRED;
                            }
                            if (reason == CertPathValidatorException.BasicReason.NOT_YET_VALID) {
                                return CertificateVerifier.X509_V_ERR_CERT_NOT_YET_VALID;
                            }
                            if (reason == CertPathValidatorException.BasicReason.REVOKED) {
                                return CertificateVerifier.X509_V_ERR_CERT_REVOKED;
                            }
                        }
                    }
                }
                return CertificateVerifier.X509_V_ERR_UNSPECIFIED;
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    private static final class DefaultOpenSslEngineMap implements OpenSslEngineMap {
        private final Map<Long, ReferenceCountedOpenSslEngine> engines;

        private DefaultOpenSslEngineMap() {
            this.engines = PlatformDependent.newConcurrentHashMap();
        }

        @Override // io.netty.handler.ssl.OpenSslEngineMap
        public ReferenceCountedOpenSslEngine remove(long j) {
            return this.engines.remove(Long.valueOf(j));
        }

        @Override // io.netty.handler.ssl.OpenSslEngineMap
        public void add(ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine) {
            this.engines.put(Long.valueOf(referenceCountedOpenSslEngine.sslPointer()), referenceCountedOpenSslEngine);
        }

        @Override // io.netty.handler.ssl.OpenSslEngineMap
        public ReferenceCountedOpenSslEngine get(long j) {
            return this.engines.get(Long.valueOf(j));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setKeyMaterial(long j, X509Certificate[] x509CertificateArr, PrivateKey privateKey, String str) throws SSLException {
        long j2;
        long j3;
        long bio;
        long j4 = 0;
        PemEncoded pemEncoded = null;
        try {
            try {
                pemEncoded = PemX509Certificate.toPEM(ByteBufAllocator.DEFAULT, true, x509CertificateArr);
                j3 = toBIO(ByteBufAllocator.DEFAULT, pemEncoded.retain());
                try {
                    bio = toBIO(ByteBufAllocator.DEFAULT, pemEncoded.retain());
                    if (privateKey != null) {
                        try {
                            j4 = toBIO(privateKey);
                        } catch (SSLException e) {
                            throw e;
                        } catch (Exception e2) {
                            e = e2;
                            throw new SSLException("failed to set certificate and key", e);
                        } catch (Throwable th) {
                            th = th;
                            j2 = bio;
                            freeBio(j4);
                            freeBio(j3);
                            freeBio(j2);
                            if (pemEncoded != null) {
                                pemEncoded.release();
                            }
                            throw th;
                        }
                    }
                } catch (SSLException e3) {
                    throw e3;
                } catch (Exception e4) {
                    e = e4;
                } catch (Throwable th2) {
                    th = th2;
                    j2 = 0;
                }
            } catch (SSLException e5) {
                throw e5;
            } catch (Exception e6) {
                e = e6;
            } catch (Throwable th3) {
                th = th3;
                j2 = 0;
                j3 = 0;
            }
            try {
                SSLContext.setCertificateBio(j, j3, j4, str == null ? "" : str);
                SSLContext.setCertificateChainBio(j, bio, true);
                freeBio(j4);
                freeBio(j3);
                freeBio(bio);
                if (pemEncoded != null) {
                    pemEncoded.release();
                }
            } catch (SSLException e7) {
            } catch (Exception e8) {
                e = e8;
                throw new SSLException("failed to set certificate and key", e);
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void freeBio(long j) {
        if (j != 0) {
            SSL.freeBIO(j);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long toBIO(PrivateKey privateKey) throws Exception {
        if (privateKey == null) {
            return 0L;
        }
        ByteBufAllocator byteBufAllocator = ByteBufAllocator.DEFAULT;
        PemEncoded pem = PemPrivateKey.toPEM(byteBufAllocator, true, privateKey);
        try {
            return toBIO(byteBufAllocator, pem.retain());
        } finally {
            pem.release();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long toBIO(X509Certificate... x509CertificateArr) throws Exception {
        if (x509CertificateArr == null) {
            return 0L;
        }
        if (x509CertificateArr.length == 0) {
            throw new IllegalArgumentException("certChain can't be empty");
        }
        ByteBufAllocator byteBufAllocator = ByteBufAllocator.DEFAULT;
        PemEncoded pem = PemX509Certificate.toPEM(byteBufAllocator, true, x509CertificateArr);
        try {
            return toBIO(byteBufAllocator, pem.retain());
        } finally {
            pem.release();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long toBIO(ByteBufAllocator byteBufAllocator, PemEncoded pemEncoded) throws Exception {
        try {
            ByteBuf content = pemEncoded.content();
            if (content.isDirect()) {
                return newBIO(content.retainedSlice());
            }
            ByteBuf directBuffer = byteBufAllocator.directBuffer(content.readableBytes());
            try {
                directBuffer.writeBytes(content, content.readerIndex(), content.readableBytes());
                long newBIO = newBIO(directBuffer.retainedSlice());
                try {
                    if (pemEncoded.isSensitive()) {
                        SslUtils.zeroout(directBuffer);
                    }
                    return newBIO;
                } finally {
                }
            } catch (Throwable th) {
                try {
                    if (pemEncoded.isSensitive()) {
                        SslUtils.zeroout(directBuffer);
                    }
                    throw th;
                } finally {
                }
            }
        } finally {
            pemEncoded.release();
        }
    }

    private static long newBIO(ByteBuf byteBuf) throws Exception {
        try {
            long newMemBIO = SSL.newMemBIO();
            int readableBytes = byteBuf.readableBytes();
            if (SSL.bioWrite(newMemBIO, OpenSsl.memoryAddress(byteBuf) + byteBuf.readerIndex(), readableBytes) == readableBytes) {
                return newMemBIO;
            }
            SSL.freeBIO(newMemBIO);
            throw new IllegalStateException("Could not write data to memory BIO");
        } finally {
            byteBuf.release();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes8.dex */
    private static final class PrivateKeyMethod implements SSLPrivateKeyMethod {
        private final OpenSslEngineMap engineMap;
        private final OpenSslPrivateKeyMethod keyMethod;

        PrivateKeyMethod(OpenSslEngineMap openSslEngineMap, OpenSslPrivateKeyMethod openSslPrivateKeyMethod) {
            this.engineMap = openSslEngineMap;
            this.keyMethod = openSslPrivateKeyMethod;
        }

        private ReferenceCountedOpenSslEngine retrieveEngine(long j) throws SSLException {
            ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine = this.engineMap.get(j);
            if (referenceCountedOpenSslEngine != null) {
                return referenceCountedOpenSslEngine;
            }
            throw new SSLException("Could not find a " + StringUtil.simpleClassName((Class<?>) ReferenceCountedOpenSslEngine.class) + " for sslPointer " + j);
        }

        public byte[] sign(long j, int i, byte[] bArr) throws Exception {
            ReferenceCountedOpenSslEngine retrieveEngine = retrieveEngine(j);
            try {
                return verifyResult(this.keyMethod.sign(retrieveEngine, i, bArr));
            } catch (Exception e) {
                retrieveEngine.initHandshakeException(e);
                throw e;
            }
        }

        public byte[] decrypt(long j, byte[] bArr) throws Exception {
            ReferenceCountedOpenSslEngine retrieveEngine = retrieveEngine(j);
            try {
                return verifyResult(this.keyMethod.decrypt(retrieveEngine, bArr));
            } catch (Exception e) {
                retrieveEngine.initHandshakeException(e);
                throw e;
            }
        }

        private static byte[] verifyResult(byte[] bArr) throws SignatureException {
            if (bArr != null) {
                return bArr;
            }
            throw new SignatureException();
        }
    }
}
