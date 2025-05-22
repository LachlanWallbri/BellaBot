package org.apache.commons.compress.archivers.tar;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes8.dex */
class TarArchiveSparseZeroInputStream extends InputStream {
    @Override // java.io.InputStream
    public int read() throws IOException {
        return 0;
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        return j;
    }
}
