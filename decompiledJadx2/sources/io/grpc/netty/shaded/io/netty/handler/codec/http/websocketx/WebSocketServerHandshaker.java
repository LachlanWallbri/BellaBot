package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx;

import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPipeline;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.SimpleChannelInboundHandler;
import io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContentCompressor;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaders;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectAggregator;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequestDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponseEncoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpServerCodec;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.EmptyArrays;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.nio.channels.ClosedChannelException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class WebSocketServerHandshaker {
    public static final String SUB_PROTOCOL_WILDCARD = "*";
    protected static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) WebSocketServerHandshaker.class);
    private final WebSocketDecoderConfig decoderConfig;
    private String selectedSubprotocol;
    private final String[] subprotocols;
    private final String uri;
    private final WebSocketVersion version;

    protected abstract FullHttpResponse newHandshakeResponse(FullHttpRequest fullHttpRequest, HttpHeaders httpHeaders);

    protected abstract WebSocketFrameEncoder newWebSocketEncoder();

    protected abstract WebSocketFrameDecoder newWebsocketDecoder();

    protected WebSocketServerHandshaker(WebSocketVersion webSocketVersion, String str, String str2, int i) {
        this(webSocketVersion, str, str2, WebSocketDecoderConfig.newBuilder().maxFramePayloadLength(i).build());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public WebSocketServerHandshaker(WebSocketVersion webSocketVersion, String str, String str2, WebSocketDecoderConfig webSocketDecoderConfig) {
        this.version = webSocketVersion;
        this.uri = str;
        if (str2 != null) {
            String[] split = str2.split(",");
            for (int i = 0; i < split.length; i++) {
                split[i] = split[i].trim();
            }
            this.subprotocols = split;
        } else {
            this.subprotocols = EmptyArrays.EMPTY_STRINGS;
        }
        this.decoderConfig = (WebSocketDecoderConfig) ObjectUtil.checkNotNull(webSocketDecoderConfig, "decoderConfig");
    }

    public String uri() {
        return this.uri;
    }

    public Set<String> subprotocols() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Collections.addAll(linkedHashSet, this.subprotocols);
        return linkedHashSet;
    }

    public WebSocketVersion version() {
        return this.version;
    }

    public int maxFramePayloadLength() {
        return this.decoderConfig.maxFramePayloadLength();
    }

    public WebSocketDecoderConfig decoderConfig() {
        return this.decoderConfig;
    }

    public ChannelFuture handshake(Channel channel, FullHttpRequest fullHttpRequest) {
        return handshake(channel, fullHttpRequest, (HttpHeaders) null, channel.newPromise());
    }

    public final ChannelFuture handshake(Channel channel, FullHttpRequest fullHttpRequest, HttpHeaders httpHeaders, final ChannelPromise channelPromise) {
        final String str;
        if (logger.isDebugEnabled()) {
            logger.debug("{} WebSocket version {} server handshake", channel, version());
        }
        FullHttpResponse newHandshakeResponse = newHandshakeResponse(fullHttpRequest, httpHeaders);
        ChannelPipeline pipeline = channel.pipeline();
        if (pipeline.get(HttpObjectAggregator.class) != null) {
            pipeline.remove(HttpObjectAggregator.class);
        }
        if (pipeline.get(HttpContentCompressor.class) != null) {
            pipeline.remove(HttpContentCompressor.class);
        }
        ChannelHandlerContext context = pipeline.context(HttpRequestDecoder.class);
        if (context == null) {
            ChannelHandlerContext context2 = pipeline.context(HttpServerCodec.class);
            if (context2 == null) {
                channelPromise.setFailure((Throwable) new IllegalStateException("No HttpDecoder and no HttpServerCodec in the pipeline"));
                return channelPromise;
            }
            pipeline.addBefore(context2.name(), "wsencoder", newWebSocketEncoder());
            pipeline.addBefore(context2.name(), "wsdecoder", newWebsocketDecoder());
            str = context2.name();
        } else {
            pipeline.replace(context.name(), "wsdecoder", newWebsocketDecoder());
            String name = pipeline.context(HttpResponseEncoder.class).name();
            pipeline.addBefore(name, "wsencoder", newWebSocketEncoder());
            str = name;
        }
        channel.writeAndFlush(newHandshakeResponse).addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker.1
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    channelFuture.channel().pipeline().remove(str);
                    channelPromise.setSuccess();
                } else {
                    channelPromise.setFailure(channelFuture.cause());
                }
            }
        });
        return channelPromise;
    }

    public ChannelFuture handshake(Channel channel, HttpRequest httpRequest) {
        return handshake(channel, httpRequest, (HttpHeaders) null, channel.newPromise());
    }

    public final ChannelFuture handshake(final Channel channel, HttpRequest httpRequest, final HttpHeaders httpHeaders, final ChannelPromise channelPromise) {
        if (httpRequest instanceof FullHttpRequest) {
            return handshake(channel, (FullHttpRequest) httpRequest, httpHeaders, channelPromise);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("{} WebSocket version {} server handshake", channel, version());
        }
        ChannelPipeline pipeline = channel.pipeline();
        ChannelHandlerContext context = pipeline.context(HttpRequestDecoder.class);
        if (context == null && (context = pipeline.context(HttpServerCodec.class)) == null) {
            channelPromise.setFailure((Throwable) new IllegalStateException("No HttpDecoder and no HttpServerCodec in the pipeline"));
            return channelPromise;
        }
        pipeline.addAfter(context.name(), "httpAggregator", new HttpObjectAggregator(8192));
        pipeline.addAfter("httpAggregator", "handshaker", new SimpleChannelInboundHandler<FullHttpRequest>() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // io.grpc.netty.shaded.io.netty.channel.SimpleChannelInboundHandler
            public void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {
                channelHandlerContext.pipeline().remove(this);
                WebSocketServerHandshaker.this.handshake(channel, fullHttpRequest, httpHeaders, channelPromise);
            }

            @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
            public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
                channelHandlerContext.pipeline().remove(this);
                channelPromise.tryFailure(th);
                channelHandlerContext.fireExceptionCaught(th);
            }

            @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
            public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
                if (!channelPromise.isDone()) {
                    channelPromise.tryFailure(new ClosedChannelException());
                }
                channelHandlerContext.fireChannelInactive();
            }
        });
        try {
            context.fireChannelRead(ReferenceCountUtil.retain(httpRequest));
        } catch (Throwable th) {
            channelPromise.setFailure(th);
        }
        return channelPromise;
    }

    public ChannelFuture close(Channel channel, CloseWebSocketFrame closeWebSocketFrame) {
        ObjectUtil.checkNotNull(channel, "channel");
        return close(channel, closeWebSocketFrame, channel.newPromise());
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [io.grpc.netty.shaded.io.netty.channel.ChannelFuture] */
    public ChannelFuture close(Channel channel, CloseWebSocketFrame closeWebSocketFrame, ChannelPromise channelPromise) {
        ObjectUtil.checkNotNull(channel, "channel");
        return channel.writeAndFlush(closeWebSocketFrame, channelPromise).addListener((GenericFutureListener<? extends Future<? super Void>>) ChannelFutureListener.CLOSE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String selectSubprotocol(String str) {
        if (str != null && this.subprotocols.length != 0) {
            for (String str2 : str.split(",")) {
                String trim = str2.trim();
                for (String str3 : this.subprotocols) {
                    if ("*".equals(str3) || trim.equals(str3)) {
                        this.selectedSubprotocol = trim;
                        return trim;
                    }
                }
            }
        }
        return null;
    }

    public String selectedSubprotocol() {
        return this.selectedSubprotocol;
    }
}
