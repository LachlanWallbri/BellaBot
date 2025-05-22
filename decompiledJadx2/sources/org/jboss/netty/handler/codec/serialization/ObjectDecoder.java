package org.jboss.netty.handler.codec.serialization;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferInputStream;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;

/* loaded from: classes7.dex */
public class ObjectDecoder extends LengthFieldBasedFrameDecoder {
    private final ClassResolver classResolver;

    @Deprecated
    public ObjectDecoder() {
        this(1048576);
    }

    public ObjectDecoder(ClassResolver classResolver) {
        this(1048576, classResolver);
    }

    @Deprecated
    public ObjectDecoder(int i) {
        this(i, ClassResolvers.weakCachingResolver(null));
    }

    public ObjectDecoder(int i, ClassResolver classResolver) {
        super(i, 0, 4, 0, 4);
        this.classResolver = classResolver;
    }

    @Deprecated
    public ObjectDecoder(int i, ClassLoader classLoader) {
        this(i, ClassResolvers.weakCachingResolver(classLoader));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder, org.jboss.netty.handler.codec.frame.FrameDecoder
    public Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer) throws Exception {
        ChannelBuffer channelBuffer2 = (ChannelBuffer) super.decode(channelHandlerContext, channel, channelBuffer);
        if (channelBuffer2 == null) {
            return null;
        }
        return new CompactObjectInputStream(new ChannelBufferInputStream(channelBuffer2), this.classResolver).readObject();
    }

    @Override // org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder
    protected ChannelBuffer extractFrame(ChannelBuffer channelBuffer, int i, int i2) {
        return channelBuffer.slice(i, i2);
    }
}
