package org.jboss.netty.handler.codec.spdy;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.util.internal.DetectionUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public abstract class SpdyHeaderBlockCompressor {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void encode(ChannelBuffer channelBuffer);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void end();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setInput(ChannelBuffer channelBuffer);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SpdyHeaderBlockCompressor newInstance(int i, int i2, int i3, int i4) {
        if (DetectionUtil.javaVersion() >= 7) {
            return new SpdyHeaderBlockZlibCompressor(i, i2);
        }
        return new SpdyHeaderBlockJZlibCompressor(i, i2, i3, i4);
    }
}
