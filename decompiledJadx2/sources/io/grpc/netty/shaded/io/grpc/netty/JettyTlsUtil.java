package io.grpc.netty.shaded.io.grpc.netty;

import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class JettyTlsUtil {
    private static Throwable jettyAlpnUnavailabilityCause;
    private static Throwable jettyNpnUnavailabilityCause;

    private JettyTlsUtil() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static class Java9AlpnUnavailabilityCauseHolder {
        static final Throwable cause = checkAlpnAvailability();

        private Java9AlpnUnavailabilityCauseHolder() {
        }

        static Throwable checkAlpnAvailability() {
            try {
                SSLContext sSLContext = SSLContext.getInstance("TLS");
                sSLContext.init(null, null, null);
                ((Method) AccessController.doPrivileged(new PrivilegedExceptionAction<Method>() { // from class: io.grpc.netty.shaded.io.grpc.netty.JettyTlsUtil.Java9AlpnUnavailabilityCauseHolder.1
                    @Override // java.security.PrivilegedExceptionAction
                    public Method run() throws Exception {
                        return SSLEngine.class.getMethod("getApplicationProtocol", new Class[0]);
                    }
                })).invoke(sSLContext.createSSLEngine(), new Object[0]);
                return null;
            } catch (Throwable th) {
                return th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized boolean isJettyAlpnConfigured() {
        synchronized (JettyTlsUtil.class) {
            try {
                Class.forName("org.eclipse.jetty.alpn.ALPN", true, null);
            } catch (ClassNotFoundException e) {
                jettyAlpnUnavailabilityCause = e;
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized Throwable getJettyAlpnUnavailabilityCause() {
        Throwable th;
        synchronized (JettyTlsUtil.class) {
            if (jettyAlpnUnavailabilityCause == null) {
                isJettyAlpnConfigured();
            }
            th = jettyAlpnUnavailabilityCause;
        }
        return th;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized boolean isJettyNpnConfigured() {
        synchronized (JettyTlsUtil.class) {
            try {
                Class.forName("org.eclipse.jetty.npn.NextProtoNego", true, null);
            } catch (ClassNotFoundException e) {
                jettyNpnUnavailabilityCause = e;
                return false;
            }
        }
        return true;
    }

    static synchronized Throwable getJettyNpnUnavailabilityCause() {
        Throwable th;
        synchronized (JettyTlsUtil.class) {
            if (jettyNpnUnavailabilityCause == null) {
                isJettyNpnConfigured();
            }
            th = jettyNpnUnavailabilityCause;
        }
        return th;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isJava9AlpnAvailable() {
        return getJava9AlpnUnavailabilityCause() == null;
    }

    static Throwable getJava9AlpnUnavailabilityCause() {
        return Java9AlpnUnavailabilityCauseHolder.cause;
    }
}
