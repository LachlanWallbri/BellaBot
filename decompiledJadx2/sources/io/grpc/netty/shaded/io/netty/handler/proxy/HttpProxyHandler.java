package io.grpc.netty.shaded.io.netty.handler.proxy;

import com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.handler.codec.base64.Base64;
import io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpClientCodec;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderNames;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaders;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMethod;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponseStatus;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpUtil;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpVersion;
import io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent;
import io.grpc.netty.shaded.io.netty.util.AsciiString;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class HttpProxyHandler extends ProxyHandler {
    private static final String AUTH_BASIC = "basic";
    private static final String PROTOCOL = "http";
    private final CharSequence authorization;
    private final HttpClientCodecWrapper codecWrapper;
    private final boolean ignoreDefaultPortsInConnectHostHeader;
    private HttpHeaders inboundHeaders;
    private final HttpHeaders outboundHeaders;
    private final String password;
    private HttpResponseStatus status;
    private final String username;

    @Override // io.grpc.netty.shaded.io.netty.handler.proxy.ProxyHandler
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
        this.codecWrapper = new HttpClientCodecWrapper();
        this.username = null;
        this.password = null;
        this.authorization = null;
        this.outboundHeaders = httpHeaders;
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
        this.codecWrapper = new HttpClientCodecWrapper();
        this.username = (String) ObjectUtil.checkNotNull(str, CognitoUserPoolsSignInProvider.AttributeKeys.USERNAME);
        this.password = (String) ObjectUtil.checkNotNull(str2, CognitoUserPoolsSignInProvider.AttributeKeys.PASSWORD);
        ByteBuf copiedBuffer = Unpooled.copiedBuffer(str + ':' + str2, CharsetUtil.UTF_8);
        try {
            ByteBuf encode = Base64.encode(copiedBuffer, false);
            try {
                this.authorization = new AsciiString("Basic " + encode.toString(CharsetUtil.US_ASCII));
                encode.release();
                this.outboundHeaders = httpHeaders;
                this.ignoreDefaultPortsInConnectHostHeader = z;
            } catch (Throwable th) {
                encode.release();
                throw th;
            }
        } finally {
            copiedBuffer.release();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.proxy.ProxyHandler
    public String authScheme() {
        return this.authorization != null ? AUTH_BASIC : "none";
    }

    public String username() {
        return this.username;
    }

    public String password() {
        return this.password;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.proxy.ProxyHandler
    protected void addCodec(ChannelHandlerContext channelHandlerContext) throws Exception {
        channelHandlerContext.pipeline().addBefore(channelHandlerContext.name(), null, this.codecWrapper);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.proxy.ProxyHandler
    protected void removeEncoder(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.codecWrapper.codec.removeOutboundHandler();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.proxy.ProxyHandler
    protected void removeDecoder(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.codecWrapper.codec.removeInboundHandler();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.proxy.ProxyHandler
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
        if (this.outboundHeaders != null) {
            defaultFullHttpRequest.headers().add(this.outboundHeaders);
        }
        return defaultFullHttpRequest;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.proxy.ProxyHandler
    protected boolean handleResponse(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (obj instanceof HttpResponse) {
            if (this.status != null) {
                throw new HttpProxyConnectException(exceptionMessage("too many responses"), null);
            }
            HttpResponse httpResponse = (HttpResponse) obj;
            this.status = httpResponse.status();
            this.inboundHeaders = httpResponse.headers();
        }
        boolean z = obj instanceof LastHttpContent;
        if (z) {
            HttpResponseStatus httpResponseStatus = this.status;
            if (httpResponseStatus == null) {
                throw new HttpProxyConnectException(exceptionMessage("missing response"), this.inboundHeaders);
            }
            if (httpResponseStatus.code() != 200) {
                throw new HttpProxyConnectException(exceptionMessage("status: " + this.status), this.inboundHeaders);
            }
        }
        return z;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
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

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class HttpClientCodecWrapper implements ChannelInboundHandler, ChannelOutboundHandler {
        final HttpClientCodec codec;

        private HttpClientCodecWrapper() {
            this.codec = new HttpClientCodec();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandler
        public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
            this.codec.handlerAdded(channelHandlerContext);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandler
        public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
            this.codec.handlerRemoved(channelHandlerContext);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
            this.codec.exceptionCaught(channelHandlerContext, th);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {
            this.codec.channelRegistered(channelHandlerContext);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void channelUnregistered(ChannelHandlerContext channelHandlerContext) throws Exception {
            this.codec.channelUnregistered(channelHandlerContext);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
            this.codec.channelActive(channelHandlerContext);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
            this.codec.channelInactive(channelHandlerContext);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
            this.codec.channelRead(channelHandlerContext, obj);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
            this.codec.channelReadComplete(channelHandlerContext);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
            this.codec.userEventTriggered(channelHandlerContext, obj);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
        public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) throws Exception {
            this.codec.channelWritabilityChanged(channelHandlerContext);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
        public void bind(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, ChannelPromise channelPromise) throws Exception {
            this.codec.bind(channelHandlerContext, socketAddress, channelPromise);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
        public void connect(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) throws Exception {
            this.codec.connect(channelHandlerContext, socketAddress, socketAddress2, channelPromise);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
        public void disconnect(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
            this.codec.disconnect(channelHandlerContext, channelPromise);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
        public void close(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
            this.codec.close(channelHandlerContext, channelPromise);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
        public void deregister(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
            this.codec.deregister(channelHandlerContext, channelPromise);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
        public void read(ChannelHandlerContext channelHandlerContext) throws Exception {
            this.codec.read(channelHandlerContext);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
        public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
            this.codec.write(channelHandlerContext, obj, channelPromise);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
        public void flush(ChannelHandlerContext channelHandlerContext) throws Exception {
            this.codec.flush(channelHandlerContext);
        }
    }
}
