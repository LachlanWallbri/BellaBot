package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.util.Attribute;
import io.grpc.netty.shaded.io.netty.util.AttributeKey;
import io.grpc.netty.shaded.io.netty.util.AttributeMap;
import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface ChannelHandlerContext extends AttributeMap, ChannelInboundInvoker, ChannelOutboundInvoker {
    ByteBufAllocator alloc();

    @Override // io.grpc.netty.shaded.io.netty.util.AttributeMap
    @Deprecated
    <T> Attribute<T> attr(AttributeKey<T> attributeKey);

    Channel channel();

    EventExecutor executor();

    ChannelHandlerContext fireChannelActive();

    ChannelHandlerContext fireChannelInactive();

    ChannelHandlerContext fireChannelRead(Object obj);

    ChannelHandlerContext fireChannelReadComplete();

    ChannelHandlerContext fireChannelRegistered();

    ChannelHandlerContext fireChannelUnregistered();

    ChannelHandlerContext fireChannelWritabilityChanged();

    ChannelHandlerContext fireExceptionCaught(Throwable th);

    ChannelHandlerContext fireUserEventTriggered(Object obj);

    ChannelHandlerContext flush();

    ChannelHandler handler();

    @Override // io.grpc.netty.shaded.io.netty.util.AttributeMap
    @Deprecated
    <T> boolean hasAttr(AttributeKey<T> attributeKey);

    boolean isRemoved();

    String name();

    ChannelPipeline pipeline();

    ChannelHandlerContext read();
}
