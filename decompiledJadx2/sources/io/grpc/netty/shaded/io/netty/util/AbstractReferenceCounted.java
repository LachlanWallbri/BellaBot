package io.grpc.netty.shaded.io.netty.util;

import io.grpc.netty.shaded.io.netty.util.internal.ReferenceCountUpdater;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class AbstractReferenceCounted implements ReferenceCounted {
    private volatile int refCnt = updater.initialValue();
    private static final long REFCNT_FIELD_OFFSET = ReferenceCountUpdater.getUnsafeOffset(AbstractReferenceCounted.class, "refCnt");
    private static final AtomicIntegerFieldUpdater<AbstractReferenceCounted> AIF_UPDATER = AtomicIntegerFieldUpdater.newUpdater(AbstractReferenceCounted.class, "refCnt");
    private static final ReferenceCountUpdater<AbstractReferenceCounted> updater = new ReferenceCountUpdater<AbstractReferenceCounted>() { // from class: io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted.1
        @Override // io.grpc.netty.shaded.io.netty.util.internal.ReferenceCountUpdater
        protected AtomicIntegerFieldUpdater<AbstractReferenceCounted> updater() {
            return AbstractReferenceCounted.AIF_UPDATER;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.internal.ReferenceCountUpdater
        protected long unsafeOffset() {
            return AbstractReferenceCounted.REFCNT_FIELD_OFFSET;
        }
    };

    protected abstract void deallocate();

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public int refCnt() {
        return updater.refCnt(this);
    }

    protected final void setRefCnt(int i) {
        updater.setRefCnt(this, i);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public ReferenceCounted retain() {
        return updater.retain(this);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public ReferenceCounted retain(int i) {
        return updater.retain(this, i);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public ReferenceCounted touch() {
        return touch(null);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public boolean release() {
        return handleRelease(updater.release(this));
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public boolean release(int i) {
        return handleRelease(updater.release(this, i));
    }

    private boolean handleRelease(boolean z) {
        if (z) {
            deallocate();
        }
        return z;
    }
}
