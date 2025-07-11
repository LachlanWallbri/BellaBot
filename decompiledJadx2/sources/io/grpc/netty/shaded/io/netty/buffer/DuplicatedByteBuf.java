package io.grpc.netty.shaded.io.netty.buffer;

import io.grpc.netty.shaded.io.netty.util.ByteProcessor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@Deprecated
/* loaded from: classes7.dex */
public class DuplicatedByteBuf extends AbstractDerivedByteBuf {
    private final ByteBuf buffer;

    public DuplicatedByteBuf(ByteBuf byteBuf) {
        this(byteBuf, byteBuf.readerIndex(), byteBuf.writerIndex());
    }

    DuplicatedByteBuf(ByteBuf byteBuf, int i, int i2) {
        super(byteBuf.maxCapacity());
        if (byteBuf instanceof DuplicatedByteBuf) {
            this.buffer = ((DuplicatedByteBuf) byteBuf).buffer;
        } else if (byteBuf instanceof AbstractPooledDerivedByteBuf) {
            this.buffer = byteBuf.unwrap();
        } else {
            this.buffer = byteBuf;
        }
        setIndex(i, i2);
        markReaderIndex();
        markWriterIndex();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf unwrap() {
        return this.buffer;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBufAllocator alloc() {
        return unwrap().alloc();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    @Deprecated
    public ByteOrder order() {
        return unwrap().order();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isDirect() {
        return unwrap().isDirect();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int capacity() {
        return unwrap().capacity();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf capacity(int i) {
        unwrap().capacity(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean hasArray() {
        return unwrap().hasArray();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public byte[] array() {
        return unwrap().array();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int arrayOffset() {
        return unwrap().arrayOffset();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean hasMemoryAddress() {
        return unwrap().hasMemoryAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long memoryAddress() {
        return unwrap().memoryAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public byte getByte(int i) {
        return unwrap().getByte(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public byte _getByte(int i) {
        return unwrap().getByte(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public short getShort(int i) {
        return unwrap().getShort(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public short _getShort(int i) {
        return unwrap().getShort(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public short getShortLE(int i) {
        return unwrap().getShortLE(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public short _getShortLE(int i) {
        return unwrap().getShortLE(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getUnsignedMedium(int i) {
        return unwrap().getUnsignedMedium(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public int _getUnsignedMedium(int i) {
        return unwrap().getUnsignedMedium(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getUnsignedMediumLE(int i) {
        return unwrap().getUnsignedMediumLE(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public int _getUnsignedMediumLE(int i) {
        return unwrap().getUnsignedMediumLE(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getInt(int i) {
        return unwrap().getInt(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public int _getInt(int i) {
        return unwrap().getInt(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getIntLE(int i) {
        return unwrap().getIntLE(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public int _getIntLE(int i) {
        return unwrap().getIntLE(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long getLong(int i) {
        return unwrap().getLong(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public long _getLong(int i) {
        return unwrap().getLong(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long getLongLE(int i) {
        return unwrap().getLongLE(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public long _getLongLE(int i) {
        return unwrap().getLongLE(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf copy(int i, int i2) {
        return unwrap().copy(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf slice(int i, int i2) {
        return unwrap().slice(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, ByteBuf byteBuf, int i2, int i3) {
        unwrap().getBytes(i, byteBuf, i2, i3);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, byte[] bArr, int i2, int i3) {
        unwrap().getBytes(i, bArr, i2, i3);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, ByteBuffer byteBuffer) {
        unwrap().getBytes(i, byteBuffer);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setByte(int i, int i2) {
        unwrap().setByte(i, i2);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setByte(int i, int i2) {
        unwrap().setByte(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setShort(int i, int i2) {
        unwrap().setShort(i, i2);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setShort(int i, int i2) {
        unwrap().setShort(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setShortLE(int i, int i2) {
        unwrap().setShortLE(i, i2);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setShortLE(int i, int i2) {
        unwrap().setShortLE(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setMedium(int i, int i2) {
        unwrap().setMedium(i, i2);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setMedium(int i, int i2) {
        unwrap().setMedium(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setMediumLE(int i, int i2) {
        unwrap().setMediumLE(i, i2);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setMediumLE(int i, int i2) {
        unwrap().setMediumLE(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setInt(int i, int i2) {
        unwrap().setInt(i, i2);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setInt(int i, int i2) {
        unwrap().setInt(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setIntLE(int i, int i2) {
        unwrap().setIntLE(i, i2);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setIntLE(int i, int i2) {
        unwrap().setIntLE(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setLong(int i, long j) {
        unwrap().setLong(i, j);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setLong(int i, long j) {
        unwrap().setLong(i, j);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setLongLE(int i, long j) {
        unwrap().setLongLE(i, j);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setLongLE(int i, long j) {
        unwrap().setLongLE(i, j);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, byte[] bArr, int i2, int i3) {
        unwrap().setBytes(i, bArr, i2, i3);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, ByteBuf byteBuf, int i2, int i3) {
        unwrap().setBytes(i, byteBuf, i2, i3);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, ByteBuffer byteBuffer) {
        unwrap().setBytes(i, byteBuffer);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, OutputStream outputStream, int i2) throws IOException {
        unwrap().getBytes(i, outputStream, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getBytes(int i, GatheringByteChannel gatheringByteChannel, int i2) throws IOException {
        return unwrap().getBytes(i, gatheringByteChannel, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getBytes(int i, FileChannel fileChannel, long j, int i2) throws IOException {
        return unwrap().getBytes(i, fileChannel, j, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int setBytes(int i, InputStream inputStream, int i2) throws IOException {
        return unwrap().setBytes(i, inputStream, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int setBytes(int i, ScatteringByteChannel scatteringByteChannel, int i2) throws IOException {
        return unwrap().setBytes(i, scatteringByteChannel, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int setBytes(int i, FileChannel fileChannel, long j, int i2) throws IOException {
        return unwrap().setBytes(i, fileChannel, j, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int nioBufferCount() {
        return unwrap().nioBufferCount();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer[] nioBuffers(int i, int i2) {
        return unwrap().nioBuffers(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int forEachByte(int i, int i2, ByteProcessor byteProcessor) {
        return unwrap().forEachByte(i, i2, byteProcessor);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int forEachByteDesc(int i, int i2, ByteProcessor byteProcessor) {
        return unwrap().forEachByteDesc(i, i2, byteProcessor);
    }
}
