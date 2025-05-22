package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderNames;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderValues;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaders;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponseStatus;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpVersion;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class WebSocketServerHandshaker00 extends WebSocketServerHandshaker {
    private static final Pattern BEGINNING_DIGIT = Pattern.compile("[^0-9]");
    private static final Pattern BEGINNING_SPACE = Pattern.compile("[^ ]");

    public WebSocketServerHandshaker00(String str, String str2, int i) {
        this(str, str2, WebSocketDecoderConfig.newBuilder().maxFramePayloadLength(i).build());
    }

    public WebSocketServerHandshaker00(String str, String str2, WebSocketDecoderConfig webSocketDecoderConfig) {
        super(WebSocketVersion.V00, str, str2, webSocketDecoderConfig);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker
    protected FullHttpResponse newHandshakeResponse(FullHttpRequest fullHttpRequest, HttpHeaders httpHeaders) {
        if (!fullHttpRequest.headers().containsValue(HttpHeaderNames.CONNECTION, HttpHeaderValues.UPGRADE, true) || !HttpHeaderValues.WEBSOCKET.contentEqualsIgnoreCase(fullHttpRequest.headers().get(HttpHeaderNames.UPGRADE))) {
            throw new WebSocketHandshakeException("not a WebSocket handshake request: missing upgrade");
        }
        boolean z = fullHttpRequest.headers().contains(HttpHeaderNames.SEC_WEBSOCKET_KEY1) && fullHttpRequest.headers().contains(HttpHeaderNames.SEC_WEBSOCKET_KEY2);
        String str = fullHttpRequest.headers().get(HttpHeaderNames.ORIGIN);
        if (str == null && !z) {
            throw new WebSocketHandshakeException("Missing origin header, got only " + fullHttpRequest.headers().names());
        }
        DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, new HttpResponseStatus(101, z ? "WebSocket Protocol Handshake" : "Web Socket Protocol Handshake"), fullHttpRequest.content().alloc().buffer(0));
        if (httpHeaders != null) {
            defaultFullHttpResponse.headers().add(httpHeaders);
        }
        defaultFullHttpResponse.headers().set(HttpHeaderNames.UPGRADE, HttpHeaderValues.WEBSOCKET).set(HttpHeaderNames.CONNECTION, HttpHeaderValues.UPGRADE);
        if (z) {
            defaultFullHttpResponse.headers().add(HttpHeaderNames.SEC_WEBSOCKET_ORIGIN, str);
            defaultFullHttpResponse.headers().add(HttpHeaderNames.SEC_WEBSOCKET_LOCATION, uri());
            String str2 = fullHttpRequest.headers().get(HttpHeaderNames.SEC_WEBSOCKET_PROTOCOL);
            if (str2 != null) {
                String selectSubprotocol = selectSubprotocol(str2);
                if (selectSubprotocol == null) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Requested subprotocol(s) not supported: {}", str2);
                    }
                } else {
                    defaultFullHttpResponse.headers().add(HttpHeaderNames.SEC_WEBSOCKET_PROTOCOL, selectSubprotocol);
                }
            }
            String str3 = fullHttpRequest.headers().get(HttpHeaderNames.SEC_WEBSOCKET_KEY1);
            String str4 = fullHttpRequest.headers().get(HttpHeaderNames.SEC_WEBSOCKET_KEY2);
            int parseLong = (int) (Long.parseLong(BEGINNING_DIGIT.matcher(str3).replaceAll("")) / BEGINNING_SPACE.matcher(str3).replaceAll("").length());
            int parseLong2 = (int) (Long.parseLong(BEGINNING_DIGIT.matcher(str4).replaceAll("")) / BEGINNING_SPACE.matcher(str4).replaceAll("").length());
            long readLong = fullHttpRequest.content().readLong();
            ByteBuf index = Unpooled.wrappedBuffer(new byte[16]).setIndex(0, 0);
            index.writeInt(parseLong);
            index.writeInt(parseLong2);
            index.writeLong(readLong);
            defaultFullHttpResponse.content().writeBytes(WebSocketUtil.md5(index.array()));
        } else {
            defaultFullHttpResponse.headers().add(HttpHeaderNames.WEBSOCKET_ORIGIN, str);
            defaultFullHttpResponse.headers().add(HttpHeaderNames.WEBSOCKET_LOCATION, uri());
            String str5 = fullHttpRequest.headers().get(HttpHeaderNames.WEBSOCKET_PROTOCOL);
            if (str5 != null) {
                defaultFullHttpResponse.headers().add(HttpHeaderNames.WEBSOCKET_PROTOCOL, selectSubprotocol(str5));
            }
        }
        return defaultFullHttpResponse;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker
    public ChannelFuture close(Channel channel, CloseWebSocketFrame closeWebSocketFrame, ChannelPromise channelPromise) {
        return channel.writeAndFlush(closeWebSocketFrame, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker
    protected WebSocketFrameDecoder newWebsocketDecoder() {
        return new WebSocket00FrameDecoder(decoderConfig());
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker
    protected WebSocketFrameEncoder newWebSocketEncoder() {
        return new WebSocket00FrameEncoder();
    }
}
