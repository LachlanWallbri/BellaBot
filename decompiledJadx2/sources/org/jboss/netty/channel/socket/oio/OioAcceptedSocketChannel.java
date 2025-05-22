package org.jboss.netty.channel.socket.oio;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.net.Socket;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelSink;
import org.jboss.netty.channel.Channels;

/* loaded from: classes7.dex */
class OioAcceptedSocketChannel extends OioSocketChannel {

    /* renamed from: in */
    private final PushbackInputStream f10024in;
    private final OutputStream out;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OioAcceptedSocketChannel(Channel channel, ChannelFactory channelFactory, ChannelPipeline channelPipeline, ChannelSink channelSink, Socket socket) {
        super(channel, channelFactory, channelPipeline, channelSink, socket);
        try {
            this.f10024in = new PushbackInputStream(socket.getInputStream(), 1);
            try {
                this.out = socket.getOutputStream();
                Channels.fireChannelOpen(this);
                Channels.fireChannelBound(this, getLocalAddress());
            } catch (IOException e) {
                throw new ChannelException("Failed to obtain an OutputStream.", e);
            }
        } catch (IOException e2) {
            throw new ChannelException("Failed to obtain an InputStream.", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.jboss.netty.channel.socket.oio.OioSocketChannel
    public PushbackInputStream getInputStream() {
        return this.f10024in;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.jboss.netty.channel.socket.oio.OioSocketChannel
    public OutputStream getOutputStream() {
        return this.out;
    }
}
