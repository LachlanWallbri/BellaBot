package io.netty.handler.ssl;

import io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public abstract class SslCompletionEvent {
    private final Throwable cause;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SslCompletionEvent() {
        this.cause = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SslCompletionEvent(Throwable th) {
        this.cause = (Throwable) ObjectUtil.checkNotNull(th, "cause");
    }

    public final boolean isSuccess() {
        return this.cause == null;
    }

    public final Throwable cause() {
        return this.cause;
    }

    public String toString() {
        Throwable cause = cause();
        if (cause == null) {
            return getClass().getSimpleName() + "(SUCCESS)";
        }
        return getClass().getSimpleName() + '(' + cause + ')';
    }
}
