package io.grpc.netty.shaded.io.netty.handler.codec.http;

import io.grpc.netty.shaded.io.netty.channel.embedded.EmbeddedChannel;
import io.grpc.netty.shaded.io.netty.handler.codec.compression.ZlibCodecFactory;
import io.grpc.netty.shaded.io.netty.handler.codec.compression.ZlibWrapper;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class HttpContentDecompressor extends HttpContentDecoder {
    private final boolean strict;

    public HttpContentDecompressor() {
        this(false);
    }

    public HttpContentDecompressor(boolean z) {
        this.strict = z;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContentDecoder
    protected EmbeddedChannel newContentDecoder(String str) throws Exception {
        if (HttpHeaderValues.GZIP.contentEqualsIgnoreCase(str) || HttpHeaderValues.X_GZIP.contentEqualsIgnoreCase(str)) {
            return new EmbeddedChannel(this.ctx.channel().mo3907id(), this.ctx.channel().metadata().hasDisconnect(), this.ctx.channel().config(), ZlibCodecFactory.newZlibDecoder(ZlibWrapper.GZIP));
        }
        if (HttpHeaderValues.DEFLATE.contentEqualsIgnoreCase(str) || HttpHeaderValues.X_DEFLATE.contentEqualsIgnoreCase(str)) {
            return new EmbeddedChannel(this.ctx.channel().mo3907id(), this.ctx.channel().metadata().hasDisconnect(), this.ctx.channel().config(), ZlibCodecFactory.newZlibDecoder(this.strict ? ZlibWrapper.ZLIB : ZlibWrapper.ZLIB_OR_NONE));
        }
        return null;
    }
}
