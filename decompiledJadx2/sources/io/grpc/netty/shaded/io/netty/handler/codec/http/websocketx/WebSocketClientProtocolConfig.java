package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx;

import io.grpc.netty.shaded.io.netty.handler.codec.http.EmptyHttpHeaders;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaders;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.net.URI;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class WebSocketClientProtocolConfig {
    static final boolean DEFAULT_ALLOW_MASK_MISMATCH = false;
    static final boolean DEFAULT_DROP_PONG_FRAMES = true;
    static final boolean DEFAULT_HANDLE_CLOSE_FRAMES = true;
    static final boolean DEFAULT_PERFORM_MASKING = true;
    private final boolean absoluteUpgradeUrl;
    private final boolean allowExtensions;
    private final boolean allowMaskMismatch;
    private final HttpHeaders customHeaders;
    private final boolean dropPongFrames;
    private final long forceCloseTimeoutMillis;
    private final boolean handleCloseFrames;
    private final long handshakeTimeoutMillis;
    private final int maxFramePayloadLength;
    private final boolean performMasking;
    private final WebSocketCloseStatus sendCloseFrame;
    private final String subprotocol;
    private final WebSocketVersion version;
    private final URI webSocketUri;

    private WebSocketClientProtocolConfig(URI uri, String str, WebSocketVersion webSocketVersion, boolean z, HttpHeaders httpHeaders, int i, boolean z2, boolean z3, boolean z4, WebSocketCloseStatus webSocketCloseStatus, boolean z5, long j, long j2, boolean z6) {
        this.webSocketUri = uri;
        this.subprotocol = str;
        this.version = webSocketVersion;
        this.allowExtensions = z;
        this.customHeaders = httpHeaders;
        this.maxFramePayloadLength = i;
        this.performMasking = z2;
        this.allowMaskMismatch = z3;
        this.forceCloseTimeoutMillis = j2;
        this.handleCloseFrames = z4;
        this.sendCloseFrame = webSocketCloseStatus;
        this.dropPongFrames = z5;
        this.handshakeTimeoutMillis = ObjectUtil.checkPositive(j, "handshakeTimeoutMillis");
        this.absoluteUpgradeUrl = z6;
    }

    public URI webSocketUri() {
        return this.webSocketUri;
    }

    public String subprotocol() {
        return this.subprotocol;
    }

    public WebSocketVersion version() {
        return this.version;
    }

    public boolean allowExtensions() {
        return this.allowExtensions;
    }

    public HttpHeaders customHeaders() {
        return this.customHeaders;
    }

    public int maxFramePayloadLength() {
        return this.maxFramePayloadLength;
    }

    public boolean performMasking() {
        return this.performMasking;
    }

    public boolean allowMaskMismatch() {
        return this.allowMaskMismatch;
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

    public long handshakeTimeoutMillis() {
        return this.handshakeTimeoutMillis;
    }

    public long forceCloseTimeoutMillis() {
        return this.forceCloseTimeoutMillis;
    }

    public boolean absoluteUpgradeUrl() {
        return this.absoluteUpgradeUrl;
    }

    public String toString() {
        return "WebSocketClientProtocolConfig {webSocketUri=" + this.webSocketUri + ", subprotocol=" + this.subprotocol + ", version=" + this.version + ", allowExtensions=" + this.allowExtensions + ", customHeaders=" + this.customHeaders + ", maxFramePayloadLength=" + this.maxFramePayloadLength + ", performMasking=" + this.performMasking + ", allowMaskMismatch=" + this.allowMaskMismatch + ", handleCloseFrames=" + this.handleCloseFrames + ", sendCloseFrame=" + this.sendCloseFrame + ", dropPongFrames=" + this.dropPongFrames + ", handshakeTimeoutMillis=" + this.handshakeTimeoutMillis + ", forceCloseTimeoutMillis=" + this.forceCloseTimeoutMillis + ", absoluteUpgradeUrl=" + this.absoluteUpgradeUrl + "}";
    }

    public Builder toBuilder() {
        return new Builder();
    }

    public static Builder newBuilder() {
        return new Builder(URI.create("https://localhost/"), null, WebSocketVersion.V13, false, EmptyHttpHeaders.INSTANCE, 65536, true, false, true, WebSocketCloseStatus.NORMAL_CLOSURE, true, 10000L, -1L, false);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Builder {
        private boolean absoluteUpgradeUrl;
        private boolean allowExtensions;
        private boolean allowMaskMismatch;
        private HttpHeaders customHeaders;
        private boolean dropPongFrames;
        private long forceCloseTimeoutMillis;
        private boolean handleCloseFrames;
        private long handshakeTimeoutMillis;
        private int maxFramePayloadLength;
        private boolean performMasking;
        private WebSocketCloseStatus sendCloseFrame;
        private String subprotocol;
        private WebSocketVersion version;
        private URI webSocketUri;

        private Builder(WebSocketClientProtocolConfig webSocketClientProtocolConfig) {
            this(((WebSocketClientProtocolConfig) ObjectUtil.checkNotNull(webSocketClientProtocolConfig, "clientConfig")).webSocketUri(), webSocketClientProtocolConfig.subprotocol(), webSocketClientProtocolConfig.version(), webSocketClientProtocolConfig.allowExtensions(), webSocketClientProtocolConfig.customHeaders(), webSocketClientProtocolConfig.maxFramePayloadLength(), webSocketClientProtocolConfig.performMasking(), webSocketClientProtocolConfig.allowMaskMismatch(), webSocketClientProtocolConfig.handleCloseFrames(), webSocketClientProtocolConfig.sendCloseFrame(), webSocketClientProtocolConfig.dropPongFrames(), webSocketClientProtocolConfig.handshakeTimeoutMillis(), webSocketClientProtocolConfig.forceCloseTimeoutMillis(), webSocketClientProtocolConfig.absoluteUpgradeUrl());
        }

        private Builder(URI uri, String str, WebSocketVersion webSocketVersion, boolean z, HttpHeaders httpHeaders, int i, boolean z2, boolean z3, boolean z4, WebSocketCloseStatus webSocketCloseStatus, boolean z5, long j, long j2, boolean z6) {
            this.webSocketUri = uri;
            this.subprotocol = str;
            this.version = webSocketVersion;
            this.allowExtensions = z;
            this.customHeaders = httpHeaders;
            this.maxFramePayloadLength = i;
            this.performMasking = z2;
            this.allowMaskMismatch = z3;
            this.handleCloseFrames = z4;
            this.sendCloseFrame = webSocketCloseStatus;
            this.dropPongFrames = z5;
            this.handshakeTimeoutMillis = j;
            this.forceCloseTimeoutMillis = j2;
            this.absoluteUpgradeUrl = z6;
        }

        public Builder webSocketUri(String str) {
            return webSocketUri(URI.create(str));
        }

        public Builder webSocketUri(URI uri) {
            this.webSocketUri = uri;
            return this;
        }

        public Builder subprotocol(String str) {
            this.subprotocol = str;
            return this;
        }

        public Builder version(WebSocketVersion webSocketVersion) {
            this.version = webSocketVersion;
            return this;
        }

        public Builder allowExtensions(boolean z) {
            this.allowExtensions = z;
            return this;
        }

        public Builder customHeaders(HttpHeaders httpHeaders) {
            this.customHeaders = httpHeaders;
            return this;
        }

        public Builder maxFramePayloadLength(int i) {
            this.maxFramePayloadLength = i;
            return this;
        }

        public Builder performMasking(boolean z) {
            this.performMasking = z;
            return this;
        }

        public Builder allowMaskMismatch(boolean z) {
            this.allowMaskMismatch = z;
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

        public Builder handshakeTimeoutMillis(long j) {
            this.handshakeTimeoutMillis = j;
            return this;
        }

        public Builder forceCloseTimeoutMillis(long j) {
            this.forceCloseTimeoutMillis = j;
            return this;
        }

        public Builder absoluteUpgradeUrl(boolean z) {
            this.absoluteUpgradeUrl = z;
            return this;
        }

        public WebSocketClientProtocolConfig build() {
            return new WebSocketClientProtocolConfig(this.webSocketUri, this.subprotocol, this.version, this.allowExtensions, this.customHeaders, this.maxFramePayloadLength, this.performMasking, this.allowMaskMismatch, this.handleCloseFrames, this.sendCloseFrame, this.dropPongFrames, this.handshakeTimeoutMillis, this.forceCloseTimeoutMillis, this.absoluteUpgradeUrl);
        }
    }
}
