package io.netty.handler.codec.spdy;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.jcraft.jzlib.Deflater;
import com.jcraft.jzlib.JZlib;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.compression.CompressionException;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class SpdyHeaderBlockJZlibEncoder extends SpdyHeaderBlockRawEncoder {
    private boolean finished;

    /* renamed from: z */
    private final Deflater f8541z;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SpdyHeaderBlockJZlibEncoder(SpdyVersion spdyVersion, int i, int i2, int i3) {
        super(spdyVersion);
        Deflater deflater = new Deflater();
        this.f8541z = deflater;
        if (i < 0 || i > 9) {
            throw new IllegalArgumentException("compressionLevel: " + i + " (expected: 0-9)");
        }
        if (i2 < 9 || i2 > 15) {
            throw new IllegalArgumentException("windowBits: " + i2 + " (expected: 9-15)");
        }
        if (i3 < 1 || i3 > 9) {
            throw new IllegalArgumentException("memLevel: " + i3 + " (expected: 1-9)");
        }
        int deflateInit = deflater.deflateInit(i, i2, i3, JZlib.W_ZLIB);
        if (deflateInit != 0) {
            throw new CompressionException("failed to initialize an SPDY header block deflater: " + deflateInit);
        }
        int deflateSetDictionary = this.f8541z.deflateSetDictionary(SpdyCodecUtil.SPDY_DICT, SpdyCodecUtil.SPDY_DICT.length);
        if (deflateSetDictionary == 0) {
            return;
        }
        throw new CompressionException("failed to set the SPDY dictionary: " + deflateSetDictionary);
    }

    private void setInput(ByteBuf byteBuf) {
        byte[] bArr;
        int i;
        int readableBytes = byteBuf.readableBytes();
        if (byteBuf.hasArray()) {
            bArr = byteBuf.array();
            i = byteBuf.arrayOffset() + byteBuf.readerIndex();
        } else {
            bArr = new byte[readableBytes];
            byteBuf.getBytes(byteBuf.readerIndex(), bArr);
            i = 0;
        }
        this.f8541z.next_in = bArr;
        this.f8541z.next_in_index = i;
        this.f8541z.avail_in = readableBytes;
    }

    private ByteBuf encode(ByteBufAllocator byteBufAllocator) {
        ByteBuf byteBuf;
        int i;
        int i2;
        int ceil;
        try {
            i = this.f8541z.next_in_index;
            i2 = this.f8541z.next_out_index;
            ceil = ((int) Math.ceil(this.f8541z.next_in.length * 1.001d)) + 12;
            byteBuf = byteBufAllocator.heapBuffer(ceil);
        } catch (Throwable th) {
            th = th;
            byteBuf = null;
        }
        try {
            this.f8541z.next_out = byteBuf.array();
            this.f8541z.next_out_index = byteBuf.arrayOffset() + byteBuf.writerIndex();
            this.f8541z.avail_out = ceil;
            try {
                int deflate = this.f8541z.deflate(2);
                if (deflate != 0) {
                    throw new CompressionException("compression failure: " + deflate);
                }
                int i3 = this.f8541z.next_out_index - i2;
                if (i3 > 0) {
                    byteBuf.writerIndex(byteBuf.writerIndex() + i3);
                }
                this.f8541z.next_in = null;
                this.f8541z.next_out = null;
                return byteBuf;
            } finally {
                byteBuf.skipBytes(this.f8541z.next_in_index - i);
            }
        } catch (Throwable th2) {
            th = th2;
            this.f8541z.next_in = null;
            this.f8541z.next_out = null;
            if (byteBuf != null) {
                byteBuf.release();
            }
            throw th;
        }
    }

    @Override // io.netty.handler.codec.spdy.SpdyHeaderBlockRawEncoder, io.netty.handler.codec.spdy.SpdyHeaderBlockEncoder
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
            setInput(encode);
            return encode(byteBufAllocator);
        } finally {
            encode.release();
        }
    }

    @Override // io.netty.handler.codec.spdy.SpdyHeaderBlockRawEncoder, io.netty.handler.codec.spdy.SpdyHeaderBlockEncoder
    public void end() {
        if (this.finished) {
            return;
        }
        this.finished = true;
        this.f8541z.deflateEnd();
        this.f8541z.next_in = null;
        this.f8541z.next_out = null;
    }
}
