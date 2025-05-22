package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.embedded.EmbeddedChannel;
import io.grpc.netty.shaded.io.netty.handler.codec.compression.ZlibCodecFactory;
import io.grpc.netty.shaded.io.netty.handler.codec.compression.ZlibWrapper;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderNames;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderValues;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection;
import io.grpc.netty.shaded.io.netty.util.AsciiString;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DelegatingDecompressorFrameListener extends Http2FrameListenerDecorator {
    private final Http2Connection connection;
    private boolean flowControllerInitialized;
    private final Http2Connection.PropertyKey propertyKey;
    private final boolean strict;

    public DelegatingDecompressorFrameListener(Http2Connection http2Connection, Http2FrameListener http2FrameListener) {
        this(http2Connection, http2FrameListener, true);
    }

    public DelegatingDecompressorFrameListener(Http2Connection http2Connection, Http2FrameListener http2FrameListener, boolean z) {
        super(http2FrameListener);
        this.connection = http2Connection;
        this.strict = z;
        this.propertyKey = http2Connection.newKey();
        http2Connection.addListener(new Http2ConnectionAdapter() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.DelegatingDecompressorFrameListener.1
            @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection.Listener
            public void onStreamRemoved(Http2Stream http2Stream) {
                Http2Decompressor decompressor = DelegatingDecompressorFrameListener.this.decompressor(http2Stream);
                if (decompressor != null) {
                    DelegatingDecompressorFrameListener.cleanup(decompressor);
                }
            }
        });
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListenerDecorator, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
    public int onDataRead(ChannelHandlerContext channelHandlerContext, int i, ByteBuf byteBuf, int i2, boolean z) throws Http2Exception {
        ByteBuf byteBuf2;
        Http2Stream stream = this.connection.stream(i);
        Http2Decompressor decompressor = decompressor(stream);
        if (decompressor == null) {
            return this.listener.onDataRead(channelHandlerContext, i, byteBuf, i2, z);
        }
        EmbeddedChannel decompressor2 = decompressor.decompressor();
        int readableBytes = byteBuf.readableBytes() + i2;
        decompressor.incrementCompressedBytes(readableBytes);
        try {
            decompressor2.writeInbound(byteBuf.retain());
            ByteBuf nextReadableBuf = nextReadableBuf(decompressor2);
            if (nextReadableBuf == null && z && decompressor2.finish()) {
                nextReadableBuf = nextReadableBuf(decompressor2);
            }
            if (nextReadableBuf == null) {
                if (z) {
                    this.listener.onDataRead(channelHandlerContext, i, Unpooled.EMPTY_BUFFER, i2, true);
                }
                decompressor.incrementDecompressedBytes(readableBytes);
                return readableBytes;
            }
            try {
                Http2LocalFlowController flowController = this.connection.local().flowController();
                decompressor.incrementDecompressedBytes(i2);
                int i3 = i2;
                byteBuf2 = nextReadableBuf;
                while (true) {
                    try {
                        ByteBuf nextReadableBuf2 = nextReadableBuf(decompressor2);
                        boolean z2 = nextReadableBuf2 == null && z;
                        if (z2 && decompressor2.finish()) {
                            nextReadableBuf2 = nextReadableBuf(decompressor2);
                            z2 = nextReadableBuf2 == null;
                        }
                        decompressor.incrementDecompressedBytes(byteBuf2.readableBytes());
                        flowController.consumeBytes(stream, this.listener.onDataRead(channelHandlerContext, i, byteBuf2, i3, z2));
                        if (nextReadableBuf2 != null) {
                            byteBuf2.release();
                            byteBuf2 = nextReadableBuf2;
                            i3 = 0;
                        } else {
                            byteBuf2.release();
                            return 0;
                        }
                    } catch (Throwable th) {
                        th = th;
                        byteBuf2.release();
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                byteBuf2 = nextReadableBuf;
            }
        } catch (Http2Exception e) {
            throw e;
        } catch (Throwable th3) {
            throw Http2Exception.streamError(stream.mo3916id(), Http2Error.INTERNAL_ERROR, th3, "Decompressor error detected while delegating data read on streamId %d", Integer.valueOf(stream.mo3916id()));
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListenerDecorator, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
    public void onHeadersRead(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, boolean z) throws Http2Exception {
        initDecompressor(channelHandlerContext, i, http2Headers, z);
        this.listener.onHeadersRead(channelHandlerContext, i, http2Headers, i2, z);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListenerDecorator, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
    public void onHeadersRead(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, short s, boolean z, int i3, boolean z2) throws Http2Exception {
        initDecompressor(channelHandlerContext, i, http2Headers, z2);
        this.listener.onHeadersRead(channelHandlerContext, i, http2Headers, i2, s, z, i3, z2);
    }

    protected EmbeddedChannel newContentDecompressor(ChannelHandlerContext channelHandlerContext, CharSequence charSequence) throws Http2Exception {
        if (HttpHeaderValues.GZIP.contentEqualsIgnoreCase(charSequence) || HttpHeaderValues.X_GZIP.contentEqualsIgnoreCase(charSequence)) {
            return new EmbeddedChannel(channelHandlerContext.channel().mo3907id(), channelHandlerContext.channel().metadata().hasDisconnect(), channelHandlerContext.channel().config(), ZlibCodecFactory.newZlibDecoder(ZlibWrapper.GZIP));
        }
        if (HttpHeaderValues.DEFLATE.contentEqualsIgnoreCase(charSequence) || HttpHeaderValues.X_DEFLATE.contentEqualsIgnoreCase(charSequence)) {
            return new EmbeddedChannel(channelHandlerContext.channel().mo3907id(), channelHandlerContext.channel().metadata().hasDisconnect(), channelHandlerContext.channel().config(), ZlibCodecFactory.newZlibDecoder(this.strict ? ZlibWrapper.ZLIB : ZlibWrapper.ZLIB_OR_NONE));
        }
        return null;
    }

    protected CharSequence getTargetContentEncoding(CharSequence charSequence) throws Http2Exception {
        return HttpHeaderValues.IDENTITY;
    }

    private void initDecompressor(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, boolean z) throws Http2Exception {
        Http2Stream stream = this.connection.stream(i);
        if (stream == null) {
            return;
        }
        Http2Decompressor decompressor = decompressor(stream);
        if (decompressor == null && !z) {
            CharSequence charSequence = http2Headers.get(HttpHeaderNames.CONTENT_ENCODING);
            if (charSequence == null) {
                charSequence = HttpHeaderValues.IDENTITY;
            }
            EmbeddedChannel newContentDecompressor = newContentDecompressor(channelHandlerContext, charSequence);
            if (newContentDecompressor != null) {
                decompressor = new Http2Decompressor(newContentDecompressor);
                stream.setProperty(this.propertyKey, decompressor);
                CharSequence targetContentEncoding = getTargetContentEncoding(charSequence);
                if (HttpHeaderValues.IDENTITY.contentEqualsIgnoreCase(targetContentEncoding)) {
                    http2Headers.remove(HttpHeaderNames.CONTENT_ENCODING);
                } else {
                    http2Headers.set((Http2Headers) HttpHeaderNames.CONTENT_ENCODING, (AsciiString) targetContentEncoding);
                }
            }
        }
        if (decompressor != null) {
            http2Headers.remove(HttpHeaderNames.CONTENT_LENGTH);
            if (this.flowControllerInitialized) {
                return;
            }
            this.flowControllerInitialized = true;
            this.connection.local().flowController(new ConsumedBytesConverter(this.connection.local().flowController()));
        }
    }

    Http2Decompressor decompressor(Http2Stream http2Stream) {
        if (http2Stream == null) {
            return null;
        }
        return (Http2Decompressor) http2Stream.getProperty(this.propertyKey);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void cleanup(Http2Decompressor http2Decompressor) {
        http2Decompressor.decompressor().finishAndReleaseAll();
    }

    private static ByteBuf nextReadableBuf(EmbeddedChannel embeddedChannel) {
        while (true) {
            ByteBuf byteBuf = (ByteBuf) embeddedChannel.readInbound();
            if (byteBuf == null) {
                return null;
            }
            if (byteBuf.isReadable()) {
                return byteBuf;
            }
            byteBuf.release();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class ConsumedBytesConverter implements Http2LocalFlowController {
        private final Http2LocalFlowController flowController;

        ConsumedBytesConverter(Http2LocalFlowController http2LocalFlowController) {
            this.flowController = (Http2LocalFlowController) ObjectUtil.checkNotNull(http2LocalFlowController, "flowController");
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2LocalFlowController
        public Http2LocalFlowController frameWriter(Http2FrameWriter http2FrameWriter) {
            return this.flowController.frameWriter(http2FrameWriter);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FlowController
        public void channelHandlerContext(ChannelHandlerContext channelHandlerContext) throws Http2Exception {
            this.flowController.channelHandlerContext(channelHandlerContext);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FlowController
        public void initialWindowSize(int i) throws Http2Exception {
            this.flowController.initialWindowSize(i);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FlowController
        public int initialWindowSize() {
            return this.flowController.initialWindowSize();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FlowController
        public int windowSize(Http2Stream http2Stream) {
            return this.flowController.windowSize(http2Stream);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FlowController
        public void incrementWindowSize(Http2Stream http2Stream, int i) throws Http2Exception {
            this.flowController.incrementWindowSize(http2Stream, i);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2LocalFlowController
        public void receiveFlowControlledFrame(Http2Stream http2Stream, ByteBuf byteBuf, int i, boolean z) throws Http2Exception {
            this.flowController.receiveFlowControlledFrame(http2Stream, byteBuf, i, z);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2LocalFlowController
        public boolean consumeBytes(Http2Stream http2Stream, int i) throws Http2Exception {
            Http2Decompressor decompressor = DelegatingDecompressorFrameListener.this.decompressor(http2Stream);
            if (decompressor != null) {
                i = decompressor.consumeBytes(http2Stream.mo3916id(), i);
            }
            try {
                return this.flowController.consumeBytes(http2Stream, i);
            } catch (Http2Exception e) {
                throw e;
            } catch (Throwable th) {
                throw Http2Exception.streamError(http2Stream.mo3916id(), Http2Error.INTERNAL_ERROR, th, "Error while returning bytes to flow control window", new Object[0]);
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2LocalFlowController
        public int unconsumedBytes(Http2Stream http2Stream) {
            return this.flowController.unconsumedBytes(http2Stream);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2LocalFlowController
        public int initialWindowSize(Http2Stream http2Stream) {
            return this.flowController.initialWindowSize(http2Stream);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Http2Decompressor {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private int compressed;
        private int decompressed;
        private final EmbeddedChannel decompressor;

        Http2Decompressor(EmbeddedChannel embeddedChannel) {
            this.decompressor = embeddedChannel;
        }

        EmbeddedChannel decompressor() {
            return this.decompressor;
        }

        void incrementCompressedBytes(int i) {
            this.compressed += i;
        }

        void incrementDecompressedBytes(int i) {
            this.decompressed += i;
        }

        int consumeBytes(int i, int i2) throws Http2Exception {
            ObjectUtil.checkPositiveOrZero(i2, "decompressedBytes");
            int i3 = this.decompressed;
            if (i3 - i2 < 0) {
                throw Http2Exception.streamError(i, Http2Error.INTERNAL_ERROR, "Attempting to return too many bytes for stream %d. decompressed: %d decompressedBytes: %d", Integer.valueOf(i), Integer.valueOf(this.decompressed), Integer.valueOf(i2));
            }
            double d = i2 / i3;
            int i4 = this.compressed;
            int min = Math.min(i4, (int) Math.ceil(i4 * d));
            int i5 = this.compressed;
            if (i5 - min < 0) {
                throw Http2Exception.streamError(i, Http2Error.INTERNAL_ERROR, "overflow when converting decompressed bytes to compressed bytes for stream %d.decompressedBytes: %d decompressed: %d compressed: %d consumedCompressed: %d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(this.decompressed), Integer.valueOf(this.compressed), Integer.valueOf(min));
            }
            this.decompressed -= i2;
            this.compressed = i5 - min;
            return min;
        }
    }
}
