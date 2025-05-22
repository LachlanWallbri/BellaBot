package org.jboss.netty.util.internal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import org.mozilla.javascript.ES6Iterator;
import sun.misc.Unsafe;

/* loaded from: classes7.dex */
public class LinkedTransferQueue<E> extends AbstractQueue<E> implements BlockingQueue<E>, Serializable {
    private static final int ASYNC = 1;
    private static final int CHAINED_SPINS = 64;
    private static final int FRONT_SPINS = 128;

    /* renamed from: MP */
    private static final boolean f10056MP;
    private static final int NOW = 0;
    static final int SWEEP_THRESHOLD = 32;
    private static final int SYNC = 2;
    private static final int TIMED = 3;
    private static final Unsafe UNSAFE;
    private static final long headOffset;
    private static final long serialVersionUID = -3223113410248163686L;
    private static final long sweepVotesOffset;
    private static final long tailOffset;
    volatile transient Node head;
    private volatile transient int sweepVotes;
    private volatile transient Node tail;

    /* JADX WARN: Multi-variable type inference failed */
    static <E> E cast(Object obj) {
        return obj;
    }

    @Override // java.util.concurrent.BlockingQueue
    public int remainingCapacity() {
        return Integer.MAX_VALUE;
    }

    static {
        f10056MP = Runtime.getRuntime().availableProcessors() > 1;
        try {
            UNSAFE = getUnsafe();
            headOffset = UNSAFE.objectFieldOffset(LinkedTransferQueue.class.getDeclaredField("head"));
            tailOffset = UNSAFE.objectFieldOffset(LinkedTransferQueue.class.getDeclaredField("tail"));
            sweepVotesOffset = UNSAFE.objectFieldOffset(LinkedTransferQueue.class.getDeclaredField("sweepVotes"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public static final class Node {
        private static final Unsafe UNSAFE;
        private static final long itemOffset;
        private static final long nextOffset;
        private static final long waiterOffset;
        final boolean isData;
        volatile Object item;
        volatile Node next;
        volatile Thread waiter;

        final boolean casNext(Node node, Node node2) {
            return UNSAFE.compareAndSwapObject(this, nextOffset, node, node2);
        }

        final boolean casItem(Object obj, Object obj2) {
            return UNSAFE.compareAndSwapObject(this, itemOffset, obj, obj2);
        }

        Node(Object obj, boolean z) {
            UNSAFE.putObject(this, itemOffset, obj);
            this.isData = z;
        }

        final void forgetNext() {
            UNSAFE.putObject(this, nextOffset, this);
        }

        final void forgetContents() {
            UNSAFE.putObject(this, itemOffset, this);
            UNSAFE.putObject(this, waiterOffset, (Object) null);
        }

        final boolean isMatched() {
            Object obj = this.item;
            if (obj != this) {
                if ((obj == null) != this.isData) {
                    return false;
                }
            }
            return true;
        }

        final boolean isUnmatchedRequest() {
            return !this.isData && this.item == null;
        }

        final boolean cannotPrecede(boolean z) {
            Object obj;
            boolean z2 = this.isData;
            if (z2 != z && (obj = this.item) != this) {
                if ((obj != null) == z2) {
                    return true;
                }
            }
            return false;
        }

        final boolean tryMatchData() {
            Object obj = this.item;
            if (obj == null || obj == this || !casItem(obj, null)) {
                return false;
            }
            LockSupport.unpark(this.waiter);
            return true;
        }

        static {
            try {
                UNSAFE = LinkedTransferQueue.getUnsafe();
                itemOffset = UNSAFE.objectFieldOffset(Node.class.getDeclaredField("item"));
                nextOffset = UNSAFE.objectFieldOffset(Node.class.getDeclaredField(ES6Iterator.NEXT_METHOD));
                waiterOffset = UNSAFE.objectFieldOffset(Node.class.getDeclaredField("waiter"));
            } catch (Exception e) {
                throw new Error(e);
            }
        }
    }

    private boolean casTail(Node node, Node node2) {
        return UNSAFE.compareAndSwapObject(this, tailOffset, node, node2);
    }

    private boolean casHead(Node node, Node node2) {
        return UNSAFE.compareAndSwapObject(this, headOffset, node, node2);
    }

    private boolean casSweepVotes(int i, int i2) {
        return UNSAFE.compareAndSwapInt(this, sweepVotesOffset, i, i2);
    }

    private E xfer(E e, boolean z, int i, long j) {
        if (z && e == null) {
            throw new NullPointerException();
        }
        Node node = null;
        while (true) {
            Node node2 = this.head;
            while (true) {
                Node node3 = node2;
                while (true) {
                    if (node2 == null) {
                        break;
                    }
                    boolean z2 = node2.isData;
                    Object obj = node2.item;
                    if (obj != node2) {
                        if ((obj != null) == z2) {
                            if (z2 == z) {
                                break;
                            }
                            if (node2.casItem(obj, e)) {
                                Node node4 = node2;
                                while (true) {
                                    if (node4 == node3) {
                                        break;
                                    }
                                    Node node5 = node4.next;
                                    if (this.head == node3) {
                                        if (node5 != null) {
                                            node4 = node5;
                                        }
                                        if (casHead(node3, node4)) {
                                            node3.forgetNext();
                                            break;
                                        }
                                    }
                                    node3 = this.head;
                                    if (node3 == null || (node4 = node3.next) == null || !node4.isMatched()) {
                                        break;
                                    }
                                }
                                LockSupport.unpark(node2.waiter);
                                return (E) cast(obj);
                            }
                        }
                    }
                    Node node6 = node2.next;
                    if (node2 != node6) {
                        node2 = node6;
                    }
                }
                node2 = this.head;
            }
            if (i == 0) {
                break;
            }
            if (node == null) {
                node = new Node(e, z);
            }
            Node tryAppend = tryAppend(node, z);
            if (tryAppend != null) {
                if (i != 1) {
                    return awaitMatch(node, tryAppend, e, i == 3, j);
                }
            }
        }
        return e;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0033, code lost:
    
        if (r0 != r1) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0037, code lost:
    
        if (r5.tail != r1) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003d, code lost:
    
        if (casTail(r1, r6) != false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x003f, code lost:
    
        r1 = r5.tail;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0041, code lost:
    
        if (r1 == null) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0043, code lost:
    
        r6 = r1.next;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0045, code lost:
    
        if (r6 == null) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0047, code lost:
    
        r6 = r6.next;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0049, code lost:
    
        if (r6 == null) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x004b, code lost:
    
        if (r6 == r1) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x004e, code lost:
    
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private Node tryAppend(Node node, boolean z) {
        Node node2;
        Node node3 = this.tail;
        Node node4 = node3;
        while (true) {
            Node node5 = null;
            if (node3 == null && (node3 = this.head) == null) {
                if (casHead(null, node)) {
                    return node;
                }
            } else {
                if (node3.cannotPrecede(z)) {
                    return null;
                }
                Node node6 = node3.next;
                if (node6 != null) {
                    if (node3 != node4 && node4 != (node2 = this.tail)) {
                        node4 = node2;
                        node5 = node4;
                    } else if (node3 != node6) {
                        node5 = node6;
                    }
                    node3 = node5;
                } else if (!node3.casNext(null, node)) {
                    node3 = node3.next;
                }
            }
        }
    }

    private E awaitMatch(Node node, Node node2, E e, boolean z, long j) {
        long nanoTime = z ? System.nanoTime() : 0L;
        Thread currentThread = Thread.currentThread();
        ThreadLocalRandom threadLocalRandom = null;
        int i = -1;
        while (true) {
            Object obj = node.item;
            if (obj != e) {
                node.forgetContents();
                return (E) cast(obj);
            }
            if ((currentThread.isInterrupted() || (z && j <= 0)) && node.casItem(e, node)) {
                unsplice(node2, node);
                return e;
            }
            if (i < 0) {
                i = spinsFor(node2, node.isData);
                if (i > 0) {
                    threadLocalRandom = ThreadLocalRandom.current();
                }
            } else if (i > 0) {
                i--;
                if (threadLocalRandom.nextInt(64) == 0) {
                    Thread.yield();
                }
            } else if (node.waiter == null) {
                node.waiter = currentThread;
            } else if (z) {
                long nanoTime2 = System.nanoTime();
                j -= nanoTime2 - nanoTime;
                if (j > 0) {
                    LockSupport.parkNanos(j);
                }
                nanoTime = nanoTime2;
            } else {
                LockSupport.park();
            }
        }
    }

    private static int spinsFor(Node node, boolean z) {
        if (!f10056MP || node == null) {
            return 0;
        }
        if (node.isData != z) {
            return 192;
        }
        if (node.isMatched()) {
            return 128;
        }
        return node.waiter == null ? 64 : 0;
    }

    final Node succ(Node node) {
        Node node2 = node.next;
        return node == node2 ? this.head : node2;
    }

    private Node firstOfMode(boolean z) {
        Node node = this.head;
        while (node != null) {
            if (node.isMatched()) {
                node = succ(node);
            } else {
                if (node.isData == z) {
                    return node;
                }
                return null;
            }
        }
        return null;
    }

    private E firstDataItem() {
        Node node = this.head;
        while (node != null) {
            Object obj = node.item;
            if (node.isData) {
                if (obj != null && obj != node) {
                    return (E) cast(obj);
                }
            } else if (obj == null) {
                return null;
            }
            node = succ(node);
        }
        return null;
    }

    private int countOfMode(boolean z) {
        int i;
        Node node = this.head;
        loop0: while (true) {
            i = 0;
            while (node != null) {
                if (!node.isMatched()) {
                    if (node.isData == z) {
                        i++;
                        if (i == Integer.MAX_VALUE) {
                            break loop0;
                        }
                    } else {
                        return 0;
                    }
                }
                Node node2 = node.next;
                if (node2 != node) {
                    node = node2;
                }
            }
            node = this.head;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public final class Itr implements Iterator<E> {
        private Node lastPred;
        private Node lastRet;
        private E nextItem;
        private Node nextNode;

        private void advance(Node node) {
            Node node2;
            Node node3 = this.lastRet;
            if (node3 != null && !node3.isMatched()) {
                this.lastPred = node3;
            } else {
                Node node4 = this.lastPred;
                if (node4 != null && !node4.isMatched()) {
                    while (true) {
                        Node node5 = node4.next;
                        if (node5 == null || node5 == node4 || !node5.isMatched() || (node2 = node5.next) == null || node2 == node5) {
                            break;
                        } else {
                            node4.casNext(node5, node2);
                        }
                    }
                } else {
                    this.lastPred = null;
                }
            }
            this.lastRet = node;
            while (true) {
                Node node6 = node == null ? LinkedTransferQueue.this.head : node.next;
                if (node6 == null) {
                    break;
                }
                if (node6 != node) {
                    Object obj = node6.item;
                    if (!node6.isData) {
                        if (obj == null) {
                            break;
                        }
                    } else if (obj != null && obj != node6) {
                        this.nextItem = (E) LinkedTransferQueue.cast(obj);
                        this.nextNode = node6;
                        return;
                    }
                    if (node == null) {
                        node = node6;
                    } else {
                        Node node7 = node6.next;
                        if (node7 == null) {
                            break;
                        } else if (node6 != node7) {
                            node.casNext(node6, node7);
                        }
                    }
                }
                node = null;
            }
            this.nextNode = null;
            this.nextItem = null;
        }

        Itr() {
            advance(null);
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.nextNode != null;
        }

        @Override // java.util.Iterator
        public final E next() {
            Node node = this.nextNode;
            if (node == null) {
                throw new NoSuchElementException();
            }
            E e = this.nextItem;
            advance(node);
            return e;
        }

        @Override // java.util.Iterator
        public final void remove() {
            Node node = this.lastRet;
            if (node == null) {
                throw new IllegalStateException();
            }
            this.lastRet = null;
            if (node.tryMatchData()) {
                LinkedTransferQueue.this.unsplice(this.lastPred, node);
            }
        }
    }

    final void unsplice(Node node, Node node2) {
        node2.forgetContents();
        if (node == null || node == node2 || node.next != node2) {
            return;
        }
        Node node3 = node2.next;
        if (node3 != null && (node3 == node2 || !node.casNext(node2, node3) || !node.isMatched())) {
            return;
        }
        while (true) {
            Node node4 = this.head;
            if (node4 == node || node4 == node2 || node4 == null) {
                return;
            }
            if (node4.isMatched()) {
                Node node5 = node4.next;
                if (node5 == null) {
                    return;
                }
                if (node5 != node4 && casHead(node4, node5)) {
                    node4.forgetNext();
                }
            } else {
                if (node.next == node || node2.next == node2) {
                    return;
                }
                while (true) {
                    int i = this.sweepVotes;
                    if (i < 32) {
                        if (casSweepVotes(i, i + 1)) {
                            return;
                        }
                    } else if (casSweepVotes(i, 0)) {
                        sweep();
                        return;
                    }
                }
            }
        }
    }

    private void sweep() {
        Node node = this.head;
        while (node != null) {
            Node node2 = node.next;
            if (node2 == null) {
                return;
            }
            if (node2.isMatched()) {
                Node node3 = node2.next;
                if (node3 == null) {
                    return;
                }
                if (node2 == node3) {
                    node = this.head;
                } else {
                    node.casNext(node2, node3);
                }
            } else {
                node = node2;
            }
        }
    }

    private boolean findAndRemove(Object obj) {
        if (obj == null) {
            return false;
        }
        Node node = this.head;
        while (true) {
            Node node2 = null;
            while (node != null) {
                Object obj2 = node.item;
                if (node.isData) {
                    if (obj2 != null && obj2 != node && obj.equals(obj2) && node.tryMatchData()) {
                        unsplice(node2, node);
                        return true;
                    }
                } else if (obj2 == null) {
                    return false;
                }
                Node node3 = node.next;
                if (node3 == node) {
                    break;
                }
                node2 = node;
                node = node3;
            }
            return false;
            node = this.head;
        }
    }

    public LinkedTransferQueue() {
    }

    public LinkedTransferQueue(Collection<? extends E> collection) {
        this();
        addAll(collection);
    }

    @Override // java.util.concurrent.BlockingQueue
    public void put(E e) {
        xfer(e, true, 1, 0L);
    }

    @Override // java.util.concurrent.BlockingQueue
    public boolean offer(E e, long j, TimeUnit timeUnit) {
        xfer(e, true, 1, 0L);
        return true;
    }

    @Override // java.util.Queue, java.util.concurrent.BlockingQueue
    public boolean offer(E e) {
        xfer(e, true, 1, 0L);
        return true;
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection, java.util.Queue, java.util.concurrent.BlockingQueue
    public boolean add(E e) {
        xfer(e, true, 1, 0L);
        return true;
    }

    public boolean tryTransfer(E e) {
        return xfer(e, true, 0, 0L) == null;
    }

    public void transfer(E e) throws InterruptedException {
        if (xfer(e, true, 2, 0L) == null) {
            return;
        }
        Thread.interrupted();
        throw new InterruptedException();
    }

    public boolean tryTransfer(E e, long j, TimeUnit timeUnit) throws InterruptedException {
        if (xfer(e, true, 3, timeUnit.toNanos(j)) == null) {
            return true;
        }
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        return false;
    }

    @Override // java.util.concurrent.BlockingQueue
    public E take() throws InterruptedException {
        E xfer = xfer(null, false, 2, 0L);
        if (xfer != null) {
            return xfer;
        }
        Thread.interrupted();
        throw new InterruptedException();
    }

    @Override // java.util.concurrent.BlockingQueue
    public E poll(long j, TimeUnit timeUnit) throws InterruptedException {
        E xfer = xfer(null, false, 3, timeUnit.toNanos(j));
        if (xfer == null && Thread.interrupted()) {
            throw new InterruptedException();
        }
        return xfer;
    }

    @Override // java.util.Queue
    public E poll() {
        return xfer(null, false, 0, 0L);
    }

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> collection) {
        if (collection == null) {
            throw new NullPointerException();
        }
        if (collection == this) {
            throw new IllegalArgumentException();
        }
        int i = 0;
        while (true) {
            E poll = poll();
            if (poll == null) {
                return i;
            }
            collection.add(poll);
            i++;
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> collection, int i) {
        if (collection == null) {
            throw new NullPointerException();
        }
        if (collection == this) {
            throw new IllegalArgumentException();
        }
        int i2 = 0;
        while (i2 < i) {
            E poll = poll();
            if (poll == null) {
                break;
            }
            collection.add(poll);
            i2++;
        }
        return i2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override // java.util.Queue
    public E peek() {
        return firstDataItem();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        Node node = this.head;
        while (node != null) {
            if (node.isMatched()) {
                node = succ(node);
            } else {
                return !node.isData;
            }
        }
        return true;
    }

    public boolean hasWaitingConsumer() {
        return firstOfMode(false) != null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return countOfMode(true);
    }

    public int getWaitingConsumerCount() {
        return countOfMode(false);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingQueue
    public boolean remove(Object obj) {
        return findAndRemove(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingQueue
    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        Node node = this.head;
        while (node != null) {
            Object obj2 = node.item;
            if (node.isData) {
                if (obj2 != null && obj2 != node && obj.equals(obj2)) {
                    return true;
                }
            } else if (obj2 == null) {
                break;
            }
            node = succ(node);
        }
        return false;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            objectOutputStream.writeObject(it.next());
        }
        objectOutputStream.writeObject(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        while (true) {
            Object readObject = objectInputStream.readObject();
            if (readObject == null) {
                return;
            } else {
                offer(readObject);
            }
        }
    }

    static Unsafe getUnsafe() {
        try {
            try {
                return Unsafe.getUnsafe();
            } catch (SecurityException unused) {
                return (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() { // from class: org.jboss.netty.util.internal.LinkedTransferQueue.1
                    @Override // java.security.PrivilegedExceptionAction
                    public Unsafe run() throws Exception {
                        Field declaredField = Unsafe.class.getDeclaredField("theUnsafe");
                        declaredField.setAccessible(true);
                        return (Unsafe) declaredField.get(null);
                    }
                });
            }
        } catch (PrivilegedActionException e) {
            throw new RuntimeException("Could not initialize intrinsics", e.getCause());
        }
    }
}
