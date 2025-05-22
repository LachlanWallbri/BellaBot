package com.acs.smartcard;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class UnresponsiveCardException extends ReaderException {
    private static final long serialVersionUID = 1;

    public UnresponsiveCardException() {
    }

    public UnresponsiveCardException(String str) {
        super(str);
    }

    public UnresponsiveCardException(Throwable th) {
        super(th);
    }

    public UnresponsiveCardException(String str, Throwable th) {
        super(str, th);
    }
}
