package io.grpc.netty.shaded.io.netty.channel;

import androidx.core.app.NotificationCompat;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.concurrent.Promise;
import io.grpc.netty.shaded.io.netty.util.concurrent.PromiseCombiner;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectPool;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.SystemPropertyUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class PendingWriteQueue {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private long bytes;
    private final ChannelHandlerContext ctx;
    private PendingWrite head;
    private int size;
    private PendingWrite tail;
    private final PendingBytesTracker tracker;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) PendingWriteQueue.class);
    private static final int PENDING_WRITE_OVERHEAD = SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.transport.pendingWriteSizeOverhead", 64);

    private void assertEmpty() {
    }

    public PendingWriteQueue(ChannelHandlerContext channelHandlerContext) {
        this.tracker = PendingBytesTracker.newTracker(channelHandlerContext.channel());
        this.ctx = channelHandlerContext;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public int size() {
        return this.size;
    }

    public long bytes() {
        return this.bytes;
    }

    private int size(Object obj) {
        int size = this.tracker.size(obj);
        if (size < 0) {
            size = 0;
        }
        return size + PENDING_WRITE_OVERHEAD;
    }

    public void add(Object obj, ChannelPromise channelPromise) {
        ObjectUtil.checkNotNull(obj, NotificationCompat.CATEGORY_MESSAGE);
        ObjectUtil.checkNotNull(channelPromise, "promise");
        int size = size(obj);
        PendingWrite newInstance = PendingWrite.newInstance(obj, size, channelPromise);
        PendingWrite pendingWrite = this.tail;
        if (pendingWrite == null) {
            this.head = newInstance;
            this.tail = newInstance;
        } else {
            pendingWrite.next = newInstance;
            this.tail = newInstance;
        }
        this.size++;
        this.bytes += size;
        this.tracker.incrementPendingOutboundBytes(newInstance.size);
    }

    public ChannelFuture removeAndWriteAll() {
        if (isEmpty()) {
            return null;
        }
        ChannelPromise newPromise = this.ctx.newPromise();
        PromiseCombiner promiseCombiner = new PromiseCombiner(this.ctx.executor());
        while (true) {
            try {
                PendingWrite pendingWrite = this.head;
                if (pendingWrite == null) {
                    break;
                }
                this.tail = null;
                this.head = null;
                this.size = 0;
                this.bytes = 0L;
                while (pendingWrite != null) {
                    PendingWrite pendingWrite2 = pendingWrite.next;
                    Object obj = pendingWrite.msg;
                    ChannelPromise channelPromise = pendingWrite.promise;
                    recycle(pendingWrite, false);
                    if (!(channelPromise instanceof VoidChannelPromise)) {
                        promiseCombiner.add((Promise) channelPromise);
                    }
                    this.ctx.write(obj, channelPromise);
                    pendingWrite = pendingWrite2;
                }
            } catch (Throwable th) {
                newPromise.setFailure(th);
            }
        }
        promiseCombiner.finish(newPromise);
        assertEmpty();
        return newPromise;
    }

    public void removeAndFailAll(Throwable th) {
        ObjectUtil.checkNotNull(th, "cause");
        while (true) {
            PendingWrite pendingWrite = this.head;
            if (pendingWrite != null) {
                this.tail = null;
                this.head = null;
                this.size = 0;
                this.bytes = 0L;
                while (pendingWrite != null) {
                    PendingWrite pendingWrite2 = pendingWrite.next;
                    ReferenceCountUtil.safeRelease(pendingWrite.msg);
                    ChannelPromise channelPromise = pendingWrite.promise;
                    recycle(pendingWrite, false);
                    safeFail(channelPromise, th);
                    pendingWrite = pendingWrite2;
                }
            } else {
                assertEmpty();
                return;
            }
        }
    }

    public void removeAndFail(Throwable th) {
        ObjectUtil.checkNotNull(th, "cause");
        PendingWrite pendingWrite = this.head;
        if (pendingWrite == null) {
            return;
        }
        ReferenceCountUtil.safeRelease(pendingWrite.msg);
        safeFail(pendingWrite.promise, th);
        recycle(pendingWrite, true);
    }

    public ChannelFuture removeAndWrite() {
        PendingWrite pendingWrite = this.head;
        if (pendingWrite == null) {
            return null;
        }
        Object obj = pendingWrite.msg;
        ChannelPromise channelPromise = pendingWrite.promise;
        recycle(pendingWrite, true);
        return this.ctx.write(obj, channelPromise);
    }

    public ChannelPromise remove() {
        PendingWrite pendingWrite = this.head;
        if (pendingWrite == null) {
            return null;
        }
        ChannelPromise channelPromise = pendingWrite.promise;
        ReferenceCountUtil.safeRelease(pendingWrite.msg);
        recycle(pendingWrite, true);
        return channelPromise;
    }

    public Object current() {
        PendingWrite pendingWrite = this.head;
        if (pendingWrite == null) {
            return null;
        }
        return pendingWrite.msg;
    }

    private void recycle(PendingWrite pendingWrite, boolean z) {
        PendingWrite pendingWrite2 = pendingWrite.next;
        long j = pendingWrite.size;
        if (z) {
            if (pendingWrite2 == null) {
                this.tail = null;
                this.head = null;
                this.size = 0;
                this.bytes = 0L;
            } else {
                this.head = pendingWrite2;
                this.size--;
                this.bytes -= j;
            }
        }
        pendingWrite.recycle();
        this.tracker.decrementPendingOutboundBytes(j);
    }

    private static void safeFail(ChannelPromise channelPromise, Throwable th) {
        if ((channelPromise instanceof VoidChannelPromise) || channelPromise.tryFailure(th)) {
            return;
        }
        logger.warn("Failed to mark a promise as failure because it's done already: {}", channelPromise, th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class PendingWrite {
        private static final ObjectPool<PendingWrite> RECYCLER = ObjectPool.newPool(new ObjectPool.ObjectCreator<PendingWrite>() { // from class: io.grpc.netty.shaded.io.netty.channel.PendingWriteQueue.PendingWrite.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.grpc.netty.shaded.io.netty.util.internal.ObjectPool.ObjectCreator
            public PendingWrite newObject(ObjectPool.Handle<PendingWrite> handle) {
                return new PendingWrite(handle);
            }
        });
        private final ObjectPool.Handle<PendingWrite> handle;
        private Object msg;
        private PendingWrite next;
        private ChannelPromise promise;
        private long size;

        private PendingWrite(ObjectPool.Handle<PendingWrite> handle) {
            this.handle = handle;
        }

        static PendingWrite newInstance(Object obj, int i, ChannelPromise channelPromise) {
            PendingWrite pendingWrite = RECYCLER.get();
            pendingWrite.size = i;
            pendingWrite.msg = obj;
            pendingWrite.promise = channelPromise;
            return pendingWrite;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void recycle() {
            this.size = 0L;
            this.next = null;
            this.msg = null;
            this.promise = null;
            this.handle.recycle(this);
        }
    }
}
