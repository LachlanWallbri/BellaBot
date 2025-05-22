package com.acs.smartcard;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class UnsupportedCardException extends ReaderException {
    private static final long serialVersionUID = 1;

    public UnsupportedCardException() {
    }

    public UnsupportedCardException(String str) {
        super(str);
    }

    public UnsupportedCardException(Throwable th) {
        super(th);
    }

    public UnsupportedCardException(String str, Throwable th) {
        super(str, th);
    }
}
