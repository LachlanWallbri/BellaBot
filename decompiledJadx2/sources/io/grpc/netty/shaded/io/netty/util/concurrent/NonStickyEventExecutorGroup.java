package io.grpc.netty.shaded.io.netty.util.concurrent;

import com.pudutech.mirsdk.compat.topo.MapElement;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class NonStickyEventExecutorGroup implements EventExecutorGroup {
    private final EventExecutorGroup group;
    private final int maxTaskExecutePerRun;

    @Override // java.util.concurrent.ExecutorService
    public /* bridge */ /* synthetic */ java.util.concurrent.Future submit(Runnable runnable, Object obj) {
        return submit(runnable, (Runnable) obj);
    }

    public NonStickyEventExecutorGroup(EventExecutorGroup eventExecutorGroup) {
        this(eventExecutorGroup, 1024);
    }

    public NonStickyEventExecutorGroup(EventExecutorGroup eventExecutorGroup, int i) {
        this.group = verify(eventExecutorGroup);
        this.maxTaskExecutePerRun = ObjectUtil.checkPositive(i, "maxTaskExecutePerRun");
    }

    private static EventExecutorGroup verify(EventExecutorGroup eventExecutorGroup) {
        for (EventExecutor eventExecutor : (EventExecutorGroup) ObjectUtil.checkNotNull(eventExecutorGroup, MapElement.Key.GROUP)) {
            if (eventExecutor instanceof OrderedEventExecutor) {
                throw new IllegalArgumentException("EventExecutorGroup " + eventExecutorGroup + " contains OrderedEventExecutors: " + eventExecutor);
            }
        }
        return eventExecutorGroup;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public NonStickyOrderedEventExecutor newExecutor(EventExecutor eventExecutor) {
        return new NonStickyOrderedEventExecutor(eventExecutor, this.maxTaskExecutePerRun);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup
    public boolean isShuttingDown() {
        return this.group.isShuttingDown();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup
    public Future<?> shutdownGracefully() {
        return this.group.shutdownGracefully();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup
    public Future<?> shutdownGracefully(long j, long j2, TimeUnit timeUnit) {
        return this.group.shutdownGracefully(j, j2, timeUnit);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup
    public Future<?> terminationFuture() {
        return this.group.terminationFuture();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup, java.util.concurrent.ExecutorService
    public void shutdown() {
        this.group.shutdown();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup
    public List<Runnable> shutdownNow() {
        return this.group.shutdownNow();
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup, io.grpc.netty.shaded.io.netty.channel.EventLoopGroup
    public EventExecutor next() {
        return newExecutor(this.group.next());
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup, java.lang.Iterable
    public Iterator<EventExecutor> iterator() {
        final Iterator<EventExecutor> it = this.group.iterator();
        return new Iterator<EventExecutor>() { // from class: io.grpc.netty.shaded.io.netty.util.concurrent.NonStickyEventExecutorGroup.1
            @Override // java.util.Iterator
            public boolean hasNext() {
                return it.hasNext();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public EventExecutor next() {
                return NonStickyEventExecutorGroup.this.newExecutor((EventExecutor) it.next());
            }

            @Override // java.util.Iterator
            public void remove() {
                it.remove();
            }
        };
    }

    @Override // java.util.concurrent.ExecutorService
    public Future<?> submit(Runnable runnable) {
        return this.group.submit(runnable);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup, java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Runnable runnable, T t) {
        return this.group.submit(runnable, (Runnable) t);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Callable<T> callable) {
        return this.group.submit((Callable) callable);
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        return this.group.schedule(runnable, j, timeUnit);
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long j, TimeUnit timeUnit) {
        return this.group.schedule((Callable) callable, j, timeUnit);
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        return this.group.scheduleAtFixedRate(runnable, j, j2, timeUnit);
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        return this.group.scheduleWithFixedDelay(runnable, j, j2, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return this.group.isShutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return this.group.isTerminated();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.group.awaitTermination(j, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<java.util.concurrent.Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
        return this.group.invokeAll(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<java.util.concurrent.Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException {
        return this.group.invokeAll(collection, j, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws InterruptedException, ExecutionException {
        return (T) this.group.invokeAny(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return (T) this.group.invokeAny(collection, j, timeUnit);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.group.execute(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class NonStickyOrderedEventExecutor extends AbstractEventExecutor implements Runnable, OrderedEventExecutor {
        private static final int NONE = 0;
        private static final int RUNNING = 2;
        private static final int SUBMITTED = 1;
        private final EventExecutor executor;
        private final int maxTaskExecutePerRun;
        private final AtomicInteger state;
        private final Queue<Runnable> tasks;

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.AbstractEventExecutor, io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor
        public boolean inEventLoop() {
            return false;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor
        public boolean inEventLoop(Thread thread) {
            return false;
        }

        NonStickyOrderedEventExecutor(EventExecutor eventExecutor, int i) {
            super(eventExecutor);
            this.tasks = PlatformDependent.newMpscQueue();
            this.state = new AtomicInteger();
            this.executor = eventExecutor;
            this.maxTaskExecutePerRun = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            Runnable poll;
            if (!this.state.compareAndSet(1, 2)) {
                return;
            }
            while (true) {
                int i = 0;
                while (i < this.maxTaskExecutePerRun && (poll = this.tasks.poll()) != null) {
                    try {
                        safeExecute(poll);
                        i++;
                    } catch (Throwable th) {
                        if (i == this.maxTaskExecutePerRun) {
                            try {
                                this.state.set(1);
                                this.executor.execute(this);
                                return;
                            } catch (Throwable unused) {
                                this.state.set(2);
                                throw th;
                            }
                        }
                        this.state.set(0);
                        if (this.tasks.isEmpty() || !this.state.compareAndSet(0, 2)) {
                            return;
                        }
                        throw th;
                    }
                }
                if (i == this.maxTaskExecutePerRun) {
                    try {
                        this.state.set(1);
                        this.executor.execute(this);
                        return;
                    } catch (Throwable unused2) {
                        this.state.set(2);
                    }
                } else {
                    this.state.set(0);
                    if (this.tasks.isEmpty() || !this.state.compareAndSet(0, 2)) {
                        return;
                    }
                }
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup
        public boolean isShuttingDown() {
            return this.executor.isShutdown();
        }

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup
        public Future<?> shutdownGracefully(long j, long j2, TimeUnit timeUnit) {
            return this.executor.shutdownGracefully(j, j2, timeUnit);
        }

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup
        public Future<?> terminationFuture() {
            return this.executor.terminationFuture();
        }

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.AbstractEventExecutor, java.util.concurrent.ExecutorService, io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutorGroup
        public void shutdown() {
            this.executor.shutdown();
        }

        @Override // java.util.concurrent.ExecutorService
        public boolean isShutdown() {
            return this.executor.isShutdown();
        }

        @Override // java.util.concurrent.ExecutorService
        public boolean isTerminated() {
            return this.executor.isTerminated();
        }

        @Override // java.util.concurrent.ExecutorService
        public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
            return this.executor.awaitTermination(j, timeUnit);
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            if (!this.tasks.offer(runnable)) {
                throw new RejectedExecutionException();
            }
            if (this.state.compareAndSet(0, 1)) {
                this.executor.execute(this);
            }
        }
    }
}
