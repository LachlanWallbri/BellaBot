package org.bouncycastle.operator;

/* loaded from: classes9.dex */
public class OperatorException extends Exception {
    private Throwable cause;

    public OperatorException(String str) {
        super(str);
    }

    public OperatorException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }
}
