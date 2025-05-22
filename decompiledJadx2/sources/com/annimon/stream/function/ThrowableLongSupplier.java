package com.annimon.stream.function;

import java.lang.Throwable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface ThrowableLongSupplier<E extends Throwable> {
    long getAsLong() throws Throwable;
}
