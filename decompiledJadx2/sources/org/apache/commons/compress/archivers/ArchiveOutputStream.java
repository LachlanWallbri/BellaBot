package org.apache.commons.compress.archivers;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.LinkOption;
import java.nio.file.Path;

/* loaded from: classes7.dex */
public abstract class ArchiveOutputStream extends OutputStream {
    static final int BYTE_MASK = 255;
    private long bytesWritten;
    private final byte[] oneByte = new byte[1];

    public boolean canWriteEntryData(ArchiveEntry archiveEntry) {
        return true;
    }

    public abstract void closeArchiveEntry() throws IOException;

    public abstract ArchiveEntry createArchiveEntry(File file, String str) throws IOException;

    public abstract void finish() throws IOException;

    public abstract void putArchiveEntry(ArchiveEntry archiveEntry) throws IOException;

    public ArchiveEntry createArchiveEntry(Path path, String str, LinkOption... linkOptionArr) throws IOException {
        return createArchiveEntry(path.toFile(), str);
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.oneByte;
        bArr[0] = (byte) (i & 255);
        write(bArr, 0, 1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void count(int i) {
        count(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void count(long j) {
        if (j != -1) {
            this.bytesWritten += j;
        }
    }

    @Deprecated
    public int getCount() {
        return (int) this.bytesWritten;
    }

    public long getBytesWritten() {
        return this.bytesWritten;
    }
}
