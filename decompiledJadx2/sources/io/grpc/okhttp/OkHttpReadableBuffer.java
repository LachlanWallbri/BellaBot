package io.grpc.okhttp;

import io.grpc.internal.AbstractReadableBuffer;
import io.grpc.internal.ReadableBuffer;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import okio.Buffer;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
class OkHttpReadableBuffer extends AbstractReadableBuffer {
    private final Buffer buffer;

    private void fakeEofExceptionMethod() throws EOFException {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OkHttpReadableBuffer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override // io.grpc.internal.ReadableBuffer
    public int readableBytes() {
        return (int) this.buffer.size();
    }

    @Override // io.grpc.internal.ReadableBuffer
    public int readUnsignedByte() {
        try {
            fakeEofExceptionMethod();
            return this.buffer.readByte() & 255;
        } catch (EOFException e) {
            throw new IndexOutOfBoundsException(e.getMessage());
        }
    }

    @Override // io.grpc.internal.ReadableBuffer
    public void skipBytes(int i) {
        try {
            this.buffer.skip(i);
        } catch (EOFException e) {
            throw new IndexOutOfBoundsException(e.getMessage());
        }
    }

    @Override // io.grpc.internal.ReadableBuffer
    public void readBytes(byte[] bArr, int i, int i2) {
        while (i2 > 0) {
            int read = this.buffer.read(bArr, i, i2);
            if (read == -1) {
                throw new IndexOutOfBoundsException("EOF trying to read " + i2 + " bytes");
            }
            i2 -= read;
            i += read;
        }
    }

    @Override // io.grpc.internal.ReadableBuffer
    public void readBytes(ByteBuffer byteBuffer) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.internal.ReadableBuffer
    public void readBytes(OutputStream outputStream, int i) throws IOException {
        this.buffer.writeTo(outputStream, i);
    }

    @Override // io.grpc.internal.ReadableBuffer
    public ReadableBuffer readBytes(int i) {
        Buffer buffer = new Buffer();
        buffer.write(this.buffer, i);
        return new OkHttpReadableBuffer(buffer);
    }

    @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.buffer.clear();
    }
}
