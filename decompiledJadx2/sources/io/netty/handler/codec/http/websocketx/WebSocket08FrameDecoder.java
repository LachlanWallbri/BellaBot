package io.netty.handler.codec.http.websocketx;

import android.support.v4.media.session.PlaybackStateCompat;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;
import io.netty.handler.codec.TooLongFrameException;
import io.netty.util.ReferenceCounted;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.nio.ByteOrder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class WebSocket08FrameDecoder extends ByteToMessageDecoder implements WebSocketFrameDecoder {
    private static final byte OPCODE_BINARY = 2;
    private static final byte OPCODE_CLOSE = 8;
    private static final byte OPCODE_CONT = 0;
    private static final byte OPCODE_PING = 9;
    private static final byte OPCODE_PONG = 10;
    private static final byte OPCODE_TEXT = 1;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) WebSocket08FrameDecoder.class);
    private final boolean allowExtensions;
    private final boolean allowMaskMismatch;
    private final boolean expectMaskedFrames;
    private int fragmentedFramesCount;
    private boolean frameFinalFlag;
    private boolean frameMasked;
    private int frameOpcode;
    private int framePayloadLen1;
    private long framePayloadLength;
    private int frameRsv;
    private byte[] maskingKey;
    private final long maxFramePayloadLength;
    private boolean receivedClosingHandshake;
    private State state;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
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
        this.state = State.READING_FIRST;
        this.expectMaskedFrames = z;
        this.allowMaskMismatch = z3;
        this.allowExtensions = z2;
        this.maxFramePayloadLength = i;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x002a. Please report as an issue. */
    @Override // io.netty.handler.codec.ByteToMessageDecoder
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int i;
        int i2;
        if (this.receivedClosingHandshake) {
            byteBuf.skipBytes(actualReadableBytes());
            return;
        }
        switch (C71561.f8503xda7b7094[this.state.ordinal()]) {
            case 1:
                if (!byteBuf.isReadable()) {
                    return;
                }
                this.framePayloadLength = 0L;
                byte readByte = byteBuf.readByte();
                this.frameFinalFlag = (readByte & 128) != 0;
                this.frameRsv = (readByte & 112) >> 4;
                this.frameOpcode = readByte & 15;
                if (logger.isDebugEnabled()) {
                    logger.debug("Decoding WebSocket Frame opCode={}", Integer.valueOf(this.frameOpcode));
                }
                this.state = State.READING_SECOND;
            case 2:
                if (!byteBuf.isReadable()) {
                    return;
                }
                byte readByte2 = byteBuf.readByte();
                this.frameMasked = (readByte2 & 128) != 0;
                this.framePayloadLen1 = readByte2 & Byte.MAX_VALUE;
                if (this.frameRsv != 0 && !this.allowExtensions) {
                    protocolViolation(channelHandlerContext, "RSV != 0 and no extension negotiated, RSV:" + this.frameRsv);
                    return;
                }
                if (!this.allowMaskMismatch && this.expectMaskedFrames != this.frameMasked) {
                    protocolViolation(channelHandlerContext, "received a frame that is not masked as expected");
                    return;
                }
                int i3 = this.frameOpcode;
                if (i3 > 7) {
                    if (!this.frameFinalFlag) {
                        protocolViolation(channelHandlerContext, "fragmented control frame");
                        return;
                    }
                    if (this.framePayloadLen1 > 125) {
                        protocolViolation(channelHandlerContext, "control frame with payload length > 125 octets");
                        return;
                    }
                    if (i3 != 8 && i3 != 9 && i3 != 10) {
                        protocolViolation(channelHandlerContext, "control frame using reserved opcode " + this.frameOpcode);
                        return;
                    }
                    if (this.frameOpcode == 8 && this.framePayloadLen1 == 1) {
                        protocolViolation(channelHandlerContext, "received close control frame with payload len 1");
                        return;
                    }
                } else {
                    if (i3 != 0 && i3 != 1 && i3 != 2) {
                        protocolViolation(channelHandlerContext, "data frame using reserved opcode " + this.frameOpcode);
                        return;
                    }
                    if (this.fragmentedFramesCount == 0 && this.frameOpcode == 0) {
                        protocolViolation(channelHandlerContext, "received continuation data frame outside fragmented message");
                        return;
                    } else if (this.fragmentedFramesCount != 0 && (i2 = this.frameOpcode) != 0 && i2 != 9) {
                        protocolViolation(channelHandlerContext, "received non-continuation data frame while inside fragmented message");
                        return;
                    }
                }
                this.state = State.READING_SIZE;
                break;
            case 3:
                int i4 = this.framePayloadLen1;
                if (i4 == 126) {
                    if (byteBuf.readableBytes() < 2) {
                        return;
                    }
                    long readUnsignedShort = byteBuf.readUnsignedShort();
                    this.framePayloadLength = readUnsignedShort;
                    if (readUnsignedShort < 126) {
                        protocolViolation(channelHandlerContext, "invalid data frame length (not using minimal length encoding)");
                        return;
                    }
                } else if (i4 == 127) {
                    if (byteBuf.readableBytes() < 8) {
                        return;
                    }
                    long readLong = byteBuf.readLong();
                    this.framePayloadLength = readLong;
                    if (readLong < PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                        protocolViolation(channelHandlerContext, "invalid data frame length (not using minimal length encoding)");
                        return;
                    }
                } else {
                    this.framePayloadLength = i4;
                }
                if (this.framePayloadLength > this.maxFramePayloadLength) {
                    protocolViolation(channelHandlerContext, "Max frame length of " + this.maxFramePayloadLength + " has been exceeded.");
                    return;
                }
                if (logger.isDebugEnabled()) {
                    logger.debug("Decoding WebSocket Frame length={}", Long.valueOf(this.framePayloadLength));
                }
                this.state = State.MASKING_KEY;
            case 4:
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
            case 5:
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
            case 6:
                if (byteBuf.isReadable()) {
                    byteBuf.readByte();
                    return;
                }
                return;
            default:
                throw new Error("Shouldn't reach here.");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes8.dex
     */
    /* renamed from: io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C71561 {

        /* renamed from: $SwitchMap$io$netty$handler$codec$http$websocketx$WebSocket08FrameDecoder$State */
        static final /* synthetic */ int[] f8503xda7b7094;

        static {
            int[] iArr = new int[State.values().length];
            f8503xda7b7094 = iArr;
            try {
                iArr[State.READING_FIRST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8503xda7b7094[State.READING_SECOND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8503xda7b7094[State.READING_SIZE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8503xda7b7094[State.MASKING_KEY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f8503xda7b7094[State.PAYLOAD.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f8503xda7b7094[State.CORRUPT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
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

    private void protocolViolation(ChannelHandlerContext channelHandlerContext, String str) {
        protocolViolation(channelHandlerContext, new CorruptedFrameException(str));
    }

    private void protocolViolation(ChannelHandlerContext channelHandlerContext, CorruptedFrameException corruptedFrameException) {
        Object closeWebSocketFrame;
        this.state = State.CORRUPT;
        if (channelHandlerContext.channel().isActive()) {
            if (this.receivedClosingHandshake) {
                closeWebSocketFrame = Unpooled.EMPTY_BUFFER;
            } else {
                closeWebSocketFrame = new CloseWebSocketFrame(1002, (String) null);
            }
            channelHandlerContext.writeAndFlush(closeWebSocketFrame).addListener((GenericFutureListener<? extends Future<? super Void>>) ChannelFutureListener.CLOSE);
            throw corruptedFrameException;
        }
        throw corruptedFrameException;
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
            protocolViolation(channelHandlerContext, "Invalid close frame body");
        }
        int readerIndex = byteBuf.readerIndex();
        byteBuf.readerIndex(0);
        short readShort = byteBuf.readShort();
        if ((readShort >= 0 && readShort <= 999) || ((readShort >= 1004 && readShort <= 1006) || (readShort >= 1012 && readShort <= 2999))) {
            protocolViolation(channelHandlerContext, "Invalid close frame getStatus code: " + ((int) readShort));
        }
        if (byteBuf.isReadable()) {
            try {
                new Utf8Validator().check(byteBuf);
            } catch (CorruptedFrameException e) {
                protocolViolation(channelHandlerContext, e);
            }
        }
        byteBuf.readerIndex(readerIndex);
    }
}
