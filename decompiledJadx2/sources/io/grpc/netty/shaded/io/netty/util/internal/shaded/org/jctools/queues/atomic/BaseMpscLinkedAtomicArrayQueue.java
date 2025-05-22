package io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.atomic;

import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;
import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueueUtil;
import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.QueueProgressIndicators;
import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.util.PortableJvmInfo;
import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.util.Pow2;
import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.util.RangeUtil;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class BaseMpscLinkedAtomicArrayQueue<E> extends BaseMpscLinkedAtomicArrayQueueColdProducerFields<E> implements MessagePassingQueue<E>, QueueProgressIndicators {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int CONTINUE_TO_P_INDEX_CAS = 0;
    private static final int QUEUE_FULL = 2;
    private static final int QUEUE_RESIZE = 3;
    private static final int RETRY = 1;
    private static final Object JUMP = new Object();
    private static final Object BUFFER_CONSUMED = new Object();

    protected abstract long availableInQueue(long j, long j2);

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.IndexedQueueSizeUtil.IndexedQueue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public abstract int capacity();

    protected abstract long getCurrentBufferCapacity(long j);

    protected abstract int getNextBufferSize(AtomicReferenceArray<E> atomicReferenceArray);

    public BaseMpscLinkedAtomicArrayQueue(int i) {
        RangeUtil.checkGreaterThanOrEqual(i, 2, "initialCapacity");
        int roundToPowerOfTwo = Pow2.roundToPowerOfTwo(i);
        long j = (roundToPowerOfTwo - 1) << 1;
        AtomicReferenceArray<E> allocateRefArray = AtomicQueueUtil.allocateRefArray(roundToPowerOfTwo + 1);
        this.producerBuffer = allocateRefArray;
        this.producerMask = j;
        this.consumerBuffer = allocateRefArray;
        this.consumerMask = j;
        soProducerLimit(j);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int size() {
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

    @Override // java.util.AbstractCollection, java.util.Collection, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public boolean isEmpty() {
        return lvConsumerIndex() == lvProducerIndex();
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return getClass().getName();
    }

    @Override // java.util.Queue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public boolean offer(E e) {
        int offerSlowPath;
        if (e == null) {
            throw new NullPointerException();
        }
        while (true) {
            long lvProducerLimit = lvProducerLimit();
            long lvProducerIndex = lvProducerIndex();
            if ((lvProducerIndex & 1) != 1) {
                long j = this.producerMask;
                AtomicReferenceArray<E> atomicReferenceArray = this.producerBuffer;
                if (lvProducerLimit <= lvProducerIndex && (offerSlowPath = offerSlowPath(j, lvProducerIndex, lvProducerLimit)) != 0) {
                    if (offerSlowPath == 1) {
                        continue;
                    } else {
                        if (offerSlowPath == 2) {
                            return false;
                        }
                        if (offerSlowPath == 3) {
                            resize(j, atomicReferenceArray, lvProducerIndex, e, null);
                            return true;
                        }
                    }
                }
                if (casProducerIndex(lvProducerIndex, 2 + lvProducerIndex)) {
                    AtomicQueueUtil.soRefElement(atomicReferenceArray, AtomicQueueUtil.modifiedCalcCircularRefElementOffset(lvProducerIndex, j), e);
                    return true;
                }
            }
        }
    }

    @Override // java.util.Queue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E poll() {
        AtomicReferenceArray<E> atomicReferenceArray = this.consumerBuffer;
        long lpConsumerIndex = lpConsumerIndex();
        long j = this.consumerMask;
        int modifiedCalcCircularRefElementOffset = AtomicQueueUtil.modifiedCalcCircularRefElementOffset(lpConsumerIndex, j);
        E e = (E) AtomicQueueUtil.lvRefElement(atomicReferenceArray, modifiedCalcCircularRefElementOffset);
        if (e == null) {
            if (lpConsumerIndex == lvProducerIndex()) {
                return null;
            }
            do {
                e = (E) AtomicQueueUtil.lvRefElement(atomicReferenceArray, modifiedCalcCircularRefElementOffset);
            } while (e == null);
        }
        if (e == JUMP) {
            return newBufferPoll(nextBuffer(atomicReferenceArray, j), lpConsumerIndex);
        }
        AtomicQueueUtil.soRefElement(atomicReferenceArray, modifiedCalcCircularRefElementOffset, null);
        soConsumerIndex(lpConsumerIndex + 2);
        return e;
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0018, code lost:
    
        if (r1 != lvProducerIndex()) goto L6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x001a, code lost:
    
        r6 = (E) io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.atomic.AtomicQueueUtil.lvRefElement(r0, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x001e, code lost:
    
        if (r6 == null) goto L14;
     */
    @Override // java.util.Queue, io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public E peek() {
        AtomicReferenceArray<E> atomicReferenceArray = this.consumerBuffer;
        long lpConsumerIndex = lpConsumerIndex();
        long j = this.consumerMask;
        int modifiedCalcCircularRefElementOffset = AtomicQueueUtil.modifiedCalcCircularRefElementOffset(lpConsumerIndex, j);
        E e = (E) AtomicQueueUtil.lvRefElement(atomicReferenceArray, modifiedCalcCircularRefElementOffset);
        if (e == null) {
        }
        return e == JUMP ? newBufferPeek(nextBuffer(atomicReferenceArray, j), lpConsumerIndex) : e;
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

    private AtomicReferenceArray<E> nextBuffer(AtomicReferenceArray<E> atomicReferenceArray, long j) {
        int nextArrayOffset = nextArrayOffset(j);
        AtomicReferenceArray<E> atomicReferenceArray2 = (AtomicReferenceArray) AtomicQueueUtil.lvRefElement(atomicReferenceArray, nextArrayOffset);
        this.consumerBuffer = atomicReferenceArray2;
        this.consumerMask = (AtomicQueueUtil.length(atomicReferenceArray2) - 2) << 1;
        AtomicQueueUtil.soRefElement(atomicReferenceArray, nextArrayOffset, BUFFER_CONSUMED);
        return atomicReferenceArray2;
    }

    private static int nextArrayOffset(long j) {
        return AtomicQueueUtil.modifiedCalcCircularRefElementOffset(j + 2, Long.MAX_VALUE);
    }

    private E newBufferPoll(AtomicReferenceArray<E> atomicReferenceArray, long j) {
        int modifiedCalcCircularRefElementOffset = AtomicQueueUtil.modifiedCalcCircularRefElementOffset(j, this.consumerMask);
        E e = (E) AtomicQueueUtil.lvRefElement(atomicReferenceArray, modifiedCalcCircularRefElementOffset);
        if (e == null) {
            throw new IllegalStateException("new buffer must have at least one element");
        }
        AtomicQueueUtil.soRefElement(atomicReferenceArray, modifiedCalcCircularRefElementOffset, null);
        soConsumerIndex(j + 2);
        return e;
    }

    private E newBufferPeek(AtomicReferenceArray<E> atomicReferenceArray, long j) {
        E e = (E) AtomicQueueUtil.lvRefElement(atomicReferenceArray, AtomicQueueUtil.modifiedCalcCircularRefElementOffset(j, this.consumerMask));
        if (e != null) {
            return e;
        }
        throw new IllegalStateException("new buffer must have at least one element");
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.QueueProgressIndicators
    public long currentProducerIndex() {
        return lvProducerIndex() / 2;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.QueueProgressIndicators
    public long currentConsumerIndex() {
        return lvConsumerIndex() / 2;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public boolean relaxedOffer(E e) {
        return offer(e);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E relaxedPoll() {
        AtomicReferenceArray<E> atomicReferenceArray = this.consumerBuffer;
        long lpConsumerIndex = lpConsumerIndex();
        long j = this.consumerMask;
        int modifiedCalcCircularRefElementOffset = AtomicQueueUtil.modifiedCalcCircularRefElementOffset(lpConsumerIndex, j);
        E e = (E) AtomicQueueUtil.lvRefElement(atomicReferenceArray, modifiedCalcCircularRefElementOffset);
        if (e == null) {
            return null;
        }
        if (e == JUMP) {
            return newBufferPoll(nextBuffer(atomicReferenceArray, j), lpConsumerIndex);
        }
        AtomicQueueUtil.soRefElement(atomicReferenceArray, modifiedCalcCircularRefElementOffset, null);
        soConsumerIndex(lpConsumerIndex + 2);
        return e;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E relaxedPeek() {
        AtomicReferenceArray<E> atomicReferenceArray = this.consumerBuffer;
        long lpConsumerIndex = lpConsumerIndex();
        long j = this.consumerMask;
        E e = (E) AtomicQueueUtil.lvRefElement(atomicReferenceArray, AtomicQueueUtil.modifiedCalcCircularRefElementOffset(lpConsumerIndex, j));
        return e == JUMP ? newBufferPeek(nextBuffer(atomicReferenceArray, j), lpConsumerIndex) : e;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
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

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int fill(MessagePassingQueue.Supplier<E> supplier, int i) {
        BaseMpscLinkedAtomicArrayQueue<E> baseMpscLinkedAtomicArrayQueue;
        long j;
        BaseMpscLinkedAtomicArrayQueue<E> baseMpscLinkedAtomicArrayQueue2 = this;
        int i2 = i;
        if (supplier == null) {
            throw new IllegalArgumentException("supplier is null");
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("limit is negative:" + i);
        }
        if (i2 == 0) {
            return 0;
        }
        while (true) {
            long lvProducerLimit = lvProducerLimit();
            long lvProducerIndex = lvProducerIndex();
            if ((lvProducerIndex & 1) != 1) {
                long j2 = baseMpscLinkedAtomicArrayQueue2.producerMask;
                AtomicReferenceArray<E> atomicReferenceArray = baseMpscLinkedAtomicArrayQueue2.producerBuffer;
                long min = Math.min(lvProducerLimit, (i2 * 2) + lvProducerIndex);
                if (lvProducerIndex >= lvProducerLimit) {
                    int offerSlowPath = offerSlowPath(j2, lvProducerIndex, lvProducerLimit);
                    if (offerSlowPath == 0 || offerSlowPath == 1) {
                        baseMpscLinkedAtomicArrayQueue2 = this;
                        i2 = i;
                    } else {
                        if (offerSlowPath == 2) {
                            return 0;
                        }
                        if (offerSlowPath == 3) {
                            resize(j2, atomicReferenceArray, lvProducerIndex, null, supplier);
                            return 1;
                        }
                        baseMpscLinkedAtomicArrayQueue = this;
                        j = min;
                    }
                } else {
                    baseMpscLinkedAtomicArrayQueue = this;
                    j = min;
                }
                if (baseMpscLinkedAtomicArrayQueue.casProducerIndex(lvProducerIndex, j)) {
                    int i3 = (int) ((j - lvProducerIndex) / 2);
                    for (int i4 = 0; i4 < i3; i4++) {
                        AtomicQueueUtil.soRefElement(atomicReferenceArray, AtomicQueueUtil.modifiedCalcCircularRefElementOffset((i4 * 2) + lvProducerIndex, j2), supplier.get());
                    }
                    return i3;
                }
                i2 = i;
                baseMpscLinkedAtomicArrayQueue2 = baseMpscLinkedAtomicArrayQueue;
            }
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public void fill(MessagePassingQueue.Supplier<E> supplier, MessagePassingQueue.WaitStrategy waitStrategy, MessagePassingQueue.ExitCondition exitCondition) {
        MessagePassingQueueUtil.fill(this, supplier, waitStrategy, exitCondition);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int drain(MessagePassingQueue.Consumer<E> consumer) {
        return drain(consumer, capacity());
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int drain(MessagePassingQueue.Consumer<E> consumer, int i) {
        return MessagePassingQueueUtil.drain(this, consumer, i);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public void drain(MessagePassingQueue.Consumer<E> consumer, MessagePassingQueue.WaitStrategy waitStrategy, MessagePassingQueue.ExitCondition exitCondition) {
        MessagePassingQueueUtil.drain(this, consumer, waitStrategy, exitCondition);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new WeakIterator(this.consumerBuffer, lvConsumerIndex(), lvProducerIndex());
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static class WeakIterator<E> implements Iterator<E> {
        private AtomicReferenceArray<E> currentBuffer;
        private int mask;
        private E nextElement;
        private long nextIndex;
        private final long pIndex;

        WeakIterator(AtomicReferenceArray<E> atomicReferenceArray, long j, long j2) {
            this.pIndex = j2 >> 1;
            this.nextIndex = j >> 1;
            setBuffer(atomicReferenceArray);
            this.nextElement = getNext();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.nextElement != null;
        }

        @Override // java.util.Iterator
        public E next() {
            E e = this.nextElement;
            if (e == null) {
                throw new NoSuchElementException();
            }
            this.nextElement = getNext();
            return e;
        }

        private void setBuffer(AtomicReferenceArray<E> atomicReferenceArray) {
            this.currentBuffer = atomicReferenceArray;
            this.mask = AtomicQueueUtil.length(atomicReferenceArray) - 2;
        }

        private E getNext() {
            while (true) {
                long j = this.nextIndex;
                if (j >= this.pIndex) {
                    break;
                }
                this.nextIndex = 1 + j;
                E e = (E) AtomicQueueUtil.lvRefElement(this.currentBuffer, AtomicQueueUtil.calcCircularRefElementOffset(j, this.mask));
                if (e != null) {
                    if (e == BaseMpscLinkedAtomicArrayQueue.JUMP) {
                        Object lvRefElement = AtomicQueueUtil.lvRefElement(this.currentBuffer, AtomicQueueUtil.calcRefElementOffset(this.mask + 1));
                        if (lvRefElement == BaseMpscLinkedAtomicArrayQueue.BUFFER_CONSUMED || lvRefElement == null) {
                            break;
                        }
                        setBuffer((AtomicReferenceArray) lvRefElement);
                        E e2 = (E) AtomicQueueUtil.lvRefElement(this.currentBuffer, AtomicQueueUtil.calcCircularRefElementOffset(j, this.mask));
                        if (e2 != null) {
                            return e2;
                        }
                    } else {
                        return e;
                    }
                }
            }
            return null;
        }
    }

    private void resize(long j, AtomicReferenceArray<E> atomicReferenceArray, long j2, E e, MessagePassingQueue.Supplier<E> supplier) {
        int nextBufferSize = getNextBufferSize(atomicReferenceArray);
        try {
            AtomicReferenceArray<E> allocateRefArray = AtomicQueueUtil.allocateRefArray(nextBufferSize);
            this.producerBuffer = allocateRefArray;
            long j3 = (nextBufferSize - 2) << 1;
            this.producerMask = j3;
            int modifiedCalcCircularRefElementOffset = AtomicQueueUtil.modifiedCalcCircularRefElementOffset(j2, j);
            int modifiedCalcCircularRefElementOffset2 = AtomicQueueUtil.modifiedCalcCircularRefElementOffset(j2, j3);
            if (e == null) {
                e = supplier.get();
            }
            AtomicQueueUtil.soRefElement(allocateRefArray, modifiedCalcCircularRefElementOffset2, e);
            AtomicQueueUtil.soRefElement(atomicReferenceArray, nextArrayOffset(j), allocateRefArray);
            long availableInQueue = availableInQueue(j2, lvConsumerIndex());
            RangeUtil.checkPositive(availableInQueue, "availableInQueue");
            soProducerLimit(Math.min(j3, availableInQueue) + j2);
            soProducerIndex(j2 + 2);
            AtomicQueueUtil.soRefElement(atomicReferenceArray, modifiedCalcCircularRefElementOffset, JUMP);
        } catch (OutOfMemoryError e2) {
            soProducerIndex(j2);
            throw e2;
        }
    }
}
