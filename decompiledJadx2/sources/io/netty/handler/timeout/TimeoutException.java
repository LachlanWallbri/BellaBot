package io.netty.handler.timeout;

import io.netty.channel.ChannelException;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class TimeoutException extends ChannelException {
    private static final long serialVersionUID = 4673641882869672533L;

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        return this;
    }
}
