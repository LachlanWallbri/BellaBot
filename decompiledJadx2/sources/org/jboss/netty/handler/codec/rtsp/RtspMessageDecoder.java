package org.jboss.netty.handler.codec.rtsp;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.embedder.DecoderEmbedder;
import org.jboss.netty.handler.codec.http.HttpChunkAggregator;
import org.jboss.netty.handler.codec.http.HttpMessage;
import org.jboss.netty.handler.codec.http.HttpMessageDecoder;

/* loaded from: classes7.dex */
public abstract class RtspMessageDecoder extends HttpMessageDecoder {
    private final DecoderEmbedder<HttpMessage> aggregator;

    /* JADX INFO: Access modifiers changed from: protected */
    public RtspMessageDecoder() {
        this(4096, 8192, 8192);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public RtspMessageDecoder(int i, int i2, int i3) {
        super(i, i2, i3 * 2);
        this.aggregator = new DecoderEmbedder<>(new HttpChunkAggregator(i3));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jboss.netty.handler.codec.http.HttpMessageDecoder, org.jboss.netty.handler.codec.replay.ReplayingDecoder
    public Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer, HttpMessageDecoder.State state) throws Exception {
        Object decode = super.decode(channelHandlerContext, channel, channelBuffer, state);
        if (decode == null || !this.aggregator.offer(decode)) {
            return null;
        }
        return this.aggregator.poll();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jboss.netty.handler.codec.http.HttpMessageDecoder
    public boolean isContentAlwaysEmpty(HttpMessage httpMessage) {
        boolean isContentAlwaysEmpty = super.isContentAlwaysEmpty(httpMessage);
        if (!isContentAlwaysEmpty && httpMessage.containsHeader("Content-Length")) {
            return isContentAlwaysEmpty;
        }
        return true;
    }
}
