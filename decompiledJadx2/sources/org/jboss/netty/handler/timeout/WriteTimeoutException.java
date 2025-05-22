package org.jboss.netty.handler.timeout;

/* loaded from: classes7.dex */
public class WriteTimeoutException extends TimeoutException {
    private static final long serialVersionUID = -7746685254523245218L;

    public WriteTimeoutException() {
    }

    public WriteTimeoutException(String str, Throwable th) {
        super(str, th);
    }

    public WriteTimeoutException(String str) {
        super(str);
    }

    public WriteTimeoutException(Throwable th) {
        super(th);
    }
}
