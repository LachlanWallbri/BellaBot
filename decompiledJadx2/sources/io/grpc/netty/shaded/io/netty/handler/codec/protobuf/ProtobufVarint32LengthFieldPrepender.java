package io.grpc.netty.shaded.io.netty.handler.codec.protobuf;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.MessageToByteEncoder;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@ChannelHandler.Sharable
/* loaded from: classes7.dex */
public class ProtobufVarint32LengthFieldPrepender extends MessageToByteEncoder<ByteBuf> {
    static int computeRawVarint32Size(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return (i & (-268435456)) == 0 ? 4 : 5;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageToByteEncoder
    public void encode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, ByteBuf byteBuf2) throws Exception {
        int readableBytes = byteBuf.readableBytes();
        byteBuf2.ensureWritable(computeRawVarint32Size(readableBytes) + readableBytes);
        writeRawVarint32(byteBuf2, readableBytes);
        byteBuf2.writeBytes(byteBuf, byteBuf.readerIndex(), readableBytes);
    }

    static void writeRawVarint32(ByteBuf byteBuf, int i) {
        while ((i & (-128)) != 0) {
            byteBuf.writeByte((i & 127) | 128);
            i >>>= 7;
        }
        byteBuf.writeByte(i);
    }
}
