package io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.atomic;

import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.util.Pow2;
import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.util.RangeUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: MpscChunkedAtomicArrayQueue.java */
/* loaded from: classes7.dex */
abstract class MpscChunkedAtomicArrayQueueColdProducerFields<E> extends BaseMpscLinkedAtomicArrayQueue<E> {
    protected final long maxQueueCapacity;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MpscChunkedAtomicArrayQueueColdProducerFields(int i, int i2) {
        super(i);
        RangeUtil.checkGreaterThanOrEqual(i2, 4, "maxCapacity");
        RangeUtil.checkLessThan(Pow2.roundToPowerOfTwo(i), Pow2.roundToPowerOfTwo(i2), "initialCapacity");
        this.maxQueueCapacity = Pow2.roundToPowerOfTwo(i2) << 1;
    }
}
