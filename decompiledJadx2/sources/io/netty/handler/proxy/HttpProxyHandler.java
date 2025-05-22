package io.netty.handler.proxy;

import com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.base64.Base64;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.util.AsciiString;
import io.netty.util.CharsetUtil;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class HttpProxyHandler extends ProxyHandler {
    private static final String AUTH_BASIC = "basic";
    private static final String PROTOCOL = "http";
    private final CharSequence authorization;
    private final HttpClientCodec codec;
    private HttpHeaders headers;
    private final boolean ignoreDefaultPortsInConnectHostHeader;
    private final String password;
    private HttpResponseStatus status;
    private final String username;

    @Override // io.netty.handler.proxy.ProxyHandler
    public String protocol() {
        return "http";
    }

    public HttpProxyHandler(SocketAddress socketAddress) {
        this(socketAddress, null);
    }

    public HttpProxyHandler(SocketAddress socketAddress, HttpHeaders httpHeaders) {
        this(socketAddress, httpHeaders, false);
    }

    public HttpProxyHandler(SocketAddress socketAddress, HttpHeaders httpHeaders, boolean z) {
        super(socketAddress);
        this.codec = new HttpClientCodec();
        this.username = null;
        this.password = null;
        this.authorization = null;
        this.headers = httpHeaders;
        this.ignoreDefaultPortsInConnectHostHeader = z;
    }

    public HttpProxyHandler(SocketAddress socketAddress, String str, String str2) {
        this(socketAddress, str, str2, null);
    }

    public HttpProxyHandler(SocketAddress socketAddress, String str, String str2, HttpHeaders httpHeaders) {
        this(socketAddress, str, str2, httpHeaders, false);
    }

    public HttpProxyHandler(SocketAddress socketAddress, String str, String str2, HttpHeaders httpHeaders, boolean z) {
        super(socketAddress);
        this.codec = new HttpClientCodec();
        if (str == null) {
            throw new NullPointerException(CognitoUserPoolsSignInProvider.AttributeKeys.USERNAME);
        }
        if (str2 == null) {
            throw new NullPointerException(CognitoUserPoolsSignInProvider.AttributeKeys.PASSWORD);
        }
        this.username = str;
        this.password = str2;
        ByteBuf copiedBuffer = Unpooled.copiedBuffer(str + ':' + str2, CharsetUtil.UTF_8);
        ByteBuf encode = Base64.encode(copiedBuffer, false);
        this.authorization = new AsciiString("Basic " + encode.toString(CharsetUtil.US_ASCII));
        copiedBuffer.release();
        encode.release();
        this.headers = httpHeaders;
        this.ignoreDefaultPortsInConnectHostHeader = z;
    }

    @Override // io.netty.handler.proxy.ProxyHandler
    public String authScheme() {
        return this.authorization != null ? AUTH_BASIC : "none";
    }

    public String username() {
        return this.username;
    }

    public String password() {
        return this.password;
    }

    @Override // io.netty.handler.proxy.ProxyHandler
    protected void addCodec(ChannelHandlerContext channelHandlerContext) throws Exception {
        channelHandlerContext.pipeline().addBefore(channelHandlerContext.name(), null, this.codec);
    }

    @Override // io.netty.handler.proxy.ProxyHandler
    protected void removeEncoder(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.codec.removeOutboundHandler();
    }

    @Override // io.netty.handler.proxy.ProxyHandler
    protected void removeDecoder(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.codec.removeInboundHandler();
    }

    @Override // io.netty.handler.proxy.ProxyHandler
    protected Object newInitialMessage(ChannelHandlerContext channelHandlerContext) throws Exception {
        InetSocketAddress inetSocketAddress = (InetSocketAddress) destinationAddress();
        String formatHostnameForHttp = HttpUtil.formatHostnameForHttp(inetSocketAddress);
        int port = inetSocketAddress.getPort();
        String str = formatHostnameForHttp + ":" + port;
        if (!this.ignoreDefaultPortsInConnectHostHeader || (port != 80 && port != 443)) {
            formatHostnameForHttp = str;
        }
        DefaultFullHttpRequest defaultFullHttpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.CONNECT, str, Unpooled.EMPTY_BUFFER, false);
        defaultFullHttpRequest.headers().set(HttpHeaderNames.HOST, formatHostnameForHttp);
        if (this.authorization != null) {
            defaultFullHttpRequest.headers().set(HttpHeaderNames.PROXY_AUTHORIZATION, this.authorization);
        }
        if (this.headers != null) {
            defaultFullHttpRequest.headers().add(this.headers);
        }
        return defaultFullHttpRequest;
    }

    @Override // io.netty.handler.proxy.ProxyHandler
    protected boolean handleResponse(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (obj instanceof HttpResponse) {
            if (this.status != null) {
                throw new ProxyConnectException(exceptionMessage("too many responses"));
            }
            this.status = ((HttpResponse) obj).status();
        }
        boolean z = obj instanceof LastHttpContent;
        if (z) {
            HttpResponseStatus httpResponseStatus = this.status;
            if (httpResponseStatus == null) {
                throw new ProxyConnectException(exceptionMessage("missing response"));
            }
            if (httpResponseStatus.code() != 200) {
                throw new ProxyConnectException(exceptionMessage("status: " + this.status));
            }
        }
        return z;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes8.dex */
    public static final class HttpProxyConnectException extends ProxyConnectException {
        private static final long serialVersionUID = -8824334609292146066L;
        private final HttpHeaders headers;

        public HttpProxyConnectException(String str, HttpHeaders httpHeaders) {
            super(str);
            this.headers = httpHeaders;
        }

        public HttpHeaders headers() {
            return this.headers;
        }
    }
}
