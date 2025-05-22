package io.netty.buffer;

import io.netty.util.IllegalReferenceCountException;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.ReferenceCountUpdater;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public abstract class AbstractReferenceCountedByteBuf extends AbstractByteBuf {
    private static final AtomicIntegerFieldUpdater<AbstractReferenceCountedByteBuf> refCntUpdater = AtomicIntegerFieldUpdater.newUpdater(AbstractReferenceCountedByteBuf.class, "refCnt");
    private volatile int refCnt;

    protected abstract void deallocate();

    @Override // io.netty.buffer.ByteBuf, io.netty.util.ReferenceCounted
    public ByteBuf touch() {
        return this;
    }

    @Override // io.netty.buffer.ByteBuf, io.netty.util.ReferenceCounted
    public ByteBuf touch(Object obj) {
        return this;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.netty.buffer.AbstractReferenceCountedByteBuf$1 */
    /* loaded from: classes7.dex */
    static class C68921 extends ReferenceCountUpdater<AbstractReferenceCountedByteBuf> {
        C68921() {
        }

        @Override // io.netty.util.internal.ReferenceCountUpdater
        protected AtomicIntegerFieldUpdater<AbstractReferenceCountedByteBuf> updater() {
            return AbstractReferenceCountedByteBuf.access$000();
        }

        @Override // io.netty.util.internal.ReferenceCountUpdater
        protected long unsafeOffset() {
            return AbstractReferenceCountedByteBuf.access$100();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractReferenceCountedByteBuf(int i) {
        super(i);
        refCntUpdater.set(this, 1);
    }

    @Override // io.netty.util.ReferenceCounted
    public int refCnt() {
        return this.refCnt;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setRefCnt(int i) {
        refCntUpdater.set(this, i);
    }

    @Override // io.netty.buffer.ByteBuf, io.netty.util.ReferenceCounted
    public ByteBuf retain() {
        return retain0(1);
    }

    @Override // io.netty.buffer.ByteBuf, io.netty.util.ReferenceCounted
    public ByteBuf retain(int i) {
        return retain0(ObjectUtil.checkPositive(i, "increment"));
    }

    private ByteBuf retain0(int i) {
        int andAdd = refCntUpdater.getAndAdd(this, i);
        if (andAdd > 0 && andAdd + i >= andAdd) {
            return this;
        }
        refCntUpdater.getAndAdd(this, -i);
        throw new IllegalReferenceCountException(andAdd, i);
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
