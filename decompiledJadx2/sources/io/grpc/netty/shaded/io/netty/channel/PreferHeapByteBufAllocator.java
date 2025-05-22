package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class PreferHeapByteBufAllocator implements ByteBufAllocator {
    private final ByteBufAllocator allocator;

    public PreferHeapByteBufAllocator(ByteBufAllocator byteBufAllocator) {
        this.allocator = (ByteBufAllocator) ObjectUtil.checkNotNull(byteBufAllocator, "allocator");
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf buffer() {
        return this.allocator.heapBuffer();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf buffer(int i) {
        return this.allocator.heapBuffer(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf buffer(int i, int i2) {
        return this.allocator.heapBuffer(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf ioBuffer() {
        return this.allocator.heapBuffer();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf ioBuffer(int i) {
        return this.allocator.heapBuffer(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf ioBuffer(int i, int i2) {
        return this.allocator.heapBuffer(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf heapBuffer() {
        return this.allocator.heapBuffer();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf heapBuffer(int i) {
        return this.allocator.heapBuffer(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf heapBuffer(int i, int i2) {
        return this.allocator.heapBuffer(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf directBuffer() {
        return this.allocator.directBuffer();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf directBuffer(int i) {
        return this.allocator.directBuffer(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public ByteBuf directBuffer(int i, int i2) {
        return this.allocator.directBuffer(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public CompositeByteBuf compositeBuffer() {
        return this.allocator.compositeHeapBuffer();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public CompositeByteBuf compositeBuffer(int i) {
        return this.allocator.compositeHeapBuffer(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public CompositeByteBuf compositeHeapBuffer() {
        return this.allocator.compositeHeapBuffer();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public CompositeByteBuf compositeHeapBuffer(int i) {
        return this.allocator.compositeHeapBuffer(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public CompositeByteBuf compositeDirectBuffer() {
        return this.allocator.compositeDirectBuffer();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public CompositeByteBuf compositeDirectBuffer(int i) {
        return this.allocator.compositeDirectBuffer(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public boolean isDirectBufferPooled() {
        return this.allocator.isDirectBufferPooled();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator
    public int calculateNewCapacity(int i, int i2) {
        return this.allocator.calculateNewCapacity(i, i2);
    }
}
