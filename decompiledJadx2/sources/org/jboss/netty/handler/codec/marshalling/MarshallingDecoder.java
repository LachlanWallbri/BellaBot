package org.jboss.netty.handler.codec.marshalling;

import org.jboss.marshalling.Unmarshaller;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;

/* loaded from: classes7.dex */
public class MarshallingDecoder extends LengthFieldBasedFrameDecoder {
    private final UnmarshallerProvider provider;

    public MarshallingDecoder(UnmarshallerProvider unmarshallerProvider) {
        this(unmarshallerProvider, 1048576);
    }

    public MarshallingDecoder(UnmarshallerProvider unmarshallerProvider, int i) {
        super(i, 0, 4, 0, 4);
        this.provider = unmarshallerProvider;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder, org.jboss.netty.handler.codec.frame.FrameDecoder
    public Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer) throws Exception {
        ChannelBuffer channelBuffer2 = (ChannelBuffer) super.decode(channelHandlerContext, channel, channelBuffer);
        if (channelBuffer2 == null) {
            return null;
        }
        Unmarshaller unmarshaller = this.provider.getUnmarshaller(channelHandlerContext);
        try {
            unmarshaller.start(new ChannelBufferByteInput(channelBuffer2));
            Object readObject = unmarshaller.readObject();
            unmarshaller.finish();
            return readObject;
        } finally {
            unmarshaller.close();
        }
    }

    @Override // org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder
    protected ChannelBuffer extractFrame(ChannelBuffer channelBuffer, int i, int i2) {
        return channelBuffer.slice(i, i2);
    }
}
