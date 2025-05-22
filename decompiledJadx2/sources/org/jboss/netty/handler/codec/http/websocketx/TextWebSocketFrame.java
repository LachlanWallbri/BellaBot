package org.jboss.netty.handler.codec.http.websocketx;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.util.CharsetUtil;

/* loaded from: classes7.dex */
public class TextWebSocketFrame extends WebSocketFrame {
    public TextWebSocketFrame() {
        setBinaryData(ChannelBuffers.EMPTY_BUFFER);
    }

    public TextWebSocketFrame(String str) {
        if (str == null || str.length() == 0) {
            setBinaryData(ChannelBuffers.EMPTY_BUFFER);
        } else {
            setBinaryData(ChannelBuffers.copiedBuffer(str, CharsetUtil.UTF_8));
        }
    }

    public TextWebSocketFrame(ChannelBuffer channelBuffer) {
        setBinaryData(channelBuffer);
    }

    public TextWebSocketFrame(boolean z, int i, String str) {
        setFinalFragment(z);
        setRsv(i);
        if (str == null || str.length() == 0) {
            setBinaryData(ChannelBuffers.EMPTY_BUFFER);
        } else {
            setBinaryData(ChannelBuffers.copiedBuffer(str, CharsetUtil.UTF_8));
        }
    }

    public TextWebSocketFrame(boolean z, int i, ChannelBuffer channelBuffer) {
        setFinalFragment(z);
        setRsv(i);
        setBinaryData(channelBuffer);
    }

    public String getText() {
        if (getBinaryData() == null) {
            return null;
        }
        return getBinaryData().toString(CharsetUtil.UTF_8);
    }

    public void setText(String str) {
        if (str == null) {
            throw new NullPointerException("text");
        }
        setBinaryData(ChannelBuffers.copiedBuffer(str, CharsetUtil.UTF_8));
    }

    public String toString() {
        return getClass().getSimpleName() + "(text: " + getText() + ')';
    }
}
