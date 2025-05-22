package org.jboss.netty.handler.codec.frame;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.Channels;

/* loaded from: classes7.dex */
public class DelimiterBasedFrameDecoder extends FrameDecoder {
    private final ChannelBuffer[] delimiters;
    private boolean discardingTooLongFrame;
    private final boolean failFast;
    private final int maxFrameLength;
    private final boolean stripDelimiter;
    private int tooLongFrameLength;

    public DelimiterBasedFrameDecoder(int i, ChannelBuffer channelBuffer) {
        this(i, true, channelBuffer);
    }

    public DelimiterBasedFrameDecoder(int i, boolean z, ChannelBuffer channelBuffer) {
        this(i, z, false, channelBuffer);
    }

    public DelimiterBasedFrameDecoder(int i, boolean z, boolean z2, ChannelBuffer channelBuffer) {
        validateMaxFrameLength(i);
        validateDelimiter(channelBuffer);
        this.delimiters = new ChannelBuffer[]{channelBuffer.slice(channelBuffer.readerIndex(), channelBuffer.readableBytes())};
        this.maxFrameLength = i;
        this.stripDelimiter = z;
        this.failFast = z2;
    }

    public DelimiterBasedFrameDecoder(int i, ChannelBuffer... channelBufferArr) {
        this(i, true, channelBufferArr);
    }

    public DelimiterBasedFrameDecoder(int i, boolean z, ChannelBuffer... channelBufferArr) {
        this(i, z, false, channelBufferArr);
    }

    public DelimiterBasedFrameDecoder(int i, boolean z, boolean z2, ChannelBuffer... channelBufferArr) {
        validateMaxFrameLength(i);
        if (channelBufferArr == null) {
            throw new NullPointerException("delimiters");
        }
        if (channelBufferArr.length == 0) {
            throw new IllegalArgumentException("empty delimiters");
        }
        this.delimiters = new ChannelBuffer[channelBufferArr.length];
        for (int i2 = 0; i2 < channelBufferArr.length; i2++) {
            ChannelBuffer channelBuffer = channelBufferArr[i2];
            validateDelimiter(channelBuffer);
            this.delimiters[i2] = channelBuffer.slice(channelBuffer.readerIndex(), channelBuffer.readableBytes());
        }
        this.maxFrameLength = i;
        this.stripDelimiter = z;
        this.failFast = z2;
    }

    @Override // org.jboss.netty.handler.codec.frame.FrameDecoder
    protected Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer) throws Exception {
        ChannelBuffer channelBuffer2 = null;
        int i = Integer.MAX_VALUE;
        for (ChannelBuffer channelBuffer3 : this.delimiters) {
            int indexOf = indexOf(channelBuffer, channelBuffer3);
            if (indexOf >= 0 && indexOf < i) {
                channelBuffer2 = channelBuffer3;
                i = indexOf;
            }
        }
        if (channelBuffer2 != null) {
            int capacity = channelBuffer2.capacity();
            if (this.discardingTooLongFrame) {
                this.discardingTooLongFrame = false;
                channelBuffer.skipBytes(i + capacity);
                int i2 = this.tooLongFrameLength;
                this.tooLongFrameLength = 0;
                if (!this.failFast) {
                    fail(channelHandlerContext, i2);
                }
                return null;
            }
            if (i > this.maxFrameLength) {
                channelBuffer.skipBytes(capacity + i);
                fail(channelHandlerContext, i);
                return null;
            }
            if (this.stripDelimiter) {
                ChannelBuffer readBytes = channelBuffer.readBytes(i);
                channelBuffer.skipBytes(capacity);
                return readBytes;
            }
            return channelBuffer.readBytes(i + capacity);
        }
        if (!this.discardingTooLongFrame) {
            if (channelBuffer.readableBytes() > this.maxFrameLength) {
                this.tooLongFrameLength = channelBuffer.readableBytes();
                channelBuffer.skipBytes(channelBuffer.readableBytes());
                this.discardingTooLongFrame = true;
                if (this.failFast) {
                    fail(channelHandlerContext, this.tooLongFrameLength);
                }
            }
        } else {
            this.tooLongFrameLength += channelBuffer.readableBytes();
            channelBuffer.skipBytes(channelBuffer.readableBytes());
        }
        return null;
    }

    private void fail(ChannelHandlerContext channelHandlerContext, long j) {
        if (j > 0) {
            Channels.fireExceptionCaught(channelHandlerContext.getChannel(), new TooLongFrameException("frame length exceeds " + this.maxFrameLength + ": " + j + " - discarded"));
            return;
        }
        Channels.fireExceptionCaught(channelHandlerContext.getChannel(), new TooLongFrameException("frame length exceeds " + this.maxFrameLength + " - discarding"));
    }

    private static int indexOf(ChannelBuffer channelBuffer, ChannelBuffer channelBuffer2) {
        for (int readerIndex = channelBuffer.readerIndex(); readerIndex < channelBuffer.writerIndex(); readerIndex++) {
            int i = 0;
            int i2 = readerIndex;
            while (i < channelBuffer2.capacity() && channelBuffer.getByte(i2) == channelBuffer2.getByte(i)) {
                i2++;
                if (i2 == channelBuffer.writerIndex() && i != channelBuffer2.capacity() - 1) {
                    return -1;
                }
                i++;
            }
            if (i == channelBuffer2.capacity()) {
                return readerIndex - channelBuffer.readerIndex();
            }
        }
        return -1;
    }

    private static void validateDelimiter(ChannelBuffer channelBuffer) {
        if (channelBuffer == null) {
            throw new NullPointerException(RequestParameters.DELIMITER);
        }
        if (!channelBuffer.readable()) {
            throw new IllegalArgumentException("empty delimiter");
        }
    }

    private static void validateMaxFrameLength(int i) {
        if (i > 0) {
            return;
        }
        throw new IllegalArgumentException("maxFrameLength must be a positive integer: " + i);
    }
}
