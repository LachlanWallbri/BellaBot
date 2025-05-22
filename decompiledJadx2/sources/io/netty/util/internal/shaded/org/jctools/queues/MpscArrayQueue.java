package io.netty.util.internal.shaded.org.jctools.queues;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;
import io.netty.util.internal.shaded.org.jctools.util.PortableJvmInfo;
import io.netty.util.internal.shaded.org.jctools.util.UnsafeRefArrayAccess;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class MpscArrayQueue<E> extends MpscArrayQueueL3Pad<E> {
    public MpscArrayQueue(int i) {
        super(i);
    }

    public boolean offerIfBelowThreshold(E e, int i) {
        long lvProducerIndex;
        if (e == null) {
            throw null;
        }
        long j = this.mask;
        long j2 = j + 1;
        long lvProducerLimit = lvProducerLimit();
        do {
            lvProducerIndex = lvProducerIndex();
            long j3 = i;
            if (j2 - (lvProducerLimit - lvProducerIndex) >= j3) {
                long lvConsumerIndex = lvConsumerIndex();
                if (lvProducerIndex - lvConsumerIndex >= j3) {
                    return false;
                }
                lvProducerLimit = lvConsumerIndex + j2;
                soProducerLimit(lvProducerLimit);
            }
        } while (!casProducerIndex(lvProducerIndex, lvProducerIndex + 1));
        UnsafeRefArrayAccess.soElement(this.buffer, calcElementOffset(lvProducerIndex, j), e);
        return true;
    }

    @Override // java.util.Queue, io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public boolean offer(E e) {
        long lvProducerIndex;
        if (e == null) {
            throw null;
        }
        long j = this.mask;
        long lvProducerLimit = lvProducerLimit();
        do {
            lvProducerIndex = lvProducerIndex();
            if (lvProducerIndex >= lvProducerLimit) {
                lvProducerLimit = lvConsumerIndex() + j + 1;
                if (lvProducerIndex >= lvProducerLimit) {
                    return false;
                }
                soProducerLimit(lvProducerLimit);
            }
        } while (!casProducerIndex(lvProducerIndex, 1 + lvProducerIndex));
        UnsafeRefArrayAccess.soElement(this.buffer, calcElementOffset(lvProducerIndex, j), e);
        return true;
    }

    public final int failFastOffer(E e) {
        if (e == null) {
            throw null;
        }
        long j = this.mask;
        long j2 = j + 1;
        long lvProducerIndex = lvProducerIndex();
        if (lvProducerIndex >= lvProducerLimit()) {
            long lvConsumerIndex = lvConsumerIndex() + j2;
            if (lvProducerIndex >= lvConsumerIndex) {
                return 1;
            }
            soProducerLimit(lvConsumerIndex);
        }
        if (!casProducerIndex(lvProducerIndex, 1 + lvProducerIndex)) {
            return -1;
        }
        UnsafeRefArrayAccess.soElement(this.buffer, calcElementOffset(lvProducerIndex, j), e);
        return 0;
    }

    @Override // java.util.Queue, io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E poll() {
        long lpConsumerIndex = lpConsumerIndex();
        long calcElementOffset = calcElementOffset(lpConsumerIndex);
        E[] eArr = this.buffer;
        E e = (E) UnsafeRefArrayAccess.lvElement(eArr, calcElementOffset);
        if (e == null) {
            if (lpConsumerIndex == lvProducerIndex()) {
                return null;
            }
            do {
                e = (E) UnsafeRefArrayAccess.lvElement(eArr, calcElementOffset);
            } while (e == null);
        }
        UnsafeRefArrayAccess.spElement(eArr, calcElementOffset, null);
        soConsumerIndex(lpConsumerIndex + 1);
        return e;
    }

    @Override // java.util.Queue, io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E peek() {
        E[] eArr = this.buffer;
        long lpConsumerIndex = lpConsumerIndex();
        long calcElementOffset = calcElementOffset(lpConsumerIndex);
        E e = (E) UnsafeRefArrayAccess.lvElement(eArr, calcElementOffset);
        if (e == null) {
            if (lpConsumerIndex == lvProducerIndex()) {
                return null;
            }
            do {
                e = (E) UnsafeRefArrayAccess.lvElement(eArr, calcElementOffset);
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
        E[] eArr = this.buffer;
        long lpConsumerIndex = lpConsumerIndex();
        long calcElementOffset = calcElementOffset(lpConsumerIndex);
        E e = (E) UnsafeRefArrayAccess.lvElement(eArr, calcElementOffset);
        if (e == null) {
            return null;
        }
        UnsafeRefArrayAccess.spElement(eArr, calcElementOffset, null);
        soConsumerIndex(lpConsumerIndex + 1);
        return e;
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E relaxedPeek() {
        return (E) UnsafeRefArrayAccess.lvElement(this.buffer, calcElementOffset(lpConsumerIndex(), this.mask));
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
        E[] eArr = this.buffer;
        long j = this.mask;
        long lpConsumerIndex = lpConsumerIndex();
        for (int i2 = 0; i2 < i; i2++) {
            long j2 = i2 + lpConsumerIndex;
            long calcElementOffset = calcElementOffset(j2, j);
            Object lvElement = UnsafeRefArrayAccess.lvElement(eArr, calcElementOffset);
            if (lvElement == null) {
                return i2;
            }
            UnsafeRefArrayAccess.spElement(eArr, calcElementOffset, null);
            soConsumerIndex(j2 + 1);
            consumer.accept(lvElement);
        }
        return i;
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int fill(MessagePassingQueue.Supplier<E> supplier, int i) {
        long lvProducerIndex;
        int i2;
        int min;
        long j = this.mask;
        long j2 = 1 + j;
        long lvProducerLimit = lvProducerLimit();
        do {
            lvProducerIndex = lvProducerIndex();
            long j3 = lvProducerLimit - lvProducerIndex;
            if (j3 <= 0) {
                lvProducerLimit = lvConsumerIndex() + j2;
                j3 = lvProducerLimit - lvProducerIndex;
                if (j3 <= 0) {
                    return 0;
                }
                soProducerLimit(lvProducerLimit);
            }
            min = Math.min((int) j3, i);
        } while (!casProducerIndex(lvProducerIndex, min + lvProducerIndex));
        E[] eArr = this.buffer;
        for (i2 = 0; i2 < min; i2++) {
            UnsafeRefArrayAccess.soElement(eArr, calcElementOffset(i2 + lvProducerIndex, j), supplier.get());
        }
        return min;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public void drain(MessagePassingQueue.Consumer<E> consumer, MessagePassingQueue.WaitStrategy waitStrategy, MessagePassingQueue.ExitCondition exitCondition) {
        E[] eArr = this.buffer;
        long j = this.mask;
        long lpConsumerIndex = lpConsumerIndex();
        int i = 0;
        while (exitCondition.keepRunning()) {
            for (int i2 = 0; i2 < 4096; i2++) {
                long calcElementOffset = calcElementOffset(lpConsumerIndex, j);
                Object lvElement = UnsafeRefArrayAccess.lvElement(eArr, calcElementOffset);
                if (lvElement == null) {
                    i = waitStrategy.idle(i);
                } else {
                    lpConsumerIndex++;
                    UnsafeRefArrayAccess.spElement(eArr, calcElementOffset, null);
                    soConsumerIndex(lpConsumerIndex);
                    consumer.accept(lvElement);
                    i = 0;
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
}
