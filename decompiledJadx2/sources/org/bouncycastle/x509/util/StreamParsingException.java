package org.bouncycastle.x509.util;

/* loaded from: classes9.dex */
public class StreamParsingException extends Exception {

    /* renamed from: _e */
    Throwable f9969_e;

    public StreamParsingException(String str, Throwable th) {
        super(str);
        this.f9969_e = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f9969_e;
    }
}
