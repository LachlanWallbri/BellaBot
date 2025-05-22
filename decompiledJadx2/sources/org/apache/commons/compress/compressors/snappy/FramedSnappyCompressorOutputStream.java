package org.apache.commons.compress.compressors.snappy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.lz77support.Parameters;
import org.apache.commons.compress.utils.ByteUtils;

/* loaded from: classes8.dex */
public class FramedSnappyCompressorOutputStream extends CompressorOutputStream {
    private static final int MAX_COMPRESSED_BUFFER_SIZE = 65536;
    private final byte[] buffer;
    private final PureJavaCrc32C checksum;
    private final ByteUtils.ByteConsumer consumer;
    private int currentIndex;
    private final byte[] oneByte;
    private final OutputStream out;
    private final Parameters params;

    static long mask(long j) {
        return (((j << 17) | (j >> 15)) + 2726488792L) & 4294967295L;
    }

    public FramedSnappyCompressorOutputStream(OutputStream outputStream) throws IOException {
        this(outputStream, SnappyCompressorOutputStream.createParameterBuilder(32768).build());
    }

    public FramedSnappyCompressorOutputStream(OutputStream outputStream, Parameters parameters) throws IOException {
        this.checksum = new PureJavaCrc32C();
        this.oneByte = new byte[1];
        this.buffer = new byte[65536];
        this.out = outputStream;
        this.params = parameters;
        this.consumer = new ByteUtils.OutputStreamByteConsumer(outputStream);
        outputStream.write(FramedSnappyCompressorInputStream.SZ_SIGNATURE);
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.oneByte;
        bArr[0] = (byte) (i & 255);
        write(bArr);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (this.currentIndex + i2 > 65536) {
            flushBuffer();
            while (i2 > 65536) {
                System.arraycopy(bArr, i, this.buffer, 0, 65536);
                i += 65536;
                i2 -= 65536;
                this.currentIndex = 65536;
                flushBuffer();
            }
        }
        System.arraycopy(bArr, i, this.buffer, this.currentIndex, i2);
        this.currentIndex += i2;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            finish();
        } finally {
            this.out.close();
        }
    }

    public void finish() throws IOException {
        if (this.currentIndex > 0) {
            flushBuffer();
        }
    }

    private void flushBuffer() throws IOException {
        this.out.write(0);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        SnappyCompressorOutputStream snappyCompressorOutputStream = new SnappyCompressorOutputStream(byteArrayOutputStream, this.currentIndex, this.params);
        try {
            snappyCompressorOutputStream.write(this.buffer, 0, this.currentIndex);
            snappyCompressorOutputStream.close();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            writeLittleEndian(3, byteArray.length + 4);
            writeCrc();
            this.out.write(byteArray);
            this.currentIndex = 0;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    snappyCompressorOutputStream.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    private void writeLittleEndian(int i, long j) throws IOException {
        ByteUtils.toLittleEndian(this.consumer, j, i);
    }

    private void writeCrc() throws IOException {
        this.checksum.update(this.buffer, 0, this.currentIndex);
        writeLittleEndian(4, mask(this.checksum.getValue()));
        this.checksum.reset();
    }
}
