package com.annimon.stream.function;

import java.lang.Throwable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface ThrowableIntFunction<R, E extends Throwable> {
    R apply(int i) throws Throwable;
}
