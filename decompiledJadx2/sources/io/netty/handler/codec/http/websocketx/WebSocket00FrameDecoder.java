package io.netty.handler.codec.http.websocketx;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.handler.codec.TooLongFrameException;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class WebSocket00FrameDecoder extends ReplayingDecoder<Void> implements WebSocketFrameDecoder {
    static final int DEFAULT_MAX_FRAME_SIZE = 16384;
    private final long maxFrameSize;
    private boolean receivedClosingHandshake;

    public WebSocket00FrameDecoder() {
        this(16384);
    }

    public WebSocket00FrameDecoder(int i) {
        this.maxFrameSize = i;
    }

    @Override // io.netty.handler.codec.ByteToMessageDecoder
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        WebSocketFrame decodeTextFrame;
        if (this.receivedClosingHandshake) {
            byteBuf.skipBytes(actualReadableBytes());
            return;
        }
        byte readByte = byteBuf.readByte();
        if ((readByte & 128) == 128) {
            decodeTextFrame = decodeBinaryFrame(channelHandlerContext, readByte, byteBuf);
        } else {
            decodeTextFrame = decodeTextFrame(channelHandlerContext, byteBuf);
        }
        if (decodeTextFrame != null) {
            list.add(decodeTextFrame);
        }
    }

    private WebSocketFrame decodeBinaryFrame(ChannelHandlerContext channelHandlerContext, byte b, ByteBuf byteBuf) {
        byte readByte;
        int i = 0;
        long j = 0;
        do {
            readByte = byteBuf.readByte();
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
        return new BinaryWebSocketFrame(ByteBufUtil.readBytes(channelHandlerContext.alloc(), byteBuf, (int) j));
    }

    private WebSocketFrame decodeTextFrame(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) {
        int readerIndex = byteBuf.readerIndex();
        int actualReadableBytes = actualReadableBytes();
        int indexOf = byteBuf.indexOf(readerIndex, readerIndex + actualReadableBytes, (byte) -1);
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
        ByteBuf readBytes = ByteBufUtil.readBytes(channelHandlerContext.alloc(), byteBuf, i);
        byteBuf.skipBytes(1);
        if (readBytes.indexOf(readBytes.readerIndex(), readBytes.writerIndex(), (byte) -1) >= 0) {
            readBytes.release();
            throw new IllegalArgumentException("a text frame should not contain 0xFF.");
        }
        return new TextWebSocketFrame(readBytes);
    }
}
