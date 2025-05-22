package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class ForwardingReadableBuffer implements ReadableBuffer {
    private final ReadableBuffer buf;

    /* JADX INFO: Access modifiers changed from: protected */
    public ForwardingReadableBuffer(ReadableBuffer readableBuffer) {
        this.buf = (ReadableBuffer) Preconditions.checkNotNull(readableBuffer, "buf");
    }

    @Override // io.grpc.internal.ReadableBuffer
    public int readableBytes() {
        return this.buf.readableBytes();
    }

    @Override // io.grpc.internal.ReadableBuffer
    public int readUnsignedByte() {
        return this.buf.readUnsignedByte();
    }

    @Override // io.grpc.internal.ReadableBuffer
    public int readInt() {
        return this.buf.readInt();
    }

    @Override // io.grpc.internal.ReadableBuffer
    public void skipBytes(int i) {
        this.buf.skipBytes(i);
    }

    @Override // io.grpc.internal.ReadableBuffer
    public void readBytes(byte[] bArr, int i, int i2) {
        this.buf.readBytes(bArr, i, i2);
    }

    @Override // io.grpc.internal.ReadableBuffer
    public void readBytes(ByteBuffer byteBuffer) {
        this.buf.readBytes(byteBuffer);
    }

    @Override // io.grpc.internal.ReadableBuffer
    public void readBytes(OutputStream outputStream, int i) throws IOException {
        this.buf.readBytes(outputStream, i);
    }

    @Override // io.grpc.internal.ReadableBuffer
    public ReadableBuffer readBytes(int i) {
        return this.buf.readBytes(i);
    }

    @Override // io.grpc.internal.ReadableBuffer
    public boolean hasArray() {
        return this.buf.hasArray();
    }

    @Override // io.grpc.internal.ReadableBuffer
    public byte[] array() {
        return this.buf.array();
    }

    @Override // io.grpc.internal.ReadableBuffer
    public int arrayOffset() {
        return this.buf.arrayOffset();
    }

    @Override // io.grpc.internal.ReadableBuffer
    public boolean markSupported() {
        return this.buf.markSupported();
    }

    @Override // io.grpc.internal.ReadableBuffer
    public void mark() {
        this.buf.mark();
    }

    @Override // io.grpc.internal.ReadableBuffer
    public void reset() {
        this.buf.reset();
    }

    @Override // io.grpc.internal.ReadableBuffer
    public boolean byteBufferSupported() {
        return this.buf.byteBufferSupported();
    }

    @Override // io.grpc.internal.ReadableBuffer
    @Nullable
    public ByteBuffer getByteBuffer() {
        return this.buf.getByteBuffer();
    }

    @Override // io.grpc.internal.ReadableBuffer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.buf.close();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", this.buf).toString();
    }
}
