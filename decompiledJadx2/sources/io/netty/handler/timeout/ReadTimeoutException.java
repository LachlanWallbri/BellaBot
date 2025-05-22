package io.netty.handler.timeout;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class ReadTimeoutException extends TimeoutException {
    public static final ReadTimeoutException INSTANCE = new ReadTimeoutException();
    private static final long serialVersionUID = 169287984113283421L;

    private ReadTimeoutException() {
    }
}
