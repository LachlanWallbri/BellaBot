package io.grpc.netty.shaded.io.netty.handler.timeout;

import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class ReadTimeoutHandler extends IdleStateHandler {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private boolean closed;

    public ReadTimeoutHandler(int i) {
        this(i, TimeUnit.SECONDS);
    }

    public ReadTimeoutHandler(long j, TimeUnit timeUnit) {
        super(j, 0L, 0L, timeUnit);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.timeout.IdleStateHandler
    protected final void channelIdle(ChannelHandlerContext channelHandlerContext, IdleStateEvent idleStateEvent) throws Exception {
        readTimedOut(channelHandlerContext);
    }

    protected void readTimedOut(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.closed) {
            return;
        }
        channelHandlerContext.fireExceptionCaught((Throwable) ReadTimeoutException.INSTANCE);
        channelHandlerContext.close();
        this.closed = true;
    }
}
