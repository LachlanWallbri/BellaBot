package com.annimon.stream.function;

import java.lang.Throwable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface ThrowablePredicate<T, E extends Throwable> {
    boolean test(T t) throws Throwable;
}
