package io.grpc.netty.shaded.io.netty.channel.epoll;

import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelMetadata;
import io.grpc.netty.shaded.io.netty.channel.ChannelOutboundBuffer;
import io.grpc.netty.shaded.io.netty.channel.ChannelPipeline;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.EventLoop;
import io.grpc.netty.shaded.io.netty.channel.ServerChannel;
import io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class AbstractEpollServerChannel extends AbstractEpollChannel implements ServerChannel {
    private static final ChannelMetadata METADATA = new ChannelMetadata(false, 16);

    abstract Channel newChildChannel(int i, byte[] bArr, int i2, int i3) throws Exception;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel, io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    public InetSocketAddress remoteAddress0() {
        return null;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public /* bridge */ /* synthetic */ boolean isActive() {
        return super.isActive();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public /* bridge */ /* synthetic */ boolean isOpen() {
        return super.isOpen();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractEpollServerChannel(int i) {
        this(new LinuxSocket(i), false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractEpollServerChannel(LinuxSocket linuxSocket) {
        this(linuxSocket, isSoErrorZero(linuxSocket));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractEpollServerChannel(LinuxSocket linuxSocket, boolean z) {
        super((Channel) null, linuxSocket, z);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public ChannelMetadata metadata() {
        return METADATA;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel, io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected boolean isCompatible(EventLoop eventLoop) {
        return eventLoop instanceof EpollEventLoop;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel, io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    public AbstractEpollChannel.AbstractEpollUnsafe newUnsafe() {
        return new EpollServerSocketUnsafe();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doWrite(ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected Object filterOutboundMessage(Object obj) throws Exception {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class EpollServerSocketUnsafe extends AbstractEpollChannel.AbstractEpollUnsafe {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final byte[] acceptedAddress;

        EpollServerSocketUnsafe() {
            super();
            this.acceptedAddress = new byte[26];
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel.AbstractEpollUnsafe, io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public void connect(SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) {
            channelPromise.setFailure((Throwable) new UnsupportedOperationException());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel.AbstractEpollUnsafe
        public void epollInReady() {
            EpollChannelConfig config = AbstractEpollServerChannel.this.config();
            if (AbstractEpollServerChannel.this.shouldBreakEpollInReady(config)) {
                clearEpollIn0();
                return;
            }
            EpollRecvByteAllocatorHandle recvBufAllocHandle = recvBufAllocHandle();
            recvBufAllocHandle.edgeTriggered(AbstractEpollServerChannel.this.isFlagSet(Native.EPOLLET));
            ChannelPipeline pipeline = AbstractEpollServerChannel.this.pipeline();
            recvBufAllocHandle.reset(config);
            recvBufAllocHandle.attemptedBytesRead(1);
            epollInBefore();
            Throwable th = null;
            do {
                try {
                    recvBufAllocHandle.lastBytesRead(AbstractEpollServerChannel.this.socket.accept(this.acceptedAddress));
                    if (recvBufAllocHandle.lastBytesRead() == -1) {
                        break;
                    }
                    recvBufAllocHandle.incMessagesRead(1);
                    this.readPending = false;
                    pipeline.fireChannelRead((Object) AbstractEpollServerChannel.this.newChildChannel(recvBufAllocHandle.lastBytesRead(), this.acceptedAddress, 1, this.acceptedAddress[0]));
                } catch (Throwable th2) {
                    th = th2;
                }
            } while (recvBufAllocHandle.continueReading());
            try {
                recvBufAllocHandle.readComplete();
                pipeline.fireChannelReadComplete();
                if (th != null) {
                    pipeline.fireExceptionCaught(th);
                }
            } finally {
                epollInFinally(config);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel
    public boolean doConnect(SocketAddress socketAddress, SocketAddress socketAddress2) throws Exception {
        throw new UnsupportedOperationException();
    }
}
