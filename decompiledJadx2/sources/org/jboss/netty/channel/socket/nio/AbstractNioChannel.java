package org.jboss.netty.channel.socket.nio;

import java.net.InetSocketAddress;
import java.nio.channels.SelectableChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.AbstractChannel;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelSink;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.socket.nio.SocketSendBufferPool;
import org.jboss.netty.util.internal.QueueFactory;
import org.jboss.netty.util.internal.ThreadLocalBoolean;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public abstract class AbstractNioChannel<C extends SelectableChannel & WritableByteChannel> extends AbstractChannel {
    final C channel;
    SocketSendBufferPool.SendBuffer currentWriteBuffer;
    MessageEvent currentWriteEvent;
    final AtomicInteger highWaterMarkCounter;
    boolean inWriteNowLoop;
    final Object interestOpsLock;
    private volatile InetSocketAddress localAddress;
    volatile InetSocketAddress remoteAddress;
    final AbstractNioWorker worker;
    final Queue<MessageEvent> writeBufferQueue;
    final AtomicInteger writeBufferSize;
    final Object writeLock;
    boolean writeSuspended;
    final Runnable writeTask;
    final AtomicBoolean writeTaskInTaskQueue;

    @Override // org.jboss.netty.channel.Channel
    public abstract NioChannelConfig getConfig();

    abstract InetSocketAddress getLocalSocketAddress() throws Exception;

    abstract InetSocketAddress getRemoteSocketAddress() throws Exception;

    protected AbstractNioChannel(Integer num, Channel channel, ChannelFactory channelFactory, ChannelPipeline channelPipeline, ChannelSink channelSink, AbstractNioWorker abstractNioWorker, C c) {
        super(num, channel, channelFactory, channelPipeline, channelSink);
        this.interestOpsLock = new Object();
        this.writeLock = new Object();
        this.writeTask = new WriteTask();
        this.writeTaskInTaskQueue = new AtomicBoolean();
        this.writeBufferQueue = new WriteRequestQueue();
        this.writeBufferSize = new AtomicInteger();
        this.highWaterMarkCounter = new AtomicInteger();
        this.worker = abstractNioWorker;
        this.channel = c;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractNioChannel(Channel channel, ChannelFactory channelFactory, ChannelPipeline channelPipeline, ChannelSink channelSink, AbstractNioWorker abstractNioWorker, C c) {
        super(channel, channelFactory, channelPipeline, channelSink);
        this.interestOpsLock = new Object();
        this.writeLock = new Object();
        this.writeTask = new WriteTask();
        this.writeTaskInTaskQueue = new AtomicBoolean();
        this.writeBufferQueue = new WriteRequestQueue();
        this.writeBufferSize = new AtomicInteger();
        this.highWaterMarkCounter = new AtomicInteger();
        this.worker = abstractNioWorker;
        this.channel = c;
    }

    public AbstractNioWorker getWorker() {
        return this.worker;
    }

    @Override // org.jboss.netty.channel.Channel
    public InetSocketAddress getLocalAddress() {
        InetSocketAddress inetSocketAddress = this.localAddress;
        if (inetSocketAddress != null) {
            return inetSocketAddress;
        }
        try {
            InetSocketAddress localSocketAddress = getLocalSocketAddress();
            this.localAddress = localSocketAddress;
            return localSocketAddress;
        } catch (Throwable unused) {
            return null;
        }
    }

    @Override // org.jboss.netty.channel.Channel
    public InetSocketAddress getRemoteAddress() {
        InetSocketAddress inetSocketAddress = this.remoteAddress;
        if (inetSocketAddress != null) {
            return inetSocketAddress;
        }
        try {
            InetSocketAddress remoteSocketAddress = getRemoteSocketAddress();
            this.remoteAddress = remoteSocketAddress;
            return remoteSocketAddress;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getRawInterestOps() {
        return super.getInterestOps();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRawInterestOpsNow(int i) {
        super.setInterestOpsNow(i);
    }

    @Override // org.jboss.netty.channel.AbstractChannel, org.jboss.netty.channel.Channel
    public int getInterestOps() {
        if (!isOpen()) {
            return 4;
        }
        int rawInterestOps = getRawInterestOps();
        int i = this.writeBufferSize.get();
        return (i == 0 || (this.highWaterMarkCounter.get() <= 0 ? i < getConfig().getWriteBufferHighWaterMark() : i < getConfig().getWriteBufferLowWaterMark())) ? rawInterestOps & (-5) : rawInterestOps | 4;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jboss.netty.channel.AbstractChannel
    public boolean setClosed() {
        return super.setClosed();
    }

    /* loaded from: classes7.dex */
    private final class WriteRequestQueue implements BlockingQueue<MessageEvent> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final ThreadLocalBoolean notifying = new ThreadLocalBoolean();
        private final BlockingQueue<MessageEvent> queue = QueueFactory.createQueue(MessageEvent.class);

        public WriteRequestQueue() {
        }

        @Override // java.util.Queue
        public MessageEvent remove() {
            return this.queue.remove();
        }

        @Override // java.util.Queue
        public MessageEvent element() {
            return this.queue.element();
        }

        @Override // java.util.Queue
        public MessageEvent peek() {
            return this.queue.peek();
        }

        @Override // java.util.Collection
        public int size() {
            return this.queue.size();
        }

        @Override // java.util.Collection
        public boolean isEmpty() {
            return this.queue.isEmpty();
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Iterator<MessageEvent> iterator() {
            return this.queue.iterator();
        }

        @Override // java.util.Collection
        public Object[] toArray() {
            return this.queue.toArray();
        }

        @Override // java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) this.queue.toArray(tArr);
        }

        @Override // java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            return this.queue.containsAll(collection);
        }

        @Override // java.util.Collection
        public boolean addAll(Collection<? extends MessageEvent> collection) {
            return this.queue.addAll(collection);
        }

        @Override // java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            return this.queue.removeAll(collection);
        }

        @Override // java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            return this.queue.retainAll(collection);
        }

        @Override // java.util.Collection
        public void clear() {
            this.queue.clear();
        }

        @Override // java.util.concurrent.BlockingQueue, java.util.Queue, java.util.Collection
        public boolean add(MessageEvent messageEvent) {
            return this.queue.add(messageEvent);
        }

        @Override // java.util.concurrent.BlockingQueue
        public void put(MessageEvent messageEvent) throws InterruptedException {
            this.queue.put(messageEvent);
        }

        @Override // java.util.concurrent.BlockingQueue
        public boolean offer(MessageEvent messageEvent, long j, TimeUnit timeUnit) throws InterruptedException {
            return this.queue.offer(messageEvent, j, timeUnit);
        }

        @Override // java.util.concurrent.BlockingQueue
        public MessageEvent take() throws InterruptedException {
            return this.queue.take();
        }

        @Override // java.util.concurrent.BlockingQueue
        public MessageEvent poll(long j, TimeUnit timeUnit) throws InterruptedException {
            return this.queue.poll(j, timeUnit);
        }

        @Override // java.util.concurrent.BlockingQueue
        public int remainingCapacity() {
            return this.queue.remainingCapacity();
        }

        @Override // java.util.concurrent.BlockingQueue, java.util.Collection
        public boolean remove(Object obj) {
            return this.queue.remove(obj);
        }

        @Override // java.util.concurrent.BlockingQueue, java.util.Collection
        public boolean contains(Object obj) {
            return this.queue.contains(obj);
        }

        @Override // java.util.concurrent.BlockingQueue
        public int drainTo(Collection<? super MessageEvent> collection) {
            return this.queue.drainTo(collection);
        }

        @Override // java.util.concurrent.BlockingQueue
        public int drainTo(Collection<? super MessageEvent> collection, int i) {
            return this.queue.drainTo(collection, i);
        }

        @Override // java.util.concurrent.BlockingQueue, java.util.Queue
        public boolean offer(MessageEvent messageEvent) {
            this.queue.offer(messageEvent);
            int messageSize = getMessageSize(messageEvent);
            int addAndGet = AbstractNioChannel.this.writeBufferSize.addAndGet(messageSize);
            int writeBufferHighWaterMark = AbstractNioChannel.this.getConfig().getWriteBufferHighWaterMark();
            if (addAndGet < writeBufferHighWaterMark || addAndGet - messageSize >= writeBufferHighWaterMark) {
                return true;
            }
            AbstractNioChannel.this.highWaterMarkCounter.incrementAndGet();
            if (this.notifying.get().booleanValue()) {
                return true;
            }
            this.notifying.set(Boolean.TRUE);
            Channels.fireChannelInterestChanged(AbstractNioChannel.this);
            this.notifying.set(Boolean.FALSE);
            return true;
        }

        @Override // java.util.Queue
        public MessageEvent poll() {
            MessageEvent poll = this.queue.poll();
            if (poll != null) {
                int messageSize = getMessageSize(poll);
                int addAndGet = AbstractNioChannel.this.writeBufferSize.addAndGet(-messageSize);
                int writeBufferLowWaterMark = AbstractNioChannel.this.getConfig().getWriteBufferLowWaterMark();
                if ((addAndGet == 0 || addAndGet < writeBufferLowWaterMark) && addAndGet + messageSize >= writeBufferLowWaterMark) {
                    AbstractNioChannel.this.highWaterMarkCounter.decrementAndGet();
                    if (AbstractNioChannel.this.isConnected() && !this.notifying.get().booleanValue()) {
                        this.notifying.set(Boolean.TRUE);
                        Channels.fireChannelInterestChanged(AbstractNioChannel.this);
                        this.notifying.set(Boolean.FALSE);
                    }
                }
            }
            return poll;
        }

        private int getMessageSize(MessageEvent messageEvent) {
            Object message = messageEvent.getMessage();
            if (message instanceof ChannelBuffer) {
                return ((ChannelBuffer) message).readableBytes();
            }
            return 0;
        }
    }

    /* loaded from: classes7.dex */
    private final class WriteTask implements Runnable {
        WriteTask() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractNioChannel.this.writeTaskInTaskQueue.set(false);
            AbstractNioChannel.this.worker.writeFromTaskLoop(AbstractNioChannel.this);
        }
    }
}
