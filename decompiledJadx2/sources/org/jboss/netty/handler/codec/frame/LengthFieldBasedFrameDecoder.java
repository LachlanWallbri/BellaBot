package org.jboss.netty.handler.codec.frame;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.Channels;

/* loaded from: classes7.dex */
public class LengthFieldBasedFrameDecoder extends FrameDecoder {
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
        this(i, i2, i3, i4, i5, false);
    }

    public LengthFieldBasedFrameDecoder(int i, int i2, int i3, int i4, int i5, boolean z) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxFrameLength must be a positive integer: " + i);
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("lengthFieldOffset must be a non-negative integer: " + i2);
        }
        if (i5 < 0) {
            throw new IllegalArgumentException("initialBytesToStrip must be a non-negative integer: " + i5);
        }
        if (i3 != 1 && i3 != 2 && i3 != 3 && i3 != 4 && i3 != 8) {
            throw new IllegalArgumentException("lengthFieldLength must be either 1, 2, 3, 4, or 8: " + i3);
        }
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

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00fd  */
    @Override // org.jboss.netty.handler.codec.frame.FrameDecoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer) throws Exception {
        int unsignedByte;
        long j;
        if (this.discardingTooLongFrame) {
            long j2 = this.bytesToDiscard;
            int min = (int) Math.min(j2, channelBuffer.readableBytes());
            channelBuffer.skipBytes(min);
            this.bytesToDiscard = j2 - min;
            failIfNecessary(channelHandlerContext, false);
            return null;
        }
        if (channelBuffer.readableBytes() < this.lengthFieldEndOffset) {
            return null;
        }
        int readerIndex = channelBuffer.readerIndex() + this.lengthFieldOffset;
        int i = this.lengthFieldLength;
        if (i == 1) {
            unsignedByte = channelBuffer.getUnsignedByte(readerIndex);
        } else if (i == 2) {
            unsignedByte = channelBuffer.getUnsignedShort(readerIndex);
        } else {
            if (i != 3) {
                if (i == 4) {
                    j = channelBuffer.getUnsignedInt(readerIndex);
                } else if (i == 8) {
                    j = channelBuffer.getLong(readerIndex);
                } else {
                    throw new Error("should not reach here");
                }
                if (j >= 0) {
                    channelBuffer.skipBytes(this.lengthFieldEndOffset);
                    throw new CorruptedFrameException("negative pre-adjustment length field: " + j);
                }
                int i2 = this.lengthAdjustment;
                int i3 = this.lengthFieldEndOffset;
                long j3 = j + i2 + i3;
                if (j3 < i3) {
                    channelBuffer.skipBytes(i3);
                    throw new CorruptedFrameException("Adjusted frame length (" + j3 + ") is less than lengthFieldEndOffset: " + this.lengthFieldEndOffset);
                }
                if (j3 > this.maxFrameLength) {
                    this.discardingTooLongFrame = true;
                    this.tooLongFrameLength = j3;
                    this.bytesToDiscard = j3 - channelBuffer.readableBytes();
                    channelBuffer.skipBytes(channelBuffer.readableBytes());
                    failIfNecessary(channelHandlerContext, true);
                    return null;
                }
                int i4 = (int) j3;
                if (channelBuffer.readableBytes() < i4) {
                    return null;
                }
                int i5 = this.initialBytesToStrip;
                if (i5 > i4) {
                    channelBuffer.skipBytes(i4);
                    throw new CorruptedFrameException("Adjusted frame length (" + j3 + ") is less than initialBytesToStrip: " + this.initialBytesToStrip);
                }
                channelBuffer.skipBytes(i5);
                int readerIndex2 = channelBuffer.readerIndex();
                int i6 = i4 - this.initialBytesToStrip;
                ChannelBuffer extractFrame = extractFrame(channelBuffer, readerIndex2, i6);
                channelBuffer.readerIndex(readerIndex2 + i6);
                return extractFrame;
            }
            unsignedByte = channelBuffer.getUnsignedMedium(readerIndex);
        }
        j = unsignedByte;
        if (j >= 0) {
        }
    }

    private void failIfNecessary(ChannelHandlerContext channelHandlerContext, boolean z) {
        if (this.bytesToDiscard == 0) {
            long j = this.tooLongFrameLength;
            this.tooLongFrameLength = 0L;
            this.discardingTooLongFrame = false;
            boolean z2 = this.failFast;
            if (!z2 || (z2 && z)) {
                fail(channelHandlerContext, j);
                return;
            }
            return;
        }
        if (this.failFast && z) {
            fail(channelHandlerContext, this.tooLongFrameLength);
        }
    }

    protected ChannelBuffer extractFrame(ChannelBuffer channelBuffer, int i, int i2) {
        ChannelBuffer buffer = channelBuffer.factory().getBuffer(i2);
        buffer.writeBytes(channelBuffer, i, i2);
        return buffer;
    }

    private void fail(ChannelHandlerContext channelHandlerContext, long j) {
        if (j > 0) {
            Channels.fireExceptionCaught(channelHandlerContext.getChannel(), new TooLongFrameException("Adjusted frame length exceeds " + this.maxFrameLength + ": " + j + " - discarded"));
            return;
        }
        Channels.fireExceptionCaught(channelHandlerContext.getChannel(), new TooLongFrameException("Adjusted frame length exceeds " + this.maxFrameLength + " - discarding"));
    }
}
