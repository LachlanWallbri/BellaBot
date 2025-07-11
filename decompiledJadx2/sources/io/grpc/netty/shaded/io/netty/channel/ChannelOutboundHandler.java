package io.grpc.netty.shaded.io.netty.channel;

import java.net.SocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface ChannelOutboundHandler extends ChannelHandler {
    void bind(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, ChannelPromise channelPromise) throws Exception;

    void close(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception;

    void connect(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) throws Exception;

    void deregister(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception;

    void disconnect(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception;

    void flush(ChannelHandlerContext channelHandlerContext) throws Exception;

    void read(ChannelHandlerContext channelHandlerContext) throws Exception;

    void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception;
}
