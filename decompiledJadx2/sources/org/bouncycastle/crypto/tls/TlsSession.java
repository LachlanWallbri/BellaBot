package org.bouncycastle.crypto.tls;

/* loaded from: classes9.dex */
public interface TlsSession {
    SessionParameters exportSessionParameters();

    byte[] getSessionID();

    void invalidate();

    boolean isResumable();
}
