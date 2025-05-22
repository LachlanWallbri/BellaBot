package com.acs.smartcard;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class UnpoweredCardException extends ReaderException {
    private static final long serialVersionUID = 1;

    public UnpoweredCardException() {
    }

    public UnpoweredCardException(String str) {
        super(str);
    }

    public UnpoweredCardException(Throwable th) {
        super(th);
    }

    public UnpoweredCardException(String str, Throwable th) {
        super(str, th);
    }
}
