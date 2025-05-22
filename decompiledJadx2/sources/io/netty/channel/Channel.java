package io.netty.channel;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.util.AttributeMap;
import java.net.SocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public interface Channel extends AttributeMap, ChannelOutboundInvoker, Comparable<Channel> {

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* loaded from: classes.dex */
    public interface Unsafe {
        void beginRead();

        void bind(SocketAddress socketAddress, ChannelPromise channelPromise);

        void close(ChannelPromise channelPromise);

        void closeForcibly();

        void connect(SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise);

        void deregister(ChannelPromise channelPromise);

        void disconnect(ChannelPromise channelPromise);

        void flush();

        SocketAddress localAddress();

        ChannelOutboundBuffer outboundBuffer();

        RecvByteBufAllocator.Handle recvBufAllocHandle();

        void register(EventLoop eventLoop, ChannelPromise channelPromise);

        SocketAddress remoteAddress();

        ChannelPromise voidPromise();

        void write(Object obj, ChannelPromise channelPromise);
    }

    ByteBufAllocator alloc();

    long bytesBeforeUnwritable();

    long bytesBeforeWritable();

    ChannelFuture closeFuture();

    ChannelConfig config();

    EventLoop eventLoop();

    @Override // io.netty.channel.ChannelOutboundInvoker
    Channel flush();

    /* renamed from: id */
    ChannelId mo3929id();

    boolean isActive();

    boolean isOpen();

    boolean isRegistered();

    boolean isWritable();

    SocketAddress localAddress();

    ChannelMetadata metadata();

    Channel parent();

    ChannelPipeline pipeline();

    @Override // io.netty.channel.ChannelOutboundInvoker
    Channel read();

    SocketAddress remoteAddress();

    Unsafe unsafe();
}
