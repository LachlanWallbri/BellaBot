package com.google.common.collect;

import com.google.common.collect.CollectCollectors;
import java.util.function.Function;

/* compiled from: lambda */
/* renamed from: com.google.common.collect.-$$Lambda$VTjmVxYvQu0bNGkSQ_bnos31reY, reason: invalid class name */
/* loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$VTjmVxYvQu0bNGkSQ_bnos31reY implements Function {
    public static final /* synthetic */ $$Lambda$VTjmVxYvQu0bNGkSQ_bnos31reY INSTANCE = new $$Lambda$VTjmVxYvQu0bNGkSQ_bnos31reY();

    private /* synthetic */ $$Lambda$VTjmVxYvQu0bNGkSQ_bnos31reY() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((CollectCollectors.EnumMapAccumulator) obj).toImmutableMap();
    }
}
