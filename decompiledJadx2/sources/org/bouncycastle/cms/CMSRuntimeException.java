package org.bouncycastle.cms;

/* loaded from: classes9.dex */
public class CMSRuntimeException extends RuntimeException {

    /* renamed from: e */
    Exception f9130e;

    public CMSRuntimeException(String str) {
        super(str);
    }

    public CMSRuntimeException(String str, Exception exc) {
        super(str);
        this.f9130e = exc;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f9130e;
    }

    public Exception getUnderlyingException() {
        return this.f9130e;
    }
}
