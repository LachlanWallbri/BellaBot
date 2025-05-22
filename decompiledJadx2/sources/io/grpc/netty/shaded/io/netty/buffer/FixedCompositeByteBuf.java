package io.grpc.netty.shaded.io.netty.buffer;

import io.grpc.netty.shaded.io.netty.util.internal.EmptyArrays;
import io.grpc.netty.shaded.io.netty.util.internal.RecyclableArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.util.Collections;
import kotlin.UShort;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class FixedCompositeByteBuf extends AbstractReferenceCountedByteBuf {
    private static final ByteBuf[] EMPTY = {Unpooled.EMPTY_BUFFER};
    private final ByteBufAllocator allocator;
    private final ByteBuf[] buffers;
    private final int capacity;
    private final boolean direct;
    private final int nioBufferCount;
    private final ByteOrder order;

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isWritable() {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isWritable(int i) {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf unwrap() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FixedCompositeByteBuf(ByteBufAllocator byteBufAllocator, ByteBuf... byteBufArr) {
        super(Integer.MAX_VALUE);
        if (byteBufArr.length == 0) {
            this.buffers = EMPTY;
            this.order = ByteOrder.BIG_ENDIAN;
            this.nioBufferCount = 1;
            this.capacity = 0;
            this.direct = Unpooled.EMPTY_BUFFER.isDirect();
        } else {
            ByteBuf byteBuf = byteBufArr[0];
            this.buffers = byteBufArr;
            int nioBufferCount = byteBuf.nioBufferCount();
            int readableBytes = byteBuf.readableBytes();
            this.order = byteBuf.order();
            boolean z = true;
            for (int i = 1; i < byteBufArr.length; i++) {
                ByteBuf byteBuf2 = byteBufArr[i];
                if (byteBufArr[i].order() != this.order) {
                    throw new IllegalArgumentException("All ByteBufs need to have same ByteOrder");
                }
                nioBufferCount += byteBuf2.nioBufferCount();
                readableBytes += byteBuf2.readableBytes();
                if (!byteBuf2.isDirect()) {
                    z = false;
                }
            }
            this.nioBufferCount = nioBufferCount;
            this.capacity = readableBytes;
            this.direct = z;
        }
        setIndex(0, capacity());
        this.allocator = byteBufAllocator;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf discardReadBytes() {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, ByteBuf byteBuf, int i2, int i3) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, byte[] bArr, int i2, int i3) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, ByteBuffer byteBuffer) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setByte(int i, int i2) {
        throw new ReadOnlyBufferException();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setByte(int i, int i2) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setShort(int i, int i2) {
        throw new ReadOnlyBufferException();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setShort(int i, int i2) {
        throw new ReadOnlyBufferException();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setShortLE(int i, int i2) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setMedium(int i, int i2) {
        throw new ReadOnlyBufferException();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setMedium(int i, int i2) {
        throw new ReadOnlyBufferException();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setMediumLE(int i, int i2) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setInt(int i, int i2) {
        throw new ReadOnlyBufferException();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setInt(int i, int i2) {
        throw new ReadOnlyBufferException();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setIntLE(int i, int i2) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setLong(int i, long j) {
        throw new ReadOnlyBufferException();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setLong(int i, long j) {
        throw new ReadOnlyBufferException();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setLongLE(int i, long j) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int setBytes(int i, InputStream inputStream, int i2) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int setBytes(int i, ScatteringByteChannel scatteringByteChannel, int i2) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int setBytes(int i, FileChannel fileChannel, long j, int i2) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int capacity() {
        return this.capacity;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int maxCapacity() {
        return this.capacity;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf capacity(int i) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBufAllocator alloc() {
        return this.allocator;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteOrder order() {
        return this.order;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isDirect() {
        return this.direct;
    }

    private Component findComponent(int i) {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            ByteBuf[] byteBufArr = this.buffers;
            if (i2 < byteBufArr.length) {
                Component component = null;
                ByteBuf byteBuf = byteBufArr[i2];
                if (byteBuf instanceof Component) {
                    component = (Component) byteBuf;
                    byteBuf = component.buf;
                }
                i3 += byteBuf.readableBytes();
                if (i < i3) {
                    if (component != null) {
                        return component;
                    }
                    Component component2 = new Component(i2, i3 - byteBuf.readableBytes(), byteBuf);
                    this.buffers[i2] = component2;
                    return component2;
                }
                i2++;
            } else {
                throw new IllegalStateException();
            }
        }
    }

    private ByteBuf buffer(int i) {
        ByteBuf byteBuf = this.buffers[i];
        return byteBuf instanceof Component ? ((Component) byteBuf).buf : byteBuf;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public byte getByte(int i) {
        return _getByte(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public byte _getByte(int i) {
        Component findComponent = findComponent(i);
        return findComponent.buf.getByte(i - findComponent.offset);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public short _getShort(int i) {
        Component findComponent = findComponent(i);
        if (i + 2 > findComponent.endOffset) {
            if (order() == ByteOrder.BIG_ENDIAN) {
                return (short) ((_getByte(i + 1) & 255) | ((_getByte(i) & 255) << 8));
            }
            return (short) (((_getByte(i + 1) & 255) << 8) | (_getByte(i) & 255));
        }
        return findComponent.buf.getShort(i - findComponent.offset);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public short _getShortLE(int i) {
        Component findComponent = findComponent(i);
        if (i + 2 > findComponent.endOffset) {
            if (order() == ByteOrder.BIG_ENDIAN) {
                return (short) (((_getByte(i + 1) & 255) << 8) | (_getByte(i) & 255));
            }
            return (short) ((_getByte(i + 1) & 255) | ((_getByte(i) & 255) << 8));
        }
        return findComponent.buf.getShortLE(i - findComponent.offset);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public int _getUnsignedMedium(int i) {
        Component findComponent = findComponent(i);
        if (i + 3 > findComponent.endOffset) {
            if (order() == ByteOrder.BIG_ENDIAN) {
                return (_getByte(i + 2) & 255) | ((_getShort(i) & UShort.MAX_VALUE) << 8);
            }
            return ((_getByte(i + 2) & 255) << 16) | (_getShort(i) & UShort.MAX_VALUE);
        }
        return findComponent.buf.getUnsignedMedium(i - findComponent.offset);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public int _getUnsignedMediumLE(int i) {
        Component findComponent = findComponent(i);
        if (i + 3 > findComponent.endOffset) {
            if (order() == ByteOrder.BIG_ENDIAN) {
                return ((_getByte(i + 2) & 255) << 16) | (_getShortLE(i) & UShort.MAX_VALUE);
            }
            return (_getByte(i + 2) & 255) | ((_getShortLE(i) & UShort.MAX_VALUE) << 8);
        }
        return findComponent.buf.getUnsignedMediumLE(i - findComponent.offset);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public int _getInt(int i) {
        Component findComponent = findComponent(i);
        if (i + 4 > findComponent.endOffset) {
            if (order() == ByteOrder.BIG_ENDIAN) {
                return (_getShort(i + 2) & UShort.MAX_VALUE) | ((_getShort(i) & UShort.MAX_VALUE) << 16);
            }
            return ((_getShort(i + 2) & UShort.MAX_VALUE) << 16) | (_getShort(i) & UShort.MAX_VALUE);
        }
        return findComponent.buf.getInt(i - findComponent.offset);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public int _getIntLE(int i) {
        Component findComponent = findComponent(i);
        if (i + 4 > findComponent.endOffset) {
            if (order() == ByteOrder.BIG_ENDIAN) {
                return ((_getShortLE(i + 2) & UShort.MAX_VALUE) << 16) | (_getShortLE(i) & UShort.MAX_VALUE);
            }
            return (_getShortLE(i + 2) & UShort.MAX_VALUE) | ((_getShortLE(i) & UShort.MAX_VALUE) << 16);
        }
        return findComponent.buf.getIntLE(i - findComponent.offset);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public long _getLong(int i) {
        Component findComponent = findComponent(i);
        if (i + 8 > findComponent.endOffset) {
            if (order() == ByteOrder.BIG_ENDIAN) {
                return ((_getInt(i) & 4294967295L) << 32) | (_getInt(i + 4) & 4294967295L);
            }
            return (_getInt(i) & 4294967295L) | ((4294967295L & _getInt(i + 4)) << 32);
        }
        return findComponent.buf.getLong(i - findComponent.offset);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public long _getLongLE(int i) {
        Component findComponent = findComponent(i);
        if (i + 8 > findComponent.endOffset) {
            if (order() == ByteOrder.BIG_ENDIAN) {
                return (_getIntLE(i) & 4294967295L) | ((4294967295L & _getIntLE(i + 4)) << 32);
            }
            return ((_getIntLE(i) & 4294967295L) << 32) | (_getIntLE(i + 4) & 4294967295L);
        }
        return findComponent.buf.getLongLE(i - findComponent.offset);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, byte[] bArr, int i2, int i3) {
        checkDstIndex(i, i3, i2, bArr.length);
        if (i3 == 0) {
            return this;
        }
        Component findComponent = findComponent(i);
        int i4 = findComponent.index;
        int i5 = findComponent.offset;
        ByteBuf byteBuf = findComponent.buf;
        while (true) {
            int i6 = i - i5;
            int min = Math.min(i3, byteBuf.readableBytes() - i6);
            byteBuf.getBytes(i6, bArr, i2, min);
            i += min;
            i2 += min;
            i3 -= min;
            i5 += byteBuf.readableBytes();
            if (i3 <= 0) {
                return this;
            }
            i4++;
            byteBuf = buffer(i4);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, ByteBuffer byteBuffer) {
        int limit = byteBuffer.limit();
        int remaining = byteBuffer.remaining();
        checkIndex(i, remaining);
        if (remaining == 0) {
            return this;
        }
        try {
            Component findComponent = findComponent(i);
            int i2 = findComponent.index;
            int i3 = findComponent.offset;
            ByteBuf byteBuf = findComponent.buf;
            while (true) {
                int i4 = i - i3;
                int min = Math.min(remaining, byteBuf.readableBytes() - i4);
                byteBuffer.limit(byteBuffer.position() + min);
                byteBuf.getBytes(i4, byteBuffer);
                i += min;
                remaining -= min;
                i3 += byteBuf.readableBytes();
                if (remaining <= 0) {
                    return this;
                }
                i2++;
                byteBuf = buffer(i2);
            }
        } finally {
            byteBuffer.limit(limit);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, ByteBuf byteBuf, int i2, int i3) {
        checkDstIndex(i, i3, i2, byteBuf.capacity());
        if (i3 == 0) {
            return this;
        }
        Component findComponent = findComponent(i);
        int i4 = findComponent.index;
        int i5 = findComponent.offset;
        ByteBuf byteBuf2 = findComponent.buf;
        while (true) {
            int i6 = i - i5;
            int min = Math.min(i3, byteBuf2.readableBytes() - i6);
            byteBuf2.getBytes(i6, byteBuf, i2, min);
            i += min;
            i2 += min;
            i3 -= min;
            i5 += byteBuf2.readableBytes();
            if (i3 <= 0) {
                return this;
            }
            i4++;
            byteBuf2 = buffer(i4);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getBytes(int i, GatheringByteChannel gatheringByteChannel, int i2) throws IOException {
        if (nioBufferCount() == 1) {
            return gatheringByteChannel.write(internalNioBuffer(i, i2));
        }
        long write = gatheringByteChannel.write(nioBuffers(i, i2));
        if (write > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) write;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getBytes(int i, FileChannel fileChannel, long j, int i2) throws IOException {
        if (nioBufferCount() == 1) {
            return fileChannel.write(internalNioBuffer(i, i2), j);
        }
        long j2 = 0;
        for (int i3 = 0; i3 < nioBuffers(i, i2).length; i3++) {
            j2 += fileChannel.write(r7[i3], j + j2);
        }
        if (j2 > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) j2;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, OutputStream outputStream, int i2) throws IOException {
        checkIndex(i, i2);
        if (i2 == 0) {
            return this;
        }
        Component findComponent = findComponent(i);
        int i3 = findComponent.index;
        int i4 = findComponent.offset;
        ByteBuf byteBuf = findComponent.buf;
        while (true) {
            int i5 = i - i4;
            int min = Math.min(i2, byteBuf.readableBytes() - i5);
            byteBuf.getBytes(i5, outputStream, min);
            i += min;
            i2 -= min;
            i4 += byteBuf.readableBytes();
            if (i2 <= 0) {
                return this;
            }
            i3++;
            byteBuf = buffer(i3);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf copy(int i, int i2) {
        checkIndex(i, i2);
        ByteBuf buffer = alloc().buffer(i2);
        try {
            buffer.writeBytes(this, i, i2);
            return buffer;
        } catch (Throwable th) {
            buffer.release();
            throw th;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int nioBufferCount() {
        return this.nioBufferCount;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer nioBuffer(int i, int i2) {
        checkIndex(i, i2);
        if (this.buffers.length == 1) {
            ByteBuf buffer = buffer(0);
            if (buffer.nioBufferCount() == 1) {
                return buffer.nioBuffer(i, i2);
            }
        }
        ByteBuffer order = ByteBuffer.allocate(i2).order(order());
        for (ByteBuffer byteBuffer : nioBuffers(i, i2)) {
            order.put(byteBuffer);
        }
        order.flip();
        return order;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer internalNioBuffer(int i, int i2) {
        if (this.buffers.length == 1) {
            return buffer(0).internalNioBuffer(i, i2);
        }
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer[] nioBuffers(int i, int i2) {
        checkIndex(i, i2);
        if (i2 == 0) {
            return EmptyArrays.EMPTY_BYTE_BUFFERS;
        }
        RecyclableArrayList newInstance = RecyclableArrayList.newInstance(this.buffers.length);
        try {
            Component findComponent = findComponent(i);
            int i3 = findComponent.index;
            int i4 = findComponent.offset;
            ByteBuf byteBuf = findComponent.buf;
            while (true) {
                int i5 = i - i4;
                int min = Math.min(i2, byteBuf.readableBytes() - i5);
                int nioBufferCount = byteBuf.nioBufferCount();
                if (nioBufferCount == 0) {
                    throw new UnsupportedOperationException();
                }
                if (nioBufferCount == 1) {
                    newInstance.add(byteBuf.nioBuffer(i5, min));
                } else {
                    Collections.addAll(newInstance, byteBuf.nioBuffers(i5, min));
                }
                i += min;
                i2 -= min;
                i4 += byteBuf.readableBytes();
                if (i2 > 0) {
                    i3++;
                    byteBuf = buffer(i3);
                } else {
                    return (ByteBuffer[]) newInstance.toArray(new ByteBuffer[0]);
                }
            }
        } finally {
            newInstance.recycle();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean hasArray() {
        int length = this.buffers.length;
        if (length == 0) {
            return true;
        }
        if (length != 1) {
            return false;
        }
        return buffer(0).hasArray();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public byte[] array() {
        int length = this.buffers.length;
        if (length == 0) {
            return EmptyArrays.EMPTY_BYTES;
        }
        if (length == 1) {
            return buffer(0).array();
        }
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int arrayOffset() {
        int length = this.buffers.length;
        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            return buffer(0).arrayOffset();
        }
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean hasMemoryAddress() {
        int length = this.buffers.length;
        if (length == 0) {
            return Unpooled.EMPTY_BUFFER.hasMemoryAddress();
        }
        if (length != 1) {
            return false;
        }
        return buffer(0).hasMemoryAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long memoryAddress() {
        int length = this.buffers.length;
        if (length == 0) {
            return Unpooled.EMPTY_BUFFER.memoryAddress();
        }
        if (length == 1) {
            return buffer(0).memoryAddress();
        }
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractReferenceCountedByteBuf
    protected void deallocate() {
        for (int i = 0; i < this.buffers.length; i++) {
            buffer(i).release();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public String toString() {
        return super.toString().substring(0, r0.length() - 1) + ", components=" + this.buffers.length + ')';
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Component extends WrappedByteBuf {
        private final int endOffset;
        private final int index;
        private final int offset;

        Component(int i, int i2, ByteBuf byteBuf) {
            super(byteBuf);
            this.index = i;
            this.offset = i2;
            this.endOffset = i2 + byteBuf.readableBytes();
        }
    }
}
