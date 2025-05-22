package io.grpc.netty.shaded.io.netty.handler.codec.marshalling;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.MessageToByteEncoder;
import org.jboss.marshalling.Marshaller;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@ChannelHandler.Sharable
/* loaded from: classes7.dex */
public class CompatibleMarshallingEncoder extends MessageToByteEncoder<Object> {
    private final MarshallerProvider provider;

    public CompatibleMarshallingEncoder(MarshallerProvider marshallerProvider) {
        this.provider = marshallerProvider;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageToByteEncoder
    protected void encode(ChannelHandlerContext channelHandlerContext, Object obj, ByteBuf byteBuf) throws Exception {
        Marshaller marshaller = this.provider.getMarshaller(channelHandlerContext);
        marshaller.start(new ChannelBufferByteOutput(byteBuf));
        marshaller.writeObject(obj);
        marshaller.finish();
        marshaller.close();
    }
}
