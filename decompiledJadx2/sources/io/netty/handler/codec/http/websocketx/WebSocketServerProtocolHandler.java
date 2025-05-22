package io.netty.handler.codec.http.websocketx;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class WebSocketServerProtocolHandler extends WebSocketProtocolHandler {
    private static final AttributeKey<WebSocketServerHandshaker> HANDSHAKER_ATTR_KEY = AttributeKey.valueOf(WebSocketServerHandshaker.class, "HANDSHAKER");
    private final boolean allowExtensions;
    private final boolean allowMaskMismatch;
    private final boolean checkStartsWith;
    private final int maxFramePayloadLength;
    private final String subprotocols;
    private final String websocketPath;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public enum ServerHandshakeStateEvent {
        HANDSHAKE_COMPLETE
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.http.websocketx.WebSocketProtocolHandler, io.netty.handler.codec.MessageToMessageDecoder
    public /* bridge */ /* synthetic */ void decode(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame, List list) throws Exception {
        decode(channelHandlerContext, webSocketFrame, (List<Object>) list);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public static final class HandshakeComplete {
        private final HttpHeaders requestHeaders;
        private final String requestUri;
        private final String selectedSubprotocol;

        /* JADX INFO: Access modifiers changed from: package-private */
        public HandshakeComplete(String str, HttpHeaders httpHeaders, String str2) {
            this.requestUri = str;
            this.requestHeaders = httpHeaders;
            this.selectedSubprotocol = str2;
        }

        public String requestUri() {
            return this.requestUri;
        }

        public HttpHeaders requestHeaders() {
            return this.requestHeaders;
        }

        public String selectedSubprotocol() {
            return this.selectedSubprotocol;
        }
    }

    public WebSocketServerProtocolHandler(String str) {
        this(str, null, false);
    }

    public WebSocketServerProtocolHandler(String str, boolean z) {
        this(str, null, false, 65536, false, z);
    }

    public WebSocketServerProtocolHandler(String str, String str2) {
        this(str, str2, false);
    }

    public WebSocketServerProtocolHandler(String str, String str2, boolean z) {
        this(str, str2, z, 65536);
    }

    public WebSocketServerProtocolHandler(String str, String str2, boolean z, int i) {
        this(str, str2, z, i, false);
    }

    public WebSocketServerProtocolHandler(String str, String str2, boolean z, int i, boolean z2) {
        this(str, str2, z, i, z2, false);
    }

    public WebSocketServerProtocolHandler(String str, String str2, boolean z, int i, boolean z2, boolean z3) {
        this.websocketPath = str;
        this.subprotocols = str2;
        this.allowExtensions = z;
        this.maxFramePayloadLength = i;
        this.allowMaskMismatch = z2;
        this.checkStartsWith = z3;
    }

    @Override // io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) {
        ChannelPipeline pipeline = channelHandlerContext.pipeline();
        if (pipeline.get(WebSocketServerProtocolHandshakeHandler.class) == null) {
            channelHandlerContext.pipeline().addBefore(channelHandlerContext.name(), WebSocketServerProtocolHandshakeHandler.class.getName(), new WebSocketServerProtocolHandshakeHandler(this.websocketPath, this.subprotocols, this.allowExtensions, this.maxFramePayloadLength, this.allowMaskMismatch, this.checkStartsWith));
        }
        if (pipeline.get(Utf8FrameValidator.class) == null) {
            channelHandlerContext.pipeline().addBefore(channelHandlerContext.name(), Utf8FrameValidator.class.getName(), new Utf8FrameValidator());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.netty.handler.codec.http.websocketx.WebSocketProtocolHandler
    public void decode(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame, List<Object> list) throws Exception {
        if (webSocketFrame instanceof CloseWebSocketFrame) {
            WebSocketServerHandshaker handshaker = getHandshaker(channelHandlerContext.channel());
            if (handshaker != null) {
                webSocketFrame.retain();
                handshaker.close(channelHandlerContext.channel(), (CloseWebSocketFrame) webSocketFrame);
                return;
            } else {
                channelHandlerContext.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener((GenericFutureListener<? extends Future<? super Void>>) ChannelFutureListener.CLOSE);
                return;
            }
        }
        super.decode(channelHandlerContext, webSocketFrame, list);
    }

    @Override // io.netty.handler.codec.http.websocketx.WebSocketProtocolHandler, io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler, io.netty.channel.ChannelInboundHandler
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
        if (th instanceof WebSocketHandshakeException) {
            channelHandlerContext.channel().writeAndFlush(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST, Unpooled.wrappedBuffer(th.getMessage().getBytes()))).addListener((GenericFutureListener<? extends Future<? super Void>>) ChannelFutureListener.CLOSE);
        } else {
            channelHandlerContext.fireExceptionCaught(th);
            channelHandlerContext.close();
        }
    }

    static WebSocketServerHandshaker getHandshaker(Channel channel) {
        return (WebSocketServerHandshaker) channel.attr(HANDSHAKER_ATTR_KEY).get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setHandshaker(Channel channel, WebSocketServerHandshaker webSocketServerHandshaker) {
        channel.attr(HANDSHAKER_ATTR_KEY).set(webSocketServerHandshaker);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ChannelHandler forbiddenHttpRequestResponder() {
        return new ChannelInboundHandlerAdapter() { // from class: io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler.1
            @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
            public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
                if (obj instanceof FullHttpRequest) {
                    ((FullHttpRequest) obj).release();
                    channelHandlerContext.channel().writeAndFlush(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.FORBIDDEN));
                    return;
                }
                channelHandlerContext.fireChannelRead(obj);
            }
        };
    }
}
