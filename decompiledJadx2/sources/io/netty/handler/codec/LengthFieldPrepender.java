package io.netty.handler.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.internal.ObjectUtil;
import java.nio.ByteOrder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
@ChannelHandler.Sharable
/* loaded from: classes.dex */
public class LengthFieldPrepender extends MessageToMessageEncoder<ByteBuf> {
    private final ByteOrder byteOrder;
    private final int lengthAdjustment;
    private final int lengthFieldLength;
    private final boolean lengthIncludesLengthFieldLength;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.MessageToMessageEncoder
    public /* bridge */ /* synthetic */ void encode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List list) throws Exception {
        encode2(channelHandlerContext, byteBuf, (List<Object>) list);
    }

    public LengthFieldPrepender(int i) {
        this(i, false);
    }

    public LengthFieldPrepender(int i, boolean z) {
        this(i, 0, z);
    }

    public LengthFieldPrepender(int i, int i2) {
        this(i, i2, false);
    }

    public LengthFieldPrepender(int i, int i2, boolean z) {
        this(ByteOrder.BIG_ENDIAN, i, i2, z);
    }

    public LengthFieldPrepender(ByteOrder byteOrder, int i, int i2, boolean z) {
        if (i != 1 && i != 2 && i != 3 && i != 4 && i != 8) {
            throw new IllegalArgumentException("lengthFieldLength must be either 1, 2, 3, 4, or 8: " + i);
        }
        ObjectUtil.checkNotNull(byteOrder, "byteOrder");
        this.byteOrder = byteOrder;
        this.lengthFieldLength = i;
        this.lengthIncludesLengthFieldLength = z;
        this.lengthAdjustment = i2;
    }

    /* renamed from: encode, reason: avoid collision after fix types in other method */
    protected void encode2(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int readableBytes = byteBuf.readableBytes() + this.lengthAdjustment;
        if (this.lengthIncludesLengthFieldLength) {
            readableBytes += this.lengthFieldLength;
        }
        if (readableBytes < 0) {
            throw new IllegalArgumentException("Adjusted frame length (" + readableBytes + ") is less than zero");
        }
        int i = this.lengthFieldLength;
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        list.add(channelHandlerContext.alloc().buffer(4).order(this.byteOrder).writeInt(readableBytes));
                    } else if (i == 8) {
                        list.add(channelHandlerContext.alloc().buffer(8).order(this.byteOrder).writeLong(readableBytes));
                    } else {
                        throw new Error("should not reach here");
                    }
                } else {
                    if (readableBytes >= 16777216) {
                        throw new IllegalArgumentException("length does not fit into a medium integer: " + readableBytes);
                    }
                    list.add(channelHandlerContext.alloc().buffer(3).order(this.byteOrder).writeMedium(readableBytes));
                }
            } else {
                if (readableBytes >= 65536) {
                    throw new IllegalArgumentException("length does not fit into a short integer: " + readableBytes);
                }
                list.add(channelHandlerContext.alloc().buffer(2).order(this.byteOrder).writeShort((short) readableBytes));
            }
        } else {
            if (readableBytes >= 256) {
                throw new IllegalArgumentException("length does not fit into a byte: " + readableBytes);
            }
            list.add(channelHandlerContext.alloc().buffer(1).order(this.byteOrder).writeByte((byte) readableBytes));
        }
        list.add(byteBuf.retain());
    }
}
