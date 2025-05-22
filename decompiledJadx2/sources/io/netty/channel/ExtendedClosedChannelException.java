package io.netty.channel;

import java.nio.channels.ClosedChannelException;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes5.dex */
final class ExtendedClosedChannelException extends ClosedChannelException {
    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        return this;
    }

    ExtendedClosedChannelException(Throwable th) {
        if (th != null) {
            initCause(th);
        }
    }
}
