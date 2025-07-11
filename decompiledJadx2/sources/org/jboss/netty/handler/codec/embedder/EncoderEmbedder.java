package org.jboss.netty.handler.codec.embedder;

import org.jboss.netty.buffer.ChannelBufferFactory;
import org.jboss.netty.channel.ChannelDownstreamHandler;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.Channels;

/* loaded from: classes7.dex */
public class EncoderEmbedder<E> extends AbstractCodecEmbedder<E> {
    @Override // org.jboss.netty.handler.codec.embedder.AbstractCodecEmbedder, org.jboss.netty.handler.codec.embedder.CodecEmbedder
    public /* bridge */ /* synthetic */ boolean finish() {
        return super.finish();
    }

    @Override // org.jboss.netty.handler.codec.embedder.AbstractCodecEmbedder, org.jboss.netty.handler.codec.embedder.CodecEmbedder
    public /* bridge */ /* synthetic */ ChannelPipeline getPipeline() {
        return super.getPipeline();
    }

    public EncoderEmbedder(ChannelDownstreamHandler... channelDownstreamHandlerArr) {
        super(channelDownstreamHandlerArr);
    }

    public EncoderEmbedder(ChannelBufferFactory channelBufferFactory, ChannelDownstreamHandler... channelDownstreamHandlerArr) {
        super(channelBufferFactory, channelDownstreamHandlerArr);
    }

    @Override // org.jboss.netty.handler.codec.embedder.CodecEmbedder
    public boolean offer(Object obj) {
        Channels.write(getChannel(), obj).setSuccess();
        return !isEmpty();
    }
}
