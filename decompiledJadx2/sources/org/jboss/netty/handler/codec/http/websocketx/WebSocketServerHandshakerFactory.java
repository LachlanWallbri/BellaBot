package org.jboss.netty.handler.codec.http.websocketx;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;

/* loaded from: classes7.dex */
public class WebSocketServerHandshakerFactory {
    private final boolean allowExtensions;
    private final long maxFramePayloadLength;
    private final String subprotocols;
    private final String webSocketURL;

    public WebSocketServerHandshakerFactory(String str, String str2, boolean z) {
        this(str, str2, z, Long.MAX_VALUE);
    }

    public WebSocketServerHandshakerFactory(String str, String str2, boolean z, long j) {
        this.webSocketURL = str;
        this.subprotocols = str2;
        this.allowExtensions = z;
        this.maxFramePayloadLength = j;
    }

    public WebSocketServerHandshaker newHandshaker(HttpRequest httpRequest) {
        String header = httpRequest.getHeader("Sec-WebSocket-Version");
        if (header != null) {
            if (header.equals(WebSocketVersion.V13.toHttpHeaderValue())) {
                return new WebSocketServerHandshaker13(this.webSocketURL, this.subprotocols, this.allowExtensions, this.maxFramePayloadLength);
            }
            if (header.equals(WebSocketVersion.V08.toHttpHeaderValue())) {
                return new WebSocketServerHandshaker08(this.webSocketURL, this.subprotocols, this.allowExtensions, this.maxFramePayloadLength);
            }
            return null;
        }
        return new WebSocketServerHandshaker00(this.webSocketURL, this.subprotocols, this.maxFramePayloadLength);
    }

    public void sendUnsupportedWebSocketVersionResponse(Channel channel) {
        DefaultHttpResponse defaultHttpResponse = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.SWITCHING_PROTOCOLS);
        defaultHttpResponse.setStatus(HttpResponseStatus.UPGRADE_REQUIRED);
        defaultHttpResponse.setHeader("Sec-WebSocket-Version", WebSocketVersion.V13.toHttpHeaderValue());
        channel.write(defaultHttpResponse);
    }
}
