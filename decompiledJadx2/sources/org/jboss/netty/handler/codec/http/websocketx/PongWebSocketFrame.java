package org.jboss.netty.handler.codec.http.websocketx;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

/* loaded from: classes7.dex */
public class PongWebSocketFrame extends WebSocketFrame {
    public PongWebSocketFrame() {
        setBinaryData(ChannelBuffers.EMPTY_BUFFER);
    }

    public PongWebSocketFrame(ChannelBuffer channelBuffer) {
        setBinaryData(channelBuffer);
    }

    public PongWebSocketFrame(boolean z, int i, ChannelBuffer channelBuffer) {
        setFinalFragment(z);
        setRsv(i);
        setBinaryData(channelBuffer);
    }

    public String toString() {
        return getClass().getSimpleName() + "(data: " + getBinaryData() + ')';
    }
}
