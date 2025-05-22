package com.acs.smartcard;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DevicePowerFailureException extends ReaderException {
    private static final long serialVersionUID = 1;

    public DevicePowerFailureException() {
    }

    public DevicePowerFailureException(String str) {
        super(str);
    }

    public DevicePowerFailureException(Throwable th) {
        super(th);
    }

    public DevicePowerFailureException(String str, Throwable th) {
        super(str, th);
    }
}
