package com.google.common.math;

import java.util.function.ObjDoubleConsumer;

/* compiled from: lambda */
/* renamed from: com.google.common.math.-$$Lambda$no_TxIFzLTgAj_T0Gi5Zx0u0Iks, reason: invalid class name */
/* loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$no_TxIFzLTgAj_T0Gi5Zx0u0Iks implements ObjDoubleConsumer {
    public static final /* synthetic */ $$Lambda$no_TxIFzLTgAj_T0Gi5Zx0u0Iks INSTANCE = new $$Lambda$no_TxIFzLTgAj_T0Gi5Zx0u0Iks();

    private /* synthetic */ $$Lambda$no_TxIFzLTgAj_T0Gi5Zx0u0Iks() {
    }

    @Override // java.util.function.ObjDoubleConsumer
    public final void accept(Object obj, double d) {
        ((StatsAccumulator) obj).add(d);
    }
}
