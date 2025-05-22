package org.jboss.netty.handler.codec.http.websocketx;

import android.support.v4.media.session.PlaybackStateCompat;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.CorruptedFrameException;
import org.jboss.netty.handler.codec.frame.TooLongFrameException;
import org.jboss.netty.handler.codec.replay.ReplayingDecoder;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;

/* loaded from: classes7.dex */
public class WebSocket08FrameDecoder extends ReplayingDecoder<State> {
    private static final byte OPCODE_BINARY = 2;
    private static final byte OPCODE_CLOSE = 8;
    private static final byte OPCODE_CONT = 0;
    private static final byte OPCODE_PING = 9;
    private static final byte OPCODE_PONG = 10;
    private static final byte OPCODE_TEXT = 1;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) WebSocket08FrameDecoder.class);
    private final boolean allowExtensions;
    private int fragmentedFramesCount;
    private UTF8Output fragmentedFramesText;
    private boolean frameFinalFlag;
    private int frameOpcode;
    private ChannelBuffer framePayload;
    private int framePayloadBytesRead;
    private long framePayloadLength;
    private int frameRsv;
    private final boolean maskedPayload;
    private ChannelBuffer maskingKey;
    private final long maxFramePayloadLength;
    private boolean receivedClosingHandshake;

    /* loaded from: classes7.dex */
    public enum State {
        FRAME_START,
        MASKING_KEY,
        PAYLOAD,
        CORRUPT
    }

    public WebSocket08FrameDecoder(boolean z, boolean z2) {
        this(z, z2, Long.MAX_VALUE);
    }

    public WebSocket08FrameDecoder(boolean z, boolean z2, long j) {
        super(State.FRAME_START);
        this.maskedPayload = z;
        this.allowExtensions = z2;
        this.maxFramePayloadLength = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:18:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x02a6  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01cb  */
    @Override // org.jboss.netty.handler.codec.replay.ReplayingDecoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer, State state) throws Exception {
        int i;
        long j;
        long j2;
        ChannelBuffer readBytes;
        ChannelBuffer channelBuffer2;
        int i2;
        String str;
        int i3;
        if (this.receivedClosingHandshake) {
            channelBuffer.skipBytes(actualReadableBytes());
            return null;
        }
        int i4 = C87121.f10036x43ecfa85[state.ordinal()];
        if (i4 == 1) {
            this.framePayloadBytesRead = 0;
            this.framePayloadLength = -1L;
            this.framePayload = null;
            byte readByte = channelBuffer.readByte();
            this.frameFinalFlag = (readByte & 128) != 0;
            this.frameRsv = (readByte & 112) >> 4;
            this.frameOpcode = readByte & 15;
            if (logger.isDebugEnabled()) {
                logger.debug("Decoding WebSocket Frame opCode=" + this.frameOpcode);
            }
            byte readByte2 = channelBuffer.readByte();
            boolean z = (readByte2 & 128) != 0;
            int i5 = readByte2 & Byte.MAX_VALUE;
            if (this.frameRsv != 0 && !this.allowExtensions) {
                protocolViolation(channel, "RSV != 0 and no extension negotiated, RSV:" + this.frameRsv);
                return null;
            }
            if (this.maskedPayload && !z) {
                protocolViolation(channel, "unmasked client to server frame");
                return null;
            }
            int i6 = this.frameOpcode;
            if (i6 > 7) {
                if (!this.frameFinalFlag) {
                    protocolViolation(channel, "fragmented control frame");
                    return null;
                }
                if (i5 > 125) {
                    protocolViolation(channel, "control frame with payload length > 125 octets");
                    return null;
                }
                if (i6 != 8 && i6 != 9 && i6 != 10) {
                    protocolViolation(channel, "control frame using reserved opcode " + this.frameOpcode);
                    return null;
                }
                if (this.frameOpcode == 8 && i5 == 1) {
                    protocolViolation(channel, "received close control frame with payload len 1");
                    return null;
                }
            } else {
                if (i6 != 0 && i6 != 1 && i6 != 2) {
                    protocolViolation(channel, "data frame using reserved opcode " + this.frameOpcode);
                    return null;
                }
                if (this.fragmentedFramesCount == 0 && this.frameOpcode == 0) {
                    protocolViolation(channel, "received continuation data frame outside fragmented message");
                    return null;
                }
                if (this.fragmentedFramesCount != 0 && (i = this.frameOpcode) != 0 && i != 9) {
                    protocolViolation(channel, "received non-continuation data frame while inside fragmented message");
                    return null;
                }
            }
            if (i5 == 126) {
                this.framePayloadLength = channelBuffer.readUnsignedShort();
                if (this.framePayloadLength < 126) {
                    protocolViolation(channel, "invalid data frame length (not using minimal length encoding)");
                    return null;
                }
            } else if (i5 == 127) {
                this.framePayloadLength = channelBuffer.readLong();
                if (this.framePayloadLength < PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                    protocolViolation(channel, "invalid data frame length (not using minimal length encoding)");
                    return null;
                }
            } else {
                this.framePayloadLength = i5;
            }
            if (this.framePayloadLength > this.maxFramePayloadLength) {
                protocolViolation(channel, "Max frame length of " + this.maxFramePayloadLength + " has been exceeded.");
                return null;
            }
            if (logger.isDebugEnabled()) {
                logger.debug("Decoding WebSocket Frame length=" + this.framePayloadLength);
            }
            checkpoint(State.MASKING_KEY);
        } else if (i4 != 2) {
            if (i4 != 3) {
                if (i4 == 4) {
                    channelBuffer.readByte();
                    return null;
                }
                throw new Error("Shouldn't reach here.");
            }
            int actualReadableBytes = actualReadableBytes();
            int i7 = this.framePayloadBytesRead;
            j = i7 + actualReadableBytes;
            j2 = this.framePayloadLength;
            if (j != j2) {
                readBytes = channelBuffer.readBytes(actualReadableBytes);
            } else {
                if (j < j2) {
                    ChannelBuffer readBytes2 = channelBuffer.readBytes(actualReadableBytes);
                    if (this.framePayload == null) {
                        this.framePayload = channel.getConfig().getBufferFactory().getBuffer(toFrameLength(this.framePayloadLength));
                    }
                    this.framePayload.writeBytes(readBytes2);
                    this.framePayloadBytesRead += actualReadableBytes;
                    return null;
                }
                readBytes = j > j2 ? channelBuffer.readBytes(toFrameLength(j2 - i7)) : null;
            }
            checkpoint(State.FRAME_START);
            channelBuffer2 = this.framePayload;
            if (channelBuffer2 != null) {
                this.framePayload = readBytes;
            } else {
                channelBuffer2.writeBytes(readBytes);
            }
            if (this.maskedPayload) {
                unmask(this.framePayload);
            }
            i2 = this.frameOpcode;
            if (i2 != 9) {
                return new PingWebSocketFrame(this.frameFinalFlag, this.frameRsv, this.framePayload);
            }
            if (i2 == 10) {
                return new PongWebSocketFrame(this.frameFinalFlag, this.frameRsv, this.framePayload);
            }
            if (i2 == 8) {
                checkCloseFrameBody(channel, this.framePayload);
                this.receivedClosingHandshake = true;
                return new CloseWebSocketFrame(this.frameFinalFlag, this.frameRsv, this.framePayload);
            }
            if (!this.frameFinalFlag) {
                if (this.fragmentedFramesCount == 0) {
                    this.fragmentedFramesText = null;
                    if (i2 == 1) {
                        checkUTF8String(channel, this.framePayload.array());
                    }
                } else if (this.fragmentedFramesText != null) {
                    checkUTF8String(channel, this.framePayload.array());
                }
                this.fragmentedFramesCount++;
            } else if (i2 != 9) {
                this.fragmentedFramesCount = 0;
                if (i2 == 1 || this.fragmentedFramesText != null) {
                    checkUTF8String(channel, this.framePayload.array());
                    str = this.fragmentedFramesText.toString();
                    this.fragmentedFramesText = null;
                    i3 = this.frameOpcode;
                    if (i3 != 1) {
                        return new TextWebSocketFrame(this.frameFinalFlag, this.frameRsv, this.framePayload);
                    }
                    if (i3 == 2) {
                        return new BinaryWebSocketFrame(this.frameFinalFlag, this.frameRsv, this.framePayload);
                    }
                    if (i3 == 0) {
                        return new ContinuationWebSocketFrame(this.frameFinalFlag, this.frameRsv, this.framePayload, str);
                    }
                    throw new UnsupportedOperationException("Cannot decode web socket frame with opcode: " + this.frameOpcode);
                }
            }
            str = null;
            i3 = this.frameOpcode;
            if (i3 != 1) {
            }
        }
        if (this.maskedPayload) {
            this.maskingKey = channelBuffer.readBytes(4);
        }
        checkpoint(State.PAYLOAD);
        int actualReadableBytes2 = actualReadableBytes();
        int i72 = this.framePayloadBytesRead;
        j = i72 + actualReadableBytes2;
        j2 = this.framePayloadLength;
        if (j != j2) {
        }
        checkpoint(State.FRAME_START);
        channelBuffer2 = this.framePayload;
        if (channelBuffer2 != null) {
        }
        if (this.maskedPayload) {
        }
        i2 = this.frameOpcode;
        if (i2 != 9) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.jboss.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder$1 */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C87121 {

        /* renamed from: $SwitchMap$org$jboss$netty$handler$codec$http$websocketx$WebSocket08FrameDecoder$State */
        static final /* synthetic */ int[] f10036x43ecfa85 = new int[State.values().length];

        static {
            try {
                f10036x43ecfa85[State.FRAME_START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10036x43ecfa85[State.MASKING_KEY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10036x43ecfa85[State.PAYLOAD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10036x43ecfa85[State.CORRUPT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void unmask(ChannelBuffer channelBuffer) {
        byte[] array = channelBuffer.array();
        for (int i = 0; i < array.length; i++) {
            channelBuffer.setByte(i, channelBuffer.getByte(i) ^ this.maskingKey.getByte(i % 4));
        }
    }

    private void protocolViolation(Channel channel, String str) throws CorruptedFrameException {
        checkpoint(State.CORRUPT);
        if (channel.isConnected()) {
            channel.write(ChannelBuffers.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
            channel.close().awaitUninterruptibly();
        }
        throw new CorruptedFrameException(str);
    }

    private static int toFrameLength(long j) throws TooLongFrameException {
        if (j <= 2147483647L) {
            return (int) j;
        }
        throw new TooLongFrameException("Length:" + j);
    }

    private void checkUTF8String(Channel channel, byte[] bArr) throws CorruptedFrameException {
        try {
            if (this.fragmentedFramesText == null) {
                this.fragmentedFramesText = new UTF8Output(bArr);
            } else {
                this.fragmentedFramesText.write(bArr);
            }
        } catch (UTF8Exception unused) {
            protocolViolation(channel, "invalid UTF-8 bytes");
        }
    }

    protected void checkCloseFrameBody(Channel channel, ChannelBuffer channelBuffer) throws CorruptedFrameException {
        if (channelBuffer == null || channelBuffer.capacity() == 0) {
            return;
        }
        if (channelBuffer.capacity() == 1) {
            protocolViolation(channel, "Invalid close frame body");
        }
        int readerIndex = channelBuffer.readerIndex();
        channelBuffer.readerIndex(0);
        short readShort = channelBuffer.readShort();
        if ((readShort >= 0 && readShort <= 999) || ((readShort >= 1004 && readShort <= 1006) || (readShort >= 1012 && readShort <= 2999))) {
            protocolViolation(channel, "Invalid close frame status code: " + ((int) readShort));
        }
        if (channelBuffer.readableBytes() > 0) {
            byte[] bArr = new byte[channelBuffer.readableBytes()];
            channelBuffer.readBytes(bArr);
            try {
                new UTF8Output(bArr);
            } catch (UTF8Exception unused) {
                protocolViolation(channel, "Invalid close frame reason text. Invalid UTF-8 bytes");
            }
        }
        channelBuffer.readerIndex(readerIndex);
    }
}
