package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx;

import android.support.v4.media.session.PlaybackStateCompat;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.TooLongFrameException;
import io.grpc.netty.shaded.io.netty.util.ReferenceCounted;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.nio.ByteOrder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class WebSocket08FrameDecoder extends ByteToMessageDecoder implements WebSocketFrameDecoder {
    private static final byte OPCODE_BINARY = 2;
    private static final byte OPCODE_CLOSE = 8;
    private static final byte OPCODE_CONT = 0;
    private static final byte OPCODE_PING = 9;
    private static final byte OPCODE_PONG = 10;
    private static final byte OPCODE_TEXT = 1;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) WebSocket08FrameDecoder.class);
    private final WebSocketDecoderConfig config;
    private int fragmentedFramesCount;
    private boolean frameFinalFlag;
    private boolean frameMasked;
    private int frameOpcode;
    private int framePayloadLen1;
    private long framePayloadLength;
    private int frameRsv;
    private byte[] maskingKey;
    private boolean receivedClosingHandshake;
    private State state;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum State {
        READING_FIRST,
        READING_SECOND,
        READING_SIZE,
        MASKING_KEY,
        PAYLOAD,
        CORRUPT
    }

    public WebSocket08FrameDecoder(boolean z, boolean z2, int i) {
        this(z, z2, i, false);
    }

    public WebSocket08FrameDecoder(boolean z, boolean z2, int i, boolean z3) {
        this(WebSocketDecoderConfig.newBuilder().expectMaskedFrames(z).allowExtensions(z2).maxFramePayloadLength(i).allowMaskMismatch(z3).build());
    }

    public WebSocket08FrameDecoder(WebSocketDecoderConfig webSocketDecoderConfig) {
        this.state = State.READING_FIRST;
        this.config = (WebSocketDecoderConfig) ObjectUtil.checkNotNull(webSocketDecoderConfig, "decoderConfig");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x002a. Please report as an issue. */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int i;
        int i2;
        if (this.receivedClosingHandshake) {
            byteBuf.skipBytes(actualReadableBytes());
            return;
        }
        switch (this.state) {
            case READING_FIRST:
                if (!byteBuf.isReadable()) {
                    return;
                }
                this.framePayloadLength = 0L;
                byte readByte = byteBuf.readByte();
                this.frameFinalFlag = (readByte & 128) != 0;
                this.frameRsv = (readByte & 112) >> 4;
                this.frameOpcode = readByte & 15;
                if (logger.isTraceEnabled()) {
                    logger.trace("Decoding WebSocket Frame opCode={}", Integer.valueOf(this.frameOpcode));
                }
                this.state = State.READING_SECOND;
            case READING_SECOND:
                if (!byteBuf.isReadable()) {
                    return;
                }
                byte readByte2 = byteBuf.readByte();
                this.frameMasked = (readByte2 & 128) != 0;
                this.framePayloadLen1 = readByte2 & Byte.MAX_VALUE;
                if (this.frameRsv != 0 && !this.config.allowExtensions()) {
                    protocolViolation(channelHandlerContext, byteBuf, "RSV != 0 and no extension negotiated, RSV:" + this.frameRsv);
                    return;
                }
                if (!this.config.allowMaskMismatch() && this.config.expectMaskedFrames() != this.frameMasked) {
                    protocolViolation(channelHandlerContext, byteBuf, "received a frame that is not masked as expected");
                    return;
                }
                int i3 = this.frameOpcode;
                if (i3 > 7) {
                    if (!this.frameFinalFlag) {
                        protocolViolation(channelHandlerContext, byteBuf, "fragmented control frame");
                        return;
                    }
                    if (this.framePayloadLen1 > 125) {
                        protocolViolation(channelHandlerContext, byteBuf, "control frame with payload length > 125 octets");
                        return;
                    }
                    if (i3 != 8 && i3 != 9 && i3 != 10) {
                        protocolViolation(channelHandlerContext, byteBuf, "control frame using reserved opcode " + this.frameOpcode);
                        return;
                    }
                    if (this.frameOpcode == 8 && this.framePayloadLen1 == 1) {
                        protocolViolation(channelHandlerContext, byteBuf, "received close control frame with payload len 1");
                        return;
                    }
                } else {
                    if (i3 != 0 && i3 != 1 && i3 != 2) {
                        protocolViolation(channelHandlerContext, byteBuf, "data frame using reserved opcode " + this.frameOpcode);
                        return;
                    }
                    if (this.fragmentedFramesCount == 0 && this.frameOpcode == 0) {
                        protocolViolation(channelHandlerContext, byteBuf, "received continuation data frame outside fragmented message");
                        return;
                    } else if (this.fragmentedFramesCount != 0 && (i2 = this.frameOpcode) != 0 && i2 != 9) {
                        protocolViolation(channelHandlerContext, byteBuf, "received non-continuation data frame while inside fragmented message");
                        return;
                    }
                }
                this.state = State.READING_SIZE;
                break;
            case READING_SIZE:
                int i4 = this.framePayloadLen1;
                if (i4 == 126) {
                    if (byteBuf.readableBytes() < 2) {
                        return;
                    }
                    this.framePayloadLength = byteBuf.readUnsignedShort();
                    if (this.framePayloadLength < 126) {
                        protocolViolation(channelHandlerContext, byteBuf, "invalid data frame length (not using minimal length encoding)");
                        return;
                    }
                } else if (i4 == 127) {
                    if (byteBuf.readableBytes() < 8) {
                        return;
                    }
                    this.framePayloadLength = byteBuf.readLong();
                    if (this.framePayloadLength < PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                        protocolViolation(channelHandlerContext, byteBuf, "invalid data frame length (not using minimal length encoding)");
                        return;
                    }
                } else {
                    this.framePayloadLength = i4;
                }
                if (this.framePayloadLength > this.config.maxFramePayloadLength()) {
                    protocolViolation(channelHandlerContext, byteBuf, WebSocketCloseStatus.MESSAGE_TOO_BIG, "Max frame length of " + this.config.maxFramePayloadLength() + " has been exceeded.");
                    return;
                }
                if (logger.isTraceEnabled()) {
                    logger.trace("Decoding WebSocket Frame length={}", Long.valueOf(this.framePayloadLength));
                }
                this.state = State.MASKING_KEY;
            case MASKING_KEY:
                if (this.frameMasked) {
                    if (byteBuf.readableBytes() < 4) {
                        return;
                    }
                    if (this.maskingKey == null) {
                        this.maskingKey = new byte[4];
                    }
                    byteBuf.readBytes(this.maskingKey);
                }
                this.state = State.PAYLOAD;
            case PAYLOAD:
                if (byteBuf.readableBytes() < this.framePayloadLength) {
                    return;
                }
                ReferenceCounted referenceCounted = null;
                try {
                    ByteBuf readBytes = ByteBufUtil.readBytes(channelHandlerContext.alloc(), byteBuf, toFrameLength(this.framePayloadLength));
                    this.state = State.READING_FIRST;
                    if (this.frameMasked) {
                        unmask(readBytes);
                    }
                    if (this.frameOpcode == 9) {
                        list.add(new PingWebSocketFrame(this.frameFinalFlag, this.frameRsv, readBytes));
                        return;
                    }
                    if (this.frameOpcode == 10) {
                        list.add(new PongWebSocketFrame(this.frameFinalFlag, this.frameRsv, readBytes));
                        return;
                    }
                    if (this.frameOpcode == 8) {
                        this.receivedClosingHandshake = true;
                        checkCloseFrameBody(channelHandlerContext, readBytes);
                        list.add(new CloseWebSocketFrame(this.frameFinalFlag, this.frameRsv, readBytes));
                        return;
                    }
                    if (this.frameFinalFlag) {
                        if (this.frameOpcode != 9) {
                            this.fragmentedFramesCount = 0;
                        }
                        i = 1;
                    } else {
                        i = 1;
                        this.fragmentedFramesCount++;
                    }
                    if (this.frameOpcode == i) {
                        list.add(new TextWebSocketFrame(this.frameFinalFlag, this.frameRsv, readBytes));
                        return;
                    }
                    if (this.frameOpcode == 2) {
                        list.add(new BinaryWebSocketFrame(this.frameFinalFlag, this.frameRsv, readBytes));
                        return;
                    } else {
                        if (this.frameOpcode == 0) {
                            list.add(new ContinuationWebSocketFrame(this.frameFinalFlag, this.frameRsv, readBytes));
                            return;
                        }
                        throw new UnsupportedOperationException("Cannot decode web socket frame with opcode: " + this.frameOpcode);
                    }
                } catch (Throwable th) {
                    if (0 != 0) {
                        referenceCounted.release();
                    }
                    throw th;
                }
            case CORRUPT:
                if (byteBuf.isReadable()) {
                    byteBuf.readByte();
                    return;
                }
                return;
            default:
                throw new Error("Shouldn't reach here.");
        }
    }

    private void unmask(ByteBuf byteBuf) {
        int readerIndex = byteBuf.readerIndex();
        int writerIndex = byteBuf.writerIndex();
        ByteOrder order = byteBuf.order();
        byte[] bArr = this.maskingKey;
        int i = (bArr[3] & 255) | ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8);
        if (order == ByteOrder.LITTLE_ENDIAN) {
            i = Integer.reverseBytes(i);
        }
        while (readerIndex + 3 < writerIndex) {
            byteBuf.setInt(readerIndex, byteBuf.getInt(readerIndex) ^ i);
            readerIndex += 4;
        }
        while (readerIndex < writerIndex) {
            byteBuf.setByte(readerIndex, byteBuf.getByte(readerIndex) ^ this.maskingKey[readerIndex % 4]);
            readerIndex++;
        }
    }

    private void protocolViolation(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, String str) {
        protocolViolation(channelHandlerContext, byteBuf, WebSocketCloseStatus.PROTOCOL_ERROR, str);
    }

    private void protocolViolation(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, WebSocketCloseStatus webSocketCloseStatus, String str) {
        protocolViolation(channelHandlerContext, byteBuf, new CorruptedWebSocketFrameException(webSocketCloseStatus, str));
    }

    private void protocolViolation(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, CorruptedWebSocketFrameException corruptedWebSocketFrameException) {
        Object obj;
        this.state = State.CORRUPT;
        int readableBytes = byteBuf.readableBytes();
        if (readableBytes > 0) {
            byteBuf.skipBytes(readableBytes);
        }
        if (!channelHandlerContext.channel().isActive()) {
            throw corruptedWebSocketFrameException;
        }
        if (this.config.closeOnProtocolViolation()) {
            if (this.receivedClosingHandshake) {
                obj = Unpooled.EMPTY_BUFFER;
            } else {
                WebSocketCloseStatus closeStatus = corruptedWebSocketFrameException.closeStatus();
                String message = corruptedWebSocketFrameException.getMessage();
                if (message == null) {
                    message = closeStatus.reasonText();
                }
                obj = new CloseWebSocketFrame(closeStatus, message);
            }
            channelHandlerContext.writeAndFlush(obj).addListener((GenericFutureListener<? extends Future<? super Void>>) ChannelFutureListener.CLOSE);
            throw corruptedWebSocketFrameException;
        }
        throw corruptedWebSocketFrameException;
    }

    private static int toFrameLength(long j) {
        if (j <= 2147483647L) {
            return (int) j;
        }
        throw new TooLongFrameException("Length:" + j);
    }

    protected void checkCloseFrameBody(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) {
        if (byteBuf == null || !byteBuf.isReadable()) {
            return;
        }
        if (byteBuf.readableBytes() == 1) {
            protocolViolation(channelHandlerContext, byteBuf, WebSocketCloseStatus.INVALID_PAYLOAD_DATA, "Invalid close frame body");
        }
        int readerIndex = byteBuf.readerIndex();
        byteBuf.readerIndex(0);
        short readShort = byteBuf.readShort();
        if (!WebSocketCloseStatus.isValidStatusCode(readShort)) {
            protocolViolation(channelHandlerContext, byteBuf, "Invalid close frame getStatus code: " + ((int) readShort));
        }
        if (byteBuf.isReadable()) {
            try {
                new Utf8Validator().check(byteBuf);
            } catch (CorruptedWebSocketFrameException e) {
                protocolViolation(channelHandlerContext, byteBuf, e);
            }
        }
        byteBuf.readerIndex(readerIndex);
    }
}
