package org.apache.commons.compress.archivers.sevenz;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

/* loaded from: classes8.dex */
class BoundedSeekableByteChannelInputStream extends InputStream {
    private static final int MAX_BUF_LEN = 8192;
    private final ByteBuffer buffer;
    private long bytesRemaining;
    private final SeekableByteChannel channel;

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public BoundedSeekableByteChannelInputStream(SeekableByteChannel seekableByteChannel, long j) {
        this.channel = seekableByteChannel;
        this.bytesRemaining = j;
        if (j < 8192 && j > 0) {
            this.buffer = ByteBuffer.allocate((int) j);
        } else {
            this.buffer = ByteBuffer.allocate(8192);
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        long j = this.bytesRemaining;
        if (j <= 0) {
            return -1;
        }
        this.bytesRemaining = j - 1;
        int read = read(1);
        return read < 0 ? read : this.buffer.get() & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        ByteBuffer allocate;
        int read;
        if (i2 == 0) {
            return 0;
        }
        long j = this.bytesRemaining;
        if (j <= 0) {
            return -1;
        }
        if (i2 > j) {
            i2 = (int) j;
        }
        if (i2 <= this.buffer.capacity()) {
            allocate = this.buffer;
            read = read(i2);
        } else {
            allocate = ByteBuffer.allocate(i2);
            read = this.channel.read(allocate);
            allocate.flip();
        }
        if (read >= 0) {
            allocate.get(bArr, i, read);
            this.bytesRemaining -= read;
        }
        return read;
    }

    private int read(int i) throws IOException {
        this.buffer.rewind().limit(i);
        int read = this.channel.read(this.buffer);
        this.buffer.flip();
        return read;
    }
}
