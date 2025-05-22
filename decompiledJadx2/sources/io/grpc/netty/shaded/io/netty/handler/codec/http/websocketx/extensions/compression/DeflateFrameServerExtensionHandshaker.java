package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.compression;

import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionData;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionEncoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionFilterProvider;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketServerExtension;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketServerExtensionHandshaker;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.util.Collections;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class DeflateFrameServerExtensionHandshaker implements WebSocketServerExtensionHandshaker {
    static final String DEFLATE_FRAME_EXTENSION = "deflate-frame";
    static final String X_WEBKIT_DEFLATE_FRAME_EXTENSION = "x-webkit-deflate-frame";
    private final int compressionLevel;
    private final WebSocketExtensionFilterProvider extensionFilterProvider;

    public DeflateFrameServerExtensionHandshaker() {
        this(6);
    }

    public DeflateFrameServerExtensionHandshaker(int i) {
        this(i, WebSocketExtensionFilterProvider.DEFAULT);
    }

    public DeflateFrameServerExtensionHandshaker(int i, WebSocketExtensionFilterProvider webSocketExtensionFilterProvider) {
        if (i < 0 || i > 9) {
            throw new IllegalArgumentException("compressionLevel: " + i + " (expected: 0-9)");
        }
        this.compressionLevel = i;
        this.extensionFilterProvider = (WebSocketExtensionFilterProvider) ObjectUtil.checkNotNull(webSocketExtensionFilterProvider, "extensionFilterProvider");
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketServerExtensionHandshaker
    public WebSocketServerExtension handshakeExtension(WebSocketExtensionData webSocketExtensionData) {
        if ((X_WEBKIT_DEFLATE_FRAME_EXTENSION.equals(webSocketExtensionData.name()) || DEFLATE_FRAME_EXTENSION.equals(webSocketExtensionData.name())) && webSocketExtensionData.parameters().isEmpty()) {
            return new DeflateFrameServerExtension(this.compressionLevel, webSocketExtensionData.name(), this.extensionFilterProvider);
        }
        return null;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static class DeflateFrameServerExtension implements WebSocketServerExtension {
        private final int compressionLevel;
        private final WebSocketExtensionFilterProvider extensionFilterProvider;
        private final String extensionName;

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtension
        public int rsv() {
            return 4;
        }

        DeflateFrameServerExtension(int i, String str, WebSocketExtensionFilterProvider webSocketExtensionFilterProvider) {
            this.extensionName = str;
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

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketServerExtension
        public WebSocketExtensionData newReponseData() {
            return new WebSocketExtensionData(this.extensionName, Collections.emptyMap());
        }
    }
}
