package io.netty.util;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.internal.MathUtil;
import io.netty.util.internal.ObjectCleaner;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public abstract class Recycler<T> {
    private static final int DEFAULT_INITIAL_MAX_CAPACITY_PER_THREAD = 4096;
    private static final int DEFAULT_MAX_CAPACITY_PER_THREAD;
    private static final FastThreadLocal<Map<Stack<?>, WeakOrderQueue>> DELAYED_RECYCLED;
    private static final AtomicInteger ID_GENERATOR;
    private static final int INITIAL_CAPACITY;
    private static final int LINK_CAPACITY;
    private static final int MAX_DELAYED_QUEUES_PER_THREAD;
    private static final int MAX_SHARED_CAPACITY_FACTOR;
    private static final int OWN_THREAD_ID;
    private static final int RATIO;
    private final int maxCapacityPerThread;
    private final int maxDelayedQueuesPerThread;
    private final int maxSharedCapacityFactor;
    private final int ratioMask;
    private final FastThreadLocal<Stack<T>> threadLocal;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) Recycler.class);
    private static final Handle NOOP_HANDLE = new Handle() { // from class: io.netty.util.Recycler.1
        @Override // io.netty.util.Recycler.Handle
        public void recycle(Object obj) {
        }
    };

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public interface Handle<T> {
        void recycle(T t);
    }

    protected abstract T newObject(Handle<T> handle);

    static {
        AtomicInteger atomicInteger = new AtomicInteger(Integer.MIN_VALUE);
        ID_GENERATOR = atomicInteger;
        OWN_THREAD_ID = atomicInteger.getAndIncrement();
        int i = SystemPropertyUtil.getInt("io.netty.recycler.maxCapacityPerThread", SystemPropertyUtil.getInt("io.netty.recycler.maxCapacity", 4096));
        DEFAULT_MAX_CAPACITY_PER_THREAD = i >= 0 ? i : 4096;
        MAX_SHARED_CAPACITY_FACTOR = Math.max(2, SystemPropertyUtil.getInt("io.netty.recycler.maxSharedCapacityFactor", 2));
        MAX_DELAYED_QUEUES_PER_THREAD = Math.max(0, SystemPropertyUtil.getInt("io.netty.recycler.maxDelayedQueuesPerThread", NettyRuntime.availableProcessors() * 2));
        LINK_CAPACITY = MathUtil.safeFindNextPositivePowerOfTwo(Math.max(SystemPropertyUtil.getInt("io.netty.recycler.linkCapacity", 16), 16));
        RATIO = MathUtil.safeFindNextPositivePowerOfTwo(SystemPropertyUtil.getInt("io.netty.recycler.ratio", 8));
        if (logger.isDebugEnabled()) {
            int i2 = DEFAULT_MAX_CAPACITY_PER_THREAD;
            if (i2 == 0) {
                logger.debug("-Dio.netty.recycler.maxCapacityPerThread: disabled");
                logger.debug("-Dio.netty.recycler.maxSharedCapacityFactor: disabled");
                logger.debug("-Dio.netty.recycler.linkCapacity: disabled");
                logger.debug("-Dio.netty.recycler.ratio: disabled");
            } else {
                logger.debug("-Dio.netty.recycler.maxCapacityPerThread: {}", Integer.valueOf(i2));
                logger.debug("-Dio.netty.recycler.maxSharedCapacityFactor: {}", Integer.valueOf(MAX_SHARED_CAPACITY_FACTOR));
                logger.debug("-Dio.netty.recycler.linkCapacity: {}", Integer.valueOf(LINK_CAPACITY));
                logger.debug("-Dio.netty.recycler.ratio: {}", Integer.valueOf(RATIO));
            }
        }
        INITIAL_CAPACITY = Math.min(DEFAULT_MAX_CAPACITY_PER_THREAD, 256);
        DELAYED_RECYCLED = new FastThreadLocal<Map<Stack<?>, WeakOrderQueue>>() { // from class: io.netty.util.Recycler.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // io.netty.util.concurrent.FastThreadLocal
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
        this.threadLocal = new FastThreadLocal<Stack<T>>() { // from class: io.netty.util.Recycler.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // io.netty.util.concurrent.FastThreadLocal
            public Stack<T> initialValue() {
                return new Stack<>(Recycler.this, Thread.currentThread(), Recycler.this.maxCapacityPerThread, Recycler.this.maxSharedCapacityFactor, Recycler.this.ratioMask, Recycler.this.maxDelayedQueuesPerThread);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // io.netty.util.concurrent.FastThreadLocal
            public void onRemoval(Stack<T> stack) {
                if (stack.threadRef.get() == Thread.currentThread() && Recycler.DELAYED_RECYCLED.isSet()) {
                    ((Map) Recycler.DELAYED_RECYCLED.get()).remove(stack);
                }
            }
        };
        this.ratioMask = MathUtil.safeFindNextPositivePowerOfTwo(i3) - 1;
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
            ((DefaultHandle) pop).value = newObject(pop);
        }
        return (T) ((DefaultHandle) pop).value;
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
        return ((Stack) this.threadLocal.get()).elements.length;
    }

    final int threadLocalSize() {
        return ((Stack) this.threadLocal.get()).size;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public static final class DefaultHandle<T> implements Handle<T> {
        boolean hasBeenRecycled;
        private int lastRecycledId;
        private int recycleId;
        private Stack<?> stack;
        private Object value;

        DefaultHandle(Stack<?> stack) {
            this.stack = stack;
        }

        @Override // io.netty.util.Recycler.Handle
        public void recycle(Object obj) {
            if (obj != this.value) {
                throw new IllegalArgumentException("object does not belong to handle");
            }
            this.stack.push(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public static final class WeakOrderQueue {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        static final WeakOrderQueue DUMMY = new WeakOrderQueue();
        private final Head head;

        /* renamed from: id */
        private final int f8577id;
        private WeakOrderQueue next;
        private final WeakReference<Thread> owner;
        private Link tail;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
          classes6.dex
          classes8.dex
         */
        /* loaded from: classes.dex */
        public static final class Link extends AtomicInteger {
            private final DefaultHandle<?>[] elements = new DefaultHandle[Recycler.LINK_CAPACITY];
            Link next;
            private int readIndex;

            Link() {
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
          classes6.dex
          classes8.dex
         */
        /* loaded from: classes.dex */
        public static final class Head implements Runnable {
            static final /* synthetic */ boolean $assertionsDisabled = false;
            private final AtomicInteger availableSharedCapacity;
            Link link;

            Head(AtomicInteger atomicInteger) {
                this.availableSharedCapacity = atomicInteger;
            }

            @Override // java.lang.Runnable
            public void run() {
                Link link = this.link;
                while (link != null) {
                    reclaimSpace(Recycler.LINK_CAPACITY);
                    link = this.link.next;
                }
            }

            void reclaimSpace(int i) {
                this.availableSharedCapacity.addAndGet(i);
            }

            boolean reserveSpace(int i) {
                return reserveSpace(this.availableSharedCapacity, i);
            }

            static boolean reserveSpace(AtomicInteger atomicInteger, int i) {
                int i2;
                do {
                    i2 = atomicInteger.get();
                    if (i2 < i) {
                        return false;
                    }
                } while (!atomicInteger.compareAndSet(i2, i2 - i));
                return true;
            }
        }

        private WeakOrderQueue() {
            this.f8577id = Recycler.ID_GENERATOR.getAndIncrement();
            this.owner = null;
            this.head = new Head(null);
        }

        private WeakOrderQueue(Stack<?> stack, Thread thread) {
            this.f8577id = Recycler.ID_GENERATOR.getAndIncrement();
            this.tail = new Link();
            Head head = new Head(stack.availableSharedCapacity);
            this.head = head;
            head.link = this.tail;
            this.owner = new WeakReference<>(thread);
        }

        static WeakOrderQueue newQueue(Stack<?> stack, Thread thread) {
            WeakOrderQueue weakOrderQueue = new WeakOrderQueue(stack, thread);
            stack.setHead(weakOrderQueue);
            ObjectCleaner.register(weakOrderQueue, weakOrderQueue.head);
            return weakOrderQueue;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNext(WeakOrderQueue weakOrderQueue) {
            this.next = weakOrderQueue;
        }

        static WeakOrderQueue allocate(Stack<?> stack, Thread thread) {
            if (Head.reserveSpace(stack.availableSharedCapacity, Recycler.LINK_CAPACITY)) {
                return newQueue(stack, thread);
            }
            return null;
        }

        void add(DefaultHandle<?> defaultHandle) {
            ((DefaultHandle) defaultHandle).lastRecycledId = this.f8577id;
            Link link = this.tail;
            int i = link.get();
            if (i == Recycler.LINK_CAPACITY) {
                if (!this.head.reserveSpace(Recycler.LINK_CAPACITY)) {
                    return;
                }
                Link link2 = new Link();
                link.next = link2;
                this.tail = link2;
                i = link2.get();
                link = link2;
            }
            link.elements[i] = defaultHandle;
            ((DefaultHandle) defaultHandle).stack = null;
            link.lazySet(i + 1);
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
                Head head = this.head;
                link = link.next;
                head.link = link;
            }
            int i = link.readIndex;
            int i2 = link.get();
            int i3 = i2 - i;
            if (i3 == 0) {
                return false;
            }
            int i4 = ((Stack) stack).size;
            int i5 = i3 + i4;
            if (i5 > ((Stack) stack).elements.length) {
                i2 = Math.min((stack.increaseCapacity(i5) + i) - i4, i2);
            }
            if (i == i2) {
                return false;
            }
            DefaultHandle<?>[] defaultHandleArr = link.elements;
            DefaultHandle[] defaultHandleArr2 = ((Stack) stack).elements;
            while (i < i2) {
                DefaultHandle<?> defaultHandle = defaultHandleArr[i];
                if (((DefaultHandle) defaultHandle).recycleId == 0) {
                    ((DefaultHandle) defaultHandle).recycleId = ((DefaultHandle) defaultHandle).lastRecycledId;
                } else if (((DefaultHandle) defaultHandle).recycleId != ((DefaultHandle) defaultHandle).lastRecycledId) {
                    throw new IllegalStateException("recycled already");
                }
                defaultHandleArr[i] = null;
                if (!stack.dropHandle(defaultHandle)) {
                    ((DefaultHandle) defaultHandle).stack = stack;
                    defaultHandleArr2[i4] = defaultHandle;
                    i4++;
                }
                i++;
            }
            if (i2 == Recycler.LINK_CAPACITY && link.next != null) {
                this.head.reclaimSpace(Recycler.LINK_CAPACITY);
                this.head.link = link.next;
            }
            link.readIndex = i2;
            if (((Stack) stack).size == i4) {
                return false;
            }
            ((Stack) stack).size = i4;
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public static final class Stack<T> {
        final AtomicInteger availableSharedCapacity;
        private WeakOrderQueue cursor;
        private DefaultHandle<?>[] elements;
        private int handleRecycleCount = -1;
        private volatile WeakOrderQueue head;
        private final int maxCapacity;
        final int maxDelayedQueues;
        final Recycler<T> parent;
        private WeakOrderQueue prev;
        private final int ratioMask;
        private int size;
        final WeakReference<Thread> threadRef;

        Stack(Recycler<T> recycler, Thread thread, int i, int i2, int i3, int i4) {
            this.parent = recycler;
            this.threadRef = new WeakReference<>(thread);
            this.maxCapacity = i;
            this.availableSharedCapacity = new AtomicInteger(Math.max(i / i2, Recycler.LINK_CAPACITY));
            this.elements = new DefaultHandle[Math.min(Recycler.INITIAL_CAPACITY, i)];
            this.ratioMask = i3;
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
            if (i == 0) {
                if (!scavenge()) {
                    return null;
                }
                i = this.size;
            }
            int i2 = i - 1;
            Object[] objArr = this.elements;
            DefaultHandle<T> defaultHandle = (DefaultHandle<T>) objArr[i2];
            objArr[i2] = null;
            if (((DefaultHandle) defaultHandle).lastRecycledId == ((DefaultHandle) defaultHandle).recycleId) {
                ((DefaultHandle) defaultHandle).recycleId = 0;
                ((DefaultHandle) defaultHandle).lastRecycledId = 0;
                this.size = i2;
                return defaultHandle;
            }
            throw new IllegalStateException("recycled multiple times");
        }

        boolean scavenge() {
            if (scavengeSome()) {
                return true;
            }
            this.prev = null;
            this.cursor = this.head;
            return false;
        }

        boolean scavengeSome() {
            WeakOrderQueue weakOrderQueue;
            WeakOrderQueue weakOrderQueue2;
            boolean z;
            WeakOrderQueue weakOrderQueue3;
            WeakOrderQueue weakOrderQueue4 = this.cursor;
            boolean z2 = false;
            if (weakOrderQueue4 == null) {
                weakOrderQueue2 = null;
                weakOrderQueue = this.head;
                if (weakOrderQueue == null) {
                    return false;
                }
            } else {
                weakOrderQueue = weakOrderQueue4;
                weakOrderQueue2 = this.prev;
            }
            while (true) {
                z = true;
                if (weakOrderQueue.transfer(this)) {
                    break;
                }
                weakOrderQueue3 = weakOrderQueue.next;
                if (weakOrderQueue.owner.get() == null) {
                    if (weakOrderQueue.hasFinalData()) {
                        while (weakOrderQueue.transfer(this)) {
                            z2 = true;
                        }
                    }
                    if (weakOrderQueue2 != null) {
                        weakOrderQueue2.setNext(weakOrderQueue3);
                    }
                } else {
                    weakOrderQueue2 = weakOrderQueue;
                }
                if (weakOrderQueue3 == null || z2) {
                    break;
                }
                weakOrderQueue = weakOrderQueue3;
            }
            z = z2;
            weakOrderQueue = weakOrderQueue3;
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
            if ((((DefaultHandle) defaultHandle).recycleId | ((DefaultHandle) defaultHandle).lastRecycledId) == 0) {
                ((DefaultHandle) defaultHandle).recycleId = ((DefaultHandle) defaultHandle).lastRecycledId = Recycler.OWN_THREAD_ID;
                int i = this.size;
                if (i >= this.maxCapacity || dropHandle(defaultHandle)) {
                    return;
                }
                DefaultHandle<?>[] defaultHandleArr = this.elements;
                if (i == defaultHandleArr.length) {
                    this.elements = (DefaultHandle[]) Arrays.copyOf(defaultHandleArr, Math.min(i << 1, this.maxCapacity));
                }
                this.elements[i] = defaultHandle;
                this.size = i + 1;
                return;
            }
            throw new IllegalStateException("recycled already");
        }

        private void pushLater(DefaultHandle<?> defaultHandle, Thread thread) {
            Map map = (Map) Recycler.DELAYED_RECYCLED.get();
            WeakOrderQueue weakOrderQueue = (WeakOrderQueue) map.get(this);
            if (weakOrderQueue == null) {
                if (map.size() >= this.maxDelayedQueues) {
                    map.put(this, WeakOrderQueue.DUMMY);
                    return;
                }
                weakOrderQueue = WeakOrderQueue.allocate(this, thread);
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

        boolean dropHandle(DefaultHandle<?> defaultHandle) {
            if (defaultHandle.hasBeenRecycled) {
                return false;
            }
            int i = this.handleRecycleCount + 1;
            this.handleRecycleCount = i;
            if ((i & this.ratioMask) != 0) {
                return true;
            }
            defaultHandle.hasBeenRecycled = true;
            return false;
        }

        DefaultHandle<T> newHandle() {
            return new DefaultHandle<>(this);
        }
    }
}
