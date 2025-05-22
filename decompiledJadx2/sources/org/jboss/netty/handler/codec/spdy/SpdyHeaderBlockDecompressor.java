package org.jboss.netty.handler.codec.spdy;

import org.jboss.netty.buffer.ChannelBuffer;

/* loaded from: classes7.dex */
abstract class SpdyHeaderBlockDecompressor {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void decode(ChannelBuffer channelBuffer) throws Exception;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void end();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setInput(ChannelBuffer channelBuffer);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SpdyHeaderBlockDecompressor newInstance(int i) {
        return new SpdyHeaderBlockZlibDecompressor(i);
    }
}
