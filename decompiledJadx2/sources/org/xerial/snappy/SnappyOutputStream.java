package org.xerial.snappy;

import java.io.IOException;
import java.io.OutputStream;
import org.xerial.snappy.buffer.BufferAllocator;
import org.xerial.snappy.buffer.BufferAllocatorFactory;
import org.xerial.snappy.buffer.CachedBufferAllocator;

/* loaded from: classes9.dex */
public class SnappyOutputStream extends OutputStream {
    static final int DEFAULT_BLOCK_SIZE = 32768;
    static final int MIN_BLOCK_SIZE = 1024;
    private final int blockSize;
    private boolean closed;
    private boolean headerWritten;
    protected byte[] inputBuffer;
    private final BufferAllocator inputBufferAllocator;
    private int inputCursor;
    protected final OutputStream out;
    protected byte[] outputBuffer;
    private final BufferAllocator outputBufferAllocator;
    private int outputCursor;

    protected void writeBlockPreemble() {
    }

    public SnappyOutputStream(OutputStream outputStream) {
        this(outputStream, 32768);
    }

    public SnappyOutputStream(OutputStream outputStream, int i) {
        this(outputStream, i, CachedBufferAllocator.getBufferAllocatorFactory());
    }

    public SnappyOutputStream(OutputStream outputStream, int i, BufferAllocatorFactory bufferAllocatorFactory) {
        this.inputCursor = 0;
        this.outputCursor = 0;
        this.out = outputStream;
        this.blockSize = Math.max(1024, i);
        int maxCompressedLength = SnappyCodec.HEADER_SIZE + 4 + Snappy.maxCompressedLength(i);
        this.inputBufferAllocator = bufferAllocatorFactory.getBufferAllocator(i);
        this.outputBufferAllocator = bufferAllocatorFactory.getBufferAllocator(maxCompressedLength);
        this.inputBuffer = this.inputBufferAllocator.allocate(i);
        this.outputBuffer = this.outputBufferAllocator.allocate(maxCompressedLength);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (this.closed) {
            throw new IOException("Stream is closed");
        }
        int i3 = 0;
        while (i3 < i2) {
            int min = Math.min(i2 - i3, this.blockSize - this.inputCursor);
            if (min > 0) {
                System.arraycopy(bArr, i + i3, this.inputBuffer, this.inputCursor, min);
                this.inputCursor += min;
            }
            if (this.inputCursor < this.blockSize) {
                return;
            }
            compressInput();
            i3 += min;
        }
    }

    public void write(long[] jArr, int i, int i2) throws IOException {
        rawWrite(jArr, i * 8, i2 * 8);
    }

    public void write(double[] dArr, int i, int i2) throws IOException {
        rawWrite(dArr, i * 8, i2 * 8);
    }

    public void write(float[] fArr, int i, int i2) throws IOException {
        rawWrite(fArr, i * 4, i2 * 4);
    }

    public void write(int[] iArr, int i, int i2) throws IOException {
        rawWrite(iArr, i * 4, i2 * 4);
    }

    public void write(short[] sArr, int i, int i2) throws IOException {
        rawWrite(sArr, i * 2, i2 * 2);
    }

    public void write(long[] jArr) throws IOException {
        write(jArr, 0, jArr.length);
    }

    public void write(double[] dArr) throws IOException {
        write(dArr, 0, dArr.length);
    }

    public void write(float[] fArr) throws IOException {
        write(fArr, 0, fArr.length);
    }

    public void write(int[] iArr) throws IOException {
        write(iArr, 0, iArr.length);
    }

    public void write(short[] sArr) throws IOException {
        write(sArr, 0, sArr.length);
    }

    private boolean hasSufficientOutputBufferFor(int i) {
        return Snappy.maxCompressedLength(i) < (this.outputBuffer.length - this.outputCursor) + (-4);
    }

    public void rawWrite(Object obj, int i, int i2) throws IOException {
        if (this.closed) {
            throw new IOException("Stream is closed");
        }
        int i3 = 0;
        while (i3 < i2) {
            int min = Math.min(i2 - i3, this.blockSize - this.inputCursor);
            if (min > 0) {
                Snappy.arrayCopy(obj, i + i3, min, this.inputBuffer, this.inputCursor);
                this.inputCursor += min;
            }
            if (this.inputCursor < this.blockSize) {
                return;
            }
            compressInput();
            i3 += min;
        }
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        if (this.closed) {
            throw new IOException("Stream is closed");
        }
        if (this.inputCursor >= this.inputBuffer.length) {
            compressInput();
        }
        byte[] bArr = this.inputBuffer;
        int i2 = this.inputCursor;
        this.inputCursor = i2 + 1;
        bArr[i2] = (byte) i;
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        if (this.closed) {
            throw new IOException("Stream is closed");
        }
        compressInput();
        dumpOutput();
        this.out.flush();
    }

    static void writeInt(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) ((i2 >> 24) & 255);
        bArr[i + 1] = (byte) ((i2 >> 16) & 255);
        bArr[i + 2] = (byte) ((i2 >> 8) & 255);
        bArr[i + 3] = (byte) ((i2 >> 0) & 255);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int readInt(byte[] bArr, int i) {
        int i2 = (bArr[i] & 255) << 24;
        int i3 = (bArr[i + 1] & 255) << 16;
        return (bArr[i + 3] & 255) | i2 | i3 | ((bArr[i + 2] & 255) << 8);
    }

    protected void dumpOutput() throws IOException {
        int i = this.outputCursor;
        if (i > 0) {
            this.out.write(this.outputBuffer, 0, i);
            this.outputCursor = 0;
        }
    }

    protected void compressInput() throws IOException {
        if (!this.headerWritten) {
            this.outputCursor = writeHeader();
            this.headerWritten = true;
        }
        int i = this.inputCursor;
        if (i <= 0) {
            return;
        }
        if (!hasSufficientOutputBufferFor(i)) {
            dumpOutput();
        }
        writeBlockPreemble();
        int compress = Snappy.compress(this.inputBuffer, 0, this.inputCursor, this.outputBuffer, this.outputCursor + 4);
        writeInt(this.outputBuffer, this.outputCursor, compress);
        this.outputCursor += compress + 4;
        this.inputCursor = 0;
    }

    protected int writeHeader() {
        return SnappyCodec.currentHeader.writeHeader(this.outputBuffer, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void writeCurrentDataSize() {
        writeInt(this.outputBuffer, this.outputCursor, this.inputCursor);
        this.outputCursor += 4;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        try {
            flush();
            this.out.close();
        } finally {
            this.closed = true;
            this.inputBufferAllocator.release(this.inputBuffer);
            this.outputBufferAllocator.release(this.outputBuffer);
            this.inputBuffer = null;
            this.outputBuffer = null;
        }
    }
}
