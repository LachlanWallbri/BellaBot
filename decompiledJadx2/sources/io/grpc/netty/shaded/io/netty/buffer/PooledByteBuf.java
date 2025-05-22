package io.grpc.netty.shaded.io.netty.buffer;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectPool;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class PooledByteBuf<T> extends AbstractReferenceCountedByteBuf {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private ByteBufAllocator allocator;
    PoolThreadCache cache;
    protected PoolChunk<T> chunk;
    protected long handle;
    protected int length;
    int maxLength;
    protected T memory;
    protected int offset;
    private final ObjectPool.Handle<PooledByteBuf<T>> recyclerHandle;
    ByteBuffer tmpNioBuf;

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final boolean isContiguous() {
        return true;
    }

    protected abstract ByteBuffer newInternalNioBuffer(T t);

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final int nioBufferCount() {
        return 1;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuf unwrap() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public PooledByteBuf(ObjectPool.Handle<? extends PooledByteBuf<T>> handle, int i) {
        super(i);
        this.recyclerHandle = handle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void init(PoolChunk<T> poolChunk, ByteBuffer byteBuffer, long j, int i, int i2, int i3, PoolThreadCache poolThreadCache) {
        init0(poolChunk, byteBuffer, j, i, i2, i3, poolThreadCache);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initUnpooled(PoolChunk<T> poolChunk, int i) {
        init0(poolChunk, null, 0L, poolChunk.offset, i, i, null);
    }

    private void init0(PoolChunk<T> poolChunk, ByteBuffer byteBuffer, long j, int i, int i2, int i3, PoolThreadCache poolThreadCache) {
        this.chunk = poolChunk;
        this.memory = poolChunk.memory;
        this.tmpNioBuf = byteBuffer;
        this.allocator = poolChunk.arena.parent;
        this.cache = poolThreadCache;
        this.handle = j;
        this.offset = i;
        this.length = i2;
        this.maxLength = i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void reuse(int i) {
        maxCapacity(i);
        resetRefCnt();
        setIndex0(0, 0);
        discardMarks();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final int capacity() {
        return this.length;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int maxFastWritableBytes() {
        return Math.min(this.maxLength, maxCapacity()) - this.writerIndex;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuf capacity(int i) {
        if (i == this.length) {
            ensureAccessible();
            return this;
        }
        checkNewCapacity(i);
        if (!this.chunk.unpooled) {
            if (i > this.length) {
                if (i <= this.maxLength) {
                    this.length = i;
                    return this;
                }
            } else {
                int i2 = this.maxLength;
                if (i > (i2 >>> 1) && (i2 > 512 || i > i2 - 16)) {
                    this.length = i;
                    trimIndicesToCapacity(i);
                    return this;
                }
            }
        }
        this.chunk.arena.reallocate(this, i, true);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBufAllocator alloc() {
        return this.allocator;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteOrder order() {
        return ByteOrder.BIG_ENDIAN;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuf retainedDuplicate() {
        return PooledDuplicatedByteBuf.newInstance(this, this, readerIndex(), writerIndex());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuf retainedSlice() {
        int readerIndex = readerIndex();
        return retainedSlice(readerIndex, writerIndex() - readerIndex);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuf retainedSlice(int i, int i2) {
        return PooledSlicedByteBuf.newInstance(this, this, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ByteBuffer internalNioBuffer() {
        ByteBuffer byteBuffer = this.tmpNioBuf;
        if (byteBuffer == null) {
            ByteBuffer newInternalNioBuffer = newInternalNioBuffer(this.memory);
            this.tmpNioBuf = newInternalNioBuffer;
            return newInternalNioBuffer;
        }
        byteBuffer.clear();
        return byteBuffer;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractReferenceCountedByteBuf
    protected final void deallocate() {
        long j = this.handle;
        if (j >= 0) {
            this.handle = -1L;
            this.memory = null;
            this.chunk.arena.free(this.chunk, this.tmpNioBuf, j, this.maxLength, this.cache);
            this.tmpNioBuf = null;
            this.chunk = null;
            recycle();
        }
    }

    private void recycle() {
        this.recyclerHandle.recycle(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int idx(int i) {
        return this.offset + i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ByteBuffer _internalNioBuffer(int i, int i2, boolean z) {
        int idx = idx(i);
        ByteBuffer newInternalNioBuffer = z ? newInternalNioBuffer(this.memory) : internalNioBuffer();
        newInternalNioBuffer.limit(i2 + idx).position(idx);
        return newInternalNioBuffer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteBuffer duplicateInternalNioBuffer(int i, int i2) {
        checkIndex(i, i2);
        return _internalNioBuffer(i, i2, true);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuffer internalNioBuffer(int i, int i2) {
        checkIndex(i, i2);
        return _internalNioBuffer(i, i2, false);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuffer nioBuffer(int i, int i2) {
        return duplicateInternalNioBuffer(i, i2).slice();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuffer[] nioBuffers(int i, int i2) {
        return new ByteBuffer[]{nioBuffer(i, i2)};
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final int getBytes(int i, GatheringByteChannel gatheringByteChannel, int i2) throws IOException {
        return gatheringByteChannel.write(duplicateInternalNioBuffer(i, i2));
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final int readBytes(GatheringByteChannel gatheringByteChannel, int i) throws IOException {
        checkReadableBytes(i);
        int write = gatheringByteChannel.write(_internalNioBuffer(this.readerIndex, i, false));
        this.readerIndex += write;
        return write;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final int getBytes(int i, FileChannel fileChannel, long j, int i2) throws IOException {
        return fileChannel.write(duplicateInternalNioBuffer(i, i2), j);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final int readBytes(FileChannel fileChannel, long j, int i) throws IOException {
        checkReadableBytes(i);
        int write = fileChannel.write(_internalNioBuffer(this.readerIndex, i, false), j);
        this.readerIndex += write;
        return write;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final int setBytes(int i, ScatteringByteChannel scatteringByteChannel, int i2) throws IOException {
        try {
            return scatteringByteChannel.read(internalNioBuffer(i, i2));
        } catch (ClosedChannelException unused) {
            return -1;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final int setBytes(int i, FileChannel fileChannel, long j, int i2) throws IOException {
        try {
            return fileChannel.read(internalNioBuffer(i, i2), j);
        } catch (ClosedChannelException unused) {
            return -1;
        }
    }
}
