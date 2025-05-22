package org.xerial.snappy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.Channels;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.zip.Checksum;
import org.xerial.snappy.pool.BufferPool;
import org.xerial.snappy.pool.DefaultPoolFactory;

/* loaded from: classes9.dex */
public final class SnappyFramedOutputStream extends OutputStream implements WritableByteChannel {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int DEFAULT_BLOCK_SIZE = 65536;
    public static final double DEFAULT_MIN_COMPRESSION_RATIO = 0.85d;
    public static final int MAX_BLOCK_SIZE = 65536;
    private final int blockSize;
    private final ByteBuffer buffer;
    private final BufferPool bufferPool;
    private boolean closed;
    private final Checksum crc32;
    private final ByteBuffer directInputBuffer;
    private final ByteBuffer headerBuffer;
    private final double minCompressionRatio;
    private final WritableByteChannel out;
    private final ByteBuffer outputBuffer;

    public SnappyFramedOutputStream(OutputStream outputStream) throws IOException {
        this(outputStream, 65536, 0.85d, DefaultPoolFactory.getDefaultPool());
    }

    public SnappyFramedOutputStream(OutputStream outputStream, BufferPool bufferPool) throws IOException {
        this(outputStream, 65536, 0.85d, bufferPool);
    }

    public SnappyFramedOutputStream(OutputStream outputStream, int i, double d) throws IOException {
        this(Channels.newChannel(outputStream), i, d, DefaultPoolFactory.getDefaultPool());
    }

    public SnappyFramedOutputStream(OutputStream outputStream, int i, double d, BufferPool bufferPool) throws IOException {
        this(Channels.newChannel(outputStream), i, d, bufferPool);
    }

    public SnappyFramedOutputStream(WritableByteChannel writableByteChannel) throws IOException {
        this(writableByteChannel, 65536, 0.85d, DefaultPoolFactory.getDefaultPool());
    }

    public SnappyFramedOutputStream(WritableByteChannel writableByteChannel, BufferPool bufferPool) throws IOException {
        this(writableByteChannel, 65536, 0.85d, bufferPool);
    }

    public SnappyFramedOutputStream(WritableByteChannel writableByteChannel, int i, double d) throws IOException {
        this(writableByteChannel, i, d, DefaultPoolFactory.getDefaultPool());
    }

    public SnappyFramedOutputStream(WritableByteChannel writableByteChannel, int i, double d, BufferPool bufferPool) throws IOException {
        this.crc32 = SnappyFramed.getCRC32C();
        this.headerBuffer = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
        if (writableByteChannel == null) {
            throw new NullPointerException("out is null");
        }
        if (bufferPool == null) {
            throw new NullPointerException("buffer pool is null");
        }
        if (d <= 0.0d || d > 1.0d) {
            throw new IllegalArgumentException("minCompressionRatio " + d + " must be in (0,1.0]");
        }
        if (i <= 0 || i > 65536) {
            throw new IllegalArgumentException("block size " + i + " must be in (0, 65536]");
        }
        this.blockSize = i;
        this.out = writableByteChannel;
        this.minCompressionRatio = d;
        this.bufferPool = bufferPool;
        this.buffer = ByteBuffer.wrap(bufferPool.allocateArray(i), 0, i);
        this.directInputBuffer = bufferPool.allocateDirect(i);
        this.outputBuffer = bufferPool.allocateDirect(Snappy.maxCompressedLength(i));
        writeHeader(writableByteChannel);
    }

