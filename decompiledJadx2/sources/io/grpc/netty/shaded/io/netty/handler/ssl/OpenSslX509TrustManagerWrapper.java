package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.util.internal.EmptyArrays;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.security.AccessController;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivilegedAction;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class OpenSslX509TrustManagerWrapper {
    private static final InternalLogger LOGGER = InternalLoggerFactory.getInstance((Class<?>) OpenSslX509TrustManagerWrapper.class);
    private static final TrustManagerWrapper WRAPPER;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface TrustManagerWrapper {
        X509TrustManager wrapIfNeeded(X509TrustManager x509TrustManager);
    }

    static /* synthetic */ SSLContext access$000() throws NoSuchAlgorithmException, NoSuchProviderException {
        return newSSLContext();
    }

    static {
        TrustManagerWrapper trustManagerWrapper = new TrustManagerWrapper() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.OpenSslX509TrustManagerWrapper.1
            @Override // io.grpc.netty.shaded.io.netty.handler.ssl.OpenSslX509TrustManagerWrapper.TrustManagerWrapper
            public X509TrustManager wrapIfNeeded(X509TrustManager x509TrustManager) {
                return x509TrustManager;
            }
        };
        final SSLContext sSLContext = null;
        if (PlatformDependent.getUnsafeUnavailabilityCause() == null) {
            try {
                SSLContext newSSLContext = newSSLContext();
                newSSLContext.init(null, new TrustManager[]{new X509TrustManager() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.OpenSslX509TrustManagerWrapper.2
                    @Override // javax.net.ssl.X509TrustManager
                    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                        throw new CertificateException();
                    }

                    @Override // javax.net.ssl.X509TrustManager
                    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                        throw new CertificateException();
                    }

                    @Override // javax.net.ssl.X509TrustManager
                    public X509Certificate[] getAcceptedIssuers() {
                        return EmptyArrays.EMPTY_X509_CERTIFICATES;
                    }
                }}, null);
                sSLContext = newSSLContext;
                th = null;
            } catch (Throwable th) {
                th = th;
            }
            if (th != null) {
                LOGGER.debug("Unable to access wrapped TrustManager", th);
            } else {
                Object doPrivileged = AccessController.doPrivileged(new PrivilegedAction<Object>() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.OpenSslX509TrustManagerWrapper.3
                    @Override // java.security.PrivilegedAction
                    public Object run() {
                        try {
                            try {
                                long objectFieldOffset = PlatformDependent.objectFieldOffset(SSLContext.class.getDeclaredField("contextSpi"));
                                Object object = PlatformDependent.getObject(sSLContext, objectFieldOffset);
                                if (object != null) {
                                    Class<?> cls = object.getClass();
                                    do {
                                        try {
                                            long objectFieldOffset2 = PlatformDependent.objectFieldOffset(cls.getDeclaredField("trustManager"));
                                            if (PlatformDependent.getObject(object, objectFieldOffset2) instanceof X509ExtendedTrustManager) {
                                                return new UnsafeTrustManagerWrapper(objectFieldOffset, objectFieldOffset2);
                                            }
                                        } catch (NoSuchFieldException unused) {
                                        }
                                        cls = cls.getSuperclass();
                                    } while (cls != null);
                                }
                                throw new NoSuchFieldException();
                            } catch (SecurityException e) {
                                return e;
                            }
                        } catch (NoSuchFieldException e2) {
                            return e2;
                        }
                    }
                });
                if (doPrivileged instanceof Throwable) {
                    LOGGER.debug("Unable to access wrapped TrustManager", (Throwable) doPrivileged);
                } else {
                    trustManagerWrapper = (TrustManagerWrapper) doPrivileged;
                }
            }
        } else {
            LOGGER.debug("Unable to access wrapped TrustManager", (Throwable) null);
        }
        WRAPPER = trustManagerWrapper;
    }

    private OpenSslX509TrustManagerWrapper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static X509TrustManager wrapIfNeeded(X509TrustManager x509TrustManager) {
        return WRAPPER.wrapIfNeeded(x509TrustManager);
    }

    private static SSLContext newSSLContext() throws NoSuchAlgorithmException, NoSuchProviderException {
        return SSLContext.getInstance("TLS", "SunJSSE");
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class UnsafeTrustManagerWrapper implements TrustManagerWrapper {
        private final long spiOffset;
        private final long tmOffset;

        UnsafeTrustManagerWrapper(long j, long j2) {
            this.spiOffset = j;
            this.tmOffset = j2;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.ssl.OpenSslX509TrustManagerWrapper.TrustManagerWrapper
        public X509TrustManager wrapIfNeeded(X509TrustManager x509TrustManager) {
            if (!(x509TrustManager instanceof X509ExtendedTrustManager)) {
                try {
                    SSLContext access$000 = OpenSslX509TrustManagerWrapper.access$000();
                    access$000.init(null, new TrustManager[]{x509TrustManager}, null);
                    Object object = PlatformDependent.getObject(access$000, this.spiOffset);
                    if (object != null) {
                        Object object2 = PlatformDependent.getObject(object, this.tmOffset);
                        if (object2 instanceof X509ExtendedTrustManager) {
                            return (X509TrustManager) object2;
                        }
                    }
                } catch (KeyManagementException e) {
                    PlatformDependent.throwException(e);
                } catch (NoSuchAlgorithmException e2) {
                    PlatformDependent.throwException(e2);
                } catch (NoSuchProviderException e3) {
                    PlatformDependent.throwException(e3);
                }
            }
            return x509TrustManager;
        }
    }
}
