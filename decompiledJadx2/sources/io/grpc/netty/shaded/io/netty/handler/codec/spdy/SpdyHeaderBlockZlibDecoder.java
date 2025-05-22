package io.grpc.netty.shaded.io.netty.handler.codec.spdy;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class SpdyHeaderBlockZlibDecoder extends SpdyHeaderBlockRawDecoder {
    private static final int DEFAULT_BUFFER_CAPACITY = 4096;
    private static final SpdyProtocolException INVALID_HEADER_BLOCK = new SpdyProtocolException("Invalid Header Block");
    private ByteBuf decompressed;
    private final Inflater decompressor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SpdyHeaderBlockZlibDecoder(SpdyVersion spdyVersion, int i) {
        super(spdyVersion, i);
        this.decompressor = new Inflater();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeaderBlockRawDecoder, io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeaderBlockDecoder
    void decode(ByteBufAllocator byteBufAllocator, ByteBuf byteBuf, SpdyHeadersFrame spdyHeadersFrame) throws Exception {
        int input = setInput(byteBuf);
        do {
        } while (decompress(byteBufAllocator, spdyHeadersFrame) > 0);
        if (this.decompressor.getRemaining() != 0) {
            throw INVALID_HEADER_BLOCK;
        }
        byteBuf.skipBytes(input);
    }

    private int setInput(ByteBuf byteBuf) {
        int readableBytes = byteBuf.readableBytes();
        if (byteBuf.hasArray()) {
            this.decompressor.setInput(byteBuf.array(), byteBuf.arrayOffset() + byteBuf.readerIndex(), readableBytes);
        } else {
            byte[] bArr = new byte[readableBytes];
            byteBuf.getBytes(byteBuf.readerIndex(), bArr);
            this.decompressor.setInput(bArr, 0, bArr.length);
        }
        return readableBytes;
    }

    private int decompress(ByteBufAllocator byteBufAllocator, SpdyHeadersFrame spdyHeadersFrame) throws Exception {
        ensureBuffer(byteBufAllocator);
        byte[] array = this.decompressed.array();
        int arrayOffset = this.decompressed.arrayOffset() + this.decompressed.writerIndex();
        try {
            int inflate = this.decompressor.inflate(array, arrayOffset, this.decompressed.writableBytes());
            if (inflate == 0 && this.decompressor.needsDictionary()) {
                try {
                    this.decompressor.setDictionary(SpdyCodecUtil.SPDY_DICT);
                    inflate = this.decompressor.inflate(array, arrayOffset, this.decompressed.writableBytes());
                } catch (IllegalArgumentException unused) {
                    throw INVALID_HEADER_BLOCK;
                }
            }
            if (spdyHeadersFrame != null) {
                this.decompressed.writerIndex(this.decompressed.writerIndex() + inflate);
                decodeHeaderBlock(this.decompressed, spdyHeadersFrame);
                this.decompressed.discardReadBytes();
            }
            return inflate;
        } catch (DataFormatException e) {
            throw new SpdyProtocolException("Received invalid header block", e);
        }
    }

    private void ensureBuffer(ByteBufAllocator byteBufAllocator) {
        if (this.decompressed == null) {
            this.decompressed = byteBufAllocator.heapBuffer(4096);
        }
        this.decompressed.ensureWritable(1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeaderBlockRawDecoder, io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeaderBlockDecoder
    public void endHeaderBlock(SpdyHeadersFrame spdyHeadersFrame) throws Exception {
        super.endHeaderBlock(spdyHeadersFrame);
        releaseBuffer();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeaderBlockRawDecoder, io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeaderBlockDecoder
    public void end() {
        super.end();
        releaseBuffer();
        this.decompressor.end();
    }

    private void releaseBuffer() {
        ByteBuf byteBuf = this.decompressed;
        if (byteBuf != null) {
            byteBuf.release();
            this.decompressed = null;
        }
    }
}
