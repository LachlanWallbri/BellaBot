package org.bouncycastle.crypto.tls;

import java.io.OutputStream;

/* loaded from: classes9.dex */
public class TlsNullCompression implements TlsCompression {
    @Override // org.bouncycastle.crypto.tls.TlsCompression
    public OutputStream compress(OutputStream outputStream) {
        return outputStream;
    }

    @Override // org.bouncycastle.crypto.tls.TlsCompression
    public OutputStream decompress(OutputStream outputStream) {
        return outputStream;
    }
}
