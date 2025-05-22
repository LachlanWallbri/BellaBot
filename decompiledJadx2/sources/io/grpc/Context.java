package io.grpc;

import io.grpc.PersistentHashArrayMappedTrie;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class Context {
    static final int CONTEXT_DEPTH_WARN_THRESH = 1000;
    final CancellableContext cancellableAncestor;
    final int generation;
    final PersistentHashArrayMappedTrie.Node<Key<?>, Object> keyValueEntries;
    static final Logger log = Logger.getLogger(Context.class.getName());
    public static final Context ROOT = new Context();

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    @interface CanIgnoreReturnValue {
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface CancellationListener {
        void cancelled(Context context);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    @interface CheckReturnValue {
    }

    static Storage storage() {
        return LazyStorage.storage;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class LazyStorage {
        static final Storage storage;

        private LazyStorage() {
        }

        static {
            AtomicReference atomicReference = new AtomicReference();
            storage = createStorage(atomicReference);
            Throwable th = (Throwable) atomicReference.get();
            if (th != null) {
                Context.log.log(Level.FINE, "Storage override doesn't exist. Using default", th);
            }
        }

        private static Storage createStorage(AtomicReference<? super ClassNotFoundException> atomicReference) {
            try {
                return (Storage) Class.forName("io.grpc.override.ContextStorageOverride").asSubclass(Storage.class).getConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (ClassNotFoundException e) {
                atomicReference.set(e);
                return new ThreadLocalContextStorage();
            } catch (Exception e2) {
                throw new RuntimeException("Storage override failed to initialize", e2);
            }
        }
    }

    public static <T> Key<T> key(String str) {
        return new Key<>(str);
    }

    public static <T> Key<T> keyWithDefault(String str, T t) {
        return new Key<>(str, t);
    }

    public static Context current() {
        Context current = storage().current();
        return current == null ? ROOT : current;
    }

    private Context(PersistentHashArrayMappedTrie.Node<Key<?>, Object> node, int i) {
        this.cancellableAncestor = null;
        this.keyValueEntries = node;
        this.generation = i;
        validateGeneration(i);
    }

    private Context(Context context, PersistentHashArrayMappedTrie.Node<Key<?>, Object> node) {
        this.cancellableAncestor = cancellableAncestor(context);
        this.keyValueEntries = node;
        this.generation = context.generation + 1;
        validateGeneration(this.generation);
    }

    private Context() {
        this.cancellableAncestor = null;
        this.keyValueEntries = null;
        this.generation = 0;
        validateGeneration(this.generation);
    }

    public CancellableContext withCancellation() {
        return new CancellableContext();
    }

    public CancellableContext withDeadlineAfter(long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        return withDeadline(Deadline.after(j, timeUnit), scheduledExecutorService);
    }

    public CancellableContext withDeadline(Deadline deadline, ScheduledExecutorService scheduledExecutorService) {
        boolean z;
        checkNotNull(deadline, "deadline");
        checkNotNull(scheduledExecutorService, "scheduler");
        Deadline deadline2 = getDeadline();
        if (deadline2 == null || deadline2.compareTo(deadline) > 0) {
            z = true;
        } else {
            z = false;
            deadline = deadline2;
        }
        CancellableContext cancellableContext = new CancellableContext(deadline);
        if (z) {
            cancellableContext.setUpDeadlineCancellation(deadline, scheduledExecutorService);
        }
        return cancellableContext;
    }

    public <V> Context withValue(Key<V> key, V v) {
        return new Context(this, (PersistentHashArrayMappedTrie.Node<Key<?>, Object>) PersistentHashArrayMappedTrie.put(this.keyValueEntries, key, v));
    }

    public <V1, V2> Context withValues(Key<V1> key, V1 v1, Key<V2> key2, V2 v2) {
        return new Context(this, (PersistentHashArrayMappedTrie.Node<Key<?>, Object>) PersistentHashArrayMappedTrie.put(PersistentHashArrayMappedTrie.put(this.keyValueEntries, key, v1), key2, v2));
    }

    public <V1, V2, V3> Context withValues(Key<V1> key, V1 v1, Key<V2> key2, V2 v2, Key<V3> key3, V3 v3) {
        return new Context(this, (PersistentHashArrayMappedTrie.Node<Key<?>, Object>) PersistentHashArrayMappedTrie.put(PersistentHashArrayMappedTrie.put(PersistentHashArrayMappedTrie.put(this.keyValueEntries, key, v1), key2, v2), key3, v3));
    }

    public <V1, V2, V3, V4> Context withValues(Key<V1> key, V1 v1, Key<V2> key2, V2 v2, Key<V3> key3, V3 v3, Key<V4> key4, V4 v4) {
        return new Context(this, (PersistentHashArrayMappedTrie.Node<Key<?>, Object>) PersistentHashArrayMappedTrie.put(PersistentHashArrayMappedTrie.put(PersistentHashArrayMappedTrie.put(PersistentHashArrayMappedTrie.put(this.keyValueEntries, key, v1), key2, v2), key3, v3), key4, v4));
    }

    public Context fork() {
        return new Context(this.keyValueEntries, this.generation + 1);
    }

    public Context attach() {
        Context doAttach = storage().doAttach(this);
        return doAttach == null ? ROOT : doAttach;
    }

    public void detach(Context context) {
        checkNotNull(context, "toAttach");
        storage().detach(this, context);
    }

    boolean isCurrent() {
        return current() == this;
    }

    public boolean isCancelled() {
        CancellableContext cancellableContext = this.cancellableAncestor;
        if (cancellableContext == null) {
            return false;
        }
        return cancellableContext.isCancelled();
    }

    public Throwable cancellationCause() {
        CancellableContext cancellableContext = this.cancellableAncestor;
        if (cancellableContext == null) {
            return null;
        }
        return cancellableContext.cancellationCause();
    }

    public Deadline getDeadline() {
        CancellableContext cancellableContext = this.cancellableAncestor;
        if (cancellableContext == null) {
            return null;
        }
        return cancellableContext.getDeadline();
    }

    public void addListener(CancellationListener cancellationListener, Executor executor) {
        checkNotNull(cancellationListener, "cancellationListener");
        checkNotNull(executor, "executor");
        CancellableContext cancellableContext = this.cancellableAncestor;
        if (cancellableContext == null) {
            return;
        }
        cancellableContext.addListenerInternal(new ExecutableListener(executor, cancellationListener, this));
    }

    public void removeListener(CancellationListener cancellationListener) {
        CancellableContext cancellableContext = this.cancellableAncestor;
        if (cancellableContext == null) {
            return;
        }
        cancellableContext.removeListenerInternal(cancellationListener, this);
    }

    int listenerCount() {
        CancellableContext cancellableContext = this.cancellableAncestor;
        if (cancellableContext == null) {
            return 0;
        }
        return cancellableContext.listenerCount();
    }

    public void run(Runnable runnable) {
        Context attach = attach();
        try {
            runnable.run();
        } finally {
            detach(attach);
        }
    }

    public <V> V call(Callable<V> callable) throws Exception {
        Context attach = attach();
        try {
            return callable.call();
        } finally {
            detach(attach);
        }
    }

    public Runnable wrap(final Runnable runnable) {
        return new Runnable() { // from class: io.grpc.Context.1
            @Override // java.lang.Runnable
            public void run() {
                Context attach = Context.this.attach();
                try {
                    runnable.run();
                } finally {
                    Context.this.detach(attach);
                }
            }
        };
    }

    public <C> Callable<C> wrap(final Callable<C> callable) {
        return new Callable<C>() { // from class: io.grpc.Context.2
            @Override // java.util.concurrent.Callable
            public C call() throws Exception {
                Context attach = Context.this.attach();
                try {
                    return (C) callable.call();
                } finally {
                    Context.this.detach(attach);
                }
            }
        };
    }

    public Executor fixedContextExecutor(final Executor executor) {
        return new Executor() { // from class: io.grpc.Context.1FixedContextExecutor
            @Override // java.util.concurrent.Executor
            public void execute(Runnable runnable) {
                executor.execute(Context.this.wrap(runnable));
            }
        };
    }

    public static Executor currentContextExecutor(final Executor executor) {
        return new Executor() { // from class: io.grpc.Context.1CurrentContextExecutor
            @Override // java.util.concurrent.Executor
            public void execute(Runnable runnable) {
                executor.execute(Context.current().wrap(runnable));
            }
        };
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class CancellableContext extends Context implements Closeable {
        private Throwable cancellationCause;
        private boolean cancelled;
        private final Deadline deadline;
        private ArrayList<ExecutableListener> listeners;
        private CancellationListener parentListener;
        private ScheduledFuture<?> pendingDeadline;
        private final Context uncancellableSurrogate;

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private CancellableContext(Context context) {
            super(context.keyValueEntries);
            this.deadline = context.getDeadline();
            this.uncancellableSurrogate = new Context(this.keyValueEntries);
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private CancellableContext(Context context, Deadline deadline) {
            super(context.keyValueEntries);
            this.deadline = deadline;
            this.uncancellableSurrogate = new Context(this.keyValueEntries);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUpDeadlineCancellation(Deadline deadline, ScheduledExecutorService scheduledExecutorService) {
            if (!deadline.isExpired()) {
                synchronized (this) {
                    this.pendingDeadline = deadline.runOnExpiration(new Runnable() { // from class: io.grpc.Context.CancellableContext.1CancelOnExpiration
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                CancellableContext.this.cancel(new TimeoutException("context timed out"));
                            } catch (Throwable th) {
                                Context.log.log(Level.SEVERE, "Cancel threw an exception, which should not happen", th);
                            }
                        }
                    }, scheduledExecutorService);
                }
            } else {
                cancel(new TimeoutException("context timed out"));
            }
        }

        @Override // io.grpc.Context
        public Context attach() {
            return this.uncancellableSurrogate.attach();
        }

        @Override // io.grpc.Context
        public void detach(Context context) {
            this.uncancellableSurrogate.detach(context);
        }

        @Override // io.grpc.Context
        public void addListener(CancellationListener cancellationListener, Executor executor) {
            checkNotNull(cancellationListener, "cancellationListener");
            checkNotNull(executor, "executor");
            addListenerInternal(new ExecutableListener(executor, cancellationListener, this));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addListenerInternal(ExecutableListener executableListener) {
            synchronized (this) {
                if (isCancelled()) {
                    executableListener.deliver();
                } else if (this.listeners == null) {
                    this.listeners = new ArrayList<>();
                    this.listeners.add(executableListener);
                    if (this.cancellableAncestor != null) {
                        this.parentListener = new CancellationListener() { // from class: io.grpc.Context.CancellableContext.1
                            @Override // io.grpc.Context.CancellationListener
                            public void cancelled(Context context) {
                                CancellableContext.this.cancel(context.cancellationCause());
                            }
                        };
                        this.cancellableAncestor.addListenerInternal(new ExecutableListener(DirectExecutor.INSTANCE, this.parentListener, this));
                    }
                } else {
                    this.listeners.add(executableListener);
                }
            }
        }

        @Override // io.grpc.Context
        public void removeListener(CancellationListener cancellationListener) {
            removeListenerInternal(cancellationListener, this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeListenerInternal(CancellationListener cancellationListener, Context context) {
            synchronized (this) {
                if (this.listeners != null) {
                    int size = this.listeners.size() - 1;
                    while (true) {
                        if (size < 0) {
                            break;
                        }
                        ExecutableListener executableListener = this.listeners.get(size);
                        if (executableListener.listener == cancellationListener && executableListener.context == context) {
                            this.listeners.remove(size);
                            break;
                        }
                        size--;
                    }
                    if (this.listeners.isEmpty()) {
                        if (this.cancellableAncestor != null) {
                            this.cancellableAncestor.removeListener(this.parentListener);
                        }
                        this.parentListener = null;
                        this.listeners = null;
                    }
                }
            }
        }

        @Override // io.grpc.Context
        @Deprecated
        public boolean isCurrent() {
            return this.uncancellableSurrogate.isCurrent();
        }

        public boolean cancel(Throwable th) {
            boolean z;
            ScheduledFuture<?> scheduledFuture;
            synchronized (this) {
                z = true;
                scheduledFuture = null;
                if (this.cancelled) {
                    z = false;
                } else {
                    this.cancelled = true;
                    if (this.pendingDeadline != null) {
                        ScheduledFuture<?> scheduledFuture2 = this.pendingDeadline;
                        this.pendingDeadline = null;
                        scheduledFuture = scheduledFuture2;
                    }
                    this.cancellationCause = th;
                }
            }
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            if (z) {
                notifyAndClearListeners();
            }
            return z;
        }

        private void notifyAndClearListeners() {
            synchronized (this) {
                if (this.listeners == null) {
                    return;
                }
                CancellationListener cancellationListener = this.parentListener;
                this.parentListener = null;
                ArrayList<ExecutableListener> arrayList = this.listeners;
                this.listeners = null;
                Iterator<ExecutableListener> it = arrayList.iterator();
                while (it.hasNext()) {
                    ExecutableListener next = it.next();
                    if (next.context == this) {
                        next.deliver();
                    }
                }
                Iterator<ExecutableListener> it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    ExecutableListener next2 = it2.next();
                    if (next2.context != this) {
                        next2.deliver();
                    }
                }
                if (this.cancellableAncestor != null) {
                    this.cancellableAncestor.removeListener(cancellationListener);
                }
            }
        }

        @Override // io.grpc.Context
        int listenerCount() {
            int size;
            synchronized (this) {
                size = this.listeners == null ? 0 : this.listeners.size();
            }
            return size;
        }

        public void detachAndCancel(Context context, Throwable th) {
            try {
                detach(context);
            } finally {
                cancel(th);
            }
        }

        @Override // io.grpc.Context
        public boolean isCancelled() {
            synchronized (this) {
                if (this.cancelled) {
                    return true;
                }
                if (!super.isCancelled()) {
                    return false;
                }
                cancel(super.cancellationCause());
                return true;
            }
        }

        @Override // io.grpc.Context
        public Throwable cancellationCause() {
            if (isCancelled()) {
                return this.cancellationCause;
            }
            return null;
        }

        @Override // io.grpc.Context
        public Deadline getDeadline() {
            return this.deadline;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            cancel(null);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Key<T> {
        private final T defaultValue;
        private final String name;

        Key(String str) {
            this(str, null);
        }

        Key(String str, T t) {
            this.name = (String) Context.checkNotNull(str, "name");
            this.defaultValue = t;
        }

        public T get() {
            return get(Context.current());
        }

        public T get(Context context) {
            T t = (T) PersistentHashArrayMappedTrie.get(context.keyValueEntries, this);
            return t == null ? this.defaultValue : t;
        }

        public String toString() {
            return this.name;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static abstract class Storage {
        public abstract Context current();

        public abstract void detach(Context context, Context context2);

        @Deprecated
        public void attach(Context context) {
            throw new UnsupportedOperationException("Deprecated. Do not call.");
        }

        public Context doAttach(Context context) {
            Context current = current();
            attach(context);
            return current;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class ExecutableListener implements Runnable {
        private final Context context;
        private final Executor executor;
        final CancellationListener listener;

        ExecutableListener(Executor executor, CancellationListener cancellationListener, Context context) {
            this.executor = executor;
            this.listener = cancellationListener;
            this.context = context;
        }

        void deliver() {
            try {
                this.executor.execute(this);
            } catch (Throwable th) {
                Context.log.log(Level.INFO, "Exception notifying context listener", th);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.listener.cancelled(this.context);
        }
    }

    static <T> T checkNotNull(T t, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum DirectExecutor implements Executor {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "Context.DirectExecutor";
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            runnable.run();
        }
    }

    static CancellableContext cancellableAncestor(Context context) {
        if (context instanceof CancellableContext) {
            return (CancellableContext) context;
        }
        return context.cancellableAncestor;
    }

    private static void validateGeneration(int i) {
        if (i == 1000) {
            log.log(Level.SEVERE, "Context ancestry chain length is abnormally long. This suggests an error in application code. Length exceeded: 1000", (Throwable) new Exception());
        }
    }
}
