package io.netty.util.internal.shaded.org.jctools.queues;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;
import io.netty.util.internal.shaded.org.jctools.util.PortableJvmInfo;
import io.netty.util.internal.shaded.org.jctools.util.Pow2;
import io.netty.util.internal.shaded.org.jctools.util.RangeUtil;
import io.netty.util.internal.shaded.org.jctools.util.UnsafeRefArrayAccess;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public abstract class BaseMpscLinkedArrayQueue<E> extends BaseMpscLinkedArrayQueueColdProducerFields<E> implements MessagePassingQueue<E>, QueueProgressIndicators {
    private static final int CONTINUE_TO_P_INDEX_CAS = 0;
    private static final Object JUMP = new Object();
    private static final int QUEUE_FULL = 2;
    private static final int QUEUE_RESIZE = 3;
    private static final int RETRY = 1;

    protected abstract long availableInQueue(long j, long j2);

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public abstract int capacity();

    protected abstract long getCurrentBufferCapacity(long j);

    protected abstract int getNextBufferSize(E[] eArr);

    public BaseMpscLinkedArrayQueue(int i) {
        RangeUtil.checkGreaterThanOrEqual(i, 2, "initialCapacity");
        int roundToPowerOfTwo = Pow2.roundToPowerOfTwo(i);
        long j = (roundToPowerOfTwo - 1) << 1;
        E[] eArr = (E[]) CircularArrayOffsetCalculator.allocate(roundToPowerOfTwo + 1);
        this.producerBuffer = eArr;
        this.producerMask = j;
        this.consumerBuffer = eArr;
        this.consumerMask = j;
        soProducerLimit(j);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public final int size() {
        long lvProducerIndex;
        long lvConsumerIndex;
        long lvConsumerIndex2 = lvConsumerIndex();
        while (true) {
            lvProducerIndex = lvProducerIndex();
            lvConsumerIndex = lvConsumerIndex();
            if (lvConsumerIndex2 == lvConsumerIndex) {
                break;
            }
            lvConsumerIndex2 = lvConsumerIndex;
        }
        long j = (lvProducerIndex - lvConsumerIndex) >> 1;
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) j;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public final boolean isEmpty() {
        return lvConsumerIndex() == lvProducerIndex();
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return getClass().getName();
    }

    @Override // java.util.Queue, io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public boolean offer(E e) {
        if (e == null) {
            throw null;
        }
        while (true) {
            long lvProducerLimit = lvProducerLimit();
            long lvProducerIndex = lvProducerIndex();
            if ((lvProducerIndex & 1) != 1) {
                long j = this.producerMask;
                E[] eArr = this.producerBuffer;
                if (lvProducerLimit <= lvProducerIndex) {
                    int offerSlowPath = offerSlowPath(j, lvProducerIndex, lvProducerLimit);
                    if (offerSlowPath == 1) {
                        continue;
                    } else {
                        if (offerSlowPath == 2) {
                            return false;
                        }
                        if (offerSlowPath == 3) {
                            resize(j, eArr, lvProducerIndex, e);
                            return true;
                        }
                    }
                }
                if (casProducerIndex(lvProducerIndex, 2 + lvProducerIndex)) {
                    UnsafeRefArrayAccess.soElement(eArr, LinkedArrayQueueUtil.modifiedCalcElementOffset(lvProducerIndex, j), e);
                    return true;
                }
            }
        }
    }

    @Override // java.util.Queue, io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E poll() {
        E[] eArr = this.consumerBuffer;
        long j = this.consumerIndex;
        long j2 = this.consumerMask;
        long modifiedCalcElementOffset = LinkedArrayQueueUtil.modifiedCalcElementOffset(j, j2);
        E e = (E) UnsafeRefArrayAccess.lvElement(eArr, modifiedCalcElementOffset);
        if (e == null) {
            if (j == lvProducerIndex()) {
                return null;
            }
            do {
                e = (E) UnsafeRefArrayAccess.lvElement(eArr, modifiedCalcElementOffset);
            } while (e == null);
        }
        if (e == JUMP) {
            return newBufferPoll(getNextBuffer(eArr, j2), j);
        }
        UnsafeRefArrayAccess.soElement(eArr, modifiedCalcElementOffset, null);
        soConsumerIndex(j + 2);
        return e;
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0016, code lost:
    
        if (r1 != lvProducerIndex()) goto L6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0018, code lost:
    
        r7 = (E) io.netty.util.internal.shaded.org.jctools.util.UnsafeRefArrayAccess.lvElement(r0, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x001c, code lost:
    
        if (r7 == null) goto L14;
     */
    @Override // java.util.Queue, io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public E peek() {
        E[] eArr = this.consumerBuffer;
        long j = this.consumerIndex;
        long j2 = this.consumerMask;
        long modifiedCalcElementOffset = LinkedArrayQueueUtil.modifiedCalcElementOffset(j, j2);
        E e = (E) UnsafeRefArrayAccess.lvElement(eArr, modifiedCalcElementOffset);
        if (e == null) {
        }
        return e == JUMP ? newBufferPeek(getNextBuffer(eArr, j2), j) : e;
    }

    private int offerSlowPath(long j, long j2, long j3) {
        long lvConsumerIndex = lvConsumerIndex();
        long currentBufferCapacity = getCurrentBufferCapacity(j) + lvConsumerIndex;
        if (currentBufferCapacity > j2) {
            return !casProducerLimit(j3, currentBufferCapacity) ? 1 : 0;
        }
        if (availableInQueue(j2, lvConsumerIndex) <= 0) {
            return 2;
        }
        return casProducerIndex(j2, 1 + j2) ? 3 : 1;
    }

    private E[] getNextBuffer(E[] eArr, long j) {
        long nextArrayOffset = nextArrayOffset(j);
        E[] eArr2 = (E[]) ((Object[]) UnsafeRefArrayAccess.lvElement(eArr, nextArrayOffset));
        UnsafeRefArrayAccess.soElement(eArr, nextArrayOffset, null);
        return eArr2;
    }

    private long nextArrayOffset(long j) {
        return LinkedArrayQueueUtil.modifiedCalcElementOffset(j + 2, Long.MAX_VALUE);
    }

    private E newBufferPoll(E[] eArr, long j) {
        long newBufferAndOffset = newBufferAndOffset(eArr, j);
        E e = (E) UnsafeRefArrayAccess.lvElement(eArr, newBufferAndOffset);
        if (e == null) {
            throw new IllegalStateException("new buffer must have at least one element");
        }
        UnsafeRefArrayAccess.soElement(eArr, newBufferAndOffset, null);
        soConsumerIndex(j + 2);
        return e;
    }

    private E newBufferPeek(E[] eArr, long j) {
        E e = (E) UnsafeRefArrayAccess.lvElement(eArr, newBufferAndOffset(eArr, j));
        if (e != null) {
            return e;
        }
        throw new IllegalStateException("new buffer must have at least one element");
    }

    private long newBufferAndOffset(E[] eArr, long j) {
        this.consumerBuffer = eArr;
        this.consumerMask = (LinkedArrayQueueUtil.length(eArr) - 2) << 1;
        return LinkedArrayQueueUtil.modifiedCalcElementOffset(j, this.consumerMask);
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.QueueProgressIndicators
    public long currentProducerIndex() {
        return lvProducerIndex() / 2;
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.QueueProgressIndicators
    public long currentConsumerIndex() {
        return lvConsumerIndex() / 2;
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public boolean relaxedOffer(E e) {
        return offer(e);
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E relaxedPoll() {
        E[] eArr = this.consumerBuffer;
        long j = this.consumerIndex;
        long j2 = this.consumerMask;
        long modifiedCalcElementOffset = LinkedArrayQueueUtil.modifiedCalcElementOffset(j, j2);
        E e = (E) UnsafeRefArrayAccess.lvElement(eArr, modifiedCalcElementOffset);
        if (e == null) {
            return null;
        }
        if (e == JUMP) {
            return newBufferPoll(getNextBuffer(eArr, j2), j);
        }
        UnsafeRefArrayAccess.soElement(eArr, modifiedCalcElementOffset, null);
        soConsumerIndex(j + 2);
        return e;
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E relaxedPeek() {
        E[] eArr = this.consumerBuffer;
        long j = this.consumerIndex;
        long j2 = this.consumerMask;
        E e = (E) UnsafeRefArrayAccess.lvElement(eArr, LinkedArrayQueueUtil.modifiedCalcElementOffset(j, j2));
        return e == JUMP ? newBufferPeek(getNextBuffer(eArr, j2), j) : e;
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int fill(MessagePassingQueue.Supplier<E> supplier) {
        int capacity = capacity();
        long j = 0;
        do {
            int fill = fill(supplier, PortableJvmInfo.RECOMENDED_OFFER_BATCH);
            if (fill == 0) {
                return (int) j;
            }
            j += fill;
        } while (j <= capacity);
        return (int) j;
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int fill(MessagePassingQueue.Supplier<E> supplier, int i) {
        while (true) {
            long lvProducerLimit = lvProducerLimit();
            long lvProducerIndex = lvProducerIndex();
            if ((lvProducerIndex & 1) != 1) {
                long j = this.producerMask;
                E[] eArr = this.producerBuffer;
                long min = Math.min(lvProducerLimit, (i * 2) + lvProducerIndex);
                if (lvProducerIndex >= lvProducerLimit || lvProducerLimit < min) {
                    int offerSlowPath = offerSlowPath(j, lvProducerIndex, lvProducerLimit);
                    if (offerSlowPath != 0 && offerSlowPath != 1) {
                        if (offerSlowPath == 2) {
                            return 0;
                        }
                        if (offerSlowPath == 3) {
                            resize(j, eArr, lvProducerIndex, supplier.get());
                            return 1;
                        }
                    }
                }
                if (casProducerIndex(lvProducerIndex, min)) {
                    int i2 = (int) ((min - lvProducerIndex) / 2);
                    for (int i3 = 0; i3 < i2; i3++) {
                        UnsafeRefArrayAccess.soElement(eArr, LinkedArrayQueueUtil.modifiedCalcElementOffset((i3 * 2) + lvProducerIndex, j), supplier.get());
                    }
                    return i2;
                }
            }
        }
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public void fill(MessagePassingQueue.Supplier<E> supplier, MessagePassingQueue.WaitStrategy waitStrategy, MessagePassingQueue.ExitCondition exitCondition) {
        while (exitCondition.keepRunning()) {
            if (fill(supplier, PortableJvmInfo.RECOMENDED_OFFER_BATCH) == 0) {
                int i = 0;
                while (exitCondition.keepRunning() && fill(supplier, PortableJvmInfo.RECOMENDED_OFFER_BATCH) == 0) {
                    i = waitStrategy.idle(i);
                }
            }
        }
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int drain(MessagePassingQueue.Consumer<E> consumer) {
        return drain(consumer, capacity());
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int drain(MessagePassingQueue.Consumer<E> consumer, int i) {
        int i2 = 0;
        while (i2 < i) {
            E relaxedPoll = relaxedPoll();
            if (relaxedPoll == null) {
                break;
            }
            consumer.accept(relaxedPoll);
            i2++;
        }
        return i2;
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public void drain(MessagePassingQueue.Consumer<E> consumer, MessagePassingQueue.WaitStrategy waitStrategy, MessagePassingQueue.ExitCondition exitCondition) {
        E relaxedPoll;
        while (true) {
            int i = 0;
            while (exitCondition.keepRunning()) {
                relaxedPoll = relaxedPoll();
                if (relaxedPoll == null) {
                    i = waitStrategy.idle(i);
                }
            }
            return;
            consumer.accept(relaxedPoll);
        }
    }

    private void resize(long j, E[] eArr, long j2, E e) {
        int nextBufferSize = getNextBufferSize(eArr);
        E[] eArr2 = (E[]) CircularArrayOffsetCalculator.allocate(nextBufferSize);
        this.producerBuffer = eArr2;
        long j3 = (nextBufferSize - 2) << 1;
        this.producerMask = j3;
        long modifiedCalcElementOffset = LinkedArrayQueueUtil.modifiedCalcElementOffset(j2, j);
        UnsafeRefArrayAccess.soElement(eArr2, LinkedArrayQueueUtil.modifiedCalcElementOffset(j2, j3), e);
        UnsafeRefArrayAccess.soElement(eArr, nextArrayOffset(j), eArr2);
        long availableInQueue = availableInQueue(j2, lvConsumerIndex());
        RangeUtil.checkPositive(availableInQueue, "availableInQueue");
        soProducerLimit(Math.min(j3, availableInQueue) + j2);
        soProducerIndex(j2 + 2);
        UnsafeRefArrayAccess.soElement(eArr, modifiedCalcElementOffset, JUMP);
    }
}
