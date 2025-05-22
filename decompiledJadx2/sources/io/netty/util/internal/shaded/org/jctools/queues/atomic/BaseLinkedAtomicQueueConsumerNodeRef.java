package io.netty.util.internal.shaded.org.jctools.queues.atomic;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* compiled from: BaseLinkedAtomicQueue.java */
/* loaded from: classes.dex */
abstract class BaseLinkedAtomicQueueConsumerNodeRef<E> extends BaseLinkedAtomicQueuePad1<E> {
    private static final AtomicReferenceFieldUpdater<BaseLinkedAtomicQueueConsumerNodeRef, LinkedQueueAtomicNode> C_NODE_UPDATER = AtomicReferenceFieldUpdater.newUpdater(BaseLinkedAtomicQueueConsumerNodeRef.class, LinkedQueueAtomicNode.class, "consumerNode");
    protected volatile LinkedQueueAtomicNode<E> consumerNode;

    /* JADX INFO: Access modifiers changed from: protected */
    public final void spConsumerNode(LinkedQueueAtomicNode<E> linkedQueueAtomicNode) {
        C_NODE_UPDATER.lazySet(this, linkedQueueAtomicNode);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final LinkedQueueAtomicNode<E> lvConsumerNode() {
        return this.consumerNode;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final LinkedQueueAtomicNode<E> lpConsumerNode() {
        return this.consumerNode;
    }
}
