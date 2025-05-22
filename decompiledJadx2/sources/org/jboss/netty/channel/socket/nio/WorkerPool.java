package org.jboss.netty.channel.socket.nio;

import org.jboss.netty.channel.socket.Worker;

/* loaded from: classes7.dex */
public interface WorkerPool<E extends Worker> {
    E nextWorker();
}
