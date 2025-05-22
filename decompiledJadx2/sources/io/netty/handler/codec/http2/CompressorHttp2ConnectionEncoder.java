package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.handler.codec.compression.ZlibWrapper;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http2.Http2Connection;
import io.netty.util.AsciiString;
import io.netty.util.concurrent.Promise;
import io.netty.util.concurrent.PromiseCombiner;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class CompressorHttp2ConnectionEncoder extends DecoratingHttp2ConnectionEncoder {
    public static final int DEFAULT_COMPRESSION_LEVEL = 6;
    public static final int DEFAULT_MEM_LEVEL = 8;
    public static final int DEFAULT_WINDOW_BITS = 15;
    private final int compressionLevel;
    private final int memLevel;
    private final Http2Connection.PropertyKey propertyKey;
    private final int windowBits;

    protected CharSequence getTargetContentEncoding(CharSequence charSequence) throws Http2Exception {
        return charSequence;
    }

    public CompressorHttp2ConnectionEncoder(Http2ConnectionEncoder http2ConnectionEncoder) {
        this(http2ConnectionEncoder, 6, 15, 8);
    }

    public CompressorHttp2ConnectionEncoder(Http2ConnectionEncoder http2ConnectionEncoder, int i, int i2, int i3) {
        super(http2ConnectionEncoder);
        if (i < 0 || i > 9) {
            throw new IllegalArgumentException("compressionLevel: " + i + " (expected: 0-9)");
        }
        if (i2 < 9 || i2 > 15) {
            throw new IllegalArgumentException("windowBits: " + i2 + " (expected: 9-15)");
        }
        if (i3 < 1 || i3 > 9) {
            throw new IllegalArgumentException("memLevel: " + i3 + " (expected: 1-9)");
        }
        this.compressionLevel = i;
        this.windowBits = i2;
        this.memLevel = i3;
        this.propertyKey = connection().newKey();
        connection().addListener(new Http2ConnectionAdapter() { // from class: io.netty.handler.codec.http2.CompressorHttp2ConnectionEncoder.1
            @Override // io.netty.handler.codec.http2.Http2ConnectionAdapter, io.netty.handler.codec.http2.Http2Connection.Listener
            public void onStreamRemoved(Http2Stream http2Stream) {
                EmbeddedChannel embeddedChannel = (EmbeddedChannel) http2Stream.getProperty(CompressorHttp2ConnectionEncoder.this.propertyKey);
                if (embeddedChannel != null) {
                    CompressorHttp2ConnectionEncoder.this.cleanup(http2Stream, embeddedChannel);
                }
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x009d, code lost:
    
        if (r21 != false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00ac, code lost:
    
        return r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00a9, code lost:
    
        cleanup(r11, r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00a7, code lost:
    
        if (r21 == false) goto L51;
     */
    @Override // io.netty.handler.codec.http2.DecoratingHttp2FrameWriter, io.netty.handler.codec.http2.Http2DataWriter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public ChannelFuture writeData(ChannelHandlerContext channelHandlerContext, int i, ByteBuf byteBuf, int i2, boolean z, ChannelPromise channelPromise) {
        Http2Stream stream = connection().stream(i);
        EmbeddedChannel embeddedChannel = stream == null ? null : (EmbeddedChannel) stream.getProperty(this.propertyKey);
        if (embeddedChannel == null) {
            return super.writeData(channelHandlerContext, i, byteBuf, i2, z, channelPromise);
        }
        try {
            embeddedChannel.writeOutbound(byteBuf);
            ByteBuf nextReadableBuf = nextReadableBuf(embeddedChannel);
            if (nextReadableBuf == null) {
                if (z) {
                    if (embeddedChannel.finish()) {
                        nextReadableBuf = nextReadableBuf(embeddedChannel);
                    }
                    ChannelFuture writeData = super.writeData(channelHandlerContext, i, nextReadableBuf == null ? Unpooled.EMPTY_BUFFER : nextReadableBuf, i2, true, channelPromise);
                    if (z) {
                        cleanup(stream, embeddedChannel);
                    }
                    return writeData;
                }
                channelPromise.setSuccess();
                if (z) {
                    cleanup(stream, embeddedChannel);
                }
                return channelPromise;
            }
            PromiseCombiner promiseCombiner = new PromiseCombiner();
            int i3 = i2;
            ByteBuf byteBuf2 = nextReadableBuf;
            while (true) {
                ByteBuf nextReadableBuf2 = nextReadableBuf(embeddedChannel);
                boolean z2 = nextReadableBuf2 == null && z;
                if (z2 && embeddedChannel.finish()) {
                    nextReadableBuf2 = nextReadableBuf(embeddedChannel);
                    z2 = nextReadableBuf2 == null;
                }
                ByteBuf byteBuf3 = nextReadableBuf2;
                ChannelPromise newPromise = channelHandlerContext.newPromise();
                promiseCombiner.add((Promise) newPromise);
                super.writeData(channelHandlerContext, i, byteBuf2, i3, z2, newPromise);
                if (byteBuf3 == null) {
                    break;
                }
                i3 = 0;
                byteBuf2 = byteBuf3;
            }
            promiseCombiner.finish(channelPromise);
        } catch (Throwable th) {
            try {
                channelPromise.tryFailure(th);
            } finally {
            }
        }
    }

    @Override // io.netty.handler.codec.http2.DecoratingHttp2FrameWriter, io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeHeaders(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, boolean z, ChannelPromise channelPromise) {
        try {
            EmbeddedChannel newCompressor = newCompressor(channelHandlerContext, http2Headers, z);
            ChannelFuture writeHeaders = super.writeHeaders(channelHandlerContext, i, http2Headers, i2, z, channelPromise);
            bindCompressorToStream(newCompressor, i);
            return writeHeaders;
        } catch (Throwable th) {
            channelPromise.tryFailure(th);
            return channelPromise;
        }
    }

    @Override // io.netty.handler.codec.http2.DecoratingHttp2FrameWriter, io.netty.handler.codec.http2.Http2FrameWriter
    public ChannelFuture writeHeaders(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, short s, boolean z, int i3, boolean z2, ChannelPromise channelPromise) {
        try {
            EmbeddedChannel newCompressor = newCompressor(channelHandlerContext, http2Headers, z2);
            ChannelFuture writeHeaders = super.writeHeaders(channelHandlerContext, i, http2Headers, i2, s, z, i3, z2, channelPromise);
            bindCompressorToStream(newCompressor, i);
            return writeHeaders;
        } catch (Throwable th) {
            channelPromise.tryFailure(th);
            return channelPromise;
        }
    }

    protected EmbeddedChannel newContentCompressor(ChannelHandlerContext channelHandlerContext, CharSequence charSequence) throws Http2Exception {
        if (HttpHeaderValues.GZIP.contentEqualsIgnoreCase(charSequence) || HttpHeaderValues.X_GZIP.contentEqualsIgnoreCase(charSequence)) {
            return newCompressionChannel(channelHandlerContext, ZlibWrapper.GZIP);
        }
        if (HttpHeaderValues.DEFLATE.contentEqualsIgnoreCase(charSequence) || HttpHeaderValues.X_DEFLATE.contentEqualsIgnoreCase(charSequence)) {
            return newCompressionChannel(channelHandlerContext, ZlibWrapper.ZLIB);
        }
        return null;
    }

    private EmbeddedChannel newCompressionChannel(ChannelHandlerContext channelHandlerContext, ZlibWrapper zlibWrapper) {
        return new EmbeddedChannel(channelHandlerContext.channel().mo3929id(), channelHandlerContext.channel().metadata().hasDisconnect(), channelHandlerContext.channel().config(), ZlibCodecFactory.newZlibEncoder(zlibWrapper, this.compressionLevel, this.windowBits, this.memLevel));
    }

    private EmbeddedChannel newCompressor(ChannelHandlerContext channelHandlerContext, Http2Headers http2Headers, boolean z) throws Http2Exception {
        if (z) {
            return null;
        }
        CharSequence charSequence = http2Headers.get(HttpHeaderNames.CONTENT_ENCODING);
        if (charSequence == null) {
            charSequence = HttpHeaderValues.IDENTITY;
        }
        EmbeddedChannel newContentCompressor = newContentCompressor(channelHandlerContext, charSequence);
        if (newContentCompressor != null) {
            CharSequence targetContentEncoding = getTargetContentEncoding(charSequence);
            if (HttpHeaderValues.IDENTITY.contentEqualsIgnoreCase(targetContentEncoding)) {
                http2Headers.remove(HttpHeaderNames.CONTENT_ENCODING);
            } else {
                http2Headers.set((Http2Headers) HttpHeaderNames.CONTENT_ENCODING, (AsciiString) targetContentEncoding);
            }
            http2Headers.remove(HttpHeaderNames.CONTENT_LENGTH);
        }
        return newContentCompressor;
    }

    private void bindCompressorToStream(EmbeddedChannel embeddedChannel, int i) {
        Http2Stream stream;
        if (embeddedChannel == null || (stream = connection().stream(i)) == null) {
            return;
        }
        stream.setProperty(this.propertyKey, embeddedChannel);
    }

    void cleanup(Http2Stream http2Stream, EmbeddedChannel embeddedChannel) {
        if (embeddedChannel.finish()) {
            while (true) {
                ByteBuf byteBuf = (ByteBuf) embeddedChannel.readOutbound();
                if (byteBuf == null) {
                    break;
                } else {
                    byteBuf.release();
                }
            }
        }
        http2Stream.removeProperty(this.propertyKey);
    }

    private static ByteBuf nextReadableBuf(EmbeddedChannel embeddedChannel) {
        while (true) {
            ByteBuf byteBuf = (ByteBuf) embeddedChannel.readOutbound();
            if (byteBuf == null) {
                return null;
            }
            if (byteBuf.isReadable()) {
                return byteBuf;
            }
            byteBuf.release();
        }
    }
}
