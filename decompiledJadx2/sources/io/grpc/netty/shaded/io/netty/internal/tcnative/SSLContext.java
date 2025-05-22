package io.grpc.netty.shaded.io.netty.internal.tcnative;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class SSLContext {
    public static native void clearOptions(long j, int i);

    public static native void disableOcsp(long j);

    public static native void enableOcsp(long j, boolean z);

    public static native int free(long j);

    public static native int getMode(long j);

    public static native int getOptions(long j);

    public static native long getSessionCacheMode(long j);

    public static native long getSessionCacheSize(long j);

    public static native long getSessionCacheTimeout(long j);

    public static native long getSslCtx(long j);

    public static native long make(int i, int i2) throws Exception;

    public static native long sessionAccept(long j);

    public static native long sessionAcceptGood(long j);

    public static native long sessionAcceptRenegotiate(long j);

    public static native long sessionCacheFull(long j);

    public static native long sessionCbHits(long j);

    public static native long sessionConnect(long j);

    public static native long sessionConnectGood(long j);

    public static native long sessionConnectRenegotiate(long j);

    public static native long sessionHits(long j);

    public static native long sessionMisses(long j);

    public static native long sessionNumber(long j);

    public static native long sessionTicketKeyFail(long j);

    public static native long sessionTicketKeyNew(long j);

    public static native long sessionTicketKeyRenew(long j);

    public static native long sessionTicketKeyResume(long j);

    public static native long sessionTimeouts(long j);

    public static native void setAlpnProtos(long j, String[] strArr, int i);

    public static native boolean setCACertificateBio(long j, long j2);

    @Deprecated
    public static native void setCertRequestedCallback(long j, CertificateRequestedCallback certificateRequestedCallback);

    public static native void setCertVerifyCallback(long j, CertificateVerifier certificateVerifier);

    public static native boolean setCertificate(long j, String str, String str2, String str3) throws Exception;

    public static native boolean setCertificateBio(long j, long j2, long j3, String str) throws Exception;

    public static native void setCertificateCallback(long j, CertificateCallback certificateCallback);

    public static native boolean setCertificateChainBio(long j, long j2, boolean z);

    public static native boolean setCertificateChainFile(long j, String str, boolean z);

    public static native boolean setCipherSuite(long j, String str, boolean z) throws Exception;

    public static native void setContextId(long j, String str);

    public static native int setMode(long j, int i);

    public static native void setNpnProtos(long j, String[] strArr, int i);

    public static native boolean setNumTickets(long j, int i);

    public static native void setOptions(long j, int i);

    public static native void setPrivateKeyMethod(long j, SSLPrivateKeyMethod sSLPrivateKeyMethod);

    public static native void setSSLSessionCache(long j, SSLSessionCache sSLSessionCache);

    public static native long setSessionCacheMode(long j, long j2);

    public static native long setSessionCacheSize(long j, long j2);

    public static native long setSessionCacheTimeout(long j, long j2);

    public static native boolean setSessionIdContext(long j, byte[] bArr);

    private static native void setSessionTicketKeys0(long j, byte[] bArr);

    public static native void setSniHostnameMatcher(long j, SniHostNameMatcher sniHostNameMatcher);

    public static native void setTmpDHLength(long j, int i);

    public static native void setUseTasks(long j, boolean z);

    public static native void setVerify(long j, int i, int i2);

    private SSLContext() {
    }

    @Deprecated
    public static boolean setCipherSuite(long j, String str) throws Exception {
        return setCipherSuite(j, str, false);
    }

    public static void setSessionTicketKeys(long j, SessionTicketKey[] sessionTicketKeyArr) {
        if (sessionTicketKeyArr == null || sessionTicketKeyArr.length == 0) {
            throw new IllegalArgumentException("Length of the keys should be longer than 0.");
        }
        byte[] bArr = new byte[sessionTicketKeyArr.length * 48];
        for (int i = 0; i < sessionTicketKeyArr.length; i++) {
            SessionTicketKey sessionTicketKey = sessionTicketKeyArr[i];
            int i2 = i * 48;
            System.arraycopy(sessionTicketKey.name, 0, bArr, i2, 16);
            int i3 = i2 + 16;
            System.arraycopy(sessionTicketKey.hmacKey, 0, bArr, i3, 16);
            System.arraycopy(sessionTicketKey.aesKey, 0, bArr, i3 + 16, 16);
        }
        setSessionTicketKeys0(j, bArr);
    }
}
