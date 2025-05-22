package io.grpc.netty.shaded.io.netty.internal.tcnative;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class SSL {
    public static final int SSL_CVERIFY_IGNORED = -1;
    public static final int SSL_CVERIFY_NONE = 0;
    public static final int SSL_CVERIFY_OPTIONAL = 1;
    public static final int SSL_CVERIFY_REQUIRED = 2;
    public static final int SSL_MODE_CLIENT = 0;
    public static final int SSL_MODE_COMBINED = 2;
    public static final int SSL_MODE_SERVER = 1;
    public static final int SSL_PROTOCOL_ALL = 63;
    public static final int SSL_PROTOCOL_NONE = 0;
    public static final int SSL_PROTOCOL_SSLV2 = 1;
    public static final int SSL_PROTOCOL_SSLV3 = 2;
    public static final int SSL_PROTOCOL_TLS = 62;
    public static final int SSL_PROTOCOL_TLSV1 = 4;
    public static final int SSL_PROTOCOL_TLSV1_1 = 8;
    public static final int SSL_PROTOCOL_TLSV1_2 = 16;
    public static final int SSL_PROTOCOL_TLSV1_3 = 32;
    public static final int SSL_SELECTOR_FAILURE_CHOOSE_MY_LAST_PROTOCOL = 1;
    public static final int SSL_SELECTOR_FAILURE_NO_ADVERTISE = 0;
    public static final int SSL_OP_CIPHER_SERVER_PREFERENCE = NativeStaticallyReferencedJniMethods.sslOpCipherServerPreference();
    public static final int SSL_OP_NO_SSLv2 = NativeStaticallyReferencedJniMethods.sslOpNoSSLv2();
    public static final int SSL_OP_NO_SSLv3 = NativeStaticallyReferencedJniMethods.sslOpNoSSLv3();
    public static final int SSL_OP_NO_TLSv1 = NativeStaticallyReferencedJniMethods.sslOpNoTLSv1();
    public static final int SSL_OP_NO_TLSv1_1 = NativeStaticallyReferencedJniMethods.sslOpNoTLSv11();
    public static final int SSL_OP_NO_TLSv1_2 = NativeStaticallyReferencedJniMethods.sslOpNoTLSv12();
    public static final int SSL_OP_NO_TLSv1_3 = NativeStaticallyReferencedJniMethods.sslOpNoTLSv13();
    public static final int SSL_OP_NO_TICKET = NativeStaticallyReferencedJniMethods.sslOpNoTicket();
    public static final int SSL_OP_NO_COMPRESSION = NativeStaticallyReferencedJniMethods.sslOpNoCompression();
    public static final long SSL_SESS_CACHE_OFF = NativeStaticallyReferencedJniMethods.sslSessCacheOff();
    public static final long SSL_SESS_CACHE_SERVER = NativeStaticallyReferencedJniMethods.sslSessCacheServer();
    public static final long SSL_SESS_CACHE_CLIENT = NativeStaticallyReferencedJniMethods.sslSessCacheClient();
    public static final long SSL_SESS_CACHE_NO_INTERNAL_LOOKUP = NativeStaticallyReferencedJniMethods.sslSessCacheNoInternalLookup();
    public static final long SSL_SESS_CACHE_NO_INTERNAL_STORE = NativeStaticallyReferencedJniMethods.sslSessCacheNoInternalStore();
    public static final int SSL_ST_CONNECT = NativeStaticallyReferencedJniMethods.sslStConnect();
    public static final int SSL_ST_ACCEPT = NativeStaticallyReferencedJniMethods.sslStAccept();
    public static final int SSL_MODE_ENABLE_PARTIAL_WRITE = NativeStaticallyReferencedJniMethods.sslModeEnablePartialWrite();
    public static final int SSL_MODE_ACCEPT_MOVING_WRITE_BUFFER = NativeStaticallyReferencedJniMethods.sslModeAcceptMovingWriteBuffer();
    public static final int SSL_MODE_RELEASE_BUFFERS = NativeStaticallyReferencedJniMethods.sslModeReleaseBuffers();
    public static final int SSL_MODE_ENABLE_FALSE_START = NativeStaticallyReferencedJniMethods.sslModeEnableFalseStart();
    public static final int SSL_MAX_PLAINTEXT_LENGTH = NativeStaticallyReferencedJniMethods.sslMaxPlaintextLength();
    public static final int SSL_MAX_RECORD_LENGTH = NativeStaticallyReferencedJniMethods.sslMaxRecordLength();
    public static final int X509_CHECK_FLAG_ALWAYS_CHECK_SUBJECT = NativeStaticallyReferencedJniMethods.x509CheckFlagAlwaysCheckSubject();
    public static final int X509_CHECK_FLAG_NO_WILD_CARDS = NativeStaticallyReferencedJniMethods.x509CheckFlagDisableWildCards();
    public static final int X509_CHECK_FLAG_NO_PARTIAL_WILD_CARDS = NativeStaticallyReferencedJniMethods.x509CheckFlagNoPartialWildCards();
    public static final int X509_CHECK_FLAG_MULTI_LABEL_WILDCARDS = NativeStaticallyReferencedJniMethods.x509CheckFlagMultiLabelWildCards();
    public static final int SSL_SENT_SHUTDOWN = NativeStaticallyReferencedJniMethods.sslSendShutdown();
    public static final int SSL_RECEIVED_SHUTDOWN = NativeStaticallyReferencedJniMethods.sslReceivedShutdown();
    public static final int SSL_ERROR_NONE = NativeStaticallyReferencedJniMethods.sslErrorNone();
    public static final int SSL_ERROR_SSL = NativeStaticallyReferencedJniMethods.sslErrorSSL();
    public static final int SSL_ERROR_WANT_READ = NativeStaticallyReferencedJniMethods.sslErrorWantRead();
    public static final int SSL_ERROR_WANT_WRITE = NativeStaticallyReferencedJniMethods.sslErrorWantWrite();
    public static final int SSL_ERROR_WANT_X509_LOOKUP = NativeStaticallyReferencedJniMethods.sslErrorWantX509Lookup();
    public static final int SSL_ERROR_SYSCALL = NativeStaticallyReferencedJniMethods.sslErrorSyscall();
    public static final int SSL_ERROR_ZERO_RETURN = NativeStaticallyReferencedJniMethods.sslErrorZeroReturn();
    public static final int SSL_ERROR_WANT_CONNECT = NativeStaticallyReferencedJniMethods.sslErrorWantConnect();
    public static final int SSL_ERROR_WANT_ACCEPT = NativeStaticallyReferencedJniMethods.sslErrorWantAccept();
    public static final int SSL_ERROR_WANT_PRIVATE_KEY_OPERATION = NativeStaticallyReferencedJniMethods.sslErrorWantPrivateKeyOperation();
    public static final int SSL_ERROR_WANT_CERTIFICATE_VERIFY = NativeStaticallyReferencedJniMethods.sslErrorWantCertificateVerify();

    public static native String[] authenticationMethods(long j);

    public static native void bioClearByteBuffer(long j);

    public static native int bioFlushByteBuffer(long j);

    public static native int bioLengthByteBuffer(long j);

    public static native int bioLengthNonApplication(long j);

    public static native long bioNewByteBuffer(long j, int i);

    public static native void bioSetByteBuffer(long j, long j2, int i, boolean z);

    public static native int bioWrite(long j, long j2, int i);

    public static native void clearError();

    public static native void clearOptions(long j, int i);

    public static native int doHandshake(long j);

    public static native void enableOcsp(long j);

    public static native void fipsModeSet(int i) throws Exception;

    public static native void freeBIO(long j);

    public static native void freePrivateKey(long j);

    public static native void freeSSL(long j);

    public static native void freeX509Chain(long j);

    public static native String getAlpnSelected(long j);

    public static native String getCipherForSSL(long j);

    public static native String[] getCiphers(long j);

    public static native byte[] getClientRandom(long j);

    public static native int getError(long j, int i);

    public static native String getErrorString(long j);

    public static native int getHandshakeCount(long j);

    public static native String getLastError();

    public static native int getLastErrorNumber();

    public static native byte[] getMasterKey(long j);

    public static native int getMaxWrapOverhead(long j);

    public static native int getMode(long j);

    public static native String getNextProtoNegotiated(long j);

    public static native byte[] getOcspResponse(long j);

    public static native int getOptions(long j);

    public static native byte[][] getPeerCertChain(long j);

    public static native byte[] getPeerCertificate(long j);

    public static native byte[] getServerRandom(long j);

    public static native long getSession(long j);

    public static native byte[] getSessionId(long j);

    public static native int getShutdown(long j);

    public static native String[] getSigAlgs(long j);

    public static native String getSniHostname(long j);

    public static native Runnable getTask(long j);

    public static native long getTime(long j);

    public static native long getTimeout(long j);

    public static native String getVersion(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int initialize(String str);

    public static native int isInInit(long j);

    public static native boolean isSessionReused(long j);

    public static native long loadPrivateKeyFromEngine(String str, String str2) throws Exception;

    public static native long newMemBIO() throws Exception;

    public static native long newSSL(long j, boolean z);

    public static native long parsePrivateKey(long j, String str) throws Exception;

    public static native long parseX509Chain(long j) throws Exception;

    public static native int readFromSSL(long j, long j2, int i);

    @Deprecated
    public static native void setCertificateBio(long j, long j2, long j3, String str) throws Exception;

    @Deprecated
    public static native void setCertificateChainBio(long j, long j2, boolean z);

    public static native boolean setCipherSuites(long j, String str, boolean z) throws Exception;

    public static native void setHostNameValidation(long j, int i, String str);

    public static native void setKeyMaterial(long j, long j2, long j3) throws Exception;

    @Deprecated
    public static native void setKeyMaterialClientSide(long j, long j2, long j3, long j4, long j5) throws Exception;

    public static native int setMode(long j, int i);

    public static native void setOcspResponse(long j, byte[] bArr);

    public static native void setOptions(long j, int i);

    public static native boolean setSession(long j, long j2);

    public static native void setShutdown(long j, int i);

    public static native long setTimeout(long j, long j2);

    private static native void setTlsExtHostName0(long j, String str);

    public static native void setVerify(long j, int i, int i2);

    public static native int shutdownSSL(long j);

    public static native int sslPending(long j);

    public static native int version();

    public static native String versionString();

    public static native int writeToSSL(long j, long j2, int i);

    private SSL() {
    }

    @Deprecated
    public static boolean setCipherSuites(long j, String str) throws Exception {
        return setCipherSuites(j, str, false);
    }

    public static void setTlsExtHostName(long j, String str) {
        if (str != null && str.endsWith(".")) {
            str = str.substring(0, str.length() - 1);
        }
        setTlsExtHostName0(j, str);
    }

    @Deprecated
    public static void setKeyMaterialServerSide(long j, long j2, long j3) throws Exception {
        setKeyMaterial(j, j2, j3);
    }
}
