package org.bouncycastle.openssl;

import java.io.IOException;

/* loaded from: classes9.dex */
public class PEMException extends IOException {
    Exception underlying;

    public PEMException(String str) {
        super(str);
    }

    public PEMException(String str, Exception exc) {
        super(str);
        this.underlying = exc;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.underlying;
    }

    public Exception getUnderlyingException() {
        return this.underlying;
    }
}
