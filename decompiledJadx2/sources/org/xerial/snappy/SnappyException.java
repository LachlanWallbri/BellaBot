package org.xerial.snappy;

@Deprecated
/* loaded from: classes9.dex */
public class SnappyException extends Exception {
    private static final long serialVersionUID = 1;
    public final SnappyErrorCode errorCode;

    public SnappyException(int i) {
        this(SnappyErrorCode.getErrorCode(i));
    }

    public SnappyException(SnappyErrorCode snappyErrorCode) {
        this.errorCode = snappyErrorCode;
    }

    public SnappyException(SnappyErrorCode snappyErrorCode, Exception exc) {
        super(exc);
        this.errorCode = snappyErrorCode;
    }

    public SnappyException(SnappyErrorCode snappyErrorCode, String str) {
        super(str);
        this.errorCode = snappyErrorCode;
    }

    public SnappyErrorCode getErrorCode() {
        return this.errorCode;
    }

    public static void throwException(int i) throws SnappyException {
        throw new SnappyException(i);
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return String.format("[%s] %s", this.errorCode.name(), super.getMessage());
    }
}
