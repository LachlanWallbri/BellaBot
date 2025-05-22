package org.jboss.netty.handler.codec.marshalling;

import org.jboss.marshalling.ByteInput;
import org.jboss.marshalling.Unmarshaller;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.handler.codec.frame.TooLongFrameException;
import org.jboss.netty.handler.codec.marshalling.LimitingByteInput;
import org.jboss.netty.handler.codec.replay.ReplayingDecoder;
import org.jboss.netty.handler.codec.replay.VoidEnum;

/* loaded from: classes7.dex */
public class CompatibleMarshallingDecoder extends ReplayingDecoder<VoidEnum> {
    protected final int maxObjectSize;
    protected final UnmarshallerProvider provider;

    public CompatibleMarshallingDecoder(UnmarshallerProvider unmarshallerProvider, int i) {
        this.provider = unmarshallerProvider;
        this.maxObjectSize = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jboss.netty.handler.codec.replay.ReplayingDecoder
    public Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer, VoidEnum voidEnum) throws Exception {
        Unmarshaller unmarshaller = this.provider.getUnmarshaller(channelHandlerContext);
        ByteInput channelBufferByteInput = new ChannelBufferByteInput(channelBuffer);
        int i = this.maxObjectSize;
        if (i != Integer.MAX_VALUE) {
            channelBufferByteInput = new LimitingByteInput(channelBufferByteInput, i);
        }
        try {
            try {
                unmarshaller.start(channelBufferByteInput);
                Object readObject = unmarshaller.readObject();
                unmarshaller.finish();
                return readObject;
            } catch (LimitingByteInput.TooBigObjectException unused) {
                throw new TooLongFrameException("Object to big to unmarshal");
            }
        } finally {
            unmarshaller.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jboss.netty.handler.codec.replay.ReplayingDecoder
    public Object decodeLast(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer, VoidEnum voidEnum) throws Exception {
        int readableBytes = channelBuffer.readableBytes();
        if (readableBytes == 0) {
            return null;
        }
        if (readableBytes == 1 && channelBuffer.getByte(channelBuffer.readerIndex()) == 121) {
            channelBuffer.skipBytes(1);
            return null;
        }
        return decode(channelHandlerContext, channel, channelBuffer, voidEnum);
    }

    @Override // org.jboss.netty.handler.codec.frame.FrameDecoder, org.jboss.netty.channel.SimpleChannelUpstreamHandler
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, ExceptionEvent exceptionEvent) throws Exception {
        if (exceptionEvent.getCause() instanceof TooLongFrameException) {
            exceptionEvent.getChannel().close();
        } else {
            super.exceptionCaught(channelHandlerContext, exceptionEvent);
        }
    }
}
