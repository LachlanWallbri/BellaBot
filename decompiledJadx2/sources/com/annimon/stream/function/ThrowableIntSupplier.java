package com.annimon.stream.function;

import java.lang.Throwable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface ThrowableIntSupplier<E extends Throwable> {
    int getAsInt() throws Throwable;
}
