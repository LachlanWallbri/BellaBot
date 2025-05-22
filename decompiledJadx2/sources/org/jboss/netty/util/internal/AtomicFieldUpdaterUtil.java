package org.jboss.netty.util.internal;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import org.mozilla.javascript.ES6Iterator;

/* loaded from: classes7.dex */
final class AtomicFieldUpdaterUtil {
    private static final boolean AVAILABLE;

    /* loaded from: classes7.dex */
    static final class Node {
        volatile Node next;

        Node() {
        }
    }

    static {
        boolean z;
        Node node;
        try {
            AtomicReferenceFieldUpdater newUpdater = AtomicReferenceFieldUpdater.newUpdater(Node.class, Node.class, ES6Iterator.NEXT_METHOD);
            node = new Node();
            newUpdater.set(node, node);
        } catch (Throwable unused) {
            z = false;
        }
        if (node.next != node) {
            throw new Exception();
        }
        z = true;
        AVAILABLE = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, V> AtomicReferenceFieldUpdater<T, V> newRefUpdater(Class<T> cls, Class<V> cls2, String str) {
        if (AVAILABLE) {
            return AtomicReferenceFieldUpdater.newUpdater(cls, cls2, str);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> AtomicIntegerFieldUpdater<T> newIntUpdater(Class<T> cls, String str) {
        if (AVAILABLE) {
            return AtomicIntegerFieldUpdater.newUpdater(cls, str);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isAvailable() {
        return AVAILABLE;
    }

    private AtomicFieldUpdaterUtil() {
    }
}
