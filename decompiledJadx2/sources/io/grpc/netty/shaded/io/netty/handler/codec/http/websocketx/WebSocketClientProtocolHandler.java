package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx;

import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPipeline;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaders;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.net.SocketAddress;
import java.net.URI;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class WebSocketClientProtocolHandler extends WebSocketProtocolHandler {
    private final WebSocketClientProtocolConfig clientConfig;
    private final WebSocketClientHandshaker handshaker;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum ClientHandshakeStateEvent {
        HANDSHAKE_TIMEOUT,
        HANDSHAKE_ISSUED,
        HANDSHAKE_COMPLETE
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketProtocolHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public /* bridge */ /* synthetic */ void bind(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, ChannelPromise channelPromise) throws Exception {
        super.bind(channelHandlerContext, socketAddress, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketProtocolHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public /* bridge */ /* synthetic */ void close(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        super.close(channelHandlerContext, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketProtocolHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public /* bridge */ /* synthetic */ void connect(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) throws Exception {
        super.connect(channelHandlerContext, socketAddress, socketAddress2, channelPromise);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketProtocolHandler, io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageDecoder
    public /* bridge */ /* synthetic */ void decode(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame, List list) throws Exception {
        decode(channelHandlerContext, webSocketFrame, (List<Object>) list);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketProtocolHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public /* bridge */ /* synthetic */ void deregister(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        super.deregister(channelHandlerContext, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketProtocolHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public /* bridge */ /* synthetic */ void disconnect(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        super.disconnect(channelHandlerContext, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketProtocolHandler, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public /* bridge */ /* synthetic */ void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
        super.exceptionCaught(channelHandlerContext, th);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketProtocolHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public /* bridge */ /* synthetic */ void flush(ChannelHandlerContext channelHandlerContext) throws Exception {
        super.flush(channelHandlerContext);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketProtocolHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public /* bridge */ /* synthetic */ void read(ChannelHandlerContext channelHandlerContext) throws Exception {
        super.read(channelHandlerContext);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketProtocolHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public /* bridge */ /* synthetic */ void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        super.write(channelHandlerContext, obj, channelPromise);
    }

    public WebSocketClientHandshaker handshaker() {
        return this.handshaker;
    }

    public WebSocketClientProtocolHandler(WebSocketClientProtocolConfig webSocketClientProtocolConfig) {
        super(((WebSocketClientProtocolConfig) ObjectUtil.checkNotNull(webSocketClientProtocolConfig, "clientConfig")).dropPongFrames(), webSocketClientProtocolConfig.sendCloseFrame(), webSocketClientProtocolConfig.forceCloseTimeoutMillis());
        this.handshaker = WebSocketClientHandshakerFactory.newHandshaker(webSocketClientProtocolConfig.webSocketUri(), webSocketClientProtocolConfig.version(), webSocketClientProtocolConfig.subprotocol(), webSocketClientProtocolConfig.allowExtensions(), webSocketClientProtocolConfig.customHeaders(), webSocketClientProtocolConfig.maxFramePayloadLength(), webSocketClientProtocolConfig.performMasking(), webSocketClientProtocolConfig.allowMaskMismatch(), webSocketClientProtocolConfig.forceCloseTimeoutMillis(), webSocketClientProtocolConfig.absoluteUpgradeUrl());
        this.clientConfig = webSocketClientProtocolConfig;
    }

    public WebSocketClientProtocolHandler(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i, boolean z2, boolean z3, boolean z4) {
        this(uri, webSocketVersion, str, z, httpHeaders, i, z2, z3, z4, 10000L);
    }

    public WebSocketClientProtocolHandler(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i, boolean z2, boolean z3, boolean z4, long j) {
        this(WebSocketClientHandshakerFactory.newHandshaker(uri, webSocketVersion, str, z, httpHeaders, i, z3, z4), z2, j);
    }

    public WebSocketClientProtocolHandler(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i, boolean z2) {
        this(uri, webSocketVersion, str, z, httpHeaders, i, z2, 10000L);
    }

    public WebSocketClientProtocolHandler(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i, boolean z2, long j) {
        this(uri, webSocketVersion, str, z, httpHeaders, i, z2, true, false, j);
    }

    public WebSocketClientProtocolHandler(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i) {
        this(uri, webSocketVersion, str, z, httpHeaders, i, 10000L);
    }

    public WebSocketClientProtocolHandler(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i, long j) {
        this(uri, webSocketVersion, str, z, httpHeaders, i, true, j);
    }

    public WebSocketClientProtocolHandler(WebSocketClientHandshaker webSocketClientHandshaker, boolean z) {
        this(webSocketClientHandshaker, z, 10000L);
    }

    public WebSocketClientProtocolHandler(WebSocketClientHandshaker webSocketClientHandshaker, boolean z, long j) {
        this(webSocketClientHandshaker, z, true, j);
    }

    public WebSocketClientProtocolHandler(WebSocketClientHandshaker webSocketClientHandshaker, boolean z, boolean z2) {
        this(webSocketClientHandshaker, z, z2, 10000L);
    }

    public WebSocketClientProtocolHandler(WebSocketClientHandshaker webSocketClientHandshaker, boolean z, boolean z2, long j) {
        super(z2);
        this.handshaker = webSocketClientHandshaker;
        this.clientConfig = WebSocketClientProtocolConfig.newBuilder().handleCloseFrames(z).handshakeTimeoutMillis(j).build();
    }

    public WebSocketClientProtocolHandler(WebSocketClientHandshaker webSocketClientHandshaker) {
        this(webSocketClientHandshaker, 10000L);
    }

    public WebSocketClientProtocolHandler(WebSocketClientHandshaker webSocketClientHandshaker, long j) {
        this(webSocketClientHandshaker, true, j);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketProtocolHandler
    public void decode(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame, List<Object> list) throws Exception {
        if (this.clientConfig.handleCloseFrames() && (webSocketFrame instanceof CloseWebSocketFrame)) {
            channelHandlerContext.close();
        } else {
            super.decode(channelHandlerContext, webSocketFrame, list);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) {
        ChannelPipeline pipeline = channelHandlerContext.pipeline();
        if (pipeline.get(WebSocketClientProtocolHandshakeHandler.class) == null) {
            channelHandlerContext.pipeline().addBefore(channelHandlerContext.name(), WebSocketClientProtocolHandshakeHandler.class.getName(), new WebSocketClientProtocolHandshakeHandler(this.handshaker, this.clientConfig.handshakeTimeoutMillis()));
        }
        if (pipeline.get(Utf8FrameValidator.class) == null) {
            channelHandlerContext.pipeline().addBefore(channelHandlerContext.name(), Utf8FrameValidator.class.getName(), new Utf8FrameValidator());
        }
    }
}
