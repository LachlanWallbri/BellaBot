package com.google.common.collect;

import com.google.common.collect.CollectCollectors;
import java.util.function.BinaryOperator;

/* compiled from: lambda */
/* renamed from: com.google.common.collect.-$$Lambda$eV01jdA0Vgsswcl1z5-Zz-PmMV4, reason: invalid class name */
/* loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$eV01jdA0Vgsswcl1z5ZzPmMV4 implements BinaryOperator {
    public static final /* synthetic */ $$Lambda$eV01jdA0Vgsswcl1z5ZzPmMV4 INSTANCE = new $$Lambda$eV01jdA0Vgsswcl1z5ZzPmMV4();

    private /* synthetic */ $$Lambda$eV01jdA0Vgsswcl1z5ZzPmMV4() {
    }

    @Override // java.util.function.BiFunction
    public final Object apply(Object obj, Object obj2) {
        return ((CollectCollectors.EnumMapAccumulator) obj).combine((CollectCollectors.EnumMapAccumulator) obj2);
    }
}
