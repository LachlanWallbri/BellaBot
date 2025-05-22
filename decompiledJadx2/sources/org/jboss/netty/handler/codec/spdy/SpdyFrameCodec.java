package org.jboss.netty.handler.codec.spdy;

import org.jboss.netty.channel.ChannelDownstreamHandler;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelUpstreamHandler;

/* loaded from: classes7.dex */
public class SpdyFrameCodec implements ChannelUpstreamHandler, ChannelDownstreamHandler {
    private final SpdyFrameDecoder decoder;
    private final SpdyFrameEncoder encoder;

    @Deprecated
    public SpdyFrameCodec() {
        this(2);
    }

    public SpdyFrameCodec(int i) {
        this(i, 8192, 16384, 6, 15, 8);
    }

    public SpdyFrameCodec(int i, int i2, int i3, int i4, int i5, int i6) {
        this.decoder = new SpdyFrameDecoder(i, i2, i3);
        this.encoder = new SpdyFrameEncoder(i, i4, i5, i6);
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
