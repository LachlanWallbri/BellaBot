package com.google.api.gax.rpc;

import com.google.common.collect.Queues;
import java.util.concurrent.BlockingQueue;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
final class QueuingResponseObserver<V> extends StateCheckingResponseObserver<V> {
    static final Object EOF_MARKER = new Object();
    private final BlockingQueue<Object> buffer = Queues.newArrayBlockingQueue(2);
    private StreamController controller;
    private boolean isCancelled;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void request() {
        this.controller.request(1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object getNext() throws InterruptedException {
        if (this.isCancelled) {
            return EOF_MARKER;
        }
        return this.buffer.take();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isReady() {
        return this.isCancelled || !this.buffer.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void cancel() {
        this.isCancelled = true;
        this.controller.cancel();
    }

    @Override // com.google.api.gax.rpc.StateCheckingResponseObserver
    protected void onStartImpl(StreamController streamController) {
        this.controller = streamController;
        streamController.disableAutoInboundFlowControl();
        streamController.request(1);
    }

    @Override // com.google.api.gax.rpc.StateCheckingResponseObserver
    protected void onResponseImpl(V v) {
        this.buffer.add(v);
    }

    @Override // com.google.api.gax.rpc.StateCheckingResponseObserver
    protected void onErrorImpl(Throwable th) {
        this.buffer.add(th);
    }

    @Override // com.google.api.gax.rpc.StateCheckingResponseObserver
    protected void onCompleteImpl() {
        this.buffer.add(EOF_MARKER);
    }
}
