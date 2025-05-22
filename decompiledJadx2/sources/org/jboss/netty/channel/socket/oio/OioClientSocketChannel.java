package org.jboss.netty.channel.socket.oio;

import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.net.Socket;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelSink;
import org.jboss.netty.channel.Channels;

/* loaded from: classes7.dex */
class OioClientSocketChannel extends OioSocketChannel {

    /* renamed from: in */
    volatile PushbackInputStream f10025in;
    volatile OutputStream out;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OioClientSocketChannel(ChannelFactory channelFactory, ChannelPipeline channelPipeline, ChannelSink channelSink) {
        super(null, channelFactory, channelPipeline, channelSink, new Socket());
        Channels.fireChannelOpen(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.jboss.netty.channel.socket.oio.OioSocketChannel
    public PushbackInputStream getInputStream() {
        return this.f10025in;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.jboss.netty.channel.socket.oio.OioSocketChannel
    public OutputStream getOutputStream() {
        return this.out;
    }
}
