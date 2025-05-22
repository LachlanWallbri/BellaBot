package io.netty.handler.codec.http.websocketx;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.concurrent.GenericFutureListener;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
class WebSocketServerProtocolHandshakeHandler extends ChannelInboundHandlerAdapter {
    private final boolean allowExtensions;
    private final boolean allowMaskMismatch;
    private final boolean checkStartsWith;
    private final int maxFramePayloadSize;
    private final String subprotocols;
    private final String websocketPath;

    WebSocketServerProtocolHandshakeHandler(String str, String str2, boolean z, int i, boolean z2) {
        this(str, str2, z, i, z2, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WebSocketServerProtocolHandshakeHandler(String str, String str2, boolean z, int i, boolean z2, boolean z3) {
        this.websocketPath = str;
        this.subprotocols = str2;
        this.allowExtensions = z;
        this.maxFramePayloadSize = i;
        this.allowMaskMismatch = z2;
        this.checkStartsWith = z3;
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelRead(final ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        final FullHttpRequest fullHttpRequest = (FullHttpRequest) obj;
        if (isNotWebSocketPath(fullHttpRequest)) {
            channelHandlerContext.fireChannelRead(obj);
            return;
        }
        try {
            if (fullHttpRequest.method() != HttpMethod.GET) {
                sendHttpResponse(channelHandlerContext, fullHttpRequest, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.FORBIDDEN));
                return;
            }
            final WebSocketServerHandshaker newHandshaker = new WebSocketServerHandshakerFactory(getWebSocketLocation(channelHandlerContext.pipeline(), fullHttpRequest, this.websocketPath), this.subprotocols, this.allowExtensions, this.maxFramePayloadSize, this.allowMaskMismatch).newHandshaker(fullHttpRequest);
            if (newHandshaker == null) {
                WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(channelHandlerContext.channel());
            } else {
                newHandshaker.handshake(channelHandlerContext.channel(), fullHttpRequest).addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandshakeHandler.1
                    @Override // io.netty.util.concurrent.GenericFutureListener
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        if (!channelFuture.isSuccess()) {
                            channelHandlerContext.fireExceptionCaught(channelFuture.cause());
                        } else {
                            channelHandlerContext.fireUserEventTriggered((Object) WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE);
                            channelHandlerContext.fireUserEventTriggered((Object) new WebSocketServerProtocolHandler.HandshakeComplete(fullHttpRequest.uri(), fullHttpRequest.headers(), newHandshaker.selectedSubprotocol()));
                        }
                    }
                });
                WebSocketServerProtocolHandler.setHandshaker(channelHandlerContext.channel(), newHandshaker);
                channelHandlerContext.pipeline().replace(this, "WS403Responder", WebSocketServerProtocolHandler.forbiddenHttpRequestResponder());
            }
        } finally {
            fullHttpRequest.release();
        }
    }

    private boolean isNotWebSocketPath(FullHttpRequest fullHttpRequest) {
        boolean z = this.checkStartsWith;
        String uri = fullHttpRequest.uri();
        if (z) {
            if (!uri.startsWith(this.websocketPath)) {
                return true;
            }
        } else if (!uri.equals(this.websocketPath)) {
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

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* renamed from: io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandshakeHandler$2 */
    /* loaded from: classes8.dex */
    class RunnableC71702 implements Runnable {
        final /* synthetic */ ChannelPromise val$localHandshakePromise;

        RunnableC71702(ChannelPromise channelPromise) {
            this.val$localHandshakePromise = channelPromise;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.val$localHandshakePromise.isDone() || !this.val$localHandshakePromise.tryFailure(new WebSocketHandshakeException("handshake timed out"))) {
                return;
            }
            WebSocketServerProtocolHandshakeHandler.access$000(WebSocketServerProtocolHandshakeHandler.this).flush().fireUserEventTriggered((Object) WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_TIMEOUT).close();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* renamed from: io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandshakeHandler$3 */
    /* loaded from: classes8.dex */
    class C71713 implements FutureListener<Void> {
        final /* synthetic */ Future val$timeoutFuture;

        C71713(Future future) {
            this.val$timeoutFuture = future;
        }

        @Override // io.netty.util.concurrent.GenericFutureListener
        public void operationComplete(Future<Void> future) throws Exception {
            this.val$timeoutFuture.cancel(false);
        }
    }
}
