package org.jboss.netty.handler.codec.replay;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferFactory;
import org.jboss.netty.buffer.ChannelBufferIndexFinder;

/* loaded from: classes7.dex */
class ReplayingDecoderBuffer implements ChannelBuffer {
    private static final Error REPLAY = new ReplayError();
    private final ReplayingDecoder<?> parent;
    private boolean terminated;

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public boolean equals(Object obj) {
        return this == obj;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public boolean hasArray() {
        return false;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public boolean writable() {
        return false;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int writableBytes() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReplayingDecoderBuffer(ReplayingDecoder<?> replayingDecoder) {
        this.parent = replayingDecoder;
    }

    private ChannelBuffer buf() {
        return this.parent.internalBuffer();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void terminate() {
        this.terminated = true;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int capacity() {
        if (this.terminated) {
            return buf().capacity();
        }
        return Integer.MAX_VALUE;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public boolean isDirect() {
        return buf().isDirect();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public byte[] array() {
        throw new UnsupportedOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int arrayOffset() {
        throw new UnsupportedOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void clear() {
        throw new UnreplayableOperationException();
    }

    @Override // java.lang.Comparable
    public int compareTo(ChannelBuffer channelBuffer) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ChannelBuffer copy() {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ChannelBuffer copy(int i, int i2) {
        checkIndex(i, i2);
        return buf().copy(i, i2);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void discardReadBytes() {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void ensureWritableBytes(int i) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ChannelBuffer duplicate() {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public byte getByte(int i) {
        checkIndex(i);
        return buf().getByte(i);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public short getUnsignedByte(int i) {
        checkIndex(i);
        return buf().getUnsignedByte(i);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void getBytes(int i, byte[] bArr, int i2, int i3) {
        checkIndex(i, i3);
        buf().getBytes(i, bArr, i2, i3);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void getBytes(int i, byte[] bArr) {
        checkIndex(i, bArr.length);
        buf().getBytes(i, bArr);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void getBytes(int i, ByteBuffer byteBuffer) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void getBytes(int i, ChannelBuffer channelBuffer, int i2, int i3) {
        checkIndex(i, i3);
        buf().getBytes(i, channelBuffer, i2, i3);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void getBytes(int i, ChannelBuffer channelBuffer, int i2) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void getBytes(int i, ChannelBuffer channelBuffer) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int getBytes(int i, GatheringByteChannel gatheringByteChannel, int i2) throws IOException {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void getBytes(int i, OutputStream outputStream, int i2) throws IOException {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int getInt(int i) {
        checkIndex(i, 4);
        return buf().getInt(i);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public long getUnsignedInt(int i) {
        checkIndex(i, 4);
        return buf().getUnsignedInt(i);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public long getLong(int i) {
        checkIndex(i, 8);
        return buf().getLong(i);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int getMedium(int i) {
        checkIndex(i, 3);
        return buf().getMedium(i);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int getUnsignedMedium(int i) {
        checkIndex(i, 3);
        return buf().getUnsignedMedium(i);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public short getShort(int i) {
        checkIndex(i, 2);
        return buf().getShort(i);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int getUnsignedShort(int i) {
        checkIndex(i, 2);
        return buf().getUnsignedShort(i);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public char getChar(int i) {
        checkIndex(i, 2);
        return buf().getChar(i);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public float getFloat(int i) {
        checkIndex(i, 4);
        return buf().getFloat(i);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public double getDouble(int i) {
        checkIndex(i, 8);
        return buf().getDouble(i);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int hashCode() {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int indexOf(int i, int i2, byte b) {
        int indexOf = buf().indexOf(i, i2, b);
        if (indexOf >= 0) {
            return indexOf;
        }
        throw REPLAY;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int indexOf(int i, int i2, ChannelBufferIndexFinder channelBufferIndexFinder) {
        int indexOf = buf().indexOf(i, i2, channelBufferIndexFinder);
        if (indexOf >= 0) {
            return indexOf;
        }
        throw REPLAY;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int bytesBefore(byte b) {
        int bytesBefore = buf().bytesBefore(b);
        if (bytesBefore >= 0) {
            return bytesBefore;
        }
        throw REPLAY;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int bytesBefore(ChannelBufferIndexFinder channelBufferIndexFinder) {
        int bytesBefore = buf().bytesBefore(channelBufferIndexFinder);
        if (bytesBefore >= 0) {
            return bytesBefore;
        }
        throw REPLAY;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int bytesBefore(int i, byte b) {
        checkReadableBytes(i);
        int bytesBefore = buf().bytesBefore(i, b);
        if (bytesBefore >= 0) {
            return bytesBefore;
        }
        throw REPLAY;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int bytesBefore(int i, ChannelBufferIndexFinder channelBufferIndexFinder) {
        checkReadableBytes(i);
        int bytesBefore = buf().bytesBefore(i, channelBufferIndexFinder);
        if (bytesBefore >= 0) {
            return bytesBefore;
        }
        throw REPLAY;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int bytesBefore(int i, int i2, byte b) {
        int bytesBefore = buf().bytesBefore(i, i2, b);
        if (bytesBefore >= 0) {
            return bytesBefore;
        }
        throw REPLAY;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int bytesBefore(int i, int i2, ChannelBufferIndexFinder channelBufferIndexFinder) {
        int bytesBefore = buf().bytesBefore(i, i2, channelBufferIndexFinder);
        if (bytesBefore >= 0) {
            return bytesBefore;
        }
        throw REPLAY;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void markReaderIndex() {
        buf().markReaderIndex();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void markWriterIndex() {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ChannelBufferFactory factory() {
        return buf().factory();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ByteOrder order() {
        return buf().order();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public boolean readable() {
        if (this.terminated) {
            return buf().readable();
        }
        return true;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int readableBytes() {
        if (this.terminated) {
            return buf().readableBytes();
        }
        return Integer.MAX_VALUE - buf().readerIndex();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public byte readByte() {
        checkReadableBytes(1);
        return buf().readByte();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public short readUnsignedByte() {
        checkReadableBytes(1);
        return buf().readUnsignedByte();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void readBytes(byte[] bArr, int i, int i2) {
        checkReadableBytes(i2);
        buf().readBytes(bArr, i, i2);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void readBytes(byte[] bArr) {
        checkReadableBytes(bArr.length);
        buf().readBytes(bArr);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void readBytes(ByteBuffer byteBuffer) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void readBytes(ChannelBuffer channelBuffer, int i, int i2) {
        checkReadableBytes(i2);
        buf().readBytes(channelBuffer, i, i2);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void readBytes(ChannelBuffer channelBuffer, int i) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void readBytes(ChannelBuffer channelBuffer) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    @Deprecated
    public ChannelBuffer readBytes(ChannelBufferIndexFinder channelBufferIndexFinder) {
        int indexOf = buf().indexOf(buf().readerIndex(), buf().writerIndex(), channelBufferIndexFinder);
        if (indexOf < 0) {
            throw REPLAY;
        }
        return buf().readBytes(indexOf - buf().readerIndex());
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int readBytes(GatheringByteChannel gatheringByteChannel, int i) throws IOException {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ChannelBuffer readBytes(int i) {
        checkReadableBytes(i);
        return buf().readBytes(i);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    @Deprecated
    public ChannelBuffer readSlice(ChannelBufferIndexFinder channelBufferIndexFinder) {
        int indexOf = buf().indexOf(buf().readerIndex(), buf().writerIndex(), channelBufferIndexFinder);
        if (indexOf < 0) {
            throw REPLAY;
        }
        return buf().readSlice(indexOf - buf().readerIndex());
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ChannelBuffer readSlice(int i) {
        checkReadableBytes(i);
        return buf().readSlice(i);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void readBytes(OutputStream outputStream, int i) throws IOException {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int readerIndex() {
        return buf().readerIndex();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void readerIndex(int i) {
        buf().readerIndex(i);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int readInt() {
        checkReadableBytes(4);
        return buf().readInt();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public long readUnsignedInt() {
        checkReadableBytes(4);
        return buf().readUnsignedInt();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public long readLong() {
        checkReadableBytes(8);
        return buf().readLong();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int readMedium() {
        checkReadableBytes(3);
        return buf().readMedium();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int readUnsignedMedium() {
        checkReadableBytes(3);
        return buf().readUnsignedMedium();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public short readShort() {
        checkReadableBytes(2);
        return buf().readShort();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int readUnsignedShort() {
        checkReadableBytes(2);
        return buf().readUnsignedShort();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public char readChar() {
        checkReadableBytes(2);
        return buf().readChar();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public float readFloat() {
        checkReadableBytes(4);
        return buf().readFloat();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public double readDouble() {
        checkReadableBytes(8);
        return buf().readDouble();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void resetReaderIndex() {
        buf().resetReaderIndex();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void resetWriterIndex() {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setByte(int i, int i2) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setBytes(int i, byte[] bArr, int i2, int i3) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setBytes(int i, byte[] bArr) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setBytes(int i, ByteBuffer byteBuffer) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setBytes(int i, ChannelBuffer channelBuffer, int i2, int i3) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setBytes(int i, ChannelBuffer channelBuffer, int i2) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setBytes(int i, ChannelBuffer channelBuffer) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int setBytes(int i, InputStream inputStream, int i2) throws IOException {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setZero(int i, int i2) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int setBytes(int i, ScatteringByteChannel scatteringByteChannel, int i2) throws IOException {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setIndex(int i, int i2) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setInt(int i, int i2) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setLong(int i, long j) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setMedium(int i, int i2) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setShort(int i, int i2) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setChar(int i, int i2) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setFloat(int i, float f) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setDouble(int i, double d) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    @Deprecated
    public int skipBytes(ChannelBufferIndexFinder channelBufferIndexFinder) {
        int readerIndex = buf().readerIndex();
        int indexOf = buf().indexOf(readerIndex, buf().writerIndex(), channelBufferIndexFinder);
        if (indexOf < 0) {
            throw REPLAY;
        }
        buf().readerIndex(indexOf);
        return indexOf - readerIndex;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void skipBytes(int i) {
        checkReadableBytes(i);
        buf().skipBytes(i);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ChannelBuffer slice() {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ChannelBuffer slice(int i, int i2) {
        checkIndex(i, i2);
        return buf().slice(i, i2);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ByteBuffer toByteBuffer() {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ByteBuffer toByteBuffer(int i, int i2) {
        checkIndex(i, i2);
        return buf().toByteBuffer(i, i2);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ByteBuffer[] toByteBuffers() {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ByteBuffer[] toByteBuffers(int i, int i2) {
        checkIndex(i, i2);
        return buf().toByteBuffers(i, i2);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public String toString(int i, int i2, Charset charset) {
        checkIndex(i, i2);
        return buf().toString(i, i2, charset);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public String toString(Charset charset) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    @Deprecated
    public String toString(int i, int i2, String str) {
        checkIndex(i, i2);
        return buf().toString(i, i2, str);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    @Deprecated
    public String toString(int i, int i2, String str, ChannelBufferIndexFinder channelBufferIndexFinder) {
        checkIndex(i, i2);
        return buf().toString(i, i2, str, channelBufferIndexFinder);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    @Deprecated
    public String toString(String str) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    @Deprecated
    public String toString(String str, ChannelBufferIndexFinder channelBufferIndexFinder) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public String toString() {
        return getClass().getSimpleName() + "(ridx=" + readerIndex() + ", widx=" + writerIndex() + ')';
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void writeByte(int i) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void writeBytes(byte[] bArr, int i, int i2) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void writeBytes(byte[] bArr) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void writeBytes(ByteBuffer byteBuffer) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void writeBytes(ChannelBuffer channelBuffer, int i, int i2) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void writeBytes(ChannelBuffer channelBuffer, int i) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void writeBytes(ChannelBuffer channelBuffer) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int writeBytes(InputStream inputStream, int i) throws IOException {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int writeBytes(ScatteringByteChannel scatteringByteChannel, int i) throws IOException {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void writeInt(int i) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void writeLong(long j) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void writeMedium(int i) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void writeZero(int i) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int writerIndex() {
        return buf().writerIndex();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void writerIndex(int i) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void writeShort(int i) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void writeChar(int i) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void writeFloat(float f) {
        throw new UnreplayableOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void writeDouble(double d) {
        throw new UnreplayableOperationException();
    }

    private void checkIndex(int i) {
        if (i > buf().writerIndex()) {
            throw REPLAY;
        }
    }

    private void checkIndex(int i, int i2) {
        if (i + i2 > buf().writerIndex()) {
            throw REPLAY;
        }
    }

    private void checkReadableBytes(int i) {
        if (buf().readableBytes() < i) {
            throw REPLAY;
        }
    }
}
