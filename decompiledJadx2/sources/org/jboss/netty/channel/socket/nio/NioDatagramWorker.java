package org.jboss.netty.channel.socket.nio;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Queue;
import java.util.concurrent.Executor;
import org.jboss.netty.buffer.ChannelBufferFactory;
import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.ReceiveBufferSizePredictor;
import org.jboss.netty.channel.socket.nio.SocketSendBufferPool;

/* loaded from: classes7.dex */
public class NioDatagramWorker extends AbstractNioWorker {
    static final /* synthetic */ boolean $assertionsDisabled = false;

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

    NioDatagramWorker(Executor executor) {
        super(executor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NioDatagramWorker(Executor executor, boolean z) {
        super(executor, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x005c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:6:0x003c  */
    @Override // org.jboss.netty.channel.socket.nio.AbstractNioWorker
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected boolean read(SelectionKey selectionKey) {
        SocketAddress socketAddress;
        boolean z;
        NioDatagramChannel nioDatagramChannel = (NioDatagramChannel) selectionKey.attachment();
        ReceiveBufferSizePredictor receiveBufferSizePredictor = nioDatagramChannel.getConfig().getReceiveBufferSizePredictor();
        ChannelBufferFactory bufferFactory = nioDatagramChannel.getConfig().getBufferFactory();
        DatagramChannel datagramChannel = (DatagramChannel) selectionKey.channel();
        ByteBuffer order = ByteBuffer.allocate(receiveBufferSizePredictor.nextReceiveBufferSize()).order(bufferFactory.getDefaultOrder());
        try {
            socketAddress = datagramChannel.receive(order);
            z = false;
        } catch (ClosedChannelException unused) {
            socketAddress = null;
            z = true;
            if (socketAddress != null) {
            }
            if (!z) {
            }
        } catch (Throwable th) {
            Channels.fireExceptionCaught(nioDatagramChannel, th);
            socketAddress = null;
            z = true;
            if (socketAddress != null) {
            }
            if (!z) {
            }
        }
        if (socketAddress != null) {
            order.flip();
            int remaining = order.remaining();
            if (remaining > 0) {
                receiveBufferSizePredictor.previousReceiveBufferSize(remaining);
                Channels.fireMessageReceived(nioDatagramChannel, bufferFactory.getBuffer(order), socketAddress);
            }
        }
        if (!z) {
            return true;
        }
        selectionKey.cancel();
        close(nioDatagramChannel, Channels.succeededFuture(nioDatagramChannel));
        return false;
    }

    @Override // org.jboss.netty.channel.socket.nio.AbstractNioWorker
    protected boolean scheduleWriteIfNecessary(AbstractNioChannel<?> abstractNioChannel) {
        Thread thread = this.thread;
        if (thread != null && Thread.currentThread() == thread) {
            return false;
        }
        if (abstractNioChannel.writeTaskInTaskQueue.compareAndSet(false, true)) {
            this.writeTaskQueue.offer(abstractNioChannel.writeTask);
        }
        Selector selector = this.selector;
        if (selector != null && this.wakenUp.compareAndSet(false, true)) {
            selector.wakeup();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void disconnect(NioDatagramChannel nioDatagramChannel, ChannelFuture channelFuture) {
        boolean isConnected = nioDatagramChannel.isConnected();
        boolean isIoThread = isIoThread(nioDatagramChannel);
        try {
            nioDatagramChannel.getDatagramChannel().disconnect();
            channelFuture.setSuccess();
            if (isConnected) {
                if (isIoThread) {
                    Channels.fireChannelDisconnected(nioDatagramChannel);
                } else {
                    Channels.fireChannelDisconnectedLater(nioDatagramChannel);
                }
            }
        } catch (Throwable th) {
            channelFuture.setFailure(th);
            if (isIoThread) {
                Channels.fireExceptionCaught(nioDatagramChannel, th);
            } else {
                Channels.fireExceptionCaughtLater(nioDatagramChannel, th);
            }
        }
    }

    @Override // org.jboss.netty.channel.socket.nio.AbstractNioWorker
    protected Runnable createRegisterTask(AbstractNioChannel<?> abstractNioChannel, ChannelFuture channelFuture) {
        return new ChannelRegistionTask((NioDatagramChannel) abstractNioChannel, channelFuture);
    }

    /* loaded from: classes7.dex */
    private final class ChannelRegistionTask implements Runnable {
        private final NioDatagramChannel channel;
        private final ChannelFuture future;

        ChannelRegistionTask(NioDatagramChannel nioDatagramChannel, ChannelFuture channelFuture) {
            this.channel = nioDatagramChannel;
            this.future = channelFuture;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.channel.getLocalAddress() == null) {
                ChannelFuture channelFuture = this.future;
                if (channelFuture != null) {
                    channelFuture.setFailure(new ClosedChannelException());
                }
                NioDatagramWorker nioDatagramWorker = NioDatagramWorker.this;
                NioDatagramChannel nioDatagramChannel = this.channel;
                nioDatagramWorker.close(nioDatagramChannel, Channels.succeededFuture(nioDatagramChannel));
                return;
            }
            try {
                synchronized (this.channel.interestOpsLock) {
                    this.channel.getDatagramChannel().register(NioDatagramWorker.this.selector, this.channel.getRawInterestOps(), this.channel);
                }
                if (this.future != null) {
                    this.future.setSuccess();
                }
            } catch (IOException e) {
                ChannelFuture channelFuture2 = this.future;
                if (channelFuture2 != null) {
                    channelFuture2.setFailure(e);
                }
                NioDatagramWorker nioDatagramWorker2 = NioDatagramWorker.this;
                NioDatagramChannel nioDatagramChannel2 = this.channel;
                nioDatagramWorker2.close(nioDatagramChannel2, Channels.succeededFuture(nioDatagramChannel2));
                if (!(e instanceof ClosedChannelException)) {
                    throw new ChannelException("Failed to register a socket to the selector.", e);
                }
            }
        }
    }

    @Override // org.jboss.netty.channel.socket.nio.AbstractNioWorker
    public void writeFromUserCode(AbstractNioChannel<?> abstractNioChannel) {
        if (!abstractNioChannel.isBound()) {
            cleanUpWriteBuffer(abstractNioChannel);
        } else {
            if (scheduleWriteIfNecessary(abstractNioChannel) || abstractNioChannel.writeSuspended || abstractNioChannel.inWriteNowLoop) {
                return;
            }
            write0(abstractNioChannel);
        }
    }

    @Override // org.jboss.netty.channel.socket.nio.AbstractNioWorker
    protected void write0(AbstractNioChannel<?> abstractNioChannel) {
        long j;
        SocketSendBufferPool.SendBuffer sendBuffer;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        long j2;
        ChannelFuture future;
        SocketSendBufferPool socketSendBufferPool = this.sendBufferPool;
        DatagramChannel datagramChannel = ((NioDatagramChannel) abstractNioChannel).getDatagramChannel();
        Queue<MessageEvent> queue = abstractNioChannel.writeBufferQueue;
        int writeSpinCount = abstractNioChannel.getConfig().getWriteSpinCount();
        synchronized (abstractNioChannel.writeLock) {
            boolean z5 = true;
            abstractNioChannel.inWriteNowLoop = true;
            boolean z6 = false;
            long j3 = 0;
            boolean z7 = false;
            j = 0;
            while (true) {
                MessageEvent messageEvent = abstractNioChannel.currentWriteEvent;
                if (messageEvent == null) {
                    messageEvent = queue.poll();
                    abstractNioChannel.currentWriteEvent = messageEvent;
                    if (messageEvent == null) {
                        abstractNioChannel.writeSuspended = z6;
                        z = z5;
                        z2 = z6;
                        z3 = z7;
                        break;
                    }
                    sendBuffer = socketSendBufferPool.acquire(messageEvent.getMessage());
                    abstractNioChannel.currentWriteBuffer = sendBuffer;
                } else {
                    sendBuffer = abstractNioChannel.currentWriteBuffer;
                }
                SocketSendBufferPool.SendBuffer sendBuffer2 = sendBuffer;
                MessageEvent messageEvent2 = messageEvent;
                try {
                    SocketAddress remoteAddress = messageEvent2.getRemoteAddress();
                    if (remoteAddress != null) {
                        int i = writeSpinCount;
                        j2 = j3;
                        while (true) {
                            if (i <= 0) {
                                break;
                            }
                            j2 = sendBuffer2.transferTo(datagramChannel, remoteAddress);
                            if (j2 != j3) {
                                j += j2;
                                break;
                            } else if (sendBuffer2.finished()) {
                                break;
                            } else {
                                i--;
                            }
                        }
                    } else {
                        int i2 = writeSpinCount;
                        long j4 = j3;
                        while (true) {
                            if (i2 <= 0) {
                                break;
                            }
                            j4 = sendBuffer2.transferTo(datagramChannel);
                            if (j4 != j3) {
                                j += j4;
                                break;
                            } else if (sendBuffer2.finished()) {
                                break;
                            } else {
                                i2--;
                            }
                        }
                        j2 = j4;
                    }
                } catch (AsynchronousCloseException unused) {
                    z3 = true;
                    z4 = false;
                } catch (Throwable th) {
                    th = th;
                    z3 = true;
                    z4 = false;
                }
                if (j2 > j3 || sendBuffer2.finished()) {
                    z3 = true;
                    z4 = false;
                    try {
                        try {
                            sendBuffer2.release();
                            future = messageEvent2.getFuture();
                            abstractNioChannel.currentWriteEvent = null;
                            abstractNioChannel.currentWriteBuffer = null;
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (AsynchronousCloseException unused2) {
                    }
                    try {
                        future.setSuccess();
                    } catch (Throwable th3) {
                        th = th3;
                        sendBuffer2 = null;
                        messageEvent2 = null;
                        sendBuffer2.release();
                        ChannelFuture future2 = messageEvent2.getFuture();
                        abstractNioChannel.currentWriteEvent = null;
                        abstractNioChannel.currentWriteBuffer = null;
                        future2.setFailure(th);
                        Channels.fireExceptionCaught(abstractNioChannel, th);
                        z5 = z3;
                        z6 = z4;
                        j3 = 0;
                    }
                    z5 = z3;
                    z6 = z4;
                    j3 = 0;
                } else {
                    z3 = true;
                    try {
                        abstractNioChannel.writeSuspended = true;
                        z2 = false;
                        z = false;
                        break;
                    } catch (AsynchronousCloseException unused3) {
                        z4 = false;
                        z7 = true;
                    } catch (Throwable th4) {
                        th = th4;
                        z4 = false;
                        z7 = true;
                        sendBuffer2.release();
                        ChannelFuture future22 = messageEvent2.getFuture();
                        abstractNioChannel.currentWriteEvent = null;
                        abstractNioChannel.currentWriteBuffer = null;
                        future22.setFailure(th);
                        Channels.fireExceptionCaught(abstractNioChannel, th);
                        z5 = z3;
                        z6 = z4;
                        j3 = 0;
                    }
                }
            }
            abstractNioChannel.inWriteNowLoop = z2;
            if (z3) {
                setOpWrite(abstractNioChannel);
            } else if (z) {
                clearOpWrite(abstractNioChannel);
            }
        }
        Channels.fireWriteComplete(abstractNioChannel, j);
    }
}
