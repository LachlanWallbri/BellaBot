package com.annimon.stream.function;

import java.lang.Throwable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface ThrowableSupplier<T, E extends Throwable> {
    T get() throws Throwable;
}
