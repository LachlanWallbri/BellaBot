package com.google.common.graph;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public abstract class AbstractValueGraph<N, V> extends AbstractBaseGraph<N> implements ValueGraph<N, V> {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public /* bridge */ /* synthetic */ int degree(Object obj) {
        return super.degree(obj);
    }

    @Override // com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public /* bridge */ /* synthetic */ Set edges() {
        return super.edges();
    }

    @Override // com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public /* bridge */ /* synthetic */ boolean hasEdgeConnecting(EndpointPair endpointPair) {
        return super.hasEdgeConnecting(endpointPair);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public /* bridge */ /* synthetic */ boolean hasEdgeConnecting(Object obj, Object obj2) {
        return super.hasEdgeConnecting(obj, obj2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public /* bridge */ /* synthetic */ int inDegree(Object obj) {
        return super.inDegree(obj);
    }

    @Override // com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public /* bridge */ /* synthetic */ ElementOrder incidentEdgeOrder() {
        return super.incidentEdgeOrder();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public /* bridge */ /* synthetic */ Set incidentEdges(Object obj) {
        return super.incidentEdges(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public /* bridge */ /* synthetic */ int outDegree(Object obj) {
        return super.outDegree(obj);
    }

    @Override // com.google.common.graph.ValueGraph
    public Graph<N> asGraph() {
        return new AbstractGraph<N>() { // from class: com.google.common.graph.AbstractValueGraph.1
            @Override // com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction, com.google.common.graph.Graph
            public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
                return predecessors((C29721) obj);
            }

            @Override // com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
            public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
                return successors((C29721) obj);
            }

            @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
            public Set<N> nodes() {
                return AbstractValueGraph.this.nodes();
            }

            @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
            public Set<EndpointPair<N>> edges() {
                return AbstractValueGraph.this.edges();
            }

            @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
            public boolean isDirected() {
                return AbstractValueGraph.this.isDirected();
            }

            @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
            public boolean allowsSelfLoops() {
                return AbstractValueGraph.this.allowsSelfLoops();
            }

            @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
            public ElementOrder<N> nodeOrder() {
                return AbstractValueGraph.this.nodeOrder();
            }

            @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
            public ElementOrder<N> incidentEdgeOrder() {
                return AbstractValueGraph.this.incidentEdgeOrder();
            }

            @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
            public Set<N> adjacentNodes(N n) {
                return AbstractValueGraph.this.adjacentNodes(n);
            }

            @Override // com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction, com.google.common.graph.Graph
            public Set<N> predecessors(N n) {
                return AbstractValueGraph.this.predecessors((Object) n);
            }

            @Override // com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
            public Set<N> successors(N n) {
                return AbstractValueGraph.this.successors((Object) n);
            }

            @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
            public int degree(N n) {
                return AbstractValueGraph.this.degree(n);
            }

            @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
            public int inDegree(N n) {
                return AbstractValueGraph.this.inDegree(n);
            }

            @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
            public int outDegree(N n) {
                return AbstractValueGraph.this.outDegree(n);
            }
        };
    }

    @Override // com.google.common.graph.ValueGraph
    public Optional<V> edgeValue(N n, N n2) {
        return Optional.ofNullable(edgeValueOrDefault(n, n2, null));
    }

    @Override // com.google.common.graph.ValueGraph
    public Optional<V> edgeValue(EndpointPair<N> endpointPair) {
        return Optional.ofNullable(edgeValueOrDefault(endpointPair, null));
    }

    @Override // com.google.common.graph.ValueGraph
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ValueGraph)) {
            return false;
        }
        ValueGraph valueGraph = (ValueGraph) obj;
        return isDirected() == valueGraph.isDirected() && nodes().equals(valueGraph.nodes()) && edgeValueMap(this).equals(edgeValueMap(valueGraph));
    }

    @Override // com.google.common.graph.ValueGraph
    public final int hashCode() {
        return edgeValueMap(this).hashCode();
    }

    public String toString() {
        boolean isDirected = isDirected();
        boolean allowsSelfLoops = allowsSelfLoops();
        String valueOf = String.valueOf(nodes());
        String valueOf2 = String.valueOf(edgeValueMap(this));
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 59 + String.valueOf(valueOf2).length());
        sb.append("isDirected: ");
        sb.append(isDirected);
        sb.append(", allowsSelfLoops: ");
        sb.append(allowsSelfLoops);
        sb.append(", nodes: ");
        sb.append(valueOf);
        sb.append(", edges: ");
        sb.append(valueOf2);
        return sb.toString();
    }

    private static <N, V> Map<EndpointPair<N>, V> edgeValueMap(final ValueGraph<N, V> valueGraph) {
        return Maps.asMap(valueGraph.edges(), new Function<EndpointPair<N>, V>() { // from class: com.google.common.graph.AbstractValueGraph.2
            @Override // com.google.common.base.Function, java.util.function.Function
            public V apply(EndpointPair<N> endpointPair) {
                return (V) ValueGraph.this.edgeValueOrDefault(endpointPair.nodeU(), endpointPair.nodeV(), null);
            }
        });
    }
}
