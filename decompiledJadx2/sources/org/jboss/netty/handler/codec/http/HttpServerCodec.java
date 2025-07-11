package org.jboss.netty.handler.codec.http;

import org.jboss.netty.channel.ChannelDownstreamHandler;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelUpstreamHandler;

/* loaded from: classes7.dex */
public class HttpServerCodec implements ChannelUpstreamHandler, ChannelDownstreamHandler {
    private final HttpRequestDecoder decoder;
    private final HttpResponseEncoder encoder;

    public HttpServerCodec() {
        this(4096, 8192, 8192);
    }

    public HttpServerCodec(int i, int i2, int i3) {
        this.encoder = new HttpResponseEncoder();
        this.decoder = new HttpRequestDecoder(i, i2, i3);
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
