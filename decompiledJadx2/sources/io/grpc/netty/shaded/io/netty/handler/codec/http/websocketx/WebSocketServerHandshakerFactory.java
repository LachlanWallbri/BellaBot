package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx;

import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderNames;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponseStatus;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpUtil;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpVersion;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class WebSocketServerHandshakerFactory {
    private final WebSocketDecoderConfig decoderConfig;
    private final String subprotocols;
    private final String webSocketURL;

    public WebSocketServerHandshakerFactory(String str, String str2, boolean z) {
        this(str, str2, z, 65536);
    }

    public WebSocketServerHandshakerFactory(String str, String str2, boolean z, int i) {
        this(str, str2, z, i, false);
    }

    public WebSocketServerHandshakerFactory(String str, String str2, boolean z, int i, boolean z2) {
        this(str, str2, WebSocketDecoderConfig.newBuilder().allowExtensions(z).maxFramePayloadLength(i).allowMaskMismatch(z2).build());
    }

    public WebSocketServerHandshakerFactory(String str, String str2, WebSocketDecoderConfig webSocketDecoderConfig) {
        this.webSocketURL = str;
        this.subprotocols = str2;
        this.decoderConfig = (WebSocketDecoderConfig) ObjectUtil.checkNotNull(webSocketDecoderConfig, "decoderConfig");
    }

    public WebSocketServerHandshaker newHandshaker(HttpRequest httpRequest) {
        String str = httpRequest.headers().get(HttpHeaderNames.SEC_WEBSOCKET_VERSION);
        if (str != null) {
            if (str.equals(WebSocketVersion.V13.toHttpHeaderValue())) {
                return new WebSocketServerHandshaker13(this.webSocketURL, this.subprotocols, this.decoderConfig);
            }
            if (str.equals(WebSocketVersion.V08.toHttpHeaderValue())) {
                return new WebSocketServerHandshaker08(this.webSocketURL, this.subprotocols, this.decoderConfig);
            }
            if (str.equals(WebSocketVersion.V07.toHttpHeaderValue())) {
                return new WebSocketServerHandshaker07(this.webSocketURL, this.subprotocols, this.decoderConfig);
            }
            return null;
        }
        return new WebSocketServerHandshaker00(this.webSocketURL, this.subprotocols, this.decoderConfig);
    }

    @Deprecated
    public static void sendUnsupportedWebSocketVersionResponse(Channel channel) {
        sendUnsupportedVersionResponse(channel);
    }

    public static ChannelFuture sendUnsupportedVersionResponse(Channel channel) {
        return sendUnsupportedVersionResponse(channel, channel.newPromise());
    }

    public static ChannelFuture sendUnsupportedVersionResponse(Channel channel, ChannelPromise channelPromise) {
        DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.UPGRADE_REQUIRED, channel.alloc().buffer(0));
        defaultFullHttpResponse.headers().set(HttpHeaderNames.SEC_WEBSOCKET_VERSION, WebSocketVersion.V13.toHttpHeaderValue());
        HttpUtil.setContentLength(defaultFullHttpResponse, 0L);
        return channel.writeAndFlush(defaultFullHttpResponse, channelPromise);
    }
}
