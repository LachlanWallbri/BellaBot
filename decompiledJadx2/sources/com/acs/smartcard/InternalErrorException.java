package com.acs.smartcard;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class InternalErrorException extends ReaderException {
    private static final long serialVersionUID = 1;

    public InternalErrorException() {
    }

    public InternalErrorException(String str) {
        super(str);
    }

    public InternalErrorException(Throwable th) {
        super(th);
    }

    public InternalErrorException(String str, Throwable th) {
        super(str, th);
    }
}
