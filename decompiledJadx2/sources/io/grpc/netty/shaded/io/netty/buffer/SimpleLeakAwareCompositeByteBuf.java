package io.grpc.netty.shaded.io.netty.buffer;

import io.grpc.netty.shaded.io.netty.util.ResourceLeakTracker;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.nio.ByteOrder;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class SimpleLeakAwareCompositeByteBuf extends WrappedCompositeByteBuf {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    final ResourceLeakTracker<ByteBuf> leak;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SimpleLeakAwareCompositeByteBuf(CompositeByteBuf compositeByteBuf, ResourceLeakTracker<ByteBuf> resourceLeakTracker) {
        super(compositeByteBuf);
        this.leak = (ResourceLeakTracker) ObjectUtil.checkNotNull(resourceLeakTracker, "leak");
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedCompositeByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractReferenceCountedByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public boolean release() {
        ByteBuf unwrap = unwrap();
        if (!super.release()) {
            return false;
        }
        closeLeak(unwrap);
        return true;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedCompositeByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractReferenceCountedByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public boolean release(int i) {
        ByteBuf unwrap = unwrap();
        if (!super.release(i)) {
            return false;
        }
        closeLeak(unwrap);
        return true;
    }

    private void closeLeak(ByteBuf byteBuf) {
        this.leak.close(byteBuf);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedCompositeByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf order(ByteOrder byteOrder) {
        return order() == byteOrder ? this : newLeakAwareByteBuf(super.order(byteOrder));
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedCompositeByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf slice() {
        return newLeakAwareByteBuf(super.slice());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedCompositeByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf retainedSlice() {
        return newLeakAwareByteBuf(super.retainedSlice());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedCompositeByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf slice(int i, int i2) {
        return newLeakAwareByteBuf(super.slice(i, i2));
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedCompositeByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf retainedSlice(int i, int i2) {
        return newLeakAwareByteBuf(super.retainedSlice(i, i2));
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedCompositeByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf duplicate() {
        return newLeakAwareByteBuf(super.duplicate());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedCompositeByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf retainedDuplicate() {
        return newLeakAwareByteBuf(super.retainedDuplicate());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedCompositeByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readSlice(int i) {
        return newLeakAwareByteBuf(super.readSlice(i));
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedCompositeByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readRetainedSlice(int i) {
        return newLeakAwareByteBuf(super.readRetainedSlice(i));
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedCompositeByteBuf, io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf asReadOnly() {
        return newLeakAwareByteBuf(super.asReadOnly());
    }

    private SimpleLeakAwareByteBuf newLeakAwareByteBuf(ByteBuf byteBuf) {
        return newLeakAwareByteBuf(byteBuf, unwrap(), this.leak);
    }

    protected SimpleLeakAwareByteBuf newLeakAwareByteBuf(ByteBuf byteBuf, ByteBuf byteBuf2, ResourceLeakTracker<ByteBuf> resourceLeakTracker) {
        return new SimpleLeakAwareByteBuf(byteBuf, byteBuf2, resourceLeakTracker);
    }
}
