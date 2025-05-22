package com.google.common.util.concurrent;

import com.google.errorprone.annotations.DoNotMock;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* JADX WARN: Classes with same name are omitted:
  
 */
@DoNotMock("Use the methods in Futures (like immediateFuture) or SettableFuture")
/* loaded from: classes3.dex */
public interface ListenableFuture<V> extends Future<V> {
    void addListener(Runnable runnable, Executor executor);
}
