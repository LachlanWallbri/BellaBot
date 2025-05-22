package io.netty.handler.codec.http.websocketx;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.TooLongFrameException;
import io.netty.util.ReferenceCounted;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class WebSocket08FrameEncoder extends MessageToMessageEncoder<WebSocketFrame> implements WebSocketFrameEncoder {
    private static final int GATHERING_WRITE_THRESHOLD = 1024;
    private static final byte OPCODE_BINARY = 2;
    private static final byte OPCODE_CLOSE = 8;
    private static final byte OPCODE_CONT = 0;
    private static final byte OPCODE_PING = 9;
    private static final byte OPCODE_PONG = 10;
    private static final byte OPCODE_TEXT = 1;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) WebSocket08FrameEncoder.class);
    private final boolean maskPayload;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.MessageToMessageEncoder
    public /* bridge */ /* synthetic */ void encode(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame, List list) throws Exception {
        encode2(channelHandlerContext, webSocketFrame, (List<Object>) list);
    }

    public WebSocket08FrameEncoder(boolean z) {
        this.maskPayload = z;
    }

    /* renamed from: encode, reason: avoid collision after fix types in other method */
    protected void encode2(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame, List<Object> list) throws Exception {
        int i;
        ReferenceCounted referenceCounted;
        int i2;
        ByteBuf buffer;
        ByteBuf content = webSocketFrame.content();
        int i3 = 0;
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
        int readableBytes = content.readableBytes();
        if (logger.isDebugEnabled()) {
            logger.debug("Encoding WebSocket Frame opCode=" + i + " length=" + readableBytes);
        }
        int rsv = ((webSocketFrame.rsv() % 8) << 4) | (webSocketFrame.isFinalFragment() ? 128 : 0) | (i % 128);
        if (i == 9 && readableBytes > 125) {
            throw new TooLongFrameException("invalid payload for PING (payload length must be <= 125, was " + readableBytes);
        }
        try {
            i2 = this.maskPayload ? 4 : 0;
        } catch (Throwable th) {
            th = th;
            referenceCounted = null;
        }
        try {
            if (readableBytes <= 125) {
                int i4 = i2 + 2;
                if (this.maskPayload || readableBytes <= 1024) {
                    i4 += readableBytes;
                }
                buffer = channelHandlerContext.alloc().buffer(i4);
                buffer.writeByte(rsv);
                buffer.writeByte((byte) (this.maskPayload ? ((byte) readableBytes) | 128 : (byte) readableBytes));
            } else if (readableBytes <= 65535) {
                int i5 = i2 + 4;
                if (this.maskPayload || readableBytes <= 1024) {
                    i5 += readableBytes;
                }
                buffer = channelHandlerContext.alloc().buffer(i5);
                buffer.writeByte(rsv);
                buffer.writeByte(this.maskPayload ? 254 : 126);
                buffer.writeByte((readableBytes >>> 8) & 255);
                buffer.writeByte(readableBytes & 255);
            } else {
                int i6 = i2 + 10;
                if (this.maskPayload || readableBytes <= 1024) {
                    i6 += readableBytes;
                }
                buffer = channelHandlerContext.alloc().buffer(i6);
                buffer.writeByte(rsv);
                buffer.writeByte(this.maskPayload ? 255 : 127);
                buffer.writeLong(readableBytes);
            }
            if (this.maskPayload) {
                byte[] array = ByteBuffer.allocate(4).putInt((int) (Math.random() * 2.147483647E9d)).array();
                buffer.writeBytes(array);
                ByteOrder order = content.order();
                ByteOrder order2 = buffer.order();
                int readerIndex = content.readerIndex();
                int writerIndex = content.writerIndex();
                if (order == order2) {
                    int i7 = (255 & array[3]) | ((array[2] & 255) << 8) | ((array[1] & 255) << 16) | ((array[0] & 255) << 24);
                    if (order == ByteOrder.LITTLE_ENDIAN) {
                        i7 = Integer.reverseBytes(i7);
                    }
                    while (readerIndex + 3 < writerIndex) {
                        buffer.writeInt(content.getInt(readerIndex) ^ i7);
                        readerIndex += 4;
                    }
                }
                while (readerIndex < writerIndex) {
                    buffer.writeByte(content.getByte(readerIndex) ^ array[i3 % 4]);
                    readerIndex++;
                    i3++;
                }
                list.add(buffer);
                return;
            }
            if (buffer.writableBytes() >= content.readableBytes()) {
                buffer.writeBytes(content);
                list.add(buffer);
            } else {
                list.add(buffer);
                list.add(content.retain());
            }
        } catch (Throwable th2) {
            th = th2;
            referenceCounted = 10;
            if (referenceCounted != null) {
                referenceCounted.release();
            }
            throw th;
        }
    }
}
