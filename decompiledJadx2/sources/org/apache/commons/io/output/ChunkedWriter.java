package org.apache.commons.io.output;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public class ChunkedWriter extends FilterWriter {
    private static final int DEFAULT_CHUNK_SIZE = 4096;
    private final int chunkSize;

    public ChunkedWriter(Writer writer, int i) {
        super(writer);
        if (i <= 0) {
            throw new IllegalArgumentException();
        }
        this.chunkSize = i;
    }

    public ChunkedWriter(Writer writer) {
        this(writer, 4096);
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(char[] cArr, int i, int i2) throws IOException {
        while (i2 > 0) {
            int min = Math.min(i2, this.chunkSize);
            this.out.write(cArr, i, min);
            i2 -= min;
            i += min;
        }
    }
}
