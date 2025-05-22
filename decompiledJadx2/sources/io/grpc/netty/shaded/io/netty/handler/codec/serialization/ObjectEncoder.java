package io.grpc.netty.shaded.io.netty.handler.codec.serialization;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufOutputStream;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.MessageToByteEncoder;
import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@ChannelHandler.Sharable
/* loaded from: classes7.dex */
public class ObjectEncoder extends MessageToByteEncoder<Serializable> {
    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageToByteEncoder
    public void encode(ChannelHandlerContext channelHandlerContext, Serializable serializable, ByteBuf byteBuf) throws Exception {
        CompactObjectOutputStream compactObjectOutputStream;
        int writerIndex = byteBuf.writerIndex();
        ByteBufOutputStream byteBufOutputStream = new ByteBufOutputStream(byteBuf);
        CompactObjectOutputStream compactObjectOutputStream2 = null;
        try {
            byteBufOutputStream.write(LENGTH_PLACEHOLDER);
            compactObjectOutputStream = new CompactObjectOutputStream(byteBufOutputStream);
        } catch (Throwable th) {
            th = th;
        }
        try {
            compactObjectOutputStream.writeObject(serializable);
            compactObjectOutputStream.flush();
            compactObjectOutputStream.close();
            byteBuf.setInt(writerIndex, (byteBuf.writerIndex() - writerIndex) - 4);
        } catch (Throwable th2) {
            th = th2;
            compactObjectOutputStream2 = compactObjectOutputStream;
            if (compactObjectOutputStream2 != null) {
                compactObjectOutputStream2.close();
            } else {
                byteBufOutputStream.close();
            }
            throw th;
        }
    }
}
