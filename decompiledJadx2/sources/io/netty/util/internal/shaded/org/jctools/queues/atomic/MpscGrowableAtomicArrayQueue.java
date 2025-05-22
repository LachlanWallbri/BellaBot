package io.netty.util.internal.shaded.org.jctools.queues.atomic;

import io.netty.util.internal.shaded.org.jctools.util.Pow2;
import io.netty.util.internal.shaded.org.jctools.util.RangeUtil;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class MpscGrowableAtomicArrayQueue<E> extends MpscChunkedAtomicArrayQueue<E> {
    public MpscGrowableAtomicArrayQueue(int i) {
        super(Math.max(2, Pow2.roundToPowerOfTwo(i / 8)), i);
    }

    public MpscGrowableAtomicArrayQueue(int i, int i2) {
        super(i, i2);
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.atomic.MpscChunkedAtomicArrayQueue, io.netty.util.internal.shaded.org.jctools.queues.atomic.BaseMpscLinkedAtomicArrayQueue
    protected int getNextBufferSize(AtomicReferenceArray<E> atomicReferenceArray) {
        RangeUtil.checkLessThanOrEqual(LinkedAtomicArrayQueueUtil.length(atomicReferenceArray), this.maxQueueCapacity / 2, "buffer.length");
        return ((LinkedAtomicArrayQueueUtil.length(atomicReferenceArray) - 1) * 2) + 1;
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.atomic.MpscChunkedAtomicArrayQueue, io.netty.util.internal.shaded.org.jctools.queues.atomic.BaseMpscLinkedAtomicArrayQueue
    protected long getCurrentBufferCapacity(long j) {
        return 2 + j == this.maxQueueCapacity ? this.maxQueueCapacity : j;
    }
}
