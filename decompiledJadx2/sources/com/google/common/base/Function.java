package com.google.common.base;

/* JADX WARN: Classes with same name are omitted:
  
 */
@FunctionalInterface
/* loaded from: classes3.dex */
public interface Function<F, T> extends java.util.function.Function<F, T> {
    @Override // java.util.function.Function
    T apply(F f);

    boolean equals(Object obj);
}
