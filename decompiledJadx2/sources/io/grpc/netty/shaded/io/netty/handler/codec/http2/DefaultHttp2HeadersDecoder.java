package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2HeadersDecoder;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DefaultHttp2HeadersDecoder implements Http2HeadersDecoder, Http2HeadersDecoder.Configuration {
    private static final float HEADERS_COUNT_WEIGHT_HISTORICAL = 0.8f;
    private static final float HEADERS_COUNT_WEIGHT_NEW = 0.2f;
    private float headerArraySizeAccumulator;
    private final HpackDecoder hpackDecoder;
    private long maxHeaderListSizeGoAway;
    private final boolean validateHeaders;

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2HeadersDecoder
    public Http2HeadersDecoder.Configuration configuration() {
        return this;
    }

    public DefaultHttp2HeadersDecoder() {
        this(true);
    }

    public DefaultHttp2HeadersDecoder(boolean z) {
        this(z, 8192L);
    }

    public DefaultHttp2HeadersDecoder(boolean z, long j) {
        this(z, j, -1);
    }

    public DefaultHttp2HeadersDecoder(boolean z, long j, @Deprecated int i) {
        this(z, new HpackDecoder(j));
    }

    DefaultHttp2HeadersDecoder(boolean z, HpackDecoder hpackDecoder) {
        this.headerArraySizeAccumulator = 8.0f;
        this.hpackDecoder = (HpackDecoder) ObjectUtil.checkNotNull(hpackDecoder, "hpackDecoder");
        this.validateHeaders = z;
        this.maxHeaderListSizeGoAway = Http2CodecUtil.calculateMaxHeaderListSizeGoAway(hpackDecoder.getMaxHeaderListSize());
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2HeadersDecoder.Configuration
    public void maxHeaderTableSize(long j) throws Http2Exception {
        this.hpackDecoder.setMaxHeaderTableSize(j);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2HeadersDecoder.Configuration
    public long maxHeaderTableSize() {
        return this.hpackDecoder.getMaxHeaderTableSize();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2HeadersDecoder.Configuration
    public void maxHeaderListSize(long j, long j2) throws Http2Exception {
        if (j2 < j || j2 < 0) {
            throw Http2Exception.connectionError(Http2Error.INTERNAL_ERROR, "Header List Size GO_AWAY %d must be non-negative and >= %d", Long.valueOf(j2), Long.valueOf(j));
        }
        this.hpackDecoder.setMaxHeaderListSize(j);
        this.maxHeaderListSizeGoAway = j2;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2HeadersDecoder.Configuration
    public long maxHeaderListSize() {
        return this.hpackDecoder.getMaxHeaderListSize();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2HeadersDecoder.Configuration
    public long maxHeaderListSizeGoAway() {
        return this.maxHeaderListSizeGoAway;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2HeadersDecoder
    public Http2Headers decodeHeaders(int i, ByteBuf byteBuf) throws Http2Exception {
        try {
            Http2Headers newHeaders = newHeaders();
            this.hpackDecoder.decode(i, byteBuf, newHeaders, this.validateHeaders);
            this.headerArraySizeAccumulator = (newHeaders.size() * 0.2f) + (this.headerArraySizeAccumulator * HEADERS_COUNT_WEIGHT_HISTORICAL);
            return newHeaders;
        } catch (Http2Exception e) {
            throw e;
        } catch (Throwable th) {
            throw Http2Exception.connectionError(Http2Error.COMPRESSION_ERROR, th, th.getMessage(), new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int numberOfHeadersGuess() {
        return (int) this.headerArraySizeAccumulator;
    }

    protected final boolean validateHeaders() {
        return this.validateHeaders;
    }

    protected Http2Headers newHeaders() {
        return new DefaultHttp2Headers(this.validateHeaders, (int) this.headerArraySizeAccumulator);
    }
}
