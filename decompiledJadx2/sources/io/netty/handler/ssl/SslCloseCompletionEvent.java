package io.netty.handler.ssl;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class SslCloseCompletionEvent extends SslCompletionEvent {
    public static final SslCloseCompletionEvent SUCCESS = new SslCloseCompletionEvent();

    private SslCloseCompletionEvent() {
    }

    public SslCloseCompletionEvent(Throwable th) {
        super(th);
    }
}
