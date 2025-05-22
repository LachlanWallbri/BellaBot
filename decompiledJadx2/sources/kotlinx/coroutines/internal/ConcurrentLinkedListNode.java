package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: ConcurrentLinkedList.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\r\n\u0002\u0010\u0000\n\u0002\b\t\b \u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u00002\u00020\u001aB\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0003\u0010\u0004J\r\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\t\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ \u0010\u000e\u001a\u0004\u0018\u00018\u00002\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0086\b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u0005¢\u0006\u0004\b\u0010\u0010\u0007J\u0015\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00028\u0000¢\u0006\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0014\u001a\u00020\b8F@\u0006¢\u0006\u0006\u001a\u0004\b\u0014\u0010\nR\u0018\u0010\u0017\u001a\u0004\u0018\u00018\u00008B@\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0015\u0010\u0019\u001a\u0004\u0018\u00018\u00008F@\u0006¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0016R\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u001a8B@\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0015\u0010\u0002\u001a\u0004\u0018\u00018\u00008F@\u0006¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0016R\u0016\u0010 \u001a\u00020\b8&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\nR\u0016\u0010\"\u001a\u00028\u00008B@\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u0016¨\u0006#"}, m3961d2 = {"Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "N", "prev", "<init>", "(Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;)V", "", "cleanPrev", "()V", "", "markAsClosed", "()Z", "Lkotlin/Function0;", "", "onClosedAction", "nextOrIfClosed", "(Lkotlin/jvm/functions/Function0;)Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "remove", ES6Iterator.VALUE_PROPERTY, "trySetNext", "(Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;)Z", "isTail", "getLeftmostAliveNode", "()Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "leftmostAliveNode", "getNext", ES6Iterator.NEXT_METHOD, "", "getNextOrClosed", "()Ljava/lang/Object;", "nextOrClosed", "getPrev", "getRemoved", "removed", "getRightmostAliveNode", "rightmostAliveNode", "kotlinx-coroutines-core"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public abstract class ConcurrentLinkedListNode<N extends ConcurrentLinkedListNode<N>> {
    private static final AtomicReferenceFieldUpdater _next$FU = AtomicReferenceFieldUpdater.newUpdater(ConcurrentLinkedListNode.class, Object.class, "_next");
    private static final AtomicReferenceFieldUpdater _prev$FU = AtomicReferenceFieldUpdater.newUpdater(ConcurrentLinkedListNode.class, Object.class, "_prev");
    private volatile Object _next = null;
    private volatile Object _prev;

    public abstract boolean getRemoved();

    public ConcurrentLinkedListNode(N n) {
        this._prev = n;
    }

    /* renamed from: getNextOrClosed, reason: from getter */
    public final Object get_next() {
        return this._next;
    }

    public final N nextOrIfClosed(Function0 onClosedAction) {
        Object obj = get_next();
        if (obj == ConcurrentLinkedListKt.CLOSED) {
            onClosedAction.invoke();
            throw null;
        }
        return (N) obj;
    }

    public final boolean trySetNext(N r3) {
        return _next$FU.compareAndSet(this, null, r3);
    }

    public final boolean isTail() {
        return getNext() == null;
    }

    public final N getPrev() {
        return (N) this._prev;
    }

    public final void cleanPrev() {
        _prev$FU.lazySet(this, null);
    }

    public final boolean markAsClosed() {
        return _next$FU.compareAndSet(this, null, ConcurrentLinkedListKt.CLOSED);
    }

    public final void remove() {
        if (DebugKt.getASSERTIONS_ENABLED() && !getRemoved()) {
            throw new AssertionError();
        }
        if (DebugKt.getASSERTIONS_ENABLED() && !(!isTail())) {
            throw new AssertionError();
        }
        while (true) {
            N leftmostAliveNode = getLeftmostAliveNode();
            N rightmostAliveNode = getRightmostAliveNode();
            rightmostAliveNode._prev = leftmostAliveNode;
            if (leftmostAliveNode != null) {
                leftmostAliveNode._next = rightmostAliveNode;
            }
            if (!rightmostAliveNode.getRemoved() && (leftmostAliveNode == null || !leftmostAliveNode.getRemoved())) {
                return;
            }
        }
    }

    private final N getLeftmostAliveNode() {
        N prev = getPrev();
        while (prev != null && prev.getRemoved()) {
            prev = (N) prev._prev;
        }
        return prev;
    }

    private final N getRightmostAliveNode() {
        if (DebugKt.getASSERTIONS_ENABLED() && !(!isTail())) {
            throw new AssertionError();
        }
        N next = getNext();
        if (next == null) {
            Intrinsics.throwNpe();
        }
        while (next.getRemoved()) {
            next = (N) next.getNext();
            if (next == null) {
                Intrinsics.throwNpe();
            }
        }
        return next;
    }

    public final N getNext() {
        Object obj = get_next();
        if (obj == ConcurrentLinkedListKt.CLOSED) {
            return null;
        }
        return (N) obj;
    }
}
