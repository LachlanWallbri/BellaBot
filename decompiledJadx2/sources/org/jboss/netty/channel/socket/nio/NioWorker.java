package org.jboss.netty.channel.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Executor;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ReceiveBufferSizePredictor;

/* loaded from: classes7.dex */
public class NioWorker extends AbstractNioWorker {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final SocketReceiveBufferPool recvBufferPool;

    @Override // org.jboss.netty.channel.socket.nio.AbstractNioWorker, org.jboss.netty.channel.socket.Worker
    public /* bridge */ /* synthetic */ void executeInIoThread(Runnable runnable) {
        super.executeInIoThread(runnable);
    }

    @Override // org.jboss.netty.channel.socket.nio.AbstractNioWorker
    public /* bridge */ /* synthetic */ void executeInIoThread(Runnable runnable, boolean z) {
        super.executeInIoThread(runnable, z);
    }

    @Override // org.jboss.netty.channel.socket.nio.AbstractNioWorker, java.lang.Runnable
    public /* bridge */ /* synthetic */ void run() {
        super.run();
    }

    public NioWorker(Executor executor) {
        super(executor);
        this.recvBufferPool = new SocketReceiveBufferPool();
    }

    public NioWorker(Executor executor, boolean z) {
        super(executor, z);
        this.recvBufferPool = new SocketReceiveBufferPool();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0060 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0059  */
    @Override // org.jboss.netty.channel.socket.nio.AbstractNioWorker
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected boolean read(SelectionKey selectionKey) {
        boolean z;
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        NioSocketChannel nioSocketChannel = (NioSocketChannel) selectionKey.attachment();
        ReceiveBufferSizePredictor receiveBufferSizePredictor = nioSocketChannel.getConfig().getReceiveBufferSizePredictor();
        ByteBuffer acquire = this.recvBufferPool.acquire(receiveBufferSizePredictor.nextReceiveBufferSize());
        int i = 0;
        int i2 = 0;
        do {
            try {
                i2 = socketChannel.read(acquire);
                if (i2 <= 0) {
                    break;
                }
                i += i2;
            } catch (ClosedChannelException unused) {
                z = true;
                if (i > 0) {
                }
                if (i2 < 0) {
                }
                selectionKey.cancel();
                close(nioSocketChannel, Channels.succeededFuture(nioSocketChannel));
                return false;
            } catch (Throwable th) {
                Channels.fireExceptionCaught(nioSocketChannel, th);
                z = true;
                if (i > 0) {
                }
                if (i2 < 0) {
                }
                selectionKey.cancel();
                close(nioSocketChannel, Channels.succeededFuture(nioSocketChannel));
                return false;
            }
        } while (acquire.hasRemaining());
        z = false;
        if (i > 0) {
            acquire.flip();
            ChannelBuffer buffer = nioSocketChannel.getConfig().getBufferFactory().getBuffer(i);
            buffer.setBytes(0, acquire);
            buffer.writerIndex(i);
            this.recvBufferPool.release(acquire);
            receiveBufferSizePredictor.previousReceiveBufferSize(i);
            Channels.fireMessageReceived(nioSocketChannel, buffer);
        } else {
            this.recvBufferPool.release(acquire);
        }
        if (i2 < 0 && !z) {
            return true;
        }
        selectionKey.cancel();
        close(nioSocketChannel, Channels.succeededFuture(nioSocketChannel));
        return false;
    }

    @Override // org.jboss.netty.channel.socket.nio.AbstractNioWorker
    protected boolean scheduleWriteIfNecessary(AbstractNioChannel<?> abstractNioChannel) {
        Selector selector;
        Thread currentThread = Thread.currentThread();
        if (currentThread == this.thread) {
            return false;
        }
        if (abstractNioChannel.writeTaskInTaskQueue.compareAndSet(false, true)) {
            this.writeTaskQueue.offer(abstractNioChannel.writeTask);
        }
        if ((!(abstractNioChannel instanceof NioAcceptedSocketChannel) || ((NioAcceptedSocketChannel) abstractNioChannel).bossThread != currentThread) && (selector = this.selector) != null && this.wakenUp.compareAndSet(false, true)) {
            selector.wakeup();
        }
        return true;
    }

    @Override // org.jboss.netty.channel.socket.nio.AbstractNioWorker
    protected Runnable createRegisterTask(AbstractNioChannel<?> abstractNioChannel, ChannelFuture channelFuture) {
        return new RegisterTask((NioSocketChannel) abstractNioChannel, channelFuture, !(abstractNioChannel instanceof NioClientSocketChannel));
    }

    /* loaded from: classes7.dex */
    private final class RegisterTask implements Runnable {
        private final NioSocketChannel channel;
        private final ChannelFuture future;
        private final boolean server;

        RegisterTask(NioSocketChannel nioSocketChannel, ChannelFuture channelFuture, boolean z) {
            this.channel = nioSocketChannel;
            this.future = channelFuture;
            this.server = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            InetSocketAddress localAddress = this.channel.getLocalAddress();
            InetSocketAddress remoteAddress = this.channel.getRemoteAddress();
            if (localAddress == null || remoteAddress == null) {
                ChannelFuture channelFuture = this.future;
                if (channelFuture != null) {
                    channelFuture.setFailure(new ClosedChannelException());
                }
                NioWorker nioWorker = NioWorker.this;
                NioSocketChannel nioSocketChannel = this.channel;
                nioWorker.close(nioSocketChannel, Channels.succeededFuture(nioSocketChannel));
                return;
            }
            try {
                if (this.server) {
                    ((SocketChannel) this.channel.channel).configureBlocking(false);
                }
                synchronized (this.channel.interestOpsLock) {
                    ((SocketChannel) this.channel.channel).register(NioWorker.this.selector, this.channel.getRawInterestOps(), this.channel);
                }
                if (this.future != null) {
                    this.channel.setConnected();
                    this.future.setSuccess();
                }
                if (this.server || !((NioClientSocketChannel) this.channel).boundManually) {
                    Channels.fireChannelBound(this.channel, localAddress);
                }
                Channels.fireChannelConnected(this.channel, remoteAddress);
            } catch (IOException e) {
                ChannelFuture channelFuture2 = this.future;
                if (channelFuture2 != null) {
                    channelFuture2.setFailure(e);
                }
                NioWorker nioWorker2 = NioWorker.this;
                NioSocketChannel nioSocketChannel2 = this.channel;
                nioWorker2.close(nioSocketChannel2, Channels.succeededFuture(nioSocketChannel2));
                if (!(e instanceof ClosedChannelException)) {
                    throw new ChannelException("Failed to register a socket to the selector.", e);
                }
            }
        }
    }
}
