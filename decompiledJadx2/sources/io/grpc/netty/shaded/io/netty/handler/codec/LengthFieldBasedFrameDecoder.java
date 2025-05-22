package io.grpc.netty.shaded.io.netty.handler.codec;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.nio.ByteOrder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class LengthFieldBasedFrameDecoder extends ByteToMessageDecoder {
    private final ByteOrder byteOrder;
    private long bytesToDiscard;
    private boolean discardingTooLongFrame;
    private final boolean failFast;
    private final int initialBytesToStrip;
    private final int lengthAdjustment;
    private final int lengthFieldEndOffset;
    private final int lengthFieldLength;
    private final int lengthFieldOffset;
    private final int maxFrameLength;
    private long tooLongFrameLength;

    public LengthFieldBasedFrameDecoder(int i, int i2, int i3) {
        this(i, i2, i3, 0, 0);
    }

    public LengthFieldBasedFrameDecoder(int i, int i2, int i3, int i4, int i5) {
        this(i, i2, i3, i4, i5, true);
    }

    public LengthFieldBasedFrameDecoder(int i, int i2, int i3, int i4, int i5, boolean z) {
        this(ByteOrder.BIG_ENDIAN, i, i2, i3, i4, i5, z);
    }

    public LengthFieldBasedFrameDecoder(ByteOrder byteOrder, int i, int i2, int i3, int i4, int i5, boolean z) {
        this.byteOrder = (ByteOrder) ObjectUtil.checkNotNull(byteOrder, "byteOrder");
        ObjectUtil.checkPositive(i, "maxFrameLength");
        ObjectUtil.checkPositiveOrZero(i2, "lengthFieldOffset");
        ObjectUtil.checkPositiveOrZero(i5, "initialBytesToStrip");
        if (i2 > i - i3) {
            throw new IllegalArgumentException("maxFrameLength (" + i + ") must be equal to or greater than lengthFieldOffset (" + i2 + ") + lengthFieldLength (" + i3 + ").");
        }
        this.maxFrameLength = i;
        this.lengthFieldOffset = i2;
        this.lengthFieldLength = i3;
        this.lengthAdjustment = i4;
        this.lengthFieldEndOffset = i2 + i3;
        this.initialBytesToStrip = i5;
        this.failFast = z;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    protected final void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        Object decode = decode(channelHandlerContext, byteBuf);
        if (decode != null) {
            list.add(decode);
        }
    }

    private void discardingTooLongFrame(ByteBuf byteBuf) {
        long j = this.bytesToDiscard;
        int min = (int) Math.min(j, byteBuf.readableBytes());
        byteBuf.skipBytes(min);
        this.bytesToDiscard = j - min;
        failIfNecessary(false);
    }

    private static void failOnNegativeLengthField(ByteBuf byteBuf, long j, int i) {
        byteBuf.skipBytes(i);
        throw new CorruptedFrameException("negative pre-adjustment length field: " + j);
    }

    private static void failOnFrameLengthLessThanLengthFieldEndOffset(ByteBuf byteBuf, long j, int i) {
        byteBuf.skipBytes(i);
        throw new CorruptedFrameException("Adjusted frame length (" + j + ") is less than lengthFieldEndOffset: " + i);
    }

    private void exceededFrameLength(ByteBuf byteBuf, long j) {
        long readableBytes = j - byteBuf.readableBytes();
        this.tooLongFrameLength = j;
        if (readableBytes < 0) {
            byteBuf.skipBytes((int) j);
        } else {
            this.discardingTooLongFrame = true;
            this.bytesToDiscard = readableBytes;
            byteBuf.skipBytes(byteBuf.readableBytes());
        }
        failIfNecessary(true);
    }

    private static void failOnFrameLengthLessThanInitialBytesToStrip(ByteBuf byteBuf, long j, int i) {
        byteBuf.skipBytes((int) j);
        throw new CorruptedFrameException("Adjusted frame length (" + j + ") is less than initialBytesToStrip: " + i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        if (this.discardingTooLongFrame) {
            discardingTooLongFrame(byteBuf);
        }
        if (byteBuf.readableBytes() < this.lengthFieldEndOffset) {
            return null;
        }
        long unadjustedFrameLength = getUnadjustedFrameLength(byteBuf, byteBuf.readerIndex() + this.lengthFieldOffset, this.lengthFieldLength, this.byteOrder);
        if (unadjustedFrameLength < 0) {
            failOnNegativeLengthField(byteBuf, unadjustedFrameLength, this.lengthFieldEndOffset);
        }
        int i = this.lengthAdjustment;
        int i2 = this.lengthFieldEndOffset;
        long j = unadjustedFrameLength + i + i2;
        if (j < i2) {
            failOnFrameLengthLessThanLengthFieldEndOffset(byteBuf, j, i2);
        }
        if (j > this.maxFrameLength) {
            exceededFrameLength(byteBuf, j);
            return null;
        }
        int i3 = (int) j;
        if (byteBuf.readableBytes() < i3) {
            return null;
        }
        int i4 = this.initialBytesToStrip;
        if (i4 > i3) {
            failOnFrameLengthLessThanInitialBytesToStrip(byteBuf, j, i4);
        }
        byteBuf.skipBytes(this.initialBytesToStrip);
        int readerIndex = byteBuf.readerIndex();
        int i5 = i3 - this.initialBytesToStrip;
        ByteBuf extractFrame = extractFrame(channelHandlerContext, byteBuf, readerIndex, i5);
        byteBuf.readerIndex(readerIndex + i5);
        return extractFrame;
    }

    protected long getUnadjustedFrameLength(ByteBuf byteBuf, int i, int i2, ByteOrder byteOrder) {
        int unsignedByte;
        ByteBuf order = byteBuf.order(byteOrder);
        if (i2 == 1) {
            unsignedByte = order.getUnsignedByte(i);
        } else if (i2 == 2) {
            unsignedByte = order.getUnsignedShort(i);
        } else {
            if (i2 != 3) {
                if (i2 == 4) {
                    return order.getUnsignedInt(i);
                }
                if (i2 == 8) {
                    return order.getLong(i);
                }
                throw new DecoderException("unsupported lengthFieldLength: " + this.lengthFieldLength + " (expected: 1, 2, 3, 4, or 8)");
            }
            unsignedByte = order.getUnsignedMedium(i);
        }
        return unsignedByte;
    }

    private void failIfNecessary(boolean z) {
        if (this.bytesToDiscard == 0) {
            long j = this.tooLongFrameLength;
            this.tooLongFrameLength = 0L;
            this.discardingTooLongFrame = false;
            if (!this.failFast || z) {
                fail(j);
                return;
            }
            return;
        }
        if (this.failFast && z) {
            fail(this.tooLongFrameLength);
        }
    }

    protected ByteBuf extractFrame(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, int i, int i2) {
        return byteBuf.retainedSlice(i, i2);
    }

    private void fail(long j) {
        if (j > 0) {
            throw new TooLongFrameException("Adjusted frame length exceeds " + this.maxFrameLength + ": " + j + " - discarded");
        }
        throw new TooLongFrameException("Adjusted frame length exceeds " + this.maxFrameLength + " - discarding");
    }
}
