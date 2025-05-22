package io.grpc.netty.shaded.io.netty.util.concurrent;

import com.iflytek.cloud.SpeechUtility;
import io.grpc.netty.shaded.io.netty.util.internal.InternalThreadLocalMap;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import io.grpc.netty.shaded.io.netty.util.internal.SystemPropertyUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ThrowableUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DefaultPromise<V> extends AbstractFuture<V> implements Promise<V> {
    private final EventExecutor executor;
    private Object listeners;
    private boolean notifyingListeners;
    private volatile Object result;
    private short waiters;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) DefaultPromise.class);
    private static final InternalLogger rejectedExecutionLogger = InternalLoggerFactory.getInstance(DefaultPromise.class.getName() + ".rejectedExecution");
    private static final int MAX_LISTENER_STACK_DEPTH = Math.min(8, SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.defaultPromise.maxListenerStackDepth", 8));
    private static final AtomicReferenceFieldUpdater<DefaultPromise, Object> RESULT_UPDATER = AtomicReferenceFieldUpdater.newUpdater(DefaultPromise.class, Object.class, SpeechUtility.TAG_RESOURCE_RESULT);
    private static final Object SUCCESS = new Object();
    private static final Object UNCANCELLABLE = new Object();
    private static final CauseHolder CANCELLATION_CAUSE_HOLDER = new CauseHolder(ThrowableUtil.unknownStackTrace(new CancellationException(), DefaultPromise.class, "cancel(...)"));
    private static final StackTraceElement[] CANCELLATION_STACK = CANCELLATION_CAUSE_HOLDER.cause.getStackTrace();

    public DefaultPromise(EventExecutor eventExecutor) {
        this.executor = (EventExecutor) ObjectUtil.checkNotNull(eventExecutor, "executor");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DefaultPromise() {
        this.executor = null;
    }

    public Promise<V> setSuccess(V v) {
        if (setSuccess0(v)) {
            return this;
        }
        throw new IllegalStateException("complete already: " + this);
    }

    public boolean trySuccess(V v) {
        return setSuccess0(v);
    }

    public Promise<V> setFailure(Throwable th) {
        if (setFailure0(th)) {
            return this;
        }
        throw new IllegalStateException("complete already: " + this, th);
    }

    public boolean tryFailure(Throwable th) {
        return setFailure0(th);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Promise
    public boolean setUncancellable() {
        if (RESULT_UPDATER.compareAndSet(this, null, UNCANCELLABLE)) {
            return true;
        }
        Object obj = this.result;
        return (isDone0(obj) && isCancelled0(obj)) ? false : true;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public boolean isSuccess() {
        Object obj = this.result;
        return (obj == null || obj == UNCANCELLABLE || (obj instanceof CauseHolder)) ? false : true;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public boolean isCancellable() {
        return this.result == null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class LeanCancellationException extends CancellationException {
        private static final long serialVersionUID = 2794674970981187807L;

        private LeanCancellationException() {
        }

        @Override // java.lang.Throwable
        public Throwable fillInStackTrace() {
            setStackTrace(DefaultPromise.CANCELLATION_STACK);
            return this;
        }

        @Override // java.lang.Throwable
        public String toString() {
            return CancellationException.class.getName();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Throwable cause() {
        return cause0(this.result);
    }

    private Throwable cause0(Object obj) {
        if (!(obj instanceof CauseHolder)) {
            return null;
        }
        if (obj == CANCELLATION_CAUSE_HOLDER) {
            LeanCancellationException leanCancellationException = new LeanCancellationException();
            if (RESULT_UPDATER.compareAndSet(this, CANCELLATION_CAUSE_HOLDER, new CauseHolder(leanCancellationException))) {
                return leanCancellationException;
            }
            obj = this.result;
        }
        return ((CauseHolder) obj).cause;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Promise<V> addListener(GenericFutureListener<? extends Future<? super V>> genericFutureListener) {
        ObjectUtil.checkNotNull(genericFutureListener, "listener");
        synchronized (this) {
            addListener0(genericFutureListener);
        }
        if (isDone()) {
            notifyListeners();
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Promise<V> addListeners(GenericFutureListener<? extends Future<? super V>>... genericFutureListenerArr) {
        ObjectUtil.checkNotNull(genericFutureListenerArr, "listeners");
        synchronized (this) {
            for (GenericFutureListener<? extends Future<? super V>> genericFutureListener : genericFutureListenerArr) {
                if (genericFutureListener == null) {
                    break;
                }
                addListener0(genericFutureListener);
            }
        }
        if (isDone()) {
            notifyListeners();
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Promise<V> removeListener(GenericFutureListener<? extends Future<? super V>> genericFutureListener) {
        ObjectUtil.checkNotNull(genericFutureListener, "listener");
        synchronized (this) {
            removeListener0(genericFutureListener);
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Promise<V> removeListeners(GenericFutureListener<? extends Future<? super V>>... genericFutureListenerArr) {
        ObjectUtil.checkNotNull(genericFutureListenerArr, "listeners");
        synchronized (this) {
            for (GenericFutureListener<? extends Future<? super V>> genericFutureListener : genericFutureListenerArr) {
                if (genericFutureListener == null) {
                    break;
                }
                removeListener0(genericFutureListener);
            }
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Promise<V> await() throws InterruptedException {
        if (isDone()) {
            return this;
        }
        if (Thread.interrupted()) {
            throw new InterruptedException(toString());
        }
        checkDeadLock();
        synchronized (this) {
            while (!isDone()) {
                incWaiters();
                try {
                    wait();
                    decWaiters();
                } catch (Throwable th) {
                    decWaiters();
                    throw th;
                }
            }
        }
        return this;
    }

    /* JADX WARN: Finally extract failed */
    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Promise<V> awaitUninterruptibly() {
        if (isDone()) {
            return this;
        }
        checkDeadLock();
        boolean z = false;
        synchronized (this) {
            while (!isDone()) {
                incWaiters();
                try {
                    try {
                        wait();
                        decWaiters();
                    } catch (Throwable th) {
                        decWaiters();
                        throw th;
                    }
                } catch (InterruptedException unused) {
                    z = true;
                    decWaiters();
                }
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public boolean await(long j, TimeUnit timeUnit) throws InterruptedException {
        return await0(timeUnit.toNanos(j), true);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public boolean await(long j) throws InterruptedException {
        return await0(TimeUnit.MILLISECONDS.toNanos(j), true);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public boolean awaitUninterruptibly(long j, TimeUnit timeUnit) {
        try {
            return await0(timeUnit.toNanos(j), false);
        } catch (InterruptedException unused) {
            throw new InternalError();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public boolean awaitUninterruptibly(long j) {
        try {
            return await0(TimeUnit.MILLISECONDS.toNanos(j), false);
        } catch (InterruptedException unused) {
            throw new InternalError();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public V getNow() {
        V v = (V) this.result;
        if ((v instanceof CauseHolder) || v == SUCCESS || v == UNCANCELLABLE) {
            return null;
        }
        return v;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.AbstractFuture, java.util.concurrent.Future
    public V get() throws InterruptedException, ExecutionException {
        V v = (V) this.result;
        if (!isDone0(v)) {
            await();
            v = (V) this.result;
        }
        if (v == SUCCESS || v == UNCANCELLABLE) {
            return null;
        }
        Throwable cause0 = cause0(v);
        if (cause0 == null) {
            return v;
        }
        if (cause0 instanceof CancellationException) {
            throw ((CancellationException) cause0);
        }
        throw new ExecutionException(cause0);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.AbstractFuture, java.util.concurrent.Future
    public V get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        V v = (V) this.result;
        if (!isDone0(v)) {
            if (!await(j, timeUnit)) {
                throw new TimeoutException();
            }
            v = (V) this.result;
        }
        if (v == SUCCESS || v == UNCANCELLABLE) {
            return null;
        }
        Throwable cause0 = cause0(v);
        if (cause0 == null) {
            return v;
        }
        if (cause0 instanceof CancellationException) {
            throw ((CancellationException) cause0);
        }
        throw new ExecutionException(cause0);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future, java.util.concurrent.Future
    public boolean cancel(boolean z) {
        if (!RESULT_UPDATER.compareAndSet(this, null, CANCELLATION_CAUSE_HOLDER)) {
            return false;
        }
        if (!checkNotifyWaiters()) {
            return true;
        }
        notifyListeners();
        return true;
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return isCancelled0(this.result);
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return isDone0(this.result);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Promise<V> sync() throws InterruptedException {
        await();
        rethrowIfFailed();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.Future
    public Promise<V> syncUninterruptibly() {
        awaitUninterruptibly();
        rethrowIfFailed();
        return this;
    }

    public String toString() {
        return toStringBuilder().toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StringBuilder toStringBuilder() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(StringUtil.simpleClassName(this));
        sb.append('@');
        sb.append(Integer.toHexString(hashCode()));
        Object obj = this.result;
        if (obj == SUCCESS) {
            sb.append("(success)");
        } else if (obj == UNCANCELLABLE) {
            sb.append("(uncancellable)");
        } else if (obj instanceof CauseHolder) {
            sb.append("(failure: ");
            sb.append(((CauseHolder) obj).cause);
            sb.append(')');
        } else if (obj != null) {
            sb.append("(success: ");
            sb.append(obj);
            sb.append(')');
        } else {
            sb.append("(incomplete)");
        }
        return sb;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public EventExecutor executor() {
        return this.executor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkDeadLock() {
        EventExecutor executor = executor();
        if (executor != null && executor.inEventLoop()) {
            throw new BlockingOperationException(toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void notifyListener(EventExecutor eventExecutor, Future<?> future, GenericFutureListener<?> genericFutureListener) {
        notifyListenerWithStackOverFlowProtection((EventExecutor) ObjectUtil.checkNotNull(eventExecutor, "eventExecutor"), (Future) ObjectUtil.checkNotNull(future, "future"), (GenericFutureListener) ObjectUtil.checkNotNull(genericFutureListener, "listener"));
    }

    private void notifyListeners() {
        InternalThreadLocalMap internalThreadLocalMap;
        int futureListenerStackDepth;
        EventExecutor executor = executor();
        if (executor.inEventLoop() && (futureListenerStackDepth = (internalThreadLocalMap = InternalThreadLocalMap.get()).futureListenerStackDepth()) < MAX_LISTENER_STACK_DEPTH) {
            internalThreadLocalMap.setFutureListenerStackDepth(futureListenerStackDepth + 1);
            try {
                notifyListenersNow();
                return;
            } finally {
                internalThreadLocalMap.setFutureListenerStackDepth(futureListenerStackDepth);
            }
        }
        safeExecute(executor, new Runnable() { // from class: io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise.1
            @Override // java.lang.Runnable
            public void run() {
                DefaultPromise.this.notifyListenersNow();
            }
        });
    }

    private static void notifyListenerWithStackOverFlowProtection(EventExecutor eventExecutor, final Future<?> future, final GenericFutureListener<?> genericFutureListener) {
        InternalThreadLocalMap internalThreadLocalMap;
        int futureListenerStackDepth;
        if (eventExecutor.inEventLoop() && (futureListenerStackDepth = (internalThreadLocalMap = InternalThreadLocalMap.get()).futureListenerStackDepth()) < MAX_LISTENER_STACK_DEPTH) {
            internalThreadLocalMap.setFutureListenerStackDepth(futureListenerStackDepth + 1);
            try {
                notifyListener0(future, genericFutureListener);
                return;
            } finally {
                internalThreadLocalMap.setFutureListenerStackDepth(futureListenerStackDepth);
            }
        }
        safeExecute(eventExecutor, new Runnable() { // from class: io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise.2
            @Override // java.lang.Runnable
            public void run() {
                DefaultPromise.notifyListener0(Future.this, genericFutureListener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyListenersNow() {
        synchronized (this) {
            if (!this.notifyingListeners && this.listeners != null) {
                this.notifyingListeners = true;
                Object obj = this.listeners;
                this.listeners = null;
                while (true) {
                    if (obj instanceof DefaultFutureListeners) {
                        notifyListeners0((DefaultFutureListeners) obj);
                    } else {
                        notifyListener0(this, (GenericFutureListener) obj);
                    }
                    synchronized (this) {
                        if (this.listeners == null) {
                            this.notifyingListeners = false;
                            return;
                        } else {
                            obj = this.listeners;
                            this.listeners = null;
                        }
                    }
                }
            }
        }
    }

    private void notifyListeners0(DefaultFutureListeners defaultFutureListeners) {
        GenericFutureListener<? extends Future<?>>[] listeners = defaultFutureListeners.listeners();
        int size = defaultFutureListeners.size();
        for (int i = 0; i < size; i++) {
            notifyListener0(this, listeners[i]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void notifyListener0(Future future, GenericFutureListener genericFutureListener) {
        try {
            genericFutureListener.operationComplete(future);
        } catch (Throwable th) {
            if (logger.isWarnEnabled()) {
                logger.warn("An exception was thrown by " + genericFutureListener.getClass().getName() + ".operationComplete()", th);
            }
        }
    }

    private void addListener0(GenericFutureListener<? extends Future<? super V>> genericFutureListener) {
        Object obj = this.listeners;
        if (obj == null) {
            this.listeners = genericFutureListener;
        } else if (obj instanceof DefaultFutureListeners) {
            ((DefaultFutureListeners) obj).add(genericFutureListener);
        } else {
            this.listeners = new DefaultFutureListeners((GenericFutureListener) obj, genericFutureListener);
        }
    }

    private void removeListener0(GenericFutureListener<? extends Future<? super V>> genericFutureListener) {
        Object obj = this.listeners;
        if (obj instanceof DefaultFutureListeners) {
            ((DefaultFutureListeners) obj).remove(genericFutureListener);
        } else if (obj == genericFutureListener) {
            this.listeners = null;
        }
    }

    private boolean setSuccess0(V v) {
        if (v == null) {
            v = (V) SUCCESS;
        }
        return setValue0(v);
    }

    private boolean setFailure0(Throwable th) {
        return setValue0(new CauseHolder((Throwable) ObjectUtil.checkNotNull(th, "cause")));
    }

    private boolean setValue0(Object obj) {
        if (!RESULT_UPDATER.compareAndSet(this, null, obj) && !RESULT_UPDATER.compareAndSet(this, UNCANCELLABLE, obj)) {
            return false;
        }
        if (!checkNotifyWaiters()) {
            return true;
        }
        notifyListeners();
        return true;
    }

    private synchronized boolean checkNotifyWaiters() {
        if (this.waiters > 0) {
            notifyAll();
        }
        return this.listeners != null;
    }

    private void incWaiters() {
        short s = this.waiters;
        if (s == Short.MAX_VALUE) {
            throw new IllegalStateException("too many waiters: " + this);
        }
        this.waiters = (short) (s + 1);
    }

    private void decWaiters() {
        this.waiters = (short) (this.waiters - 1);
    }

    private void rethrowIfFailed() {
        Throwable cause = cause();
        if (cause == null) {
            return;
        }
        PlatformDependent.throwException(cause);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:50:0x0091 -> B:51:0x008c). Please report as a decompilation issue!!! */
    private boolean await0(long j, boolean z) throws InterruptedException {
        boolean z2 = true;
        if (isDone()) {
            return true;
        }
        if (j <= 0) {
            return isDone();
        }
        if (z && Thread.interrupted()) {
            throw new InterruptedException(toString());
        }
        checkDeadLock();
        long nanoTime = System.nanoTime();
        boolean z3 = false;
        long j2 = j;
        do {
            try {
                synchronized (this) {
                    try {
                        if (isDone()) {
                            if (z3) {
                                Thread.currentThread().interrupt();
                            }
                            return true;
                        }
                        incWaiters();
                        try {
                            try {
                                wait(j2 / 1000000, (int) (j2 % 1000000));
                            } finally {
                                decWaiters();
                            }
                        } catch (InterruptedException e) {
                            if (z) {
                                throw e;
                            }
                            try {
                                z3 = true;
                            } catch (Throwable th) {
                                th = th;
                                try {
                                    throw th;
                                } catch (Throwable th2) {
                                    th = th2;
                                    z3 = z2;
                                    if (z3) {
                                        Thread.currentThread().interrupt();
                                    }
                                    throw th;
                                }
                            }
                        }
                        if (isDone()) {
                            if (z3) {
                                Thread.currentThread().interrupt();
                            }
                            return true;
                        }
                        j2 = j - (System.nanoTime() - nanoTime);
                    } catch (Throwable th3) {
                        th = th3;
                        z2 = z3;
                        throw th;
                    }
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } while (j2 > 0);
        boolean isDone = isDone();
        if (z3) {
            Thread.currentThread().interrupt();
        }
        return isDone;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public void notifyProgressiveListeners(final long j, final long j2) {
        Object progressiveListeners = progressiveListeners();
        if (progressiveListeners == null) {
            return;
        }
        final ProgressiveFuture progressiveFuture = (ProgressiveFuture) this;
        EventExecutor executor = executor();
        if (executor.inEventLoop()) {
            if (progressiveListeners instanceof GenericProgressiveFutureListener[]) {
                notifyProgressiveListeners0(progressiveFuture, (GenericProgressiveFutureListener[]) progressiveListeners, j, j2);
                return;
            } else {
                notifyProgressiveListener0(progressiveFuture, (GenericProgressiveFutureListener) progressiveListeners, j, j2);
                return;
            }
        }
        if (progressiveListeners instanceof GenericProgressiveFutureListener[]) {
            final GenericProgressiveFutureListener[] genericProgressiveFutureListenerArr = (GenericProgressiveFutureListener[]) progressiveListeners;
            safeExecute(executor, new Runnable() { // from class: io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise.3
                @Override // java.lang.Runnable
                public void run() {
                    DefaultPromise.notifyProgressiveListeners0(progressiveFuture, genericProgressiveFutureListenerArr, j, j2);
                }
            });
        } else {
            final GenericProgressiveFutureListener genericProgressiveFutureListener = (GenericProgressiveFutureListener) progressiveListeners;
            safeExecute(executor, new Runnable() { // from class: io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise.4
                @Override // java.lang.Runnable
                public void run() {
                    DefaultPromise.notifyProgressiveListener0(progressiveFuture, genericProgressiveFutureListener, j, j2);
                }
            });
        }
    }

    private synchronized Object progressiveListeners() {
        Object obj = this.listeners;
        if (obj == null) {
            return null;
        }
        if (obj instanceof DefaultFutureListeners) {
            DefaultFutureListeners defaultFutureListeners = (DefaultFutureListeners) obj;
            int progressiveSize = defaultFutureListeners.progressiveSize();
            if (progressiveSize == 0) {
                return null;
            }
            int i = 0;
            if (progressiveSize == 1) {
                GenericFutureListener<? extends Future<?>>[] listeners = defaultFutureListeners.listeners();
                int length = listeners.length;
                while (i < length) {
                    GenericFutureListener<? extends Future<?>> genericFutureListener = listeners[i];
                    if (genericFutureListener instanceof GenericProgressiveFutureListener) {
                        return genericFutureListener;
                    }
                    i++;
                }
                return null;
            }
            GenericFutureListener<? extends Future<?>>[] listeners2 = defaultFutureListeners.listeners();
            GenericProgressiveFutureListener[] genericProgressiveFutureListenerArr = new GenericProgressiveFutureListener[progressiveSize];
            int i2 = 0;
            while (i < progressiveSize) {
                GenericFutureListener<? extends Future<?>> genericFutureListener2 = listeners2[i2];
                if (genericFutureListener2 instanceof GenericProgressiveFutureListener) {
                    int i3 = i + 1;
                    genericProgressiveFutureListenerArr[i] = (GenericProgressiveFutureListener) genericFutureListener2;
                    i = i3;
                }
                i2++;
            }
            return genericProgressiveFutureListenerArr;
        }
        if (obj instanceof GenericProgressiveFutureListener) {
            return obj;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void notifyProgressiveListeners0(ProgressiveFuture<?> progressiveFuture, GenericProgressiveFutureListener<?>[] genericProgressiveFutureListenerArr, long j, long j2) {
        for (GenericProgressiveFutureListener<?> genericProgressiveFutureListener : genericProgressiveFutureListenerArr) {
            if (genericProgressiveFutureListener == null) {
                return;
            }
            notifyProgressiveListener0(progressiveFuture, genericProgressiveFutureListener, j, j2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void notifyProgressiveListener0(ProgressiveFuture progressiveFuture, GenericProgressiveFutureListener genericProgressiveFutureListener, long j, long j2) {
        try {
            genericProgressiveFutureListener.operationProgressed(progressiveFuture, j, j2);
        } catch (Throwable th) {
            if (logger.isWarnEnabled()) {
                logger.warn("An exception was thrown by " + genericProgressiveFutureListener.getClass().getName() + ".operationProgressed()", th);
            }
        }
    }

    private static boolean isCancelled0(Object obj) {
        return (obj instanceof CauseHolder) && (((CauseHolder) obj).cause instanceof CancellationException);
    }

    private static boolean isDone0(Object obj) {
        return (obj == null || obj == UNCANCELLABLE) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class CauseHolder {
        final Throwable cause;

        CauseHolder(Throwable th) {
            this.cause = th;
        }
    }

    private static void safeExecute(EventExecutor eventExecutor, Runnable runnable) {
        try {
            eventExecutor.execute(runnable);
        } catch (Throwable th) {
            rejectedExecutionLogger.error("Failed to submit a listener notification task. Event loop shut down?", th);
        }
    }
}
