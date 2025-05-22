package io.grpc.netty.shaded.io.netty.buffer;

import io.grpc.netty.shaded.io.netty.util.ByteProcessor;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import kotlin.UShort;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@Deprecated
/* loaded from: classes7.dex */
public class SwappedByteBuf extends ByteBuf {
    private final ByteBuf buf;
    private final ByteOrder order;

    public SwappedByteBuf(ByteBuf byteBuf) {
        this.buf = (ByteBuf) ObjectUtil.checkNotNull(byteBuf, "buf");
        if (byteBuf.order() == ByteOrder.BIG_ENDIAN) {
            this.order = ByteOrder.LITTLE_ENDIAN;
        } else {
            this.order = ByteOrder.BIG_ENDIAN;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteOrder order() {
        return this.order;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf order(ByteOrder byteOrder) {
        return ObjectUtil.checkNotNull(byteOrder, "endianness") == this.order ? this : this.buf;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf unwrap() {
        return this.buf;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBufAllocator alloc() {
        return this.buf.alloc();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int capacity() {
        return this.buf.capacity();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf capacity(int i) {
        this.buf.capacity(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int maxCapacity() {
        return this.buf.maxCapacity();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isReadOnly() {
        return this.buf.isReadOnly();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf asReadOnly() {
        return Unpooled.unmodifiableBuffer(this);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isDirect() {
        return this.buf.isDirect();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readerIndex() {
        return this.buf.readerIndex();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readerIndex(int i) {
        this.buf.readerIndex(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int writerIndex() {
        return this.buf.writerIndex();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writerIndex(int i) {
        this.buf.writerIndex(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setIndex(int i, int i2) {
        this.buf.setIndex(i, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readableBytes() {
        return this.buf.readableBytes();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int writableBytes() {
        return this.buf.writableBytes();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int maxWritableBytes() {
        return this.buf.maxWritableBytes();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int maxFastWritableBytes() {
        return this.buf.maxFastWritableBytes();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isReadable() {
        return this.buf.isReadable();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isReadable(int i) {
        return this.buf.isReadable(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isWritable() {
        return this.buf.isWritable();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isWritable(int i) {
        return this.buf.isWritable(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf clear() {
        this.buf.clear();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf markReaderIndex() {
        this.buf.markReaderIndex();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf resetReaderIndex() {
        this.buf.resetReaderIndex();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf markWriterIndex() {
        this.buf.markWriterIndex();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf resetWriterIndex() {
        this.buf.resetWriterIndex();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf discardReadBytes() {
        this.buf.discardReadBytes();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf discardSomeReadBytes() {
        this.buf.discardSomeReadBytes();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf ensureWritable(int i) {
        this.buf.ensureWritable(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int ensureWritable(int i, boolean z) {
        return this.buf.ensureWritable(i, z);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean getBoolean(int i) {
        return this.buf.getBoolean(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public byte getByte(int i) {
        return this.buf.getByte(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public short getUnsignedByte(int i) {
        return this.buf.getUnsignedByte(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public short getShort(int i) {
        return ByteBufUtil.swapShort(this.buf.getShort(i));
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public short getShortLE(int i) {
        return this.buf.getShort(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getUnsignedShort(int i) {
        return getShort(i) & UShort.MAX_VALUE;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getUnsignedShortLE(int i) {
        return getShortLE(i) & UShort.MAX_VALUE;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getMedium(int i) {
        return ByteBufUtil.swapMedium(this.buf.getMedium(i));
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getMediumLE(int i) {
        return this.buf.getMedium(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getUnsignedMedium(int i) {
        return getMedium(i) & 16777215;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getUnsignedMediumLE(int i) {
        return getMediumLE(i) & 16777215;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getInt(int i) {
        return ByteBufUtil.swapInt(this.buf.getInt(i));
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getIntLE(int i) {
        return this.buf.getInt(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long getUnsignedInt(int i) {
        return getInt(i) & 4294967295L;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long getUnsignedIntLE(int i) {
        return getIntLE(i) & 4294967295L;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long getLong(int i) {
        return ByteBufUtil.swapLong(this.buf.getLong(i));
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long getLongLE(int i) {
        return this.buf.getLong(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public char getChar(int i) {
        return (char) getShort(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public float getFloat(int i) {
        return Float.intBitsToFloat(getInt(i));
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public double getDouble(int i) {
        return Double.longBitsToDouble(getLong(i));
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, ByteBuf byteBuf) {
        this.buf.getBytes(i, byteBuf);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, ByteBuf byteBuf, int i2) {
        this.buf.getBytes(i, byteBuf, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, ByteBuf byteBuf, int i2, int i3) {
        this.buf.getBytes(i, byteBuf, i2, i3);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, byte[] bArr) {
        this.buf.getBytes(i, bArr);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, byte[] bArr, int i2, int i3) {
        this.buf.getBytes(i, bArr, i2, i3);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, ByteBuffer byteBuffer) {
        this.buf.getBytes(i, byteBuffer);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, OutputStream outputStream, int i2) throws IOException {
        this.buf.getBytes(i, outputStream, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getBytes(int i, GatheringByteChannel gatheringByteChannel, int i2) throws IOException {
        return this.buf.getBytes(i, gatheringByteChannel, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getBytes(int i, FileChannel fileChannel, long j, int i2) throws IOException {
        return this.buf.getBytes(i, fileChannel, j, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CharSequence getCharSequence(int i, int i2, Charset charset) {
        return this.buf.getCharSequence(i, i2, charset);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBoolean(int i, boolean z) {
        this.buf.setBoolean(i, z);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setByte(int i, int i2) {
        this.buf.setByte(i, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setShort(int i, int i2) {
        this.buf.setShort(i, ByteBufUtil.swapShort((short) i2));
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setShortLE(int i, int i2) {
        this.buf.setShort(i, (short) i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setMedium(int i, int i2) {
        this.buf.setMedium(i, ByteBufUtil.swapMedium(i2));
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setMediumLE(int i, int i2) {
        this.buf.setMedium(i, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setInt(int i, int i2) {
        this.buf.setInt(i, ByteBufUtil.swapInt(i2));
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setIntLE(int i, int i2) {
        this.buf.setInt(i, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setLong(int i, long j) {
        this.buf.setLong(i, ByteBufUtil.swapLong(j));
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setLongLE(int i, long j) {
        this.buf.setLong(i, j);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setChar(int i, int i2) {
        setShort(i, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setFloat(int i, float f) {
        setInt(i, Float.floatToRawIntBits(f));
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setDouble(int i, double d) {
        setLong(i, Double.doubleToRawLongBits(d));
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, ByteBuf byteBuf) {
        this.buf.setBytes(i, byteBuf);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, ByteBuf byteBuf, int i2) {
        this.buf.setBytes(i, byteBuf, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, ByteBuf byteBuf, int i2, int i3) {
        this.buf.setBytes(i, byteBuf, i2, i3);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, byte[] bArr) {
        this.buf.setBytes(i, bArr);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, byte[] bArr, int i2, int i3) {
        this.buf.setBytes(i, bArr, i2, i3);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, ByteBuffer byteBuffer) {
        this.buf.setBytes(i, byteBuffer);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int setBytes(int i, InputStream inputStream, int i2) throws IOException {
        return this.buf.setBytes(i, inputStream, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int setBytes(int i, ScatteringByteChannel scatteringByteChannel, int i2) throws IOException {
        return this.buf.setBytes(i, scatteringByteChannel, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int setBytes(int i, FileChannel fileChannel, long j, int i2) throws IOException {
        return this.buf.setBytes(i, fileChannel, j, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setZero(int i, int i2) {
        this.buf.setZero(i, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int setCharSequence(int i, CharSequence charSequence, Charset charset) {
        return this.buf.setCharSequence(i, charSequence, charset);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean readBoolean() {
        return this.buf.readBoolean();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public byte readByte() {
        return this.buf.readByte();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public short readUnsignedByte() {
        return this.buf.readUnsignedByte();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public short readShort() {
        return ByteBufUtil.swapShort(this.buf.readShort());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public short readShortLE() {
        return this.buf.readShort();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readUnsignedShort() {
        return readShort() & UShort.MAX_VALUE;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readUnsignedShortLE() {
        return readShortLE() & UShort.MAX_VALUE;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readMedium() {
        return ByteBufUtil.swapMedium(this.buf.readMedium());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readMediumLE() {
        return this.buf.readMedium();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readUnsignedMedium() {
        return readMedium() & 16777215;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readUnsignedMediumLE() {
        return readMediumLE() & 16777215;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readInt() {
        return ByteBufUtil.swapInt(this.buf.readInt());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readIntLE() {
        return this.buf.readInt();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long readUnsignedInt() {
        return readInt() & 4294967295L;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long readUnsignedIntLE() {
        return readIntLE() & 4294967295L;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long readLong() {
        return ByteBufUtil.swapLong(this.buf.readLong());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long readLongLE() {
        return this.buf.readLong();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public char readChar() {
        return (char) readShort();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public float readFloat() {
        return Float.intBitsToFloat(readInt());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(int i) {
        return this.buf.readBytes(i).order(order());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readSlice(int i) {
        return this.buf.readSlice(i).order(this.order);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readRetainedSlice(int i) {
        return this.buf.readRetainedSlice(i).order(this.order);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(ByteBuf byteBuf) {
        this.buf.readBytes(byteBuf);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(ByteBuf byteBuf, int i) {
        this.buf.readBytes(byteBuf, i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(ByteBuf byteBuf, int i, int i2) {
        this.buf.readBytes(byteBuf, i, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(byte[] bArr) {
        this.buf.readBytes(bArr);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(byte[] bArr, int i, int i2) {
        this.buf.readBytes(bArr, i, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(ByteBuffer byteBuffer) {
        this.buf.readBytes(byteBuffer);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(OutputStream outputStream, int i) throws IOException {
        this.buf.readBytes(outputStream, i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readBytes(GatheringByteChannel gatheringByteChannel, int i) throws IOException {
        return this.buf.readBytes(gatheringByteChannel, i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readBytes(FileChannel fileChannel, long j, int i) throws IOException {
        return this.buf.readBytes(fileChannel, j, i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CharSequence readCharSequence(int i, Charset charset) {
        return this.buf.readCharSequence(i, charset);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf skipBytes(int i) {
        this.buf.skipBytes(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeBoolean(boolean z) {
        this.buf.writeBoolean(z);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeByte(int i) {
        this.buf.writeByte(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeShort(int i) {
        this.buf.writeShort(ByteBufUtil.swapShort((short) i));
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeShortLE(int i) {
        this.buf.writeShort((short) i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeMedium(int i) {
        this.buf.writeMedium(ByteBufUtil.swapMedium(i));
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeMediumLE(int i) {
        this.buf.writeMedium(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeInt(int i) {
        this.buf.writeInt(ByteBufUtil.swapInt(i));
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeIntLE(int i) {
        this.buf.writeInt(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeLong(long j) {
        this.buf.writeLong(ByteBufUtil.swapLong(j));
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeLongLE(long j) {
        this.buf.writeLong(j);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeChar(int i) {
        writeShort(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeFloat(float f) {
        writeInt(Float.floatToRawIntBits(f));
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeDouble(double d) {
        writeLong(Double.doubleToRawLongBits(d));
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeBytes(ByteBuf byteBuf) {
        this.buf.writeBytes(byteBuf);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeBytes(ByteBuf byteBuf, int i) {
        this.buf.writeBytes(byteBuf, i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeBytes(ByteBuf byteBuf, int i, int i2) {
        this.buf.writeBytes(byteBuf, i, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeBytes(byte[] bArr) {
        this.buf.writeBytes(bArr);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeBytes(byte[] bArr, int i, int i2) {
        this.buf.writeBytes(bArr, i, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeBytes(ByteBuffer byteBuffer) {
        this.buf.writeBytes(byteBuffer);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int writeBytes(InputStream inputStream, int i) throws IOException {
        return this.buf.writeBytes(inputStream, i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int writeBytes(ScatteringByteChannel scatteringByteChannel, int i) throws IOException {
        return this.buf.writeBytes(scatteringByteChannel, i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int writeBytes(FileChannel fileChannel, long j, int i) throws IOException {
        return this.buf.writeBytes(fileChannel, j, i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeZero(int i) {
        this.buf.writeZero(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int writeCharSequence(CharSequence charSequence, Charset charset) {
        return this.buf.writeCharSequence(charSequence, charset);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int indexOf(int i, int i2, byte b) {
        return this.buf.indexOf(i, i2, b);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int bytesBefore(byte b) {
        return this.buf.bytesBefore(b);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int bytesBefore(int i, byte b) {
        return this.buf.bytesBefore(i, b);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int bytesBefore(int i, int i2, byte b) {
        return this.buf.bytesBefore(i, i2, b);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int forEachByte(ByteProcessor byteProcessor) {
        return this.buf.forEachByte(byteProcessor);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int forEachByte(int i, int i2, ByteProcessor byteProcessor) {
        return this.buf.forEachByte(i, i2, byteProcessor);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int forEachByteDesc(ByteProcessor byteProcessor) {
        return this.buf.forEachByteDesc(byteProcessor);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int forEachByteDesc(int i, int i2, ByteProcessor byteProcessor) {
        return this.buf.forEachByteDesc(i, i2, byteProcessor);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf copy() {
        return this.buf.copy().order(this.order);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf copy(int i, int i2) {
        return this.buf.copy(i, i2).order(this.order);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf slice() {
        return this.buf.slice().order(this.order);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf retainedSlice() {
        return this.buf.retainedSlice().order(this.order);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf slice(int i, int i2) {
        return this.buf.slice(i, i2).order(this.order);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf retainedSlice(int i, int i2) {
        return this.buf.retainedSlice(i, i2).order(this.order);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf duplicate() {
        return this.buf.duplicate().order(this.order);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf retainedDuplicate() {
        return this.buf.retainedDuplicate().order(this.order);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int nioBufferCount() {
        return this.buf.nioBufferCount();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer nioBuffer() {
        return this.buf.nioBuffer().order(this.order);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer nioBuffer(int i, int i2) {
        return this.buf.nioBuffer(i, i2).order(this.order);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer internalNioBuffer(int i, int i2) {
        return nioBuffer(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer[] nioBuffers() {
        ByteBuffer[] nioBuffers = this.buf.nioBuffers();
        for (int i = 0; i < nioBuffers.length; i++) {
            nioBuffers[i] = nioBuffers[i].order(this.order);
        }
        return nioBuffers;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer[] nioBuffers(int i, int i2) {
        ByteBuffer[] nioBuffers = this.buf.nioBuffers(i, i2);
        for (int i3 = 0; i3 < nioBuffers.length; i3++) {
            nioBuffers[i3] = nioBuffers[i3].order(this.order);
        }
        return nioBuffers;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean hasArray() {
        return this.buf.hasArray();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public byte[] array() {
        return this.buf.array();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int arrayOffset() {
        return this.buf.arrayOffset();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean hasMemoryAddress() {
        return this.buf.hasMemoryAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isContiguous() {
        return this.buf.isContiguous();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long memoryAddress() {
        return this.buf.memoryAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public String toString(Charset charset) {
        return this.buf.toString(charset);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public String toString(int i, int i2, Charset charset) {
        return this.buf.toString(i, i2, charset);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public int refCnt() {
        return this.buf.refCnt();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public final boolean isAccessible() {
        return this.buf.isAccessible();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public ByteBuf retain() {
        this.buf.retain();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public ByteBuf retain(int i) {
        this.buf.retain(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public ByteBuf touch() {
        this.buf.touch();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public ByteBuf touch(Object obj) {
        this.buf.touch(obj);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public boolean release() {
        return this.buf.release();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public boolean release(int i) {
        return this.buf.release(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int hashCode() {
        return this.buf.hashCode();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ByteBuf) {
            return ByteBufUtil.equals(this, (ByteBuf) obj);
        }
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf, java.lang.Comparable
    public int compareTo(ByteBuf byteBuf) {
        return ByteBufUtil.compare(this, byteBuf);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public String toString() {
        return "Swapped(" + this.buf + ')';
    }
}
