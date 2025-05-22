package com.acs.smartcard;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class InsufficientBufferException extends ReaderException {
    private static final long serialVersionUID = 1;

    public InsufficientBufferException() {
    }

    public InsufficientBufferException(String str) {
        super(str);
    }

    public InsufficientBufferException(Throwable th) {
        super(th);
    }

    public InsufficientBufferException(String str, Throwable th) {
        super(str, th);
    }
}
