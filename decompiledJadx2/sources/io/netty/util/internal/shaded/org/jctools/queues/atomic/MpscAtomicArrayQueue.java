package io.netty.util.internal.shaded.org.jctools.queues.atomic;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;
import io.netty.util.internal.shaded.org.jctools.util.PortableJvmInfo;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class MpscAtomicArrayQueue<E> extends MpscAtomicArrayQueueL3Pad<E> {
    @Override // io.netty.util.internal.shaded.org.jctools.queues.atomic.AtomicReferenceArrayQueue, java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection, io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.atomic.AtomicReferenceArrayQueue, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public /* bridge */ /* synthetic */ Iterator iterator() {
        return super.iterator();
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.atomic.AtomicReferenceArrayQueue, java.util.AbstractCollection
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public MpscAtomicArrayQueue(int i) {
        super(i);
    }

    public boolean offerIfBelowThreshold(E e, int i) {
        long lvProducerIndex;
        if (e == null) {
            throw null;
        }
        int i2 = this.mask;
        long j = i2 + 1;
        long lvProducerLimit = lvProducerLimit();
        do {
            lvProducerIndex = lvProducerIndex();
            long j2 = i;
            if (j - (lvProducerLimit - lvProducerIndex) >= j2) {
                long lvConsumerIndex = lvConsumerIndex();
                if (lvProducerIndex - lvConsumerIndex >= j2) {
                    return false;
                }
                lvProducerLimit = lvConsumerIndex + j;
                soProducerLimit(lvProducerLimit);
            }
        } while (!casProducerIndex(lvProducerIndex, 1 + lvProducerIndex));
        soElement(this.buffer, calcElementOffset(lvProducerIndex, i2), e);
        return true;
    }

    @Override // java.util.Queue, io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public boolean offer(E e) {
        long lvProducerIndex;
        if (e == null) {
            throw null;
        }
        int i = this.mask;
        long lvProducerLimit = lvProducerLimit();
        do {
            lvProducerIndex = lvProducerIndex();
            if (lvProducerIndex >= lvProducerLimit) {
                lvProducerLimit = lvConsumerIndex() + i + 1;
                if (lvProducerIndex >= lvProducerLimit) {
                    return false;
                }
                soProducerLimit(lvProducerLimit);
            }
        } while (!casProducerIndex(lvProducerIndex, 1 + lvProducerIndex));
        soElement(this.buffer, calcElementOffset(lvProducerIndex, i), e);
        return true;
    }

    public final int failFastOffer(E e) {
        if (e == null) {
            throw null;
        }
        int i = this.mask;
        long j = i + 1;
        long lvProducerIndex = lvProducerIndex();
        if (lvProducerIndex >= lvProducerLimit()) {
            long lvConsumerIndex = lvConsumerIndex() + j;
            if (lvProducerIndex >= lvConsumerIndex) {
                return 1;
            }
            soProducerLimit(lvConsumerIndex);
        }
        if (!casProducerIndex(lvProducerIndex, 1 + lvProducerIndex)) {
            return -1;
        }
        soElement(this.buffer, calcElementOffset(lvProducerIndex, i), e);
        return 0;
    }

    @Override // java.util.Queue, io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E poll() {
        long lpConsumerIndex = lpConsumerIndex();
        int calcElementOffset = calcElementOffset(lpConsumerIndex);
        AtomicReferenceArray<E> atomicReferenceArray = this.buffer;
        E e = (E) lvElement(atomicReferenceArray, calcElementOffset);
        if (e == null) {
            if (lpConsumerIndex == lvProducerIndex()) {
                return null;
            }
            do {
                e = (E) lvElement(atomicReferenceArray, calcElementOffset);
            } while (e == null);
        }
        spElement(atomicReferenceArray, calcElementOffset, null);
        soConsumerIndex(lpConsumerIndex + 1);
        return e;
    }

    @Override // java.util.Queue, io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E peek() {
        AtomicReferenceArray<E> atomicReferenceArray = this.buffer;
        long lpConsumerIndex = lpConsumerIndex();
        int calcElementOffset = calcElementOffset(lpConsumerIndex);
        E e = (E) lvElement(atomicReferenceArray, calcElementOffset);
        if (e == null) {
            if (lpConsumerIndex == lvProducerIndex()) {
                return null;
            }
            do {
                e = (E) lvElement(atomicReferenceArray, calcElementOffset);
            } while (e == null);
        }
        return e;
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public boolean relaxedOffer(E e) {
        return offer(e);
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E relaxedPoll() {
        AtomicReferenceArray<E> atomicReferenceArray = this.buffer;
        long lpConsumerIndex = lpConsumerIndex();
        int calcElementOffset = calcElementOffset(lpConsumerIndex);
        E e = (E) lvElement(atomicReferenceArray, calcElementOffset);
        if (e == null) {
            return null;
        }
        spElement(atomicReferenceArray, calcElementOffset, null);
        soConsumerIndex(lpConsumerIndex + 1);
        return e;
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E relaxedPeek() {
        return (E) lvElement(this.buffer, calcElementOffset(lpConsumerIndex(), this.mask));
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int drain(MessagePassingQueue.Consumer<E> consumer) {
        return drain(consumer, capacity());
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

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int drain(MessagePassingQueue.Consumer<E> consumer, int i) {
        AtomicReferenceArray<E> atomicReferenceArray = this.buffer;
        int i2 = this.mask;
        long lpConsumerIndex = lpConsumerIndex();
        for (int i3 = 0; i3 < i; i3++) {
            long j = i3 + lpConsumerIndex;
            int calcElementOffset = calcElementOffset(j, i2);
            Object lvElement = lvElement(atomicReferenceArray, calcElementOffset);
            if (lvElement == null) {
                return i3;
            }
            spElement(atomicReferenceArray, calcElementOffset, null);
            soConsumerIndex(j + 1);
            consumer.accept(lvElement);
        }
        return i;
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int fill(MessagePassingQueue.Supplier<E> supplier, int i) {
        long lvProducerIndex;
        int i2;
        int min;
        int i3 = this.mask;
        long j = i3 + 1;
        long lvProducerLimit = lvProducerLimit();
        do {
            lvProducerIndex = lvProducerIndex();
            long j2 = lvProducerLimit - lvProducerIndex;
            if (j2 <= 0) {
                lvProducerLimit = lvConsumerIndex() + j;
                j2 = lvProducerLimit - lvProducerIndex;
                if (j2 <= 0) {
                    return 0;
                }
                soProducerLimit(lvProducerLimit);
            }
            min = Math.min((int) j2, i);
        } while (!casProducerIndex(lvProducerIndex, min + lvProducerIndex));
        AtomicReferenceArray<E> atomicReferenceArray = this.buffer;
        for (i2 = 0; i2 < min; i2++) {
            soElement(atomicReferenceArray, calcElementOffset(i2 + lvProducerIndex, i3), supplier.get());
        }
        return min;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public void drain(MessagePassingQueue.Consumer<E> consumer, MessagePassingQueue.WaitStrategy waitStrategy, MessagePassingQueue.ExitCondition exitCondition) {
        AtomicReferenceArray<E> atomicReferenceArray = this.buffer;
        int i = this.mask;
        long lpConsumerIndex = lpConsumerIndex();
        int i2 = 0;
        while (exitCondition.keepRunning()) {
            for (int i3 = 0; i3 < 4096; i3++) {
                int calcElementOffset = calcElementOffset(lpConsumerIndex, i);
                Object lvElement = lvElement(atomicReferenceArray, calcElementOffset);
                if (lvElement == null) {
                    i2 = waitStrategy.idle(i2);
                } else {
                    lpConsumerIndex++;
                    spElement(atomicReferenceArray, calcElementOffset, null);
                    soConsumerIndex(lpConsumerIndex);
                    consumer.accept(lvElement);
                    i2 = 0;
                }
            }
        }
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public void fill(MessagePassingQueue.Supplier<E> supplier, MessagePassingQueue.WaitStrategy waitStrategy, MessagePassingQueue.ExitCondition exitCondition) {
        while (true) {
            int i = 0;
            while (exitCondition.keepRunning()) {
                if (fill(supplier, PortableJvmInfo.RECOMENDED_OFFER_BATCH) == 0) {
                    i = waitStrategy.idle(i);
                }
            }
            return;
        }
    }

    @Deprecated
    public int weakOffer(E e) {
        return failFastOffer(e);
    }
}
