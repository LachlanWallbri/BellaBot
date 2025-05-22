package io.netty.handler.codec.http2;

import io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder;
import io.netty.handler.codec.http2.Http2ConnectionHandler;
import io.netty.handler.codec.http2.Http2HeadersEncoder;
import io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public abstract class AbstractHttp2ConnectionHandlerBuilder<T extends Http2ConnectionHandler, B extends AbstractHttp2ConnectionHandlerBuilder<T, B>> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Http2HeadersEncoder.SensitivityDetector DEFAULT_HEADER_SENSITIVITY_DETECTOR = Http2HeadersEncoder.NEVER_SENSITIVE;
    private Http2Connection connection;
    private Http2ConnectionDecoder decoder;
    private Http2ConnectionEncoder encoder;
    private Boolean encoderEnforceMaxConcurrentStreams;
    private Boolean encoderIgnoreMaxHeaderListSize;
    private Http2FrameListener frameListener;
    private Http2FrameLogger frameLogger;
    private Http2HeadersEncoder.SensitivityDetector headerSensitivityDetector;
    private Boolean isServer;
    private Integer maxReservedStreams;
    private Boolean validateHeaders;
    private Http2Settings initialSettings = Http2Settings.defaultSettings();
    private long gracefulShutdownTimeoutMillis = Http2CodecUtil.DEFAULT_GRACEFUL_SHUTDOWN_TIMEOUT_MILLIS;
    private int initialHuffmanDecodeCapacity = 32;

    protected abstract T build(Http2ConnectionDecoder http2ConnectionDecoder, Http2ConnectionEncoder http2ConnectionEncoder, Http2Settings http2Settings) throws Exception;

    protected final B self() {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Http2Settings initialSettings() {
        return this.initialSettings;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public B initialSettings(Http2Settings http2Settings) {
        this.initialSettings = (Http2Settings) ObjectUtil.checkNotNull(http2Settings, "settings");
        return self();
    }

    protected Http2FrameListener frameListener() {
        return this.frameListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public B frameListener(Http2FrameListener http2FrameListener) {
        this.frameListener = (Http2FrameListener) ObjectUtil.checkNotNull(http2FrameListener, "frameListener");
        return self();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long gracefulShutdownTimeoutMillis() {
        return this.gracefulShutdownTimeoutMillis;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public B gracefulShutdownTimeoutMillis(long j) {
        if (j < -1) {
            throw new IllegalArgumentException("gracefulShutdownTimeoutMillis: " + j + " (expected: -1 for indefinite or >= 0)");
        }
        this.gracefulShutdownTimeoutMillis = j;
        return self();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isServer() {
        Boolean bool = this.isServer;
        if (bool != null) {
            return bool.booleanValue();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public B server(boolean z) {
        enforceConstraint("server", "connection", this.connection);
        enforceConstraint("server", "codec", this.decoder);
        enforceConstraint("server", "codec", this.encoder);
        this.isServer = Boolean.valueOf(z);
        return self();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int maxReservedStreams() {
        Integer num = this.maxReservedStreams;
        if (num != null) {
            return num.intValue();
        }
        return 100;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public B maxReservedStreams(int i) {
        enforceConstraint("server", "connection", this.connection);
        enforceConstraint("server", "codec", this.decoder);
        enforceConstraint("server", "codec", this.encoder);
        this.maxReservedStreams = Integer.valueOf(ObjectUtil.checkPositiveOrZero(i, "maxReservedStreams"));
        return self();
    }

    protected Http2Connection connection() {
        return this.connection;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public B connection(Http2Connection http2Connection) {
        enforceConstraint("connection", "maxReservedStreams", this.maxReservedStreams);
        enforceConstraint("connection", "server", this.isServer);
        enforceConstraint("connection", "codec", this.decoder);
        enforceConstraint("connection", "codec", this.encoder);
        this.connection = (Http2Connection) ObjectUtil.checkNotNull(http2Connection, "connection");
        return self();
    }

    protected Http2ConnectionDecoder decoder() {
        return this.decoder;
    }

    protected Http2ConnectionEncoder encoder() {
        return this.encoder;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public B codec(Http2ConnectionDecoder http2ConnectionDecoder, Http2ConnectionEncoder http2ConnectionEncoder) {
        enforceConstraint("codec", "server", this.isServer);
        enforceConstraint("codec", "maxReservedStreams", this.maxReservedStreams);
        enforceConstraint("codec", "connection", this.connection);
        enforceConstraint("codec", "frameLogger", this.frameLogger);
        enforceConstraint("codec", "validateHeaders", this.validateHeaders);
        enforceConstraint("codec", "headerSensitivityDetector", this.headerSensitivityDetector);
        enforceConstraint("codec", "encoderEnforceMaxConcurrentStreams", this.encoderEnforceMaxConcurrentStreams);
        ObjectUtil.checkNotNull(http2ConnectionDecoder, "decoder");
        ObjectUtil.checkNotNull(http2ConnectionEncoder, "encoder");
        if (http2ConnectionDecoder.connection() != http2ConnectionEncoder.connection()) {
            throw new IllegalArgumentException("The specified encoder and decoder have different connections.");
        }
        this.decoder = http2ConnectionDecoder;
        this.encoder = http2ConnectionEncoder;
        return self();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isValidateHeaders() {
        Boolean bool = this.validateHeaders;
        if (bool != null) {
            return bool.booleanValue();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public B validateHeaders(boolean z) {
        enforceNonCodecConstraints("validateHeaders");
        this.validateHeaders = Boolean.valueOf(z);
        return self();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Http2FrameLogger frameLogger() {
        return this.frameLogger;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public B frameLogger(Http2FrameLogger http2FrameLogger) {
        enforceNonCodecConstraints("frameLogger");
        this.frameLogger = (Http2FrameLogger) ObjectUtil.checkNotNull(http2FrameLogger, "frameLogger");
        return self();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean encoderEnforceMaxConcurrentStreams() {
        Boolean bool = this.encoderEnforceMaxConcurrentStreams;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public B encoderEnforceMaxConcurrentStreams(boolean z) {
        enforceNonCodecConstraints("encoderEnforceMaxConcurrentStreams");
        this.encoderEnforceMaxConcurrentStreams = Boolean.valueOf(z);
        return self();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Http2HeadersEncoder.SensitivityDetector headerSensitivityDetector() {
        Http2HeadersEncoder.SensitivityDetector sensitivityDetector = this.headerSensitivityDetector;
        return sensitivityDetector != null ? sensitivityDetector : DEFAULT_HEADER_SENSITIVITY_DETECTOR;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public B headerSensitivityDetector(Http2HeadersEncoder.SensitivityDetector sensitivityDetector) {
        enforceNonCodecConstraints("headerSensitivityDetector");
        this.headerSensitivityDetector = (Http2HeadersEncoder.SensitivityDetector) ObjectUtil.checkNotNull(sensitivityDetector, "headerSensitivityDetector");
        return self();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public B encoderIgnoreMaxHeaderListSize(boolean z) {
        enforceNonCodecConstraints("encoderIgnoreMaxHeaderListSize");
        this.encoderIgnoreMaxHeaderListSize = Boolean.valueOf(z);
        return self();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public B initialHuffmanDecodeCapacity(int i) {
        enforceNonCodecConstraints("initialHuffmanDecodeCapacity");
        this.initialHuffmanDecodeCapacity = ObjectUtil.checkPositive(i, "initialHuffmanDecodeCapacity");
        return self();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public T build() {
        Http2ConnectionEncoder http2ConnectionEncoder = this.encoder;
        if (http2ConnectionEncoder != null) {
            return buildFromCodec(this.decoder, http2ConnectionEncoder);
        }
        Http2Connection http2Connection = this.connection;
        if (http2Connection == null) {
            http2Connection = new DefaultHttp2Connection(isServer(), maxReservedStreams());
        }
        return buildFromConnection(http2Connection);
    }

    private T buildFromConnection(Http2Connection http2Connection) {
        Http2FrameWriter defaultHttp2FrameWriter;
        Long maxHeaderListSize = this.initialSettings.maxHeaderListSize();
        Http2FrameReader defaultHttp2FrameReader = new DefaultHttp2FrameReader(new DefaultHttp2HeadersDecoder(isValidateHeaders(), maxHeaderListSize == null ? 8192L : maxHeaderListSize.longValue(), this.initialHuffmanDecodeCapacity));
        if (this.encoderIgnoreMaxHeaderListSize == null) {
            defaultHttp2FrameWriter = new DefaultHttp2FrameWriter(headerSensitivityDetector());
        } else {
            defaultHttp2FrameWriter = new DefaultHttp2FrameWriter(headerSensitivityDetector(), this.encoderIgnoreMaxHeaderListSize.booleanValue());
        }
        if (this.frameLogger != null) {
            Http2FrameReader http2InboundFrameLogger = new Http2InboundFrameLogger(defaultHttp2FrameReader, this.frameLogger);
            defaultHttp2FrameWriter = new Http2OutboundFrameLogger(defaultHttp2FrameWriter, this.frameLogger);
            defaultHttp2FrameReader = http2InboundFrameLogger;
        }
        Http2ConnectionEncoder defaultHttp2ConnectionEncoder = new DefaultHttp2ConnectionEncoder(http2Connection, defaultHttp2FrameWriter);
        boolean encoderEnforceMaxConcurrentStreams = encoderEnforceMaxConcurrentStreams();
        if (encoderEnforceMaxConcurrentStreams) {
            if (http2Connection.isServer()) {
                defaultHttp2ConnectionEncoder.close();
                defaultHttp2FrameReader.close();
                throw new IllegalArgumentException("encoderEnforceMaxConcurrentStreams: " + encoderEnforceMaxConcurrentStreams + " not supported for server");
            }
            defaultHttp2ConnectionEncoder = new StreamBufferingEncoder(defaultHttp2ConnectionEncoder);
        }
        return buildFromCodec(new DefaultHttp2ConnectionDecoder(http2Connection, defaultHttp2ConnectionEncoder, defaultHttp2FrameReader), defaultHttp2ConnectionEncoder);
    }

    private T buildFromCodec(Http2ConnectionDecoder http2ConnectionDecoder, Http2ConnectionEncoder http2ConnectionEncoder) {
        try {
            T build = build(http2ConnectionDecoder, http2ConnectionEncoder, this.initialSettings);
            build.gracefulShutdownTimeoutMillis(this.gracefulShutdownTimeoutMillis);
            if (build.decoder().frameListener() == null) {
                build.decoder().frameListener(this.frameListener);
            }
            return build;
        } catch (Throwable th) {
            http2ConnectionEncoder.close();
            http2ConnectionDecoder.close();
            throw new IllegalStateException("failed to build a Http2ConnectionHandler", th);
        }
    }

    private void enforceNonCodecConstraints(String str) {
        enforceConstraint(str, "server/connection", this.decoder);
        enforceConstraint(str, "server/connection", this.encoder);
    }

    private static void enforceConstraint(String str, String str2, Object obj) {
        if (obj == null) {
            return;
        }
        throw new IllegalStateException(str + "() cannot be called because " + str2 + "() has been called already.");
    }
}
