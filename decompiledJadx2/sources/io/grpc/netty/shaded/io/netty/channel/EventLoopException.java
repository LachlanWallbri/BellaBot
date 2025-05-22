package io.grpc.netty.shaded.io.netty.channel;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class EventLoopException extends ChannelException {
    private static final long serialVersionUID = -8969100344583703616L;

    public EventLoopException() {
    }

    public EventLoopException(String str, Throwable th) {
        super(str, th);
    }

    public EventLoopException(String str) {
        super(str);
    }

    public EventLoopException(Throwable th) {
        super(th);
    }
}
