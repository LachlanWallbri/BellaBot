package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface WebSocketClientExtensionHandshaker {
    WebSocketClientExtension handshakeExtension(WebSocketExtensionData webSocketExtensionData);

    WebSocketExtensionData newRequestData();
}
