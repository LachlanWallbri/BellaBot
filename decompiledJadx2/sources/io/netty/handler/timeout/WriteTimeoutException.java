package io.netty.handler.timeout;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class WriteTimeoutException extends TimeoutException {
    public static final WriteTimeoutException INSTANCE = new WriteTimeoutException();
    private static final long serialVersionUID = -144786655770296065L;

    private WriteTimeoutException() {
    }
}
