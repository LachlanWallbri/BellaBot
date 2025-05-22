package org.jboss.netty.handler.execution;

import java.util.concurrent.Executor;
import org.jboss.netty.util.ExternalResourceReleasable;
import org.jboss.netty.util.internal.ExecutorUtil;
import org.mozilla.javascript.ES6Iterator;

/* loaded from: classes7.dex */
public class ChainedExecutor implements Executor, ExternalResourceReleasable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Executor cur;
    private final ChannelEventRunnableFilter filter;
    private final Executor next;

    public ChainedExecutor(ChannelEventRunnableFilter channelEventRunnableFilter, Executor executor, Executor executor2) {
        if (channelEventRunnableFilter == null) {
            throw new NullPointerException("filter");
        }
        if (executor == null) {
            throw new NullPointerException("cur");
        }
        if (executor2 == null) {
            throw new NullPointerException(ES6Iterator.NEXT_METHOD);
        }
        this.filter = channelEventRunnableFilter;
        this.cur = executor;
        this.next = executor2;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        if (this.filter.filter((ChannelEventRunnable) runnable)) {
            this.cur.execute(runnable);
        } else {
            this.next.execute(runnable);
        }
    }

    @Override // org.jboss.netty.util.ExternalResourceReleasable
    public void releaseExternalResources() {
        ExecutorUtil.terminate(this.cur, this.next);
        releaseExternal(this.cur);
        releaseExternal(this.next);
    }

    private static void releaseExternal(Executor executor) {
        if (executor instanceof ExternalResourceReleasable) {
            ((ExternalResourceReleasable) executor).releaseExternalResources();
        }
    }
}
