package org.bouncycastle.crypto.tls;

import java.security.SecureRandom;
import org.bouncycastle.crypto.prng.RandomGenerator;

/* loaded from: classes9.dex */
public interface TlsContext {
    byte[] exportKeyingMaterial(String str, byte[] bArr, int i);

    ProtocolVersion getClientVersion();

    RandomGenerator getNonceRandomGenerator();

    TlsSession getResumableSession();

    SecureRandom getSecureRandom();

    SecurityParameters getSecurityParameters();

    ProtocolVersion getServerVersion();

    Object getUserObject();

    boolean isServer();

    void setUserObject(Object obj);
}
