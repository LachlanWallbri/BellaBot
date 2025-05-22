package io.grpc.netty.shaded.io.netty.channel.socket.nio;

import io.grpc.netty.shaded.io.netty.channel.ChannelException;
import io.grpc.netty.shaded.io.netty.channel.ChannelMetadata;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
import io.grpc.netty.shaded.io.netty.channel.ChannelOutboundBuffer;
import io.grpc.netty.shaded.io.netty.channel.nio.AbstractNioMessageChannel;
import io.grpc.netty.shaded.io.netty.channel.socket.DefaultServerSocketChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.socket.ServerSocketChannel;
import io.grpc.netty.shaded.io.netty.channel.socket.ServerSocketChannelConfig;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.SocketUtils;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class NioServerSocketChannel extends AbstractNioMessageChannel implements ServerSocketChannel {
    private final ServerSocketChannelConfig config;
    private static final ChannelMetadata METADATA = new ChannelMetadata(false, 16);
    private static final SelectorProvider DEFAULT_SELECTOR_PROVIDER = SelectorProvider.provider();
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) NioServerSocketChannel.class);

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public InetSocketAddress remoteAddress() {
        return null;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected SocketAddress remoteAddress0() {
        return null;
    }

    private static java.nio.channels.ServerSocketChannel newSocket(SelectorProvider selectorProvider) {
        try {
            return selectorProvider.openServerSocketChannel();
        } catch (IOException e) {
            throw new ChannelException("Failed to open a server socket.", e);
        }
    }

    public NioServerSocketChannel() {
        this(newSocket(DEFAULT_SELECTOR_PROVIDER));
    }

    public NioServerSocketChannel(SelectorProvider selectorProvider) {
        this(newSocket(selectorProvider));
    }

    public NioServerSocketChannel(java.nio.channels.ServerSocketChannel serverSocketChannel) {
        super(null, serverSocketChannel, 16);
        this.config = new NioServerSocketChannelConfig(this, javaChannel().socket());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public InetSocketAddress localAddress() {
        return (InetSocketAddress) super.localAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public ChannelMetadata metadata() {
        return METADATA;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public ServerSocketChannelConfig config() {
        return this.config;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public boolean isActive() {
        return isOpen() && javaChannel().socket().isBound();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.channel.nio.AbstractNioChannel
    public java.nio.channels.ServerSocketChannel javaChannel() {
        return (java.nio.channels.ServerSocketChannel) super.javaChannel();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected SocketAddress localAddress0() {
        return SocketUtils.localSocketAddress(javaChannel().socket());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doBind(SocketAddress socketAddress) throws Exception {
        if (PlatformDependent.javaVersion() >= 7) {
            javaChannel().bind(socketAddress, this.config.getBacklog());
        } else {
            javaChannel().socket().bind(socketAddress, this.config.getBacklog());
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.nio.AbstractNioChannel, io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doClose() throws Exception {
        javaChannel().close();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.nio.AbstractNioMessageChannel
    protected int doReadMessages(List<Object> list) throws Exception {
        SocketChannel accept = SocketUtils.accept(javaChannel());
        if (accept == null) {
            return 0;
        }
        try {
            list.add(new NioSocketChannel(this, accept));
            return 1;
        } catch (Throwable th) {
            logger.warn("Failed to create a new channel from an accepted socket.", th);
            try {
                accept.close();
                return 0;
            } catch (Throwable th2) {
                logger.warn("Failed to close a socket.", th2);
                return 0;
            }
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.nio.AbstractNioChannel
    protected boolean doConnect(SocketAddress socketAddress, SocketAddress socketAddress2) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.nio.AbstractNioChannel
    protected void doFinishConnect() throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doDisconnect() throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.nio.AbstractNioMessageChannel
    protected boolean doWriteMessage(Object obj, ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected final Object filterOutboundMessage(Object obj) throws Exception {
        throw new UnsupportedOperationException();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private final class NioServerSocketChannelConfig extends DefaultServerSocketChannelConfig {
        private NioServerSocketChannelConfig(NioServerSocketChannel nioServerSocketChannel, ServerSocket serverSocket) {
            super(nioServerSocketChannel, serverSocket);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig
        protected void autoReadCleared() {
            NioServerSocketChannel.this.clearReadPending();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.socket.DefaultServerSocketChannelConfig, io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
        public <T> boolean setOption(ChannelOption<T> channelOption, T t) {
            if (PlatformDependent.javaVersion() >= 7 && (channelOption instanceof NioChannelOption)) {
                return NioChannelOption.setOption(jdkChannel(), (NioChannelOption) channelOption, t);
            }
            return super.setOption(channelOption, t);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.socket.DefaultServerSocketChannelConfig, io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
        public <T> T getOption(ChannelOption<T> channelOption) {
            if (PlatformDependent.javaVersion() >= 7 && (channelOption instanceof NioChannelOption)) {
                return (T) NioChannelOption.getOption(jdkChannel(), (NioChannelOption) channelOption);
            }
            return (T) super.getOption(channelOption);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.socket.DefaultServerSocketChannelConfig, io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
        public Map<ChannelOption<?>, Object> getOptions() {
            if (PlatformDependent.javaVersion() >= 7) {
                return getOptions(super.getOptions(), NioChannelOption.getOptions(jdkChannel()));
            }
            return super.getOptions();
        }

        private java.nio.channels.ServerSocketChannel jdkChannel() {
            return ((NioServerSocketChannel) this.channel).javaChannel();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.nio.AbstractNioMessageChannel
    protected boolean closeOnReadError(Throwable th) {
        return super.closeOnReadError(th);
    }
}
