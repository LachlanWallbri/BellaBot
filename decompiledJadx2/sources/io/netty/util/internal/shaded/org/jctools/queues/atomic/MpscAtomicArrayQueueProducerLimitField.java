package io.netty.util.internal.shaded.org.jctools.queues.atomic;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* compiled from: MpscAtomicArrayQueue.java */
/* loaded from: classes.dex */
abstract class MpscAtomicArrayQueueProducerLimitField<E> extends MpscAtomicArrayQueueMidPad<E> {
    private static final AtomicLongFieldUpdater<MpscAtomicArrayQueueProducerLimitField> P_LIMIT_UPDATER = AtomicLongFieldUpdater.newUpdater(MpscAtomicArrayQueueProducerLimitField.class, "producerLimit");
    private volatile long producerLimit;

    public MpscAtomicArrayQueueProducerLimitField(int i) {
        super(i);
        this.producerLimit = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long lvProducerLimit() {
        return this.producerLimit;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void soProducerLimit(long j) {
        P_LIMIT_UPDATER.lazySet(this, j);
    }
}
