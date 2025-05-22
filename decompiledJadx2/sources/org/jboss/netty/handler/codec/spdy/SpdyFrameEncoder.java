package org.jboss.netty.handler.codec.spdy;

import java.nio.ByteOrder;
import java.util.Iterator;
import java.util.Set;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelState;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/* loaded from: classes7.dex */
public class SpdyFrameEncoder extends OneToOneEncoder {
    private volatile boolean finished;
    private final SpdyHeaderBlockCompressor headerBlockCompressor;
    private final int version;

    @Deprecated
    public SpdyFrameEncoder() {
        this(2, 6, 15, 8);
    }

    public SpdyFrameEncoder(int i) {
        this(i, 6, 15, 8);
    }

    @Deprecated
    public SpdyFrameEncoder(int i, int i2, int i3) {
        this(2, i, i2, i3);
    }

    public SpdyFrameEncoder(int i, int i2, int i3, int i4) {
        if (i < 2 || i > 3) {
            throw new IllegalArgumentException("unknown version: " + i);
        }
        this.version = i;
        this.headerBlockCompressor = SpdyHeaderBlockCompressor.newInstance(i, i2, i3, i4);
    }

    @Override // org.jboss.netty.handler.codec.oneone.OneToOneEncoder, org.jboss.netty.channel.ChannelDownstreamHandler
    public void handleDownstream(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent) throws Exception {
        if (channelEvent instanceof ChannelStateEvent) {
            ChannelStateEvent channelStateEvent = (ChannelStateEvent) channelEvent;
            int i = C87161.$SwitchMap$org$jboss$netty$channel$ChannelState[channelStateEvent.getState().ordinal()];
            if ((i == 1 || i == 2 || i == 3) && (Boolean.FALSE.equals(channelStateEvent.getValue()) || channelStateEvent.getValue() == null)) {
                synchronized (this.headerBlockCompressor) {
                    this.finished = true;
                    this.headerBlockCompressor.end();
                }
            }
        }
        super.handleDownstream(channelHandlerContext, channelEvent);
    }

    /* renamed from: org.jboss.netty.handler.codec.spdy.SpdyFrameEncoder$1 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C87161 {
        static final /* synthetic */ int[] $SwitchMap$org$jboss$netty$channel$ChannelState = new int[ChannelState.values().length];

