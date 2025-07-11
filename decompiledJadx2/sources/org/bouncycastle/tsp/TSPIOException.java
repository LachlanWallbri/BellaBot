package org.bouncycastle.tsp;

import java.io.IOException;

/* loaded from: classes9.dex */
public class TSPIOException extends IOException {
    Throwable underlyingException;

    public TSPIOException(String str) {
        super(str);
    }

    public TSPIOException(String str, Throwable th) {
        super(str);
        this.underlyingException = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.underlyingException;
    }

    public Exception getUnderlyingException() {
        return (Exception) this.underlyingException;
    }
}
