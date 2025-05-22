package com.google.common.math;

import java.util.function.ObjIntConsumer;

/* compiled from: lambda */
/* renamed from: com.google.common.math.-$$Lambda$N2aUQ0vhoZ_gqty4bDlD6FDa3jc, reason: invalid class name */
/* loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$N2aUQ0vhoZ_gqty4bDlD6FDa3jc implements ObjIntConsumer {
    public static final /* synthetic */ $$Lambda$N2aUQ0vhoZ_gqty4bDlD6FDa3jc INSTANCE = new $$Lambda$N2aUQ0vhoZ_gqty4bDlD6FDa3jc();

    private /* synthetic */ $$Lambda$N2aUQ0vhoZ_gqty4bDlD6FDa3jc() {
    }

    @Override // java.util.function.ObjIntConsumer
    public final void accept(Object obj, int i) {
        ((StatsAccumulator) obj).add(i);
    }
}
