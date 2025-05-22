package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import org.apache.commons.compress.utils.InputStreamStatistics;

/* loaded from: classes8.dex */
class InflaterInputStreamWithStatistics extends InflaterInputStream implements InputStreamStatistics {
    private long compressedCount;
    private long uncompressedCount;

    public InflaterInputStreamWithStatistics(InputStream inputStream) {
        super(inputStream);
    }

    public InflaterInputStreamWithStatistics(InputStream inputStream, Inflater inflater) {
        super(inputStream, inflater);
    }

    public InflaterInputStreamWithStatistics(InputStream inputStream, Inflater inflater, int i) {
        super(inputStream, inflater, i);
    }

    @Override // java.util.zip.InflaterInputStream
    protected void fill() throws IOException {
        super.fill();
        this.compressedCount += this.inf.getRemaining();
    }

    @Override // java.util.zip.InflaterInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int read = super.read();
        if (read > -1) {
            this.uncompressedCount++;
        }
        return read;
    }

    @Override // java.util.zip.InflaterInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = super.read(bArr, i, i2);
        if (read > -1) {
            this.uncompressedCount += read;
        }
        return read;
    }

    @Override // org.apache.commons.compress.utils.InputStreamStatistics
    public long getCompressedCount() {
        return this.compressedCount;
    }

    @Override // org.apache.commons.compress.utils.InputStreamStatistics
    public long getUncompressedCount() {
        return this.uncompressedCount;
    }
}
