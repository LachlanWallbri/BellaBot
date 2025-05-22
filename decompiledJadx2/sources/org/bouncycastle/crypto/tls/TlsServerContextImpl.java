package org.bouncycastle.crypto.tls;

import java.security.SecureRandom;

/* loaded from: classes9.dex */
class TlsServerContextImpl extends AbstractTlsContext implements TlsServerContext {
    /* JADX INFO: Access modifiers changed from: package-private */
    public TlsServerContextImpl(SecureRandom secureRandom, SecurityParameters securityParameters) {
        super(secureRandom, securityParameters);
    }

    @Override // org.bouncycastle.crypto.tls.TlsContext
    public boolean isServer() {
        return true;
    }
}
