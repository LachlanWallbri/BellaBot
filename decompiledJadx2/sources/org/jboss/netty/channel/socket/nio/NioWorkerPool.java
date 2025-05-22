package org.jboss.netty.channel.socket.nio;

import java.util.concurrent.Executor;

/* loaded from: classes7.dex */
public class NioWorkerPool extends AbstractNioWorkerPool<NioWorker> {
    public NioWorkerPool(Executor executor, int i, boolean z) {
        super(executor, i, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jboss.netty.channel.socket.nio.AbstractNioWorkerPool
    public NioWorker createWorker(Executor executor, boolean z) {
        return new NioWorker(executor, z);
    }
}
