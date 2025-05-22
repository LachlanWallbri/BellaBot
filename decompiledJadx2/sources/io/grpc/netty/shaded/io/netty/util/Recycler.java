package io.grpc.netty.shaded.io.netty.util;

import io.grpc.netty.shaded.io.netty.util.concurrent.FastThreadLocal;
import io.grpc.netty.shaded.io.netty.util.internal.MathUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectPool;
import io.grpc.netty.shaded.io.netty.util.internal.SystemPropertyUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class Recycler<T> {
    private static final int DEFAULT_INITIAL_MAX_CAPACITY_PER_THREAD = 4096;
    private static final int DEFAULT_MAX_CAPACITY_PER_THREAD;
    private static final int DELAYED_QUEUE_RATIO;
    private static final FastThreadLocal<Map<Stack<?>, WeakOrderQueue>> DELAYED_RECYCLED;
    private static final int INITIAL_CAPACITY;
    private static final int LINK_CAPACITY;
    private static final int MAX_DELAYED_QUEUES_PER_THREAD;
    private static final int MAX_SHARED_CAPACITY_FACTOR;
    private static final int RATIO;
    private final int delayedQueueInterval;
    private final int interval;
    private final int maxCapacityPerThread;
    private final int maxDelayedQueuesPerThread;
    private final int maxSharedCapacityFactor;
    private final FastThreadLocal<Stack<T>> threadLocal;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) Recycler.class);
    private static final Handle NOOP_HANDLE = new Handle() { // from class: io.grpc.netty.shaded.io.netty.util.Recycler.1
        @Override // io.grpc.netty.shaded.io.netty.util.internal.ObjectPool.Handle
        public void recycle(Object obj) {
        }
    };
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(Integer.MIN_VALUE);
    private static final int OWN_THREAD_ID = ID_GENERATOR.getAndIncrement();

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface Handle<T> extends ObjectPool.Handle<T> {
    }

    protected abstract T newObject(Handle<T> handle);

    static {
        int i = SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.recycler.maxCapacityPerThread", SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.recycler.maxCapacity", 4096));
        DEFAULT_MAX_CAPACITY_PER_THREAD = i >= 0 ? i : 4096;
        MAX_SHARED_CAPACITY_FACTOR = Math.max(2, SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.recycler.maxSharedCapacityFactor", 2));
        MAX_DELAYED_QUEUES_PER_THREAD = Math.max(0, SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.recycler.maxDelayedQueuesPerThread", NettyRuntime.availableProcessors() * 2));
        LINK_CAPACITY = MathUtil.safeFindNextPositivePowerOfTwo(Math.max(SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.recycler.linkCapacity", 16), 16));
        RATIO = Math.max(0, SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.recycler.ratio", 8));
        DELAYED_QUEUE_RATIO = Math.max(0, SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.recycler.delayedQueue.ratio", RATIO));
        if (logger.isDebugEnabled()) {
            int i2 = DEFAULT_MAX_CAPACITY_PER_THREAD;
            if (i2 == 0) {
                logger.debug("-Dio.netty.recycler.maxCapacityPerThread: disabled");
                logger.debug("-Dio.netty.recycler.maxSharedCapacityFactor: disabled");
                logger.debug("-Dio.netty.recycler.linkCapacity: disabled");
                logger.debug("-Dio.netty.recycler.ratio: disabled");
                logger.debug("-Dio.netty.recycler.delayedQueue.ratio: disabled");
            } else {
                logger.debug("-Dio.netty.recycler.maxCapacityPerThread: {}", Integer.valueOf(i2));
                logger.debug("-Dio.netty.recycler.maxSharedCapacityFactor: {}", Integer.valueOf(MAX_SHARED_CAPACITY_FACTOR));
                logger.debug("-Dio.netty.recycler.linkCapacity: {}", Integer.valueOf(LINK_CAPACITY));
                logger.debug("-Dio.netty.recycler.ratio: {}", Integer.valueOf(RATIO));
                logger.debug("-Dio.netty.recycler.delayedQueue.ratio: {}", Integer.valueOf(DELAYED_QUEUE_RATIO));
            }
        }
        INITIAL_CAPACITY = Math.min(DEFAULT_MAX_CAPACITY_PER_THREAD, 256);
        DELAYED_RECYCLED = new FastThreadLocal<Map<Stack<?>, WeakOrderQueue>>() { // from class: io.grpc.netty.shaded.io.netty.util.Recycler.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.FastThreadLocal
            public Map<Stack<?>, WeakOrderQueue> initialValue() {
                return new WeakHashMap();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Recycler() {
        this(DEFAULT_MAX_CAPACITY_PER_THREAD);
    }

    protected Recycler(int i) {
        this(i, MAX_SHARED_CAPACITY_FACTOR);
    }

    protected Recycler(int i, int i2) {
        this(i, i2, RATIO, MAX_DELAYED_QUEUES_PER_THREAD);
    }

    protected Recycler(int i, int i2, int i3, int i4) {
        this(i, i2, i3, i4, DELAYED_QUEUE_RATIO);
    }

    protected Recycler(int i, int i2, int i3, int i4, int i5) {
        this.threadLocal = new FastThreadLocal<Stack<T>>() { // from class: io.grpc.netty.shaded.io.netty.util.Recycler.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.FastThreadLocal
            public Stack<T> initialValue() {
                return new Stack<>(Recycler.this, Thread.currentThread(), Recycler.this.maxCapacityPerThread, Recycler.this.maxSharedCapacityFactor, Recycler.this.interval, Recycler.this.maxDelayedQueuesPerThread, Recycler.this.delayedQueueInterval);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.FastThreadLocal
            public void onRemoval(Stack<T> stack) {
                if (stack.threadRef.get() == Thread.currentThread() && Recycler.DELAYED_RECYCLED.isSet()) {
                    ((Map) Recycler.DELAYED_RECYCLED.get()).remove(stack);
                }
            }
        };
        this.interval = Math.max(0, i3);
        this.delayedQueueInterval = Math.max(0, i5);
        if (i <= 0) {
            this.maxCapacityPerThread = 0;
            this.maxSharedCapacityFactor = 1;
            this.maxDelayedQueuesPerThread = 0;
        } else {
            this.maxCapacityPerThread = i;
            this.maxSharedCapacityFactor = Math.max(1, i2);
            this.maxDelayedQueuesPerThread = Math.max(0, i4);
        }
    }

    public final T get() {
        if (this.maxCapacityPerThread == 0) {
            return newObject(NOOP_HANDLE);
        }
        Stack<T> stack = this.threadLocal.get();
        DefaultHandle<T> pop = stack.pop();
        if (pop == null) {
            pop = stack.newHandle();
            pop.value = newObject(pop);
        }
        return (T) pop.value;
    }

    @Deprecated
    public final boolean recycle(T t, Handle<T> handle) {
        if (handle == NOOP_HANDLE) {
            return false;
        }
        DefaultHandle defaultHandle = (DefaultHandle) handle;
        if (defaultHandle.stack.parent != this) {
            return false;
        }
        defaultHandle.recycle(t);
        return true;
    }

    final int threadLocalCapacity() {
        return this.threadLocal.get().elements.length;
    }

    final int threadLocalSize() {
        return this.threadLocal.get().size;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class DefaultHandle<T> implements Handle<T> {
        boolean hasBeenRecycled;
        int lastRecycledId;
        int recycleId;
        Stack<?> stack;
        Object value;

        DefaultHandle(Stack<?> stack) {
            this.stack = stack;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.internal.ObjectPool.Handle
        public void recycle(Object obj) {
            if (obj != this.value) {
                throw new IllegalArgumentException("object does not belong to handle");
            }
            Stack<?> stack = this.stack;
            if (this.lastRecycledId != this.recycleId || stack == null) {
                throw new IllegalStateException("recycled already");
            }
            stack.push(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class WeakOrderQueue extends WeakReference<Thread> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        static final WeakOrderQueue DUMMY = new WeakOrderQueue();
        private int handleRecycleCount;
        private final Head head;

        /* renamed from: id */
        private final int f8430id;
        private final int interval;
        private WeakOrderQueue next;
        private Link tail;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
          classes6.dex
         */
        /* loaded from: classes7.dex */
        public static final class Link extends AtomicInteger {
            final DefaultHandle<?>[] elements = new DefaultHandle[Recycler.LINK_CAPACITY];
            Link next;
            int readIndex;

            Link() {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Classes with same name are omitted:
          classes6.dex
         */
        /* loaded from: classes7.dex */
        public static final class Head {
            private final AtomicInteger availableSharedCapacity;
            Link link;

            Head(AtomicInteger atomicInteger) {
                this.availableSharedCapacity = atomicInteger;
            }

            void reclaimAllSpaceAndUnlink() {
                Link link = this.link;
                this.link = null;
                int i = 0;
                while (link != null) {
                    i += Recycler.LINK_CAPACITY;
                    Link link2 = link.next;
                    link.next = null;
                    link = link2;
                }
                if (i > 0) {
                    reclaimSpace(i);
                }
            }

            private void reclaimSpace(int i) {
                this.availableSharedCapacity.addAndGet(i);
            }

            void relink(Link link) {
                reclaimSpace(Recycler.LINK_CAPACITY);
                this.link = link;
            }

            Link newLink() {
                if (reserveSpaceForLink(this.availableSharedCapacity)) {
                    return new Link();
                }
                return null;
            }

            static boolean reserveSpaceForLink(AtomicInteger atomicInteger) {
                int i;
                do {
                    i = atomicInteger.get();
                    if (i < Recycler.LINK_CAPACITY) {
                        return false;
                    }
                } while (!atomicInteger.compareAndSet(i, i - Recycler.LINK_CAPACITY));
                return true;
            }
        }

        private WeakOrderQueue() {
            super(null);
            this.f8430id = Recycler.ID_GENERATOR.getAndIncrement();
            this.head = new Head(null);
            this.interval = 0;
        }

        private WeakOrderQueue(Stack<?> stack, Thread thread) {
            super(thread);
            this.f8430id = Recycler.ID_GENERATOR.getAndIncrement();
            this.tail = new Link();
            this.head = new Head(stack.availableSharedCapacity);
            this.head.link = this.tail;
            this.interval = ((Stack) stack).delayedQueueInterval;
            this.handleRecycleCount = this.interval;
        }

        static WeakOrderQueue newQueue(Stack<?> stack, Thread thread) {
            if (!Head.reserveSpaceForLink(stack.availableSharedCapacity)) {
                return null;
            }
            WeakOrderQueue weakOrderQueue = new WeakOrderQueue(stack, thread);
            stack.setHead(weakOrderQueue);
            return weakOrderQueue;
        }

        WeakOrderQueue getNext() {
            return this.next;
        }

        void setNext(WeakOrderQueue weakOrderQueue) {
            this.next = weakOrderQueue;
        }

        void reclaimAllSpaceAndUnlink() {
            this.head.reclaimAllSpaceAndUnlink();
            this.next = null;
        }

        void add(DefaultHandle<?> defaultHandle) {
            defaultHandle.lastRecycledId = this.f8430id;
            int i = this.handleRecycleCount;
            if (i < this.interval) {
                this.handleRecycleCount = i + 1;
                return;
            }
            this.handleRecycleCount = 0;
            Link link = this.tail;
            int i2 = link.get();
            if (i2 == Recycler.LINK_CAPACITY) {
                Link newLink = this.head.newLink();
                if (newLink == null) {
                    return;
                }
                link.next = newLink;
                this.tail = newLink;
                i2 = newLink.get();
                link = newLink;
            }
            link.elements[i2] = defaultHandle;
            defaultHandle.stack = null;
            link.lazySet(i2 + 1);
        }

        boolean hasFinalData() {
            return this.tail.readIndex != this.tail.get();
        }

        boolean transfer(Stack<?> stack) {
            Link link = this.head.link;
            if (link == null) {
                return false;
            }
            if (link.readIndex == Recycler.LINK_CAPACITY) {
                if (link.next == null) {
                    return false;
                }
                link = link.next;
                this.head.relink(link);
            }
            int i = link.readIndex;
            int i2 = link.get();
            int i3 = i2 - i;
            if (i3 == 0) {
                return false;
            }
            int i4 = stack.size;
            int i5 = i3 + i4;
            if (i5 > stack.elements.length) {
                i2 = Math.min((stack.increaseCapacity(i5) + i) - i4, i2);
            }
            if (i == i2) {
                return false;
            }
            DefaultHandle<?>[] defaultHandleArr = link.elements;
            DefaultHandle<?>[] defaultHandleArr2 = stack.elements;
            while (i < i2) {
                DefaultHandle<?> defaultHandle = defaultHandleArr[i];
                if (defaultHandle.recycleId == 0) {
                    defaultHandle.recycleId = defaultHandle.lastRecycledId;
                } else if (defaultHandle.recycleId != defaultHandle.lastRecycledId) {
                    throw new IllegalStateException("recycled already");
                }
                defaultHandleArr[i] = null;
                if (!stack.dropHandle(defaultHandle)) {
                    defaultHandle.stack = stack;
                    defaultHandleArr2[i4] = defaultHandle;
                    i4++;
                }
                i++;
            }
            if (i2 == Recycler.LINK_CAPACITY && link.next != null) {
                this.head.relink(link.next);
            }
            link.readIndex = i2;
            if (stack.size == i4) {
                return false;
            }
            stack.size = i4;
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Stack<T> {
        final AtomicInteger availableSharedCapacity;
        private WeakOrderQueue cursor;
        private final int delayedQueueInterval;
        DefaultHandle<?>[] elements;
        private int handleRecycleCount;
        private volatile WeakOrderQueue head;
        private final int interval;
        private final int maxCapacity;
        private final int maxDelayedQueues;
        final Recycler<T> parent;
        private WeakOrderQueue prev;
        int size;
        final WeakReference<Thread> threadRef;

        Stack(Recycler<T> recycler, Thread thread, int i, int i2, int i3, int i4, int i5) {
            this.parent = recycler;
            this.threadRef = new WeakReference<>(thread);
            this.maxCapacity = i;
            this.availableSharedCapacity = new AtomicInteger(Math.max(i / i2, Recycler.LINK_CAPACITY));
            this.elements = new DefaultHandle[Math.min(Recycler.INITIAL_CAPACITY, i)];
            this.interval = i3;
            this.delayedQueueInterval = i5;
            this.handleRecycleCount = i3;
            this.maxDelayedQueues = i4;
        }

        synchronized void setHead(WeakOrderQueue weakOrderQueue) {
            weakOrderQueue.setNext(this.head);
            this.head = weakOrderQueue;
        }

        int increaseCapacity(int i) {
            int length = this.elements.length;
            int i2 = this.maxCapacity;
            do {
                length <<= 1;
                if (length >= i) {
                    break;
                }
            } while (length < i2);
            int min = Math.min(length, i2);
            DefaultHandle<?>[] defaultHandleArr = this.elements;
            if (min != defaultHandleArr.length) {
                this.elements = (DefaultHandle[]) Arrays.copyOf(defaultHandleArr, min);
            }
            return min;
        }

        DefaultHandle<T> pop() {
            int i = this.size;
            if (i == 0 && (!scavenge() || (i = this.size) <= 0)) {
                return null;
            }
            int i2 = i - 1;
            Object[] objArr = this.elements;
            DefaultHandle<T> defaultHandle = (DefaultHandle<T>) objArr[i2];
            objArr[i2] = null;
            this.size = i2;
            if (defaultHandle.lastRecycledId != defaultHandle.recycleId) {
                throw new IllegalStateException("recycled multiple times");
            }
            defaultHandle.recycleId = 0;
            defaultHandle.lastRecycledId = 0;
            return defaultHandle;
        }

        private boolean scavenge() {
            if (scavengeSome()) {
                return true;
            }
            this.prev = null;
            this.cursor = this.head;
            return false;
        }

        private boolean scavengeSome() {
            WeakOrderQueue weakOrderQueue;
            WeakOrderQueue weakOrderQueue2;
            WeakOrderQueue weakOrderQueue3 = this.cursor;
            boolean z = false;
            if (weakOrderQueue3 == null) {
                weakOrderQueue2 = null;
                weakOrderQueue = this.head;
                if (weakOrderQueue == null) {
                    return false;
                }
            } else {
                weakOrderQueue = weakOrderQueue3;
                weakOrderQueue2 = this.prev;
            }
            while (!weakOrderQueue.transfer(this)) {
                WeakOrderQueue next = weakOrderQueue.getNext();
                if (weakOrderQueue.get() == null) {
                    if (weakOrderQueue.hasFinalData()) {
                        while (weakOrderQueue.transfer(this)) {
                            z = true;
                        }
                    }
                    if (weakOrderQueue2 != null) {
                        weakOrderQueue.reclaimAllSpaceAndUnlink();
                        weakOrderQueue2.setNext(next);
                    }
                } else {
                    weakOrderQueue2 = weakOrderQueue;
                }
                if (next == null || z) {
                    weakOrderQueue = next;
                    break;
                }
                weakOrderQueue = next;
            }
            z = true;
            this.prev = weakOrderQueue2;
            this.cursor = weakOrderQueue;
            return z;
        }

        void push(DefaultHandle<?> defaultHandle) {
            Thread currentThread = Thread.currentThread();
            if (this.threadRef.get() == currentThread) {
                pushNow(defaultHandle);
            } else {
                pushLater(defaultHandle, currentThread);
            }
        }

        private void pushNow(DefaultHandle<?> defaultHandle) {
            if ((defaultHandle.recycleId | defaultHandle.lastRecycledId) == 0) {
                int i = Recycler.OWN_THREAD_ID;
                defaultHandle.lastRecycledId = i;
                defaultHandle.recycleId = i;
                int i2 = this.size;
                if (i2 >= this.maxCapacity || dropHandle(defaultHandle)) {
                    return;
                }
                DefaultHandle<?>[] defaultHandleArr = this.elements;
                if (i2 == defaultHandleArr.length) {
                    this.elements = (DefaultHandle[]) Arrays.copyOf(defaultHandleArr, Math.min(i2 << 1, this.maxCapacity));
                }
                this.elements[i2] = defaultHandle;
                this.size = i2 + 1;
                return;
            }
            throw new IllegalStateException("recycled already");
        }

        private void pushLater(DefaultHandle<?> defaultHandle, Thread thread) {
            if (this.maxDelayedQueues == 0) {
                return;
            }
            Map map = (Map) Recycler.DELAYED_RECYCLED.get();
            WeakOrderQueue weakOrderQueue = (WeakOrderQueue) map.get(this);
            if (weakOrderQueue == null) {
                if (map.size() >= this.maxDelayedQueues) {
                    map.put(this, WeakOrderQueue.DUMMY);
                    return;
                }
                weakOrderQueue = newWeakOrderQueue(thread);
                if (weakOrderQueue == null) {
                    return;
                } else {
                    map.put(this, weakOrderQueue);
                }
            } else if (weakOrderQueue == WeakOrderQueue.DUMMY) {
                return;
            }
            weakOrderQueue.add(defaultHandle);
        }

        private WeakOrderQueue newWeakOrderQueue(Thread thread) {
            return WeakOrderQueue.newQueue(this, thread);
        }

        boolean dropHandle(DefaultHandle<?> defaultHandle) {
            if (!defaultHandle.hasBeenRecycled) {
                int i = this.handleRecycleCount;
                if (i < this.interval) {
                    this.handleRecycleCount = i + 1;
                    return true;
                }
                this.handleRecycleCount = 0;
                defaultHandle.hasBeenRecycled = true;
            }
            return false;
        }

        DefaultHandle<T> newHandle() {
            return new DefaultHandle<>(this);
        }
    }
}
