package io.netty.buffer;

import com.iflytek.cloud.SpeechEvent;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import kotlin.UShort;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public class ByteBufInputStream extends InputStream implements DataInput {
    private final ByteBuf buffer;
    private boolean closed;
    private final int endIndex;
    private final StringBuilder lineBuf;
    private final boolean releaseOnClose;
    private final int startIndex;

    @Override // java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    public ByteBufInputStream(ByteBuf byteBuf) {
        this(byteBuf, byteBuf.readableBytes());
    }

    public ByteBufInputStream(ByteBuf byteBuf, int i) {
        this(byteBuf, i, false);
    }

    public ByteBufInputStream(ByteBuf byteBuf, boolean z) {
        this(byteBuf, byteBuf.readableBytes(), z);
    }

    public ByteBufInputStream(ByteBuf byteBuf, int i, boolean z) {
        this.lineBuf = new StringBuilder();
        if (byteBuf == null) {
            throw new NullPointerException(SpeechEvent.KEY_EVENT_TTS_BUFFER);
        }
        if (i < 0) {
            if (z) {
                byteBuf.release();
            }
            throw new IllegalArgumentException("length: " + i);
        }
        if (i > byteBuf.readableBytes()) {
            if (z) {
                byteBuf.release();
            }
            throw new IndexOutOfBoundsException("Too many bytes to be read - Needs " + i + ", maximum is " + byteBuf.readableBytes());
        }
        this.releaseOnClose = z;
        this.buffer = byteBuf;
        int readerIndex = byteBuf.readerIndex();
        this.startIndex = readerIndex;
        this.endIndex = readerIndex + i;
        byteBuf.markReaderIndex();
    }

    public int readBytes() {
        return this.buffer.readerIndex() - this.startIndex;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            super.close();
        } finally {
            if (this.releaseOnClose && !this.closed) {
                this.closed = true;
                this.buffer.release();
            }
        }
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.endIndex - this.buffer.readerIndex();
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        this.buffer.markReaderIndex();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.buffer.isReadable()) {
            return this.buffer.readByte() & 255;
        }
        return -1;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int available = available();
        if (available == 0) {
            return -1;
        }
        int min = Math.min(available, i2);
        this.buffer.readBytes(bArr, i, min);
        return min;
    }

    @Override // java.io.InputStream
    public void reset() throws IOException {
        this.buffer.resetReaderIndex();
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        int skipBytes;
        if (j > 2147483647L) {
            skipBytes = skipBytes(Integer.MAX_VALUE);
        } else {
            skipBytes = skipBytes((int) j);
        }
        return skipBytes;
    }

    @Override // java.io.DataInput
    public boolean readBoolean() throws IOException {
        checkAvailable(1);
        return read() != 0;
    }

    @Override // java.io.DataInput
    public byte readByte() throws IOException {
        if (!this.buffer.isReadable()) {
            throw new EOFException();
        }
        return this.buffer.readByte();
    }

    @Override // java.io.DataInput
    public char readChar() throws IOException {
        return (char) readShort();
    }

    @Override // java.io.DataInput
    public double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }

    @Override // java.io.DataInput
    public float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }

    @Override // java.io.DataInput
    public void readFully(byte[] bArr) throws IOException {
        readFully(bArr, 0, bArr.length);
    }

    @Override // java.io.DataInput
    public void readFully(byte[] bArr, int i, int i2) throws IOException {
        checkAvailable(i2);
        this.buffer.readBytes(bArr, i, i2);
    }

    @Override // java.io.DataInput
    public int readInt() throws IOException {
        checkAvailable(4);
        return this.buffer.readInt();
    }

    @Override // java.io.DataInput
    public String readLine() throws IOException {
        this.lineBuf.setLength(0);
        while (this.buffer.isReadable()) {
            short readUnsignedByte = this.buffer.readUnsignedByte();
            if (readUnsignedByte != 10) {
                if (readUnsignedByte == 13) {
                    if (this.buffer.isReadable()) {
                        ByteBuf byteBuf = this.buffer;
                        if (((char) byteBuf.getUnsignedByte(byteBuf.readerIndex())) == '\n') {
                            this.buffer.skipBytes(1);
                        }
                    }
                } else {
                    this.lineBuf.append((char) readUnsignedByte);
                }
            }
            return this.lineBuf.toString();
        }
        if (this.lineBuf.length() > 0) {
            return this.lineBuf.toString();
        }
        return null;
    }

    @Override // java.io.DataInput
    public long readLong() throws IOException {
        checkAvailable(8);
        return this.buffer.readLong();
    }

    @Override // java.io.DataInput
    public short readShort() throws IOException {
        checkAvailable(2);
        return this.buffer.readShort();
    }

    @Override // java.io.DataInput
    public String readUTF() throws IOException {
        return DataInputStream.readUTF(this);
    }

    @Override // java.io.DataInput
    public int readUnsignedByte() throws IOException {
        return readByte() & 255;
    }

    @Override // java.io.DataInput
    public int readUnsignedShort() throws IOException {
        return readShort() & UShort.MAX_VALUE;
    }

    @Override // java.io.DataInput
    public int skipBytes(int i) throws IOException {
        int min = Math.min(available(), i);
        this.buffer.skipBytes(min);
        return min;
    }

    private void checkAvailable(int i) throws IOException {
        if (i < 0) {
            throw new IndexOutOfBoundsException("fieldSize cannot be a negative number");
        }
        if (i <= available()) {
            return;
        }
        throw new EOFException("fieldSize is too long! Length is " + i + ", but maximum is " + available());
    }
}
