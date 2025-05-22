package org.bouncycastle.util;

/* loaded from: classes9.dex */
public class StoreException extends RuntimeException {

    /* renamed from: _e */
    private Throwable f9966_e;

    public StoreException(String str, Throwable th) {
        super(str);
        this.f9966_e = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f9966_e;
    }
}
