package org.jboss.netty.handler.codec.http.websocketx;

import java.net.URI;
import java.util.Map;

/* loaded from: classes7.dex */
public class WebSocketClientHandshakerFactory {
    public WebSocketClientHandshaker newHandshaker(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, Map<String, String> map) throws WebSocketHandshakeException {
        return newHandshaker(uri, webSocketVersion, str, z, map, Long.MAX_VALUE);
    }

    public WebSocketClientHandshaker newHandshaker(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, Map<String, String> map, long j) throws WebSocketHandshakeException {
        if (webSocketVersion == WebSocketVersion.V13) {
            return new WebSocketClientHandshaker13(uri, webSocketVersion, str, z, map, j);
        }
        if (webSocketVersion == WebSocketVersion.V08) {
            return new WebSocketClientHandshaker08(uri, webSocketVersion, str, z, map, j);
        }
        if (webSocketVersion == WebSocketVersion.V00) {
            return new WebSocketClientHandshaker00(uri, webSocketVersion, str, map, j);
        }
        throw new WebSocketHandshakeException("Protocol version " + webSocketVersion.toString() + " not supported.");
    }
}
