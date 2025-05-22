package org.bouncycastle.crypto.tls;

/* loaded from: classes9.dex */
public abstract class ServerOnlyTlsAuthentication implements TlsAuthentication {
    @Override // org.bouncycastle.crypto.tls.TlsAuthentication
    public final TlsCredentials getClientCredentials(CertificateRequest certificateRequest) {
        return null;
    }
}
