package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerMask;
import io.grpc.netty.shaded.io.netty.util.internal.InternalThreadLocalMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class ChannelHandlerAdapter implements ChannelHandler {
    boolean added;

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void ensureNotSharable() {
        if (isSharable()) {
            throw new IllegalStateException("ChannelHandler " + getClass().getName() + " is not allowed to be shared");
        }
    }

    public boolean isSharable() {
        Class<?> cls = getClass();
        Map<Class<?>, Boolean> handlerSharableCache = InternalThreadLocalMap.get().handlerSharableCache();
        Boolean bool = handlerSharableCache.get(cls);
        if (bool == null) {
            bool = Boolean.valueOf(cls.isAnnotationPresent(ChannelHandler.Sharable.class));
            handlerSharableCache.put(cls, bool);
        }
        return bool.booleanValue();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandler, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    @ChannelHandlerMask.Skip
    @Deprecated
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
        channelHandlerContext.fireExceptionCaught(th);
    }
}
