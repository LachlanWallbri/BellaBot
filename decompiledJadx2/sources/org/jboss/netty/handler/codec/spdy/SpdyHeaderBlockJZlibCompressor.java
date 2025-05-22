package org.jboss.netty.handler.codec.spdy;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.handler.codec.compression.CompressionException;
import org.jboss.netty.util.internal.jzlib.JZlib;
import org.jboss.netty.util.internal.jzlib.ZStream;

/* loaded from: classes7.dex */
class SpdyHeaderBlockJZlibCompressor extends SpdyHeaderBlockCompressor {

    /* renamed from: z */
    private final ZStream f10042z = new ZStream();

    public SpdyHeaderBlockJZlibCompressor(int i, int i2, int i3, int i4) {
        int deflateSetDictionary;
        if (i < 2 || i > 3) {
            throw new IllegalArgumentException("unsupported version: " + i);
        }
        if (i2 < 0 || i2 > 9) {
            throw new IllegalArgumentException("compressionLevel: " + i2 + " (expected: 0-9)");
        }
        if (i3 < 9 || i3 > 15) {
            throw new IllegalArgumentException("windowBits: " + i3 + " (expected: 9-15)");
        }
        if (i4 < 1 || i4 > 9) {
            throw new IllegalArgumentException("memLevel: " + i4 + " (expected: 1-9)");
        }
        int deflateInit = this.f10042z.deflateInit(i2, i3, i4, JZlib.W_ZLIB);
        if (deflateInit != 0) {
            throw new CompressionException("failed to initialize an SPDY header block deflater: " + deflateInit);
        }
        if (i < 3) {
            deflateSetDictionary = this.f10042z.deflateSetDictionary(SpdyCodecUtil.SPDY2_DICT, SpdyCodecUtil.SPDY2_DICT.length);
        } else {
            deflateSetDictionary = this.f10042z.deflateSetDictionary(SpdyCodecUtil.SPDY_DICT, SpdyCodecUtil.SPDY_DICT.length);
        }
        if (deflateSetDictionary == 0) {
            return;
        }
        throw new CompressionException("failed to set the SPDY dictionary: " + deflateSetDictionary);
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyHeaderBlockCompressor
    public void setInput(ChannelBuffer channelBuffer) {
        byte[] bArr = new byte[channelBuffer.readableBytes()];
        channelBuffer.readBytes(bArr);
        ZStream zStream = this.f10042z;
        zStream.next_in = bArr;
        zStream.next_in_index = 0;
        zStream.avail_in = bArr.length;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyHeaderBlockCompressor
    public void encode(ChannelBuffer channelBuffer) {
        try {
            byte[] bArr = new byte[((int) Math.ceil(this.f10042z.next_in.length * 1.001d)) + 12];
            this.f10042z.next_out = bArr;
            this.f10042z.next_out_index = 0;
            this.f10042z.avail_out = bArr.length;
            int deflate = this.f10042z.deflate(2);
            if (deflate != 0) {
                throw new CompressionException("compression failure: " + deflate);
            }
            if (this.f10042z.next_out_index != 0) {
                channelBuffer.writeBytes(bArr, 0, this.f10042z.next_out_index);
            }
        } finally {
            ZStream zStream = this.f10042z;
            zStream.next_in = null;
            zStream.next_out = null;
        }
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyHeaderBlockCompressor
    public void end() {
        this.f10042z.deflateEnd();
        ZStream zStream = this.f10042z;
        zStream.next_in = null;
        zStream.next_out = null;
    }
}
