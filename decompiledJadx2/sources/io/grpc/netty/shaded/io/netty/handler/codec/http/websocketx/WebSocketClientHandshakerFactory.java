package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx;

import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaders;
import java.net.URI;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class WebSocketClientHandshakerFactory {
    private WebSocketClientHandshakerFactory() {
    }

    public static WebSocketClientHandshaker newHandshaker(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders) {
        return newHandshaker(uri, webSocketVersion, str, z, httpHeaders, 65536);
    }

    public static WebSocketClientHandshaker newHandshaker(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i) {
        return newHandshaker(uri, webSocketVersion, str, z, httpHeaders, i, true, false);
    }

    public static WebSocketClientHandshaker newHandshaker(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i, boolean z2, boolean z3) {
        return newHandshaker(uri, webSocketVersion, str, z, httpHeaders, i, z2, z3, -1L);
    }

    public static WebSocketClientHandshaker newHandshaker(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i, boolean z2, boolean z3, long j) {
        if (webSocketVersion == WebSocketVersion.V13) {
            return new WebSocketClientHandshaker13(uri, WebSocketVersion.V13, str, z, httpHeaders, i, z2, z3, j);
        }
        if (webSocketVersion == WebSocketVersion.V08) {
            return new WebSocketClientHandshaker08(uri, WebSocketVersion.V08, str, z, httpHeaders, i, z2, z3, j);
        }
        if (webSocketVersion == WebSocketVersion.V07) {
            return new WebSocketClientHandshaker07(uri, WebSocketVersion.V07, str, z, httpHeaders, i, z2, z3, j);
        }
        if (webSocketVersion == WebSocketVersion.V00) {
            return new WebSocketClientHandshaker00(uri, WebSocketVersion.V00, str, httpHeaders, i, j);
        }
        throw new WebSocketHandshakeException("Protocol version " + webSocketVersion + " not supported.");
    }

    public static WebSocketClientHandshaker newHandshaker(URI uri, WebSocketVersion webSocketVersion, String str, boolean z, HttpHeaders httpHeaders, int i, boolean z2, boolean z3, long j, boolean z4) {
        if (webSocketVersion == WebSocketVersion.V13) {
            return new WebSocketClientHandshaker13(uri, WebSocketVersion.V13, str, z, httpHeaders, i, z2, z3, j, z4);
        }
        if (webSocketVersion == WebSocketVersion.V08) {
            return new WebSocketClientHandshaker08(uri, WebSocketVersion.V08, str, z, httpHeaders, i, z2, z3, j, z4);
        }
        if (webSocketVersion == WebSocketVersion.V07) {
            return new WebSocketClientHandshaker07(uri, WebSocketVersion.V07, str, z, httpHeaders, i, z2, z3, j, z4);
        }
        if (webSocketVersion == WebSocketVersion.V00) {
            return new WebSocketClientHandshaker00(uri, WebSocketVersion.V00, str, httpHeaders, i, j, z4);
        }
        throw new WebSocketHandshakeException("Protocol version " + webSocketVersion + " not supported.");
    }
}
