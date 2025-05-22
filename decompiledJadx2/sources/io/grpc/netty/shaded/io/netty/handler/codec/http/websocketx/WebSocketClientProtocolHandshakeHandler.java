package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx;

import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.FutureListener;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.concurrent.ScheduledFuture;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class WebSocketClientProtocolHandshakeHandler extends ChannelInboundHandlerAdapter {
    private static final long DEFAULT_HANDSHAKE_TIMEOUT_MS = 10000;
    private ChannelHandlerContext ctx;
    private ChannelPromise handshakePromise;
    private final long handshakeTimeoutMillis;
    private final WebSocketClientHandshaker handshaker;

    WebSocketClientProtocolHandshakeHandler(WebSocketClientHandshaker webSocketClientHandshaker) {
        this(webSocketClientHandshaker, DEFAULT_HANDSHAKE_TIMEOUT_MS);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WebSocketClientProtocolHandshakeHandler(WebSocketClientHandshaker webSocketClientHandshaker, long j) {
        this.handshaker = webSocketClientHandshaker;
        this.handshakeTimeoutMillis = ObjectUtil.checkPositive(j, "handshakeTimeoutMillis");
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.ctx = channelHandlerContext;
        this.handshakePromise = channelHandlerContext.newPromise();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelActive(final ChannelHandlerContext channelHandlerContext) throws Exception {
        super.channelActive(channelHandlerContext);
        this.handshaker.handshake(channelHandlerContext.channel()).addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandshakeHandler.1
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (!channelFuture.isSuccess()) {
                    WebSocketClientProtocolHandshakeHandler.this.handshakePromise.tryFailure(channelFuture.cause());
                    channelHandlerContext.fireExceptionCaught(channelFuture.cause());
                } else {
                    channelHandlerContext.fireUserEventTriggered((Object) WebSocketClientProtocolHandler.ClientHandshakeStateEvent.HANDSHAKE_ISSUED);
                }
            }
        });
        applyHandshakeTimeout();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (!(obj instanceof FullHttpResponse)) {
            channelHandlerContext.fireChannelRead(obj);
            return;
        }
        FullHttpResponse fullHttpResponse = (FullHttpResponse) obj;
        try {
            if (!this.handshaker.isHandshakeComplete()) {
                this.handshaker.finishHandshake(channelHandlerContext.channel(), fullHttpResponse);
                this.handshakePromise.trySuccess();
                channelHandlerContext.fireUserEventTriggered((Object) WebSocketClientProtocolHandler.ClientHandshakeStateEvent.HANDSHAKE_COMPLETE);
                channelHandlerContext.pipeline().remove(this);
                return;
            }
            throw new IllegalStateException("WebSocketClientHandshaker should have been non finished yet");
        } finally {
            fullHttpResponse.release();
        }
    }

    private void applyHandshakeTimeout() {
        final ChannelPromise channelPromise = this.handshakePromise;
        if (this.handshakeTimeoutMillis <= 0 || channelPromise.isDone()) {
            return;
        }
        final ScheduledFuture<?> schedule = this.ctx.executor().schedule(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandshakeHandler.2
            @Override // java.lang.Runnable
            public void run() {
                if (!channelPromise.isDone() && channelPromise.tryFailure(new WebSocketHandshakeException("handshake timed out"))) {
                    WebSocketClientProtocolHandshakeHandler.this.ctx.flush().fireUserEventTriggered((Object) WebSocketClientProtocolHandler.ClientHandshakeStateEvent.HANDSHAKE_TIMEOUT).close();
                }
            }
        }, this.handshakeTimeoutMillis, TimeUnit.MILLISECONDS);
        channelPromise.addListener((GenericFutureListener<? extends Future<? super Void>>) new FutureListener<Void>() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandshakeHandler.3
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(Future<Void> future) throws Exception {
                schedule.cancel(false);
            }
        });
    }

    ChannelFuture getHandshakeFuture() {
        return this.handshakePromise;
    }
}
