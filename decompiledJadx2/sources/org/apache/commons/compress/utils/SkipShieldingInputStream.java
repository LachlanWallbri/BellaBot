package org.apache.commons.compress.utils;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes9.dex */
public class SkipShieldingInputStream extends FilterInputStream {
    private static final byte[] SKIP_BUFFER = new byte[8192];
    private static final int SKIP_BUFFER_SIZE = 8192;

    public SkipShieldingInputStream(InputStream inputStream) {
        super(inputStream);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        if (j < 0) {
            return 0L;
        }
        return read(SKIP_BUFFER, 0, (int) Math.min(j, 8192L));
    }
}
