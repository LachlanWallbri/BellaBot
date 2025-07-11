package io.netty.handler.codec.http.cors;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class CorsHandler extends ChannelDuplexHandler {
    private static final String ANY_ORIGIN = "*";
    private static final String NULL_ORIGIN = "null";
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) CorsHandler.class);
    private final CorsConfig config;
    private HttpRequest request;

    public CorsHandler(CorsConfig corsConfig) {
        this.config = (CorsConfig) ObjectUtil.checkNotNull(corsConfig, "config");
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (this.config.isCorsSupportEnabled() && (obj instanceof HttpRequest)) {
            HttpRequest httpRequest = (HttpRequest) obj;
            this.request = httpRequest;
            if (isPreflightRequest(httpRequest)) {
                handlePreflight(channelHandlerContext, this.request);
                return;
            } else if (this.config.isShortCircuit() && !validateOrigin()) {
                forbidden(channelHandlerContext, this.request);
                return;
            }
        }
        channelHandlerContext.fireChannelRead(obj);
    }

    private void handlePreflight(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest) {
        DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(httpRequest.protocolVersion(), HttpResponseStatus.f8500OK, true, true);
        if (setOrigin(defaultFullHttpResponse)) {
            setAllowMethods(defaultFullHttpResponse);
            setAllowHeaders(defaultFullHttpResponse);
            setAllowCredentials(defaultFullHttpResponse);
            setMaxAge(defaultFullHttpResponse);
            setPreflightHeaders(defaultFullHttpResponse);
        }
        if (!defaultFullHttpResponse.headers().contains(HttpHeaderNames.CONTENT_LENGTH)) {
            defaultFullHttpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, HttpHeaderValues.ZERO);
        }
        ReferenceCountUtil.release(httpRequest);
        respond(channelHandlerContext, httpRequest, defaultFullHttpResponse);
    }

    private void setPreflightHeaders(HttpResponse httpResponse) {
        httpResponse.headers().add(this.config.preflightResponseHeaders());
    }

    private boolean setOrigin(HttpResponse httpResponse) {
        String str = this.request.headers().get(HttpHeaderNames.ORIGIN);
        if (str == null) {
            return false;
        }
        if ("null".equals(str) && this.config.isNullOriginAllowed()) {
            setNullOrigin(httpResponse);
            return true;
        }
        if (this.config.isAnyOriginSupported()) {
            if (this.config.isCredentialsAllowed()) {
                echoRequestOrigin(httpResponse);
                setVaryHeader(httpResponse);
            } else {
                setAnyOrigin(httpResponse);
            }
            return true;
        }
        if (this.config.origins().contains(str)) {
            setOrigin(httpResponse, str);
            setVaryHeader(httpResponse);
            return true;
        }
        logger.debug("Request origin [{}]] was not among the configured origins [{}]", str, this.config.origins());
        return false;
    }

    private boolean validateOrigin() {
        String str;
        if (this.config.isAnyOriginSupported() || (str = this.request.headers().get(HttpHeaderNames.ORIGIN)) == null) {
            return true;
        }
        if ("null".equals(str) && this.config.isNullOriginAllowed()) {
            return true;
        }
        return this.config.origins().contains(str);
    }

    private void echoRequestOrigin(HttpResponse httpResponse) {
        setOrigin(httpResponse, this.request.headers().get(HttpHeaderNames.ORIGIN));
    }

    private static void setVaryHeader(HttpResponse httpResponse) {
        httpResponse.headers().set(HttpHeaderNames.VARY, HttpHeaderNames.ORIGIN);
    }

    private static void setAnyOrigin(HttpResponse httpResponse) {
        setOrigin(httpResponse, "*");
    }

    private static void setNullOrigin(HttpResponse httpResponse) {
        setOrigin(httpResponse, "null");
    }

    private static void setOrigin(HttpResponse httpResponse, String str) {
        httpResponse.headers().set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN, str);
    }

    private void setAllowCredentials(HttpResponse httpResponse) {
        if (!this.config.isCredentialsAllowed() || httpResponse.headers().get(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN).equals("*")) {
            return;
        }
        httpResponse.headers().set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
    }

    private static boolean isPreflightRequest(HttpRequest httpRequest) {
        HttpHeaders headers = httpRequest.headers();
        return httpRequest.method().equals(HttpMethod.OPTIONS) && headers.contains(HttpHeaderNames.ORIGIN) && headers.contains(HttpHeaderNames.ACCESS_CONTROL_REQUEST_METHOD);
    }

    private void setExposeHeaders(HttpResponse httpResponse) {
        if (this.config.exposedHeaders().isEmpty()) {
            return;
        }
        httpResponse.headers().set((CharSequence) HttpHeaderNames.ACCESS_CONTROL_EXPOSE_HEADERS, (Iterable<?>) this.config.exposedHeaders());
    }

    private void setAllowMethods(HttpResponse httpResponse) {
        httpResponse.headers().set((CharSequence) HttpHeaderNames.ACCESS_CONTROL_ALLOW_METHODS, (Iterable<?>) this.config.allowedRequestMethods());
    }

    private void setAllowHeaders(HttpResponse httpResponse) {
        httpResponse.headers().set((CharSequence) HttpHeaderNames.ACCESS_CONTROL_ALLOW_HEADERS, (Iterable<?>) this.config.allowedRequestHeaders());
    }

    private void setMaxAge(HttpResponse httpResponse) {
        httpResponse.headers().set(HttpHeaderNames.ACCESS_CONTROL_MAX_AGE, Long.valueOf(this.config.maxAge()));
    }

    @Override // io.netty.channel.ChannelDuplexHandler, io.netty.channel.ChannelOutboundHandler
    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        if (this.config.isCorsSupportEnabled() && (obj instanceof HttpResponse)) {
            HttpResponse httpResponse = (HttpResponse) obj;
            if (setOrigin(httpResponse)) {
                setAllowCredentials(httpResponse);
                setExposeHeaders(httpResponse);
            }
        }
        channelHandlerContext.write(obj, channelPromise);
    }

    private static void forbidden(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest) {
        DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(httpRequest.protocolVersion(), HttpResponseStatus.FORBIDDEN);
        defaultFullHttpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, HttpHeaderValues.ZERO);
        ReferenceCountUtil.release(httpRequest);
        respond(channelHandlerContext, httpRequest, defaultFullHttpResponse);
    }

    private static void respond(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest, HttpResponse httpResponse) {
        boolean isKeepAlive = HttpUtil.isKeepAlive(httpRequest);
        HttpUtil.setKeepAlive(httpResponse, isKeepAlive);
        ChannelFuture writeAndFlush = channelHandlerContext.writeAndFlush(httpResponse);
        if (isKeepAlive) {
            return;
        }
        writeAndFlush.addListener((GenericFutureListener<? extends Future<? super Void>>) ChannelFutureListener.CLOSE);
    }
}
