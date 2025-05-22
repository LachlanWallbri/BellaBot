package org.conscrypt;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.KeyManagementException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.cert.X509Certificate;
import java.util.Properties;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLContextSpi;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.conscrypt.io.IoUtils;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public final class Conscrypt {
    private static final Version VERSION;

    public static int maxEncryptedPacketLength() {
        return 16709;
    }

    private Conscrypt() {
    }

    public static boolean isAvailable() {
        try {
            checkAvailability();
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public static class Version {
        private final int major;
        private final int minor;
        private final int patch;

        private Version(int i, int i2, int i3) {
            this.major = i;
            this.minor = i2;
            this.patch = i3;
        }

        public int major() {
            return this.major;
        }

        public int minor() {
            return this.minor;
        }

        public int patch() {
            return this.patch;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0050 A[ADDED_TO_REGION] */
    static {
        InputStream inputStream;
        int i;
        int i2;
        int i3;
        Properties properties;
        int i4 = -1;
        try {
            inputStream = Conscrypt.class.getResourceAsStream("conscrypt.properties");
            if (inputStream != null) {
                try {
                    try {
                        properties = new Properties();
                        properties.load(inputStream);
                        i = Integer.parseInt(properties.getProperty("org.conscrypt.version.major", "-1"));
                        try {
                            i2 = Integer.parseInt(properties.getProperty("org.conscrypt.version.minor", "-1"));
                        } catch (IOException unused) {
                            i2 = -1;
                        }
                    } catch (Throwable th) {
                        th = th;
                        IoUtils.closeQuietly(inputStream);
                        throw th;
                    }
                } catch (IOException unused2) {
                    i = -1;
                    i2 = -1;
                    IoUtils.closeQuietly(inputStream);
                    i3 = -1;
                    i4 = i;
                    if (i4 < 0) {
                    }
                    VERSION = null;
                }
                try {
                    i3 = Integer.parseInt(properties.getProperty("org.conscrypt.version.patch", "-1"));
                    i4 = i;
                } catch (IOException unused3) {
                    IoUtils.closeQuietly(inputStream);
                    i3 = -1;
                    i4 = i;
                    if (i4 < 0) {
                    }
                    VERSION = null;
                }
            } else {
                i3 = -1;
                i2 = -1;
            }
            IoUtils.closeQuietly(inputStream);
        } catch (IOException unused4) {
            inputStream = null;
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
        }
        if (i4 < 0 && i2 >= 0 && i3 >= 0) {
            VERSION = new Version(i4, i2, i3);
        } else {
            VERSION = null;
        }
    }

    public static Version version() {
        return VERSION;
    }

    public static void checkAvailability() {
        NativeCrypto.checkAvailability();
    }

    public static boolean isConscrypt(Provider provider) {
        return provider instanceof OpenSSLProvider;
    }

    public static Provider newProvider() {
        checkAvailability();
        return new OpenSSLProvider();
    }

    @Deprecated
    public static Provider newProvider(String str) {
        checkAvailability();
        return newProviderBuilder().setName(str).build();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public static class ProviderBuilder {
        private String defaultTlsProtocol;
        private String name;
        private boolean provideTrustManager;

        private ProviderBuilder() {
            this.name = Platform.getDefaultProviderName();
            this.provideTrustManager = Platform.provideTrustManagerByDefault();
            this.defaultTlsProtocol = "TLSv1.3";
        }

        public ProviderBuilder setName(String str) {
            this.name = str;
            return this;
        }

        @Deprecated
        public ProviderBuilder provideTrustManager() {
            return provideTrustManager(true);
        }

        public ProviderBuilder provideTrustManager(boolean z) {
            this.provideTrustManager = z;
            return this;
        }

        public ProviderBuilder defaultTlsProtocol(String str) {
            this.defaultTlsProtocol = str;
            return this;
        }

        public Provider build() {
            return new OpenSSLProvider(this.name, this.provideTrustManager, this.defaultTlsProtocol);
        }
    }

    public static ProviderBuilder newProviderBuilder() {
        return new ProviderBuilder();
    }

    public static X509TrustManager getDefaultX509TrustManager() throws KeyManagementException {
        checkAvailability();
        return SSLParametersImpl.getDefaultX509TrustManager();
    }

    public static boolean isConscrypt(SSLContext sSLContext) {
        return sSLContext.getProvider() instanceof OpenSSLProvider;
    }

    public static SSLContextSpi newPreferredSSLContextSpi() {
        checkAvailability();
        return OpenSSLContextImpl.getPreferred();
    }

    public static void setClientSessionCache(SSLContext sSLContext, SSLClientSessionCache sSLClientSessionCache) {
        SSLSessionContext clientSessionContext = sSLContext.getClientSessionContext();
        if (!(clientSessionContext instanceof ClientSessionContext)) {
            throw new IllegalArgumentException("Not a conscrypt client context: " + clientSessionContext.getClass().getName());
        }
        ((ClientSessionContext) clientSessionContext).setPersistentCache(sSLClientSessionCache);
    }

    public static void setServerSessionCache(SSLContext sSLContext, SSLServerSessionCache sSLServerSessionCache) {
        SSLSessionContext serverSessionContext = sSLContext.getServerSessionContext();
        if (!(serverSessionContext instanceof ServerSessionContext)) {
            throw new IllegalArgumentException("Not a conscrypt client context: " + serverSessionContext.getClass().getName());
        }
        ((ServerSessionContext) serverSessionContext).setPersistentCache(sSLServerSessionCache);
    }

    public static boolean isConscrypt(SSLSocketFactory sSLSocketFactory) {
        return sSLSocketFactory instanceof OpenSSLSocketFactoryImpl;
    }

    private static OpenSSLSocketFactoryImpl toConscrypt(SSLSocketFactory sSLSocketFactory) {
        if (!isConscrypt(sSLSocketFactory)) {
            throw new IllegalArgumentException("Not a conscrypt socket factory: " + sSLSocketFactory.getClass().getName());
        }
        return (OpenSSLSocketFactoryImpl) sSLSocketFactory;
    }

    public static void setUseEngineSocketByDefault(boolean z) {
        OpenSSLSocketFactoryImpl.setUseEngineSocketByDefault(z);
        OpenSSLServerSocketFactoryImpl.setUseEngineSocketByDefault(z);
    }

    public static void setUseEngineSocket(SSLSocketFactory sSLSocketFactory, boolean z) {
        toConscrypt(sSLSocketFactory).setUseEngineSocket(z);
    }

    public static boolean isConscrypt(SSLServerSocketFactory sSLServerSocketFactory) {
        return sSLServerSocketFactory instanceof OpenSSLServerSocketFactoryImpl;
    }

    private static OpenSSLServerSocketFactoryImpl toConscrypt(SSLServerSocketFactory sSLServerSocketFactory) {
        if (!isConscrypt(sSLServerSocketFactory)) {
            throw new IllegalArgumentException("Not a conscrypt server socket factory: " + sSLServerSocketFactory.getClass().getName());
        }
        return (OpenSSLServerSocketFactoryImpl) sSLServerSocketFactory;
    }

    public static void setUseEngineSocket(SSLServerSocketFactory sSLServerSocketFactory, boolean z) {
        toConscrypt(sSLServerSocketFactory).setUseEngineSocket(z);
    }

    public static boolean isConscrypt(SSLSocket sSLSocket) {
        return sSLSocket instanceof AbstractConscryptSocket;
    }

    private static AbstractConscryptSocket toConscrypt(SSLSocket sSLSocket) {
        if (!isConscrypt(sSLSocket)) {
            throw new IllegalArgumentException("Not a conscrypt socket: " + sSLSocket.getClass().getName());
        }
        return (AbstractConscryptSocket) sSLSocket;
    }

    public static void setHostname(SSLSocket sSLSocket, String str) {
        toConscrypt(sSLSocket).setHostname(str);
    }

    public static String getHostname(SSLSocket sSLSocket) {
        return toConscrypt(sSLSocket).getHostname();
    }

    public static String getHostnameOrIP(SSLSocket sSLSocket) {
        return toConscrypt(sSLSocket).getHostnameOrIP();
    }

    public static void setUseSessionTickets(SSLSocket sSLSocket, boolean z) {
        toConscrypt(sSLSocket).setUseSessionTickets(z);
    }

    public static void setChannelIdEnabled(SSLSocket sSLSocket, boolean z) {
        toConscrypt(sSLSocket).setChannelIdEnabled(z);
    }

    public static byte[] getChannelId(SSLSocket sSLSocket) throws SSLException {
        return toConscrypt(sSLSocket).getChannelId();
    }

    public static void setChannelIdPrivateKey(SSLSocket sSLSocket, PrivateKey privateKey) {
        toConscrypt(sSLSocket).setChannelIdPrivateKey(privateKey);
    }

    public static String getApplicationProtocol(SSLSocket sSLSocket) {
        return toConscrypt(sSLSocket).getApplicationProtocol();
    }

    public static void setApplicationProtocolSelector(SSLSocket sSLSocket, ApplicationProtocolSelector applicationProtocolSelector) {
        toConscrypt(sSLSocket).setApplicationProtocolSelector(applicationProtocolSelector);
    }

    public static void setApplicationProtocols(SSLSocket sSLSocket, String[] strArr) {
        toConscrypt(sSLSocket).setApplicationProtocols(strArr);
    }

    public static String[] getApplicationProtocols(SSLSocket sSLSocket) {
        return toConscrypt(sSLSocket).getApplicationProtocols();
    }

    public static byte[] getTlsUnique(SSLSocket sSLSocket) {
        return toConscrypt(sSLSocket).getTlsUnique();
    }

    public static byte[] exportKeyingMaterial(SSLSocket sSLSocket, String str, byte[] bArr, int i) throws SSLException {
        return toConscrypt(sSLSocket).exportKeyingMaterial(str, bArr, i);
    }

    public static boolean isConscrypt(SSLEngine sSLEngine) {
        return sSLEngine instanceof AbstractConscryptEngine;
    }

    private static AbstractConscryptEngine toConscrypt(SSLEngine sSLEngine) {
        if (!isConscrypt(sSLEngine)) {
            throw new IllegalArgumentException("Not a conscrypt engine: " + sSLEngine.getClass().getName());
        }
        return (AbstractConscryptEngine) sSLEngine;
    }

    public static void setBufferAllocator(SSLEngine sSLEngine, BufferAllocator bufferAllocator) {
        toConscrypt(sSLEngine).setBufferAllocator(bufferAllocator);
    }

    public static void setBufferAllocator(SSLSocket sSLSocket, BufferAllocator bufferAllocator) {
        AbstractConscryptSocket conscrypt = toConscrypt(sSLSocket);
        if (conscrypt instanceof ConscryptEngineSocket) {
            ((ConscryptEngineSocket) conscrypt).setBufferAllocator(bufferAllocator);
        }
    }

    public static void setDefaultBufferAllocator(BufferAllocator bufferAllocator) {
        ConscryptEngine.setDefaultBufferAllocator(bufferAllocator);
    }

    public static void setHostname(SSLEngine sSLEngine, String str) {
        toConscrypt(sSLEngine).setHostname(str);
    }

    public static String getHostname(SSLEngine sSLEngine) {
        return toConscrypt(sSLEngine).getHostname();
    }

    public static int maxSealOverhead(SSLEngine sSLEngine) {
        return toConscrypt(sSLEngine).maxSealOverhead();
    }

    public static void setHandshakeListener(SSLEngine sSLEngine, HandshakeListener handshakeListener) {
        toConscrypt(sSLEngine).setHandshakeListener(handshakeListener);
    }

    public static void setChannelIdEnabled(SSLEngine sSLEngine, boolean z) {
        toConscrypt(sSLEngine).setChannelIdEnabled(z);
    }

    public static byte[] getChannelId(SSLEngine sSLEngine) throws SSLException {
        return toConscrypt(sSLEngine).getChannelId();
    }

    public static void setChannelIdPrivateKey(SSLEngine sSLEngine, PrivateKey privateKey) {
        toConscrypt(sSLEngine).setChannelIdPrivateKey(privateKey);
    }

    public static SSLEngineResult unwrap(SSLEngine sSLEngine, ByteBuffer[] byteBufferArr, ByteBuffer[] byteBufferArr2) throws SSLException {
        return toConscrypt(sSLEngine).unwrap(byteBufferArr, byteBufferArr2);
    }

    public static SSLEngineResult unwrap(SSLEngine sSLEngine, ByteBuffer[] byteBufferArr, int i, int i2, ByteBuffer[] byteBufferArr2, int i3, int i4) throws SSLException {
        return toConscrypt(sSLEngine).unwrap(byteBufferArr, i, i2, byteBufferArr2, i3, i4);
    }

    public static void setUseSessionTickets(SSLEngine sSLEngine, boolean z) {
        toConscrypt(sSLEngine).setUseSessionTickets(z);
    }

    public static void setApplicationProtocols(SSLEngine sSLEngine, String[] strArr) {
        toConscrypt(sSLEngine).setApplicationProtocols(strArr);
    }

    public static String[] getApplicationProtocols(SSLEngine sSLEngine) {
        return toConscrypt(sSLEngine).getApplicationProtocols();
    }

    public static void setApplicationProtocolSelector(SSLEngine sSLEngine, ApplicationProtocolSelector applicationProtocolSelector) {
        toConscrypt(sSLEngine).setApplicationProtocolSelector(applicationProtocolSelector);
    }

    public static String getApplicationProtocol(SSLEngine sSLEngine) {
        return toConscrypt(sSLEngine).getApplicationProtocol();
    }

    public static byte[] getTlsUnique(SSLEngine sSLEngine) {
        return toConscrypt(sSLEngine).getTlsUnique();
    }

    public static byte[] exportKeyingMaterial(SSLEngine sSLEngine, String str, byte[] bArr, int i) throws SSLException {
        return toConscrypt(sSLEngine).exportKeyingMaterial(str, bArr, i);
    }

    public static boolean isConscrypt(TrustManager trustManager) {
        return trustManager instanceof TrustManagerImpl;
    }

    private static TrustManagerImpl toConscrypt(TrustManager trustManager) {
        if (!isConscrypt(trustManager)) {
            throw new IllegalArgumentException("Not a Conscrypt trust manager: " + trustManager.getClass().getName());
        }
        return (TrustManagerImpl) trustManager;
    }

    public static synchronized void setDefaultHostnameVerifier(ConscryptHostnameVerifier conscryptHostnameVerifier) {
        synchronized (Conscrypt.class) {
            TrustManagerImpl.setDefaultHostnameVerifier(conscryptHostnameVerifier);
        }
    }

    public static synchronized ConscryptHostnameVerifier getDefaultHostnameVerifier(TrustManager trustManager) {
        ConscryptHostnameVerifier defaultHostnameVerifier;
        synchronized (Conscrypt.class) {
            defaultHostnameVerifier = TrustManagerImpl.getDefaultHostnameVerifier();
        }
        return defaultHostnameVerifier;
    }

    public static void setHostnameVerifier(TrustManager trustManager, ConscryptHostnameVerifier conscryptHostnameVerifier) {
        toConscrypt(trustManager).setHostnameVerifier(conscryptHostnameVerifier);
    }

    public static ConscryptHostnameVerifier getHostnameVerifier(TrustManager trustManager) {
        return toConscrypt(trustManager).getHostnameVerifier();
    }

    public static ConscryptHostnameVerifier wrapHostnameVerifier(final HostnameVerifier hostnameVerifier) {
        return new ConscryptHostnameVerifier() { // from class: org.conscrypt.Conscrypt.1
            @Override // org.conscrypt.ConscryptHostnameVerifier
            public boolean verify(X509Certificate[] x509CertificateArr, String str, SSLSession sSLSession) {
                return hostnameVerifier.verify(str, sSLSession);
            }
        };
    }
}
