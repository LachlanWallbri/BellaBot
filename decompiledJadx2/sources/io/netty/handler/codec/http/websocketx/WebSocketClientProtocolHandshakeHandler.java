package io.netty.handler.codec.http.websocketx;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.concurrent.GenericFutureListener;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
class WebSocketClientProtocolHandshakeHandler extends ChannelInboundHandlerAdapter {
    private final WebSocketClientHandshaker handshaker;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WebSocketClientProtocolHandshakeHandler(WebSocketClientHandshaker webSocketClientHandshaker) {
        this.handshaker = webSocketClientHandshaker;
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelActive(final ChannelHandlerContext channelHandlerContext) throws Exception {
        super.channelActive(channelHandlerContext);
        this.handshaker.handshake(channelHandlerContext.channel()).addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandshakeHandler.1
            @Override // io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (!channelFuture.isSuccess()) {
                    channelHandlerContext.fireExceptionCaught(channelFuture.cause());
                } else {
                    channelHandlerContext.fireUserEventTriggered((Object) WebSocketClientProtocolHandler.ClientHandshakeStateEvent.HANDSHAKE_ISSUED);
                }
            }
        });
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (!(obj instanceof FullHttpResponse)) {
            channelHandlerContext.fireChannelRead(obj);
            return;
        }
        FullHttpResponse fullHttpResponse = (FullHttpResponse) obj;
        try {
            if (!this.handshaker.isHandshakeComplete()) {
                this.handshaker.finishHandshake(channelHandlerContext.channel(), fullHttpResponse);
                channelHandlerContext.fireUserEventTriggered((Object) WebSocketClientProtocolHandler.ClientHandshakeStateEvent.HANDSHAKE_COMPLETE);
                channelHandlerContext.pipeline().remove(this);
                return;
            }
            throw new IllegalStateException("WebSocketClientHandshaker should have been non finished yet");
        } finally {
            fullHttpResponse.release();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* renamed from: io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandshakeHandler$2 */
    /* loaded from: classes8.dex */
    class RunnableC71632 implements Runnable {
        final /* synthetic */ ChannelPromise val$localHandshakePromise;

        RunnableC71632(ChannelPromise channelPromise) {
            this.val$localHandshakePromise = channelPromise;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.val$localHandshakePromise.isDone() && this.val$localHandshakePromise.tryFailure(new WebSocketHandshakeException("handshake timed out"))) {
                WebSocketClientProtocolHandshakeHandler.access$100(WebSocketClientProtocolHandshakeHandler.this).flush().fireUserEventTriggered((Object) WebSocketClientProtocolHandler.ClientHandshakeStateEvent.HANDSHAKE_TIMEOUT).close();
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* renamed from: io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandshakeHandler$3 */
    /* loaded from: classes8.dex */
    class C71643 implements FutureListener<Void> {
        final /* synthetic */ Future val$timeoutFuture;

        C71643(Future future) {
            this.val$timeoutFuture = future;
        }

        @Override // io.netty.util.concurrent.GenericFutureListener
        public void operationComplete(Future<Void> future) throws Exception {
            this.val$timeoutFuture.cancel(false);
        }
    }
}
