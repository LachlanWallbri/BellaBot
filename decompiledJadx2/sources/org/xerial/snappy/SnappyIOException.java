package org.xerial.snappy;

import java.io.IOException;

/* loaded from: classes9.dex */
public class SnappyIOException extends IOException {
    private final SnappyErrorCode errorCode;

    public SnappyIOException(SnappyErrorCode snappyErrorCode, String str) {
        super(str);
        this.errorCode = snappyErrorCode;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return String.format("[%s] %s", this.errorCode.name(), super.getMessage());
    }

    public SnappyErrorCode getErrorCode() {
        return this.errorCode;
    }
}
