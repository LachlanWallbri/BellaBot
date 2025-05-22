package io.grpc.netty.shaded.io.netty.handler.codec.http.cors;

import io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderNames;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderValues;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaders;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMethod;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponseStatus;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpUtil;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class CorsHandler extends ChannelDuplexHandler {
    private static final String ANY_ORIGIN = "*";
    private static final String NULL_ORIGIN = "null";
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) CorsHandler.class);
    private CorsConfig config;
    private final List<CorsConfig> configList;
    private boolean isShortCircuit;
    private HttpRequest request;

    public CorsHandler(CorsConfig corsConfig) {
        this(Collections.singletonList(ObjectUtil.checkNotNull(corsConfig, "config")), corsConfig.isShortCircuit());
    }

    public CorsHandler(List<CorsConfig> list, boolean z) {
        ObjectUtil.checkNonEmpty(list, "configList");
        this.configList = list;
        this.isShortCircuit = z;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (obj instanceof HttpRequest) {
            this.request = (HttpRequest) obj;
            String str = this.request.headers().get(HttpHeaderNames.ORIGIN);
            this.config = getForOrigin(str);
            if (isPreflightRequest(this.request)) {
                handlePreflight(channelHandlerContext, this.request);
                return;
            } else if (this.isShortCircuit && str != null && this.config == null) {
                forbidden(channelHandlerContext, this.request);
                return;
            }
        }
        channelHandlerContext.fireChannelRead(obj);
    }

    private void handlePreflight(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest) {
        DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(httpRequest.protocolVersion(), HttpResponseStatus.f8370OK, true, true);
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

    private CorsConfig getForOrigin(String str) {
        Iterator<CorsConfig> it = this.configList.iterator();
        while (it.hasNext()) {
            CorsConfig next = it.next();
            if (next.isAnyOriginSupported() || next.origins().contains(str) || next.isNullOriginAllowed() || "null".equals(str)) {
                return next;
            }
        }
        return null;
    }

    private boolean setOrigin(HttpResponse httpResponse) {
        String str = this.request.headers().get(HttpHeaderNames.ORIGIN);
        if (str == null || this.config == null) {
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
        return HttpMethod.OPTIONS.equals(httpRequest.method()) && headers.contains(HttpHeaderNames.ORIGIN) && headers.contains(HttpHeaderNames.ACCESS_CONTROL_REQUEST_METHOD);
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

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        CorsConfig corsConfig = this.config;
        if (corsConfig != null && corsConfig.isCorsSupportEnabled() && (obj instanceof HttpResponse)) {
            HttpResponse httpResponse = (HttpResponse) obj;
            if (setOrigin(httpResponse)) {
                setAllowCredentials(httpResponse);
                setExposeHeaders(httpResponse);
            }
        }
        channelHandlerContext.write(obj, channelPromise);
    }

    private static void forbidden(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest) {
        DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(httpRequest.protocolVersion(), HttpResponseStatus.FORBIDDEN, channelHandlerContext.alloc().buffer(0));
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
