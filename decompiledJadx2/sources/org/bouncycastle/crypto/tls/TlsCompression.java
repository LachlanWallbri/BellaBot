package org.bouncycastle.crypto.tls;

import java.io.OutputStream;

/* loaded from: classes9.dex */
public interface TlsCompression {
    OutputStream compress(OutputStream outputStream);

    OutputStream decompress(OutputStream outputStream);
}
