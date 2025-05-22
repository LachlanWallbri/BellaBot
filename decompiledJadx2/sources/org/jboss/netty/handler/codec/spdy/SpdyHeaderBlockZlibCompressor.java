package org.jboss.netty.handler.codec.spdy;

import java.util.zip.Deflater;
import org.jboss.netty.buffer.ChannelBuffer;

/* loaded from: classes7.dex */
class SpdyHeaderBlockZlibCompressor extends SpdyHeaderBlockCompressor {
    private final Deflater compressor;
    private final byte[] out = new byte[8192];

    public SpdyHeaderBlockZlibCompressor(int i, int i2) {
        if (i < 2 || i > 3) {
            throw new IllegalArgumentException("unsupported version: " + i);
        }
        if (i2 < 0 || i2 > 9) {
            throw new IllegalArgumentException("compressionLevel: " + i2 + " (expected: 0-9)");
        }
        this.compressor = new Deflater(i2);
        if (i < 3) {
            this.compressor.setDictionary(SpdyCodecUtil.SPDY2_DICT);
        } else {
            this.compressor.setDictionary(SpdyCodecUtil.SPDY_DICT);
        }
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyHeaderBlockCompressor
    public void setInput(ChannelBuffer channelBuffer) {
        byte[] bArr = new byte[channelBuffer.readableBytes()];
        channelBuffer.readBytes(bArr);
        this.compressor.setInput(bArr);
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyHeaderBlockCompressor
    public void encode(ChannelBuffer channelBuffer) {
        while (!this.compressor.needsInput()) {
            Deflater deflater = this.compressor;
            byte[] bArr = this.out;
            channelBuffer.writeBytes(this.out, 0, deflater.deflate(bArr, 0, bArr.length, 2));
        }
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyHeaderBlockCompressor
    public void end() {
        this.compressor.end();
    }
}
