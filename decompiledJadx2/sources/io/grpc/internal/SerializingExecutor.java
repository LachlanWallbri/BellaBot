package io.grpc.internal;

import com.google.common.base.Preconditions;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class SerializingExecutor implements Executor, Runnable {
    private static final int RUNNING = -1;
    private static final int STOPPED = 0;
    private final Executor executor;
    private final Queue<Runnable> runQueue = new ConcurrentLinkedQueue();
    private volatile int runState = 0;
    private static final Logger log = Logger.getLogger(SerializingExecutor.class.getName());
    private static final AtomicHelper atomicHelper = getAtomicHelper();

    private static AtomicHelper getAtomicHelper() {
        try {
            return new FieldUpdaterAtomicHelper(AtomicIntegerFieldUpdater.newUpdater(SerializingExecutor.class, "runState"));
        } catch (Throwable th) {
            log.log(Level.SEVERE, "FieldUpdaterAtomicHelper failed", th);
            return new SynchronizedAtomicHelper();
        }
    }

    public SerializingExecutor(Executor executor) {
        Preconditions.checkNotNull(executor, "'executor' must not be null.");
        this.executor = executor;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.runQueue.add(Preconditions.checkNotNull(runnable, "'r' must not be null."));
        schedule(runnable);
    }

    private void schedule(@Nullable Runnable runnable) {
        if (atomicHelper.runStateCompareAndSet(this, 0, -1)) {
            try {
                this.executor.execute(this);
            } catch (Throwable th) {
                if (runnable != null) {
                    this.runQueue.remove(runnable);
                }
                atomicHelper.runStateSet(this, 0);
                throw th;
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        while (true) {
            try {
                Runnable poll = this.runQueue.poll();
                if (poll == null) {
                    break;
                }
                try {
                    poll.run();
                } catch (RuntimeException e) {
                    log.log(Level.SEVERE, "Exception while executing runnable " + poll, (Throwable) e);
                }
            } catch (Throwable th) {
                atomicHelper.runStateSet(this, 0);
                throw th;
            }
        }
        atomicHelper.runStateSet(this, 0);
        if (this.runQueue.isEmpty()) {
            return;
        }
        schedule(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static abstract class AtomicHelper {
        public abstract boolean runStateCompareAndSet(SerializingExecutor serializingExecutor, int i, int i2);

        public abstract void runStateSet(SerializingExecutor serializingExecutor, int i);

        private AtomicHelper() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class FieldUpdaterAtomicHelper extends AtomicHelper {
        private final AtomicIntegerFieldUpdater<SerializingExecutor> runStateUpdater;

        private FieldUpdaterAtomicHelper(AtomicIntegerFieldUpdater<SerializingExecutor> atomicIntegerFieldUpdater) {
            super();
            this.runStateUpdater = atomicIntegerFieldUpdater;
        }

        @Override // io.grpc.internal.SerializingExecutor.AtomicHelper
        public boolean runStateCompareAndSet(SerializingExecutor serializingExecutor, int i, int i2) {
            return this.runStateUpdater.compareAndSet(serializingExecutor, i, i2);
        }

        @Override // io.grpc.internal.SerializingExecutor.AtomicHelper
        public void runStateSet(SerializingExecutor serializingExecutor, int i) {
            this.runStateUpdater.set(serializingExecutor, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class SynchronizedAtomicHelper extends AtomicHelper {
        private SynchronizedAtomicHelper() {
            super();
        }

        @Override // io.grpc.internal.SerializingExecutor.AtomicHelper
        public boolean runStateCompareAndSet(SerializingExecutor serializingExecutor, int i, int i2) {
            synchronized (serializingExecutor) {
                if (serializingExecutor.runState != i) {
                    return false;
                }
                serializingExecutor.runState = i2;
                return true;
            }
        }

        @Override // io.grpc.internal.SerializingExecutor.AtomicHelper
        public void runStateSet(SerializingExecutor serializingExecutor, int i) {
            synchronized (serializingExecutor) {
                serializingExecutor.runState = i;
            }
        }
    }
}
