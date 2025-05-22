package com.google.common.base;

/* JADX WARN: Classes with same name are omitted:
  
 */
@FunctionalInterface
/* loaded from: classes3.dex */
public interface Predicate<T> extends java.util.function.Predicate<T> {
    boolean apply(T t);

    boolean equals(Object obj);

    @Override // java.util.function.Predicate
    default boolean test(T t) {
        return apply(t);
    }
}
