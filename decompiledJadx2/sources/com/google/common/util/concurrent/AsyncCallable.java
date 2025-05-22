package com.google.common.util.concurrent;

/* JADX WARN: Classes with same name are omitted:
  
 */
@FunctionalInterface
/* loaded from: classes3.dex */
public interface AsyncCallable<V> {
    ListenableFuture<V> call() throws Exception;
}
