package org.jboss.netty.handler.codec.spdy;

import org.jboss.netty.channel.ChannelDownstreamHandler;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelUpstreamHandler;

/* loaded from: classes7.dex */
public class SpdyHttpCodec implements ChannelUpstreamHandler, ChannelDownstreamHandler {
    private final SpdyHttpDecoder decoder;
    private final SpdyHttpEncoder encoder;

    @Deprecated
    public SpdyHttpCodec(int i) {
        this(2, i);
    }

    public SpdyHttpCodec(int i, int i2) {
        this.decoder = new SpdyHttpDecoder(i, i2);
        this.encoder = new SpdyHttpEncoder(i);
    }

    @Override // org.jboss.netty.channel.ChannelUpstreamHandler
    public void handleUpstream(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent) throws Exception {
        this.decoder.handleUpstream(channelHandlerContext, channelEvent);
    }

    @Override // org.jboss.netty.channel.ChannelDownstreamHandler
    public void handleDownstream(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent) throws Exception {
        this.encoder.handleDownstream(channelHandlerContext, channelEvent);
    }
}
