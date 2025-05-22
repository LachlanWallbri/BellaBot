package io.grpc.netty.shaded.io.netty.buffer;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectPool;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class AbstractPooledDerivedByteBuf extends AbstractReferenceCountedByteBuf {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private ByteBuf parent;
    private final ObjectPool.Handle<AbstractPooledDerivedByteBuf> recyclerHandle;
    private AbstractByteBuf rootParent;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public AbstractPooledDerivedByteBuf(ObjectPool.Handle<? extends AbstractPooledDerivedByteBuf> handle) {
        super(0);
        this.recyclerHandle = handle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void parent(ByteBuf byteBuf) {
        this.parent = byteBuf;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final AbstractByteBuf unwrap() {
        return this.rootParent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public final <U extends AbstractPooledDerivedByteBuf> U init(AbstractByteBuf abstractByteBuf, ByteBuf byteBuf, int i, int i2, int i3) {
        byteBuf.retain();
        this.parent = byteBuf;
        this.rootParent = abstractByteBuf;
        try {
            maxCapacity(i3);
            setIndex0(i, i2);
            resetRefCnt();
            return this;
        } catch (Throwable th) {
            if (byteBuf != null) {
                this.rootParent = null;
                this.parent = null;
                byteBuf.release();
            }
            throw th;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractReferenceCountedByteBuf
    protected final void deallocate() {
        ByteBuf byteBuf = this.parent;
        this.recyclerHandle.recycle(this);
        byteBuf.release();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBufAllocator alloc() {
        return unwrap().alloc();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    @Deprecated
    public final ByteOrder order() {
        return unwrap().order();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isReadOnly() {
        return unwrap().isReadOnly();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final boolean isDirect() {
        return unwrap().isDirect();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean hasArray() {
        return unwrap().hasArray();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public byte[] array() {
        return unwrap().array();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean hasMemoryAddress() {
        return unwrap().hasMemoryAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isContiguous() {
        return unwrap().isContiguous();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final int nioBufferCount() {
        return unwrap().nioBufferCount();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuffer internalNioBuffer(int i, int i2) {
        return nioBuffer(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuf retainedSlice() {
        int readerIndex = readerIndex();
        return retainedSlice(readerIndex, writerIndex() - readerIndex);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf slice(int i, int i2) {
        ensureAccessible();
        return new PooledNonRetainedSlicedByteBuf(this, unwrap(), i, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ByteBuf duplicate0() {
        ensureAccessible();
        return new PooledNonRetainedDuplicateByteBuf(this, unwrap());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class PooledNonRetainedDuplicateByteBuf extends UnpooledDuplicatedByteBuf {
        private final ByteBuf referenceCountDelegate;

        PooledNonRetainedDuplicateByteBuf(ByteBuf byteBuf, AbstractByteBuf abstractByteBuf) {
            super(abstractByteBuf);
            this.referenceCountDelegate = byteBuf;
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractDerivedByteBuf
        boolean isAccessible0() {
            return this.referenceCountDelegate.isAccessible();
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractDerivedByteBuf
        int refCnt0() {
            return this.referenceCountDelegate.refCnt();
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractDerivedByteBuf
        ByteBuf retain0() {
            this.referenceCountDelegate.retain();
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractDerivedByteBuf
        ByteBuf retain0(int i) {
            this.referenceCountDelegate.retain(i);
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractDerivedByteBuf
        ByteBuf touch0() {
            this.referenceCountDelegate.touch();
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractDerivedByteBuf
        ByteBuf touch0(Object obj) {
            this.referenceCountDelegate.touch(obj);
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractDerivedByteBuf
        boolean release0() {
            return this.referenceCountDelegate.release();
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractDerivedByteBuf
        boolean release0(int i) {
            return this.referenceCountDelegate.release(i);
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
        public ByteBuf duplicate() {
            ensureAccessible();
            return new PooledNonRetainedDuplicateByteBuf(this.referenceCountDelegate, this);
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
        public ByteBuf retainedDuplicate() {
            return PooledDuplicatedByteBuf.newInstance(unwrap(), this, readerIndex(), writerIndex());
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.DuplicatedByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
        public ByteBuf slice(int i, int i2) {
            checkIndex(i, i2);
            return new PooledNonRetainedSlicedByteBuf(this.referenceCountDelegate, unwrap(), i, i2);
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
        public ByteBuf retainedSlice() {
            return retainedSlice(readerIndex(), capacity());
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
        public ByteBuf retainedSlice(int i, int i2) {
            return PooledSlicedByteBuf.newInstance(unwrap(), this, i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class PooledNonRetainedSlicedByteBuf extends UnpooledSlicedByteBuf {
        private final ByteBuf referenceCountDelegate;

        PooledNonRetainedSlicedByteBuf(ByteBuf byteBuf, AbstractByteBuf abstractByteBuf, int i, int i2) {
            super(abstractByteBuf, i, i2);
            this.referenceCountDelegate = byteBuf;
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractDerivedByteBuf
        boolean isAccessible0() {
            return this.referenceCountDelegate.isAccessible();
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractDerivedByteBuf
        int refCnt0() {
            return this.referenceCountDelegate.refCnt();
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractDerivedByteBuf
        ByteBuf retain0() {
            this.referenceCountDelegate.retain();
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractDerivedByteBuf
        ByteBuf retain0(int i) {
            this.referenceCountDelegate.retain(i);
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractDerivedByteBuf
        ByteBuf touch0() {
            this.referenceCountDelegate.touch();
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractDerivedByteBuf
        ByteBuf touch0(Object obj) {
            this.referenceCountDelegate.touch(obj);
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractDerivedByteBuf
        boolean release0() {
            return this.referenceCountDelegate.release();
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractDerivedByteBuf
        boolean release0(int i) {
            return this.referenceCountDelegate.release(i);
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractUnpooledSlicedByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
        public ByteBuf duplicate() {
            ensureAccessible();
            return new PooledNonRetainedDuplicateByteBuf(this.referenceCountDelegate, unwrap()).setIndex(idx(readerIndex()), idx(writerIndex()));
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
        public ByteBuf retainedDuplicate() {
            return PooledDuplicatedByteBuf.newInstance(unwrap(), this, idx(readerIndex()), idx(writerIndex()));
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractUnpooledSlicedByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
        public ByteBuf slice(int i, int i2) {
            checkIndex(i, i2);
            return new PooledNonRetainedSlicedByteBuf(this.referenceCountDelegate, unwrap(), idx(i), i2);
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
        public ByteBuf retainedSlice() {
            return retainedSlice(0, capacity());
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
        public ByteBuf retainedSlice(int i, int i2) {
            return PooledSlicedByteBuf.newInstance(unwrap(), this, idx(i), i2);
        }
    }
}
