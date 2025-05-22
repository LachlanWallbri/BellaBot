package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.compression;

import io.grpc.netty.shaded.io.netty.handler.codec.compression.ZlibCodecFactory;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketClientExtension;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketClientExtensionHandshaker;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionData;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionEncoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionFilterProvider;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class PerMessageDeflateClientExtensionHandshaker implements WebSocketClientExtensionHandshaker {
    private final boolean allowClientNoContext;
    private final boolean allowClientWindowSize;
    private final int compressionLevel;
    private final WebSocketExtensionFilterProvider extensionFilterProvider;
    private final boolean requestedServerNoContext;
    private final int requestedServerWindowSize;

    public PerMessageDeflateClientExtensionHandshaker() {
        this(6, ZlibCodecFactory.isSupportingWindowSizeAndMemLevel(), 15, false, false);
    }

    public PerMessageDeflateClientExtensionHandshaker(int i, boolean z, int i2, boolean z2, boolean z3) {
        this(i, z, i2, z2, z3, WebSocketExtensionFilterProvider.DEFAULT);
    }

    public PerMessageDeflateClientExtensionHandshaker(int i, boolean z, int i2, boolean z2, boolean z3, WebSocketExtensionFilterProvider webSocketExtensionFilterProvider) {
        if (i2 > 15 || i2 < 8) {
            throw new IllegalArgumentException("requestedServerWindowSize: " + i2 + " (expected: 8-15)");
        }
        if (i < 0 || i > 9) {
            throw new IllegalArgumentException("compressionLevel: " + i + " (expected: 0-9)");
        }
        this.compressionLevel = i;
        this.allowClientWindowSize = z;
        this.requestedServerWindowSize = i2;
        this.allowClientNoContext = z2;
        this.requestedServerNoContext = z3;
        this.extensionFilterProvider = (WebSocketExtensionFilterProvider) ObjectUtil.checkNotNull(webSocketExtensionFilterProvider, "extensionFilterProvider");
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketClientExtensionHandshaker
    public WebSocketExtensionData newRequestData() {
        HashMap hashMap = new HashMap(4);
        if (this.requestedServerWindowSize != 15) {
            hashMap.put("server_no_context_takeover", null);
        }
        if (this.allowClientNoContext) {
            hashMap.put("client_no_context_takeover", null);
        }
        int i = this.requestedServerWindowSize;
        if (i != 15) {
            hashMap.put("server_max_window_bits", Integer.toString(i));
        }
        if (this.allowClientWindowSize) {
            hashMap.put("client_max_window_bits", null);
        }
        return new WebSocketExtensionData("permessage-deflate", hashMap);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketClientExtensionHandshaker
    public WebSocketClientExtension handshakeExtension(WebSocketExtensionData webSocketExtensionData) {
        if (!"permessage-deflate".equals(webSocketExtensionData.name())) {
            return null;
        }
        Iterator<Map.Entry<String, String>> it = webSocketExtensionData.parameters().entrySet().iterator();
        boolean z = false;
        int i = 15;
        int i2 = 15;
        boolean z2 = true;
        boolean z3 = false;
        boolean z4 = false;
        while (z2 && it.hasNext()) {
            Map.Entry<String, String> next = it.next();
            if ("client_max_window_bits".equalsIgnoreCase(next.getKey())) {
                if (this.allowClientWindowSize) {
                    i2 = Integer.parseInt(next.getValue());
                } else {
                    z2 = false;
                }
            } else if ("server_max_window_bits".equalsIgnoreCase(next.getKey())) {
                int parseInt = Integer.parseInt(next.getValue());
                if (i2 > 15 || i2 < 8) {
                    z2 = false;
                }
                i = parseInt;
            } else if ("client_no_context_takeover".equalsIgnoreCase(next.getKey())) {
                if (this.allowClientNoContext) {
                    z4 = true;
                } else {
                    z2 = false;
                }
            } else {
                if ("server_no_context_takeover".equalsIgnoreCase(next.getKey()) && this.requestedServerNoContext) {
                    z3 = true;
                }
                z2 = false;
            }
        }
        if ((!this.requestedServerNoContext || z3) && this.requestedServerWindowSize == i) {
            z = z2;
        }
        if (z) {
            return new PermessageDeflateExtension(z3, i, z4, i2, this.extensionFilterProvider);
        }
        return null;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private final class PermessageDeflateExtension implements WebSocketClientExtension {
        private final boolean clientNoContext;
        private final int clientWindowSize;
        private final WebSocketExtensionFilterProvider extensionFilterProvider;
        private final boolean serverNoContext;
        private final int serverWindowSize;

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtension
        public int rsv() {
            return 4;
        }

        PermessageDeflateExtension(boolean z, int i, boolean z2, int i2, WebSocketExtensionFilterProvider webSocketExtensionFilterProvider) {
            this.serverNoContext = z;
            this.serverWindowSize = i;
            this.clientNoContext = z2;
            this.clientWindowSize = i2;
            this.extensionFilterProvider = webSocketExtensionFilterProvider;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtension
        public WebSocketExtensionEncoder newExtensionEncoder() {
            return new PerMessageDeflateEncoder(PerMessageDeflateClientExtensionHandshaker.this.compressionLevel, this.clientWindowSize, this.clientNoContext, this.extensionFilterProvider.encoderFilter());
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtension
        public WebSocketExtensionDecoder newExtensionDecoder() {
            return new PerMessageDeflateDecoder(this.serverNoContext, this.extensionFilterProvider.decoderFilter());
        }
    }
}
