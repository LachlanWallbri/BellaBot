package io.netty.buffer;

import io.netty.util.internal.PlatformDependent;
import java.nio.ByteBuffer;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public final class ReadOnlyUnsafeDirectByteBuf extends ReadOnlyByteBufferBuf {
    private final long memoryAddress;

    @Override // io.netty.buffer.ReadOnlyByteBufferBuf, io.netty.buffer.ByteBuf
    public boolean hasMemoryAddress() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReadOnlyUnsafeDirectByteBuf(ByteBufAllocator byteBufAllocator, ByteBuffer byteBuffer) {
        super(byteBufAllocator, byteBuffer);
        this.memoryAddress = PlatformDependent.directBufferAddress(this.buffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.buffer.ReadOnlyByteBufferBuf, io.netty.buffer.AbstractByteBuf
    public byte _getByte(int i) {
        return UnsafeByteBufUtil.getByte(addr(i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.buffer.ReadOnlyByteBufferBuf, io.netty.buffer.AbstractByteBuf
    public short _getShort(int i) {
        return UnsafeByteBufUtil.getShort(addr(i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.buffer.ReadOnlyByteBufferBuf, io.netty.buffer.AbstractByteBuf
    public int _getUnsignedMedium(int i) {
        return UnsafeByteBufUtil.getUnsignedMedium(addr(i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.buffer.ReadOnlyByteBufferBuf, io.netty.buffer.AbstractByteBuf
    public int _getInt(int i) {
        return UnsafeByteBufUtil.getInt(addr(i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.buffer.ReadOnlyByteBufferBuf, io.netty.buffer.AbstractByteBuf
    public long _getLong(int i) {
        return UnsafeByteBufUtil.getLong(addr(i));
    }

    @Override // io.netty.buffer.ReadOnlyByteBufferBuf, io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, ByteBuf byteBuf, int i2, int i3) {
        checkIndex(i, i3);
        if (byteBuf == null) {
            throw new NullPointerException("dst");
        }
        if (i2 < 0 || i2 > byteBuf.capacity() - i3) {
            throw new IndexOutOfBoundsException("dstIndex: " + i2);
        }
        if (byteBuf.hasMemoryAddress()) {
            PlatformDependent.copyMemory(addr(i), i2 + byteBuf.memoryAddress(), i3);
        } else if (byteBuf.hasArray()) {
            PlatformDependent.copyMemory(addr(i), byteBuf.array(), byteBuf.arrayOffset() + i2, i3);
        } else {
            byteBuf.setBytes(i2, this, i, i3);
        }
        return this;
    }

    @Override // io.netty.buffer.ReadOnlyByteBufferBuf, io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, byte[] bArr, int i2, int i3) {
        checkIndex(i, i3);
        if (bArr == null) {
            throw new NullPointerException("dst");
        }
        if (i2 < 0 || i2 > bArr.length - i3) {
            throw new IndexOutOfBoundsException(String.format("dstIndex: %d, length: %d (expected: range(0, %d))", Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(bArr.length)));
        }
        if (i3 != 0) {
            PlatformDependent.copyMemory(addr(i), bArr, i2, i3);
        }
        return this;
    }

    @Override // io.netty.buffer.ReadOnlyByteBufferBuf, io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, ByteBuffer byteBuffer) {
        checkIndex(i);
        if (byteBuffer == null) {
            throw new NullPointerException("dst");
        }
        int min = Math.min(capacity() - i, byteBuffer.remaining());
        ByteBuffer internalNioBuffer = internalNioBuffer();
        internalNioBuffer.clear().position(i).limit(i + min);
        byteBuffer.put(internalNioBuffer);
        return this;
    }

    @Override // io.netty.buffer.ReadOnlyByteBufferBuf, io.netty.buffer.ByteBuf
    public ByteBuf copy(int i, int i2) {
        checkIndex(i, i2);
        ByteBuf directBuffer = alloc().directBuffer(i2, maxCapacity());
        if (i2 != 0) {
            if (directBuffer.hasMemoryAddress()) {
                PlatformDependent.copyMemory(addr(i), directBuffer.memoryAddress(), i2);
                directBuffer.setIndex(0, i2);
            } else {
                directBuffer.writeBytes(this, i, i2);
            }
        }
        return directBuffer;
    }

    @Override // io.netty.buffer.ReadOnlyByteBufferBuf, io.netty.buffer.ByteBuf
    public long memoryAddress() {
        return this.memoryAddress;
    }

    private long addr(int i) {
        return this.memoryAddress + i;
    }
}
