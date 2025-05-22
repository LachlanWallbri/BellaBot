package org.jboss.netty.channel.socket.nio;

import java.util.concurrent.Executor;

/* loaded from: classes7.dex */
public class NioDatagramWorkerPool extends AbstractNioWorkerPool<NioDatagramWorker> {
    public NioDatagramWorkerPool(Executor executor, int i, boolean z) {
        super(executor, i, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jboss.netty.channel.socket.nio.AbstractNioWorkerPool
    public NioDatagramWorker createWorker(Executor executor, boolean z) {
        return new NioDatagramWorker(executor, z);
    }
}
