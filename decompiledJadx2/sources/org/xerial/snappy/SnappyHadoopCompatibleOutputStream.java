package org.xerial.snappy;

import java.io.OutputStream;
import org.xerial.snappy.buffer.CachedBufferAllocator;

/* loaded from: classes9.dex */
public class SnappyHadoopCompatibleOutputStream extends SnappyOutputStream {
    @Override // org.xerial.snappy.SnappyOutputStream
    protected int writeHeader() {
        return 0;
    }

    public SnappyHadoopCompatibleOutputStream(OutputStream outputStream) {
        this(outputStream, 32768);
    }

    public SnappyHadoopCompatibleOutputStream(OutputStream outputStream, int i) {
        super(outputStream, i, CachedBufferAllocator.getBufferAllocatorFactory());
    }

    @Override // org.xerial.snappy.SnappyOutputStream
    protected void writeBlockPreemble() {
        writeCurrentDataSize();
    }
}
