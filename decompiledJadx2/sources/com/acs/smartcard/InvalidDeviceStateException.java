package com.acs.smartcard;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class InvalidDeviceStateException extends ReaderException {
    private static final long serialVersionUID = 1;

    public InvalidDeviceStateException() {
    }

    public InvalidDeviceStateException(String str) {
        super(str);
    }

    public InvalidDeviceStateException(Throwable th) {
        super(th);
    }

    public InvalidDeviceStateException(String str, Throwable th) {
        super(str, th);
    }
}
