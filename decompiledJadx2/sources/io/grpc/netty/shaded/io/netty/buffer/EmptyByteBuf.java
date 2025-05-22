package io.grpc.netty.shaded.io.netty.buffer;

import io.grpc.netty.shaded.io.netty.util.ByteProcessor;
import io.grpc.netty.shaded.io.netty.util.internal.EmptyArrays;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import org.simpleframework.xml.strategy.Name;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class EmptyByteBuf extends ByteBuf {
    private static final ByteBuffer EMPTY_BYTE_BUFFER = ByteBuffer.allocateDirect(0);
    private static final long EMPTY_BYTE_BUFFER_ADDRESS;
    static final int EMPTY_BYTE_BUF_HASH_CODE = 1;
    private final ByteBufAllocator alloc;
    private final ByteOrder order;
    private final String str;
    private EmptyByteBuf swapped;

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int arrayOffset() {
        return 0;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int bytesBefore(byte b) {
        return -1;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int capacity() {
        return 0;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf clear() {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf copy() {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf discardReadBytes() {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf discardSomeReadBytes() {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf duplicate() {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int forEachByte(ByteProcessor byteProcessor) {
        return -1;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int forEachByteDesc(ByteProcessor byteProcessor) {
        return -1;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean hasArray() {
        return true;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int hashCode() {
        return 1;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isContiguous() {
        return true;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isDirect() {
        return true;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isReadOnly() {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isReadable() {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isReadable(int i) {
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
    public ByteBuf markReaderIndex() {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf markWriterIndex() {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int maxCapacity() {
        return 0;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int maxWritableBytes() {
        return 0;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int nioBufferCount() {
        return 1;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readableBytes() {
        return 0;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readerIndex() {
        return 0;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public int refCnt() {
        return 1;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public boolean release() {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public boolean release(int i) {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf resetReaderIndex() {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf resetWriterIndex() {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public ByteBuf retain() {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public ByteBuf retain(int i) {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf retainedDuplicate() {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf retainedSlice() {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf slice() {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public String toString(Charset charset) {
        return "";
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public ByteBuf touch() {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public ByteBuf touch(Object obj) {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf unwrap() {
        return null;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int writableBytes() {
        return 0;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int writerIndex() {
        return 0;
    }

    static {
        long j = 0;
        try {
            if (PlatformDependent.hasUnsafe()) {
                j = PlatformDependent.directBufferAddress(EMPTY_BYTE_BUFFER);
            }
        } catch (Throwable unused) {
        }
        EMPTY_BYTE_BUFFER_ADDRESS = j;
    }

    public EmptyByteBuf(ByteBufAllocator byteBufAllocator) {
        this(byteBufAllocator, ByteOrder.BIG_ENDIAN);
    }

    private EmptyByteBuf(ByteBufAllocator byteBufAllocator, ByteOrder byteOrder) {
        this.alloc = (ByteBufAllocator) ObjectUtil.checkNotNull(byteBufAllocator, "alloc");
        this.order = byteOrder;
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtil.simpleClassName(this));
        sb.append(byteOrder == ByteOrder.BIG_ENDIAN ? "BE" : "LE");
        this.str = sb.toString();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf capacity(int i) {
        throw new ReadOnlyBufferException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBufAllocator alloc() {
        return this.alloc;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteOrder order() {
        return this.order;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf asReadOnly() {
        return Unpooled.unmodifiableBuffer(this);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf order(ByteOrder byteOrder) {
        if (ObjectUtil.checkNotNull(byteOrder, "endianness") == order()) {
            return this;
        }
        EmptyByteBuf emptyByteBuf = this.swapped;
        if (emptyByteBuf != null) {
            return emptyByteBuf;
        }
        EmptyByteBuf emptyByteBuf2 = new EmptyByteBuf(alloc(), byteOrder);
        this.swapped = emptyByteBuf2;
        return emptyByteBuf2;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readerIndex(int i) {
        return checkIndex(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writerIndex(int i) {
        return checkIndex(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setIndex(int i, int i2) {
        checkIndex(i);
        checkIndex(i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf ensureWritable(int i) {
        ObjectUtil.checkPositiveOrZero(i, "minWritableBytes");
        if (i == 0) {
            return this;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int ensureWritable(int i, boolean z) {
        ObjectUtil.checkPositiveOrZero(i, "minWritableBytes");
        return i == 0 ? 0 : 1;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean getBoolean(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public byte getByte(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public short getUnsignedByte(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public short getShort(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public short getShortLE(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getUnsignedShort(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getUnsignedShortLE(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getMedium(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getMediumLE(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getUnsignedMedium(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getUnsignedMediumLE(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getInt(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getIntLE(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long getUnsignedInt(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long getUnsignedIntLE(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long getLong(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long getLongLE(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public char getChar(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public float getFloat(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public double getDouble(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, ByteBuf byteBuf) {
        return checkIndex(i, byteBuf.writableBytes());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, ByteBuf byteBuf, int i2) {
        return checkIndex(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, ByteBuf byteBuf, int i2, int i3) {
        return checkIndex(i, i3);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, byte[] bArr) {
        return checkIndex(i, bArr.length);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, byte[] bArr, int i2, int i3) {
        return checkIndex(i, i3);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, ByteBuffer byteBuffer) {
        return checkIndex(i, byteBuffer.remaining());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, OutputStream outputStream, int i2) {
        return checkIndex(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getBytes(int i, GatheringByteChannel gatheringByteChannel, int i2) {
        checkIndex(i, i2);
        return 0;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getBytes(int i, FileChannel fileChannel, long j, int i2) {
        checkIndex(i, i2);
        return 0;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CharSequence getCharSequence(int i, int i2, Charset charset) {
        checkIndex(i, i2);
        return null;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBoolean(int i, boolean z) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setByte(int i, int i2) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setShort(int i, int i2) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setShortLE(int i, int i2) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setMedium(int i, int i2) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setMediumLE(int i, int i2) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setInt(int i, int i2) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setIntLE(int i, int i2) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setLong(int i, long j) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setLongLE(int i, long j) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setChar(int i, int i2) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setFloat(int i, float f) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setDouble(int i, double d) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, ByteBuf byteBuf) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, ByteBuf byteBuf, int i2) {
        return checkIndex(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, ByteBuf byteBuf, int i2, int i3) {
        return checkIndex(i, i3);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, byte[] bArr) {
        return checkIndex(i, bArr.length);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, byte[] bArr, int i2, int i3) {
        return checkIndex(i, i3);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, ByteBuffer byteBuffer) {
        return checkIndex(i, byteBuffer.remaining());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int setBytes(int i, InputStream inputStream, int i2) {
        checkIndex(i, i2);
        return 0;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int setBytes(int i, ScatteringByteChannel scatteringByteChannel, int i2) {
        checkIndex(i, i2);
        return 0;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int setBytes(int i, FileChannel fileChannel, long j, int i2) {
        checkIndex(i, i2);
        return 0;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setZero(int i, int i2) {
        return checkIndex(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int setCharSequence(int i, CharSequence charSequence, Charset charset) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean readBoolean() {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public byte readByte() {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public short readUnsignedByte() {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public short readShort() {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public short readShortLE() {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readUnsignedShort() {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readUnsignedShortLE() {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readMedium() {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readMediumLE() {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readUnsignedMedium() {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readUnsignedMediumLE() {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readInt() {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readIntLE() {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long readUnsignedInt() {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long readUnsignedIntLE() {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long readLong() {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long readLongLE() {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public char readChar() {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public float readFloat() {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public double readDouble() {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(int i) {
        return checkLength(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readSlice(int i) {
        return checkLength(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readRetainedSlice(int i) {
        return checkLength(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(ByteBuf byteBuf) {
        return checkLength(byteBuf.writableBytes());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(ByteBuf byteBuf, int i) {
        return checkLength(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(ByteBuf byteBuf, int i, int i2) {
        return checkLength(i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(byte[] bArr) {
        return checkLength(bArr.length);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(byte[] bArr, int i, int i2) {
        return checkLength(i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(ByteBuffer byteBuffer) {
        return checkLength(byteBuffer.remaining());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(OutputStream outputStream, int i) {
        return checkLength(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readBytes(GatheringByteChannel gatheringByteChannel, int i) {
        checkLength(i);
        return 0;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readBytes(FileChannel fileChannel, long j, int i) {
        checkLength(i);
        return 0;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CharSequence readCharSequence(int i, Charset charset) {
        checkLength(i);
        return "";
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf skipBytes(int i) {
        return checkLength(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeBoolean(boolean z) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeByte(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeShort(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeShortLE(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeMedium(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeMediumLE(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeInt(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeIntLE(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeLong(long j) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeLongLE(long j) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeChar(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeFloat(float f) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeDouble(double d) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeBytes(ByteBuf byteBuf) {
        return checkLength(byteBuf.readableBytes());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeBytes(ByteBuf byteBuf, int i) {
        return checkLength(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeBytes(ByteBuf byteBuf, int i, int i2) {
        return checkLength(i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeBytes(byte[] bArr) {
        return checkLength(bArr.length);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeBytes(byte[] bArr, int i, int i2) {
        return checkLength(i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeBytes(ByteBuffer byteBuffer) {
        return checkLength(byteBuffer.remaining());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int writeBytes(InputStream inputStream, int i) {
        checkLength(i);
        return 0;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int writeBytes(ScatteringByteChannel scatteringByteChannel, int i) {
        checkLength(i);
        return 0;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int writeBytes(FileChannel fileChannel, long j, int i) {
        checkLength(i);
        return 0;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeZero(int i) {
        return checkLength(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int writeCharSequence(CharSequence charSequence, Charset charset) {
        throw new IndexOutOfBoundsException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int indexOf(int i, int i2, byte b) {
        checkIndex(i);
        checkIndex(i2);
        return -1;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int bytesBefore(int i, byte b) {
        checkLength(i);
        return -1;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int bytesBefore(int i, int i2, byte b) {
        checkIndex(i, i2);
        return -1;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int forEachByte(int i, int i2, ByteProcessor byteProcessor) {
        checkIndex(i, i2);
        return -1;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int forEachByteDesc(int i, int i2, ByteProcessor byteProcessor) {
        checkIndex(i, i2);
        return -1;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf copy(int i, int i2) {
        return checkIndex(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf slice(int i, int i2) {
        return checkIndex(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf retainedSlice(int i, int i2) {
        return checkIndex(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer nioBuffer() {
        return EMPTY_BYTE_BUFFER;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer nioBuffer(int i, int i2) {
        checkIndex(i, i2);
        return nioBuffer();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer[] nioBuffers() {
        return new ByteBuffer[]{EMPTY_BYTE_BUFFER};
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer[] nioBuffers(int i, int i2) {
        checkIndex(i, i2);
        return nioBuffers();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer internalNioBuffer(int i, int i2) {
        return EMPTY_BYTE_BUFFER;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public byte[] array() {
        return EmptyArrays.EMPTY_BYTES;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean hasMemoryAddress() {
        return EMPTY_BYTE_BUFFER_ADDRESS != 0;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long memoryAddress() {
        if (hasMemoryAddress()) {
            return EMPTY_BYTE_BUFFER_ADDRESS;
        }
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public String toString(int i, int i2, Charset charset) {
        checkIndex(i, i2);
        return toString(charset);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean equals(Object obj) {
        return (obj instanceof ByteBuf) && !((ByteBuf) obj).isReadable();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf, java.lang.Comparable
    public int compareTo(ByteBuf byteBuf) {
        return byteBuf.isReadable() ? -1 : 0;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public String toString() {
        return this.str;
    }

    private ByteBuf checkIndex(int i) {
        if (i == 0) {
            return this;
        }
        throw new IndexOutOfBoundsException();
    }

    private ByteBuf checkIndex(int i, int i2) {
        ObjectUtil.checkPositiveOrZero(i2, Name.LENGTH);
        if (i == 0 && i2 == 0) {
            return this;
        }
        throw new IndexOutOfBoundsException();
    }

    private ByteBuf checkLength(int i) {
        ObjectUtil.checkPositiveOrZero(i, Name.LENGTH);
        if (i == 0) {
            return this;
        }
        throw new IndexOutOfBoundsException();
    }
}
