package org.jboss.netty.handler.codec.http.websocketx;

import java.nio.ByteBuffer;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.TooLongFrameException;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;

/* loaded from: classes7.dex */
public class WebSocket08FrameEncoder extends OneToOneEncoder {
    private static final byte OPCODE_BINARY = 2;
    private static final byte OPCODE_CLOSE = 8;
    private static final byte OPCODE_CONT = 0;
    private static final byte OPCODE_PING = 9;
    private static final byte OPCODE_PONG = 10;
    private static final byte OPCODE_TEXT = 1;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) WebSocket08FrameEncoder.class);
    private final boolean maskPayload;

    public WebSocket08FrameEncoder(boolean z) {
        this.maskPayload = z;
    }

    @Override // org.jboss.netty.handler.codec.oneone.OneToOneEncoder
    protected Object encode(ChannelHandlerContext channelHandlerContext, Channel channel, Object obj) throws Exception {
        int i;
        ChannelBuffer buffer;
        if (!(obj instanceof WebSocketFrame)) {
            return obj;
        }
        WebSocketFrame webSocketFrame = (WebSocketFrame) obj;
        ChannelBuffer binaryData = webSocketFrame.getBinaryData();
        if (binaryData == null) {
            binaryData = ChannelBuffers.EMPTY_BUFFER;
        }
        if (webSocketFrame instanceof TextWebSocketFrame) {
            i = 1;
        } else if (webSocketFrame instanceof PingWebSocketFrame) {
            i = 9;
        } else if (webSocketFrame instanceof PongWebSocketFrame) {
            i = 10;
        } else if (webSocketFrame instanceof CloseWebSocketFrame) {
            i = 8;
        } else if (webSocketFrame instanceof BinaryWebSocketFrame) {
            i = 2;
        } else {
            if (!(webSocketFrame instanceof ContinuationWebSocketFrame)) {
                throw new UnsupportedOperationException("Cannot encode frame of type: " + webSocketFrame.getClass().getName());
            }
            i = 0;
        }
        int readableBytes = binaryData.readableBytes();
        if (logger.isDebugEnabled()) {
            logger.debug("Encoding WebSocket Frame opCode=" + i + " length=" + readableBytes);
        }
        int rsv = ((webSocketFrame.getRsv() % 8) << 4) | (webSocketFrame.isFinalFragment() ? 128 : 0) | (i % 128);
        if (i == 9 && readableBytes > 125) {
            throw new TooLongFrameException("invalid payload for PING (payload length must be <= 125, was " + readableBytes);
        }
        int i2 = this.maskPayload ? 4 : 0;
        if (readableBytes <= 125) {
            buffer = ChannelBuffers.buffer(i2 + 2);
            buffer.writeByte(rsv);
            buffer.writeByte((byte) (this.maskPayload ? ((byte) readableBytes) | 128 : (byte) readableBytes));
        } else {
            if (readableBytes <= 65535) {
                buffer = ChannelBuffers.buffer(i2 + 4);
                buffer.writeByte(rsv);
                buffer.writeByte(this.maskPayload ? 254 : 126);
                buffer.writeByte((readableBytes >>> 8) & 255);
                buffer.writeByte(readableBytes & 255);
            } else {
                buffer = ChannelBuffers.buffer(i2 + 10);
                buffer.writeByte(rsv);
                buffer.writeByte(this.maskPayload ? 255 : 127);
                buffer.writeLong(readableBytes);
            }
        }
        if (this.maskPayload) {
            byte[] array = ByteBuffer.allocate(4).putInt(Integer.valueOf((int) (Math.random() * 2.147483647E9d)).intValue()).array();
            buffer.writeBytes(array);
            ChannelBuffer buffer2 = ChannelBuffers.buffer(readableBytes);
            int i3 = 0;
            while (binaryData.readableBytes() > 0) {
                buffer2.writeByte(array[i3 % 4] ^ binaryData.readByte());
                i3++;
            }
            binaryData = buffer2;
        }
        return ChannelBuffers.wrappedBuffer(buffer, binaryData);
    }
}
