package org.jboss.netty.handler.codec.frame;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

@ChannelHandler.Sharable
/* loaded from: classes7.dex */
public class LengthFieldPrepender extends OneToOneEncoder {
    private final int lengthFieldLength;
    private final boolean lengthIncludesLengthFieldLength;

    public LengthFieldPrepender(int i) {
        this(i, false);
    }

    public LengthFieldPrepender(int i, boolean z) {
        if (i != 1 && i != 2 && i != 3 && i != 4 && i != 8) {
            throw new IllegalArgumentException("lengthFieldLength must be either 1, 2, 3, 4, or 8: " + i);
        }
        this.lengthFieldLength = i;
        this.lengthIncludesLengthFieldLength = z;
    }

    @Override // org.jboss.netty.handler.codec.oneone.OneToOneEncoder
    protected Object encode(ChannelHandlerContext channelHandlerContext, Channel channel, Object obj) throws Exception {
        if (!(obj instanceof ChannelBuffer)) {
            return obj;
        }
        ChannelBuffer channelBuffer = (ChannelBuffer) obj;
        ChannelBuffer buffer = channel.getConfig().getBufferFactory().getBuffer(channelBuffer.order(), this.lengthFieldLength);
        int readableBytes = this.lengthIncludesLengthFieldLength ? channelBuffer.readableBytes() + this.lengthFieldLength : channelBuffer.readableBytes();
        int i = this.lengthFieldLength;
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        buffer.writeInt(readableBytes);
                    } else if (i == 8) {
                        buffer.writeLong(readableBytes);
                    } else {
                        throw new Error("should not reach here");
                    }
                } else {
                    if (readableBytes >= 16777216) {
                        throw new IllegalArgumentException("length does not fit into a medium integer: " + readableBytes);
                    }
                    buffer.writeMedium(readableBytes);
                }
            } else {
                if (readableBytes >= 65536) {
                    throw new IllegalArgumentException("length does not fit into a short integer: " + readableBytes);
                }
                buffer.writeShort((short) readableBytes);
            }
        } else {
            if (readableBytes >= 256) {
                throw new IllegalArgumentException("length does not fit into a byte: " + readableBytes);
            }
            buffer.writeByte((byte) readableBytes);
        }
        return ChannelBuffers.wrappedBuffer(buffer, channelBuffer);
    }
}
