package org.jboss.netty.handler.codec.spdy;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import org.jboss.netty.handler.codec.frame.TooLongFrameException;

/* loaded from: classes7.dex */
public class SpdyFrameDecoder extends FrameDecoder {
    private ChannelBuffer decompressed;
    private byte flags;
    private final SpdyHeaderBlockDecompressor headerBlockDecompressor;
    private int headerSize;
    private int length;
    private final int maxChunkSize;
    private final int maxHeaderSize;
    private int numHeaders;
    private SpdyHeaderBlock spdyHeaderBlock;
    private SpdySettingsFrame spdySettingsFrame;
    private final int spdyVersion;
    private State state;
    private int streamID;
    private int type;
    private int version;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public enum State {
        READ_COMMON_HEADER,
        READ_CONTROL_FRAME,
        READ_SETTINGS_FRAME,
        READ_HEADER_BLOCK_FRAME,
        READ_HEADER_BLOCK,
        READ_DATA_FRAME,
        DISCARD_FRAME,
        FRAME_ERROR
    }

    @Deprecated
    public SpdyFrameDecoder() {
        this(2);
    }

    public SpdyFrameDecoder(int i) {
        this(i, 8192, 16384);
    }

    public SpdyFrameDecoder(int i, int i2, int i3) {
        super(false);
        if (i < 2 || i > 3) {
            throw new IllegalArgumentException("unsupported version: " + i);
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("maxChunkSize must be a positive integer: " + i2);
        }
        if (i3 <= 0) {
            throw new IllegalArgumentException("maxHeaderSize must be a positive integer: " + i3);
        }
        this.spdyVersion = i;
        this.maxChunkSize = i2;
        this.maxHeaderSize = i3;
        this.headerBlockDecompressor = SpdyHeaderBlockDecompressor.newInstance(i);
        this.state = State.READ_COMMON_HEADER;
    }

    @Override // org.jboss.netty.handler.codec.frame.FrameDecoder
    protected Object decodeLast(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer) throws Exception {
        try {
            return decode(channelHandlerContext, channel, channelBuffer);
        } finally {
            this.headerBlockDecompressor.end();
        }
    }

