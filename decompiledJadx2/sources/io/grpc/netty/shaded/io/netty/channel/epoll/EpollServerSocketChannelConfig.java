package io.grpc.netty.shaded.io.netty.channel.epoll;

import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.ChannelException;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
import io.grpc.netty.shaded.io.netty.channel.MessageSizeEstimator;
import io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.WriteBufferWaterMark;
import io.grpc.netty.shaded.io.netty.channel.socket.ServerSocketChannelConfig;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class EpollServerSocketChannelConfig extends EpollServerChannelConfig implements ServerSocketChannelConfig {
    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.EpollServerChannelConfig, io.grpc.netty.shaded.io.netty.channel.socket.ServerSocketChannelConfig
    public EpollServerSocketChannelConfig setPerformancePreferences(int i, int i2, int i3) {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EpollServerSocketChannelConfig(EpollServerSocketChannel epollServerSocketChannel) {
        super(epollServerSocketChannel);
        setReuseAddress(true);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.EpollServerChannelConfig, io.grpc.netty.shaded.io.netty.channel.epoll.EpollChannelConfig, io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    public Map<ChannelOption<?>, Object> getOptions() {
        return getOptions(super.getOptions(), EpollChannelOption.SO_REUSEPORT, EpollChannelOption.IP_FREEBIND, EpollChannelOption.IP_TRANSPARENT, EpollChannelOption.TCP_DEFER_ACCEPT);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.EpollServerChannelConfig, io.grpc.netty.shaded.io.netty.channel.epoll.EpollChannelConfig, io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    public <T> T getOption(ChannelOption<T> channelOption) {
        if (channelOption == EpollChannelOption.SO_REUSEPORT) {
            return (T) Boolean.valueOf(isReusePort());
        }
        if (channelOption == EpollChannelOption.IP_FREEBIND) {
            return (T) Boolean.valueOf(isFreeBind());
        }
        if (channelOption == EpollChannelOption.IP_TRANSPARENT) {
            return (T) Boolean.valueOf(isIpTransparent());
        }
        if (channelOption == EpollChannelOption.TCP_DEFER_ACCEPT) {
            return (T) Integer.valueOf(getTcpDeferAccept());
        }
        return (T) super.getOption(channelOption);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.EpollServerChannelConfig, io.grpc.netty.shaded.io.netty.channel.epoll.EpollChannelConfig, io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    public <T> boolean setOption(ChannelOption<T> channelOption, T t) {
        validate(channelOption, t);
        if (channelOption == EpollChannelOption.SO_REUSEPORT) {
            setReusePort(((Boolean) t).booleanValue());
            return true;
        }
        if (channelOption == EpollChannelOption.IP_FREEBIND) {
            setFreeBind(((Boolean) t).booleanValue());
            return true;
        }
        if (channelOption == EpollChannelOption.IP_TRANSPARENT) {
            setIpTransparent(((Boolean) t).booleanValue());
            return true;
        }
        if (channelOption == EpollChannelOption.TCP_MD5SIG) {
            setTcpMd5Sig((Map) t);
            return true;
        }
        if (channelOption == EpollChannelOption.TCP_DEFER_ACCEPT) {
            setTcpDeferAccept(((Integer) t).intValue());
            return true;
        }
        return super.setOption(channelOption, t);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.EpollServerChannelConfig, io.grpc.netty.shaded.io.netty.channel.socket.ServerSocketChannelConfig
    public EpollServerSocketChannelConfig setReuseAddress(boolean z) {
        super.setReuseAddress(z);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.EpollServerChannelConfig, io.grpc.netty.shaded.io.netty.channel.socket.ServerSocketChannelConfig
    public EpollServerSocketChannelConfig setReceiveBufferSize(int i) {
        super.setReceiveBufferSize(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.EpollServerChannelConfig, io.grpc.netty.shaded.io.netty.channel.socket.ServerSocketChannelConfig
    public EpollServerSocketChannelConfig setBacklog(int i) {
        super.setBacklog(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.EpollServerChannelConfig, io.grpc.netty.shaded.io.netty.channel.epoll.EpollChannelConfig, io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    public EpollServerSocketChannelConfig setConnectTimeoutMillis(int i) {
        super.setConnectTimeoutMillis(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.EpollServerChannelConfig, io.grpc.netty.shaded.io.netty.channel.epoll.EpollChannelConfig, io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    @Deprecated
    public EpollServerSocketChannelConfig setMaxMessagesPerRead(int i) {
        super.setMaxMessagesPerRead(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.EpollServerChannelConfig, io.grpc.netty.shaded.io.netty.channel.epoll.EpollChannelConfig, io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    public EpollServerSocketChannelConfig setWriteSpinCount(int i) {
        super.setWriteSpinCount(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.EpollServerChannelConfig, io.grpc.netty.shaded.io.netty.channel.epoll.EpollChannelConfig, io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    public EpollServerSocketChannelConfig setAllocator(ByteBufAllocator byteBufAllocator) {
        super.setAllocator(byteBufAllocator);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.EpollServerChannelConfig, io.grpc.netty.shaded.io.netty.channel.epoll.EpollChannelConfig, io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    public EpollServerSocketChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator recvByteBufAllocator) {
        super.setRecvByteBufAllocator(recvByteBufAllocator);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.EpollServerChannelConfig, io.grpc.netty.shaded.io.netty.channel.epoll.EpollChannelConfig, io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    public EpollServerSocketChannelConfig setAutoRead(boolean z) {
        super.setAutoRead(z);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.EpollServerChannelConfig, io.grpc.netty.shaded.io.netty.channel.epoll.EpollChannelConfig, io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    @Deprecated
    public EpollServerSocketChannelConfig setWriteBufferHighWaterMark(int i) {
        super.setWriteBufferHighWaterMark(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.EpollServerChannelConfig, io.grpc.netty.shaded.io.netty.channel.epoll.EpollChannelConfig, io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    @Deprecated
    public EpollServerSocketChannelConfig setWriteBufferLowWaterMark(int i) {
        super.setWriteBufferLowWaterMark(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.EpollServerChannelConfig, io.grpc.netty.shaded.io.netty.channel.epoll.EpollChannelConfig, io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    public EpollServerSocketChannelConfig setWriteBufferWaterMark(WriteBufferWaterMark writeBufferWaterMark) {
        super.setWriteBufferWaterMark(writeBufferWaterMark);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.EpollServerChannelConfig, io.grpc.netty.shaded.io.netty.channel.epoll.EpollChannelConfig, io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    public EpollServerSocketChannelConfig setMessageSizeEstimator(MessageSizeEstimator messageSizeEstimator) {
        super.setMessageSizeEstimator(messageSizeEstimator);
        return this;
    }

    public EpollServerSocketChannelConfig setTcpMd5Sig(Map<InetAddress, byte[]> map) {
        try {
            ((EpollServerSocketChannel) this.channel).setTcpMd5Sig(map);
            return this;
        } catch (IOException e) {
            throw new ChannelException(e);
        }
    }

    public boolean isReusePort() {
        try {
            return ((EpollServerSocketChannel) this.channel).socket.isReusePort();
        } catch (IOException e) {
            throw new ChannelException(e);
        }
    }

    public EpollServerSocketChannelConfig setReusePort(boolean z) {
        try {
            ((EpollServerSocketChannel) this.channel).socket.setReusePort(z);
            return this;
        } catch (IOException e) {
            throw new ChannelException(e);
        }
    }

    public boolean isFreeBind() {
        try {
            return ((EpollServerSocketChannel) this.channel).socket.isIpFreeBind();
        } catch (IOException e) {
            throw new ChannelException(e);
        }
    }

    public EpollServerSocketChannelConfig setFreeBind(boolean z) {
        try {
            ((EpollServerSocketChannel) this.channel).socket.setIpFreeBind(z);
            return this;
        } catch (IOException e) {
            throw new ChannelException(e);
        }
    }

    public boolean isIpTransparent() {
        try {
            return ((EpollServerSocketChannel) this.channel).socket.isIpTransparent();
        } catch (IOException e) {
            throw new ChannelException(e);
        }
    }

    public EpollServerSocketChannelConfig setIpTransparent(boolean z) {
        try {
            ((EpollServerSocketChannel) this.channel).socket.setIpTransparent(z);
            return this;
        } catch (IOException e) {
            throw new ChannelException(e);
        }
    }

    public EpollServerSocketChannelConfig setTcpDeferAccept(int i) {
        try {
            ((EpollServerSocketChannel) this.channel).socket.setTcpDeferAccept(i);
            return this;
        } catch (IOException e) {
            throw new ChannelException(e);
        }
    }

    public int getTcpDeferAccept() {
        try {
            return ((EpollServerSocketChannel) this.channel).socket.getTcpDeferAccept();
        } catch (IOException e) {
            throw new ChannelException(e);
        }
    }
}
