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
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpClientCodec;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContentDecompressor;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderNames;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaders;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectAggregator;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequestEncoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponseDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpScheme;
import io.grpc.netty.shaded.io.netty.util.NetUtil;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.concurrent.ScheduledFuture;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.net.URI;
import java.nio.channels.ClosedChannelException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class WebSocketClientHandshaker {
    protected static final int DEFAULT_FORCE_CLOSE_TIMEOUT_MILLIS = 10000;
    private final boolean absoluteUpgradeUrl;
    private volatile String actualSubprotocol;
    protected final HttpHeaders customHeaders;
    private final String expectedSubprotocol;
    private volatile boolean forceCloseComplete;
    private volatile int forceCloseInit;
    private volatile long forceCloseTimeoutMillis;
    private volatile boolean handshakeComplete;
    private final int maxFramePayloadLength;
    private final URI uri;
    private final WebSocketVersion version;
    private static final String HTTP_SCHEME_PREFIX = HttpScheme.HTTP + "://";
    private static final String HTTPS_SCHEME_PREFIX = HttpScheme.HTTPS + "://";
    private static final AtomicIntegerFieldUpdater<WebSocketClientHandshaker> FORCE_CLOSE_INIT_UPDATER = AtomicIntegerFieldUpdater.newUpdater(WebSocketClientHandshaker.class, "forceCloseInit");

    protected abstract FullHttpRequest newHandshakeRequest();

    protected abstract WebSocketFrameEncoder newWebSocketEncoder();

    protected abstract WebSocketFrameDecoder newWebsocketDecoder();

    protected abstract void verify(FullHttpResponse fullHttpResponse);

    protected WebSocketClientHandshaker(URI uri, WebSocketVersion webSocketVersion, String str, HttpHeaders httpHeaders, int i) {
        this(uri, webSocketVersion, str, httpHeaders, i, 10000L);
    }

    protected WebSocketClientHandshaker(URI uri, WebSocketVersion webSocketVersion, String str, HttpHeaders httpHeaders, int i, long j) {
        this(uri, webSocketVersion, str, httpHeaders, i, j, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public WebSocketClientHandshaker(URI uri, WebSocketVersion webSocketVersion, String str, HttpHeaders httpHeaders, int i, long j, boolean z) {
        this.forceCloseTimeoutMillis = 10000L;
        this.uri = uri;
        this.version = webSocketVersion;
        this.expectedSubprotocol = str;
        this.customHeaders = httpHeaders;
        this.maxFramePayloadLength = i;
        this.forceCloseTimeoutMillis = j;
        this.absoluteUpgradeUrl = z;
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

    public long forceCloseTimeoutMillis() {
        return this.forceCloseTimeoutMillis;
    }

    protected boolean isForceCloseComplete() {
        return this.forceCloseComplete;
    }

    public WebSocketClientHandshaker setForceCloseTimeoutMillis(long j) {
        this.forceCloseTimeoutMillis = j;
        return this;
    }

    public ChannelFuture handshake(Channel channel) {
        ObjectUtil.checkNotNull(channel, "channel");
        return handshake(channel, channel.newPromise());
    }

    public final ChannelFuture handshake(Channel channel, final ChannelPromise channelPromise) {
        ChannelPipeline pipeline = channel.pipeline();
        if (((HttpResponseDecoder) pipeline.get(HttpResponseDecoder.class)) == null && ((HttpClientCodec) pipeline.get(HttpClientCodec.class)) == null) {
            channelPromise.setFailure((Throwable) new IllegalStateException("ChannelPipeline does not contain an HttpResponseDecoder or HttpClientCodec"));
            return channelPromise;
        }
        channel.writeAndFlush(newHandshakeRequest()).addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker.1
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture) {
                if (channelFuture.isSuccess()) {
                    ChannelPipeline pipeline2 = channelFuture.channel().pipeline();
                    ChannelHandlerContext context = pipeline2.context(HttpRequestEncoder.class);
                    if (context == null) {
                        context = pipeline2.context(HttpClientCodec.class);
                    }
                    if (context == null) {
                        channelPromise.setFailure((Throwable) new IllegalStateException("ChannelPipeline does not contain an HttpRequestEncoder or HttpClientCodec"));
                        return;
                    } else {
                        pipeline2.addAfter(context.name(), "ws-encoder", WebSocketClientHandshaker.this.newWebSocketEncoder());
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
                    throw new IllegalStateException("ChannelPipeline does not contain an HttpRequestEncoder or HttpClientCodec");
                }
                final HttpClientCodec httpClientCodec = (HttpClientCodec) context2.handler();
                httpClientCodec.removeOutboundHandler();
                pipeline.addAfter(context2.name(), "ws-decoder", newWebsocketDecoder());
                channel.eventLoop().execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker.2
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
            channel.eventLoop().execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker.3
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
                return channelPromise.setFailure((Throwable) new IllegalStateException("ChannelPipeline does not contain an HttpResponseDecoder or HttpClientCodec"));
            }
            pipeline.addAfter(context.name(), "httpAggregator", new HttpObjectAggregator(8192));
            pipeline.addAfter("httpAggregator", "handshaker", new SimpleChannelInboundHandler<FullHttpResponse>() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker.4
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // io.grpc.netty.shaded.io.netty.channel.SimpleChannelInboundHandler
                public void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpResponse fullHttpResponse) throws Exception {
                    channelHandlerContext.pipeline().remove(this);
                    try {
                        WebSocketClientHandshaker.this.finishHandshake(channel, fullHttpResponse);
                        channelPromise.setSuccess();
                    } catch (Throwable th2) {
                        channelPromise.setFailure(th2);
                    }
                }

                @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
                public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th2) throws Exception {
                    channelHandlerContext.pipeline().remove(this);
                    channelPromise.setFailure(th2);
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
                context.fireChannelRead(ReferenceCountUtil.retain(httpResponse));
            } catch (Throwable th2) {
                channelPromise.setFailure(th2);
            }
        }
        return channelPromise;
    }

    public ChannelFuture close(Channel channel, CloseWebSocketFrame closeWebSocketFrame) {
        ObjectUtil.checkNotNull(channel, "channel");
        return close(channel, closeWebSocketFrame, channel.newPromise());
    }

    public ChannelFuture close(Channel channel, CloseWebSocketFrame closeWebSocketFrame, ChannelPromise channelPromise) {
        ObjectUtil.checkNotNull(channel, "channel");
        channel.writeAndFlush(closeWebSocketFrame, channelPromise);
        applyForceCloseTimeout(channel, channelPromise);
        return channelPromise;
    }

    private void applyForceCloseTimeout(final Channel channel, ChannelFuture channelFuture) {
        final long j = this.forceCloseTimeoutMillis;
        if (j > 0 && channel.isActive() && this.forceCloseInit == 0) {
            channelFuture.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker.5
                @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(ChannelFuture channelFuture2) throws Exception {
                    if (channelFuture2.isSuccess() && channel.isActive() && WebSocketClientHandshaker.FORCE_CLOSE_INIT_UPDATER.compareAndSet(this, 0, 1)) {
                        final ScheduledFuture<?> schedule = channel.eventLoop().schedule(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker.5.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (channel.isActive()) {
                                    channel.close();
                                    WebSocketClientHandshaker.this.forceCloseComplete = true;
                                }
                            }
                        }, j, TimeUnit.MILLISECONDS);
                        channel.closeFuture().addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker.5.2
                            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                            public void operationComplete(ChannelFuture channelFuture3) throws Exception {
                                schedule.cancel(false);
                            }
                        });
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String upgradeUrl(URI uri) {
        if (this.absoluteUpgradeUrl) {
            return uri.toString();
        }
        String rawPath = uri.getRawPath();
        if (rawPath == null || rawPath.isEmpty()) {
            rawPath = "/";
        }
        String rawQuery = uri.getRawQuery();
        if (rawQuery == null || rawQuery.isEmpty()) {
            return rawPath;
        }
        return rawPath + '?' + rawQuery;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CharSequence websocketHostValue(URI uri) {
        int port = uri.getPort();
        if (port == -1) {
            return uri.getHost();
        }
        String host = uri.getHost();
        String scheme = uri.getScheme();
        if (port == HttpScheme.HTTP.port()) {
            return (HttpScheme.HTTP.name().contentEquals(scheme) || WebSocketScheme.f8374WS.name().contentEquals(scheme)) ? host : NetUtil.toSocketAddressString(host, port);
        }
        if (port == HttpScheme.HTTPS.port()) {
            return (HttpScheme.HTTPS.name().contentEquals(scheme) || WebSocketScheme.WSS.name().contentEquals(scheme)) ? host : NetUtil.toSocketAddressString(host, port);
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
            port = WebSocketScheme.f8374WS.port();
        }
        String lowerCase = uri.getHost().toLowerCase(Locale.US);
        if (port2 != port && port2 != -1) {
            return str + NetUtil.toSocketAddressString(lowerCase, port2);
        }
        return str + lowerCase;
    }
}
