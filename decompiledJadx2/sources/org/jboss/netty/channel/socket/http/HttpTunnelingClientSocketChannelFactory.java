package org.jboss.netty.channel.socket.http;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelSink;
import org.jboss.netty.channel.socket.ClientSocketChannelFactory;
import org.jboss.netty.channel.socket.SocketChannel;

/* loaded from: classes7.dex */
public class HttpTunnelingClientSocketChannelFactory implements ClientSocketChannelFactory {
    private final ClientSocketChannelFactory clientSocketChannelFactory;
    private final ChannelSink sink = new HttpTunnelingClientSocketPipelineSink();

    public HttpTunnelingClientSocketChannelFactory(ClientSocketChannelFactory clientSocketChannelFactory) {
        this.clientSocketChannelFactory = clientSocketChannelFactory;
    }

    @Override // org.jboss.netty.channel.ChannelFactory
    public SocketChannel newChannel(ChannelPipeline channelPipeline) {
        return new HttpTunnelingClientSocketChannel(this, channelPipeline, this.sink, this.clientSocketChannelFactory);
    }

    @Override // org.jboss.netty.channel.ChannelFactory, org.jboss.netty.util.ExternalResourceReleasable
    public void releaseExternalResources() {
        this.clientSocketChannelFactory.releaseExternalResources();
    }
}