        static {
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.CONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.BOUND.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jboss.netty.handler.codec.oneone.OneToOneEncoder
    protected Object encode(ChannelHandlerContext channelHandlerContext, Channel channel, Object obj) throws Exception {
        if (obj instanceof SpdyDataFrame) {
            SpdyDataFrame spdyDataFrame = (SpdyDataFrame) obj;
            ChannelBuffer data = spdyDataFrame.getData();
            boolean isLast = spdyDataFrame.isLast();
            ChannelBuffer buffer = ChannelBuffers.buffer(ByteOrder.BIG_ENDIAN, 8);
            buffer.writeInt(spdyDataFrame.getStreamId() & Integer.MAX_VALUE);
            buffer.writeByte(isLast ? 1 : 0);
            buffer.writeMedium(data.readableBytes());
            return ChannelBuffers.wrappedBuffer(buffer, data);
        }
        if (obj instanceof SpdySynStreamFrame) {
            SpdySynStreamFrame spdySynStreamFrame = (SpdySynStreamFrame) obj;
            ChannelBuffer compressHeaderBlock = compressHeaderBlock(encodeHeaderBlock(this.version, spdySynStreamFrame));
            boolean isLast2 = spdySynStreamFrame.isLast();
            byte b = isLast2;
            if (spdySynStreamFrame.isUnidirectional()) {
                b = (byte) (isLast2 | 2);
            }
            int readableBytes = compressHeaderBlock.readableBytes();
            int i = (this.version >= 3 || readableBytes != 0) ? readableBytes + 10 : 12;
            ChannelBuffer buffer2 = ChannelBuffers.buffer(ByteOrder.BIG_ENDIAN, i + 8);
            buffer2.writeShort(32768 | this.version);
            buffer2.writeShort(1);
            buffer2.writeByte(b);
            buffer2.writeMedium(i);
            buffer2.writeInt(spdySynStreamFrame.getStreamId());
            buffer2.writeInt(spdySynStreamFrame.getAssociatedToStreamId());
            if (this.version < 3) {
                byte priority = spdySynStreamFrame.getPriority();
                if (priority > 3) {
                    priority = 3;
                }
                buffer2.writeShort((priority & 255) << 14);
            } else {
                buffer2.writeShort((spdySynStreamFrame.getPriority() & 255) << 13);
            }
            if (this.version < 3 && compressHeaderBlock.readableBytes() == 0) {
                buffer2.writeShort(0);
            }
            return ChannelBuffers.wrappedBuffer(buffer2, compressHeaderBlock);
        }
        int i2 = 4;
        if (obj instanceof SpdySynReplyFrame) {
            SpdySynReplyFrame spdySynReplyFrame = (SpdySynReplyFrame) obj;
            ChannelBuffer compressHeaderBlock2 = compressHeaderBlock(encodeHeaderBlock(this.version, spdySynReplyFrame));
            boolean isLast3 = spdySynReplyFrame.isLast();
            int readableBytes2 = compressHeaderBlock2.readableBytes();
            if (this.version >= 3) {
                r2 = readableBytes2 + 4;
            } else if (readableBytes2 != 0) {
                r2 = readableBytes2 + 6;
            }
            ChannelBuffer buffer3 = ChannelBuffers.buffer(ByteOrder.BIG_ENDIAN, r2 + 8);
            buffer3.writeShort(32768 | this.version);
            buffer3.writeShort(2);
            buffer3.writeByte(isLast3 ? 1 : 0);
            buffer3.writeMedium(r2);
            buffer3.writeInt(spdySynReplyFrame.getStreamId());
            if (this.version < 3) {
                if (compressHeaderBlock2.readableBytes() == 0) {
                    buffer3.writeInt(0);
                } else {
                    buffer3.writeShort(0);
                }
            }
            return ChannelBuffers.wrappedBuffer(buffer3, compressHeaderBlock2);
        }
        if (obj instanceof SpdyRstStreamFrame) {
            SpdyRstStreamFrame spdyRstStreamFrame = (SpdyRstStreamFrame) obj;
            ChannelBuffer buffer4 = ChannelBuffers.buffer(ByteOrder.BIG_ENDIAN, 16);
            buffer4.writeShort(this.version | 32768);
            buffer4.writeShort(3);
            buffer4.writeInt(8);
            buffer4.writeInt(spdyRstStreamFrame.getStreamId());
            buffer4.writeInt(spdyRstStreamFrame.getStatus().getCode());
            return buffer4;
        }
        if (obj instanceof SpdySettingsFrame) {
            SpdySettingsFrame spdySettingsFrame = (SpdySettingsFrame) obj;
            boolean clearPreviouslyPersistedSettings = spdySettingsFrame.clearPreviouslyPersistedSettings();
            Set<Integer> ids = spdySettingsFrame.getIds();
            int size = ids.size();
            int i3 = (size * 8) + 4;
            ChannelBuffer buffer5 = ChannelBuffers.buffer(ByteOrder.BIG_ENDIAN, i3 + 8);
            buffer5.writeShort(32768 | this.version);
            buffer5.writeShort(4);
            buffer5.writeByte(clearPreviouslyPersistedSettings ? 1 : 0);
            buffer5.writeMedium(i3);
            buffer5.writeInt(size);
            Iterator<Integer> it = ids.iterator();
            while (it.hasNext()) {
                int intValue = it.next().intValue();
                byte b2 = spdySettingsFrame.isPersistValue(intValue) ? (byte) 1 : (byte) 0;
                if (spdySettingsFrame.isPersisted(intValue)) {
                    b2 = (byte) (b2 | 2);
                }
                if (this.version < 3) {
                    buffer5.writeByte((intValue >> 0) & 255);
                    buffer5.writeByte((intValue >> 8) & 255);
                    buffer5.writeByte((intValue >> 16) & 255);
                    buffer5.writeByte(b2);
                } else {
                    buffer5.writeByte(b2);
                    buffer5.writeMedium(intValue);
                }
                buffer5.writeInt(spdySettingsFrame.getValue(intValue));
            }
            return buffer5;
        }
        if (obj instanceof SpdyNoOpFrame) {
            ChannelBuffer buffer6 = ChannelBuffers.buffer(ByteOrder.BIG_ENDIAN, 8);
            buffer6.writeShort(this.version | 32768);
            buffer6.writeShort(5);
            buffer6.writeInt(0);
            return buffer6;
        }
        if (obj instanceof SpdyPingFrame) {
            ChannelBuffer buffer7 = ChannelBuffers.buffer(ByteOrder.BIG_ENDIAN, 12);
            buffer7.writeShort(this.version | 32768);
            buffer7.writeShort(6);
            buffer7.writeInt(4);
            buffer7.writeInt(((SpdyPingFrame) obj).getId());
            return buffer7;
        }
        if (obj instanceof SpdyGoAwayFrame) {
            SpdyGoAwayFrame spdyGoAwayFrame = (SpdyGoAwayFrame) obj;
            r2 = this.version < 3 ? 4 : 8;
            ChannelBuffer buffer8 = ChannelBuffers.buffer(ByteOrder.BIG_ENDIAN, r2 + 8);
            buffer8.writeShort(this.version | 32768);
            buffer8.writeShort(7);
            buffer8.writeInt(r2);
            buffer8.writeInt(spdyGoAwayFrame.getLastGoodStreamId());
            if (this.version >= 3) {
                buffer8.writeInt(spdyGoAwayFrame.getStatus().getCode());
            }
            return buffer8;
        }
        if (obj instanceof SpdyHeadersFrame) {
            SpdyHeadersFrame spdyHeadersFrame = (SpdyHeadersFrame) obj;
            ChannelBuffer compressHeaderBlock3 = compressHeaderBlock(encodeHeaderBlock(this.version, spdyHeadersFrame));
            boolean isLast4 = spdyHeadersFrame.isLast();
            int readableBytes3 = compressHeaderBlock3.readableBytes();
            if (this.version >= 3) {
                i2 = 4 + readableBytes3;
            } else if (readableBytes3 != 0) {
                i2 = readableBytes3 + 6;
            }
            ChannelBuffer buffer9 = ChannelBuffers.buffer(ByteOrder.BIG_ENDIAN, i2 + 8);
            buffer9.writeShort(32768 | this.version);
            buffer9.writeShort(8);
            buffer9.writeByte(isLast4 ? 1 : 0);
            buffer9.writeMedium(i2);
            buffer9.writeInt(spdyHeadersFrame.getStreamId());
            if (this.version < 3 && compressHeaderBlock3.readableBytes() != 0) {
                buffer9.writeShort(0);
            }
            return ChannelBuffers.wrappedBuffer(buffer9, compressHeaderBlock3);
        }
        if (!(obj instanceof SpdyWindowUpdateFrame)) {
            return obj;
        }
        SpdyWindowUpdateFrame spdyWindowUpdateFrame = (SpdyWindowUpdateFrame) obj;
        ChannelBuffer buffer10 = ChannelBuffers.buffer(ByteOrder.BIG_ENDIAN, 16);
        buffer10.writeShort(this.version | 32768);
        buffer10.writeShort(9);
        buffer10.writeInt(8);
        buffer10.writeInt(spdyWindowUpdateFrame.getStreamId());
        buffer10.writeInt(spdyWindowUpdateFrame.getDeltaWindowSize());
        return buffer10;
    }

    private static void writeLengthField(int i, ChannelBuffer channelBuffer, int i2) {
        if (i < 3) {
            channelBuffer.writeShort(i2);
        } else {
            channelBuffer.writeInt(i2);
        }
    }

    private static void setLengthField(int i, ChannelBuffer channelBuffer, int i2, int i3) {
        if (i < 3) {
            channelBuffer.setShort(i2, i3);
        } else {
            channelBuffer.setInt(i2, i3);
        }
    }

    private static ChannelBuffer encodeHeaderBlock(int i, SpdyHeaderBlock spdyHeaderBlock) throws Exception {
        Set<String> headerNames = spdyHeaderBlock.getHeaderNames();
        int size = headerNames.size();
        if (size == 0) {
            return ChannelBuffers.EMPTY_BUFFER;
        }
        if (size > 65535) {
            throw new IllegalArgumentException("header block contains too many headers");
        }
        ChannelBuffer dynamicBuffer = ChannelBuffers.dynamicBuffer(ByteOrder.BIG_ENDIAN, 256);
        writeLengthField(i, dynamicBuffer, size);
        for (String str : headerNames) {
            byte[] bytes = str.getBytes("UTF-8");
            writeLengthField(i, dynamicBuffer, bytes.length);
            dynamicBuffer.writeBytes(bytes);
            int writerIndex = dynamicBuffer.writerIndex();
            writeLengthField(i, dynamicBuffer, 0);
            Iterator<String> it = spdyHeaderBlock.getHeaders(str).iterator();
            int i2 = 0;
            while (it.hasNext()) {
                byte[] bytes2 = it.next().getBytes("UTF-8");
                dynamicBuffer.writeBytes(bytes2);
                dynamicBuffer.writeByte(0);
                i2 += bytes2.length + 1;
            }
            int i3 = i2 - 1;
            if (i3 > 65535) {
                throw new IllegalArgumentException("header exceeds allowable length: " + str);
            }
            setLengthField(i, dynamicBuffer, writerIndex, i3);
            dynamicBuffer.writerIndex(dynamicBuffer.writerIndex() - 1);
        }
        return dynamicBuffer;
    }

    private synchronized ChannelBuffer compressHeaderBlock(ChannelBuffer channelBuffer) throws Exception {
        if (channelBuffer.readableBytes() == 0) {
            return ChannelBuffers.EMPTY_BUFFER;
        }
        ChannelBuffer dynamicBuffer = ChannelBuffers.dynamicBuffer();
        synchronized (this.headerBlockCompressor) {
            if (!this.finished) {
                this.headerBlockCompressor.setInput(channelBuffer);
                this.headerBlockCompressor.encode(dynamicBuffer);
            }
        }
        return dynamicBuffer;
    }
}
