package io.netty.handler.codec.http.websocketx.extensions;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface WebSocketClientExtensionHandshaker {
    WebSocketClientExtension handshakeExtension(WebSocketExtensionData webSocketExtensionData);

    WebSocketExtensionData newRequestData();
}
