package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.compression;

import io.grpc.netty.shaded.io.netty.handler.codec.compression.ZlibCodecFactory;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionData;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionEncoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionFilterProvider;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketServerExtension;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketServerExtensionHandshaker;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class PerMessageDeflateServerExtensionHandshaker implements WebSocketServerExtensionHandshaker {
    static final String CLIENT_MAX_WINDOW = "client_max_window_bits";
    static final String CLIENT_NO_CONTEXT = "client_no_context_takeover";
    public static final int MAX_WINDOW_SIZE = 15;
    public static final int MIN_WINDOW_SIZE = 8;
    static final String PERMESSAGE_DEFLATE_EXTENSION = "permessage-deflate";
    static final String SERVER_MAX_WINDOW = "server_max_window_bits";
    static final String SERVER_NO_CONTEXT = "server_no_context_takeover";
    private final boolean allowServerNoContext;
    private final boolean allowServerWindowSize;
    private final int compressionLevel;
    private final WebSocketExtensionFilterProvider extensionFilterProvider;
    private final boolean preferredClientNoContext;
    private final int preferredClientWindowSize;

    public PerMessageDeflateServerExtensionHandshaker() {
        this(6, ZlibCodecFactory.isSupportingWindowSizeAndMemLevel(), 15, false, false);
    }

    public PerMessageDeflateServerExtensionHandshaker(int i, boolean z, int i2, boolean z2, boolean z3) {
        this(i, z, i2, z2, z3, WebSocketExtensionFilterProvider.DEFAULT);
    }

    public PerMessageDeflateServerExtensionHandshaker(int i, boolean z, int i2, boolean z2, boolean z3, WebSocketExtensionFilterProvider webSocketExtensionFilterProvider) {
        if (i2 > 15 || i2 < 8) {
            throw new IllegalArgumentException("preferredServerWindowSize: " + i2 + " (expected: 8-15)");
        }
        if (i < 0 || i > 9) {
            throw new IllegalArgumentException("compressionLevel: " + i + " (expected: 0-9)");
        }
        this.compressionLevel = i;
        this.allowServerWindowSize = z;
        this.preferredClientWindowSize = i2;
        this.allowServerNoContext = z2;
        this.preferredClientNoContext = z3;
        this.extensionFilterProvider = (WebSocketExtensionFilterProvider) ObjectUtil.checkNotNull(webSocketExtensionFilterProvider, "extensionFilterProvider");
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketServerExtensionHandshaker
    public WebSocketServerExtension handshakeExtension(WebSocketExtensionData webSocketExtensionData) {
        if (!PERMESSAGE_DEFLATE_EXTENSION.equals(webSocketExtensionData.name())) {
            return null;
        }
        Iterator<Map.Entry<String, String>> it = webSocketExtensionData.parameters().entrySet().iterator();
        boolean z = true;
        int i = 15;
        int i2 = 15;
        boolean z2 = false;
        boolean z3 = false;
        while (z && it.hasNext()) {
            Map.Entry<String, String> next = it.next();
            if (CLIENT_MAX_WINDOW.equalsIgnoreCase(next.getKey())) {
                i2 = this.preferredClientWindowSize;
            } else if (SERVER_MAX_WINDOW.equalsIgnoreCase(next.getKey())) {
                if (this.allowServerWindowSize) {
                    int parseInt = Integer.parseInt(next.getValue());
                    if (parseInt > 15 || parseInt < 8) {
                        z = false;
                    }
                    i = parseInt;
                } else {
                    z = false;
                }
            } else if (CLIENT_NO_CONTEXT.equalsIgnoreCase(next.getKey())) {
                z3 = this.preferredClientNoContext;
            } else {
                if (SERVER_NO_CONTEXT.equalsIgnoreCase(next.getKey()) && this.allowServerNoContext) {
                    z2 = true;
                }
                z = false;
            }
        }
        if (z) {
            return new PermessageDeflateExtension(this.compressionLevel, z2, i, z3, i2, this.extensionFilterProvider);
        }
        return null;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static class PermessageDeflateExtension implements WebSocketServerExtension {
        private final boolean clientNoContext;
        private final int clientWindowSize;
        private final int compressionLevel;
        private final WebSocketExtensionFilterProvider extensionFilterProvider;
        private final boolean serverNoContext;
        private final int serverWindowSize;

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtension
        public int rsv() {
            return 4;
        }

        PermessageDeflateExtension(int i, boolean z, int i2, boolean z2, int i3, WebSocketExtensionFilterProvider webSocketExtensionFilterProvider) {
            this.compressionLevel = i;
            this.serverNoContext = z;
            this.serverWindowSize = i2;
            this.clientNoContext = z2;
            this.clientWindowSize = i3;
            this.extensionFilterProvider = webSocketExtensionFilterProvider;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtension
        public WebSocketExtensionEncoder newExtensionEncoder() {
            return new PerMessageDeflateEncoder(this.compressionLevel, this.serverWindowSize, this.serverNoContext, this.extensionFilterProvider.encoderFilter());
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtension
        public WebSocketExtensionDecoder newExtensionDecoder() {
            return new PerMessageDeflateDecoder(this.clientNoContext, this.extensionFilterProvider.decoderFilter());
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketServerExtension
        public WebSocketExtensionData newReponseData() {
            HashMap hashMap = new HashMap(4);
            if (this.serverNoContext) {
                hashMap.put(PerMessageDeflateServerExtensionHandshaker.SERVER_NO_CONTEXT, null);
            }
            if (this.clientNoContext) {
                hashMap.put(PerMessageDeflateServerExtensionHandshaker.CLIENT_NO_CONTEXT, null);
            }
            int i = this.serverWindowSize;
            if (i != 15) {
                hashMap.put(PerMessageDeflateServerExtensionHandshaker.SERVER_MAX_WINDOW, Integer.toString(i));
            }
            int i2 = this.clientWindowSize;
            if (i2 != 15) {
                hashMap.put(PerMessageDeflateServerExtensionHandshaker.CLIENT_MAX_WINDOW, Integer.toString(i2));
            }
            return new WebSocketExtensionData(PerMessageDeflateServerExtensionHandshaker.PERMESSAGE_DEFLATE_EXTENSION, hashMap);
        }
    }
}
