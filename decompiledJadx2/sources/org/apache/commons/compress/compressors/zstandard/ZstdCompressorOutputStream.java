package org.apache.commons.compress.compressors.zstandard;

import com.github.luben.zstd.ZstdOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;

/* loaded from: classes8.dex */
public class ZstdCompressorOutputStream extends CompressorOutputStream {
    private final ZstdOutputStream encOS;

    public ZstdCompressorOutputStream(OutputStream outputStream, int i, boolean z, boolean z2) throws IOException {
        this.encOS = new ZstdOutputStream(outputStream, i);
        this.encOS.setCloseFrameOnFlush(z);
        this.encOS.setChecksum(z2);
    }

    public ZstdCompressorOutputStream(OutputStream outputStream, int i, boolean z) throws IOException {
        this.encOS = new ZstdOutputStream(outputStream, i);
        this.encOS.setCloseFrameOnFlush(z);
    }

    public ZstdCompressorOutputStream(OutputStream outputStream, int i) throws IOException {
        this.encOS = new ZstdOutputStream(outputStream, i);
    }

    public ZstdCompressorOutputStream(OutputStream outputStream) throws IOException {
        this.encOS = new ZstdOutputStream(outputStream);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.encOS.close();
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.encOS.write(i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.encOS.write(bArr, i, i2);
    }

    public String toString() {
        return this.encOS.toString();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.encOS.flush();
    }
}
