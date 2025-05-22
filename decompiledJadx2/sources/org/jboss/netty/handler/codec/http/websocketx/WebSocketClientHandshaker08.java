package org.jboss.netty.handler.codec.http.websocketx;

import java.net.URI;
import java.util.Map;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.handler.codec.http.DefaultHttpRequest;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.jboss.netty.handler.codec.http.HttpRequestEncoder;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseDecoder;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;
import org.jboss.netty.util.CharsetUtil;

/* loaded from: classes7.dex */
public class WebSocketClientHandshaker08 extends WebSocketClientHandshaker {
    public static final String MAGIC_GUID = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) WebSocketClientHandshaker08.class);
    private final boolean allowExtensions;
    private String expectedChallengeResponseString;

    public WebSocketClientHandshaker08(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, Map<String, String> map) {
        this(uri, webSocketVersion, str, z, map, Long.MAX_VALUE);
    }

    public WebSocketClientHandshaker08(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, Map<String, String> map, long j) {
        super(uri, webSocketVersion, str, map, j);
        this.allowExtensions = z;
    }

    @Override // org.jboss.netty.handler.codec.http.websocketx.WebSocketClientHandshaker
    public ChannelFuture handshake(Channel channel) throws Exception {
        URI webSocketUrl = getWebSocketUrl();
        String path = webSocketUrl.getPath();
        if (webSocketUrl.getQuery() != null && webSocketUrl.getQuery().length() > 0) {
            path = webSocketUrl.getPath() + "?" + webSocketUrl.getQuery();
        }
        String base64 = WebSocketUtil.base64(WebSocketUtil.randomBytes(16));
        this.expectedChallengeResponseString = WebSocketUtil.base64(WebSocketUtil.sha1((base64 + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes(CharsetUtil.US_ASCII.name())));
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("WS Version 08 Client Handshake key: %s. Expected response: %s.", base64, this.expectedChallengeResponseString));
        }
        DefaultHttpRequest defaultHttpRequest = new DefaultHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, path);
        defaultHttpRequest.addHeader("Upgrade", "WebSocket".toLowerCase());
        defaultHttpRequest.addHeader("Connection", "Upgrade");
        defaultHttpRequest.addHeader("Sec-WebSocket-Key", base64);
        defaultHttpRequest.addHeader("Host", webSocketUrl.getHost());
        int port = webSocketUrl.getPort();
        String str = "http://" + webSocketUrl.getHost();
        if (port != 80 && port != 443) {
            str = str + ":" + port;
        }
        defaultHttpRequest.addHeader("Sec-WebSocket-Origin", str);
        String expectedSubprotocol = getExpectedSubprotocol();
        if (expectedSubprotocol != null && !expectedSubprotocol.equals("")) {
            defaultHttpRequest.addHeader("Sec-WebSocket-Protocol", expectedSubprotocol);
        }
        defaultHttpRequest.addHeader("Sec-WebSocket-Version", "8");
        if (this.customHeaders != null) {
            for (String str2 : this.customHeaders.keySet()) {
                defaultHttpRequest.addHeader(str2, this.customHeaders.get(str2));
            }
        }
        ChannelFuture write = channel.write(defaultHttpRequest);
        channel.getPipeline().replace(HttpRequestEncoder.class, "ws-encoder", new WebSocket08FrameEncoder(true));
        return write;
    }

    @Override // org.jboss.netty.handler.codec.http.websocketx.WebSocketClientHandshaker
    public void finishHandshake(Channel channel, HttpResponse httpResponse) {
        if (!httpResponse.getStatus().equals(HttpResponseStatus.SWITCHING_PROTOCOLS)) {
            throw new WebSocketHandshakeException("Invalid handshake response status: " + httpResponse.getStatus());
        }
        String header = httpResponse.getHeader("Upgrade");
        if (header == null || !header.toLowerCase().equals("WebSocket".toLowerCase())) {
            throw new WebSocketHandshakeException("Invalid handshake response upgrade: " + httpResponse.getHeader("Upgrade"));
        }
        String header2 = httpResponse.getHeader("Connection");
        if (header2 == null || !header2.toLowerCase().equals("Upgrade".toLowerCase())) {
            throw new WebSocketHandshakeException("Invalid handshake response connection: " + httpResponse.getHeader("Connection"));
        }
        String header3 = httpResponse.getHeader("Sec-WebSocket-Accept");
        if (header3 == null || !header3.equals(this.expectedChallengeResponseString)) {
            throw new WebSocketHandshakeException(String.format("Invalid challenge. Actual: %s. Expected: %s", header3, this.expectedChallengeResponseString));
        }
        setActualSubprotocol(httpResponse.getHeader("Sec-WebSocket-Protocol"));
        setHandshakeComplete();
        ((HttpResponseDecoder) channel.getPipeline().get(HttpResponseDecoder.class)).replace("ws-decoder", new WebSocket08FrameDecoder(false, this.allowExtensions, getMaxFramePayloadLength()));
    }
}
