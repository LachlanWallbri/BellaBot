package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions;

import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketFrame;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface WebSocketExtensionFilter {
    public static final WebSocketExtensionFilter NEVER_SKIP = new WebSocketExtensionFilter() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionFilter.1
        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionFilter
        public boolean mustSkip(WebSocketFrame webSocketFrame) {
            return false;
        }
    };
    public static final WebSocketExtensionFilter ALWAYS_SKIP = new WebSocketExtensionFilter() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionFilter.2
        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionFilter
        public boolean mustSkip(WebSocketFrame webSocketFrame) {
            return true;
        }
    };

    boolean mustSkip(WebSocketFrame webSocketFrame);
}
