package org.jboss.netty.handler.codec.http.websocketx;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.TooLongFrameException;
import org.jboss.netty.handler.codec.replay.ReplayingDecoder;
import org.jboss.netty.handler.codec.replay.VoidEnum;

/* loaded from: classes7.dex */
public class WebSocket00FrameDecoder extends ReplayingDecoder<VoidEnum> {
    private static final int DEFAULT_MAX_FRAME_SIZE = 16384;
    private final long maxFrameSize;
    private boolean receivedClosingHandshake;

    public WebSocket00FrameDecoder() {
        this(16384);
    }

    @Deprecated
    public WebSocket00FrameDecoder(int i) {
        this.maxFrameSize = i;
    }

    public WebSocket00FrameDecoder(long j) {
        this.maxFrameSize = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jboss.netty.handler.codec.replay.ReplayingDecoder
    public Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer, VoidEnum voidEnum) throws Exception {
        if (this.receivedClosingHandshake) {
            channelBuffer.skipBytes(actualReadableBytes());
            return null;
        }
        byte readByte = channelBuffer.readByte();
        if ((readByte & 128) == 128) {
            return decodeBinaryFrame(readByte, channelBuffer);
        }
        return decodeTextFrame(channelBuffer);
    }

    private WebSocketFrame decodeBinaryFrame(byte b, ChannelBuffer channelBuffer) throws TooLongFrameException {
        byte readByte;
        int i = 0;
        long j = 0;
        do {
            readByte = channelBuffer.readByte();
            j = (j << 7) | (readByte & Byte.MAX_VALUE);
            if (j > this.maxFrameSize) {
                throw new TooLongFrameException();
            }
            i++;
            if (i > 8) {
                throw new TooLongFrameException();
            }
        } while ((readByte & 128) == 128);
        if (b == -1 && j == 0) {
            this.receivedClosingHandshake = true;
            return new CloseWebSocketFrame();
        }
        return new BinaryWebSocketFrame(channelBuffer.readBytes((int) j));
    }

    private WebSocketFrame decodeTextFrame(ChannelBuffer channelBuffer) throws TooLongFrameException {
        int readerIndex = channelBuffer.readerIndex();
        int actualReadableBytes = actualReadableBytes();
        int indexOf = channelBuffer.indexOf(readerIndex, readerIndex + actualReadableBytes, (byte) -1);
        if (indexOf == -1) {
            if (actualReadableBytes <= this.maxFrameSize) {
                return null;
            }
            throw new TooLongFrameException();
        }
        int i = indexOf - readerIndex;
        if (i > this.maxFrameSize) {
            throw new TooLongFrameException();
        }
        ChannelBuffer readBytes = channelBuffer.readBytes(i);
        channelBuffer.skipBytes(1);
        if (readBytes.indexOf(readBytes.readerIndex(), readBytes.writerIndex(), (byte) -1) >= 0) {
            throw new IllegalArgumentException("a text frame should not contain 0xFF.");
        }
        return new TextWebSocketFrame(readBytes);
    }
}
