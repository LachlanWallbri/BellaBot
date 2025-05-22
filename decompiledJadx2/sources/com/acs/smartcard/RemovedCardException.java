package com.acs.smartcard;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class RemovedCardException extends ReaderException {
    private static final long serialVersionUID = 1;

    public RemovedCardException() {
    }

    public RemovedCardException(String str) {
        super(str);
    }

    public RemovedCardException(Throwable th) {
        super(th);
    }

    public RemovedCardException(String str, Throwable th) {
        super(str, th);
    }
}
