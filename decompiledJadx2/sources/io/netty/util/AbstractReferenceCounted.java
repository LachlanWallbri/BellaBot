package io.netty.util;

import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.ReferenceCountUpdater;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public abstract class AbstractReferenceCounted implements ReferenceCounted {
    private static final AtomicIntegerFieldUpdater<AbstractReferenceCounted> refCntUpdater = AtomicIntegerFieldUpdater.newUpdater(AbstractReferenceCounted.class, "refCnt");
    private volatile int refCnt = 1;

    protected abstract void deallocate();

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.netty.util.AbstractReferenceCounted$1 */
    /* loaded from: classes8.dex */
    static class C74111 extends ReferenceCountUpdater<AbstractReferenceCounted> {
        C74111() {
        }

        @Override // io.netty.util.internal.ReferenceCountUpdater
        protected AtomicIntegerFieldUpdater<AbstractReferenceCounted> updater() {
            return AbstractReferenceCounted.access$000();
        }

        @Override // io.netty.util.internal.ReferenceCountUpdater
        protected long unsafeOffset() {
            return AbstractReferenceCounted.access$100();
        }
    }

    @Override // io.netty.util.ReferenceCounted
    public final int refCnt() {
        return this.refCnt;
    }

    protected final void setRefCnt(int i) {
        refCntUpdater.set(this, i);
    }

    @Override // io.netty.util.ReferenceCounted
    public ReferenceCounted retain() {
        return retain0(1);
    }

    @Override // io.netty.util.ReferenceCounted
    public ReferenceCounted retain(int i) {
        return retain0(ObjectUtil.checkPositive(i, "increment"));
    }

    private ReferenceCounted retain0(int i) {
        int andAdd = refCntUpdater.getAndAdd(this, i);
        if (andAdd > 0 && andAdd + i >= andAdd) {
            return this;
        }
        refCntUpdater.getAndAdd(this, -i);
        throw new IllegalReferenceCountException(andAdd, i);
    }

    @Override // io.netty.util.ReferenceCounted
    public ReferenceCounted touch() {
        return touch(null);
    }

    @Override // io.netty.util.ReferenceCounted
    public boolean release() {
        return release0(1);
    }

    @Override // io.netty.util.ReferenceCounted
    public boolean release(int i) {
        return release0(ObjectUtil.checkPositive(i, "decrement"));
    }

    private boolean release0(int i) {
        int andAdd = refCntUpdater.getAndAdd(this, -i);
        if (andAdd == i) {
            deallocate();
            return true;
        }
        if (andAdd >= i && andAdd - i <= andAdd) {
            return false;
        }
        refCntUpdater.getAndAdd(this, i);
        throw new IllegalReferenceCountException(andAdd, i);
    }
}
