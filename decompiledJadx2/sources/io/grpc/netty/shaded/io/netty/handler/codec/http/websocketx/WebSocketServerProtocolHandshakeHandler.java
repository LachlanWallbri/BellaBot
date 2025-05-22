package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx;

import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter;
import io.grpc.netty.shaded.io.netty.channel.ChannelPipeline;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderNames;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMethod;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponseStatus;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpUtil;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpVersion;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.FutureListener;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.concurrent.ScheduledFuture;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
class WebSocketServerProtocolHandshakeHandler extends ChannelInboundHandlerAdapter {
    private ChannelHandlerContext ctx;
    private ChannelPromise handshakePromise;
    private final WebSocketServerProtocolConfig serverConfig;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WebSocketServerProtocolHandshakeHandler(WebSocketServerProtocolConfig webSocketServerProtocolConfig) {
        this.serverConfig = (WebSocketServerProtocolConfig) ObjectUtil.checkNotNull(webSocketServerProtocolConfig, "serverConfig");
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) {
        this.ctx = channelHandlerContext;
        this.handshakePromise = channelHandlerContext.newPromise();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelRead(final ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        final FullHttpRequest fullHttpRequest = (FullHttpRequest) obj;
        if (isNotWebSocketPath(fullHttpRequest)) {
            channelHandlerContext.fireChannelRead(obj);
            return;
        }
        try {
            if (!HttpMethod.GET.equals(fullHttpRequest.method())) {
                sendHttpResponse(channelHandlerContext, fullHttpRequest, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.FORBIDDEN, channelHandlerContext.alloc().buffer(0)));
                return;
            }
            final WebSocketServerHandshaker newHandshaker = new WebSocketServerHandshakerFactory(getWebSocketLocation(channelHandlerContext.pipeline(), fullHttpRequest, this.serverConfig.websocketPath()), this.serverConfig.subprotocols(), this.serverConfig.decoderConfig()).newHandshaker(fullHttpRequest);
            final ChannelPromise channelPromise = this.handshakePromise;
            if (newHandshaker == null) {
                WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(channelHandlerContext.channel());
            } else {
                WebSocketServerProtocolHandler.setHandshaker(channelHandlerContext.channel(), newHandshaker);
                channelHandlerContext.pipeline().remove(this);
                newHandshaker.handshake(channelHandlerContext.channel(), fullHttpRequest).addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandshakeHandler.1
                    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                    public void operationComplete(ChannelFuture channelFuture) {
                        if (!channelFuture.isSuccess()) {
                            channelPromise.tryFailure(channelFuture.cause());
                            channelHandlerContext.fireExceptionCaught(channelFuture.cause());
                        } else {
                            channelPromise.trySuccess();
                            channelHandlerContext.fireUserEventTriggered((Object) WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE);
                            channelHandlerContext.fireUserEventTriggered((Object) new WebSocketServerProtocolHandler.HandshakeComplete(fullHttpRequest.uri(), fullHttpRequest.headers(), newHandshaker.selectedSubprotocol()));
                        }
                    }
                });
                applyHandshakeTimeout();
            }
        } finally {
            fullHttpRequest.release();
        }
    }

    private boolean isNotWebSocketPath(FullHttpRequest fullHttpRequest) {
        String websocketPath = this.serverConfig.websocketPath();
        boolean checkStartsWith = this.serverConfig.checkStartsWith();
        String uri = fullHttpRequest.uri();
        if (checkStartsWith) {
            if (!uri.startsWith(websocketPath)) {
                return true;
            }
        } else if (!uri.equals(websocketPath)) {
            return true;
        }
        return false;
    }

    private static void sendHttpResponse(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest, HttpResponse httpResponse) {
        ChannelFuture writeAndFlush = channelHandlerContext.channel().writeAndFlush(httpResponse);
        if (HttpUtil.isKeepAlive(httpRequest) && httpResponse.status().code() == 200) {
            return;
        }
        writeAndFlush.addListener((GenericFutureListener<? extends Future<? super Void>>) ChannelFutureListener.CLOSE);
    }

    private static String getWebSocketLocation(ChannelPipeline channelPipeline, HttpRequest httpRequest, String str) {
        return (channelPipeline.get(SslHandler.class) != null ? "wss" : "ws") + "://" + httpRequest.headers().get(HttpHeaderNames.HOST) + str;
    }

    private void applyHandshakeTimeout() {
        final ChannelPromise channelPromise = this.handshakePromise;
        long handshakeTimeoutMillis = this.serverConfig.handshakeTimeoutMillis();
        if (handshakeTimeoutMillis <= 0 || channelPromise.isDone()) {
            return;
        }
        final ScheduledFuture<?> schedule = this.ctx.executor().schedule(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandshakeHandler.2
            @Override // java.lang.Runnable
            public void run() {
                if (channelPromise.isDone() || !channelPromise.tryFailure(new WebSocketHandshakeException("handshake timed out"))) {
                    return;
                }
                WebSocketServerProtocolHandshakeHandler.this.ctx.flush().fireUserEventTriggered((Object) WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_TIMEOUT).close();
            }
        }, handshakeTimeoutMillis, TimeUnit.MILLISECONDS);
        channelPromise.addListener((GenericFutureListener<? extends Future<? super Void>>) new FutureListener<Void>() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandshakeHandler.3
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(Future<Void> future) {
                schedule.cancel(false);
            }
        });
    }
}
