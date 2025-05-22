package org.apache.commons.compress.compressors.lzma;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.CountingInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.tukaani.xz.LZMAInputStream;
import org.tukaani.xz.MemoryLimitException;

/* loaded from: classes8.dex */
public class LZMACompressorInputStream extends CompressorInputStream implements InputStreamStatistics {
    private final CountingInputStream countingStream;

    /* renamed from: in */
    private final InputStream f8941in;

    public LZMACompressorInputStream(InputStream inputStream) throws IOException {
        CountingInputStream countingInputStream = new CountingInputStream(inputStream);
        this.countingStream = countingInputStream;
        this.f8941in = new LZMAInputStream(countingInputStream, -1);
    }

    public LZMACompressorInputStream(InputStream inputStream, int i) throws IOException {
        try {
            CountingInputStream countingInputStream = new CountingInputStream(inputStream);
            this.countingStream = countingInputStream;
            this.f8941in = new LZMAInputStream(countingInputStream, i);
        } catch (MemoryLimitException e) {
            throw new org.apache.commons.compress.MemoryLimitException(e.getMemoryNeeded(), e.getMemoryLimit(), e);
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int read = this.f8941in.read();
        count(read == -1 ? 0 : 1);
        return read;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.f8941in.read(bArr, i, i2);
        count(read);
        return read;
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        return IOUtils.skip(this.f8941in, j);
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.f8941in.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f8941in.close();
    }

    @Override // org.apache.commons.compress.utils.InputStreamStatistics
    public long getCompressedCount() {
        return this.countingStream.getBytesRead();
    }

    public static boolean matches(byte[] bArr, int i) {
        return bArr != null && i >= 3 && bArr[0] == 93 && bArr[1] == 0 && bArr[2] == 0;
    }
}
