package io.grpc.netty.shaded.io.netty.buffer;

import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class UnsafeDirectSwappedByteBuf extends AbstractUnsafeSwappedByteBuf {
    /* JADX INFO: Access modifiers changed from: package-private */
    public UnsafeDirectSwappedByteBuf(AbstractByteBuf abstractByteBuf) {
        super(abstractByteBuf);
    }

    private static long addr(AbstractByteBuf abstractByteBuf, int i) {
        return abstractByteBuf.memoryAddress() + i;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractUnsafeSwappedByteBuf
    protected long _getLong(AbstractByteBuf abstractByteBuf, int i) {
        return PlatformDependent.getLong(addr(abstractByteBuf, i));
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractUnsafeSwappedByteBuf
    protected int _getInt(AbstractByteBuf abstractByteBuf, int i) {
        return PlatformDependent.getInt(addr(abstractByteBuf, i));
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractUnsafeSwappedByteBuf
    protected short _getShort(AbstractByteBuf abstractByteBuf, int i) {
        return PlatformDependent.getShort(addr(abstractByteBuf, i));
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractUnsafeSwappedByteBuf
    protected void _setShort(AbstractByteBuf abstractByteBuf, int i, short s) {
        PlatformDependent.putShort(addr(abstractByteBuf, i), s);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractUnsafeSwappedByteBuf
    protected void _setInt(AbstractByteBuf abstractByteBuf, int i, int i2) {
        PlatformDependent.putInt(addr(abstractByteBuf, i), i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractUnsafeSwappedByteBuf
    protected void _setLong(AbstractByteBuf abstractByteBuf, int i, long j) {
        PlatformDependent.putLong(addr(abstractByteBuf, i), j);
    }
}
