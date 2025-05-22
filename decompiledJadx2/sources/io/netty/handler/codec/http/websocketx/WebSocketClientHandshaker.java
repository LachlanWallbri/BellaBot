package io.netty.handler.codec.http.websocketx;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpScheme;
import io.netty.util.NetUtil;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.ScheduledFuture;
import io.netty.util.internal.ThrowableUtil;
import java.net.URI;
import java.nio.channels.ClosedChannelException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public abstract class WebSocketClientHandshaker {
    private volatile String actualSubprotocol;
    protected final HttpHeaders customHeaders;
    private final String expectedSubprotocol;
    private volatile boolean handshakeComplete;
    private final int maxFramePayloadLength;
    private final URI uri;
    private final WebSocketVersion version;
    private static final ClosedChannelException CLOSED_CHANNEL_EXCEPTION = (ClosedChannelException) ThrowableUtil.unknownStackTrace(new ClosedChannelException(), WebSocketClientHandshaker.class, "processHandshake(...)");
    private static final String HTTP_SCHEME_PREFIX = HttpScheme.HTTP + "://";
    private static final String HTTPS_SCHEME_PREFIX = HttpScheme.HTTPS + "://";

    protected abstract FullHttpRequest newHandshakeRequest();

    protected abstract WebSocketFrameEncoder newWebSocketEncoder();

    protected abstract WebSocketFrameDecoder newWebsocketDecoder();

    protected abstract void verify(FullHttpResponse fullHttpResponse);

    /* JADX INFO: Access modifiers changed from: protected */
    public WebSocketClientHandshaker(URI uri, WebSocketVersion webSocketVersion, String str, HttpHeaders httpHeaders, int i) {
        this.uri = uri;
        this.version = webSocketVersion;
        this.expectedSubprotocol = str;
        this.customHeaders = httpHeaders;
        this.maxFramePayloadLength = i;
    }

    public URI uri() {
        return this.uri;
    }

    public WebSocketVersion version() {
        return this.version;
    }

    public int maxFramePayloadLength() {
        return this.maxFramePayloadLength;
    }

    public boolean isHandshakeComplete() {
        return this.handshakeComplete;
    }

    private void setHandshakeComplete() {
        this.handshakeComplete = true;
    }

    public String expectedSubprotocol() {
        return this.expectedSubprotocol;
    }

    public String actualSubprotocol() {
        return this.actualSubprotocol;
    }

    private void setActualSubprotocol(String str) {
        this.actualSubprotocol = str;
    }

    public ChannelFuture handshake(Channel channel) {
        if (channel == null) {
            throw new NullPointerException("channel");
        }
        return handshake(channel, channel.newPromise());
    }

    public final ChannelFuture handshake(Channel channel, final ChannelPromise channelPromise) {
        FullHttpRequest newHandshakeRequest = newHandshakeRequest();
        if (((HttpResponseDecoder) channel.pipeline().get(HttpResponseDecoder.class)) == null && ((HttpClientCodec) channel.pipeline().get(HttpClientCodec.class)) == null) {
            channelPromise.setFailure((Throwable) new IllegalStateException("ChannelPipeline does not contain a HttpResponseDecoder or HttpClientCodec"));
            return channelPromise;
        }
        channel.writeAndFlush(newHandshakeRequest).addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker.1
            @Override // io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture) {
                if (channelFuture.isSuccess()) {
                    ChannelPipeline pipeline = channelFuture.channel().pipeline();
                    ChannelHandlerContext context = pipeline.context(HttpRequestEncoder.class);
                    if (context == null) {
                        context = pipeline.context(HttpClientCodec.class);
                    }
                    if (context == null) {
                        channelPromise.setFailure((Throwable) new IllegalStateException("ChannelPipeline does not contain a HttpRequestEncoder or HttpClientCodec"));
                        return;
                    } else {
                        pipeline.addAfter(context.name(), "ws-encoder", WebSocketClientHandshaker.this.newWebSocketEncoder());
                        channelPromise.setSuccess();
                        return;
                    }
                }
                channelPromise.setFailure(channelFuture.cause());
            }
        });
        return channelPromise;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00dc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void finishHandshake(Channel channel, FullHttpResponse fullHttpResponse) {
        boolean z;
        verify(fullHttpResponse);
        String str = fullHttpResponse.headers().get(HttpHeaderNames.SEC_WEBSOCKET_PROTOCOL);
        String trim = str != null ? str.trim() : null;
        String str2 = this.expectedSubprotocol;
        if (str2 == null) {
            str2 = "";
        }
        if (str2.isEmpty() && trim == null) {
            setActualSubprotocol(this.expectedSubprotocol);
        } else {
            if (!str2.isEmpty() && trim != null && !trim.isEmpty()) {
                for (String str3 : str2.split(",")) {
                    if (str3.trim().equals(trim)) {
                        setActualSubprotocol(trim);
                    }
                }
            }
            z = false;
            if (z) {
                throw new WebSocketHandshakeException(String.format("Invalid subprotocol. Actual: %s. Expected one of: %s", trim, this.expectedSubprotocol));
            }
            setHandshakeComplete();
            final ChannelPipeline pipeline = channel.pipeline();
            HttpContentDecompressor httpContentDecompressor = (HttpContentDecompressor) pipeline.get(HttpContentDecompressor.class);
            if (httpContentDecompressor != null) {
                pipeline.remove(httpContentDecompressor);
            }
            HttpObjectAggregator httpObjectAggregator = (HttpObjectAggregator) pipeline.get(HttpObjectAggregator.class);
            if (httpObjectAggregator != null) {
                pipeline.remove(httpObjectAggregator);
            }
            final ChannelHandlerContext context = pipeline.context(HttpResponseDecoder.class);
            if (context == null) {
                ChannelHandlerContext context2 = pipeline.context(HttpClientCodec.class);
                if (context2 == null) {
                    throw new IllegalStateException("ChannelPipeline does not contain a HttpRequestEncoder or HttpClientCodec");
                }
                final HttpClientCodec httpClientCodec = (HttpClientCodec) context2.handler();
                httpClientCodec.removeOutboundHandler();
                pipeline.addAfter(context2.name(), "ws-decoder", newWebsocketDecoder());
                channel.eventLoop().execute(new Runnable() { // from class: io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker.2
                    @Override // java.lang.Runnable
                    public void run() {
                        pipeline.remove(httpClientCodec);
                    }
                });
                return;
            }
            if (pipeline.get(HttpRequestEncoder.class) != null) {
                pipeline.remove(HttpRequestEncoder.class);
            }
            pipeline.addAfter(context.name(), "ws-decoder", newWebsocketDecoder());
            channel.eventLoop().execute(new Runnable() { // from class: io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker.3
                @Override // java.lang.Runnable
                public void run() {
                    pipeline.remove(context.handler());
                }
            });
            return;
        }
        z = true;
        if (z) {
        }
    }

    public final ChannelFuture processHandshake(Channel channel, HttpResponse httpResponse) {
        return processHandshake(channel, httpResponse, channel.newPromise());
    }

    public final ChannelFuture processHandshake(final Channel channel, HttpResponse httpResponse, final ChannelPromise channelPromise) {
        if (httpResponse instanceof FullHttpResponse) {
            try {
                finishHandshake(channel, (FullHttpResponse) httpResponse);
                channelPromise.setSuccess();
            } catch (Throwable th) {
                channelPromise.setFailure(th);
            }
        } else {
            ChannelPipeline pipeline = channel.pipeline();
            ChannelHandlerContext context = pipeline.context(HttpResponseDecoder.class);
            if (context == null && (context = pipeline.context(HttpClientCodec.class)) == null) {
                return channelPromise.setFailure((Throwable) new IllegalStateException("ChannelPipeline does not contain a HttpResponseDecoder or HttpClientCodec"));
            }
            pipeline.addAfter(context.name(), "httpAggregator", new HttpObjectAggregator(8192));
            pipeline.addAfter("httpAggregator", "handshaker", new SimpleChannelInboundHandler<FullHttpResponse>() { // from class: io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker.4
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // io.netty.channel.SimpleChannelInboundHandler
                public void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpResponse fullHttpResponse) throws Exception {
                    channelHandlerContext.pipeline().remove(this);
                    try {
                        WebSocketClientHandshaker.this.finishHandshake(channel, fullHttpResponse);
                        channelPromise.setSuccess();
                    } catch (Throwable th2) {
                        channelPromise.setFailure(th2);
                    }
                }

                @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler, io.netty.channel.ChannelInboundHandler
                public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th2) throws Exception {
                    channelHandlerContext.pipeline().remove(this);
                    channelPromise.setFailure(th2);
                }

                @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
                public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
                    channelPromise.tryFailure(WebSocketClientHandshaker.CLOSED_CHANNEL_EXCEPTION);
                    channelHandlerContext.fireChannelInactive();
                }
            });
            try {
                context.fireChannelRead(ReferenceCountUtil.retain(httpResponse));
            } catch (Throwable th2) {
                channelPromise.setFailure(th2);
            }
        }
        return channelPromise;
    }

    public ChannelFuture close(Channel channel, CloseWebSocketFrame closeWebSocketFrame) {
        if (channel == null) {
            throw new NullPointerException("channel");
        }
        return close(channel, closeWebSocketFrame, channel.newPromise());
    }

    public ChannelFuture close(Channel channel, CloseWebSocketFrame closeWebSocketFrame, ChannelPromise channelPromise) {
        if (channel == null) {
            throw new NullPointerException("channel");
        }
        return channel.writeAndFlush(closeWebSocketFrame, channelPromise);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String rawPath(URI uri) {
        String rawPath = uri.getRawPath();
        String rawQuery = uri.getRawQuery();
        if (rawQuery != null && !rawQuery.isEmpty()) {
            rawPath = rawPath + '?' + rawQuery;
        }
        return (rawPath == null || rawPath.isEmpty()) ? "/" : rawPath;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CharSequence websocketHostValue(URI uri) {
        int port = uri.getPort();
        if (port == -1) {
            return uri.getHost();
        }
        String host = uri.getHost();
        if (port == HttpScheme.HTTP.port()) {
            return (HttpScheme.HTTP.name().contentEquals(uri.getScheme()) || WebSocketScheme.f8504WS.name().contentEquals(uri.getScheme())) ? host : NetUtil.toSocketAddressString(host, port);
        }
        if (port == HttpScheme.HTTPS.port()) {
            return (HttpScheme.HTTPS.name().contentEquals(uri.getScheme()) || WebSocketScheme.WSS.name().contentEquals(uri.getScheme())) ? host : NetUtil.toSocketAddressString(host, port);
        }
        return NetUtil.toSocketAddressString(host, port);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CharSequence websocketOriginValue(URI uri) {
        String str;
        int port;
        String scheme = uri.getScheme();
        int port2 = uri.getPort();
        if (WebSocketScheme.WSS.name().contentEquals(scheme) || HttpScheme.HTTPS.name().contentEquals(scheme) || (scheme == null && port2 == WebSocketScheme.WSS.port())) {
            str = HTTPS_SCHEME_PREFIX;
            port = WebSocketScheme.WSS.port();
        } else {
            str = HTTP_SCHEME_PREFIX;
            port = WebSocketScheme.f8504WS.port();
        }
        String lowerCase = uri.getHost().toLowerCase(Locale.US);
        if (port2 != port && port2 != -1) {
            return str + NetUtil.toSocketAddressString(lowerCase, port2);
        }
        return str + lowerCase;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* renamed from: io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker$5 */
    /* loaded from: classes8.dex */
    class C71615 implements ChannelFutureListener {
        final /* synthetic */ Channel val$channel;
        final /* synthetic */ long val$forceCloseTimeoutMillis;
        final /* synthetic */ WebSocketClientHandshaker val$handshaker;

        C71615(Channel channel, WebSocketClientHandshaker webSocketClientHandshaker, long j) {
            this.val$channel = channel;
            this.val$handshaker = webSocketClientHandshaker;
            this.val$forceCloseTimeoutMillis = j;
        }

        @Override // io.netty.util.concurrent.GenericFutureListener
        public void operationComplete(ChannelFuture channelFuture) throws Exception {
            if (channelFuture.isSuccess() && this.val$channel.isActive() && WebSocketClientHandshaker.CLOSED_CHANNEL_EXCEPTION.compareAndSet(this.val$handshaker, 0, 1)) {
                final ScheduledFuture<?> schedule = this.val$channel.eventLoop().schedule(new Runnable() { // from class: io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (C71615.this.val$channel.isActive()) {
                            C71615.this.val$channel.close();
                            WebSocketClientHandshaker.access$102(WebSocketClientHandshaker.this, true);
                        }
                    }
                }, this.val$forceCloseTimeoutMillis, TimeUnit.MILLISECONDS);
                this.val$channel.closeFuture().addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker.5.2
                    @Override // io.netty.util.concurrent.GenericFutureListener
                    public void operationComplete(ChannelFuture channelFuture2) throws Exception {
                        schedule.cancel(false);
                    }
                });
            }
        }
    }
}
