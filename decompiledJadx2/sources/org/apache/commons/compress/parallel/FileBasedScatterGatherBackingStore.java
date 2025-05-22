package org.apache.commons.compress.parallel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;

/* loaded from: classes9.dex */
public class FileBasedScatterGatherBackingStore implements ScatterGatherBackingStore {
    private boolean closed;

    /* renamed from: os */
    private final OutputStream f8970os;
    private final File target;

    public FileBasedScatterGatherBackingStore(File file) throws FileNotFoundException {
        this.target = file;
        try {
            this.f8970os = Files.newOutputStream(file.toPath(), new OpenOption[0]);
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // org.apache.commons.compress.parallel.ScatterGatherBackingStore
    public InputStream getInputStream() throws IOException {
        return Files.newInputStream(this.target.toPath(), new OpenOption[0]);
    }

    @Override // org.apache.commons.compress.parallel.ScatterGatherBackingStore
    public void closeForWriting() throws IOException {
        if (this.closed) {
            return;
        }
        this.f8970os.close();
        this.closed = true;
    }

    @Override // org.apache.commons.compress.parallel.ScatterGatherBackingStore
    public void writeOut(byte[] bArr, int i, int i2) throws IOException {
        this.f8970os.write(bArr, i, i2);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            closeForWriting();
        } finally {
            if (this.target.exists() && !this.target.delete()) {
                this.target.deleteOnExit();
            }
        }
    }
}
