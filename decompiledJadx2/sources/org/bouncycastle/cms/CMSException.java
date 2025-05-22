package org.bouncycastle.cms;

/* loaded from: classes9.dex */
public class CMSException extends Exception {

    /* renamed from: e */
    Exception f9129e;

    public CMSException(String str) {
        super(str);
    }

    public CMSException(String str, Exception exc) {
        super(str);
        this.f9129e = exc;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f9129e;
    }

    public Exception getUnderlyingException() {
        return this.f9129e;
    }
}
