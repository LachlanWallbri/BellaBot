package org.jboss.netty.handler.codec.http.websocketx;

import java.util.LinkedHashSet;
import java.util.Set;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.http.HttpRequest;

/* loaded from: classes7.dex */
public abstract class WebSocketServerHandshaker {
    public static final ChannelFutureListener HANDSHAKE_LISTENER = new ChannelFutureListener() { // from class: org.jboss.netty.handler.codec.http.websocketx.WebSocketServerHandshaker.1
        @Override // org.jboss.netty.channel.ChannelFutureListener
        public void operationComplete(ChannelFuture channelFuture) throws Exception {
            if (channelFuture.isSuccess()) {
                return;
            }
            Channels.fireExceptionCaught(channelFuture.getChannel(), channelFuture.getCause());
        }
    };
    private final long maxFramePayloadLength;
    private String selectedSubprotocol;
    private final String[] subprotocols;
    private final WebSocketVersion version;
    private final String webSocketUrl;

    public abstract ChannelFuture close(Channel channel, CloseWebSocketFrame closeWebSocketFrame);

    public abstract ChannelFuture handshake(Channel channel, HttpRequest httpRequest);

    protected WebSocketServerHandshaker(WebSocketVersion webSocketVersion, String str, String str2) {
        this(webSocketVersion, str, str2, Long.MAX_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public WebSocketServerHandshaker(WebSocketVersion webSocketVersion, String str, String str2, long j) {
        this.version = webSocketVersion;
        this.webSocketUrl = str;
        if (str2 != null) {
            String[] split = str2.split(",");
            for (int i = 0; i < split.length; i++) {
                split[i] = split[i].trim();
            }
            this.subprotocols = split;
        } else {
            this.subprotocols = new String[0];
        }
        this.maxFramePayloadLength = j;
    }

    public String getWebSocketUrl() {
        return this.webSocketUrl;
    }

    public Set<String> getSubprotocols() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (String str : this.subprotocols) {
            linkedHashSet.add(str);
        }
        return linkedHashSet;
    }

    public WebSocketVersion getVersion() {
        return this.version;
    }

    public long getMaxFramePayloadLength() {
        return this.maxFramePayloadLength;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String selectSubprotocol(String str) {
        if (str != null && this.subprotocols.length != 0) {
            for (String str2 : str.split(",")) {
                String trim = str2.trim();
                for (String str3 : this.subprotocols) {
                    if (trim.equals(str3)) {
                        return trim;
                    }
                }
            }
        }
        return null;
    }

    public String getSelectedSubprotocol() {
        return this.selectedSubprotocol;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setSelectedSubprotocol(String str) {
        this.selectedSubprotocol = str;
    }
}
