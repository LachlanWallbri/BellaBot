package com.google.common.collect;

import java.util.Map;
import java.util.function.Function;

/* compiled from: lambda */
/* renamed from: com.google.common.collect.-$$Lambda$73uG8GvDFSgCg7ViZNTbzvdqilI, reason: invalid class name */
/* loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$73uG8GvDFSgCg7ViZNTbzvdqilI implements Function {
    public static final /* synthetic */ $$Lambda$73uG8GvDFSgCg7ViZNTbzvdqilI INSTANCE = new $$Lambda$73uG8GvDFSgCg7ViZNTbzvdqilI();

    private /* synthetic */ $$Lambda$73uG8GvDFSgCg7ViZNTbzvdqilI() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((Map.Entry) obj).getValue();
    }
}
