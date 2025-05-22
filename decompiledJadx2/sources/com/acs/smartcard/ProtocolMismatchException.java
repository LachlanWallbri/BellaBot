package com.acs.smartcard;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ProtocolMismatchException extends ReaderException {
    private static final long serialVersionUID = 1;

    public ProtocolMismatchException() {
    }

    public ProtocolMismatchException(String str) {
        super(str);
    }

    public ProtocolMismatchException(Throwable th) {
        super(th);
    }

    public ProtocolMismatchException(String str, Throwable th) {
        super(str, th);
    }
}
