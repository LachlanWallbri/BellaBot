package io.grpc.netty.shaded.io.netty.channel;

import java.nio.channels.ClosedChannelException;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
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
