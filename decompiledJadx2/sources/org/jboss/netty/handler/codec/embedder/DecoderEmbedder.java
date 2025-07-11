package org.jboss.netty.handler.codec.embedder;

import org.jboss.netty.buffer.ChannelBufferFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelUpstreamHandler;
import org.jboss.netty.channel.Channels;

/* loaded from: classes7.dex */
public class DecoderEmbedder<E> extends AbstractCodecEmbedder<E> {
    @Override // org.jboss.netty.handler.codec.embedder.AbstractCodecEmbedder, org.jboss.netty.handler.codec.embedder.CodecEmbedder
    public /* bridge */ /* synthetic */ boolean finish() {
        return super.finish();
    }

    @Override // org.jboss.netty.handler.codec.embedder.AbstractCodecEmbedder, org.jboss.netty.handler.codec.embedder.CodecEmbedder
    public /* bridge */ /* synthetic */ ChannelPipeline getPipeline() {
        return super.getPipeline();
    }

    public DecoderEmbedder(ChannelUpstreamHandler... channelUpstreamHandlerArr) {
        super(channelUpstreamHandlerArr);
    }

    public DecoderEmbedder(ChannelBufferFactory channelBufferFactory, ChannelUpstreamHandler... channelUpstreamHandlerArr) {
        super(channelBufferFactory, channelUpstreamHandlerArr);
    }

    @Override // org.jboss.netty.handler.codec.embedder.CodecEmbedder
    public boolean offer(Object obj) {
        Channels.fireMessageReceived(getChannel(), obj);
        return !super.isEmpty();
    }
}
