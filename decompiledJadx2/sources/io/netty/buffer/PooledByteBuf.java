package io.netty.buffer;

import io.netty.util.Recycler;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes.dex */
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
    private final Recycler.Handle<PooledByteBuf<T>> recyclerHandle;
    private ByteBuffer tmpNioBuf;

    protected abstract ByteBuffer newInternalNioBuffer(T t);

    @Override // io.netty.buffer.ByteBuf
    public final ByteBuf unwrap() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public PooledByteBuf(Recycler.Handle<? extends PooledByteBuf<T>> handle, int i) {
        super(i);
        this.recyclerHandle = handle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void init(PoolChunk<T> poolChunk, long j, int i, int i2, int i3, PoolThreadCache poolThreadCache) {
        init0(poolChunk, j, i, i2, i3, poolThreadCache);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initUnpooled(PoolChunk<T> poolChunk, int i) {
        init0(poolChunk, 0L, poolChunk.offset, i, i, null);
    }

    private void init0(PoolChunk<T> poolChunk, long j, int i, int i2, int i3, PoolThreadCache poolThreadCache) {
        this.chunk = poolChunk;
        this.memory = poolChunk.memory;
        this.allocator = poolChunk.arena.parent;
        this.cache = poolThreadCache;
        this.handle = j;
        this.offset = i;
        this.length = i2;
        this.maxLength = i3;
        this.tmpNioBuf = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void reuse(int i) {
        maxCapacity(i);
        setRefCnt(1);
        setIndex0(0, 0);
        discardMarks();
    }

    @Override // io.netty.buffer.ByteBuf
    public final int capacity() {
        return this.length;
    }

    @Override // io.netty.buffer.ByteBuf
    public final ByteBuf capacity(int i) {
        checkNewCapacity(i);
        if (this.chunk.unpooled) {
            if (i == this.length) {
                return this;
            }
        } else {
            int i2 = this.length;
            if (i <= i2) {
                if (i < i2) {
                    int i3 = this.maxLength;
                    if (i > (i3 >>> 1)) {
                        if (i3 > 512) {
                            this.length = i;
                            setIndex(Math.min(readerIndex(), i), Math.min(writerIndex(), i));
                            return this;
                        }
                        if (i > i3 - 16) {
                            this.length = i;
                            setIndex(Math.min(readerIndex(), i), Math.min(writerIndex(), i));
                            return this;
                        }
                    }
                }
                return this;
            }
            if (i <= this.maxLength) {
                this.length = i;
                return this;
            }
        }
        this.chunk.arena.reallocate(this, i, true);
        return this;
    }

    @Override // io.netty.buffer.ByteBuf
    public final ByteBufAllocator alloc() {
        return this.allocator;
    }

    @Override // io.netty.buffer.ByteBuf
    public final ByteOrder order() {
        return ByteOrder.BIG_ENDIAN;
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public final ByteBuf retainedDuplicate() {
        return PooledDuplicatedByteBuf.newInstance(this, this, readerIndex(), writerIndex());
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public final ByteBuf retainedSlice() {
        int readerIndex = readerIndex();
        return retainedSlice(readerIndex, writerIndex() - readerIndex);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public final ByteBuf retainedSlice(int i, int i2) {
        return PooledSlicedByteBuf.newInstance(this, this, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ByteBuffer internalNioBuffer() {
        ByteBuffer byteBuffer = this.tmpNioBuf;
        if (byteBuffer != null) {
            return byteBuffer;
        }
        ByteBuffer newInternalNioBuffer = newInternalNioBuffer(this.memory);
        this.tmpNioBuf = newInternalNioBuffer;
        return newInternalNioBuffer;
    }

    @Override // io.netty.buffer.AbstractReferenceCountedByteBuf
    protected final void deallocate() {
        long j = this.handle;
        if (j >= 0) {
            this.handle = -1L;
            this.memory = null;
            this.tmpNioBuf = null;
            this.chunk.arena.free(this.chunk, j, this.maxLength, this.cache);
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
}
