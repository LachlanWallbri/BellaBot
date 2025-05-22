package io.grpc.netty.shaded.io.netty.handler.codec;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.buffer.SwappedByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.util.ByteProcessor;
import io.grpc.netty.shaded.io.netty.util.Signal;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class ReplayingDecoderByteBuf extends ByteBuf {
    private ByteBuf buffer;
    private SwappedByteBuf swapped;
    private boolean terminated;
    private static final Signal REPLAY = ReplayingDecoder.REPLAY;
    static final ReplayingDecoderByteBuf EMPTY_BUFFER = new ReplayingDecoderByteBuf(Unpooled.EMPTY_BUFFER);

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean equals(Object obj) {
        return this == obj;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean hasArray() {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean hasMemoryAddress() {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isReadOnly() {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isWritable() {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isWritable(int i) {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int maxWritableBytes() {
        return 0;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int writableBytes() {
        return 0;
    }

    static {
        EMPTY_BUFFER.terminate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReplayingDecoderByteBuf() {
    }

    ReplayingDecoderByteBuf(ByteBuf byteBuf) {
        setCumulation(byteBuf);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCumulation(ByteBuf byteBuf) {
        this.buffer = byteBuf;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void terminate() {
        this.terminated = true;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int capacity() {
        if (this.terminated) {
            return this.buffer.capacity();
        }
        return Integer.MAX_VALUE;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf capacity(int i) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int maxCapacity() {
        return capacity();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBufAllocator alloc() {
        return this.buffer.alloc();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf asReadOnly() {
        return Unpooled.unmodifiableBuffer(this);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isDirect() {
        return this.buffer.isDirect();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public byte[] array() {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int arrayOffset() {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long memoryAddress() {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf clear() {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf, java.lang.Comparable
    public int compareTo(ByteBuf byteBuf) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf copy() {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf copy(int i, int i2) {
        checkIndex(i, i2);
        return this.buffer.copy(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf discardReadBytes() {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf ensureWritable(int i) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int ensureWritable(int i, boolean z) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf duplicate() {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf retainedDuplicate() {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean getBoolean(int i) {
        checkIndex(i, 1);
        return this.buffer.getBoolean(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public byte getByte(int i) {
        checkIndex(i, 1);
        return this.buffer.getByte(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public short getUnsignedByte(int i) {
        checkIndex(i, 1);
        return this.buffer.getUnsignedByte(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, byte[] bArr, int i2, int i3) {
        checkIndex(i, i3);
        this.buffer.getBytes(i, bArr, i2, i3);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, byte[] bArr) {
        checkIndex(i, bArr.length);
        this.buffer.getBytes(i, bArr);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, ByteBuffer byteBuffer) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, ByteBuf byteBuf, int i2, int i3) {
        checkIndex(i, i3);
        this.buffer.getBytes(i, byteBuf, i2, i3);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, ByteBuf byteBuf, int i2) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, ByteBuf byteBuf) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getBytes(int i, GatheringByteChannel gatheringByteChannel, int i2) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getBytes(int i, FileChannel fileChannel, long j, int i2) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, OutputStream outputStream, int i2) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getInt(int i) {
        checkIndex(i, 4);
        return this.buffer.getInt(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getIntLE(int i) {
        checkIndex(i, 4);
        return this.buffer.getIntLE(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long getUnsignedInt(int i) {
        checkIndex(i, 4);
        return this.buffer.getUnsignedInt(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long getUnsignedIntLE(int i) {
        checkIndex(i, 4);
        return this.buffer.getUnsignedIntLE(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long getLong(int i) {
        checkIndex(i, 8);
        return this.buffer.getLong(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long getLongLE(int i) {
        checkIndex(i, 8);
        return this.buffer.getLongLE(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getMedium(int i) {
        checkIndex(i, 3);
        return this.buffer.getMedium(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getMediumLE(int i) {
        checkIndex(i, 3);
        return this.buffer.getMediumLE(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getUnsignedMedium(int i) {
        checkIndex(i, 3);
        return this.buffer.getUnsignedMedium(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getUnsignedMediumLE(int i) {
        checkIndex(i, 3);
        return this.buffer.getUnsignedMediumLE(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public short getShort(int i) {
        checkIndex(i, 2);
        return this.buffer.getShort(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public short getShortLE(int i) {
        checkIndex(i, 2);
        return this.buffer.getShortLE(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getUnsignedShort(int i) {
        checkIndex(i, 2);
        return this.buffer.getUnsignedShort(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getUnsignedShortLE(int i) {
        checkIndex(i, 2);
        return this.buffer.getUnsignedShortLE(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public char getChar(int i) {
        checkIndex(i, 2);
        return this.buffer.getChar(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public float getFloat(int i) {
        checkIndex(i, 4);
        return this.buffer.getFloat(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public double getDouble(int i) {
        checkIndex(i, 8);
        return this.buffer.getDouble(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CharSequence getCharSequence(int i, int i2, Charset charset) {
        checkIndex(i, i2);
        return this.buffer.getCharSequence(i, i2, charset);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int hashCode() {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int indexOf(int i, int i2, byte b) {
        if (i == i2) {
            return -1;
        }
        if (Math.max(i, i2) > this.buffer.writerIndex()) {
            throw REPLAY;
        }
        return this.buffer.indexOf(i, i2, b);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int bytesBefore(byte b) {
        int bytesBefore = this.buffer.bytesBefore(b);
        if (bytesBefore >= 0) {
            return bytesBefore;
        }
        throw REPLAY;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int bytesBefore(int i, byte b) {
        return bytesBefore(this.buffer.readerIndex(), i, b);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int bytesBefore(int i, int i2, byte b) {
        int writerIndex = this.buffer.writerIndex();
        if (i >= writerIndex) {
            throw REPLAY;
        }
        if (i <= writerIndex - i2) {
            return this.buffer.bytesBefore(i, i2, b);
        }
        int bytesBefore = this.buffer.bytesBefore(i, writerIndex - i, b);
        if (bytesBefore >= 0) {
            return bytesBefore;
        }
        throw REPLAY;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int forEachByte(ByteProcessor byteProcessor) {
        int forEachByte = this.buffer.forEachByte(byteProcessor);
        if (forEachByte >= 0) {
            return forEachByte;
        }
        throw REPLAY;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int forEachByte(int i, int i2, ByteProcessor byteProcessor) {
        int writerIndex = this.buffer.writerIndex();
        if (i >= writerIndex) {
            throw REPLAY;
        }
        if (i <= writerIndex - i2) {
            return this.buffer.forEachByte(i, i2, byteProcessor);
        }
        int forEachByte = this.buffer.forEachByte(i, writerIndex - i, byteProcessor);
        if (forEachByte >= 0) {
            return forEachByte;
        }
        throw REPLAY;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int forEachByteDesc(ByteProcessor byteProcessor) {
        if (this.terminated) {
            return this.buffer.forEachByteDesc(byteProcessor);
        }
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int forEachByteDesc(int i, int i2, ByteProcessor byteProcessor) {
        if (i + i2 > this.buffer.writerIndex()) {
            throw REPLAY;
        }
        return this.buffer.forEachByteDesc(i, i2, byteProcessor);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf markReaderIndex() {
        this.buffer.markReaderIndex();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf markWriterIndex() {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteOrder order() {
        return this.buffer.order();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf order(ByteOrder byteOrder) {
        if (ObjectUtil.checkNotNull(byteOrder, "endianness") == order()) {
            return this;
        }
        SwappedByteBuf swappedByteBuf = this.swapped;
        if (swappedByteBuf != null) {
            return swappedByteBuf;
        }
        SwappedByteBuf swappedByteBuf2 = new SwappedByteBuf(this);
        this.swapped = swappedByteBuf2;
        return swappedByteBuf2;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isReadable() {
        return !this.terminated || this.buffer.isReadable();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isReadable(int i) {
        return !this.terminated || this.buffer.isReadable(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readableBytes() {
        if (this.terminated) {
            return this.buffer.readableBytes();
        }
        return Integer.MAX_VALUE - this.buffer.readerIndex();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean readBoolean() {
        checkReadableBytes(1);
        return this.buffer.readBoolean();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public byte readByte() {
        checkReadableBytes(1);
        return this.buffer.readByte();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public short readUnsignedByte() {
        checkReadableBytes(1);
        return this.buffer.readUnsignedByte();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(byte[] bArr, int i, int i2) {
        checkReadableBytes(i2);
        this.buffer.readBytes(bArr, i, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(byte[] bArr) {
        checkReadableBytes(bArr.length);
        this.buffer.readBytes(bArr);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(ByteBuffer byteBuffer) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(ByteBuf byteBuf, int i, int i2) {
        checkReadableBytes(i2);
        this.buffer.readBytes(byteBuf, i, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(ByteBuf byteBuf, int i) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(ByteBuf byteBuf) {
        checkReadableBytes(byteBuf.writableBytes());
        this.buffer.readBytes(byteBuf);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readBytes(GatheringByteChannel gatheringByteChannel, int i) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readBytes(FileChannel fileChannel, long j, int i) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(int i) {
        checkReadableBytes(i);
        return this.buffer.readBytes(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readSlice(int i) {
        checkReadableBytes(i);
        return this.buffer.readSlice(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readRetainedSlice(int i) {
        checkReadableBytes(i);
        return this.buffer.readRetainedSlice(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(OutputStream outputStream, int i) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readerIndex() {
        return this.buffer.readerIndex();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readerIndex(int i) {
        this.buffer.readerIndex(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readInt() {
        checkReadableBytes(4);
        return this.buffer.readInt();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readIntLE() {
        checkReadableBytes(4);
        return this.buffer.readIntLE();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long readUnsignedInt() {
        checkReadableBytes(4);
        return this.buffer.readUnsignedInt();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long readUnsignedIntLE() {
        checkReadableBytes(4);
        return this.buffer.readUnsignedIntLE();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long readLong() {
        checkReadableBytes(8);
        return this.buffer.readLong();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long readLongLE() {
        checkReadableBytes(8);
        return this.buffer.readLongLE();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readMedium() {
        checkReadableBytes(3);
        return this.buffer.readMedium();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readMediumLE() {
        checkReadableBytes(3);
        return this.buffer.readMediumLE();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readUnsignedMedium() {
        checkReadableBytes(3);
        return this.buffer.readUnsignedMedium();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readUnsignedMediumLE() {
        checkReadableBytes(3);
        return this.buffer.readUnsignedMediumLE();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public short readShort() {
        checkReadableBytes(2);
        return this.buffer.readShort();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public short readShortLE() {
        checkReadableBytes(2);
        return this.buffer.readShortLE();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readUnsignedShort() {
        checkReadableBytes(2);
        return this.buffer.readUnsignedShort();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readUnsignedShortLE() {
        checkReadableBytes(2);
        return this.buffer.readUnsignedShortLE();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public char readChar() {
        checkReadableBytes(2);
        return this.buffer.readChar();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public float readFloat() {
        checkReadableBytes(4);
        return this.buffer.readFloat();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public double readDouble() {
        checkReadableBytes(8);
        return this.buffer.readDouble();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CharSequence readCharSequence(int i, Charset charset) {
        checkReadableBytes(i);
        return this.buffer.readCharSequence(i, charset);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf resetReaderIndex() {
        this.buffer.resetReaderIndex();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf resetWriterIndex() {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBoolean(int i, boolean z) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setByte(int i, int i2) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, byte[] bArr, int i2, int i3) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, byte[] bArr) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, ByteBuffer byteBuffer) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, ByteBuf byteBuf, int i2, int i3) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, ByteBuf byteBuf, int i2) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, ByteBuf byteBuf) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int setBytes(int i, InputStream inputStream, int i2) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setZero(int i, int i2) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int setBytes(int i, ScatteringByteChannel scatteringByteChannel, int i2) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int setBytes(int i, FileChannel fileChannel, long j, int i2) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setIndex(int i, int i2) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setInt(int i, int i2) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setIntLE(int i, int i2) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setLong(int i, long j) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setLongLE(int i, long j) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setMedium(int i, int i2) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setMediumLE(int i, int i2) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setShort(int i, int i2) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setShortLE(int i, int i2) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setChar(int i, int i2) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setFloat(int i, float f) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setDouble(int i, double d) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf skipBytes(int i) {
        checkReadableBytes(i);
        this.buffer.skipBytes(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf slice() {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf retainedSlice() {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf slice(int i, int i2) {
        checkIndex(i, i2);
        return this.buffer.slice(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf retainedSlice(int i, int i2) {
        checkIndex(i, i2);
        return this.buffer.slice(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int nioBufferCount() {
        return this.buffer.nioBufferCount();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer nioBuffer() {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer nioBuffer(int i, int i2) {
        checkIndex(i, i2);
        return this.buffer.nioBuffer(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer[] nioBuffers() {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer[] nioBuffers(int i, int i2) {
        checkIndex(i, i2);
        return this.buffer.nioBuffers(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer internalNioBuffer(int i, int i2) {
        checkIndex(i, i2);
        return this.buffer.internalNioBuffer(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public String toString(int i, int i2, Charset charset) {
        checkIndex(i, i2);
        return this.buffer.toString(i, i2, charset);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public String toString(Charset charset) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public String toString() {
        return StringUtil.simpleClassName(this) + "(ridx=" + readerIndex() + ", widx=" + writerIndex() + ')';
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeBoolean(boolean z) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeByte(int i) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeBytes(byte[] bArr, int i, int i2) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeBytes(byte[] bArr) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeBytes(ByteBuffer byteBuffer) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeBytes(ByteBuf byteBuf, int i, int i2) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeBytes(ByteBuf byteBuf, int i) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeBytes(ByteBuf byteBuf) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int writeBytes(InputStream inputStream, int i) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int writeBytes(ScatteringByteChannel scatteringByteChannel, int i) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int writeBytes(FileChannel fileChannel, long j, int i) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeInt(int i) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeIntLE(int i) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeLong(long j) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeLongLE(long j) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeMedium(int i) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeMediumLE(int i) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeZero(int i) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int writerIndex() {
        return this.buffer.writerIndex();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writerIndex(int i) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeShort(int i) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeShortLE(int i) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeChar(int i) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeFloat(float f) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeDouble(double d) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int setCharSequence(int i, CharSequence charSequence, Charset charset) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int writeCharSequence(CharSequence charSequence, Charset charset) {
        throw reject();
    }

    private void checkIndex(int i, int i2) {
        if (i + i2 > this.buffer.writerIndex()) {
            throw REPLAY;
        }
    }

    private void checkReadableBytes(int i) {
        if (this.buffer.readableBytes() < i) {
            throw REPLAY;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf discardSomeReadBytes() {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public int refCnt() {
        return this.buffer.refCnt();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public ByteBuf retain() {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public ByteBuf retain(int i) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public ByteBuf touch() {
        this.buffer.touch();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public ByteBuf touch(Object obj) {
        this.buffer.touch(obj);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public boolean release() {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public boolean release(int i) {
        throw reject();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf unwrap() {
        throw reject();
    }

    private static UnsupportedOperationException reject() {
        return new UnsupportedOperationException("not a replayable operation");
    }
}
