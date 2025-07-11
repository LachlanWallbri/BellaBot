package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.math.IntMath;
import java.util.AbstractSet;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public abstract class AbstractDirectedNetworkConnections<N, E> implements NetworkConnections<N, E> {
    protected final Map<E, N> inEdgeMap;
    protected final Map<E, N> outEdgeMap;
    private int selfLoopCount;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractDirectedNetworkConnections(Map<E, N> map, Map<E, N> map2, int i) {
        this.inEdgeMap = (Map) Preconditions.checkNotNull(map);
        this.outEdgeMap = (Map) Preconditions.checkNotNull(map2);
        this.selfLoopCount = Graphs.checkNonNegative(i);
        Preconditions.checkState(i <= map.size() && i <= map2.size());
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<N> adjacentNodes() {
        return Sets.union(predecessors(), successors());
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> incidentEdges() {
        return new AbstractSet<E>() { // from class: com.google.common.graph.AbstractDirectedNetworkConnections.1
            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<E> iterator() {
                Iterable union;
                if (AbstractDirectedNetworkConnections.this.selfLoopCount == 0) {
                    union = Iterables.concat(AbstractDirectedNetworkConnections.this.inEdgeMap.keySet(), AbstractDirectedNetworkConnections.this.outEdgeMap.keySet());
                } else {
                    union = Sets.union(AbstractDirectedNetworkConnections.this.inEdgeMap.keySet(), AbstractDirectedNetworkConnections.this.outEdgeMap.keySet());
                }
                return Iterators.unmodifiableIterator(union.iterator());
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return IntMath.saturatedAdd(AbstractDirectedNetworkConnections.this.inEdgeMap.size(), AbstractDirectedNetworkConnections.this.outEdgeMap.size() - AbstractDirectedNetworkConnections.this.selfLoopCount);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                return AbstractDirectedNetworkConnections.this.inEdgeMap.containsKey(obj) || AbstractDirectedNetworkConnections.this.outEdgeMap.containsKey(obj);
            }
        };
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> inEdges() {
        return Collections.unmodifiableSet(this.inEdgeMap.keySet());
    }

    @Override // com.google.common.graph.NetworkConnections
    public Set<E> outEdges() {
        return Collections.unmodifiableSet(this.outEdgeMap.keySet());
    }

    @Override // com.google.common.graph.NetworkConnections
    public N adjacentNode(E e) {
        return (N) Preconditions.checkNotNull(this.outEdgeMap.get(e));
    }

    @Override // com.google.common.graph.NetworkConnections
    public N removeInEdge(E e, boolean z) {
        if (z) {
            int i = this.selfLoopCount - 1;
            this.selfLoopCount = i;
            Graphs.checkNonNegative(i);
        }
        return (N) Preconditions.checkNotNull(this.inEdgeMap.remove(e));
    }

    @Override // com.google.common.graph.NetworkConnections
    public N removeOutEdge(E e) {
        return (N) Preconditions.checkNotNull(this.outEdgeMap.remove(e));
    }

    @Override // com.google.common.graph.NetworkConnections
    public void addInEdge(E e, N n, boolean z) {
        Preconditions.checkNotNull(e);
        Preconditions.checkNotNull(n);
        if (z) {
            int i = this.selfLoopCount + 1;
            this.selfLoopCount = i;
            Graphs.checkPositive(i);
        }
        Preconditions.checkState(this.inEdgeMap.put(e, n) == null);
    }

    @Override // com.google.common.graph.NetworkConnections
    public void addOutEdge(E e, N n) {
        Preconditions.checkNotNull(e);
        Preconditions.checkNotNull(n);
        Preconditions.checkState(this.outEdgeMap.put(e, n) == null);
    }
}
