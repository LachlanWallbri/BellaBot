package org.jboss.netty.channel.socket;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.DefaultChannelFuture;

/* loaded from: classes7.dex */
public class ChannelRunnableWrapper extends DefaultChannelFuture implements Runnable {
    private boolean started;
    private final Runnable task;

    public ChannelRunnableWrapper(Channel channel, Runnable runnable) {
        super(channel, true);
        this.task = runnable;
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this) {
            if (isCancelled()) {
                return;
            }
            this.started = true;
            try {
                this.task.run();
                setSuccess();
            } catch (Throwable th) {
                setFailure(th);
            }
        }
    }

    @Override // org.jboss.netty.channel.DefaultChannelFuture, org.jboss.netty.channel.ChannelFuture
    public synchronized boolean cancel() {
        if (this.started) {
            return false;
        }
        return super.cancel();
    }
}
