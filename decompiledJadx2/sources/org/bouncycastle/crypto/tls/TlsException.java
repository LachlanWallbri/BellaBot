package org.bouncycastle.crypto.tls;

import java.io.IOException;

/* loaded from: classes9.dex */
public class TlsException extends IOException {
    protected Throwable cause;

    public TlsException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }
}
