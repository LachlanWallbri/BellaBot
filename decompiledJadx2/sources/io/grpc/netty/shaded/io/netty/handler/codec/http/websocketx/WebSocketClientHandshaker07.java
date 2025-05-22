package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx;

import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderNames;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderValues;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaders;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMethod;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponseStatus;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpVersion;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.URI;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class WebSocketClientHandshaker07 extends WebSocketClientHandshaker {
    public static final String MAGIC_GUID = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) WebSocketClientHandshaker07.class);
    private final boolean allowExtensions;
    private final boolean allowMaskMismatch;
    private String expectedChallengeResponseString;
    private final boolean performMasking;

    public WebSocketClientHandshaker07(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i) {
        this(uri, webSocketVersion, str, z, httpHeaders, i, true, false);
    }

    public WebSocketClientHandshaker07(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i, boolean z2, boolean z3) {
        this(uri, webSocketVersion, str, z, httpHeaders, i, z2, z3, 10000L);
    }

    public WebSocketClientHandshaker07(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i, boolean z2, boolean z3, long j) {
        this(uri, webSocketVersion, str, z, httpHeaders, i, z2, z3, j, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WebSocketClientHandshaker07(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i, boolean z2, boolean z3, long j, boolean z4) {
        super(uri, webSocketVersion, str, httpHeaders, i, j, z4);
        this.allowExtensions = z;
        this.performMasking = z2;
        this.allowMaskMismatch = z3;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker
    protected FullHttpRequest newHandshakeRequest() {
        URI uri = uri();
        String base64 = WebSocketUtil.base64(WebSocketUtil.randomBytes(16));
        this.expectedChallengeResponseString = WebSocketUtil.base64(WebSocketUtil.sha1((base64 + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes(CharsetUtil.US_ASCII)));
        if (logger.isDebugEnabled()) {
            logger.debug("WebSocket version 07 client handshake key: {}, expected response: {}", base64, this.expectedChallengeResponseString);
        }
        DefaultFullHttpRequest defaultFullHttpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, upgradeUrl(uri), Unpooled.EMPTY_BUFFER);
        HttpHeaders headers = defaultFullHttpRequest.headers();
        if (this.customHeaders != null) {
            headers.add(this.customHeaders);
            if (!headers.contains(HttpHeaderNames.HOST)) {
                headers.set(HttpHeaderNames.HOST, websocketHostValue(uri));
            }
        } else {
            headers.set(HttpHeaderNames.HOST, websocketHostValue(uri));
        }
        headers.set(HttpHeaderNames.UPGRADE, HttpHeaderValues.WEBSOCKET).set(HttpHeaderNames.CONNECTION, HttpHeaderValues.UPGRADE).set(HttpHeaderNames.SEC_WEBSOCKET_KEY, base64);
        if (!headers.contains(HttpHeaderNames.SEC_WEBSOCKET_ORIGIN)) {
            headers.set(HttpHeaderNames.SEC_WEBSOCKET_ORIGIN, websocketOriginValue(uri));
        }
        String expectedSubprotocol = expectedSubprotocol();
        if (expectedSubprotocol != null && !expectedSubprotocol.isEmpty()) {
            headers.set(HttpHeaderNames.SEC_WEBSOCKET_PROTOCOL, expectedSubprotocol);
        }
        headers.set(HttpHeaderNames.SEC_WEBSOCKET_VERSION, version().toAsciiString());
        return defaultFullHttpRequest;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker
    protected void verify(FullHttpResponse fullHttpResponse) {
        HttpResponseStatus httpResponseStatus = HttpResponseStatus.SWITCHING_PROTOCOLS;
        HttpHeaders headers = fullHttpResponse.headers();
        if (!fullHttpResponse.status().equals(httpResponseStatus)) {
            throw new WebSocketHandshakeException("Invalid handshake response getStatus: " + fullHttpResponse.status());
        }
        String str = headers.get(HttpHeaderNames.UPGRADE);
        if (!HttpHeaderValues.WEBSOCKET.contentEqualsIgnoreCase(str)) {
            throw new WebSocketHandshakeException("Invalid handshake response upgrade: " + ((Object) str));
        }
        if (!headers.containsValue(HttpHeaderNames.CONNECTION, HttpHeaderValues.UPGRADE, true)) {
            throw new WebSocketHandshakeException("Invalid handshake response connection: " + headers.get(HttpHeaderNames.CONNECTION));
        }
        String str2 = headers.get(HttpHeaderNames.SEC_WEBSOCKET_ACCEPT);
        if (str2 == null || !str2.equals(this.expectedChallengeResponseString)) {
            throw new WebSocketHandshakeException(String.format("Invalid challenge. Actual: %s. Expected: %s", str2, this.expectedChallengeResponseString));
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker
    protected WebSocketFrameDecoder newWebsocketDecoder() {
        return new WebSocket07FrameDecoder(false, this.allowExtensions, maxFramePayloadLength(), this.allowMaskMismatch);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker
    protected WebSocketFrameEncoder newWebSocketEncoder() {
        return new WebSocket07FrameEncoder(this.performMasking);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker
    public WebSocketClientHandshaker07 setForceCloseTimeoutMillis(long j) {
        super.setForceCloseTimeoutMillis(j);
        return this;
    }
}
