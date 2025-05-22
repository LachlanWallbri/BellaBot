package org.bouncycastle.util;

/* loaded from: classes9.dex */
public class StreamParsingException extends Exception {

    /* renamed from: _e */
    Throwable f9967_e;

    public StreamParsingException(String str, Throwable th) {
        super(str);
        this.f9967_e = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f9967_e;
    }
}
