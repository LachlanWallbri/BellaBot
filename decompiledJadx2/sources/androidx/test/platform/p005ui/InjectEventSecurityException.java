package androidx.test.platform.p005ui;

import androidx.test.platform.TestFrameworkException;

/* loaded from: classes.dex */
public class InjectEventSecurityException extends Exception implements TestFrameworkException {
    public InjectEventSecurityException(String message) {
        super(message);
    }

    public InjectEventSecurityException(Throwable cause) {
        super(cause);
    }

    public InjectEventSecurityException(String message, Throwable cause) {
        super(message, cause);
    }
}
