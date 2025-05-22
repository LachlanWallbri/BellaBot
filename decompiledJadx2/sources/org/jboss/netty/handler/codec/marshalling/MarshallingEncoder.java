package org.jboss.netty.handler.codec.marshalling;

import org.jboss.marshalling.Marshaller;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

@ChannelHandler.Sharable
/* loaded from: classes7.dex */
public class MarshallingEncoder extends OneToOneEncoder {
    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];
    private final int estimatedLength;
    private final MarshallerProvider provider;

    public MarshallingEncoder(MarshallerProvider marshallerProvider) {
        this(marshallerProvider, 512);
    }

    public MarshallingEncoder(MarshallerProvider marshallerProvider, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("estimatedLength: " + i);
        }
        this.estimatedLength = i;
        this.provider = marshallerProvider;
    }

    @Override // org.jboss.netty.handler.codec.oneone.OneToOneEncoder
    protected Object encode(ChannelHandlerContext channelHandlerContext, Channel channel, Object obj) throws Exception {
        Marshaller marshaller = this.provider.getMarshaller(channelHandlerContext);
        ChannelBufferByteOutput channelBufferByteOutput = new ChannelBufferByteOutput(channelHandlerContext.getChannel().getConfig().getBufferFactory(), this.estimatedLength);
        channelBufferByteOutput.getBuffer().writeBytes(LENGTH_PLACEHOLDER);
        marshaller.start(channelBufferByteOutput);
        marshaller.writeObject(obj);
        marshaller.finish();
        marshaller.close();
        ChannelBuffer buffer = channelBufferByteOutput.getBuffer();
        buffer.setInt(0, buffer.writerIndex() - 4);
        return buffer;
    }
}
