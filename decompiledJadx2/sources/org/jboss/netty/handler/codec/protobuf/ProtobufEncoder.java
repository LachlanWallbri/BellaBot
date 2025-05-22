package org.jboss.netty.handler.codec.protobuf;

import com.google.protobuf.MessageLite;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

@ChannelHandler.Sharable
/* loaded from: classes7.dex */
public class ProtobufEncoder extends OneToOneEncoder {
    @Override // org.jboss.netty.handler.codec.oneone.OneToOneEncoder
    protected Object encode(ChannelHandlerContext channelHandlerContext, Channel channel, Object obj) throws Exception {
        if (obj instanceof MessageLite) {
            return ChannelBuffers.wrappedBuffer(((MessageLite) obj).toByteArray());
        }
        return obj instanceof MessageLite.Builder ? ChannelBuffers.wrappedBuffer(((MessageLite.Builder) obj).build().toByteArray()) : obj;
    }
}
