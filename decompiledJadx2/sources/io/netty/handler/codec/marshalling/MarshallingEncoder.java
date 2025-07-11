package io.netty.handler.codec.marshalling;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.jboss.marshalling.Marshaller;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
@ChannelHandler.Sharable
/* loaded from: classes.dex */
public class MarshallingEncoder extends MessageToByteEncoder<Object> {
    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];
    private final MarshallerProvider provider;

    public MarshallingEncoder(MarshallerProvider marshallerProvider) {
        this.provider = marshallerProvider;
    }

    @Override // io.netty.handler.codec.MessageToByteEncoder
    protected void encode(ChannelHandlerContext channelHandlerContext, Object obj, ByteBuf byteBuf) throws Exception {
        Marshaller marshaller = this.provider.getMarshaller(channelHandlerContext);
        int writerIndex = byteBuf.writerIndex();
        byteBuf.writeBytes(LENGTH_PLACEHOLDER);
        marshaller.start(new ChannelBufferByteOutput(byteBuf));
        marshaller.writeObject(obj);
        marshaller.finish();
        marshaller.close();
        byteBuf.setInt(writerIndex, (byteBuf.writerIndex() - writerIndex) - 4);
    }
}
