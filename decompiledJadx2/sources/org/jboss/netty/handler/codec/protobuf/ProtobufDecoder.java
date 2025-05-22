package org.jboss.netty.handler.codec.protobuf;

import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.MessageLite;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferInputStream;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

@ChannelHandler.Sharable
/* loaded from: classes7.dex */
public class ProtobufDecoder extends OneToOneDecoder {
    private final ExtensionRegistry extensionRegistry;
    private final MessageLite prototype;

    public ProtobufDecoder(MessageLite messageLite) {
        this(messageLite, null);
    }

    public ProtobufDecoder(MessageLite messageLite, ExtensionRegistry extensionRegistry) {
        if (messageLite == null) {
            throw new NullPointerException("prototype");
        }
        this.prototype = messageLite.getDefaultInstanceForType();
        this.extensionRegistry = extensionRegistry;
    }

    @Override // org.jboss.netty.handler.codec.oneone.OneToOneDecoder
    protected Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, Object obj) throws Exception {
        if (!(obj instanceof ChannelBuffer)) {
            return obj;
        }
        ChannelBuffer channelBuffer = (ChannelBuffer) obj;
        if (channelBuffer.hasArray()) {
            int readerIndex = channelBuffer.readerIndex();
            if (this.extensionRegistry == null) {
                return this.prototype.newBuilderForType().mergeFrom(channelBuffer.array(), channelBuffer.arrayOffset() + readerIndex, channelBuffer.readableBytes()).build();
            }
            return this.prototype.newBuilderForType().mergeFrom(channelBuffer.array(), channelBuffer.arrayOffset() + readerIndex, channelBuffer.readableBytes(), this.extensionRegistry).build();
        }
        if (this.extensionRegistry == null) {
            return this.prototype.newBuilderForType().mergeFrom(new ChannelBufferInputStream(channelBuffer)).build();
        }
        return this.prototype.newBuilderForType().mergeFrom(new ChannelBufferInputStream(channelBuffer), this.extensionRegistry).build();
    }
}
