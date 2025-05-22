package io.grpc.netty.shaded.io.netty.handler.ssl;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class SniCompletionEvent extends SslCompletionEvent {
    private final String hostname;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SniCompletionEvent(String str) {
        this.hostname = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SniCompletionEvent(String str, Throwable th) {
        super(th);
        this.hostname = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SniCompletionEvent(Throwable th) {
        this(null, th);
    }

    public String hostname() {
        return this.hostname;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.SslCompletionEvent
    public String toString() {
        Throwable cause = cause();
        if (cause == null) {
            return getClass().getSimpleName() + "(SUCCESS='" + this.hostname + "'\")";
        }
        return getClass().getSimpleName() + '(' + cause + ')';
    }
}
