package io.grpc.alts.internal;

import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import java.util.LinkedList;
import java.util.Queue;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class AsyncSemaphore {
    private int permits;
    private final Object lock = new Object();
    private final Queue<ChannelPromise> queue = new LinkedList();

    public AsyncSemaphore(int i) {
        this.permits = i;
    }

    public ChannelFuture acquire(ChannelHandlerContext channelHandlerContext) {
        synchronized (this.lock) {
            if (this.permits > 0) {
                this.permits--;
                return channelHandlerContext.newSucceededFuture();
            }
            ChannelPromise newPromise = channelHandlerContext.newPromise();
            this.queue.add(newPromise);
            return newPromise;
        }
    }

    public void release() {
        synchronized (this.lock) {
            ChannelPromise poll = this.queue.poll();
            if (poll == null) {
                this.permits++;
            } else {
                poll.setSuccess();
            }
        }
    }
}
