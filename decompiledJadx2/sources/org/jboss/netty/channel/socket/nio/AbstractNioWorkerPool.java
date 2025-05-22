package org.jboss.netty.channel.socket.nio;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import org.jboss.netty.channel.socket.nio.AbstractNioWorker;
import org.jboss.netty.util.ExternalResourceReleasable;
import org.jboss.netty.util.internal.ExecutorUtil;

/* loaded from: classes7.dex */
public abstract class AbstractNioWorkerPool<E extends AbstractNioWorker> implements WorkerPool<E>, ExternalResourceReleasable {
    private final Executor workerExecutor;
    private final AtomicInteger workerIndex = new AtomicInteger();
    private final AbstractNioWorker[] workers;

    protected abstract E createWorker(Executor executor, boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractNioWorkerPool(Executor executor, int i, boolean z) {
        if (executor == null) {
            throw new NullPointerException("workerExecutor");
        }
        if (i <= 0) {
            throw new IllegalArgumentException("workerCount (" + i + ") must be a positive integer.");
        }
        this.workers = new AbstractNioWorker[i];
        int i2 = 0;
        while (true) {
            AbstractNioWorker[] abstractNioWorkerArr = this.workers;
            if (i2 < abstractNioWorkerArr.length) {
                abstractNioWorkerArr[i2] = createWorker(executor, z);
                i2++;
            } else {
                this.workerExecutor = executor;
                return;
            }
        }
    }

    @Override // org.jboss.netty.channel.socket.nio.WorkerPool
    public E nextWorker() {
        return (E) this.workers[Math.abs(this.workerIndex.getAndIncrement() % this.workers.length)];
    }

    @Override // org.jboss.netty.util.ExternalResourceReleasable
    public void releaseExternalResources() {
        ExecutorUtil.terminate(this.workerExecutor);
    }
}