    private void writeHeader(WritableByteChannel writableByteChannel) throws IOException {
        writableByteChannel.write(ByteBuffer.wrap(SnappyFramed.HEADER_BYTES));
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.closed;
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        if (this.closed) {
            throw new IOException("Stream is closed");
        }
        if (this.buffer.remaining() <= 0) {
            flushBuffer();
        }
        this.buffer.put((byte) i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        if (this.closed) {
            throw new IOException("Stream is closed");
        }
        if (bArr == null) {
            throw new NullPointerException();
        }
        if (i < 0 || i > bArr.length || i2 < 0 || (i3 = i + i2) > bArr.length || i3 < 0) {
            throw new IndexOutOfBoundsException();
        }
        while (i2 > 0) {
            if (this.buffer.remaining() <= 0) {
                flushBuffer();
            }
            int min = Math.min(i2, this.buffer.remaining());
            this.buffer.put(bArr, i, min);
            i += min;
            i2 -= min;
        }
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) throws IOException {
        if (this.closed) {
            throw new ClosedChannelException();
        }
        if (this.buffer.remaining() <= 0) {
            flushBuffer();
        }
        int remaining = byteBuffer.remaining();
        if (this.buffer.remaining() >= byteBuffer.remaining()) {
            this.buffer.put(byteBuffer);
            return remaining;
        }
        int position = byteBuffer.position() + byteBuffer.remaining();
        while (byteBuffer.position() + this.buffer.remaining() <= position) {
            byteBuffer.limit(byteBuffer.position() + this.buffer.remaining());
            this.buffer.put(byteBuffer);
            flushBuffer();
        }
        byteBuffer.limit(position);
        this.buffer.put(byteBuffer);
        return remaining;
    }

    public long transferFrom(InputStream inputStream) throws IOException {
        if (this.closed) {
            throw new ClosedChannelException();
        }
        if (inputStream == null) {
            throw new NullPointerException();
        }
        if (this.buffer.remaining() == 0) {
            flushBuffer();
        }
        byte[] array = this.buffer.array();
        int arrayOffset = this.buffer.arrayOffset();
        long j = 0;
        while (true) {
            int read = inputStream.read(array, this.buffer.position() + arrayOffset, this.buffer.remaining());
            if (read == -1) {
                return j;
            }
            ByteBuffer byteBuffer = this.buffer;
            byteBuffer.position(byteBuffer.position() + read);
            if (this.buffer.remaining() == 0) {
                flushBuffer();
            }
            j += read;
        }
    }

    public long transferFrom(ReadableByteChannel readableByteChannel) throws IOException {
        if (this.closed) {
            throw new ClosedChannelException();
        }
        if (readableByteChannel == null) {
            throw new NullPointerException();
        }
        if (this.buffer.remaining() == 0) {
            flushBuffer();
        }
        long j = 0;
        while (true) {
            int read = readableByteChannel.read(this.buffer);
            if (read == -1) {
                return j;
            }
            if (this.buffer.remaining() == 0) {
                flushBuffer();
            }
            j += read;
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public final void flush() throws IOException {
        if (this.closed) {
            throw new IOException("Stream is closed");
        }
        flushBuffer();
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public final void close() throws IOException {
        if (this.closed) {
            return;
        }
        try {
            flush();
            this.out.close();
        } finally {
            this.closed = true;
            this.bufferPool.releaseArray(this.buffer.array());
            this.bufferPool.releaseDirect(this.directInputBuffer);
            this.bufferPool.releaseDirect(this.outputBuffer);
        }
    }

    private void flushBuffer() throws IOException {
        if (this.buffer.position() > 0) {
            this.buffer.flip();
            writeCompressed(this.buffer);
            this.buffer.clear();
            this.buffer.limit(this.blockSize);
        }
    }

    private void writeCompressed(ByteBuffer byteBuffer) throws IOException {
        byte[] array = byteBuffer.array();
        int remaining = byteBuffer.remaining();
        int maskedCrc32c = SnappyFramed.maskedCrc32c(this.crc32, array, 0, remaining);
        this.directInputBuffer.clear();
        this.directInputBuffer.put(byteBuffer);
        this.directInputBuffer.flip();
        this.outputBuffer.clear();
        Snappy.compress(this.directInputBuffer, this.outputBuffer);
        if (this.outputBuffer.remaining() / remaining <= this.minCompressionRatio) {
            writeBlock(this.out, this.outputBuffer, true, maskedCrc32c);
        } else {
            byteBuffer.flip();
            writeBlock(this.out, byteBuffer, false, maskedCrc32c);
        }
    }

    private void writeBlock(WritableByteChannel writableByteChannel, ByteBuffer byteBuffer, boolean z, int i) throws IOException {
        this.headerBuffer.clear();
        this.headerBuffer.put((byte) (!z ? 1 : 0));
        int remaining = byteBuffer.remaining() + 4;
        this.headerBuffer.put((byte) remaining);
        this.headerBuffer.put((byte) (remaining >>> 8));
        this.headerBuffer.put((byte) (remaining >>> 16));
        this.headerBuffer.putInt(i);
        this.headerBuffer.flip();
        writableByteChannel.write(this.headerBuffer);
        writableByteChannel.write(byteBuffer);
    }
}
