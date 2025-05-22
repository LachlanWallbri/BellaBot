package org.apache.commons.compress.archivers.examples;

import java.io.Closeable;
import java.io.IOException;
import java.util.Objects;

/* loaded from: classes8.dex */
final class CloseableConsumerAdapter implements Closeable {
    private Closeable closeable;
    private final CloseableConsumer consumer;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CloseableConsumerAdapter(CloseableConsumer closeableConsumer) {
        this.consumer = (CloseableConsumer) Objects.requireNonNull(closeableConsumer, "consumer");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <C extends Closeable> C track(C c) {
        this.closeable = c;
        return c;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Closeable closeable = this.closeable;
        if (closeable != null) {
            this.consumer.accept(closeable);
        }
    }
}
