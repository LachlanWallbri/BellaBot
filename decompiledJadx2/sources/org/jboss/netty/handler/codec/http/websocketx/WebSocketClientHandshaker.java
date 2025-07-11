package org.jboss.netty.handler.codec.http.websocketx;

import java.net.URI;
import java.util.Map;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.handler.codec.http.HttpResponse;

/* loaded from: classes7.dex */
public abstract class WebSocketClientHandshaker {
    private String actualSubprotocol;
    protected final Map<String, String> customHeaders;
    private final String expectedSubprotocol;
    private boolean handshakeComplete;
    private final long maxFramePayloadLength;
    private final WebSocketVersion version;
    private final URI webSocketUrl;

    public abstract void finishHandshake(Channel channel, HttpResponse httpResponse);

    public abstract ChannelFuture handshake(Channel channel) throws Exception;

    public WebSocketClientHandshaker(URI uri, WebSocketVersion webSocketVersion, String str, Map<String, String> map) {
        this(uri, webSocketVersion, str, map, Long.MAX_VALUE);
    }

    public WebSocketClientHandshaker(URI uri, WebSocketVersion webSocketVersion, String str, Map<String, String> map, long j) {
        this.webSocketUrl = uri;
        this.version = webSocketVersion;
        this.expectedSubprotocol = str;
        this.customHeaders = map;
        this.maxFramePayloadLength = j;
    }

    public URI getWebSocketUrl() {
        return this.webSocketUrl;
    }

    public WebSocketVersion getVersion() {
        return this.version;
    }

    public long getMaxFramePayloadLength() {
        return this.maxFramePayloadLength;
    }

    public boolean isHandshakeComplete() {
        return this.handshakeComplete;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setHandshakeComplete() {
        this.handshakeComplete = true;
    }

    public String getExpectedSubprotocol() {
        return this.expectedSubprotocol;
    }

    public String getActualSubprotocol() {
        return this.actualSubprotocol;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setActualSubprotocol(String str) {
        this.actualSubprotocol = str;
    }
}
