package org.bouncycastle.crypto.tls;

import java.io.IOException;

/* loaded from: classes9.dex */
public class AbstractTlsCipherFactory implements TlsCipherFactory {
    @Override // org.bouncycastle.crypto.tls.TlsCipherFactory
    public TlsCipher createCipher(TlsContext tlsContext, int i, int i2) throws IOException {
        throw new TlsFatalAlert((short) 80);
    }
}
