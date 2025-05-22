package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import java.net.Socket;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.X509ExtendedTrustManager;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class OpenSslTlsv13X509ExtendedTrustManager extends X509ExtendedTrustManager {

    /* renamed from: tm */
    private final X509ExtendedTrustManager f8415tm;

    private OpenSslTlsv13X509ExtendedTrustManager(X509ExtendedTrustManager x509ExtendedTrustManager) {
        this.f8415tm = x509ExtendedTrustManager;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static X509ExtendedTrustManager wrap(X509ExtendedTrustManager x509ExtendedTrustManager) {
        return (SslProvider.isTlsv13Supported(SslProvider.JDK) || !SslProvider.isTlsv13Supported(SslProvider.OPENSSL)) ? x509ExtendedTrustManager : new OpenSslTlsv13X509ExtendedTrustManager(x509ExtendedTrustManager);
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) throws CertificateException {
        this.f8415tm.checkClientTrusted(x509CertificateArr, str, socket);
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) throws CertificateException {
        this.f8415tm.checkServerTrusted(x509CertificateArr, str, socket);
    }

    private static SSLEngine wrapEngine(final SSLEngine sSLEngine) {
        final SSLSession handshakeSession = sSLEngine.getHandshakeSession();
        return (handshakeSession == null || !"TLSv1.3".equals(handshakeSession.getProtocol())) ? sSLEngine : new JdkSslEngine(sSLEngine) { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.OpenSslTlsv13X509ExtendedTrustManager.1
            @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkSslEngine, io.grpc.netty.shaded.io.netty.handler.ssl.ApplicationProtocolAccessor
            public String getNegotiatedApplicationProtocol() {
                Object obj = sSLEngine;
                if (obj instanceof ApplicationProtocolAccessor) {
                    return ((ApplicationProtocolAccessor) obj).getNegotiatedApplicationProtocol();
                }
                return super.getNegotiatedApplicationProtocol();
            }

            @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkSslEngine, javax.net.ssl.SSLEngine
            public SSLSession getHandshakeSession() {
                if (PlatformDependent.javaVersion() >= 7) {
                    SSLSession sSLSession = handshakeSession;
                    if (sSLSession instanceof ExtendedOpenSslSession) {
                        final ExtendedOpenSslSession extendedOpenSslSession = (ExtendedOpenSslSession) sSLSession;
                        return new ExtendedOpenSslSession(extendedOpenSslSession) { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.OpenSslTlsv13X509ExtendedTrustManager.1.1
                            @Override // io.grpc.netty.shaded.io.netty.handler.ssl.ExtendedOpenSslSession, javax.net.ssl.SSLSession
                            public String getProtocol() {
                                return "TLSv1.2";
                            }

                            @Override // io.grpc.netty.shaded.io.netty.handler.ssl.ExtendedOpenSslSession, javax.net.ssl.ExtendedSSLSession
                            public List getRequestedServerNames() {
                                return extendedOpenSslSession.getRequestedServerNames();
                            }

                            @Override // javax.net.ssl.ExtendedSSLSession
                            public String[] getPeerSupportedSignatureAlgorithms() {
                                return extendedOpenSslSession.getPeerSupportedSignatureAlgorithms();
                            }
                        };
                    }
                }
                return new SSLSession() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.OpenSslTlsv13X509ExtendedTrustManager.1.2
                    @Override // javax.net.ssl.SSLSession
                    public String getProtocol() {
                        return "TLSv1.2";
                    }

                    @Override // javax.net.ssl.SSLSession
                    public byte[] getId() {
                        return handshakeSession.getId();
                    }

                    @Override // javax.net.ssl.SSLSession
                    public SSLSessionContext getSessionContext() {
                        return handshakeSession.getSessionContext();
                    }

                    @Override // javax.net.ssl.SSLSession
                    public long getCreationTime() {
                        return handshakeSession.getCreationTime();
                    }

                    @Override // javax.net.ssl.SSLSession
                    public long getLastAccessedTime() {
                        return handshakeSession.getLastAccessedTime();
                    }

                    @Override // javax.net.ssl.SSLSession
                    public void invalidate() {
                        handshakeSession.invalidate();
                    }

                    @Override // javax.net.ssl.SSLSession
                    public boolean isValid() {
                        return handshakeSession.isValid();
                    }

                    @Override // javax.net.ssl.SSLSession
                    public void putValue(String str, Object obj) {
                        handshakeSession.putValue(str, obj);
                    }

                    @Override // javax.net.ssl.SSLSession
                    public Object getValue(String str) {
                        return handshakeSession.getValue(str);
                    }

                    @Override // javax.net.ssl.SSLSession
                    public void removeValue(String str) {
                        handshakeSession.removeValue(str);
                    }

                    @Override // javax.net.ssl.SSLSession
                    public String[] getValueNames() {
                        return handshakeSession.getValueNames();
                    }

                    @Override // javax.net.ssl.SSLSession
                    public Certificate[] getPeerCertificates() throws SSLPeerUnverifiedException {
                        return handshakeSession.getPeerCertificates();
                    }

                    @Override // javax.net.ssl.SSLSession
                    public Certificate[] getLocalCertificates() {
                        return handshakeSession.getLocalCertificates();
                    }

                    @Override // javax.net.ssl.SSLSession
                    public javax.security.cert.X509Certificate[] getPeerCertificateChain() throws SSLPeerUnverifiedException {
                        return handshakeSession.getPeerCertificateChain();
                    }

                    @Override // javax.net.ssl.SSLSession
                    public Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
                        return handshakeSession.getPeerPrincipal();
                    }

                    @Override // javax.net.ssl.SSLSession
                    public Principal getLocalPrincipal() {
                        return handshakeSession.getLocalPrincipal();
                    }

                    @Override // javax.net.ssl.SSLSession
                    public String getCipherSuite() {
                        return handshakeSession.getCipherSuite();
                    }

                    @Override // javax.net.ssl.SSLSession
                    public String getPeerHost() {
                        return handshakeSession.getPeerHost();
                    }

                    @Override // javax.net.ssl.SSLSession
                    public int getPeerPort() {
                        return handshakeSession.getPeerPort();
                    }

                    @Override // javax.net.ssl.SSLSession
                    public int getPacketBufferSize() {
                        return handshakeSession.getPacketBufferSize();
                    }

                    @Override // javax.net.ssl.SSLSession
                    public int getApplicationBufferSize() {
                        return handshakeSession.getApplicationBufferSize();
                    }
                };
            }
        };
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) throws CertificateException {
        this.f8415tm.checkClientTrusted(x509CertificateArr, str, wrapEngine(sSLEngine));
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) throws CertificateException {
        this.f8415tm.checkServerTrusted(x509CertificateArr, str, wrapEngine(sSLEngine));
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        this.f8415tm.checkClientTrusted(x509CertificateArr, str);
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        this.f8415tm.checkServerTrusted(x509CertificateArr, str);
    }

    @Override // javax.net.ssl.X509TrustManager
    public X509Certificate[] getAcceptedIssuers() {
        return this.f8415tm.getAcceptedIssuers();
    }
}
