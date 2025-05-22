package org.bouncycastle.crypto.tls;

import java.security.SecureRandom;

/* loaded from: classes9.dex */
class TlsClientContextImpl extends AbstractTlsContext implements TlsClientContext {
    /* JADX INFO: Access modifiers changed from: package-private */
    public TlsClientContextImpl(SecureRandom secureRandom, SecurityParameters securityParameters) {
        super(secureRandom, securityParameters);
    }

    @Override // org.bouncycastle.crypto.tls.TlsContext
    public boolean isServer() {
        return false;
    }
}
