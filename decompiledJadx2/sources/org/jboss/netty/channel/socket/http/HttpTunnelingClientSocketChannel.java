package org.jboss.netty.channel.socket.http;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.NotYetConnectedException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.AbstractChannel;
import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelSink;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.DefaultChannelPipeline;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.socket.ClientSocketChannelFactory;
import org.jboss.netty.channel.socket.SocketChannel;
import org.jboss.netty.handler.codec.http.DefaultHttpChunk;
import org.jboss.netty.handler.codec.http.DefaultHttpRequest;
import org.jboss.netty.handler.codec.http.HttpChunk;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.jboss.netty.handler.codec.http.HttpRequestEncoder;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseDecoder;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;
import org.jboss.netty.handler.ssl.SslHandler;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public class HttpTunnelingClientSocketChannel extends AbstractChannel implements SocketChannel {
    final HttpTunnelingSocketChannelConfig config;
    private final ServletChannelHandler handler;
    final Object interestOpsLock;
    final SocketChannel realChannel;
    volatile boolean requestHeaderWritten;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpTunnelingClientSocketChannel(ChannelFactory channelFactory, ChannelPipeline channelPipeline, ChannelSink channelSink, ClientSocketChannelFactory clientSocketChannelFactory) {
        super(null, channelFactory, channelPipeline, channelSink);
        this.interestOpsLock = new Object();
        this.handler = new ServletChannelHandler();
        this.config = new HttpTunnelingSocketChannelConfig(this);
        DefaultChannelPipeline defaultChannelPipeline = new DefaultChannelPipeline();
        defaultChannelPipeline.addLast("decoder", new HttpResponseDecoder());
        defaultChannelPipeline.addLast("encoder", new HttpRequestEncoder());
        defaultChannelPipeline.addLast("handler", this.handler);
        this.realChannel = clientSocketChannelFactory.newChannel((ChannelPipeline) defaultChannelPipeline);
        Channels.fireChannelOpen(this);
    }

    @Override // org.jboss.netty.channel.Channel
    public HttpTunnelingSocketChannelConfig getConfig() {
        return this.config;
    }

    @Override // org.jboss.netty.channel.Channel
    public InetSocketAddress getLocalAddress() {
        return this.realChannel.getLocalAddress();
    }

    @Override // org.jboss.netty.channel.Channel
    public InetSocketAddress getRemoteAddress() {
        return this.realChannel.getRemoteAddress();
    }

    @Override // org.jboss.netty.channel.Channel
    public boolean isBound() {
        return this.realChannel.isBound();
    }

    @Override // org.jboss.netty.channel.Channel
    public boolean isConnected() {
        return this.realChannel.isConnected();
    }

    @Override // org.jboss.netty.channel.AbstractChannel, org.jboss.netty.channel.Channel
    public int getInterestOps() {
        return this.realChannel.getInterestOps();
    }

    @Override // org.jboss.netty.channel.AbstractChannel, org.jboss.netty.channel.Channel
    public boolean isWritable() {
        return this.realChannel.isWritable();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jboss.netty.channel.AbstractChannel
    public boolean setClosed() {
        return super.setClosed();
    }

    @Override // org.jboss.netty.channel.AbstractChannel, org.jboss.netty.channel.Channel
    public ChannelFuture write(Object obj, SocketAddress socketAddress) {
        if (socketAddress == null || socketAddress.equals(getRemoteAddress())) {
            return super.write(obj, null);
        }
        return getUnsupportedOperationFuture();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void bindReal(SocketAddress socketAddress, final ChannelFuture channelFuture) {
        this.realChannel.bind(socketAddress).addListener(new ChannelFutureListener() { // from class: org.jboss.netty.channel.socket.http.HttpTunnelingClientSocketChannel.1
            @Override // org.jboss.netty.channel.ChannelFutureListener
            public void operationComplete(ChannelFuture channelFuture2) {
                if (channelFuture2.isSuccess()) {
                    channelFuture.setSuccess();
                } else {
                    channelFuture.setFailure(channelFuture2.getCause());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void connectReal(final SocketAddress socketAddress, final ChannelFuture channelFuture) {
        this.realChannel.connect(socketAddress).addListener(new ChannelFutureListener() { // from class: org.jboss.netty.channel.socket.http.HttpTunnelingClientSocketChannel.2
            @Override // org.jboss.netty.channel.ChannelFutureListener
            public void operationComplete(ChannelFuture channelFuture2) {
                SSLEngine createSSLEngine;
                String serverName = HttpTunnelingClientSocketChannel.this.config.getServerName();
                int port = ((InetSocketAddress) socketAddress).getPort();
                String serverPath = HttpTunnelingClientSocketChannel.this.config.getServerPath();
                if (channelFuture2.isSuccess()) {
                    SSLContext sslContext = HttpTunnelingClientSocketChannel.this.config.getSslContext();
                    ChannelFuture channelFuture3 = null;
                    if (sslContext != null) {
                        if (serverName != null) {
                            createSSLEngine = sslContext.createSSLEngine(serverName, port);
                        } else {
                            createSSLEngine = sslContext.createSSLEngine();
                        }
                        createSSLEngine.setUseClientMode(true);
                        createSSLEngine.setEnableSessionCreation(HttpTunnelingClientSocketChannel.this.config.isEnableSslSessionCreation());
                        String[] enabledSslCipherSuites = HttpTunnelingClientSocketChannel.this.config.getEnabledSslCipherSuites();
                        if (enabledSslCipherSuites != null) {
                            createSSLEngine.setEnabledCipherSuites(enabledSslCipherSuites);
                        }
                        String[] enabledSslProtocols = HttpTunnelingClientSocketChannel.this.config.getEnabledSslProtocols();
                        if (enabledSslProtocols != null) {
                            createSSLEngine.setEnabledProtocols(enabledSslProtocols);
                        }
                        SslHandler sslHandler = new SslHandler(createSSLEngine);
                        HttpTunnelingClientSocketChannel.this.realChannel.getPipeline().addFirst("ssl", sslHandler);
                        channelFuture3 = sslHandler.handshake();
                    }
                    final DefaultHttpRequest defaultHttpRequest = new DefaultHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, serverPath);
                    if (serverName != null) {
                        defaultHttpRequest.setHeader("Host", serverName);
                    }
                    defaultHttpRequest.setHeader("Content-Type", "application/octet-stream");
                    defaultHttpRequest.setHeader("Transfer-Encoding", "chunked");
                    defaultHttpRequest.setHeader("Content-Transfer-Encoding", "binary");
                    defaultHttpRequest.setHeader("User-Agent", HttpTunnelingClientSocketChannel.class.getName());
                    if (channelFuture3 == null) {
                        HttpTunnelingClientSocketChannel.this.realChannel.write(defaultHttpRequest);
                        HttpTunnelingClientSocketChannel.this.requestHeaderWritten = true;
                        channelFuture.setSuccess();
                        Channels.fireChannelConnected(this, socketAddress);
                        return;
                    }
                    channelFuture3.addListener(new ChannelFutureListener() { // from class: org.jboss.netty.channel.socket.http.HttpTunnelingClientSocketChannel.2.1
                        @Override // org.jboss.netty.channel.ChannelFutureListener
                        public void operationComplete(ChannelFuture channelFuture4) {
                            if (channelFuture4.isSuccess()) {
                                HttpTunnelingClientSocketChannel.this.realChannel.write(defaultHttpRequest);
                                HttpTunnelingClientSocketChannel.this.requestHeaderWritten = true;
                                channelFuture.setSuccess();
                                Channels.fireChannelConnected(this, socketAddress);
                                return;
                            }
                            channelFuture.setFailure(channelFuture4.getCause());
                            Channels.fireExceptionCaught(this, channelFuture4.getCause());
                        }
                    });
                    return;
                }
                channelFuture.setFailure(channelFuture2.getCause());
                Channels.fireExceptionCaught(this, channelFuture2.getCause());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeReal(ChannelBuffer channelBuffer, final ChannelFuture channelFuture) {
        ChannelFuture write;
        if (!this.requestHeaderWritten) {
            throw new NotYetConnectedException();
        }
        final int readableBytes = channelBuffer.readableBytes();
        if (readableBytes == 0) {
            write = this.realChannel.write(ChannelBuffers.EMPTY_BUFFER);
        } else {
            write = this.realChannel.write(new DefaultHttpChunk(channelBuffer));
        }
        write.addListener(new ChannelFutureListener() { // from class: org.jboss.netty.channel.socket.http.HttpTunnelingClientSocketChannel.3
            @Override // org.jboss.netty.channel.ChannelFutureListener
            public void operationComplete(ChannelFuture channelFuture2) {
                if (channelFuture2.isSuccess()) {
                    channelFuture.setSuccess();
                    int i = readableBytes;
                    if (i != 0) {
                        Channels.fireWriteComplete(HttpTunnelingClientSocketChannel.this, i);
                        return;
                    }
                    return;
                }
                channelFuture.setFailure(channelFuture2.getCause());
            }
        });
    }

    private ChannelFuture writeLastChunk() {
        if (!this.requestHeaderWritten) {
            return Channels.failedFuture(this, new NotYetConnectedException());
        }
        return this.realChannel.write(HttpChunk.LAST_CHUNK);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setInterestOpsReal(int i, final ChannelFuture channelFuture) {
        this.realChannel.setInterestOps(i).addListener(new ChannelFutureListener() { // from class: org.jboss.netty.channel.socket.http.HttpTunnelingClientSocketChannel.4
            @Override // org.jboss.netty.channel.ChannelFutureListener
            public void operationComplete(ChannelFuture channelFuture2) {
                if (channelFuture2.isSuccess()) {
                    channelFuture.setSuccess();
                } else {
                    channelFuture.setFailure(channelFuture2.getCause());
                }
            }
        });
    }

    void disconnectReal(final ChannelFuture channelFuture) {
        writeLastChunk().addListener(new ChannelFutureListener() { // from class: org.jboss.netty.channel.socket.http.HttpTunnelingClientSocketChannel.5
            @Override // org.jboss.netty.channel.ChannelFutureListener
            public void operationComplete(ChannelFuture channelFuture2) {
                HttpTunnelingClientSocketChannel.this.realChannel.disconnect().addListener(new ChannelFutureListener() { // from class: org.jboss.netty.channel.socket.http.HttpTunnelingClientSocketChannel.5.1
                    @Override // org.jboss.netty.channel.ChannelFutureListener
                    public void operationComplete(ChannelFuture channelFuture3) {
                        if (channelFuture3.isSuccess()) {
                            channelFuture.setSuccess();
                        } else {
                            channelFuture.setFailure(channelFuture3.getCause());
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void unbindReal(final ChannelFuture channelFuture) {
        writeLastChunk().addListener(new ChannelFutureListener() { // from class: org.jboss.netty.channel.socket.http.HttpTunnelingClientSocketChannel.6
            @Override // org.jboss.netty.channel.ChannelFutureListener
            public void operationComplete(ChannelFuture channelFuture2) {
                HttpTunnelingClientSocketChannel.this.realChannel.unbind().addListener(new ChannelFutureListener() { // from class: org.jboss.netty.channel.socket.http.HttpTunnelingClientSocketChannel.6.1
                    @Override // org.jboss.netty.channel.ChannelFutureListener
                    public void operationComplete(ChannelFuture channelFuture3) {
                        if (channelFuture3.isSuccess()) {
                            channelFuture.setSuccess();
                        } else {
                            channelFuture.setFailure(channelFuture3.getCause());
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void closeReal(final ChannelFuture channelFuture) {
        writeLastChunk().addListener(new ChannelFutureListener() { // from class: org.jboss.netty.channel.socket.http.HttpTunnelingClientSocketChannel.7
            @Override // org.jboss.netty.channel.ChannelFutureListener
            public void operationComplete(ChannelFuture channelFuture2) {
                HttpTunnelingClientSocketChannel.this.realChannel.close().addListener(new ChannelFutureListener() { // from class: org.jboss.netty.channel.socket.http.HttpTunnelingClientSocketChannel.7.1
                    @Override // org.jboss.netty.channel.ChannelFutureListener
                    public void operationComplete(ChannelFuture channelFuture3) {
                        if (channelFuture3.isSuccess()) {
                            channelFuture.setSuccess();
                        } else {
                            channelFuture.setFailure(channelFuture3.getCause());
                        }
                        HttpTunnelingClientSocketChannel.this.setClosed();
                    }
                });
            }
        });
    }

    /* loaded from: classes7.dex */
    final class ServletChannelHandler extends SimpleChannelUpstreamHandler {
        private volatile boolean readingChunks;
        final SocketChannel virtualChannel;

        ServletChannelHandler() {
            this.virtualChannel = HttpTunnelingClientSocketChannel.this;
        }

        @Override // org.jboss.netty.channel.SimpleChannelUpstreamHandler
        public void channelBound(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) throws Exception {
            Channels.fireChannelBound(this.virtualChannel, (SocketAddress) channelStateEvent.getValue());
        }

        @Override // org.jboss.netty.channel.SimpleChannelUpstreamHandler
        public void messageReceived(ChannelHandlerContext channelHandlerContext, MessageEvent messageEvent) throws Exception {
            if (!this.readingChunks) {
                HttpResponse httpResponse = (HttpResponse) messageEvent.getMessage();
                if (httpResponse.getStatus().getCode() != HttpResponseStatus.f10034OK.getCode()) {
                    throw new ChannelException("Unexpected HTTP response status: " + httpResponse.getStatus());
                }
                if (httpResponse.isChunked()) {
                    this.readingChunks = true;
                    return;
                }
                ChannelBuffer content = httpResponse.getContent();
                if (content.readable()) {
                    Channels.fireMessageReceived(HttpTunnelingClientSocketChannel.this, content);
                }
                HttpTunnelingClientSocketChannel.this.closeReal(Channels.succeededFuture(this.virtualChannel));
                return;
            }
            HttpChunk httpChunk = (HttpChunk) messageEvent.getMessage();
            if (!httpChunk.isLast()) {
                Channels.fireMessageReceived(HttpTunnelingClientSocketChannel.this, httpChunk.getContent());
            } else {
                this.readingChunks = false;
                HttpTunnelingClientSocketChannel.this.closeReal(Channels.succeededFuture(this.virtualChannel));
            }
        }

        @Override // org.jboss.netty.channel.SimpleChannelUpstreamHandler
        public void channelInterestChanged(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) throws Exception {
            Channels.fireChannelInterestChanged(this.virtualChannel);
        }

        @Override // org.jboss.netty.channel.SimpleChannelUpstreamHandler
        public void channelDisconnected(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) throws Exception {
            Channels.fireChannelDisconnected(this.virtualChannel);
        }

        @Override // org.jboss.netty.channel.SimpleChannelUpstreamHandler
        public void channelUnbound(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) throws Exception {
            Channels.fireChannelUnbound(this.virtualChannel);
        }

        @Override // org.jboss.netty.channel.SimpleChannelUpstreamHandler
        public void channelClosed(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) throws Exception {
            Channels.fireChannelClosed(this.virtualChannel);
        }

        @Override // org.jboss.netty.channel.SimpleChannelUpstreamHandler
        public void exceptionCaught(ChannelHandlerContext channelHandlerContext, ExceptionEvent exceptionEvent) throws Exception {
            Channels.fireExceptionCaught(this.virtualChannel, exceptionEvent.getCause());
            HttpTunnelingClientSocketChannel.this.realChannel.close();
        }
    }
}
