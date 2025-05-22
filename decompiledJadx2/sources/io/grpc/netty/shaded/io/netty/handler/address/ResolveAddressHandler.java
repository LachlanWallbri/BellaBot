package io.grpc.netty.shaded.io.netty.handler.address;

import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandlerAdapter;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.resolver.AddressResolver;
import io.grpc.netty.shaded.io.netty.resolver.AddressResolverGroup;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.FutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.net.SocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@ChannelHandler.Sharable
/* loaded from: classes7.dex */
public class ResolveAddressHandler extends ChannelOutboundHandlerAdapter {
    private final AddressResolverGroup<? extends SocketAddress> resolverGroup;

    public ResolveAddressHandler(AddressResolverGroup<? extends SocketAddress> addressResolverGroup) {
        this.resolverGroup = (AddressResolverGroup) ObjectUtil.checkNotNull(addressResolverGroup, "resolverGroup");
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void connect(final ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, final SocketAddress socketAddress2, final ChannelPromise channelPromise) {
        AddressResolver<? extends SocketAddress> resolver = this.resolverGroup.getResolver(channelHandlerContext.executor());
        if (resolver.isSupported(socketAddress) && !resolver.isResolved(socketAddress)) {
            resolver.resolve(socketAddress).addListener(new FutureListener<SocketAddress>() { // from class: io.grpc.netty.shaded.io.netty.handler.address.ResolveAddressHandler.1
                @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(Future<SocketAddress> future) {
                    Throwable cause = future.cause();
                    if (cause != null) {
                        channelPromise.setFailure(cause);
                    } else {
                        channelHandlerContext.connect(future.getNow(), socketAddress2, channelPromise);
                    }
                    channelHandlerContext.pipeline().remove(ResolveAddressHandler.this);
                }
            });
        } else {
            channelHandlerContext.connect(socketAddress, socketAddress2, channelPromise);
            channelHandlerContext.pipeline().remove(this);
        }
    }
}
