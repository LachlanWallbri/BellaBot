package org.apache.commons.compress.archivers.examples;

import java.io.Closeable;
import java.io.IOException;

/* loaded from: classes8.dex */
public interface CloseableConsumer {
    public static final CloseableConsumer CLOSING_CONSUMER = new CloseableConsumer() { // from class: org.apache.commons.compress.archivers.examples.-$$Lambda$doM-Svgh8XqUW-yNW_byo3IV_oA
        @Override // org.apache.commons.compress.archivers.examples.CloseableConsumer
        public final void accept(Closeable closeable) {
            closeable.close();
        }
    };
    public static final CloseableConsumer NULL_CONSUMER = new CloseableConsumer() { // from class: org.apache.commons.compress.archivers.examples.-$$Lambda$CloseableConsumer$NpHdhhs5szZvFxfYDbI8fhj7gBo
        @Override // org.apache.commons.compress.archivers.examples.CloseableConsumer
        public final void accept(Closeable closeable) {
            CloseableConsumer.lambda$static$0(closeable);
        }
    };

    static /* synthetic */ void lambda$static$0(Closeable closeable) throws IOException {
    }

    void accept(Closeable closeable) throws IOException;
}
