package com.acs.smartcard;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class CommunicationErrorException extends ReaderException {
    private static final long serialVersionUID = 1;

    /* renamed from: a */
    private boolean f147a;

    /* renamed from: b */
    private byte f148b;

    public CommunicationErrorException() {
    }

    public CommunicationErrorException(String str) {
        super(str);
    }

    public CommunicationErrorException(Throwable th) {
        super(th);
    }

    public CommunicationErrorException(String str, Throwable th) {
        super(str, th);
    }

    public boolean isCcidError() {
        return this.f147a;
    }

    public void setCcidError(boolean z) {
        this.f147a = z;
    }

    public byte getCcidErrorCode() {
        return this.f148b;
    }

    public void setCcidErrorCode(byte b) {
        this.f148b = b;
    }
}
