package com.google.common.graph;

import com.google.errorprone.annotations.DoNotMock;

/* JADX WARN: Classes with same name are omitted:
  
 */
@DoNotMock("Implement with a lambda, or use GraphBuilder to build a Graph with the desired edges")
/* loaded from: classes3.dex */
public interface PredecessorsFunction<N> {
    Iterable<? extends N> predecessors(N n);
}
