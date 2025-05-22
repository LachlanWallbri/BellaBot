package io.grpc.netty.shaded.io.netty.buffer;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectPool;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class PooledHeapByteBuf extends PooledByteBuf<byte[]> {
    private static final ObjectPool<PooledHeapByteBuf> RECYCLER = ObjectPool.newPool(new ObjectPool.ObjectCreator<PooledHeapByteBuf>() { // from class: io.grpc.netty.shaded.io.netty.buffer.PooledHeapByteBuf.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.grpc.netty.shaded.io.netty.util.internal.ObjectPool.ObjectCreator
        public PooledHeapByteBuf newObject(ObjectPool.Handle<PooledHeapByteBuf> handle) {
            return new PooledHeapByteBuf(handle, 0);
        }
    });

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final boolean hasArray() {
        return true;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final boolean hasMemoryAddress() {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final boolean isDirect() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PooledHeapByteBuf newInstance(int i) {
        PooledHeapByteBuf pooledHeapByteBuf = RECYCLER.get();
        pooledHeapByteBuf.reuse(i);
        return pooledHeapByteBuf;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PooledHeapByteBuf(ObjectPool.Handle<? extends PooledHeapByteBuf> handle, int i) {
        super(handle, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public byte _getByte(int i) {
        return HeapByteBufUtil.getByte((byte[]) this.memory, idx(i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public short _getShort(int i) {
        return HeapByteBufUtil.getShort((byte[]) this.memory, idx(i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public short _getShortLE(int i) {
        return HeapByteBufUtil.getShortLE((byte[]) this.memory, idx(i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public int _getUnsignedMedium(int i) {
        return HeapByteBufUtil.getUnsignedMedium((byte[]) this.memory, idx(i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public int _getUnsignedMediumLE(int i) {
        return HeapByteBufUtil.getUnsignedMediumLE((byte[]) this.memory, idx(i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public int _getInt(int i) {
        return HeapByteBufUtil.getInt((byte[]) this.memory, idx(i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public int _getIntLE(int i) {
        return HeapByteBufUtil.getIntLE((byte[]) this.memory, idx(i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public long _getLong(int i) {
        return HeapByteBufUtil.getLong((byte[]) this.memory, idx(i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public long _getLongLE(int i) {
        return HeapByteBufUtil.getLongLE((byte[]) this.memory, idx(i));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuf getBytes(int i, ByteBuf byteBuf, int i2, int i3) {
        checkDstIndex(i, i3, i2, byteBuf.capacity());
        if (byteBuf.hasMemoryAddress()) {
            PlatformDependent.copyMemory((byte[]) this.memory, idx(i), i2 + byteBuf.memoryAddress(), i3);
        } else if (byteBuf.hasArray()) {
            getBytes(i, byteBuf.array(), byteBuf.arrayOffset() + i2, i3);
        } else {
            byteBuf.setBytes(i2, (byte[]) this.memory, idx(i), i3);
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuf getBytes(int i, byte[] bArr, int i2, int i3) {
        checkDstIndex(i, i3, i2, bArr.length);
        System.arraycopy(this.memory, idx(i), bArr, i2, i3);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuf getBytes(int i, ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining();
        checkIndex(i, remaining);
        byteBuffer.put((byte[]) this.memory, idx(i), remaining);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuf getBytes(int i, OutputStream outputStream, int i2) throws IOException {
        checkIndex(i, i2);
        outputStream.write((byte[]) this.memory, idx(i), i2);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setByte(int i, int i2) {
        HeapByteBufUtil.setByte((byte[]) this.memory, idx(i), i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setShort(int i, int i2) {
        HeapByteBufUtil.setShort((byte[]) this.memory, idx(i), i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setShortLE(int i, int i2) {
        HeapByteBufUtil.setShortLE((byte[]) this.memory, idx(i), i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setMedium(int i, int i2) {
        HeapByteBufUtil.setMedium((byte[]) this.memory, idx(i), i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setMediumLE(int i, int i2) {
        HeapByteBufUtil.setMediumLE((byte[]) this.memory, idx(i), i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setInt(int i, int i2) {
        HeapByteBufUtil.setInt((byte[]) this.memory, idx(i), i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setIntLE(int i, int i2) {
        HeapByteBufUtil.setIntLE((byte[]) this.memory, idx(i), i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setLong(int i, long j) {
        HeapByteBufUtil.setLong((byte[]) this.memory, idx(i), j);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setLongLE(int i, long j) {
        HeapByteBufUtil.setLongLE((byte[]) this.memory, idx(i), j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuf setBytes(int i, ByteBuf byteBuf, int i2, int i3) {
        checkSrcIndex(i, i3, i2, byteBuf.capacity());
        if (byteBuf.hasMemoryAddress()) {
            PlatformDependent.copyMemory(byteBuf.memoryAddress() + i2, (byte[]) this.memory, idx(i), i3);
        } else if (byteBuf.hasArray()) {
            setBytes(i, byteBuf.array(), byteBuf.arrayOffset() + i2, i3);
        } else {
            byteBuf.getBytes(i2, (byte[]) this.memory, idx(i), i3);
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuf setBytes(int i, byte[] bArr, int i2, int i3) {
        checkSrcIndex(i, i3, i2, bArr.length);
        System.arraycopy(bArr, i2, this.memory, idx(i), i3);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuf setBytes(int i, ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining();
        checkIndex(i, remaining);
        byteBuffer.get((byte[]) this.memory, idx(i), remaining);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final int setBytes(int i, InputStream inputStream, int i2) throws IOException {
        checkIndex(i, i2);
        return inputStream.read((byte[]) this.memory, idx(i), i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final ByteBuf copy(int i, int i2) {
        checkIndex(i, i2);
        return alloc().heapBuffer(i2, maxCapacity()).writeBytes((byte[]) this.memory, idx(i), i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.buffer.PooledByteBuf
    public final ByteBuffer duplicateInternalNioBuffer(int i, int i2) {
        checkIndex(i, i2);
        return ByteBuffer.wrap((byte[]) this.memory, idx(i), i2).slice();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final byte[] array() {
        ensureAccessible();
        return (byte[]) this.memory;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final int arrayOffset() {
        return this.offset;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final long memoryAddress() {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.PooledByteBuf
    public final ByteBuffer newInternalNioBuffer(byte[] bArr) {
        return ByteBuffer.wrap(bArr);
    }
}
