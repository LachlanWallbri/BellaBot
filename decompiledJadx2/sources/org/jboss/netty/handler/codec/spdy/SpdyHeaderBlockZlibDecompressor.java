package org.jboss.netty.handler.codec.spdy;

import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import org.jboss.netty.buffer.ChannelBuffer;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public class SpdyHeaderBlockZlibDecompressor extends SpdyHeaderBlockDecompressor {
    private final int version;
    private final byte[] out = new byte[8192];
    private final Inflater decompressor = new Inflater();

    public SpdyHeaderBlockZlibDecompressor(int i) {
        if (i < 2 || i > 3) {
            throw new IllegalArgumentException("unsupported version: " + i);
        }
        this.version = i;
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyHeaderBlockDecompressor
    public void setInput(ChannelBuffer channelBuffer) {
        byte[] bArr = new byte[channelBuffer.readableBytes()];
        channelBuffer.readBytes(bArr);
        this.decompressor.setInput(bArr);
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyHeaderBlockDecompressor
    public void decode(ChannelBuffer channelBuffer) throws Exception {
        try {
            int inflate = this.decompressor.inflate(this.out);
            if (inflate == 0 && this.decompressor.needsDictionary()) {
                if (this.version < 3) {
                    this.decompressor.setDictionary(SpdyCodecUtil.SPDY2_DICT);
                } else {
                    this.decompressor.setDictionary(SpdyCodecUtil.SPDY_DICT);
                }
                inflate = this.decompressor.inflate(this.out);
            }
            channelBuffer.writeBytes(this.out, 0, inflate);
        } catch (DataFormatException e) {
            throw new SpdyProtocolException("Received invalid header block", e);
        }
    }

    @Override // org.jboss.netty.handler.codec.spdy.SpdyHeaderBlockDecompressor
    public void end() {
        this.decompressor.end();
    }
}
