package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.compression;

import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketClientExtension;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketClientExtensionHandshaker;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionData;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionEncoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionFilterProvider;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.util.Collections;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class DeflateFrameClientExtensionHandshaker implements WebSocketClientExtensionHandshaker {
    private final int compressionLevel;
    private final WebSocketExtensionFilterProvider extensionFilterProvider;
    private final boolean useWebkitExtensionName;

    public DeflateFrameClientExtensionHandshaker(boolean z) {
        this(6, z);
    }

    public DeflateFrameClientExtensionHandshaker(int i, boolean z) {
        this(i, z, WebSocketExtensionFilterProvider.DEFAULT);
    }

    public DeflateFrameClientExtensionHandshaker(int i, boolean z, WebSocketExtensionFilterProvider webSocketExtensionFilterProvider) {
        if (i < 0 || i > 9) {
            throw new IllegalArgumentException("compressionLevel: " + i + " (expected: 0-9)");
        }
        this.compressionLevel = i;
        this.useWebkitExtensionName = z;
        this.extensionFilterProvider = (WebSocketExtensionFilterProvider) ObjectUtil.checkNotNull(webSocketExtensionFilterProvider, "extensionFilterProvider");
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketClientExtensionHandshaker
    public WebSocketExtensionData newRequestData() {
        return new WebSocketExtensionData(this.useWebkitExtensionName ? "x-webkit-deflate-frame" : "deflate-frame", Collections.emptyMap());
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketClientExtensionHandshaker
    public WebSocketClientExtension handshakeExtension(WebSocketExtensionData webSocketExtensionData) {
        if (("x-webkit-deflate-frame".equals(webSocketExtensionData.name()) || "deflate-frame".equals(webSocketExtensionData.name())) && webSocketExtensionData.parameters().isEmpty()) {
            return new DeflateFrameClientExtension(this.compressionLevel, this.extensionFilterProvider);
        }
        return null;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static class DeflateFrameClientExtension implements WebSocketClientExtension {
        private final int compressionLevel;
        private final WebSocketExtensionFilterProvider extensionFilterProvider;

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtension
        public int rsv() {
            return 4;
        }

        DeflateFrameClientExtension(int i, WebSocketExtensionFilterProvider webSocketExtensionFilterProvider) {
            this.compressionLevel = i;
            this.extensionFilterProvider = webSocketExtensionFilterProvider;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtension
        public WebSocketExtensionEncoder newExtensionEncoder() {
            return new PerFrameDeflateEncoder(this.compressionLevel, 15, false, this.extensionFilterProvider.encoderFilter());
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtension
        public WebSocketExtensionDecoder newExtensionDecoder() {
            return new PerFrameDeflateDecoder(false, this.extensionFilterProvider.decoderFilter());
        }
    }
}
