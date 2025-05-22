package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.core.InternalApi;
import java.util.Iterator;
import javax.annotation.Nonnull;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for streaming is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public class ServerStream<V> implements Iterable<V> {
    private boolean consumed;
    private final QueuingResponseObserver<V> observer = new QueuingResponseObserver<>();
    private final ServerStreamIterator<V> iterator = new ServerStreamIterator<>(this.observer);

    /* JADX INFO: Access modifiers changed from: package-private */
    @InternalApi("For use by ServerStreamingCallable only.")
    public ServerStream() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @InternalApi("For use by ServerStreamingCallable only.")
    public ResponseObserver<V> observer() {
        return this.observer;
    }

    @Override // java.lang.Iterable
    @Nonnull
    public Iterator<V> iterator() {
        if (this.consumed) {
            throw new IllegalStateException("Iterator already consumed");
        }
        this.consumed = true;
        return this.iterator;
    }

    public boolean isReceiveReady() {
        return this.iterator.isReady();
    }

    public void cancel() {
        this.observer.cancel();
    }
}
