package org.apache.commons.compress.compressors.zstandard;

import com.github.luben.zstd.BufferPool;
import com.github.luben.zstd.ZstdInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.CountingInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;

/* loaded from: classes8.dex */
public class ZstdCompressorInputStream extends CompressorInputStream implements InputStreamStatistics {
    private final CountingInputStream countingStream;
    private final ZstdInputStream decIS;

    public ZstdCompressorInputStream(InputStream inputStream) throws IOException {
        CountingInputStream countingInputStream = new CountingInputStream(inputStream);
        this.countingStream = countingInputStream;
        this.decIS = new ZstdInputStream(countingInputStream);
    }

    public ZstdCompressorInputStream(InputStream inputStream, BufferPool bufferPool) throws IOException {
        CountingInputStream countingInputStream = new CountingInputStream(inputStream);
        this.countingStream = countingInputStream;
        this.decIS = new ZstdInputStream(countingInputStream, bufferPool);
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.decIS.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.decIS.close();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        return IOUtils.skip(this.decIS, j);
    }

    @Override // java.io.InputStream
    public synchronized void mark(int i) {
        this.decIS.mark(i);
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.decIS.markSupported();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int read = this.decIS.read();
        count(read == -1 ? 0 : 1);
        return read;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        int read = this.decIS.read(bArr, i, i2);
        count(read);
        return read;
    }

    public String toString() {
        return this.decIS.toString();
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        this.decIS.reset();
    }

    @Override // org.apache.commons.compress.utils.InputStreamStatistics
    public long getCompressedCount() {
        return this.countingStream.getBytesRead();
    }
}
