package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface ChannelFutureListener extends GenericFutureListener<ChannelFuture> {
    public static final ChannelFutureListener CLOSE = new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener.1
        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
        public void operationComplete(ChannelFuture channelFuture) {
            channelFuture.channel().close();
        }
    };
    public static final ChannelFutureListener CLOSE_ON_FAILURE = new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener.2
        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
        public void operationComplete(ChannelFuture channelFuture) {
            if (channelFuture.isSuccess()) {
                return;
            }
            channelFuture.channel().close();
        }
    };
    public static final ChannelFutureListener FIRE_EXCEPTION_ON_FAILURE = new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener.3
        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
        public void operationComplete(ChannelFuture channelFuture) {
            if (channelFuture.isSuccess()) {
                return;
            }
            channelFuture.channel().pipeline().fireExceptionCaught(channelFuture.cause());
        }
    };
}
