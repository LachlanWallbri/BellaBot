package io.netty.util.internal.shaded.org.jctools.queues;

import io.netty.util.internal.shaded.org.jctools.util.UnsafeAccess;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* compiled from: MpscArrayQueue.java */
/* loaded from: classes.dex */
abstract class MpscArrayQueueProducerLimitField<E> extends MpscArrayQueueMidPad<E> {
    private static final long P_LIMIT_OFFSET;
    private volatile long producerLimit;

    static {
        try {
            P_LIMIT_OFFSET = UnsafeAccess.UNSAFE.objectFieldOffset(MpscArrayQueueProducerLimitField.class.getDeclaredField("producerLimit"));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public MpscArrayQueueProducerLimitField(int i) {
        super(i);
        this.producerLimit = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long lvProducerLimit() {
        return this.producerLimit;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void soProducerLimit(long j) {
        UnsafeAccess.UNSAFE.putOrderedLong(this, P_LIMIT_OFFSET, j);
    }
}
