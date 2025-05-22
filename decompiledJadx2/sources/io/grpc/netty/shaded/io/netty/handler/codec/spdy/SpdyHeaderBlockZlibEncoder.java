package io.grpc.netty.shaded.io.netty.handler.codec.spdy;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import java.util.zip.Deflater;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class SpdyHeaderBlockZlibEncoder extends SpdyHeaderBlockRawEncoder {
    private final Deflater compressor;
    private boolean finished;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SpdyHeaderBlockZlibEncoder(SpdyVersion spdyVersion, int i) {
        super(spdyVersion);
        if (i < 0 || i > 9) {
            throw new IllegalArgumentException("compressionLevel: " + i + " (expected: 0-9)");
        }
        this.compressor = new Deflater(i);
        this.compressor.setDictionary(SpdyCodecUtil.SPDY_DICT);
    }

    private int setInput(ByteBuf byteBuf) {
        int readableBytes = byteBuf.readableBytes();
        if (byteBuf.hasArray()) {
            this.compressor.setInput(byteBuf.array(), byteBuf.arrayOffset() + byteBuf.readerIndex(), readableBytes);
        } else {
            byte[] bArr = new byte[readableBytes];
            byteBuf.getBytes(byteBuf.readerIndex(), bArr);
            this.compressor.setInput(bArr, 0, bArr.length);
        }
        return readableBytes;
    }

    private ByteBuf encode(ByteBufAllocator byteBufAllocator, int i) {
        ByteBuf heapBuffer = byteBufAllocator.heapBuffer(i);
        while (compressInto(heapBuffer)) {
            try {
                heapBuffer.ensureWritable(heapBuffer.capacity() << 1);
            } catch (Throwable th) {
                heapBuffer.release();
                throw th;
            }
        }
        return heapBuffer;
    }

    private boolean compressInto(ByteBuf byteBuf) {
        int deflate;
        byte[] array = byteBuf.array();
        int arrayOffset = byteBuf.arrayOffset() + byteBuf.writerIndex();
        int writableBytes = byteBuf.writableBytes();
        if (PlatformDependent.javaVersion() >= 7) {
            deflate = this.compressor.deflate(array, arrayOffset, writableBytes, 2);
        } else {
            deflate = this.compressor.deflate(array, arrayOffset, writableBytes);
        }
        byteBuf.writerIndex(byteBuf.writerIndex() + deflate);
        return deflate == writableBytes;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeaderBlockRawEncoder, io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeaderBlockEncoder
    public ByteBuf encode(ByteBufAllocator byteBufAllocator, SpdyHeadersFrame spdyHeadersFrame) throws Exception {
        if (spdyHeadersFrame == null) {
            throw new IllegalArgumentException(TypedValues.Attributes.S_FRAME);
        }
        if (this.finished) {
            return Unpooled.EMPTY_BUFFER;
        }
        ByteBuf encode = super.encode(byteBufAllocator, spdyHeadersFrame);
        try {
            if (!encode.isReadable()) {
                return Unpooled.EMPTY_BUFFER;
            }
            return encode(byteBufAllocator, setInput(encode));
        } finally {
            encode.release();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeaderBlockRawEncoder, io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyHeaderBlockEncoder
    public void end() {
        if (this.finished) {
            return;
        }
        this.finished = true;
        this.compressor.end();
        super.end();
    }
}
