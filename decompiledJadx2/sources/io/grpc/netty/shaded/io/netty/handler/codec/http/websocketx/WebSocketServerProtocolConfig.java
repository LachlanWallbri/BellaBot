package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx;

import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketDecoderConfig;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class WebSocketServerProtocolConfig {
    static final long DEFAULT_HANDSHAKE_TIMEOUT_MILLIS = 10000;
    private final boolean checkStartsWith;
    private final WebSocketDecoderConfig decoderConfig;
    private final boolean dropPongFrames;
    private final long forceCloseTimeoutMillis;
    private final boolean handleCloseFrames;
    private final long handshakeTimeoutMillis;
    private final WebSocketCloseStatus sendCloseFrame;
    private final String subprotocols;
    private final String websocketPath;

    private WebSocketServerProtocolConfig(String str, String str2, boolean z, long j, long j2, boolean z2, WebSocketCloseStatus webSocketCloseStatus, boolean z3, WebSocketDecoderConfig webSocketDecoderConfig) {
        this.websocketPath = str;
        this.subprotocols = str2;
        this.checkStartsWith = z;
        this.handshakeTimeoutMillis = ObjectUtil.checkPositive(j, "handshakeTimeoutMillis");
        this.forceCloseTimeoutMillis = j2;
        this.handleCloseFrames = z2;
        this.sendCloseFrame = webSocketCloseStatus;
        this.dropPongFrames = z3;
        this.decoderConfig = webSocketDecoderConfig == null ? WebSocketDecoderConfig.DEFAULT : webSocketDecoderConfig;
    }

    public String websocketPath() {
        return this.websocketPath;
    }

    public String subprotocols() {
        return this.subprotocols;
    }

    public boolean checkStartsWith() {
        return this.checkStartsWith;
    }

    public long handshakeTimeoutMillis() {
        return this.handshakeTimeoutMillis;
    }

    public long forceCloseTimeoutMillis() {
        return this.forceCloseTimeoutMillis;
    }

    public boolean handleCloseFrames() {
        return this.handleCloseFrames;
    }

    public WebSocketCloseStatus sendCloseFrame() {
        return this.sendCloseFrame;
    }

    public boolean dropPongFrames() {
        return this.dropPongFrames;
    }

    public WebSocketDecoderConfig decoderConfig() {
        return this.decoderConfig;
    }

    public String toString() {
        return "WebSocketServerProtocolConfig {websocketPath=" + this.websocketPath + ", subprotocols=" + this.subprotocols + ", checkStartsWith=" + this.checkStartsWith + ", handshakeTimeoutMillis=" + this.handshakeTimeoutMillis + ", forceCloseTimeoutMillis=" + this.forceCloseTimeoutMillis + ", handleCloseFrames=" + this.handleCloseFrames + ", sendCloseFrame=" + this.sendCloseFrame + ", dropPongFrames=" + this.dropPongFrames + ", decoderConfig=" + this.decoderConfig + "}";
    }

    public Builder toBuilder() {
        return new Builder();
    }

    public static Builder newBuilder() {
        return new Builder("/", null, false, DEFAULT_HANDSHAKE_TIMEOUT_MILLIS, 0L, true, WebSocketCloseStatus.NORMAL_CLOSURE, true, WebSocketDecoderConfig.DEFAULT);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Builder {
        private boolean checkStartsWith;
        private WebSocketDecoderConfig decoderConfig;
        private WebSocketDecoderConfig.Builder decoderConfigBuilder;
        private boolean dropPongFrames;
        private long forceCloseTimeoutMillis;
        private boolean handleCloseFrames;
        private long handshakeTimeoutMillis;
        private WebSocketCloseStatus sendCloseFrame;
        private String subprotocols;
        private String websocketPath;

        private Builder(WebSocketServerProtocolConfig webSocketServerProtocolConfig) {
            this(((WebSocketServerProtocolConfig) ObjectUtil.checkNotNull(webSocketServerProtocolConfig, "serverConfig")).websocketPath(), webSocketServerProtocolConfig.subprotocols(), webSocketServerProtocolConfig.checkStartsWith(), webSocketServerProtocolConfig.handshakeTimeoutMillis(), webSocketServerProtocolConfig.forceCloseTimeoutMillis(), webSocketServerProtocolConfig.handleCloseFrames(), webSocketServerProtocolConfig.sendCloseFrame(), webSocketServerProtocolConfig.dropPongFrames(), webSocketServerProtocolConfig.decoderConfig());
        }

        private Builder(String str, String str2, boolean z, long j, long j2, boolean z2, WebSocketCloseStatus webSocketCloseStatus, boolean z3, WebSocketDecoderConfig webSocketDecoderConfig) {
            this.websocketPath = str;
            this.subprotocols = str2;
            this.checkStartsWith = z;
            this.handshakeTimeoutMillis = j;
            this.forceCloseTimeoutMillis = j2;
            this.handleCloseFrames = z2;
            this.sendCloseFrame = webSocketCloseStatus;
            this.dropPongFrames = z3;
            this.decoderConfig = webSocketDecoderConfig;
        }

        public Builder websocketPath(String str) {
            this.websocketPath = str;
            return this;
        }

        public Builder subprotocols(String str) {
            this.subprotocols = str;
            return this;
        }

        public Builder checkStartsWith(boolean z) {
            this.checkStartsWith = z;
            return this;
        }

        public Builder handshakeTimeoutMillis(long j) {
            this.handshakeTimeoutMillis = j;
            return this;
        }

        public Builder forceCloseTimeoutMillis(long j) {
            this.forceCloseTimeoutMillis = j;
            return this;
        }

        public Builder handleCloseFrames(boolean z) {
            this.handleCloseFrames = z;
            return this;
        }

        public Builder sendCloseFrame(WebSocketCloseStatus webSocketCloseStatus) {
            this.sendCloseFrame = webSocketCloseStatus;
            return this;
        }

        public Builder dropPongFrames(boolean z) {
            this.dropPongFrames = z;
            return this;
        }

        public Builder decoderConfig(WebSocketDecoderConfig webSocketDecoderConfig) {
            if (webSocketDecoderConfig == null) {
                webSocketDecoderConfig = WebSocketDecoderConfig.DEFAULT;
            }
            this.decoderConfig = webSocketDecoderConfig;
            this.decoderConfigBuilder = null;
            return this;
        }

        private WebSocketDecoderConfig.Builder decoderConfigBuilder() {
            if (this.decoderConfigBuilder == null) {
                this.decoderConfigBuilder = this.decoderConfig.toBuilder();
            }
            return this.decoderConfigBuilder;
        }

        public Builder maxFramePayloadLength(int i) {
            decoderConfigBuilder().maxFramePayloadLength(i);
            return this;
        }

        public Builder expectMaskedFrames(boolean z) {
            decoderConfigBuilder().expectMaskedFrames(z);
            return this;
        }

        public Builder allowMaskMismatch(boolean z) {
            decoderConfigBuilder().allowMaskMismatch(z);
            return this;
        }

        public Builder allowExtensions(boolean z) {
            decoderConfigBuilder().allowExtensions(z);
            return this;
        }

        public Builder closeOnProtocolViolation(boolean z) {
            decoderConfigBuilder().closeOnProtocolViolation(z);
            return this;
        }

        public Builder withUTF8Validator(boolean z) {
            decoderConfigBuilder().withUTF8Validator(z);
            return this;
        }

        public WebSocketServerProtocolConfig build() {
            String str = this.websocketPath;
            String str2 = this.subprotocols;
            boolean z = this.checkStartsWith;
            long j = this.handshakeTimeoutMillis;
            long j2 = this.forceCloseTimeoutMillis;
            boolean z2 = this.handleCloseFrames;
            WebSocketCloseStatus webSocketCloseStatus = this.sendCloseFrame;
            boolean z3 = this.dropPongFrames;
            WebSocketDecoderConfig.Builder builder = this.decoderConfigBuilder;
            return new WebSocketServerProtocolConfig(str, str2, z, j, j2, z2, webSocketCloseStatus, z3, builder == null ? this.decoderConfig : builder.build());
        }
    }
}
