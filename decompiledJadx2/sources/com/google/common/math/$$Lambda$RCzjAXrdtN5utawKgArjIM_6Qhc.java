package com.google.common.math;

import java.util.function.BiConsumer;

/* compiled from: lambda */
/* renamed from: com.google.common.math.-$$Lambda$RCzjAXrdtN5utawKgArjIM_6Qhc, reason: invalid class name */
/* loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$RCzjAXrdtN5utawKgArjIM_6Qhc implements BiConsumer {
    public static final /* synthetic */ $$Lambda$RCzjAXrdtN5utawKgArjIM_6Qhc INSTANCE = new $$Lambda$RCzjAXrdtN5utawKgArjIM_6Qhc();

    private /* synthetic */ $$Lambda$RCzjAXrdtN5utawKgArjIM_6Qhc() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((StatsAccumulator) obj).addAll((StatsAccumulator) obj2);
    }
}
