package io.grpc.netty.shaded.io.netty.buffer;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.nio.ByteOrder;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class UnreleasableByteBuf extends WrappedByteBuf {
    private SwappedByteBuf swappedBuf;

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public boolean release() {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public boolean release(int i) {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public ByteBuf retain() {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public ByteBuf retain(int i) {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public ByteBuf touch() {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public ByteBuf touch(Object obj) {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UnreleasableByteBuf(ByteBuf byteBuf) {
        super(byteBuf instanceof UnreleasableByteBuf ? byteBuf.unwrap() : byteBuf);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf order(ByteOrder byteOrder) {
        if (ObjectUtil.checkNotNull(byteOrder, "endianness") == order()) {
            return this;
        }
        SwappedByteBuf swappedByteBuf = this.swappedBuf;
        if (swappedByteBuf != null) {
            return swappedByteBuf;
        }
        SwappedByteBuf swappedByteBuf2 = new SwappedByteBuf(this);
        this.swappedBuf = swappedByteBuf2;
        return swappedByteBuf2;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf asReadOnly() {
        return this.buf.isReadOnly() ? this : new UnreleasableByteBuf(this.buf.asReadOnly());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readSlice(int i) {
        return new UnreleasableByteBuf(this.buf.readSlice(i));
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readRetainedSlice(int i) {
        return readSlice(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf slice() {
        return new UnreleasableByteBuf(this.buf.slice());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf retainedSlice() {
        return slice();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf slice(int i, int i2) {
        return new UnreleasableByteBuf(this.buf.slice(i, i2));
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf retainedSlice(int i, int i2) {
        return slice(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf duplicate() {
        return new UnreleasableByteBuf(this.buf.duplicate());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf retainedDuplicate() {
        return duplicate();
    }
}
