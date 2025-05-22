package com.acs.smartcard;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DeviceProtocolErrorException extends ReaderException {
    private static final long serialVersionUID = 1;

    public DeviceProtocolErrorException() {
    }

    public DeviceProtocolErrorException(String str) {
        super(str);
    }

    public DeviceProtocolErrorException(Throwable th) {
        super(th);
    }

    public DeviceProtocolErrorException(String str, Throwable th) {
        super(str, th);
    }
}
