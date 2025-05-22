package com.google.api.client.http;

import com.google.common.io.ByteStreams;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
final class ConsumingInputStream extends FilterInputStream {
    private boolean closed;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConsumingInputStream(InputStream inputStream) {
        super(inputStream);
        this.closed = false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed || this.in == null) {
            return;
        }
        try {
            ByteStreams.exhaust(this);
            ((FilterInputStream) this).in.close();
        } finally {
            this.closed = true;
        }
    }
}