    @Override // org.jboss.netty.handler.codec.frame.FrameDecoder
    protected Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer) throws Exception {
        byte readByte;
        int unsignedMedium;
        switch (this.state) {
            case READ_COMMON_HEADER:
                this.state = readCommonHeader(channelBuffer);
                if (this.state == State.FRAME_ERROR) {
                    if (this.version != this.spdyVersion) {
                        fireProtocolException(channelHandlerContext, "Unsupported version: " + this.version);
                    } else {
                        fireInvalidControlFrameException(channelHandlerContext);
                    }
                }
                if (this.length == 0) {
                    if (this.state == State.READ_DATA_FRAME) {
                        int i = this.streamID;
                        if (i == 0) {
                            this.state = State.FRAME_ERROR;
                            fireProtocolException(channelHandlerContext, "Received invalid data frame");
                            return null;
                        }
                        DefaultSpdyDataFrame defaultSpdyDataFrame = new DefaultSpdyDataFrame(i);
                        defaultSpdyDataFrame.setLast((this.flags & 1) != 0);
                        this.state = State.READ_COMMON_HEADER;
                        return defaultSpdyDataFrame;
                    }
                    this.state = State.READ_COMMON_HEADER;
                }
                return null;
            case READ_CONTROL_FRAME:
                try {
                    Object readControlFrame = readControlFrame(channelBuffer);
                    if (readControlFrame != null) {
                        this.state = State.READ_COMMON_HEADER;
                    }
                    return readControlFrame;
                } catch (IllegalArgumentException unused) {
                    this.state = State.FRAME_ERROR;
                    fireInvalidControlFrameException(channelHandlerContext);
                    return null;
                }
            case READ_SETTINGS_FRAME:
                if (this.spdySettingsFrame == null) {
                    if (channelBuffer.readableBytes() < 4) {
                        return null;
                    }
                    int unsignedInt = SpdyCodecUtil.getUnsignedInt(channelBuffer, channelBuffer.readerIndex());
                    channelBuffer.skipBytes(4);
                    this.length -= 4;
                    int i2 = this.length;
                    if ((i2 & 7) != 0 || (i2 >> 3) != unsignedInt) {
                        this.state = State.FRAME_ERROR;
                        fireInvalidControlFrameException(channelHandlerContext);
                        return null;
                    }
                    this.spdySettingsFrame = new DefaultSpdySettingsFrame();
                    this.spdySettingsFrame.setClearPreviouslyPersistedSettings((this.flags & 1) != 0);
                }
                int min = Math.min(channelBuffer.readableBytes() >> 3, this.length >> 3);
                for (int i3 = 0; i3 < min; i3++) {
                    if (this.version < 3) {
                        unsignedMedium = (channelBuffer.readByte() & 255) | ((channelBuffer.readByte() & 255) << 8) | ((channelBuffer.readByte() & 255) << 16);
                        readByte = channelBuffer.readByte();
                    } else {
                        readByte = channelBuffer.readByte();
                        unsignedMedium = SpdyCodecUtil.getUnsignedMedium(channelBuffer, channelBuffer.readerIndex());
                        channelBuffer.skipBytes(3);
                    }
                    int signedInt = SpdyCodecUtil.getSignedInt(channelBuffer, channelBuffer.readerIndex());
                    channelBuffer.skipBytes(4);
                    if (unsignedMedium == 0) {
                        this.state = State.FRAME_ERROR;
                        this.spdySettingsFrame = null;
                        fireInvalidControlFrameException(channelHandlerContext);
                        return null;
                    }
                    if (!this.spdySettingsFrame.isSet(unsignedMedium)) {
                        this.spdySettingsFrame.setValue(unsignedMedium, signedInt, (readByte & 1) != 0, (readByte & 2) != 0);
                    }
                }
                this.length -= min * 8;
                if (this.length != 0) {
                    return null;
                }
                this.state = State.READ_COMMON_HEADER;
                SpdySettingsFrame spdySettingsFrame = this.spdySettingsFrame;
                this.spdySettingsFrame = null;
                return spdySettingsFrame;
            case READ_HEADER_BLOCK_FRAME:
                try {
                    this.spdyHeaderBlock = readHeaderBlockFrame(channelBuffer);
                    if (this.spdyHeaderBlock != null) {
                        if (this.length == 0) {
                            this.state = State.READ_COMMON_HEADER;
                            SpdyHeaderBlock spdyHeaderBlock = this.spdyHeaderBlock;
                            this.spdyHeaderBlock = null;
                            return spdyHeaderBlock;
                        }
                        this.state = State.READ_HEADER_BLOCK;
                    }
                    return null;
                } catch (IllegalArgumentException unused2) {
                    this.state = State.FRAME_ERROR;
                    fireInvalidControlFrameException(channelHandlerContext);
                    return null;
                }
            case READ_HEADER_BLOCK:
                int min2 = Math.min(channelBuffer.readableBytes(), this.length);
                this.length -= min2;
                try {
                    decodeHeaderBlock(channelBuffer.readSlice(min2));
                    SpdyHeaderBlock spdyHeaderBlock2 = this.spdyHeaderBlock;
                    if (spdyHeaderBlock2 != null && spdyHeaderBlock2.isInvalid()) {
                        SpdyHeaderBlock spdyHeaderBlock3 = this.spdyHeaderBlock;
                        this.spdyHeaderBlock = null;
                        this.decompressed = null;
                        if (this.length == 0) {
                            this.state = State.READ_COMMON_HEADER;
                        }
                        return spdyHeaderBlock3;
                    }
                    if (this.length != 0) {
                        return null;
                    }
                    SpdyHeaderBlock spdyHeaderBlock4 = this.spdyHeaderBlock;
                    this.spdyHeaderBlock = null;
                    this.state = State.READ_COMMON_HEADER;
                    return spdyHeaderBlock4;
                } catch (Exception e) {
                    this.state = State.FRAME_ERROR;
                    this.spdyHeaderBlock = null;
                    this.decompressed = null;
                    Channels.fireExceptionCaught(channelHandlerContext, e);
                    return null;
                }
            case READ_DATA_FRAME:
                if (this.streamID == 0) {
                    this.state = State.FRAME_ERROR;
                    fireProtocolException(channelHandlerContext, "Received invalid data frame");
                    return null;
                }
                int min3 = Math.min(this.maxChunkSize, this.length);
                if (channelBuffer.readableBytes() < min3) {
                    return null;
                }
                DefaultSpdyDataFrame defaultSpdyDataFrame2 = new DefaultSpdyDataFrame(this.streamID);
                defaultSpdyDataFrame2.setData(channelBuffer.readBytes(min3));
                this.length -= min3;
                if (this.length == 0) {
                    defaultSpdyDataFrame2.setLast((this.flags & 1) != 0);
                    this.state = State.READ_COMMON_HEADER;
                }
                return defaultSpdyDataFrame2;
            case DISCARD_FRAME:
                int min4 = Math.min(channelBuffer.readableBytes(), this.length);
                channelBuffer.skipBytes(min4);
                this.length -= min4;
                if (this.length == 0) {
                    this.state = State.READ_COMMON_HEADER;
                }
                return null;
            case FRAME_ERROR:
                channelBuffer.skipBytes(channelBuffer.readableBytes());
                return null;
            default:
                throw new Error("Shouldn't reach here.");
        }
    }

    private State readCommonHeader(ChannelBuffer channelBuffer) {
        if (channelBuffer.readableBytes() < 8) {
            return State.READ_COMMON_HEADER;
        }
        int readerIndex = channelBuffer.readerIndex();
        int i = readerIndex + 4;
        int i2 = readerIndex + 5;
        channelBuffer.skipBytes(8);
        boolean z = (channelBuffer.getByte(readerIndex) & 128) != 0;
        this.flags = channelBuffer.getByte(i);
        this.length = SpdyCodecUtil.getUnsignedMedium(channelBuffer, i2);
        if (z) {
            this.version = SpdyCodecUtil.getUnsignedShort(channelBuffer, readerIndex) & 32767;
            this.type = SpdyCodecUtil.getUnsignedShort(channelBuffer, readerIndex + 2);
            if (this.version != this.spdyVersion || !isValidControlFrameHeader()) {
                return State.FRAME_ERROR;
            }
            if (willGenerateControlFrame()) {
                int i3 = this.type;
                if (i3 != 1 && i3 != 2) {
                    if (i3 == 4) {
                        return State.READ_SETTINGS_FRAME;
                    }
                    if (i3 != 8) {
                        return State.READ_CONTROL_FRAME;
                    }
                }
                return State.READ_HEADER_BLOCK_FRAME;
            }
            if (this.length != 0) {
                return State.DISCARD_FRAME;
            }
            return State.READ_COMMON_HEADER;
        }
        this.streamID = SpdyCodecUtil.getUnsignedInt(channelBuffer, readerIndex);
        return State.READ_DATA_FRAME;
    }

    private Object readControlFrame(ChannelBuffer channelBuffer) {
        int i = this.type;
        if (i == 3) {
            if (channelBuffer.readableBytes() < 8) {
                return null;
            }
            int unsignedInt = SpdyCodecUtil.getUnsignedInt(channelBuffer, channelBuffer.readerIndex());
            int signedInt = SpdyCodecUtil.getSignedInt(channelBuffer, channelBuffer.readerIndex() + 4);
            channelBuffer.skipBytes(8);
            return new DefaultSpdyRstStreamFrame(unsignedInt, signedInt);
        }
        if (i == 9) {
            if (channelBuffer.readableBytes() < 8) {
                return null;
            }
            int unsignedInt2 = SpdyCodecUtil.getUnsignedInt(channelBuffer, channelBuffer.readerIndex());
            int unsignedInt3 = SpdyCodecUtil.getUnsignedInt(channelBuffer, channelBuffer.readerIndex() + 4);
            channelBuffer.skipBytes(8);
            return new DefaultSpdyWindowUpdateFrame(unsignedInt2, unsignedInt3);
        }
        if (i == 6) {
            if (channelBuffer.readableBytes() < 4) {
                return null;
            }
            int signedInt2 = SpdyCodecUtil.getSignedInt(channelBuffer, channelBuffer.readerIndex());
            channelBuffer.skipBytes(4);
            return new DefaultSpdyPingFrame(signedInt2);
        }
        if (i == 7) {
            if (channelBuffer.readableBytes() < (this.version < 3 ? 4 : 8)) {
                return null;
            }
            int unsignedInt4 = SpdyCodecUtil.getUnsignedInt(channelBuffer, channelBuffer.readerIndex());
            channelBuffer.skipBytes(4);
            if (this.version < 3) {
                return new DefaultSpdyGoAwayFrame(unsignedInt4);
            }
            int signedInt3 = SpdyCodecUtil.getSignedInt(channelBuffer, channelBuffer.readerIndex());
            channelBuffer.skipBytes(4);
            return new DefaultSpdyGoAwayFrame(unsignedInt4, signedInt3);
        }
        throw new Error("Shouldn't reach here.");
    }

    private SpdyHeaderBlock readHeaderBlockFrame(ChannelBuffer channelBuffer) {
        int i = this.type;
        if (i == 1) {
            if (channelBuffer.readableBytes() < (this.version < 3 ? 12 : 10)) {
                return null;
            }
            int readerIndex = channelBuffer.readerIndex();
            int unsignedInt = SpdyCodecUtil.getUnsignedInt(channelBuffer, readerIndex);
            int unsignedInt2 = SpdyCodecUtil.getUnsignedInt(channelBuffer, readerIndex + 4);
            byte b = (byte) ((channelBuffer.getByte(readerIndex + 8) >> 5) & 7);
            if (this.version < 3) {
                b = (byte) (b >> 1);
            }
            channelBuffer.skipBytes(10);
            this.length -= 10;
            if (this.version < 3 && this.length == 2 && channelBuffer.getShort(channelBuffer.readerIndex()) == 0) {
                channelBuffer.skipBytes(2);
                this.length = 0;
            }
            DefaultSpdySynStreamFrame defaultSpdySynStreamFrame = new DefaultSpdySynStreamFrame(unsignedInt, unsignedInt2, b);
            defaultSpdySynStreamFrame.setLast((this.flags & 1) != 0);
            defaultSpdySynStreamFrame.setUnidirectional((this.flags & 2) != 0);
            return defaultSpdySynStreamFrame;
        }
        if (i == 2) {
            if (channelBuffer.readableBytes() < (this.version >= 3 ? 4 : 8)) {
                return null;
            }
            int unsignedInt3 = SpdyCodecUtil.getUnsignedInt(channelBuffer, channelBuffer.readerIndex());
            channelBuffer.skipBytes(4);
            this.length -= 4;
            if (this.version < 3) {
                channelBuffer.skipBytes(2);
                this.length -= 2;
            }
            if (this.version < 3 && this.length == 2 && channelBuffer.getShort(channelBuffer.readerIndex()) == 0) {
                channelBuffer.skipBytes(2);
                this.length = 0;
            }
            DefaultSpdySynReplyFrame defaultSpdySynReplyFrame = new DefaultSpdySynReplyFrame(unsignedInt3);
            defaultSpdySynReplyFrame.setLast((this.flags & 1) != 0);
            return defaultSpdySynReplyFrame;
        }
        if (i == 8) {
            if (channelBuffer.readableBytes() < 4) {
                return null;
            }
            if (this.version < 3 && this.length > 4 && channelBuffer.readableBytes() < 8) {
                return null;
            }
            int unsignedInt4 = SpdyCodecUtil.getUnsignedInt(channelBuffer, channelBuffer.readerIndex());
            channelBuffer.skipBytes(4);
            this.length -= 4;
            if (this.version < 3 && this.length != 0) {
                channelBuffer.skipBytes(2);
                this.length -= 2;
            }
            if (this.version < 3 && this.length == 2 && channelBuffer.getShort(channelBuffer.readerIndex()) == 0) {
                channelBuffer.skipBytes(2);
                this.length = 0;
            }
            DefaultSpdyHeadersFrame defaultSpdyHeadersFrame = new DefaultSpdyHeadersFrame(unsignedInt4);
            defaultSpdyHeadersFrame.setLast((this.flags & 1) != 0);
            return defaultSpdyHeadersFrame;
        }
        throw new Error("Shouldn't reach here.");
    }

    private boolean ensureBytes(int i) throws Exception {
        if (this.decompressed.readableBytes() >= i) {
            return true;
        }
        this.headerBlockDecompressor.decode(this.decompressed);
        return this.decompressed.readableBytes() >= i;
    }

    private int readLengthField() {
        if (this.version < 3) {
            return this.decompressed.readUnsignedShort();
        }
        return this.decompressed.readInt();
    }

    private void decodeHeaderBlock(ChannelBuffer channelBuffer) throws Exception {
        if (this.decompressed == null) {
            this.headerSize = 0;
            this.numHeaders = -1;
            this.decompressed = ChannelBuffers.dynamicBuffer(8192);
        }
        this.headerBlockDecompressor.setInput(channelBuffer);
        this.headerBlockDecompressor.decode(this.decompressed);
        if (this.spdyHeaderBlock == null) {
            this.decompressed = null;
            return;
        }
        int i = this.version < 3 ? 2 : 4;
        if (this.numHeaders == -1) {
            if (this.decompressed.readableBytes() < i) {
                return;
            }
            this.numHeaders = readLengthField();
            if (this.numHeaders < 0) {
                this.spdyHeaderBlock.setInvalid();
                return;
            }
        }
        while (this.numHeaders > 0) {
            int i2 = this.headerSize;
            this.decompressed.markReaderIndex();
            if (!ensureBytes(i)) {
                this.decompressed.resetReaderIndex();
                this.decompressed.discardReadBytes();
                return;
            }
            int readLengthField = readLengthField();
            if (readLengthField <= 0) {
                this.spdyHeaderBlock.setInvalid();
                return;
            }
            int i3 = i2 + readLengthField;
            if (i3 > this.maxHeaderSize) {
                throw new TooLongFrameException("Header block exceeds " + this.maxHeaderSize);
            }
            if (!ensureBytes(readLengthField)) {
                this.decompressed.resetReaderIndex();
                this.decompressed.discardReadBytes();
                return;
            }
            byte[] bArr = new byte[readLengthField];
            this.decompressed.readBytes(bArr);
            String str = new String(bArr, "UTF-8");
            if (this.spdyHeaderBlock.containsHeader(str)) {
                this.spdyHeaderBlock.setInvalid();
                return;
            }
            if (!ensureBytes(i)) {
                this.decompressed.resetReaderIndex();
                this.decompressed.discardReadBytes();
                return;
            }
            int readLengthField2 = readLengthField();
            if (readLengthField2 <= 0) {
                this.spdyHeaderBlock.setInvalid();
                return;
            }
            int i4 = i3 + readLengthField2;
            if (i4 > this.maxHeaderSize) {
                throw new TooLongFrameException("Header block exceeds " + this.maxHeaderSize);
            }
            if (!ensureBytes(readLengthField2)) {
                this.decompressed.resetReaderIndex();
                this.decompressed.discardReadBytes();
                return;
            }
            byte[] bArr2 = new byte[readLengthField2];
            this.decompressed.readBytes(bArr2);
            int i5 = 0;
            int i6 = 0;
            while (i5 < readLengthField2) {
                while (i5 < bArr2.length && bArr2[i5] != 0) {
                    i5++;
                }
                if (i5 < bArr2.length && bArr2[i5 + 1] == 0) {
                    this.spdyHeaderBlock.setInvalid();
                    return;
                }
                try {
                    this.spdyHeaderBlock.addHeader(str, new String(bArr2, i6, i5 - i6, "UTF-8"));
                    i6 = i5 + 1;
                    i5 = i6;
                } catch (IllegalArgumentException unused) {
                    this.spdyHeaderBlock.setInvalid();
                    return;
                }
            }
            this.numHeaders--;
            this.headerSize = i4;
        }
        this.decompressed = null;
    }

    private boolean isValidControlFrameHeader() {
        switch (this.type) {
            case 1:
                if (this.version < 3) {
                    if (this.length < 12) {
                        return false;
                    }
                } else if (this.length < 10) {
                    return false;
                }
                return true;
            case 2:
                if (this.version < 3) {
                    if (this.length < 8) {
                        return false;
                    }
                } else if (this.length < 4) {
                    return false;
                }
                return true;
            case 3:
                return this.flags == 0 && this.length == 8;
            case 4:
                return this.length >= 4;
            case 5:
                return this.length == 0;
            case 6:
                return this.length == 4;
            case 7:
                if (this.version < 3) {
                    if (this.length != 4) {
                        return false;
                    }
                } else if (this.length != 8) {
                    return false;
                }
                return true;
            case 8:
                if (this.version >= 3) {
                    return this.length >= 4;
                }
                int i = this.length;
                return i == 4 || i >= 8;
            case 9:
                return this.length == 8;
            default:
                return true;
        }
    }

    private boolean willGenerateControlFrame() {
        switch (this.type) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 6:
            case 7:
            case 8:
            case 9:
                return true;
            case 5:
            default:
                return false;
        }
    }

    private void fireInvalidControlFrameException(ChannelHandlerContext channelHandlerContext) {
        String str;
        switch (this.type) {
            case 1:
                str = "Received invalid SYN_STREAM control frame";
                break;
            case 2:
                str = "Received invalid SYN_REPLY control frame";
                break;
            case 3:
                str = "Received invalid RST_STREAM control frame";
                break;
            case 4:
                str = "Received invalid SETTINGS control frame";
                break;
            case 5:
                str = "Received invalid NOOP control frame";
                break;
            case 6:
                str = "Received invalid PING control frame";
                break;
            case 7:
                str = "Received invalid GOAWAY control frame";
                break;
            case 8:
                str = "Received invalid HEADERS control frame";
                break;
            case 9:
                str = "Received invalid WINDOW_UPDATE control frame";
                break;
            case 10:
                str = "Received invalid CREDENTIAL control frame";
                break;
            default:
                str = "Received invalid control frame";
                break;
        }
        fireProtocolException(channelHandlerContext, str);
    }

    private static void fireProtocolException(ChannelHandlerContext channelHandlerContext, String str) {
        Channels.fireExceptionCaught(channelHandlerContext, new SpdyProtocolException(str));
    }
}
