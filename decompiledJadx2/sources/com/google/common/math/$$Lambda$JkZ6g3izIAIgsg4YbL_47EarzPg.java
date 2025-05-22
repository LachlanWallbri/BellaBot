package com.google.common.math;

import java.util.function.ObjLongConsumer;

/* compiled from: lambda */
/* renamed from: com.google.common.math.-$$Lambda$JkZ6g3izIAIgsg4YbL_47EarzPg, reason: invalid class name */
/* loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$JkZ6g3izIAIgsg4YbL_47EarzPg implements ObjLongConsumer {
    public static final /* synthetic */ $$Lambda$JkZ6g3izIAIgsg4YbL_47EarzPg INSTANCE = new $$Lambda$JkZ6g3izIAIgsg4YbL_47EarzPg();

    private /* synthetic */ $$Lambda$JkZ6g3izIAIgsg4YbL_47EarzPg() {
    }

    @Override // java.util.function.ObjLongConsumer
    public final void accept(Object obj, long j) {
        ((StatsAccumulator) obj).add(j);
    }
}
