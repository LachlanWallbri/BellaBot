package com.acs.smartcard;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class BufferOverflowException extends ReaderException {
    private static final long serialVersionUID = 1;

    public BufferOverflowException() {
    }

    public BufferOverflowException(String str) {
        super(str);
    }

    public BufferOverflowException(Throwable th) {
        super(th);
    }

    public BufferOverflowException(String str, Throwable th) {
        super(str, th);
    }
}
