package org.apache.commons.compress.compressors.lzma;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.tukaani.xz.LZMA2Options;
import org.tukaani.xz.LZMAOutputStream;

/* loaded from: classes8.dex */
public class LZMACompressorOutputStream extends CompressorOutputStream {
    private final LZMAOutputStream out;

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
    }

    public LZMACompressorOutputStream(OutputStream outputStream) throws IOException {
        this.out = new LZMAOutputStream(outputStream, new LZMA2Options(), -1L);
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.out.write(i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
    }

    public void finish() throws IOException {
        this.out.finish();
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.out.close();
    }
}
