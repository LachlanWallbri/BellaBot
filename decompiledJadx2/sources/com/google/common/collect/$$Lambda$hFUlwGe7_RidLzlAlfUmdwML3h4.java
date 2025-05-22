package com.google.common.collect;

import java.util.Map;
import java.util.function.Function;

/* compiled from: lambda */
/* renamed from: com.google.common.collect.-$$Lambda$hFUlwGe7_RidLzlAlfUmdwML3h4, reason: invalid class name */
/* loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$hFUlwGe7_RidLzlAlfUmdwML3h4 implements Function {
    public static final /* synthetic */ $$Lambda$hFUlwGe7_RidLzlAlfUmdwML3h4 INSTANCE = new $$Lambda$hFUlwGe7_RidLzlAlfUmdwML3h4();

    private /* synthetic */ $$Lambda$hFUlwGe7_RidLzlAlfUmdwML3h4() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((Map.Entry) obj).getKey();
    }
}
