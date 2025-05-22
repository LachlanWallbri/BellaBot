package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http2.Http2HeadersDecoder;
import io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class DefaultHttp2HeadersDecoder implements Http2HeadersDecoder, Http2HeadersDecoder.Configuration {
    private static final float HEADERS_COUNT_WEIGHT_HISTORICAL = 0.8f;
    private static final float HEADERS_COUNT_WEIGHT_NEW = 0.2f;
    private float headerArraySizeAccumulator;
    private final HpackDecoder hpackDecoder;
    private final boolean validateHeaders;

    @Override // io.netty.handler.codec.http2.Http2HeadersDecoder
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
        this(z, j, 32);
    }

    public DefaultHttp2HeadersDecoder(boolean z, long j, int i) {
        this(z, new HpackDecoder(j, i));
    }

    DefaultHttp2HeadersDecoder(boolean z, HpackDecoder hpackDecoder) {
        this.headerArraySizeAccumulator = 8.0f;
        this.hpackDecoder = (HpackDecoder) ObjectUtil.checkNotNull(hpackDecoder, "hpackDecoder");
        this.validateHeaders = z;
    }

    @Override // io.netty.handler.codec.http2.Http2HeadersDecoder.Configuration
    public void maxHeaderTableSize(long j) throws Http2Exception {
        this.hpackDecoder.setMaxHeaderTableSize(j);
    }

    @Override // io.netty.handler.codec.http2.Http2HeadersDecoder.Configuration
    public long maxHeaderTableSize() {
        return this.hpackDecoder.getMaxHeaderTableSize();
    }

    @Override // io.netty.handler.codec.http2.Http2HeadersDecoder.Configuration
    public void maxHeaderListSize(long j, long j2) throws Http2Exception {
        this.hpackDecoder.setMaxHeaderListSize(j, j2);
    }

    @Override // io.netty.handler.codec.http2.Http2HeadersDecoder.Configuration
    public long maxHeaderListSize() {
        return this.hpackDecoder.getMaxHeaderListSize();
    }

    @Override // io.netty.handler.codec.http2.Http2HeadersDecoder.Configuration
    public long maxHeaderListSizeGoAway() {
        return this.hpackDecoder.getMaxHeaderListSizeGoAway();
    }

    @Override // io.netty.handler.codec.http2.Http2HeadersDecoder
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

    protected final int numberOfHeadersGuess() {
        return (int) this.headerArraySizeAccumulator;
    }

    protected final boolean validateHeaders() {
        return this.validateHeaders;
    }

    protected Http2Headers newHeaders() {
        return new DefaultHttp2Headers(this.validateHeaders, (int) this.headerArraySizeAccumulator);
    }
}
