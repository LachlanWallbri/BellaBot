package io.grpc.internal;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.iflytek.cloud.SpeechEvent;
import io.grpc.Detachable;
import io.grpc.HasByteBuffer;
import io.grpc.KnownLength;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.InvalidMarkException;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ReadableBuffers {
    private static final ReadableBuffer EMPTY_BUFFER = new ByteArrayWrapper(new byte[0]);

    public static ReadableBuffer empty() {
        return EMPTY_BUFFER;
    }

    public static ReadableBuffer wrap(byte[] bArr) {
        return new ByteArrayWrapper(bArr, 0, bArr.length);
    }

    public static ReadableBuffer wrap(byte[] bArr, int i, int i2) {
        return new ByteArrayWrapper(bArr, i, i2);
    }

    public static ReadableBuffer wrap(ByteBuffer byteBuffer) {
        return new ByteReadableBufferWrapper(byteBuffer);
    }

    public static byte[] readArray(ReadableBuffer readableBuffer) {
        Preconditions.checkNotNull(readableBuffer, SpeechEvent.KEY_EVENT_TTS_BUFFER);
        int readableBytes = readableBuffer.readableBytes();
        byte[] bArr = new byte[readableBytes];
        readableBuffer.readBytes(bArr, 0, readableBytes);
        return bArr;
    }

    public static String readAsString(ReadableBuffer readableBuffer, Charset charset) {
        Preconditions.checkNotNull(charset, "charset");
        return new String(readArray(readableBuffer), charset);
    }

    public static String readAsStringUtf8(ReadableBuffer readableBuffer) {
        return readAsString(readableBuffer, Charsets.UTF_8);
    }

    public static InputStream openStream(ReadableBuffer readableBuffer, boolean z) {
        if (!z) {
            readableBuffer = ignoreClose(readableBuffer);
        }
        return new BufferInputStream(readableBuffer);
    }

    public static ReadableBuffer ignoreClose(ReadableBuffer readableBuffer) {
        return new ForwardingReadableBuffer(readableBuffer) { // from class: io.grpc.internal.ReadableBuffers.1
            @Override // io.grpc.internal.ForwardingReadableBuffer, io.grpc.internal.ReadableBuffer, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }
        };
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static class ByteArrayWrapper extends AbstractReadableBuffer {
        final byte[] bytes;
        final int end;
        int mark;
        int offset;

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public boolean hasArray() {
            return true;
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public boolean markSupported() {
            return true;
        }

        ByteArrayWrapper(byte[] bArr) {
            this(bArr, 0, bArr.length);
        }

        ByteArrayWrapper(byte[] bArr, int i, int i2) {
            this.mark = -1;
            Preconditions.checkArgument(i >= 0, "offset must be >= 0");
            Preconditions.checkArgument(i2 >= 0, "length must be >= 0");
            int i3 = i2 + i;
            Preconditions.checkArgument(i3 <= bArr.length, "offset + length exceeds array boundary");
            this.bytes = (byte[]) Preconditions.checkNotNull(bArr, "bytes");
            this.offset = i;
            this.end = i3;
        }

        @Override // io.grpc.internal.ReadableBuffer
        public int readableBytes() {
            return this.end - this.offset;
        }

        @Override // io.grpc.internal.ReadableBuffer
        public void skipBytes(int i) {
            checkReadable(i);
            this.offset += i;
        }

        @Override // io.grpc.internal.ReadableBuffer
        public int readUnsignedByte() {
            checkReadable(1);
            byte[] bArr = this.bytes;
            int i = this.offset;
            this.offset = i + 1;
            return bArr[i] & 255;
        }

        @Override // io.grpc.internal.ReadableBuffer
        public void readBytes(byte[] bArr, int i, int i2) {
            System.arraycopy(this.bytes, this.offset, bArr, i, i2);
            this.offset += i2;
        }

        @Override // io.grpc.internal.ReadableBuffer
        public void readBytes(ByteBuffer byteBuffer) {
            Preconditions.checkNotNull(byteBuffer, "dest");
            int remaining = byteBuffer.remaining();
            checkReadable(remaining);
            byteBuffer.put(this.bytes, this.offset, remaining);
            this.offset += remaining;
        }

        @Override // io.grpc.internal.ReadableBuffer
        public void readBytes(OutputStream outputStream, int i) throws IOException {
            checkReadable(i);
            outputStream.write(this.bytes, this.offset, i);
            this.offset += i;
        }

        @Override // io.grpc.internal.ReadableBuffer
        public ByteArrayWrapper readBytes(int i) {
            checkReadable(i);
            int i2 = this.offset;
            this.offset = i2 + i;
            return new ByteArrayWrapper(this.bytes, i2, i);
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public byte[] array() {
            return this.bytes;
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public int arrayOffset() {
            return this.offset;
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public void mark() {
            this.mark = this.offset;
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public void reset() {
            int i = this.mark;
            if (i == -1) {
                throw new InvalidMarkException();
            }
            this.offset = i;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static class ByteReadableBufferWrapper extends AbstractReadableBuffer {
        final ByteBuffer bytes;

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public boolean byteBufferSupported() {
            return true;
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public boolean markSupported() {
            return true;
        }

        ByteReadableBufferWrapper(ByteBuffer byteBuffer) {
            this.bytes = (ByteBuffer) Preconditions.checkNotNull(byteBuffer, "bytes");
        }

        @Override // io.grpc.internal.ReadableBuffer
        public int readableBytes() {
            return this.bytes.remaining();
        }

        @Override // io.grpc.internal.ReadableBuffer
        public int readUnsignedByte() {
            checkReadable(1);
            return this.bytes.get() & 255;
        }

        @Override // io.grpc.internal.ReadableBuffer
        public void skipBytes(int i) {
            checkReadable(i);
            ByteBuffer byteBuffer = this.bytes;
            byteBuffer.position(byteBuffer.position() + i);
        }

        @Override // io.grpc.internal.ReadableBuffer
        public void readBytes(byte[] bArr, int i, int i2) {
            checkReadable(i2);
            this.bytes.get(bArr, i, i2);
        }

        @Override // io.grpc.internal.ReadableBuffer
        public void readBytes(ByteBuffer byteBuffer) {
            Preconditions.checkNotNull(byteBuffer, "dest");
            int remaining = byteBuffer.remaining();
            checkReadable(remaining);
            int limit = this.bytes.limit();
            ByteBuffer byteBuffer2 = this.bytes;
            byteBuffer2.limit(byteBuffer2.position() + remaining);
            byteBuffer.put(this.bytes);
            this.bytes.limit(limit);
        }

        @Override // io.grpc.internal.ReadableBuffer
        public void readBytes(OutputStream outputStream, int i) throws IOException {
            checkReadable(i);
            if (hasArray()) {
                outputStream.write(array(), arrayOffset(), i);
                ByteBuffer byteBuffer = this.bytes;
                byteBuffer.position(byteBuffer.position() + i);
            } else {
                byte[] bArr = new byte[i];
                this.bytes.get(bArr);
                outputStream.write(bArr);
            }
        }

        @Override // io.grpc.internal.ReadableBuffer
        public ByteReadableBufferWrapper readBytes(int i) {
            checkReadable(i);
            ByteBuffer duplicate = this.bytes.duplicate();
            duplicate.limit(this.bytes.position() + i);
            ByteBuffer byteBuffer = this.bytes;
            byteBuffer.position(byteBuffer.position() + i);
            return new ByteReadableBufferWrapper(duplicate);
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public boolean hasArray() {
            return this.bytes.hasArray();
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public byte[] array() {
            return this.bytes.array();
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public int arrayOffset() {
            return this.bytes.arrayOffset() + this.bytes.position();
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public void mark() {
            this.bytes.mark();
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public void reset() {
            this.bytes.reset();
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public ByteBuffer getByteBuffer() {
            return this.bytes.slice();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class BufferInputStream extends InputStream implements KnownLength, HasByteBuffer, Detachable {
        private ReadableBuffer buffer;

        public BufferInputStream(ReadableBuffer readableBuffer) {
            this.buffer = (ReadableBuffer) Preconditions.checkNotNull(readableBuffer, SpeechEvent.KEY_EVENT_TTS_BUFFER);
        }

        @Override // java.io.InputStream, io.grpc.KnownLength
        public int available() throws IOException {
            return this.buffer.readableBytes();
        }

        @Override // java.io.InputStream
        public int read() {
            if (this.buffer.readableBytes() == 0) {
                return -1;
            }
            return this.buffer.readUnsignedByte();
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            if (this.buffer.readableBytes() == 0) {
                return -1;
            }
            int min = Math.min(this.buffer.readableBytes(), i2);
            this.buffer.readBytes(bArr, i, min);
            return min;
        }

        @Override // java.io.InputStream
        public long skip(long j) throws IOException {
            int min = (int) Math.min(this.buffer.readableBytes(), j);
            this.buffer.skipBytes(min);
            return min;
        }

        @Override // java.io.InputStream
        public void mark(int i) {
            this.buffer.mark();
        }

        @Override // java.io.InputStream
        public void reset() throws IOException {
            this.buffer.reset();
        }

        @Override // java.io.InputStream
        public boolean markSupported() {
            return this.buffer.markSupported();
        }

        @Override // io.grpc.HasByteBuffer
        public boolean byteBufferSupported() {
            return this.buffer.byteBufferSupported();
        }

        @Override // io.grpc.HasByteBuffer
        @Nullable
        public ByteBuffer getByteBuffer() {
            return this.buffer.getByteBuffer();
        }

        @Override // io.grpc.Detachable
        public InputStream detach() {
            ReadableBuffer readableBuffer = this.buffer;
            this.buffer = readableBuffer.readBytes(0);
            return new BufferInputStream(readableBuffer);
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.buffer.close();
        }
    }

    private ReadableBuffers() {
    }
}
