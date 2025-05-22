package com.google.api.core;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface ApiFuture<V> extends Future<V> {
    void addListener(Runnable runnable, Executor executor);
}
