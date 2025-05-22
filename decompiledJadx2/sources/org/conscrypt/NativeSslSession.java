package org.conscrypt;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionContext;
import org.conscrypt.NativeRef;
import org.conscrypt.SSLUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public abstract class NativeSslSession {
    private static final Logger logger = Logger.getLogger(NativeSslSession.class.getName());

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String getCipherSuite();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte[] getId();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String getPeerHost();

    abstract byte[] getPeerOcspStapledResponse();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int getPeerPort();

    abstract byte[] getPeerSignedCertificateTimestamp();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String getProtocol();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean isSingleUse();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean isValid();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void offerToResume(NativeSsl nativeSsl) throws SSLException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte[] toBytes();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract SSLSession toSSLSession();

    NativeSslSession() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static NativeSslSession newInstance(NativeRef.SSL_SESSION ssl_session, ConscryptSession conscryptSession) throws SSLPeerUnverifiedException {
        AbstractSessionContext abstractSessionContext = (AbstractSessionContext) conscryptSession.getSessionContext();
        if (abstractSessionContext instanceof ClientSessionContext) {
            return new Impl(abstractSessionContext, ssl_session, conscryptSession.getPeerHost(), conscryptSession.getPeerPort(), conscryptSession.getPeerCertificates(), getOcspResponse(conscryptSession), conscryptSession.getPeerSignedCertificateTimestamp());
        }
        return new Impl(abstractSessionContext, ssl_session, null, -1, null, null, null);
    }

    private static byte[] getOcspResponse(ConscryptSession conscryptSession) {
        List<byte[]> statusResponses = conscryptSession.getStatusResponses();
        if (statusResponses.size() >= 1) {
            return statusResponses.get(0);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0094 A[Catch: BufferUnderflowException -> 0x00e3, IOException -> 0x00e8, TryCatch #3 {IOException -> 0x00e8, BufferUnderflowException -> 0x00e3, blocks: (B:3:0x0005, B:5:0x000f, B:7:0x0027, B:9:0x0033, B:12:0x003c, B:13:0x005a, B:16:0x005b, B:18:0x0061, B:20:0x006b, B:22:0x0079, B:25:0x008e, B:27:0x0094, B:29:0x009d, B:30:0x00a5, B:32:0x00ab, B:34:0x00b6, B:38:0x00cc, B:39:0x00e2), top: B:2:0x0005, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ab A[Catch: BufferUnderflowException -> 0x00e3, IOException -> 0x00e8, TryCatch #3 {IOException -> 0x00e8, BufferUnderflowException -> 0x00e3, blocks: (B:3:0x0005, B:5:0x000f, B:7:0x0027, B:9:0x0033, B:12:0x003c, B:13:0x005a, B:16:0x005b, B:18:0x0061, B:20:0x006b, B:22:0x0079, B:25:0x008e, B:27:0x0094, B:29:0x009d, B:30:0x00a5, B:32:0x00ab, B:34:0x00b6, B:38:0x00cc, B:39:0x00e2), top: B:2:0x0005, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00b6 A[Catch: BufferUnderflowException -> 0x00e3, IOException -> 0x00e8, TryCatch #3 {IOException -> 0x00e8, BufferUnderflowException -> 0x00e3, blocks: (B:3:0x0005, B:5:0x000f, B:7:0x0027, B:9:0x0033, B:12:0x003c, B:13:0x005a, B:16:0x005b, B:18:0x0061, B:20:0x006b, B:22:0x0079, B:25:0x008e, B:27:0x0094, B:29:0x009d, B:30:0x00a5, B:32:0x00ab, B:34:0x00b6, B:38:0x00cc, B:39:0x00e2), top: B:2:0x0005, inners: #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static NativeSslSession newInstance(AbstractSessionContext abstractSessionContext, byte[] bArr, String str, int i) {
        byte[] bArr2;
        byte[] bArr3;
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        try {
            int i2 = wrap.getInt();
            if (!SSLUtils.SessionType.isSupportedType(i2)) {
                throw new IOException("Unexpected type ID: " + i2);
            }
            int i3 = wrap.getInt();
            checkRemaining(wrap, i3);
            byte[] bArr4 = new byte[i3];
            wrap.get(bArr4);
            int i4 = wrap.getInt();
            checkRemaining(wrap, i4);
            X509Certificate[] x509CertificateArr = new X509Certificate[i4];
            for (int i5 = 0; i5 < i4; i5++) {
                int i6 = wrap.getInt();
                checkRemaining(wrap, i6);
                byte[] bArr5 = new byte[i6];
                wrap.get(bArr5);
                try {
                    x509CertificateArr[i5] = OpenSSLX509Certificate.fromX509Der(bArr5);
                } catch (Exception unused) {
                    throw new IOException("Can not read certificate " + i5 + "/" + i4);
                }
            }
            if (i2 >= SSLUtils.SessionType.OPEN_SSL_WITH_OCSP.value) {
                int i7 = wrap.getInt();
                checkRemaining(wrap, i7);
                if (i7 >= 1) {
                    int i8 = wrap.getInt();
                    checkRemaining(wrap, i8);
                    byte[] bArr6 = new byte[i8];
                    wrap.get(bArr6);
                    for (int i9 = 1; i9 < i7; i9++) {
                        int i10 = wrap.getInt();
                        checkRemaining(wrap, i10);
                        wrap.position(wrap.position() + i10);
                    }
                    bArr2 = bArr6;
                    if (i2 == SSLUtils.SessionType.OPEN_SSL_WITH_TLS_SCT.value) {
                        int i11 = wrap.getInt();
                        checkRemaining(wrap, i11);
                        if (i11 > 0) {
                            byte[] bArr7 = new byte[i11];
                            wrap.get(bArr7);
                            bArr3 = bArr7;
                            if (wrap.remaining() != 0) {
                                log(new AssertionError("Read entire session, but data still remains; rejecting"));
                                return null;
                            }
                            return new Impl(abstractSessionContext, new NativeRef.SSL_SESSION(NativeCrypto.d2i_SSL_SESSION(bArr4)), str, i, x509CertificateArr, bArr2, bArr3);
                        }
                    }
                    bArr3 = null;
                    if (wrap.remaining() != 0) {
                    }
                }
            }
            bArr2 = null;
            if (i2 == SSLUtils.SessionType.OPEN_SSL_WITH_TLS_SCT.value) {
            }
            bArr3 = null;
            if (wrap.remaining() != 0) {
            }
        } catch (IOException e) {
            log(e);
            return null;
        } catch (BufferUnderflowException e2) {
            log(e2);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public static final class Impl extends NativeSslSession {
        private final String cipherSuite;
        private final AbstractSessionContext context;
        private final String host;
        private final X509Certificate[] peerCertificates;
        private final byte[] peerOcspStapledResponse;
        private final byte[] peerSignedCertificateTimestamp;
        private final int port;
        private final String protocol;
        private final NativeRef.SSL_SESSION ref;

        private Impl(AbstractSessionContext abstractSessionContext, NativeRef.SSL_SESSION ssl_session, String str, int i, X509Certificate[] x509CertificateArr, byte[] bArr, byte[] bArr2) {
            this.context = abstractSessionContext;
            this.host = str;
            this.port = i;
            this.peerCertificates = x509CertificateArr;
            this.peerOcspStapledResponse = bArr;
            this.peerSignedCertificateTimestamp = bArr2;
            this.protocol = NativeCrypto.SSL_SESSION_get_version(ssl_session.address);
            this.cipherSuite = NativeCrypto.cipherSuiteToJava(NativeCrypto.SSL_SESSION_cipher(ssl_session.address));
            this.ref = ssl_session;
        }

        @Override // org.conscrypt.NativeSslSession
        byte[] getId() {
            return NativeCrypto.SSL_SESSION_session_id(this.ref.address);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public long getCreationTime() {
            return NativeCrypto.SSL_SESSION_get_time(this.ref.address);
        }

        @Override // org.conscrypt.NativeSslSession
        boolean isValid() {
            return System.currentTimeMillis() - (Math.max(0L, Math.min((long) this.context.getSessionTimeout(), NativeCrypto.SSL_SESSION_get_timeout(this.ref.address))) * 1000) < getCreationTime();
        }

        @Override // org.conscrypt.NativeSslSession
        boolean isSingleUse() {
            return NativeCrypto.SSL_SESSION_should_be_single_use(this.ref.address);
        }

        @Override // org.conscrypt.NativeSslSession
        void offerToResume(NativeSsl nativeSsl) throws SSLException {
            nativeSsl.offerToResumeSession(this.ref.address);
        }

        @Override // org.conscrypt.NativeSslSession
        String getCipherSuite() {
            return this.cipherSuite;
        }

        @Override // org.conscrypt.NativeSslSession
        String getProtocol() {
            return this.protocol;
        }

        @Override // org.conscrypt.NativeSslSession
        String getPeerHost() {
            return this.host;
        }

        @Override // org.conscrypt.NativeSslSession
        int getPeerPort() {
            return this.port;
        }

        @Override // org.conscrypt.NativeSslSession
        byte[] getPeerOcspStapledResponse() {
            return this.peerOcspStapledResponse;
        }

        @Override // org.conscrypt.NativeSslSession
        byte[] getPeerSignedCertificateTimestamp() {
            return this.peerSignedCertificateTimestamp;
        }

        @Override // org.conscrypt.NativeSslSession
        byte[] toBytes() {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                dataOutputStream.writeInt(SSLUtils.SessionType.OPEN_SSL_WITH_TLS_SCT.value);
                byte[] i2d_SSL_SESSION = NativeCrypto.i2d_SSL_SESSION(this.ref.address);
                dataOutputStream.writeInt(i2d_SSL_SESSION.length);
                dataOutputStream.write(i2d_SSL_SESSION);
                dataOutputStream.writeInt(this.peerCertificates.length);
                for (X509Certificate x509Certificate : this.peerCertificates) {
                    byte[] encoded = x509Certificate.getEncoded();
                    dataOutputStream.writeInt(encoded.length);
                    dataOutputStream.write(encoded);
                }
                if (this.peerOcspStapledResponse != null) {
                    dataOutputStream.writeInt(1);
                    dataOutputStream.writeInt(this.peerOcspStapledResponse.length);
                    dataOutputStream.write(this.peerOcspStapledResponse);
                } else {
                    dataOutputStream.writeInt(0);
                }
                if (this.peerSignedCertificateTimestamp != null) {
                    dataOutputStream.writeInt(this.peerSignedCertificateTimestamp.length);
                    dataOutputStream.write(this.peerSignedCertificateTimestamp);
                } else {
                    dataOutputStream.writeInt(0);
                }
                return byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                NativeSslSession.logger.log(Level.WARNING, "Failed to convert saved SSL Session: ", (Throwable) e);
                return null;
            } catch (CertificateEncodingException e2) {
                NativeSslSession.log(e2);
                return null;
            }
        }

        @Override // org.conscrypt.NativeSslSession
        SSLSession toSSLSession() {
            return new SSLSession() { // from class: org.conscrypt.NativeSslSession.Impl.1
                @Override // javax.net.ssl.SSLSession
                public byte[] getId() {
                    return Impl.this.getId();
                }

                @Override // javax.net.ssl.SSLSession
                public String getCipherSuite() {
                    return Impl.this.getCipherSuite();
                }

                @Override // javax.net.ssl.SSLSession
                public String getProtocol() {
                    return Impl.this.getProtocol();
                }

                @Override // javax.net.ssl.SSLSession
                public String getPeerHost() {
                    return Impl.this.getPeerHost();
                }

                @Override // javax.net.ssl.SSLSession
                public int getPeerPort() {
                    return Impl.this.getPeerPort();
                }

                @Override // javax.net.ssl.SSLSession
                public long getCreationTime() {
                    return Impl.this.getCreationTime();
                }

                @Override // javax.net.ssl.SSLSession
                public boolean isValid() {
                    return Impl.this.isValid();
                }

                @Override // javax.net.ssl.SSLSession
                public SSLSessionContext getSessionContext() {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public long getLastAccessedTime() {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public void invalidate() {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public void putValue(String str, Object obj) {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public Object getValue(String str) {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public void removeValue(String str) {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public String[] getValueNames() {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public Certificate[] getPeerCertificates() throws SSLPeerUnverifiedException {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public Certificate[] getLocalCertificates() {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public javax.security.cert.X509Certificate[] getPeerCertificateChain() throws SSLPeerUnverifiedException {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public Principal getLocalPrincipal() {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public int getPacketBufferSize() {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public int getApplicationBufferSize() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void log(Throwable th) {
        logger.log(Level.INFO, "Error inflating SSL session: {0}", th.getMessage() != null ? th.getMessage() : th.getClass().getName());
    }

    private static void checkRemaining(ByteBuffer byteBuffer, int i) throws IOException {
        if (i < 0) {
            throw new IOException("Length is negative: " + i);
        }
        if (i <= byteBuffer.remaining()) {
            return;
        }
        throw new IOException("Length of blob is longer than available: " + i + " > " + byteBuffer.remaining());
    }
}
