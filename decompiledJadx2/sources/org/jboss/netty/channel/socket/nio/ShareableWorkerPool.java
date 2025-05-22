package org.jboss.netty.channel.socket.nio;

import org.jboss.netty.channel.socket.Worker;
import org.jboss.netty.util.ExternalResourceReleasable;

/* loaded from: classes7.dex */
public final class ShareableWorkerPool<E extends Worker> implements WorkerPool<E> {
    private final WorkerPool<E> wrapped;

    public ShareableWorkerPool(WorkerPool<E> workerPool) {
        this.wrapped = workerPool;
    }

    @Override // org.jboss.netty.channel.socket.nio.WorkerPool
    public E nextWorker() {
        return this.wrapped.nextWorker();
    }

    public void destroy() {
        WorkerPool<E> workerPool = this.wrapped;
        if (workerPool instanceof ExternalResourceReleasable) {
            ((ExternalResourceReleasable) workerPool).releaseExternalResources();
        }
    }
}
