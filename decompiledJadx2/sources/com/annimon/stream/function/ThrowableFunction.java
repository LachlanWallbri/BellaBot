package com.annimon.stream.function;

import java.lang.Throwable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface ThrowableFunction<I, R, E extends Throwable> {
    R apply(I i) throws Throwable;
}
